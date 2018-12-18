package com.zhaolearn.crawlertest.crawlerUrl;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name ="url_table" )
public class URLEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "file_name")
    private String fileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}