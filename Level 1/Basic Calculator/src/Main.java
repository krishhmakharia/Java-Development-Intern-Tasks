import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            double result;

            System.out.print("Enter the first number: ");
            double num1 = input.nextDouble();

            System.out.print("Enter an operator (+, -, *, /): ");
            char operator = input.next().charAt(0);

            System.out.print("Enter the second number: ");
            double num2 = input.nextDouble();
            switch (operator) {
                case '+':
                    result = Operations.add(num1, num2);
                    System.out.println("The result is: " + result);
                    break;
                case '-':
                    result = Operations.subtract(num1, num2);
                    System.out.println("The result is: " + result);
                    break;
                case '*':
                    result = Operations.multiply(num1, num2);
                    System.out.println("The result is: " + result);
                    break;
                case '/':
                    try {
                        result = Operations.divide(num1, num2);
                        System.out.println("The result is: " + result);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Error: Invalid operator entered.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter valid numbers.");
        }

    }
}