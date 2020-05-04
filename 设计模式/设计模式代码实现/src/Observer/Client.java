package Observer;

public class Client {

    public static void main(String[] args) {

        /*
        传统方法
         */
        //创建接入方
        CurrentConditions currentConditions = new CurrentConditions();

        WeatherData weatherData = new WeatherData(currentConditions);

        //更新天气情况
        weatherData.setData(30,150,40);
    }
}
