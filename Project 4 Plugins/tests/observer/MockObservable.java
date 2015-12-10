package observer;

import model.observer.Observable;

public class MockObservable extends Observable<String> {
	
	public int getNumberOfObservers() {
		return this.observers.size();
	}
	
}
