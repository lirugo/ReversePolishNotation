import java.util.Stack;

public class RPN {

    public static String getExpression(StringBuilder input) {
        //String for expression
        String output = "";

        //Stack for operators
        Stack<Character> operators = new Stack<>();

        //Set start and end symbol $
        input.insert(0, '$');
        input.insert(input.length(), '$');

        return null;
    }

    //Get priority of an operation
    private static byte getPriority(char c) {
        switch (c)
        {
            case '(': return 0;
            case ')': return 0;
            case '+': return 1;
            case '-': return 1;
            case '*': return 2;
            case '/': return 2;
            case '^': return 2;
            default: return 0;
        }
    }

    //Check is delimiter
    private static boolean isDelimiter(char c) {
        return " =".indexOf(c) != -1;
    }

    //Check is operator
    private static boolean isOperator(char c) {
        return "+-*/()".indexOf(c) != -1;
    }
}
