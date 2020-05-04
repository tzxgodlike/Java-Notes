package Observer.improve;


//发布者     让weatherData实现
public interface Subject {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();
}
