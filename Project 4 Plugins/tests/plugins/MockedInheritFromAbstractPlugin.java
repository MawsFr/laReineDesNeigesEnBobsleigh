package plugins;

/**
 * A mocked plugin which inherits from an abstract plugin to tests THAT A PLUGIN THAT DEPENDS ON OTHER CLASSES ISN'T ADDED WHEN THE DEPENDANCES ARE NOT SATISFIED (here when the abstract class isn't added in the plugin folder with this plugin)
 */
public class MockedInheritFromAbstractPlugin extends MockedAbstractPlugin {

	/* (non-Javadoc)
	 * @see plugins.Plugin#transform(java.lang.String)
	 */
	@Override
	public String transform(String s) {
		return null;
	}

	/* (non-Javadoc)
	 * @see plugins.Plugin#getLabel()
	 */
	@Override
	public String getLabel() {
		return null;
	}

}
