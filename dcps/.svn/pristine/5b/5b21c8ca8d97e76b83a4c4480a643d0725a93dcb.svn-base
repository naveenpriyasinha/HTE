

<html>
<head>
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/OuterPaybillPara.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	

<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<c:set var="deptList" value="${resValue.deptList}" ></c:set>
<c:set var="demandCode" value="${resValue.demandNo}" ></c:set>
<c:set var="majorHead" value="${resValue.mjrHead}" ></c:set>
<c:set var="subMajorHead" value="${resValue.subMjrHead}" ></c:set>
<c:set var="minorHead" value="${resValue.mnrHead}" ></c:set>
<c:set var="subHead" value="${resValue.subHeadCode}" ></c:set>
<c:set var="deptName" value="${resValue.deptName}" ></c:set>
<c:set var="month" value="${resValue.monthSel}" ></c:set>
<c:set var="year" value="${resValue.yearSel}" ></c:set>
<c:set var="locationid" value="${resValue.locationid}" ></c:set>
<c:set var="billList" value="${resValue.billList}" ></c:set>
<c:set var="curYear" value="${resValue.curYear}" ></c:set>
<c:set var="curMonth" value="${resValue.curMonth}" ></c:set>
<c:set var="arrearList" value="${resValue.arrearList}" ></c:set>
<script><!--

var lookupArray = new Array();
function setBudgetType(){
	alert('old value ' + document.getElementById("cmbBudTypeTemp").value);
	document.getElementById("cmbBudType").value = document.getElementById("cmbBudTypeTemp").value
	alert('new value ' + document.getElementById("cmbBudTypeTemp").value);

}
function setSchemeNumber()
{
  document.getElementById('txtSchemeNo').value = document.getElementById('txtSchemeNoTemp').value;
}
function checkPaybillStatus()
{
	
	 if(document.getElementById("cmbYear").value=="-1")
	{
		alert("Please Select The Year First.");
		document.getElementById("cmbYear").focus();
		document.getElementById("billTypeCmb").selectedIndex = 0;
		return false;
	}
	if(document.getElementById("cmbMonth").value=="-1")
	{
		alert("Please Select The month First.");
		document.getElementById("cmbMonth").focus();
		document.getElementById("billTypeCmb").selectedIndex = 0;
		return false;
	}
	if(document.getElementById("billNo").value=="-1")
	{
		alert("Please Select The Bill Number First.");
		document.getElementById("billNo").focus();
		document.getElementById("billTypeCmb").selectedIndex = 0;
		document.getElementById("billNo").selectedIndex = 0;
		return false;
	}
	
	
	  xmlHttp=GetXmlHttpObject();
	  if (xmlHttp==null)
	  {
		  alert ("Your browser does not support AJAX!");
		  return;
	  } 
	  if(document.getElementById("billTypeCmb").value!="arrears" )
	  {	  	  
	      if(document.getElementById("billTypeCmb").value!="-1" )
		  {
	  
		  var url; 
		  var uri='';
		  url= uri+'&subHeadCode='+ document.forms[0].billNo.value+'&month='+document.forms[0].cmbMonth.value+'&year='
		  +document.forms[0].cmbYear.value+'&billTypeLookupId='+document.forms[0].billTypeCmb.value;
		  var actionf="checkOuterPaybillStatus"
			
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 		    		 
			xmlHttp.onreadystatechange=checkStatus;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
			
		}	
		else
		{
			document.getElementById("txtSchemeNo").value="";
			document.getElementById("billNo").selectedIndex = 0;
		}
	  }
	  else
	  {
			getAllData();
			updateBillNo();
	  } 
		

}

