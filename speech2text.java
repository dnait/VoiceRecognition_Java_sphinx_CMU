//  Created by Rao Xu
//  Copyright (c) 2014. All rights reserved.
package sphinxTTScalculator

import java.io.FileWriter;
import java.io.IOException;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

import java.io.File;
import java.io.BufferedWriter;




public class speech2text {
	public String speaktotext(){
		ttsspeech speak2=new ttsspeech();
		speak2.speak("This is Kavin, please tell me your expression.");


		//System.out.println(mychar1);
        	String resultText;
		ConfigurationManager cm;
		cm = new ConfigurationManager(speech2text.class.getResource("myword.config.xml"));
		Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
		recognizer.allocate();

		// start the microphone or exit if the programm if this is not possible
		Microphone microphone = (Microphone) cm.lookup("microphone");
		if (!microphone.startRecording()) {
		    	System.out.println("Cannot start microphone.");
		    	recognizer.deallocate();
		    	System.exit(1);
		}

		//System.out.println("Waiting 5' and Say: ( one | two | three... )");
		ttsspeech speak1=new ttsspeech();
		//speak1.speak("Please tell me the expression");
		//String mystring="5";// loop the recognition until the programm exits.
		String mychar="";
		for (int i=0;i<100;i++){
			Result result = recognizer.recognize();
			resultText = result.getBestFinalResultNoFiller(); 
			if (result != null && resultText !="equal") {
		        //resultText = result.getBestFinalResultNoFiller(); 
				switch (resultText){
			  		case "zero":
			 			resultText="0";mychar=mychar+resultText;
			  			break;
			  		case "one":
		        			resultText="1";mychar=mychar+resultText;
		        			break;
			  		case "two":
		        			resultText="2";mychar=mychar+resultText;
		        			break;
			 		case "three":
				        	resultText="3";mychar=mychar+resultText;
				        	break;
					case "four":
				        	resultText="4";mychar=mychar+resultText;
				        	break;
				        case "five":
				        	resultText="5";mychar=mychar+resultText;
				        	break;
					case "six":
					    	resultText="6";mychar=mychar+resultText;
					      	break;
					case "seven":
					       	resultText="7";mychar=mychar+resultText;
				        	break;
				      	case "eight":
		        			resultText="8";mychar=mychar+resultText;
		        			break;
					case "nine":
		        			resultText="9";mychar=mychar+resultText;
		        			break;
		        		case "add":
		        			resultText="+";mychar=mychar+resultText;
		        			break;
				      	case "minus":
					     	resultText="-";mychar=mychar+resultText;
				     	case "multiply":
					     	resultText="*";mychar=mychar+resultText;
					   	break;
					case "power":
		        			resultText="^";mychar=mychar+resultText;
		        			break;
			 		case "left-brace":
		        			resultText="(";mychar=mychar+resultText;
		        			break;
		        		case "right-brace":
		        			resultText=")";mychar=mychar+resultText;
		        			break;
		        		case "backspace":
		        			int leng=mychar.length();
		        			if (leng==1) {
		        				mychar="";System.out.println("No Input!");speak1.speak("No Input");
		        			} else {
							mychar=mychar.substring(0,leng-1);
		        			}
		        			break;
		        		case "equal":
		        			resultText="=";
		    				myrpn myconvert=new myrpn();
		    				long ans=myconvert.stringToArithmetic(mychar);	
		        			mychar=mychar+resultText+(ans+"");
						i=99;
		        			break;
		        		case "clear":
		        			mychar="";
		        			System.out.println("Cleared!");
		        			speak1.speak("Cleared");
		        			System.exit(1);
		        			break;
		        		case "close":
		        			System.exit(0);
		        			break;
		    		}
		    		System.out.println(mychar);
		        	//write into the file
		    		File file = new File("voice.txt");
		    		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
		        	 	bw.write(String.valueOf(mychar));
		    		} catch (IOException ex) {
		            		ex.printStackTrace();
				}    
			} 
		}
		return mychar;
	}
} 




