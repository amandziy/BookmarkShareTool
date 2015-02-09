package org.softserveinc.converters;

import org.softserveinc.service.BookmarkService;
import org.softserveinc.util.ReferenceType;
import org.softserveinc.util.json.AbstractJsonObject;

public class JsonBuilderDirector {
    private AbstractJsonBuilder jsonBuilder;

    public AbstractJsonObject buildJson(BookmarkService bookmarkService, ReferenceType owner, String ownerId){
        jsonBuilder.setBookmarkService(bookmarkService);
        jsonBuilder.setBookmarkReferencesByOwnerId(owner, ownerId);
        jsonBuilder.setMapOfIdsAndBookmarks();
        return jsonBuilder.buildJsonByOwnerId();
    }

    public void setJsonBuilder(AbstractJsonBuilder jsonBuilder) {
        this.jsonBuilder = jsonBuilder;
    }
}
