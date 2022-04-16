/***
 * Class to define to Heap
 * @author Gabrielle Dias
 * @version 0.1
 * Date of creation: April 15, 2022
 * Last Date Modified: April 15, 2022
 */

import java.util.ArrayList;

/***
 * check if input Heap is a valid input
 * @author gabrielledias
 *
 * @param <E> for the E you are searching for
 */
public class Heap<E extends Comparable<E>> { // O(n)
	private ArrayList<E> list;
	public Heap(){
		list = new ArrayList<>();
	}
	public int size(){
		return list.size();
	}
	public boolean isEmpty(){
		return list.isEmpty();
	}
	public void clear(){
		list.clear();
	}
	public String toString(){
		return list.toString();
	}

	public int contains(E value) {
		int iterations = 0;
		for(int i=0; i<list.size(); i++) {
			iterations++;
			if(list.get(i).equals(value))
				return iterations;
		}
		return iterations;
	}

	/***
	 * check if input add is a valid input
	 * @param value for the value you are searching for
	 * @return whether the add E value input is valid or no
	 */
	public int add(E value) { 
		int iterations = 0;
		list.add(value); //append value to the heap
		int currentIndex = list.size()-1;
		//index of the last element
		while(currentIndex > 0) {
			iterations++;
			int parentIndex = (currentIndex-1)/2;
			//swap if current is greater than its parent
			E current = list.get(currentIndex);
			E parent = list.get(parentIndex);
			if(current.compareTo(parent) > 0) {
				list.set(currentIndex, parent);
				list.set(parentIndex, current);
			}
			else
				break; // the tree is a heap
			currentIndex = parentIndex;
		}
		return iterations;
	}

	/***
	 * check if input remove is a valid input
	 * @return whether the remove input is valid or no
	 */
	public int remove() { 
		int iterations = 0;
		if(list.size() == 0) 
			return iterations;
		//copy the value of the last node to root
		E removedItem = list.get(0);
		list.set(0, list.get(list.size()-1));
		//remove the last node from the heap
		list.remove(list.size()-1);
		int currentIndex = 0;

		while (currentIndex < list.size()) {
			iterations++;
			int left = 2 * currentIndex + 1;
			int right = 2 * currentIndex + 2;
			//find the maximum of the left and right nodes
			if (left >= list.size())
				break; // no left child
			int maxIndex = left;
			E max = list.get(maxIndex);
			if (right < list.size()) // right child exists
				if(max.compareTo(list.get(right)) < 0)
					maxIndex = right;

			// swap if current is less than max
			E current = list.get(currentIndex);
			max = list.get(maxIndex);
			if(current.compareTo(max) < 0){
				list.set(maxIndex, current);
				list.set(currentIndex, max);
				currentIndex = maxIndex;
			}
			else
				break; // the tree is a heap
		}
		return iterations;
	}
	
	public int height() {
		return height(0);
		
	}
	
	/***
	 * check if input height is a valid input
	 * @param node for the node you are searching for
	 * @return whether the height input is valid or no
	 */
	// Recursive Helper Method
	public int height(int node) { // O(n)
		if(node >= size())
			return 0;
		else {
			int hLeft = height(2 * node + 1);
			int hRight = height(2 * node + 2);
			return 1 + Math.max(hLeft, hRight);
		}	
	}
	
	public boolean isBalanced() {
		return isBalanced(0);
	}
	
	/***
	 * check if input is balanced is a valid input
	 * @param node for the node you are searching for
	 * @return whether the is balanced input is valid or no
	 */
	// Recursive Helper Method
	public boolean isBalanced(int node) { // O(n)
		if(node >= size())
			return true;
		else {
			int hLeft = height(2 * node + 1);
			int hRight = height(2 * node + 2);
			if(Math.abs(hLeft - hRight) > 1) {
				return false;
			}
			return isBalanced(2 * node + 1) && isBalanced(2 * node + 2);
		}
}

}
