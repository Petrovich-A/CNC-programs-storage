package by.petrovich.storage;

import by.petrovich.storage.entity.EmployeePosition;

public class main {

	public static void main(String[] args) {
	int ordinalNum = EmployeePosition.ENGINEERING_TECHNICIAN.ordinal();
	int ordinalNum1 = EmployeePosition.CNC_PROGRAMMER.ordinal()+1;
	ordinalNum++;
	ordinalNum1++;
			System.out.println("ordinalNum: " + ordinalNum + " ordinalNum1: " + ordinalNum1);

	}

}
