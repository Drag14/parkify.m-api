package agh.edu.pl.parkify;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by twozn on 24.04.2016.
 */
public class AndroidAPI {
	List<WGSCoordinate> getAvailablePlaces(WGSCoordinate position, double radiusKM, int userID) throws DataFormatException {
		HttpURLConnection con = null;
		try {
			String parametersString = "?longitude=" + position.getLongitude() + "&latitude=" + position.getLatitude()
					+ "&radius=" + radiusKM + "&userID=" + userID;
			String getUrl = "http://www.agh.edu.pl/parkcostam";
			URL url = new URL(getUrl + parametersString);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				List<WGSCoordinate> placesList = new ArrayList<>();
				while ((inputLine = in.readLine()) != null) {
					String[] tokens = inputLine.split(",");
					if (tokens.length != 2) {
						throw new DataFormatException("Niepoprawny format wspolrzednych w GET body");
					}
					double longitude = Double.valueOf(tokens[0]);
					double latitude = Double.valueOf(tokens[1]);
					placesList.add(new WGSCoordinate(longitude, latitude));
				}
				in.close();
				return placesList;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return null;
	}
}
