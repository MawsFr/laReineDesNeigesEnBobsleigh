package model.pluginfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * This class represents a timer which does the action of the actionListener it contains (generally a pluginFinder)
 */
public class ExtendedTimer implements ActionListener {
	
	/**
	 * The tick interval when the action is performed
	 */
	protected int tickInterval;
	
	/**
	 * The actionListener of this timer 
	 */
	protected ActionListener actionListener;
	
	/**
	 * The timer 
	 */
	protected Timer timer;
	
	/**
	 * Constructor with action listener
	 * @param actionListener The action listener to perform action
	 */
	public ExtendedTimer(ActionListener actionListener) {
		this(actionListener, 1000);
	}
	
	/**
	 * Constructor with action listener and tick interval
	 * @param actionListener The action listener to perform action
	 * @param tickInterval The tick interval
	 */
	public ExtendedTimer(ActionListener actionListener, int tickInterval) throws NullPointerException, IllegalArgumentException {
		if(actionListener == null){
			throw new NullPointerException();
		}
		if(tickInterval <= 0){
			throw new IllegalArgumentException();
		}
		this.actionListener = actionListener;
		this.tickInterval = tickInterval;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		actionListener.actionPerformed(e);
	}
	
	/**
	 * @return True if this timer is already started, else false
	 */
	public boolean isStarted(){
		return timer != null;
	}
	
	/**
	 * @return True if this timer is stopped
	 */
	public boolean isStopped(){
		return timer == null;
	}
	
	/**
	 * Starts the timer
	 * If it is already started it stops it and starts again
	 */
	public void start() {
		if(isStarted()) {
			timer.stop();
		}
		
		timer = new Timer(tickInterval, this);
		timer.start();
	}
	
	/**
	 * Stops this timer
	 * @throws NullPointerException If it's already stopped
	 */
	public void stop() throws NullPointerException {
		if(isStopped()){
			throw new NullPointerException();
		}
		timer.stop();
		timer = null;
	}

}
