
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="../core/include.jsp"%>

<script>
	document.getElementById("banner").src = "images/HomePageImages/FianlHomePG_1_11.jpg";
</script>

<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<script type="text/javascript" src="script/common/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/dcps/dcpsSrka.js"></script>

<script type="text/javascript" src="script/dcps/dcpsDDO.js"></script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels"
	scope="request" />
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts"
	scope="request" />

<hdiits:form name="DDOEmpDeselect" id="DDOEmpDeselect"
	encType="multipart/form-data" validate="true" method="post">
	
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.DESELECTEMPLIST}"></c:set>
<c:set var="postExpFlag" value="${resValue.postExpFlag}"></c:set>
<c:set var="postEndDate" value="${resValue.postEndDate}"></c:set>
<c:set var="pranActive" value="${resValue.pranActive}"></c:set>


	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message
		key="CMN.SEARCHEMP" bundle="${dcpsLabels}"></fmt:message></b> </legend>
	<table>
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="PRAN.PRANNO"
				bundle="${dcpsLabels}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtPranNo" style="text-transform: uppercase" size="30"
				name="txtPranNo" onblur="validate"/></td>
		</tr>
	<%-- By Viru 	<tr>
			<td colspan="2" align="center"><label style="color: red"><fmt:message
				key="MSG.OR" bundle="${dcpsLabels}" /></label></td>
		</tr>
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
				bundle="${dcpsLabels}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtEmployeeName" size="30" name="txtEmployeeName" onkeypress="alphaFormatWithSpace(this);" onblur="isName(this,'This field should not contain any special characters or Number');"/> <span
				id="roleIndicatorRegion" style="display: none"> <img
				src="./images/busy-indicator.gif" /></span></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><label style="color: red"><fmt:message
				key="MSG.OR" bundle="${dcpsLabels}" /></label></td>
		</tr>
			<tr align="center">
			<td width="25%" align="left">DCPS ID</td>
			<td width="50%" align="left"><input type="text"
				id="txtDcpsId" size="30" name="txtDcpsId" /> <span
				id="roleIndicatorRegion" style="display: none"> <img
				src="./images/busy-indicator.gif" /></span></td>
		</tr> --%>
		
			<!-- <tr align="center">
			<td width="25%" align="left">PPAN NO.</td>
			<td width="50%" align="left"><input type="text"
				id="txtPpanNo" size="30" name="txtPpanNo" /> <span
				id="roleIndicatorRegion" style="display: none"> <img
				src="./images/busy-indicator.gif" /></span></td>
		</tr> -->

		
		<tr>
			<td colspan="2" align="center"><label style="color: red"><fmt:message
				key="MSG.MYSEARCHACTDEACTIVATE" bundle="${dcpsLabels}" /></label></td>
		</tr>
	</table>
	<div style="width: 50; text-align: center; align: center"><hdiits:button
		name="btnSearch" style="align:center" type="button"
		captionid="CMN.SEARCH" bundle="${dcpsLabels}"
		onclick="getAllEmp();" /></div>
	</fieldset>

	<br>
	
<div id="displayData">
<c:if test="${postExpFlag != null}">
<c:if test="${postExpFlag == '0'}">
<script>
alert("Post end date for selected employee is already passed. Post end date is :-  ${postEndDate}");
</script>
</c:if>
<c:if test="${postExpFlag != '0'}">

	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message key="CMN.UPDATEPRAN"
		bundle="${dcpsLabels}"></fmt:message></b> </legend> <input type="hidden"
		name="hdnCounter" id="hdnCounter" value="0" /> <input type="hidden"
		name="currDate1" id="currDate1" value="${resValue.gDtCurDate}" />
		
		<div class="scrollablediv" style="width: 100%; overflow: auto; height: 200px;">

	
			 <c:if test="${VOList != null}">
		
			<display:table list="${VOList}" id="vo" cellpadding="5" style="width:100%" requestURI="">
			
			

<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:8%"  sortable="true"
			titleKey="PRAN.SRNO">
			<c:out value="${vo_rowNum}" />
		</display:column>


<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:12%" sortable="true"
			titleKey="CMN.EMPLOYEENAME">
			<c:out value="${vo[0]}" />
		</display:column>


<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:15%"  sortable="true"
			titleKey="PRAN.DCPSID">
			<c:out value="${vo[1]}" />
		</display:column>


<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:8%" sortable="true"
			titleKey="CMN.SEVARTHID" >
			<c:out value="${vo[2]}" />
			<input type="hidden" id="seva_id" value="${vo[2]}" />
		</display:column>


