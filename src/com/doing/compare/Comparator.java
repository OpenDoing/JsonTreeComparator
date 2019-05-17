package com.doing.compare;

/**
 * Top Interface,
 * @author doing
 */
public interface Comparator {

    /**
     * 顶层接口，比较器的本质
     * @param lobject 第一个参数
     * @param robject 第二个参数
     * @param type 参数之间的关系
     * @return 比较结果
     */
    CompareResult compare(Object lobject, Object robject, CompareType type);
}
