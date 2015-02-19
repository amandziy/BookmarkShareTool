package org.softserveinc.domain;

import javax.persistence.*;

@Entity
@Table(name = "BOOKMARK")
public class Bookmark {

    private Integer bookmarkId;
    private String name;
    private String URL;
    private String description;

    public Bookmark() {
    }

    public Bookmark(String name, String URL, String description) {
        this.name = name;
        this.URL = URL;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Integer bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(nullable = false,name = "URL")
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
