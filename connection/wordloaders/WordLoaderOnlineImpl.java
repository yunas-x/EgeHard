package org.example.connection.wordloaders;

import lombok.Getter;
import lombok.Setter;
import org.example.connection.interfaces.DeserializeEntries;
import org.example.connection.interfaces.GetResponseBody;
import org.example.standard.interfaces.WordLoader;

public abstract class WordLoaderOnlineImpl<TAnswer, TClass> implements WordLoader<TAnswer>,
        DeserializeEntries<TClass>,
        GetResponseBody {

    // ADD AS CONST
    @Getter
    @Setter
    String URL; // Substitute with yours


}
