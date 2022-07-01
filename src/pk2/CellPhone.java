package pk2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author William Zicha 40127016
 * @version Assignment 4 String
 */
public class CellPhone {

	protected long serialNumber;
	protected String brand;
	protected double price;
	protected int year;
	public static ArrayList<Long> serials = new ArrayList<>();

	/**
	 * 
	 * @return Unique 7-digit serial number.
	 */
	private Long generateSerial() {
		Long unique = (long) (Math.random() * 10000000);
		if (serials.contains(unique))
			unique = generateSerial();
		System.out.println("Warning: Serial number collision, replaced by: " + unique + ".");
		return unique;
	}

	/**
	 * 
	 * @param serialNumber
	 * @param brand
	 * @param year
	 * @param price
	 */
	public CellPhone(long serialNumber, String brand, double price, int year) {
		if (serials.contains(serialNumber)) {
			this.serialNumber = generateSerial();
			System.out.println(
					"Warning: Serial number collision: " + serialNumber + " replaced by: " + this.serialNumber + ".");
		} else {
			this.serialNumber = serialNumber;
		}
		this.brand = brand;
		this.year = year;
		this.price = price;
		serials.add(this.serialNumber);
	}

	/**
	 * 
	 * @param toCopy
	 * @param newSerial
	 */
	public CellPhone(CellPhone toCopy, long serialNumber) {
		if (serials.contains(serialNumber)) {
			this.serialNumber = generateSerial();
			System.out.println(
					"Warning: Serial number collision: " + serialNumber + " replaced by: " + this.serialNumber + ".");
		} else {
			this.serialNumber = serialNumber;
		}
		brand = toCopy.brand;
		year = toCopy.year;
		price = toCopy.price;
	}

	public CellPhone cloneManual() {
		Scanner key = new Scanner(System.in);
		System.out.print("Enter unique Serial Number (7-digits): ");
		long newSerial = key.nextLong();
		key.nextLine();
		return new CellPhone(this, newSerial);
	}

	public CellPhone clone() {
		return new CellPhone(this, generateSerial());
	}

	/**
	 * @return the serialNumber
	 */
	public long getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber
	 *            the serialNumber to set
	 */
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 */

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CellPhone other = (CellPhone) obj;
		return brand == other.brand && Double.compare(price, other.price) == 0 && year == other.year;
	}

	@Override
	public String toString() {
		return String.format("[%-8d: %-12s %#.2f$ %d]", serialNumber, brand, price, year);																																												
	}

	// test 
	public static void main(String[] args) {
		testToString();
	}

	public static void testToString() {
		CellPhone test1 = new CellPhone(1234567L, "Nokia", 99., 2001);
		System.out.println(test1);
		CellPhone test2 = new CellPhone(1234567L, "Nokia", 99.99, 2001);
		System.out.println(test2);
		CellPhone test3 = new CellPhone(1234567L, "Nokia", 99.9911, 2001);
		System.out.println(test3);
		CellPhone test4 = null;
		System.out.println(test4);
	}
}
