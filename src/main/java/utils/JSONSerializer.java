package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import java.io.*;
import java.util.Stack;

/**
 * Created by annadowling on 04/10/2016.
 */

public class JSONSerializer implements Serializer {

    private Stack stack = new Stack();
    private File file;

    public JSONSerializer(File file) {
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
        ObjectInputStream is = null;
        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            is = xstream.createObjectInputStream(new FileReader(file));
            stack = (Stack) is.readObject();

        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public void write() throws Exception {
        ObjectOutputStream os = null;
        try {
            XStream xstream = new XStream(new JettisonMappedXmlDriver());
            os = xstream.createObjectOutputStream(new FileWriter(file));
            os.writeObject(stack);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
