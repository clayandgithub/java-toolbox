package com.clayoverwind.toolbox.io;

import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by wangweiwei on 2017/3/20.
 */
public class LineIterableFile implements Iterable<String>{
    private BufferedReader bufferedReader;

    public LineIterableFile(File file) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            String nextLine = null;

            @Override
            public boolean hasNext() {
                if (nextLine != null) {
                    return true;
                } else {
                    try {
                        nextLine = bufferedReader.readLine();
                        return (nextLine != null);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public String next() {
                if (nextLine != null || hasNext()) {
                    String line = nextLine;
                    nextLine = null;
                    return line;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                throw new RuntimeException(new OperationNotSupportedException());
            }
        };
    }

    public void close() throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }
}