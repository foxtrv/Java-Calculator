/******************* 
Author: Trevor Fox
Date: 10/10/16
Assignment: Lab 5
********************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CalculatorFrame extends Frame implements ActionListener {
	CalculatorEngine engine;
	TextField display;
	public String cc = ""; // string to hold the expression on calculator
	WindowListener listener = new WindowAdapter() {
		public void windowClosing(WindowEvent e) { 
			System.exit(0); 
		}
   	};

CalculatorFrame(CalculatorEngine e) {
	super("Calculator");
	Panel top, bottom; Button b;
	engine = e;
	top = new Panel();
	top.add(display = new TextField(20));
	bottom = new Panel();
	bottom.setLayout(new GridLayout(6,4));
	bottom.add(b = new Button("1")); b.addActionListener(this);
	bottom.add(b = new Button("2")); b.addActionListener(this);
	bottom.add(b = new Button("3")); b.addActionListener(this);
	bottom.add(b = new Button("+")); b.addActionListener(this);
	bottom.add(b = new Button("4")); b.addActionListener(this);
	bottom.add(b = new Button("5")); b.addActionListener(this);
	bottom.add(b = new Button("6")); b.addActionListener(this);
	bottom.add(b = new Button("-")); b.addActionListener(this);
	bottom.add(b = new Button("7")); b.addActionListener(this);
	bottom.add(b = new Button("8")); b.addActionListener(this);
	bottom.add(b = new Button("9")); b.addActionListener(this);
	bottom.add(b = new Button("*")); b.addActionListener(this);
	bottom.add(b = new Button("C")); b.addActionListener(this);
	bottom.add(b = new Button("0")); b.addActionListener(this);
	bottom.add(b = new Button("=")); b.addActionListener(this);
	bottom.add(b = new Button("/")); b.addActionListener(this);
	bottom.add(b = new Button("(")); b.addActionListener(this);
	bottom.add(b = new Button(")")); b.addActionListener(this);
	bottom.add(b = new Button("M+")); b.addActionListener(this);
	bottom.add(b = new Button("MR")); b.addActionListener(this);
	bottom.add(b = new Button("MC")); b.addActionListener(this);
	bottom.add(b = new Button(".")); b.addActionListener(this);
	setLayout(new BorderLayout());
	add("North", top);
	add("South", bottom) ;
	addWindowListener(listener) ;
	setSize(300, 250) ;
	show();
}

public void actionPerformed(ActionEvent e) {
	char c = e.getActionCommand().charAt(0);
	if (c == '='){
		if (cc != ""){ // an empty string
			try {
				double x = engine.compute(cc);
				cc = ""+x; // set string to answer
				display.setText(cc);
			}
			catch (Exception ee){
				display.setText("SYNTAX ERROR");
			}
		}
	}

	else if (c == 'C'){
		cc = ""; // reset string
		display.setText(cc);
	}

	if (c == 'M'){
		char c2 = e.getActionCommand().charAt(1); 
		if (c2 == '+')
			engine.MPLUS(cc);
		if (c2 == 'R'){
			String k = engine.MR();
			cc = k;
			display.setText(cc);
		}
		if (c2 == 'C')	
			engine.MC();
	}

	if (c != '=' && c != 'C' && c != 'M'){
		cc = cc + c; // append char to string expression
		display.setText(cc);
	}
}

public static void main(String arg[]) {
	new CalculatorFrame(new CalculatorEngine());
 }
};


