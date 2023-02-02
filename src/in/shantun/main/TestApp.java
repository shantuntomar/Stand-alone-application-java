package in.shantun.main;

import java.io.*;
import in.shantun.controller.IStudentController;
import in.shantun.factory.StudentControllerFactory;
import in.shantun.dto.Student;

public class TestApp {

	public static void main(String[] args) {
		IStudentController studentController = null;
		String status = null, name = null, city = null, email = null, country = null;
		Integer id = null;
		Student student = null;
		Student studentRecord = null;
		
		//taking input from the user.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while(true) {
				
				System.out.println("1 : CREATE ");
				System.out.println("2 : READ ");
				System.out.println("3 : UPDATE ");
				System.out.println("4 : DELETE ");
				System.out.println("5 : EXIT ");
				Integer option = Integer.parseInt(br.readLine());
				
				//creating object of controller .
				studentController = StudentControllerFactory.getStudentController();
				
				switch(option) {
				
					case 1 :
						System.out.print("Enter the name:: ");
						name = br.readLine();
						System.out.print("Enter the city:: ");
						city = br.readLine();
						System.out.print("Enter the email:: ");
						email = br.readLine();
						System.out.print("Enter the country:: ");
						country = br.readLine();
						
						student = new Student();
						student.setName(name);
						student.setCountry(country);
						student.setCity(city);
						student.setEmail(email);
						
						//to create student object you have to send student object.
						status = studentController.save(student);
						
						if(status.equalsIgnoreCase("success")) {
							System.out.print("RECORD INSERTED SUCCESSFULLY...");
						}
						else if(status.equalsIgnoreCase("failure")) {
							System.out.print("RECORD NOT INSERTED...");
						}
						else {
							System.out.print("SOME PROBLEM OCCURR...");
						}
						break;
						
					case 2 :
						System.out.print("Enter the id:: ");
						id = Integer.parseInt(br.readLine());
						studentRecord = studentController.findById(id);
						if (studentRecord != null)
							System.out.println(studentRecord);
						else
							System.out.println("Record not available for the given id ::" + id);
						break;
					
					case 3 :
						System.out.print("Enter the id you want to update:: ");
						id = Integer.parseInt(br.readLine());
						studentRecord = studentController.findById(id);
						
						if(studentRecord != null) {
							Student newStudent = new Student();
							newStudent.setId(id);
							System.out.println("-------------------------------------------");
							System.out.println("Student old name is :: " + studentRecord.getName());
							String newName = br.readLine();
							if(newName == null || newName == "") newStudent.setName(studentRecord.getName());
							else newStudent.setName(newName);
							System.out.println("-------------------------------------------");
							System.out.println("Student old Email is :: " + studentRecord.getEmail());
							String newEmail = br.readLine();
							if(newEmail == null || newEmail == "") newStudent.setEmail(studentRecord.getEmail());
							else newStudent.setEmail(newEmail);
							System.out.println("-------------------------------------------");
							System.out.println("Student old City is :: " + studentRecord.getCity());
							String newCity = br.readLine();
							if(newCity == null || newCity == "") newStudent.setCity(studentRecord.getCity());
							else newStudent.setCity(newCity);
							System.out.println("-------------------------------------------");
							System.out.println("Student old Country is :: " + studentRecord.getCountry());
							String newCountry = br.readLine();
							if(newCountry == null || newCountry == "") newStudent.setCountry(studentRecord.getCountry());
							else newStudent.setCountry(newCountry);
							
							status = studentController.updateById(newStudent);
							if(status.equalsIgnoreCase("success")) {
								System.out.print("RECORD UPDATED SUCCESSFULLY...");
							}
							else if(status.equalsIgnoreCase("failure")) {
								System.out.print("RECORD NOT UPDATED...");
							}
							else {
								System.out.print("SOME PROBLEM OCCURR...");
							}
						}
						else System.out.print("RECORD NOT PRESENT IN THE DB...");
						break;
						
					case 4 :
						System.out.print("Enter the id:: ");
						id = Integer.parseInt(br.readLine());
						status = studentController.deleteById(id);
						if(status.equalsIgnoreCase("success")) {
							System.out.println("RECORD DELETED SUCCESSFULLY...");
						}
						else if(status.equalsIgnoreCase("failure")) {
							System.out.print("RECORD NOT DELETED...");
						}
						else {
							System.out.print("RECORD NOT PRESENT...");
						}
						break;	
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
