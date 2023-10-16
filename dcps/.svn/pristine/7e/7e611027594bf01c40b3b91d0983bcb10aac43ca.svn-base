<%
try {
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>


<fmt:setBundle basename="resources.hr.allocation.Allocation" var="AllocLab" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="newlist" value="${resValue.newlist}"> </c:set>
<c:set var="level1" value="${resValue.level1}"> </c:set>
<c:set var="idforlevel1" value="${resValue.idforlevel1}"> </c:set>
<c:set var="loggedlocIdlist" value="${resValue.loggedlocIdlist}"> </c:set>
<c:set var="DeptmstVO" value="${resValue.DeptmstVO}"> </c:set>
<html>
<script type="text/javascript">
<c:if test="${not empty newlist}">
	var idlist=new Array();
	var tmp="${idforlevel1}";
	
	var len=tmp.length;
	
	len=len*1-1;
	
	var idStr=tmp.substr(1,len-1);
	
	idlist=idStr.split(", ");
	
	var cnt=0;
	var gcnt=0;
		</c:if>
		
</script>
<script type="text/javascript">

var locidfinal='';
var emparray = new Array();
var emparraychkbox=new Array();
var empcount = 0 ;
function checkclick(form){

if(form.checked == true)
{
emparray[empcount]=document.getElementById(form.id).value;
emparraychkbox[empcount]=form.id;
empcount++;

}else
{ 
  for(var i=0; i<emparray.length; i++)
  {  
  if(emparray[i]== document.getElementById(form.id).value){
  emparray.splice(i,1);
  emparraychkbox.splice(i,1);
    empcount--;
  }

  
  }

}


}
function updateDataInTableforAllocation(hiddenField, displayFieldArray) 
	{
		
		var trow = document.getElementById(updateRow);
		
		var hFieldId = updateRow.substring(3, updateRow.length);
		
		updateRow = null;
		
		
		if(flagForUpdatedVO)
		{
		
			document.getElementById(hFieldId).value = xmlHttp.responseText +"_U";
			flagForUpdatedVO = false;
		}
		else
		{
			document.getElementById(hFieldId).value = xmlHttp.responseText;
		}
		
		
		trow.cells[0].style.display = 'none';
		
		var field = document.getElementById(displayFieldArray[0]);
		
		trow.cells[7].innerHTML = field.options[field.selectedIndex].text;	
				
	}

function updateandvalidate()
{
if(document.Allocation.sel_level1.selectedIndex==0)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectlocation"/>');
}
else if(document.Allocation.sel_level1values.selectedIndex==0)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectlocation"/>');
}
else
{
addOrUpdateRecord('updateRecord','updatesearchlocation', new Array('serialno','sel_level1', 'sel_level1values'));
}
}
function updateRecord()
{
 if (xmlHttp.readyState == 4) {
 
 var displayFieldArray = new Array("sel_level1values");

		 updateDataInTableforAllocation('addedPunch', displayFieldArray); 
		 document.getElementById('LocationComboTable').style.display='none'; 
		document.getElementById('btnUpdate').style.display='none'; 
		 
 }
}
function checkresp()
{

if(document.getElementById('txnAdd').rows.length==1)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.onerecord"/>');
}
else{
document.getElementById('btnSubmit').disabled='true';
document.Allocation.action="hdiits.htm?actionFlag=AllocationSearchFile";
document.Allocation.submit();
}

}
function editRecord(rowId)
{
document.getElementById('LocationComboTable').style.display=''; 
updateRow=null;
sendAjaxRequestForEdit(rowId,'populateForm');

		
}

function populateForm() {
	  if (xmlHttp.readyState == 4) { 	
	
	  		var decXML = xmlHttp.responseText;
	  	
			var xmlDOM = getDOMFromXML(decXML);
		
			var combostring=getXPathValueFromDOM(xmlDOM,'comboString');
			locidfinal=getXPathValueFromDOM(xmlDOM,'locationId');
	
			document.Allocation.serialno.value=getXPathValueFromDOM(xmlDOM,'serialNo'); 
			var cmbstring='${loggedlocIdlist.locId}'+'/'+'${DeptmstVO.departmentId}';
			
			
			
				document.Allocation.sel_level1.value=combostring;
				getLevel1(document.Allocation.sel_level1);
		
				
				
										    
   	    	document.getElementById('btnUpdate').style.display=''; 	
   	   
			}
			  
	   }
	
function validatesearch()
{

if(document.Allocation.datetime.value=='')
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectdatetime"/>');
document.Allocation.datetime.value='';
document.Allocation.anddatetime.value='';
return false;
}
 if(document.Allocation.anddatetime.value=='')
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectdatetime"/>');
document.Allocation.datetime.value='';
document.Allocation.anddatetime.value='';
return false;
}

