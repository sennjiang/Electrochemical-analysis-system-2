package bluedot.electrochemistry.service;

import bluedot.electrochemistry.exception.IllegalIndexException;

/**
 * @author Senn
 * @Create 2021/12/16 18:58
 */
public abstract class Direction<T> implements Lifecycle{

    protected T[] indexs;

    protected int capacity;

    public abstract T get(int index) throws IllegalIndexException, IllegalIndexException;

    @Override
    public void destroy() {
        indexs = null;
    }
}
