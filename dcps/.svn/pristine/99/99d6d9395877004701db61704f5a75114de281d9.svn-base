<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ include file="//WEB-INF/jsp/common/cmnMsg.jsp"%>
<%
try
{%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<fmt:setBundle basename="resources.ess.asset.AssetAlertsMsg" var="as1" scope="request"/>
<fmt:setBundle basename="resources.ess.asset.AssetLables" var="al" scope="request"/> 
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hrms/ess/asset/assetPermission.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="script/hrms/ess/asset/assetAddress.js"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetValidation.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetAdminScreen.js"></script>
<script type="text/javascript"  src="script/hrms/ess/asset/assetJoining.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TypeofassetList" value="${resultValue.Typeofasset}"></c:set>
<c:set var="flag" value="${resultValue.flag}"></c:set>	
<c:set var="FixedList" value="${resultValue.AssetFixed}"></c:set>		
<c:set var="MovableList" value="${resultValue.AssetMovable}"></c:set>
<c:set var="empDoj" value="${resultValue.doj}"></c:set>
<c:set var="assetOwnerIdList" value="${resultValue.Asset_ownerDtls}"></c:set>
<c:set var="relationComboValue" value="${resultValue.relationComboValue}"></c:set>
<c:set var="familyList" value="${resultValue.familyList}"></c:set>
<c:set var="NoFamilyDtls" value="${resultValue.NoFamilyDtls}"></c:set>
<c:set var="AssetObtainTypeList" value="${resultValue.Asset_Obtain_Type}"></c:set>
<c:set var="joiningReqFlag" value="${resultValue.joiningReqFlag}"></c:set>
<c:set var="appliedUserId" value="${resultValue.appliedUserId}"></c:set>


<script language="javascript">
var declareJoiningAlertMsg=new Array();
declareJoiningAlertMsg[0]='<fmt:message  bundle="${as1}" key="ASSET.ANY_RELATION_PERSON"/>';
declareJoiningAlertMsg[1]='<fmt:message  bundle="${as1}" key="ASSET.SPECIFY_RELATION"/>';
declareJoiningAlertMsg[2]='<fmt:message  bundle="${as1}" key="ASSET.NAME_OF_PERSON"/>';
declareJoiningAlertMsg[3]='<fmt:message  bundle="${as1}" key="ASSET.ADD_OF_ASSET"/>';
declareJoiningAlertMsg[4]='<fmt:message  bundle="${as1}" key="ASSET.ADD_OF_OWNER"/>';



function getDate1()
   {
   		var dt= new Date();
   		var dd1=dt.getDate();
   		dd1 = (dd1 > 0 && dd1 < 10)? ("0"+dd1) : dd1;
   		var mm1=dt.getMonth()+1;
   		mm1 = (mm1 > 0 && mm1 < 10)? ("0"+mm1) : mm1;
   		var yy1=dt.getYear();
   		var sysDate=dd1+'/'+mm1+'/'+yy1;
   		//alert(sysDate);
   		var doj = "${empDoj}";
   		//alert(doj);
   		//sysDate = "12/07/2005";
   		var dateDiff = getDateDiffInString(doj,sysDate);
   		
   		trow= document.getElementById('totalServiceTable');
   		trow.cells[1].innerText=dateDiff;
   		
   		
}

function findNameAndAddressFromRelation(l,flag)
{
	var id = l.value;
	
	if(id == '')
	{
					return;
	}
	if(id == 0)
	{
		if(flag == 1)
		{
			document.getElementById("firstname1").value="";
			document.getElementById("middlename1").value="";
			document.getElementById("lastname1").value="";
			resetAddress('OwnerJoiningAddress');
		}
		if(flag == 2)
		{
			document.getElementById("InheritedFirstname").value="";
			document.getElementById("InheritedMiddlename").value="";
			document.getElementById("InheritedLastname").value="";
			resetAddress('OwnerJoiningAddress');
		}
	}
	else if(id != 0)
	{
		var nameAndRelation = id.split(" (");
		var relationType = nameAndRelation[0];
		
		var temp = nameAndRelation[1];
		var temp1 = temp.split(")");
		var firstName = temp1[0];
		//alert('firstName'+firstName);
		var familyList = "${familyList}";
		
		var list = familyList.substring(1,familyList.length-1);
		var lastList=list.split(",");
	//	alert(lastList.length);
		
			for( var i=0;i<lastList.length;i++)
			{
				if(lastList[i].indexOf(firstName)!=-1)
				{
					if(lastList[i] == ' '+firstName)
					{
						//	alert(i);
						var middleName = lastList[i+1];
						var lastName = lastList[i+2];
					//	alert(firstName);
					//	alert(middleName);
					//	alert(lastName);
						break;
					}
					else
					{	//alert('else');
						i++;
					}
				}			
			}  
			if(flag == 1)
			{
				if(firstName != null && firstName != '')
				{
					document.getElementById("firstname1").value = firstName;
					//document.form1.firstname1.readOnly = true;
				//	document.form1.firstname.disabled = true;
				}
				if(middleName != null && middleName != '')
				{
					document.getElementById("middlename1").value = middleName;
				//	document.form1.middlename1.readOnly = true;
				}
				if(lastName != null && lastName != '')
				{
					document.getElementById("lastname1").value = lastName;
				//	document.form1.lastname1.readOnly = true;
				}
			}
			if(flag == 2)
			{
				if(firstName != null && firstName != '')
				{
					document.getElementById("InheritedFirstname").value = firstName;
					//document.form1.firstname1.readOnly = true;
				//	document.form1.firstname.disabled = true;
				}
				if(middleName != null && middleName != '')
				{
					document.getElementById("InheritedMiddlename").value = middleName;
				//	document.form1.middlename1.readOnly = true;
				}
				if(lastName != null && lastName != '')
				{
					document.getElementById("InheritedLastname").value = lastName;
				//	document.form1.lastname1.readOnly = true;
				}
			}
			
		try{   
	    			xmlHttp=new XMLHttpRequest();	    	    
		    	}
				catch (e)
				{    // Internet Explorer    
						try{
	      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
	      				}
			    		catch (e){
			          		try
	        		  		{
	                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
	        		  		}
					      	catch (e)
					      	{
	       	   	  	           	  
				            	  return false;        
				      		}
				 	}
	        	}
	                     		
	        	if(flag == 1)
	        	{
					var url = "hdiits.htm?actionFlag=getEmpFamilyDtlsByName&name="+firstName +middleName +lastName;    
					xmlHttp.open("POST", encodeURI(url) , true);			
					xmlHttp.onreadystatechange = showFamilyMemberInfo;
					xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
					xmlHttp.send(encodeURIComponent(null));
				}	
				
		}		
}
function showFamilyMemberInfo()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var xmlStr = xmlHttp.responseText;
					//alert(xmlStr);
			    	var XMLDoc=getDOMFromXML(xmlStr); 			    
			    	
			    	xmlFileName1=XMLDoc.getElementsByTagName('Address');
			    	xmlFileName=xmlFileName1[0].childNodes[0].text;		
			    	    						
					try{   
		    			xmlHttp=new XMLHttpRequest();	    	    
			    	}
					catch (e)
					{    // Internet Explorer    
							try{
		      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
		      				}
				    		catch (e){
				          		try
		        		  		{
		                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
		        		  		}
						      	catch (e)
						      	{
		       	   	  	           	 
					            	  return false;        
					      		}
					 		}			 
		        	}	
					var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;   
					xmlHttp.open("POST", encodeURI(url) , true);			
					xmlHttp.onreadystatechange = showAddressDt;
					xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
					xmlHttp.send(encodeURIComponent(null));
					
				}
			}
		}

