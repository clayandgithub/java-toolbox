import java.lang.reflect.Method;
import java.util.*;

public class Main {
    private static String[] dict = {"0", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(testOperation(1.0, 2.0,BasicOperation.PLUS));
        B a = new B();
        Number n = new Integer(1);
        a.print(n);
    }

    public static <T extends Enum<T> & Operation> double testOperation(double x, double y, T operation) {
        return operation.apply(x, y);
    }
}

interface Operation {
    double apply(double x, double y);
}

enum BasicOperation implements Operation {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    };
    private String symbol;
    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

}

class Wset<T> {
    private T[] f;
    public T[] getF(){
        return f;
    }
}

class WUtil {
    public static <T extends Number> Set<T> union(Set<? extends T> s1, Set<? extends T> s2) {
        Set<T> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }
}

class A {
    public void print(Number n) {
        System.out.println("A");
    }
}

class B extends A{
    public void print(Integer n) {
        System.out.println("B");
    }
}