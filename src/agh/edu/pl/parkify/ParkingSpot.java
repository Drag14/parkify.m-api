package agh.edu.pl.parkify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by twozn on 05.05.2016.
 */
public class ParkingSpot {
	private WGSCoordinate coords;

	public ParkingSpot (WGSCoordinate coords){
		this.setCoords(coords);
	}

	String toJSONString() throws JsonProcessingException{
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(this);
	}

	static ParkingSpot fromJSONString(String jsonString) throws IOException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.readValue(jsonString, ParkingSpot.class);
	}

	public WGSCoordinate getCoords() {
		return coords;
	}

	String addUUIDtoJSON(String JSONParkingSpot, UUID uuid) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(JSONParkingSpot);
		ObjectNode objectnode = (ObjectNode)json;
		objectnode.put("UUID", uuid.toString());
		return objectnode.toString();
	}

	private void setCoords(WGSCoordinate coords) {
		this.coords = coords;
	}
}
