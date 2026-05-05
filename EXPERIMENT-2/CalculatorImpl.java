import CalculatorModule.*;
import org.omg.CORBA.*;

public class CalculatorImpl extends CalculatorPOA {
    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public float div(int a, int b) {
        if (b == 0) return 0;
        return (float) a / b;
    }
}