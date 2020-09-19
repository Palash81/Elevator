/* Palash Roy DevOps Engineering 2019 */
package newproject;
public class Person extends Thread implements Comparable<Person> {

	int inFloor;
	int outFloor;
	String direction;
	Controller controller;

	Person(int w, int in, int out, Controller c) {
		inFloor = in;
		outFloor = out;
		controller = c;
		

		if(inFloor == outFloor) {
			System.out.println("Source floor and destination floor cannot be the same.");
			System.exit(1);
		}
		
		
		direction = (inFloor < outFloor) ? "up" : "down";
	}

	public void run() {
		try{
			synchronized(controller) {
				controller.callElevator(this);	
				
			}	

			synchronized(this) {
				wait();	
			}	

			System.out.println("End of thread : " + this);

		} catch(InterruptedException ie) {
				ie.printStackTrace();
		}
	}

	void wakeUp( ) {
		try {
			System.out.println("Thread : " + this + "  ::::::ELEVATOR IS WALKING UP ::::::");
			synchronized(this) {
				notify();	
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public int compareTo( Person p ) {
		if( this.inFloor < p.inFloor ) {
			return -1;
		}
		else if( this.inFloor > p.inFloor ) {
			return 1;
		} else {
			return 0;
		}

	}


	public String toString() {
		return "TravelerID: " + getId(); 
		}
}