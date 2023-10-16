<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp"%>
<script type="text/javascript">
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
		//alert("In a Approve Request Method");
		var urlstyle="hrms.htm?actionFlag=forwardReq";
		document.frm1.action=urlstyle;
		document.frm1.submit();
	}
	function addrow()
	{
		//chkrow();
		
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
		cell1.innerHTML+="<input type='checkbox' mandatory='false' disabled='true'  name='srno"+apnd+"' tabindex='8' id='srno"+apnd+"'/>";
		
		var cell2=row.insertCell(1);
		cell2.colSpan="1";
		cell2.rowSpan="2";
		var str="<select name='arr_plc"+apnd+"' size='1' caption='drop_down' id='arr_plc"+apnd+"'  mandatory='true' onchange='setpredepart(this)'> <option value='-1'  selected='true'>-----Select -------</option>";
		str+="</select>";
	    cell2.innerHTML+=str;
	
		var cell3=row.insertCell(2);
		cell3.colSpan="1";
		cell3.rowSpan="2";
		cell3.innerHTML="<input type='text' mandatory='false' maxlength=10 size=10 name='arr_dt"+apnd+"' tabindex='8' id='arr_dt"+apnd+"' onkeypress='return checkDateNumber()' class='dateTimetag' onblur='departurechkdate(this)' value=''  />";
		cell3.innerHTML+="<img src='images/CalendarImages/ico-calendar.gif'	width=20 onClick=window_open('arr_dt"+apnd+"',375,570)  alt='Click to View Calendar'	align='absmiddle'>";
		
		var cell4=row.insertCell(3);
		cell4.colSpan="1";
		cell4.rowSpan="2";
		cell4.innerHTML="<input type='text' mandatory='false' size='1'  name='arr_tm"+apnd+"' tabindex='8' id='arr_tm"+apnd+"' onblur='arrivalTimeCheck(this)'/>";
		
		var cell5=row.insertCell(4);
		cell5.colSpan="1";
		cell5.rowSpan="2";
		str="<select name='depart_plc"+apnd+"' size='1' caption='drop_down'  id='depart_plc"+apnd+"'  mandatory='true' onchange='setArrival(this)'> <option value='-1'  selected='true'> -----Select -------</option>";
		str+="</select>";
		cell5.innerHTML+=str;
		
		var cell6=row.insertCell(5);
		cell6.colSpan="1";
		cell6.rowSpan="2";
		cell6.innerHTML+="<input type='text' mandatory='false' maxlength=10 size=10 name='depart_dt"+apnd+"' tabindex='8' id='depart_dt"+apnd+"' onkeypress='return checkDateNumber()' class='dateTimetag' onblur='checkdate(this)' value='' />";
		cell6.innerHTML+="<img src='images/CalendarImages/ico-calendar.gif'	width=20 onClick=window_open('depart_dt"+apnd+"',375,570)  alt='Click to View Calendar'	align='absmiddle'>";
		
		var cell7=row.insertCell(6);
		cell7.colSpan="1";
		cell7.rowSpan="2";
		cell7.innerHTML="<input type='text' mandatory='false' size='1'  name='depart_tm"+apnd+"' onblur='departureTimeCheck(this)' tabindex='8' id='depart_tm"+apnd+"'/>";
		
		var cell8=row.insertCell(7);
		cell8.colSpan="1";
		cell8.rowSpan="2";
		str="<select name='con_mod"+apnd+"' size='1' caption='drop_down' id='con_mod"+apnd+"'  mandatory='true' onchange='hide(this)'> <option value='-1'  selected='true'>-----Select -------</option>";
	    str+="</select>";
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
		cell17.innerHTML="<label>0</label>";
		
		var cell18=row.insertCell(17);
		cell18.colSpan="1";
		cell18.rowSpan="2";
		cell18.style.display="none";
		cell18.id="discol"+apnd;
		cell18.innerHTML="<input type='text'  size='3' name='dis"+apnd+"' id='dis"+apnd+"'  tabindex='8' />";
		
		
		var cell19=row.insertCell(18);
		cell19.colSpan="1";
		cell19.rowSpan="2";
		str="<select name='purpose"+apnd+"' size='1' id='purpose"+apnd+"' caption='drop_down'   mandatory='true' onchange=''> <option value='-1'  selected='true'>-----Select -------</option>";
	    str+="</select>";
	    cell19.innerHTML+=str;
	
		var cell20=row.insertCell(19);
		cell20.colSpan="1";
		cell20.rowSpan="2";
		str="<select name='accom"+apnd+"' size='1' caption='drop_down' id='accom"+apnd+"'  mandatory='true' onchange=''> <option value='-1'  selected='true'>--------Select -----------</option>";
	    str+="</select>";
        cell20.innerHTML+=str;
		
		var cell21=row.insertCell(20);
		cell21.colSpan="1";
		cell21.rowSpan="2";
		cell21.innerHTML="<input type='text'  size='2' name='cost"+apnd+"' id='cost"+apnd+"'  tabindex='8' />";
		
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
		str="<select name='sub_train"+apnd+"' id='sub_train"+apnd+"' size='1' caption='drop_down'   mandatory='true' onchange='' disabled='true'> <option value='-1'  selected='true'>-----Select -------</option>";
	    str+="</select>";
    	str+="<select name='sub_own"+apnd+"' id='sub_own"+apnd+"' size='1' caption='drop_down' style='display:none'  mandatory='true' onchange='enabletype(this)'> <option value='-1'  selected='true'>-----Select -------</option>";
	    str+="</select>";
		cell1.innerHTML=str;
		
		
		cell2 = row.insertCell(1);
		cell2.colSpan="1";
		cell2.rowSpan="1";
		cell2.id="clscol"+apnd;
		cell2.style.display="";
		str="<select name='sub_class"+apnd+"' id='sub_class"+apnd+"' size='1' caption='drop_down'   mandatory='true' onchange='' disabled='true'> <option value='-1'  selected='true'>-----Select -------</option>";
	    str+="</select>";
		str+="<select name='sub_veh"+apnd+"' id='sub_veh"+apnd+"' size='1' caption='drop_down' style='display:none'  mandatory='true' onchange='' disabled='true'> <option value='-1'  selected='true'>-----Select -------</option>";
	    str+="</select>";
	    
		cell2.innerHTML=str;
		
		
}
</script>
<html>
	<c:set var="resultObj" value="${result}"></c:set>
	<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
   <c:set var="travelMstList" value="${resValue.travelMst}"></c:set>
   <c:set var="travelDtlsList" value="${resValue.travelDtls}"></c:set>
   <c:set var="travelreqList" value="${resValue.hrTravelReq}"></c:set>
	<c:set var="travelacomList" value="${resValue.travelacomDtls}"></c:set>
	<c:set var="tourDtls" value="${resValue.travelMstlistVo}"></c:set>
