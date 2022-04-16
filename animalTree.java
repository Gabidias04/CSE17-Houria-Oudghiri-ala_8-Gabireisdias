/***
 * Class to define to Animal Tree
 * @author Gabrielle Dias
 * @version 0.1
 * Date of creation: April 15, 2022
 * Last Date Modified: April 15, 2022
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/***
 * check if input Animal Tree is a valid input
 * @author gabrielledias
 *
 */
public class animalTree {

	public static void main(String[] args) {
		BST<String> animalBST = new BST<>(); 
		Heap<String> animalHeap = new Heap<>();
		ArrayList<String> animalAL = new ArrayList<>();
		readFile(animalAL, "animals.txt");
		testAdd(animalBST, animalHeap, animalAL);
		testContains(animalBST, animalHeap, animalAL);
		testRemove(animalBST, animalHeap, animalAL); 
		System.out.println("\n\nBefore sorting animalAL");
		System.out.println("BST height: " + animalBST.height());
		System.out.println("heap height: " + animalHeap.height());
		
		System.out.println("BST isBalanced? " + animalBST.isBalanced());
		System.out.println("Heap isBalanced? " + animalHeap.isBalanced());
		animalBST.clear();
		animalHeap.clear();
		java.util.Collections.sort(animalAL);
		for(int i=0; i<animalAL.size(); i++) {
			String name = animalAL.get(i);
			animalBST.add(name);
			animalHeap.add(name);
			
		}
		System.out.println("\n\nAfter sorting animalAL");
		System.out.println("BST height: " + animalBST.height());
		System.out.println("heap height: " + animalHeap.height());
		
		System.out.println("BST isBalanced? " + animalBST.isBalanced());
		System.out.println("Heap isBalanced? " + animalHeap.isBalanced());
		
		
	}
	
	/***
	 * check if input Read File is a valid input
	 * @param al for the al you are searching for
	 * @param filename for the filename you are searching for
	 */
	public static void readFile(ArrayList<String> al, String filename) {
		File file = new File(filename);
		try {
			Scanner read = new Scanner(file);
			while(read.hasNextLine()) {
				al.add(read.nextLine());
			}
			read.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
	}
	
	/***
	 * check if input Test Add is a valid input
	 * @param bst for the bst you are searching for
	 * @param heap for the heap you are searching for
	 * @param al for the al you are searching for
	 */
	public static void testAdd(BST<String> bst, Heap<String> heap, ArrayList<String> al) {
		int totalBST = 0, totalHeap = 0;
		int count = 0;
		System.out.println("Testing Add()");
		System.out.printf("%-30s\t%-10s\t%-10s\n", "Animal name", "BST Iterations", "Heap Iterations");
		for(int i=0; i<al.size(); i++) {
			String name = al.get(i);
			int bstIter = bst.add(name);
			int heapIter = heap.add(name);
			if(i % 24 == 0) {
				count++;
			System.out.printf("%-30s\t%-10d\t%-10d\n", name, bstIter, heapIter);
				
			totalBST += bstIter;
			totalHeap += heapIter;
			}
		}
		System.out.printf("%-30s\t%-10d\t%-10d\n", "average", totalBST/count, totalHeap/count);
	}
	
	/***
	 * check if input Test Contains is a valid input
	 * @param bst for the bst you are searching for
	 * @param heap for the heap you are searching for
	 * @param al for the al you are searching for
	 */
	public static void testContains(BST<String> bst, Heap<String> heap, ArrayList<String> al) {
		int totalBST = 0, totalHeap = 0;
		System.out.println("Testing Contains()");
		System.out.printf("%-30s\t%-10s\t%-10s\n", "Animal name", "BST Iterations", "Heap Iterations");
		for(int i=0; i<20; i++) {
			int index = (int) (Math.random() * al.size());
			String name = al.get(index);
			int bstIter = bst.contains(name);
			int heapIter = heap.contains(name);
			System.out.printf("%-30s\t%-10d\t%-10d\n", name, bstIter, heapIter);
				
			totalBST += bstIter;
			totalHeap += heapIter;
		}
		System.out.printf("%-30s\t%-10d\t%-10d\n", "average", totalBST/20, totalHeap/20);
	}
	
	/***
	 * check if input Test Remove is a valid input
	 * @param bst for the bst you are searching for
	 * @param heap for the heap you are searching for
	 * @param al for the al you are searching for
	 */
	public static void testRemove(BST<String> bst, Heap<String> heap, ArrayList<String> al) {
		int totalBST = 0, totalHeap = 0;
		System.out.println("Testing Remove()");
		System.out.printf("%-30s\t%-10s\t%-10s\n", "Animal name", "BST Iterations", "Heap Iterations");
		for(int i=0; i<20; i++) {
			int index = (int) (Math.random() * al.size());
			String name = al.get(index);
			int bstIter = bst.remove(name);
			int heapIter = heap.remove();
			System.out.printf("%-30s\t%-10d\t%-10d\n", name, bstIter, heapIter);
				
			totalBST += bstIter;
			totalHeap += heapIter;
		}
		System.out.printf("%-30s\t%-10d\t%-10d\n", "average", totalBST/20, totalHeap/20);
	}
}
