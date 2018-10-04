import java.util.Stack;

public class RPN {

    private static final char END_CHAR = '$';

    private StringBuilder input;
    private Stack<StringBuilder> output = new Stack<>();

    //Class Constructor
    public RPN(StringBuilder input) {
        //Set start and end symbol $
        input.insert(0, END_CHAR);
        input.insert(input.length(), END_CHAR);

        this.input = input;
    }

    //Get Polish recording
    public Stack<StringBuilder> getExpression() {
        //Stack for operators
        Stack<Character> operators = new Stack<>();

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

    //Get Solution
    public Double getSolution(){
        Stack<Double> solution = new Stack<>();

        //Cycle for each item
        for (StringBuilder item : output) {
           if(isOperator(item.charAt(0))) {
               double a,b;
               b = Double.parseDouble(solution.pop().toString());
               a = Double.parseDouble(solution.pop().toString());
               solution.push(makeOperation(a, b, item.charAt(0)));
           }
           else {
                solution.push(Double.parseDouble(item.toString()));
           }
        }
        return solution.pop();
    }

    //Validate input
    public boolean validate(){
        int bracket = 0;

        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == '(')
                bracket++;
            else if(input.charAt(i) == ')')
                bracket--;

            if(!Character.isDigit(input.charAt(i)) && !isOperator(input.charAt(i)) && input.charAt(i) != END_CHAR) {
                System.out.println("ERROR INPUT DATA");
                return false;
            }
        }

        if(bracket != 0) {
            System.out.println("ERROR INPUT DATA");
            return false;
        }

        return true;
    }

    private Double makeOperation(double a, double b, char operator){
        switch (operator)
        {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            case '^': return 0.00;
            default: return 0.00;
        }
    }

    //Get priority of an operation
    private byte getPriority(char c) {
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
    private boolean isEndChar(char c) {
        return END_CHAR == c;
    }

    //Check is end symbol
    private boolean isDelimiter(char c) {
        return " ".indexOf(c) != -1;
    }

    //Check is operator
    private boolean isOperator(char c) {
        return "+-*/^()".indexOf(c) != -1;
    }
}
