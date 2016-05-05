package agh.edu.pl.parkify;

/**
 * Created by twozn on 06.05.2016.
 */
public class WgsCoordinate {
	private double longitude;
	private double latitude;

	public WgsCoordinate(double longitude, double latitude) {
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