<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:10%"  sortable="true"
			titleKey="PRAN.PPANNO">
			<c:out value="${vo[6]}" />
		</display:column>


<display:column headerClass="datatableheader" class="oddcentre" 
			style="text-align:center;width:10%"  sortable="true"
			titleKey="PRAN.DESIG">
			<c:out value="${vo[3]}" />
		</display:column>


<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:9%" sortable="true"
			titleKey="PRAN.DOJ">
			<c:out value="${vo[4]}" />
		</display:column>


<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:9%" sortable="true"
			titleKey="PRAN.DOB">
			<c:out value="${vo[5]}" />
		</display:column>
<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:9%" sortable="true"
			title="DDO Code">
			<c:out value="${vo[8]}" />
		</display:column>


  <display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:9%"  sortable="true"
			titleKey="PRAN.PRANNO">
			<c:out value="${vo[7]}" />
			<input type="hidden" id="pranNoForUpdate" value="${vo[7]}" />
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:9%" sortable="true"
			title="Previous Updated Date">
			 <c:out value="${vo[14]}" /> 
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"  
			style="text-align:center;width:9%" sortable="true"
			title="Pran Remark">
			<c:choose>
			<c:when test="${fn:length(VOList)  <= 1}">
			<hdiits:text captionid="rem" id="remarkTxt" name="remarkTxt" mandatory="true"    />
			</c:when>
			<c:otherwise>
			<input type="text" id="remarkTxt" name="remarkTxt" mandatory="true"  readonly="readonly"  value="${vo[12]}" > 
			</c:otherwise>
			</c:choose>
		    <%-- <c:out value="${vo[12]}" />  --%> 
			 <input type="hidden" id="existRemark" value="${vo[12]}" /> 
		</display:column>
		
	<%--by viru	
<display:column headerClass="datatableheader" style="text-align:center; width:10%"  
class="oddcentre" title="Update"  titleKey="PRAN.UPDATE" >
	<input type="button" id="update" value="Update" onclick="getPranNo()" onblur="isNumberForPran()" class="buttontag" />
	</display:column>  --%>
						

</display:table> 

	</c:if>
</div>
</fieldset>
</c:if>
<center><table>
<tr>
<c:if test="${fn:length(VOList)  <= 1}">
<c:if test="${pranActive=='0'}"> 
<td>
<div style="width: 50; text-align: center; align: center"><hdiits:button
 name="active" id="1"  style="align:center" type="button"
		captionid="CMN.ACT" bundle="${dcpsLabels}"
		onclick="updateFlag(this.id);" /></div>
</td>
 </c:if> 
 <c:if test="${pranActive=='1'}"> 
<td>
<div style="width: 50; text-align:center ; align: center"><hdiits:button
		 id="0"  name="deactive"  style="align:center" type="button"
		captionid="CMN.DEACT" bundle="${dcpsLabels}"
		onclick="updateFlag(this.id);" /></div>
</td>
 </c:if> 
 </c:if>
</tr>
</table></center>
</c:if>
</div>
	<!--<div style="width:50;text-align: center;align:center">
			<hdiits:button name="btnDeselect" id="btnDeselect" classcss="bigbutton" type="button" readonly="true" captionid="CMN.DDOEMPDESELECTBTN" bundle="${dcpsLabels}" onclick="dcpsDDOEmpDeselect();" />
			<hdiits:button name="btnGenLPC" id="btnGenLPC" classcss="bigbutton" type="button" value="Generate LPC" onclick="billGeneratedOrNot();" />-->




	<input type="hidden" name="hidSearchFromDDODeSelection"
		id="hidSearchFromDDODeSelection" value="searchFromDDODeSelection" />

</hdiits:form>



<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameAutoCompleteForNps"
	parameters="searchKey={txtEmployeeName},searchBy={hidSearchFromDDODeSelection}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />
	
<script type="text/javascript">



function digitFormat1(formfield)
{	var val;
//alert(window.event.keyCode);
	if(window.event.keyCode>47 && window.event.keyCode<58)
	{
		val=formfield.value;
		if(val[1]!=null)
		{
			if(val[1].length>1)
			{
				window.event.keyCode=0;
			}
		}
	}
	else
	{
		window.event.keyCode=0;
	}
}


function isNumberForPran(field)
{
	//alert("helloo***");
  var str=field.value;
  if (!str || trim(str)=="") { return  true; }
  re1 = /[^a-z^0-9\/\." "]/gi;
  if(str.search(re1) < 0)
  {
	//  alert("helloooo in if");
  	return true;
  }
  else
  {
	//  alert("helloooo in else");
  	alert("paste not allowed");
  	field.value='';
  	field.focus();
  	return false;
  }
  //return (str.search(re1) < 0 ? true : false);
}



