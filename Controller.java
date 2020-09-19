/* Palash Roy DevOps Engineering 2019 */
package newproject;
import java.util.*;
public class Controller {

	Elevator elevator;
	private static Scanner sc;

	Controller() {
		System.out.print("        ***Welcome To Elevator Program***          ");
		System.out.print("\n-------------------------------------------------\n");
		System.out.print("Enter the Floor Number You Wish to Travel [1-10]: ");
		int n = sc.nextInt();
		if(n < 0) {
			System.out.print("Invalid input");
			System.exit(1);
		}
		elevator = new Elevator(n);
		elevator.start();

	}

	public static void main(String args[]) {
		
		sc = new Scanner(System.in);
 		Controller c = new Controller();
		System.out.print("Enter the Number of people : ");
 		int n = sc.nextInt();

		if(n >= 0) {
 			c.testCase(n);
		} else {
 			System.out.println("Request not accepted, Please Enter a Positive Number.");
 			System.exit(1);
		}
 	}
	void callElevator(Person p) {
		
		if(p.direction == "up")
			elevator.ElevatorUpword.addAsc(p);
		else
			elevator.ElevatorDownword.addDesc(p);	
	}

	void updateView() {
		System.out.println(elevator);
	}
			
	

	void testCase( int n ) {
		elevator.controller = this;
				
		Random r = new Random();
		int weight; 
		int source;
		int dest;
		Person p;
		
		for(int i = 0; i < n; i++) {
			weight = r.nextInt(61) + 40;
			source = r.nextInt(Elevator.maxFloor) + 1;
			dest = r.nextInt(Elevator.maxFloor) + 1;
			
			p = new Person(weight, source, dest, this);
			p.start();
		}
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){ e.printStackTrace(); }

		elevator.interrupt();

	}
	
	void testCase2(int n) {
		elevator.controller = this;
		
		int weight; 
		int source;
		int dest;
		Person p;
		System.out.println();
		for(int i = 0; i < n; i++) {
			System.out.print("weight :");
			weight = sc.nextInt();
			System.out.print("Getting in at floor :");
			source = sc.nextInt();
			System.out.print("Getting out at floor :");
			dest = sc.nextInt();
			
			p = new Person(weight, source, dest, this);
			p.start();
		}
							
		try{
			Thread.sleep(2000);
			
		}catch(Exception e){ e.printStackTrace(); }

		elevator.interrupt();

	}
}