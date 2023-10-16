<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/acr/acrHierarchyGrp.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript">
function submit1()
{
	document.SelfAppraisal.method="POST";
	document.SelfAppraisal.action="./hrms.htm?actionFlag=ACRSelfAppraisalByUser";
	document.SelfAppraisal.submit();
}
function validation()
{		
		var count = document.getElementById('count').value;
		for(i=0;i<=count;i++)
		{	
			var a=document.getElementById("Value"+i);		
			if(a.value=="")
			{		
				alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.Mandatory"/>');
				return;
			}	
		}
		showProgressbar("Please Wait...");				
		submit1();
}


//method to check data into textarea(it should no tbe some special characters...)

function isValidData(txtarea)
{
	
	var len,str,str1,i;
	len=txtarea.value.length;
	str=txtarea.value;
	
	//add or remove characters into this string to be checked 
	str1="`~!#$%^&*+|"
	for(i=0;i<len;i++)
	{
		
		if((str1.indexOf(str.charAt(i)))!=-1)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SpecialCharsNotAllowed"/>');
			txtarea.focus();
			return;
		}
		
	}

	return;
}

//method to check if there is already entry for this user and for selected year is there

function checkForSelfEntry()
{
		var year=document.getElementById("YearCmb").value;
		var dsgnCode=document.getElementById("dsgnCode").value;
		var authority=document.getElementById("authority").value;
		if(document.getElementById("YearCmb").selectedIndex!=0)
		{
				showProgressbar();	
				try{   
	    			xmlHttp=new XMLHttpRequest();    
		    	}
				catch (e)
				{    // Internet Explorer    
						try{
	      					xmlHttp=new 
	                        ActiveXObject("Msxml2.XMLHTTP");      
	      				}
			    		catch (e){
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
				var url = "hrms.htm?actionFlag=ACRCheckForSelfEntryByYear&year="+year+"&authority="+authority+"&dsgnCode="+dsgnCode;    		  
				xmlHttp.open("POST", encodeURI(url) , true);			
				xmlHttp.onreadystatechange = processResponseCheckSelfData;
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				xmlHttp.send(encodeURIComponent(null));
		}
		else
		{
				document.getElementById('rowCount').value="-1";
				document.getElementById("recordInserted").style.display='none';
		    	document.getElementById("closeTable1").style.display='';		    	        	
		    	document.getElementById("poitTable").style.display='none';
		    	document.getElementById("submitTable").style.display='none';	           			          			
		}
}

function processResponseCheckSelfData()
{
		if (xmlHttp.readyState == 4) 
		{    
			if (xmlHttp.status == 200) 
			{       
					var value;						
			    	var xmlStr = xmlHttp.responseText;				    	
			    	var XMLDoc=getDOMFromXML(xmlStr);  					    			    					    	
	    	        var grpFlag=XMLDoc.getElementsByTagName('dataFlag');		    	       
	    	        var pointList = XMLDoc.getElementsByTagName('Point');
	    	        var PointCode = XMLDoc.getElementsByTagName('PointCode');		    	        
	    	        if(eval(grpFlag[0].childNodes[0].text)==1)
	    	        {		    	        	
	    	        	document.getElementById("recordInserted").style.display='';
	    	        	document.getElementById("closeTable1").style.display='';		    	        	
	    	        	document.getElementById("poitTable").style.display='none';
	    	        	document.getElementById("submitTable").style.display='none';	           				
	    	        }
	    	        else
	    	        {
	    	        	document.getElementById("poitTable").style.display='';
	    	        	document.getElementById("submitTable").style.display='';
           				
           				document.getElementById("recordInserted").style.display='none';
           				document.getElementById("closeTable1").style.display='none'
           				
           				//Added By Hardik//
           				var tableId ='poitTable';
           				deleteAllRow(tableId);
           				document.getElementById(tableId).style.display='';       				
           				for(var i=0;i<PointCode.length;i++)
           				{
	          				var pointCodeValue=PointCode[i].childNodes[0].text;
	          				var pointListValue =pointList[i].childNodes[0].text;
	           				var trow=document.getElementById(tableId).insertRow();
							trow.id = 'row' + i;								
							trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='pointCode" +i+ "' id='pointCode"+i+"' value='"+pointCodeValue+"' />";
							trow.cells[0].style.display = 'none';
							trow.insertCell(1).innerHTML =i+1;
							trow.insertCell(2).innerHTML =	pointListValue;
							trow.insertCell(3).innerHTML ="<textarea name='Value"+i+"'  onkeyup='return setMaxLengthTextArea(this, 4000)' class='textareatag mandatorycontrol'  onBlur='isValidData(this)'  id='Value"+i+"' value='' cols=100 rows=3></textarea>&nbsp;&nbsp;<label class='mandatoryindicator'>*</label><label class=captionTag >(Max 4000 Characters)</label>";												
						}
	    	        }
	    	        document.getElementById('rowCount').value=i-1;
	    	        hideProgressbar();		    	               			
			}
		}			
}
//function to be called on close click
function closeFunction()
{
	document.SelfAppraisal.method="POST";

	document.SelfAppraisal.action="./hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	
	document.SelfAppraisal.submit();
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="year" value="${resValue.year}"></c:set>
<c:set var="pointList" value="${resValue.pointList}"></c:set>
<c:set var="pointCount" value="${resValue.pointCount}"></c:set>
<c:set var="trnList" value="${resValue.trnList}"></c:set>
<c:set var="officerMap" value="${resValue.officerMap}"></c:set>
<c:set var="timeLst" value="${officerMap.Time}"></c:set>
<c:set var="hieGrpDtlLst" value="${officerMap.HieGrpDtlLst}"></c:set>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<hdiits:form name="SelfAppraisal" method="POST" validate="true"  encType="multipart/form-data" >
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> 
			<fmt:message key="HR.ACR.ACR" bundle="${commonLables}"/>
		</b></a></li>
	</ul>
</div>
<div >	
	 <div id="tcontent1"  tabno="0">
	 	<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp">
		<jsp:param name="empImage" value="N"/></jsp:include>
			<table class="tabtable">
			<tr bgcolor="#386CB7">
						<td class="fieldLabel" width="100%" align="center">
						<font class="Label3" color="white">
						<u>
							<b><fmt:message key="HR.ACR.SelfAppraisal" bundle="${commonLables}"/></b>
						</u>
						</font>
						</td>
					</tr>					
			</table>
		<c:if test="${empty pointList and empty trnList}">			
				<table class="tabtable" align="center" border="1" bordercolor="black">
					<tr>
						<td colspan="4" align="center"  bgcolor="white">
							<b><hdiits:caption captionid="HR.ACR.NoPointsEntered" bundle="${commonLables}"/></b>						
						</td>
					<tr><td colspan="4"></td></tr>
					<tr>					
						<td align="center" colspan="4">
							<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="Close" type="button" onclick="closeFunction()"/>		
						</td>
					</tr>
				</table>
		</c:if>
		<c:set var="colSpanNo" value="0"/>
		<c:forEach items="${hieGrpDtlLst}" var="parentObj1" >
				<c:set var="colSpanNo" value="${colSpanNo+1}"></c:set>
		</c:forEach>
		<BR><BR>
		<hdiits:hidden name="count" id="count" default="-1"/>		
		<c:if test="${not empty pointList and empty trnList}">
			<table class="tabtable" id="poitTable" name="poitTable" border="1" bgcolor="white" bordercolor="black" >
				<tr bgcolor="B0C4DE">
					<td align="center"><b><hdiits:caption captionid="HR.ACR.index"
						bundle="${commonLables}" /></b></td>
					<td align="center"><b><hdiits:caption captionid="HR.ACR.Goal"
						bundle="${commonLables}" /></b></td>
					<td align="center" colspan="${colSpanNo}"><b><hdiits:caption
						captionid="HR.ACR.Remark" bundle="${commonLables}" /></b></td>
				</tr>
				<c:set var="i" value="0"></c:set>
				<c:set var="p" value="0"></c:set>
				<c:forEach items="${pointList}" var="point" varStatus="x">
					<tr>
						<td><c:out value="${i+1}"></c:out></td>
						<td><c:out value="${point.point}"></c:out>						
						</td>
						<c:forEach items="${hieGrpDtlLst}" var="parentObj" varStatus="sta">
						<td>							
								<hdiits:textarea id="Value${p}" captionid="HR.ACR.Remark" bundle="${commonLables}" 
								maxlength="4000" mandatory="true" name="Value${p}" validation="txt.isrequired" />
								<c:out value="Time-${timeLst[sta.index]}"></c:out>
								<hdiits:hidden name="pointCode${p}" id="pointCode${p}" default="${point.pointCode}" />
								<hdiits:hidden name="no${p}" id="no${p}" default="${parentObj.groupDtlId}" />
								<script type="text/javascript">							
									document.getElementById('count').value='${p}';
								</script>
								<c:set var="p" value="${p+1}"></c:set>	
						</td>
						</c:forEach>
					</tr>					
					<c:set var="i" value="${i+1}"></c:set>
				</c:forEach>
			</table>
			<br>
			<table id="submitTable" align="center">
			<tr>
				<td>
					<hdiits:button captionid="HR.ACR.Submit" bundle="${commonLables}" name="Submit" type="button" onclick="validation()"/>
				</td>
				<td>
					<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="Close1" type="button" onclick="closeFunction()"/>		
				</td>
			</tr>
			</table>						
		</c:if>
		<br>
		
		<c:if test="${not empty trnList}">
			<table id="recordInserted" align="center" border="1" bgcolor="white" bordercolor="black">
			<tr>
				<td colspan="4">
				<b>
					<hdiits:caption captionid="HR.ACR.RecordAlreadyInserted" bundle="${commonLables}" />
					<c:out value=" ${year} - ${year+1}"></c:out>
				</b>				
				</td>
			</tr>
			<tr>					
				<td align="center" colspan="4">
					<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="btnCloseAcr" type="button" onclick="closeFunction()"/>		
				</td>
			</tr>
			</table>
		</c:if>	
		<BR><BR>
<hdiits:hidden name="Year" id="Year" default="${year}"/>		
<hdiits:hidden name="authority" id="authority" default="${resValue.authority}"/>
<hdiits:hidden name="dsgnCode" id="dsgnCode" default="${resValue.dsgnCode}"/>
<hdiits:validate locale="${locale}" controlNames="" />
</div>
</div>
</hdiits:form>			 
<script type="text/javascript">
	initializetabcontent("maintab")
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>	