<c:set var="ReqId" value="${resValue.RequestId}"></c:set>

	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<br>
<br>
<br>
<br>

<fmt:setBundle basename="resources.leave.AlertMessages"
	var="alertLables" scope="request" />
<hdiits:form name="frm1" validate="true" method="POST"	encType="multipart/form-data">
	<br>
	<div style="width: 100%;height: 100%;overflow:scroll"><br>
	<!--  Starting main Table -->
	<table class="tabtable">
		<tr class="datatableheader">
			<td class="fieldLabel" colspan="4" align="left"><font
				color="#00000"> <strong><u><fmt:message
				key="HRMS.Trip_Details" /></u></strong> </font></td>
		</tr>
		<tr>
			<td><b><fmt:message key="HRMS.Purpose" /></td>
			<td><hdiits:textarea mandatory="true" rows="4" cols="35"
				name="trippurpose" tabindex="1" id="trippurpose" validation="txt.isrequired"
				caption="purpose" readonly="true" /></td>
			<td><b><fmt:message key="HRMS.Ref_file_no" /></td>
			<td><hdiits:text mandatory="false" name="ref_file_no"
				tabindex="2" id="ref_file_no" validation="txt.isrequired"
				caption="ref. file no." readonly="true" /></td>
		</tr>
		<tr class="datatableheader">
			<td class="fieldLabel" width="15%" align="left"><font
				color="#0000"> <strong><u><fmt:message
				key="HRMS.Journey_Details" /></u></strong> </font></td>
		</tr>
	</table> 
	<!--  End main Table --> <!-- Start : Detailed table -->
	<table id='detailsTable' border="1" cellpadding="0" cellspacing="0"
		BGCOLOR="WHITE" class="datatable" style="background: #eeeeee;padding: 2px;">
		<!-- Start: Heading -->
		<thead title="Heading">
			<br>
			<tr height="25%" class="datatableheader">
				<td rowspan="2" colspan="1" width="1%"></td>
				<td rowspan="1" colspan="3" height="25%" width="25%" align="center"><fmt:message
					key="HRMS.Departure" /></td>
				<td rowspan="1" colspan="3" height="25%" width="25%" align="center"><fmt:message
					key="HRMS.Arrival" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><fmt:message
					key="HRMS.Conveyance_Mode" /></td>
				<td rowspan="2" colspan="2" height="25%" align="center"><fmt:message
					key="HRMS.Sub_Items" /></td>
				<td rowspan="2" colspan="1" height="25%" width="1%"><fmt:message
					key="HRMS.Distance" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><fmt:message
					key="HRMS.Purose_Of_Stay" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><fmt:message
					key="HRMS.Accomodation" /></td>
				<td rowspan="2" colspan="1" height="25%" width="2%"><fmt:message
					key="HRMS.Total_Cost" /></td>
				<td rowspan="2" colspan="1" height="25%" width="10%"><fmt:message
					key="HRMS.Remark" /></td>
			</tr>
			<tr class="datatableheader">
				<td colspan="1" width="10%"><fmt:message key="HRMS.Place" /></td>
				<td colspan="1" width="5%"><fmt:message key="HRMS.Departure_Date" /></td>
				<td colspan="1" width="1%"><fmt:message key="HRMS.Time" /></td>
				<td colspan="1" width="10%"><fmt:message key="HRMS.Place" /></td>
				<td colspan="1" width="5%"><fmt:message key="HRMS.Arrival_Date" /></td>
				<td colspan="1" width="1%"><fmt:message key="HRMS.Time" /></td>
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
					validation="sel.isrequired" id="arr_plc0"
					onchange="setDeparture(this)" tabindex="2" readonly="true" >
					<hdiits:option value="-1" selected="true" >------Select -----</hdiits:option>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="arr_dt0" caption="arr_dt0" disabled="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					name="arr_tm0" id="arr_tm0" size="1" maxlength="5" onblur="arrivalTimeCheck(this)" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="depart_plc0" size="1" caption="departure_place"
					validation="sel.isrequired" id="depart_plc0" mandatory="true"
					onchange="setArrival(this)" readonly="true">
					<hdiits:option value="-1" selected="true">------Select -----</hdiits:option>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="depart_dt0" caption="depart_dt0" onblur=" checkdate(this)" disabled="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					size="1" name="depart_tm0" readonly="true"  id="depart_tm0" onblur="departureTimeCheck(this)" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%">
				<hdiits:select
					name="con_mod0" id="con_mod0" caption="con_mode0" mandatory="true"
					validation="sel.isrequired" onchange="hide(this)" readonly="true">
					<hdiits:option value="-1" selected="true" >------Select -----</hdiits:option>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="bus0">Business <hdiits:radio name="airmod0" id="airmod0" value="Business"  title="Business" /></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="eco0">Economic <hdiits:radio name="airmod0" id="airmod0" value="Economic" title="Economic" /></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="own0"><label>Own by</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="veh0"><label>Vehicle Type</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="trn0"
					style="display: none" ><label style="border-spacing:em;">Train</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="class0"
					style="display: none"><label>Class</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="fir_dm0"><label></label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="sec_dm0"><label></label></td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="dislab0"><center><label>0</label></center></td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="discol0" style="display: none;" ><hdiits:text
					size="3" name="dis0" id="dis0" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="purpose0" id="purpose0" caption="purposeofstay0"
					mandatory="true" validation="sel.isrequired" readonly="true">
					<hdiits:option value="-1" selected="true">------Select -----</hdiits:option>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="accom0" id="accom0" caption="accomodation0" mandatory="true"
					validation="sel.isrequired" readonly="true">
					<hdiits:option value="-1" selected="true">---------Select---------</hdiits:option>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="2%"><hdiits:text
					size="2" name="cost0" readonly="true"  id="cost0" /></td>
				<td rowspan="2" colspan="1" height="25%" width="10%"><hdiits:textarea
					name="remark0" readonly="true"  id="remark0" rows="3" cols="28"></hdiits:textarea></td>
			</tr>
			<tr height="25%">
				<td rowspan="1" colspan="1" height="25%" align="center" id="trncol0" >
				<hdiits:select name="sub_train0" id="sub_train0" caption="train"
					mandatory="false" validation="sel.isrequired" readonly="true">
					<hdiits:option value='-1' selected="true">------Select -----</hdiits:option>
				</hdiits:select>
				<hdiits:select name="sub_own0" id="sub_onw0" caption="train"
					mandatory="false" validation="sel.isrequired" readonly="true" style="display: none">
					<hdiits:option value='-1' selected="true">------Select -----</hdiits:option>
				</hdiits:select>
				</td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="clscol0">
				<hdiits:select name="sub_class0" id="sub_class0" caption="class"
					mandatory="false" validation="sel.isrequired" readonly="true">
					<hdiits:option value='-1' selected="true">------Select -----</hdiits:option>
				</hdiits:select>
				<hdiits:select name="sub_veh0" id="sub_veh0" caption="class"
					mandatory="false" validation="sel.isrequired" style="display: none" readonly="true">
					<hdiits:option value='-1' selected="true"> ------Select -----</hdiits:option>
				</hdiits:select>
				</td>
				
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
					validation="sel.isrequired" id="arr_plc1" readonly="true">
					<hdiits:option value="-1" selected="true">------Select -----</hdiits:option>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="arr_dt1" caption="arr_dt1" onblur="departurechkdate(this)" disabled="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					size="1" name="arr_tm1" readonly="true"  onblur="arrivalTimeCheck(this)" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="depart_plc1" size="1" caption="depart_place1"
					validation="sel.isrequired" id="depart_plc1" mandatory="true" onchange="donotchange(this)" readonly="true">
					<hdiits:option value="-1" selected="true">------Select -----</hdiits:option>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="depart_dt1" caption="depart_dt1" disabled="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					size="1" name="depart_tm1" readonly="true"  onblur="departureTimeCheck(this)" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="con_mod1" caption="con_mode1" mandatory="true"
					validation="sel.isrequired" onchange="hide(this)" id="con_mod1" readonly="true">
					<hdiits:option value="-1" selected="true"> ------Select -----</hdiits:option>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="bus1">Business  <hdiits:radio name="airmod1" title="Business" id="airmod1" value=""/></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="eco1">Economic  <hdiits:radio name="airmod1"  title="Economic" id="airmod1" value=""/></td>
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
				<td rowspan="2" colspan="1" height="25%" width="1%" id="dislab1"><center><label>0</label></center></td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="discol1" style="display: none;" ><hdiits:text
					size="3" name="dis1" id="dis1" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="purpose1" caption="purposeofstay1" mandatory="true"
					validation="sel.isrequired" readonly="true">
						<hdiits:option value="-1" selected="true">------Select -----</hdiits:option>
					</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="accom1" caption="accomodation1" mandatory="true"
					validation="sel.isrequired" readonly="true">
					<hdiits:option value="-1">------Select -----</hdiits:option>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="2%"><hdiits:text
					size="2" name="cost1" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="10%"><hdiits:textarea
					name="remark1" readonly="true"  rows="3" cols="28"></hdiits:textarea></td>
			</tr>
			<tr height="25%">
				<td rowspan="1" colspan="1" height="25%" align="center" id="trncol1">
				<hdiits:select name="sub_train1" id="sub_train1" caption="train1"
					mandatory="false" validation="sel.isrequired" readonly="true">
					<hdiits:option value='-1'  selected="true">---------Select--------</hdiits:option>
				</hdiits:select>
				<hdiits:select name="sub_own1" id="sub_own1" caption="train1"
					mandatory="false" validation="sel.isrequired" style="display:none" onchange="enabletype(this)" readonly="true">
					<hdiits:option value='-1' selected="true">------Select -----</hdiits:option>
				</hdiits:select>
				</td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="clscol1">
				<hdiits:select name="sub_class1" id="sub_class1"  caption="class1 "
					mandatory="false" validation="sel.isrequired" readonly="true">
					<hdiits:option value='-1' selected="true">------Select -----</hdiits:option>
				</hdiits:select>
				<hdiits:select name="sub_veh1" id="sub_veh1" caption="class1 "
					mandatory="false" validation="sel.isrequired" style="display:none" readonly="true">
					<hdiits:option value='-1' selected="true">------Select -----</hdiits:option>
				</hdiits:select>
				</td>
				

			</tr>
		</tbody>
		<!-- End: Second Row -->
	</table>
	<!-- End : Detailed table -->
	<br>
	<br>
	<br>
	<br>

	<table border="1" style="display: none;">
		<tr>
			<td><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="TravelAttachment" />
				<jsp:param name="formName" value="frm1" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="multiple" value="N" />
			</jsp:include></td>
		</tr>
	</table>
	<br>
	<center>
	<table id='tripdtls' border="1" cellpadding="0" width="68%"
		cellspacing="0" BGCOLOR="WHITE"
		style="background: #eeeeee;padding: 2px;">
		<tr class="datatableheader">
			<td style="width: 5%"></td>
			<td style="width: 35%">Trip Name</td>
			<td style="width: 10%">Actions</td>
		</tr>
		
		<c:set var="cnt" value="1"> </c:set>
		<c:forEach var="Dtls" items="${tourDtls}">
		<tr>
			<td style="width: 5%"><hdiits:checkbox name="srno" value="-1"/> </td>
			<td style="width: 35%">${Dtls.tourName}</td>
			<td style="width: 10%"><a href="#" name="${Dtls.tourId}" onclick="viewDetails(this)">View</a>
		</tr>
		</c:forEach>

	</table>
	</center>
	<br>
	<center><hdiits:button type="button" name="approve"
		value="Approve" id="approve" captionid="approve_trip"
		onclick="approveReq()" /> <hdiits:button type="button" name="forward"
		value="Forward" id="forward" captionid="forward_req"
		onclick="forwardreq()" /> <hdiits:button type="button"
		name="closeButton" value="Close" id="closeButton" captionid="Close"
		onclick="closewindow()" /> <br>
	<br>
	</center>