function alphaFormatWithSpace(formfield)
{	
	var val;
	
	if((window.event.keyCode == 32) || (window.event.keyCode>64 && window.event.keyCode<91) || (window.event.keyCode>96 && window.event.keyCode<123)) 
	{
		val=formfield.value;
		if(val[1]!=null)
		{
			if(val[1].length>1)
			{
				window.event.keyCode=0;
			}
		}
	}
	else
	{
		window.event.keyCode=0;
	}
}

// Exist Code For Check Pran No
   function getAllEmp()
	{

		var txtPranNo =  document.getElementById("txtPranNo").value.trim();
	/* By Viru 	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim();
		var txtDcpsId = document.getElementById("txtDcpsId").value.trim();
		var txtPpanNo = "";//document.getElementById("txtPpanNo").value.trim();
	 */	
		// boolean flag=true;
		if(txtPranNo!="" ){ //|| txtPpanNo!=""
		//var url="ifms.htm?actionFlag=checkPranNoForNps";
		var url="ifms.htm?actionFlag=checkPranNo";
		var uri ="&txtPranNo="+txtPranNo;
		var a=true;
		url=url+uri;
		
		 var myAjax = new Ajax.Request(url,
				   {
			           method: 'post',
			           asynchronous: false,
		                  parameters: uri,
		                  onSuccess: function(myAjax){
						a=getAllEmpAjax(myAjax);					
                           		   },
                       onFailure: function(){ alert('Something went wrong...');} 
			          } );		   
// Exist Code For Pran No and Some Parameter Removed By me
if(a==true){
			var urls ="ifms.htm?actionFlag=empListForActDeactPranNo&txtPranNo="+txtPranNo+"&fromSearch=Yes";
		showProgressbar('Please Wait<br>Your request is in progress...');
		document.DDOEmpDeselect.action = urls ;
		document.DDOEmpDeselect.submit();
}
		}
		else if( txtPpanNo=="")
		{
			alert('Please enter search criteria');
			return false;
		}

	}

