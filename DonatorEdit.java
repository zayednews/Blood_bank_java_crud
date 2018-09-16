/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbankmain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Progey Tech 3
 */
public class DonatorEdit extends JFrame {

	private JPanel panel;
	private JLabel labelForm, labelMob, labelName, labelAge, labelBlood, labelEmail, labelAdrs;
	private JTextField textMob, textName, textEmail, textAge, textAdrs;
	private JComboBox comboBlood;
	private JButton buttonEdit, buttonShow, buttonDelete;
	
	public DonatorEdit() {
		this.initializeComponents();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void initializeComponents()	{
		
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Edit Donator");
		this.setSize(450, 350);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.labelForm = new JLabel("Donator Details");
		this.labelForm.setBounds(150, 10, 250, 20);
		this.panel.add(this.labelForm);
		
		this.labelMob = new JLabel("Mobile Number");
		this.labelMob.setBounds(20, 50, 180, 20);
		this.panel.add(this.labelMob);
		
		this.textMob = new JTextField();
		this.textMob.setBounds(200, 50, 180, 20);
		this.panel.add(this.textMob);
		
		this.labelName = new JLabel("Name");
		this.labelName.setBounds(20, 80, 180, 20);
		this.panel.add(this.labelName);
		
		this.textName = new JTextField();
		this.textName.setBounds(200, 80, 180, 20);
		this.panel.add(this.textName);
		
		this.labelEmail = new JLabel("Email");
		this.labelEmail.setBounds(20, 110, 180, 20);
		this.panel.add(this.labelEmail);
		
		this.textEmail = new JTextField();
		this.textEmail.setBounds(200, 110, 180, 20);
		this.panel.add(this.textEmail);
		
		this.labelAge = new JLabel("Age");
		this.labelAge.setBounds(20, 140, 180, 20);
		this.panel.add(this.labelAge);
		
		this.textAge = new JTextField();
		this.textAge.setBounds(200, 140, 180, 20);
		this.panel.add(this.textAge);
		
		this.labelAdrs = new JLabel("Address");
		this.labelAdrs.setBounds(20, 170, 180, 20);
		this.panel.add(this.labelAdrs);
		
		this.textAdrs = new JTextField();
		this.textAdrs.setBounds(200, 170, 180, 20);
		this.panel.add(this.textAdrs);

		this.labelBlood = new JLabel("Blood Group");
		this.labelBlood.setBounds(20, 200, 180, 20);
		this.panel.add(this.labelBlood);

		//String dept[] = new String[] {"A +ve", "A -ve", "B +ve", "B -ve", "O +ve", "O -ve", "AB +ve", "AB -ve"};
		String dept[] = new String[] {""};
		this.comboBlood = new JComboBox(dept);
                //this.comboBlood = new JComboBox();
		this.comboBlood.setBounds(200, 200, 180, 20);
		this.panel.add(this.comboBlood);
		
		this.buttonEdit = new JButton("Edit");
		this.buttonEdit.setBounds(95, 240, 70, 40);
		this.panel.add(this.buttonEdit);
		
                this.buttonShow = new JButton("Show");
		this.buttonShow.setBounds(175, 240, 70, 40);
		this.panel.add(this.buttonShow);
                
                this.buttonDelete = new JButton("Delete");
		this.buttonDelete.setBounds(255, 240, 70, 40);
		this.panel.add(this.buttonDelete);
                
		this.add(this.panel);
		
		this.buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String mob, name, email, age, adrs, b_group;
				mob = textMob.getText();
				name = textName.getText();
				email = textEmail.getText();
				age = textAge.getText();
				adrs = textAdrs.getText();
				b_group = comboBlood.getSelectedItem().toString();
				
				DBAccess db = new DBAccess();
                                if(!mob.trim().isEmpty() && mob!=null)
                                {   
                                    db.editToDB(mob, name, email, age, adrs, b_group); 
                                }
				else
                                {
                                    JOptionPane.showMessageDialog(null, "Data Not Provided!", "Error ! !", JOptionPane.INFORMATION_MESSAGE);
                                
                                }
			}
		});
                
                this.buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String mob, name, email, age, adrs, b_group;
				mob = textMob.getText();
				name = textName.getText();
				email = textEmail.getText();
				age = textAge.getText();
				adrs = textAdrs.getText();
				b_group = comboBlood.getSelectedItem().toString();
				
				DBAccess db = new DBAccess();
				db.deleteToDB(mob, name, email, age, adrs, b_group);
			}
		});
                this.buttonShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
                                Connection conn=null;
                                Statement pst=null;
                                ResultSet rs=null;
                                
                                try{
                                        Class.forName("com.mysql.jdbc.Driver");
                                        System.out.println("Driver loaded!");
                                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbank","root","");
                                        System.out.println("Connection Done!");
                                        pst = conn.createStatement();
                                } catch(Exception e) {
                                        System.out.println("Problem to Conect To DB!");
                                        e.printStackTrace();

                                }
                                try {
                                        String sql = "SELECT NAME,EMAIL,AGE,ADRS,B_GROUP FROM DONATORS WHERE MOB ='"+textMob.getText()+"'";
                                        pst=conn.prepareStatement(sql);
                                       // pst.setString(1,textMob.getText());

                                        rs = pst.executeQuery(sql);
                                    if(rs.next()) { 
                                        textName.setText(rs.getString("NAME"));
                                        textEmail.setText(rs.getString("EMAIL"));
                                        textAge.setText(rs.getString("AGE"));
                                        textAdrs.setText(rs.getString("ADRS"));
                                        comboBlood.addItem(rs.getString("B_GROUP"));
                                        //comboBlood.setSelectedItem("B_GROUP");
                                        comboBlood.setSelectedIndex(1);
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Mobile Number Not Exist in DB!", "Error ! !", JOptionPane.INFORMATION_MESSAGE);
                                        pst.close();
                                        conn.close();
                                    }
                                    } catch (SQLException e ) {
                                    JOptionPane.showMessageDialog(null, e);

                                    }
		}});
	}
}