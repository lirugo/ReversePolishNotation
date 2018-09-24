import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        Stack<StringBuilder> output;
        StringBuilder input = null;

        while(true) {
            System.out.print("Enter your expression: ");

            try {
                input = new StringBuilder(console.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(input.toString().equals("exit"))
                return;

            RPN polish = new RPN(input);
            output = polish.getExpression();
            System.out.printf("In Polish reverse notation is: %s", output);
            System.out.println();

            System.out.printf("Solution: %.2f", polish.getSolution());
            System.out.println();
        }
    }

}
