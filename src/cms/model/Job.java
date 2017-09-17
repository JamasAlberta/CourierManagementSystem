package cms.model;

import cms.model.interfaces.Vehicle;

public class Job
{
	private double cost;
	private double distance;
	private String jobID;
	private Vehicle vehicle;

	public Job(String jobID, double distance, Vehicle vehicle, double cost)
	{
		this.jobID = jobID;
		this.distance = distance;
		this.vehicle = vehicle;
		this.cost = cost;
	}

	/**
	 * @return Wear and Tear of the Vehicle performing this Job
	 */
	public double getCost()
	{
		return cost;
	}

	/**
	 * @return Vehicle assigned to this Job
	 */
	public Vehicle getVehicle()
	{
		return vehicle;
	}

	public String getJobID()
	{
		return jobID;
	}

	@Override
	public String toString()
	{
		return String.format(
				"JobID: %s , Distance: %s, Cost: %s, (Vehicle : %s)", jobID,
				distance, cost, vehicle.getRegNo());
	}
}