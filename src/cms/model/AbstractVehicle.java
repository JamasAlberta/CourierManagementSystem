package cms.model;

import cms.model.interfaces.Vehicle;

public abstract class AbstractVehicle implements Vehicle
{
	private String make;
	private String model;
	private double odometer = 0.0;
	private String regNo;
	private int year;

	public AbstractVehicle(String regNo, String make, String model, int year)
	{
		this.regNo = regNo;
		this.make = make;
		this.model = model;
		this.year = year;
	}

	@Override
	public String getMake()
	{
		return make;
	}

	@Override
	public double getOdometer()
	{
		return odometer;
	}

	@Override
	public String getRegNo()
	{
		return regNo;
	}

	@Override
	public int getYear()
	{
		return year;
	}

	@Override
	public String toString()
	{
		return String.format("Reg_Number: %s, Make: %s, Model: %s, Year:%s",
				regNo, make, model, year);
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof AbstractVehicle))
			return false;

		AbstractVehicle toCompare = (AbstractVehicle) o;
		return regNo.equals(toCompare.getRegNo())
				&& year == toCompare.getYear()
				&& make.equals(toCompare.getMake());
	}
}