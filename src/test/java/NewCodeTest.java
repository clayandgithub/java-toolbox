import com.clayoverwind.toolbox.io.LineIterableFile;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.*;

/**
 * Created by wangweiwei on 2017/3/20.
 */
public class NewCodeTest {

//    @Test
//    public void testLineIterableFile() throws IOException, InterruptedException, BrokenBarrierException {
//        LineIterableFile file = new LineIterableFile(new File("pom.xml"));
//        for (String line : file) {
//            System.out.println(line);
//        }
//        file.close();
//        printA();
//    }



    @Test
    public void testExample() {
        Example.print();
        Example.getInstance();
    }
}

class Example {
    private Example(){}

    private static class ExampleInstanceHolder {
        static {
            System.out.println("ExampleInstanceHolder loaded!");
        }
        static final Example INSTANCE = new Example();
    }

    public static Example getInstance() {
        System.out.println("getInstance called!");
        return ExampleInstanceHolder.INSTANCE;
    }

    public static void print() {
        System.out.println("print");
    }
}