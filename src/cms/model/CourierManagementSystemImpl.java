package cms.model;

import java.util.ArrayList;
import java.util.List;

import cms.model.interfaces.CourierManagementSystem;
import cms.model.interfaces.Vehicle;

public class CourierManagementSystemImpl implements CourierManagementSystem
{
	private List<Job> jobs = new ArrayList<Job>();
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	@Override
	public boolean addVehicle(Vehicle v)
	{
		return vehicles.add(v);
	}

	@Override
	public boolean removeVehicle(String vehicleRegNo)
	{
		Vehicle v = getVehicle(vehicleRegNo);
		return vehicles.remove(v);
	}

	@Override
	public void displayAllJobs()
	{
		for (Job j : jobs)
		{
			System.out.println(j);
		}
	}

	@Override
	public void displayAllVehicles()
	{
		for (Vehicle v : vehicles)
		{
			System.out.println(v);
		}
	}

	@Override
	public void displayJobInfo(String jobID)
	{
		Job j = getJob(jobID);
		if (j != null)
			System.out.println(j);
	}

	@Override
	public void displayVehicleInfo(String regNo)
	{
		Vehicle v = getVehicle(regNo);
		if (v != null)
			System.out.println(v);
	}

	@Override
	public Job getJob(String jobID)
	{
		for (Job j : jobs)
		{
			if (j.getJobID().equals(jobID))
				return j;
		}
		return null;
	}

	/**
	 * Service the vehicle corresponding to the specified registrationNo
	 * 
	 * @param regNo
	 */
	@Override
	public void serviceVehicle(String regNo)
	{
		Vehicle toService = getVehicle(regNo);
		if (toService != null)
			toService.service();
	}

	@Override
	public Vehicle getVehicle(String regNo)
	{
		for (Vehicle v : vehicles)
		{
			if (v.getRegNo().equals(regNo))
				return v;
		}
		return null;
	}

	/**
	 * Creates a job and schedules it to the specified vehicle if traveling the
	 * required distance does not violate the vehicle's maintenance requirements
	 */
	@Override
	public boolean scheduleJob(double distance, String regNo)
	{
		Vehicle vehicleToAssign = getVehicle(regNo);
		if (vehicleToAssign == null || !vehicleToAssign.canTravel(distance))
			return false;

		String jobID = "job" + jobs.size();
		jobs.add(new Job(jobID, distance, vehicleToAssign, vehicleToAssign
				.calculateWearAndTear(distance)));
		vehicleToAssign.travel(distance);
		return true;
	}
}