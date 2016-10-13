package utils;

import java.io.*;
import java.util.Stack;

/**
 * Created by annadowling on 04/10/2016.
 */

public class BinarySerializer implements Serializer {

    private Stack stack = new Stack();
    private File file;

    public BinarySerializer(File file) {
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
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            ObjectInputStream objStream = new ObjectInputStream(inputStream);
            stack = (Stack) objStream.readObject();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    public void write() throws Exception {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            ObjectOutputStream objStream = new ObjectOutputStream(outputStream);
            objStream.writeObject(stack);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
