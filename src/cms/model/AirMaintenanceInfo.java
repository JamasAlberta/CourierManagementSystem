package cms.model;

import cms.model.interfaces.MaintenanceInfo;

public class AirMaintenanceInfo implements MaintenanceInfo
{
	private AirVehicle aircraft;
	private int lastSPFlightCount = 0;
	private double lastSPFlightHours = 0;

	private final int SERVICE_INTERVAL_FLIGHT_COUNT;
	private final double SERVICE_INTERVAL_FLIGHT_HOURS;

	public AirMaintenanceInfo(AirVehicle vehicle, double siFH, int siFC)
	{
		this.SERVICE_INTERVAL_FLIGHT_COUNT = siFC;
		this.SERVICE_INTERVAL_FLIGHT_HOURS = siFH;
		this.aircraft = vehicle;
	}

	public int getLastSPFlightCount()
	{
		return lastSPFlightCount;
	}

	public double getLastSPFlightHours()
	{
		return lastSPFlightHours;
	}

	public int getServiceIntervalFlightCount()
	{
		return SERVICE_INTERVAL_FLIGHT_COUNT;
	}

	public double getServiceIntervalFlightHours()
	{
		return SERVICE_INTERVAL_FLIGHT_HOURS;
	}

	public AirVehicle getVehicle()
	{
		return aircraft;
	}

	/*
	 * Services an Aircraft by setting the lastservicepoint in terms of both
	 * flight count and flight hours to the current reading of these values in
	 * the aircraft
	 * 
	 * @see MaintenanceInfo#service()
	 */
	@Override
	public void service()
	{
		setLastSPFlightCount(aircraft.getFlightCount());
		setLastSPFlightHours(aircraft.getFlightHours());
	}

	public void setLastSPFlightCount(int lastSPFlightCount)
	{
		this.lastSPFlightCount = lastSPFlightCount;
	}

	public void setLastSPFlightHours(double lastSPFlightHours)
	{
		this.lastSPFlightHours = lastSPFlightHours;
	}

	/*
	 * Checks to see if flying the specified distance would violate the
	 * servicing requirements of the vehicle interms of the flying hours and
	 * flight count.
	 * 
	 * @param distToTravel: distance to travel
	 * 
	 * @see MaintenanceInfo#wouldExceedServicePoint(double)
	 */
	@Override
	public boolean exceedsServicePoint(double distToTravel)
	{
		return (aircraft.estimateFlyingHours(distToTravel)
				+ aircraft.getFlightHours() > lastSPFlightHours
				+ SERVICE_INTERVAL_FLIGHT_HOURS)
				|| (AirVehicle.FLIGHTS_PER_JOB + aircraft.getFlightCount() > lastSPFlightCount
						+ SERVICE_INTERVAL_FLIGHT_COUNT);
	}

	@Override
	public String toString()
	{
		return String
				.format("Last_ServicePoint_FlightCount: %s, Last_ServicePoint_FlightHours: %s, ServiceInterval_FlightCount: %s, ServiceInterval_FlightHours:%s",
						lastSPFlightCount, lastSPFlightHours,
						SERVICE_INTERVAL_FLIGHT_COUNT,
						SERVICE_INTERVAL_FLIGHT_HOURS);
	}
}
