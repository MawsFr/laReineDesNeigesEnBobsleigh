package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import plugins.Plugin;

public class PluginMenuItem extends JMenuItem implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4514222093702103516L;
	
	protected Plugin plugin;
	
	public PluginMenuItem(Plugin plugin) {
		this.plugin = plugin;
		this.setName(plugin.getLabel());
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(plugin.transform("Bonjour 783K QSDJLK"));
	}
	
}
