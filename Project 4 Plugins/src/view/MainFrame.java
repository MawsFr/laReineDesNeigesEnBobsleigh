package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.observer.Observable;
import model.observer.Observer;
import model.pluginfinder.PluginFinder;
import plugins.Plugin;

/**
 * The main frame when displayed in gui mode
 */
public class MainFrame extends JFrame implements Observer<List<Plugin>> {

	/**
	 * A singleton of the program
	 */
	public static MainFrame instance;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2901605648414032427L;
	
	/**
	 * The default width constant   
	 */
	public static final int WIDTH = 480;
	
	/**
	 * The default height constant  
	 */
	public static final int HEIGHT = 680;
	
	/**
	 * The plugin finder 
	 */
	protected PluginFinder pluginFinder;
	
	/**
	 * The menu bar of this frame 
	 */
	protected MenuBar menuBar;
	
	/**
	 * The text area  
	 */
	protected JTextArea editor;
	
	/**
	 * The contentpane of the frame
	 */
	protected Container c;

	/**
	 * Default constructor
	 */
	private MainFrame(){
		super("Plugin's project");
		this.pluginFinder = new PluginFinder("dropins/plugins");
		pluginFinder.addObserver(this);
		c = getContentPane();
		c.setLayout(new BorderLayout());

		this.menuBar = new MenuBar();
		this.setJMenuBar(menuBar);

		this.editor = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(editor);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		c.add(scrollPane, BorderLayout.CENTER);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Stopping timer");
				try {
					pluginFinder.stop();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}


		});

		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(getPreferredSize());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}

	/**
	 * @return The editor
	 */
	public JTextArea getEditor() {
		return editor;
	}

	/**
	 * @return The plugin finder
	 */
	public PluginFinder getPluginFinder() {
		return pluginFinder;
	}

	/**
	 * @return The program instance
	 */
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}

		return instance;
	}

//	public void showError(String title, String message) {
//		JOptionPane.showMessageDialog(this,
//				message,
//				title,
//				JOptionPane.ERROR_MESSAGE);
//	} 
//
//	public void showWarning(String title, String message) {
//		JOptionPane.showMessageDialog(this,
//				message,
//				title,
//				JOptionPane.WARNING_MESSAGE);
//	}

	/* (non-Javadoc)
	 * @see model.observer.Observer#update(model.observer.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable<List<Plugin>> source, List<Plugin> newPlugins) {
		menuBar.removePluginsMenu();
		System.out.println("Modifications in plugins folder !");
		for(Plugin plugin: newPlugins){
			menuBar.createMenu(plugin);
			System.out.println("Added " + plugin.getLabel());
		}

	}

//	@Override
//	public void showMessage(String title, String message, int type) {
//		if(type == 0) {
//			showError(title, message);
//		} else {
//			showWarning(title, message);
//		}
//	}
}