function checkStatus()
{
	
		try 
		{
				if (xmlHttp.readyState==complete_state)
		 		{ 						
							var XMLDoc=xmlHttp.responseXML.documentElement;		
							
		                    var namesEntries = XMLDoc.getElementsByTagName("subHeadCode");
		                   // alert('namesEntries' +namesEntries.length);
		                   
							if(namesEntries[0].childNodes[0].text=='true')
							{
								//added by ravysh to check number of supplimentary bills
								
								
								if(namesEntries[0].childNodes[1].text=='true') 
								{
									//alert('You can generate the bill');
									
							//	}
							//else
								//{
									alert("Duplicate bill not generated");
									 document.getElementById('billNo').options[0].text = 'Select';
									    document.getElementById('billNo').value = '-1';
									    document.getElementById('billTypeCmb').options[0].text = 'Select';
									    document.getElementById('billTypeCmb').value = '-1';
										clearAllData();
										clearGradeDsgnListBox();
									
								}
								else
								{
									
								}
								if(namesEntries[0].childNodes[2].text=='2500341')	
								{
									
									alert('Three supplementary bills already Generated');
									document.getElementById('billNo').options[0].text = 'Select';
								    document.getElementById('billNo').value = '-1';
								    document.getElementById('billTypeCmb').options[0].text = 'Select';
								    document.getElementById('billTypeCmb').value = '-1';
									clearAllData();
									clearGradeDsgnListBox();
								}
								else{
								getAllData();
								updateBillNo();
								}
								
							}   
							else
							{
								alert('Paybill Should be Generated');
                                  
							    document.getElementById('billNo').options[0].text = 'Select';
							    document.getElementById('billNo').value = '-1';
							    document.getElementById('billTypeCmb').options[0].text = 'Select';
							    document.getElementById('billTypeCmb').value = '-1';
								clearAllData();
								clearGradeDsgnListBox();
								
							}
								     
		                              
		  		}
		}
		catch( e ) 
		{
			
		}
	
}

function getAllData()
{ 
	clearGradeDsgnListBox();
  //document.getElementsByName('ctrlNo').value=document.getElementsByName('billNo').text;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&subHeadCode='+ document.forms[0].billNo.value;
		  var actionf="getAllDataFromSubHeadId"
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
          		  		  
			xmlHttp.onreadystatechange=fillAllCombo;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
  }

function clearAllData()
{
    document.getElementById('cmbDemand').options[0].text = 'Select';
    document.getElementById('mjrHead').options[0].text = 'Select';
    document.getElementById('cmbSubMjrHead').options[0].text = 'Select';
    document.getElementById('cmbMnrHead').options[0].text = 'Select';
    document.getElementById('cmbSubHead').options[0].text = 'Select';
    
    document.getElementById('cmbDemand').options[0].value = '-1';
	document.getElementById('mjrHead').options[0].value = '-1';
    document.getElementById('cmbSubMjrHead').options[0].value = '-1';
    document.getElementById('cmbMnrHead').options[0].value = '-1';
    document.getElementById('cmbSubHead').options[0].value = '-1';
    
    document.getElementById('ctrlNo').value = "";
    document.getElementById('gradeId1').value = "";
    document.getElementById('designations').value = "";
    
}