function  getAllEmpAjax(myAjax){

	 var XMLDoc = myAjax.responseXML.documentElement;
	
		var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');
		
		var lBlFlag = XMLEntries[0].childNodes[0].firstChild.nodeValue; 	
	
		var div=document.getElementById('displayData');
		alert(lBlFlag);
	 		if(lBlFlag == 'NA')
	 		{
	 			alert(' Invalid PranNo');
	 			
	 			div.style.display = "none";
	 			document.getElementById("DDOEmpDeselect").reset();
	 		
	 			return false;
	 		}
	 		/* by viru else if(lBlFlag=='NA7')
	 		{
		 		alert("This employee does not have PPAN. Please update PPAN for the employee first.");
		 		div.style.display = "none";
		 		document.getElementById("DDOEmpDeselect").reset();
		 		return false;
	 		} 		 */
	 		else if(lBlFlag=='NA1')
	 		{
		 		alert("This employee is not  DCPS employee. Please check and confirm 'Date of Joining' for the same.");
		 		div.style.display = "none";
		 		document.getElementById("DDOEmpDeselect").reset();
		 		return false;
	 		}
	 		else if(lBlFlag=='NA4')
	 		{
		 		alert("This employee is not  DCPS employee. Please check and confirm 'Date of Joining' for the same.");
		 		div.style.display = "none";
		 		document.getElementById("DDOEmpDeselect").reset();
		 		return false;
	 		}
	 		
	 		/* else if(lBlFlag=='NA2')
	 		{
		 		alert("Employee Service has ended or Service End Date is not present");
		 		
		 		div.style.display = "none";
		 		document.getElementById("DDOEmpDeselect").reset();
		 		return false;
	 		} */
	 //		else if(lBlFlag=='NA3')
	 //		{
	//	 		alert("Employee is not SRKA/IAS/IPS/IFS employee");
	//	 		document.getElementById("DDOEmpDeselect").reset();
	//	 		div.style.display = "none";
	//	 		
		// 		return false;
	// 		}
	 		// by viru else 
	 		if(lBlFlag=='NA5')
	 		{
		 		 
		 		return true;
	 		
	 		}  
	 //		else if(lBlFlag=='NA6')
	 //		{
	//	 		alert("Employee does not belong to logged in TO");
	//	 		div.style.display = "none";
	//	 		document.getElementById("DDOEmpDeselect").reset();
	//	 		return false;
	// 		 }
	 		 else{
				return true;
	 		 }
	 		
	 		 
}
   
	function getPranNo()
 	 {
		 var PranNo = document.getElementById("pranNo").value.trim();  
 		   var txtSevaarthId = document.getElementById("seva_id").value.trim();
 		   if(PranNo.length!=12){
 	 		   alert("PRAN No. must be of 12 digits");
 	 		   document.getElementById("pranNo").value = '';
 	 		   return false;
 		   }
 		   if(PranNo.charAt(0)!='1')
			{
				alert('Please enter correct PRAN.');
	 	 		   document.getElementById("pranNo").value = '';
				return false;
			}
 		   if(PranNo=='000000000000'||PranNo=='111111111111'||PranNo=='222222222222'||PranNo=='333333333333'||PranNo=='444444444444'||PranNo=='555555555555'||PranNo=='666666666666'||PranNo=='777777777777'||PranNo=='888888888888'||PranNo=='999999999999'){
 	 		   alert("PRAN is not in  correct format");
 	 		   document.getElementById("pranNo").value = '';
 	 		   return false;
 		   }
 		   else {    
 			   var url = "";
 			   var uri = "ifms.htm?actionFlag=testPranNOForNps&pranno="+PranNo+"&txtSevaarthId="+txtSevaarthId;
 			   var myAjax = new Ajax.Request(uri,
 					   {
 				           method: 'post',
 				           asynchronous: false,
 			                  parameters: url,
 			                  onSuccess: function(myAjax){
	                  if( getDataStateChangedForCheckpranNO(myAjax))return true;
	                  else return false;
 	                             		   },
 	                         onFailure: function(){ alert('Something went wrong...');} 
 				          } );	
		          return true;	   
		          }
 		  
 	 }
	 function getDataStateChangedForCheckpranNO(myAjax)
  	 {	
  	var XMLDoc = myAjax.responseXML.documentElement;
	 	var XMLEntries = XMLDoc.getElementsByTagName('Flag');
	 	var lBlFlag = XMLEntries[0].childNodes[0].text; 
	 	var XMLEntries1 = XMLDoc.getElementsByTagName('EmpName');	
		var empName = XMLEntries1[0].childNodes[0].text; 
		var XMLEntries2 = XMLDoc.getElementsByTagName('DcpsId');
		var dcpsId = XMLEntries2[0].childNodes[0].text; 
  	 	if(lBlFlag == "true")
  	 		{
  	 			alert('The entered PRAN No is already mapped to employee having \nName : '+empName+' \nDcps Id : '+dcpsId);
  	 			document.getElementById("pranNo").value = '';
  	 			return false;
  	 		}
  	 	else
  	 	{
  	 		updatePranNo();
  	  	 }
	 		return true;
		}

function updateFlag(val)	
{ 
	
	
	 var remark = document.getElementById("remarkTxt").value.trim();
	 if( remark!=undefined && remark!=""){
		   remark=remark;
	   }
	  else{
		 remark=document.getElementById("existRemark").value;
	 } 
	 var pranNo=document.getElementById("pranNoForUpdate").value.trim();
	 var active =val;
	 var deactive = val;
	 if(active!=null && active!=="" && active!=undefined){
		 active=active;
}else {
	 active=deactive;
}
	 var url = "";
     var uri ="ifms.htm?actionFlag=updateFlagForActDeact&remark="+remark+"&active="+active+"&pranNo="+pranNo;
     var myAjax = new Ajax.Request(uri,
			   {
		           method: 'post',
		           asynchronous: false,
	                  parameters: url,
	                  onSuccess: function(myAjax){
    updatePranNoAjax(myAjax);
                        		   },
                    onFailure: function(){ alert('Something went wrong...');} 
		          } );		   
        
     
}
//}

function updatePranNoAjax(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
 	var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');
 	var lBlFlag = XMLEntries[0].childNodes[0].firstChild.nodeValue; 	
	 	if(lBlFlag == "Updated")
	 		{ 
	 			alert(" updated successfully!!");


	 	var url ="ifms.htm?actionFlag=activePranNoForNPS&elementId=90002699";
	 			//showProgressbar('Please Wait<br>Your request is in progress...');
	 			document.DDOEmpDeselect.action = url ;
	 			//enableAjaxSubmit(true);
	 			document.DDOEmpDeselect.submit();
	 			//return false;
	 		}
	return true;
}



 	 
function isIntegers(s,msg)
{
	var i;
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (((c < "0") || (c > "9")))
        alert(msg);
        return false;
    }
    return true;
}


</script>


