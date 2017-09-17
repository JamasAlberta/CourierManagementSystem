package cms.app;

import cms.model.AbstractVehicle;
import cms.model.Aircraft;
import cms.model.CourierManagementSystemImpl;
import cms.model.Truck;
import cms.model.Van;
import cms.model.interfaces.CourierManagementSystem;
import cms.model.interfaces.Vehicle;

/**
 * TestHarness which tests the following functionalities
 * 
 * 1. Tests adding, retrieving LandVehicles and Aircrafts
 * 
 * 2. Tests scheduling a Job to a Land Vehicle (Van, Truck)
 * 
 * 3. Tests Scheduling a Job to an Aircraft
 * 
 * 4. Tests Servicing a Vehicle
 * 
 */
public class CMSTestHarness1
{
	private CourierManagementSystem cms;

	private Vehicle[] testVehicles = new Vehicle[]
	{ new Van("v1", "Toyota", "Sienna", 1998, 500.0),
			new Van("v2", "Volkswagen", "Routan S", 2009, 1000.0),
			new Truck("t1", "Peterbilt", "379 ", 1990, 2000.0, 3000),
			new Truck("t2", "Peterbilt", "386", 2005, 2000.0, 3500),
			new Aircraft("a1", "Boeing", "747", 2003, 1000, 6, 30),
			new Aircraft("a2", "Boeing", "707", 1979, 1000, 6, 20),
			new Aircraft("a3", "Lockheed", "L188C Electra", 1957, 1000, 3, 10) };

	public CMSTestHarness1()
	{
		cms = new CourierManagementSystemImpl();
	}

	/**
	 * Tests adding, retrieving and removing LandVehicles and Aircrafts
	 * 
	 * @return true if the test succeeds false otherwise
	 */
	public boolean test1()
	{

		displayTitle("Test1(Add, Get and Remove Vertices)");
		/*
		 * Add all testVehicles
		 */
		for (int i = 0; i < testVehicles.length; i++)
		{
			cms.addVehicle(testVehicles[i]);
			// check whether vehicle has been added
			Vehicle v = cms.getVehicle(testVehicles[i].getRegNo());
			if (!checkEqual(v, testVehicles[i]))
			{
				System.out.println("FAILED to add vehicle "
						+ testVehicles[i].getRegNo());
				return false;
			}
		}

		/*
		 * Remove ALL vehicles
		 */
		for (int i = 0; i < testVehicles.length; i++)
		{
			cms.removeVehicle(testVehicles[i].getRegNo());
			// test whether vehicle has been removed
			if (cms.getVehicle(testVehicles[i].getRegNo()) != null)
			{
				System.out.println("FAILED to remove vehicle "
						+ testVehicles[i].getRegNo());
				return false;
			}
		}
		return true;
	}

	/**
	 * Tests scheduling a Jobs to a Land Vehicle (Van)
	 * 
	 * @return true if the test succeeds false otherwise
	 */
	public boolean test2()
	{
		displayTitle("Test2(Scheduling jobs to a Land Vehicle(Van)");
		cms.addVehicle(testVehicles[1]);// add v2
		boolean correctlySched = true;

		correctlySched &= cms.scheduleJob(700, "v2"); // should accept
		correctlySched &= !cms.scheduleJob(500, "v2"); // should reject
		correctlySched &= cms.scheduleJob(100, "v2"); // should accept
		correctlySched &= !cms.scheduleJob(300, "v2"); // should reject
		if (!correctlySched)
		{
			System.out
					.println("FAILED to accurately schedule OR reject one or more jobs to a Van;");
		}
		cms.removeVehicle("v2");
		return correctlySched;
	}

	/**
	 * Tests Scheduling a Jobs to an Aircraft
	 * 
	 * @return true if the test succeeds false otherwise
	 */
	public boolean test3()
	{
		displayTitle("Test2(Scheduling jobs to an Aircraft");
		cms.addVehicle(testVehicles[6]);// add a3
		boolean correctlySched = true;
		// should schedule
		correctlySched &= cms.scheduleJob(4000, "a3");
		// should schedule
		correctlySched &= cms.scheduleJob(5000, "a3");
		// should reject, exceeds flightHrs
		correctlySched &= !cms.scheduleJob(3000, "a3");

		// should schedule
		correctlySched &= cms.scheduleJob(500, "a3");
		// should reject,exceeds flightCount
		correctlySched &= !cms.scheduleJob(300, "a3");
		if (!correctlySched)
		{
			System.out
					.println("FAILED to accurately schedule  OR reject one or more jobs to an aircraft;");
		}
		cms.removeVehicle("a3");
		return correctlySched;
	}

	/**
	 * Tests Servicing a Vehicle
	 * 
	 * @return true if the test succeeds false otherwise
	 */
	public boolean test4()
	{
		displayTitle("Test4 ( Sevicing Land Vehicle(Van) and Aircraft) ");

		cms.addVehicle(testVehicles[0]);
		boolean correctlySchedVan = true;
		// travel distance
		correctlySchedVan &= cms.scheduleJob(400, "v1");
		// second travel should be rejected before servicing, because it would
		// exceed NSP
		correctlySchedVan &= !cms.scheduleJob(400, "v1");
		cms.serviceVehicle("v1");// service
		// job can be traveled once serviced
		correctlySchedVan &= cms.scheduleJob(400, "v1");

		if (!correctlySchedVan)
		{
			System.out.println("FAILED to correctly Service Van");
			return false;
		}

		boolean correctlySchedAircraft = true;
		cms.addVehicle(testVehicles[5]);
		// travel distance
		correctlySchedAircraft &= cms.scheduleJob(15000.0, "a2");
		// second travel should be rejected before servicing, because it would
		// exceed NSP
		correctlySchedAircraft &= !cms.scheduleJob(15000.0, "a2");
		cms.serviceVehicle("a2");
		// job can be traveled once serviced
		correctlySchedAircraft &= cms.scheduleJob(15000.0, "a2");

		if (!correctlySchedAircraft)
		{
			System.out.println("FAILED to correctly service an Aircraft");
			return false;
		}
		return true;
	}

	public boolean testAll()
	{
		boolean allPass = true;
		if ((allPass &= test1()))
			System.out.println("PASSED");
		if ((allPass &= test2()))
			System.out.println("PASSED");
		if ((allPass &= test3()))
			System.out.println("PASSED");
		if ((allPass &= test4()))
			System.out.println("PASSED");
		return allPass;
	}

	/**
	 * The method checks to see if two Vehicles are equal. It assumes the
	 * {@link AbstractVehicle#equals(Object)} method has been overridden in
	 * AbstractVehicle, which would compare the regNo,make, model and year of
	 * two vehicles when determining equality
	 * 
	 * @param v
	 * @param vehicle
	 */
	private boolean checkEqual(Vehicle given, Vehicle expected)
	{
		if (given == null)
			return expected == null;
		return given.equals(expected);
	}

	/**
	 * @param string
	 */
	private void displayTitle(String title)
	{
		System.out.println(String.format("%010d%s%015d", 0, title, 0).replace(
				"0", "-"));
	}

	public static void main(String... args)
	{
		System.out
				.println(new CMSTestHarness1().testAll() ? "\nRESULT:\n\tALL TESTS PASSED!!"
						: "\nRESULT:\n\tSOME TESTS FAILED!");
	}
}
