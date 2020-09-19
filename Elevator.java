/* Palash Roy DevOps Engineering 2019 */
package newproject;
public class Elevator extends Thread {
	String name;
	final static int maxWeight = 300;
	final static int minWeight = 40;
	final static int minFloor = 1;
	static int maxFloor = 10;
	static int currentFloor;
	String direction;
	String door;
	Controller controller = null;

	LinkedList<Person> ElevatorUpword;
	LinkedList<Person> ElevatorDownword;
	LinkedList<Person> inElevator;

	Elevator(int n) {
		maxFloor = n;
		currentFloor = minFloor;
		direction = "up";
		door = "closed";

		ElevatorUpword = new LinkedList<Person>();
		ElevatorDownword = new LinkedList<Person>();
		inElevator = new LinkedList<Person>();

	}

	public void run() {
		while(true) {


			if(isInterrupted()) {
				while( ElevatorUpword.head != null || ElevatorDownword.head != null || inElevator.head != null ) {
					if( direction == "up" && currentFloor <= maxFloor) {
						servePeopleGoingUp();
						direction = "down";
					}
					else if( direction == "down" && currentFloor >= minFloor) {
						servePeopleGoingDown();
						direction = "up";
					}
				}
				
				break;	
			}
			 
			
		}
	}

	void servePeopleGoingUp() {
		Node<Person> currentPersonInUpList = ElevatorUpword.head;
		Node<Person> currentPersonInElevator = inElevator.head;
		LinkedList<Person> tempList = new LinkedList<Person>();
		boolean isDoorOpen = false;

		while( currentPersonInUpList != null || currentPersonInElevator != null ) {	
			System.out.println("\nPling .................at floor : " + currentFloor + "");
			
			if(currentPersonInElevator != null) {		
				currentPersonInElevator = inElevator.head;
				while(currentPersonInElevator != null) {
					if( currentPersonInElevator.data.outFloor == currentFloor ) {		
						
						if(!isDoorOpen) {
							openDoor();
							isDoorOpen = false;
						}
					
						inElevator.remove(currentPersonInElevator.data);
						System.out.println("Person : " + currentPersonInElevator.data + "  ::::::TRAVELER LEAVING ELEVATOR ::::::");
						currentPersonInElevator.data.wakeUp();
					
					}
					currentPersonInElevator = currentPersonInElevator.next;					
				}
				 
			}

			if(currentPersonInUpList != null) {			
				if( currentPersonInUpList.data.inFloor == currentFloor ) {
					openDoor();
					while( currentPersonInUpList.data.inFloor == currentFloor ) {
						System.out.println("Taking in : " + currentPersonInUpList.data + " at floor : " + currentFloor );
						inElevator.addAsc(currentPersonInUpList.data);
						if( overweight() ) {
							inElevator.remove(currentPersonInUpList.data);
							System.out.println(currentPersonInUpList.data + " you should go out : Elevator overweight!!");
							ElevatorUpword.remove(currentPersonInUpList.data);
							tempList.addAsc(currentPersonInUpList.data);
							
						} else {
							ElevatorUpword.remove(currentPersonInUpList.data);
						}
							
						currentPersonInUpList = currentPersonInUpList.next;
						if(currentPersonInUpList == null)
							break;				
					}
					
					closeDoor();
					
				}
			} else {
				closeDoor();
			}

			System.out.println("ElevatorUpword : " + ElevatorUpword);
			System.out.println("inElevator : " + inElevator);
			System.out.println("ElevatorDownword : " + ElevatorDownword);

			currentPersonInUpList = ElevatorUpword.head;
			currentPersonInElevator = inElevator.head;
			

			if( currentPersonInUpList != null || currentPersonInElevator != null )
				currentFloor = currentFloor + 1;
		}
		
		Node<Person> currentPersonInTempList = tempList.head;
		while(currentPersonInTempList != null) {
			ElevatorUpword.addAsc(currentPersonInTempList.data);
			currentPersonInTempList = currentPersonInTempList.next;
		}			
		
		if(ElevatorDownword.head != null) {
			int downFloorStart = ElevatorDownword.head.data.inFloor;
			if(downFloorStart > currentFloor) {
				while(downFloorStart != currentFloor) {
					currentFloor = currentFloor + 1;
					System.out.println("\nPling .................at floor : " + currentFloor + "");
					System.out.println("ElevatorUpword : " + ElevatorUpword);
					System.out.println("inElevator : " + inElevator);
					System.out.println("Downword : " + ElevatorDownword);
				}				
					
			}			
		}
		 
		
	}

