package model.pluginfinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import plugins.Plugin;
import exceptions.PluginException;

/**
 * This represents a file filter which filters all valid .class than can be converted to Plugins
 *
 */
public class PluginFilter implements FilenameFilter{
	
	/**
	 * A list of invalid non loaded plugins
	 */
	protected List<String> nonChargedPluginsList;
	
	/**
	 * Default constructor
	 */
	public PluginFilter() {
		this.nonChargedPluginsList = new ArrayList<String>();
	}
	
	/* (non-Javadoc)
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(File dir, String name) {
		//DONE : verifier que le type de fichier est bien un .class
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
		//DONE: Test si la class n'est pas une interface ou une Enum ou une classe abstraite
		//DONE: test si la class est dans le package plugin
		//DONE: Verifier que la class a bien un constructeur par défaut
		//DONE : Verifier que la classe est une classe fille de plugins
		if(isAbstractOrInterfaceOrEnum(theClass)
				|| !isInPluginPackage(theClass)
				|| !isSubclassOfPlugin(theClass)
				|| !hasDefaultConstructor(theClass)
				|| !hasCorrectMethods(theClass)) {
			this.nonChargedPluginsList.add(name); //DONE : Faire des assert equals sur la size de la list des plugins invalides
			return false;
		}
		
		return true;
	}
	
	/**
	 * @return The list of invalid non loaded plugins
	 */
	public List<String> getNonLoadedPluginsList() {
		return nonChargedPluginsList;
	}

	/**
	 * Verifies if the class has a default constructor
	 * @param theClass The class to proccess
	 * @return true or false
	 * @throws NullPointerException When theClass parameter is null
	 */
	public boolean hasDefaultConstructor(Class<?> theClass) throws NullPointerException {
		if(theClass == null) {
			throw new NullPointerException("You must specify a non null theClass parameter");
		}
		//DONE : Tester que la list de class ne soit pas vide
		for(Constructor<?> constructor : theClass.getConstructors()) {
			if(constructor.getParameterCount() == 0) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Verifies if theClass is an abstract class or an enum or an interface
	 * @param theClass The class to proccess
	 * @return True or false
	 * @throws NullPointerException When theClass parameter is null
	 */
	public boolean isAbstractOrInterfaceOrEnum(Class<?> theClass) throws NullPointerException {
		//DONE : theClass non null
		if(theClass == null) {
			throw new NullPointerException("You must specify a non null theClass parameter");
		}
		return theClass.isInterface() || Modifier.isAbstract(theClass.getModifiers()) || theClass.isEnum();
	}
	
	/**
	 * Verifies if theClass has the correct methods (getLabel and transform)
	 * @param theClass The class to proccess
	 * @return True or false
	 * @throws NullPointerException If theClass parameter is null
	 */
	public boolean hasCorrectMethods(Class<?> theClass) throws NullPointerException {
		//DONE : Verifier si theClass different de null
		if(theClass == null) {
			throw new NullPointerException("You must specify a non null theClass parameter");
		}
		
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

	/**
	 * Verifies if the classname ends with .class
	 * @param fileName The class file name
	 * @return True or false
	 * @throws NullPointerException If the file name is null
	 * @throws IllegalArgumentException If the filename is empty
	 */
	public boolean nameEndsWithClass(String fileName) throws NullPointerException, IllegalArgumentException {
		//DONE : filename non null non empty
		if(fileName == null) {
			throw new NullPointerException("You must specify a non empty filename");
		}
		
		if(fileName.isEmpty()) {
			throw new IllegalArgumentException("You must specify a non empty filename");
		}
		return fileName.endsWith(".class");
		
	}
	
	/**
	 * Gets the class of the file passed in parameter 
	 * @param name The name of the file
	 * @return The class object
	 * @throws PluginException If the class is not found or isn't defined
	 * @throws NullPointerException If name is null
	 * @throws IllegalArgumentException if name is empty
	 */
	public Class<?> getTheClass(String name) throws PluginException, NullPointerException, IllegalArgumentException {
		//DONE : name non null, non empty
		if(name == null) {
			throw new NullPointerException("You must specify a non empty filename");
		}
		
		if(name.isEmpty()) {
			throw new IllegalArgumentException("You must specify a non empty filename");
		}
		
		Class<?> theClass = null;
		name = name.replaceFirst("\\.class", "");
		try {
			theClass = Class.forName(PluginFinder.PLUGINS_PACKAGE + "." + name);
		} catch (ClassNotFoundException | NoClassDefFoundError e) {
			throw new PluginException("Problem while trying to get the class of " + name);
		}
		
		return theClass;
	}
	
	/**
	 * Verifies if the the plugin is in the plugin package
	 * @param theClass The class to proccess
	 * @return true or false
	 * @throws NullPointerException If theClass parameter is null
	 */
	public boolean isInPluginPackage(Class<?> theClass) throws NullPointerException {
		//DONE : test theClass non null
		if(theClass == null) {
			throw new NullPointerException("You must specify a non null theClass parameter");
		}
		return theClass.getPackage().getName().equals(PluginFinder.PLUGINS_PACKAGE);
	}
	
	/**
	 * Verifies if the class is a subclass of plugin
	 * @param theClass The class to proccess
	 * @return true or false
	 * @throws NullPointerException If theClass parameter is null
	 */
	public boolean isSubclassOfPlugin(Class<?> theClass) throws NullPointerException {
		//DONE : the Class non null
		if(theClass == null) {
			throw new NullPointerException("You must specify a non null theClass parameter");
		}
		return Plugin.class.isAssignableFrom(theClass);
	}
	
	/**
	 * Converts a list of files to plugins object
	 * @param files The files to convert
	 * @return A list of plugins
	 * @throws PluginException If 
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws FileNotFoundException
	 */
	public List<Plugin> filesToPlugins(List<File> files) throws PluginException, NullPointerException, IllegalArgumentException, FileNotFoundException {
		if(files == null) {
			throw new NullPointerException("You must specify a non null files parameters");
		}
		
		//DONE : verifier que la list files ne sois pas null ou vide
		List<Plugin> plugins = new ArrayList<Plugin>();
		for(File file : files) {
			plugins.add(fileToPlugin(file));
		}
		return plugins;
	}
	
	/**
	 * Converts a file to a plugin
	 * @param file The file to convert
	 * @return The corresponding plugin 
	 * @throws PluginException If it failed to convert the file to a plugin
	 * @throws FileNotFoundException If the file is not found
	 * @throws NullPointerException If the file parameter is null
	 */
	public Plugin fileToPlugin(File file) throws PluginException, FileNotFoundException, NullPointerException {
		//DONE : Vérifier que file n'est pas null et existe
		//DONE : Verifier que le fichier est bien converti en plugin
		if(file == null) {
			throw new NullPointerException("You must specify a non null file parameters");
		}
		
		if(!file.exists()) {
			throw new FileNotFoundException("The file doesn't exists !");
		}
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
