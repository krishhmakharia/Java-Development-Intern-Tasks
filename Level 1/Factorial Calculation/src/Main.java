import java.util.Scanner;

public class Main {
    public static int factorial(int num){
        if(num<0){
            return -1;
        }
        if(num==0){
            return 1;
        }
        return num*factorial(num-1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Factorial Calculation Program!");
        System.out.print("Enter a number : ");
        int number = sc.nextInt();
        System.out.println(factorial(number));
    }
}
