/**
 * 
 */
package view;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	protected JMenuItem menuTools;
	
	public MenuBar() {
		this.menuTools = new JMenuItem("Tools");
		this.add(menuTools);
	}
	
	public void createMenu(Plugin plugin){
		PluginMenuItem item = new PluginMenuItem(plugin);
		this.menuTools.add(item);
		menuTools.repaint();
		menuTools.revalidate();
		
	}	
	
	public void removePluginsMenu(){
		menuTools.removeAll();
	}
}
