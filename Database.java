/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;

   
import java.sql.*;
import java.io.*;

public class Database
{
    Display2 displayinfo = new Display2();
    String acc,date,age,name,add,bala;
    String SQLStatement;
    
    public static String Instruction = "";

    
    private boolean CheckPin(String accNo, String pin) {
        System.out.println("AccNo = " + accNo + " pin = " + pin );
        String Query = "SELECT * from CustomerInfo where Account_Num = '" + accNo +
                "' and PIN = '" + pin + "'";
        System.out.println("Query = " + Query);
        String URL = "jdbc:mysql://localhost:3306/Bank_Info?zeroDateTimeBehavior=convertToNull";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, "root", "password");
            Statement stmt = con.createStatement();
            ResultSet RS = null;
            
            RS = stmt.executeQuery(Query);
            return (RS.next());
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error - " + ex);
            return false;
        }
        
    }
    
    
    public void AddCustomerEntry(String s1,String s2,String s3,String s4, String s5)
    {
        System.out.println(" "+s1+" "+s2+" "+s3+" "+s4);
        String url="jdbc:mysql://localhost:3306/Bank_Info?zeroDateTimeBehavior=convertToNull";
        String driver="com.mysql.jdbc.Driver";
        Instruction = "";
        int i=0;
        
        try
        {
            String acc;
            FileReader fr = new FileReader("ACCOUNT_NUMBER.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((acc = br.readLine()) != null) 
            {
                System.out.println(acc); 
                try
                {
                    i = Integer.parseInt(acc);
                    i++;
                }
                catch(NumberFormatException e2)
                { }
                try
                {
                
                    String SQLStatement="INSERT INTO CustomerInfo VALUES" +
                        "('"+s1+"','"+acc+"','"+s2+"','"+s3+"','"+s4+"', '" + s5 + "')";
                    System.out.println(SQLStatement);
                    System.out.println("Driver is " + driver);
                    Class.forName(driver);
                    Connection connection=DriverManager.getConnection(url, "root", "password");
                    Statement statement=connection.createStatement();
                    statement.executeUpdate(SQLStatement);
                    connection.close();
                }

                catch(ClassNotFoundException cnfe)
                {
                    System.out.println(cnfe);
                }

                catch(SQLException squl)
                {
                    System.out.println("HI"+squl);
                }
                
                try
                {
                    String s = "0";
                    String SQLStatement="INSERT INTO BalanceInfo VALUES" +
                    "('"+s1+"','"+acc+"','"+s+"')";
                    System.out.println(SQLStatement);
                    Class.forName(driver);
                    Connection connection1=DriverManager.getConnection(url, "root", "password");
                    Statement statement=connection1.createStatement();
                    statement.executeUpdate(SQLStatement);
                    connection1.close();
                    Instruction = "";
                }
                catch(ClassNotFoundException cnfe)
                {
                    System.out.println("Class not found Exception - " + cnfe);
                    Instruction = cnfe.getMessage();
                }

                catch(SQLException squl)
                {
                    System.out.println("HI"+squl);
                    Instruction = squl.getMessage();
                }
            }    
        }
        catch(IOException e)
        {
            System.out.println("File Not Found");
            Instruction = "FILE NOT FOUND!";
        }
        try
        {
            String str = Integer.toString(i);
            FileWriter fw = new FileWriter("ACCOUNT_NUMBER.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        }
        catch(IOException e1)
        { } 
        
    }
    
    public void AddAmmountEntry(String s1,String s2,String s3,String s4)
    {
        System.out.println(" "+s1+" "+s2+" "+s3+" "+s4);
        String Balance = null;
        String url="jdbc:mysql://localhost:3306/Bank_Info?zeroDateTimeBehavior=convertToNull";
        String driver="com.mysql.jdbc.Driver";
        String name = null;
        ResultSet Result = null;
        Instruction = "";
        
        if (CheckPin(s1, s2)) {
        
            try
            {
                 String SQLStatement="SELECT Name FROM CustomerInfo WHERE Account_Num='"+s1+"'";
                 System.out.println(SQLStatement);
                 Class.forName(driver);
                 Connection connection1=DriverManager.getConnection(url, "root", "password");
                 Statement statement=connection1.createStatement();
                 Result = statement.executeQuery(SQLStatement);

                 while(Result.next()) 
                 {
                    name = Result.getString("Name");
                    System.out.println(" "+name);
                 }
            }
            catch(ClassNotFoundException cnfe)
            {
                System.out.println(cnfe);
            }

            catch(SQLException squl)
            {
                System.out.println("HI"+squl);
            }

            try
            {
                 SQLStatement = "INSERT INTO DepositInfo VALUES" +
                 "('"+name+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"')";
                 System.out.println(SQLStatement);
                 Class.forName(driver);
                 Connection connection1=DriverManager.getConnection(url, "root", "password");
                 Statement statement=connection1.createStatement();
                 statement.executeUpdate(SQLStatement);
                 connection1.close();
                 
                 SQLStatement = "SELECT Balance from BalanceInfo where Account_Num = '" + s1 + "'";
                 ResultSet RS;
                 Connection con = DriverManager.getConnection(url, "root", "password");
                 Statement stmt = con.createStatement();
                 RS = stmt.executeQuery(SQLStatement);
                 RS.next();
                 
                 Balance = RS.getString("Balance");
                 
                 
                 

                 
                 Instruction = "";
            }
            catch(ClassNotFoundException cnfe)
            {
                Instruction = cnfe.getMessage();
            }

            catch(SQLException squl)
            {
                Instruction = squl.getMessage();
            }

            try
            {
                 int s41 = Integer.parseInt(s4);
                 int Balance1 = Integer.parseInt(Balance);
                 Balance1 = Balance1+s41;
                 String Balance2 = Integer.toString(Balance1);
                 String SQLStatement="UPDATE BalanceInfo SET Balance = '"+Balance2+"' WHERE Account_Num='"+s1+"'";
                 System.out.println(SQLStatement);
                 Class.forName(driver);
                 Connection connection1=DriverManager.getConnection(url, "root", "password");
                 Statement statement=connection1.createStatement();
                 statement.executeUpdate(SQLStatement);
                 connection1.close();
                 
                 Instruction = "";
            }
            catch(ClassNotFoundException cnfe)
            {
                Instruction = cnfe.getMessage();
            }

            catch(SQLException squl)
            {
                Instruction = squl.getMessage();
            }
        }
        else {
            Instruction = "INCORRECT PIN!";
        }
        
     }
    
    public void DELETE_ACCEntry(String s1, String pin)
    {
        System.out.println(" "+s1);
        String url="jdbc:mysql://localhost:3306/Bank_Info?zeroDateTimeBehavior=convertToNull";
        String driver="com.mysql.jdbc.Driver";
        int acc = Integer.parseInt(s1);
        Instruction = "";
        
        if (CheckPin(s1, pin)) {
            try
            {
                 String SQLStatement="DELETE FROM CustomerInfo WHERE Account_Num='"+s1+"'";
                 System.out.println(SQLStatement);
                 System.out.println(acc);
                 Class.forName(driver);
                 Connection connection1=DriverManager.getConnection(url, "root", "password");
                 Statement statement=connection1.createStatement();
                 statement.executeUpdate(SQLStatement);
                 connection1.close();
                 Instruction = "";
            }
            catch(ClassNotFoundException cnfe)
            {
                System.out.println(cnfe);
            }

            catch(SQLException squl)
            {
                System.out.println("HI"+squl);
            }
        }
        else {
            Instruction = "INCORRECT PIN!";
        }
        
    }

    public void WithdrawAmmountEntry(String s1,String s2,String s3,String s4)
    {
        System.out.println(" "+s4);
        String url="jdbc:mysql://localhost:3306/Bank_Info?zeroDateTimeBehavior=convertToNull";
        String driver="com.mysql.jdbc.Driver";
        float bal = 0;
        String name = null;
        ResultSet Result = null;
        Instruction = "";
        
        if (CheckPin(s1, s2)) {
            
        
        

            try
            {
                 String SQLStatement="SELECT Name,Balance FROM BalanceInfo WHERE Account_Num='"+s1+"'";
                 System.out.println(SQLStatement);
                 Class.forName(driver);
                 Connection connection1=DriverManager.getConnection(url, "root", "password");
                 Statement statement=connection1.createStatement();
                 Result = statement.executeQuery(SQLStatement);

                 if (Result.next()) 
                 {
                    bal = Result.getFloat("Balance");
                    name = Result.getObject(1).toString();
                    System.out.println(bal+" "+name);
                 }


                 int s41 = Integer.parseInt(s4);

                if(bal >= s41)
                {
                     bal = bal-s41;

                     try
                     {
                         String SQLStatement1="UPDATE BalanceInfo SET Balance = '"+bal+"' WHERE Account_Num='"+s1+"'";
                         System.out.println(SQLStatement1);
                         Class.forName(driver);
                         Connection connection2=DriverManager.getConnection(url, "root", "password");
                         Statement statement1=connection2.createStatement();
                         statement1.executeUpdate(SQLStatement1);

                         connection2.close();
                     }
                    catch(ClassNotFoundException cnfe)
                    {
                         System.out.println(cnfe);
                    }

                    catch(SQLException squl)
                    {
                         System.out.println("HI"+squl);
                    }
                }
                else {
                    
                    Instruction = "INSUFFICIENT BALANCE";
                    System.out.println(Instruction);
                }

                connection1.close();
            }
            catch(ClassNotFoundException cnfe)
            {
                System.out.println(cnfe);
                Instruction = cnfe.getMessage();
            }

            catch(SQLException squl)
            {
                System.out.println("HI"+squl);
                Instruction = squl.getMessage();
            }
            
            if (Instruction.equals("")) {
                try
                {
                     SQLStatement="INSERT INTO WithdrawInfo VALUES" +
                     "('"+name+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"')";
                     System.out.println(SQLStatement);
                     Class.forName(driver);
                     Connection connection1=DriverManager.getConnection(url, "root", "password");
                     Statement statement=connection1.createStatement();
                     statement.executeUpdate(SQLStatement);
                     connection1.close();
                     Instruction = "";
                }
                catch(ClassNotFoundException cnfe)
                {
                    System.out.println(cnfe);
                    Instruction = cnfe.getMessage();
                }

                catch(SQLException squl)
                {
                    System.out.println("HI"+squl);
                    Instruction = squl.getMessage();
                }
            }
        }
        else {
            Instruction = "INCORRECT PIN!";
        }
     }
    
    public void DisplayInfo(String s, String pin, int i)
    {
        System.out.println("HHH"+s);
        String url="jdbc:mysql://localhost:3306/Bank_Info?zeroDateTimeBehavior=convertToNull";
        String driver="com.mysql.jdbc.Driver";
        ResultSet Result;
        Instruction = "";
        
        if (CheckPin(s, pin)) {

            try
            {
                 SQLStatement="SELECT * FROM CustomerInfo WHERE Account_Num='"+s+"'";
                 System.out.println(SQLStatement);
                 Class.forName(driver);
                 Connection connection1=DriverManager.getConnection(url, "root", "password");
                 Statement statement=connection1.createStatement();
                 Result = statement.executeQuery(SQLStatement);

                 if(i==1)
                 {
                    while(Result.next()) 
                    {
                        name = Result.getObject(1).toString();
                        acc = Result.getObject(2).toString();
                        date = Result.getObject(3).toString();
                        add = Result.getObject(4).toString();
                        age = Result.getObject(5).toString();
                    }
                 }
                 else if(i==2)
                 {
                     while(Result.next()) 
                    {
                        name = Result.getObject(1).toString();
                        acc = Result.getObject(2).toString();
                        bala = Result.getObject(3).toString();
                    }
                 }
                 connection1.close();
            }
            catch(ClassNotFoundException cnfe)
            {
                Instruction = cnfe.getMessage();
            }

            catch(SQLException squl)
            {
                Instruction = squl.getMessage();
            }
            if(i==1)
             displayinfo.displayaccount(name,acc,date,add,age);
            else if(i==2)
             displayinfo.displaybalance(name,acc,bala);  
        }
        else {
            if(i==1)
             displayinfo.displayaccount("INCORRECT","","","","PIN!");
            else if(i==2)
             displayinfo.displaybalance("INCORRECT","","PIN!");
        }
    }
}
