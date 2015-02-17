package org.softserveinc.util.json;

import java.sql.Date;
import java.util.List;


public class GoogleTreeNode extends AbstractJsonObject{
    private List<GoogleTreeNode> children;
    private Date dateAdded;
    private Date dateGroupModified;
    private Integer id;
    private Integer index;
    private Integer parentId;
    private String title;
    private transient NodeType type;
    private String url;

    //This constructor is used when node is a bookmark
    public GoogleTreeNode(Date dateAdded, Integer id, Integer index, Integer parentId, String title, String url) {
        this.dateAdded = dateAdded;
        this.id = id;
        this.index = index;
        this.parentId = parentId;
        this.title = title;
        this.type = NodeType.url;
        this.url = url;
    }

    //This constructor is used when node is a folder
    public GoogleTreeNode(List<GoogleTreeNode> children, Date dateAdded, Date dateGroupModified, Integer id, Integer index, Integer parentId, String title) {
        this.children = children;
        this.dateAdded = dateAdded;
        this.dateGroupModified = dateGroupModified;
        this.id = id;
        this.index = index;
        this.parentId = parentId;
        this.title = title;
        this.type = NodeType.folder;
    }

    //This constructor is used when node is a root folder
    public GoogleTreeNode(List<GoogleTreeNode> children, Date dateAdded, Integer id, String title) {
        this.children = children;
        this.dateAdded = dateAdded;
        this.id = id;
        this.title = title;
        this.type = NodeType.folder;

    }

    public GoogleTreeNode(){}

    public List<GoogleTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<GoogleTreeNode> children) {
        this.children = children;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateGroupModified() {
        return dateGroupModified;
    }

    public void setDateGroupModified(Date dateGroupModified) {
        this.dateGroupModified = dateGroupModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
