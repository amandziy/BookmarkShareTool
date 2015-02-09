package org.softserveinc.converters;

import org.softserveinc.domain.Bookmark;
import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.util.ReferenceType;
import org.softserveinc.util.json.TreeNode;

import java.util.*;

import static org.softserveinc.util.Constants.ROOT_FOLDER;

public class DefaultJsonBuilder extends AbstractJsonBuilder{

    @Override
    public TreeNode buildJsonByOwnerId() {

        TreeNode mainTreeNode = new TreeNode();
        mainTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

        if(bookmarkReferences.size() == 0) {
            return mainTreeNode;
        }

        List<String> pathAndIds = new ArrayList<String>();

        //get tree of the folders
        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            String path = bookmarkReference.getPath();

            if(path.equals(ROOT_FOLDER)) {
                Bookmark bookmark = mapOfIdsAndBookmarks.get(bookmarkReference.getBookmarkId());

                TreeNode treeNode = new TreeNode();
                treeNode.setBookmark(bookmark);
                treeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

                mainTreeNode.getListOfTreeNodes().add(treeNode);
            } else {
                String pathAndId = path + ":" + bookmarkReference.getBookmarkId();
                pathAndIds.add(pathAndId);
            }
        }

        if (pathAndIds.isEmpty()) {
            return mainTreeNode;
        }

        Collections.sort(pathAndIds);

        for (String pathAndId : pathAndIds) {
            String path = pathAndId.substring(0, pathAndId.indexOf(":"));
            List<String> chainOfFolders = Arrays.asList(path.split("/"));
            Integer bookmarkId = Integer.parseInt(pathAndId.substring(pathAndId.indexOf(":") + 1));

            buildTree(chainOfFolders, mainTreeNode, bookmarkId, mapOfIdsAndBookmarks);
        }

        return mainTreeNode;
    }

    private Boolean buildTree(List<String> chainOfFolders, TreeNode parentTreeNode, Integer bookmarkId, Map<Integer, Bookmark> mapOfIdsAndBookmarks) {
        boolean flag = true;
        List<String> innerChainOfFolders = new ArrayList<String>(chainOfFolders);
        String folder = chainOfFolders.get(0);
        List<TreeNode> parentTreeNodes = parentTreeNode.getListOfTreeNodes();

        List<TreeNode> parentTreeNodesFoldersOnly = new ArrayList<TreeNode>();
        for (TreeNode treeNode : parentTreeNodes) {
            if(treeNode.getFolderName() != null) {
                parentTreeNodesFoldersOnly.add(treeNode);
            }
        }

        for (TreeNode treeNode : parentTreeNodesFoldersOnly) {

            if(treeNode.getFolderName().equals(folder)) {
                innerChainOfFolders.remove(0);

                if(innerChainOfFolders.size() > 0) {
                    flag = buildTree(innerChainOfFolders, treeNode, bookmarkId, mapOfIdsAndBookmarks);
                } else {
                    Bookmark bookmark = mapOfIdsAndBookmarks.get(bookmarkId);

                    TreeNode nestedTreeNode = new TreeNode();
                    nestedTreeNode.setBookmark(bookmark);
                    nestedTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

                    treeNode.getListOfTreeNodes().add(nestedTreeNode);

                    return false;
                }
            }
        }

        if(flag) {
            createParallelBranch(innerChainOfFolders, parentTreeNode, bookmarkId, mapOfIdsAndBookmarks);
        }

        flag = false;
        return flag;
    }

    private void createParallelBranch(List<String> chainOfFolders, TreeNode parentTreeNode, Integer bookmarkId, Map<Integer, Bookmark> mapOfIdsAndBookmarks) {
        List<String> innerChainOfFolders = new ArrayList<String>(chainOfFolders);
        String folder = chainOfFolders.get(0);

        TreeNode treeNode = new TreeNode();
        treeNode.setFolderName(folder);
        treeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

        parentTreeNode.getListOfTreeNodes().add(0, treeNode);

        innerChainOfFolders.remove(0);
        if(innerChainOfFolders.size() > 0) {
            createParallelBranch(innerChainOfFolders, treeNode, bookmarkId, mapOfIdsAndBookmarks);
        } else  {
            Bookmark bookmark = mapOfIdsAndBookmarks.get(bookmarkId);

            TreeNode nestedTreeNode = new TreeNode();
            nestedTreeNode.setBookmark(bookmark);
            nestedTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

            treeNode.getListOfTreeNodes().add(nestedTreeNode);
        }
    }

}
