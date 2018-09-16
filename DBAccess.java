/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbankmain;

//import java.beans.Statement;
import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
/**
 *
 * @author Progey Tech 3
 */
public class DBAccess {
    String query;
	Connection con;
	Statement st;
	ResultSet rs;
	
	public DBAccess() {
		//createTable();
	}
	
	public void connectToDB()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
                        System.out.println("Driver loaded!");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbank","root","");
                        System.out.println("Connection Done!");
			st = con.createStatement();
		} catch(Exception e) {
                        System.out.println("Problem to Conect To DB!");
			e.printStackTrace();
                         
		}
	}
	
	public void createTable()
	{
		connectToDB();
		query = "SELECT * FROM 	bbank where table_name = 'DONATORS'";
		try {
			rs = st.executeQuery(query);
			if(rs.next()) {
			}
			else {
				query = "CREATE TABLE DONATORS"
					  + "(MOB			VARCHAR(50),"
					  + "NAME			VARCHAR(50),"
					  + "EMAIL			VARCHAR(50),"
					  + "AGE			VARCHAR(50),"
					  + "ADRS			VARCHAR(50),"
					  + "B_GROUP		VARCHAR(50))";
				st.executeUpdate(query);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
        public static boolean validEmail(String email) {
            return email.matches("[a-zA-Z0-9._%+-][a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{3}");
        }
        
	public void addToDB(String mob, String name, String email, String age, String adrs, String b_group) {
		connectToDB();
		try {
                        query = "SELECT * FROM DONATORS WHERE MOB = '"+mob+"'";
			rs = st.executeQuery(query);
			if(!rs.next()) {
                                validEmail(email);
                                if(validEmail(email))
                                {
                                       query = "INSERT INTO DONATORS"
					+ "(MOB, NAME, EMAIL, AGE, ADRS, B_GROUP) "
					+ "VALUES"
					+ "('"+mob+"', '"+name+"', '"+email+"', '"+age+"', '"+adrs+"', '"+b_group+"')";
                                    st.executeUpdate(query);
                                    JOptionPane.showMessageDialog(null, "Donator Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    st.close();
                                    con.close(); 
                                }
                                else{
                                     JOptionPane.showMessageDialog(null, "Email Address is not Correct!", "Error ! !", JOptionPane.INFORMATION_MESSAGE);
                                    st.close();
                                    con.close();
                                }
			}
                        else {
                            JOptionPane.showMessageDialog(null, "Mobile Number Exist in DB!", "Error ! !", JOptionPane.INFORMATION_MESSAGE);
                                    st.close();
                                    con.close();
                        }
                                 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
        public void editToDB(String mob, String name, String email, String age, String adrs, String b_group) {
		connectToDB();
		try {
                        query = "SELECT * FROM DONATORS WHERE MOB = '"+mob+"'";
			rs = st.executeQuery(query);
			if(!rs.next()) {
                                    JOptionPane.showMessageDialog(null, "Mobile Number Not Exist in DB!", "Error ! !", JOptionPane.INFORMATION_MESSAGE);
                                    st.close();
                                    con.close();
			}
                        else {
                                validEmail(email);
                                if(validEmail(email))
                                {
                                       query = "Update DONATORS "
					+ "set NAME='"+name+"', EMAIL='"+email+"', AGE='"+age+"', ADRS='"+adrs+"', B_GROUP='"+b_group+"' "
					+ "where MOB='"+mob+"'";
                                    st.executeUpdate(query);
                                    JOptionPane.showMessageDialog(null, "Donator Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    st.close();
                                    con.close(); 
                                }
                                else{
                                     JOptionPane.showMessageDialog(null, "Email Address is not Correct!", "Error ! !", JOptionPane.INFORMATION_MESSAGE);
                                    st.close();
                                    con.close();
                                }
                        }
                                 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
        public void deleteToDB(String mob, String name, String email, String age, String adrs, String b_group) {
		connectToDB();
		try {
                        query = "SELECT * FROM DONATORS WHERE MOB = '"+mob+"'";
			rs = st.executeQuery(query);
			if(!rs.next()) {
                                    JOptionPane.showMessageDialog(null, "Mobile Number Not Exist in DB!", "Error ! !", JOptionPane.INFORMATION_MESSAGE);
                                    st.close();
                                    con.close();
			}
                        else {
                                
                                       query = "Delete from DONATORS "
					+ "where MOB='"+mob+"'";
                                    st.executeUpdate(query);
                                    JOptionPane.showMessageDialog(null, "Donator Deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    st.close();
                                    con.close(); 
                               
                        }
                                 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
	public Vector<Vector<String>> getData(String b_group) {
		Vector<Vector<String>> vss = new Vector<Vector<String>>();
		connectToDB();
		try {
			query = "SELECT * FROM DONATORS WHERE B_GROUP = '"+b_group+"'";
			rs = st.executeQuery(query);
			if(!rs.next()) {
				JOptionPane.showMessageDialog(null, "No Donator Found!", "Empty", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				rs.close();
				rs = st.executeQuery(query);
				while(rs.next()) {
					Vector<String> vc = new Vector<String>();
					for(int i=1; i<=6; i++) {
						vc.add(rs.getString(i));
					}
					vss.add(vc);
				}
			}
		} catch (Exception e) {
		}
		return vss;
	}
	
}