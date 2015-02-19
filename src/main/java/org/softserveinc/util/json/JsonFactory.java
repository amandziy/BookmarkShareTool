package org.softserveinc.util.json;

import org.softserveinc.converters.DefaultJsonBuilder;
import org.softserveinc.converters.GoogleExtensionJsonBuilder;
import org.softserveinc.converters.JsonBuilderDirector;
import org.springframework.stereotype.Component;

import static org.softserveinc.util.Constants.DEFAULT_JSON_CLIENT;
import static org.softserveinc.util.Constants.GOOGLE_EXTENSION_JSON_CLIENT;

@Component
public class JsonFactory {
    public JsonBuilderDirector getJsonBuilderDirector(Integer jsonClient){
        JsonBuilderDirector director =new JsonBuilderDirector();
        switch (jsonClient){
            case GOOGLE_EXTENSION_JSON_CLIENT:
                director.setJsonBuilder(new GoogleExtensionJsonBuilder());
                break;
            case DEFAULT_JSON_CLIENT:
                director.setJsonBuilder(new DefaultJsonBuilder());
                break;
            default:
                director.setJsonBuilder(new DefaultJsonBuilder());
        }
        return director;

    }
}
