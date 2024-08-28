package nl.mpdev.demo_web_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WeatherController {

  @GetMapping(value = "/weather/current")
  public String getCurrentWeather() {
    return "Sunny in Amsterdam";
  }

  @GetMapping(value = "/weather/current/{city}")
  public ResponseEntity<String> getCurrentWeatherByCity(@PathVariable String city) {
    String formattedString = city.toLowerCase();
    return checkList(citiesForecast(), formattedString);
  }

  @GetMapping(value = "weather/current/coordinates")
  public String getCurrentWeatherByCoordinates(
    @RequestParam(value = "latitude") double latitude,
    @RequestParam(value = "longitude") double longitude) {
    return "Rainy in " + latitude + ", " + longitude;
  }

  private static Map<String, String> citiesForecast() {
    Map<String, String> citiesForecast = new HashMap<>();
    citiesForecast.put("Amsterdam", "Regenachtig in Amsterdam");
    citiesForecast.put("Rotterdam", "Zonnig in Rotterdam");
    citiesForecast.put("Utrecht", "Bewolkt in Utrecht");
    citiesForecast.put("Den Haag", "Windy in Den Haag");
    return citiesForecast;
  }

  private ResponseEntity<String> checkList(Map<String, String> citiesForecast, String requestedCity) {
    for(Map.Entry<String, String> entry : citiesForecast.entrySet()) {
      if (entry.getKey().equalsIgnoreCase(requestedCity)) {
        return  ResponseEntity.ok().body(entry.getValue());
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Weersvoorspelling niet beschikbaar voor " + requestedCity);
  }

}
