package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * This class is an action that quit the program
 */
public class QuitAction extends AbstractAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9209938156823648573L;

	/**
	 * Dafault Constructor
	 */
	public QuitAction() {
		super("Quit");
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
