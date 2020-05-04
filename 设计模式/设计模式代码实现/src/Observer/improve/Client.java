package Observer.improve;

public class Client {
    public static void main(String[] args) {
        WeatherData weatherData= new WeatherData();

        CurrentConditions currentConditions = new CurrentConditions();

        weatherData.registerObserver(currentConditions);
        //添加新的网站[观察者]
        weatherData.registerObserver(new BaiduSite());

        System.out.println("通知观察者看信息");

        weatherData.setData(10f,100f,30f);
    }
}
