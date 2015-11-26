package model.observer;

public interface IObservable<O> {
	public void notifyObservers();
	public void notifyObservers(O object);

}
