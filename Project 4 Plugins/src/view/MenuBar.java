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
public class MenuBar extends JMenuBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = -111659266189879540L;
	protected JMenu menuFile;
	protected JMenu menuTools;
	protected JMenuItem menuQuit;
	
	public MenuBar() {
		this.menuFile = new JMenu("File");
		this.menuTools = new JMenu("Tools");
		
		this.menuQuit = new JMenuItem(new QuitAction());
		this.menuFile.add(menuQuit);
		
		this.add(menuFile);
		this.add(menuTools);
	}
	
	public void createMenu(Plugin plugin){
		PluginMenuItem item = new PluginMenuItem(plugin);
		this.menuTools.add(item);
		
	}	
	
	public void removePluginsMenu(){
		menuTools.removeAll();
	}
}
