package connexion;

import java.sql.*;  

class MysqlCon{  
	public static void main(String args[]){  
		try{  
			
			Class.forName("com.mysql.jdbc.Driver");  
		  
		Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3308/bdd","root","root");  
		 System.out.println("ok");
		con.close();
		
		}catch(Exception e){ System.out.println(e);}  
	}  
}  