<%@ page language="java" contentType="text/plain" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.jsp.*"%>
<%@page import="java.io.PrintWriter"%>
<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>
<c:set var="vitoStringList" value="${resValue.vitoStringList}"> </c:set>
<c:set var="vitoListSize" value="${resValue.vitoListSize}"> </c:set>
<c:set var="vitoCode" value="${resValue.vitoCode}"> </c:set>
<c:set var="HeaderDetails" value="${resValue.HeaderDetails}"></c:set>
<c:set var="HeaderWidth" value="${resValue.HeaderWidth}"></c:set>
<c:set var="LineSize" value="${resValue.LineSize}"></c:set>
<c:set var="reportHeader" value="${resValue.reportHeader}"></c:set>
<%!
public void printData(String strData,String width,String strAlign,PageContext pageContext,JspWriter writer,int columnNumber)
{
    System.out.println(strData);
    System.out.println(width);
    System.out.println(strAlign);
    
    int intWidth=Integer.parseInt(width);
    String strResult=null;
	if(strData.length() <= intWidth)
	{
	    int extraSpace=intWidth-(strData.length()+1);
		if(strAlign.equals("-")==true)
		{
		 	   strResult=strData;
		 	   while(extraSpace>0)
		 	   {
		 	       strResult=strResult+" ";
		 	       extraSpace--;
		 	   }
		}
		else 
		{
		       strResult="";
		 	   while(extraSpace>0)
		 	   {
		 	       strResult=strResult+" ";
		 	       extraSpace--;
		 	   }
		 	   strResult=strResult+strData;
		}			
	}
	else
	{
	}
	try
	{
		writer.write(strResult);
		if(strData.length()!=intWidth)
		{
			writer.write(" ");
		}
	}
	catch(Exception ex)
	{
	    ex.printStackTrace();
	}
}
%>
<%!
public void printLine(JspWriter writer,String strLineSize)
{
	int line=Integer.parseInt(strLineSize);
    for(int i=0;i<=line;i++)
    {
		try
		{
		writer.write("-");
		}catch(Exception ex){}
    }
}
%>
<%!
public String getSpace(int len)
{
	String strData="";
	for(int i=0;i<len-1;i++)
	{
	    strData=strData+" ";
	}
	return strData;
}
%>
<%
	List headerDetails=(ArrayList)pageContext.getAttribute("HeaderDetails");
	List headerWidth=(ArrayList)pageContext.getAttribute("HeaderWidth");
	List vitoDetails=(ArrayList)pageContext.getAttribute("vitoStringList");
	String strLineSize=(String)pageContext.getAttribute("LineSize");
	String strReportHeader=(String)pageContext.getAttribute("reportHeader");
	Iterator itHeader=headerDetails.iterator();
	Iterator itWidth=headerWidth.iterator();
	String[] widthDetails=new String[headerWidth.size()];
	String[] alignDetails=new String[headerWidth.size()];
	out.print("<pre>");
	out.print(strReportHeader);
	out.write("\r\n");
	out.write("\r\n");
	printLine(out,strLineSize);
	out.write("\r\n");
	int Counter=0;
	SortedMap leftDetailsHeader=new TreeMap();
	while(itHeader.hasNext())
	{
	    String strHeader=itHeader.next().toString();
	    String strWidth=itWidth.next().toString();
	    String strAlign=strWidth.substring(0,1);
	    strWidth=strWidth.substring(1);
	    int intHeaderWidth=Integer.parseInt(strWidth);
	    
	    widthDetails[Counter]=strWidth;
	    alignDetails[Counter]=strAlign;
	    
	    if(strHeader.length()>intHeaderWidth)
	    {
			String strLeftData=strHeader.substring(intHeaderWidth-1);
			strHeader=strHeader.substring(0,intHeaderWidth-1);
			leftDetailsHeader.put(Counter,strLeftData);
	    }
	    
		printData(strHeader,strWidth,strAlign,pageContext,out,Counter);
		Counter++;
	}
	if(leftDetailsHeader.size()>0)
	{
		while(leftDetailsHeader.size()>0)
		{
		    out.write("\r\n");
		    for(int i=0;i<widthDetails.length;i++)
		    {
				boolean present=leftDetailsHeader.containsKey(i);
				int len=Integer.parseInt(widthDetails[i].toString());
				String strInnerWidth=widthDetails[i].toString();
				String strInnerAlign=alignDetails[i].toString();
				if(present==true)
				{
				    String strData=leftDetailsHeader.get(i).toString();
				    if(strData.length()>len)
				    {
						String strLeftData=strData.substring(len);
						try
						{
							strData=strData.substring(0,len-1);
						}
						catch(Exception ex)
						{
						    strData=strData.substring(0);
						}
						leftDetailsHeader.remove(i);
						
						leftDetailsHeader.put(i,strLeftData);
						printData(strData,strInnerWidth,strInnerAlign,pageContext,out,i);						
				    }
				    else
				    {
						printData(strData,strInnerWidth,strInnerAlign,pageContext,out,i);						
						leftDetailsHeader.remove(i);
				    }
				}
				else
				{
				    String strData=getSpace(len);
				    printData(strData,strInnerWidth,strInnerAlign,pageContext,out,i);
				}
		    }
								    
		}
	}
	out.write("\r\n");
	printLine(out,strLineSize);
	out.write("\r\n");

	if(vitoDetails.size()>0)
	{
	    Iterator itDetails=vitoDetails.iterator();
	    while(itDetails.hasNext())
	    {
			List vitoData=(ArrayList)itDetails.next();
			out.write("\r\n");
			if(vitoData.size()>0)
			{
				Iterator itVitoData=vitoData.iterator();
				String strWidth="20";
				int intWidth=20;
				String strAlign="-";
				int count=0;
				SortedMap leftDetails=new TreeMap();
				while(itVitoData.hasNext())
				{
				    String strData="-";
				    try{
						strData=itVitoData.next().toString();
				    }
				    catch(Exception ex)
				    {}
				    try
				    {
						strWidth=widthDetails[count].toString();
						intWidth=Integer.parseInt(strWidth);
						System.out.println("Width="+strWidth);
				    }catch(Exception ex){
					System.out.println("Error for count"+count);
				    }
				    try
				    {
						strAlign=alignDetails[count].toString();
						System.out.println("Align="+strAlign);
				    }catch(Exception ex){
					System.out.println("Error for count"+count);
				    }
				    if(strData.length()>intWidth)
				    {
						String strLeftData=strData.substring(intWidth-1);
						strData=strData.substring(0,intWidth-1);
						leftDetails.put(count,strLeftData);
				    }
				    printData(strData,strWidth,strAlign,pageContext,out,count);
				    count++;
				}
				if(leftDetails.size()>0)
				{
					while(leftDetails.size()>0)
					{
					    out.write("\r\n");
					    for(int i=0;i<widthDetails.length;i++)
					    {
							boolean present=leftDetails.containsKey(i);
							int len=Integer.parseInt(widthDetails[i].toString());
							String strInnerWidth=widthDetails[i].toString();
							String strInnerAlign=alignDetails[i].toString();
							if(present==true)
							{
							    String strData=leftDetails.get(i).toString();
							    if(strData.length()>len)
							    {
									String strLeftData=strData.substring(len);
									try
									{
										strData=strData.substring(0,len-1);
									}
									catch(Exception ex)
									{
									    strData=strData.substring(0);
									}
									leftDetails.remove(i);
									
									leftDetails.put(i,strLeftData);
									printData(strData,strInnerWidth,strInnerAlign,pageContext,out,i);						
							    }
							    else
							    {
									printData(strData,strInnerWidth,strInnerAlign,pageContext,out,i);						
									leftDetails.remove(i);
							    }
							}
							else
							{
							    String strData=getSpace(len);
							    printData(strData,strInnerWidth,strInnerAlign,pageContext,out,i);
							}
					    }
											    
					}
				}
			}
			
	    }
	}
	out.print("</pre>");
%>
<script language="javascript">
window.print();
</script>
