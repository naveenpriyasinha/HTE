<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject,java.text.NumberFormat,java.util.Date,java.text.SimpleDateFormat"%>
<%@page import=" com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO"%>
<html>
<head>

<script type="text/javascript" src="<c:url value="/script/common/validations.js"/>"></script>

<script>
var majorNames = new Array(7);
		majorNames[0] = "";
    	majorNames[1] = " Thousand";
    	majorNames[2] = " Lac";
    	majorNames[3] = " Crore";
    	majorNames[4] = " Trillion";
    	majorNames[5] = " Quadrillion";
		majorNames[6] = " Quintillion";
		
		var tensNames = new Array(10);
		tensNames[0] = "";
    	tensNames[1] = " Ten";
    	tensNames[2] = " Twenty";
    	tensNames[3] = " Thirty";
    	tensNames[4] = " Fourty";
    	tensNames[5] = " Fifty";
		tensNames[6] = " Sixty";
		tensNames[7] = " Seventy";
		tensNames[8] = " Eighty";
		tensNames[9] = " Ninety";
		
		var numNames = new Array(10);
		numNames[0] = "";
    	numNames[1] = " One";
    	numNames[2] = " Two";
    	numNames[3] = " Three";
    	numNames[4] = " Four";
    	numNames[5] = " Five";
		numNames[6] = " Six";
		numNames[7] = " Seven";
		numNames[8] = " Eight";
		numNames[9] = " Nine";
		numNames[10] = " Ten";
		numNames[11] = " Eleven";
		numNames[12] = " Twelve";
		numNames[13] = " Thirteen";
		numNames[14] = " Fourteen";
		numNames[15] = " Fifteen";
		numNames[16] = " Sixteen";
		numNames[17] = " Seventeen";
		numNames[18] = " Eighteen";		
		numNames[19] = " Nineteen";
				
		function convertLessThanOneThousand(number) 
		{
			var soFar = new String("");

    		if (parseInt(number % 100) < 20)
    		{
        		soFar = numNames[parseInt(number % 100)];
        		number = parseInt(number / 100);
       		}
    		else 
    		{
        		soFar = numNames[parseInt(number % 10)];
        		number = parseInt(number / 10);

        		soFar = tensNames[parseInt(number % 10)] + soFar;
        		number = parseInt(number / 10);
       		}
    		if (number == 0) 
    		{
    			return soFar;
    		}
    		
    		return numNames[number] + " hundred" + soFar;	    	
		}
		function convert(number)
		{
			var prefix = new String("");
			var soFar = new String("");
    		var place = 0;

			if (number == 0) 
			{ 
				return "zero"; 
			}
    		if (number < 0) 
    		{
        		number = -number;
        		prefix = "negative ";
      		}


      		var n = parseInt(number % 1000);
    		if(n != 0)
    		{
       			var s = new String(convertLessThanOneThousand(n));
       			soFar = s + majorNames[place] + soFar;
       		}

   			place++;
   			number = parseInt(number / 1000);

    		while(number > 0) 
    		{
      			var n = parseInt(number % 100);
      			if(n != 0)
      			{
         			var s = new String(convertLessThanOneThousand(n));
         			soFar = s + majorNames[place] + soFar;
         		}

      			place++;
      			number = parseInt(number / 100);
      		} 

    		return prefix + soFar.trim();	    	
		}
		function getAmountInWords(number) 
		{
			
		 	var s = new String("");
	 
	 		if(isNaN(number))
	 		{
	 			return s;
	 		}
	 		
			if(number  > 10000000 )
			{
				s = convert(number / 10000000) + " Crore"; 
			
				if (number % 10000000 != 0)
				{
					s += convert(number % 10000000);  
				}
			}
			else
			{
				s = convert(number); 
			}
				
			return s;
		}
<%
System.out.println("----IN Form B JSP-");
LcDivisionInformationVO divInformationVO =null;
	String lStrDistrictName="";
	String lStrLocShortName="";
