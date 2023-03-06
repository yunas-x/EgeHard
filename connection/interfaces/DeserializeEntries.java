package org.example.connection.interfaces;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;


public interface DeserializeEntries<TClass> {

    default List<? extends TClass> loadList(ResponseBody body, Class<? extends TClass> cls) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, cls);

        return mapper.readValue(body.byteStream(), type);
    }

}
