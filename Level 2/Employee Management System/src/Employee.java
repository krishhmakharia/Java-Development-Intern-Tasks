import java.util.ArrayList;
import java.util.Scanner;

public class Employee {
    String emp_name;
    int emp_id;
    double salary;
    Employee( int emp_id,String emp_name, double salary ){
        this.emp_id=emp_id;
        this.emp_name=emp_name;
        this.salary=salary;
    }

    void display() {
        System.out.println("ID: " + emp_id + ", Name: " + emp_name + ", Salary: " + salary );
    }


}
