/* Palash Roy DevOps Engineering 2019 */
package newproject;
public class LinkedList<T extends Comparable<T>> {
	
	Node<T> head;
	
	LinkedList() {
		head = null;
	}

	void addAsc(T obj){
		if(head == null)
			head = new Node<T>(obj,head);
		else {
			Node<T> newNode = new Node<>(obj,null);
			Node<T> current = head;
			Node<T> previous = head;
			while(current != null) {

				if( current.data.compareTo(newNode.data) >= 0 ) {	
					if( current != previous ) {
						newNode.next = current;			
						previous.next = newNode;
						break;	
					} else { 		
						newNode.next = current;			
						head = newNode;
						break;
					}

					
				}
				previous = current;
				current = current.next;
			}	

			if(current == null) {
				previous.next = newNode;
			}

		}
	}

	void addDesc(T obj){
		if(head == null)
			head = new Node<T>(obj,head);
		else {
			Node<T> newNode = new Node<>(obj,null);
			Node<T> current = head;
			Node<T> previous = head;
			while(current != null) {

				if( current.data.compareTo(newNode.data) <= 0 ) {	
					if( current != previous ) {
						newNode.next = current;			
						previous.next = newNode;
						break;	
					} else { 		
						newNode.next = current;			
						head = newNode;
						break;
					}

					
				}
				
				previous = current;
				current = current.next;
			}	

			if(current == null) {
				previous.next = newNode;
			}

		}
	}

	void remove(T obj) {
		Node<T> current = head;
		Node<T> previous = head;
		while(current != null) {

			if( current.data == obj) {				
				if( current != previous ) {
					previous.next = current.next;		
					break;
				} else { 							
					head = head.next;							
					break;
				}

				
			}
			
			previous = current;
			current = current.next;
		}	



	}

	public String toString() {
		Node<T> current = head;
		String temp = "\n[";
	
		while(current != null) {
			temp = temp + current.data + " ";
			current = current.next;
		}

		temp = temp + "] \n";

		return temp;
	}

}

class Node<E extends Comparable<E>> {
	E data;
	Node<E> next;

	Node( E data, Node<E> next ) {
		this.data = data;
		this.next = next;
	}
}