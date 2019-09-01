package root;
import java.sql.*;
public class Accounts {
	public boolean login(String username,String pwd) {
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_manager","root","password");
			Statement stmt=conn.createStatement();
			ResultSet res=stmt.executeQuery("select uid,password from users where uid='"+username+"';");
			if(count(res)!=1) {
				System.out.println("User not found. Register before you login.");
				return false;
			}
			res.first();
			if(!pwd.equals(res.getString("password"))) {
				System.out.println("The password you entered is incorrect..");
				return false;
			}
			
			return true;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		
	}
	private int count(ResultSet s) throws SQLException{
		int c=0;
		while(s.next()) {
			c++;
		}
		return c;
	}
	public boolean register(String uname,String pwd) {
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_manager","root","password");
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("insert into users values ('"+uname+"','"+pwd+"');");
			System.out.println("New user "+ uname + " created.");
			return true;
		}
		catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("This username is already taken..Try a different username.");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
