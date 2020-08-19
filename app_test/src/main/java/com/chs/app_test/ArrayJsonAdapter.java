package com.chs.app_test;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * @author chs
 * date: 2020-08-13 16:15
 * des:
 */
public class ArrayJsonAdapter implements JsonDeserializer<List<?>> {

    @Override
    public List<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(json.isJsonArray()){
            Gson gson = new Gson();
            return gson.fromJson(json,typeOfT);
        }else {
            return Collections.emptyList();
        }
    }
}
