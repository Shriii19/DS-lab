import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            Calculator calc = CalculatorHelper.narrow(ncRef.resolve_str("Calculator"));

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter two numbers: ");
            int a = sc.nextInt();
            int b = sc.nextInt();

            System.out.println("Addition: " + calc.add(a, b));
            System.out.println("Subtraction: " + calc.sub(a, b));
            System.out.println("Multiplication: " + calc.mul(a, b));
            System.out.println("Division: " + calc.div(a, b));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}