	void servePeopleGoingDown() {
		
		Node<Person> currentPersonInDownList = ElevatorDownword.head;
		Node<Person> currentPersonInElevator = inElevator.head;
		LinkedList<Person> tempList = new LinkedList<Person>();
		boolean isDoorOpen = false;

		while( currentPersonInDownList != null || currentPersonInElevator != null ) {	

			System.out.println("\nPling .................at floor : " + currentFloor + "");
			
			if(currentPersonInElevator != null) {			
				currentPersonInElevator = inElevator.head;
				while(currentPersonInElevator != null) {
					if( currentPersonInElevator.data.outFloor == currentFloor ) {		
						
						if(!isDoorOpen) {
							openDoor();
							isDoorOpen = false;
						}
					
						inElevator.remove(currentPersonInElevator.data);
						System.out.println("Person : " + currentPersonInElevator.data + "  ::::::TRAVELER LEAVING ELEVATOR ::::::");
						currentPersonInElevator.data.wakeUp();
					
					}
					currentPersonInElevator = currentPersonInElevator.next;					
				}				
			}

			if(currentPersonInDownList != null) {			
				if( currentPersonInDownList.data.inFloor == currentFloor ) {
					openDoor();
					while( currentPersonInDownList.data.inFloor == currentFloor ) {
						System.out.println("Taking in : " + currentPersonInDownList.data + " at floor : " + currentFloor );
						inElevator.addDesc(currentPersonInDownList.data);
						if( overweight() ) {
							inElevator.remove(currentPersonInDownList.data);
							System.out.println(currentPersonInDownList.data + " you should go out : Elevator overweight!!");
							ElevatorDownword.remove(currentPersonInDownList.data);
							tempList.addDesc(currentPersonInDownList.data);
							
						} else {
							ElevatorDownword.remove(currentPersonInDownList.data);
						}
							
						currentPersonInDownList = currentPersonInDownList.next;
						if(currentPersonInDownList == null)
							break;				
					}
					
					closeDoor();
					
				}
			} else {
				closeDoor();
			}

			System.out.println("ElevatorUpword : " + ElevatorUpword);
			System.out.println("inElevator : " + inElevator);
			System.out.println("ElevatorDownword : " + ElevatorDownword);
		
			
			currentPersonInDownList = ElevatorDownword.head;
			currentPersonInElevator = inElevator.head;
			

			if( currentPersonInDownList != null || currentPersonInElevator != null )
				currentFloor = currentFloor - 1;
		}
		
		Node<Person> currentPersonInTempList = tempList.head;
		while(currentPersonInTempList != null) {
			ElevatorDownword.addDesc(currentPersonInTempList.data);
			currentPersonInTempList = currentPersonInTempList.next;
		}			
		
		if(ElevatorUpword.head != null) {
			int upFloorStart = ElevatorUpword.head.data.inFloor;
			if(upFloorStart < currentFloor) {
				while(upFloorStart != currentFloor) {
					currentFloor = currentFloor - 1;
					System.out.println("\nPling .................at floor : " + currentFloor + "");
					System.out.println("ElevatorUpword : " + ElevatorUpword);
					System.out.println("inElevator : " + inElevator);
					System.out.println("ElevatorDownword : " + ElevatorDownword);
				}				
					
			}			
		}
		
	}

	boolean overweight() {
		boolean goesOver = false;
		Node<Person> currentPersonInElevator = inElevator.head;
		int totalWeight = 0;
		while( currentPersonInElevator != null ) {
		currentPersonInElevator = currentPersonInElevator.next;
		}

		if(totalWeight > maxWeight)
			goesOver = true;

		return goesOver;
	}

	void openDoor() {
		door = "open";
		System.out.println("Elevator Door Opened");
	}

	void closeDoor() {
		door = "closed";
		System.out.println("Elevator Door Closed");
	}

	public String toString() {
		return "[ Floor : " + currentFloor + ", Door : " + door + ", going " + direction + " ]";
	}




}