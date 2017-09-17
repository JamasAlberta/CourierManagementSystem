package cms.model.interfaces;

public interface MaintenanceInfo
{
	/**
	 * Service a Vehicle by resetting its lastServicePoint
	 */
	public abstract void service();

	public abstract boolean exceedsServicePoint(double distToTravel);
}