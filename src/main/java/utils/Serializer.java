package utils;

/**
 * Created by annadowling on 28/09/2016.
 * Interface to handle serialization methods to be implemented in other classes using file formats including Binary, JSON, YAML and XML.
 */

public interface Serializer {
    void push(Object o);

    Object pop();

    void write() throws Exception;

    void read() throws Exception;
}