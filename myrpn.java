//  Created by Rao Xu
//  Copyright (c) 2014. All rights reserved.
package sphinxTTScalculator

import java.util.Stack;
import java.util.regex.*;


import javax.swing.JOptionPane;
public class myrpn {
    public long stringToArithmetic(String string) {
    	double temp= suffixToArithmetic(infixToSuffix(string));
    	long x = (long)temp; 
        return x;
    }
  
    public static String infixToSuffix(String infix) {
        Stack< Character> stack = new Stack< Character>();
        String suffix = "";
        int length = infix.length();
        for (int i = 0; i < length; i++) {

            Character temp ;
            char c = infix.charAt(i);
            switch (c) {
                
            case '(':
                stack.push(c);
                break;
                
            case '+':
            	 if ( infix.charAt(i)=='+'&&( i==0||infix.charAt(i-1)=='(')){
            	    suffix += "0" + "";
                }
                
            case '-':
                if ( infix.charAt(i)=='-'&&( i==0||infix.charAt(i-1)=='(')){
            	    suffix += "0" + "";
                }
                while (stack.size() != 0) {
                    temp = stack.pop();
                    if (temp == '(') {
                        stack.push('(');
                        break;
                    }
                    suffix += " " + temp;
                }
                stack.push(c);
                suffix += " ";
                break;
                
            case '*':
                while (stack.size() != 0) {
                    temp = stack.pop();
                    if (temp == '(' || temp == '+' || temp == '-') {
                        stack.push(temp);
                        break;
                    } else {
                        suffix += " " + temp;
                    }
                }
                stack.push(c);
                suffix += " ";
                break;
                
            case '^':
                while (stack.size() != 0) {
                    temp = stack.pop();
                    if (temp == '*' ||temp == '(' || temp == '+' || temp == '-') {
                        stack.push(temp);
                        break;
                    } else {
                        suffix += " " + temp;
                    }
                }
                stack.push(c);
                suffix += " ";
                break;
                
            case ')':
                while (stack.size() != 0) {
                    temp = stack.pop();
                    if (temp == '(') {
                        break;
                    } else {
                        suffix += " " + temp;
                    }
                }
                break;
                
            default:
                suffix += c;
            }
        }
        while (stack.size() != 0) {
            suffix += " " + stack.pop();
        }
        return suffix;
    }
    
    public static double suffixToArithmetic(String postfix) {
        Pattern pattern = Pattern.compile("\\d+"); 
        String strings[] = postfix.split(" ");
        for (int i = 0; i < strings.length; i++) {
            strings[i].trim(); 
        }
        Stack< Double> stack = new Stack< Double>();
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("")) {
                continue;
            }
            if ((pattern.matcher(strings[i])).matches()) {
                stack.push(Double.parseDouble(strings[i]));
            } else {
                double y = stack.pop();
                double x = stack.pop();
                stack.push(caculate(x, y, strings[i]));
            }
        }
        return stack.pop();
    }
    
    private static double caculate(double x, double y, String simble) {
        if (simble.trim().equals("+")) {
            return x + y;
        }
        if (simble.trim().equals("-")) {
            return x - y;
        }
        if (simble.trim().equals("*")) {
            return x * y;
        }
        if (simble.trim().equals("^")) {
            if (y>=0) {    		
                return Math.pow(x,y);
            } else {
        	   JOptionPane.showMessageDialog(null,"The power should be non-negative. Try again!");
        	}
            return 0;
        }
    }
}
