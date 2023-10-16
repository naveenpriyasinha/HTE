<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="BillGroupList" value="${resValue.BillGroupList}"></c:set>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/ddoBillGroup.js"></script>
<%--START added by samadhan--%>
<script>
function validateSchemeForBillGroup()
{
	var e = document.getElementById("cmbSchemeName");
	var schemeDesc= e.options[e.selectedIndex].text;
	
	 
	
	var schemeCode=document.getElementById("txtSchemeCode").value;
	var subSchemeCode=document.getElementById("txtSubSchemeCode").value;
	//alert('inside validateSchemeForBillGroup()'+schemeDesc+"  schemeCode "+schemeCode);
	//alert(schemeCode);
	if(schemeCode!=-1){

		var uri = 'ifms.htm?actionFlag=checkSubBillGroupLimit';
		var url = 'schemeCode='+schemeCode+'&subSchemeCode='+subSchemeCode;

		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						checkSchemeCount(myAjax,schemeCode,schemeDesc);
						
					},
			        onFailure: function()
			        			{ 
	  						alert('Something went wrong...');
	  					} 
			          } 
	);
	}

	
	
}

function checkSchemeCount(myAjax,schemeCode,schemeDesc){
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(checkFlag=='correct')
	{
		status= true;
		//alert('all ok');
	}
	else if(checkFlag=='wrong')
	{
		
		alert('For scheme '+schemeDesc+" ("+schemeCode+") , two sub bill groups are already created.");
		
		
		document.getElementById("cmbSchemeName").value="-1";	
		document.getElementById("txtDescription").value='';	
		document.getElementById("txtSchemeCode").value='';	
		status= false;
	}
	return status;
}


function setSubSchemeCode()
{
	document.getElementById("txtSubSchemeCode").value=document.getElementById("cmbSubSchemeName").value;
}


function getSubSchemeDetails() 
{ 
//        alert('inside getSubSchemeDetails '); 
        var txtSchemeCode = document.getElementById("txtSchemeCode").value; 
        
                var uri = 'ifms.htm?actionFlag=getSubSchemeDetails'; 
                var url = 'txtSchemeCode='+txtSchemeCode+'&level=1'; 
                
                var myAjax = new Ajax.Request(uri, 
                               { 
                                method: 'post', 
                                asynchronous: false, 
                                parameters:url, 
                                onSuccess: function(myAjax) { 
                                                getDataStateChangedForPopUpSchemes(myAjax); 
                                        }, 
                                onFailure: function(){ alert('Something went wrong...')} 
                                  } ); 
        
} 


</script>
<%--END added by samadhan--%>
<style>/* Added By Pratik 03-08-23 */
table#tblBillDetl tr td:first-child, table#tblBillDetl tr td:nth-child(3)
	{
	width: 15% !IMPORTANT;
}
</style>
<hdiits:form name="frmDdoGroupBill" encType="multipart/form-data" validate="true" method="post">
<c:set var="space" value="   "></c:set>
<fieldset class="tabstyle"><legend> 
	                  <fmt:message key="CMN.ALREADYADDEDBILLGROUPS" bundle="${dcpsLables}"></fmt:message></legend>
	                  </br>
	                  <div align="left">
	               		<display:table list="${BillGroupList}" id="vo" requestURI=""  style="width:50%" pagesize="5">
								<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" sortable="true"  titleKey="CMN.BILLGROUPID">
										<a href=# onclick="popUpBillGroupDtls('${vo[0]}');">${vo[0]}</a>		
					          	</display:column>		
								
								<c:choose>
									<c:when test="${vo[2]==0}">
										<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left" sortable="true"  titleKey="CMN.DESCRIPTION">
												<label id="billGroupDescription"><c:out value="${vo[1]}"></c:out></label>			 
										</display:column>
									</c:when>
									<c:otherwise>
										<c:set var="space" value="   "></c:set>
										<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;padding-left: 10%" sortable="true"  titleKey="CMN.DESCRIPTION">
												<label id="billGroupDescription"><c:out value="${vo[1]}"></c:out></label>			 
										</display:column>
									</c:otherwise>
								</c:choose>	
								
								<display:setProperty name="paging.banner.placement" value="bottom" />	
						</display:table>  
					  </div>
	                  
</fieldset>	                  
<br/>

<fieldset class="tabstyle"><legend> 
	                  <fmt:message key="CMN.CREATEBILLGROUP" bundle="${dcpsLables}"></fmt:message></legend>
	
 
