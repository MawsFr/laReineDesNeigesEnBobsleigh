package observer;

import model.observer.Observer;

public class MockObserver implements Observer<String>{

	protected boolean isNotified;
	
	@Override
	public void update(String object) {
		this.isNotified = true;
	}
	
	public boolean isNotified() {
		return isNotified;
	}

}
