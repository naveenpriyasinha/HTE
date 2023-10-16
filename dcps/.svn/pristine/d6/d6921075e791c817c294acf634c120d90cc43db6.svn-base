<%@ include file="../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="SearchFieldName" value="${resultValue.strSearchFieldName}"></c:set>

<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables" scope="request"/>
<fmt:setBundle basename="resources.ess.noc.AlertMessages" var="NOCAlerts" scope="request"/>

<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/modalDialog.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/SearchCountry.js"/>"></script>		
<script language="javascript">

	var SearchCountryArray = new Array();
	SearchCountryArray[0]="<fmt:message bundle='${NOCAlerts}' key='CTRY.AtleastOne'/>";
	
	var searchCountryFileld ='${SearchFieldName}';	

	
	

</script>  

<hdiits:form name="frmSearch" validate="true" method="post">

	<table align="center">
		<tr>
			<td>
			</td>
			<td align="right" class=Label2>
				<hdiits:caption captionid="CTRY.SearchCtry" bundle="${NOCLables}"/>
			</td>
			<td>
				<hdiits:text name="txtSearch" size="15" maxlength="20"/>
				<hdiits:text name="txtSearch1" size="15" style="display : NONE"/>
				

			</td>
			<td>
				<img src="images/search_icon.gif" align="right" height="16" width="16" onclick="searchCountry();" />
				<img src="images/search_icon.gif" align="right" height="16" width="16" onclick="searchCountry();" />
			</td>
		</tr>
	</table>
	
	<fieldset class="tabstyle">
		<legend  id="headingMsg"><hdiits:caption captionid="CTRY.Ctry" bundle="${NOCLables}"/></legend>			
			<display:table list="${resultValue.ResultMap}" id="countryList" requestURI="" pagesize="10"  export="true" style="width:100%" offset="1">	
				<display:setProperty name="paging.banner.placement" value="bottom"/>
					
					<display:column class="oddcentre" headerClass="datatableheader" style="text-align:center"><input type="radio" name="chkBox" id="chkBox" value="${countryList.countryCode}"/></display:column>
					<display:column style="text-align: center;" class="tablecelltext" titleKey="CTRY.CtryName" headerClass="datatableheader" sortable="true" >${countryList.countryName}</display:column>	    
					
				<display:footer media="html"></display:footer>		
			</display:table>			
	</fieldset>
	
	<center>
		<hdiits:button  name="btnSelect" type="button" captionid="CTRY.Ok" bundle="${NOCLables}" onclick="selectCountry()"  />
		<hdiits:button  name="btnClose" type="button" captionid="CTRY.Close" bundle="${NOCLables}" onclick="window.close();"/>
	</center>

	
</hdiits:form>
	
<script type="text/javascript">

	setWindowName(window, document.frmSearch);

</script>	
	
								