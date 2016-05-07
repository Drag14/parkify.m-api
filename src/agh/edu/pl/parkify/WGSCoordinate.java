package agh.edu.pl.parkify;

/**
 * Created by twozn on 06.05.2016.
 */
public class WGSCoordinate {
	private double longitude;
	private double latitude;

	public WGSCoordinate(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
