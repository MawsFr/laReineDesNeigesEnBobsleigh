package observer;
import org.junit.Test;

/**
 * @author admin
 * @param <O>
 *
 */
public class ObservableTest<O>{
	
	protected MockObservable observable = new MockObservable();

	@Test(expected =  NullPointerException.class)
	public void testIfTheObserverIsNullIntoAddObserver(){
		observable.addObserver(null);
	}

	@Test(expected =NullPointerException.class)
	public void testIfTheObserverIsEmptyIntoNotifyObserver(){
		observable.notifyObservers(null);
	}
	
	
}
