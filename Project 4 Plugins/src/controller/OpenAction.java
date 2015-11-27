package controller;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import view.MainFrame;

public class OpenAction extends AbstractAction {

	public OpenAction() {
		super("Open");
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Text files (.txt)";
			}
			
			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".txt");
			}
		});
		
		int butonClicked = fileChooser.showOpenDialog(MainFrame.getInstance());
		if(butonClicked == JFileChooser.OPEN_DIALOG) {
			File selectedFile = fileChooser.getSelectedFile();
			BufferedReader fileReader = null;
			try {
				fileReader = new BufferedReader(new FileReader(selectedFile));
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			String line = "";
			MainFrame.getInstance().getEditor().setText("");
			try {
				while((line = fileReader.readLine()) != null) {
					MainFrame.getInstance().getEditor().append(line + "\n");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
}
