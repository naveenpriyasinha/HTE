<%try{ %>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels_en_US" var="pensionLabels" scope="request"/>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/pensionpay/PensionConfig.js"></script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="HeadCodeConfigDtls" value="${resValue.lLstHeadCodeConfigDtls}"></c:set>
<c:set var="Index" value="1"></c:set>
<c:set var="counter" value="1"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="TotalHeadCode" value="${resValue.lLstHeadCode}"></c:set>
<c:set var="TotalHeadCodeNo" value="${resValue.TotalHeadCodeNo}"></c:set>
<hdiits:form name="HeadCodeConfig" encType="multipart/form-data" id="HeadCodeConfig"
	validate="true" method="post">
	<input type="hidden" name="hidTotalHeadCode" id="hidTotalHeadCode" value="${TotalHeadCode}"></input>
	<input type="hidden" name="hidHeadCodeTotalNo" id="hidHeadCodeTotalNo" value="${TotalHeadCodeNo}"></input>
	<!-- <input type="hidden" name="hidHeadCode" id="hidHeadCode" ></input>  -->
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PPMT.SUBCATEGORYCONFIG"
	bundle="${pensionLabels}"></fmt:message></b></legend>
<!-- txtHeadCode -->
<table width="50%" align="left">
	<tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.MAINCATEGORY" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		   <select name="cmbMainCategory" id="cmbMainCategory" style="width:60%;">
			<c:forEach var="ListOfMainCategory" items="${resValue.lLstMainCtgryConfigDtls}">
        		<option value="${ListOfMainCategory.id}">
					<c:out value="${ListOfMainCategory.desc}"></c:out>									
				</option>
			</c:forEach>
		</select>
		   <label class="mandatoryindicator">*</label>
		</td>
  </tr>

  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.PNSNRCATAGORY" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		   <input type="text" id="txtPnsnrCtgry"  size="20" maxlength="25" name="txtPnsnrCtgry" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		   <label class="mandatoryindicator">*</label>
		</td>
  </tr>
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.DESCRIPTION" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		 <input type="text" id="txtDescription"  size="40"  name="txtDescription" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		 <label class="mandatoryindicator">*</label>
		</td>
</tr>


<tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.PENSIONSCHEMECODE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		   <input type="text" id="txtPensionSchemeCode"  size="20"  name="txtPensionSchemeCode" onfocus="onFocus(this)"  onblur="onBlur(this);checkSchemeCode(this);"/>
		   <label class="mandatoryindicator">*</label>&nbsp;&nbsp;
		   <a href="#" id="txtPensionSchemeCode" onclick="showSchemeCode(this);"><img src="images/search.gif" /></a>
		   
		</td>
  </tr>
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.CVPSCHEMECODE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		   <input type="text" id="txtCvpSchemeCode"  size="20"  name="txtCvpSchemeCode" onfocus="onFocus(this)"  onblur="onBlur(this);checkSchemeCode(this);"/>
		   <label class="mandatoryindicator">*</label>&nbsp;&nbsp;
		   <a href="#" id="txtCvpSchemeCode" onclick="showSchemeCode(this);"><img src="images/search.gif" /></a>
		</td>
  </tr>
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.DCRGSCHEMECODE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		   <input type="text" id="txtDcrgSchemeCode"  size="20"  name="txtDcrgSchemeCode" onfocus="onFocus(this)"  onblur="onBlur(this);checkSchemeCode(this);"/>
		   <label class="mandatoryindicator">*</label>&nbsp;&nbsp;
		   <a href="#" id="txtDcrgSchemeCode" onclick="showSchemeCode(this);"><img src="images/search.gif" /></a>
		</td>
  </tr>   
</table>
</fieldset>


<div>&nbsp;</div>
<table align="center">
<tr>
<td width="30%" align="center" >
<hdiits:button name="btnSave"	type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveHeadCodeConfig();"/>&nbsp;&nbsp;&nbsp;&nbsp;
<hdiits:button name="btnDelete"	type="button" captionid="PPMT.DELETE" bundle="${pensionLabels}" onclick="deleteHeadCodeConfig();" />&nbsp;&nbsp;&nbsp;&nbsp;
<hdiits:button name="btnClose"	type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
</td>
</tr>
</table>
<div>&nbsp;</div>
<div style="width:100%;overflow:auto;height:100%;" >

<display:table list="${HeadCodeConfigDtls}" id="vo" cellpadding="5" style="width:100%" pagesize="100" export="false" partialList="true"  offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending"  >
				
	<display:setProperty name="paging.banner.placement" value="bottom" />		
	 <display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>"
			headerClass="datatableheader" style="width:1%"  class="oddcentre" >
			<input type="checkbox" align="middle" name="SelectAll"
				id="checkbox${counter}"  value="${vo[0]}" />
	</display:column>	
	
	<display:column titleKey="PPMT.MAINCATEGORY" headerClass="datatableheader" class="oddcentre"
			sortable="true" style="width:10%;text-align:center" >
			<label id="lblPnsnrMainCtgry${counter}">${vo[8]}</label>
	</display:column>
	
	<display:column titleKey="PPMT.SUBCATEGORY" headerClass="datatableheader" class="oddcentre"
			sortable="true" style="width:10%;text-align:center" >
			<label id="lblPnsnrCtgry${counter}">${vo[7]}</label>
	</display:column>
			
	<display:column titleKey="PPMT.DESCRIPTION" headerClass="datatableheader" class="oddcentre"
			sortable="true" style="width:10%;text-align:left">
			<label id="lblDescrptn${counter}">${vo[3]}</label>
	</display:column>	
			
	<display:column titleKey="PPMT.PENSIONSCHEMECODE" headerClass="datatableheader" class="oddcentre" 
			sortable="true" style="width:10%;text-align:center" >
			<label id="lblPnsnSchemeCode${counter}">${vo[4]}</label>
	</display:column>	
				
	<display:column titleKey="PPMT.CVPSCHEMECODE" headerClass="datatableheader" class="oddcentre" 
			sortable="true" style="width:10%;text-align:center" >
			<label id="lblCvpSchemeCode${counter}">${vo[5]}</label>
	</display:column>
	
	<display:column titleKey="PPMT.DCRGSCHEMECODE" headerClass="datatableheader" class="oddcentre" 
			sortable="true" style="width:10%;text-align:center" >
			<label id="lblDcrgSchemeCode${counter}">${vo[6]}</label>
	</display:column>
	
	<display:column titleKey="PPMT.UPDATE" headerClass="datatableheader" class="oddcentre" 
			sortable="true"  style="width:10%;text-align:center">
		<input type="button" name="btnUpdate"	id="btnUpdate${counter}" onclick="moveHeadCodeConfig('${counter}','Update');" value="Update" />
		<input type="hidden" id="hidMstHeadCodePk${counter}" name="hidMstHeadCodePk" value="${vo[0]}"/>
		<input type="hidden" id="hidRltHeadCodePk${counter}" name="hidRltHeadCodePk" value="${vo[1]}"/>
		<label style ="display: none"  id="lblHeadCode${vo_rowNum}">${vo[2]}</label>		
	</display:column>
	
	<c:set var="counter" value="${counter+1}"></c:set>
	
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}" />
<input type="hidden" id="gString" name="gString" value=""/>
</div>

</hdiits:form>
<%}
catch(Exception e)
{
	e.printStackTrace();
}%>