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
		//TODO : throw une exception sur pluginFinder est null
		//TODO : throw une exception si tickInterval est inferieur ou = 0
		this.actionListener = pluginFinder;
		this.tickInterval = tickInterval;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		actionListener.actionPerformed(e);
	}
	
	public void start() {
		//TODO : Ici on a deja le test de si le timer est deja start, il faut faire le test associé dans ExtendedTimerTest !
		if(timer != null) {
			timer.stop();
		}
		
		timer = new Timer(tickInterval, this);
		timer.start();
	}
	
	public void stop() {
		//TODO : throw une exception si déjà stoppé (si timer est deja null quoi)
		timer.stop();
		timer = null;
	}

}
