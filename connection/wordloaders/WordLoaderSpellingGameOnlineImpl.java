package org.example.connection.wordloaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.squareup.okhttp.ResponseBody;
import org.example.spelling.SpellingGameEntry;

import java.io.IOException;
import java.util.List;

public class WordLoaderSpellingGameOnlineImpl extends WordLoaderOnlineImpl<Character, SpellingGameEntry> {

    @Override
    public List<? extends SpellingGameEntry> Load() throws IOException {
        ResponseBody body = getResponseBody(getURL());
        return loadList(body, SpellingGameEntry.class);
    }

}
