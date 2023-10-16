<% 
try{
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="allHolidatData" value="${resValue.allHolidatData}"> </c:set>
<c:set var="searchForHoliday" value="${resValue.searchForHoliday}"> </c:set>
<c:set var="searchTypeValue" value="${resValue.searchType}"></c:set>
<fmt:setBundle basename="resources.common.MstScrLables" var="mstScrLables" scope="request" />

<html>
<head>	
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script type="text/javascript">

function setDeleteFlag(){


	var isChecked = false;
	if(document.setCmnHolidayMst.deletedata != null)//added by uzma
	{
		for (var i = 0; i < document.setCmnHolidayMst.deletedata.length; i++) 
		{
	   			if (document.setCmnHolidayMst.deletedata[i].checked) 
	   			{
	     			isChecked = true;
	  			}
		}
		if(document.setCmnHolidayMst.deletedata.checked)
		{	
			isChecked = true;
		}
		if(isChecked )
		{
			var answer = confirm('<fmt:message  bundle="${mstScrLables}" key="MstScr.confirmDelete"/>');
			if(answer)
			{
				showProgressbar();
				document.setCmnHolidayMst.action='hdiits.htm?actionFlag=getHolidayMstData&flag=deleteHoliday';
				document.setCmnHolidayMst.submit();
			}
		}
		else
		{
			alert('<fmt:message  bundle="${mstScrLables}" key="Holiday.seleChkBox"/>');
		}
	}
	
}

function getCmnHolidayMstList(){	
	showProgressbar();
	document.setCmnHolidayMst.action='hdiits.htm?actionFlag=getHolidayMstData&flag=search';
	document.setCmnHolidayMst.submit();
	
}

function editHolidayRecord(holidayId)
{
	showProgressbar();
	document.getElementById('holidayId').value = holidayId;
	document.setCmnHolidayMst.action='hdiits.htm?actionFlag=getHolidayMstData&flag=edit';
	document.setCmnHolidayMst.submit();		
}

function createNewHoliday(){
showProgressbar();
document.setCmnHolidayMst.action='hdiits.htm?actionFlag=getHolidayMstData&flag=newHoliday';
document.setCmnHolidayMst.submit();

}

</script>

</head>
<body onkeypress="return checkSpecialCharacter(event)">
<c:set value="1" var="i"></c:set>
<hdiits:form name="setCmnHolidayMst" action="" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				<b>Holiday Master</b>
			</a>
		</li>		
	</ul>
	</div>
		<div class="tabcontentstyle" style="height: 100%">
	
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
	<table align="center" id="districtSearchTable" width="50%">
	<tr>
	<td align="right"><hdiits:caption  captionid="MstScr.searchBy" bundle="${mstScrLables}"/></td>
	<td align="center">
		<hdiits:select id="searchType" name="searchType" captionid="MstScr.searchBy" bundle="${mstScrLables}" sort="false" default="${searchTypeValue}">
			<hdiits:option value="Occ"><fmt:message key="Holiday.Occ" bundle="${mstScrLables}"></fmt:message></hdiits:option>
			<hdiits:option value="OccType"><fmt:message key="Holiday.OccType" bundle="${mstScrLables}"></fmt:message></hdiits:option>
		</hdiits:select>
	</td>
	<td align="center"><hdiits:text name="searchForHoliday" id="searchForHoliday" default="${searchForHoliday}"/></td>
	</tr>
	</table>
	<table align="center">
	<tr>
	<td align="center"><hdiits:button type="button" name="searchButton" caption="Search" onclick="getCmnHolidayMstList()"/></td>
	</tr>
	</table>
	<br>
	<c:set var="checkAll">
    	<hdiits:checkbox name="allbox" captionid="MstScr.selectClear" value="" bundle="${mstScrLables}" onclick="selectAllClearAll(this)" />
	</c:set>
	
	<display:table pagesize="15" name="${allHolidatData}" id="row"  export="false" offset="1" style="width:100%" requestURI="hrms.htm?actionFlag=showHolidayMst" decorator="com.tcs.sgv.common.decorator.CmnHolidayMstDecorator">
			
				<display:setProperty name="paging.banner.placement" value="bottom"/>
		
				<display:column style="text-align: center;width:15%" class="tablecelltext" title="${checkAll}" headerClass="datatableheader" property="checkBox">	
				</display:column>
				
			 	<display:column style="text-align: center;" class="tablecelltext"  property="link2"  titleKey= "Holiday.Occ" headerClass="datatableheader">${row.hldyOccsn}</display:column>
				<display:column style="text-align: center;" class="tablecelltext"  titleKey= "Holiday.OccType" headerClass="datatableheader">${row.cmnLookupMst.lookupDesc} </display:column>
				<fmt:formatDate value="${row.hldyDt}" var="hldyDt" pattern="dd/MM/yyyy"/>
			 	<display:column style="text-align: center;" class="tablecelltext"  titleKey= "Holiday.OccDate" headerClass="datatableheader">${hldyDt}</display:column>
			       
				<display:setProperty name="export.pdf" value="true" />
				<c:set var="i" value="${i+1}"></c:set>
	</display:table>
	<table align="center">
	<tr>
		<td><hdiits:button name="AddHoliday" type="button" captionid="MstScr.addNew" bundle="${mstScrLables}"  onclick="createNewHoliday()"/></td>
		<td><hdiits:button name="DeleteHoliday" type="button" captionid="MstScr.delete" bundle="${mstScrLables}" onclick="setDeleteFlag()" /></td>
		<td><hdiits:button captionid="MstScr.close" bundle="${mstScrLables}" onclick="closeWindow()" name="closeBtn" type="button"/></td>
	</tr>
	</table>
	
	
	</div>
	</div>
	
	
	
	<script type="text/javascript">		
		initializetabcontent("maintab");
		document.getElementById("tcontent1").focus();
	</script>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
	<hdiits:hidden name="holidayId" id="holidayId" default="0"/>

</hdiits:form>	
</body>
</html>
	
	
<%	
}catch(Exception e){
	
	out.println(e);
	e.printStackTrace();
}
%>