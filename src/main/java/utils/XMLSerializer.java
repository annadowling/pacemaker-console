package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

/**
 * Created by annadowling on 28/09/2016.
 * XMLSerializer serialises data into XML which outputs to file format .xml
 */
public class XMLSerializer implements Serializer {

    private Stack stack = new Stack();
    private File file;

    public XMLSerializer(File file) {
        this.file = file;
    }

    public void push(Object o) {
        stack.push(o);
    }

    public Object pop() {
        return stack.pop();
    }

    /**
     * We create an Xstream XML object using DomDriver.
     * The ObjectInputStream is assigned to the out put of xstream.createObjectInputStream
     * createObjectInputStream is called on the xstream object and the file is passed into this method as a FileReader object.
     * The ObjectInputStream is then read to the stack.
     * Finally the stream is closed so that this stream is not left in memory.
     */
    @SuppressWarnings("unchecked")
    public void read() throws Exception {
        ObjectInputStream is = null;

        try {
            XStream xstream = new XStream(new DomDriver());
            is = xstream.createObjectInputStream(new FileReader(file));
            stack = (Stack) is.readObject();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * We create an Xstream XML object using DomDriver.
     * The ObjectOutputStream is assigned to the out put of xstream.createObjectOutputStream
     * createObjectOutputStream is called on the xstream object and the file is passed into this method as a FileWriter object.
     * The ObjectOutputStream is then written.
     * Finally the stream is closed so that this stream is not left in memory.
     */
    public void write() throws Exception {
        ObjectOutputStream os = null;

        try {
            XStream xstream = new XStream(new DomDriver());
            os = xstream.createObjectOutputStream(new FileWriter(file));
            os.writeObject(stack);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}