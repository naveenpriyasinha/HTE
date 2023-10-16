<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>' />
<%@page import="java.util.Map"%>

<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<%@page import="java.util.HashMap"%>
<%@page import="com.tcs.sgv.common.valueobject.MstParty"%>
<%@page import="com.tcs.sgv.common.valueobject.MstBank"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
			var j=0;
			for(i=0;i < document.frmObjectionDtls.elements.length;i++)
			{
				if(document.frmObjectionDtls.elements[i].type =="checkbox")
				{
					if(document.frmObjectionDtls.elements[i].checked)
					{		
						partId =document.frmObjectionDtls.elements[i].value;
		    			values = partId.split("~");
		    			if('<%=request.getParameter("Index")%>'=='-1')
		    			{
		    			window.opener.document.forms[0].txtPartyNameNew.value=values[0];
		    			window.opener.document.forms[0].txtAddress.value=values[1];
		    			window.opener.document.forms[0].txtAccountNo.value=values[2];
		    			window.opener.document.forms[0].partyCode.value=values[3];
		    			}
		    			else
		    			{
						eval("window.opener.document.forms[0].txtPartyName"+indx).value=values[0];
    	        		eval("window.opener.document.forms[0].txtAddress"+indx).value=values[1];
        	    		eval("window.opener.document.forms[0].txtAccountNo"+indx).value=values[2];
        	    		eval("window.opener.document.forms[0].partyCode"+indx).value=values[3];
        	    		}
        	    		j++;
        	    		break;
					}
				}
			}	
			if(j==0)
			{
				alert('Select atleast one party');
				return false;
			}
		   
			window.close();
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
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="PartyList" value="${resValue.PartyList}"
	scope="request">
</c:set>
<c:set var="bankPartyList" value="${resValue.bankPartyList}"
	>
</c:set>
<%@page import="java.util.ResourceBundle"%>
<%
ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>


<body>

<%
			ResultObject resObj = (ResultObject)request.getAttribute("result");
			Map resultValue = (Map)resObj.getResultValue();		
			String lStrIndex = (String)resultValue.get("Index");
			String lStrDdo = (String)request.getParameter("ddo");
			System.out.print("lStrDdo ::::::::::::::::::::::::::::::::::::::::::::::::"+lStrDdo);
			ArrayList lStrTempDdo = new ArrayList();
			if(lStrDdo.equals("y"))
			{

				String lStrDdoNames= (String) request.getParameter("ddos");
				System.out.println("lStrDdoNames :::::: " + lStrDdoNames);
				String lStrDdoName [] = lStrDdoNames.split("~");
				System.out.print(" lStrDdoName Length is :::::"+lStrDdoName.length);
				lStrTempDdo.add(lStrDdoName[0]);
				for(int i=1;i<lStrDdoName.length;i++)
				{
					int k=0;
					for(int j=0;j<lStrTempDdo.size();j++)
					{
						if(lStrTempDdo.get(j).equals(lStrDdoName[i]))
						{
							k=1;
							break;
						}
					}
					if(k==0)
					{
						lStrTempDdo.add(lStrDdoName[i]);
					}
				}
			}
		
	
%>	

<hdiits:form name="frmObjectionDtls" validate="true" method="post">

<% if(lStrDdo.equals("n")) { %>	
			<display:table list="${sessionScope.PartyList}" pagesize="12" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				   	id="VO" excludedParams="ajax" varTotals="totals" export="true" partialList="" style="width:100%">
					<display:setProperty name="paging.banner.placement" value="bottom"/>    
					<display:column class="oddcentre" title="" headerClass="datatableheader" ><input name="chkbox" value="${VO.partyName}~${VO.partyAddr}~${VO.accntNum}~${VO.partyCode}" type="checkbox"/> </display:column>	    
					<display:column property="partyName" class="oddcentre" titleKey="CHQ.PARTYNAME" sortable="true" headerClass="datatableheader"/>
					<display:column property="partyAddr" class="oddcentre" titleKey="CHQ.PARTYADDRESS" sortable="true" headerClass="datatableheader"/>
					
	  				<display:footer media="html">
					</display:footer>
	 		</display:table> 
	 		<display:table list="${bankPartyList}" pagesize="12" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				   	id="VO" excludedParams="ajax" varTotals="totals1" export="true" partialList="" style="width:100%">
					<display:setProperty name="paging.banner.placement" value="bottom"/>    
					<display:column class="oddcentre" title="" headerClass="datatableheader" ><input name="chkbox" value="${VO.branchName}~~~" type="checkbox"/> </display:column>	    
					<display:column property="branchName" class="oddcentre" title="Bank Branch Name" sortable="true" headerClass="datatableheader"/>
	  				<display:footer media="html">
					</display:footer>
	 		</display:table> 
<% } else { int inde=0; %>	 		
			<display:table list="<%=lStrTempDdo%>" pagesize="12" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				   	id="VO" excludedParams="ajax" varTotals="totals" export="true" partialList="" style="width:100%">
				   	
					<display:setProperty name="paging.banner.placement" value="bottom"/>    
					<display:column class="oddcentre" title="" headerClass="datatableheader" ><input name="chkbox" value="<%=lStrTempDdo.get(inde)%>~~~" type="checkbox"/> </display:column>	    
					<display:column class="oddcentre" title="Party Name" sortable="true" headerClass="datatableheader">
					<%=lStrTempDdo.get(inde)%>
					<% inde++; %>
					</display:column>
					
	  				<display:footer media="html">
					</display:footer>
	 		</display:table> 
<% } %>	 		
	<table align="center" width="90%">
			
		
		<tr>
			<td>
			
			</td>
		</tr>
		<script>
			var strIndx = '<%=lStrIndex%>';
		</script>
		<tr>
			<td colspan="3" align="center">
				<hdiits:button type="button" name="Select" value='<%=buttonBundle.getString("COMMON.SELECT")%>' onclick="javascript:selectParty(strIndx);" />&nbsp;
				<hdiits:button type="button" name="Close" value='<%=buttonBundle.getString("COMMON.CLOSE")%>' onclick="javascript:window.close();" />
			</td>
		</tr>
	</table>
</hdiits:form>
</body>
</html>
