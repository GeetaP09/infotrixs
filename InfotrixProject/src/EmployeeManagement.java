import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
class Employee implements Serializable {
	int id;
	String name;
	float salary;
	long contact_no;
	String email;
	
	public Employee(int id,String name,float salary,long contact_no,String email) {
		this.id=id;
		this.name=name;
		this.salary=salary;
		this.contact_no=contact_no;
		this.email=email;
	}
	
	public String toString() {
		return "\nEmployee Details: " + "\nID: " + this.id + "\nName: " + this.name + "\nSalary: " + this.salary +
				"\nContact No: " + this.contact_no + "\nEmail: " + this.email;
	}
	
}

public class EmployeeManagement{
	static void display(ArrayList<Employee> arr) {
		System.out.println("\n              ---------------Employee Details--------------             ");
		System.out.println(String.format("%-10s%-15s%-10s%-20s%-10s","ID","name","salary","contact-no","email"));
		for(Employee e:arr) {
			System.out.println(String.format("%-5s%-20s%-10s%-15s%-10s",e.id,e.name,e.salary,e.contact_no,e.email));
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) {
		int id;
		String name;
		float salary;
		long contact_no;
		String email;
		
		Scanner sc=new Scanner(System.in);
		ArrayList<Employee> arr=new ArrayList<Employee>();
		
		File f=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		try {
			f=new File("C:/Users/HP/Desktop/CNCGEETA/AdvJava/Infotrix/EmployeeManagement.txt");
			if(f.exists()) {
				fis=new FileInputStream(f);
				ois=new ObjectInputStream(fis);
				arr=(ArrayList<Employee>)ois.readObject();
			}
		}
		catch(Exception ee) {
			System.out.println(ee);
		}
		
		do {
			System.out.println("\n ****************Welcome to Employee Management System*****************");
			System.out.println("1). Add employee details\n"
					+"2). Search for employee\n"
					+"3). Edit employee details\n"
					+"4). Delete Employee details\n"
					+"5). Display all employees working in this company\n"
					+"6). Exit\n");
			System.out.println("Enter your choice : ");
			int ch=sc.nextInt();
			
			switch(ch) {
				case 1:
					System.out.println("\nEnter the following details to add Employee Details: ");
					System.out.println("Enter Id: ");
					id=sc.nextInt();
					System.out.println("Enter Name: ");
					name=sc.next();
					System.out.println("Enter Salary: ");
					salary=sc.nextFloat();
					System.out.println("Enter Contact-No: ");
					contact_no=sc.nextLong();
					System.out.println("Enter Email: ");
					email=sc.next();
					arr.add(new Employee(id,name,salary,contact_no,email));
					display(arr);
					break;
				
				case 2:
					System.out.println("Enter the Employee Id to search");
					id=sc.nextInt();
					int i=0;
					for(Employee e:arr) {
						if(id==e.id) {
							System.out.println(e+"\n");
							i++;
						}
					}
					if(i==0) {
						System.out.println("Employee Details are not Available, please enter valid Id");
					}
					break;
				
				case 3:
					System.out.println("\n Enter Employee Id to Edit the Details: ");
					id=sc.nextInt();
					int j=0;
					for(Employee e:arr) {
						if(id==e.id) {
							j++;
						do {
							int ch1=0;
							System.out.println("\nEdit Employee Details: \n"
									+ "1). Employee Id:\n"
									+ "2). Name:\n"
									+ "3). Salary:\n"
									+ "4). Contact No:\n"
									+ "5). Email:\n"
									+ "6). Go Back\n");
							System.out.println("Enter Your Choice");
							ch1=sc.nextInt();
							switch(ch1) {
							case 1:
								System.out.println("\nEnter new Employee Id: ");
								e.id=sc.nextInt();
								System.out.println(e+"\n");
								break;
							
							case 2:
								System.out.println("\nEnter new Employee Name: ");
								e.name=sc.next();
								System.out.println(e+"\n");
								break;
								
							case 3:
								System.out.println("\nEnter new Employee Salary: ");
								e.salary=sc.nextFloat();
								System.out.println(e+"\n");
								break;
								
							case 4:
								System.out.println("\nEnter new Employee Contact No: ");
								e.contact_no=sc.nextLong();
								System.out.println(e+"\n");
								break;
							
							case 5:
								System.out.println("\nEnter new Employee Id: ");
								e.email=sc.next();
								System.out.println(e+"\n");
								break;
								
							case 6:
								j++;
								break;
							
							default : 
								System.out.println("\nEnter Your Correct Choice. ");
								break;
							}
						}
						while(j==1);
						}
					}
					if(j==0) {
						System.out.println("\nEmployee Details are not available,Please enter valid Id.");
					}
					break;
				
				case 4:
					System.out.println("\nEnter Employee id  to Delete Details: ");
					id=sc.nextInt();
					int k=0;
					try {
						for(Employee e:arr) {
							if(id==e.id) {
								arr.remove(e);
								display(arr);
								k++;
							}
						}
						if(k==0) {
							System.out.println("\nEmployee Details are not available,Please enter valid Id.");
						}
					}
					catch(Exception ex) {
						System.out.println(ex);
					}
					break;
					
				case 5:
					try {
						arr =(ArrayList<Employee>)ois.readObject();
					}
					catch(ClassNotFoundException ce) {
						System.out.println(ce);
					}
					catch(Exception ee2) {
						System.out.println(ee2);
					}
					display(arr);
					break;
					
				case 6:
					try {
						fos=new FileOutputStream(f);
						oos=new ObjectOutputStream(fos);
						oos.writeObject(arr);
					}
					catch(IOException io){
						io.printStackTrace();
					}
					catch(Exception ee3) {
						ee3.printStackTrace();
					}
					finally {
						try {
							fis.close();
							ois.close();
							fos.close();
							oos.close();
						}
						catch(Exception ee4) {
							ee4.printStackTrace();
						}
					}
					System.out.println("\n You have choosen to EXIT!! Saving Files and Closing.");
					sc.close();
					System.exit(0);
					break;
					
				default:
					System.out.println("\n Enter correct Choice From the Choices..");
					break;
			}
		}
		while(true);
	}
}