if(request.getAttribute("divInformationVO") != null && request.getAttribute("DistrictNames")!=null)
			  {
				  divInformationVO=(LcDivisionInformationVO)request.getAttribute("divInformationVO");
				  lStrDistrictName=request.getAttribute("DistrictNames").toString();
				  lStrLocShortName=request.getAttribute("LocationShortName").toString();
				  System.out.println("----IN Form B JSP----divInformationVO----"+divInformationVO.toString());
			  }

                  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                  SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
                  Date curDate = new Date();
                  Date lDtFrom = sdf2.parse(divInformationVO.getLc_valid_from());
                  Date lDtTo = sdf2.parse(divInformationVO.getDepartment_name());
                  String lStrFromDt = sdf.format(lDtFrom);
                  String lStrToDt = sdf.format(lDtTo);
                  String lStrCurDt = sdf.format(curDate);
                  System.out.println("----1----------");
 %>
function printPage()
{
	var linkObj=document.getElementById("printlink");
	linkObj.style.display='none';
	window.print();
    linkObj.style.display='inline';		 		
	
}
function closeWindow()
{
	window.close();
}
var amt=<%=divInformationVO.getDepartmentCode() %>;

var stringamt=getAmountInWords(amt);

</script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<body>	
<p align="left">
<a id="printlink" href="javascript:printPage()">Print<img src="/IFMS/themes/ifms/images/reports/printer.gif"></a>
<br>		   
FormB<br>
Letter of credit</p><br><br><br>
<p align=right>No.<u> <%System.out.println("----2----------");%><%= divInformationVO.getLc_order_id() %></u><br>
District Treasury Office,<br>
<u><%= lStrDistrictName %></u><br>
Date:<u><%System.out.println("----3----------");%><%=lStrCurDt%></u><br></br>
</p>
<br><br>
<p align="left">
To,<br>
The Agent/Manager<br>
State bank of India,L.H.O<br>
<%= lStrDistrictName %><br><br>
Dear Sir,<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In accordance with the provision of Finance department Govt. Resolution No TJR-1193-677-Z<br>
dt 19-9-04.I have the honour to request that you will encashment of cheques drawn by <u><%System.out.println("----4----------");%><%=lStrLocShortName%>-<%=divInformationVO.getDivision_name()%></u><br>
<u><%System.out.println("----5----------");%><%=lStrLocShortName%> - <%=divInformationVO.getDivision_name()%></u><br>
To extent of Rs.(<%System.out.println("----6----------");%><%=divInformationVO.getDepartmentCode() %>) <script>document.write(stringamt)</script> <br><br>
The progressive Amount of LC with this LC is now Rs.<u>
<%System.out.println("----7----------");%>
<%
NumberFormat lnfLong=NumberFormat.getNumberInstance();
lnfLong.setGroupingUsed(false);
lnfLong.setMaximumFractionDigits(3);
lnfLong.setMinimumFractionDigits(3);

if(divInformationVO.getDistrictCode().equals("150006"))
{
%>
(<%=lnfLong.format(Double.parseDouble(divInformationVO.getOpening_lc())-Double.parseDouble(divInformationVO.getDepartmentCode())) %> + <%=lnfLong.format(Double.parseDouble(divInformationVO.getDepartmentCode().toString())) %> )= <%=lnfLong.format(Double.parseDouble(divInformationVO.getOpening_lc().toString()))%>
<%
}
else
{
%>
(<%=lnfLong.format(Double.parseDouble(divInformationVO.getOpening_lc())+Double.parseDouble(divInformationVO.getDepartmentCode())) %> - <%=lnfLong.format(Double.parseDouble(divInformationVO.getDepartmentCode())) %> )= <%=lnfLong.format(Double.parseDouble(divInformationVO.getOpening_lc()))%>
<%
}
%>
</u><br><br>
This letter of credit has effect for the quarter from <u><%System.out.println("----8----------");%><%=lStrFromDt %> to <%=lStrToDt %></u> <br><br>
Code:<u><%System.out.println("----9----------");%><%=lStrLocShortName%> - <%=divInformationVO.getDivision_name()%></u>
</p>
<p align="right"> Yours faithfully<br>
Sr.Treasury Officer,<%= lStrDistrictName %><br><br>
</p>
<p align="left">
Copy To: <br>
<%System.out.println("----10----------");%>
1)<%=lStrLocShortName%> - <%=divInformationVO.getDivision_name() %>, <%=divInformationVO.getDistrict_name() %> <%=divInformationVO.getDivisionId() %>
<%System.out.println("----11----------");%></p>
<input type="button" name="clsBtn" value="OK" onclick="closeWindow()">
</body>
</html>
