/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expression_Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPosfix {

    /**
     * Le asigna un valor a los caracteres para colocarlos en orden de
     * importancia a la hora de resolver
     *
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
            case '(':
            case ')':
                return -2;

        }
        return -1;
    }

    /**
     * Cuenta la cantidad de digitos de cada numero y lo guarda en una lista
     * @param infix problema amtematico en notacion sufija
     * @return lista con el numero de dititos de cada numero
     */
    static List<Integer> BuildList(String infix) {
        List<Integer> numlist = new ArrayList<Integer>();
        for (int i = 0; i < infix.length(); i++) {
            int j = 1;
            char c = infix.charAt(i);
            if (Prec(c) == -1) {
                while (Prec(infix.charAt(i + j)) == -1) {
                    j++;
                }
                numlist.add(j);
                i += j - 1;
            }
        }
        return numlist;
    }

    /**
     * Convierte la expresion de sufija a posfija
     *
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
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty()
                        && stack.peek() != '(') {
                    result += stack.pop();
                }

                stack.pop();
            } else {
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
     * @return resultado de resolver el problema matematico
     */
    public static double main(String infix) {
        System.out.println(infix);
        System.out.println(BuildList(infix));
        return ExpressionTree.main(infixToPostfix(infix), BuildList(infix));
    }
}
