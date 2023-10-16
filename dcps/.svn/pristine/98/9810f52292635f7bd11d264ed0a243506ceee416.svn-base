<%@ include file="../core/include.jsp" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@page import="java.util.Map"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.List"%>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.app.diary.DiaryLables" var="diaryLables" scope="request"/>

<c:set var="DdoHeadDtls" scope="request" value="${result.resultValue.DDOHeadDtls}"></c:set>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script language="javascript">
	<%
		String  locale = (String)session.getAttribute("locale");
		Locale localee = new Locale(locale);
		ResourceBundle bundle =ResourceBundle.getBundle("resources/billproc/billproc",localee);
	%>	
	
		function selectDdoHead()
		{
			var n = 0;
			if(document.forms[0].ddoHead.length > 0)
			{
				for (i=0;i<document.forms[0].ddoHead.length;i++) 
				{
					if (document.forms[0].ddoHead[i].checked) 
					{
						partId = document.forms[0].ddoHead[i].value;
				    	values = partId.split("~");
				    	
				    	window.opener.document.forms[0].cmbDemand.value = values[1];
				    	window.opener.document.forms[0].cmbMajorHead.value = values[2];
				    	window.opener.document.forms[0].cmbSubMajorHead.value = values[3];
				    	window.opener.document.forms[0].cmbMinorHead.value = values[4];
				    	window.opener.document.forms[0].cmbSubHead.value = values[5];
				    	window.opener.document.forms[0].txtSchemeCode.value = values[6];
				    	n = n+1;
					}
				}
			}
			else
				window.close();
			if(n==0)
			{
				alert("Please select any one option.");
				return false;
			}
			else
				window.close();	
		}
		</script>
</head>

<%	ResultObject rs = (ResultObject)request.getAttribute("result");
	Map map = (Map) rs.getResultValue();
	List HeadDetailsList = (List)map.get("DDOHeadDtls");
	System.out.println("Value of list in cmnDDoHEAD : " +HeadDetailsList);
	if(HeadDetailsList!=null)
		System.out.println("Size of list in cmnDDoHEAD : " +HeadDetailsList.size());
%>	
<body>
<form name="frmDdoHeadDtlsPopup">
	<table id="ddoHeadDtls" align="center" width="100%" class="TableBorderLTRBN">
		<tr class="TableHeaderBG">
			<td align="center" colspan="6" width="100%" class="HLabel">
				<fmt:message key="CNTR.BUDGET_INFO" bundle="${billprocLabels}"></fmt:message>
			</td>
		</tr>
		<hdiits:tr>
			<td colspan="6" width="100%">
				<br />
			</td>
		</hdiits:tr>
		<tr class="datatableheader">
			<td align="center" class="HLabel">
				<b> <fmt:message key="COMMON.SELECT" bundle="${billprocLabels}"></fmt:message></b>				
			</td>
			<td align="center" class="HLabel">
				<b> <fmt:message key="CMN.HEADSTR" bundle="${billprocLabels}"></fmt:message></b>				
			</td>
			<td align="center" class="HLabel">
				<b> <fmt:message key="CNTR.SCHEME_CODE" bundle="${billprocLabels}"></fmt:message></b>				
			</td>
			<td align="center" class="HLabel">
				<b> <fmt:message key="CMN.SUB_HEAD" bundle="${billprocLabels}"></fmt:message></b>				
			</td>
		</tr>
		<c:forEach var="ArrDDoHead" items="${DdoHeadDtls}" varStatus="No">
			<tr class="odd">
				<td align="center" class="Label">
					<input id="RedSelect${No.count}" type="radio" name="ddoHead" value="${ArrDDoHead[0]}~${ArrDDoHead[2]}~${ArrDDoHead[3]}~${ArrDDoHead[4]}~${ArrDDoHead[5]}~${ArrDDoHead[6]}~${ArrDDoHead[0]}"/><!--  onclick="selectDdoHead(this)"/> -->
				</td>
				<td align="center" class="Label">
					<c:out value="${ArrDDoHead[2]}:${ArrDDoHead[3]}:${ArrDDoHead[4]}:${ArrDDoHead[5]}:${ArrDDoHead[6]}"></c:out>
				</td>
				<td align="center" class="Label">
					<c:out value="${ArrDDoHead[0]}"></c:out>
				</td>
				<td align="center" class="Label">
					<c:out value="${ArrDDoHead[1]}"></c:out>
				</td>
			</tr>
		</c:forEach>
		<hdiits:tr>
			<hdiits:td align="center" colspan="6">
				<br />
				<hdiits:button type="button" name="butSubmit" value='<%=bundle.getString("CMN.SUBMIT")%>' onclick="javascript:selectDdoHead();" />
				<hdiits:button name="btnClose" type="button" value='<%= bundle.getString("CMN.CLOSE")%>' onclick="self.close();" ></hdiits:button>
			</hdiits:td>
		</hdiits:tr>
	</table>
</form>
</body>
</html>