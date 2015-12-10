package pluginfinder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import model.pluginfinder.PluginFilter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import plugins.Plugin;
import exceptions.PluginException;


public class PluginFilterTest {

	protected PluginFilter pluginFilter;

	@Before
	public void setUp() throws Exception {
		pluginFilter = new PluginFilter();
	}

	@Test
	public void classEndsWithClassTest() {
		assertFalse(pluginFilter.accept(null, "bonjour"));
	}

	@Test
	public void pluginNotInPluginPackageTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("MockedPlugin2.class"));
		pluginFilter.accept(null, "MockedPlugin2.class");
		assertEquals("MockedPlugin2.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}

	@Test
	public void invalidPluginNameTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("Bonjour.class"));
		pluginFilter.accept(null, "Bonjour.class");
		assertEquals("Bonjour.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}

	@Test
	public void abstractPluginTest() throws PluginException {
		assertTrue(pluginFilter.nameEndsWithClass("CesarCodePlugin.class"));
		assertNotNull(pluginFilter.getTheClass("CesarCodePlugin.class"));
		pluginFilter.accept(null, "CesarCodePlugin.class");
		assertEquals("CesarCodePlugin.class", pluginFilter.getNonLoadedPluginsList().get(0));
	}

	
//	//TODO : Pour ces tests il faut un mocked plugin dans plugins et avoir une autre interface Plugin
//	@Test
//	public void notSubclassOfPluginTest() throws PluginException {
//		assertTrue(pluginFilter.nameEndsWithClass("MockedPlugin.class"));
//		assertNotNull(pluginFilter.getTheClass("MockedPlugin.class"));
//		pluginFilter.accept(null, "MockedPlugin.class");
//		assertEquals("MockedPlugin.class", pluginFilter.getNonLoadedPluginsList().get(0));
//	}
	
//	@Test
//	public void fileToPluginTest() throws PluginException, URISyntaxException, FileNotFoundException {
////		File plugin = new File("./tests/plugins/MockedPlugin.java");
//		File plugin = new File(Thread.currentThread().getContextClassLoader().getResource("/tests/plugins/MockedPlugin.java").toURI());
//		Plugin mockedPlugin = pluginFilter.fileToPlugin(plugin);
//		assertNotNull(mockedPlugin);
//	}
	
	//Test on parameters
	
	@Test(expected = NullPointerException.class)
	public void hasDefaultConstructorWithNullParameterTest() {
		pluginFilter.hasDefaultConstructor(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void isAbstractOrInterfaceOrEnumWithNullParameterTest() {
		pluginFilter.isAbstractOrInterfaceOrEnum(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void hasCorrectMethodsWithNullParameterTest() {
		pluginFilter.hasCorrectMethods(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void nameEndsWithClassWithNullParameterTest() {
		pluginFilter.nameEndsWithClass(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nameEndsWithClassWithEmptyParameterTest() {
		pluginFilter.nameEndsWithClass("");
	}
	
	@Test(expected = NullPointerException.class)
	public void getTheClassWithNullNameParameterTest() throws NullPointerException, IllegalArgumentException, PluginException {
		pluginFilter.getTheClass(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getTheClassWithEmptyNameParameterTest() throws NullPointerException, IllegalArgumentException, PluginException {
		pluginFilter.getTheClass("");
	}
	
	@Test(expected = NullPointerException.class)
	public void isInPluginPackageWithNullParameterTest() throws NullPointerException, IllegalArgumentException, PluginException {
		pluginFilter.isInPluginPackage(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void isSubclassOfPluginWithNullParameterTest() throws NullPointerException, IllegalArgumentException, PluginException {
		pluginFilter.isSubclassOfPlugin(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void filesToPluginsWithNullParameterTest() throws NullPointerException, IllegalArgumentException, PluginException, FileNotFoundException {
		pluginFilter.filesToPlugins(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void filesToPluginsWithEmptyListParameterTest() throws NullPointerException, IllegalArgumentException, PluginException, FileNotFoundException {
		pluginFilter.filesToPlugins(new ArrayList<File>());
	}
	
	@Test(expected = NullPointerException.class)
	public void fileToPluginsWithNullParameterTest() throws NullPointerException, IllegalArgumentException, PluginException, FileNotFoundException {
		pluginFilter.fileToPlugin(null);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void fileToPluginsWithNonExistingFileParameterTest() throws NullPointerException, IllegalArgumentException, PluginException, FileNotFoundException {
		pluginFilter.fileToPlugin(new File("bonjour"));
	}
	
	
}
