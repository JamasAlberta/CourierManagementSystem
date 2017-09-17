package cms.model;

import cms.model.interfaces.MaintenanceInfo;

public class LandMaintenanceInfo implements MaintenanceInfo
{
	private double lastServicePoint = 0.0;

	private final double SERVICE_INTERVAL;

	private LandVehicle landVehicle;

	public LandMaintenanceInfo(LandVehicle vehicle, double serviceInterval)
	{
		SERVICE_INTERVAL = serviceInterval;
		this.landVehicle = vehicle;
	}

	/**
	 * @return the odometer reading at which the vehicle was serviced last
	 */
	public double getLastServicePoint()
	{
		return lastServicePoint;
	}

	/**
	 * @return the odometer reading at which the vehicle should be serviced next
	 */
	public double getNextServicePoint()
	{
		return lastServicePoint + SERVICE_INTERVAL;
	}

	public double getServiceInterval()
	{
		return SERVICE_INTERVAL;
	}

	public LandVehicle getVehicle()
	{
		return landVehicle;
	}

	@Override
	public void service()
	{
		lastServicePoint = landVehicle.getOdometer();
	}

	@Override
	public String toString()
	{
		return String.format("Last_Service_Point: %s, Service_Interval: %s",
				lastServicePoint, SERVICE_INTERVAL);
	}

	@Override
	public boolean exceedsServicePoint(double distToTravel)
	{
		return (landVehicle.getOdometer() + distToTravel) > getNextServicePoint();
	}
}