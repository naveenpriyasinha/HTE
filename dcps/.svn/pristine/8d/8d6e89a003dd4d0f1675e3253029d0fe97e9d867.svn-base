<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>IFMS</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-color:black;
	font-size: 11px;
	font-style: normal;
	text-decoration: none;
}
-->
</style>
<script language="javascript">
	function showDetails(showTab,hideTab)
	{				
//		alert("Inside Function" + showTab);
		document.getElementById(hideTab).style.display='none';
//						alert("Hide Table");
		document.getElementById(showTab).style.display='block';
//						alert("Show Table");				
	}

/*function open_win()
{
 parent.frames[1].document.forms[0].action="fdbkform99.html"; 
 parent.frames[1].document.forms[0].target="right";
 parent.frames[1].document.forms[0].submit();
  
}
function open_win99()
{
 window.open('helpdoc.html','newWin99','toolbar=yes,location=yes,directories=yes,status=yes, menubar=yes,scrollbars=yes,resizable=yes,copyhistory=yes,addressbar=yes,titlebar=yes,width=1000,height=1000,top=20,left=20');
  
}*/
</script>
</head>

<!-- <link href="common/css/iwasstylesheet.css" rel="stylesheet" type="text/css"> -->
<body>
	<table width="100%" border="1" cellspacing="0" cellpadding="0" background="common/images/layout_innerPages_header_03.gif" id="header">
		<tr> 
	  		<td width="13%" height="28"> 
            	<table width="85%" border="0" cellspacing="0" cellpadding="0">
	              <tr> 
    	            <td> 
        	          <div align="right"><img src="common/images/top_tabs_07.gif" width="9" height="31"></div>
            		</td>
	                <td background="common/images/top_tabs_08.gif" valign="bottom" nowrap> 
		                  <hdiits:a href="javascript:void(0)" onclick="showDetails('billPage','audit')">
		                  	Bill Details
		                  </hdiits:a>
        	        </td>
            	    <td> 
                	  <div align="left"><img src="common/images/top_tabs_10.gif" width="12" height="31"></div>
	                </td>
    	          </tr>
        	    </table>
	          </td>
    	      <td width="13%" height="28"> 
        		  <table width="85%" border="0" cellspacing="0" cellpadding="0">
		              <tr> 
        		        <td> 
		                </td>
        		        <td background="common/images/top_tabs_08.gif" valign="bottom" nowrap> 
			                  <hdiits:a href="javascript:void(0);" onclick="showDetails('audit','billPage')" >
			                  	Remarks
			                  </hdiits:a>
			            </td>
    			        <td> 
	            		      <div align="left"><img src="common/images/top_tabs_10.gif" width="12" height="31"></div>
		                </td>
        		      </tr>
		            </table>
	            </tr>
    	     </table>
    	     
				<br><br>
				
			<table id="billPage" width="90%">
				<tr>
					<td>
						<%@include file="MRBillPage2.jsp" %>
					</td>
				</tr>
			</table>

			<table id="audit" width="90%" style='display:none'>
				<tr>
					<td>
					
						<%@include file="adtAuditDetails.jsp" %>
					
					</td>
				</tr>
			</table>
		</body>
	</html>