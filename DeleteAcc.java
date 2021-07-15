/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteAcc
{
    DeleteAcc()
    {
        Acc_Dele AD = new Acc_Dele();
        AD.Delete();
    }
}

class Acc_Dele extends JFrame
{
    
    private JTextField Acc_Num;
    private JTextField PIN;
    private JButton DELETE;
    private JButton CANCEL;
    private JLabel jlabel;
    private JLabel jlabel2;
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JPanel jpanel3;    
    
    Database db = new Database(); 
    
    public void Delete()
    {
        System.out.println("Account Deletion");
        
        Acc_Num = new JTextField(10);
        PIN = new JTextField(4);
        DELETE = new JButton("DELETE");
        CANCEL = new JButton("CANCEL");
        jlabel = new JLabel("Enter Account Number");
        jlabel2 = new JLabel("Enter Pin: ");
        
        jpanel1 = new JPanel();
        jpanel2 = new JPanel();
        jpanel3 = new JPanel();
        
        jpanel1.setLayout(new GridLayout(2,1,1,1));
        jpanel1.add(jlabel);   
        jpanel1.add(Acc_Num);
        
        jpanel2.setLayout(new GridLayout(2, 1, 1, 1));
        jpanel2.add(jlabel2);
        jpanel2.add(PIN);
        
        jpanel3.setLayout(new GridLayout(1,1,1,1));
        jpanel3.add(DELETE);
        jpanel3.add(CANCEL);
        
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(jpanel1);
        
        Container container1 = getContentPane();
        container1.setLayout(new FlowLayout());
        container1.add(jpanel2);
        
        Container container3 = getContentPane();
        container3.setLayout(new FlowLayout());
        container3.add(jpanel3);
        
        setVisible(true);
        setTitle("Delete Account");
        setBounds(100,100,270,130);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DELETE.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                db.DELETE_ACCEntry(Acc_Num.getText(), PIN.getText());
                MainMenu mm=new MainMenu();
                mm.menu();
            }
        });
        
        CANCEL.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                MainMenu mm=new MainMenu();
                mm.menu();
            }
        });
    }
}