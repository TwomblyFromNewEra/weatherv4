package ah.hathi.weatherv4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public EditText cityEditText;
    public TextView temperatureTextView;
    public TextView humidityTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.getWeatherButton);
        cityEditText = findViewById(R.id.cityEditText);
        temperatureTextView = findViewById(R.id.temperatureTextView);




    }

    public void getWeather(View view) throws IOException {
        String cityName = cityEditText.getText().toString();

        WeatherClient.getInstance().getWeather(cityName, new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    double temperature = weatherResponse.getTemp();
                    int humidity = weatherResponse.getHumidity();
                    Button btn = findViewById(R.id.getWeatherButton);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            temperatureTextView.setText("Temperature:" + temperature);
                            humidityTextView.setText("Humidity" + humidity);
                        }});

                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}