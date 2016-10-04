package utils;

/**
 * Created by annadowling on 28/09/2016.
 */

public interface Serializer {
    void push(Object o);

    Object pop();

    void write() throws Exception;

    void read() throws Exception;
}