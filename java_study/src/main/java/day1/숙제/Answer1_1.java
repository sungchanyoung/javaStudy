package day1.숙제;
enum WeatherCondition1{
    FREEZING("매우 추움"),
    COLD("추움"),
    COOL("서늘함"),
    MILD("적당함"),
    WARN("따뜻함"),
    HOT("더움"),
    VERY_HOT("매우 더움");
    private final String description;
    WeatherCondition1(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}

enum DisComfortLevel1{
    COMFORTABLE("쾌적함"),
    SLIGHTLY_COMFORTABLE("약간 쾌적함"),
    UNCOMFORTABLE("불쾌함"),
    VERY_UNCOMFORTABLE("매우 쾌적함");
    private final String description;

    DisComfortLevel1(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}


public record Answer1_1(
        double temperatureCelsius,
        double humidityPercent,
        double pressureHpa,
        double windSpeedMps,
        int widDirectionDegrees
) {
    private static final double MIN_TEMP_CELSIUS = -100.0;
    private static final double MAX_TEMP_CELSIUS = 100.0;
    private static final double MIN_HUMIDITY = 0.0;
    private static final double MAX_HUMIDITY = 100.0;
    private static final double MIN_PRESSURE_HPA = 800.0;
    private static final double MAX_PRESSURE_HPA = 1200.0;
    private static final double MIN_WIND_SPEED_MPS = 0.0;
    private static final double MAX_WIND_SPEED_MPS = 200.0;

    public Answer1_1 {
        //온도 유효성 검증
        if (temperatureCelsius < MIN_TEMP_CELSIUS || temperatureCelsius > MAX_TEMP_CELSIUS){
            throw new IllegalArgumentException(
                    String.format("temperatureCelsius must be between %f and %f",
                            MIN_TEMP_CELSIUS, MAX_TEMP_CELSIUS)
            );
        }
        //습도 유효성 검증
        if(MIN_HUMIDITY > humidityPercent || humidityPercent > MAX_HUMIDITY){
            throw new IllegalArgumentException(
                    String.format("humidityPercent must be between %f and %f",
                            MIN_HUMIDITY, MAX_HUMIDITY)
            );
        }

        if(pressureHpa < MIN_PRESSURE_HPA || pressureHpa > MAX_PRESSURE_HPA){
            throw new IllegalArgumentException(
                    String.format("pressureHpa must be between %f and %f",
                            MIN_PRESSURE_HPA, MAX_PRESSURE_HPA)
            );
        }
        if(windSpeedMps < MIN_WIND_SPEED_MPS || windSpeedMps > MAX_WIND_SPEED_MPS){
            throw new IllegalArgumentException(
                    String.format("windSpeedMps must be between %f and %f",
                            MIN_WIND_SPEED_MPS, MAX_WIND_SPEED_MPS)
            );
        }

    }

    //온도 단위 변환
    public double getMinTempCelsiusFahrenheit(){
        return  temperatureCelsius * 1.8 + 32;
    }

    //날씨 상태 계산
    public WeatherCondition1 getWeatherCondition(){
        if(temperatureCelsius < -10) return WeatherCondition1.FREEZING;
        if(temperatureCelsius < 0) return WeatherCondition1.COLD;
        if(temperatureCelsius < 10) return WeatherCondition1.COOL;
        if(temperatureCelsius < 20) return WeatherCondition1.MILD;
        if(temperatureCelsius < 30) return WeatherCondition1.WARN;
        if(temperatureCelsius < 40) return WeatherCondition1.HOT;
        return WeatherCondition1.VERY_HOT;
    }

    //0.81 * 기온  + 0.01 * 습도 (0.99 * 기온 * 14.3 ) + 46.3
    public double getDiscomfortIndex(){
        return 0.81 * temperatureCelsius + 0.01 * humidityPercent * (0.99 * temperatureCelsius - 14.3) + 46.3;
    }

    public DisComfortLevel1 getDiscomfortLevel(){
        double index = getDiscomfortIndex();
        if(index < 68) return DisComfortLevel1.COMFORTABLE;
        if(index < 75) return DisComfortLevel1.SLIGHTLY_COMFORTABLE;
        if(index < 80) return DisComfortLevel1.UNCOMFORTABLE;
        return DisComfortLevel1.VERY_UNCOMFORTABLE;
    }

    public Answer1_1 withHumidityPercent(double humidityPercent){
        return new Answer1_1(
                temperatureCelsius,
                humidityPercent,
                pressureHpa,
                windSpeedMps,
                widDirectionDegrees
        );
    }
}
