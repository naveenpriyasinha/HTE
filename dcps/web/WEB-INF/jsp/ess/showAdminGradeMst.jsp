<%
try {
%>

<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<fmt:setBundle basename="resources.ess.OrgGradeMstLabels" var="CommonLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="dataList" value="${resValue.recordList}"> </c:set>
<c:set var="fieldList" value="${resValue.fieldCaptionList}"> </c:set>	
<c:set var="orgGradeMstList" value="${resValue.orgGradeMstList}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>
<c:set var="gradeName" value="${resValue.gradeName}"></c:set>

<html>
<head>
<script type="text/javascript" src="script/ess/orgGrademstAdmin.js"></script>

<script>
	var selectCheckBoxalert = "<fmt:message bundle='${CommonLables}' key='GRADE.CHECKBOX'/>";
	//alert('select chekc');
</script>
</head>
<body>
<c:set value="1" var="i"></c:set>

<hdiits:form name="frmAdminCrtGradeMst" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<fmt:message key="GRADE.VIEWGRADES" bundle="${CommonLables}"></fmt:message>
		</b></a></li>		
	</ul>
	</div>

	<div id="createGrade" name="createGrade" class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		
		<table width="50%" align="center">
		<tr>
			<td align="right">
				<hdiits:caption captionid="GRADE.gradeName" bundle="${CommonLables}"/>
			</td>
			<td align="center">
				<hdiits:text id="txtGradeName" name="txtGradeName" captionid="GRADE.gradeName" bundle="${CommonLables}" default="${gradeName}"/>
			</td>
		</tr>
	</table>
		
	<table align="center">
		<tr>
			<td align="center">
				<hdiits:button name="btnSearch" id="btnSearch" type="button" captionid="GRADE.Search" bundle="${CommonLables}" onclick="searchGrade()"/>
			</td>
		</tr>
	</table>
		
	<display:table pagesize="10" name="${dataList}" id="row"  export="false" offset="1" requestURI="hrms.htm?actionFlag=showAdminGradeMst" style="width:100%" decorator="com.tcs.sgv.ess.decorator.AdminOrgGradeMstDecorator">
			
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
		<display:column style="text-align: center;" class="tablecelltext" titleKey="srNumber" headerClass="datatableheader">
			<c:out value="${i}"></c:out>
		</display:column>
		<c:forEach var="fld" items="${fieldList}">
	 	   <display:column style="text-align: center;" class="tablecelltext" sortable="true" titleKey= "GRADE.gradeName" headerClass="datatableheader" value="${dataList[i-1]}"/>
	    </c:forEach>

	    <display:column style="text-align: center;" property="link2" class="tablecelltext" media="html" titleKey="Actions" headerClass="datatableheader" >	    	
	    </display:column>

		<display:setProperty name="export.pdf" value="true" />
		<c:set var="i" value="${i+1}"></c:set>
	</display:table>
	
	<table width="100%">
		<tr align="center" width="100%">
			<td align="center" width="100%">
				<hdiits:button name="addNewEntry_button" captionid="GRADE.AddRec" bundle="${CommonLables}" onclick="addNewEntry()" type="button"/>
				<hdiits:button captionid="GRADE.Delete" bundle="${CommonLables}" onclick="deleteData()" name="cmdDel2" type="button"/>
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
}catch(Exception e) {e.printStackTrace();}
%>