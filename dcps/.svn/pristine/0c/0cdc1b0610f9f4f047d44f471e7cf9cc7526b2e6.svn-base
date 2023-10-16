<%
try {
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/hrms/ess/mrb/MrbAddRcrd.js"/>"></script>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="RowID" value="${resultValue.RowID}">
</c:set>
<c:set var="XMLPath" value="${resultValue.XMLPath}">
</c:set>
<c:set var="BillDataComingFromDb" value="${resultValue.BillDataComingFromDb}">
</c:set>
<fmt:setBundle basename="resources.ess.MRB.MRBLables" var="cmnLables" scope="request" />
<script>
 var cntr=0;
 var getrow=new Array();
var secndrow=new Array();
var empcount=0;
var MediId="";
var comparend="";
var GlobFlagForNonExistMedi=false;
function GetFrmSearchAndAdd()
{
	var SelObj=window.frames['SearchData'].document.getElementsByName("check");
	
	var SetChkBox="";
	if(SelObj.length>0)
	{
		for(var i=0;i<SelObj.length;i++)
		{
			if(SelObj[i].checked)
			{
				SetChkBox=SetChkBox+","+SelObj[i].value;
			}
		
		}
	
		if (SetChkBox.length > 1)
		{
			SetChkBox = SetChkBox.substring(1);
		}
		if(SetChkBox.length>0)
		{
			var getMedcnSepar=SetChkBox.split(",");
			for(var j=0;j<getMedcnSepar.length;j++) //row
			{
				var toSeparate=getMedcnSepar[j];
				var getMediContntSepar=toSeparate.split("~");
			
				if(getMediContntSepar.length>0)
				{
					document.getElementById('MediName').value=getMediContntSepar[0];
					document.getElementById('MedType').value=getMediContntSepar[1];
					document.getElementById('MedCat').value=getMediContntSepar[2];
					document.getElementById('MedWt').value=getMediContntSepar[3];
					document.getElementById('MedId').value=getMediContntSepar[4];
				}
			
				var SeparMediId="";
				
				if(MediId.length>0)
				{
					SeparMediId=MediId.split(",");
				}

				if(SeparMediId.length>1)
				{	
					var flag = true;
			
					for(var k=0;k<SeparMediId.length;k++)
					{
						if(SeparMediId[k]!="")
						{
							if(SeparMediId[k]==getMediContntSepar[4])
							{
								//addOrUpdateRecord('addRecordMedicine', 'addMedicineDtls',new Array('MediName','MedType','MedCat','MedWt','MedId'));
								flag = false;
								break;
							}
						}	
					}
					if (flag)
					{
						addOrUpdateRecordMRB('addRecordMedicine', 'addMedicineDtls',new Array('MediName','MedType','MedCat','MedWt','MedId'));
						cntr++;
						MediId=MediId+","+getMediContntSepar[4];
					}
					else
					{
						alert('<fmt:message  bundle="${cmnLables}"  key="MRB.AlreadyAddedMedi"/>');
					}
			
				}
				else
				{
					addOrUpdateRecordMRB('addRecordMedicine', 'addMedicineDtls',new Array('MediName','MedType','MedCat','MedWt','MedId'));
					cntr++;
					MediId=MediId+","+getMediContntSepar[4];
				}
			}
		}
		else
		{
			alert('<fmt:message  bundle="${cmnLables}"  key="MRB.FirstSelectMedi"/>');
		}
	}
	else
	{
		var MedName=window.frames['SearchData'].document.getElementsByName("MedName");
		var MediType=window.frames['SearchData'].document.getElementsByName("MedType");
		var MedCat=window.frames['SearchData'].document.getElementsByName("MedCat");
		if(window.frames['SearchData'].document.MRBFRM1.MediWt!=null)
		{
			var MedWt=window.frames['SearchData'].document.getElementsByName("MediWt");
			if(MedName[0].value=="" || MediType[0].value=="" || MedCat[0].value=="" || MedWt[0].value=="")
			{
			
				if(MedName[0].value=="")
				{
					alert('<fmt:message  bundle="${cmnLables}"  key="mrb.alert5"/>');
					window.frames['SearchData'].document.MRBFRM1.MedName.focus();
				}
				else if(MediType[0].value=="")
				{
					alert('<fmt:message  bundle="${cmnLables}"  key="mrb.alert6"/>');
					window.frames['SearchData'].document.MRBFRM1.MedType.focus();
				}
				else if(MedCat[0].value=="")
				{
					alert('<fmt:message  bundle="${cmnLables}"  key="mrb.alert7"/>');
					window.frames['SearchData'].document.MRBFRM1.MedCat.focus();
				}
				else if(MedWt[0].value=="")
				{
					if(window.frames['SearchData'].document.MRBFRM1.MediWt!=null)
					{
						alert('<fmt:message  bundle="${cmnLables}"  key="mrb.alert8"/>');
						window.frames['SearchData'].document.MRBFRM1.MediWt.focus();
					}
				}
			}
			else
			{
				document.getElementById('OtherMediName').value=MedName[0].value;
				document.getElementById('OtherMedType').value=MediType[0].value;
				document.getElementById('OtherMedCat').value=MedCat[0].value;
				document.getElementById('OtherMedWt').value=MedWt[0].value;
				document.getElementById('MedId').value=0;
			
				GlobFlagForNonExistMedi=true;
				addOrUpdateRecordMRB('addRecordMedicine', 'addMedicineDtls',new Array('OtherMediName','OtherMedType','OtherMedCat','OtherMedWt','MedId'));
				cntr++;
				window.frames['SearchData'].document.MRBFRM1.MedName.value="";
				window.frames['SearchData'].document.MRBFRM1.MedType.value="";
				window.frames['SearchData'].document.MRBFRM1.MedCat.value="";
				window.frames['SearchData'].document.MRBFRM1.MediWt.value="";
			}
		}
		else
		{
			alert("First Search Medicine From Existing List");
		}
	}
	
}

function addRecordMedicine()
{
		if (xmlHttp.readyState == 4) 
			{ 
				var decXMl=xmlHttp.responseText;
			   
			    var displayFieldArray=new Array();
			   
				
				
			    if(GlobFlagForNonExistMedi==true)
			    {
			    	displayFieldArray = new Array('OtherMediName','OtherMedType','OtherMedCat','OtherMedWt');
			    }
			    else
			    {
			    	displayFieldArray = new Array('MediName','MedType','MedCat','MedWt');
			    }
			    var xyz=addDataInTableMRB('MedicineDtlsTab', 'encXMLMedi', displayFieldArray,'','deleteRecord');
			    GlobFlagForNonExistMedi=false;
			}
}

function deleteMedicineRecord(RowIds)
{
	
	var delCap = "";
	delCap = "Are you sure you want to delete this record ?";
	var answer = confirm(delCap);
	if(answer)
	{
		var delRow 	= document.getElementById(RowIds);
		var content = delRow.cells[0].innerHTML;
		showAlert(content);
		content = content.replace(".xml_N",".xml_D");
		content = content.replace(".xml_U",".xml_D");
		delRow.cells[0].innerHTML = content;					
		delRow.style.display='none';
	}
	var hField = RowIds.substring(3, RowIds.length);
	
	var xmlFileName = document.getElementById(hField).value;
	
	SeparExistPath=ExistXMlPath.split(",");
	
	ExistXMlPath="";
	for(var i=0;i<SeparExistPath.length;i++)
	{
		var ActualLength=xmlFileName.length;
		var PathForCompare=xmlFileName.substring(0,ActualLength-2);
		
		if(SeparExistPath[i]!=PathForCompare)
		{
			ExistXMlPath=ExistXMlPath+","+SeparExistPath[i];
		}
	}
	ExistXMlPath=ExistXMlPath+","+xmlFileName;
	
	if(ExistXMlPath.length>1)
	{
		ExistXMlPath=ExistXMlPath.substring(1);
		
	}
}
function SendBackSrchRslt()
{
	
	if(cntr>0)
	{
		for(var i=counters;i<=counters+cntr;i++)
		{
			
			if(document.getElementById("encXMLMedi"+i)!=null)
			{
				var xmlPath=document.getElementById("encXMLMedi"+i).value;
				var MedAmnt=document.getElementById("MedAmnt"+i).value;
				
				var iChars = "1234567890.";
				var OnlyNoAllow=true;
				for (var j = 0; j < MedAmnt.length; j++)
  					{
  						if (iChars.indexOf(MedAmnt.charAt(j)) == -1) 
  						{
  							//control.focus();
  							OnlyNoAllow=false;
  							
  						}
  					}
  					if(OnlyNoAllow==false)
  					{
  						alert('<fmt:message  bundle="${cmnLables}"  key="MRB.NOs"/>');
  						document.getElementById("MedAmnt"+i).value="";
  						document.getElementById("MedAmnt"+i).focus();
  						return false;
  						
  					}
				
				if(MedAmnt!="")
				{
					var delRow = document.getElementById("rowencXMLMedi"+i);	
					delRow.parentNode.deleteRow(delRow.rowIndex);
					xmlHttp=GetXmlHttpObject();
					if (xmlHttp==null) 
					{
		  				alert ("Your browser does not support AJAX!");
		  				return;
					} 		
					var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlPath;	
					xmlHttp.onreadystatechange = function()
					{
						if(xmlHttp.readyState == 4) 
						{
							eval("GetXMlVal('"+MedAmnt+"')");
			 			}
		 			}
					xmlHttp.open("POST",encodeURI(url),false);
					xmlHttp.send(null);	
				}
				else
				{
					alert('<fmt:message  bundle="${cmnLables}"  key="mrb.alert11"/>');
					return;
				}
			}
		}
	}
	var Rowid="${RowID}";
	var XMlPath="${XMLPath}";
	
	if(FinalMedArray.length>1)
	{
		FinalMedArray=FinalMedArray.substring(1);
	
	}
	
	if(ExistXMlPath!="")
	{
		if(FinalMedArray!="")
		{
			FinalMedArray=FinalMedArray+","+ExistXMlPath;
		}
		else
		{
			FinalMedArray=FinalMedArray+","+ExistXMlPath;
			if(FinalMedArray.length>1)
			FinalMedArray=FinalMedArray.substring(1);
		}
		
	}
	
	if(FinalMedArray!="")
	{
		window.opener.MediSearch(FinalMedArray,Rowid,XMlPath);
		window.close();
	}
	else
	{
		alert('<fmt:message  bundle="${cmnLables}"  key="MRB.FirstSelectMedi"/>');
	}
	DataComingFromDB=false;
}
function GetXMlVal(MedAmnt)
{
	if (xmlHttp.readyState == 4) 
			  { 				  		
			  	var decXML = xmlHttp.responseText;
			  	var xmlDOM = getDOMFromXML(decXML);	
			  	if(getXPathValueFromDOM(xmlDOM, 'MedicineId')!=0)
				{
			  		document.getElementById('MediName').value=getXPathValueFromDOM(xmlDOM, 'MedicineName');
					document.getElementById('MedType').value=getXPathValueFromDOM(xmlDOM, 'MedicineType');
					document.getElementById('MedCat').value=getXPathValueFromDOM(xmlDOM, 'MedicineCat');
					document.getElementById('MedWt').value=getXPathValueFromDOM(xmlDOM, 'MedicineWt');
					document.getElementById('MedId').value=getXPathValueFromDOM(xmlDOM, 'MedicineId');					 						
					if(getXPathValueFromDOM(xmlDOM, 'MedicineAmnt')==0.0)
					{
						document.getElementById('MedAmnt').value=MedAmnt;
						addOrUpdateRecordMRB('addRecordMedicine1', 'addMedicineDtls',new Array('MediName','MedType','MedCat','MedWt','MedAmnt','MedId'));
					}
				}
				else
				{		
					
					document.getElementById('OtherMediName').value=getXPathValueFromDOM(xmlDOM, 'OtherMedicineName');
					document.getElementById('OtherMedType').value=getXPathValueFromDOM(xmlDOM, 'OtherMedicineType');
					document.getElementById('OtherMedCat').value=getXPathValueFromDOM(xmlDOM, 'OtherMedicineCat');
					document.getElementById('OtherMedWt').value=getXPathValueFromDOM(xmlDOM, 'OtherMedicineWt');
					document.getElementById('MedId').value=getXPathValueFromDOM(xmlDOM, 'MedicineId');	
					
					if(getXPathValueFromDOM(xmlDOM, 'MedicineAmnt')==0.0)
					{
						document.getElementById('MedAmnt').value=MedAmnt;
						addOrUpdateRecordMRB('addRecordMedicine1', 'addMedicineDtls',new Array('OtherMediName','OtherMedType','OtherMedCat','OtherMedWt','MedAmnt','MedId'));
					}
				}		
			}
}
var FinalMedArray="";
function addRecordMedicine1()
{
		if (xmlHttp.readyState == 4) 
			{ 
				var decXMl=xmlHttp.responseText;
			    
			     var displayFieldArray=new Array();
			     
			    if(document.getElementById('MediName').value=="" || document.getElementById('MedType').value=="" || document.getElementById('MedCat').value=="" || document.getElementById('MedWt').value=="")
			    {
			    	 displayFieldArray = new Array('OtherMediName','OtherMedType','OtherMedCat','OtherMedWt','MedAmnt');
			    }
			    else
			    {
			    	displayFieldArray = new Array('MediName','MedType','MedCat','MedWt','MedAmnt');
			   	}
			   	addDataInTable('MedicineDtlsTab', 'encXMLMedicine', displayFieldArray,'','deleteMediRecord');
			    FinalMedArray=FinalMedArray+","+decXMl;  
			}
}


function getCarryXMlComponent()
	{
		var XMlPath="${XMLPath}";
		xmlHttp=GetXmlHttpObject();		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + XMlPath;	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{
				eval("GetXMlValues()");
			}
		}
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);	
	}
