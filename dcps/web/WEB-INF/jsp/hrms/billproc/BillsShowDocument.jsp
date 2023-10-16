<%@page contentType="text/html;  charset=ISO-8859-1" import="com.tcs.sgv.core.valueobject.ResultObject,java.util.Map" %>

<html>
<head>
<%	
	ResultObject resObj = (ResultObject)request.getAttribute("result");
	Map resultMap = (Map)resObj.getResultValue();
%>
 <script Language = "JavaScript">
 function fn_print()
        {
            window.print();
            window.close();
        }
        
       function getWarning(sss)
       {
       		if(sss == null || sss== 'null')
       		{       		
	       		if (confirm("Please set Page Setting before printing cheques. \n 1. Select Page Setup from File menu."
	       		 + "\n 2. Select Paper to Cheque \n 3. set Left,Top,Right,Bottom margin to 0 \n 4. Select Source to- Contin feed no Break "))
	       		 {
			        alert("Cheque Printing Will Start");
	              }
	              else
	              {
	              	alert("Cheque Printing Failed");
	              }
	        }
	        else
	        {
	        	alert(' Selected Cheque(s) already printed ');
	        	self.close();
	        }
       }
        </script>
    </head>

    <body onload="getWarning('<%=resultMap.get("ChequePrinted")%>');" >

<% 

   try 
      { 
	   	out.print("<pre>");
	   	String strOutput= new String((byte[])resultMap.get("File"));      
	   	out.print(strOutput);
	   	out.print("</pre>");
	   	out.flush();
      
      }       catch (Exception ex)       {    ex.printStackTrace();}
       
%>
</body>
</html>