if(compareDate(document.Allocation.datetime.value,document.Allocation.anddatetime.value)<=0)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectdatetime"/>');
document.Allocation.datetime.value='';
document.Allocation.anddatetime.value='';
return false;
}
 

else 
{

document.getElementById('SearchAllocation').disabled='true';
document.Allocation.action="hdiits.htm?actionFlag=resultallocation";
document.Allocation.submit();
}
}

function getLevel1(cmb)
{
			var id=cmb.value;	
			
			
			if(id=='') {return;}					
			if(id=='select') {
				var z=document.getElementById('sel_level1values');
				for (var i=z.length;i>=0;i--)
   				{	     	   								
					z.remove(z.value);
					z.remove(i);					   				
   				}
   				var y=document.createElement('option');
   				y.text='--Select--';
				y.value='Select';
   				try
				{
					z.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
	 				z.add(y);	   			 				 
				}
				return;
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
			var url = "hrms.htm?actionFlag=getallocationlevel&cmbid="+id;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processLevel1;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}			
function processLevel1()
		{						
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{     

						var textId;												
		            	var z=document.getElementById('sel_level1values');			            		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	var SubQuaStr = XMLDoc.getElementsByTagName('SubLevel');				    	
				    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('ID'); 
				    	for (var i=z.length;i>=0;i--)
	     				{	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}	     		     							
						for ( var i = 0 ; i < SubQuaStr.length ; i++ )
	     				{	     		     								
	     				    value=SubQuaStr[i].childNodes[0].text;	     				    
	     				    textId=SubCoCurrStr_ID[i].childNodes[0].text;
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=textId;

							try
	   						{
	    						z.add(y,null); 	    						
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(y);	   			 				 
	   						}     						     					
	           			}
	           			if(locidfinal!=''){
	           			
	           			document.getElementById('sel_level1values').value=locidfinal;locidfinal='';
	           			}
				}
				else 
				{  			

				}
			}
		}
function validateSamelocation()
{
if(emparraychkbox.length==0)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.onerecord"/>');
}

else
{
 for(var b=0; b<emparraychkbox.length; b++)
  {  
  
  document.getElementById(emparraychkbox[b]).checked='';
  document.getElementById(emparraychkbox[b]).disabled="true";
  }
  
  var locidsame='${loggedlocIdlist.locId}';
 
document.getElementById('btnAddSamelocation').style.display='none';
document.getElementById('LocationDetailsTable').style.display='none'; 

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
			var url = "hrms.htm?actionFlag=getEmployeeXML&emparray="+emparray+"&locationid="+locidsame;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));

}
}

function validate()
{
if(emparraychkbox.length==0)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.onerecord"/>');
}
else if(document.Allocation.sel_level1.selectedIndex==0)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectlocation"/>');
}
else if(document.Allocation.sel_level1values.selectedIndex==0)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectlocation"/>');
}
else
{
 
  
  var locid=document.Allocation.sel_level1values.value;
 
document.getElementById('btnAdd').style.display='none';
document.getElementById('LocationComboTable').style.display='none'; 

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
			var url = "hrms.htm?actionFlag=getEmployeeXML&emparray="+emparray+"&locationid="+locid;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));

}
}
function processResponse()
{




if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var xmlStr = xmlHttp.responseText;
					
			    	var XMLDoc=getDOMFromXML(xmlStr);
			    			    

			    	var XmlValues=XMLDoc.getElementsByTagName('Xmlfile');
			    	var empname=XMLDoc.getElementsByTagName('Name');
			    	var Categoryfnl=XMLDoc.getElementsByTagName('Category');
			    	var Postfnl=XMLDoc.getElementsByTagName('Post');
			    	var Dateofb=XMLDoc.getElementsByTagName('Dob');
			    	var Eduqualfnl=XMLDoc.getElementsByTagName('Eduqual');
			    	var Yrrecruitfnl=XMLDoc.getElementsByTagName('Yrrecruit');
			    	
			    	var fnllocation=XMLDoc.getElementsByTagName('Location');
			    	document.getElementById('txnAdd').style.display='';
			    	
			    	 if(XmlValues.length>0)
			    	 {

			    	
			    	 for(var i = 0 ;i < XmlValues.length ; i++)
			    	 {					
					


					var	nametbl=empname[i].childNodes[0].text;	
					
					var	Categorytbl=Categoryfnl[i].childNodes[0].text;	
					
					var	Posttbl=Postfnl[i].childNodes[0].text;
						
					var	dobtbl=Dateofb[i].childNodes[0].text;	
					
					
					
					var	Eduqualtbl=Eduqualfnl[i].childNodes[0].text;	
					
					var	Yrrecruittbl=Yrrecruitfnl[i].childNodes[0].text;	
					
					var	locnamefinal=fnllocation[i].childNodes[0].text;	
					
				
					var xmlFileName=XmlValues[i].childNodes[0].text;
						
					var displayFieldA  = new Array(nametbl,Categorytbl,Posttbl,dobtbl,Eduqualtbl,Yrrecruittbl,locnamefinal);

					addDBDataInTable('txnAdd','addedPunch',displayFieldA,xmlFileName,'editRecord','','');
							
					}
					
					
				}
				 
			}
		}
		for(var b=0; b<emparraychkbox.length; b++)
  			{  
  
  				document.getElementById(emparraychkbox[b]).checked='';
  				document.getElementById(emparraychkbox[b]).disabled="true";
  			}
					for(var i=0; i<emparray.length; i++)
 					 {  
 						emparray.splice(i,1);
  						emparraychkbox.splice(i,1);
   						empcount--;
 					}
 				
}


