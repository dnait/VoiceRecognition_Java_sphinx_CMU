//  Created by Rao Xu
//  Copyright (c) 2014. All rights reserved.
package sphinxTTScalculator

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import com.sun.speech.freetts.*;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;



class Foo implements Runnable {
    	private volatile String value;
	 @Override
	 public void run() {
    		speech2text voicetotext=new speech2text();
			value=voicetotext.speaktotext(); 
		//value="BB";
		 
    	}

    	public String getValue() {
        	try {
            		Thread.sleep(4000);
          	} catch (InterruptedException e) {
            	// TODO Auto-generated catch block
        	 	e.printStackTrace();
             	}
        	return value;
      	}
}
public class myCalculator extends JFrame {
    	@SuppressWarnings("deprecation")
    	public static void main(String[] args) {
        	final Frame jf = new Frame("Calculator");
        	jf.setSize(390,412); 
        	jf.setResizable(false); 
	 	/*******textfile********/
        	jf.setLayout(null); 
        	final JTextField jt1 = new JTextField("");
        	jt1.setHorizontalAlignment(JTextField.RIGHT); 
        	jt1.setEditable(true); 
        	jt1.setBounds(15,55,360,60);
        	jf.add(jt1);
        
    		//0 button

        	final JButton b0 = new JButton("0");
        	b0.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
                		jt1.setText(jt1.getText()+"0");
            		}
        	});
		//1 button
		final JButton b1 = new JButton("1");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"1");
			}
		});
		//2 button
		final JButton b2 = new JButton("2");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"2");
			}
		});
		//3 button
		final JButton b3 = new JButton("3");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"3"); }
		});
		//4 button
		final JButton b4 = new JButton("4");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"4");
			}
		});
		//5 button
		final JButton b5 = new JButton("5");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"5");
			}
		});
		//6 button
		final JButton b6 = new JButton("6");
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"6");
			}
		});
		//7 button
		final JButton b7 = new JButton("7");
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"7");
			}
		});
		//8 button
		final JButton b8 = new JButton("8");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"8");
			}
		});
		//9 button
		final JButton b9 = new JButton("9");
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"9");
			}
		});
		//+ button
		final JButton badd = new JButton("+");
		badd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"+");
			}
		});
		//- button
		final JButton bminus = new JButton("-");
		bminus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"-");
			}
		});
		//x button
		final JButton bmulti = new JButton("*");
		bmulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"*");
			}
		});
		//^button
		final JButton bpower = new JButton("<html>x<SUP>y</SUP></html>");
		bpower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"^");
			}
		});
		//=tex button 
		final JButton bequaltex = new JButton("<html>=<SUP>Tex</SUP></html>");
		bequaltex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				myrpn myconvert=new myrpn();
				long jt2=myconvert.stringToArithmetic(jt1.getText());
				MyTex texconvert= new MyTex();
		    		String tex=texconvert.totex(jt1.getText());
				jt1.setText("$$"+jt1.getText()+"="+tex+"="+jt2+"$$");
			}
		});
		//"(" button
		final JButton bleft = new JButton("(");
		bleft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText(jt1.getText()+"(");
			}
		});
		//")" button
		final JButton bright = new JButton(")");
		bright.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	  			jt1.setText(jt1.getText()+")");
			}
		});
		//RadioButton
		final JRadioButton aradioButton= new JRadioButton("<html>VOICE<SUP>Mode</SUP></html>");
		aradioButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
	      			if (aradioButton.isSelected()) {
					String myString="VOICE ON";
					Foo foo = new Foo();
					new Thread(foo).start();
					String foovalue = foo.getValue();
					myString = myString+foovalue;
					//jt1.setText(myString);	
				}
			}
		});
		/*put two actions into two listeners and use periodicTask to track the update of the recognized words in the voice.text file. 
		 * (Not the best solution).But I dont know how to stop the periodicTack when "equal" command stops the voice recognition.
		 * If you have better ideas you can change actions here.
		*/
		aradioButton.addMouseListener(new MouseAdapter() {
	    		public void mouseReleased(MouseEvent e) {
	    			jt1.setText("Waiting 3 sec and Say: ( one | two | three... )");	
	    	
	    			//periodicTask, to update the voice.text file created by the voice recognition in real time.
	    			ScheduledThreadPoolExecutor sch = (ScheduledThreadPoolExecutor) 
			  	Executors.newScheduledThreadPool(3);
	            		Runnable periodicTask = new Runnable() {
		        		private Scanner scnr;
					@Override
		        		public void run() {
		        			try{  //scan file
		        		 		File text = new File("voice.txt");
		        	        		scnr = new Scanner(text);
		                			 //Reading each line of file using Scanner class
		                		  	int lineNumber = 1;
		                     	
		                     			while(scnr.hasNextLine()) {
		                         			String line = scnr.nextLine();
		                         			System.out.println("line " + lineNumber + " :" + line);
		                         			lineNumber++;
		                         			jt1.setText(line);
		                        		 }
		                			//Dont know how to stop the periodicTask when equation is over.
		                    			Thread.sleep(1 * 1000);
		        			 }catch(Exception e){
		        	 		 }
		            		}
			 	};
				ScheduledFuture<?> periodicFuture = sch.scheduleAtFixedRate(periodicTask, 1, 1, TimeUnit.SECONDS);
	        	}
		});




		final JRadioButton bradioButton= new JRadioButton("QUIT");
		bradioButton.addActionListener(new ActionListener(){ 
			//boolean isEqual=false;String mychar="\0"; boolean microstop=false;
			public void actionPerformed(ActionEvent e) {
 				System.exit(0);
			}
		});

		//Clear button
		final JButton bce = new JButton("CE");
		bce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText("");
			}
		});
		//Backspace button
		final JButton bback = new JButton("¡û");
		bback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message;
				int leng=jt1.getText().length();
				message=jt1.getText();
				if(leng==0) {
					JOptionPane.showMessageDialog(null, "No input. Try again!"); 
				} else {
					jt1.setText(message.substring(0,leng-1)); 
				}
			}
		});

		//=Speech button
		final JButton bequalspeech = new JButton("<html>=<SUP>Spe</SUP></html>");
		bequalspeech.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { 
				myrpn myconvert=new myrpn();
				String tf1=myrpn.infixToSuffix(jt1.getText());
				long jt2=myconvert.stringToArithmetic(jt1.getText());
				ttsspeech speak1=new ttsspeech();
				if (jt2>0) {
					speak1.speak("Your answer is:"+jt2);
				} else {
					speak1.speak("Your answer is: negative"+jt2);
				}
		 		jt1.setText(jt1.getText()+"="+tf1+"="+jt2);
			}
		});


		//layout
		b0.setBounds(15,343,67,52);
		b1.setBounds(15,285,67,52); 
		b2.setBounds(85,285,67,52);
		b3.setBounds(155,285,67,52);
		b4.setBounds(15,226,67,52);
		b5.setBounds(85,226,67,52);
		b6.setBounds(155,226,67,52);
		b7.setBounds(15,168,67,52);
		b8.setBounds(85,168,67,52);
		b9.setBounds(155,168,67,52);
		badd.setBounds(226,343,67,52);
		bminus.setBounds(226,285,67,52);
		bmulti.setBounds(226,226,67,52);
		bpower.setBounds(226,168,67,52);
		bequalspeech.setBounds(297,285,78,52);
		bequaltex.setBounds(297,343,78,52);
		bleft.setBounds(85,343,67,52);
		bright.setBounds(155,343,67,52);
		bce.setBounds(297,226,78,52);
		aradioButton.setBounds(15,124,139,37);
		aradioButton.setBackground(Color.white);
		bradioButton.setBounds(260,124,60,37);
		bradioButton.setBackground(Color.white);
		bback.setBounds(297,168,78,52);

		//component
		jf.add(b0);
		jf.add(b1);
		jf.add(b2);
		jf.add(b3);
		jf.add(b4);
		jf.add(b5);
		jf.add(b6);
		jf.add(b7);
		jf.add(b8);
		jf.add(b9);
		jf.add(badd);
		jf.add(bminus);
		jf.add(bmulti);
		jf.add(bpower);
		jf.add(bequaltex);
		jf.add(bleft);
		jf.add(bright);

		jf.add(bce);
		jf.add(bback);
		jf.add(bequalspeech);
		jf.add(aradioButton);
		jf.add(bradioButton);
	
		//Close
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} 
		}); 
		jf.show();
	}
}


