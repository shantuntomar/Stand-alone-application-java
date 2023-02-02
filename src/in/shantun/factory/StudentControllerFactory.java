package in.shantun.factory;

import in.shantun.controller.IStudentController;
import in.shantun.controller.StudentControllerImpl;

public class StudentControllerFactory {
	
	private static IStudentController studentController = null;
	
	private StudentControllerFactory() {
		
	}
	
	public static IStudentController getStudentController() {
		if(studentController == null) studentController = new StudentControllerImpl();
		return studentController;
	}

}
