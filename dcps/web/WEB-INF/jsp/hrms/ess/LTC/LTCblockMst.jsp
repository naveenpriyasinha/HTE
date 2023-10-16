<%
try { 
%>
<%@ include file="../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VisitingPlaceList" value="${resultValue.VisitingPlaceList}"></c:set>
<c:set var="objLtcBlockMst" value="${resultValue.objLtcBlockMst}"></c:set>
<c:set var="arBlockYear" value="${resultValue.arBlockYear}"></c:set>
<c:set var="duplicate" value="${resultValue.duplicate}"></c:set>

<fmt:setBundle basename="resources.ess.ltc.AlertMessages" var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.ltc.LtcCaption" var="ltcLables" scope="request"/>
<script>
/*function changeLabel()
{
	if(document.frmLtcBlockMst.rdoActivate[0].checked)
	{
		document.getElementById("startDateId").style.display = '';
		document.getElementById("endDateId").style.display = 'none';
	}
	else
	{
		document.getElementById("startDateId").style.display = 'none';
		document.getElementById("endDateId").style.display = '';
	}
	document.getElementById('blkStartEndDate').focus();
}*/

function submitLtcBlk()
{
	var blockId = "${objLtcBlockMst.blockId}";
	if(blockId == null || blockId == '')
	{
		blockId = 0;
	}
	document.frmLtcBlockMst.action="hrms.htm?actionFlag=geNewtLtcBlkSubmit&blockId="+blockId;
	document.frmLtcBlockMst.submit();
}

function getBlocks(obj)
{
	var blockType = obj.value;
	if(blockType == "LTC")
	{
		document.frmLtcBlockMst.selBlkSize.value='4';
	}
	else if(blockType == "Home Town")
	{
		document.frmLtcBlockMst.selBlkSize.value='1';
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
           	   	  alert(awardMsg[8]);        
            	  return false;        
      		}
 		}			 
    }	
     
	var url ="hrms.htm?actionFlag=addEditLtcBlock&blockId=0&blockType="+blockType;      
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = processResponse;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}

function processResponse()
{	
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{          				
			var textId;						
           	//var z=document.getElementById('awdCat');	
           	var z=document.getElementById('selBlkStartYr');
                                   		            			            	
	    	var xmlStr = xmlHttp.responseText;
	    	
	    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;			
	    	
	    	var blockYr = XMLDoc.getElementsByTagName('BlockYear');   	     
   	   		
   	        for (var i=z.length;i>0;i--)  // removing a previous value of combo
			{	     				     					
				z.remove(z.value);	     				
				z.remove(z.text);
			}
			   	     	     		     							
			for ( var i = 0 ; i < blockYr.length ; i++ )
			{	     		     								
			    value1= blockYr[i].childNodes[0].text;
			
			     //alert(awardId);
			    	     				    	     					
			    //alert(value1);
				var temp=document.createElement('option');
				var temp1=document.createElement('option');
				temp.text = "--Select--";
				temp.value='0';
				temp1.text = value1;
				temp1.value = value1;
						 					
				try
				{
					if(i==0)
						z.add(temp,null); 
					
					z.add(temp1,null); 
				}
				catch(ex)
				{
					if(i==0)
						z.add(temp);	   			 
	 				z.add(temp1);	   			 				 
				} 
         	}
         	
         	   
		}
		else 
		{  			
			alert("ERROR IN PROCESS RESPONSE...");					
		}
	}
	
}



function calcEndYear(obj)
{
	var blkStartYr = obj.value;	
	if(blkStartYr != 0)
	{
		document.getElementById("txtBlkEndYr").value = blkStartYr * 1 + document.getElementById("selBlkSize").value * 1;
		document.getElementById("blkStartDate").value = "01/01/" + document.getElementById("selBlkStartYr").value;
		document.getElementById("blkEndDate").value = "31/12/" + document.getElementById("txtBlkEndYr").value;
	}
}

