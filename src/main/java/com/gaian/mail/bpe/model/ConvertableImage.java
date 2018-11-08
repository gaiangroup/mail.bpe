package com.gaian.mail.bpe.model;

public class ConvertableImage {


    private Boolean isGif;

    private Boolean isPanorama;

    private String filePath;

    private String text;


    public Boolean getIsGif() {
        return isGif;
    }


    public void setIsGif(Boolean isGif) {
        this.isGif = isGif;
    }


    public Boolean getIsPanorama() {
        return isPanorama;
    }


    public void setIsPanorama(Boolean isPanorama) {
        this.isPanorama = isPanorama;
    }


    public String getFilePath() {
        return filePath;
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

}