<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="schoolList" value="${resValue.schoolList}"></c:set>
<c:set var="inactivemsg" value="${resValue.inactivemsg}"></c:set>
<c:set var="finalCheckmsg" value="${resValue.finalCheckmsg}"></c:set>
<c:set var="vo_rowNum" value="1"></c:set>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<script>

function ShowEmployee()
{
	//alert('inside validateAndPopulateAstDDO');
	var SevaarthId=document.getElementById("txtSevaarthId").value;
	if(SevaarthId=="" || SevaarthId==null)
	{
		alert('Please enter Sevaarth Id of the Employee.');
		return false;
	}
	else
	{
		var uri = 'ifms.htm?actionFlag=loadEmpDetails';
		var url = 'empUserName='+SevaarthId;

		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getMsg(myAjax);
					},
			        onFailure: function()
			        			{ 
	  						alert('Something went wrong...');
	  					} 
			          } 
	);
	}
}


function getMsg(myAjax){
	//alert("hiii checdksdsd");
	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var msg = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	alert(msg);
	if(msg != ""){
		
		document.getElementById("txtSevaarthId").value = "";
			
	}
	
}
function Savedetails()
{
	var SevarthId=document.getElementById("txtSevaarthId").value;
	var uri = "ifms.htm?actionFlag=save6PCDetails";
	//alert("uri--"+uri);
	var url = "SevarthId="+SevarthId;
	myAjax = new Ajax.Request(uri,
			{
		method: 'post',
		asynchronous: false,
		parameters:url,
		onSuccess: function (myAjax) {
			getUpdateSuccess(myAjax);
		},
        onFailure: function(){ alert('Something went wrong...');} 
          } );

}
			 
			
		 
		 function getUpdateSuccess(myAjax){
			 var XMLDoc = myAjax.responseXML.documentElement;
				var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');	
				var Flag =  XMLEntries[0].childNodes[0].firstChild.nodeValue;
			
				if(Flag == 'Success'){
				alert('Basic details updated as per dated 01/01/2019');
					self.location.href="ifms.htm?viewName=conversionof7pcto6pc";
				}

				
			} 
		 function clearAll(){
			
					self.location.href="ifms.htm?actionFlag=GetUserType";
				}

			

</script>
<hdiits:form name="Conversion7to6" id="Conversion7to6" encType="multipart/form-data" validate="true" method="post">
<div class="">
<fieldset class="tabstyle"><legend> <b>Conversion of 7Pc to 6Pc </b> </legend>
<table width="100%" align="center">
<tr>
<td width="25%">Sevaarth Id:</td><td width="70%"> <input type="text" id="txtSevaarthId" name="txtSevaarthId" onblur="ShowEmployee();"> <label class="mandatoryindicator" ${varLabelDisabled}>  *</label> </td>
</tr>
</table>
<br><br>
<table width="50%" align="center">
<tr>
<td width="100%"><input type="button" value="Reset" class="buttontag" id="btnVerifyAndReset" onclick="Savedetails();">
		&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Close" class="buttontag" onclick="clearAll();" > 
		</td>
</tr>
</table>
</fieldset>
</div>
</hdiits:form>