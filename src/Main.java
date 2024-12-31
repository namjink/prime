import java.util.Arrays;
import org.apache.commons.math3.*;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.Fraction;

public class Main {
    // https://chatgpt.com/share/67681256-b8c0-800b-9445-81f9606ad2fa
    static PrimeUtil pu = new PrimeUtil();
    static ExpressionUtil eu = new ExpressionUtil();

    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++) {
            System.out.println(i + " => " + pu.getPrimeFactors(i));
        }
    }
}