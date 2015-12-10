package observer;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @param <O>
 *
 */
public class ObservableObserverTest{
	
	protected MockObservable createObservable() {
		return new MockObservable();
	}
	
	protected MockObserver createObserver() {
		return new MockObserver();
	}
	
	protected MockObservable observable;
	protected MockObserver observer;
	
	@Before
	public void setUp() {
		observable = createObservable();
		observer = createObserver();
	}

	@Test(expected =  NullPointerException.class)
	public void addInvalidObserverTest(){
		observable.addObserver(null);
	}
	
	@Test
	public void addValidObserverTest(){
		observable.addObserver(observer);
		assertEquals(1, observable.getNumberOfObservers());
	}

	@Test(expected = NullPointerException.class)
	public void noObserversToNotifyTest(){
		observable.notifyObservers(null);
	}
	
	@Test
	public void notifyObserverTest() {
		observable.addObserver(observer);
		observable.notifyObservers();
		assertTrue(observer.isNotified());
	}
	
	@Test
	public void notifyObserverNullParameterTest() {
		MockObserver observer2 = createObserver();
		observable.addObserver(observer);
		observable.addObserver(observer2);
		observable.notifyObservers(null); //to know if we authorize it or not
		assertTrue(observer.isNotified());
		assertTrue(observer2.isNotified());
	}
	
	
}
