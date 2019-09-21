package root;
import java.sql.*;
import java.util.Map;
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
	public boolean addContact(String name,String phnno1,String phnno2,String email,String addr) {
		try {
			stmt.executeUpdate("insert into contacts (name,phnno1,phnno2,email,address,uid) values ('"+name+"','"+phnno1+"','"+phnno2+"','"+email+"','"+addr+"','"+username+"');");
			return true;
		}
		catch(SQLException e) {
			return false;
		}
	}
	public int deleteContact(int cid) {
		try {
			int no_rows=stmt.executeUpdate("delete from contacts where cid="+cid+" and uid='"+username+"';");
			if(no_rows > 0)return 1;
			else return 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public int updateContact(int cid,Map<String,String> m) {
//		System.out.println(m.toString());
		if(m.isEmpty()) {
			System.out.println("Nothing to update!!");
			return -2;
		}
		String attrs="";
		if(m.containsKey("name")) {
			attrs+=",name='"+m.get("name")+"'";
		}
		if(m.containsKey("phnno1")) {
			attrs+=",phnno1='"+m.get("phnno1")+"'";
		}
		if(m.containsKey("phnno2")) {
			attrs+=",phnno2='"+m.get("phnno2")+"'";
		}
		if(m.containsKey("email")) {
			attrs+=",email='"+m.get("email")+"'";
		}
		if(m.containsKey("addr")) {
			attrs+=",address='"+m.get("addr")+"'";
		}
		String q="update contacts set "+attrs+" where cid="+cid+" and uid='"+username+"';";
		q=q.replaceFirst(",", "");
		
//		System.out.println(q);
		try {
			int no_rows=stmt.executeUpdate(q);
			return no_rows;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
}
