package org.softserveinc.rest;

import com.google.gson.Gson;
import org.softserveinc.converters.AbstractJsonBuilder;
import org.softserveinc.converters.JsonBuilderDirector;
import org.softserveinc.domain.User;
import org.softserveinc.service.BookmarkService;
import org.softserveinc.util.ReferenceType;
import org.softserveinc.util.json.AbstractJsonObject;
import org.softserveinc.util.json.JsonFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.softserveinc.util.Constants.GOOGLE_EXTENSION_JSON_CLIENT;

@RestController
public class ChromeBookmarkRestController {

    @Inject
    BookmarkService bookmarkService;
    @Inject
    JsonFactory jsonFactory;

    @RequestMapping(value = "/rest/bookmark/google", method = RequestMethod.GET)
    public String getBookmarksForChrome() {
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JsonBuilderDirector director = jsonFactory.getJsonBuilderDirector(GOOGLE_EXTENSION_JSON_CLIENT);
        AbstractJsonObject jsonObject = director.buildJson(bookmarkService,ReferenceType.USER, user.getUserId().toString());
        return new Gson().toJson(jsonObject);

    }
}
