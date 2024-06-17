// --== CS400 File Headearr Information ==--
// Name: <Liangqi Cai>
// Email: <lcai42@wisc.edu>
// Team: <JB>
// TA: <Harper>
// Lecturer: <Florian>
// Notes to Grader: <optional extra notes>



import java.io.IOException;
import java.util.*;
import java.util.Scanner;


public class launch {
	private static RoadSystem system;
	private static Scanner input;

	public static void main(String args[]) throws Exception{
		
	    system = new RoadSystem();
		input = new Scanner(System.in);		
		launch();
	       
    }
	
	private static void launch() {
		menu();
		String command = getCommand();
		execute(command);
	}
	
	//This method will show the menu of application
	private static void menu() {
		System.out.println("============================================");
	    System.out.println("         European E-ROAD Map System         ");
	    System.out.println("============================================");
	    System.out.println("|Find roads between two city............[1]|");
	    System.out.println("|Build new roads between two city.......[2]|");
	    System.out.println("|Remove a road between two city.........[3]|");
	    System.out.println("|Check if system contains the city......[4]|");
	    System.out.println("|Clear all the new added routes.........[5]|");
	    System.out.println("|Help...................................[6]|");
	    System.out.println("|Quit...................................[7]|");
	    System.out.println("============================================");
	}
	
	//This method will ask user to type in command 
	private static String getCommand() {
		System.out.println("Enter the command of the function: ");
		String command = input.nextLine();
		return command;		
	}
	
	//This method will operate according to the command that user types in
	private static void execute(String command) {
		if (command.equals("1")) {
			findRoad();
		} else if (command.equals("2")) {
			build();
		} else if (command.equals("3")) {
			remove();
		} else if (command.equals("4")) {
			ifContain();
		} else if (command.equals("5")) {
			clear();
		} else if (command.equals("6") || command.equalsIgnoreCase("Help")) {
			help();
		} else if (command.equals("7") || command.equalsIgnoreCase("Quit")) {
			quit();
		} else {
			String Command = (String) getCommand();
			execute(Command);			
		}
	}
	
	//This method will function as finding path mode
	private static void findRoad() {
		System.out.println("============================================");
		System.out.println("Enter the city of depature: ");
		String Input = input.nextLine();
		Input = name(Input);
		while (!contain(Input)) {
			if (Input.equalsIgnoreCase("Quit") || Input.equalsIgnoreCase("q") || Input.equalsIgnoreCase("[quit]")) {
				launch();
			}
			System.out.println("Your city doesn't exist in system.");
			System.out.println("Please Enter the city again or Enter [Quit] to quit: ");
			Input = input.nextLine();
			Input = name(Input);			
		} 		
		System.out.println("Enter the target city: ");
		String target = input.nextLine();
		target = name(target);
		while (!contain(target)) {
			if ((target.equalsIgnoreCase("Quit") || target.equalsIgnoreCase("q") || target.equalsIgnoreCase("[quit]"))) {
				launch();
			}
			System.out.println("Your city doesn't exist in system.");
			System.out.println("Please Enter the target city again or Enter [Quit] to quit: ");
			target = input.nextLine();
			target = name(target);				
		}
		String path = system.findPath(Input, target);
		int distance = system.distance(Input, target);
		String[] list = path.split(" ");
		String result = "";
		for (int i = 0; i < list.length; i++) {
			String word = list[i];
			if (word.startsWith("E") && Character.isDigit(word.charAt(1))) {
				result = result + "---(" + list[i] +")---> ";
			} else {
				result = result + list[i] + " ";
			}
		}
		System.out.println("============================================");
		System.out.println("The shortest path: ");
		System.out.println(result);
		System.out.println("The distance of the path: " + distance + "KM");
		System.out.println("============================================");
		continueSearch();
	}
	
	//Deal with the input that user type in
	private static String name(String input) {
		if (input.length() == 0) {
			return "";
		}
		String result ="";
		String[] list = input.split(" ");
		result = result + list[0].substring(0, 1).toUpperCase() + list[0].substring(1).toLowerCase();
		for (int i = 1; i < list.length; i++) {
			result = result + " " + list[i].substring(0, 1).toUpperCase() + list[i].substring(1).toLowerCase();
		}		
		return result;
	}
	
	//This method will ask user if he/she wants to continue to search new road
	private static void continueSearch() {
		System.out.println("Do you want to continue? Yes/No");
		String Input = input.nextLine();
		if (Input.equalsIgnoreCase("yes") || Input.equalsIgnoreCase("y")) {
			findRoad();
		} else if (Input.equalsIgnoreCase("No") || Input.equalsIgnoreCase("N")) {
			launch();
		} else {
			continueSearch();
		}
	}
	
