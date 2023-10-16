
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
<script>
	var urlLink  = 'ifms.htm?actionFlag=getHyrUsers';
			
			
			
</script>	
<%@page import="java.util.ResourceBundle"%>
<%
ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

	<body>

 <c:set var="resultObj" value="${result}" > </c:set>
 <c:set var="resValue" value="${resultObj.resultValue}"> </c:set>	
 <c:set var="userList" value="${resValue.userList11}"> </c:set>
  <c:set var="msg" value="${resValue.Msg}"> </c:set>
 
 
 <%
 	ResultObject rs = (ResultObject)request.getAttribute("result");
 	Map map = (Map)rs.getResultValue();
 	String toRefId = (String)map.get("ToHeirarchyRefID");
 	
 	if(request.getParameter("BillNo") != null)
 	{
 	%>
 		<script>
 		 urlLink = urlLink+'&BillNo='+'<%=(String)request.getParameter("BillNo")%>';
 		</script>
 	<%
 	}
	 if(request.getParameter("page") != null)
 	{
		 %>
	 		<script>
	 		 urlLink = urlLink+'&page='+'<%=(String)request.getParameter("page")%>';
	 		</script>
	 	<%
 	}
 	if(request.getParameter("actionVal")!= null)
 	{
 		%>
 		<script>
 		 urlLink = urlLink+'&actionVal='+'<%=(String)request.getParameter("actionVal")%>';
 		 window.opener.document.forms[0].actionVal.value = '<%=(String)request.getParameter("actionVal")%>';
 		</script>
 	<%
 	}
 	if(request.getParameter("statusFlag")!= null)
 	{
 		%>
 		<script>
 		 urlLink = urlLink+'&statusFlag='+'<%=(String)request.getParameter("statusFlag")%>';

 		</script>
 	<%
 	}
 	String sendTO ="";
 	String hChk ="checked";
 	String pChk ="";
 	if(request.getParameter("sendTo")!= null)
 	{
 		sendTO = (String)request.getParameter("sendTo");
 		if(sendTO.equals("H"))
 		{
 			hChk = "checked";
 			pChk ="";
 		}
 		else if(sendTO.equals("P"))
 		{
 			hChk = "";
 			pChk ="checked";
 		}
 	}
 	
    
 %>

	<hdiits:form action="" validate="true" name="cmnForward" method="post">
		<table width="100%" align="center">
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr class="datatableheader"><td>
		<fmt:message key="CMN.FORWARD_TO" bundle="${billprocLabels}"></fmt:message>
		</td></tr>
				<tr><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td></tr>
	<c:choose>
				<c:when test="${msg=='0'}">
				<script>
				alert("Cheques generated for selected Bill combination is not correct. Please make necessary changes");
				self.window.close();
				</script>

				<tr>
					<td>
						<c:out value="Cheques generated for selected Bill combination is not correct. Please make necessary changes" />
					</td>
					</tr>
					<tr>
					<td>
						<input type="button" name="Buttn" value="Close" onclick="javascript:window.close();">
					</td>
					</tr>
					</table>	
 				</c:when>
 			<c:otherwise>	
 											
	<table align="left" width="90%">	
			<%
				int userCount=0;
				List userList = (List)map.get("userList11");
				boolean showFlag = false;  
				if(userList!= null && userList.size() >0)
				{
					showFlag =true;
				}

			%>	
	
	<tr>
		<td>
			<hdiits:select name="cmbName" size="5" >
			<%if(showFlag){ userCount++; %>
				<c:forEach var="vo" items="${userList}">
							<option value="<c:out value="${vo[1]}~${vo[2]}" />" selected="true" > 
								<c:out value="${vo[0]}" />  
							</option>
				</c:forEach>				
			 	<%}
				else
				{
					%>
						<option value="">-- No Users Found --</option>
					<%
						
				}%>
			</hdiits:select>
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
	<td>
		<input type="hidden" name="toPost" value="5">	
		<input type="hidden" name="toUser" value="6">
		<input type="button" class="buttontag" name="Butt" value='<%=buttonBundle.getString("COMMON.FORWARD")%>' onclick="popup()">&nbsp;
		<input type="button" class="buttontag" name="CloseBtn" value='<%=buttonBundle.getString("CHQCOMMON.CLOSE")%>' onclick="javascript:window.close();">
	</td>	
	</tr>	
	</c:otherwise>
 	</c:choose>

		<script>
			function hierarchy()
			{				
				urlLink = urlLink+'&sendTo=H';
				document.forms[0].action = urlLink;
				document.forms[0].submit();
			}
			function peer()
			{
				urlLink = urlLink+'&sendTo=P';
				document.forms[0].action = urlLink;				
				document.forms[0].submit();
			}	
			function popup()
			{
				var sendToBtn = '<%=request.getParameter("sendTo")%>';
				if(sendToBtn != null || sendToBtn !='null' || sendToBtn != '')
				{
					sendToBtn = sendToBtn;
				}
				else 
				{
					sendToBtn="H";
				}
				if(document.cmnForward.cmbName.value=="")
				{
					alert('Please Select Employee For Forward');
					return;
				}
				
				window.opener.document.forms[0].toPost.value=document.cmnForward.cmbName.value;
				window.opener.document.forms[0].toUser.value=document.cmnForward.cmbName.value; // change here to user if needed
				window.opener.document.forms[0].SendTo.value = sendToBtn;
				<%
				if(toRefId!= null && !toRefId.equals("") )
			 	{
			 	%>
			 		window.opener.document.forms[0].ToHeirarchyRefId.value = '<%=toRefId%>';
			 	<%	
			 	}
				%>
				
				window.opener.displ();
				window.close();
			}	
		</script>
			</table >	
			<%
			String currentUserPost  = (String) map.get("currentUserPost");
			{
			%>
			<script>
			if(document.cmnForward.cmbName.length==1)
			{
				var selval=document.cmnForward.cmbName.options[0].value
				var postIs=selval.split("~");
				
				if(postIs[0]=='<%=currentUserPost%>')
				{
					window.opener.document.forms[0].toPost.value=selval;
					window.opener.document.forms[0].toUser.value=selval; // change here to user if needed
					//window.opener.document.forms[0].SendTo.value = "H";
					window.opener.displ();
					window.close();
					
				}
			}
			</script>
			<%
			}
			%>
			
	</hdiits:form>
	</body>
</html>
 			