var ExistXMlPath="";
var DataComingFromDB=false;
var counters=1;
	function GetXMlValues()
	{
		if (xmlHttp.readyState == 4) 
		{ 				  		
			var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML);	
			var ExistId=getXPathValueFromDOM(xmlDOM, 'MediIdCombintn');
			
			var XmlPaths=getXPathValueFromDOM(xmlDOM, 'MedicineXMlPath');
			if(XmlPaths!="NoPath")
			{
				ExistXMlPath=XmlPaths;
			}
			var separXMlPath=XmlPaths.split(",");
			
			if(separXMlPath!="NoPath")
			{
				var ExistingMediIds=getXPathValueFromDOM(xmlDOM, 'MediIdCombintn');
				MediId=ExistingMediIds;
				if(MediId="null")
				{
					MediId="";
				}
				
				var GetBillIdForExistRec=getXPathValueFromDOM(xmlDOM, 'BillId');
				if(GetBillIdForExistRec!=0)
				{
					DataComingFromDB=true;
				}
				
				for(var i=0;i<separXMlPath.length;i++)
				{
					xmlHttp=GetXmlHttpObject();		
					if(separXMlPath[i].substring(separXMlPath[i].length-2,separXMlPath[i].length)!="_D")
					{
						var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + separXMlPath[i];	
						xmlHttp.onreadystatechange = function()
						{
							if(xmlHttp.readyState == 4) 
							{
								var decXML = xmlHttp.responseText;
								var xmlDOM = getDOMFromXML(decXML);
								var MedName="";
								var MedType="";
								var MedCat="";
								var MedWt="";
								var MedAmnt="";
								if(getXPathValueFromDOM(xmlDOM, 'MedicineName')!=null || getXPathValueFromDOM(xmlDOM, 'MedicineType')!=null || getXPathValueFromDOM(xmlDOM, 'MedicineCat')!=null || getXPathValueFromDOM(xmlDOM, 'MedicineWt')!=null)
								{
									MedName=getXPathValueFromDOM(xmlDOM, 'MedicineName');
									MedType=getXPathValueFromDOM(xmlDOM, 'MedicineType');
									MedCat=getXPathValueFromDOM(xmlDOM, 'MedicineCat');
									MedWt=getXPathValueFromDOM(xmlDOM, 'MedicineWt');
									MedAmnt=getXPathValueFromDOM(xmlDOM, 'MedicineAmnt');
								}
								else
								{
									MedName=getXPathValueFromDOM(xmlDOM, 'OtherMedicineName');
									MedType=getXPathValueFromDOM(xmlDOM, 'OtherMedicineType');
									MedCat=getXPathValueFromDOM(xmlDOM, 'OtherMedicineCat');
									MedWt=getXPathValueFromDOM(xmlDOM, 'OtherMedicineWt');
									MedAmnt=getXPathValueFromDOM(xmlDOM, 'MedicineAmnt');
								}
								var dispFieldA=new Array(MedName,MedType,MedCat,MedWt,MedAmnt);
								
								addDBDataInTable('MedicineDtlsTab','encXMLMedicine1',dispFieldA,separXMlPath[i],'','deleteMedicineRecord','');	
								counters++;
							}
						}
						xmlHttp.open("POST",encodeURI(url),false);
						xmlHttp.send(null);	
					}
				}
			}
		}
	}

