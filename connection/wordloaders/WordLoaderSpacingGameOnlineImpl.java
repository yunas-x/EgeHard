package org.example.connection.wordloaders;

import com.squareup.okhttp.ResponseBody;
import org.example.spacing.SpacingGameEntry;
import org.example.spacing.SpacingVariants;

import java.io.IOException;
import java.util.List;

public class WordLoaderSpacingGameOnlineImpl extends WordLoaderOnlineImpl<SpacingVariants, SpacingGameEntry> {


    @Override
    public List<? extends SpacingGameEntry> Load() throws IOException {
        ResponseBody body = getResponseBody(getURL());
        return loadList(body, SpacingGameEntry.class);
    }
}
