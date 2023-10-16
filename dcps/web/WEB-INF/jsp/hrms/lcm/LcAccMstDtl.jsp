<%@ include file="../core/include.jsp" %>

<%@page import="java.util.ArrayList,com.tcs.sgv.apps.common.valuebeans.ComboValuesVO,com.tcs.sgv.lcm.valueobject.LcAccMstVO"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setBundle basename="resources.lcm.lcexp_en_US" var="lcexpLabels" scope="application"/>
<fmt:setBundle basename="resources.lcm.LcmConstants" var="lcconstants" scope="application"/>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>LC Advice Received </title>
		
		<script  type="text/javascript"  src="script/common/LCCommonFunction.js"></script>
		<style >
		.tabstyle {
	border-width: 5px 1px 1px 1px; 
	border-color: #2065c0;
	border-style: solid ;
	}
	
legend {
	padding-left:5px;
	padding-right:5px;
	font-weight:normal; 
	font-family:verdana;
		
	border-width: 0px 0px 1px 0px; 
	border-color: #2065c0;
	border-style: solid ;
	}
		
		</style>

<script>

function addAcc()
{

		var cntxtPath = '<%=request.getContextPath()%>';
		var windowURL = cntxtPath +'/ifms.htm?actionFlag=addAccount' ;
		
		window.open(windowURL,"confirmWindow","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=70,left=50,width=850,height=560");
		
		
}

function updateAcc()
{
	var cnt=0;
	var index=0;

  for(i=0;i < document.forms[0].elements.length;i++)
  {
    if(document.forms[0].elements[i].type=="checkbox")
    {  
      if (document.forms[0].elements[i].checked == true)
       {
         cnt++;
         index=i;
       }
     }
  }  
  
  if(cnt>1)
  {
  	alert('Please Select One Record to Update');
  	return false;
  }
  
  if (cnt!=0)
  {
      var accNo=document.forms[0].elements[index].value;
      
     	var cntxtPath = '<%=request.getContextPath()%>';
		var windowURL = cntxtPath+'/ifms.htm?actionFlag=updtAccount&accNo='+accNo;
		
		window.open(windowURL,"confirmWindow","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=150,left=130,width=750,height=420");
		
    
  }
  
  else
  {
    alert("Please Select Atleast One Record To Update");
    return false;
  }
}


function deleteAcc()
{
	var cnt=0;
	var index=0;

  for(i=0;i < document.forms[0].elements.length;i++)
  {
    if(document.forms[0].elements[i].type=="checkbox")
    {  
      if (document.forms[0].elements[i].checked == true)
       {
         cnt++;
         index=i;
       }
     }
  }  
  
  if(cnt>1)
  {
  	alert('Please Select One Record to Delete');
  	return false;
  }
  
  if (cnt!=0)
  {
    if (confirm("Are you sure you want to delete?")==true)
    {
     //document.forms[0].action = "/IWAS/FrontServlet?requestType=BankRequest&actionVal=DeleteBank";
     //document.forms[0].submit();
     alert(document.forms[0].elements[index].value);
    }
    else
    {
      return false;
    }
  }
  
  else
  {
    alert("Please Select Atleast One Record To Delete");
    return false;
  }
}


function closeWindow()
{
	var contextPath = '<%=request.getContextPath()%>';			
			var newURL = contextPath +'/ifms.htm?viewName=homePage&theme=ifmsblue' ;
	 	    document.forms[0].action=newURL;
	 		if(confirm('Do You Want to Close This Window ?'))
	 		{
	 		  document.forms[0].submit();
	 		}  
}	
</script>


<%

	ArrayList lArrDivInfoList = new ArrayList();
	lArrDivInfoList=(ArrayList)request.getAttribute("AccList");
	System.out.println("=========in JSP div array size :: "+lArrDivInfoList.size());


%>




		</head>
	<body>
		<hdiits:form name="frmAdviceReceived" validate="true" method="post"  >

<fieldset  style = "width:950px" class="tabstyle">
	<legend  id="headingMsg">			<fmt:message key="LC.ACCMASTER" bundle="${lcexpLabels}"></fmt:message></legend>
<table width="100%" align="center" class="TableBorderLTRBN" border="0" cellspacing="0" cellpadding="1">
<tr>
<td colspan=6><br>
</td>
<td colspan=6><br>
</td>
</tr>
<tr>
<td colspan=6><br>
</td>
</tr>
</table>




<table width="100%" align="center" class="TableBorderLTRBN" border="1" cellspacing="0" cellpadding="1">




	<tr class="TableHeaderBG">
		<td>
		</td>
		<td align="center" >
			<fmt:message key="LC.DEPT" bundle="${lcexpLabels}"></fmt:message>
		</td>
		<td align="center" >
			<fmt:message key="LC.DIVISION_CD" bundle="${lcexpLabels}"></fmt:message>
		</td>
		<td align="center" >
			<fmt:message key="LC.DIVISION_NM" bundle="${lcexpLabels}"></fmt:message>
		</td>
		<td align="center" >
			<fmt:message key="LC.UNDERCODE" bundle="${lcexpLabels}"></fmt:message>
		</td>
		<td align="center" >
			<fmt:message key="LC.DESCRIPTION" bundle="${lcexpLabels}"></fmt:message>
		</td>
	</tr>
	
	<%

		for(int i=0;i<lArrDivInfoList.size();i++)
		{

			LcAccMstVO accVO =(LcAccMstVO)lArrDivInfoList.get(i);
			
	%>
	
	<tr >
		<td align="center">
			<input type="checkBox" id="chkBox<%=i%>" name="chkBox<%=i%>" value="<%=accVO.getAccountNo()%>" />
		</td>
		<td align="left" >
			<%=accVO.getDepartment() %>
		</td>
		<td align="left" >
			<%=accVO.getDivCode() %>
		</td>
		<td align="left" >
			<%=accVO.getDivName() %>
		</td>
		<td align="left" >
			<%=accVO.getUnderCode() %>
		</td>
		<td align="left" >
			<%=accVO.getUnderCodeDesc() %>
		</td>
	</tr>
	
	
	<%

		}


		%>
		

</table>
</fieldset>
<br>
<br>
<center>
		<hdiits:button type="button" name="btnAdd" value=" Add " onclick="addAcc()"/>
		<hdiits:button type="button" name="btnUpd" value=" Update " onclick="updateAcc()"/>
		<hdiits:button type="button" name="btnDelete" value=" Delete " onclick="deleteAcc()"/>
		<hdiits:button type="button" name="btnCncl" value=" Cancel " onclick="closeWindow()"/>
	
</center>
		</hdiits:form>

	</body>
</html>