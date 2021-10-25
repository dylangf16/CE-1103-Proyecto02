/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expression_Tree;

import java.util.Stack;

public class InfixToPosfix {

    /**
     * Le asigna un valor a los caracteres para colocarlos en orden de importancia a la hora de resolver
     * @param ch caracter al que le asgina valor
     * @return valor del caracter
     */
    static int Prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
            case '%':
                return 2;
        }
        return -1;
    }

    /**
     * Convierte la expresion de sufija a posfija
     * @param exp expresion en notacion sufija
     * @return expreson en notacion posfija
     */
    static String infixToPostfix(String exp) {
        String result = new String("");

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result += c;
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty()
                        && stack.peek() != '(') {
                    result += stack.pop();
                }

                stack.pop();
            } else
            {
                while (!stack.isEmpty() && Prec(c)
                        <= Prec(stack.peek())) {

                    result += stack.pop();
                }
                stack.push(c);
            }

        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression";
            }
            result += stack.pop();
        }
        return result;
    }

    /**
     * Metodo main
     * @param infix problema matematico en notacion sufija
     * @return problema matematico en notacion posfija
     */
    public static String main(String infix) {
        String exp = infix;
        System.out.println(exp);
        return infixToPostfix(exp);
    }
}