function OtherLocation()
{
document.getElementById('btnAdd').style.display='';
document.getElementById('btnAddSamelocation').style.display='none';
document.getElementById('LocationComboTable').style.display=''; 
document.getElementById('LocationDetailsTable').style.display='none'; 
 
}
function Samelocation()
{
document.getElementById('btnAdd').style.display='none';
document.getElementById('btnAddSamelocation').style.display='';
document.getElementById('LocationComboTable').style.display='none'; 
document.getElementById('LocationDetailsTable').style.display=''; 

  
}
function Closebt()
{	
	method="POST";
	document.Allocation.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.Allocation.submit();
}

</script>





<hdiits:form name="Allocation" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Allocation.allocation" bundle="${AllocLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
  
	<div id="tcontent1" class="tabcontent" tabno="0">	
	
	<hdiits:fieldGroup titleCaptionId="Allocation.further" bundle="${AllocLab}" expandable="true"  > 
	<table width="100%" >
	
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.dobbetween" bundle="${AllocLab}"/></b></td>
	<td width="25%"><hdiits:dateTime name="datetime" captionid="Allocation.dobbetween" bundle="${AllocLab}" default="" maxvalue="31/12/2099"/></td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.and" bundle="${AllocLab}"/></b></td>
	<td width="25%"><hdiits:dateTime name="anddatetime" captionid="Allocation.dobbetween" bundle="${AllocLab}" default="" maxvalue="31/12/2099"/></td>
	</tr>	
	
	
	</table>
		  
		  
		  <table align="center">
	<tr align="center">
	<td align="center">
	<hdiits:button name="SearchAllocation" type="button" captionid="Allocation.Submit" bundle="${AllocLab}"  onclick="validatesearch()"/>
	<hdiits:hidden name="serialno" default="" id="serialno"/>
	</td>
	
	
		<td align="center">
		<hdiits:button name="close" type="button" captionid="Allocation.Close" bundle="${AllocLab}" onclick="Closebt()"/>
		</td>
		
		</tr>
	</table>  	
	<c:if test="${not empty newlist}">
		 
  		<table name="allocationtable" id="allocationtable"   border="1" align="center"    cellpadding="0" width="100%" cellspacing="0" BGCOLOR="WHITE"  >
	<tr class="datatableheader">
	<td  align="center" ></td>
	<td  align="center" ><b><hdiits:caption captionid="Allocation.name" bundle="${AllocLab}"/></b></td>
	
	<td align="center"><b><hdiits:caption captionid="Allocation.category" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.post" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.yrrecruit" bundle="${AllocLab}"/></b></td>
	
	<td align="center"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
	
	</tr> 
	
	<c:forEach var="allocationrow" items="${newlist}">
	
	<tr>
	
	<td align="center"><hdiits:checkbox name="check${allocationrow.serialNo}" id="check${allocationrow.serialNo}" value="${allocationrow.serialNo}"  onclick="checkclick(this);"/></td>
	<td align="center">${allocationrow.salutation} ${allocationrow.empFirstName} ${allocationrow.empMiddleName} ${allocationrow.empLastName} </td>
	<td align="center">${allocationrow.category}</td>
	<td align="center">${allocationrow.designation}</td>
	 
	<td align="center"><fmt:formatDate  value="${allocationrow.dob}" pattern="dd/MM/yyyy"/></td>
	<td align="center">${allocationrow.subEducqualification}</td>
	<td align="center">${allocationrow.yearrecruit}</td>
	<td align="center">${allocationrow.location}</td>
	
	</tr>

	</c:forEach>
	
	</table>
	</c:if>
	<c:if test="${not empty newlist}">
	<table width="100%"><tr>
  		  <td width="15%"></td>
	<td width="25%%"></td><td><hdiits:button id="btnOtherlocation" name="btnOtherlocation" type="button" onclick="OtherLocation();" captionid="Allocation.otherlocation" bundle="${AllocLab}"/></td>
	 
	<td width="25%%"><hdiits:button id="btnSamelocation" name="btnSamelocation" type="button"  captionid="Allocation.samelocation" bundle="${AllocLab}"  onclick="Samelocation();"/>
	</td>
	<td width="25%"></td>
	</tr>	
	</table>
	<table width="100%" style="display:none" name="LocationDetailsTable" id="LocationDetailsTable">
  		  <tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.type" bundle="${AllocLab}" /></b></td>
	<td width="25%">${DeptmstVO.depName}</td>
	
	<td width="25%"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
		<td width="25%">${loggedlocIdlist.locName}</td>
  		  </tr>
  		  </table>
	<table width="100%" style="display:none" name="LocationComboTable" id="LocationComboTable">
  		  <tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.type" bundle="${AllocLab}" /></b></td>
	<td width="25%"><hdiits:select name="sel_level1" id="sel_level1"  onchange="getLevel1(this)" sort="false"  ><hdiits:option value="select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	
	<c:forEach items="${level1}" var="level1">
	 <option>
	<c:out value="${level1}"/></option>
	</c:forEach>	
	
	</hdiits:select></td>
	
	<td width="25%"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
		<td width="25%"><hdiits:select name="sel_level1values" default="0" id="sel_level1values"><hdiits:option value="select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
		
	</hdiits:select></td>
  		  </tr>
  		  </table>
  		  
	<table width="90%"><tr>
	<td width="50%"></td><td><hdiits:button id="btnAdd" name="btnAdd" type="button" onclick="javascript:validate()" captionid="Allocation.Add" bundle="${AllocLab}" style="display:none"/>
	<hdiits:button id="btnUpdate" name="btnUpdate" type="button"  captionid="Allocation.Update" bundle="${AllocLab}" style="display:none" onclick="updateandvalidate();" />
	<hdiits:button id="btnAddSamelocation" name="btnAddSamelocation" type="button" onclick="validateSamelocation();" captionid="Allocation.Add" bundle="${AllocLab}" style="display:none"/>
	</td>
	</tr>	
	</table>
	
	</c:if>
	
	<table name="txnAdd" id="txnAdd"   border="1" align="center"    cellpadding="0" width="100%" cellspacing="0" BGCOLOR="WHITE"  style="display:none">
	<tr class="datatableheader">
	<td  align="center" ><b><hdiits:caption captionid="Allocation.name" bundle="${AllocLab}"/></b></td>
	
	<td align="center"><b><hdiits:caption captionid="Allocation.category" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.post" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.yrrecruit" bundle="${AllocLab}"/></b></td>
	
	<td align="center"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
	<td align="center" ></td>
	</tr> 
	
	</table>
	<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center; display:none;" id="noRecFnd" >
	<tr>
		<td align="center">
		<hdiits:caption captionid="Allocation.norecfnd" bundle="${AllocLab}" captionLang="single"/>
		</td>
	</tr>
		
</table>
</hdiits:fieldGroup>
	</div>	
	
<table align="center">
	<tr align="center">
	<td align="center">
	
	<hdiits:button name="btnSubmit" type="button" captionid="Allocation.Submit" bundle="${AllocLab}"  onclick="checkresp()" style="display:none"/>
	</td>
	<td align="center">
		<hdiits:button name="closebt" type="button" captionid="Allocation.Close" bundle="${AllocLab}" onclick="Closebt()" style="display:none"/>
		</td>
	</tr>
	</table>
	
 	</div>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
	<script>
	<c:if test="${not empty newlist}">
	
	<c:if test="${not empty idforlevel1}">
	
	var sel_level1=document.getElementById('sel_level1');
	
	for(var i=1;i<=idlist.length;i++)
	{
		sel_level1.options[i].value=idlist[gcnt];
		gcnt++;
	}
	document.getElementById('btnSubmit').style.display='';
	document.getElementById('closebt').style.display='';
	</c:if>
	</c:if>
	
	if('${resValue.noRecFnd}'=="1")
	{
	document.getElementById('noRecFnd').style.display='';
	}
</script>

</hdiits:form>
</html>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>