package pluginfinder;
import static org.junit.Assert.*;

import org.junit.Test;

import model.pluginfinder.ExtendedTimer;
import model.pluginfinder.PluginFinder;

/**
 * 
 */

/**
 * @author admin
 *
 */
public class ExtendedTimerTest {

	protected ExtendedTimer timer;
	
	@Test(expected = NullPointerException.class) 
	public void testPluginFinderNullIntoExtendedTimer(){
		timer = new ExtendedTimer(null,20); 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTickIntervalEqualZeroIntoExtendedTimer(){
		timer = new ExtendedTimer(new PluginFinder(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTickIntervalNegativeIntoExtendedTimer(){
		timer = new ExtendedTimer(new PluginFinder(), -1);
	}
	
	@Test(expected = NullPointerException.class)
	public void timerIsAlreadyStop() throws NullPointerException{
		timer = new ExtendedTimer(new PluginFinder(),20); 
		assertTrue(timer.isStopped());
		timer.start();
		assertTrue(timer.isStarted());
		timer.stop();
		assertTrue(timer.isStopped());
		timer.stop();
		assertTrue(timer.isStopped());
	}
	
	@Test
	public void timerIsAlreadyStarted(){
		timer = new ExtendedTimer(new PluginFinder(),20);
		assertTrue(timer.isStopped());
		timer.start();
		assertTrue(timer.isStarted());
		timer.start();
		assertTrue(timer.isStarted());
	}
	
}
