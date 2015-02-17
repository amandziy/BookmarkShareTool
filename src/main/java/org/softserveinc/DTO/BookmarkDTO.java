package org.softserveinc.DTO;

public class BookmarkDTO {

    private String name;
    private String url;
    private String path;
    private String description;

    public BookmarkDTO(String name, String url, String path, String description) {
        this.name = name;
        this.url = url;
        this.path = path;
        this.description = description;
    }
    public BookmarkDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
