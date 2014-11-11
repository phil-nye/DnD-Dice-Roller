/***************************************************************************************************
*	Copyright (c) 2012, Philip Cho Ni <philipni122@gmail.com>
*	All rights reserved.
*
*	Redistribution and use in source and binary forms, with or without
*	modification, are permitted provided that the following conditions are met:
*		* Redistributions of source code must retain the above copyright
*		  notice, this list of conditions and the following disclaimer.
*		* Redistributions in binary form must reproduce the above copyright
*		  notice, this list of conditions and the following disclaimer in the
*		  documentation and/or other materials provided with the distribution.
*		* Neither the name of the <organization> nor the
*		  names of its contributors may be used to endorse or promote products
*		  derived from this software without specific prior written permission.
*
*	THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
*	ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
*	WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
*	DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
*	DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
*	(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
*	LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
*	ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
*	(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
*	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*
*	Created by Philip C. Ni
*	Last Modified:	10/31/2014
*
*	NOTE:	Please read the included READ_ME.txt file attached with this program for instructions.
***************************************************************************************************/

import java.lang.Integer;
import java.lang.Exception.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dice{
	private JFrame win;
	private JTabbedPane t_Pane;
	private JPanel instructions;
	private JPanel attrPanel;
	private JPanel diePanel;
	
	//Objects for instructions tab
	private JTextArea instr_display;
	
	//Objects for the attribute roll
	private JTextArea attr_display;
	private JTextField attr_quantity;
	private JButton attr_roll;
	private JButton clear;
	
	//Objects for the die roll
	private JTextArea roll_display;
	private JTextField die_faces;
	private JTextField die_quantity;
	private JButton die_roll;
	private JLabel fLabel, qLabel;
	
	private static int die_quant = 0, attr_sets = 0, faces = 0, list = 0;
	
	public Dice(){
		prepareFrame();
	}
	
	private class attrListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Component source = (Component) e.getSource();
			String str = new String();
			attr_sets = 0;
			
			//When option is selected, sort numArr and display total with numArr contents
			if(source == attr_roll){
					//Clear the text area module
						//attr_display.setText(null);
					
					//System.out.print("attr_roll button pressed:\t");
					str = attr_quantity.getText();
					try{
						attr_sets = Integer.parseInt(str);
						if(attr_sets < 0)	attr_sets *= -1;
						//System.out.println(attr_sets);
						attr_display.append("####################\n\nNumber of sets: " + str + "\n\n");
						roll4DropLowest();
					}
					catch(NumberFormatException nfe){
						attr_display.append("Runtime ERROR: JAVA NumberFormatException\n");
					}
			}
			if(source == clear){
				//System.out.println("clear display...");
				attr_display.setText(null);
			}
		}
	}
	
	private void prepareFrame(){
		//Setting up the window frame
		win = new JFrame("Virtual Dice Roller");
		win.setSize(400, 600);//(width, height)
		win.setResizable(false);
		win.setBackground(Color.white);
		win.setLayout(new BorderLayout());
		
		//does the same thing as "win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);"
		win.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent winEvent){
				System.exit(0);
			}
		});
		
		//Create tabbed pane
		t_Pane = new JTabbedPane();
		
		//Preparing Tabs
		prepareInstructionsTab();
		prepareAttrTab();
		prepareDieTab();
		
		//Add tabs to window frame
		t_Pane.addTab("Instructions", instructions);
		t_Pane.addTab("Attributes", attrPanel);
		t_Pane.addTab("Dice", diePanel);
		win.add(t_Pane);
		
		//Make the window frame visible
		win.setLocationRelativeTo(null);
		win.setVisible(true);
	}
	
	private void prepareInstructionsTab(){
		//Initialize instructions panel
		instructions = new JPanel();
		instructions.setLayout(new BorderLayout());
		instructions.setBackground(Color.white);
		
		JPanel instr = new JPanel();
		instructions.add(instr, BorderLayout.CENTER);
		
		//Add text area for instructions
		instr_display = new JTextArea(30, 34);
		instr_display.setLineWrap(true);
		instr_display.setWrapStyleWord(true);
		//instr_display.setEditable(true);					//Allows for debug of JTextArea
		instr_display.setEditable(false);
			//add usage instructions here
			instr_display.setText("\t              ###################\n");
			instr_display.append("\t              VIRTUAL DICE ROLLER\n");
			instr_display.append("\t              ###################\n");
			instr_display.append("\nPlease read the READ_ME.txt for important notes.\n");
			printInstructions();
		instr.setLayout(new FlowLayout());
		
		JScrollPane scrollPane = new JScrollPane(instr_display);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		instr.add(scrollPane);
		
		//Initialize button panel
		JPanel buttonArea = new JPanel();
		buttonArea.setLayout(new FlowLayout());
		instructions.add(buttonArea, BorderLayout.SOUTH);
		
		//cancel button
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actEvent){
				System.exit(0);
			}
		});
		buttonArea.add(cancel);
	}
	
	private void roll4DropLowest(){
		int numAttr = 6, numDie = 4;
		int value = 0, rndNum;
		int numArr[] = new int[numDie];
		Random rnd = new Random();
		
		for(int i = 0; i < attr_sets; i++){
			attr_display.append(Integer.toString(i + 1) + ")\n");
			for(int j = 0; j < numAttr; j++){
				value = 0;
				attr_display.append("     {");
				for(int k = 0; k < numDie; k++){
					rndNum = rnd.nextInt();
					if(rndNum < 0)	rndNum *= -1;
					numArr[k] = (rndNum % 6) + 1;
					attr_display.append(Integer.toString(numArr[k]));
					if(k != numDie - 1)	attr_display.append(", ");
				}
				attr_display.append("}  ");
				Arrays.sort(numArr);
				for(int k = 1; k < numArr.length; k++){
					value += numArr[k];
				}
				attr_display.append("\tValue:" + Integer.toString(value) + "\n");
			}
			attr_display.append("\n");
		}
	}
	
	private void prepareAttrTab(){
		//Setting up tab panel
		attrPanel = new JPanel();
		//attrPanel.setLayout(new GridLayout(3, 1, 1, 3));
		attrPanel.setLayout(new BorderLayout());
		attrPanel.setBackground(Color.white);
		
		//Initializing the attribute text area
		JPanel displayPan = new JPanel();
		displayPan.setLayout(new FlowLayout());
		attrPanel.add(displayPan, BorderLayout.CENTER);
		
		attr_display = new JTextArea(27, 34);
		attr_display.setEditable(false);
		attr_display.setLineWrap(true);
		attr_display.setWrapStyleWord(true);
		
		//Initialize buttons
		attr_roll = new JButton("Roll Attribute");
		JButton cancel = new JButton("Cancel");
		clear = new JButton("Clear Entries");
		
		//Add attr_roll button and quantity textfield and quantity label
		JPanel northPan = new JPanel();			//center panel for quantity stuff
		northPan.setLayout(new FlowLayout());
		attrPanel.add(northPan, BorderLayout.NORTH);
		
		JLabel qLabel = new JLabel("# of sets to roll (int): ");
		northPan.add(qLabel);
		attr_quantity = new JTextField(10);
		attr_quantity.setText("0");
		attr_quantity.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String str = new String();
				attr_sets = 0;
				str = attr_quantity.getText();
				try{
					attr_sets = Integer.parseInt(str);
					if(attr_sets < 0)	attr_sets *= -1;
					attr_display.append("####################\n\nNumber of sets: " + str + "\n\n");
					roll4DropLowest();
				}
				catch(NumberFormatException nfe){
					attr_display.append("Runtime ERROR: JAVA NumberFormatException\n");
				}
			}
		});
		northPan.add(attr_quantity);
		
		//Add action listener to attr_roll button
		attr_roll.addActionListener(new attrListener());
		northPan.add(attr_roll);
		
		//Create button area panel for bottom panel
		JPanel buttonArea = new JPanel();
		buttonArea.setLayout(new FlowLayout());
		attrPanel.add(buttonArea, BorderLayout.SOUTH);
		
		//Add clear button
		clear.addActionListener(new attrListener());
		buttonArea.add(clear);
		
		//Add cancel button
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actEvent){
				System.exit(0);
			}
		});
		buttonArea.add(cancel);
		
		//Add text area for attribute roll outputs
		JScrollPane scrollPane = new JScrollPane(attr_display);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		displayPan.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void rollDie(){
		int value = 0, roll = 0;
		Random rand = new Random();
		
		roll_display.append("     {");
		for(int i = 0; i < die_quant; i++){
			roll = rand.nextInt();
			if(roll < 0)	roll *= -1;
			roll = (roll % faces) + 1;
			roll_display.append(Integer.toString(roll));
			if(i != die_quant - 1)	roll_display.append(", ");
			value += roll;
		}
		roll_display.append("}\n     Sum: " + Integer.toString(value) + "\n\n");
	}
	
	private void prepareDieTab(){
		//Setting up tab panel
		diePanel = new JPanel();
		diePanel.setLayout(new BorderLayout());
		
		//Create button area panel for the bottom section
		JPanel buttonArea = new JPanel();
		buttonArea.setLayout(new FlowLayout());
		diePanel.add(buttonArea, BorderLayout.SOUTH);
		
		//Initialize and add sub-panels
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());
		diePanel.add(pan, BorderLayout.CENTER);
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		pan.add(top, BorderLayout.SOUTH);
		
		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());
		pan.add(center, BorderLayout.CENTER);
		
		//Add text area for the center section
		roll_display = new JTextArea(28, 34);
		roll_display.setLineWrap(true);
		roll_display.setWrapStyleWord(true);
		roll_display.setEditable(false);			//for debug purposes
		JScrollPane scrollableTextArea = new JScrollPane(roll_display);
		scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		center.add(scrollableTextArea);
		
		//Add labels and text fields
		fLabel = new JLabel("# of faces: ");
		top.add(fLabel);
		
		die_faces = new JTextField(4);
		die_faces.setText("0");
		die_faces.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae1){
				try{
					list += 1;
					die_quant = Integer.parseInt(die_quantity.getText());
					if(die_quant < 0)	die_quant *= -1;
					faces = Integer.parseInt(die_faces.getText());
					if(faces < 0)	faces *= -1;
					roll_display.append(Integer.toString(list) + ")\n");
					rollDie();
				}
				catch(NumberFormatException ne){
					attr_display.append("Runtime ERROR: JAVA NumberFormatException\n");
				}
			}
		});
		top.add(die_faces);
		
		qLabel = new JLabel("           # to roll: ");
		top.add(qLabel);
		
		die_quantity = new JTextField(4);
		die_quantity.setText("0");
		die_quantity.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae2){
				try{
					list += 1;
					die_quant = Integer.parseInt(die_quantity.getText());
					if(die_quant < 0)	die_quant *= -1;
					faces = Integer.parseInt(die_faces.getText());
					if(faces < 0)	faces *= -1;
					roll_display.append(Integer.toString(list) + ")\n");
					rollDie();
				}
				catch(NumberFormatException ne){
					attr_display.append("Runtime ERROR: JAVA NumberFormatException\n");
				}
			}
		});
		top.add(die_quantity);
		
		//Add roll die button
		die_roll = new JButton("Roll Dice");
		die_roll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae1){
				try{
					list += 1;
					die_quant = Integer.parseInt(die_quantity.getText());
					if(die_quant < 0)	die_quant *= -1;
					faces = Integer.parseInt(die_faces.getText());
					if(faces < 0)	faces *= -1;
					roll_display.append(Integer.toString(list) + ")\n");
					rollDie();
				}
				catch(NumberFormatException ne){
					attr_display.append("Runtime ERROR: JAVA NumberFormatException\n");
				}
			}
		});
		buttonArea.add(die_roll);
		
		//Add clear button
		JButton clear = new JButton("Clear Entries");
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae2){
				roll_display.setText(null);
				list = 0;
			}
		});
		buttonArea.add(clear);

		//Add cancel button
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actEvent){
				System.exit(0);
			}
		});
		buttonArea.add(cancel);
	}
	
	private void printInstructions(){
		//Attribute tab instructions:
		instr_display.append("\n#####################################################\n");
		instr_display.append("\nAttributes: \n");
		instr_display.append("     Click on the \"Attributes\" tab from the tab pane above to roll for the attributes. ");
		instr_display.append("This function will roll 4d6 and return the value of all four rolls and the sum of the three highest rolls for the six attributes ");
		instr_display.append("[Str, Dex, Con, Wis, Int, Cha]. Of course you don't have to take these skills in any particular order.\n\n");
		instr_display.append("     In order to begin rolling, you must specify the number of sets to roll in the \"# of sets to roll:\" text field. ");
		instr_display.append("Then, hit the [ENTER] key or press the \"Roll Attribute\" button to generate your attributes. ");
		instr_display.append("Please note that this value must be a positive integer greater than 0 (zero) and less than or equal to 32,767.\n");
		
		//Dice tab instructions:
		instr_display.append("\n#####################################################\n");
		instr_display.append("\nDice: \n");
		instr_display.append("     Click on the \"Dice\" tab from the tab pane above to roll an \'n\'-sided die \'m\' times. The \'m\' number of rolls and the sum of the rolls will be displayed on the screen.\n\n");
		instr_display.append("     In order to begin rolling, enter the number of faces you want on your die in the \"# of faces:\" text field. ");
		instr_display.append("Then, enter the number of times you want to roll this die in the \"# to roll:\" text field. This is the \'n\' value. ");
		instr_display.append("Finally, hit the [ENTER] key or press the \"Roll Dice\" button to roll the die. This is the \'m\' ");
		instr_display.append("Please note that all values entered into the text fields must be positive integers greater than 0 (zero) and less than or equal to 32, 767.\n");
	}
	
	public static void main(String args[]){
		Dice dice = new Dice();
	}
}