package pluginfinder;
import static org.junit.Assert.assertTrue;
import model.pluginfinder.ExtendedTimer;
import model.pluginfinder.PluginFinder;

import org.junit.After;
import org.junit.Test;

/**
 * This class tests the ExtendedTimer class 
 */
public class ExtendedTimerTest {

	/**
	 * An extended timer instance
	 */
	protected ExtendedTimer timer;
	
	/**
	 * Tests creating an extended timer with a null action listener parameter 
	 */
	@Test(expected = NullPointerException.class) 
	public void nullActionListenerParameterTest(){
		timer = new ExtendedTimer(null,20); 
	}
	
	/**
	 * Tests creating an extended timer with a null tickInterval parameter
	 */
	@Test(expected = IllegalArgumentException.class)
	public void nullTickIntervalTest(){
		timer = new ExtendedTimer(new PluginFinder(), 0);
	}
	
	/**
	 * Tests creating an extended timer with a negative tickInterval parameter
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTickIntervalNegativeIntoExtendedTimer(){
		timer = new ExtendedTimer(new PluginFinder(), -1);
	}
	
	/**
	 * Tests that an exception is thrown when trying to stop an already stopped timer
	 * @throws NullPointerException Because the intern timer object is null
	 */
	@Test(expected = NullPointerException.class)
	public void timerIsAlreadyStop() throws NullPointerException{
		timer = new ExtendedTimer(new PluginFinder(),20); 
		assertTrue(timer.isStopped());
		timer.start();
		assertTrue(timer.isStarted());
		timer.stop();
		assertTrue(timer.isStopped());
		timer.stop();
	}
	
	/**
	 * Tests that no exception is thrown when restarting a timer
	 */
	@Test
	public void timerIsAlreadyStarted(){
		timer = new ExtendedTimer(new PluginFinder(),20);
		assertTrue(timer.isStopped());
		timer.start();
		assertTrue(timer.isStarted());
		timer.start();
		assertTrue(timer.isStarted());
		timer.stop();
	}
	
	/**
	 * Removes some variables
	 */
	@After
	public void tearUp() {
		timer = null;
	}
	
}
