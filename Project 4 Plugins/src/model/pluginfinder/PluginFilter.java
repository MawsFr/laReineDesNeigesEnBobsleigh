package model.pluginfinder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import plugins.Plugin;

public class PluginFilter implements FilenameFilter{
	
	@Override
	public boolean accept(File dir, String name) {
		if(!nameEndsWithClass(name)) {
			return false;
		}
		
		Class<?> theClass = getTheClass(name);
		
		if(theClass == null) {
			return false;
		}
		
		if(!isInPluginPackage(theClass)) {
			return false;
			
		}
		
		if(!isSubclassOfPlugin(theClass)) {
			return false;
		}
		
		//TODO : Verify if the class a constructor without parameters
		return true;
	}
	
	public boolean nameEndsWithClass(String fileName) {
		return fileName.endsWith(".class");
		
	}
	
	public Class<?> getTheClass(String name) {
		Class<?> theClass = null;
		try {
			theClass = Class.forName(PluginFinder.PLUGINS_PACKAGE + "." + name.replaceFirst("\\.class",""));
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //TODO : Enlever a la fin
		}
		
		return theClass;
	}
	
	public boolean isInPluginPackage(Class<?> theClass) {
		return theClass.getPackage().getName().equals(PluginFinder.PLUGINS_PACKAGE);
	}
	
	public boolean isSubclassOfPlugin(Class<?> theClass) {
		return Plugin.class.isAssignableFrom(theClass);
	}
	
	public List<Plugin> filesToPlugins(List<File> files) {
		List<Plugin> plugins = new ArrayList<Plugin>();
		for(File file : files) {
			plugins.add(fileToPlugin(file));
		}
		return plugins;
	}
	
	public Plugin fileToPlugin(File file) {
		Class<?> theClass = null;
		Plugin theInstance = null;
		
		theClass = getTheClass(file.getName());
		//verify if theClass is null by extracting the variable creation into a method getTheClass
		try {
			theInstance = (Plugin) theClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return theInstance;
	}

}
