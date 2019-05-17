package com.doing.compare.submodule;

import com.doing.compare.AbstractCompare;
import com.doing.compare.CompareResult;
import com.doing.compare.CompareType;

/**
 * 等价，要求内容一致，顺序可以不一致
 */
public class Equivalence extends AbstractCompare {

    @Override
    public CompareResult compare(Object lobject, Object robject, CompareType type) {
        return null;
    }
}