function checkDates()
{
	if(document.getElementById("blkStartDate").value!="" && document.getElementById("blkEndDate").value!="")
	{			
		var lStartDate=document.getElementById("blkStartDate").value;							
		var lEndDate=document.getElementById("blkEndDate").value;					
		if(compareDate(lStartDate,lEndDate) < 0 )
		{
			alert("End date must be greater than start date");
			document.getElementById('blkStartDate').value='';
			document.getElementById("blkEndDate").value="";
			return;			
		}
	}
}
function goback()
{
	var urlstyle="hdiits.htm?actionFlag=getExistinLtcBlckList";
	document.frmLtcBlockMst.action=urlstyle;
	document.frmLtcBlockMst.submit();
}

function closeWindow()
{
	document.frmLtcBlockMst.action = "hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
   	document.frmLtcBlockMst.submit();
}
</script>
<hdiits:form name="frmLtcBlockMst" validate="true" method="post">
<fmt:formatDate var="blkStartDt" pattern="dd/MM/yyyy" value="${objLtcBlockMst.startDate}" type="date" />
<fmt:formatDate var="blkEndDt" pattern="dd/MM/yyyy" value="${objLtcBlockMst.endDate}" type="date" />
	<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected">
					<a href="#" rel="tcontent1"><b><hdiits:caption captionid="HRMS.blckMaster" bundle="${ltcLables}" captionLang="single"/> </b></a>
				</li>
			</ul>
	</div>
	<div class="tabcontentstyle">	
		<div id="tcontent1" class="tabcontent" tabno="0">
			<table width="100%">
				
				<tr>
					<td width="25%"><hdiits:caption captionid="HRMS.blocktype" bundle="${ltcLables}"/></td>
					
					<td width="25%">
						<hdiits:select sort="false" name="visitingPlace" size="1" id="visitingPlace" captionid="HRMS.blocktype" bundle="${ltcLables}" mandatory="true" onchange="getBlocks(this);" >
							<hdiits:option value="0">
								<fmt:message key="HR.SELECT" bundle="${alertLables}" />
							</hdiits:option>
							<c:forEach var="visitingPlace" items="${VisitingPlaceList}">
								<hdiits:option value="${visitingPlace.lookupName}">
									${visitingPlace.lookupDesc}
								</hdiits:option>
							</c:forEach>							
						</hdiits:select>
					</td>
					<td width="25%"><hdiits:caption captionid="HRMS.blockSize" bundle="${ltcLables}"/></td>
					
					<td width="25%">
						<hdiits:select sort="false" name="selBlkSize" size="1" id="selBlkSize" captionid="HRMS.blocktype" bundle="${ltcLables}" mandatory="true" onchange="calcEndYear(document.getElementById('selBlkStartYr'));" >
							<hdiits:option value="0">
								<fmt:message key="HR.SELECT" bundle="${alertLables}" />
							</hdiits:option>
							<c:set var="i" value="1" />	
							<hdiits:option value="${i}">
									${i}
								</hdiits:option>							
							<c:set var="i" value="${i+1}" />							
								<hdiits:option value="${i}">
									${i}
								</hdiits:option>
								<c:set var="i" value="${i+1}" />							
								<hdiits:option value="${i}">
									${i}
								</hdiits:option>
								<c:set var="i" value="${i+1}" />							
								<hdiits:option value="${i}">
									${i}
								</hdiits:option>
								<c:set var="i" value="${i+1}" />							
								<hdiits:option value="${i}">
									${i}
								</hdiits:option>					
						</hdiits:select>
					</td>
				</tr>
				<tr>
					<td><hdiits:caption captionid="HRMS.blkStartYr" bundle="${ltcLables}"/></td>
					
					<td><hdiits:select sort="false" name="selBlkStartYr" size="1" id="selBlkStartYr" captionid="HRMS.blkStartYr" bundle="${ltcLables}" mandatory="true"	onchange="calcEndYear(this)">
							<hdiits:option value="0">
								<fmt:message key="HR.SELECT" bundle="${alertLables}" />
							</hdiits:option>
							<c:forEach var="blockYr" items="${arBlockYear}">
								<hdiits:option value="${blockYr}">
									${blockYr}
								</hdiits:option>
							</c:forEach>
						</hdiits:select>
					</td>
					<td><hdiits:caption captionid="HRMS.blkEndYr" bundle="${ltcLables}"/></td>
					
					<td><hdiits:text name="txtBlkEndYr" id="txtBlkEndYr" captionid="HRMS.blkEndYr" bundle="${ltcLables}" readonly="true"/>
							
					</td>
					
				</tr>
			<tr>
				<td><hdiits:caption captionid="HRMS.activateFlag" bundle="${ltcLables}"/></td>
				
				<td><hdiits:radio name="rdoActivate" value="Y" captionid="HRMS.active" bundle="${ltcLables}" mandatory="false" default="Y"/><br>
				<hdiits:radio name="rdoActivate" value="N" captionid="HRMS.deactive" bundle="${ltcLables}" mandatory="false"/></td>
			</tr>
			<tr>
				<td id="startDateId" ><hdiits:caption captionid="HRMS.blkStartDate" bundle="${ltcLables}"/></td>
				<td><hdiits:dateTime name="blkStartDate" captionid="HRMS.blkStartDate" bundle="${ltcLables}" mandatory="true" maxvalue="31/12/2099 00:00:00" afterDateSelect="checkDates();" /></td>
				<td id="endDateId" ><hdiits:caption captionid="HRMS.blkEndDate" bundle="${ltcLables}"/></td>				
				<td><hdiits:dateTime name="blkEndDate" captionid="HRMS.blkEndDate" bundle="${ltcLables}" mandatory="true" maxvalue="31/12/2099 00:00:00" afterDateSelect="checkDates();" /></td>
			</tr>

		</table>
		<br>
		<br>
		<br>
		
		<table align="center">
			<tr>
				<c:if test="${objLtcBlockMst eq null}">
					<td><hdiits:button type="button" name="subButton" id="subButton" captionid="HR.Submit" bundle="${alertLables}" style="width:100px" onclick="submitLtcBlk()"/></td>
				</c:if>
				<c:if test="${objLtcBlockMst ne null}">
					<td><hdiits:button type="button" name="subButton" id="subButton" captionid="HRMS.blkUpdate" bundle="${ltcLables}" style="width:100px" onclick="submitLtcBlk()"/></td>
				</c:if>
				<td><hdiits:button type="button" name="Back" id="back" captionid="HRMS.back" bundle="${ltcLables}" style="width:100px" onclick="goback()" /></td>
				<td><hdiits:button type="button" name="close" id="close" captionid="HR.Close" bundle="${alertLables}" style="width:100px" onclick="closeWindow()" />
				</td>
			</tr>
		</table>
		<br>
		<c:if test="${duplicate eq 'duplicate'}">
		<center><b><font color="red"><fmt:message key="HRMS.alreadyExist" bundle="${ltcLables}" /></font></b></center>
		</c:if>
		</div>	
	</div>
