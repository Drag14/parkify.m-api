package agh.edu.pl;

import agh.edu.pl.parkify.*;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        WGSCoordinate coordinates = new WGSCoordinate(321.23, 31.23);
        ParkingSpot parkingSpot = new ParkingSpot(coordinates);

        UUID id1 = UUID.randomUUID();

        ClientServer server = new ClientServer();
        server.addParkingSpot(parkingSpot, id1);

    }
}
