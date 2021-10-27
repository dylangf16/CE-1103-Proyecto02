package Expression_Tree;

import java.util.Stack;

class Node {

    String value;
    Node left, right;

    /**
     * Metodo constructor
     * @param item String que almacena el nodo
     */
    Node(String item) {
        value = item;
        left = right = null;
    }
}

public class ExpressionTree {

    /**
     * Reconoce si un caracter es un operando o no
     * @param c el character que va a evaluar
     * @return
     */
    boolean isOperator(char c) {
        if (c == '+' || c == '-'
                || c == '*' || c == '/'
                || c == '%') {
            return true;
        }
        return false;
    }

    /**
     * Construye el arbol a partir de la notacion posfija
     * @param postfix lista de caracteres de la notacion posfija
     * @return nodo raiz del arbol construido
     */
    Node constructTree(char postfix[]) {
        Stack<Node> st = new Stack<Node>();
        Node t, t1, t2;

        for (int i = 0; i < postfix.length; i++) {
            int j = 0;

            if (!isOperator(postfix[i])) {
                String digits = Character.toString(postfix[i]);
                while (!isOperator(postfix[i+j])) {
                    j++;
                }
                    for(int s = 1; s <= j-i; s++){
                        digits += Character.toString(postfix[i+s]);
                    }

                t = new Node(digits);
                st.push(t);
                i += j;
            } else
            {
                t = new Node(Character.toString(postfix[i]));

                t1 = st.pop();
                t2 = st.pop();

                t.right = t1;
                t.left = t2;

                st.push(t);
            }
        }

        t = st.peek();
        st.pop();

        return t;
    }

    /**
     * Convierte un string a un digito
     * @param ch caracter de un numero
     * @return caracter convertido a numero
     */
    private int toDigit(String ch) {
        return Integer.parseInt(ch);
    }

    /**
     * Resuelve el problema matematico a partir del arbol utilizando recursividad
     * @param ptr nodo raiz del arbol
     * @return resultado tras resolver el problema
     */
    public double evaluate(Node ptr) {
        if (ptr.left == null && ptr.right == null) {
            return toDigit(ptr.value);
        } else {
            double result = 0.0;
            double left = evaluate(ptr.left);
            double right = evaluate(ptr.right);
            String operator = ptr.value;

            switch (operator) {
                case "+":
                    result = left + right;
                    break;
                case "-":
                    result = left - right;
                    break;
                case "*":
                    result = left * right;
                    break;
                case "/":
                    result = left / right;
                    break;
                case "%":
                    result = left % right;
                    break;
                default:
                    result = left + right;
                    break;
            }
            return result;
        }
    }

    /**
     * Metodo main
     * @param problem problema matematico en notacion posfija
     * @return resultado del problema recibido
     */
    public static double main(String problem) {

        ExpressionTree et = new ExpressionTree();
        String postfix = problem;
        char[] charArray = postfix.toCharArray();
        Node root = et.constructTree(charArray);
        return et.evaluate(root);

    }
}