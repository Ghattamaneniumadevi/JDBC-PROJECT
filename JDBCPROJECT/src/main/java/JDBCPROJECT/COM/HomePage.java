package JDBCPROJECT.COM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HomePage {
public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_project","root","root");
	    Scanner scan=new Scanner(System.in);
	    System.out.println("===============================");
	    System.out.println("wlecome to my application");
	    System.out.println("================================");
	     int choices=0;
		    while(choices!=3) {
		    	System.out.println("1.Register");
		    	System.out.println("2 Login");
		    	System.out.println("3 Exit");
		    	System.out.println("Enter your choices");
		    	choices=scan.nextInt();
		    	scan.nextLine();
		    	switch(choices) {
		    	case 1:register(con,scan);
		    	break;
		    	case 2:login(con,scan);
		    	break;
		    	
				default:
					System.out.println("Invalid choies try ");
		    	}    	
	    }
		    con.close(); 
}
                 public static void register(Connection con,Scanner scan) throws SQLException {
                	 PreparedStatement ps=con.prepareStatement("insert into User values(?,?,?,?,?)");
      			   System.out.println("Enter the id please");
      				int id=scan.nextInt();
      				System.out.println("Enter the name please");
      				String name=scan.next();
      				System.out.println("Enter the phone numaber");
      				long phoneNumaber=scan.nextLong();
      				System.out.println("Enter your email please");
      				String email=scan.next();
      				System.out.println("enter password");
      				int password=scan.nextInt();
      				ps.setInt(1, id);
      				ps.setString(2, name);
      				ps.setLong(3, phoneNumaber);
      				ps.setString(4, email);
      				ps.setInt(5,password);
      				ps.executeUpdate();
      				System.out.println("============registartion is successfully===============");
                 }

         		public static void login(Connection con,Scanner scan) throws SQLException {
         			System.out.println("==========================");
         			System.out.println("LOGIN form");
         			System.out.println("===========================");
         		    System.out.println("enter the email");
         			String email=scan.next();
         			System.out.println("Enter the password");
         			int password=scan.nextInt();
         			PreparedStatement ps=con.prepareStatement("select * from User where email=? and password=?");
         				ps.setString(1, email);
         				ps.setInt(2, password);
         				ResultSet rs=ps.executeQuery();
         				if(rs.next()) {
         					System.out.println("login successfully");
         					int choices=0;
         					while(choices!=2) {
         						System.out.println("=======================");
         						System.out.println("1. view your detiles");
         						System.out.println("2.Logout");
         						System.out.println("======================");
         						System.out.println("enter your choices");
         						choices=scan.nextInt();
         						
        						switch(choices) {
        						case 1: viewUserDetiles(con,email);
        						break;
        						case 2:
        							System.out.println("Logout");
        							break;
        							default:
        								System.out.println("Invalid email or password please check");
        							
      	}
        						
}

}
        	}
         		public static void viewUserDetiles(Connection con,String email) throws SQLException {
        			PreparedStatement ps=con.prepareStatement("select * from User where email=?");
        			ps.setString(1, email);
        			ResultSet rs=ps.executeQuery();
        			if(rs.next()) {
        				System.out.println("====================================");
        				System.out.println("User Details");
        				System.out.println("===================================");
        				System.out.println("ID:"+" "+rs.getInt("id"));
        				System.out.println("Name:"+" "+rs.getString("name"));
        				System.out.println("phone_Number:"+" "+rs.getLong("phone_Number"));
        				System.out.println("Email:"+" "+rs.getString("email"));
        			}
        			else {
        			
        	       System.out.println("USER IS NOT FOUND.....PLEASE TYE AGIN");
        			
           }
         		}
}
		
		 

	

