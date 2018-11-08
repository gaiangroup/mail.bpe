package com.gaian.mail.bpe.scheduler;

import com.gaian.mail.bpe.Application.BpeClient;
import com.gaian.mail.bpe.model.BpeRequest;
import com.gaian.mail.bpe.model.ConvertableImage;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.SortTerm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BpeScheduler {

    @Autowired
    private BpeClient client;

    @Value("${mail.user}")
    private String user;

    @Value("${mail.password}")
    private String password;

    @Scheduled(fixedDelay = 5000)
    public void scheduleBpe() throws MessagingException {
        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        Flag flag = null;
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);

            store = session.getStore("imaps");

            store.connect("imap.googlemail.com", user, password);

            folder = (IMAPFolder) store
                .getFolder("inbox"); // This doesn't work for other email account
            //folder = (IMAPFolder) store.getFolder("inbox"); This works for both email account

            if (!folder.isOpen()) {
                folder.open(Folder.READ_WRITE);
            }
            List<SortTerm> sortTerms = new ArrayList<SortTerm>();
            sortTerms.add(SortTerm.DATE);

            Message[] messages = folder.search(new SubjectTerm("notification:"));

            System.out.println("No of Messages : " + folder.getMessageCount());
            System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
            System.out.println(messages.length);
            for (int i = 0; i < messages.length; i++) {

                Message msg = messages[i];

                if (msg.isSet(Flag.SEEN)) {
                    continue;
                }

                System.out.println(
                    "*****************************************************************************");
                System.out.println("MESSAGE " + (i + 1) + ":");

                //System.out.println(msg.getMessageNumber());
                //Object String;
                //System.out.println(folder.getUID(msg)

                subject = msg.getSubject();
                if (!subject.contains("notification:")) {
                    continue;
                }

                System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: " + msg.getAllRecipients()[0]);
                System.out.println("Date: " + msg.getReceivedDate());
                System.out.println("Size: " + msg.getSize());
                System.out.println(msg.getFlags());
                System.out.println("Body: \n" + msg.getContent());
                System.out.println(msg.getContentType());

                msg.getContent();

                Multipart multipart = (Multipart) msg.getContent();
                List<File> attachments = new ArrayList<File>();

                File dir = new File(
                    "/tmp/" + msg.getMessageNumber());

                for (int j = 0; j < multipart.getCount(); j++) {
                    BodyPart bodyPart = multipart.getBodyPart(j);
                    if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) &&
                        StringUtils.isEmpty(bodyPart.getFileName())) {
                        continue; // dealing with attachments only
                    }
                    InputStream is = bodyPart.getInputStream();

                    dir.mkdir();

                    File f = new File(dir, bodyPart.getFileName());

                    FileOutputStream fos = new FileOutputStream(f);
                    byte[] buf = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = is.read(buf)) != -1) {
                        fos.write(buf, 0, bytesRead);
                    }
                    fos.close();
                    attachments.add(f);
                }

                client.convert(createBpeRequest(dir, attachments));

                msg.setFlag(Flag.SEEN, true);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (folder != null && folder.isOpen()) {
                folder.close(true);
            }
            if (store != null) {
                store.close();
            }
        }

    }

    private BpeRequest createBpeRequest(File dir, List<File> attachments) {
        BpeRequest request = new BpeRequest();

        List<ConvertableImage> convertableImages = new ArrayList<>();
        attachments.forEach(file -> {
            ConvertableImage image = new ConvertableImage();
            image.setIsPanorama(true);
            image.setIsGif(false);
            image.setFilePath(dir.getAbsolutePath());
            image.setText(file.getName());

            convertableImages.add(image);
        });
        request.setBaseFolder(dir.getAbsolutePath());
        request.setConvertableImages(convertableImages);

        request.setTargetFolderPath(Paths.get(dir.getAbsolutePath(), "panaroma").toAbsolutePath().toString());

        return request;
    }
}
