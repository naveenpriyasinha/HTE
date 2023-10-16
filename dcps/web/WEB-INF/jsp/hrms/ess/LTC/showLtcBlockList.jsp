<%
try { 
%>
<%@ include file="../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<fmt:setBundle basename="resources.ess.ltc.AlertMessages" var="ltcAlert" scope="request"/>
<fmt:setBundle basename="resources.ess.ltc.LtcCaption" var="ltcLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ltcBlockList" value="${resultValue.ltcBlockList}"></c:set>


<script type="text/javascript">
	function addNewEntry()
	{
		document.forms[0].action='hdiits.htm?actionFlag=addEditLtcBlock&blockId=0';
		document.forms[0].submit();
	}
	function editRecord(blockId)
	{
		//alert("inside----");
		//alert("awardId----"+awardId);
		document.forms[0].action="hdiits.htm?actionFlag=addEditLtcBlock&blockId="+blockId;
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
		if(isChecked )
		{
			var answer=confirm('<fmt:message key="HR.deleteAlert" bundle="${ltcAlert}"/>');
			if(answer)
			{
				document.forms[0].action='hdiits.htm?actionFlag=getRecordsToDelete';
				document.forms[0].submit();
			}
		}
		else
		{
			alert('<fmt:message key="HR.selCheckBox" bundle="${ltcAlert}"/>');
		}
	}
	
	function closeWindow()
	{
		document.frmBlockMst.action = "hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	   	document.frmBlockMst.submit();
	}
</script>

<hdiits:form name="frmBlockMst" validate="true" method="post">
	<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected">
					<a href="#" rel="tcontent1"><b><hdiits:caption captionid="HRMS.blckMaster" bundle="${ltcLables}" captionLang="single"/> </b></a>
				</li>
			</ul>
	</div>
	<div class="tabcontentstyle">
	
	<div id="tcontent1" class="tabcontent" tabno="0">
		
		<c:set var="i" value="1" />		
			<display:table list="${ltcBlockList}" id="row" requestURI="" pagesize="10"  style="width:100%" offset="1" export="false" decorator="com.tcs.sgv.ess.ltc.decorator.LTCDecorator">
				<display:setProperty name="paging.banner.placement" value="bottom"/>
							
				<display:column class="tablecelltext" titleKey="HRMS.srNo" headerClass="datatableheader" style="text-align: center" sortable="true" >${i}</display:column>
				
				<display:column class="tablecelltext" titleKey="HRMS.blocktype"  headerClass="datatableheader" style="text-align: center" sortable="true" ><c:if test="${row.cmnLookupMstBlockType.lookupName eq 'Home Town'}"><fmt:message key="HRMS.HomeTown" bundle="${ltcLables}" /> </c:if><c:if test="${row.cmnLookupMstBlockType.lookupName eq 'LTC'}"><fmt:message key="HR.LTC" bundle="${ltcAlert}" /> </c:if></display:column>
				
				<display:column class="tablecelltext" titleKey="HRMS.Block" headerClass="datatableheader" style="text-align: center;" sortable="true" >${row.fromYear} - ${row.toYear}</display:column>
				
				<display:column property="link2" class="tablecelltext" media="html" titleKey="awd.actions" style="text-align: center;" headerClass="datatableheader"></display:column>
				
				<c:set var="i" value="${i+1}" />
				<display:footer media="html"></display:footer>		
				
			</display:table>
		
		<br><br>
		<table align="center">
		<tr>
			<td>
				<hdiits:button name="addNewEntry_button" captionid="HRMS.addNew" bundle="${ltcLables}" style="width:100px" onclick="addNewEntry()" type="button"/>
			</td>
			
			<td>
				<hdiits:button name="btnClose" type="button" captionid="HR.Close" bundle="${ltcAlert}" style="width:100px" onclick="closeWindow();" />
			</td>
		</tr>
		</table>
		</div>	
	</div>
</hdiits:form>	
<script type="text/javascript">
			initializetabcontent("maintab")
		</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>