function showAddressDt()			    				    	
		{
			if (xmlHttp.readyState == 4) 
			{     		
				if (xmlHttp.status == 200) 
				{						
					var xmlStr = xmlHttp.responseText;
					//alert(xmlStr);
			    	var XMLDoc=getDOMFromXML(xmlStr);	
			    			    	
			    	var addrXPath = 'cmnAddressMst';
					editAddress('OwnerJoiningAddress',XMLDoc,addrXPath);
				//	makeEnableDisable("OwnerAddress",0);
				
						    						    	
				}							
			}
		}	
		
function addJoiningDtls()
{
startProcess();
window.setTimeout('submitForm_Submit("form1")',1000);

}
function submitForm_Submit(formNameValue)
{
	//joiningValidation();
	var validData = validateForm_form1();
	if( validData==true )
	{
		//window.document.forms[formNameValue].submit();
		addAllData();
		
	}
endProcess();
}

	
	
	/*var dt= new Date();
   	var dd1=dt.getDate();
   	dd1 = (dd1 > 0 && dd1 < 10)? ("0"+dd1) : dd1;
   	var mm1=dt.getMonth()+1;
   	mm1 = (mm1 > 0 && mm1 < 10)? ("0"+mm1) : mm1;
   	var yy1=dt.getYear();
   	var sysDate=dd1+'/'+mm1+'/'+yy1;
   	
   	var fromdate = document.form1.date_of_trans.value;
	if(compareDate(fromdate,sysDate) < 0 )
			{
				alert('<fmt:message  bundle="${as1}" key="ASSET.PURCHASED_DATE"/>');
				document.form1.date_of_trans.value = "";
				document.form1.date_of_trans.focus();
				return;
			}	*/
		


