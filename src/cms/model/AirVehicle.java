package cms.model;

import cms.model.interfaces.MaintenanceInfo;

public abstract class AirVehicle extends AbstractVehicle
{

	// 1 job == 1 flight
	public static final int FLIGHTS_PER_JOB = 1;

	private int flightCount = 0;
	private double flightHours = 0.0;
	private double averageSpeed = 0;
	private MaintenanceInfo airMInfo;

	public AirVehicle(String regNo, String make, String model, int year,
			double averageSpeed, int siFlightCountInterval,
			double siFlightHoursInterval)
	{

		super(regNo, make, model, year);
		this.setAverageSpeed(averageSpeed);

		airMInfo = new AirMaintenanceInfo(this, siFlightHoursInterval,
				siFlightCountInterval);
	}

	@Override
	public boolean canTravel(double distance)
	{
		return !airMInfo.exceedsServicePoint(distance);
	}

	@Override
	public void service()
	{
		airMInfo.service();
	}

	@Override
	public double travel(double distance)
	{
		flightHours += estimateFlyingHours(distance);
		flightCount += FLIGHTS_PER_JOB;
		return calculateWearAndTear(distance);
	}

	/**
	 * Performs a simple estimation of the time it would take to travel the
	 * specified distance given the current averagespeed of the aircraft
	 * 
	 * @param distance
	 * @return flying hours
	 */
	public double estimateFlyingHours(double distance)
	{
		return distance / averageSpeed;
	}

	public int getFlightCount()
	{
		return flightCount;
	}

	double getFlightHours()
	{
		return flightHours;
	}

	public void setAverageSpeed(double averageSpeed)
	{
		this.averageSpeed = averageSpeed;
	}

	@Override
	public String toString()
	{
		return super.toString()
				+ String.format(
						", Av_Speed: %s, Flight_Count:%s, Flight_Hours:%s, %s",
						averageSpeed, flightCount, flightHours, airMInfo);
	}

	public double getAverageSpeed()
	{
		return averageSpeed;
	}
}
