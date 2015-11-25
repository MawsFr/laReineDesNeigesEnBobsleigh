package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ExtendedTimer implements ActionListener {
	
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
		
		timer = new Timer(1000, this);
		timer.start();
	}
	
	public void stop() {
		timer.stop();
		timer = null;
	}

}
