package cms.model.interfaces;

import cms.model.Job;

public interface CourierManagementSystem
{
	public abstract boolean addVehicle(Vehicle v);

	public abstract void displayAllJobs();

	public abstract boolean removeVehicle(String vehicleRegNo);

	public abstract void displayAllVehicles();

	public abstract void displayJobInfo(String jobID);

	public abstract void displayVehicleInfo(String regNo);

	public abstract Job getJob(String jobID);

	public abstract void serviceVehicle(String regNo);

	public abstract Vehicle getVehicle(String regNo);

	public abstract boolean scheduleJob(double distance, String regNo);
}