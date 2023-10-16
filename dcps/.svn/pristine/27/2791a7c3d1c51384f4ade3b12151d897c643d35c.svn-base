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
						
					
						var arr_plc_nm="arr_plc"+i;
						var arr_dt_nm="arr_dt"+i;
						
						var arr_tm_nm="arr_tm"+i;
						var depart_plc_nm="depart_plc"+i;
						var depart_dt_nm="depart_dt"+i;
						var depart_tm_nm="depart_tm"+i;
						var con_mod_nm="con_mod"+i;
						var dis_nm="dis"+i;
						var purpose_nm="purpose"+i;
						var accom_nm="accom"+i;
						var cost_nm="cost"+i;
						var remark_nm="remark"+i;
						var sub_train_nm="sub_train"+i;
						var sub_class_nm="sub_class"+i;
						var sub_own_nm="sub_own"+i;
						var sub_veh_nm="sub_veh"+i;
						var airmod_nm="airmod"+i;
						
						
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
								sub_train.disabled="";
								sub_train.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTrainName/lookupId');
								sub_train.selected="true";
								
								sub_class.disabled="";
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
						
						
						cost.value=getXPathValueFromDOM(xmlDOM, parentNode+'/costIncurred');
						cost.selected="true";
						
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
				str=getXPathValueFromDOM(xmlDOM, 'travel_From_Date');
				document.getElementById('purpose').value=getXPathValueFromDOM(xmlDOM, 'purpose');
				document.getElementById("ref_file_no").value=getXPathValueFromDOM(xmlDOM, 'refFileNo');
				
				
				var savetrip=document.getElementById('savetrip');
				savetrip.style.display="";				
				
				var addtripbt=document.getElementById('addtripbt');
				addtripbt.disabled="true";				

		}
	}	
	
	function enabletype(form)
	{
		var idx=form.name.charAt(form.name.length-1);
		var sub_veh_nm="sub_veh"+idx;
		if(form.selectedIndex==4 ||form.selectedIndex==5)
		{
			var sub_veh=document.getElementById(sub_veh_nm);
			sub_veh.disabled="true";
		}
		else
		{
			var sub_veh=document.getElementById(sub_veh_nm);
			sub_veh.disabled="";
		}
			
	}  
	function changeReqType(form)
   {
   		if(form.value=='330004'||form.value=='330009')
  	 	{
	  	 	var urlstyle="hdiits.htm?actionFlag=getTravelView&flag=draft"
			document.frm1.action=urlstyle;
			document.frm1.submit(); 
   		}
   		if(form.value=='330010'||form.value=='330005')
   		{
   			var urlstyle="hdiits.htm?actionFlag=getTravelView&flag=pending"
			document.frm1.action=urlstyle;
			document.frm1.submit(); 
   		}
   		if(form.value=='330008'||form.value=='330003')
   		{
   			var urlstyle="hdiits.htm?actionFlag=getTravelView&flag=approve"
			document.frm1.action=urlstyle;
			document.frm1.submit(); 
   		}
   }
	function hide(form)
	{
		
		var mod=form.value;
		 
		var idx=form.id.charAt(form.id.length-1);
		
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
		var airmod_nm="airmod"+idx;
		
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
		var airmod=document.getElementById(airmod_nm);
		
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
			airmod.value="321611";
		}else if(mod=="321594" || mod=="321598")
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
		}else if(mod=='321595'|| mod=="321599")
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
		}else if (mod=='321600' || mod=="321596")
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
		}
		
		
	}
