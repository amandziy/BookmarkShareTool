package org.softserveinc.rest;

import com.google.gson.Gson;
import org.softserveinc.DTO.BookmarkDTO;
import org.softserveinc.converters.JsonBuilderDirector;
import org.softserveinc.service.BookmarkService;
import org.softserveinc.util.ReferenceType;
import org.softserveinc.util.json.AbstractJsonObject;
import org.softserveinc.util.json.JsonFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import static org.softserveinc.util.Constants.DEFAULT_JSON_CLIENT;


@RestController
public class BookmarkRestController {
    @Inject
    private BookmarkService bookmarkService;

    @Inject
    JsonFactory jsonFactory;

    @RequestMapping(value = "/rest/bookmark", method = RequestMethod.POST)
    public void createNewBookmark(@RequestBody BookmarkDTO bookmarkDTO) {
        bookmarkService.saveBookmark(bookmarkDTO);
    }

    @RequestMapping(value = "/rest/team/bookmarks/{teamId}", method = RequestMethod.GET)
    public String getTreeOfBookmarksByTeamId(@PathVariable("teamId") String teamId) {
        JsonBuilderDirector director = jsonFactory.getJsonBuilderDirector(DEFAULT_JSON_CLIENT);
        AbstractJsonObject jsonObject = director.buildJson(bookmarkService, ReferenceType.TEAM, teamId);
        return new Gson().toJson(jsonObject);
    }

    @RequestMapping(value = "/rest/user/bookmarks/{userId}", method = RequestMethod.GET)
    public String getTreeOfBookmarksByUserId(@PathVariable("userId") String userId) {
        JsonBuilderDirector director = jsonFactory.getJsonBuilderDirector(DEFAULT_JSON_CLIENT);
        AbstractJsonObject jsonObject = director.buildJson(bookmarkService,ReferenceType.USER, userId);
        return new Gson().toJson(jsonObject);
    }

}
