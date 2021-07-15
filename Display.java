/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Display 
{
    Display()
    {
        BalCheck BC = new BalCheck();
        BC.Display1();
    }
}

class BalCheck extends JFrame
{
    
    private JLabel jlabel2;
    private JLabel jlabel3;
    private JTextField Account_Num;
    private JTextField PinNum;
       
    private JButton AccountInfo;
    private JButton BalanceInfo;
    private JButton DepositInfo;
    private JButton WithdrawInfo;
    
    private JPanel jpanel2;
    private JPanel jpanel3;
    private JPanel jpanel4;
    private JPanel jpanel5;
    
    Database db = new Database();
    Display2 d2 = new Display2();
    
    public void Display1()
    {
        
        jlabel2 = new JLabel("Enter Account Number");
        Account_Num = new JTextField(10);
        jlabel3 = new JLabel("Enter PIN");
        PinNum = new JTextField(4);

        
        jpanel3 = new JPanel();
        jpanel3.setLayout(new GridLayout(1,1,1,1));
        jpanel3.add(jlabel2);
        
        jpanel4 = new JPanel();
        jpanel4.setLayout(new GridLayout(1,2,1,1));
        jpanel4.add(jlabel2);
        jpanel4.add(Account_Num);
        
        jpanel5 = new JPanel();
        jpanel5.setLayout(new GridLayout(1,2,1,1));
        jpanel5.add(jlabel3);
        jpanel5.add(PinNum);
        
        AccountInfo = new JButton("AccountInfo");
        BalanceInfo = new JButton("BalanceInfo");
        DepositInfo = new JButton("DepositInfo");
        WithdrawInfo = new JButton("WithdrawInfo");
        jpanel2 = new JPanel();
        jpanel2.setLayout(new GridLayout(1,1,50,50));
        jpanel2.add(BalanceInfo);

        
        Container conn2 = getContentPane();
        conn2.setLayout(new FlowLayout());
        conn2.add(jpanel3);
        
        
        
        Container conn3 = getContentPane();
        conn3.setLayout(new FlowLayout());
        conn3.add(jpanel4);
        
        Container conn4 = getContentPane();
        conn4.setLayout(new FlowLayout());
        conn4.add(jpanel5);
        
        Container conn1 = getContentPane();
        conn1.setLayout(new FlowLayout());
        conn1.add(jpanel2);
        
        
        
        setBounds(100,100,400,200);
        setTitle("Display Info");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        AccountInfo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                db.DisplayInfo(Account_Num.getText(), PinNum.getText(), 1);
                setVisible(false);
            }
        });
        
        BalanceInfo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                db.DisplayInfo(Account_Num.getText(), PinNum.getText(), 2);
                setVisible(false);
            }
        });
        
        DepositInfo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                d2.displaydeposit(Account_Num.getText(),1);
                setVisible(false);
            }
        });
        
        WithdrawInfo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                d2.displaydeposit(Account_Num.getText(),2);
                setVisible(false);
            }
        });
    }
    
    public void displayaccount(String s1,String s2,String s3,String s4,String s5)
    {
        System.out.println("HI");
    }
}
