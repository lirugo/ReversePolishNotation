import java.util.Stack;

public class RPN {

    private static final char END_CHAR = '$';

    //Make Polish expression
    public static Stack<StringBuilder> getExpression(StringBuilder input) {
        //String for expression
        Stack<StringBuilder> output = new Stack<>();

        //Stack for operators
        Stack<Character> operators = new Stack<>();

        //Set start and end symbol $
        input.insert(0, END_CHAR);
        input.insert(input.length(), END_CHAR);

        //First char set to stack
        operators.push(input.charAt(0));

        //For each char in input string
        for(int i=1; i<input.length(); i++){

            //Skip if its space
            if(isDelimiter(input.charAt(i)))
                continue;
            //If its digit
            else if(Character.isDigit(input.charAt(i))){
                StringBuilder digit = new StringBuilder();
                //Until is digit
                while (!isDelimiter(input.charAt(i)) && !isOperator(input.charAt(i)) && !isEndChar(input.charAt(i))){
                    //Add to output
                    digit.append(input.charAt(i));
                    i++;
                    if(i == input.length())
                        break;
                }
                output.push(digit);
                i--;
            }
            //If its operator
            else if(isOperator(input.charAt(i))){
                //If is '('
                if(input.charAt(i) == '(')
                    operators.push(input.charAt(i));

                //If is ')'
                else if(input.charAt(i) == ')'){
                    //If current operation have low priority
                    if(getPriority(input.charAt(i)) < getPriority(operators.peek())){
                        Character s = operators.pop();
                        while (s != '(')
                        {
                            output.push(new StringBuilder(s.toString()));
                            s = operators.pop();
                        }
                    //If current operation have high priority
                    }else if(getPriority(input.charAt(i)) > getPriority(operators.peek())){
                        //Generate error
                        try {
                            throw new Exception();
                        } catch (Exception e) {
                            System.out.println("ERROR INPUT DATA");
                            return null;
                        }
                        //If operation priority is equals
                    }else{
                        operators.pop();
                    }
                }

                else if(isOperator(input.charAt(i))) {
                    //If priority of current action lower or equals to priority in stack push operator from stack to output
                    if (getPriority(input.charAt(i)) <= getPriority(operators.peek()))
                        while (getPriority(input.charAt(i)) <= getPriority(operators.peek()))
                            output.push(new StringBuilder(operators.pop().toString()));
                    //Add to stack operators
                    operators.push(input.charAt(i));

                }

            }
            //If is end char
            else if(isEndChar(input.charAt(i))){
                Character s = operators.pop();
                while (s != END_CHAR)
                {
                    output.push(new StringBuilder(s.toString()));
                    s = operators.pop();
                }
            }
        }

        return output;
    }

    //Get priority of an operation
    private static byte getPriority(char c) {
        switch (c)
        {
            case '$': return 0;
            case '(': return 1;
            case ')': return 1;
            case '+': return 2;
            case '-': return 2;
            case '*': return 3;
            case '/': return 3;
            case '^': return 3;
            default: return 1;
        }
    }

    //Check is delimiter
    private static boolean isEndChar(char c) {
        return END_CHAR == c;
    }

    //Check is end symbol
    private static boolean isDelimiter(char c) {
        return " ".indexOf(c) != -1;
    }

    //Check is operator
    private static boolean isOperator(char c) {
        return "+-*/^()".indexOf(c) != -1;
    }
}
