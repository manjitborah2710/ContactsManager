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
				try {
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
							sc.nextLine();
							String username,pwd;
							do {
								System.out.println("Enter username");
								username=sc.nextLine().trim();
								System.out.println("Enter password");
								pwd=sc.nextLine().trim();
							}while(username.equals("") || pwd.equals(""));
							Accounts accs=new Accounts();
							boolean res=accs.deleteAccount(username, pwd);
							if(res) {
								System.out.println("User deleted !!");
							}
							else {
								System.out.println("Could not delete user");
							}
							break;
						}
						case 4:{
							System.exit(0);
							break;
						}
						default:{
							System.out.println("Choose a valid option..");
							break;
						}
					}
				}
				catch(InputMismatchException e) {
					System.out.println("Wrong input. Please run the app again and choose correct options");
					System.exit(-1);
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
						sc.nextLine();
						try {
							String name;
							do{
								System.out.println("Enter name");
								name=sc.nextLine().trim();
							}while(name.equals(""));
							
							System.out.println("Enter Phone no 1");
							String phnno1=sc.nextLine().trim();
							System.out.println("Enter Phone no 2");
							String phnno2=sc.nextLine().trim();
							System.out.println("Enter email");
							String email=sc.nextLine().trim();
							System.out.println("Enter address");
							String addr=sc.nextLine().trim();
							
							Contacts cts=new Contacts(user);
							boolean isSuccessful=cts.addContact(name, phnno1, phnno2, email, addr);
							if(isSuccessful) {
								System.out.println("Contact added!");
							}
							else {
								System.out.println("Contact could not be added!!!!! Try again");
							}
							
						}
						catch(Exception e) {
							e.printStackTrace();
						}
						
						break;
					}
					case 3:{
						
						
						try {
							Contacts cts = new Contacts(user);
							System.out.println("Select an id from your available contacts to delete the contact-\n\n");
							cts.viewContacts();
							System.out.println("Enter contact ID");
							int cid=sc.nextInt();
							int isSuccessful=cts.deleteContact(cid);
							if(isSuccessful==1) {
								System.out.println("Contact deleted");
							}
							else if(isSuccessful==0){
								System.out.println("Could not find contact");
							}
							else {
								System.out.println("Error deleting contact");
							}
						} catch (SQLException e) {
							System.out.println("Error deleting contact");
							e.printStackTrace();
						}
						
						
						break;
					}
					case 4:{
						try {
							Contacts cts = new Contacts(user);
							System.out.println("Select an id from your available contacts to update the contact-\n\n");
							cts.viewContacts();
							System.out.println("Enter contact ID");
							int cid=sc.nextInt();
							
							sc.nextLine();
							
							
							Map<String,String> updateMap=new HashMap<>();
							System.out.println("Enter name (Press enter to keep the earlier value)");
							String name=sc.nextLine().trim();
							if(!name.equals("")) {
								updateMap.put("name",name);
							}
							System.out.println("Enter Phone no 1 (Press enter to keep the earlier value)");
							String phnno1=sc.nextLine().trim();
							if(!phnno1.equals("")) {
								updateMap.put("phnno1",phnno1);
							}
							System.out.println("Enter Phone no 2 (Press enter to keep the earlier value)");
							String phnno2=sc.nextLine().trim();
							if(!phnno2.equals("")) {
								updateMap.put("phnno2",phnno2);
							}
							System.out.println("Enter email (Press enter to keep the earlier value)");
							String email=sc.nextLine().trim();
							if(!email.equals("")) {
								updateMap.put("email",email);
							}
							System.out.println("Enter address (Press enter to keep the earlier value)");
							String addr=sc.nextLine().trim();
							if(!addr.equals("")) {
								updateMap.put("addr",addr);
							}
							
							int res=cts.updateContact(cid, updateMap);
							if(res > 0) {
								System.out.println("Contact updated");
							}
							else if(res == 0) {
								System.out.println("Could not find contact");
							}
	
							
						} catch (SQLException e) {
							System.out.println("Update error !!!");
							e.printStackTrace();
						}
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
