package model.pluginfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.observer.Observable;
import plugins.Plugin;
import exceptions.PluginException;

/**
 * This class represents plugin researcher. It loads the files in the plugin directory and converts them into plugins thanks to the plugin filter 
 */
public class PluginFinder extends Observable<List<Plugin>> implements ActionListener {
	
	/**
	 * Constants storing the default plugins folder
	 */
	public static final String DEFAULT_PLUGINS_PATH = "dropins/plugins";
	
	/**
	 * Constants storing the plugin package
	 */
	public static final String PLUGINS_PACKAGE = Plugin.class.getPackage().getName();
	
	/**
	 * The timer that perform this plugin finder's action every tick
	 */
	protected ExtendedTimer timer;
	
	/**
	 * The list of plugins 
	 */
	protected List<File> plugins;
	
	/**
	 * The plugin filter that filters plugin file type
	 */
	protected PluginFilter pluginFilter;
	
	/**
	 * The plugin directory where this plugin finder will search for the plugin to load 
	 */
	protected String pluginDirectory;
	
	/**
	 * Constructor with the plugin directory
	 * @param pluginDirectory The plugin directory where this plugin finder will search for the plugin to load
	 */
	public PluginFinder(String pluginDirectory) {
		//DONE : verifier pluginDirectory non null et non empty
		if(pluginDirectory ==null){
			throw new NullPointerException();
		}
		
		if(pluginDirectory.isEmpty()){
			throw new IllegalArgumentException();
		}
		
		this.pluginDirectory = pluginDirectory;
		this.pluginFilter = new PluginFilter();
		timer = new ExtendedTimer(this);
		this.plugins = new ArrayList<File>();
		
	}
	
	/**
	 * Default constructor
	 */
	public PluginFinder() {
		this(DEFAULT_PLUGINS_PATH);
	}
	
	/**
	 * Starts the timer
	 */
	public void start() {
		timer.start();
	}
	
	/**
	 * Stops the timer
	 * @throws Exception If it is already stopped
	 */
	public void stop() throws Exception {
		timer.stop();
	}
	
	/**
	 * Gets all files in the plugin drectory
	 * @return A list of plugin files
	 */
	public List<File> getAllFiles() {
		File dir = new File(pluginDirectory);
		File[] files = dir.listFiles(pluginFilter);
		
		if(files == null || files.length == 0) {
			//DONE : Verifier que l'on a bien une liste vide quand on a pas de fichier dans le dossier
			return new ArrayList<File>();
		}
		
		return new ArrayList<File>(Arrays.asList(files));
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		searchForNewPlugins();
		
//		if(!pluginFilter.getNonLoadedPluginsList().isEmpty()) {
//			String pluginsNotLoaded = "";
//			Path dropinsPath = null;
//			try {
//				dropinsPath = Paths.get(pluginDirectory);
//				dropinsPath = dropinsPath.getParent();
//				pluginsNotLoaded = moveInvalidPlugin(pluginsNotLoaded, dropinsPath);
//			} catch(IllegalArgumentException | IOException e1) {
//				error("Error while moving invalid plugins", e1.getMessage());
//				e1.printStackTrace();
//			}
//			
//			
//			
//			warning(
//				    "Didn't manage to load all plugins, please verify if there are all files needed for some of them.\n"
//			+ "Plugins not loaded (moved to directory : " + dropinsPath + ") :\n" + pluginsNotLoaded, "Plugin loading semi-failed");
//			
//			pluginFilter.getNonLoadedPluginsList().clear();
//		}
		
	}

	/**
	 * Searches for new plugins 
	 */
	public synchronized void searchForNewPlugins() {
		List<File> newPlugins = getAllFiles();
		
		if(!(newPlugins.equals(this.plugins))) {
			//DONE : Faire un gros test qui test que quand on ajoute un plugin dans le dossier ca modifie bien la liste "plugins"
			this.plugins = newPlugins;
			notify(plugins);
		}
	}

//	/**
//	 * Moves invalid plugin into the parent directory of the plugin directory BUT removed because if we put a plugin like cesarCode it will automatically move it BUT we need it for CesarCode1 ans 13 to work
//	 */
//	public String moveInvalidPlugin(String pluginsNotLoaded, Path dropinsPath) throws InvalidPathException, FileNotFoundException, IOException {
//		//DONE : Verification sur les parametre non null et non empty pour les string
//		
//		if(pluginsNotLoaded == null || dropinsPath == null){
//			throw new NullPointerException();
//		}
//		
//		Path sourcePath = null;
//		for(String pluginName : pluginFilter.getNonLoadedPluginsList()) {
//			pluginsNotLoaded += pluginName + "\n";
//			File file = new File(pluginDirectory + File.separator + pluginName);
//			if(!file.exists()) {
//				throw new FileNotFoundException("The file " + pluginName + " has disapeared");
//			}
//			
//			try {
//				sourcePath = Paths.get(file.getAbsolutePath());
//			} catch(InvalidPathException e) {
//				throw e;
//			}
//			try {
//				Files.move(sourcePath, dropinsPath.resolve(sourcePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
//			} catch (IOException e1) {
//				throw e1;
//			}
//			
//		}
//		return pluginsNotLoaded;
//	}
	
	/**
	 * Creates a list of plugins and notifies observers
	 * @param files The list of plugin files
	 */
	public synchronized void notify(List<File> files) {
		if(files == null){
			throw new NullPointerException();
		}
		
		//DONE : verifier files non null
		List<Plugin> pluginsFinded = null;
		try {
			pluginsFinded = pluginFilter.filesToPlugins(files);
		} catch (PluginException 
				| NullPointerException 
				| IllegalArgumentException 
				| FileNotFoundException e) {
			System.out.println("Error while converting files to plugins");
//			e.printStackTrace();
		}
		
		notifyObservers(pluginsFinded);
	}
	

}