</div>
<!--List of  Hidden -->	

	<hdiits:hidden name="RequestId" id="RequestId"/>
	<hdiits:hidden name="tourId" id="tourId"/>
	<script>
		var RequestId=document.getElementById('RequestId');
		RequestId.value="${ReqId}"
		//alert("Value of Request Id is set to jsp==="+RequestId.value);
	</script>	
<!-- End Hiddens -->
</hdiits:form>

</body>
</html>
	<script type="text/javascript" >
		var cnt=0;
		var row=0;
		var row1=0;
	</script>

<c:forEach var="travelDtls" items="${travelDtlsList}">
	<script type="text/javascript" >
		 cnt=cnt*1+1;
	</script>
</c:forEach>
<script type="text/javascript" >
		row1=cnt*1-1;
		row=cnt*1-1;
</script>

<c:if test="${travelMstList}==null"></c:if>
<c:forEach var="travelMst" items="${travelMstList}">
	<script type="text/javascript" >
	//Set value of Trip purpose	
		var trip_purpose=document.getElementById("trippurpose");
		trip_purpose.disabled="true";
		trip_purpose.value="${travelMst.purpose}";
		
	//Set Reffileno 
		var ref_file_no=document.getElementById("ref_file_no");
		ref_file_no.value="${travelMst.refFileNo}";
		ref_file_no.disabled="true";
	</script>

