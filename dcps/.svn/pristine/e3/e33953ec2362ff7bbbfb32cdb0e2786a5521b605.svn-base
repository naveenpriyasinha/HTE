<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Map"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ShowBillVO" value="${resValue.ShowBillVO}" scope="request"> </c:set>  
<input type="hidden" name="chkbox" value="${ShowBillVO.objections}">
<%
	ResultObject rs = (ResultObject) request.getAttribute("result");
	Map map = (Map)rs.getResultValue();
	
	String lStrBillState = "";
	String lStrCurrRemarks = "";
	String lStrObjectionFlag = "";
	
	if(map.get("billStatus") != null)
	{
		lStrBillState = (String) map.get("billStatus");
	}
	
	if(map.get("currRemarks") != null)
	{
		lStrCurrRemarks = (String) map.get("currRemarks");
	}

	if(map.get("ShowObjection") != null)
	{
		lStrObjectionFlag = String.valueOf(map.get("ShowObjection"));
	}
		
	ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
	String BillInStat = lObjRsrcBndle.getString("STATUS.BillInward");
%>

	<script language="javascript">	
		var defaultEmptyOK = false;	
		
/*		function isEmpty(s)
		{   
			return ((s == null) || (s.length == 0))
		}
*/		
		function validRemarks(s)
		{
			var i;
			var reAlphanumeric = /^[a-zA-Z0-9\s\.\_\n\r]*$/;
		    if (isEmpty(s)) 
		       	if (validRemarks.arguments.length == 1) 
		       		return defaultEmptyOK;
		       	else 
	      			 return (validRemarks.arguments[1] == true);
	
		    else 
		    {
		       	return reAlphanumeric.test(s);
		    }
	   }
	   function digiSignRemarksDtlsData()
	   {
	   		generateDigiSign(document.forms[0].txtareaRemarks.value,"digi7_0_REMARKS");
	   }	    
	</script>		  
		  
	<table id="tabScreen" align="center" border="0"  width="100%" cellpadding="0" cellspacing="0">  
        <tr>
    	    <td>
 				<table id="tabScreen1" align="center" border="0"  width="90%">
				  <tr>
					<td>
						<table align="center" class="TableBorderLTRBN" width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center">									
								</td>
							</tr>					
							<tr><td><br></td></tr>
							<tr><td><br></td></tr>
			<% 
					if(!lStrBillState.equals(BillInStat))
					{
			%>	   				
							<tr>
								<td colspan="10" align="center" class="Label4">
									<b>
										<fmt:message key="CMN.REMARKS_DETAILS" bundle="${billprocLabels}"></fmt:message>							
									</b>
								</td>
							</tr>			
							<tr><td><br>
					</td>
				</tr>
   	
				<tr><td><br></td></tr>
				
				<tr>
					<td align="center" class="Label">
						<hdiits:a href="#" onclick="javascript:showRemarks('ifms.htm?actionFlag=adtObjectionDetails');" caption="">
							<fmt:message key="CMN.PREV_REMARKS" bundle="${billprocLabels}"></fmt:message>
						</hdiits:a>
			 		</td>
				</tr>
		<% } %>
			</table>
		</td></tr>
			</table>
		 </td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td><hr></td></tr>
 	<tr><td>&nbsp;</td></tr>
	<tr>
		 <td>
			 <table width="100%" class="groupTable">			 		 	
		 		<tr>
			 		<td class="Label">
			 			<fmt:message key="CMN.REMARKS" bundle="${billprocLabels}"></fmt:message>
			 		</td>
			 		<td colspan="2" align="left">
			 		:&nbsp;<textarea name="txtareaRemarks" id="id_txtareaRemarks" cols="60" rows="7" dir="ltr" onfocus="showHighLight(this)" onblur="hideHighLight(this)"><%=lStrCurrRemarks%></textarea>
			 		<input type="hidden" name="digi7_0_REMARKS"/>
			 		</td>
			 	</tr>
			<% 
				System.out.println("Value of OBJ "  + request.getParameter("obj"));
					if(!lStrBillState.equals(BillInStat) || lStrObjectionFlag.equals("Y"))
					{
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + request.getParameter("obj"));
			%>
				 <tr id="id_objection"> 
			 		<td class="Label">
				 		<fmt:message key="ADT.OBJECTION" bundle="${billprocLabels}"></fmt:message>
				 	</td>
				 	<td align="left">
				 		<hdiits:a href="#" onclick="javascript:showBill('ifms.htm?actionFlag=showObjection&billCntrlNo=${ShowBillVO.billCntrlNo}');" caption="">
				 			:&nbsp;<hdiits:image source="common/images/Details.gif" id="id_imgObjection" />
				 		</hdiits:a>
				 	</td>
				 </tr>
			<% 
				}
			%>			 				 	 
			 	 <tr>
				 	<td  class="Label">
				 		<fmt:message key="CMN.ATTACHMENT" bundle="${billprocLabels}"></fmt:message>
				 	</td>			 			
				 	<td colspan="2" align="left">
				 	   <hdiits:a href="#" onclick="javascript:showAttach();" caption="">
				 	  	 :&nbsp;<hdiits:image source="common/images/CalendarImages/doclink.gif" />
			 		   </hdiits:a>
					</td>
				</tr>
	 		 </table>
		 </td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>

</table>
       		    
