package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import plugins.Plugin;

/**
 * This class represents a menu of a plugin, containing the plugin it uses
 */
public class PluginMenuItem extends JMenuItem implements ActionListener {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4514222093702103516L;
	
	/**
	 * The associated plugin
	 */
	protected Plugin plugin;
	
	/**
	 * Constructor with the plugin to associate to the menu action
	 * @param plugin The plugin to associate to the menu action
	 */
	public PluginMenuItem(Plugin plugin) {
		super(plugin.getLabel());
		this.plugin = plugin;
		this.addActionListener(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = MainFrame.getInstance().getEditor().getText();
		String transformedText = plugin.transform(text);
		MainFrame.getInstance().getEditor().setText(transformedText);
	}
	
}