function setAirmodeval(form)
	{
			
		var airmod0=document.getElementById(form.id);
		if(form.title=="Business")
		{
			
			airmod0.value=air_modCmb[0];
		}
		else
		{
			
			airmod0.value=air_modCmb[1];
		}
		
		
		
	}

	function closewindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.frm1.action=urlstyle;
		document.frm1.submit();
	}
		function donotchange(form)
	{
		var arr_plc0=document.getElementById("arr_plc0");
		form.value=arr_plc0.value;
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
		var str="<select name='arr_plc"+apnd+"' size='1' alertLables='drop_down' id='arr_plc"+apnd+"'  mandatory='true' onchange='setpredepart(this)'> <option value='321611'>-----Select -------</option>";
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
		cell3.innerHTML="<input type='text' name='arr_dt"+apnd+"' readOnly='true' onkeypress='return checkDateNumber()' id='arr_dt"+apnd+"' class='texttag'  onBlur='departurechkdate(this)' value=''  maxlength=10 size=10/>";
		cell3.innerHTML+="<img style='cursor:hand' id='img_arr_dt"+apnd+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('arr_dt"+apnd+"',375,570,'','arr_dt"+apnd+"','Please~enter~valid~$CPTN,Departure~Date') >";
		cell3.innerHTML+="&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";

		
		var cell4=row.insertCell(3);
		cell4.colSpan="1";
		cell4.rowSpan="2";
		cell4.innerHTML="<input type='text' mandatory='false' size='1'  name='arr_tm"+apnd+"' tabindex='8' id='arr_tm"+apnd+"' onblur='arrivalTimeCheck(this)'/>";
		cell4.innerHTML+="<label class='mandatoryindicator'>*</label>";
		
		var cell5=row.insertCell(4);
		cell5.colSpan="1";
		cell5.rowSpan="2";
		str="<select name='depart_plc"+apnd+"' size='1' alertLables='drop_down'  id='depart_plc"+apnd+"'  mandatory='true' onchange='setArrival(this)'> <option value='321611'>-----Select -------</option>";
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
		cell6.innerHTML="<input type='text' name='depart_dt"+apnd+"'  onkeypress='return checkDateNumber()' id='depart_dt"+apnd+"' readOnly='true' class='texttag'  onBlur='departurechkdate(this)' value=''  maxlength=10 size=10/>";
		cell6.innerHTML+="<img style='cursor:hand' id='img_depart_dt"+apnd+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('depart_dt"+apnd+"',375,570,'','depart_dt"+apnd+"','Please~enter~valid~$CPTN,Departure~Date') >";
		cell6.innerHTML+="&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";

		
		var cell7=row.insertCell(6);
		cell7.colSpan="1";
		cell7.rowSpan="2";
		cell7.innerHTML="<input type='text' mandatory='false' size='1'  name='depart_tm"+apnd+"' onblur='departureTimeCheck(this)' tabindex='8' id='depart_tm"+apnd+"'/>";
		cell7.innerHTML+="<label class='mandatoryindicator'>*</label>"
		
		var cell8=row.insertCell(7);
		cell8.colSpan="1";
		cell8.rowSpan="2";
		str="<select name='con_mod"+apnd+"' size='1' alertLables='drop_down' id='con_mod"+apnd+"'  mandatory='true' onchange='hide(this)'> <option value='321611'>-----Select -------</option>";
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
		cell11.innerHTML="Business <input type='radio' id='airmod"+apnd+"'  title='Business'  onclick='setAirmodeval(this)' value='321611' name='airmod"+apnd+"'>";
		
		var cell12=row.insertCell(11);
		cell12.colSpan="1";
		cell12.rowSpan="2";
		cell12.id="eco"+apnd;
		cell12.style.display="none";
		cell12.innerHTML="Ecomony <input type='radio' id='airmod"+apnd+"' title='Economic' onclick='setAirmodeval(this)' value='321611' name='airmod"+apnd+"'>";
		

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
		cell18.innerHTML="<input type='text'  value='0'  size='3' name='dis"+apnd+"' id='dis"+apnd+"'  tabindex='8' />";

		
		var cell19=row.insertCell(18);
		cell19.colSpan="1";
		cell19.rowSpan="2";
		str="<select name='purpose"+apnd+"' size='1' id='purpose"+apnd+"' alertLables='drop_down'   mandatory='true' onchange=''> <option value='321611'>-----Select -------</option>";
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
		str="<select name='accom"+apnd+"' size='1' alertLables='drop_down' id='accom"+apnd+"'  mandatory='true' onchange=''> <option value='321611'>--------Select -----------</option>";
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
		cell21.innerHTML="<input type='text'  size='2' value='0.00' maxlength='6' name='cost"+apnd+"' id='cost"+apnd+"' onkeypress='return checkNumberOnly(true)'  tabindex='8' />";
		 
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
		str="<select name='sub_train"+apnd+"' id='sub_train"+apnd+"' size='1' alertLables='drop_down'   mandatory='true' onchange='' disabled='true'> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<trainCmb.length-1;i++)
		{
			str+="<option value='"+trainCmbVal[i]+"'>"+trainCmb[i]+"</option>";
			
		}
	    str+="</select>";
    	str+="<select name='sub_own"+apnd+"' id='sub_own"+apnd+"' size='1' alertLables='drop_down' style='display:none'  mandatory='true' onchange='enabletype(this)'> <option value='321611'>-----Select -------</option>";
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
		str="<select name='sub_class"+apnd+"' id='sub_class"+apnd+"' size='1' alertLables='drop_down'   mandatory='true' onchange='' disabled='true'> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<classCmb.length-1;i++)
		{
			str+="<option value='"+classCmbVal[i]+"'>"+classCmb[i]+"</option>";
			
		}
	    str+="</select>";
		str+="<select name='sub_veh"+apnd+"' id='sub_veh"+apnd+"' size='1' alertLables='drop_down' style='display:none'  mandatory='true' onchange='' disabled='true'> <option value='321611'>-----Select -------</option>";
	    for(var i=0;i<veh_typeCmb.length-1;i++)
		{
			str+="<option value='"+veh_typeCmbVal[i]+"'>"+veh_typeCmb[i]+"</option>";
			
		}
	    str+="</select>";
	    str+="<label class='mandatoryindicator'>*</label>"
		cell2.innerHTML=str;
		
		
		
		
		
		newlen=tb.rows.length;
		newlen=(newlen-2)/2;
		
		var tb1 = document.getElementById('detailsTable');
		var len1 = tb.rows.length;
		var row1=(len1-2)/2;
		if(row1>2)
		{
			var bt = document.getElementById('deleterow');
			bt.style.display="";
		}
			
			
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
	