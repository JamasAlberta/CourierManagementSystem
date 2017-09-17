package cms.model;

import cms.model.interfaces.MaintenanceInfo;

public abstract class LandVehicle extends AbstractVehicle
{

	private double odometer = 0.0;
	private MaintenanceInfo landMInfo;

	public LandVehicle(String regNo, String make, String model, int year,
			double serviceInterval)
	{
		super(regNo, make, model, year);

		landMInfo = new LandMaintenanceInfo(this, serviceInterval);
	}

	/**
	 * determines whether a vehicle can travel a specified distance without
	 * exceeding its next service point
	 */
	@Override
	public boolean canTravel(double distance)
	{
		return !landMInfo.exceedsServicePoint(distance);
	}

	@Override
	public void service()
	{
		landMInfo.service();
	}

	@Override
	public String toString()
	{
		return super.toString()
				+ String.format(", Current_Odometer:%skm, %s", odometer,
						landMInfo);
	}

	/**
	 * Increments the odometer reading by the specified distance hence
	 * simulating the vehicle traveling.
	 */
	public double travel(double distance)
	{
		double wearAndTear = calculateWearAndTear(distance);
		odometer += distance;
		return wearAndTear;
	}
}
