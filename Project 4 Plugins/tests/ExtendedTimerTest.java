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
	
	@Test(expected = Exception.class)
	public void timerIsAlreadyStop() throws Exception{
		timer = new ExtendedTimer(new PluginFinder(),20); 
		timer.start();
		timer.stop();
		timer.stop();
	}
	
	@Test
	public void timerIsAlreadyStarted(){
		timer = new ExtendedTimer(new PluginFinder(),20);
		timer.start();
		if(timer != null){
			timer.start();
		}
	}
	
}
