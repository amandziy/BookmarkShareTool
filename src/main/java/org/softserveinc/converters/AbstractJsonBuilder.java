package org.softserveinc.converters;


import org.softserveinc.domain.Bookmark;
import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.service.BookmarkService;
import org.softserveinc.util.ReferenceType;
import org.softserveinc.util.json.AbstractJsonObject;

import java.util.List;
import java.util.Map;


/**
 * Design pattern "Builder" is used.
 * This class provides required preparation steps for building Json tree with bookmarks.
 */
public abstract class AbstractJsonBuilder {
    BookmarkService bookmarkService;
    List<BookmarkReference> bookmarkReferences;
    Map<Integer, Bookmark> bookmarkMap;

    void setBookmarkService(BookmarkService bookmarkService){
        this.bookmarkService = bookmarkService;
    }

    void setBookmarkMap(){
        bookmarkMap = bookmarkService.getBookmarkMap(bookmarkReferences);
    }

    void setBookmarkReferencesByOwnerId(ReferenceType ownerReferenceType, String referenceId){
        switch (ownerReferenceType){
            case TEAM: bookmarkReferences = bookmarkService.getBookmarkReferencesByTeamId(referenceId);
                break;
            case USER: bookmarkReferences = bookmarkService.getBookmarkReferencesByUserId(referenceId);
                break;
        }
    }

    abstract AbstractJsonObject buildJsonByOwnerId();
}
