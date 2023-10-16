<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/dcpsTreasury.js"></script>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="AdminDept" value="${resValue.listAdminDept}"></c:set>
<c:set var="Districts" value="${resValue.lLstDistricts}"></c:set>
<c:set var="OfficeId" value="${resValue.OfficeId}"></c:set>
<c:set var="DummyOfficeList" value="${resValue.DummyOfficeList}"></c:set>
<c:set var="Index" value="1"></c:set>
<c:set var="counter" value="1"></c:set>

<hdiits:form name="dummyOfficeDsply" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle"><legend> 
		<fmt:message key="CMN.DEPTNOFFICEMSTENTRY" bundle="${dcpsLables}"></fmt:message> </legend>
			<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
			<div align="center">
	             <display:table list="${DummyOfficeList}" id="vo" cellpadding="4" style="width:60%;" pagesize="5" >
	             
	              <display:setProperty name="paging.banner.placement" value="bottom" />
	              
	              <display:column style="text-align: center;" class="oddcentre" title="Sr.No" headerClass="datatableheader"
		            value="${Index}" > 	
		           <c:set var="Index" value="${Index+1}"> </c:set>	           	          
	              </display:column>
	              	
	              <display:column  style="text-align:left" title= "Office Code" headerClass="datatableheader" class="oddcentre"
	               value="${vo[0]}">			      
		          </display:column>		          		         
	              	              	              
	              <display:column style="text-align: left;"  title="Office Name" headerClass="datatableheader" class="oddcentre"
		           value="${vo[1]}"> 		           	          
	              </display:column>
	              
	              <display:column style="text-align:left;"  title="Status" headerClass="datatableheader" class="oddcentre">
	               		<c:if test="${vo[2] == 'Y'}"><c:out value="Active"></c:out></c:if>
	               		<c:if test="${vo[2] == 'N'}"><c:out value="Inactive"></c:out></c:if>
	               </display:column>
	             
	              <display:column  style="text-align:center" class="oddcentre" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>" headerClass="datatableheader">
	              
			      <input type="checkbox" id="checkbox${counter}" name="SelectAll" value="${vo[0]}" />
			      
			       			<script>
								document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
						 	</script>
			     			<c:set var="counter" value="${counter+1}"></c:set>
		          </display:column>
			</display:table>
			</div> 
<br/>
    
<div align="center">

<hdiits:button  name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
								bundle="${dcpsLables}" onclick="ReturnLoginPage();"/>  
<hdiits:button name="btnNew" id="btnNew" type="button" captionid="BTN.NEW" 
		               			bundle="${dcpsLables}" onclick="newDummyOffice();" />     

<hdiits:button name="btnEdit" id="btnEdit" type="button" captionid="BTN.EDIT" 
		               			bundle="${dcpsLables}" onclick="editDummyOffice();" />     
</div>
                                               
</fieldset>  
</hdiits:form>