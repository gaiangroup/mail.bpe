package com.gaian.mail.bpe;

import com.gaian.mail.bpe.model.BpeRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication
            .run(Application.class, args);
        applicationContext.getBeanDefinitionNames();
    }




    @FeignClient(name = "bpe", url = "http://127.0.0.1:5000/todo/api/v1.0")
    public interface BpeClient {

        @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = "application/json")
        String convert(BpeRequest bpeRequest);
    }
}
