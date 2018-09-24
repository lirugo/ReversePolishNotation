import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output;
        String input = "";

        while(true) {
            System.out.print("Enter your expression: ");

            try {
                input = console.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(input.equals("exit"))
                return;

            output = RPN.getExpression(new StringBuilder(input));
            System.out.printf("In Polish reverse notation is: %s", output);
            System.out.println();
        }
    }

}
