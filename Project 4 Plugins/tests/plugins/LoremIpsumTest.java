package plugins;

import org.junit.Test;

public class LoremIpsumTest extends PluginTest {

	@Override
	public Plugin createPlugin() {
		return new LoremIpsumPlugin();
	}
	
	@Test
	public void transformTest() {
		super.transformTest("", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent aliquam, ipsum quis blandit auctor, ipsum neque elementum nibh, non sodales risus mi eget tellus. Pellentesque sit amet diam quis lacus lacinia luctus. Quisque eu mauris in eros ultricies pretium. Ut eget ipsum nec dolor venenatis venenatis a eu erat. Mauris ac malesuada elit. Vivamus hendrerit odio a metus luctus, a ultrices orci euismod. Aliquam aliquet justo et accumsan sodales. Ut ante diam, ornare eu sodales a, efficitur non sem. Cras lobortis ornare odio, tincidunt viverra sem porttitor sed. Mauris vel felis cursus, molestie elit a, aliquet tellus. Nulla molestie erat commodo, consectetur velit eget, faucibus velit. Curabitur eget porttitor eros, sit amet pellentesque nibh. Integer porttitor arcu quis est auctor porta. Morbi eu felis vel augue suscipit viverra quis sit amet nunc. Quisque in porta justo. Suspendisse eu ornare nisl, non sagittis purus."+
		"Nunc dignissim, eros at blandit tincidunt, risus tortor sollicitudin neque, pellentesque aliquet neque felis at nulla. Donec lacus turpis, mattis at faucibus ac, porta in libero. Sed malesuada mi in ante ornare cursus. Mauris vestibulum auctor interdum. Vestibulum eget vestibulum ligula, vitae lacinia dolor. Etiam euismod dictum orci, in tincidunt est mattis non. Sed sodales elementum placerat. Nullam tempus euismod lectus, non tincidunt libero suscipit ut. Aenean venenatis tristique laoreet. Etiam malesuada euismod eros, vitae tristique justo aliquet eu. Maecenas nec lacus vel sapien hendrerit lacinia non vel enim."+
		"Duis velit diam, egestas viverra tempus et, finibus sit amet mauris. Quisque accumsan efficitur semper. Ut at libero velit. Aenean tristique elementum leo, in euismod diam convallis eu. In ante erat, porta ut tincidunt sollicitudin, condimentum nec neque. Morbi aliquam arcu arcu, nec convallis magna dignissim ac. Vivamus mollis volutpat eros, ut sodales purus eleifend a."+
		"In eu nulla rhoncus, vulputate ante et, faucibus tellus. Vestibulum a leo vel lectus dignissim laoreet ut eget ipsum. Quisque purus leo, sagittis eget volutpat vitae, varius id enim. Sed in enim nec lectus bibendum porta. Sed egestas dui augue, lobortis sagittis mauris varius quis. Fusce ut quam a tellus imperdiet scelerisque. Aliquam erat volutpat."+
		"Cras congue mi non dolor finibus, at gravida eros iaculis. Nunc placerat sodales est nec rutrum. Suspendisse lobortis, sapien eu rutrum malesuada, purus purus congue eros, tempus vulputate massa urna nec nisi. Pellentesque sit amet dui dui. Maecenas sollicitudin hendrerit leo sit amet mattis. Donec malesuada facilisis erat ut malesuada. In scelerisque purus felis, quis sodales turpis maximus non. Suspendisse iaculis sem eu erat sagittis venenatis. In mollis, dui ac pellentesque mattis, lectus lectus fringilla nunc, sit amet blandit mi leo eu mauris. In hac habitasse platea dictumst. Praesent hendrerit augue ac lorem vehicula, ac dignissim sapien blandit. Praesent porta gravida auctor. Phasellus nec enim neque. Praesent eget viverra eros.");
	}
	
	@Test
	public void getLabel() {
		super.getLabelTest("Lorem Ipsum");
	}
}
