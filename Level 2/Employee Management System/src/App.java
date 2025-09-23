import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    public static ArrayList<Employee> employees = new ArrayList<>();

    public void createEmp(){
        System.out.print("Employee ID :");
        int id = sc.nextInt();
        System.out.print("Employee Name :");
        String name = sc.next();
        System.out.print("Employee Salary :");
        double sal = sc.nextDouble();
        employees.add(new Employee(id,name,sal));
    }
    public void viewEmp(){
        System.out.println("--------------------List of Employees------------------ ");
        for (Employee employee : employees){
            employee.display();
        }
        System.out.println();
    }
    public void deleteEmp(){
        int flag=0;
        System.out.print("Enter Employee Id : ");
        int del_id = sc.nextInt();
        for (int i=0; i<employees.size();i++){
            if(employees.get(i).emp_id==del_id){
                employees.remove(i);
                System.out.println("Successfully ID: "+i+" Removed");
                flag=1;
                break;
            }
        }
        if(flag==0){
            System.out.println("Id not exists in records");
        }
    }
    public void updateEmp(){
        int flag=0;
        System.out.print("Enter Employee Id : ");
        int upd_id = sc.nextInt();
        for (int i=0; i<employees.size();i++){
            if(employees.get(i).emp_id==upd_id){
                System.out.print("New Name :");
                String new_name=sc.next();

                System.out.print("New Salary :");
                double new_salary = sc.nextDouble();

                employees.get(i).emp_name=new_name;
                employees.get(i).salary = new_salary;

                System.out.println("Successfully ID: "+i+" Updated");
                flag=1;
                break;
            }
        }
        if(flag == 0){
            System.out.println("Id not exists in records");
        }
    }
    public static void main(String[] args) {
        App app = new App();
        System.out.println("EMPLOYEE MANAGEMENT SYSTEM!");
        boolean flag = true;
        while(flag){
            System.out.println("Operations:");
            System.out.println("(1) Create new Employee\n(2) View Employees\n(3) Delete Employee Records\n(4) Update Employee\n(5) exit");
            System.out.print("Enter Your choice ---> ");
            int choice = sc.nextInt();
            switch (choice){
                case 1://create
                    app.createEmp();
                    break;
                case 2://view
                    app.viewEmp();
                    break;
                case 3://Delete
                    app.deleteEmp();
                    break;
                case 4://update
                    app.updateEmp();
                    break;
                case 5://exit
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
    }
}
