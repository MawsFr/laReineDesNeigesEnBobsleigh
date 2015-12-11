package observer;

import java.util.List;

import model.observer.Observable;
import model.observer.Observer;
import plugins.Plugin;

/**
 * This class represents a mocked observer
 */
public class MockObserver implements Observer<List<Plugin>>{

	/**
	 * Boolean that contains true if this observer has been notified
	 */
	protected boolean isNotified;
	
	/**
	 * Number of plugins found in the plugin directory 
	 */
	protected int numberOfPlugins; 
	
	/* (non-Javadoc)
	 * @see model.observer.Observer#update(model.observer.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable<List<Plugin>> source, List<Plugin> object) {
		this.isNotified = true;
		if(object != null) {
			this.numberOfPlugins = object.size();
		}
		
	}
	
	/**
	 * @return true if it has been notified, else false
	 */
	public boolean isNotified() {
		return isNotified;
	}
	
//	@Override
//	public void showMessage(String title, String message, int type) {
//		System.out.println(title + " : " + message);
//	}
	
	/**
	 * @return The number of plugins found in the plugin directory
	 */
	public int getNumberOfPlugins() {
		return numberOfPlugins;
	}

}
