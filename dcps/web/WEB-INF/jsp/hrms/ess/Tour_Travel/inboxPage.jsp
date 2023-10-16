<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/leave/leavevalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>
<script type="text/javascript" src="script/hrms/ess/travel/travelCommonScripts.js"></script>

<%
try {
%>
<!-- For Xml File List Return By VoGen -->

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="ReqId" value="${resultValue.reqId}">
</c:set>
<c:set var="flag" value="${resultValue.flag}">
</c:set>

<c:set var="ajaxkeyList" value="${resultValue.ajaxkeyList}">
</c:set>

<c:set var="cityList" value="${resultValue.cityList}">
</c:set>
<!--Convyance Mode-->
<c:set var="ConvyanceModeList" value="${resultValue.ConvyanceMode}">
</c:set>
<!--Train-->
<c:set var="TrainList" value="${resultValue.Train}">
</c:set>
<!--Class-->
<c:set var="ClassList" value="${resultValue.Class}">
</c:set>
<!--Accomodation-->
<c:set var="AccomodationList" value="${resultValue.Accomodation}">
</c:set>

<!--Purpose_of_Stay-->
<c:set var="Purpose_of_StayList" value="${resultValue.Purpose_of_Stay}">
</c:set>
<!--owned_by-->
<c:set var="owned_byList" value="${resultValue.owned_by}">
</c:set>
<!--veh_type-->
<c:set var="veh_typeList" value="${resultValue.veh_type}">
</c:set>
<!-- Travel_Submit_Type -->
<c:set var="Travel_Submit_Type" value="${resultValue.Travel_Submit_Type}">
</c:set>

<script type="text/javascript">

//Global Variable
	var tourCnt=1;
	var errorStr="";
	
//End
	var cityList="";
	var conmodList="";
	var TrainList="";
	var ClassList="";
	var AccomodationList="";
	var Purpose_of_StayList="";
	var owned_byList="";
	var veh_typeList="";

	var cityListVal="";
	var conmodListVal="";
	var TrainListVal="";
	var ClassListVal="";
	var AccomodationListVal="";
	var Purpose_of_StayListVal="";
	var owned_byListVal="";
	var veh_typeListVal="";
	var air_modVal="";
	
	var submit_Type="";
	var submit_TypeVal="";
</script>

<c:forEach var="Travel_Submit_Type" items="${Travel_Submit_Type}">
	<script>
		submit_Type+="${Travel_Submit_Type.lookupId}";
		submit_Type+=",";	
		submit_TypeVal+="${Travel_Submit_Type.lookupDesc}";
		submit_TypeVal+=",";	
	</script>
</c:forEach>


<c:forEach var="Air_modList" items="${Air_modList}">
	<script>
		air_modVal+="${Air_modList.lookupId}";
		air_modVal+=",";	
	</script>
</c:forEach>
<c:forEach var="leavetypes" items="${cityList}">
	<script>
		cityList+="${leavetypes.cityName}";
		cityList+=",";	
		cityListVal+="${leavetypes.cityId}";
		cityListVal+=",";	
	</script>
</c:forEach>
<c:forEach var="ConvyanceModevar" items="${ConvyanceModeList}">
	<script>
	conmodList+="${ConvyanceModevar.lookupDesc}";
	conmodList+=",";
	conmodListVal+="${ConvyanceModevar.lookupId}";
	conmodListVal+=",";
	
</script>
</c:forEach>
<c:forEach var="TrainListvar" items="${TrainList}">
	<script>
		TrainList+="${TrainListvar.lookupDesc}";
		TrainList+=",";	
		TrainListVal+="${TrainListvar.lookupId}";
		TrainListVal+=",";	
	</script>
</c:forEach>
<c:forEach var="ClassListvar" items="${ClassList}">
	<script>
		ClassList+="${ClassListvar.lookupDesc}";
		ClassList+=",";	
		ClassListVal+="${ClassListvar.lookupId}";
		ClassListVal+=",";	
		
	</script>

</c:forEach>
<c:forEach var="AccomodationListvar" items="${AccomodationList}">
	<script>
		AccomodationList+="${AccomodationListvar.lookupDesc}";
		AccomodationList+=",";	
		AccomodationListVal+="${AccomodationListvar.lookupId}";
		AccomodationListVal+=",";	
		
	</script>

</c:forEach>
<c:forEach var="Purpose_of_StayListvar" items="${Purpose_of_StayList}">
	<script>
		Purpose_of_StayList+="${Purpose_of_StayListvar.lookupDesc}";
		Purpose_of_StayList+=",";	
		Purpose_of_StayListVal+="${Purpose_of_StayListvar.lookupId}";
		Purpose_of_StayListVal+=",";	
		
	</script>
</c:forEach>
<c:forEach var="owned_byvar" items="${owned_byList}">
	<script>
		owned_byList+="${owned_byvar.lookupDesc}";
		owned_byList+=",";	
		owned_byListVal+="${owned_byvar.lookupId}";
		owned_byListVal+=",";	
	</script>
</c:forEach>
<c:forEach var="veh_typevar" items="${veh_typeList}">
	<script>
		veh_typeList+="${veh_typevar.lookupDesc}";
		veh_typeList+=",";	
		veh_typeListVal+="${veh_typevar.lookupId}";
		veh_typeListVal+=",";	
		
	</script>
</c:forEach>

<script type="text/javascript">
/*
	var submit_Type="";
	var submit_TypeVal="";

*/
		var submit_TypeCmb=new Array();
		var cityCmb=new Array();
		var conmodCmb=new Array();
		var trainCmb=new Array();
		var classCmb=new Array();
		var accomCmb=new Array();
		var purCmb=new Array();
		var veh_typeCmb=new Array();
		var own_byCmb=new Array();
		var air_modCmb=new Array();
		
		var submit_TypeValCmb=new Array();
		var cityCmbVal=new Array();
		var conmodCmbVal=new Array();
		var trainCmbVal=new Array();
		var classCmbVal=new Array();
		var accomCmbVal=new Array();
		var purCmbVal=new Array();
		var veh_typeCmbVal=new Array();
		var own_byCmbVal=new Array();
		air_modCmb=air_modVal.split(",");
	
		submit_TypeCmb=submit_Type.split(",");	
		
		conmodCmb=conmodList.split(",");
		cityCmb=cityList.split(",");
		trainCmb=TrainList.split(",");
		classCmb=ClassList.split(",");
		accomCmb=AccomodationList.split(",");
		purCmb=Purpose_of_StayList.split(",");
		veh_typeCmb=veh_typeList.split(",");
		own_byCmb=owned_byList.split(",");
	
		submit_TypeValCmb=submit_TypeVal.split(",");	

		cityCmbVal=cityListVal.split(",");
		conmodCmbVal=conmodListVal.split(",");
		trainCmbVal=TrainListVal.split(",");
		classCmbVal=ClassListVal.split(",");
		accomCmbVal=AccomodationListVal.split(",");
		purCmbVal=Purpose_of_StayListVal.split(",");
		veh_typeCmbVal=veh_typeListVal.split(",");
		own_byCmbVal=owned_byListVal.split(",");
		function unableButton()
		{
			var approve=document.getElementById('approve');
			approve.disabled="";
		}
		function checkAllBox(form)
		{
			var approve=document.getElementById('approve');
			
			if(form.id=="unSelected")
			{
				for (var lCntr = 0; lCntr < document.frm1.elements.length; lCntr++)
				{ 
					var reqId=document.frm1.elements[lCntr].id;
				
				     if((document.frm1.elements[lCntr].type == "checkbox")&&(reqId.substring(0,10)=="tourChkBox") ) 
				   	  {
							document.frm1.elements[lCntr].checked ="true";
						
				      }
				}
				form.id="selected";
				approve.disabled="";
			}else
			{
				for (var lCntr = 0; lCntr < document.frm1.elements.length; lCntr++)
					{ 
						var reqId=document.frm1.elements[lCntr].id;
					
					     if((document.frm1.elements[lCntr].type == "checkbox")&&(reqId.substring(0,10)=="tourChkBox") ) 
					   	  {
								document.frm1.elements[lCntr].checked ="";
							
					      }
					}
					form.id="unSelected";
					approve.disabled="true";
			}
			
			
		}
		

		function viewDetails(form)
		{
			
			var tourId=document.getElementById('tourId');
			tourId.value=form.name;
			var url="hrms.htm?actionFlag=getTravelDtls&reqtype=view";
			document.frm1.action=url;
			document.frm1.submit();
		}
		function approveReq()
		{
			
			var urlstyle="hrms.htm?actionFlag=approveReq";
			document.frm1.action=urlstyle;
			document.frm1.submit();
		}
		function closewindow()
		{
			
			var urlstyle="hdiits.htm?actionFlag=getHomePage"
			document.frm1.action=urlstyle;
			document.frm1.submit();
		}
		function forwardreq()
		{
			
			var urlstyle="hrms.htm?actionFlag=forwardReq";
			document.frm1.action=urlstyle;
			document.frm1.submit();
		}
		function addrow()
		{
		
			var arr_plc1 = document.getElementById('arr_plc1');
			arr_plc1.selectedIndex=0;
			var tb = document.getElementById('detailsTable');
			var len = tb.rows.length;
			var nextId =len+2;
	
			var apnd=nextId/2-2;
			var arrival_dt=document.getElementById("arr_dt1");
			arrival_dt.value="";
			
		
			var row = tb.insertRow(len-2);
		
		
			var cell1 = row.insertCell(0);
			cell1.colSpan="1";
			cell1.rowSpan="2";
			cell1.innerHTML+="<input type='checkbox' mandatory='false'  name='srno"+apnd+"' tabindex='8' id='srno"+apnd+"'/>";
		
			var cell2=row.insertCell(1);
		cell2.colSpan="1";
		cell2.rowSpan="2";
		var str="<select name='arr_plc"+apnd+"' size='1' caption='drop_down' id='arr_plc"+apnd+"'  mandatory='true' onchange='setpredepart(this)'> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<cityCmb.length-1;i++)
		{
			str+="<option value='"+cityCmbVal[i]+"'>"+cityCmb[i]+"</option>";
			
		}
		str+="</select>";
		str+="<label class='mandatoryindicator'>*</label>"
	    cell2.innerHTML+=str;
	
		var cell3=row.insertCell(2);
		cell3.colSpan="1";
		cell3.rowSpan="2";
		cell3.innerHTML="<input type='text' mandatory='false' maxlength=10 size=10 name='arr_dt"+apnd+"' tabindex='8' id='arr_dt"+apnd+"' onkeypress='return checkDateNumber()' class='dateTimetag'  value=''  />";
		cell3.innerHTML+="<img src='images/CalendarImages/ico-calendar.gif'	width=20 onClick=window_open('arr_dt"+apnd+"',375,570)  alt='Click to View Calendar'	align='absmiddle'>";
		cell3.innerHTML+="<label class='mandatoryindicator'>*</label>";
		
		var cell4=row.insertCell(3);
		cell4.colSpan="1";
		cell4.rowSpan="2";
		cell4.innerHTML="<input type='text' mandatory='false' size='1'  name='arr_tm"+apnd+"' tabindex='8' id='arr_tm"+apnd+"' />";
		cell4.innerHTML+="<label class='mandatoryindicator'>*</label>";
		
		var cell5=row.insertCell(4);
		cell5.colSpan="1";
		cell5.rowSpan="2";
		str="<select name='depart_plc"+apnd+"' size='1' caption='drop_down'  id='depart_plc"+apnd+"'  mandatory='true' onchange='setArrival(this)'> <option value='321611'>-----Select -------</option>";
	  	for(var i=0;i<cityCmb.length-1;i++)
		{
			str+="<option value='"+cityCmbVal[i]+"'>"+cityCmb[i]+"</option>";
			
		}
		str+="</select>";
		str+="<label class='mandatoryindicator'>*</label>"
		cell5.innerHTML+=str;
		
		var cell6=row.insertCell(5);
		cell6.colSpan="1";
		cell6.rowSpan="2";
		cell6.innerHTML+="<input type='text' mandatory='false' maxlength=10 size=10 name='depart_dt"+apnd+"' tabindex='8' id='depart_dt"+apnd+"' onkeypress='return checkDateNumber()' class='dateTimetag' value='' />";
		cell6.innerHTML+="<img src='images/CalendarImages/ico-calendar.gif'	width=20 onClick=window_open('depart_dt"+apnd+"',375,570)  alt='Click to View Calendar'	align='absmiddle'>";
		cell6.innerHTML+="<label class='mandatoryindicator'>*</label>"
		
		var cell7=row.insertCell(6);
		cell7.colSpan="1";
		cell7.rowSpan="2";
		cell7.innerHTML="<input type='text' mandatory='false' size='1'  name='depart_tm"+apnd+"'  tabindex='8' id='depart_tm"+apnd+"'/>";
		cell7.innerHTML+="<label class='mandatoryindicator'>*</label>"
		
		var cell8=row.insertCell(7);
		cell8.colSpan="1";
		cell8.rowSpan="2";
		str="<select name='con_mod"+apnd+"' size='1' caption='drop_down' id='con_mod"+apnd+"'  mandatory='true' onchange='hide(this)'> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<conmodCmb.length-1;i++)
		{
			str+="<option value='"+conmodCmbVal[i]+"'>"+conmodCmb[i]+"</option>";
			
		}
	    str+="</select>";
	    str+="<label class='mandatoryindicator'>*</label>"
	   	cell8.innerHTML+=str;
	// Start Hide columns	
		var cell9=row.insertCell(8);
		cell9.colSpan="1";
		cell9.rowSpan="1";
		cell9.id="own"+apnd;
		cell9.style.display="none";
		cell9.innerHTML="<center><label>Owned By</center></label>";
		
		var cell10=row.insertCell(9);
		cell10.colSpan="1";
		cell10.rowSpan="1";
		cell10.id="veh"+apnd;
		cell10.style.display="none";
		cell10.innerHTML="<center><label>Vehicle Type</center></label>";
				
		var cell11=row.insertCell(10);
		cell11.colSpan="1";
		cell11.rowSpan="2";
		cell11.id="bus"+apnd;
		cell11.style.display="none";
		cell11.innerHTML="Business <input type='radio' id='airmod"+apnd+"'  title='Business'  onclick='setAirmodeval(this)' value='' name='airmod"+apnd+"'>";
		
		var cell12=row.insertCell(11);
		cell12.colSpan="1";
		cell12.rowSpan="2";
		cell12.id="eco"+apnd;
		cell12.style.display="none";
		cell12.innerHTML="Ecomony <input type='radio' id='airmod"+apnd+"' title='Economic' onclick='setAirmodeval(this)' value='' name='airmod"+apnd+"'>";
		

		var cell13=row.insertCell(12);
		cell13.colSpan="1";
		cell13.rowSpan="1";
		cell13.id="trn"+apnd;
		cell13.style.display="none";
		cell13.innerHTML="<center><label>Train</center></label>";
		
		var cell14=row.insertCell(13);
		cell14.colSpan="1";
		cell14.rowSpan="1";
		cell14.id="class"+apnd;
		cell14.style.display="none";
		cell14.innerHTML="<center><label>Class</center></label>";
		
	//End hide columns
	
		var cell15=row.insertCell(14);
		cell15.colSpan="1";
		cell15.rowSpan="1";
		cell15.id="fir_dm"+apnd;
		cell15.innerHTML="";
		
		var cell16=row.insertCell(15);
		cell16.colSpan="1";
		cell16.rowSpan="1";
		cell16.id="sec_dm"+apnd;
		cell16.innerHTML="";
		
		var cell17=row.insertCell(16);
		cell17.colSpan="1";
		cell17.rowSpan="2";
		cell17.id="dislab"+apnd;
		cell17.innerHTML="<center><label>0</label></center>";
		
		var cell18=row.insertCell(17);
		cell18.colSpan="1";
		cell18.rowSpan="2";
		cell18.style.display="none";
		cell18.id="discol"+apnd;
		cell18.innerHTML="<input type='text' default='0'  size='3' name='dis"+apnd+"' id='dis"+apnd+"'  tabindex='8' />";

		
		var cell19=row.insertCell(18);
		cell19.colSpan="1";
		cell19.rowSpan="2";
		str="<select name='purpose"+apnd+"' size='1' id='purpose"+apnd+"' caption='drop_down'   mandatory='true' onchange=''> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<purCmb.length-1;i++)
		{
			str+="<option value='"+purCmbVal[i]+"'>"+purCmb[i]+"</option>";
		
		}
	    str+="</select>";
	    str+="<label class='mandatoryindicator'>*</label>"
	    cell19.innerHTML+=str;
	
		var cell20=row.insertCell(19);
		cell20.colSpan="1";
		cell20.rowSpan="2";
		str="<select name='accom"+apnd+"' size='1' caption='drop_down' id='accom"+apnd+"'  mandatory='true' onchange=''> <option value='321611'>--------Select -----------</option>";
	    for(var i=0;i<accomCmb.length-1;i++)
		{
			str+="<option value='"+accomCmbVal[i]+"'>"+accomCmb[i]+"</option>";
		
		}
	    str+="</select>";
	    str+="<label class='mandatoryindicator'>*</label>"
        cell20.innerHTML+=str;
		
		var cell21=row.insertCell(20);
		cell21.colSpan="1";
		cell21.rowSpan="2";
		cell21.innerHTML="<input type='text'  size='2' default='0.00' maxlength='6' name='cost"+apnd+"' id='cost"+apnd+"'  tabindex='8' />";
		 
		var cell22=row.insertCell(21);
		cell22.colSpan="1";
		cell22.rowSpan="2";
		cell22.innerHTML="<textarea rows='3' name='remark"+apnd+"' id='remark"+apnd+"' cols='21'/>";
		
		
		var newlen=tb.rows.length;
		row = tb.insertRow(newlen-2)
		cell1 = row.insertCell(0);
		cell1.colSpan="1";
		cell1.rowSpan="1";
		cell1.style.display="";
		cell1.id="trncol"+apnd;
		str="<select name='sub_train"+apnd+"' id='sub_train"+apnd+"' size='1' caption='drop_down'   mandatory='true' onchange='' disabled='true'> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<trainCmb.length-1;i++)
		{
			str+="<option value='"+trainCmbVal[i]+"'>"+trainCmb[i]+"</option>";
			
		}
	    str+="</select>";
    	str+="<select name='sub_own"+apnd+"' id='sub_own"+apnd+"' size='1' caption='drop_down' style='display:none'  mandatory='true' onchange='enabletype(this)'> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<own_byCmb.length-1;i++)
		{
			str+="<option value='"+own_byCmbVal[i]+"'>"+own_byCmb[i]+"</option>";
			
		}
	    str+="</select>";
	    str+="<label class='mandatoryindicator'>*</label>"
		cell1.innerHTML=str;
		
		
		cell2 = row.insertCell(1);
		cell2.colSpan="1";
		cell2.rowSpan="1";
		cell2.id="clscol"+apnd;
		cell2.style.display="";
		str="<select name='sub_class"+apnd+"' id='sub_class"+apnd+"' size='1' caption='drop_down'   mandatory='true' onchange='' disabled='true'> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<classCmb.length-1;i++)
		{
			str+="<option value='"+classCmbVal[i]+"'>"+classCmb[i]+"</option>";
			
		}
	    str+="</select>";
		str+="<select name='sub_veh"+apnd+"' id='sub_veh"+apnd+"' size='1' caption='drop_down' style='display:none'  mandatory='true' onchange='' disabled='true'> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<veh_typeCmb.length-1;i++)
		{
			str+="<option value='"+veh_typeCmbVal[i]+"'>"+veh_typeCmb[i]+"</option>";
			
		}
	    str+="</select>";
	    str+="<label class='mandatoryindicator'>*</label>"
		cell2.innerHTML=str;
		
		
		
		
		
		newlen=tb.rows.length;
		newlen=(newlen-2)/2;
		
		
		if(newlen==3)
		{
			var depart_plc_nm="depart_plc"+(newlen-3);
			var depart_dt_nm="depart_dt"+(newlen-3);
			var arr_dt_nm="arr_dt"+(newlen-3);
			
			var depart_plc=document.getElementById(depart_plc_nm);
			var depart_dt=document.getElementById(depart_dt_nm);
			var arr_dt=document.getElementById(arr_dt_nm);
			
			var arr_plc_nm="arr_plc"+apnd;
			
			var arr_plc=document.getElementById(arr_plc_nm);
			arr_plc.value=depart_plc.value;
			
			return;		
		}
		var depart_plc_nm="depart_plc"+(newlen-2);
		var depart_plc=document.getElementById(depart_plc_nm);
		var arr_plc_nm="arr_plc"+apnd;
		var arr_plc=document.getElementById(arr_plc_nm);
		arr_plc.value=depart_plc.value;
		
		
		
		}
		function resettripdtls()
		{
			
			var tb = document.getElementById('detailsTable');
			var len = tb.rows.length;
			var row=(len*1-2)/2;
			var trippurpose=document.getElementById("purpose");
			trippurpose.value="";
			var ref_file_no=document.getElementById("ref_file_no");
			ref_file_no.value="";
			for(var i=0;i<row;i++)
			{
			
					//Hide column
					var own_nm="own"+i;
					var bus_nm="bus"+i;
					var eco_nm="eco"+i;
					var veh_nm="veh"+i;
					var trn_nm="trn"+i;
					var class_nm="class"+i;
					var trncol_nm="trncol"+i;
					var clscol_nm="clscol"+i;
					var fir_nm="fir_dm"+i;
					var sec_nm="sec_dm"+i;
					var dislab_nm="dislab"+i;
					var discol_nm="discol"+i;
					var airmod_nm="airmod"+i;
					
					var own=document.getElementById(own_nm);
					var bus=document.getElementById(bus_nm);
					var eco=document.getElementById(eco_nm);
					var veh=document.getElementById(veh_nm);
					var trn=document.getElementById(trn_nm);
					var cls = document.getElementById(class_nm);
					var trncol=document.getElementById(trncol_nm);
					var clscol=document.getElementById(clscol_nm);
					var fir=document.getElementById(fir_nm);
					var sec=document.getElementById(sec_nm);
					var dislab=document.getElementById(dislab_nm);
					var discol=document.getElementById(discol_nm);
					var airmod=document.getElementById(airmod_nm);
					
					own.style.display="none";
					bus.style.display="none";
					eco.style.display="none";
					veh.style.display="none";
						
					trn.style.display="none";
					cls.style.display="none";
					trncol.style.display="";
					clscol.style.display="";
					fir.style.display="";
					sec.style.display="";
					discol.style.display="none";
					dislab.style.display="";
								
					var arr_plc_nm="arr_plc"+i;
					var arr_dt_nm="arr_dt"+i;
					var arr_tm_nm="arr_tm"+i;
					
					var depart_plc_nm="depart_plc"+i;
					var depart_dt_nm="depart_dt"+i;
					var depart_tm_nm="depart_tm"+i;
					
					var con_mod_nm="con_mod"+i;
					
					var sub_train_nm="sub_train"+i;
					var sub_class_nm="sub_class"+i;
					var sub_own_nm="sub_own"+i;
					var sub_veh_nm="sub_veh"+i;
					
					var purpose_nm="purpose"+i;
					
					var accom_nm="accom"+i;
					var cost_nm="cost"+i;
					var remark_nm="remark"+i;
					
					var arr_plc=document.getElementById(arr_plc_nm);
					var arr_dt=document.getElementById(arr_dt_nm);
					var arr_tm=document.getElementById(arr_tm_nm);
					
					var depart_plc=document.getElementById(depart_plc_nm);
					var depart_dt=document.getElementById(depart_dt_nm);
					var depart_tm=document.getElementById(depart_tm_nm);
				
					var con_mod=document.getElementById(con_mod_nm);
					
					var sub_train=document.getElementById(sub_train_nm);
					var sub_class=document.getElementById(sub_class_nm);
					
					var purpose=document.getElementById(purpose_nm);
					
					var accom=document.getElementById(accom_nm);
					var cost=document.getElementById(cost_nm);
					var remark=document.getElementById(remark_nm);
				
					var sub_own=document.getElementById(sub_own_nm);
					var sub_veh=document.getElementById(sub_veh_nm);
					
					sub_own.selectedIndex=0;
					sub_veh.selectedIndex=0;
					sub_own.style.display="none";
					sub_veh.style.display="none";
					sub_train.style.display="";
					sub_class.style.display="";
					
					arr_plc.selectedIndex=0;
					arr_tm.value="";	
					arr_dt.value="";
					
					
					
					depart_plc.selectedIndex=0;		
					depart_tm.value="";
					depart_dt.value="";
				
					con_mod.selectedIndex=0;
					
					sub_train.selectedIndex=0;
					sub_class.selectedIndex=0;
					
					purpose.selectedIndex=0;
					
					accom.selectedIndex=0;
					
					cost.value="0.00";
					
					remark.value="";
					
					airmod.value="321611"
	
			}
			
		}
		
		function deleteallrows()
		{
			
			var tb=document.getElementById('detailsTable');
			var len=tb.rows.length;
			var row=(len-2)/2;
			
			for(var i=2;i<row;i++)
			{
				var index=2*2;
				tb.deleteRow(index);
				tb.deleteRow(index);
				
			}
			 
			
			
		}
		function editRecordForDraft(rowId,rowNum)
		{
				

			updateRow=null;
			
			resettripdtls();
			deleteallrows(); 
			var detailsTable=document.getElementById('detailsTable');
			detailsTable.style.display="";
			var maintb=document.getElementById('maintb');
			maintb.style.display="";
		
		   	var attachtb=document.getElementById('attachtb');
		   	attachtb.style.display="";
			sendAjaxRequestForEditAttachment(rowId, 'populateForm','TravelAttachment',rowNum);
		}
		function populateForm()
		{
	
		 if (xmlHttp.readyState == 4) 
		 { 				  
			  	var decXML = xmlHttp.responseText;
			  	
		
				var xmlDOM=populateAttachment(decXML,'frm1');
				
				var len=getChildNodeLengnthOfGivenSet(xmlDOM, 'hrTravelJourneyDtlses');	
		
				for(var i=2;i<len;i++)
				{
					addrow();
				}
				
				for(var j=0;j<len;j++)
				{
						
						var parentNode='hrTravelJourneyDtlses['+j+']';
						var i=getXPathValueFromDOM(xmlDOM, parentNode+'/tourRowId');
					//	alert(i);
					
						var arr_plc_nm="arr_plc"+j;
						var arr_dt_nm="arr_dt"+j;
						
						var arr_tm_nm="arr_tm"+j;
						var depart_plc_nm="depart_plc"+j;
						var depart_dt_nm="depart_dt"+j;
						var depart_tm_nm="depart_tm"+j;
						var con_mod_nm="con_mod"+j;
						var dis_nm="dis"+j;
						var purpose_nm="purpose"+j;
						var accom_nm="accom"+j;
						var cost_nm="cost"+j;
						var remark_nm="remark"+j;
						var sub_train_nm="sub_train"+j;
						var sub_class_nm="sub_class"+j;
						var sub_own_nm="sub_own"+j;
						var sub_veh_nm="sub_veh"+j;
						var airmod_nm="airmod"+j;
						
						
						var arr_plc=document.getElementById(arr_plc_nm);
						var arr_dt=document.getElementById(arr_dt_nm);
						var arr_tm=document.getElementById(arr_tm_nm);
						var depart_plc=document.getElementById(depart_plc_nm);
						var depart_dt=document.getElementById(depart_dt_nm);
						var depart_tm=document.getElementById(depart_tm_nm);
						var con_mod=document.getElementById(con_mod_nm);
						var dis=document.getElementById(dis_nm);
						var purpose=document.getElementById(purpose_nm);
						var accom=document.getElementById(accom_nm);
						var cost=document.getElementById(cost_nm);
						var remark=document.getElementById(remark_nm);
						var sub_train=document.getElementById(sub_train_nm);
						var sub_class=document.getElementById(sub_class_nm);
						var sub_own=document.getElementById(sub_own_nm);
						var sub_veh=document.getElementById(sub_veh_nm);
						var airmod=document.getElementsByName(airmod_nm);
					
						
						arr_plc.value=getXPathValueFromDOM(xmlDOM, parentNode+'/travelFromPlace/cityId');
						arr_plc.selected="true";
						
						
						depart_plc.value=getXPathValueFromDOM(xmlDOM, parentNode+'/travelToPlace/cityId');
						depart_plc.selected="true";
						
					
						var mod=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTravelMode/lookupId')
						con_mod.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTravelMode/lookupId');
						con_mod.selected="true";
						
						//Hide Coluem According To Selected TravelMode
							var own_nm="own"+j;
							var bus_nm="bus"+j;
							var eco_nm="eco"+j;
							var veh_nm="veh"+j;
							var trn_nm="trn"+j;
							var class_nm="class"+j;
							var trncol_nm="trncol"+j;
							var clscol_nm="clscol"+j;
							var fir_nm="fir_dm"+j;
							var sec_nm="sec_dm"+j;
							var dislab_nm="dislab"+j;
							var discol_nm="discol"+j;
					
							
							var own=document.getElementById(own_nm);
							var bus=document.getElementById(bus_nm);
							var eco=document.getElementById(eco_nm);
							var veh=document.getElementById(veh_nm);
							var trn=document.getElementById(trn_nm);
							var cls = document.getElementById(class_nm);
							var trncol=document.getElementById(trncol_nm);
							var clscol=document.getElementById(clscol_nm);
							var fir=document.getElementById(fir_nm);
							var sec=document.getElementById(sec_nm);
							var dislab=document.getElementById(dislab_nm);
							var discol=document.getElementById(discol_nm);
				
							
							if(mod=='321611')
							{
								own.style.display="none";
								bus.style.display="none";
								eco.style.display="none";
								veh.style.display="none";
								trn.style.display="none";
								cls.style.display="none";
								trncol.style.display="";
								clscol.style.display="";
								fir.style.display="";
								sec.style.display="";
								discol.style.display="none";
								dislab.style.display="";
								sub_train.style.display="";
								sub_train.disabled="true";
								sub_train.value="321611";
								sub_class.style.display="";
								sub_class.disabled="true";
								sub_class.value="321611";
								sub_own.style.display="none";
								sub_veh.style.display="none";
							}else if(mod=="321594" ||mod=="321598")
							{
								
								own.style.display="none";
								bus.style.display="";
								var type=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByAirType/lookupId');
								if(type=="350501")
								{
									airmod[0].checked='true';
								}else
								{
									airmod[1].checked='true';
								}
								
								eco.style.display="";
								veh.style.display="none";
								trn.style.display="none";
								cls.style.display="none";
								trncol.style.display="none";
								clscol.style.display="none";
								fir.style.display="none";
								sec.style.display="none";
								discol.style.display="none";
								dislab.style.display="";
							}else if(mod=='321595'||mod=="321599")
							{
								sub_train.disabled="true";
								sub_train.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTrainName/lookupId');
								sub_train.selected="true";
								
								sub_class.disabled="true";
								sub_class.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTravelClass/lookupId');
								sub_class.selected="true";
								
								own.style.display="none";
								bus.style.display="none";
								eco.style.display="none";
								veh.style.display="none";
								trn.style.display="";
								cls.style.display="";
								trncol.style.display="";
								clscol.style.display="";
								fir.style.display="none";
								sec.style.display="none";
								discol.style.display="none";
								dislab.style.display="";
								sub_train.style.display="";
								sub_class.style.display="";
								sub_own.style.display="none";
								sub_veh.style.display="none";
							}else if (mod=='321596'||mod=="321600")
							{
								
								own.style.display="";
								bus.style.display="none";
								eco.style.display="none";
								veh.style.display="";
								trn.style.display="none";
								cls.style.display="none";
								trncol.style.display="";
								clscol.style.display="";
								fir.style.display="none";
								sec.style.display="none";
								discol.style.display="";
								dislab.style.display="none";
								sub_train.style.display="none";
								sub_class.style.display="none";
								sub_own.style.display="";
								sub_own.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByGovtOrGovtpaid/lookupId')
								sub_own.selected="true";
								
								sub_veh.style.display="";
								sub_veh.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByVehicleType/lookupId')
								sub_veh.selected="true";
							}
						
						if(getXPathValueFromDOM(xmlDOM, parentNode+'/remark')=="$")
						{
							remark.value="";
						}else
						{
							remark.value=getXPathValueFromDOM(xmlDOM, parentNode+'/remark');
	
						}
		
						if(getXPathValueFromDOM(xmlDOM, parentNode+'/costIncurred')==null)
						{
							cost.value="0.0";
				
						}else
						{
							cost.value=getXPathValueFromDOM(xmlDOM, parentNode+'/costIncurred');
						}
						//cost.selected="true";
						
						purpose.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByPurposeOfStay/lookupId');
						purpose.selected="true";
						
						var tmp_dt=new Date();
						tmp_dt=getXPathValueFromDOM(xmlDOM, parentNode+'/travelFromDate');
			
						
						var arrayDate=new Array();
						arrayDate=getDateAndTimeFromDateObj(tmp_dt);
						
						if(arrayDate[0]!="11/11/1111")
						{
							arr_dt.value=arrayDate[0];
						}
						if(arrayDate[1]!="00:00")
						{
							arr_tm.value=arrayDate[1];
						}
						
						
						tmp_dt=getXPathValueFromDOM(xmlDOM, parentNode+'/travelToDate');
						arrayDate=getDateAndTimeFromDateObj(tmp_dt);
						
						if(arrayDate[0]!="11/11/1111")
						{
							depart_dt.value=arrayDate[0];
						}
						if(arrayDate[1]!="00:00")
						{
							depart_tm.value=arrayDate[1];						
						}
						
				
						
						
						accom.value=getXPathValueFromDOM(xmlDOM, parentNode+'/hrTravelAcomDtlses[0]/accomType');
						accom.selected="true";
				}
				
			
				document.getElementById('purpose').value=getXPathValueFromDOM(xmlDOM, 'purpose');
				document.getElementById("ref_file_no").value=getXPathValueFromDOM(xmlDOM, 'refFileNo');
				
				
				
		

			}
			
		}	
		
  
</script>





<style type="text/css">
  .prog-border {
  height: 8px;
  width: 200px;
  background: white;
  border: 1px solid #000;
  border-color: black;
  margin: 1;
  padding: 0;
  }

  .prog-bar {
  height: 6px;
  margin: 2px;
  padding: 0px;
  background: #3299CC;
  }
</style>

<fmt:setBundle basename="resources.ess.travel.caption" var="caption"
	scope="request" />


<hdiits:form name="frm1" validate="true" method="POST" action=""
	encType="multipart/form-data">
	<br>
	<br>
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <hdiits:caption
			captionid="HRMS.ReqDtls" bundle="${caption}"></hdiits:caption>
		</b></a></li>
	</ul>
	</div>
	<br>
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	<div style="width: 100%;height: 100%;overflow:scroll"><br>
	<!--  Starting main Table -->
	<table id="maintb" style="display: none">
		<tr>
			<td><b><fmt:message key="HRMS.Purpose" bundle="${caption}" /></td>
			<td><hdiits:textarea mandatory="true" rows="4" cols="35"
				name="purpose" tabindex="1" id="purpose" validation="txt.isrequired"
				caption="purpose" maxlength="99" readonly="true" /></td>
			<td><b><fmt:message key="HRMS.Ref_file_no" bundle="${caption}" /></td>
			<td><hdiits:text mandatory="false" name="ref_file_no"
				tabindex="2" id="ref_file_no" 
				caption="ref. file no." readonly="true" /></td>
			<td width="25%"></td>
		</tr>
		<tr  bgcolor="#386CB7">
			<td class="fieldLabel" width="15%" align="left"><font
				color="#0000"> <strong><u><fmt:message
				key="HRMS.Journey_Details" bundle="${caption}" /></u></strong> </font></td>
		</tr>
	</table>
	<!--  End main Table --> <!-- Start : Detailed table -->
	<table id='detailsTable' border="1" cellpadding="0" cellspacing="0"	style="display: none">
		<!-- Start: Heading -->
		<thead title="Heading">
			<br>
			<tr height="25%" class="datatableheader">
				<td rowspan="2" colspan="1" width="1%"></td>
				<td rowspan="1" colspan="3" height="25%" width="25%" align="center"><fmt:message
					key="HRMS.Departure" bundle="${caption}" /></td>
				<td rowspan="1" colspan="3" height="25%" width="25%" align="center"><fmt:message
					key="HRMS.Arrival" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><fmt:message
					key="HRMS.Conveyance_Mode" bundle="${caption}" /></td>
				<td rowspan="2" colspan="2" height="25%" align="center"><fmt:message
					key="HRMS.Sub_Items" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="1%"><fmt:message
					key="HRMS.Distance" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><fmt:message
					key="HRMS.Purose_Of_Stay" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><fmt:message
					key="HRMS.Accomodation" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="2%"><fmt:message
					key="HRMS.Total_Cost" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="10%"><fmt:message
					key="HRMS.Remark" bundle="${caption}" /></td>
			</tr>
			<tr class="datatableheader">
				<td colspan="1" width="10%"><fmt:message key="HRMS.Place" bundle="${caption}" /></td>
				<td colspan="1" width="5%"><fmt:message
					key="HRMS.Departure_Date" bundle="${caption}"  /></td>
				<td colspan="1" width="1%"><fmt:message key="HRMS.Time" bundle="${caption}" /></td>
				<td colspan="1" width="10%"><fmt:message key="HRMS.Place" bundle="${caption}" /></td>
				<td colspan="1" width="5%"><fmt:message key="HRMS.Arrival_Date" bundle="${caption}" /></td>
				<td colspan="1" width="1%"><fmt:message key="HRMS.Time" bundle="${caption}" /></td>
			</tr>
		</thead>
		<!-- End: Heading -->
		<!-- Start: First Row -->
		<tbody>
			<tr height="25%">
				<td rowspan="2" colspan="1" width="1%"><hdiits:checkbox
					name="srno0" id="srno0" value="chk" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="arr_plc0" size="1" caption="arrival_place"
					validation="sel.isrequired" id="arr_plc0" mandatory="true"
					onchange="setDeparture(this)" tabindex="2" readonly="true">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="leavetypes" items="${cityList}">
						<hdiits:option value="${leavetypes.cityId}">
							${leavetypes.cityName}
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="arr_dt0" caption="arr_dt0" mandatory="true" disabled="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					name="arr_tm0" mandatory="true" id="arr_tm0" size="1" maxlength="5"
					 readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="depart_plc0" size="1" caption="departure_place"
					validation="sel.isrequired" id="depart_plc0" mandatory="true"
					 readonly="true">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="leavetypes" items="${cityList}">
						<hdiits:option value="${leavetypes.cityId}">
							${leavetypes.cityName}
						</hdiits:option>
					</c:forEach>
				</hdiits:select>
				</td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="depart_dt0" caption="depart_dt0" 
					mandatory="true" disabled="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					size="1" name="depart_tm0" id="depart_tm0"
					 mandatory="true" maxlength="5" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="con_mod0" id="con_mod0" caption="con_mode0" mandatory="true"
					validation="sel.isrequired" onchange="hide(this)" readonly="true"> 
					<hdiits:option value="321611">------Select------</hdiits:option>
					<c:forEach var="ConvyanceModevar" items="${ConvyanceModeList}">
						<hdiits:option value="${ConvyanceModevar.lookupId}">${ConvyanceModevar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display:none" id="bus0">Business <hdiits:radio
					name="airmod0" id="airmod0" value="321611" title="Business"
					onclick="setAirmodeval(this)" /></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="eco0">Economic <hdiits:radio
					name="airmod0" id="airmod0" value="321611" title="Economic"
					onclick="setAirmodeval(this)" /></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="own0"><label>Own by</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="veh0"><label>Vehicle Type</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="trn0"
					style="display: none"><label style="border-spacing:em;">Train</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="class0"
					style="display: none"><label>Class</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="fir_dm0"><label></label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="sec_dm0"><label></label></td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="dislab0">
				<center><label>0</label></center>
				</td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="discol0"
					style="display: none;"><hdiits:text size="3" name="dis0"
					default="0" id="dis0" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="purpose0" id="purpose0" caption="purposeofstay0"
					mandatory="true" validation="sel.isrequired" readonly="true">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="Purpose_of_StayListvar"
						items="${Purpose_of_StayList}">
						<hdiits:option value="${Purpose_of_StayListvar.lookupId}">
						${Purpose_of_StayListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="accom0" id="accom0" caption="accomodation0" mandatory="true"
					validation="sel.isrequired" sort="false" readonly="true">
					<hdiits:option value="321611">---------Select---------</hdiits:option>
					<c:forEach var="AccomodationListvar" items="${AccomodationList}">
						<hdiits:option value="${AccomodationListvar.lookupId}">
						${AccomodationListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="2%"><hdiits:text
					size="2" name="cost0" id="cost0" default="0.00" maxlength="6" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="10%"><hdiits:textarea
					name="remark0" id="remark0" rows="3" cols="28" readonly="true"></hdiits:textarea></td>
			</tr>
			<tr height="25%">
				<td rowspan="1" colspan="1" height="25%" align="center" id="trncol0">
				<hdiits:select name="sub_train0" id="sub_train0" caption="train"
					mandatory="false" validation="sel.isrequired" readonly="true" 
					sort="false">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="TrainListvar" items="${TrainList}">
						<hdiits:option value="${TrainListvar.lookupId}">
						${TrainListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> <hdiits:select name="sub_own0" id="sub_onw0" caption="train"
					mandatory="false" validation="sel.isrequired" style="display: none" 
					onchange="enabletype(this)" readonly="true">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="owned_byvar" items="${owned_byList}">
						<hdiits:option value="${owned_byvar.lookupId}">
							${owned_byvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="clscol0">
				<hdiits:select name="sub_class0" id="sub_class0" caption="class"
					mandatory="false" validation="sel.isrequired" readonly="true"
					sort="false" >
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="ClassListvar" items="${ClassList}">
						<hdiits:option value="${ClassListvar.lookupId}"> 
						${ClassListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> <hdiits:select name="sub_veh0" id="sub_veh0" caption="class"
					mandatory="false" validation="sel.isrequired" style="display: none"
					readonly="true">
					<hdiits:option value='321611'> ------Select -----</hdiits:option>
					<c:forEach var="veh_typevar" items="${veh_typeList}">
						<hdiits:option value="${veh_typevar.lookupId}">
							${veh_typevar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>

			</tr>
		</tbody>
		<!-- End: First Row -->
		<!-- Start: Second Row -->
		<tbody>
			<tr height="25%">
				<td rowspan="2" colspan="1" width="1%"><hdiits:checkbox
					name="srno1" id="srno1" value="chk" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="arr_plc1" size="1" caption="arrival_place1"
					validation="sel.isrequired" id="arr_plc1" mandatory="true"
					onchange="setpredepart(this)" readonly="true">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="leavetypes" items="${cityList}">
						<hdiits:option value="${leavetypes.cityId}">
							${leavetypes.cityName}
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="arr_dt1" caption="arr_dt1" 
					mandatory="true" disabled="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					size="1" name="arr_tm1" mandatory="true" maxlength="5" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="depart_plc1" size="1" caption="depart_place1"
					validation="sel.isrequired" id="depart_plc1" mandatory="true"
					 readonly="true">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="leavetypes" items="${cityList}">
						<hdiits:option value="${leavetypes.cityId}">
							${leavetypes.cityName}
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="depart_dt1" caption="depart_dt1" 
					mandatory="true" disabled="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					size="1" name="depart_tm1" 
					mandatory="true" maxlength="5" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="con_mod1" caption="con_mode1" mandatory="true"
					validation="sel.isrequired" onchange="hide(this)" id="con_mod1" readonly="true">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="ConvyanceModevar" items="${ConvyanceModeList}">
						<hdiits:option value="${ConvyanceModevar.lookupId}">${ConvyanceModevar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="bus1">Business <hdiits:radio
					name="airmod1" id="airmod1" title="Business" value="321611"
					onclick="setAirmodeval(this)" /></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="eco1">Economic <hdiits:radio
					name="airmod1" id="airmod1" title="Economic" value="321611"
					onclick="setAirmodeval(this)" /></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="own1"><label>Own by</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="veh1"><label>Vehicle Type</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="trn1"
					style="display: none"><label>Train</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="class1"
					style="display: none"><label>Class</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="fir_dm1"><label></label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="sec_dm1"><label></label></td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="dislab1">
				<center><label>0</label></center>
				</td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="discol1"
					style="display: none;"><hdiits:text size="3" name="dis1"
					id="dis1" default="0" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="purpose1" caption="purposeofstay1" mandatory="true"
					validation="sel.isrequired" readonly="true" >
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="Purpose_of_StayListvar"
						items="${Purpose_of_StayList}">
						<hdiits:option value="${Purpose_of_StayListvar.lookupId}">
							${Purpose_of_StayListvar.lookupDesc}
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="accom1" caption="accomodation1" mandatory="true"
					validation="sel.isrequired" readonly="true" sort="false">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="AccomodationListvar" items="${AccomodationList}">
						<hdiits:option value="${AccomodationListvar.lookupId}">
						${AccomodationListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="2%"><hdiits:text
					size="2" name="cost1" default="0.00" maxlength="6" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="10%"><hdiits:textarea
					name="remark1" readonly="true" rows="3" cols="28"></hdiits:textarea></td>
			</tr>
			<tr height="25%">
				<td rowspan="1" colspan="1" height="25%" align="center" id="trncol1">
				<hdiits:select name="sub_train1" id="sub_train1" caption="train1"
					readonly="true"
					sort="false">
					<hdiits:option value='321611'>---------Select--------</hdiits:option>
					<c:forEach var="TrainListvar" items="${TrainList}">
						<hdiits:option value="${TrainListvar.lookupId}">
						${TrainListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> <hdiits:select name="sub_own1" id="sub_own1" caption="train1"
					 style="display:none"
					onchange="enabletype(this)" readonly="true">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="owned_byvar" items="${owned_byList}">
						<hdiits:option value="${owned_byvar.lookupId}">
							${owned_byvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="clscol1">
				<hdiits:select name="sub_class1" id="sub_class1" caption="class1 "
					 readonly="true" 
					sort="false">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="ClassListvar" items="${ClassList}">
						<hdiits:option value="${ClassListvar.lookupId}"> 
						${ClassListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> <hdiits:select name="sub_veh1" id="sub_veh1" caption="class1 "
					 style="display:none"
					readonly="true">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="veh_typevar" items="${veh_typeList}">
						<hdiits:option value="${veh_typevar.lookupId}">
							${veh_typevar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>


			</tr>
		</tbody>
		<!-- End: Second Row -->

	</table>
	<!-- End : Detailed table --> <br>
	<br>
		<center>
	<table border="1" style="display: none" id="attachtb">
		<tr>
			<td><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="TravelAttachment" />
				<jsp:param name="formName" value="frm1" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />
				<jsp:param name="multiple" value="Y" />
				<jsp:param name="rowNumber" value="1" />
			</jsp:include></td>
		</tr>
	</table>
	</center>
	<br>
	<br>
	<center>
	<table id='tripdtls' border="1" cellpadding="0" width="68%"
		cellspacing="0" BGCOLOR="WHITE"
		style="background: #eeeeee;padding: 2px">
		<tr class="datatableheader">
			<td style="width: 45%">Trip Name</td>
			<td style="width: 15%">Status</td>
			<td style="width: 15%">Actions</td>
		</tr>
	</table>
	</center>

	<br>
	<br>

<hdiits:hidden name="Trip_Name" id="Trip_Name" />	
<hdiits:hidden name="RequestId" id="RequestId"/>
<hdiits:hidden name="tourId" id="tourId"/>
<hdiits:hidden name="approveId" id="approveId"/>
<hdiits:hidden name="rejectedId" id="rejectedId"/>
<hdiits:hidden name="flag" id="flag"/>

<script>
	var RequestId=document.getElementById('RequestId');
	RequestId.value="${ReqId}";
	var flag=document.getElementById('flag');
	flag.value="${flag}";
</script>	
	<!-- Hidden Fields -->
	
	</div>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
<c:forEach var="arrayList" items="ajaxkeyList">
<script>
		var res="${ajaxkeyList}";

		var resultList=res.substring(1,res.length-1);

</script>
</c:forEach>
<script>
		
		//	alert(res);
			
			var respArray=new Array();
			respArray=resultList.split("##");

			
			for(var i=0;i<respArray.length;i++)
			{
		
				var xmlPath=respArray[i].split("$$");
				var Trip_Name=document.getElementById("Trip_Name");
				var IdArray=new Array();
				IdArray=xmlPath[1].split(",");
				var tmpArray = new Array();
				tmpArray=IdArray[0].split("@@");
				//Start
				var status=new Array();
				status=tmpArray[0].split("^^");
				Trip_Name.value=status[0];
				
				var s="";
				if(status[1]=="P")
				{
					s="Pending";
				}else if(status[1]=="Y")
				{
					s="Approve";
				}else if(status[1]=="R")
				{
					s="Rejected";
				}else
				{
					s="Not Approved";
				}
				
				
				//End 
				
				if(IdArray[1]!="1")
				{
		
					var rowNum = addDBDataInTableAttachmentforTravel('tripdtls','encXML',new Array(status[0],s),xmlPath[0],IdArray[1],'','','editRecordForDraft',tmpArray[1],status[1]);
				}else
				{
					var rowNum = addDBDataInTableAttachmentforTravel('tripdtls','encXML',new Array(status[0],s),xmlPath[0],'','','','editRecordForDraft',tmpArray[1],status[1]);
					//var rowNum=addDBDataInTable('tripdtls','encXML',new Array(IdArray[0]),xmlPath[0],'','','editRecordForDraft');
				}
				
			}
		//	alert("Stop!!!!!!!!");
			function addDBDataInTableAttachmentforTravel(tableId, hiddenField, displayFieldArray, xmlFilePath, attachmentIds,
			editMethodName, deleteMethodName, viewMethodName,tourId,s)
			{
			
				if(deleteMethodName == undefined) {
					deleteMethodName = '';
				}
				if(editMethodName == undefined) {
					editMethodName = '';
				}
				if(viewMethodName == undefined) {
					viewMethodName = '';
				}
				
		//		alert('attachmentIds : ' + attachmentIds);
				document.getElementById(tableId).style.display='';
				var trow=document.getElementById(tableId).insertRow();
		
				trow.id = 'row' + hiddenField + counter;
				trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
				trow.cells[0].style.display = 'none';		
				
				var len = displayFieldArray.length;
				for(var i = 0; i < len; i++) 
				{
					trow.insertCell(i+1).innerHTML = displayFieldArray[i];	
				}	
				if(document.getElementById("Incremented_rowNumber")==null)
				{
					var table = document.getElementById(tableId);
					table.rows[0].insertCell(len+1).innerHTML = "<INPUT type='hidden' name='Incremented_rowNumber' id='Incremented_rowNumber' value='1'/>";
					table.rows[0].cells[len+1].style.display = 'none';
				}
				var rowNumberForRow = document.getElementById("Incremented_rowNumber").value;
				var editCap = "";
				var delCap  = "";
				var viewCap = "";
				try 
				{
					editCap = cmnLblArray[0];
					delCap  = cmnLblArray[1];
					viewCap = cmnLblArray[4];
				}
				catch (e)
				{
		//			alert ("EXCEPTION THROWN ");
					editCap = "Edit";
					delCap  = "Delete";
					viewCap = "View";
				}
				
				
				if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
					trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow +
														 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
														 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+delCap+"</a>";			
				
				else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
					trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
														 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id  + "','" + rowNumberForRow + "')>"+delCap+"</a>";										
														 
				else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
					trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow +
														 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "','" + rowNumberForRow + "')>"+editCap+"</a>";
														 
				else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
					trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow + 
														 "')>View</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + tourId + "','" + rowNumberForRow + "')>"+delCap+"</a>";			
				
				else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
					trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
				
				else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
					trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+editCap+"</a>";		
					
				else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
					trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "','" + rowNumberForRow + "')>View</a>";
			
				trow.insertCell(len + 2).innerHTML = "<INPUT type='hidden' name='attachment" + hiddenField + "' id='attachment" + hiddenField + counter + "' value='" + attachmentIds + "' />";		
				trow.cells[len + 2].style.display = 'none';					
			
				trow.insertCell(len + 3).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "_rowNumber' value='" + rowNumberForRow + "'/>";
				trow.cells[len + 3].style.display = 'none';
			
				
				
				rowNumberForRow = parseInt(rowNumberForRow) + 1;
				document.getElementById("Incremented_rowNumber").value = rowNumberForRow;
			
				counter++;
			return rowNumberForRow - 1;
	}

			
	</script>