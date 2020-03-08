package com.doing.compare.adapter;

import com.doing.compare.Comparator;
import com.doing.compare.CompareResult;
import com.doing.compare.CompareType;
import com.doing.compare.CompareUtil;
import com.google.gson.JsonObject;

/**
 * comment here
 *
 * @author Duyining
 * @date 2020/2/3
 */
public class CompareAdapter implements Comparator{

    JsonTreeComparator jsonTreeComparator = new JsonTreeComparator();

    @Override
    public CompareResult compare(Object lobject, Object robject, CompareType type) {
        CompareUtil compareUtil = new CompareUtil();
        return jsonTreeComparator.compare(compareUtil.jsonToTree((JsonObject) lobject),
                compareUtil.jsonToTree((JsonObject) robject) , type);
    }
}
