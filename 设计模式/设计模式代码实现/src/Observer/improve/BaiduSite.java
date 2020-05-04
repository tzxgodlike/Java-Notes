package Observer.improve;

public class BaiduSite implements Observer{

    //气象网站  用于展示
    private float temperature;
    private float pressure;
    private float humidity;


    //更新  由weatherData来调用  这是推送模式
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("***百度天气 Today mTemperature: " + temperature + "***");
        System.out.println("***百度天气 Today mPressure: " + pressure + "***");
        System.out.println("***百度天气 Today mHumidity: " + humidity + "***");
    }
}
