
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>


<%@page import="com.tcs.sgv.common.utils.DateUtility"%>
<fmt:setBundle basename="resources.trng.NominationLables" var="nomLable" scope="request" />

<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" >
var counter = 1;
function getTrainingDetail()
{

	var actionFlag = "getTrainingDetailForSelfNomination";
	var TID=document.getElementById('hdntrngId').value;
	var SCD=document.getElementById('hdnschId').value;
   
	xmlHttp=GetXmlHttpObject();

	if(xmlHttp == null)
	{
		alert('Your browser does not support AJAX!');
	}
  
  	var url= '${contextPath}' + '/hdiits.htm?actionFlag=' + actionFlag + '&TrainingId=' + TID + '&ScheduleID=' + SCD;
  
  	showProgressbar("Please Wait...");
  	xmlHttp.onreadystatechange=TrainingResponse;
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(encodeURIComponent(null));

}

function TrainingResponse()
{
	var chk;
	var displayFieldA=new Array();
	if (xmlHttp.readyState == 4) 
	{     
		   
		if (xmlHttp.status == 200) 
		{ 
			var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML);	
			
			if(decXML!= null)
		    {
	    		if(getXPathValueFromDOM(xmlDOM, 'isext')!= null)      	
	    			chk=getXPathValueFromDOM(xmlDOM, 'isext');
		    	if(getXPathValueFromDOM(xmlDOM, 'elig')!= null)      	
			 		document.forms[0].elgcrt.value = getXPathValueFromDOM(xmlDOM, 'elig');
	    		if(getXPathValueFromDOM(xmlDOM, 'remarks')!= null)	
					document.forms[0].rem.value = getXPathValueFromDOM(xmlDOM, 'remarks');
				if(getXPathValueFromDOM(xmlDOM, 'location')!= null)	
					document.forms[0].loc.value = getXPathValueFromDOM(xmlDOM, 'location');
				if(getXPathValueFromDOM(xmlDOM, 'addressId/addressId')!= null)
				{

					var addrXPath = 'addressId';		
		 			editAddress('trainingAddress',xmlDOM,addrXPath);	
		 			makeReadOnly('trainingAddress');
				}
				if(getXPathValueFromDOM(xmlDOM, 'nomircvdate')!= null)    
			 		document.forms[0].nomircvdate.value = getXPathValueFromDOM(xmlDOM, 'nomircvdate');

	    	}
	    	
	    	if(chk == 'Y')
		     {
			     document.getElementById('selfdis').style.display="";
	    	 	 document.getElementById('selfdisext').style.display="";
	    	 	 document.getElementById('selfdis1').style.display="none";
				
				document.getElementById('ttt12').innerHTML = document.forms[0].elgcrt.value;
	    	 	document.getElementById('ttt13').innerHTML = document.forms[0].rem.value;
	    	 	document.getElementById('tablsub').style.display='';
	    	 }
	    	 else
	    	 {
	    	
	    	 	document.getElementById('selfdis1').style.display="";
	    	 	document.getElementById('selfdis').style.display="none";
	    	 	document.getElementById('selfdisext').style.display="none";
	    	 	document.getElementById('ttt').innerHTML = document.forms[0].elgcrt.value;
	    	 	document.getElementById('ttt1').innerHTML = document.forms[0].loc.value;
	    	 	document.getElementById('ttt2').innerHTML = document.forms[0].rem.value;
	    	 	document.getElementById('tablsub').style.display='';
	    	 }
		}
		else 
		{  
			alert("ERROR");
		}
				    	
	}
	hideProgressbar();
}
function checkvalidatefordate(altdt)
{
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	var curr_year = d.getFullYear();
	curr_month=curr_month+1;
	var curr_date_chk= curr_date + "/" + curr_month + "/" + curr_year;
   
	var nomircvlstdate=document.forms[0].nomircvdate.value;
	 var msgnomircvlstdate=document.forms[0].nomircvdate.value;
	var DD = nomircvlstdate.substr(0,2);
	var MM = nomircvlstdate.substr(3,2);
	var YY = nomircvlstdate.substr(6,4);
	nomircvlstdate=DD + "/" + MM + "/" + YY;


	var result=compareDate(nomircvlstdate,curr_date_chk); 
	
	var toDate  = new Date(YY, MM - 1, DD); 
	var frmDate = new Date(curr_year, curr_month - 1, curr_date);


	var diff =toDate.getTime() - frmDate.getTime();

	if(diff < 0)
	{
		alert(altdt+" :- " +msgnomircvlstdate);
		return false;
	}
	return true;

}
</script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<fmt:message var="ALTMSG" bundle="${nomLable}" key="TR.ALTMSG"></fmt:message>
<c:out value="${resValue.msg}" />
<hdiits:form name="frmSelfNominate" validate="true" method="POST" encType="multipart/form-data" action="./hdiits.htm?actionFlag=insertSelfNomination">
<hdiits:hidden name="elgcrt" default=""/>
<hdiits:hidden name="loc" default=""/>
<hdiits:hidden name="rem" default=""/>
<hdiits:hidden name="nomircvdate" default=""/>