function addAllData()
{
		var assetAddressVal = assetAddressValidation();
		if(assetAddressVal == true  && document.form1.ObtainType[0].checked == true && document.getElementById('addInheritedDetailsTable').rows.length == 1)
		{
			var inheritedValidation = inheritedDtlValidation();
			
			if(inheritedValidation == true)
			{	
				if(document.form1.InheritedFirstname.value!="" || document.form1.InheritedLastname.value!=0)
				{
					
					alert('<fmt:message  bundle="${as1}" key="ASSET.PLEASE_ADD_RECORD"/>');
					return;
				}
				else{
				var ownerAddressVal = ownerAddressValidation();
				}
			}
			
		}	
		if(assetAddressVal == true  && document.form1.ObtainType[0].checked == true && document.getElementById('addInheritedDetailsTable').rows.length > 1)
		{
			if(document.form1.InheritedFirstname.value!="" || document.form1.InheritedLastname.value!=0)
			{
				
				alert('<fmt:message  bundle="${as1}" key="ASSET.PLEASE_ADD_RECORD"/>');
				return;
			}
			else{
			var ownerAddressVal = ownerAddressValidation();
			}
		
		}	
		if(assetAddressVal)
		{
			{
				var ownerAddressVal = ownerAddressValidation();
			}
		}	
		
		if(assetAddressVal == true && ownerAddressVal == true && document.getElementById('addInheritedDetailsTable').rows.length > 1)
		{
			
			document.getElementById('addInheritedDetailsTable').style.display = "none";
			if(document.getElementById("addButton").style.display == "")
			{
				joiningTimeAddData();
			}
			if(document.getElementById("updateButton").style.display == "")
			{
				updateJoiningDtls();
			}
		}
		else if(assetAddressVal == true && ownerAddressVal == true && document.form1.ObtainType[1].checked == true)
		{
			if(document.getElementById("addButton").style.display == "")
			{
				joiningTimeAddData();
			}
			if(document.getElementById("updateButton").style.display == "")
			{
				updateJoiningDtls();
			}
		}	
	
}


function submitAssetJoiningDtls(flag)
{	//alert(flag);
	if(document.getElementById("updateButton").style.display == "")
	{
		alert('<fmt:message  bundle="${as1}" key="ASSET.PLEASE_ADD_RECORD"/>');
		return;
	}
	var agree=confirm('<fmt:message  bundle="${as1}" key="ASSET.DO_WANT_SUBMIT"/>');
     	if (agree)
     	{
			var urlstyle="hdiits.htm?actionFlag=insertAssetJoiningDetails&flag="+flag;
			document.form1.action=urlstyle;
			document.form1.submit();
		}	
}

function addInheritedDetails()
{	
	if(inheritedDtlValidation())
	{
		addDataRelatedToInheritedDetails();
	}
}

