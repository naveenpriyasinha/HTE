<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.ess.wll.wll_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>




<fmt:setBundle basename="resources.ess.wll.wll" var="commonLables" scope="request"/>



<script>


function show1(q1){
if(q1.value=='-1' || q1.value=='Assistance'){

document.getElementById("wlfRtIntEn").value=0;
document.getElementById("show1").style.display="none";

}
else
{
document.getElementById("show1").style.display='';		
	
		
}  

}


var counter=new Number();
var recordCount=new Number();
var editDBFlag=new Number();
var changeFlag=new Number();

function validation()
{

	
		var a=document.getElementById("wlfSchEn");
		var b=document.getElementById("wlfSchGu");
		var c=document.getElementById("wlfTypeEn");
		
		var d=document.getElementById("wlfDescEn");
		var e=document.getElementById("wlfDescGu");
		var f=document.getElementById("wlfMaxAmtEn");
	
		var h=document.getElementById("wlfRtIntEn");
	
		if(a.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
		return;
		}
		else if(b.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(c.value=="-1")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(d.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(e.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(f.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(c.value=='Loan' && h.value=="" )
		{
		   
			alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		
		}
		else
		{
			if(c.value=='Assistance')
			{
			 h.value=0;
			
			}
			addOrUpdateRecord('addRecord', 'WelfareAddMultiplePoints', new Array('wlfSchEn', 'wlfSchGu','wlfTypeEn','wlfDescEn','wlfDescGu','wlfMaxAmtEn','wlfRtIntEn','code'));
		}
	

}




function addRecord() {
//alert("inside addRecord");

	  if (xmlHttp.readyState == 4)
	  { 		
		var displayFieldArray = new Array('wlfSchEn', 'wlfSchGu','wlfTypeEn','wlfDescEn','wlfDescGu','wlfMaxAmtEn','wlfRtIntEn');
		addDataInTable('txnAdd', 'encXML', displayFieldArray,'editRecord','deleteRec','');	
		recordCount=recordCount+1;			
   	    resetData();   			
	   }	
}


function deleteRec(rowId)
{
	recordCount=recordCount-1;
	deleteRecord(rowId);
}
function updateRecord() 
{
document.getElementById('submit').style.display=''; 
	if (xmlHttp.readyState == 4) 
	{ 	
		var displayFieldArray = new Array('wlfSchEn', 'wlfSchGu','wlfTypeEn','wlfDescEn','wlfDescGu','wlfMaxAmtEn','wlfRtIntEn');
			//alert("document.getElementById('code').value::::"+document.getElementById('code').value);
			
		
		if(editDBFlag==-1)
		{
			var a=document.getElementById("wlfSchEn");
			var b=document.getElementById("wlfSchGu");
			var c=document.getElementById("wlfTypeEn");
			
			var d=document.getElementById("wlfDescEn");
			var e=document.getElementById("wlfDescGu");
			var f=document.getElementById("wlfMaxAmtEn");
		
			var h=document.getElementById("wlfRtIntEn");
		
			if(a.value=="")
			{
				alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
				return;
			}
			else if(b.value=="")
			{
				alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
				return;
			}
			else if(c.value=="-1")
			{
				alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
				return;
			}
			else if(d.value=="")
			{
				alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
				return;
			}
			else if(e.value=="")
			{
				alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
				return;
			}
			else if(f.value=="")
			{
				alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
				return;
			}
			else if(c.value=='Loan' && h.value=="" )
			{
				alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
				return;
			}
			else
			{
				if(c.value=='Assistance')
				{
				 	h.value=0;
				}
				updateDataInTable('encXML', displayFieldArray);		
			}
		}
		else if(editDBFlag==1)
		{
		
		var a=document.getElementById("wlfSchEn");
		var b=document.getElementById("wlfSchGu");
		var c=document.getElementById("wlfTypeEn");
		
		var d=document.getElementById("wlfDescEn");
		var e=document.getElementById("wlfDescGu");
		var f=document.getElementById("wlfMaxAmtEn");
	
		var h=document.getElementById("wlfRtIntEn");
	
		if(a.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
		return;
		}
		else if(b.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(c.value=="-1")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(d.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(e.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(f.value=="")
		{
		alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		}
		else if(c.value=='Loan' && h.value=="" )
		{
		   
			alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.Mandatory"/>');
			return;
		
		}
		else
		{
			if(c.value=='Assistance')
			{
			 h.value=0;
			
			}
		
				
			updateDataInTable('addedPunch', displayFieldArray);		
	}
		}
		
		resetData();
		editDBFlag=0;
		document.getElementById('btnAdd').style.display='';
		document.getElementById('btnUpdate').style.display='none'; 
		document.getElementById('reset').style.display='none';
		
		   	  }
	}
	
function resetData() {	
		document.getElementById('wlfSchEn').value = '';
		document.getElementById('wlfSchGu').value = '';
		document.getElementById('wlfTypeEn').value ='-1';
	
		document.getElementById('wlfDescEn').value = '';
		document.getElementById('wlfDescGu').value = '';
		document.getElementById('wlfMaxAmtEn').value = '';
		
		document.getElementById('wlfRtIntEn').value = '';
	
   		
   	}		



function editRecord(rowId) 
{	
	//alert(rowId);
	editDBFlag=-1;
		document.getElementById('submit').style.display='none'; 
	sendAjaxRequestForEdit(rowId,'populateForm');    	
}	


function populateForm() 
{
	if (xmlHttp.readyState == 4) 
	{ 
		if (xmlHttp.status == 200) 
		{	
		//	alert('response >>> '+xmlHttp.responseText);
		  	var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML,'encXML');
			
			var wlfSchEn = getXPathValueFromDOM(xmlDOM,'wlfSchEn'); 
			var wlfSchGu = getXPathValueFromDOM(xmlDOM,'wlfSchGu');
			var wlfTypeEn = getXPathValueFromDOM(xmlDOM,'wlfTypeEn'); 
			
		
			
						 
					if(wlfTypeEn=='Loan')
					{
					document.getElementById("show1").style.display='';		
	
		
					} 
					else
					{
					document.getElementById("show1").style.display="none";
					} 
			
			
			var wlfDescEn = getXPathValueFromDOM(xmlDOM,'wlfDescEn');
			var wlfDescGu = getXPathValueFromDOM(xmlDOM,'wlfDescGu'); 
			var wlfMaxAmtEn = getXPathValueFromDOM(xmlDOM,'wlfMaxAmtEn');
			 
			var wlfRtIntEn = getXPathValueFromDOM(xmlDOM,'wlfRtIntEn');
			
			if(wlfSchEn != null)
			{				  
				  document.getElementById('wlfSchEn').value = wlfSchEn;				  
		  	}
		  	if(wlfSchGu != null)
			{				  
				  document.getElementById('wlfSchGu').value = wlfSchGu;				  
		  	}
		  	if(wlfTypeEn != null)
			{				  
				  document.getElementById('wlfTypeEn').value = wlfTypeEn;				  
		  	}
		 
		  	if(wlfDescEn != null)
			{				  
				  document.getElementById('wlfDescEn').value = wlfDescEn;				  
		  	}
		  	if(wlfDescGu != null)
			{				  
				  document.getElementById('wlfDescGu').value = wlfDescGu;				  
		  	}
		  	if(wlfMaxAmtEn != null)
			{				  
				  document.getElementById('wlfMaxAmtEn').value = wlfMaxAmtEn;				  
		  	}
		  
		  	if(wlfRtIntEn != null)
			{				  
				  document.getElementById('wlfRtIntEn').value = wlfRtIntEn;				  
		  	}	
		  	    
			document.getElementById('btnAdd').style.display='none'; 	    
			document.getElementById('btnUpdate').style.display=''; 
			document.getElementById('reset').style.display='';  	    		   		    
		}
	}
}


function editDBRecord(rowId) 
{	
		//alert("inn editDBRecord");
		editDBFlag=1;
		changeFlag=1;
			document.getElementById('submit').style.display='none'; 
	sendAjaxRequestForEdit(rowId,'populateFormWithDBData');    	
}	


function populateFormWithDBData() 
{
	if (xmlHttp.readyState == 4) 
	{ 
		if (xmlHttp.status == 200) 
		{	
		//alert("inn populateFormWithDBData");
			//alert(xmlHttp.responseText);
		  	var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML,'addedPunch');
			
		
			var wlfSchEn = getXPathValueFromDOM(xmlDOM,'wlfSchEn'); 
			var wlfSchGu = getXPathValueFromDOM(xmlDOM,'wlfSchGu');
			var wlfTypeEn = getXPathValueFromDOM(xmlDOM,'wlfTypeEn');
			 
			
			 
			 
				if(wlfTypeEn=='Loan')
				{
				document.getElementById("show1").style.display='';		
	
		
				} 
				else
				{
				document.getElementById("show1").style.display="none";
				} 
			 
			 
			 
			var wlfDescEn = getXPathValueFromDOM(xmlDOM,'wlfDescEn');
			var wlfDescGu = getXPathValueFromDOM(xmlDOM,'wlfDescGu'); 
			var wlfMaxAmtEn = getXPathValueFromDOM(xmlDOM,'wlfMaxAmtEn');
		 
			var wlfRtIntEn = getXPathValueFromDOM(xmlDOM,'wlfRtIntEn');
		
			var goalCode = getXPathValueFromDOM(xmlDOM,'goalCode');
			//alert("goalCode"+goalCode);
			if(wlfSchEn != null)
			{				  
				  document.getElementById('wlfSchEn').value = wlfSchEn;				  
		  	}
		  	if(wlfSchGu != null)
			{				  
				  document.getElementById('wlfSchGu').value = wlfSchGu;				  
		  	}
		  	if(wlfTypeEn != null)
			{				  
				  document.getElementById('wlfTypeEn').value = wlfTypeEn;				  
		  	}
		  
		  	if(wlfDescEn != null)
			{				  
				  document.getElementById('wlfDescEn').value = wlfDescEn;				  
		  	}
		  	if(wlfDescGu != null)
			{				  
				  document.getElementById('wlfDescGu').value = wlfDescGu;				  
		  	}
		  	if(wlfMaxAmtEn != null)
			{				  
				  document.getElementById('wlfMaxAmtEn').value = wlfMaxAmtEn;				  
		  	}
		  
		  	if(wlfRtIntEn != null)
			{				  
				  document.getElementById('wlfRtIntEn').value = wlfRtIntEn;				  
		  	}	
		  	
		  	if(goalCode != null)
			{				  
				  document.getElementById('code').value = goalCode;				  
		  	}	    
		  	
			document.getElementById('btnAdd').style.display='none'; 	    
			document.getElementById('btnUpdate').style.display='';
			document.getElementById('reset').style.display='';   	    		   		    
		}
	}
}

function deleteDBRec(rowId)
{
		changeFlag=1;
		deleteDBRecord(rowId);
}




function checkRecordCount()
{
	if(recordCount>0 || changeFlag==1)
	{
		submit1();
	}
	else
	{
			alert('<fmt:message  bundle="${commonLables}" key="HR.EIS.AddPoint"/>');
			return;
	}
}




function submit1()
{
	document.welfarePointMst.method="POST";
	document.welfarePointMst.action="./hrms.htm?actionFlag=welfarePointMstSubmit";	
	document.welfarePointMst.submit();	
}

function datareset()
{


	document.welfarePointMst.method="POST";
	document.welfarePointMst.action="./hrms.htm?actionFlag=WelfareMasterScreen";	
	document.welfarePointMst.submit();	
}







</script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	


<c:set var="pointList" value="${resValue.masterpointList}"></c:set>
<c:set var="sizeOfList" value="${resValue.masterpointListsize}"></c:set>


<c:set var="xmlFilePathNameForMulAdd" value="${resValue.xmlList}"/>
<c:set var="customVoList" value="${resValue.customVoList}"/>


	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="HR.EIS.Welfare" bundle="${commonLables}" captionLang="single"/></a></li>
	</ul>
	</div>

<hdiits:form name="welfarePointMst"  validate="true" method="POST" encType="multipart/form-data" >	 
	 		
		<hdiits:hidden name="code" id="code"/>
	
			<table width="100%" align="center">
				<tr>
					<td width="100%" align="center">
						<font>
							<hdiits:fieldGroup titleCaptionId="HR.EIS.SetPoints" bundle="${commonLables}"></hdiits:fieldGroup> 
						</font>
					</td>
				</tr>
			
			</table>
			
			<table id="pointTable" align="center">
				<tr>
					<td>
						<b><hdiits:caption captionid="HR.EIS.WlfSchEn" bundle="${commonLables}"/></b>
					</td>
					<td>
						<hdiits:textarea mandatory="true" rows="3" cols="50" name="wlfSchEn" tabindex="7" id="wlfSchEn" captionid="HR.EIS.WlfSchEn" bundle="${commonLables}" maxlength="4000"/>
					</td>
					<td>
						<b><hdiits:caption captionid="HR.EIS.WlfSchGu" bundle="${commonLables}"/></b>
					</td>
					<td>
						<hdiits:textarea mandatory="true" rows="3" cols="50" name="wlfSchGu" tabindex="7" id="wlfSchGu" captionid="HR.EIS.WlfSchGu" bundle="${commonLables}" maxlength="4000"/>
					</td>
				</tr>
				
				<tr>
					<td>
						<b><hdiits:caption captionid="HR.EIS.WlfTypeEn" bundle="${commonLables}"/></b>
					</td>
					<td>
						<hdiits:select captionid="HR.EIS.WlfTypeEn" name="wlfTypeEn" id="wlfTypeEn" mandatory="true" validation="sel.isrequired" sort="true" onchange="show1(this);">
							<option value="-1"><hdiits:caption captionid="HR.EIS.Select" bundle="${commonLables}" captionLang="single"/></option>
							<option value="Loan"><hdiits:caption captionid="HR.EIS.Loan" bundle="${commonLables}" captionLang="single"/></option>
	                		<option value="Assistance"><hdiits:caption captionid="HR.EIS.Assistance" bundle="${commonLables}" captionLang="single"/></option>                   			
	                 	</hdiits:select >                  			
					</td>
				</tr>

				<tr>
					<td>
						<b><hdiits:caption captionid="HR.EIS.WlfDescEn" bundle="${commonLables}"/></b>
					</td>
					<td>
						<hdiits:textarea mandatory="true" rows="3" cols="50" name="wlfDescEn" tabindex="7" id="wlfDescEn" captionid="HR.EIS.WlfDescEn" bundle="${commonLables}" maxlength="4000"/>
					</td>
					<td>
						<b><hdiits:caption captionid="HR.EIS.WlfDescGu" bundle="${commonLables}"/></b>
					</td>
					<td>
						<hdiits:textarea mandatory="true" rows="3" cols="50" name="wlfDescGu" tabindex="7" id="wlfDescGu" captionid="HR.EIS.WlfDescGu" bundle="${commonLables}" maxlength="4000"/>
					</td>
				</tr>
				
				<tr>
					<td>
						<b><hdiits:caption captionid="HR.EIS.WlfMaxAmtEn" bundle="${commonLables}"/></b>
					</td>
					<td>
						<hdiits:number name="wlfMaxAmtEn" id="wlfMaxAmtEn" captionid="HR.EIS.WlfMaxAmtEn" bundle="${commonLables}" mandatory="true" validation="txt.isrequired,txt.isnumber"/>
					</td>
				</tr>
				<tr id="show1" style="display:none;">
					<td>
						<b><hdiits:caption captionid="HR.EIS.WlfRtIntEn" bundle="${commonLables}" /></b>
					</td>
					<td>
						<hdiits:number name="wlfRtIntEn"  id="wlfRtIntEn" captionid="HR.EIS.WlfRtIntEn" bundle="${commonLables}" mandatory="true" validation="txt.isrequired,txt.isnumber"/>
					</td>
                 </tr>
			</table>	
				
			<table align="center">
				<tr>
					<td>
						<hdiits:button type="button" name="btnAdd" value="Add" captionid="HR.EIS.Add" bundle="${commonLables}" onclick="javascript:validation();"/>
					</td>
					<td>
						<hdiits:button type="button" name="btnUpdate" value="Update" style="display:none" captionid="HR.EIS.Update" bundle="${commonLables}"
						onclick="javascript:addOrUpdateRecord('updateRecord', 'WelfareAddMultiplePoints',  new Array('wlfSchEn', 'wlfSchGu','wlfTypeEn','wlfDescEn','wlfDescGu','wlfMaxAmtEn','wlfRtIntEn','code'))"/>
					</td>
					<td>
						<hdiits:button type="button" name="reset" value="Reset" captionid="HR.EIS.Reset" bundle="${commonLables}" style="display:none" onclick="javascript:datareset();"/>
					</td>
				</tr>
			</table>
		
			<hdiits:hidden name="count" id="counter"/>
			
			<table width="100%">
				<tr>
					<td width="100%" align="center">
						<font>
							<hdiits:fieldGroup titleCaptionId="HR.EIS.PointTable" bundle="${commonLables}"></hdiits:fieldGroup>
						</font>
					</td>
				</tr>
			</table>
			
			<br>
		
			<table id="txnAdd"  border="1" align="center" width="80%" class=tabtable style="border-collapse: collapse;"  borderColor="BLACK"> 
				<tr>
   					<td class="fieldLabel" align="center" bgcolor="#C9DFFF">
   						<b><hdiits:caption captionid="HR.EIS.PointSchEnglish" bundle="${commonLables}"/></b>
					</td>
   					<td class="fieldLabel" align="center" bgcolor="#C9DFFF">
   						<b><hdiits:caption captionid="HR.EIS.PointSchGujarati" bundle="${commonLables}"/></b>
					</td>
					<td class="fieldLabel" align="center" bgcolor="#C9DFFF">
   						<b><hdiits:caption captionid="HR.EIS.PointTypeEnglish" bundle="${commonLables}"/></b>
					</td>
					<td class="fieldLabel" align="center" bgcolor="#C9DFFF">
   						<b><hdiits:caption captionid="HR.EIS.PointDescEnglish" bundle="${commonLables}"/></b>
					</td>
   					<td class="fieldLabel" align="center" bgcolor="#C9DFFF">
   						<b><hdiits:caption captionid="HR.EIS.PointDescGujarati" bundle="${commonLables}"/></b>
					</td>
					<td class="fieldLabel" align="center" bgcolor="#C9DFFF">
   						<b><hdiits:caption captionid="HR.EIS.PointMaxAmtEnglish" bundle="${commonLables}"/> </b>
					</td>
					<td class="fieldLabel" align="center" bgcolor="#C9DFFF">
   						<b><hdiits:caption captionid="HR.EIS.PointRtIntEnglish" bundle="${commonLables}"/></b>
					</td>
						<td class="fieldLabel"  align="center" bgcolor="#C9DFFF">
						<b><hdiits:caption captionid="HR.EIS.EditDelete" bundle="${commonLables}"/> </b>
					</td>  
   				</tr>
			</table>  
			
			<br>
			<br>
		
			<table id="submit"  align="center">
				<tr>
					<td>
						<hdiits:button captionid="HR.EIS.Submit" bundle="${commonLables}" name="AcceptChange" type="button"  onclick="checkRecordCount();"/>
					</td>
					<td>
						<hdiits:button captionid="HR.EIS.Close" bundle="${commonLables}" name="SetDefault" type="button" onclick="history.go(-1);"/>
					</td>
				</tr>
			</table>
		
		
		<c:forEach var="curXMLFileName" items="${xmlFilePathNameForMulAdd}" varStatus="x">
			<c:set var="customVo" value="${customVoList[x.index]}" ></c:set>
			<c:set var="Schenglish" value="${customVo.wlfSchEn}"/>
			<c:set var="Schgujarati" value="${customVo.wlfSchGu}"/>
			<c:set var="Typenglish" value="${customVo.wlfTypeEn}"/>
			<c:set var="Descenglish" value="${customVo.wlfDescEn}"/>
			<c:set var="Descgujarati" value="${customVo.wlfDescGu}"/>
			<c:set var="MaxAmtenglish" value="${customVo.wlfMaxAmtEn}"/>
			<c:set var="RtIntenglish" value="${customVo.wlfRtIntEn}"/>
				
			<script type="text/javascript">
				var xmlFileName = '${curXMLFileName}';
				var displayFieldA  = new Array('${Schenglish}','${Schgujarati}','${Typenglish}','${Descenglish}','${Descgujarati}','${MaxAmtenglish}','${RtIntenglish}');
				addDBDataInTable('txnAdd','addedPunch',displayFieldA,xmlFileName,'editDBRecord','deleteDBRec');
			</script>
		</c:forEach>			
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>



