package agh.edu.pl.parkify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by twozn on 05.05.2016.
 */
public class ParkingSpot {
	private WgsCoordinate coords;

	String toJSONString() throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(this);
	}

	static ParkingSpot fromJSONString(String jsonString) throws IOException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.readValue(jsonString, ParkingSpot.class);
	}

	public WgsCoordinate getCoords() {
		return coords;
	}

	public void setCoords(WgsCoordinate coords) {
		this.coords = coords;
	}
}