</hdiits:form>	
<script type="text/javascript">
	initializetabcontent("maintab")
	//alert("${objLtcBlockMst}");
	if("${objLtcBlockMst}" != null && "${objLtcBlockMst}" != '')
	{		
		document.getElementById("visitingPlace").value = "${objLtcBlockMst.cmnLookupMstBlockType.lookupName}"
		document.getElementById("selBlkStartYr").value = "${objLtcBlockMst.fromYear}";
		document.getElementById("txtBlkEndYr").value = "${objLtcBlockMst.toYear}";
		var frmYear = "${objLtcBlockMst.fromYear}";
		var toYear = "${objLtcBlockMst.toYear}";
		document.getElementById("selBlkSize").value = toYear - frmYear;
		if("${objLtcBlockMst.activateFlag}" == 'Y')
		{
			document.frmLtcBlockMst.rdoActivate[0].checked = 'true';
		}
		else if("${objLtcBlockMst.activateFlag}" == 'Y')
		{
			document.frmLtcBlockMst.rdoActivate[1].checked = 'true';
		}
		document.getElementById("blkStartDate").value = '${blkStartDt}';	
		document.getElementById("blkEndDate").value = '${blkEndDt}';
		
	}
	//alert("ooo"+"${objLtcBlockMst.fromYear} - ${objLtcBlockMst.toYear}"+"ooo");
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>