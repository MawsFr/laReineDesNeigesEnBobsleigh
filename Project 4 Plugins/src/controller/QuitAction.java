package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class QuitAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9209938156823648573L;

	public QuitAction() {
		super("Quit");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
