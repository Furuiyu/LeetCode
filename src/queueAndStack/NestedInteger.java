package queueAndStack;


import java.util.List;

/**
 * @author Fr
 */
public interface NestedInteger {

    /**
     *
     * return true if this NestedInteger holds a single integer, rather than a nested list.
     * @return return true if this NestedInteger holds a single integer, rather than a nested list.
     */
     public boolean isInteger();



    /**
     * Return null if this NestedInteger holds a nested list
     * @return the single integer that this NestedInteger holds, if it holds a single integer
     */
     public Integer getInteger();


    /**
     * Return null if this NestedInteger holds a single integer
     * @return the nested list that this NestedInteger holds, if it holds a nested list
     */
     public List<NestedInteger> getList();
 }