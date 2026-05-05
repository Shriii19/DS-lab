package calculator;
import javax.jws.WebService;

@WebService(endpointInterface = "calculator.CalculatorInterface")
public class CalculatorService implements CalculatorInterface {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}