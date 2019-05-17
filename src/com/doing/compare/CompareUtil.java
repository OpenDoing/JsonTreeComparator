package com.doing.compare;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

public class CompareUtil {

    boolean isSimpleValue(Object o){

        return true;
    }

    List<Object> jsonArrayToList(Gson expected){

        return null;
    }

    Map<Object, JsonObject> arrayOfJsonObjectToMap(Gson array){

        return null;
    }

    int getJsonArrayLength(Gson array){

        return array.toJson(array).length();
    }
}
