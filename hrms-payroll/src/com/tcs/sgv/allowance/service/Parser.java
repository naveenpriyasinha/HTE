package com.tcs.sgv.allowance.service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class Parser{
	Log logger = LogFactory.getLog(getClass());
		public double stringParse( String str )
		{
		String output="";
		//String str = "5 * 4 - 10 + 3 / 2";
		int strlength = str.length();
		
		Convert theTrans = new Convert();
	        output = theTrans.infixtopostfix(str);

		
		double result = theTrans.eval(output);
		
		
		logger.info(" result is " +  result + '\n');
		return result;
	}	
	
}
