package model.pluginfinder;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import plugins.Plugin;
import exceptions.PluginException;

public class PluginFilter implements FilenameFilter{
	
	protected List<String> nonChargedPluginsList;
	
	public PluginFilter() {
		this.nonChargedPluginsList = new ArrayList<String>();
	}
	
	@Override
	public boolean accept(File dir, String name) {
		//TODO: verifier que le type de fichier est bien un .class
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
		//TODO: Test si la class n'est pas un interface ou une Enum ou une classe abstraite
		//TODO: test si la class est dans le package plugin
		//TODO: Verifier que la class a bien un constructeur par défaut
		//TODO : Verifier que la classe est une classe fille de plugins
		if(isAbstractOrInterfaceOrEnum(theClass)
				|| !isInPluginPackage(theClass)
				|| !isSubclassOfPlugin(theClass)
				|| !hasDefaultConstructor(theClass)
				|| !hasCorrectMethods(theClass)) {
			this.nonChargedPluginsList.add(name); //TODO : Faire des assert equals sur la size de la list des plugins invalides
			return false;
		}
		
		return true;
	}
	
	public List<String> getNonLoadedPluginsList() {
		return nonChargedPluginsList;
	}

	public boolean hasDefaultConstructor(Class<?> theClass) {
		//Tester que la list de class ne soit pas vide
		for(Constructor<?> constructor : theClass.getConstructors()) {
			if(constructor.getParameterCount() == 0) {
				return true;
			}
		}
		
		return false;
	}

	public boolean isAbstractOrInterfaceOrEnum(Class<?> theClass) {
		//TODO : theClass non null
		return theClass.isInterface() || Modifier.isAbstract(theClass.getModifiers()) || theClass.isEnum();
	}
	
	public boolean hasCorrectMethods(Class<?> theClass) {
		//TODO : Verifier si theClass different de null
		Method getLabelMethod = null;
		Method transformMethod = null;
		
		try {
			getLabelMethod = theClass.getMethod("getLabel");
			transformMethod = theClass.getMethod("transform", String.class);
	 	} catch (NoSuchMethodException | SecurityException e) {
	 		return false;
	 	}
		
		if(!getLabelMethod.getReturnType().equals(String.class) || !transformMethod.getReturnType().equals(String.class)) {
			return false;
		}
		
		
		return true;
	}

	public boolean nameEndsWithClass(String fileName) {
		//TODO : filename non null non empty
		return fileName.endsWith(".class");
		
	}
	
	public Class<?> getTheClass(String name) throws PluginException {
		//TODO : name non null, non empty
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
		//TODO : test theClass non null
		return theClass.getPackage().getName().equals(PluginFinder.PLUGINS_PACKAGE);
	}
	
	public boolean isSubclassOfPlugin(Class<?> theClass) {
		//TODO : the CLass non null
		return Plugin.class.isAssignableFrom(theClass);
	}
	
	public List<Plugin> filesToPlugins(List<File> files) throws PluginException {
		//TODO : verifier que la list files ne sois pas null ou vide
		List<Plugin> plugins = new ArrayList<Plugin>();
		for(File file : files) {
			plugins.add(fileToPlugin(file));
		}
		return plugins;
	}
	
	public Plugin fileToPlugin(File file) throws PluginException {
		//TODO : Vérifier que file n'est pas null
		//TODO : Verifier que le fichier est bien converti en plugin
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