<table id="tblBillDetl" width="100%" border = "0" align="left" cellpadding = "4" cellspacing = "4">	

		<tr>
				<td width="15%" align="left"><fmt:message key="CMN.SCHEMENAME"
					bundle="${dcpsLables}"></fmt:message>
					</td>
				<td width="35%" align="left"><select name="cmbSchemeName" id="cmbSchemeName" style="width: 360px" onChange="displaySchemeNameForCode();getSubSchemeDetails();validateSchemeForBillGroup();" >
						<c:forEach var="schemeName" items="${resValue.lListSchemes}" >
																	<option value="${schemeName.id}" title="${schemeName.desc}" ><c:out 
																			value="${schemeName.desc}"></c:out></option>
						</c:forEach>
						</select>
						<label class="mandatoryindicator">*</label>
				</td>
				
				
				<td width="15%" align="left"><fmt:message key="CMN.SCHEMECODE"
						bundle="${dcpsLables}"></fmt:message>
						</td>		
				<td width="35%" align="left"><input type="text" name='txtSchemeCode' id="txtSchemeCode" maxlength="10" style="text-align: left;" readonly="readonly" size=32 /></td><!--size changed 40 to 32 by Pratik 03-08-23  -->
				
		</tr>	
		
		
		
		    <tr> 
	           <td width="100%" align="left"><fmt:message key="CMN.SUBSCHEMENAME"
				   bundle="${dcpsLables}"></fmt:message>
				   </td>
		      <td width="35%" align="left"><select name="cmbSubSchemeName" id="cmbSubSchemeName" size="1px" onchange="setSubSchemeCode();validateSchemeForBillGroup();" >
		
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT"/></option>
					<option value="0"></option>
				
		</select>
		<input type="hidden" name="demandCode" id="demandCode">
		<label class="mandatoryindicator">*</label>
	</td>		
				
				<td width="15%" align="left"><fmt:message key="CMN.SUBSCHEMECODE"
						bundle="${dcpsLables}"></fmt:message>
						</td>		
				<td width="35%" align="left"><input type="text" name='txtSubSchemeCode' id="txtSubSchemeCode" maxlength="10" style="text-align: left;" readonly="readonly" size=32 /></td>
		</tr>
		
		
		<tr>
				<td width="15%" align="left"><fmt:message key="CMN.BILLGROUPID"
						bundle="${dcpsLables}"></fmt:message>
						</td>
				<td width="35%" align="left"><input type="text" name='txtBillGroupNo' id="txtBillGroupNo" maxlength="6" style="text-align: left" size=48 readonly="readonly"/></td>
				
				<input type="hidden" name="hidSubBGOrNot" id="hidSubBGOrNot" value="" />
				<input type="hidden" name="hidEmpsInBGOrNot" id="hidEmpsInBGOrNot" value="" />
				<input type="hidden" name="hidContrisInBGOrNot" id="hidContrisInBGOrNot" value="" />
				
				<input type="hidden" name="hidVctPostInBGOrNot" id="hidVctPostInBGOrNot" value="" />
		
				<td width="15%" align="left"><fmt:message key="CMN.DESCRIPTION"
						bundle="${dcpsLables}"></fmt:message>
						</td>
				<td width="35%" align="left"><input type="text" name='txtDescription' id="txtDescription" maxlength="100" style="text-align: left" size=32  /><!--size changed 48 to 32 by Pratik 03-08-23  -->
				</td>
		</tr>
		<tr>	
				<td width="15%" align="left"><fmt:message key="CMN.TYPEOFPOST"
						bundle="${dcpsLables}"></fmt:message>
				</td>
				<td width="85%" align="left" colspan="3">
				<input type="radio" name="RadioPermenantTempBoth" id="RadioPermenantTempBothP" value="P" >Permanent
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="RadioPermenantTempBoth" id="RadioPermenantTempBothT" value="T">Temporary
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="RadioPermenantTempBoth" id="RadioPermenantTempBothS" value="S">Statutory
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="RadioPermenantTempBoth" id="RadioPermenantTempBothB" value="B" checked>All
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				
				</td>
				
		</tr>
	
		<tr>
				<td width="15%" align="left"><fmt:message key="CMN.GROUP"
						bundle="${dcpsLables}"></fmt:message>
				</td>
				<td align="left" colspan="3" width="85%">
						<table>
								<tr>
									<td width="100%" align="left">
											<c:forEach var="ClassGroup" items="${resValue.lListClassGroups}">
													<c:choose>
														<c:when test="${ClassGroup.lookupDesc == 'NA'}">
															<input type="checkbox" name="Group" id="Group${ClassGroup.lookupDesc}" value="${ClassGroup.lookupDesc}" onClick="disable();" readonly="readonly">${ClassGroup.lookupDesc}
														</c:when>
														<c:otherwise>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="Group" id="Group${ClassGroup.lookupDesc}" checked value="${ClassGroup.lookupDesc}" onclick="disableNA();">${ClassGroup.lookupDesc}
														</c:otherwise>
													</c:choose> 
									        </c:forEach>
						&nbsp;&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
									</td> 
								</tr>
						</table>
				</td>
				
				
		</tr>
</table>
</fieldset>	

<br/>

<div align="center">
			<hdiits:button name="btnSave" id="btnSave" type="button"  captionid="BTN.SAVE" bundle="${dcpsLables}" style="display: none"  onclick="SaveDataAfterValidation(1);"/>
			<hdiits:button name="btnCreateAndSave" id="btnCreateAndSave" type="button"  captionid="BTN.SAVE" bundle="${dcpsLables}" onclick="SaveDataAfterValidation(2);"/>
			<hdiits:button name="btnDelete" id="btnDelete" type="button"  captionid="BTN.DELETE" bundle="${dcpsLables}" onclick="deleteBillGroup();"  />
			<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>
</div>
		
</hdiits:form>