function updateInheritedDetails()
{
	if(inheritedDtlValidation())
	{
		updateDataRelatedToInheritedDetails();
	}
}

</script>

<hdiits:form name="form1" validate="true" method="post" encType="multipart/form-data"  action="hdiits.htm"> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<c:if test="${flag == 'assetDeclarationsOnJoining'}">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_DECL_ON_JOINING" bundle="${al}"/></b></a></li>
	</c:if>
	<c:if test="${flag == 'assetBachmark'}">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_BANCHMARK" bundle="${al}"/></b></a></li>
	</c:if>
	</ul>
</div>
<div id="tcontent1" class="tabcontent" tabno="0">


<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<c:if test="${flag == 'assetDeclarationsOnJoining'}">
<hdiits:fieldGroup bundle="${al}" expandable="true" id="totalYearFldGrp" titleCaptionId="TOTAL_YEARS_OF_SERVICE">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="TOTAL_YEARS_OF_SERVICE" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>-->
<TABLE class=tabtable width="25%">  
	<tr>  
		<TD class=fieldLabel width="25%"><hdiits:caption captionid="GAZETTED"  bundle="${al}"/></td>
		<td width="25%">Not Applicable</td>    
		<td width="25%"></td>
		<td width="25%"></td>
	</tr>
	<tr>  
		<TD class=fieldLabel width="25%"><hdiits:caption captionid="NON_GAZETTED" bundle="${al}"/></td>
		<td width="25%">Not Applicable</td>  
		<td width="25%"></td>
		<td width="25%"></td>  
	</tr>
	<tr id="totalServiceTable">  
		<TD class=fieldLabel width="25%"><hdiits:caption captionid="TOTAL_SERVICE" bundle="${al}"/></td>
		<td width="25%">
			<script>getDate1();</script>
		</td> 
		<td width="25%"></td>
		<td width="25%"></td>
	</tr>
</TABLE>	
</hdiits:fieldGroup>
</c:if>	  

<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="ASSET_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
  </TABLE>-->
<c:if test="${joiningReqFlag == 'joiningReqNotSubmitted'}">
<hdiits:fieldGroup bundle="${al}" expandable="true" id="assetDtlsFldGrp" titleCaptionId="ASSET_DTLS">
<TABLE class=tabtable style="" id="assetDetails">
  <TBODY>
  	<TR>
	<td class=fieldLabel width="25%"><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}"/></td>
	<TD><hdiits:select  id="assettype"  name="assettype" sort="false" size="1" condition="" captionid="MOV_OR_NOT" bundle="${al}" tabindex="4" mandatory="true" validation="sel.isrequired" onchange="typeasset(this,'joiningDtl')">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="TypeofassetListLoc" items="${TypeofassetList}">
		<hdiits:option  value="${TypeofassetListLoc.lookupName}">${TypeofassetListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select></td>
	
	
	</TR>
 </table> 

<TABLE class=tabtable style="DISPLAY: none" id="movable">
  <tr>
 
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}"/></b></td>
	<td><hdiits:select name="property"  sort="false" size="1"  tabindex="7" mandatory="true" condition="valAssetMovable()" captionid="NAME_OF_PRPTY" bundle="${al}" validation="sel.isrequired" onchange="joiningSelectNo(this),selectother1(this)">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="MovableListLoc" items="${MovableList}">
		<hdiits:option  value="${MovableListLoc.lookupName}">${MovableListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select></td>
	<TD class=fieldLabel id=Name_other1 style="DISPLAY: none" width="60%"><hdiits:text name="other_movable" mandatory="true" condition="valOtherMovableAsset()" captionid="NAME_OF_PRPTY" bundle="${al}" validation="txt.isrequired,txt.isname" /></TD>
	
	
	<TD class=fieldLabel id=Registrationno style="DISPLAY: none" width="25%"><hdiits:caption captionid="REGI_NO" bundle="${al}"/></TD>
	<TD class=fieldLabel id=Registrationno1 style="DISPLAY: none" width="25%"><hdiits:text name="registration" mandatory="true" condition="valDeclRegNo()" captionid="REGI_NO" bundle="${al}" validation="txt.isrequired"/></TD>

	
  
</tr>
</TABLE>	

<TABLE class=tabtable style="DISPLAY: none" id="fixed">
  <tr>
	<TD class=fieldLabel width="25%" align="left"><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}"/></td>
	<td><hdiits:select name="asset" size="1" sort="false"  tabindex="4" mandatory="true" condition="valAssetFixed()" captionid="NAME_OF_PRPTY" bundle="${al}" validation="sel.isrequired"  onchange="selectother(this)">
	<hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	
	<c:forEach var="FixedListLoc" items="${FixedList}">
		<hdiits:option value="${FixedListLoc.lookupName}">${FixedListLoc.lookupDesc}</hdiits:option>
	</c:forEach>	
	</hdiits:select>
	</td>
	<TD class=fieldLabel id=Name_other style="DISPLAY: none" width="60%"><hdiits:text  name="other_fixed"  mandatory="true" condition="valOtherAsset()" captionid="NAME_OF_PRPTY" bundle="${al}" validation="txt.isrequired,txt.isname" /></TD>
	
	
	</TR>
	</TABLE>
	
