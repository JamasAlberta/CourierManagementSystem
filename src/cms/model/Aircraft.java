package cms.model;

public class Aircraft extends AirVehicle
{
	// Flagfall for every job
	public static final int WEAR_AND_TEAR_FLAGFALL = 30000;
	private final double WEAR_AND_TEAR_RATE = 5;

	public Aircraft(String regNo, String make, String model, int year,
			double averageSpeed, int siFlightCountInterval,
			double siFlightHoursInterval)
	{
		super(regNo, make, model, year, averageSpeed, siFlightCountInterval,
				siFlightHoursInterval);
	}

	@Override
	public double calculateWearAndTear(double distance)
	{
		return WEAR_AND_TEAR_FLAGFALL + (WEAR_AND_TEAR_RATE * distance);
	}
}
