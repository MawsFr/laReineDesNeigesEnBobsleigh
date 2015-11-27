/**
 * 
 */
package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import plugins.Plugin;

/**
 * @author erwan
 *
 */
public class MenuBar extends JMenuBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = -111659266189879540L;
	protected JMenu menuTools;
	
	public MenuBar() {
		this.menuTools = new JMenu("Tools");
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
