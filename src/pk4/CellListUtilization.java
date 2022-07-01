package pk4;

/**
 * 
 * @author William Zicha 40127016
 * @version Assignment 4
 *String
 */

import pk2.CellPhone;
import pk3.CellList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CellListUtilization {
	private static File inputDirectory = new File(System.getProperty("user.dir") + "/sourceFiles");
	private static Scanner fr[] = null;
	private static Scanner key = new Scanner(System.in);
	private static File[] sourceFiles;

	public static void main(String[] args) {
		System.out.println("\t\t\tCELL LIST TESTER");
		System.out.println("=======================");

		CellList list1 = new CellList();
		CellList list2 = new CellList();

		sourceFiles = new File[1];
		sourceFiles[0] = new File(inputDirectory + "/Cell_Info.txt");

		System.out.println("\nCreating new CellList from data contained in " + sourceFiles[0].getName());
		stall();
		linkSourceFiles();

		list1.fileToCellList(fr[0]);

		list1.showContents();

		System.out.println("Would you like to test Exceptions?" + "\n1.Yes" + "\n2.No");
		String testE = key.next();
		key.nextLine();
		if (testE.equalsIgnoreCase("1") | testE.equalsIgnoreCase("yes")) {
			
		}

		System.out.println("Deleting first element deleteFromStart()");
		stall();
		list1.deleteFromStart();
		list1.showContents();

		System.out.println("Searching serial Number: 1117333403");
		stall();
		if (list1.find(1117333403) == null) {
			System.out.println("Serial number not found, took " + list1.getIteration() + " iterations.");
		} else {
			System.out.println("Serial number found after " + list1.getIteration() + " iterations.");
		}

		System.out.println();
		System.out.println("Creating new phone with parameters: 1234567,\"IPHONY\",199.9927,2023");

		CellPhone new1 = new CellPhone(1234567, "IPHONY", 199.9927, 2023);

		System.out.println("\nAdding phone to list1, second index.");
		stall();
		list1.insertAtIndex(new1, 1);
		list1.showContents();

		System.out.println("\nDeleting and inserting it at end of list:");
		stall();
		list1.deleteFromIndex(1);
		list1.insertAtIndex(new1, list1.getSize());
		list1.showContents();

		System.out.println("\nDeleting and inserting at head of list with insertAtIndex() method:");
		stall();
		list1.deleteFromIndex(list1.getSize() - 1);
		list1.insertAtIndex(new1, 0);
		list1.showContents();

		System.out.println("\nDeleting from head of list with deleteFromStart() method:");
		stall();
		list1.deleteFromStart();
		list1.showContents();

		System.out.println("\nAddto 5th index of empty list:");
		stall();
		list2.insertAtIndex(new1, 0);
		list2.addToStart(new1.clone());
		list2.showContents();

		System.out.println("Test: clone() a CellPhone and add to list.");
		System.out.println("CellPhone: " + new1);
		stall();
		CellPhone new1Clone = new1.clone();
		System.out.print("Enter index to insert clone (0 <= index <= " + list1.getSize() + "):");
		list1.insertAtIndex(new1Clone, key.nextInt());
		list1.showContents();
	}

	public static void displayAvailableFiles(File directory) {
		String[] availableFiles = directory.list((optionalInnerDirectory, file) -> {
			return file.toLowerCase().endsWith("txt");
		});
		System.out.println("Available Source File(s):");
		for (String fileName : availableFiles) {
			System.out.println(fileName);
		}
		System.out.println();
	}

	public static void linkSourceFiles() {
		fr = new Scanner[sourceFiles.length];
		int i = 0;
		try {
			for (; i < fr.length; i++) {
				fr[i] = new Scanner(sourceFiles[i]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File " + sourceFiles[i].getName() + " not found." + "\nEnsure file present in: "
					+ inputDirectory.getAbsolutePath() + "Program closing..");
			closeStreamsAndExit();
		}
	}

	public static void closeStreamsAndExit() {
		for (Scanner scanner : fr) {
			scanner.close();
		}
		key.close();
		System.exit(0);
	}

	public static void stall() {
		System.out.println("Press ENTER to continue..");
		key.nextLine();
	}

	public static void runException(CellList list) {
		System.out.println("");
	}
}
