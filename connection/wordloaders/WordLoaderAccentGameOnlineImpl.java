package org.example.connection.wordloaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.squareup.okhttp.ResponseBody;
import org.example.accent.AccentGameEntry;
import org.example.spelling.SpellingGameEntry;

import java.io.IOException;
import java.util.List;

public class WordLoaderAccentGameOnlineImpl extends WordLoaderOnlineImpl<Integer, AccentGameEntry> {

    @Override
    public List<? extends AccentGameEntry> Load() throws IOException {
        ResponseBody body = getResponseBody(getURL());
        return loadList(body, AccentGameEntry.class);
    }

}
