<%
	try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.ess.UserDsgnLables" var="userDsgnLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="dataList" value="${resValue.recordList}"> </c:set>
<c:set var="fieldList" value="${resValue.fieldCaptionList}"> </c:set>	
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>
<c:set var="orgDsgnDtlList" value="${resValue.orgDsgnDtlList}"></c:set>
<c:set var="dsgnName" value="${resValue.dsgnName}"></c:set>
<html>
<head>
<script type="text/javascript">
function addNewEntry()
{
	document.forms[0].action='hdiits.htm?actionFlag=addAdminDsgnDtl';
	document.forms[0].submit();
}
function editDsgnRecord(dsgnCode){
	
	document.forms[0].action="hdiits.htm?actionFlag=editAdminOrgDsgnData&dsgnCode="+dsgnCode;
	document.forms[0].submit();		
}

function deleteData()
{	
	var isChecked = false;
	 
	for (var i = 0; i < document.forms[0].deletedata.length; i++) 
	{
   			if (document.forms[0].deletedata[i].checked) 
   			{
   				
     			isChecked = true;
  			}
	}
	if(document.frmAdminCrtDsgn.deletedata.checked)
	{	
		isChecked = true;
	}
	if(isChecked )
	{
		
		var answer=confirm("<fmt:message bundle='${userDsgnLables}' key='ORG.deleteChk'/>");
		if(answer)
		{
			 
			document.forms[0].action='hdiits.htm?actionFlag=deleteAdminOrgDsgnData';
			document.forms[0].submit();
		}
	}
	else
	{
	
		alert("<fmt:message bundle='${userDsgnLables}' key='ORG.seleChkBox'/>");
		 
	}
}
function searchDesignation()
{
	document.frmAdminCrtDsgn.action='hdiits.htm?actionFlag=showAdminDsgnDtl';
	document.frmAdminCrtDsgn.submit();
}
</script>
</head>
<body>
<c:set value="1" var="i"></c:set>
<hdiits:form name="frmAdminCrtDsgn" action="" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<fmt:message key="ORG.showDsgnDtl" bundle="${userDsgnLables}"></fmt:message>
		</b></a></li>		
	</ul>
	</div>
	<div id="createPost" name="createPost" class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<table width="50%" align="center">
		<tr>
			<td align="right">
				<hdiits:caption captionid="ORG.dsgnName" bundle="${userDsgnLables}"/>
			</td>
			<td align="center">
				<hdiits:text id="txtDsgnName" name="txtDsgnName" captionid="ORG.dsgnName" bundle="${userDsgnLables}" default="${dsgnName}"/>
			</td>
			<td align="center" style="display: none">
				<hdiits:text id="dummyTxtBox" name="dummyTxtBox" />
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td align="center">
				<hdiits:button name="btnSearch" id="btnSearch" type="button" captionid="ORG.search" bundle="${userDsgnLables}" onclick="searchDesignation()"/>
			</td>
		</tr>
	</table>	
			<display:table pagesize="10" name="${dataList}" id="row"  export="false" offset="1" requestURI="hrms.htm?actionFlag=showAdminDsgnDtl" style="width:100%" decorator="com.tcs.sgv.ess.decorator.AdminOrgDsgnDtlDecorator">
			
				<display:setProperty name="paging.banner.placement" value="bottom"/>
				
				<display:column style="text-align: center;" class="tablecelltext" titleKey="ORG.srNo" headerClass="datatableheader">
					<c:out value="${i}"></c:out>
				</display:column>
				<c:forEach var="fld" items="${fieldList}">
			 	   <display:column style="text-align: center;"  class="tablecelltext" sortable="true" titleKey= "${fld[0]}"  headerClass="datatableheader" value="${dataList[i-1]}"/>
			    </c:forEach>
		
			    <display:column style="text-align: center;" property="link2" class="tablecelltext" media="html" titleKey="ORG.action" headerClass="datatableheader" >	    	
			    </display:column>
		
				<display:setProperty name="export.pdf" value="true" />
				<c:set var="i" value="${i+1}"></c:set>
			</display:table>
		
		<table width="100%">
			<tr align="center" width="100%">
				<td align="center" width="100%">
					<hdiits:button name="addNewEntry_button" captionid="ORG.addNewEntry" bundle="${userDsgnLables}" onclick="addNewEntry()" type="button"/>
					<c:if test="${not empty dataList}">
						<hdiits:button captionid="ORG.delete" bundle="${userDsgnLables}" onclick="deleteData()" name="cmdDel2" type="button"/>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
	</div>
<hdiits:validate locale="${locale}" controlNames="" />
<script type="text/javascript">	
	initializetabcontent("maintab")
</script>
<hdiits:hidden id="multiLang" name="multiLang" default="${multiLang}"/>
<hdiits:hidden id="multiLangHdn" name="multiLangHdn" default="${multiLang}"/>	
</hdiits:form>
</body>
</html>
<%
	}
	catch(Exception e) {
		e.printStackTrace();
	}

%>
