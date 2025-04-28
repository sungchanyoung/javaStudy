package day1.숙제;

enum WeatherCondition{
    FREEZING("매우 추움"),
    COLD("추움"),
    COOL("서늘함"),
    MILD("적당함"),
    WARN("따뜻함"),
    HOT("더움"),
    VERY_HOT("매우더워");

    private final String description;

    WeatherCondition(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
enum DiscomfortLevel{
    CONFORMABLE("쾌적함"),
    SLIGHTLY_UNCOMFORTABLE("약간 쾌적함"),
    UNCOMFORTABLE("불쾌함"),
    VERT_UNCOMFORTABLE("매우불쾌함");
    private final String description;

    DiscomfortLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
public final class Answer1 {
    private final double temperatureCelsius;
    private final double humidity;
    private final double pressureHpa;
    private final double winfSpeedMps;
    private final double windDirectionDegrees;

    private final double MIN_TEMP_CELSIUS = -100.0;
    private final double MAX_TEMP_CELSIUS = 100.0;
    private final double MIN_HUMIDITY = 0.0;
    private final double MAX_HUMIDITY = 100.0;
    private final double MIN_PRESSURE_HPA = 800.0;
    private final double MAX_PRESSURE_HPA = 1200.0;
    private final double MIN_WIND_SPEED_MPS = 0.0;
    private final double MAX_WIND_SPEED_MPS = 200.0;

    public Answer1(double temperatureCelsius, double humidity, double pressureHpa, double winfSpeedMps, double windDirectionDegrees) {
        if(temperatureCelsius < MIN_TEMP_CELSIUS || temperatureCelsius > MAX_TEMP_CELSIUS){
            throw new IllegalArgumentException(String.format("temperatureCelsius must be between %f and %f",
                    MIN_TEMP_CELSIUS, MAX_TEMP_CELSIUS)
            );
        }
        this.temperatureCelsius = temperatureCelsius;
        this.humidity = humidity;
        this.pressureHpa = pressureHpa;
        this.winfSpeedMps = winfSpeedMps;
        this.windDirectionDegrees = windDirectionDegrees;
    }
    public double getDiscomfortIndex() {
        return temperatureCelsius * 9/5 + 32;
    }

    public WeatherCondition getWeatherCondition() {
        if(temperatureCelsius < -10) return WeatherCondition.FREEZING;
        if(temperatureCelsius < 0) return WeatherCondition.COLD;
        if(temperatureCelsius < 10) return WeatherCondition.COOL;
        if(temperatureCelsius < 20) return WeatherCondition.MILD;
        if(temperatureCelsius < 30) return WeatherCondition.WARN;
        if(temperatureCelsius < 40) return WeatherCondition.HOT;
        return WeatherCondition.VERY_HOT;
    }

    public double getDiscomfortLevel() {
        return 0.81 * temperatureCelsius + 0.01 *
                humidity * (0.99 * temperatureCelsius - 14.3)
                + 46.3;
    }

    public DiscomfortLevel getDisconfortLevel() {
        double index = getDiscomfortIndex();
        if(index < 68) return DiscomfortLevel.CONFORMABLE;
        if(index < 75) return DiscomfortLevel.SLIGHTLY_UNCOMFORTABLE;
        if(index < 80) return DiscomfortLevel.UNCOMFORTABLE;
        return DiscomfortLevel.VERT_UNCOMFORTABLE;
    }

    public  Answer1 withHumidity(double humidity) {
        return new Answer1(temperatureCelsius, humidity, pressureHpa, winfSpeedMps, windDirectionDegrees);
    }

}
