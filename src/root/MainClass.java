package root;
import java.util.*;
import java.sql.*;
public class MainClass {
	public static void main(String []args) {
		Scanner sc=new Scanner(System.in);
		boolean logged_in=false;
		String user=null;
		
		while(true) {
			while(!logged_in) {
				System.out.println("Choose from below\n1. Login\t2. Register\t3. Delete account\t4. Exit");
				int opt=sc.nextInt();
				switch(opt) {
					case 1:{
						System.out.println("Enter username : ");
						sc.nextLine();
						String username=sc.nextLine().trim();
						System.out.println("Enter password : ");
						String pwd=sc.nextLine().trim();
						Accounts accs=new Accounts();
						logged_in=accs.login(username, pwd);
						if(logged_in) {
							user=username;
							System.out.println("Successfully logged in");
						}
						break;
					}
					case 2:{
						System.out.println("Enter username : ");
						sc.nextLine();
						String username=sc.nextLine();
						String pwd,cpwd;
						do {
							System.out.println("Enter password : ");
							pwd=sc.nextLine().trim();
							System.out.println("Confirm password : ");
							cpwd=sc.nextLine().trim();
						}while(!pwd.equals(cpwd));
						
						Accounts acc=new Accounts();
						acc.register(username,pwd);
						break;
					}
					case 3:{
						
						break;
					}
					case 4:{
						System.exit(0);
						break;
					}
				}
			}
			while(logged_in) {
				System.out.println("Choose from below\n1. View Contacts\t2. Add Contact\t3. Delete Contact\t4. Update contact\t5. Logout\t6. Logout and exit");
				int opt=sc.nextInt();
				switch(opt) {
					case 1:{
						//view contacts
						try {
							Contacts cts=new Contacts(user);
							cts.viewContacts();
						}
						catch(SQLException e) {
							e.printStackTrace();
						}
						break;
					}
					case 2:{
						
						break;
					}
					case 3:{
						
						break;
					}
					case 5:{
						logged_in=false;
						user=null;
						break;
					}
					case 6:{
						System.exit(0);
						break;
					}
				}
				
			}
		}
	}
}
