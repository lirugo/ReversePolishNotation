import java.util.Stack;

public class RPN {

    public static String GetExpression(StringBuilder input){
        String output = ""; //String for expression
        Stack<Character> operators = new Stack<Character>(); //Stack for operators

        for(int i = 0; i < input.length(); i++){
            if(IsDelimeter(input.charAt(i)))
                continue;

            if(Character.isDigit(input.charAt(i))){
                while(!IsDelimeter(input.charAt(i)) && !IsOperator(input.charAt(i))){
                    output += input.charAt(i);
                    i++;

                    if(i == input.length()) {
                        i--;
                        break;
                    }
                }
            }

            if(IsOperator(input.charAt(i))){
                if(input.charAt(i) == '('){
                    operators.push(input.charAt(i));
                } else if(input.charAt(i) == ')'){
                    char s = operators.pop();

                    while(s != '('){
                        output += s;
                        s = operators.pop();
                    }
                } else{
                    if(!operators.empty())
                        while (GetPriority(input.charAt(i)) <= GetPriority(operators.peek())) {
                            output += operators.pop().toString();
                            if (operators.empty())
                                break;
                        }

                    operators.push(input.charAt(i));
                }
            }

            System.out.println(output);
            System.out.println(operators);
        }

        while(!operators.empty()){
            output += operators.pop();
        }

        System.out.println("_____________________________________");
        System.out.println(output);
        System.out.println(input);
        return "asd";
    }

    private static boolean IsDelimeter(char c){
        return " =".indexOf(c) != -1;
    }

    private static boolean IsOperator(char c){
        return "+-*/()".indexOf(c) != -1;
    }

    private static byte GetPriority(char c){
        switch (c)
        {
            case '(': return 0;
            case ')': return 0;
            case '+': return 1;
            case '-': return 1;
            case '*': return 2;
            case '/': return 2;
            default: return 0;
        }
    }
}
