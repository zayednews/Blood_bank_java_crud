/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbankmain;

/**
 *
 * @author Progey Tech 3
 */
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame {
 private JPanel panel;
 JLabel l2, l3;
 JTextField tf1;
 JButton btn1;
 JPasswordField p1;
 public LoginForm() {
  
                this.initializeComponents();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
 }
 private void initializeComponents()	{
    //JFrame frame = new JFrame("Login Form");
    this.panel = new JPanel();
    this.panel.setLayout(null);
    this.setTitle("Login Form");
    this.setSize(450, 350);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    this.l2 = new JLabel("Username");
    this.l2.setBounds(20, 70, 180, 20);
    this.panel.add(this.l2);
    
    this.l3 = new JLabel("Password");
    this.l3.setBounds(20, 100, 180, 20);
    this.panel.add(this.l3);
    
    this.tf1 = new JTextField();
    this.tf1.setBounds(200, 70, 180, 20);
    this.panel.add(this.tf1);
    
    this.p1 = new JPasswordField();
    this.p1.setBounds(200, 100, 180, 20);
    this.panel.add(this.p1);
    
    this.btn1 = new JButton("Login");
    this.btn1.setBounds(175, 170, 70, 40);
    this.panel.add(this.btn1);
    
    this.add(this.panel);
    
    this.btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname = tf1.getText();
                                String pass = p1.getText();
                                if(uname.equals("admin") && pass.equals("1234"))
                                {
                                   //this.EXIT_ON_CLOSE;
                                    dispose();
                                    SearchWindow home = new SearchWindow();
                                    
                                    
                                 }
                                 else
                                 {
                                   JOptionPane.showMessageDialog(null,"Incorrect login or password","Error",JOptionPane.INFORMATION_MESSAGE); 
                                 }
			}
		});
 }

 }