<TABLE class=tabtable style="DISPLAY: none" id="addressofasset">
	<TR><TD>
		<hdiits:fmtMessage key="ADD_OF_ASSET"  bundle="${al}" var="AssetAddress" />
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="Address"/>
						<jsp:param name="addressTitle"  value="${AssetAddress}" />
						<jsp:param name="addrLookupName" value="Permanent Address"/>
						<jsp:param name="mandatory" value="Y" />						
	</jsp:include>	</TD>
  </TR>
</TABLE>
</hdiits:fieldGroup>
<hdiits:fieldGroup bundle="${al}" expandable="true" id="trnsDtlsFldGrp" titleCaptionId="TRAN_DTLS">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="TRAN_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>-->
<TABLE class=tabtable>  
	<tr>  
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="ASSET_OBTAIN_TYPE" bundle="${al}"/></td>
	<TD>
 	<c:forEach var="AssetObtainTypeListLoc" items="${AssetObtainTypeList}"> 					
	<hdiits:radio name="ObtainType"  value= "${AssetObtainTypeListLoc.lookupName}" captionid="ASSET_OBTAIN_TYPE" errCaption="How asset was obtained?"  mandatory="true" validation="sel.isradio" onclick="ChooseAssetObtainType(this,'${AssetObtainTypeListLoc.lookupDesc}')"/>
	<c:out value="${AssetObtainTypeListLoc.lookupDesc}"/>
	</c:forEach></td>
	</TR>
</TABLE>


<TABLE class=tabtable style="DISPLAY: none" id="transactionDetails">  
	<tr>  
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}"/></td>
	<td  class=fieldLabel width="25%"><hdiits:dateTime  name="date_of_trans" caption="dateTime1"  condition="joiningTranxnValidation()" mandatory="true"  captionid="DATE_OF_TRANS" bundle="${al}" validation="txt.isdt,txt.isrequired" ></hdiits:dateTime></td>      
	
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="APP_PRICE" bundle="${al}"/></td>
	<td  class=fieldLabel width="25%"><hdiits:number name="approxprice" maxlength="20"  mandatory="true" condition="joiningTranxnValidation()" captionid="APP_PRICE" bundle="${al}" validation="txt.isrequired"/></td>
  </TR>
</TABLE>

<TABLE class=tabtable style="DISPLAY: none" id="InheritedDetails">  
	<tr>  
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="ASSET_INHERITED_DATE" bundle="${al}"/></td>
	<td  class=fieldLabel width="25%"><hdiits:dateTime  name="date_of_Inherited" caption="dateTime1" mandatory="true" condition="joiningInheritedDetailsValidation()" captionid="ASSET_INHERITED_DATE" bundle="${al}"  validation="txt.isdt,txt.isrequired" ></hdiits:dateTime></td>      
	
	<TD class=fieldLabel width="25%"><hdiits:caption captionid="ASSET_INHERITED_PRICE" bundle="${al}"/></td>
	<td  class=fieldLabel width="25%"><hdiits:number name="approxprice_of_Inherited" maxlength="20"  captionid="APP_PRICE" bundle="${al}" /></td>
  </TR>
