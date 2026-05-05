package calculator;
import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface CalculatorInterface {
    @WebMethod
    int add(int a, int b);
}