</c:forEach>
	<script>
		
		 for(var i=0;i<(cnt-2);i++)
		 {
		 	addrow();
		 }
		
	</script>
<c:forEach var="travelacomDtls" items="${travelacomList}">
	<script>
		var accom_nm="accom"+row1;
		var accom=document.getElementById(accom_nm);
		accom.style.width = "140px";
		accom.disabled="true";
		var acomId="${travelacomDtls}";
		if(acomId=="-1")
		{
			accom.options[accom.selectedIndex].text="----selected-----";
		}else if(acomId=="400415")
		{
			accom.options[accom.selectedIndex].text="Govt/Private Sector";
		}else if(acomId=="400416")
		{
			accom.options[accom.selectedIndex].text="Hotel/Institute";	
		}else if(acomId=="400417")
		{
			accom.options[accom.selectedIndex].text="Self Accomodation";
		}else if(acomId=="400418")
		{
			accom.options[accom.selectedIndex].text="Circuit House";
		}else if(acomId=="400419")
		{
			accom.options[accom.selectedIndex].text="N/A";
		}
		
		row1=row1*1-1;
	</script>
</c:forEach>
	<c:forEach var="travelDtls" items="${travelDtlsList}">
	<script>
/*
//set value of Arrival placess
		var arr_plc_nm="arr_plc"+row;
		var arr_plc=document.getElementById(arr_plc_nm);
		arr_plc.style.width = "120px";
		arr_plc.disabled="true";
    	arr_plc.options[arr_plc.selectedIndex].text="${travelDtls.travelFromPlace}";
    	
  */

