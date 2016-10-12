package utils;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.*;
import java.util.Stack;

/**
 * Created by annadowling on 04/10/2016.
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
