package model.pluginfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ExtendedTimer implements ActionListener {
	
	protected int tickInterval;
	protected ActionListener actionListener;
	protected Timer timer;
	private boolean isStart;
	private boolean isStop;
	
	public ExtendedTimer(ActionListener pluginFinder) {
		this(pluginFinder, 1000);
		isStart = false;
		isStop = true;
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
	
	public boolean isStarted(){
		return isStart;
	}
	
	public boolean isStopped(){
		return isStop;
	}
	
	public void start() {
		if(timer != null) {
			timer.stop();
		}
		
		timer = new Timer(tickInterval, this);
		timer.start();
		isStart = true;
	}
	
	public void stop() throws Exception {
		if(timer == null){
			throw new Exception();
		}
		timer.stop();
		isStop = true;
		isStart = false;
		timer = null;
	}

}
