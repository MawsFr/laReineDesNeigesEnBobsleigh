package model.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an Observable object
 * @param <O>
 */
public class Observable<O> implements IObservable<O> {
	/**
	 * List of all observers
	 */
	protected List<Observer<O>> observers;

	/**
	 * Default constructor
	 */
	public Observable() {
		this.observers = new ArrayList<Observer<O>>();
	}
	
	/**
	 * Adds an observer to the list
	 * @param observer The observer to add
	 */
	public synchronized void addObserver(Observer<O> observer) {
		if(observer == null){
			throw new NullPointerException();
		}
		this.observers.add(observer);
	}
	
	/* (non-Javadoc)
	 * @see model.observer.IObservable#notifyObservers()
	 */
	public synchronized void notifyObservers() {
		notifyObservers(null);
	}
	
	/* (non-Javadoc)
	 * @see model.observer.IObservable#notifyObservers(java.lang.Object)
	 */
	@Override
	public synchronized void notifyObservers(O object) {
		if(observers.isEmpty()){
			throw new NullPointerException();
		}
		
		for(Observer<O> observer : observers) {
			observer.update(this, object);
		}
	}
	
//	@Override
//	public synchronized void warning(String title, String message) {
//		for(Observer<O> observer : observers) {	
//			observer.showMessage(title, message, 1);;
//		}
//	}
//
//	@Override
//	public synchronized void error(String title, String message) {
//		for(Observer<O> observer : observers) {	
//			observer.showMessage(title, message, 0);;
//		}
//	}
//	
	

}
