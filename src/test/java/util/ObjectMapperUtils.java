package util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {
    //        <T> T -> Herhangi bir data tipi demek
//        ObjectMapper().readValue(json, cls) methodu birinci parametrede aldığı String formatındaki
//                                   Json datayı ijkinci parametrede belitilen Java objesine çevirir.
    public static <T> T convertJsonToJava(String json, Class<T> cls){ // Generic Method

        try {
            return new ObjectMapper().readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}