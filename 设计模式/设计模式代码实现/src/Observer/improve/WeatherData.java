package Observer.improve;


import java.util.ArrayList;

/*
用一个list存储观察者们 有数据更新时  主动调用list 让所有的观察者看到
 */
public class WeatherData implements Subject{

    private float temperatrue;   //温度
    private float pressure;      //气压
    private float humidity;      //湿度

    //观察者集合
    private ArrayList<Observer> observers;


    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0;i<observers.size();i++) {
            observers.get(i).update(this.temperatrue,this.pressure,this.humidity);
        }
    }

    /*
        天气数据改变后 需要通知网站去更新  推送模式
         */
    public void dataChange() {
        //currentConditions.update(getTemperature(), getPressure(), getHumidity());
        notifyObservers();
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
