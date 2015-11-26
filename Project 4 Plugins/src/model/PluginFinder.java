package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class PluginFinder extends Observable implements ActionListener {
	
	protected ExtendedTimer timer;
	protected List<File> plugins;
	protected PluginFilter pluginFilter;
	protected String pluginDirectory;
	
	public PluginFinder(String pluginDirectory) {
		this.pluginDirectory = pluginDirectory;
		this.pluginFilter = new PluginFilter();
		timer = new ExtendedTimer(this);
		this.plugins = new ArrayList<File>();
		timer.start();
		
	}
	
	public List<File> getAllFiles() {
		File dir = new File(pluginDirectory);
		System.out.println(dir.getAbsolutePath());
		File[] files = dir.listFiles(pluginFilter);
		
		if(files == null || files.length == 0) {
			return new ArrayList<File>();
		}
		
		return new ArrayList<File>(Arrays.asList(files));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<File> newPlugins = getAllFiles();
		if(!(newPlugins.equals(this.plugins))) {
			notify(newPlugins);
		}
		
	}
	
	public void notify(List<File> files) {
		//TODO : notify the view
		for(File file : files) {
			sendMessageToObservers("Added new plugin " + file);
		}
	}
	
	public void sendMessageToObservers(String message) {
		setChanged();
		notifyObservers(message);
	}

}
