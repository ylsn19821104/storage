package com.l1.entity;

/**
 * Created by luopotaotao on 2016/5/7.
 */
public class Image {
    private Integer id;
    private String comment;
    private String suffix;
    public Image() {
    }

    public Image(Integer id, String comment, String suffix) {
        this.id = id;
        this.comment = comment;
        this.suffix = suffix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
