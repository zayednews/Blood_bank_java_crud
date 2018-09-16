/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbankmain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Progey Tech 3
 */
public class SearchWindow extends JFrame {
	
	private JPanel panel;
	private JLabel labelForm, labelSearch;
	private JComboBox comboBlood;
	private JButton buttonSearch, buttonAdd, buttonEdit, buttonLogout;
	
	public SearchWindow() {
		this.initializeComponents();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void initializeComponents()	{
		
		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.setTitle("Search Blood Donator");
		this.setSize(450, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.labelForm = new JLabel("Search With Blood Group");
		this.labelForm.setBounds(150, 30, 250, 20);
		this.panel.add(this.labelForm);
		
		this.labelSearch = new JLabel("Blood Group");
		this.labelSearch.setBounds(80, 110, 180, 20);
		this.panel.add(this.labelSearch);

		String dept[] = new String[] {"A +ve", "A -ve", "B +ve", "B -ve", "O +ve", "O -ve", "AB +ve", "AB -ve"};
		
		this.comboBlood = new JComboBox(dept);
		this.comboBlood.setBounds(200, 110, 180, 20);
		this.panel.add(this.comboBlood);

		
		this.buttonAdd = new JButton("Add New Donator");
		this.buttonAdd.setBounds(10, 205, 140, 30);
		this.panel.add(this.buttonAdd);
		
                this.buttonEdit = new JButton("Edit Donator");
		this.buttonEdit.setBounds(280, 205, 140, 30);
		this.panel.add(this.buttonEdit);
                
		this.buttonSearch  = new JButton("Search");
		this.buttonSearch.setBounds(165, 200, 100, 40);
		this.panel.add(this.buttonSearch);
                
                this.buttonLogout  = new JButton("Logout");
		this.buttonLogout.setBounds(165, 245, 100, 40);
		this.panel.add(this.buttonLogout);
		
		this.add(this.panel);
		
		this.buttonAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DonatorAdder();
			}
		});
		
                this.buttonLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
                            dispose();
                            new LoginForm();
			}
		});
                
                this.buttonEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DonatorEdit();
			}
		});
                
		this.buttonSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DBAccess db = new DBAccess();
				new SearchResult(db.getData(comboBlood.getSelectedItem().toString()));
			}
		});
	}
}
