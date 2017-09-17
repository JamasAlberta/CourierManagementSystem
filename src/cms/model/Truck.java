package cms.model;

public class Truck extends LandVehicle
{
	public static final double WEAR_AND_TEAR_RATE = 0.5;

	private double loadCapacity = 0;

	public Truck(String regNo, String make, String model, int year,
			double serviceInterval, double loadCapacity)
	{
		super(regNo, make, model, year, serviceInterval);
		this.loadCapacity = loadCapacity;
	}

	@Override
	public double calculateWearAndTear(double distance)
	{
		return WEAR_AND_TEAR_RATE * loadCapacity * distance;
	}

	public double getLoadCapacity()
	{
		return loadCapacity;
	}

	@Override
	public String toString()
	{
		return "Type: Truck, " + super.toString() + ", Load Capacity: "
				+ loadCapacity;
	}
}