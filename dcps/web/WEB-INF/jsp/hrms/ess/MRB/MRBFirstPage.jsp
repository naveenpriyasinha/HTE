<!-- Atul 220680 ASE -->
<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}">
</c:set>

	<fmt:setBundle basename="resources.ess.MRB.MRBLables" var="cmnLables" scope="request" />


<%@page session="true"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="enLables" scope="request" />
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/statusbar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/hrms/ess/mrb/MRB.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/hrms/ess/mrb/MrbAddRcrd.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript"  
         src="common/script/commonfunctions.js">
</script>

<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="cmnlookupList" value="${resValue.cmnlookupList}"></c:set>
<script type="text/javascript">
var counter = 1;
function getDataByReq(q)
{
	var reqType=q.value;

	if(reqType=="Select")
	{
		document.getElementById("NoRecord").style.display="none";
	}
	document.getElementById("MRBReqTabelAppr").style.display="none";
	document.getElementById("MRBReqTabel").style.display="none";
  		
  		var rows=0;
  		if(document.getElementById("MRBReqTabelAppr").rows.length>1)
  		{
  			rows=document.getElementById("MRBReqTabelAppr").rows.length;
  			
  			for(var i=eval(rows-1);i>0;i--)
			{
				
				document.getElementById("MRBReqTabelAppr").deleteRow(i);
    		}	
  		}
  		else
  		{
  			rows=document.getElementById("MRBReqTabel").rows.length;
  			
  			for(var i=eval(rows-1);i>0;i--)
			{
				
				document.getElementById("MRBReqTabel").deleteRow(i);
    		}	
  		}
		
	
	
	
	if(reqType!="NewReq" )
	{
		if(reqType!="Select")
		{
			try
			{   
    			xmlHttp=new XMLHttpRequest();    
			}
			catch (e)
			{    // Internet Explorer    
				try
				{
      				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
      			}
				catch (e)
				{
		  			try
		  			{
              			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        			}
					catch (e)
					{
			    		alert("Your browser does not support AJAX!");        
			    		return false;        
					}
				}			 
     		}	
			var url = "hrms.htm?actionFlag=getRequestData&ReqType="+reqType;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse1;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}
		globalReqType="";
	}
	else 
	{
		document.MRBFIRSTFORM.action = "hrms.htm?actionFlag=getMRBReqPage";
		document.MRBFIRSTFORM.submit();
		showProgressbar();
	}
	document.getElementById("OK").style.display="";
	document.getElementById("PrintPrv").style.display="";
}
var globalReqType="";
function processResponse1()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{       
	          	     var xmlStr = xmlHttp.responseText;
	          	     
					 var XMLDoc=getDOMFromXML(xmlStr);
					  
					 var ReqId=XMLDoc.getElementsByTagName('ReqId');
					 var PatientType=XMLDoc.getElementsByTagName('PatientType');
					 var ReqAmnt=XMLDoc.getElementsByTagName('ReqAmnt');
					 var SanctnAmnt=XMLDoc.getElementsByTagName('SanctionAmnt');
					 var ApplyDate=XMLDoc.getElementsByTagName('ApplyDate');
					 var XmlPath=XMLDoc.getElementsByTagName('xmlFilePath');
					 var RequestType=XMLDoc.getElementsByTagName('RequestType');
					 var DependName=XMLDoc.getElementsByTagName('DependName');
					
					 var RequestType1="";
					 if(RequestType.length>0)
					 {
					 	RequestType1=RequestType[0].childNodes[0].text;
					 	
					 	globalReqType=RequestType1;
					 	document.getElementById("NoRecord").style.display="none"
					 	
					 }
					 else
					 {
					 	document.getElementById("NoRecord").style.display="";
					 }
					
					 if(ReqId.length>0)
					 {
					 	for(var i=0;i<ReqId.length;i++)
					 	{
					 		var RequestID=ReqId[i].childNodes[0].text;
					 		var DependName1=DependName[i].childNodes[0].text;
					 		
					 		var PatType=PatientType[i].childNodes[0].text;
					 		if(PatType=="-1")
					 		{
					 			PatType="Self";
					 		}
					 		else
					 		{
					 			PatType=DependName1;
					 		}
					 		var RequestAmnt=ReqAmnt[i].childNodes[0].text;
					 		var SanctnedAmnt=SanctnAmnt[i].childNodes[0].text;
					 		var AppliedDate=ApplyDate[i].childNodes[0].text;
					 		
					 		var datearr1 =AppliedDate.split(' ');
				    		var newdate1=datearr1[0].split('-');
				    		var formattedate1=newdate1[2]+"/"+newdate1[1]+"/"+newdate1[0];
					 		var ReqType=RequestType[i].childNodes[0].text;
					 		var PathXML=XmlPath[i].childNodes[0].text;
					 		
					 		if(ReqType=='ApproveReq')
					 		{
					 			addDBDataInTableForMRB('MRBReqTabelAppr','encXMLRequest',new Array(RequestID,PatType,RequestAmnt,SanctnedAmnt,formattedate1),PathXML,'','','',RequestID);
					 		}
					 		else
					 		{
					 			addDBDataInTableForMRB('MRBReqTabel','encXMLRequest',new Array(RequestID,PatType,RequestAmnt,formattedate1),PathXML,'','','',RequestID);
					 		}
					 	}
					 }
				}
			}
}
function getExistingReqWindow()
{	
	
	if(globalReqId!="")
	{
		var ReqId=globalReqId;
		var ExistineData="ExistingData";
		
		document.MRBFIRSTFORM.action = "hdiits.htm?actionFlag=getMRBReqPage&ExiFlag="+ExistineData+"&MRBId="+ReqId+"&ReqType="+globalReqType;
		document.MRBFIRSTFORM.submit();
	}
	else
	{
		alert('<fmt:message key="MRB.SelectRecFirst" bundle="${cmnLables}"/>');
	}
	globalReqId="";
}
function getExistingReqWindowPrntPrvw()
{
	if(globalReqId!="")
	{
		var ReqId=globalReqId;
		var ExistineData="ExistingData";
		var PrntPrvwFlag="PrintPreview";
		
		document.MRBFIRSTFORM.action = "hdiits.htm?actionFlag=getMRBReqPage&ExiFlag="+ExistineData+"&MRBId="+ReqId+"&ReqType="+globalReqType+"&PrntPrvwFlag="+PrntPrvwFlag;
		document.MRBFIRSTFORM.submit();
	}
	else
	{
		alert('<fmt:message key="MRB.SelectRecFirst" bundle="${cmnLables}"/>');
	}
	globalReqId="";
}


