package model.pluginfinder;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import exceptions.PluginException;
import plugins.Plugin;

public class PluginFilter implements FilenameFilter{
	
	protected List<String> nonChargedPluginsList;
	
	public PluginFilter() {
		this.nonChargedPluginsList = new ArrayList<String>();
	}
	
	@Override
	public boolean accept(File dir, String name) {
		if(!nameEndsWithClass(name)) {
			return false;
		}
		
		Class<?> theClass;
		try {
			theClass = getTheClass(name);
		} catch (PluginException e) {
			this.nonChargedPluginsList.add(name);
			return false;
		}
		
		if(isAbstractOrInterfaceOrEnum(theClass)
				|| !isInPluginPackage(theClass)
				|| !isSubclassOfPlugin(theClass)
				|| !hasDefaultConstructor(theClass)) {
			this.nonChargedPluginsList.add(name);
			return false;
		}
		
		//TODO : Verify if the class a constructor without parameters
		return true;
	}
	
	public List<String> getNonLoadedPluginsList() {
		return nonChargedPluginsList;
	}

	public boolean hasDefaultConstructor(Class<?> theClass) {
		for(Constructor<?> constructor : theClass.getConstructors()) {
			if(constructor.getParameterCount() == 0) {
				return true;
			}
		}
		
		return false;
	}

	public boolean isAbstractOrInterfaceOrEnum(Class<?> theClass) {
		return theClass.isInterface() || Modifier.isAbstract(theClass.getModifiers()) || theClass.isEnum();
	}

	public boolean nameEndsWithClass(String fileName) {
		return fileName.endsWith(".class");
		
	}
	
	public Class<?> getTheClass(String name) throws PluginException {
		Class<?> theClass = null;
		name = name.replaceFirst("\\.class", "");
		
		try {
			theClass = Class.forName(PluginFinder.PLUGINS_PACKAGE + "." + name);
		} catch (ClassNotFoundException | NoClassDefFoundError e) {
			throw new PluginException("Problem while trying to get the class of " + name);
		}
		
		return theClass;
	}
	
	public boolean isInPluginPackage(Class<?> theClass) {
		return theClass.getPackage().getName().equals(PluginFinder.PLUGINS_PACKAGE);
	}
	
	public boolean isSubclassOfPlugin(Class<?> theClass) {
		return Plugin.class.isAssignableFrom(theClass);
	}
	
	public List<Plugin> filesToPlugins(List<File> files) throws PluginException {
		List<Plugin> plugins = new ArrayList<Plugin>();
		for(File file : files) {
			plugins.add(fileToPlugin(file));
		}
		return plugins;
	}
	
	public Plugin fileToPlugin(File file) throws PluginException {
		Class<?> theClass = null;
		Plugin theInstance = null;
		
		theClass = getTheClass(file.getName());
		
		try {
			theInstance = (Plugin) theClass.newInstance();
		} catch (InstantiationException | IllegalAccessException | NoClassDefFoundError e) {
			throw new PluginException("Cannot convert " + file.getName() + " to plugin");
		}
		
		return theInstance;
	}

}
