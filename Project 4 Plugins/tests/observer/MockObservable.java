package observer;

import java.util.List;

import plugins.Plugin;
import model.observer.Observable;

/**
 * This class represents a mocked observable
 */
public class MockObservable extends Observable<List<Plugin>> {
	
	/**
	 * @return The number of observers of this object
	 */
	public int getNumberOfObservers() {
		return this.observers.size();
	}
	
}
