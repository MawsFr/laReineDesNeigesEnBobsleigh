package observer;

import java.util.List;

import model.observer.Observable;
import model.observer.Observer;
import plugins.Plugin;

public class MockObserver implements Observer<List<Plugin>>{

	protected boolean isNotified;
	protected int numberOfPlugins; 
	
	@Override
	public void update(Observable<List<Plugin>> source, List<Plugin> object) {
		this.isNotified = true;
		if(object != null) {
			this.numberOfPlugins = object.size();
		}
		
	}
	
	public boolean isNotified() {
		return isNotified;
	}
	
//	@Override
//	public void showMessage(String title, String message, int type) {
//		System.out.println(title + " : " + message);
//	}
	
	public int getNumberOfPlugins() {
		return numberOfPlugins;
	}

}
