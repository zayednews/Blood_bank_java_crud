/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbankmain;

import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Progey Tech 3
 */
public class SearchResult extends JFrame {
	
	JTable table;	
	DefaultTableModel model;
	JScrollPane scroll;
	Vector<Vector<String>> vss;
	
	public SearchResult() {
	}
	
	public SearchResult(Vector<Vector<String>> vss) {
		if(vss.isEmpty()) {
			return;
		}
		this.vss = vss;
		initComps();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initComps() {
		this.setTitle("Donator List");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(800, 500);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Mobile");
		columnNames.add("Name");
		columnNames.add("Email");
		columnNames.add("Age");
		columnNames.add("Address");
		columnNames.add("Blood Group");
		
		model = new DefaultTableModel(vss, columnNames);
		
		table = new JTable(model);
		
		scroll = new JScrollPane(table);
		
		this.add(scroll);
	}

}