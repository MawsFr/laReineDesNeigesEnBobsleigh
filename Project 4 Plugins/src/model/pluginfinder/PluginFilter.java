package model.pluginfinder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import plugins.Plugin;

public class PluginFilter implements FilenameFilter{
	
	@Override
	public boolean accept(File dir, String name) {
		if(!name.endsWith(".class")) {
			return false;
		}
		
		Class<?> theClass = null;
		
		try {
			theClass = Class.forName(PluginFinder.PLUGINS_PACKAGE + "." + name.replaceFirst("\\.class",""));
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //TODO : Enlever a la fin
			return false;
		}
		if(!Plugin.class.isAssignableFrom(theClass)) {
			return false;
		}
		
		//TODO : Verify if the class a constructor without parameters
		return true;
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
			theClass = Class.forName(PluginFinder.PLUGINS_PACKAGE + "." + file.getName().replaceFirst("\\.class",""));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		//verify if theClass is null by extracting the variable creation into a method getTheClass
		try {
			theInstance = (Plugin) theClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return theInstance;
	}

}