function fillAllCombo()
{

if (xmlHttp.readyState==complete_state)
 { 						
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    var namesEntries = XMLDoc.getElementsByTagName('subHeadCode');

                   
                    document.getElementById('cmbDemand').options[0].text = namesEntries[0].childNodes[1].text;
                    document.getElementById('mjrHead').options[0].text = namesEntries[0].childNodes[2].text;
                    document.getElementById('cmbSubMjrHead').options[0].text = namesEntries[0].childNodes[3].text;
                    document.getElementById('cmbMnrHead').options[0].text = namesEntries[0].childNodes[4].text;
                    document.getElementById('cmbSubHead').options[0].text = namesEntries[0].childNodes[0].text;
                    document.getElementById('schemeName').options[0].value = namesEntries[0].childNodes[10].text;
                    
                    document.getElementById('cmbDemand').options[0].value = namesEntries[0].childNodes[1].text;
 					document.getElementById('mjrHead').options[0].value = namesEntries[0].childNodes[2].text;
                    document.getElementById('cmbSubMjrHead').options[0].value = namesEntries[0].childNodes[3].text;
                    document.getElementById('cmbMnrHead').options[0].value = namesEntries[0].childNodes[4].text;
                    document.getElementById('cmbSubHead').options[0].value = namesEntries[0].childNodes[0].text; 
                    
                                       
                  
                    
                    var abc = namesEntries[0].childNodes[5].text;
   	                var clas = namesEntries[0].childNodes[6].text;
   	                var dsgnName = namesEntries[0].childNodes[7].text;
   	                dsgnName=dsgnName.replace("[",'');
   	                dsgnName=dsgnName.replace("]",'');
   	                
   	                var clasName = namesEntries[0].childNodes[8].text;
   	                clasName=clasName.replace("[",'');
   	                clasName=clasName.replace("]",'');   	                
   	                
                    var dsgndata = abc.split(",");
                    var desigShrtName = dsgnName.split(",");

                    
                    
                    var post = document.getElementById("cmbSelDesignations");
                    
                    for(var s= 0; s < dsgndata.length; s++)
                    {
	                   
	                    var y = document.createElement('option');   
     			        y.value=dsgndata[s];
     			        y.text=desigShrtName[s];	
     			        try
   				        {      				    					
                            post.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       post.add(y); 
   			   	        }	
	                    
	                   
	                }
	                
	                

                    var clsData = clas.split(",");
                    var clasShrtName = clasName.split(",");
                    
	                var classData = document.getElementById('cmbGrade');
	                
	                
	                for(var k = 0; k < clsData.length;k++)
					{
	                   var y1 = document.createElement('option');  
     			        y1.value=clsData[k];
     			        y1.text=clasShrtName[k];	
     			        try
   				        {      				    					
                            classData.add(y1,null);
           			    }
 				        catch(ex)
   				        {
   			 		       classData.add(y1); 
   			   	        }	
                    }
                    
                    
                    document.getElementById("Demand").value = namesEntries[0].childNodes[1].text;
                    document.getElementById('SubMjHd').value = namesEntries[0].childNodes[3].text;
                    document.getElementById('MjHd').value = namesEntries[0].childNodes[2].text;
                    document.getElementById('MnrHd').value = namesEntries[0].childNodes[4].text;
                    document.getElementById('SubHd').value= namesEntries[0].childNodes[0].text;
                   	document.getElementById("DtlHd").value='00'; 
                   	
                   	//alert("hello 1");
                   	addGradesForBill();
                   	                   	//alert("hello 2");
                   	                   	
					addDesignationForBill(); 
					                   	//alert("hello3");
					fillBillDesc();                 
					                   	//alert("hello 4");
                    loadData();
                                       	//alert("hello 5");
                    //document.getElementById('cmbDemand').options[0].selected = true;
                  updateBillNo();  
                    
  }
 
}

function updateBillNo()
{
	if(document.getElementById("cmbYear").value=="-1")
	{
		alert("Please Select The Year First.");
		document.getElementById("cmbYear").focus();
		document.getElementById("billTypeCmb").selectedIndex = 0;
		return false;
	}
	if(document.getElementById("cmbMonth").value=="-1")
	{
		alert("Please Select The month First.");
		document.getElementById("cmbMonth").focus();
		document.getElementById("billTypeCmb").selectedIndex = 0;
		return false;
	}
	if(document.getElementById("billNo").value=="-1")
	{
		alert("Please Select The Bill Number First.");
		document.getElementById("billNo").focus();
		document.getElementById("billTypeCmb").selectedIndex = 0;
		return false;
	}
	if(document.getElementById("billTypeCmb").value!="" && document.getElementById("billTypeCmb").value!="-1")
	{
		var billType = document.getElementById("billTypeCmb").value;
		var month = document.getElementById("cmbMonth").options[document.getElementById("cmbMonth").selectedIndex].text
		var year = document.getElementById("cmbYear").value;
		var billNo = document.getElementById("billNo").options[document.getElementById("billNo").selectedIndex].text;
		if(billType == "arrears")
			document.getElementById("ctrlNo").value =billNo+'('+month+'/'+year+')A';
		else
			document.getElementById("ctrlNo").value =billNo+'('+month+'/'+year+')P';	
		return true;
	}
}

