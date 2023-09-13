package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.security.Key;
import java.text.ParseException;
import java.util.Iterator;

public class JsonReader {

    public static String getTestData(String key) throws Exception {
        return (String) getJSONData_ObjectType().get(key);
    }

    public static JSONObject getJSONData_ObjectType() throws Exception
    {
        JSONParser jsonParser = new JSONParser();
        File file = new File("./resources/TestData/testData.json");
        FileReader fileReader = new FileReader(file);
        Object obj  = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;

    }

    public static JSONArray getJSONArray_FromJson(Object key) throws Exception
    {
        JSONObject jsonObject = getJSONData_ObjectType() ;
        JSONArray jsonArray =  (JSONArray) jsonObject.get(key);
        return jsonArray ;
    }

    public static Object getJSONArrayDate_FromJSONArray(String key, int index) throws Exception
    {
        JSONArray jsonArray = getJSONArray_FromJson(key);
        Iterator<String> iterator = jsonArray.iterator();
        return null;

    }
}
