import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try{
            FileOutputStream fout = new FileOutputStream("D:\\Tasks\\Level 2\\FileHandeling\\src\\write.txt");
            PrintStream pr = new PrintStream(fout);
            FileInputStream fr = new FileInputStream("D:\\Tasks\\Level 2\\FileHandeling\\src\\read.txt");
            int i;
            while((i =fr.read())!= -1){
                pr.print((char)i);
            }
            System.out.println("Report Success!");
            fout.close();
            pr.close();
            fr.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Exists!\n"+ e.getMessage());
        }

    }
}
