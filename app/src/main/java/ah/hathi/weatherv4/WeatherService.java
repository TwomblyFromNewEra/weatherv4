package ah.hathi.weatherv4;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {
    private static final String API_KEY = "41e9d5063e0f92cba9e17f3f32f092b4";
    private String city;

    public WeatherService(String city) {
        this.city = city;
    }

    public WeatherResponse getWeather(String cityName) throws IOException {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        InputStream responseStream = con.getInputStream();
        Scanner scanner = new Scanner(responseStream);
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();
        responseStream.close();
        String jsonResponse = response.toString();
        double temperature = Double.parseDouble(jsonResponse.split("\"temp\":")[1].split(",")[0]);
        int humidity = Integer.parseInt(jsonResponse.split("\"humidity\":")[1].split(",")[0]);
        double windSpeed = Double.parseDouble(jsonResponse.split("\"speed\":")[1].split(",")[0]);
        String description = jsonResponse.split("\"description\":\"")[1].split("\",")[0];
        return new WeatherResponse(temperature, humidity, windSpeed, description);
    }
}