var globalReqId="";
function showchecked(ReqId)
{
	globalReqId="";
	globalReqId=ReqId;
}
function addDBDataInTableForMRB(tableId,hiddenField,dispFieldA,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName,ReqId)
	{
			
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName 	== undefined) 
		{
			editMethodName 	 = '';
		}
		if(viewMethodName 	== undefined) 
		{
			viewMethodName 	 = '';
		}
		document.getElementById(tableId).style.display='';
		
		var RequestId=ReqId;
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
	
		
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		
		trow.insertCell(1).innerHTML="<INPUT type='radio' name='REqRadio' id='REqRadio' value='"+RequestId+"' onclick=showchecked('" + RequestId + "')>"
		
		var len = dispFieldA.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+2).innerHTML = dispFieldA[i];	
		}	
		counter++;
		
		return trow.id;
	}
function ClosePage(frm){
frm.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
frm.method="POST";
frm.submit();
}	
</script>

<hdiits:form name="MRBFIRSTFORM" validate="true" method="POST" action="">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs" compact="compact">
		<li class="selected"><a href="#" rel="tcontent1"><font>
		<b><fmt:message key="MRB.HOMEPage" bundle="${cmnLables}" /></b></font></a></li>
	</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<br>
	<br>
	
	<table id="ReqType" width="50%">
		<tbody>
			<tr>
				<td>
				<hdiits:caption captionid="MRB.SelectReqType" bundle="${cmnLables}"/>
				</td>
				<td>
				<hdiits:select name="ReqType" id="ReqType" mandatory="true"
					validation="sel.isrequired" sort="false" captionid="MRB.SelectReqType" 
					bundle="${cmnLables}" onchange="getDataByReq(this)">
					<hdiits:option value="Select">
					<fmt:message key="mrb.select" bundle="${cmnLables}" />
					
					</hdiits:option>
					<c:forEach var="Reqtype" items="${cmnlookupList}">
					<hdiits:option value="${Reqtype.lookupName}">
				${Reqtype.lookupDesc}</hdiits:option>
				</c:forEach>
				</hdiits:select>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<br><br>
	<br>
	<table id="MRBReqTabelAppr" width="100%" style="border-collapse: collapse;display:none" border="1"  borderColor="BLACK">
		<tr >
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"></td>
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.ReqId" bundle="${cmnLables}"/></b></td>
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.patdtl" bundle="${cmnLables}"/></b></td>
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.ReqAmnt" bundle="${cmnLables}"/></b></td>
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.ApprvAmnt" bundle="${cmnLables}"/></b></td>
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.ApplyDate" bundle="${cmnLables}"/></b></td>
		</tr>
	</table>
	<table id="MRBReqTabel" width="100%" style="border-collapse: collapse;display:none" border="1"  borderColor="BLACK">
	<tbody>
		<tr bgcolor="#AAAAA">
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"></td>
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.ReqId" bundle="${cmnLables}"/></b></td>
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.patdtl" bundle="${cmnLables}"/></b></td>
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.ReqAmnt" bundle="${cmnLables}"/></b></td>
			<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><hdiits:caption captionid="MRB.ApplyDate" bundle="${cmnLables}"/></b></td>
		</tr>
	</tbody>
	</table>
	<table id="NoRecord" style="display:none" align="center">
		<tbody>
			<tr><td><font color="red"><b><hdiits:caption captionid="MRB.NotFound" bundle="${cmnLables}"/></b></font></td></tr>
		</tbody>
	</table>
	<center>
	<hdiits:button type="button" name="OK" value="OK" onclick="getExistingReqWindow();" style="display:none"/> 
	<hdiits:button type="button" name="Close" value="Close" onclick="ClosePage(document.MRBFIRSTFORM);" />
	<hdiits:button type="button" name="PrintPrv" value="Print Preview" onclick="getExistingReqWindowPrntPrvw();" style="display:none"/>
	</center>
	</div>
	</div>


<script type="text/javascript">
	initializetabcontent("maintab");
</script> 
</hdiits:form>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
