public class BankAccount {
    private Long id;
    private String name;
    private double balance;

    BankAccount(Long id, String name, double balance){
        this.id=id;
        this.balance=balance;
        this.name=name;
    }

    public void deposit(double amount){
        this.balance=this.balance+amount;
        checkBalance();
    }
    public void withdraw(double amount){
        if(amount<=this.balance){
            this.balance=this.balance - amount;
            checkBalance();
        }else{
            System.out.println("-----Insufficient funds!!!----");
        }
    }
    public void checkBalance(){
        System.out.println("Current Balance : "+balance);
    }
}
