<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/statusbar.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<fmt:setBundle basename="resources.hr.dept.deptLables" var="deptLables" scope="request"/>
<fmt:setBundle basename="resources.eis.vacancy.vacancyReportLabels" var="vacancyReportLabels" scope="request"/>

<script>

	function showAsOnDate()
	{
		//alert('Inside showASOnDAte');
		document.getElementById('dtAsondate').style.display='';
		document.getElementById('dummy').style.display='none';
		document.getElementById('dummy1').style.display='';
		document.getElementById('dummy2').style.display='';
		document.getElementById('dummy3').style.display='';
		document.getElementById('dummy4').style.display='';
		document.getElementById('lblDate').style.display='';
		document.getElementById('trQuarter').style.display='none';
		document.getElementById('trMonth').style.display='none';
		document.getElementById('lblYear').style.display='none';
		document.getElementById('yearcombo').style.display='none';
	}
	
	function showYMQ()
	{
		//alert('Inside showYMQ');
		document.getElementById('dtAsondate').style.display='none';
		document.getElementById('dummy').style.display='';
		document.getElementById('lblDate').style.display='none';
		document.getElementById('dummy1').style.display='none';
		document.getElementById('dummy2').style.display='none';
		document.getElementById('dummy3').style.display='none';
		document.getElementById('dummy4').style.display='none';
		document.getElementById('trQuarter').style.display='';
		document.getElementById('trMonth').style.display='';
		document.getElementById('lblYear').style.display='';
		document.getElementById('yearcombo').style.display='';
		document.getElementById('dummy5').style.display='';
		getYearCombo();
	}
	
	function getMonthCombo()
	{
   		var monCmb=document.getElementById('cmbMonth');
    	var len=monCmb.options.length;
		for(var i=0;i<=len;i++)
		{
			monCmb.remove(1);
		}
		addOrUpdateRecord('addMonthCombo','getVacancyMonth',new Array('cmbMonth'));		
	}
	
	function addMonthCombo()
	{
		
		if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			if(encXML!="error")
			{
				document.getElementById('cmbMonth').disabled='';	
				var monList=encXML.split("$");
				for (var i=0; i < monList.length;++i){
					var keyValPair=monList[i].split("/");
					addOption(document.getElementById('cmbMonth'), keyValPair[0], keyValPair[1]);
				}
				
			}else
			{
				var qtrCmb=document.getElementById('cmbMonth');
				for(var i=0;i<=qtrCmb.options.length;i++)
				{
					qtrCmb.remove(1);
				}
				document.getElementById('cmbMonth').disabled="true";
			}	
		}
	}
	
	function getYearCombo(){
		var yearCmb=document.getElementById('cmbYear');
    	var len=yearCmb.options.length;
		for(var i=0;i<=len;i++)
		{
			yearCmb.remove(1);
		}
		addOrUpdateRecord('addYearCombo','getVacancyYear',new Array('cmbYear'));
	}

	function addYearCombo(){
		
		if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			if(encXML!="error")
			{
				document.getElementById('cmbYear').disabled='';	
				var yearList=encXML.split("$");
				for (var i=0; i < yearList.length;++i){
					var keyValPair=yearList[i].split("/");
					addOption(document.getElementById('cmbYear'), keyValPair[0], keyValPair[1]);
				}
			}else
			{
				var yearCmb=document.getElementById('cmbYear');
				for(var i=0;i<=yearCmb.options.length;i++)
				{
					yearCmb.remove(1);
				}
				document.getElementById('cmbYear').disabled="true";
			}	
		}
	}

	function addOption(selectbox,text,value )
	{
		var optn = document.createElement("OPTION");
		optn.text = text;
		optn.value = value;
		selectbox.options.add(optn);
	}   
	
	
	function disableMonth()
	{
		document.getElementById('cmbMonth').disabled=true;
	}
	
	function disableQuarter()
	{
		document.getElementById('cmbQuarter').disabled=true;
	}
	
	/*function refreshasset()
	{
		document.getElementById("classOfficer").value="-1";
		document.getElementById("classOfficerCol").style.display = "none";
		document.getElementById("captionClassOfficer").style.display = "none";
		document.getElementById("tdClassOfficer").style.display = "none";
		document.getElementById("reportFor").value="-1";
		getDesignationByClass();
		hideProgressbar();
	}*/
	
	function validateControls1()
	{
		if((document.ReportForm.asondate.value == "") && (document.getElementById('cmbYear').value=="-1")) 
		{
			alert('<fmt:message  bundle="${vacancyReportLabels}" key="HRMS.ENTERDATE"/>');
			return false;
		}
		//alert('Hello');
		if(document.getElementById('cmbYear').value!="-1")
		{
			//alert('Hello2222');
			//alert('Quarter >>>   '+document.getElementById('cmbQuarter').value);
			//alert('Month >>>   '+document.getElementById('cmbMonth').value);
			
			if(document.getElementById('cmbQuarter').value=="0" && document.getElementById('cmbMonth').value=="-1")
			{
				alert('<fmt:message  bundle="${vacancyReportLabels}" key="HRMS.QTRMONTH"/>');
				return false;
			}
		}
		return true;
	}
	
