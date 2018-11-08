package com.gaian.mail.bpe.model;

import java.util.List;

public class BpeRequest {

    
    private String presentationId;
    
    private String baseFolder;
    
    private String target;
    
    private String ecosystemId;
    
    private String publishId;
    
    private String coid;
    
    private String quality;
    
    private String compression;
    
    private String jsonFirst;
    
    private String groupType;
    
    private String groupId;
    
    private List<String> deviceIds = null;
    
    private String targetFolderPath;
    
    private String communicationType;
    
    private List<ConvertableImage> convertableImages = null;
    
    private List<Object> videos = null;
    
    private List<Object> nonConvertableImages = null;
    
    private List<Object> jsons = null;
    
    private String callbackURL;

    
    public String getPresentationId() {
        return presentationId;
    }

    
    public void setPresentationId(String presentationId) {
        this.presentationId = presentationId;
    }

    
    public String getBaseFolder() {
        return baseFolder;
    }

    
    public void setBaseFolder(String baseFolder) {
        this.baseFolder = baseFolder;
    }

    
    public String getTarget() {
        return target;
    }

    
    public void setTarget(String target) {
        this.target = target;
    }

    
    public String getEcosystemId() {
        return ecosystemId;
    }

    
    public void setEcosystemId(String ecosystemId) {
        this.ecosystemId = ecosystemId;
    }

    
    public String getPublishId() {
        return publishId;
    }

    
    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    
    public String getCoid() {
        return coid;
    }

    
    public void setCoid(String coid) {
        this.coid = coid;
    }

    
    public String getQuality() {
        return quality;
    }

    
    public void setQuality(String quality) {
        this.quality = quality;
    }

    
    public String getCompression() {
        return compression;
    }

    
    public void setCompression(String compression) {
        this.compression = compression;
    }

    
    public String getJsonFirst() {
        return jsonFirst;
    }

    
    public void setJsonFirst(String jsonFirst) {
        this.jsonFirst = jsonFirst;
    }

    
    public String getGroupType() {
        return groupType;
    }

    
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    
    public String getGroupId() {
        return groupId;
    }

    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    
    public List<String> getDeviceIds() {
        return deviceIds;
    }

    
    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }

    
    public String getTargetFolderPath() {
        return targetFolderPath;
    }

    
    public void setTargetFolderPath(String targetFolderPath) {
        this.targetFolderPath = targetFolderPath;
    }

    
    public String getCommunicationType() {
        return communicationType;
    }

    
    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    
    public List<ConvertableImage> getConvertableImages() {
        return convertableImages;
    }

    
    public void setConvertableImages(List<ConvertableImage> convertableImages) {
        this.convertableImages = convertableImages;
    }

    
    public List<Object> getVideos() {
        return videos;
    }

    
    public void setVideos(List<Object> videos) {
        this.videos = videos;
    }

    
    public List<Object> getNonConvertableImages() {
        return nonConvertableImages;
    }

    
    public void setNonConvertableImages(List<Object> nonConvertableImages) {
        this.nonConvertableImages = nonConvertableImages;
    }

    
    public List<Object> getJsons() {
        return jsons;
    }

    
    public void setJsons(List<Object> jsons) {
        this.jsons = jsons;
    }

    
    public String getCallbackURL() {
        return callbackURL;
    }

    
    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }

}