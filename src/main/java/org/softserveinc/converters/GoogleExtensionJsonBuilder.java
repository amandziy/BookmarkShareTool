package org.softserveinc.converters;

import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.util.json.GoogleTreeNode;

import java.util.*;

import static org.softserveinc.util.Constants.ROOT_FOLDER;

/**
 * This class is used to build tree of bookmarks is the same format that Google Chrome uses.
 */
public class GoogleExtensionJsonBuilder extends AbstractJsonBuilder{

    private int treeNodeId=0;

    @Override
    public GoogleTreeNode buildJsonByOwnerId() {


        GoogleTreeNode googleTreeNode =new GoogleTreeNode();
        if(bookmarkReferences.size() == 0) {
            return googleTreeNode;
        }
        Collections.sort(bookmarkReferences, new Comparator<BookmarkReference>() {
            @Override
            public int compare(BookmarkReference o1, BookmarkReference o2) {
                return o1.getBookmarkIndex().compareTo(o2.getBookmarkIndex());

            }
        });



        googleTreeNode.setChildren(new ArrayList<GoogleTreeNode>());
        googleTreeNode.setId(++treeNodeId);

        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            List<String> chainOfFolders = new ArrayList<>(Arrays.asList(bookmarkReference.getPath().split("/")));
            buildTree(chainOfFolders, googleTreeNode, bookmarkReference);
        }
        return googleTreeNode;


    }

    private void buildTree( List<String> chainOfFolders, GoogleTreeNode parentNode, BookmarkReference bookmarkReference){

        // chainOfFolders.size()==0 means we created all chain of folders and now we should create bookmark
        // chainOfFolders.get(0).equals("_root") when bookmark is in root
        if(chainOfFolders.size()==0||chainOfFolders.get(0).equals(ROOT_FOLDER)){
            parentNode.getChildren().add(new GoogleTreeNode(
                    bookmarkReference.getCreated(),
                    ++treeNodeId,
                    bookmarkReference.getBookmarkIndex(),
                    parentNode.getId(),
                    bookmarkMap.get(bookmarkReference.getBookmarkId()).getName(),
                    bookmarkMap.get(bookmarkReference.getBookmarkId()).getURL()));
            return;
        }
        //Logic to find out is next node bookmark or folder
        for (GoogleTreeNode existingNode : parentNode.getChildren()) {

            if(chainOfFolders.get(0).equals(existingNode.getTitle())){
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
        GoogleTreeNode newFolderNode = new GoogleTreeNode(
                new ArrayList<GoogleTreeNode>(),
                bookmarkReference.getCreated(),
                bookmarkReference.getCreated()
                ,++treeNodeId,
                bookmarkReference.getBookmarkIndex(),
                parentNode.getId(),
                chainOfFolders.get(0));
        parentNode.getChildren().add(newFolderNode);
        chainOfFolders.remove(0);
        buildTree(chainOfFolders,newFolderNode,bookmarkReference);
    }
}
