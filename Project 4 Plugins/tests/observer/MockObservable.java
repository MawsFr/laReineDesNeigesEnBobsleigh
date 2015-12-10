package observer;

import java.util.List;

import plugins.Plugin;
import model.observer.Observable;

public class MockObservable extends Observable<List<Plugin>> {
	
	public int getNumberOfObservers() {
		return this.observers.size();
	}
	
}
