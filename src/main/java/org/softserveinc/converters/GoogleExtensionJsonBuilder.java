package org.softserveinc.converters;

import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.util.ReferenceType;
import org.softserveinc.util.json.GoogleJson;
import org.softserveinc.util.json.GoogleTreeNode;
import org.softserveinc.util.json.NodeType;

import java.util.*;

import static org.softserveinc.util.Constants.ROOT_FOLDER;

public class GoogleExtensionJsonBuilder extends AbstractJsonBuilder{

    private int treeNodeId=0;

    @Override
    public GoogleJson buildJsonByOwnerId() {


        GoogleJson googleJson =new GoogleJson();
        if(bookmarkReferences.size() == 0) {
            return googleJson;
        }
        Collections.sort(bookmarkReferences, new Comparator<BookmarkReference>() {
            @Override
            public int compare(BookmarkReference o1, BookmarkReference o2) {
                return o1.getPath().compareTo(o2.getPath());

            }
        });


        GoogleTreeNode synced = googleJson.getRoots().getSynced();
        synced.setChildren(new ArrayList<GoogleTreeNode>());

        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            List<String> chainOfFolders = new ArrayList<>(Arrays.asList(bookmarkReference.getPath().split("/")));
            buildTree(chainOfFolders, synced, bookmarkReference);
        }
        return googleJson;


    }


    private void buildTree( List<String> chainOfFolders, GoogleTreeNode parentNode, BookmarkReference bookmarkReference){
        // chainOfFolders.size()==0 means we created all chain of folders and now we should create bookmark
        // chainOfFolders.get(0).equals("_root") when bookmark is in root
        if(chainOfFolders.size()==0||chainOfFolders.get(0).equals(ROOT_FOLDER)){
            parentNode.getChildren().add(new GoogleTreeNode(null,
                    bookmarkReference.getCreated(),
                    bookmarkReference.getCreated()
                    ,++treeNodeId,
                    mapOfIdsAndBookmarks.get(bookmarkReference.getBookmarkId()).getName(),
                    NodeType.url,
                    mapOfIdsAndBookmarks.get(bookmarkReference.getBookmarkId()).getURL()));
            return;
        }
        //Logic to find out is next node bookmark or folder
        for (GoogleTreeNode existingNode : parentNode.getChildren()) {

            if(chainOfFolders.get(0).equals(existingNode.getName())){
                if(chainOfFolders.size()>1){
                    chainOfFolders.remove(0);
                    buildTree(chainOfFolders, existingNode,bookmarkReference);
                    return;
                }
                    buildTree(new ArrayList<String>(), existingNode, bookmarkReference);
                    return;

            }
        }

        //Adding folder
        GoogleTreeNode newFolderNode = new GoogleTreeNode(new ArrayList<GoogleTreeNode>(),
                bookmarkReference.getCreated(),
                bookmarkReference.getCreated()
                ,++treeNodeId,
                chainOfFolders.get(0),
                NodeType.folder,
                null);
        parentNode.getChildren().add(newFolderNode);
        chainOfFolders.remove(0);
        buildTree(chainOfFolders,newFolderNode,bookmarkReference);
    }
}
