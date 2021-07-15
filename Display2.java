/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Display2 extends JFrame //implements ActionListener
{
    private JLabel jlabel1;
    private JLabel jlabel2;
    private JLabel jlabel3;
    private JLabel jlabel4;
    private JLabel jlabel5;
    
    private JTextField NAME;
    private JTextField ACC;
    private JTextField DATE;
    private JTextField ADD;
    private JTextField AGE;
    private JTextField BALANCE;
    
    private JButton jbutton;
    
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JPanel jpanel3;
    
    JLabel command=new JLabel();
    JScrollPane resultsPane;
    Connection connection;
    Statement statement;
    ResultsModel model;
    
    void displayaccount(String s1,String s2,String s3,String s4,String s5)
    {
        System.out.println("HHH"+s1+" "+s2+" "+s3+" "+s4+" "+s5);
        jlabel1 = new JLabel("NAME");
        jlabel2 = new JLabel("ACC_NUM");
        jlabel3 = new JLabel("DATE");
        jlabel4 = new JLabel("ADDRESS");
        jlabel5 = new JLabel("AGE");
        
        jpanel1 = new JPanel();
        jpanel1.setLayout(new GridLayout(1,1,40,40));
        jpanel1.add(jlabel1);
        jpanel1.add(jlabel2);
        jpanel1.add(jlabel3);
        jpanel1.add(jlabel4);
        jpanel1.add(jlabel5);
        
        jbutton = new JButton("OK");
        
        NAME = new JTextField(8);
        ACC = new JTextField(8);
        DATE = new JTextField(8);
        ADD = new JTextField(8);
        AGE = new JTextField(8);
        
        jpanel2 = new JPanel();
        jpanel2.setLayout(new GridLayout(1,1,1,1));
        jpanel2.add(NAME);
        jpanel2.add(ACC);
        jpanel2.add(DATE);
        jpanel2.add(ADD);
        jpanel2.add(AGE);
        
        jpanel3 = new JPanel();
        jpanel3.setLayout(new GridLayout(1,1,1,1));
        jpanel3.add(jbutton);
        
        Container conntainer = getContentPane();
        conntainer.setLayout(new FlowLayout());
        conntainer.add(jpanel1);
       
        Container conntainer1 = getContentPane();
        conntainer1.setLayout(new FlowLayout());
        conntainer1.add(jpanel2);
        
        Container conntainer2 = getContentPane();
        conntainer2.setLayout(new FlowLayout());
        conntainer2.add(jpanel3);
        
        NAME.setText(s1);
        ACC.setText(s2);
        DATE.setText(s3);
        ADD.setText(s4);
        AGE.setText(s5);
        
        setVisible(true);
        setTitle("Account Information");
        setBounds(100,100,480,120);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                MainMenu mm=new MainMenu();
                mm.menu();
            }
        });
    }
    
    void displaybalance(String s1,String s2,String s3)
    {
        System.out.println("HHH"+s1+" "+s2+" "+s3);
        jlabel1 = new JLabel("NAME");
        jlabel2 = new JLabel("ACC_NUM");
        jlabel3 = new JLabel("BALANCE");
        
        jpanel1 = new JPanel();
        jpanel1.setLayout(new GridLayout(1,1,40,40));
        jpanel1.add(jlabel1);
        jpanel1.add(jlabel2);
        jpanel1.add(jlabel3);
                
        jbutton = new JButton("OK");
        
        NAME = new JTextField(8);
        ACC = new JTextField(8);
        BALANCE = new JTextField(8);
                
        jpanel2 = new JPanel();
        jpanel2.setLayout(new GridLayout(1,1,1,1));
        jpanel2.add(NAME);
        jpanel2.add(ACC);
        jpanel2.add(BALANCE);
                
        jpanel3 = new JPanel();
        jpanel3.setLayout(new GridLayout(1,1,1,1));
        jpanel3.add(jbutton);
        
        Container conntainer = getContentPane();
        conntainer.setLayout(new FlowLayout());
        conntainer.add(jpanel1);
       
        Container conntainer1 = getContentPane();
        conntainer1.setLayout(new FlowLayout());
        conntainer1.add(jpanel2);
        
        Container conntainer2 = getContentPane();
        conntainer2.setLayout(new FlowLayout());
        conntainer2.add(jpanel3);
        
        NAME.setText(s1);
        ACC.setText(s2);
        BALANCE.setText(s3);
        
        setVisible(true);
        setTitle("Account Information");
        setBounds(100,100,330,120);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                MainMenu mm=new MainMenu();
                mm.menu();
            }
        });
    }
    
    void displaydeposit(String s,int i)
    {
        String url="jdbc:mysql://localhost:3306/Bank_Info?zeroDateTimeBehavior=convertToNull";
        String driver="com.mysql.jdbc.Driver";
        
        setBounds(100,100,480,120);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JButton OK = new JButton("OK");
        
        JLabel lable=new JLabel("Customer Database");
        lable.setToolTipText("Customer Database");
        lable.setFont(new Font("SansSerif",Font.BOLD,18));
        JPanel lpanel=new JPanel();
        lable.setForeground(Color.red);
        lpanel.setBackground(Color.orange);
        lpanel.add(lable,"Center");
        lpanel.setToolTipText("Customer Database");


       Container contentPane=getContentPane();
       contentPane.add(lpanel,"North");

       getContentPane().add(OK,BorderLayout.SOUTH);
  
       try
        {
            Class.forName(driver);
            connection=DriverManager.getConnection(url);
            statement=connection.createStatement();

            model=new ResultsModel();
            JTable table=new JTable(model);
            //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            resultsPane=new JScrollPane(table);
            getContentPane().add(resultsPane,BorderLayout.CENTER);
            if(i==1)    
            model.setResultSet(statement.executeQuery("SELECT * from DepositInfo WHERE Account_Num = '"+s+"'"));
            else if(i==2)
            model.setResultSet(statement.executeQuery("SELECT * from WithdrawInfo WHERE Account_Num = '"+s+"'"));    
                
            OK.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    MainMenu mm = new MainMenu();
                    mm.menu();
                    setVisible(false);
                }
            });
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println(cnf);
        }

        catch(SQLException sql)
        {
            System.out.println(sql);
        }

        pack();
        setVisible(true);
    }

    /*private JMenuItem makeMenuItem(String name)
    {
        JMenuItem m=new JMenuItem(name);
        m.addActionListener(this);
        return m;
    }
    public void actionPerformed(ActionEvent evt)
    {
          String command=evt.getActionCommand();
          if(command.equals("OK"))
          {
              setVisible(false);
                MainMenu mm=new MainMenu();
                mm.menu();
          }
    }*/

}
