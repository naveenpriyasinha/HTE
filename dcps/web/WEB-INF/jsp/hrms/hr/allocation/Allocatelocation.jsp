<%
try {
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>


<fmt:setBundle basename="resources.hr.allocation.Allocation" var="AllocLab" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="xmlFilePathNameForMulAdd" value="${resValue.xmlFilePathNameForMulAdd}" />
<c:set var="fileid" value="${resValue.fileId}"/>
<c:set var="hdInvvisitPanchMpgSet" value="${resValue.hdInvvisitpanchMpgSetKey}" />
<c:set var="level1" value="${resValue.level1}"> </c:set>
<c:set var="idforlevel1" value="${resValue.idforlevel1}"> </c:set>
<c:set var="newlist" value="${resValue.newlist}"> </c:set>
<c:set var="xmlFileNamesame" value="${resValue.xmlFileNamesame}"> </c:set>
<c:set var="locIdSame" value="${resValue.locId}"> </c:set>
<html>
<script type="text/javascript">

	var idlist=new Array();
	var tmp="${idforlevel1}";
	
	var len=tmp.length;
	
	len=len*1-1;
	
	var idStr=tmp.substr(1,len-1);
	
	idlist=idStr.split(", ");
	
	var cnt=0;
	var gcnt=0;
	var myrowId='';	
	 var locid='';
</script>
<hdiits:form name="Allocation" validate="true" method="POST"  action="./hrms.htm?actionFlag=AllocateLocation" encType="text/form-data" >
<script type="text/javascript">
var locidfinal='';
var emparray=new Array();
function editRecord(rowId)
{
myrowId=rowId;
updateRow=null;

sendAjaxRequestForEdit(rowId,'populateForm');

		
}

function populateForm() {
	  if (xmlHttp.readyState == 4) { 	
	
	  	var decXML = xmlHttp.responseText;
	  	
		var xmlDOM = getDOMFromXML(decXML);
		
		
		document.Allocation.sel_level1.value=getXPathValueFromDOM(xmlDOM,'comboString'); 
		document.Allocation.serialno.value=getXPathValueFromDOM(xmlDOM,'serialNo'); 
		
			locidfinal=getXPathValueFromDOM(xmlDOM,'locationId');
			getLevel1(document.Allocation.sel_level1);
			
			 
			   
			    
   	    	document.getElementById('btnUpdate').style.display=''; 	
   	   
			
			  
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

function validate()
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
  var delRow = document.getElementById(myrowId);
			
			delRow.parentNode.deleteRow(delRow.rowIndex);	
  
   locid=document.Allocation.sel_level1values.value;
 emparray[0]=document.Allocation.serialno.value;



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
				var locsame='${locIdSame}';
				if(locid!=locsame)
			{
					addDBDataInTable('txnAdd','addedPunch',displayFieldA,xmlFileName,'editRecord','','');
				}
				if(locid==locsame)	
				{
				addDBDataInTable('allocationtable','addedSameLocation',displayFieldA,xmlFileName,'editRecord','','');
				}					
				}
					
					
				}
				 
			}
		}
					
					
 						emparray.splice(0,1);
  						
   					document.getElementById('btnUpdate').style.display='none';	
 					
 				document.Allocation.sel_level1.value='select';
 				getLevel1(document.Allocation.sel_level1);
}



</script>