function getSupplBillData()
{
  if(document.getElementById("billTypeCmb").value=="multiplemonthpaybill")
  {
	  document.getElementById("FromYear1").style.display="";
	  document.getElementById("FromYear2").style.display="";
	  document.getElementById("FromMonth1").style.display="";
	  document.getElementById("FromMonth2").style.display="";
	  document.getElementById("ToYear1").style.display="none";
	  document.getElementById("ToMonth1").style.display="none";
	  document.getElementById("ToYear2").style.display="";
	  document.getElementById("ToMonth2").style.display="";
  }
  else
  {
	  document.getElementById("FromYear1").style.display="none";
	  document.getElementById("FromYear2").style.display="none";
	  document.getElementById("FromMonth1").style.display="none";
	  document.getElementById("FromMonth2").style.display="none";
	  document.getElementById("ToYear2").style.display="none";
	  document.getElementById("ToMonth2").style.display="none";
	  document.getElementById("ToYear1").style.display="";
	  document.getElementById("ToMonth1").style.display="";
  }
}

function chkDuration()
{
	 if(document.getElementById("billTypeCmb").value=="multiplemonthpaybill")
	 {
		 var FromMonth=parseInt(document.getElementById("cmbFromMonth").value);
		 var FromYear=document.getElementById("cmbFromYear").value;
		 var ToMonth=parseInt(document.getElementById("cmbMonth").value);
		 var ToYear=document.getElementById("cmbYear").value;

		if(FromYear=="-1"){
			alert("Please select from year");
		return false;
	 }
		else if (FromMonth==-1){
		alert("Please select from month");
		return false;
		}
		else if(FromYear>ToYear){
			alert("To Year cannot be less than from Year");
			document.getElementById("cmbFromYear").value="-1";
			document.getElementById("cmbYear").value="-1";
			document.getElementById("cmbFromMonth").value="-1";
			document.getElementById("cmbMonth").value="-1";
			
			return false;
			}
		else if((FromYear==ToYear)&&(FromMonth>=ToMonth))
			{
			
			alert("To Month cannot be less than or equal to from Month for same Year");
			document.getElementById("cmbFromYear").value="-1";
			document.getElementById("cmbYear").value="-1";
			document.getElementById("cmbFromMonth").value="-1";
			document.getElementById("cmbMonth").value="-1";
			
		return false;
			}
	 }
	 return true;
}
--></script>

	<br> <br> <br>
	<br> <br> <br>

	<table align="center" width="60%" cellspacing="10" >
	<tr>	
	
	 <!-- added by ravysh for multiple month supplementary bill -->
 
  <TD align="left" width="16%"  id="FromYear1" style="display: none"><b>From Year</b></TD>
   <td align="left" width="34%" style="display: none" id="FromYear2">
	<c:choose>
	<c:when test="${demandCode eq null}">	
    <hdiits:select name="selFromYear" size="1" sort="false" id="cmbFromYear" onchange="GetFromMonths()">
	   	<hdiits:option value="-1"> --Select-- </hdiits:option>
	 	<c:forEach items="${yearList}" var="FromyearList">
 	     <hdiits:option value="${FromyearList.lookupShortName}"> ${FromyearList.lookupDesc} </hdiits:option>
	    </c:forEach>
	  
	   </hdiits:select>
	  </c:when>
	  <c:otherwise>
	      <hdiits:select name="selFromYear" size="1" sort="false" id="cmbFromYear" readonly="true"> 
	      <hdiits:option value="-1" selected="true"> ${year} </hdiits:option>
	      </hdiits:select>
	  </c:otherwise>
	 </c:choose>
	</td>
	<TD  align="left" width="16%"   style="display: none" id="FromMonth1"><b>From Month</b></TD>
	<td  align="left" width="34%" style="display: none" id="FromMonth2">
    <c:choose>
	<c:when test="${demandCode eq null}">	
	 <hdiits:select  name="selFromMonth" size="1" sort="false" id="cmbFromMonth" >
	 <hdiits:option value="-1"> --Select-- </hdiits:option>  	  
	</hdiits:select>
    </c:when>
    <c:otherwise>
    	 <hdiits:select name="selFromMonth" size="1" sort="false" id="cmbFromMonth" readonly="true" onchange="fillBillDesc();updateBillNo();">
    	 <hdiits:option value="-1" selected="true"> ${month} </hdiits:option>
    	 </hdiits:select>
    </c:otherwise>
   </c:choose>
	</td>
	<!-- ended by ravysh -->
	
	<td  align="left" width="16%"  id="ToYear1"> 
		<b><fmt:message key="PR.YEAR" bundle="${commonLables}" /></b>				
     </TD>
      <TD  align="left" width="34%"   id="ToYear2" style="display: none"><b>To Year</b></TD>
	<td>
	<c:choose>
	<c:when test="${demandCode eq null}">	
    <hdiits:select name="selYear" size="1" sort="false" id="cmbYear" onchange="GetMonths()">
	   	<hdiits:option value="-1"> --Select-- </hdiits:option>
	 	<c:forEach items="${yearList}" var="yearList">
 	     <hdiits:option value="${yearList.lookupShortName}"> ${yearList.lookupDesc} </hdiits:option>
	    </c:forEach>
	  
	      <!--<hdiits:option value="${curYear}" >${curYear} </hdiits:option> -->
	   </hdiits:select>
	  </c:when>
	  <c:otherwise>
	      <hdiits:select name="selYear" size="1" sort="false" id="cmbYear" readonly="true"> 
	      <hdiits:option value="-1" selected="true"> ${year} </hdiits:option>
	      </hdiits:select>
	  </c:otherwise>
	 </c:choose>
	</td>
	<TD align="left" width="16%"   id="ToMonth1"><b><fmt:message key="PR.MONTH" bundle="${commonLables}"/></b></TD>
	 <TD  align="left" width="34%"   id="ToMonth2" style="display: none"><b>To Month</b></TD>
	<td>
    <c:choose>
	<c:when test="${demandCode eq null}">	
	 <hdiits:select onchange="fillBillDesc();" name="selMonth" size="1" sort="false" id="cmbMonth">
	 <hdiits:option value="-1"> --Select-- </hdiits:option>  	  
	</hdiits:select>
    </c:when>
    <c:otherwise>
    	 <hdiits:select name="selMonth" size="1" sort="false" id="cmbMonth" readonly="true" onchange="fillBillDesc();updateBillNo();">
    	 <hdiits:option value="-1" selected="true"> ${month} </hdiits:option>
    	 </hdiits:select>
    </c:otherwise>
   </c:choose>
	
	</td>
  
	</tr>

	</table>
		<table align="center" width="60%" cellspacing="10" >
	
	
	<c:choose>
	<c:when test="${demandCode eq null}">
	<tr>
	<TD   align="left" width="16%"  ><b>Bill Id</b></TD>
	<td  align="left" width="34%" >
	<hdiits:select style="width:140px" name="billNo" size="1" id="billNo" sort="false"  caption="BillNo" captionid="billNo"
		validation="sel.isrequired" mandatory="true" onchange="clearAllData();getAllData();updateBillNo();">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	    <c:forEach var="billList" items="${billList}">
         <hdiits:option value="${billList.billHeadId}"><c:out value="${billList.billId}"> </c:out></hdiits:option>
         </c:forEach>
	   </hdiits:select>
	   	   </td>
	   	   
	   	   
	   <td  align="left" width="16%" >
		<b>Bill Type</b> 
		</td>
		<td  align="left" width="34%" >
			<hdiits:select name="billTypeCmb" style="width:140px" mandatory="true" onchange="checkPaybillStatus();getSupplBillData();">
				<hdiits:option value="-1" >Select</hdiits:option>
				<hdiits:option value="paybill">Paybill</hdiits:option>								
			</hdiits:select>
		</td></tr></c:when></c:choose>
	
	<tr>
	
	<tr>
	<TD   align="left" width="16%"  ><b><fmt:message key="PR.SCHEM" bundle="${commonLables}"/></b></TD>
	<td  align="left" width="34%" >
	<hdiits:text tabindex="3" name="txtSchemeNoTemp" default="0000000" caption="Scheme Number"  size="10" maxlength="8" onblur="setSchemeNumber();"/>
	</td>
	
	
	<!-- Added by ankit -->
	   	   
	   	   <TD   align="left" width="16%"  ><b>Scheme Name</b></TD>
	<td  align="left" width="34%" >
	<hdiits:select style="width:140px" name="schemeName" size="1" id="schemeName" sort="false"  caption="SchemeName" captionid="SchemeName">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>	    
	   </hdiits:select>
	   	   </td>
	   	   
	   	   <!--  Ended -->
				
	
	</tr>
	
	<TD  align="left" width="16%"  ><b><fmt:message key="PR.DEMAND" bundle="${commonLables}"/></b></TD>
	<td  align="left" width="34%"  >
	<c:choose>
	<c:when test="${demandCode eq null}">	 
	 <hdiits:select style="width:140px" name="cmbDemand" id="cmbDemand" size="1" mandatory="true" caption="Demand No" captionid="DmdNo"
		validation="sel.isrequired" sort="false">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	 </hdiits:select>
	</c:when>
	<c:otherwise>
		 <hdiits:select style="width:140px" name="cmbDemand" id="cmbDemand" size="1" caption="Demand No" captionid="DmdNo"
		 sort="false" readonly="true">
	   <hdiits:option value="-1" selected="true"> ${demandCode} </hdiits:option>
	 </hdiits:select>
	</c:otherwise>
	</c:choose>
	 </td>
	
	<TD align="left" width="16%" ><b><fmt:message key="PR.MJRHEAD" bundle="${commonLables}"/></b></TD>
	<td align="left" width="34%" >
	 <c:choose>
	 <c:when test="${majorHead eq null}"> 
	 <hdiits:select style="width:140px" name="cmbMjrHead" caption="Major Head" captionid="MjrHeadNo" id="mjrHead"
		size="1" sort="false" mandatory="true">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	</hdiits:select>
	</c:when>
	<c:otherwise>
	<hdiits:select style="width:140px" name="cmbMjrHead" caption="Major Head" captionid="MjrHeadNo" id="mjrHead"
		size="1" sort="false" readonly="true">
	   <hdiits:option value="-1" selected="true"> ${majorHead} </hdiits:option>
	</hdiits:select>
	</c:otherwise>
    </c:choose>
	</td>
	</tr>
	
	<td> </td>
	
	<tr>
	<TD align="left" width="16%"><b><fmt:message key="PR.SUBMJRHEAD" bundle="${commonLables}"/></b></TD>
	<td align="left" width="34%">
	<c:choose>
	 <c:when test="${subMajorHead eq null}">
	  <hdiits:select style="width:140px" name="cmbSubMjrHead" id="cmbSubMjrHead" size="1" caption="Sub Major Head"
		 captionid="subMjrHeadNo"
		validation="sel.isrequired" mandatory="true" sort="false" onchange="GetMnrHeadsbyMonthYear()">
	    <hdiits:option value="-1"> --Select-- </hdiits:option>
	  </hdiits:select>
	 </c:when>
	 <c:otherwise>
	 <hdiits:select style="width:140px" name="cmbSubMjrHead" id="cmbSubMjrHead" size="1" caption="Sub Major Head"
		 captionid="subMjrHeadNo" sort="false" readonly="true">
	    <hdiits:option value="-1" selected="true"> ${subMajorHead} </hdiits:option>
	  </hdiits:select>
	 
	 </c:otherwise>
	</c:choose>
	  </td>
	
	<TD align="left" width="16%"><b><fmt:message key="PR.MNRHEAD" bundle="${commonLables}"/></b></TD>
	<td align="left" width="34%">
	<c:choose>
	 <c:when test="${minorHead eq null}">
	 <hdiits:select style="width:140px" name="cmbMnrHead" id="cmbMnrHead" size="1" sort="false"  caption="Minor Head" captionid="MnrHeadNo"
		mandatory="true" validation="sel.isrequired" onchange="GetSubHeadsbyMonthYear()">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	 </hdiits:select>
	 </c:when>
	 <c:otherwise>	 
	 <hdiits:select style="width:140px" name="cmbMnrHead" id="cmbMnrHead" size="1" sort="false"  caption="Minor Head" captionid="MnrHeadNo" readonly="true">
	   <hdiits:option value="-1" selected="true"> ${minorHead} </hdiits:option>
	 </hdiits:select>
	 </c:otherwise>
	</c:choose>
	 </td>
	</tr>
	
	<tr>
	<TD align="left" width="16%"><b><fmt:message key="PR.SUBHEAD" bundle="${commonLables}"/></b></TD>
	<td align="left" width="34%" > 
	<c:choose>
	 <c:when test="${subHead eq null}">
	 <hdiits:select name="cmbSubHead" id="cmbSubHead" size="1" sort="false"  caption="Sub Head" captionid="SubHeadNo" style="width:140px"
		 mandatory="true" validation="sel.isrequired" onchange="GetDtlHeadsbyMonthYear()">
	  <hdiits:option value="-1"> --Select-- </hdiits:option>
	 </hdiits:select>
	</c:when>
	<c:otherwise>
	<hdiits:select name="cmbSubHead" id="cmbSubHead" size="1" sort="false"  caption="Sub Head" captionid="SubHeadNo" style="width:140px"
		 readonly="true">
	  <hdiits:option value="-1" selected="true"> ${subHead} </hdiits:option>
	 </hdiits:select>
	</c:otherwise>
   </c:choose>
	 </td>
	
	<TD align="left" width="16%" ><b><fmt:message key="PR.DTLHEAD" bundle="${commonLables}"/></b></TD>
	<td align="left" width="34%" >
	<c:choose>
	 <c:when test="${subHead eq null}">
	 <hdiits:select name="cmbDtlHead" id="cmbDtlHead" size="1" sort="false"style="width:140px" >
	  <hdiits:option value="-1"> --Select-- </hdiits:option>
	 </hdiits:select>
	</c:when>
	<c:otherwise>
		 <hdiits:select name="cmbDtlHead" id="cmbDtlHead" size="1" style="width:140px" sort="false" readonly="true">
        <hdiits:option value="-1" selected="true"> --Select-- </hdiits:option>
	 </hdiits:select>
	</c:otherwise>
	</c:choose>
	 </td>
	</tr> 

    <tr>
   <!--  <td align='right'>
    <fmt:message key="PR.CAT" bundle="${commonLables}"/></TD>
	<td>
	<c:choose>
	 <c:when test="${subHead eq null}">
	 <hdiits:select name="cmbCat" id="cmbCat" size="1" sort="false" captionid="Category"
	 caption="Category" validation="sel.isrequired" mandatory="true"
	  onchange="getGradesFromCategory()">
	  <hdiits:option value="-1"> --Select-- </hdiits:option>
	  	  <hdiits:option value="1"> IAS/AIS </hdiits:option>
  	  	  <hdiits:option value="2"> Gazetted </hdiits:option>
  	  	  <hdiits:option value="3"> Non - Gazetted </hdiits:option>
   	  	  <hdiits:option value="4"> Custom </hdiits:option>
	 </hdiits:select>
	 </c:when>
	 <c:otherwise>	 
	 <hdiits:select name="cmbCat" id="cmbCat" size="1" sort="false" captionid="Category"
	 caption="Category" readonly="true">
	 <hdiits:option value="-1" selected="true"> --Select-- </hdiits:option>
	  </hdiits:select>
	 </c:otherwise>
	 </c:choose>
    </td>  -->
    
    <!-- <td colspan="2">
	    <hdiits:select name="cmbSelGrade" id="cmbSelGrade" size="4" sort="false" style="display:none">  
	    </hdiits:select>
	    </br>
	    <hdiits:button name="btnAdd" type="button" caption=" Add "  captionid="Add" id="btnAdd" style="display:none" onclick="addGrades()"/>
    </td> -->
    </tr>
    <c:choose>
	 <c:when test="${subHead eq null}">  
    <tr>
    <td align="left" width="16%"><b><fmt:message key="eis.grade_lst" bundle="${commonLables}"/></b></td>
    <td align="left" width="34%">
	    <hdiits:select name="cmbGrade" id="cmbGrade" size="4" sort="false" style="width:140px;"><!-- display:none -->  
    	</hdiits:select>
    </td>
   <!--  <td>
    	<hdiits:button name="btnRemove" type="button" caption=" Remove "  captionid="Remove" id="btnRemove" onclick="removeGrades()"/>
    </td>  -->
    
    <!-- added by Ankit bhatt for Disabling the Designation Combo at View Time -->
	          
    <!-- <tr>
    
	    <td align='right'><fmt:message key="eis.desig_dtl" bundle="${commonLables}"/><br/></td>
	    <td>
		    <hdiits:select name="cmbDesignations" id="cmbDesignations" size="1" sort="false" style="width:140px">  
			</hdiits:select>
	    <hdiits:button name="btnAddDesig" type="button" id="btnRemoveDesig" caption="Use Designation" onclick="addDesignation()"/>
	    
    </tr>  -->
    

    
    	<!-- Added By Paurav for adding Designations Combo --> 

	    <td align="left" width="16%"><b><fmt:message key="eis.dsgn_lst" bundle="${commonLables}"/></b></td>
	    <td align="left" width="34%">
	    <hdiits:select name="cmbSelDesignations" id="cmbSelDesignations" size="4" sort="false" style="width:140px">  
	
	    </hdiits:select>
	    <br/>
	    <!--<hdiits:button name="btnRemoveDesig" type="button" id="btnRemoveDesig" caption=" Ignore " onclick="removeDesignation()"/>-->
	    
	    </td>
	    <!-- Ended By Paurav -->
    </tr>
    </c:when>
    <c:otherwise>
    
    </c:otherwise>
    </c:choose>
 
		<c:if test="${demandCode eq null}">	
		<tr>
			<td  align="left" width="16%"  ><b>Bill Desc<b></td>
			<td align="left" width="34%" ><hdiits:text style="width:140px"  id = "ctrlNo" name="ctrlNo" caption="Bill No."  maxlength="20" size="20" mandatory="true" validation="txt.isrequired" readonly="true"/></td>

			<td  align="left" width="16%"  ><b>Arrear Type<b></td>
			<td align="left" width="34%" >
			 <hdiits:select name="arrearType" size="1" sort="false" style="width:140px" caption="Arrear Type">
			   <hdiits:option value=""> --Select-- </hdiits:option>
			     <c:forEach items="${arrearList}" var="arrearList">			      
												<option value='<c:out value="${arrearList.salRevId}"/>'>
												<c:out value="${arrearList.rltBillTypeEdp.edpShortName}"/>
												<fmt:formatDate var="orderDate" value="${arrearList.revOrderDate}" pattern="dd/MM/yyyy" dateStyle="medium"  />	
												(<c:out value="${orderDate}"/>)</option>
			     </c:forEach>
			   </hdiits:select>
		</tr> 
	</c:if>   
  
	</table>

     <input type="hidden" name="txtGradeId" id="txtGradeId" maxlength="100"/>
     
     
     <hdiits:hidden  name="gradeId1" id="gradeId1" default="" />
	
  	 <!-- Added By Paurav for generating paybill designation wise -->
     <hdiits:hidden name="designations" id="designations" default="" />
	 <!-- Ended By Paurav -->
  	
	<script type="text/javascript">
		
		</script>
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>





</html>
	