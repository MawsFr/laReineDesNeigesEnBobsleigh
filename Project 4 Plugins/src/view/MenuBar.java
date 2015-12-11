/**
 * 
 */
package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import plugins.Plugin;
import controller.QuitAction;

/**
 * @author erwan
 *
 */
/**
 * This class represents the menu bar of the program
 */
public class MenuBar extends JMenuBar{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -111659266189879540L;
	
	/**
	 * The file menu
	 */
	protected JMenu menuFile;
	
	/**
	 * The tools menu 
	 */
	protected JMenu menuTools;
	
	/**
	 * The quit menu 
	 */
	protected JMenuItem menuQuit;
	
	/**
	 * Default constructor
	 */
	public MenuBar() {
		this.menuFile = new JMenu("File");
		this.menuTools = new JMenu("Tools");
		
		this.menuQuit = new JMenuItem(new QuitAction());
		this.menuFile.add(menuQuit);
		
		this.add(menuFile);
		this.add(menuTools);
	}
	
	/**
	 * Creates a menu representing a plugin
	 * @param plugin The plugin to add to the menu bar
	 */
	public void createMenu(Plugin plugin){
		PluginMenuItem item = new PluginMenuItem(plugin);
		this.menuTools.add(item);
		
	}	
	
	/**
	 * Removes all plugins
	 */
	public void removePluginsMenu(){
		menuTools.removeAll();
	}
}
