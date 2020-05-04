package Observer;

public class WeatherData {

    private float temperatrue;   //温度
    private float pressure;      //气压
    private float humidity;      //湿度

    private CurrentConditions currentConditions;

    public WeatherData(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }

    /*
    天气数据改变后 需要通知网站去更新  推送模式
     */
    public void dataChange() {
        currentConditions.update(getTemperature(), getPressure(), getHumidity());
    }

    public void setData(float temperature, float pressure, float humidity) {
        this.temperatrue = temperature;
        this.pressure = pressure;
        this.humidity = humidity;

        //推送更新  调用接入方的update方法
        dataChange();
    }
    public float getTemperature() {
        return temperatrue;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }


}
