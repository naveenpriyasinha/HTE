package com.tcs.sgv.common.util;

import java.util.StringTokenizer;

public class IFMSUtil {
	
	public static String getInitCapString(String lStrIn)
	{
    	if(lStrIn == null)
    		return null;
    	
    	StringBuffer lStrReturn = new StringBuffer();
		StringBuffer lSBData = new StringBuffer();
		
		StringTokenizer lSTStr = new StringTokenizer(lStrIn.toLowerCase());
		
		while(lSTStr.hasMoreElements())
		{
			lSBData.delete(0, lSBData.length()); 
			lSBData.append(lSTStr.nextToken());
			
			if(lSBData.charAt(0) >= ((byte) 'a') && lSBData.charAt(0) <= ((byte) 'z'))
				lSBData.replace(0, 1, String.valueOf((char) (((byte)lSBData.charAt(0)) - 32)));
					
			lStrReturn.append( (lStrReturn.length() == 0) ? lSBData.toString() : " " + lSBData.toString() );  
		}
		return lStrReturn.toString();		
	}
}
