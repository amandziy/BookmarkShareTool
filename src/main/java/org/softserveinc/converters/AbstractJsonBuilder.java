package org.softserveinc.converters;


import org.softserveinc.domain.Bookmark;
import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.service.BookmarkService;
import org.softserveinc.util.ReferenceType;
import org.softserveinc.util.json.AbstractJsonObject;

import java.util.List;
import java.util.Map;

public abstract class AbstractJsonBuilder {
    BookmarkService bookmarkService;
    List<BookmarkReference> bookmarkReferences;
    Map<Integer, Bookmark> mapOfIdsAndBookmarks;

    void setBookmarkService(BookmarkService bookmarkService){
        this.bookmarkService = bookmarkService;
    }

    void setMapOfIdsAndBookmarks(){
        mapOfIdsAndBookmarks = bookmarkService.getMapOfIdsAndBookmarks(bookmarkReferences);
    }

    void setBookmarkReferencesByOwnerId(ReferenceType owner, String referenceId){
        switch (owner){
            case TEAM: bookmarkReferences = bookmarkService.getBookmarkReferencesByTeamId(referenceId);
                break;
            case USER: bookmarkReferences = bookmarkService.getBookmarkReferencesByUserId(referenceId);
                break;
        }
    }

    abstract AbstractJsonObject buildJsonByOwnerId();
}
