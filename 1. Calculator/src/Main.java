import java.util.Scanner;

public class Main {

    static float res = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char op = '+';
        float num;

        while (op != 'S') {
            System.out.println(" Enter a number");
            num = input.nextFloat(); // reads a number

            calculate(num, op);
            printResult();

            System.out.println(" Enter an operation: +, -, * or /");
            op = input.next().charAt(0); // reads the 1st character of a string
            if (op == 'C') {
                res = 0;
                printResult();
            }

        } // close while
    } // close main

    private static void calculate(float n, char ch) {

        switch (ch) {
            case '+': { res += n; break; }
            case '-': { res -= n; break; }
            case '*': { res *= n; break; }
            case '/': { res /= n; break; }
            default: {
                System.out.println("! Unsupported operation: " + ch);
                break;
            }
        } // switch
    } // calculate

    private static void printResult() {
        System.out.println("__Current result = " + res);
    }


} // close class Main
