package observer;
import org.junit.Test;

import model.observer.Observable;

/**
 * @author admin
 * @param <O>
 *
 */
public class ObservableTest<O>{
	
	protected model.observer.Observable<O> observable = new Observable<>();

	@Test(expected =  NullPointerException.class)
	public void testIfTheObserverIsNullIntoAddObserver(){
		
		observable.addObserver(null);
	}

	@Test(expected =NullPointerException.class)
	public void testIfTheObserverIsEmptyIntoNotifyObserver(){
		observable.notifyObservers(null);
	}
	
	
}
