package model.pluginfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ExtendedTimer implements ActionListener {
	
	protected int tickInterval;
	protected ActionListener actionListener;
	protected Timer timer;
	
	public ExtendedTimer(ActionListener pluginFinder) {
		this(pluginFinder, 1000);
	}
	
	public ExtendedTimer(ActionListener pluginFinder, int tickInterval) {
		if(pluginFinder == null){
			throw new NullPointerException();
		}
		if(tickInterval <=0){
			throw new IllegalArgumentException();
		}
		this.actionListener = pluginFinder;
		this.tickInterval = tickInterval;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		actionListener.actionPerformed(e);
	}
	
	public boolean isStart(){
		return timer != null;
	}
	
	public boolean isStopped(){
		return timer == null;
	}
	
	public void start() {
		if(timer != null) {
			timer.stop();
		}
		
		timer = new Timer(tickInterval, this);
		timer.start();
	}
	
	public void stop() throws NullPointerException {
		if(timer == null){
			throw new NullPointerException();
		}
		timer.stop();
		timer = null;
	}

}
