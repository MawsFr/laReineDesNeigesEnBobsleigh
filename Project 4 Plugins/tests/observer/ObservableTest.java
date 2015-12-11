package observer;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Observable class
 *
 */
public class ObservableTest{
	
	/**
	 * @return A mocked observable
	 */
	protected MockObservable createObservable() {
		return new MockObservable();
	}
	
	/**
	 * @return A mocked observer
	 */
	protected MockObserver createObserver() {
		return new MockObserver();
	}
	
	/**
	 * The observable
	 */
	protected MockObservable observable;
	
	/**
	 * The observer 
	 */
	protected MockObserver observer;
	
	/**
	 * Sets some variables
	 */
	@Before
	public void setUp() {
		observable = createObservable();
		observer = createObserver();
	}

	/**
	 * Tests adding a null observer to the observable
	 */
	@Test(expected =  NullPointerException.class)
	public void addInvalidObserverTest(){
		observable.addObserver(null);
	}
	
	/**
	 * Tests adding a valid observer to the observable
	 */
	@Test
	public void addValidObserverTest(){
		observable.addObserver(observer);
		assertEquals(1, observable.getNumberOfObservers());
	}

	/**
	 * Tests that an exception is thrown when notifying no observers (empty observers list)
	 */
	@Test(expected = NullPointerException.class)
	public void noObserversToNotifyTest(){
		observable.notifyObservers(null);
	}
	
	/**
	 * Tests notifying an observer
	 */
	@Test
	public void notifyObserverTest() {
		observable.addObserver(observer);
		observable.notifyObservers();
		assertTrue(observer.isNotified());
	}
	
	/**
	 * Tests notifying observers with a null parameter (to know if we authorize it or not)
	 */
	@Test
	public void notifyObserverNullParameterTest() {
		MockObserver observer2 = createObserver();
		observable.addObserver(observer);
		observable.addObserver(observer2);
		observable.notifyObservers(null);
		assertTrue(observer.isNotified());
		assertTrue(observer2.isNotified());
	}
	
	
}