<hdiits:jsField jsFunction="checkvalidatefordate('${ALTMSG}')" name="checkvalidatefordate"/>
<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SelfNomination" bundle="${nomLable}"></hdiits:caption> </a></li>
		</ul>
</div>

<div class="tabcontentstyle">

<div id="tcontent1" class="tabcontent" tabno="0">
<fmt:message key="TR.SEARCHTRNG"  bundle="${nomLable}" var="trainingTitle"></fmt:message>
<TABLE class="tabtable">
		<tr>  
		<td class="fieldLabel" colspan="6">
		<jsp:include page="trainingSearch.jsp">
			<jsp:param name="searchTrainingTitle" value="${trainingTitle}"/>
			<jsp:param name="mandatory" value="Yes" />
			<jsp:param name="functionName" value="getTrainingDetail"/>
		</jsp:include> 
		</td>
		</tr>
		<tr height="10"></tr>
		
		
</table>  
<br>

<div id="selfdis"  style="display: none;">
	<table class="tabtable" id="disself1" width="100%" align="center">
			<tr>
				<td width="25%"><hdiits:caption captionid="TR.ELEG"	bundle="${nomLable}" /></td>
				<td id="ttt12" ></td>
				<td width="25%"><hdiits:caption captionid="TR.REMARKS"	bundle="${nomLable}" /></td>
				<td id="ttt13" ></td>
		</tr>
				
	</table>
</div>
<div id="selfdis1"  style="display: none;">
	<table class="tabtable" id="disself2" width="100%" align="center" >
		<tr>
				<td width="25%"><b><hdiits:caption captionid="TR.ELEG"	bundle="${nomLable}" /></b></td>
				<td id="ttt"></td>
				<td width="25%"><b><hdiits:caption captionid="TR.LOC"	bundle="${nomLable}" /></b></td>
				<td id="ttt1"></td>
		</tr>	
		<tr>
				<td width="25%"><b><hdiits:caption captionid="TR.REMARKS"	bundle="${nomLable}" /></b></td>
				<td id="ttt2" ></td>
				<td width="25%"> </td>
		</tr>
			
	</table>
</div>
<br>
<div id="selfdisext"  style="display: none;">
	<jsp:include page="/WEB-INF/jsp/common/address.jsp">
		<jsp:param name="addrName" value="trainingAddress" />
		<jsp:param name="addrLookupName" value="Training Address" />
		<jsp:param name="addressTitle" value="Training Address" />
       
	</jsp:include>
	
</div>
  
<br>
<TABLE class="tabtable" id="tablsub" style="display:none;">
		<tr>
		<td class="fieldLabel" colspan="6" align="center"><hdiits:formSubmitButton name="formApproveButton" type="button" captionid="TR.NOM" bundle="${nomLable}"/></td>
		</tr>
		
</table>  
	
</div>

</div>

<script	type="text/javascript">
		initializetabcontent("maintab")
</script> 
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