//set Value of Arrival Date
		var arr_dt_nm="arr_dt"+row;
		var arr_dt=document.getElementById(arr_dt_nm);
		arr_dt.disabled="true";
		var dt="${travelDtls.travelFromDate}";
		var ary =new Array();
		var dateary=new Array();
		
		ary=dt.split(" ");
		
		dateary=ary[0].split("-");
		
		var newDt=dateary[2]+"/"+dateary[1]+"/"+dateary[0];

		arr_dt.value=newDt;
//set value of Arrival Time
		
		var subtm=ary[1].substr(0,5);
		var arr_tm_nm="arr_tm"+row;
		var arr_tm=document.getElementById(arr_tm_nm);	
		arr_tm.disabled="true";
		arr_tm.value=subtm;

/*
//set value of Depart placess
		var depart_plc_nm="depart_plc"+row;
		var depart_plc=document.getElementById(depart_plc_nm);
		depart_plc.style.width = "120px";
		depart_plc.disabled="true";
    	depart_plc.options[depart_plc.selectedIndex].text="${travelDtls.travelToPlace}";
*/

//set Value of Arrival Date
		var depart_dt_nm="depart_dt"+row;
		var depart_dt=document.getElementById(depart_dt_nm);
		depart_dt.disabled="true";
		var dt1="${travelDtls.travelToDate}";
		
		ary=dt1.split(" ");
		
		dateary=ary[0].split("-");
		
		newDt=dateary[2]+"/"+dateary[1]+"/"+dateary[0];
		depart_dt.value=newDt;

