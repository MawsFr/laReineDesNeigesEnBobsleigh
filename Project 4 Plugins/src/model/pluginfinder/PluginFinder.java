package model.pluginfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.PluginException;
import model.observer.Observable;
import plugins.Plugin;
import view.MainFrame;

public class PluginFinder extends Observable<List<Plugin>> implements ActionListener {
	
	public static final String DEFAULT_PLUGINS_PATH = "dropins/plugins";
	public static final String PLUGINS_PACKAGE = Plugin.class.getPackage().getName();
	
	protected ExtendedTimer timer;
	protected List<File> plugins;
	protected PluginFilter pluginFilter;
	protected String pluginDirectory;
	
	public PluginFinder(String pluginDirectory) {
		//TODO : verifier pluginDirectory non null et non empty
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
		System.out.println(this.pluginDirectory);
		
	}
	
	public PluginFinder() {
		this(DEFAULT_PLUGINS_PATH);
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() throws Exception {
		timer.stop();
	}
	
	public List<File> getAllFiles() {
		File dir = new File(pluginDirectory);
		File[] files = dir.listFiles(pluginFilter);
		
		if(files == null || files.length == 0) {
			//TODO : Verifier que l'on a bien une liste vide quand on a pas de fichier dans le dossier
			return new ArrayList<File>();
		}
		
		return new ArrayList<File>(Arrays.asList(files));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<File> newPlugins = getAllFiles();
		
		if(!(newPlugins.equals(this.plugins))) {
			//TODO : Faire un gros test qui test que quand on ajoute un plugin dans le dossier ca modifie bien la liste "plugins"
			this.plugins = newPlugins;
			notify(plugins);
		}
		if(!pluginFilter.getNonLoadedPluginsList().isEmpty()) {
			String pluginsNotLoaded = "";
			Path dropinsPath = null;
			try {
				dropinsPath = Paths.get(pluginDirectory);
				dropinsPath = dropinsPath.getParent();
				pluginsNotLoaded = moveInvalidPlugin(pluginsNotLoaded, dropinsPath);
			} catch(IllegalArgumentException | IOException e1) {
				MainFrame.getInstance().showError("Error while moving invalid plugins", e1.getMessage());
			}
			
			
			
			MainFrame.getInstance().showWarning(
				    "Didn't manage to load all plugins, please verify if there are all files needed for some of them.\n"
			+ "Plugins not loaded (moved to directory : " + dropinsPath + ") :\n" + pluginsNotLoaded, "Plugin loading semi-failed");
			
			pluginFilter.getNonLoadedPluginsList().clear();
		}
		
	}

	public String moveInvalidPlugin(String pluginsNotLoaded, Path dropinsPath) throws InvalidPathException, FileNotFoundException, IOException {
		//TODO : Verification sur les parametre non null et non empty pour les string
		
		if(pluginsNotLoaded == null ||dropinsPath == null){
			throw new NullPointerException();
		}
		if(pluginsNotLoaded.isEmpty()){
			throw new IllegalArgumentException();
		}
		
		Path sourcePath = null;
		for(String pluginName : pluginFilter.getNonLoadedPluginsList()) {
			pluginsNotLoaded += pluginName + "\n";
			File file = new File(pluginDirectory + File.separator + pluginName);
			if(!file.exists()) {
				throw new FileNotFoundException("The file " + pluginName + " has disapeared");
			}
			
			try {
				sourcePath = Paths.get(file.getAbsolutePath());
			} catch(InvalidPathException e) {
				throw e;
			}
			try {
				Files.move(sourcePath, dropinsPath.resolve(sourcePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				throw e1;
			}
			
		}
		return pluginsNotLoaded;
	}
	
	public void notify(List<File> files) {
		if(files == null){
			throw new NullPointerException();
		}
		//TODO : verifier files non null
		List<Plugin> pluginsFinded = null;
		try {
			pluginsFinded = pluginFilter.filesToPlugins(files);
		} catch (PluginException 
				| NullPointerException 
				| IllegalArgumentException 
				| FileNotFoundException e) {
			e.printStackTrace();
		}
		
		notifyObservers(pluginsFinded);
	}

}
