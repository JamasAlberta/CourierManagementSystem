package cms.model.interfaces;

public interface Vehicle
{
	public double calculateWearAndTear(double distance);

	public abstract String getRegNo();

	public abstract String getMake();

	public abstract double getOdometer();

	public abstract int getYear();

	/**
	 * Service a vehicle by setting the current odometer reading to the last
	 * service point
	 * 
	 * @see MaintenanceInfo#service(double)
	 */
	public abstract void service();

	/**
	 * determines whether a vehicle can travel a specified distance without
	 * exceeding its next service point
	 * 
	 * @see MaintenanceInfo#wouldExceedServicePoint(double, double)
	 */
	public abstract boolean canTravel(double distance);

	public abstract double travel(double distance);
}