//set value of Arrival Time
		
		var subtm=ary[1].substr(0,5);
		var depart_tm_nm="depart_tm"+row;
		var depart_tm=document.getElementById(depart_tm_nm);	
		depart_tm.disabled="true";
		depart_tm.value=subtm;

//set Value of ConvyanceMode
		var con_mod_nm="con_mod"+row;
		var con_mod=document.getElementById(con_mod_nm);
		con_mod.style.width = "80px";
		con_mod.disabled="true";
		con_mod.options[con_mod.selectedIndex].text="${travelDtls.cmnLookupMstByTravelMode.lookupDesc}";
		var idx=row;
		var mod="${travelDtls.cmnLookupMstByTravelMode.lookupId}";
		

				
		var own_nm="own"+idx;
		var bus_nm="bus"+idx;
		var eco_nm="eco"+idx;
		var veh_nm="veh"+idx;
		var trn_nm="trn"+idx;
		var class_nm="class"+idx;
		var trncol_nm="trncol"+idx;
		var clscol_nm="clscol"+idx;
		var fir_nm="fir_dm"+idx;
		var sec_nm="sec_dm"+idx;
		var dislab_nm="dislab"+idx;
		var discol_nm="discol"+idx;
		var sub_train_nm="sub_train"+idx;
		var sub_class_nm="sub_class"+idx;
		var sub_own_nm="sub_own"+idx;
		var sub_veh_nm="sub_veh"+idx;
		var dis_nm="dis"+idx;
		
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
		var sub_train=document.getElementById(sub_train_nm);
		var sub_class=document.getElementById(sub_class_nm);
		var sub_own=document.getElementById(sub_own_nm);
		var sub_veh=document.getElementById(sub_veh_nm);	
		
		if(mod=='-1')
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
			sub_train.value="-1";
			sub_class.style.display="";
			sub_class.disabled="true";
			sub_class.value="-1";
			sub_own.style.display="none";
			sub_veh.style.display="none";
		}else if(mod=="500002")
		{
			
			own.style.display="none";
			bus.style.display="";
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
		}else if(mod=='500003')
		{
			sub_train.disabled="";
			sub_class.disabled="";
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
			sub_train.style.width = "120px";
			sub_train.disabled="true";
			sub_train.options[sub_train.selectedIndex].text="${travelDtls.cmnLookupMstByTrainName.lookupDesc}";
			sub_class.style.width = "120px";
			sub_class.disabled="true";
			sub_class.options[sub_class.selectedIndex].text="${travelDtls.cmnLookupMstByTravelClass.lookupDesc}";
			
		}else if (mod=='500004')
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
			sub_veh.style.display="";
			sub_veh.disabled="true";
			if("${travelDtls.cmnLookupMstByGovtOrGovtpaid.lookupId}"=="-1")
			{
				sub_veh.options[sub_veh.selectedIndex].text="-----Select---------";
			}
			else
			{
				sub_veh.options[sub_veh.selectedIndex].text="${travelDtls.cmnLookupMstByGovtOrGovtpaid.lookupDesc}";
			}
			sub_own.disabled="true";
			sub_own.options[sub_own.selectedIndex].text="${travelDtls.cmnLookupMstByVehicleType.lookupDesc}";
			var dis=document.getElementById(dis_nm);
			dis.disabled="true";
			dis.value="${travelDtls.distance}";
			
		}
		
		
//set value of Economic or Business value		

		var modval="${travelDtls.cmnLookupMstByAirType.lookupId}";
		
		
//Set value of pupose of Stay
		var purpose_nm="purpose"+row;
		var purpose=document.getElementById(purpose_nm);
		purpose.style.width = "120px";
		purpose.disabled="true";
		if("${travelDtls.cmnLookupMstByPurposeOfStay.lookupId}"=="-1")
		{
			purpose.options[purpose.selectedIndex].text="-----selected------";
		}else
		{
					purpose.options[purpose.selectedIndex].text="${travelDtls.cmnLookupMstByPurposeOfStay.lookupDesc}";
		}

//Set value of Cost
		var cost_nm="cost"+row;
		var cost=document.getElementById(cost_nm);
		cost.disabled="true";
		cost.value="${travelDtls.costIncurred}";
		
//Set value of Remarks
		var remark_nm="remark"+row;
		var remark=document.getElementById(remark_nm);
		remark.disabled="true";
		remark.value="${travelDtls.remark}";
// End
		row=row*1-1; 
	
	</script>
</c:forEach>

<script>
	
</script>
 <%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
 %>