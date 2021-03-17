package com.ksutarlim.randnamegen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ksutarlim.randnamegen.generator.Generator;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Random Name Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(600,200);
    	
    	// Create ListModel
    	DefaultListModel<String> savedNames = new DefaultListModel<String>();
    	
    	// Create panel
    	JPanel buttons = new JPanel();
    	buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
    	JButton generateButton = new JButton("Generate");
    	JButton copyButton = new JButton("Copy to Clipboard");
    	JButton saveButton = new JButton("Save to list");
    	generateButton.setAlignmentX((float) 0.5);
    	copyButton.setAlignmentX((float) 0.5);
    	saveButton.setAlignmentX((float) 0.5);
    	copyButton.setEnabled(false);
    	saveButton.setEnabled(false);
    	buttons.add(generateButton);
    	buttons.add(copyButton);
    	buttons.add(saveButton);
    	
    	JPanel generatorPanel = new JPanel();
    	generatorPanel.setLayout(new BoxLayout(generatorPanel, BoxLayout.PAGE_AXIS));
    	
    	JLabel generatedName = new JLabel("Click Generate to create a new name");
    	generatedName.setBorder(new EmptyBorder(20,20,20,20));
    	generatedName.setAlignmentX((float) 0.5);
    	
    	// Create 
    	generateButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e){  
                Generator generator = new Generator();
                generator.loadData();
                generatedName.setText(generator.generate());
    			copyButton.setEnabled(true);
    			saveButton.setEnabled(true);
            }
    	});
    	copyButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e){  
    			StringSelection stringSelection = new StringSelection(generatedName.getText());
    			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    			clipboard.setContents(stringSelection, null);
            }
    	});
    	saveButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e){  
    			savedNames.addElement(generatedName.getText());
    			saveButton.setEnabled(false);
            }
    	});
    	
    	generatorPanel.add(generatedName);
    	generatorPanel.add(buttons);
    	
    	// Create list
    	JPanel savedNamesPanel = new JPanel();
    	savedNamesPanel.setLayout(new BoxLayout(savedNamesPanel, BoxLayout.PAGE_AXIS));
    	
    	JList<String> list = new JList<String>(savedNames);
    	list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    	list.setLayoutOrientation(JList.VERTICAL_WRAP);
    	list.setVisibleRowCount(-1);
    	
    	JScrollPane listScroller = new JScrollPane(list);
    	listScroller.setPreferredSize(new Dimension(200, 50));
    	
    	JLabel savedLabel = new JLabel("Saved names");
    	savedLabel.setBorder(new EmptyBorder(20,0,5,0));
    	
    	JButton exportListButton = new JButton("Export to txt");
    	exportListButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e){  
    			try {
    		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    		    }catch(Exception ex) {
    		        ex.printStackTrace();
    		    }
    			JFileChooser fileChooser = new JFileChooser();
    			fileChooser.setDialogTitle("Export names");
    			fileChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
    			int userSelection = fileChooser.showSaveDialog(frame);
    			if (userSelection == JFileChooser.APPROVE_OPTION) {
    			    File fileToSave = fileChooser.getSelectedFile();
    			    if (!fileToSave.getName().endsWith(".txt")) {
    			    	String chosenFilePath = fileToSave.getAbsolutePath();
    			    	fileToSave = new File(chosenFilePath+".txt");
    			    }
					try {
						FileWriter exportWriter = new FileWriter(fileToSave);
						for (int i=0; i<savedNames.size(); i++) {    			    	
	    			    	try {
								exportWriter.write(savedNames.elementAt(i) + "\n");
							} catch (IOException ioException) {
								ioException.printStackTrace();
							}
	    			    }
						exportWriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    			    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    			}
            }
    	});
    	
    	savedNamesPanel.add(savedLabel);
    	savedNamesPanel.add(listScroller);
    	savedNamesPanel.add(exportListButton);
    	
    	// Create main panel
    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(generatorPanel, BorderLayout.CENTER);
    	mainPanel.add(savedNamesPanel, BorderLayout.LINE_END);
    	
    	frame.add(mainPanel);
    	frame.setVisible(true);
	}

}
