<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>' />
<%@page import="java.util.Map"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.tcs.sgv.common.valueobject.MstObjection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script language="javascript">
		function checkUncheckAll(theElement) 
		{
		     var theForm = theElement.form, z = 0;
			 for(z=0; z<theForm.length;z++)
			 {
			      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect')
			      {
					  theForm[z].checked = theElement.checked;
				  }
		     }
	    	 for(z=0; z<theForm.length;z++)
	    	 {      
			      if(theForm[z].type == 'select-one')
			      {
			      }
		  	 }
	     }
	     
		function selectCheck()
		{
			//alert("Inside select Check");
			indx = document.frmObjectionDtls.objCode.value;
			//alert("Inside selectcheck() Index value : " +indx);
		}
		function submitObjection(url)
		{
//			document.frmObjectionDtls.action ='ifms.htm?actionFlag=getObjectionDetails';
			//alert("URL in submit form L:  " + url);
			document.frmObjectionDtls.action =url;
//			document.frmObjectionDtls.submit();
			//alert("After submitting data to objecitons");
			return true;
			
		}
		function SubmitObjData(url)
		{
			if(confirm('Are you sure you want to raise objections?'))
			{
				if(submitObjection(url) == true)
				{	
//					for(var i=0;i<2000000;i++);
					var objList='';
					for(i=0;i<document.frmObjectionDtls.elements.length;i++)
				    {
				        if(document.frmObjectionDtls.elements[i].type=="checkbox" && document.frmObjectionDtls.elements[i].name != 'chkSelect')
				        {	
			    	      if(document.frmObjectionDtls.elements[i].checked == true)
			        	  {
			          		objList = objList+'~'+document.frmObjectionDtls.elements[i].value;
				          }
				        } 
				    }    
					window.opener.document.forms[0].chkbox.value = objList;
					self.close();
				}
			}
		}
		function callFunction(indx)
		{
			document.getElementById(indx).checked=true;			
		}
</script>
</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="ObjectionList" value="${resValue.ObjectionList}" scope="request"></c:set>
<%@page import="java.util.ResourceBundle"%>
<%
		ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<body>
<hdiits:form name="frmObjectionDtls" validate="true" method="post">
	<%
		System.out.println("Value of Bill no in objection details : " +request.getParameter("billCntrlNo"));
	%>
	<br>
	<table align="center" width="90%">	
		<tr>
			<td >
				<fmt:message key="CMN.ObjForBill" bundle="${billprocLabels}"></fmt:message>
			</td>
			<td>
				<input type="textbox" name="txtBillNo" value='<%=request.getParameter("billCntrlNo")%>' size="25" readonly="true">
			</td>
		</tr>
	</table>
	<br>

	<display:table list="${ObjectionList}" pagesize="12" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				   id="VO" excludedParams="ajax" varTotals="totals" export="true" partialList="" style="width:100%">
					<display:setProperty name="paging.banner.placement" value="bottom"/>    
					<display:column class="oddcentre" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this);'/>" headerClass="datatableheader" >
					<input name="chkbox" value="${VO.objectionCode}" type="checkbox" id="${VO.objectionCode}"/> </display:column>
					<c:out value="-->${VO.objectionCode}"/>
					<display:column property="objectionCode" class="oddcentre" titleKey="ADT.OBJCODE" sortable="true" headerClass="datatableheader" />
					<display:column property="objectionDesc" class="oddcentre" titleKey="ADT.OBJDESC" sortable="true" headerClass="datatableheader"  />			
	</display:table>
	<table align="center" width="90%">
		
		<tr>
			<td colspan="3" align="center">
				<hdiits:button type="button" name="btnSelect" value='<%=buttonBundle.getString("COMMON.SELECT")%>' onclick="javascript:SubmitObjData('ifms.htm?actionFlag=getObjectionDetails')" />
				<hdiits:button type="button" name='<%=buttonBundle.getString("CHQCOMMON.CLOSE")%>' value="Close" onclick="window.close();"/>
			</td>
		</tr>
	</table>
		<script>
	
		var objlist = window.opener.document.forms[0].chkbox.value;
		for(i=0;i<document.frmObjectionDtls.elements.length;i++)
				    {
				        if(document.frmObjectionDtls.elements[i].type=="checkbox" && document.frmObjectionDtls.elements[i].name != 'chkSelect')
				        {
				        	var chkVal=document.frmObjectionDtls.elements[i].value;
				        	
				        	if(objlist.indexOf(chkVal)>=0)
				        	{
				        		document.frmObjectionDtls.elements[i].checked = true;
				        	}
				        }
				     }
		</script>
</hdiits:form>
</body>
</html>
