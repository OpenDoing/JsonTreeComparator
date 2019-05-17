package com.doing.compare;

/**
 * 抽象类，将子模块中共有的方法在这里提供默认实现
 */
public abstract class AbstractCompare implements Comparator{

    /**
     * 简单类型的比较
     * @param lobject 待比较对象1
     * @param robject 待比较对象2
     * @return 比较结果
     */
    CompareResult SimpleCompare(Object lobject, Object robject){

        return null;
    }

}