</TABLE>

<TABLE class=tabtable style="DISPLAY: none" id="InheritedDetailsLine">
  <TBODY>
  <TR bgColor=#386cb7>
    <TD class=fieldLabel align=middle colSpan=5><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    </TBODY>
</TABLE>

<TABLE class=tabtable style="DISPLAY: none" id="InheritedRelationDtls">
  <TBODY>
  <TR>	
  	<TD class=fieldLabel width="25%"><hdiits:caption captionid="ASSET_RELATION_INHERITED" bundle="${al}"/></TD>
  	<TD><hdiits:radio name="ifInheritedRelation" value="Y" mandatory="true"   captionid="YES" bundle="${al}" onclick="InheritedRelationYes()"/>
    <hdiits:radio name="ifInheritedRelation" value="N" mandatory="true" captionid="NO" bundle="${al}" onclick="InheritedRelationNo()"/></TD>
    
    <TD class=fieldLabel width="25%" id="InheritedRelationDisply" style="DISPLAY: none"><hdiits:caption captionid="SPECIFY_DTLS" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%" style="DISPLAY: none" id="InheritedRelationDisply1"><hdiits:select name="InheritedRelation"  sort="false"  captionid="SPECIFY_DTLS" bundle="${al}" mandatory="true"  onchange="findNameAndAddressFromRelation(this,2)">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="resValue2" items="${relationComboValue}">
		<hdiits:option value="${resValue2}">${resValue2}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
    
  </TR>
 </TBODY>
</TABLE> 

<TABLE class=tabtable style="DISPLAY: none" id="InheritedPersonName">
  <TBODY> 
  <TR>
    <TD class=fieldLabel width="25%"><b><hdiits:caption captionid="NAME_OF_PERSON" bundle="${al}"></hdiits:caption></b></TD>
    <TD class=fieldLabel width="25%"><hdiits:text name="InheritedFirstname" captionid="NAME_OF_PERSON" bundle="${al}" mandatory="true" validation="txt.isname" /><br><fmt:message key="FIRST_NAME"  bundle="${al}"></fmt:message></td>
    <TD class=fieldLabel width="25%"><hdiits:text name="InheritedMiddlename" captionid="NAME_OF_PERSON" bundle="${al}" onclick="" condition="" validation="txt.isname"/><br><fmt:message key="MIDDLE_NAME" bundle="${al}"></fmt:message></td>
    <TD class=fieldLabel width="25%"><hdiits:text name="InheritedLastname" captionid="NAME_OF_PERSON" bundle="${al}" mandatory="true" validation="txt.isname"/><br><fmt:message key="LAST_NAME" bundle="${al}"></fmt:message></td>
  </TR>
  </TBODY>
</TABLE> 

<TABLE class=tabtable style="DISPLAY: none" id="InheritedDetailsAddButton">
<tr>
	<td align="center">
	<hdiits:button  type="button" name="InheritedDetailsAdd"  captionid="ASSET_ADD" bundle="${al}" onclick="addInheritedDetails()"/>
	</td></tr> 
</table>

<TABLE class=tabtable style="DISPLAY: none" id="InheritedDetailsUpdateButton">
<tr>
	<td align="center">
	<hdiits:button  type="button" name="InheritedDetailsUpdate"  captionid="ASSET_UPDATE" bundle="${al}" onclick="updateInheritedDetails()"/>
	</td></tr> 
</table>

