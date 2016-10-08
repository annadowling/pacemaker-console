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
        InputStream inputStream = null;
        byte[] buffer = new byte[(int) file.length()];
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            inputStream.read(buffer);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    public void write() throws Exception {
        OutputStream outputStream = null;
        byte[] buffer = new byte[(int) file.length()];
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(buffer);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
