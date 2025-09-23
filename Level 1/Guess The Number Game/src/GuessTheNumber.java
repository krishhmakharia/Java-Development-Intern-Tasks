import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args){
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int computer_number = random.nextInt(100)+1;
        boolean is_guess_correctly=false;
        int max_guess = 10;
        int current_guess = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have " + max_guess + " attempts to find it. Good luck!");

        while(current_guess<max_guess && !is_guess_correctly){
            System.out.println("----------------------------------------");
            System.out.print("Attempt #" + (current_guess + 1) + ": Enter your guess: ");

            int user_guess;

            try{
                user_guess = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter whole numbers only.");
                sc.next();
                continue;
            }
            current_guess++;

            if(user_guess>computer_number){
                System.out.println("Too high! Try a lower number.");
            }else if(user_guess <computer_number){
                System.out.println("Too Low! Try a higher number.");
            }else{
                is_guess_correctly = true;
                System.out.println("🏆 Congratulations! You guessed the correct number!");
                System.out.println("You found it in " + current_guess + " attempts.");
            }
        }
        if (!is_guess_correctly) {
            System.out.println("----------------------------------------");
            System.out.println("😥 Game over! You've used all your attempts.");
            System.out.println("The number I was thinking of was: " + computer_number);
        }
    }
}
