package utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonToJava {

    private static ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
    }

        
    public static <T> T convertJsonToJavaObject(String json, Class<T> cls){ //Generic

        T javaResult = null;

        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaResult;
    }

        
    public static <T> T convertJsonToJavaObject(File file, Class<T> cls){

        T javaResult = null;

        try {
            javaResult = mapper.readValue(file, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaResult;
    }
}
