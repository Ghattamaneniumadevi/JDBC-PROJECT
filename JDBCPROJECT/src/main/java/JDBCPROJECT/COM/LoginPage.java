package JDBCPROJECT.COM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginPage extends HomePage  {
   
static Boolean islogin=false;
static String loginUserEmail="";
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_project","root","root");
	    PreparedStatement ps=con.prepareStatement("select * from  User where email=? AND password=?");
	    Scanner scan=new Scanner(System.in);
	    System.out.println("enter the email please");
	    String email=scan.next();
	    System.out.println("enter the password");
	    int pasword=scan.nextInt();
	   ps.setString(1, email);
	   ps.setInt(2, pasword);
	   ResultSet rs=ps.executeQuery();
	   if(rs.next()){
		   System.out.println("login successfully");
		   islogin=true;
		   loginUserEmail=email;
		   
		   
	   }
	   else {
		   System.out.println("Inavlid email id password");
	   }
	    
	}
	static void viewDetils(Connection con,Scanner scan) throws SQLException {
		while(islogin) {
			System.out.println("1.view Detiles");
			System.out.println("2.Enter your Choies");
			int choices=scan.nextInt();
			scan.nextLine();
			switch(choices) {
			case 1:
				displayUserDetiles(con,loginUserEmail);
				break;
			case 2:
				islogin=false;
				loginUserEmail="";
				System.out.println("LOGIN OUT SUCCESSFULLY.....");
				break;
				default:
					System.out.println("invalied choices please try again");
				
				
			}
			
		}
		
	}
	public static void displayUserDetiles(Connection con,String email) throws SQLException {
		PreparedStatement ps=con.prepareStatement("select * from user where email=?");
		ps.setString(1, email);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			System.out.println("iD"+rs.getInt("id"));
			System.out.println("Name"+rs.getString("name"));
			System.out.println("phonenumber"+rs.getLong("phone_Number"));
			System.out.println("email"+rs.getString("email"));
			
		}
	}

}
