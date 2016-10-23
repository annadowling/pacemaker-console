package utils;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.*;
import java.util.Stack;

/**
 * Created by annadowling on 04/10/2016.
 * YAMLSerializer serialises data into YAML which outputs to file format .yml
 */

public class YAMLSerializer implements Serializer {

    private Stack stack = new Stack();
    private File file;

    public YAMLSerializer(File file) {
        this.file = file;
    }

    public void push(Object o) {
        stack.push(o);
    }

    public Object pop() {
        return stack.pop();
    }

    /**
     * The read method reads file data using YamlReader which is passed a FileReader object.
     * YamlReader creates the stack object to be read which is assigned to the stack object.
     * Finally the stream is closed so that this stream is not left in memory.
     */
    @SuppressWarnings("unchecked")
    public void read() throws Exception {
        YamlReader reader = null;
        try {
            reader = new YamlReader(new FileReader(file));
            stack = (Stack) reader.read();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * The write method writes file data using YamlWriter which is passed a FileWriter object.
     * YamlWriter creates the stack object to be written to, which we call .write on.
     * Finally the stream is closed so that this stream is not left in memory.
     */
    public void write() throws Exception {
        YamlWriter writer = null;
        try {
            writer = new YamlWriter(new FileWriter(file));
            writer.write(stack);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
