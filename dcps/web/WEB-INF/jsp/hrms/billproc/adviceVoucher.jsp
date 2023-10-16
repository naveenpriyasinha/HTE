<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>' />
<%@page import="java.util.Map"%>

<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<%@page import="java.util.HashMap"%>
<%@page import="com.tcs.sgv.common.valueobject.MstParty"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="common/script/prototype-1.3.1.js">
</script>

<script type="text/javascript" src="common/script/ajaxtags-1.1.5.js">
</script>
<script language="javascript">
		function selectCheck()
		{	
			indx = document.frmObjectionDtls.objCode.value;			
		}
		function submitForm(url)
		{
			document.frmObjectionDtls.action =url;
			document.frmObjectionDtls.submit();
			return true;
		}
		function SubmitData(url)
		{
			if(submitForm(url))
			{
				self.close();
			}
		}
		function selectParty(indx)
		{
			for(i=0;i< document.forms[0].chkbox.length;i++)
			{
				if(document.forms[0].chkbox[i].checked == true)
				{
				  partId =document.forms[0].chkbox[i].value;
				  values = partId.split("~");
				 
	              eval("window.opener.document.forms[0].txtPartyName"+indx).value=values[0];
    	          eval("window.opener.document.forms[0].txtAddress"+indx).value=values[1];
        	      eval("window.opener.document.forms[0].txtAccountNo"+indx).value=values[2];
				  
				}
			}
			
			window.close();
		}
		
		function dispatchCheques()
		{		
			
			//document.forms[0].action ='ifms.htm?actionFlag=chqRangeUpdateAtAdviceGen';
			//document.forms[0].submit();
			insertdt();
			dispatchCheques1();
		}
		function dispatchCheques1()
		{		
			var arr = window.opener.DispatchCheques();		
  		    var bill = arr[0].split("~");
			document.forms[0].action ='ifms.htm?actionFlag=getHyrUsers&statusFlag=CHQ_DISP_DDO&BillNo='+bill[0]+'&page=savedBillsUpdate&BillArr='+arr;

			document.forms[0].submit();
			
		}
		
		function checkUncheckAll(theElement) {
     var theForm = theElement.form, z = 0; 
	 for(z=0; z<theForm.length;z++){
      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect'){
	  theForm[z].checked = theElement.checked;
	  }
     }
     }

		</script>
</head>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}" >
</c:set>
<c:set var="adviceList" value="${resValue.AdviceList}" ></c:set>
<c:set var="voucherList" value="${resValue.VoucherList}" ></c:set>

<body>

<hdiits:form name="frmAdviceVoucher" validate="true" method="post">
<input type="hidden" name="actionFlag" value="chqRangeUpdateAtAdviceGen">
<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
<div id="progressImage" style="display:none"></div>	

	<table width="100%">
		<tr>
			<td class="datatableheader">
				<fmt:message key="CMN.ADVCHQ" bundle="${billprocLabels}"></fmt:message>
			</td>
		</tr>		
	</table>
	<br>
	<%
			ResultObject rs = (ResultObject)request.getAttribute("result");
			Map map = (Map)rs.getResultValue();
			
			
	%>

			<display:table list="${adviceList}" pagesize="12" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				   	id="VO" excludedParams="ajax" varTotals="totals" export="false" partialList="" style="width:100%">
					<display:setProperty name="paging.banner.placement" value="bottom"/>    					
					<display:column property="commonId" class="oddcentre" titleKey="CMN.CHEQUE_NO" sortable="false" headerClass="datatableheader"/>
					
					<display:column property="commonDesc" class="oddcentre" titleKey="CMN.ADVICE_NO" sortable="false" headerClass="datatableheader"/>
					<display:column class="oddcentre" title="Date" sortable="false" headerClass="datatableheader"><input type="text" size="10" name='date_<c:out value="${VO.commonId}"/>' readonly="true" value="<c:out value="${VO.frmDate}"/>" >&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif"   width="20" onClick=window_open('date_<c:out value="${VO.commonId}"/>',375,570) ><input type="hidden" name="chequesData" value="<c:out value="${VO.commonId}"/>" ></display:column>
			</display:table> 
			
		<%
		if(request.getParameter("advFlg")!=null && request.getParameter("advFlg").equals("1"))
		{
			%>
			<center>
				<input type='button' name='btnClose' value="Close" onclick="javascript:insertdt(); window.close();" />
			</center>
			<%
		}
		else
		{
		%>	
		<br>	
		<table width="100%">
		<tr>
			<td class="datatableheader">
				<fmt:message key="CMN.VOCBILL" bundle="${billprocLabels}"></fmt:message>
			</td>
		</tr>		
	</table>
	<br>
	<display:table list="${voucherList}" pagesize="12" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				   	id="VO1" excludedParams="ajax" varTotals="totals" export="false" partialList="" style="width:100%">
					<display:setProperty name="paging.banner.placement" value="bottom"/>    					
					<display:column property="commonId" class="oddcentre" titleKey="CMN.BILL_CONTROL_NO" sortable="false" headerClass="datatableheader"/>
					<display:column property="commonDesc" class="oddcentre" titleKey="CMN.VOUCHER_NO" sortable="false" headerClass="datatableheader"/>
					
			</display:table> 
	<br>
	
	<center>
		<input type='button' name='btnClose' value="Ok" onclick='dispatchCheques()' />
	</center>
	<%
	}
	%>
</hdiits:form>
</body>
</html>
