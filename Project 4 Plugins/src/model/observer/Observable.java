package model.observer;

import java.util.ArrayList;
import java.util.List;

public class Observable<O> implements IObservable<O> {
	protected List<Observer<O>> observers;

	public Observable() {
		this.observers = new ArrayList<Observer<O>>();
	}
	
	public void addObserver(Observer<O> observer) {
		if(observer==null){
			throw new NullPointerException();
		}
		this.observers.add(observer);
	}
	
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	@Override
	public void notifyObservers(O object) {
		if(observers.isEmpty()){
			throw new NullPointerException();
		}
		for(Observer<O> observer : observers) {	
			observer.update(object);
		}
	}
	

}
