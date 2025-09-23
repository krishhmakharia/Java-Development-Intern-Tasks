import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account1 = new BankAccount(1L,"Krish Makharia",100);
        boolean flag = true;
        while(flag){
            System.out.println("\n\nOperations :");
            System.out.println("(1) Check Balance\n(2) Deposit funds\n(3) Withdraw funds");
            System.out.print("\nYour choice :");
            int choice = sc.nextInt();
            switch(choice){
                case 1://Check Balance
                    account1.checkBalance();
                    break;
                case 2://Deposit
                    System.out.print("Enter Amount : ");
                    double fund = sc.nextDouble();
                    account1.deposit(fund);
                    break;
                case 3://withdraw
                    System.out.print("Enter Amount : ");
                    double withdraw_fund = sc.nextDouble();
                    account1.withdraw(withdraw_fund);
                    break;
                case 4:
                    flag=false;
                    break;
                default:
                    System.out.println("!!!!!!--------Invalid Input---------!!!!!!");
                    break;
            }
        }

    }
}
