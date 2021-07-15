/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

public class Deposit 
{
    Deposit()
    {
        DepAmm DA = new DepAmm();
        DA.DepositAmmount();
    }
}

class DepAmm extends JFrame
{
    
    private JButton ADD;
    private JButton CANCEL;
    
    private JLabel jlabel1;
    private JLabel jlabel2;
    private JLabel jlabel3;
    private JLabel jlabel4;
    private JLabel jlabel5;
    
    private JTextField Acc;
    private JTextField PIN;
    private JTextField Date;
    private JTextField Ammount;
    //JComboBox Type;
    
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JPanel jpanel3;
    private JPanel jpanel4;
    private JPanel jpanel5;
    
    String str[] = {"Cash","Cheque"};
    
    Database db = new Database();
    
    public void DepositAmmount()
    {
        jlabel1 = new JLabel("Enter Amount Information");
        jlabel2 = new JLabel("Account_Num");
        jlabel3 = new JLabel("PIN");
        jlabel4 = new JLabel("Date");
        jlabel5 = new JLabel("Amount");
        
        Date now = new Date();
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
        String date = df1.format(now);
        
        Acc = new JTextField(25);
        PIN = new JTextField(25);
        Date = new JTextField(date);
        Date.setEnabled(false);
        Ammount = new JTextField(10);
        
        ADD = new JButton("ADD");
        CANCEL = new JButton("CANCEL");
        
        jpanel1 = new JPanel();
        jpanel1.setLayout(new GridLayout(1,1,1,1));
        jpanel1.add(jlabel1);
        
        jpanel2 = new JPanel();
        jpanel2.setLayout(new GridLayout(4,1,20,20));
        jpanel2.add(jlabel2);
        jpanel2.add(jlabel3);
        jpanel2.add(jlabel4);
        jpanel2.add(jlabel5);
        
        jpanel3 = new JPanel();
        jpanel3.setLayout(new GridLayout(4,1,20,20));
        jpanel3.add(Acc);
        jpanel3.add(PIN);
        jpanel3.add(Date);
        jpanel3.add(Ammount);
        
        jpanel4 = new JPanel();
        jpanel4.setLayout(new FlowLayout());
        jpanel4.add(jpanel2);
        jpanel4.add(jpanel3);
        
        jpanel5 = new JPanel();
        jpanel5.setLayout(new FlowLayout());
        jpanel5.add(ADD);
        jpanel5.add(CANCEL);
        
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(jpanel1);
        
        Container container2 = getContentPane();
        container2.setLayout(new FlowLayout());
        container2.add(jpanel4);
        
        Container container3 = getContentPane();
        container3.setLayout(new FlowLayout());
        container3.add(jpanel5);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setTitle("Deposit Amount");
        setBounds(100,100,400,250);
        
        ADD.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //int i = Type.getSelectedIndex();
                db.AddAmmountEntry(Acc.getText(),PIN.getText(),Date.getText(),Ammount.getText());
                setVisible(false);
                MainMenu mm=new MainMenu();
                mm.menu();
            }
        });
        
        CANCEL.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                setVisible(false);
                MainMenu mm=new MainMenu();
                mm.menu(); 
            }
        });
    }
}
