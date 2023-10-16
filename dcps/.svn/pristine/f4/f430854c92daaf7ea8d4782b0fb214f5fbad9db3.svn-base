<%try{ %>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels_en_US" var="pensionLabels" scope="request"/>

<script type="text/javascript" src="script/pensionpay/PensionConfig.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="counter" value="1"></c:set>
<c:set var="MainCategoryConfigDtls" value="${resValue.lLstMainCategoryConfigDtls}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="MainCategoryIdList" value="${resValue.lLstMainCategoryId}"></c:set>
<hdiits:form name="MainCategoryConfig" encType="multipart/form-data" id="MainCategoryConfig" validate="true" method="post">
<input type="hidden" name="hidMainCategoryIdList" id="hidMainCategoryIdList" value= "${MainCategoryIdList}"/>
<fieldset style="width: 100%" class="tabstyle">
	<legend id="headingMsg"><b> <fmt:message key="PPMT.MAINCATEGORYCONFIG" bundle="${pensionLabels}"></fmt:message></b></legend>
	<input type="hidden" name="hidMainCatId" id="hidMainCatId" value="" />
		<table width="50%" align="left">
		<tr>
			<td width="20%" align="left" >
				<fmt:message key="PPMT.MAINCATEGORYDESC" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			   <input type="text" id="txtMainCtgryDesc"  size="20" maxlength="25" name="txtMainCtgryDesc" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
			   
			</td>
	 	</tr>

<tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.PENSIONSCHEMECODE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		   <input type="text" id="txtPensionSchemeCode"  size="20"  name="txtPensionSchemeCode" onfocus="onFocus(this)"  onblur="onBlur(this);checkSchemeCode(this);"/>
		   <a href="#" id="txtPensionSchemeCode" onclick="showSchemeCode(this);"><img src="images/search.gif" /></a>
		</td>
  </tr>
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.CVPSCHEMECODE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		   <input type="text" id="txtCvpSchemeCode"  size="20"  name="txtCvpSchemeCode" onfocus="onFocus(this)"  onblur="onBlur(this);checkSchemeCode(this);"/>
		   <a href="#" id="txtCvpSchemeCode" onclick="showSchemeCode(this);"><img src="images/search.gif" /></a>
		</td>
  </tr>
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.DCRGSCHEMECODE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		   <input type="text" id="txtDcrgSchemeCode"  size="20"  name="txtDcrgSchemeCode" onfocus="onFocus(this)"  onblur="onBlur(this);checkSchemeCode(this);"/>
		   <a href="#" id="txtDcrgSchemeCode" onclick="showSchemeCode(this);"><img src="images/search.gif" /></a>
		</td>
  </tr>   
</table>
</fieldset>


<div>&nbsp;</div>
<table align="center">
<tr>
<td width="30%" align="center" >
<hdiits:button name="btnSave"	type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveMainCategoryConfig();"/>&nbsp;&nbsp;&nbsp;&nbsp;
<hdiits:button name="btnDelete"	type="button" captionid="PPMT.DELETE" bundle="${pensionLabels}" onclick="deleteMainCategoryConfig();" />&nbsp;&nbsp;&nbsp;&nbsp;
<hdiits:button name="btnClose"	type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
</td>
</tr>
</table>
<div>&nbsp;</div>
<div style="width:100%;overflow:auto;height:100%;" >

<display:table list="${MainCategoryConfigDtls}" id="vo" cellpadding="5" style="width:100%" pagesize="100" export="false" partialList="true"  offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending"  >
				
	<display:setProperty name="paging.banner.placement" value="bottom" />
			
	<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>"
			headerClass="datatableheader" class="oddcentre" style="width:1%;" >
			<input type="checkbox" align="middle" name="SelectAll"
				id="checkbox${counter}"  value="${vo[0]}" />
	</display:column>
	
	<display:column titleKey="PPMT.MAINCATEGORYDESC" headerClass="datatableheader" class="oddcentre"
			sortable="true" style="width:10%;text-align:left">
			<label id="lblDescrptn${counter}">${vo[1]}</label>
	</display:column>	
			
	<display:column titleKey="PPMT.PENSIONSCHEMECODE" headerClass="datatableheader" class="oddcentre" 
			sortable="true" style="width:10%;text-align:center" >
			<label id="lblPnsnSchemeCode${counter}">${vo[2]}</label>
	</display:column>	
				
	<display:column titleKey="PPMT.CVPSCHEMECODE" headerClass="datatableheader" class="oddcentre" 
			sortable="true" style="width:10%;text-align:center" >
			<label id="lblCvpSchemeCode${counter}">${vo[3]}</label>
	</display:column>
	
	<display:column titleKey="PPMT.DCRGSCHEMECODE" headerClass="datatableheader" class="oddcentre" 
			sortable="true" style="width:10%;text-align:center" >
			<label id="lblDcrgSchemeCode${counter}">${vo[4]}</label>
	</display:column>
	
	<display:column titleKey="PPMT.UPDATE" headerClass="datatableheader" class="oddcentre" 
			sortable="true"  style="width:10%;text-align:center">
			
		<input type="hidden" id="hidMstPensionMainCategoryPk${counter}" name="hidMstPensionMainCategoryPk" value="${vo[0]}"/>
		<input type="button" name="btnUpdate"	id="btnUpdate${counter}" onclick="moveMainCategoryConfig('${counter}','Update');" value="Update" />
	</display:column>
	
	<c:set var="counter" value="${counter+1}"></c:set>
	
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}" />
<input type="hidden" id="hidString" name="hidString" value=""/>
</div>

</hdiits:form>
<%}
catch(Exception e)
{
	e.printStackTrace();
}%>