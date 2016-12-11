package com.app.model.entity;

import javax.persistence.*;

/**
 * Created by dami on 2016-11-28.
 */
@MappedSuperclass
public abstract class BaseEntityFile {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String token;

    private String newExtension;

    private String extension;

    @Lob
    private String encodedContent;

    @Lob
    private byte[] newFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewExtension() {
        if(newExtension == null){
            newExtension = extension;
        }
        return newExtension;
    }

    public void setNewExtension(String newExtension) {
        this.newExtension = newExtension;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEncodedContent() {
        return encodedContent;
    }

    public void setEncodedContent(String encodedContent) {
        this.encodedContent = encodedContent;
    }

    public byte[] getNewFile() {
        return newFile;
    }

    public void setNewFile(byte[] newFile) {
        this.newFile = newFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseEntityFile that = (BaseEntityFile) o;
        if (id == null && that.id == null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
