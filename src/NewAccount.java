/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class NewAccount 
{
    NewAccount()
    {
        newacc na = new newacc();
        na.newaccgui();
    }
}

class newacc extends JFrame
{
    private JButton ADD;
    private JButton CANCEL;
    private JTextField NAME;
    private JTextField DATE;
    private JTextField ADDRESS;
    private JTextField AGE;
    private JTextField PIN;
    
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JPanel jpanel3;
    private JPanel jpanel4;
    private JPanel jpanel5;
    
    private JLabel jlabell;
    private JLabel jlabel2;
    private JLabel jlabel3;
    private JLabel jlabel4;
    private JLabel jlabel5;
    private JLabel jlabel6;
    
    Database db = new Database();
    
    public void newaccgui()
    {
        System.out.println("New Account Creation");
        jlabell = new JLabel("Enter Customer Information");
        jlabel2 = new JLabel("NAME");
        jlabel3 = new JLabel("DATE");
        jlabel4 = new JLabel("ADDRESS");
        jlabel5 = new JLabel("AGE");
        jlabel6 = new JLabel("PIN");
        
        Date now = new Date();
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
        String date = df1.format(now);
      
        ADD = new JButton("ADD");
        CANCEL = new JButton("CANCEL"); 
        NAME = new JTextField(25);
        DATE = new JTextField(date);
        ADDRESS = new JTextField(25);
        AGE = new JTextField(3);
        PIN = new JTextField(4);
        
        jpanel1 = new JPanel();
        jpanel1.setLayout(new GridLayout(1,1,1,1));
        jpanel1.add(jlabell);
        
        jpanel2 = new JPanel();
        jpanel2.setLayout(new GridLayout(5,1,20,20));
        jpanel2.add(jlabel2);
        jpanel2.add(jlabel3);
        jpanel2.add(jlabel4);
        jpanel2.add(jlabel5);
        jpanel2.add(jlabel6);
        
        jpanel3 = new JPanel();
        jpanel3.setLayout(new GridLayout(5,1,20,20));
        jpanel3.add(NAME);
        jpanel3.add(DATE);
        jpanel3.add(ADDRESS);
        jpanel3.add(AGE);
        jpanel3.add(PIN);
        
        jpanel4 = new JPanel();
        jpanel4.setLayout(new FlowLayout());
        jpanel4.add(ADD);
        jpanel4.add(CANCEL);
        
        jpanel5 = new JPanel();
        jpanel5.setLayout(new FlowLayout());
        jpanel5.add(jpanel2);
        jpanel5.add(jpanel3);
                               
        Container container1 = getContentPane();
        container1.setLayout(new FlowLayout());
        
        container1.add(jpanel1);
        
        Container container2 = getContentPane();
        container2.setLayout(new FlowLayout());
        
        container2.add(jpanel5);
        
        Container container3 = getContentPane();
        container3.setLayout(new FlowLayout());
        
        container3.add(jpanel4);
        
        setTitle("New Account Creation");
        setBounds(200,100,370,400);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ADD.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                db.AddCustomerEntry(NAME.getText(),DATE.getText(),ADDRESS.getText(),AGE.getText(), PIN.getText());
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