<table id="addInheritedDetailsTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="70%"> 
<br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="30%"><b><hdiits:caption captionid="NAME_OF_PERSON" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="20%"><b><hdiits:caption captionid="RELATION" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="ACTION" bundle="${al}" /></b></td>
</tr>
</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup bundle="${al}" expandable="true" id="ownrDtlsFldGrp" titleCaptionId="OWNER_DTLS">
<!--<TABLE class=tabtable>
<br>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="OWNER_DTLS" bundle="${al}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>-->
<TABLE class=tabtable>
  <TBODY>
  <TR> 
  	<TD class=fieldLabel width="25%"><hdiits:caption captionid="OWNER" bundle="${al}"/></TD>
    <TD><hdiits:select name="TypeOfOwner" sort="false" mandatory="true"  captionid="OWNER" bundle="${al}"  validation="sel.isrequired"  onchange="ownername(this)">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
    <c:forEach var="assetOwnerIdListLoc" items="${assetOwnerIdList}">
	<hdiits:option value="${assetOwnerIdListLoc.lookupName}">${assetOwnerIdListLoc.lookupDesc}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
	 </TR>
  </TBODY>
</TABLE>

<TABLE class=tabtable style="DISPLAY: none" id="ownerdetails">
  <TBODY>
  <TR>	
  	<TD class=fieldLabel width="25%"><hdiits:caption captionid="RELATION_EMP" bundle="${al}"/></TD>
  	<TD><hdiits:radio name="relationempwithowner" value="Y" mandatory="true" condition="valJoiningOwnerName()" validation="sel.isradio" errCaption="Any relation with employee" captionid="YES" bundle="${al}" onclick="relationempwithowneryes()"/>
    <hdiits:radio name="relationempwithowner" value="N" mandatory="true" validation="sel.isradio" captionid="NO" bundle="${al}" onclick="relationempwithownerno()"/></TD>
    
    <TD class=fieldLabel width="25%" id="ownerrelation" style="DISPLAY: none"><hdiits:caption captionid="SPECIFY_DTLS" bundle="${al}"/></TD>
    <TD class=fieldLabel width="25%" style="DISPLAY: none" id="ownerrelation1"><hdiits:select name="ownerrelation"  sort="false"  captionid="SPECIFY_DTLS" bundle="${al}" mandatory="true" condition="valJoiningOwnerSpecificRelation()" validation="sel.isrequired" onchange="findNameAndAddressFromRelation(this,1)">
    <hdiits:option value="0"><fmt:message key="DROPDOWN.SELECT" bundle="${al}" /></hdiits:option>
	<c:forEach var="resValue2" items="${relationComboValue}">
		<hdiits:option value="${resValue2}">${resValue2}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	</TD>
    
  </TR>
 </TBODY>
</TABLE> 
<TABLE class=tabtable style="DISPLAY: none" id="NoFamilyDtlForOwner">
<c:if test="${NoFamilyDtls == 'NoRecordFound'}">
  <tr align="center">
  <td class=fieldLabel  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <tr align="center">
  <td class=fieldLabel  width="25%" align="center"><b><FONT color="RED"><b><fmt:message key="IF_FILL_FAMILY_DTLS" bundle="${al}"></b></fmt:message></FONT></b></td>
  </tr>
  <script>
  	document.form1.ownerrelation.disabled=true;
  </script>
  </c:if>
</TABLE>    

<TABLE class=tabtable style="DISPLAY: none" id="ownerName">
  <TBODY> 
  <TR>
    <TD class=fieldLabel width="25%"><b><hdiits:caption captionid="NAME_OF_OWNER" bundle="${al}"></hdiits:caption></b></TD>
    <TD class=fieldLabel width="25%"><hdiits:text name="firstname1" captionid="NAME_OF_OWNER" bundle="${al}" mandatory="true" condition="valJoiningOwnerName()" validation="txt.isrequired,txt.isname" onclick=""/><br><fmt:message key="FIRST_NAME"  bundle="${al}"></fmt:message></td>
    <TD class=fieldLabel width="25%"><hdiits:text name="middlename1" captionid="NAME_OF_OWNER" bundle="${al}" onclick="" condition="valJoiningOwnerName()" validation="txt.isname"/><br><fmt:message key="MIDDLE_NAME" bundle="${al}"></fmt:message></td>
    <TD class=fieldLabel width="25%"><hdiits:text name="lastname1" captionid="NAME_OF_OWNER" bundle="${al}" mandatory="true" condition="valJoiningOwnerName()" validation="txt.isrequired,txt.isname" onclick=""/><br><fmt:message key="LAST_NAME" bundle="${al}"></fmt:message></td>
  </TR>
  </TBODY>
