package calculator;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class CalculatorClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/calculator?wsdl");
        QName qname = new QName("http://calculator/", "CalculatorService");
        Service service = Service.create(url, qname);
        CalculatorInterface calc = service.getPort(CalculatorInterface.class);

        int result = calc.add(10, 20);
        System.out.println("Result = " + result);
    }
}