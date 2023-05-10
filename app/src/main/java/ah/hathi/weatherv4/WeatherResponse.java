package ah.hathi.weatherv4;

public class WeatherResponse {
    private double temperature;
    private int humidity;
    private double windSpeed;
    private String description;

    public WeatherResponse(double temperature, int humidity, double windSpeed, String description) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
    }

    public String allWeather() {
        return "Temperature: " + temperature + "°C\nHumidity: " + humidity + "%\nWind speed: " + windSpeed + " m/s\nDescription: " + description;
    }

    public double getTemp() {
       return temperature;
    }
    public int getHumidity(){
        return humidity;
    }

}