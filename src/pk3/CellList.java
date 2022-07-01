package pk3;

import java.util.NoSuchElementException;
import java.util.Scanner;

import pk2.CellPhone;

/**
 * 
 * @author William Zicha 40127016
 * @version Assignment 4 String
 */
public class CellList {

	private CellNode head;
	private int size;
	private int iteration = 0;					
	private int listIndex = 0;
								

	
	private class CellNode {

		private CellPhone cellPhone;
		private CellNode pointer;

		/**
		 * 
		 */

		private CellNode() {
			cellPhone = null;
			pointer = null;
		}

		/**
		 * 
		 */
		private CellNode(CellPhone cellPhone, CellNode pointer) {
			this.cellPhone = cellPhone;
			this.pointer = pointer;
		}

		/**
		 * 
		 */
		private CellNode(CellNode toCopy) {
			// TODO
		}

		/**
		 * @return the cellPhone
		 */
		public CellPhone getCellPhone() {
			// TODO
			return cellPhone;
		}

		/**
		 * @param cellPhone
		 *            the cellPhone to set
		 */
		public void setCellPhone(CellPhone cellPhone) {
			// TODO
			this.cellPhone = cellPhone;
		}

		/**
		 * @return the pointer
		 */
		public CellNode getPointer() {
			// TODO
			return pointer;
		}

		/**
		 * @param pointer
		 *            the pointer to set
		 */
		public void setPointer(CellNode pointer) {
			// TODO
			this.pointer = pointer;
		}

		private CellNode moveTo(int index) {
			if (index == listIndex) {
				return this;
			}
			listIndex++;
			return pointer.moveTo(index);
		}


		private CellNode search(long serialNumber) {
			iteration++;
			if (head == null)
				return null;
			if (getCellPhone().getSerialNumber() == serialNumber)
				return this;
			if (pointer == null | getIteration() > size)
				return null;
			return pointer.search(serialNumber);
		}

		
		private String iterPrint() {
			iteration = 0;
			String listToString = "";
			CellNode pos = this;
			while (pos != null) {
				if (getIteration() > size)
					return "Error: List contains a loop, toString() failure.";
				if (iteration % 3 == 0)
					listToString += "\n";
				listToString += pos.cellPhone.toString() + " ---> ";
				pos = pos.pointer;
				iteration++;
			}
			listToString += "X";
			return listToString;
		}

		/**
		 * Starting at each head, iterates through links comparing CellPhone equality.
		 * 
		 * @param otherHead
		 *            CellList to compare to.
		 * @return true if each CellNode contains equal CellPhones.
		 */
		private boolean iterEquals(CellNode otherHead) {
			CellNode first = head;
			CellNode other = otherHead;
			while (first != null) {
				if (!(first.cellPhone.equals(other.cellPhone)))
					return false;
				first = first.pointer;
				other = other.pointer;
			}
			return true;
		}

	}

	/**
	 * 
	 */
	public CellList() {
		head = null;
		size = 0;
	}

	/**
	 * 
	 * @param toCopy
	 */
	public CellList(CellList toCopy) {
		// TODO
	}

	/**
	 * 
	 * @param cellPhone
	 */
	public void addToStart(CellPhone cellPhone) {
		if (head == null | size == 0) {
			head = new CellNode(cellPhone, null);
			size++;
		} else {
			head = new CellNode(cellPhone, head);
			size++;
		}
	}

	public void insertAtIndex(CellPhone cellPhone, int index) {
		// TODO
		try {
			if (index < 0 | index > (size))
				throw new NoSuchElementException();
		} catch (NoSuchElementException e) {
			System.out.println("Error: NoSuchElementException \nProgram closing..");
			System.exit(1);
		}
		if (index == 0) {
			addToStart(cellPhone);
			return;
		}
		listIndex = 0;
		CellNode before = head.moveTo(index - 1);
		CellNode current = before.getPointer();
		CellNode insert = new CellNode(cellPhone, current);
		before.setPointer(insert);
		size++;

	}

	public void deleteFromIndex(int index) {
		// TODO
		try {
			if (index < 0 | index > (size - 1))
				throw new NoSuchElementException();
		} catch (NoSuchElementException e) {
			System.out.println("Error: NoSuchElementException \nProgram closing..");
			System.exit(1);
		}
		listIndex = 0;
		CellNode before = head.moveTo(index - 1);
		CellNode toDelete = before.getPointer();
		CellNode after = toDelete.getPointer();
		before.setPointer(after);
		size--;
	}

	public void deleteFromStart() {
		// TODO
		head = head.getPointer();
		size--;
	}

	public void replaceAtIndex(CellPhone cellPhone, int index) {
		// TODO
		listIndex = 0;
		CellNode toReplace = head.moveTo(index);
		toReplace.setCellPhone(cellPhone);
	}

	public CellNode find(long serialNumber) {
		iteration = 0;
		return head.search(serialNumber);
	}

	public boolean contains(long serialNumber) {
		iteration = 0;
		if (!(head == null)) {
			if (head.search(serialNumber) != null) {
				return true;
			}
		}
		return false;
	}

	public void fileToCellList(Scanner fr) {
		
		int line = 0;
		while (fr.hasNextLine()) {
			line++;
			long serial = fr.nextLong();
			String brand = fr.next();
			double price = fr.nextDouble();
			int year = fr.nextInt();
			if (!contains(serial)) {
				CellPhone newPhone = new CellPhone(serial, brand, price, year);
				addToStart(newPhone);
			}
		}
		fr.close();
	}

	public void showContents() {
		System.out.println("The size of the list is " + size + ". These are the contents of the list:"
				+ "\n=================================================================\n" + toString() + "\n");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass()) 
			return false;
		CellList other = (CellList) obj;
		if (this.size != other.size)
			return false;
		return (this.head).iterEquals(other.head);
	}

	@Override
	public String toString() {
		return head.iterPrint();
	}

	public int getIteration() {
		return iteration;
	}

	public int getSize() {
		return size;
	}
}
