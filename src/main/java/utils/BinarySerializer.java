package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        byte[] buffer = null;
        int length = (int) file.length();
        buffer = new byte[length];
        try {
            inputStream = new FileInputStream(file);
            inputStream.read(buffer);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    public void write() throws Exception {
        FileOutputStream outputStream = null;
        byte[] buffer = null;
        int length = (int) file.length();
        buffer = new byte[length];
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(buffer);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }


    }
}
