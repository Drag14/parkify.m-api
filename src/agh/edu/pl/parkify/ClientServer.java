package agh.edu.pl.parkify;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.ContentTypes;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.ResponseEntity;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;

/**
 * Created by twozn on 05.05.2016.
 */
public class ClientServer {
	private final ActorSystem system = ActorSystem.create();
	private final Materializer materializer = ActorMaterializer.create(system);

	public void addParkingSpot(ParkingSpot parkingSpot, UUID user) {

        String JSONtosend = null;
        try {
            JSONtosend = parkingSpot.addUUIDtoJSON(parkingSpot.toJSONString(), user);
            //System.out.println(JSONtosend);
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestPOST("http:/localhost:8080/spots", JSONtosend);
    }

	public void removeParkingSpot(ParkingSpot parkingSpot, UUID user) {
		requestPOST("/removeParkingSpot", "");
	}

	public List<ParkingSpot> getParkingSpotsInRadius(WGSCoordinate coords, double radius) {
		ResponseEntity responseEntity = requestGET("/getParkingSpots", "");
		ObjectMapper objMapper = new ObjectMapper();
		List<ParkingSpot> spotsList = new ArrayList<>();
		try {
			spotsList = objMapper.readValue(responseEntity.toString(), new TypeReference<List<ParkingSpot>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return spotsList;
	}

	private void sendRequest(HttpRequest request, HttpResponseCallback callback) {
		CompletionStage<HttpResponse> s = Http.get(system).singleRequest(request, materializer);
		s.whenComplete(new BiConsumer<HttpResponse, Throwable>() {
			@Override
			public void accept(HttpResponse httpResponse, Throwable throwable) {
				System.out.println(httpResponse.status());
				if (callback != null) {
					callback.onComplete(httpResponse.entity());
				}
			}
		});
	}

	private void requestPOST(String url, String entityJson) {
		HttpRequest request = HttpRequest.POST(url).withEntity(ContentTypes.APPLICATION_JSON, entityJson);
		sendRequest(request, null);
	}

	private ResponseEntity requestGET(String url, String entityJson) {
		HttpRequest request = HttpRequest.GET(url).withEntity(ContentTypes.APPLICATION_JSON, entityJson);
		HttpResponseCallback callback = new HttpResponseCallback();
		sendRequest(request, callback);
		return callback.getResponeEntity();
	}

	private void requestPUT(String url, String entityJson) {
		HttpRequest request = HttpRequest.PUT(url).withEntity(ContentTypes.APPLICATION_JSON, entityJson);
		sendRequest(request, null);
	}
}