	//This method will build new road between two city
	private static void build() {
		System.out.println("============================================");
		System.out.println("Enter the city of depature: ");
		String origin = input.nextLine();
		origin = name(origin);
		while (origin.length() == 0) {
			System.out.println("Enter the city of depature: ");
			origin = input.nextLine();
			origin = name(origin);
		}
		System.out.println("Enter the target city: ");
		String target = input.nextLine();
		target = name(target);
		while (target.length() == 0) {
			System.out.println("Enter the target city: ");
			target = input.nextLine();
			target = name(target);
		}
		int distance = 0;
		System.out.println("Enter the distance of the new road: ");
		while (!input.hasNextInt()) {
			System.out.println("Please Enter a number!");
			System.out.println("Enter the distance of the new road: ");
			input.next();
		}
		distance = input.nextInt();		
		input.nextLine();
		System.out.println("Enter the name of the new road: ");
		String name = input.nextLine();
		while (name.length() == 0) {
			System.out.println("Enter the name of the new road: ");
			name = input.nextLine();
		}
		system.setRoad(origin, target, distance, name);
		System.out.println("============================================");
		System.out.println("Here is a new road!!!!!!!!");
		continueBuild();	
	}
	
	//This method will ask user if he/she wants to continue to build new road
	private static void continueBuild() {
		System.out.println("Do you want to continue? Yes/No");
		String Input = input.nextLine();
		if (Input.equalsIgnoreCase("yes") || Input.equalsIgnoreCase("y")) {
			build();
		} else if (Input.equalsIgnoreCase("No") || Input.equalsIgnoreCase("N")) {
			launch();
		} else {
			continueBuild();
		}
	}
	
	//This method will remove a road from two city
	private static void remove() {
		System.out.println("============================================");
		System.out.println("Enter the city of depature: ");
		String origin = input.nextLine();
		origin = name(origin);
		while (origin.length() == 0) {
			System.out.println("Enter the city of depature: ");
			origin = input.nextLine();
			origin = name(origin);
		}
		System.out.println("Enter the target city: ");
		String target = input.nextLine();
		target = name(target);
		while (target.length() == 0) {
			System.out.println("Enter the target city: ");
			target = input.nextLine();
			target = name(target);
		}
		if (system.removeRoad(origin, target)) {
			System.out.println("============================================");
			System.out.println("The road is removed successful!!!");
		} else {
			System.out.println("============================================");
			System.out.println("Failed to remove the road!!!");
		}
		continueRemove();
		
	}
	
	//This method will ask user if he/she wants to continue to remove road
	private static void continueRemove() {
		System.out.println("Do you want to continue? Yes/No");
		String Input = input.nextLine();
		if (Input.equalsIgnoreCase("yes") || Input.equalsIgnoreCase("y")) {
			remove();
		} else if (Input.equalsIgnoreCase("No") || Input.equalsIgnoreCase("N")) {
			launch();
		} else {
			continueRemove();
		}
	}
	
	//This method will check if the city exist in the system
	private static void ifContain() {
		System.out.println("============================================");
		System.out.println("Enter the city you want to check: ");
		String city = input.nextLine();
		city = name(city);
		while (city.length() == 0) {
			System.out.println("Enter the city you want to check: ");
			city = input.nextLine();
			city = name(city);
		}	
		List<String> list = system.findNameWithPrefix(city.substring(0, 1));
		if (contain(city)) {
			System.out.println("===========================================");
			System.out.println("The city exists in the system!");
		} else {
			System.out.println("===========================================");
			System.out.println("The city doesn't exist in the system!");
			System.out.println("Here is the list of possible city(Type [-] to get more or Type[q] to quit): ");
			Enter(list, list.size());
		}
		continueIfContain();
	}
	
	private static void Enter(List<String> list, int curr) {
		int total = curr;
		if (total >= 10) {
			for (int i = total - 1; i > total -11; i--) {
				System.out.println(list.get(i));
			}
			String Input = input.next();
			if (Input.equalsIgnoreCase("q") || Input.equalsIgnoreCase("quit")) {
				continueIfContain();
			} else {
				Enter(list, total - 10);
			}
			
		} else {
			for (int i = 0; i < total; i++) {
				System.out.println(list.get(i));
			}					
		}	
		
	}
	
	//This method will ask user if he/she wants to continue to check
	//if the city exists in the system
	private static void continueIfContain() {
		input.nextLine();
		System.out.println("Do you want to continue? Yes/No");
		String Input = input.nextLine();
		if (Input.equalsIgnoreCase("yes") || Input.equalsIgnoreCase("y")) {
			ifContain();
		} else if (Input.equalsIgnoreCase("No") || Input.equalsIgnoreCase("N")) {
			launch();
		} else {
			continueIfContain();
		}
	}
	
	//This method will clear all the new added routes
	private static void clear() {
		system.reset();
		System.out.println("============================================");
		System.out.println("The system is reset!!!!!!!!!");
		launch();
	}
	
	//This method will show the information of application
	private static void help() {
		System.out.println("========Description of Application===========");
		System.out.println("This app is for travelers in Europe to find the");
		System.out.println("shortest e-road from origin city to target city.");
		System.out.println("The travelers who use cars as the way of");
		System.out.println("transportation can use this app to find the ");
		System.out.println("optimal e-road to reach the destination.");
		System.out.println("-------------Front End Developer--------------");
		System.out.println("Liangqi Cai and Minghao Zhou");
		launch();
	}	
	
	//This method will check if system contains the city
	private static boolean contain(String city) {
		if (!system.contain(city)) {
			return false;
		}		
		return true;
	}
	
	//This method will quit the application
	private static void quit() {
		System.out.println("============================================");
		System.out.println("Thank you for using European E-ROAD Map System!");
		Runtime.getRuntime().exit(0);
	}

}
