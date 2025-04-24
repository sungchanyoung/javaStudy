package 숙제;

import java.util.Objects;

public record Try1_1(double temperatureCelsius, int humidity, double pressureHpa, double windSpeedMps, double windDirectionDegrees) {

    public Try1_1 {
        Objects.requireNonNull(temperatureCelsius, "temperatureCelsius cannot be null");
        Objects.requireNonNull(humidity, "humidity cannot be null");
        Objects.requireNonNull(pressureHpa, "pressureHpa cannot be null");
        Objects.requireNonNull(windSpeedMps, "windSpeedMps cannot be null");
        Objects.requireNonNull(windDirectionDegrees, "windDirectionDegrees cannot be null");

        if (temperatureCelsius < -100 || temperatureCelsius > 100)
        {
            throw new IllegalArgumentException("temperatureCelsius must be greater than -273.15");
        }
        temperatureCelsius = temperatureCelsius * 9/5 + 32;
        if (humidity < 0 || humidity > 100)
        {
            throw new IllegalArgumentException("humidity must be between 0 and 100");
        }

    }

    public static double calculateTemperatureFeeling(double temperatureCelsius, int humidity) {
        return 0.81 * temperatureCelsius + 0.01 * humidity * (0.99 * temperatureCelsius - 14.3) + 46.3;

    }

    public enum TemperatureFeeling {
        COLD,
        WARM,
        HOT;
    }

    public static Try1.TemperatureFeeling calculateWeatherFeeling(double temperatureCelsius, int humidity){
        double di = calculateTemperatureFeeling(temperatureCelsius, humidity);
        if(di < 68)
        {
            return  Try1.TemperatureFeeling.COLD;
        }
        else if (di <=75)
        {
            return Try1.TemperatureFeeling.WARM;
        }
        else
        {
            return Try1.TemperatureFeeling.HOT;
        }
    }
    public String getFormattedInfo(){
        return String.format("weather{temperatureCelsius = %.2f, humidity = %d, pressureHpa = %.2f, windSpeedMps = %.2f, " +
                        "windDirectionDegrees = %.2f, temperatureFeeling = %.2f, weatherFeeling = %s }",
                temperatureCelsius, humidity, pressureHpa, windSpeedMps, windDirectionDegrees,
                calculateTemperatureFeeling(temperatureCelsius, humidity),
                calculateWeatherFeeling(temperatureCelsius, humidity));
    }

}
