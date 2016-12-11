package com.app.model.dto;

/**
 * Created by Damian on 2016-11-20.
 */
public class ImageDTO {

    private String name;

    private Long height;

    private Long width;

    private String token;

    private String extension;

    private byte[] content;

    private String newHeight;

    private String newWidth;

    private String newExtension;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getNewHeight() {
        return newHeight;
    }

    public void setNewHeight(String newHeight) {
        this.newHeight = newHeight;
    }

    public String getNewWidth() {
        return newWidth;
    }

    public void setNewWidth(String newWidth) {
        this.newWidth = newWidth;
    }

    public String getNewExtension() {
        return newExtension;
    }

    public void setNewExtension(String newExtension) {
        this.newExtension = newExtension;
    }

    public static class Builder {

        private ImageDTO imageDTO = new ImageDTO();

        public Builder setName(String name) {
            imageDTO.setName(name);
            return this;
        }

        public Builder setHeight(Long height) {
           imageDTO.setHeight(height);
            return this;
        }

        public Builder setWidth(Long width) {
            imageDTO.setWidth(width);
            return this;
        }

        public Builder setContent(byte[] content) {
            imageDTO.setContent(content);
            return this;
        }

        public Builder setToken(String token) {
            imageDTO.setToken(token);
            return this;
        }

        public Builder setExtension(String extension) {
            imageDTO.setExtension(extension);
            return this;
        }

        public Builder setNewHeight(String newHeight) {
            imageDTO.setNewHeight(newHeight);
            return this;
        }

        public Builder setNewWidth(String newWidth) {
            imageDTO.setNewWidth(newWidth);
            return this;
        }

        public Builder setNewExtension(String newExtension) {
            imageDTO.setNewExtension(newExtension);
            return this;
        }

        public ImageDTO build(){
            return imageDTO;
        }

    }
}