</script>
<hdiits:form name="MRBSearch" validate="true" method="POST" action=""
	encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" compact="compact">
		<li class="selected"><a href="#" rel="tcontent1"><font>
		<b>Medicine Search</b></font></a></li>
	</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<iframe id="SearchData" name="SearchData" src="hdiits.htm?actionFlag=getMediSearchPage" height="75%" width="100%" scrolling="yes" frameborder="0"></iframe>
	<center>
		<hdiits:button type="button" name="Add" value="Add" onclick="GetFrmSearchAndAdd()" captionid="MRB.Add" bundle="${cmnLables}"/>
	</center>
	<br>
	<table id="MedicineDtlsTab" name="MedicineDtlsTab" width="100%" style="border-collapse: collapse;display:none" border="1"
		align="center" borderColor="BLACK">
		<tbody>
				<tr>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.MED_NAME" bundle="${cmnLables}"/></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.MED_TYPE" bundle="${cmnLables}"/></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.MED_CAT" bundle="${cmnLables}"/></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.MED_WT" bundle="${cmnLables}"/></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="MRB.MED_AMT" bundle="${cmnLables}"/></b></td>
				<td align="middle" class="fieldLabel" bgcolor="#C9DFFF"><b><fmt:message key="mrb.action" bundle="${cmnLables}"/></b></td>
			</tr>
		</tbody>
	</table>
	<br>
	<center>
	<hdiits:button type="button" name="submit" value="Submit" onclick="SendBackSrchRslt()" captionid="MRB.Submit" bundle="${cmnLables}"/>
	</center>
	<script>
	getCarryXMlComponent();
	</script>
	</div>
	</div>
	<hdiits:hidden name="MediName" id="MediName"/>
	<hdiits:hidden name="MedType" id="MedType"/>
	<hdiits:hidden name="MedCat" id="MedCat"/>
	<hdiits:hidden name="MedWt" id="MedWt"/>
	<hdiits:hidden name="OtherMediName" id="OtherMediName"/>
	<hdiits:hidden name="OtherMedType" id="OtherMedType"/>
	<hdiits:hidden name="OtherMedCat" id="OtherMedCat"/>
	<hdiits:hidden name="OtherMedWt" id="OtherMedWt"/>
	<hdiits:hidden name="MedId" id="MedId"/>
	<hdiits:hidden name="MedAmnt" id="MedAmnt"/>
<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' /> 
	<script type="text/javascript">
	initializetabcontent("maintab");
	</script> 

</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	