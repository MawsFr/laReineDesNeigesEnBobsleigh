package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class QuitAction extends AbstractAction {

	public QuitAction() {
		super("Quit");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
