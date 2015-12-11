package pluginfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import model.pluginfinder.PluginFinder;
import observer.MockObserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Plugin finder class
 */
public class PluginFinderTest {
	
	/**
	 * The plugin test directory
	 */
	private static final String PLUGINS_DIRECTORY = "testdropins/plugins";
	
	/**
	 * The directory that contains the plugins to copy into the plugin directory to run the tests (the plugin in this directory should never be deleted !!) 
	 */
	private static final String PLUGINS_TOCOPY_DIRECTORY = "testdropins/pluginsToCopy";
	
	/**
	 * A plugin finder instance
	 */
	protected PluginFinder pluginFinder;
	
	/**
	 * A file representing the plugin test folder
	 */
	protected File pluginDirectory = new File(PLUGINS_DIRECTORY);
	
	/**
	 * A plugin finder observer
	 */
	protected MockObserver observer;
	
	/**
	 * Sets up some variables
	 */
	@Before
	public void setUp() {
		pluginFinder = new PluginFinder(PLUGINS_DIRECTORY);
		observer = new MockObserver();
		pluginFinder.addObserver(observer);
	}
	
	/**
	 * Copies a bunch of plugin files from the PLUGINS_TOCOPY_DIRECTORY directory to the PLUGINS_DIRECTORY directory (needed to do the tests
	 * @param files The files to copy
	 */
	private void copyPluginsToPluginPath(String... files) throws URISyntaxException, IOException {
		for(String file : files) {
			File plugin = new File(PLUGINS_TOCOPY_DIRECTORY + File.separator + file);
			if(plugin.exists()) {
				Files.copy(Paths.get(plugin.getAbsolutePath()), Paths.get(PLUGINS_DIRECTORY).resolve(plugin.getName()), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		
	}
	
	/**
	 * Tests creating a plugin finder with a null parameter
	 */
	@Test(expected=NullPointerException.class)
	public void nullParameterConstructorTest() {
		new PluginFinder(null);
	}
	
	/**
	 * Tests creating a plugin finder with an empty parameter 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void emptyParameterConstructorTest() {
		new PluginFinder("");
	}
	
	
	/**
	 * Tests getting plugins when no files are in the plugin directory 
	 */
	@Test
	public void getAllFilesFromEmptyFolderReturnEmptyListTest() {
		cleanPluginFolder();
		assertTrue(pluginFinder.getAllFiles().isEmpty());
	}
	
	/**
	 * Tests getting an empty arraylist when searching for an invalid filename
	 */
	@Test
	public void getAllFilesFromInvalidFolderReturnEmptyListTest() {
		pluginFinder = new PluginFinder("Bonjour");
		assertTrue(pluginFinder.getAllFiles().isEmpty());
	}
	
	/**
	 * Tests getting a list of plugin files
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void getAllFilesFromValidFolderTest() throws URISyntaxException, IOException {
		copyPluginsToPluginPath("MockedPlugin.class", "MockedInheritFromAbstractPlugin.class", "MockedAbstractPlugin.class");
		assertEquals(2, pluginFinder.getAllFiles().size());
	}
	
	/**
	 * Tests that when we add or delete plugins from plugin directory it detects it
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void addPluginsTest() throws URISyntaxException, IOException {
		assertEquals(0, observer.getNumberOfPlugins());
		copyPluginsToPluginPath("MockedPlugin.class"); //We add one valid plugin
		pluginFinder.actionPerformed(null);
		assertEquals(1, observer.getNumberOfPlugins());
		pluginFinder.actionPerformed(null); //for testing when plugins found are equals to plugins already found
		assertEquals(1, observer.getNumberOfPlugins());
		copyPluginsToPluginPath("MockedPlugin2.class");//We add another valid plugin
		pluginFinder.actionPerformed(null);
		assertEquals(2, observer.getNumberOfPlugins());
		copyPluginsToPluginPath("MockedAbstractPlugin.class", "MockEnum.class", "MockInterface.class"); // we add an abstract class, an enum and interface
		pluginFinder.actionPerformed(null);
		assertEquals(2, observer.getNumberOfPlugins()); //no changes
		copyPluginsToPluginPath("MockedAbstractPlugin.class", "MockedInheritFromAbstractPlugin.class"); // we add a plugin depending of an abstract plugin
		pluginFinder.actionPerformed(null);
		assertEquals(3, observer.getNumberOfPlugins());
		File pluginToDel = new File(PLUGINS_DIRECTORY + File.separator + "MockedPlugin.class");
		pluginToDel.delete();
		pluginFinder.actionPerformed(null);
		assertEquals(2, observer.getNumberOfPlugins());
		cleanPluginFolder(); //we clear
		pluginFinder.actionPerformed(null);
		assertEquals(0, observer.getNumberOfPlugins());
//		copyPluginsToPluginPath("MockedInheritFromAbstractPlugin.class"); // we add a plugin depending of an abstract plugin
//		pluginFinder.actionPerformed(null);
//		assertEquals(0, observer.getNumberOfPlugins()); //if there wasn't mockedAbstractPlugin.class but as we can't delete it, we can't do this...
		
	}
	
	/**
	 * Tests passing a null list of files when notifying the view
	 */
	@Test(expected=NullPointerException.class)
	public void notifyNullParameterTest() {
		pluginFinder.notify(null);
	}
	
	/**
	 * Cleans the plugin test directory
	 */
	private void cleanPluginFolder() {
		for(File f : pluginDirectory.listFiles()) {
			f.delete();
		}
	}
	
	/**
	 * Cleans after running a test
	 */
	@After
	public void clean() {
		cleanPluginFolder();
	}

}
