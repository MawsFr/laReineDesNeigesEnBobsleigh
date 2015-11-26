package model.pluginfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ExtendedTimer implements ActionListener {
	
	public static final int TICK_INTERVAL = 1000;
	
	protected ActionListener actionListener;
	protected Timer timer;
	
	public ExtendedTimer(ActionListener pluginFinder) {
		this.actionListener = pluginFinder;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		actionListener.actionPerformed(e);
	}
	
	public void start() {
		if(timer != null) {
			timer.stop();
		}
		
		timer = new Timer(TICK_INTERVAL, this);
		timer.start();
	}
	
	public void stop() {
		timer.stop();
		timer = null;
	}

}
