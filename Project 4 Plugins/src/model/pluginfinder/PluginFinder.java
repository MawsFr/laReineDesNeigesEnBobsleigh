package model.pluginfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import plugins.Plugin;
import model.observer.Observable;

public class PluginFinder extends Observable<List<Plugin>> implements ActionListener {
	
	public static final String DEFAULT_PLUGINS_PATH = "./plugins";
	public static final String PLUGINS_PACKAGE = Plugin.class.getPackage().getName();
	
	protected ExtendedTimer timer;
	protected List<File> plugins;
	protected PluginFilter pluginFilter;
	protected String pluginDirectory;
	
	public PluginFinder(String pluginDirectory) {
		this.pluginDirectory = pluginDirectory;
		this.pluginFilter = new PluginFilter();
		timer = new ExtendedTimer(this);
		this.plugins = new ArrayList<File>();
		System.out.println(PLUGINS_PACKAGE);
		
	}
	
	public PluginFinder() {
		this(DEFAULT_PLUGINS_PATH);
	}
	
	public void start() {
		timer.start();
	}
	
	public List<File> getAllFiles() {
		File dir = new File(pluginDirectory);
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
			this.plugins = newPlugins;
			notify(plugins);
		}
		
	}
	
	
	
	public void notify(List<File> files) {
		//TODO : notify the view
		List<Plugin> pluginsFinded = filesToPlugins(files);
		notifyObservers(pluginsFinded);
	}

	public List<Plugin> filesToPlugins(List<File> files) {
		// TODO Auto-generated method stub
		List<Plugin> plugins = new ArrayList<Plugin>();
		for(File file : files) {
			plugins.add(fileToPlugin(file));
		}
		return plugins;
	}
	
	public Plugin fileToPlugin(File file) {
		Class<?> theClass = null;
		Plugin theInstance = null;
		try {
			theClass = Class.forName(PLUGINS_PACKAGE + "." + file.getName().replaceFirst("\\.class", ""));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(!theClass.isAssignableFrom(Plugin.class)) {
			throw new ClassCastException(theClass.getName() + " doesn't extend Plugin !");
		}
		try {
			theInstance = (Plugin) theClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return theInstance;
	}
	
	
}
