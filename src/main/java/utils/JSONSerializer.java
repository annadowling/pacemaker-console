package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import java.io.*;
import java.util.Stack;

/**
 * Created by annadowling on 04/10/2016.
 * JSONSerializer serialises data into JSON which outputs to file format .JSON
 */

public class JSONSerializer implements Serializer {

    @SuppressWarnings("unchecked")
    private Stack stack = new Stack<>();
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

    /**
     * We create an Xstream JSON object using JettisonMappedXmlDriver.
     * The ObjectInputStream is assigned to the out put of xstream.createObjectInputStream
     * createObjectInputStream is called on the xstream object and the file is passed into this method as a FileReader object.
     * The ObjectInputStream is then read to the stack.
     * Finally the stream is closed so that this stream is not left in memory.
     */
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
    /**
     * We create an Xstream JSON object using JettisonMappedXmlDriver.
     * The ObjectOutputStream is assigned to the out put of xstream.createObjectOutputStream
     * createObjectOutputStream is called on the xstream object and the file is passed into this method as a FileWriter object.
     * The ObjectOutputStream is then written.
     * Finally the stream is closed so that this stream is not left in memory.
     */
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
