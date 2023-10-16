package com.tcs.sgv.allowance.service;
import java.util.Stack;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
class Convert{
	Log logger = LogFactory.getLog(getClass());
	  public String output = "";
	  Stack<Character> theStack= new Stack<Character>();
          Stack<Double> opStack= new Stack<Double>();
 	  public boolean isOperator(String op)
	  {
		return op.equals("+") || op.equals("-")|| op.equals("*") || op.equals("/")|| op.equals("(")|| op.equals(")");
	  }
	  public boolean isSpace(String op)
	  {
		return op.equals(" ") || op.equals("\t") || op.equals("\n");
	}
	public String infixtopostfix( String str )
	{
		StringTokenizer st= new StringTokenizer(str," \t\n+-*/",true);
		while (st.hasMoreTokens())
		{
			String token =st.nextToken();
			if ( isOperator(token) )
			{
				char ch = token.charAt(0);
	         		switch(ch)
			        {
			            case '+':               
		        	    case '-':
					     gotOper(ch, 1);      
	                             	     break;               
		
			            case '*':               
			            case '/':gotOper(ch, 2);      
		        	             break;               
			            case '(':               
        	       			     theStack.push(ch);   
				             break;
		        	    case ')':      
			        	     gotParen(ch);
				             break;
			           
		                } 
			}
			else if ( !isSpace(token) )
				output = output +" "+token;
        	 }  
	         while( !theStack.empty() )     
	         {
		      output = output +" "+theStack.pop(); 
	         }
	         return output;                   
          }  
	  public  void gotOper(char opThis, int prec1)
	  {  
	      while( !theStack.empty() )
              {
	         char opTop = theStack.pop();
	         if( opTop == '(' )           
	         {
	            theStack.push(opTop);      
        	    break;
                 }
     		 else
            	 {
	            int prec2;                 
	            if( opTop == '+'  || opTop == '-' )
        		    prec2 = 1;	
		    else
			    prec2 = 2;
	            if(prec2 < prec1) 
        	    {                 
	               theStack.push(opTop);
        	       break;
               	    }
	            else
        	       output = output +" "+ opTop;  
            	  }  
               }  
  	       theStack.push(opThis);  
      	    } 
    	    public  void gotParen(char ch)
	    { 
   	  	 while( !theStack.isEmpty() )
           	 {
         		char chx = theStack.pop();
		        if( chx == '(' )     
        		    break;                 
	        	 else                      
        	    	    output = output +" "+ chx;  
           	  }  
      	    }  
	    double eval( String str )
      	    {
 		 		 
		 double num1, num2, interAns;
	      	 StringTokenizer st= new StringTokenizer(str," \t\n+-*/",true);
		 while (st.hasMoreTokens())
		 {
			String token =st.nextToken();
			
			if ( isOperator(token) )
			{
				char ch = token.charAt(0);             
 	                        num2 = opStack.pop();        
        			num1 = opStack.pop();
			        switch(ch)                     
        			{
	        	       		case '+':
        			       		interAns = num1 + num2;
	                       	      	        break;
	                                case '-':
		               	 	        interAns = num1 - num2;
 	                               	        break;
	 		                case '*':
        			       		interAns = num1 * num2;
			               		break;
		               		case '/':
        			       		interAns = num1 / num2;
			               		break;
		               		default:
        			       		interAns = 0;
              		    	}  
		            opStack.push(interAns);      
		         } 
			 else if ( !isSpace(token) ) 
				opStack.push( Double.valueOf(token).doubleValue() );
	         } 
		 interAns = opStack.pop();            
		 return interAns;
	      }  

   }  

