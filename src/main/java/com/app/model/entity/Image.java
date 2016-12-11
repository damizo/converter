package com.app.model.entity;


import javax.persistence.*;

/**
 * Created by Damian on 2016-11-19.
 */

@Entity
@Table(name = "images")
public class Image extends BaseEntityFile {

    private Long height;

    private Long width;

    private Long newHeight;

    private Long newWidth;

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

    public Long getNewHeight() {
        return newHeight;
    }

    public void setNewHeight(Long newHeight) {
        this.newHeight = newHeight;
    }

    public Long getNewWidth() {
        return newWidth;
    }

    public void setNewWidth(Long newWidth) {
        this.newWidth = newWidth;
    }
}

