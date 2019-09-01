package root;
import java.sql.*;
public class Contacts {
	String username;
	Connection conn;
	Statement stmt;
	public Contacts(String username) throws SQLException {
		this.username=username;
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_manager","root","password");
		stmt=conn.createStatement();
	}
	public void viewContacts() throws SQLException {
		ResultSet res=stmt.executeQuery("select * from contacts where uid='"+username+"';");
		String ans="";
		while(res.next()) {
			ans+="Contact ID : "+res.getString(1)+"\nName : "+res.getString(2)+"\nPhone no. 1 : "+res.getString(3)+"\nPhone no. 2"+res.getString(4)+"\nEmail : "+res.getString(5)+"\nAddress : "+res.getString(6);
			ans+="\n_________________________________________________________\n\n";
		}
		System.out.println("\n"+ans);
	}
}