<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Allocation.allocation" bundle="${AllocLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
  
	<div id="tcontent1" class="tabcontent" tabno="0">	
	
	
		  
		  
		 
	<hdiits:hidden name="serialno" default="0" id="serialno"/>
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
	 <hdiits:hidden name="fileId" id="fileId" default="${fileid}"/>	
	
	
  		 
  		
	<table width="100%">
  		  <tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.type" bundle="${AllocLab}"/></b></td>
	<td width="25%"><hdiits:select name="sel_level1" id="sel_level1"  onchange="getLevel1(this)" sort="false" ><hdiits:option value="select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	
	<c:forEach items="${level1}" var="level1">
	 <option>
	<c:out value="${level1}"/></option>
	</c:forEach>	
	
	</hdiits:select></td>
	
	<td width="25%"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
		<td width="25%"><hdiits:select name="sel_level1values" default="0" id="sel_level1values" ><hdiits:option value="Select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
		
	</hdiits:select></td>
  		  </tr>
  		  </table>
  		  <table width="90%"><tr>
	<td width="50%"></td><td>
	
	
	<hdiits:button id="btnUpdate" name="btnUpdate" type="button"  captionid="Allocation.Update" bundle="${AllocLab}" style="display:none" onclick="validate();"/>
	</td>
	</tr>	
	</table>
	<br>
	<hdiits:fieldGroup titleCaptionId="Allocation.listSamelocation" bundle="${AllocLab}" expandable="true"  > 
  		   
  		<table name="allocationtable" id="allocationtable"   border="1" align="center"    cellpadding="0" width="100%" cellspacing="0" BGCOLOR="WHITE"  >
	<tr class="datatableheader" >
	
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
	</hdiits:fieldGroup>
	<c:forEach items="${newlist}" var="newlist" varStatus="x">

<c:set var="xmlFilesame" value="${xmlFileNamesame[x.index]}" ></c:set>


<c:set var="namesame" value="${newlist.salutation} ${newlist.empFirstName} ${newlist.empMiddleName} ${newlist.empLastName}"/>

<c:set var="categorysame" value="${newlist.category}"/>
<c:set var="postsame" value="${newlist.designation}"/>
<fmt:formatDate var="dobsame" value="${newlist.dob}" pattern="dd/MM/yyyy"/>

<c:set var="eduqualificationsame" value="${newlist.subEducqualification}(${newlist.specialisation})"/>
<c:set var="yrrecruitsame" value="${newlist.yearrecruit}"/>

<c:set var="locationjurissame" value="${newlist.location}"/>


<script type="text/javascript">
var xmlFilesameName = '${xmlFilesame}';
var displayFieldA  = new Array('${namesame}','${categorysame}','${postsame}','${dobsame}','${eduqualificationsame}','${yrrecruitsame}','${locationjurissame}');

addDBDataInTable('allocationtable','addedSameLocation',displayFieldA,xmlFilesameName,'editRecord','','');
</script>
</c:forEach>
	
	
	
	
  		  
	
	<hdiits:fieldGroup titleCaptionId="Allocation.listOtherlocation" bundle="${AllocLab}" expandable="true"  > 
	
	<table name="txnAdd" id="txnAdd"   border="1" align="center"    cellpadding="0" width="100%" cellspacing="0" BGCOLOR="WHITE"  >
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
	</hdiits:fieldGroup>
	<c:forEach items="${hdInvvisitPanchMpgSet}" var="hdInvvisitPanchMpgTuples" varStatus="x">

<c:set var="curXMLFileName" value="${xmlFilePathNameForMulAdd[x.index]}" ></c:set>


<c:set var="name12" value="${hdInvvisitPanchMpgTuples.salutation} ${hdInvvisitPanchMpgTuples.empFirstName} ${hdInvvisitPanchMpgTuples.empMiddleName} ${hdInvvisitPanchMpgTuples.empLastName}"/>

<c:set var="category" value="${hdInvvisitPanchMpgTuples.category}"/>
<c:set var="post" value="${hdInvvisitPanchMpgTuples.designation}"/>
<fmt:formatDate var="dob" value="${hdInvvisitPanchMpgTuples.dob}" pattern="dd/MM/yyyy"/>

<c:set var="eduqualification" value="${hdInvvisitPanchMpgTuples.subEducqualification}"/>
<c:set var="yrrecruit" value="${hdInvvisitPanchMpgTuples.yearrecruit}"/>

<c:set var="locationjuris" value="${hdInvvisitPanchMpgTuples.location}"/>


<script type="text/javascript">
var xmlFileName = '${curXMLFileName}';
var displayFieldA  = new Array('${name12}','${category}','${post}','${dob}','${eduqualification}','${yrrecruit}','${locationjuris}');

addDBDataInTable('txnAdd','addedPunch',displayFieldA,xmlFileName,'editRecord','','');
</script>
</c:forEach>
	
	
	
	
	
	</div>	
	
	
	
 	</div>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
	<script>
	
	var sel_level1=document.getElementById('sel_level1');
	
	for(var i=1;i<=idlist.length;i++)
	{
		sel_level1.options[i].value=idlist[gcnt];
		gcnt++;
	}

</script>

</hdiits:form>
</html>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>