</script>
		
			<tr>
			    <td>&nbsp;</td>
			    <td><hdiits:radio name="rdbselType" value="1" captionid="HRMS.AsOnDate" bundle="${vacancyReportLabels}" id="rdbselType" onclick="showAsOnDate()" mandatory="true"/></td>
			    <td></td>
			    <td colspan="1"></td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td><hdiits:radio name="rdbselType" value="2" captionid="HRMS.YMQ" bundle="${vacancyReportLabels}" id="rdbselType" onclick="showYMQ()" mandatory="true"/></td>
			    <td></td>
			    <td colspan="1"></td>
			    <td>&nbsp;</td>            
			</tr>
			
			<tr>
			    <td id="dummy1" style="display:none">&nbsp;</td>
			    <td class="captionTag" id="lblDate" style="display:none"><hdiits:caption captionid="HRMS.DATE" bundle="${vacancyReportLabels}"/></td>
			    <td id="dummy2" style="display:none">&nbsp;</td>
			    <td colspan="1" id="dtAsondate" style="display:none"> 
			       <hdiits:dateTime captionid="HRMS.DATE" bundle="${vacancyReportLabels}" name="asondate"/>
			    </td>
			    <td id="dummy3" style="display:none">&nbsp;</td>
			    <td id="dummy4" style="display:none">&nbsp;</td>
			    
			    <td style="display:none" colspan="6" id="dummy">&nbsp;</td>
			    
			    <td class="captionTag" id="lblYear" style="display:none"><hdiits:caption captionid="HRMS.YEAR" bundle="${vacancyReportLabels}"/></td>
			    <td id="dummy5" style="display:none">&nbsp;</td>
			    <td colspan="1" nowrap="nowrap" id="yearcombo" style="display:none">
			    	<hdiits:select name="cmbYear" id="cmbYear" onchange="getMonthCombo();" sort="true">
							<hdiits:option value="-1">---Select---</hdiits:option> 
					</hdiits:select >
			    </td>
			    <td>&nbsp;</td>            
			</tr>
			
			<tr id="trQuarter" style="display:none">
			    <td>&nbsp;</td>
			    <td></td>
			    <td>&nbsp;</td>
			    <td colspan="1"></td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			            
			    <td class="captionTag"><hdiits:caption captionid="HRMS.QUARTER" bundle="${vacancyReportLabels}"/></td>
			    <td>&nbsp;</td>
			    <td colspan="1">
			    	<hdiits:select name="cmbQuarter" id="cmbQuarter" onchange="disableMonth()" sort="false">
							<hdiits:option value="0">---Select---</hdiits:option> 
							<hdiits:option value="1"><hdiits:caption captionid="HRMS.FIRSTQTR" bundle="${vacancyReportLabels}"/></hdiits:option> 
							<hdiits:option value="2"><hdiits:caption captionid="HRMS.SECONDQTR" bundle="${vacancyReportLabels}"/></hdiits:option> 
							<hdiits:option value="3"><hdiits:caption captionid="HRMS.THIRDQTR" bundle="${vacancyReportLabels}"/></hdiits:option> 
							<hdiits:option value="4"><hdiits:caption captionid="HRMS.FOURTHQTR" bundle="${vacancyReportLabels}"/></hdiits:option> 
					</hdiits:select >
			    </td>
			    <td>&nbsp;</td>            
			</tr>
				
			<tr id="trMonth" style="display:none">
			    <td>&nbsp;</td>
			    <td></td>
			    <td>&nbsp;</td>
			    <td colspan="1"></td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			            
			    <td class="captionTag"><hdiits:caption captionid="HRMS.MONTH" bundle="${vacancyReportLabels}"/></td>
			    <td>&nbsp;</td>
			    <td colspan="1">
			    	<hdiits:select name="cmbMonth" id="cmbMonth" onchange="disableQuarter()">
							<hdiits:option value="-1">---Select---</hdiits:option> 
					</hdiits:select >
			    </td>
			    <td>&nbsp;</td>            
			</tr>
		
			
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	

	
