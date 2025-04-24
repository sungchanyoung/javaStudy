package 숙제;

import java.util.Objects;

public final class Try1 {
/*
날짜 정보를 나타내는 불변 객체 클래스 
double temperatureCelsius;
int huumbidityPercent;
double pressureHpa;
double windSpeedMps;
int windDirectionDegrees;
1.유효성 검증(범위)
//상수형태로 고정해서 기준 값을 잡아라
2. 온도 단위 변환 테스트 (섭씨 <-> 화씨)
enum타입으로 추움 더움 적당함
3.날씨 상태 계산 메소드(추움 더움 적당함)
4. 불쾌지수 계산 메서드
5.widCalsius, withHumidity
 */
    private final double temperatureCelsius; //온도 섭씨
    private final int  humidity;
    private final double pressureHpa;
    private final double windSpeedMps;
    private final double windDirectionDegrees;

    public Try1(double temperatureCelsius, int humidity, double pressureHpa, double windSpeedMps, double windDirectionDegrees) {

        if(temperatureCelsius < -100 || temperatureCelsius  > 100)
        {
            throw new IllegalArgumentException("temperatureCelsius is out of range");
        }

        this.temperatureCelsius = temperatureCelsius;
        if (humidity < 0 || humidity > 100){
            throw new IllegalArgumentException("humidty is out of range");
        }
        this.humidity = humidity;

        this.pressureHpa = pressureHpa;
        this.windSpeedMps = windSpeedMps;
        this.windDirectionDegrees = windDirectionDegrees;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius * 9/5 +32;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getPressureHpa() {
        return pressureHpa;
    }

    public double getWindSpeedMps() {
        return windSpeedMps;
    }

    public double getWindDirectionDegrees() {
        return windDirectionDegrees;
    }

    public enum TemperatureFeeling {
        COLD,
        WARM,
        HOT;

    }

    public static double calculateTemperatureFeeling(double temperatureCelsius , int humidity){
        return  0.81 * temperatureCelsius + 0.01 * humidity  * (0.99 * temperatureCelsius - 14.3) + 46.3;
    }

    public static TemperatureFeeling calculateWeatherFeeling(double temperatureCelsius, int humidity){
        double di = calculateTemperatureFeeling(temperatureCelsius, humidity);
        if(di < 68)
        {
            return  TemperatureFeeling.COLD;
        }
        else if (di <=75)
        {
            return TemperatureFeeling.WARM;
        }
        else
        {
            return TemperatureFeeling.HOT;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Try1 that)) return false;
        return Double.compare(that.temperatureCelsius, temperatureCelsius) == 0 &&
                humidity == that.humidity &&
                Double.compare(that.pressureHpa, pressureHpa) == 0 &&
                Double.compare(that.windSpeedMps, windSpeedMps) == 0 &&
                Double.compare(that.windDirectionDegrees, windDirectionDegrees) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(temperatureCelsius, humidity, pressureHpa, windSpeedMps, windDirectionDegrees);
    }
    @Override
    public String toString() {
        return "Try1{" +
                "temperatureCelsius=" + temperatureCelsius +
                ", humidity=" + humidity +
                ", pressureHpa=" + pressureHpa +
                ", windSpeedMps=" + windSpeedMps +
                ", windDirectionDegrees=" + windDirectionDegrees +
                '}';
    }
}