</TABLE> 

<TABLE class=tabtable style="DISPLAY: none" id="OwnerAddress">
	<TR>
  <td>
  		<hdiits:fmtMessage key="OWNER_ADDRESS"  bundle="${al}" var="OwnerAddress" />
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="OwnerJoiningAddress"/>
						<jsp:param name="addressTitle" value="${OwnerAddress}"/>
						<jsp:param name="addrLookupName" value="Permanent Address"/>
						<jsp:param name="mandatory" value="Y" />						
				</jsp:include>
				</td>	
  
 </TR>
</TABLE>  
</hdiits:fieldGroup>
<TABLE class=tabtable style="" id="addButton">
<tr>
	<td align="center">
	<hdiits:button  type="button" name="Add"  captionid="ASSET_ADD" bundle="${al}" onclick="addJoiningDtls()"/>
	
	</td></tr> 
</table>

<TABLE class=tabtable style="DISPLAY: none" id="updateButton">
<tr>
	<td align="center">
	<hdiits:button  type="button" name="Update"  captionid="ASSET_UPDATE" bundle="${al}" onclick="addJoiningDtls()"/>
	
	</td></tr> 
</table>

<table id="addJoiningTable" style="DISPLAY: none" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1  width="98%"> 
<br></br>
<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="15%"><b><hdiits:caption captionid="ASSET_OBTAIN_TYPE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="07%"><b><hdiits:caption captionid="MOV_OR_NOT" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="07%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="20%"><b><hdiits:caption captionid="ADD_OF_ASSET" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="REGI_NO" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="07%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="07%"><b><hdiits:caption captionid="APP_PRICE" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="15%"><b><hdiits:caption captionid="OWNER" bundle="${al}" /></b></td>
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="ACTION" bundle="${al}" /></b></td>
</tr>
</table>

<TABLE class=tabtable style="DISPLAY: none" id="submitButton">
<tr>
<br>
	<td align="center">
	<hdiits:button  type="button" name="Submit" captionid="ASSET_SUBMIT" bundle="${al}" onclick="submitAssetJoiningDtls('${flag}')"  />
	<hdiits:button type="button" name="Close"  captionid="ASSET_CLOSE" bundle="${al}" onclick="closewindow()"/>
	</td></tr> 
</table>
</c:if>
<c:if test="${joiningReqFlag == 'joiningReqAllReadySubmitted'}">

<table align="center">
<br>
	<TBODY>
	 <TR bgColor=#386cb7>
    <TD class=fieldLabel align="center" colSpan=5 ><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    <tr><TD class=fieldLabel  align="center"><b><fmt:message key="ASSET_DECL_ON_JOINING_SUB_ALLREADY" bundle="${al}"/></b></TD></tr>
    <TR bgColor=#386cb7>
    <TD class=fieldLabel align="center" colSpan=5 ><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
   	<tr></tr>
   	<tr></tr>
   	<tr></tr>
    <tr><td align="center">
    <hdiits:button  type="button" name="showJoiningDtls"  captionid="SHOW_JOINING_DECL_DTLS" bundle="${al}" onclick="showJoiningDeclDtls('${appliedUserId}')"/>
    </td></tr>
   </TBODY>
</table> 	
</c:if>
</div>
<hdiits:hidden name="assetAddress" id="assetAddress"/>
<hdiits:hidden name="ownerName" id="ownerName"/>
<hdiits:hidden name="defaultName" default="-" id="defaultName"/>
<hdiits:hidden name="personName" id="personName"/>
<hdiits:hidden name="relation" default="-" id="relation"/>
<hdiits:hidden name="inheritedxmlPaths" id="inheritedxmlPaths"/>
<hdiits:hidden name="ObtainTypeHidden" id="ObtainTypeHidden"/>


		<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />	

</hdiits:form>


<%}catch(Exception e)
  {
  e.printStackTrace();
  }
%>