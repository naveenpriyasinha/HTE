

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
<script type="text/javascript" src="script/hod/ps/common.js"></script>
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

	<!--<c:set var="flag" value="${resultObj.resultValue.flag}" ></c:set>-->
<script><!--


function GetMonths()
{

		//alert( 'Villages Funct called..');

		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&year_id='+ document.frmPaybillPara.cmbYear.value;
		  var actionf="getMonths";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
        //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_Year;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_Year()
	{
		if (xmlHttp.readyState==complete_state)
		{ 		
		    clearMonthCombo();
  	        
			var month = document.getElementById("cmbMonth");
			//alert(month.value);
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var monthEntries = XMLDoc.getElementsByTagName('month-mapping');
                        //alert('month entry size is ' + monthEntries.length);
	           			for ( var i = 0 ; i < monthEntries.length ; i++ )
	     				{
	     				    val=monthEntries[i].childNodes[0].text || monthEntries[i].childNodes[0].textContent;    
	     				    text = monthEntries[i].childNodes[1].text || monthEntries[i].childNodes[1].textContent; 
//	     				    alert('Month val is:' + val + 'and text is:-' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               month.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    month.add(y); 
	   						 }
	   		          }
	           			document.getElementById("cmbMonth").value= y.value;
	   		         }
 	   }
 }

GetMonths();






var lookupArray = new Array();
function setBudgetType(){
	alert('old value ' + document.getElementById("cmbBudTypeTemp").value);
	document.getElementById("cmbBudType").value = document.getElementById("cmbBudTypeTemp").value
	alert('new value ' + document.getElementById("cmbBudTypeTemp").value);

}

//alert("cuuent month :: "+'${curMonth}');

GetFromMonths();
GetMonths();

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
	  //alert('Value of paybill is'+document.getElementById("billTypeCmb").value);
	  if(document.getElementById("billTypeCmb").value!="arrears" )
	  {	  	  
	      if(document.getElementById("billTypeCmb").value!="-1" )
		  {
	  
		  var url; 
		  var uri='';
		  url= uri+'&subHeadCode='+ document.forms[0].billNo.value+'&month='+document.forms[0].cmbMonth.value+'&year='
		  +document.forms[0].cmbYear.value+'&billTypeLookupId='+document.forms[0].billTypeCmb.value;
		  var actionf="checkPriviewPaybillStatus"
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
							if(namesEntries[0].childNodes[0].text=='false')
							{
								//added by ravysh to check number of supplimentary bills
								
								if(namesEntries[0].childNodes[1].text=='2500341')	
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
								//alert("Before the line 238");
								alert('Paybill already Generated');
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
			alert('Caught Exception: ' + e.description);
		}
	
}

function getAllData()
{ 

	//alert("getAllData");

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
		  url= uri+'&subHeadCode='+ document.forms[0].billNo.value+'&month='+document.forms[0].cmbMonth.value+'&year='
		  +document.forms[0].cmbYear.value;

		  //alert("url"+url);

		  
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
    document.getElementById('schemeName').options[0].text = 'Select';
    document.getElementById('subSchemeName').options[0].text = 'Select';
    
    document.getElementById('cmbDemand').options[0].value = '-1';
	document.getElementById('mjrHead').options[0].value = '-1';
    document.getElementById('cmbSubMjrHead').options[0].value = '-1';
    document.getElementById('cmbMnrHead').options[0].value = '-1';
    document.getElementById('cmbSubHead').options[0].value = '-1';
    document.getElementById('schemeName').options[0].value = '-1';
    document.getElementById('subSchemeName').options[0].value = '-1';
    
    document.getElementById('ctrlNo').value = "";
    document.getElementById('gradeId1').value = "";
    document.getElementById('designations').value = "";
    
}

function fillAllCombo()
{


if (xmlHttp.readyState==complete_state)
 { 						
			//alert("fillAllCombo");
					var XMLDoc=xmlHttp.responseXML.documentElement;	
//alert(xmlHttp.responseText);
				//	 var isGenerated = XMLDoc.getElementsByTagName('isGenerated');
					  var namesEntries = XMLDoc.getElementsByTagName('subHeadCode');
					  var approveFlagParent = XMLDoc.getElementsByTagName('approveFlagParent');
					//  alert(namesEntries.length);

					// var flag = isGenerated[0].childNodes[0].text;
//									 
					if(namesEntries.length>0)
					
					{
						 document.frmPaybillPara.formSubmitButton.disabled=false; 
						 document.frmPaybillPara.Back.disabled=false; 
						 document.frmPaybillPara.Reset.disabled=false; 
						 
		                    document.getElementById('cmbDemand').options[0].text = namesEntries[0].childNodes[1].firstChild.nodeValue; 
		                    document.getElementById('mjrHead').options[0].text = namesEntries[0].childNodes[2].firstChild.nodeValue; 
		                    document.getElementById('cmbSubMjrHead').options[0].text = namesEntries[0].childNodes[3].firstChild.nodeValue;
		                    document.getElementById('cmbMnrHead').options[0].text = namesEntries[0].childNodes[4].firstChild.nodeValue;
		                    document.getElementById('cmbSubHead').options[0].text = namesEntries[0].childNodes[0].firstChild.nodeValue; 
		                    document.getElementById('schemeName').options[0].text =namesEntries[0].childNodes[9].firstChild.nodeValue;
		                    document.getElementById('subSchemeName').options[0].text =namesEntries[0].childNodes[14].firstChild.nodeValue;
		                    
		                    document.getElementById('cmbDemand').options[0].value = namesEntries[0].childNodes[1].firstChild.nodeValue;
		 					document.getElementById('mjrHead').options[0].value = namesEntries[0].childNodes[2].firstChild.nodeValue;
		                    document.getElementById('cmbSubMjrHead').options[0].value = namesEntries[0].childNodes[3].firstChild.nodeValue;
		                    document.getElementById('cmbMnrHead').options[0].value = namesEntries[0].childNodes[4].firstChild.nodeValue;
		                    document.getElementById('cmbSubHead').options[0].value = namesEntries[0].childNodes[0].firstChild.nodeValue;
		                    document.getElementById('schemeName').options[0].value = namesEntries[0].childNodes[9].firstChild.nodeValue;
		                    document.getElementById('subSchemeName').options[0].value = namesEntries[0].childNodes[14].firstChild.nodeValue;
		                                       
		                   // alert(namesEntries[0].childNodes[5].firstChild.nodeValue);
		                    var abc = namesEntries[0].childNodes[5].firstChild.nodeValue;
		   	                var clas = namesEntries[0].childNodes[6].firstChild.nodeValue;
		   	                var dsgnName = namesEntries[0].childNodes[7].firstChild.nodeValue;
		   	                 
		   	             	
		   	                dsgnName=dsgnName.replace("[",'');
		   	                dsgnName=dsgnName.replace("]",'');
		   	                
		   	               var clasName = namesEntries[0].childNodes[8].firstChild.nodeValue;
		   	               clasName=clasName.replace("[",'');
		   	               clasName=clasName.replace("]",'');   	                

			   	            var dsgndata = abc.split(",");
		                    var desigShrtName = dsgnName.split(",");

		                    var post = document.getElementById("cmbSelDesignations");

		                   // alert('post'+post);
		                    
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
			                
		                   // alert('Here1');

		                   var clsData = clas.split(",");
		                    var clasShrtName = clasName.split(",");
		                    
			                var classData = document.getElementById('cmbGrade');
			                
			                //alert('Here2');
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
		                    
		                   //alert('MjHd' +namesEntries[0].childNodes[2].firstChild.nodeValue);
		                    document.getElementById("Demand").value = namesEntries[0].childNodes[1].firstChild.nodeValue;
		                    document.getElementById("SubMjHd").value = namesEntries[0].childNodes[3].firstChild.nodeValue;
		                    document.getElementById("MjHd").value = namesEntries[0].childNodes[2].firstChild.nodeValue;
		                    document.getElementById('MnrHd').value = namesEntries[0].childNodes[4].firstChild.nodeValue;
		                    document.getElementById('SubHd').value= namesEntries[0].childNodes[0].firstChild.nodeValue;
		                   	document.getElementById("DtlHd").value='00'; 
		                   	
		                   	
		                   	addGradesForBill();
		                   	                   	//alert("hello 2");
		                   	                   	
							addDesignationForBill(); 
							                   //	alert("hello3");
							fillBillDesc();                 
							                   	//alert("hello 4");
		                    loadData();
		                                       	//alert("hello 5");
		                  updateBillNo();  
		                 
						 document.frmPaybillPara.formSubmitButton.disabled=false; 
					}
					else if(approveFlagParent.length > 0){
						document.frmPaybillPara.formSubmitButton.disabled=true; 

						var message = approveFlagParent[0].childNodes[0].firstChild.nodeValue;
						alert(message);
						document.getElementById("billNo").focus();
						document.getElementById("billNo").selectedIndex = 0;

					}
					else
					{
						//alert("Before the line 444");
						alert('Paybill Already Generated');
						document.getElementById("billNo").focus();
						document.getElementById("billNo").selectedIndex = 0;

						document.frmPaybillPara.formSubmitButton.disabled=true; 		
					}
						 
					

				
  }
 
}
function updateBillType(){
	alert("hello");
	document.getElementById("billNo").focus();
	document.getElementById("billNo").selectedIndex = 0;
	document.getElementById("billNo").selectedIndex = 0;
	alert("hhh");
}
function updateBillNo()
{
	//alert('updateBillNo');
	//alert(document.getElementById("selFromMonth").value);
	
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
	if(document.getElement("billNo").value=="-1")
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
		//alert(document.getElementById("ctrlNo").value);
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

<script>
function checkNoOfEmp(){
	 
	var billNo=document.getElementById("billNo").value;

	
	
	var url="";
	 
	var uri = "ifms.htm?actionFlag=checkEmpCount&billNo="+billNo;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	checkEmpCount(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function checkEmpCount(myAjax){
	 
	var XMLDoc = myAjax.responseXML.documentElement;
	var item = XMLDoc.getElementsByTagName('value');
	var count = item[0].firstChild.nodeValue;
	 


	 
	if(count=="NA"){
		alert('Please update the bank branch details of the DDO to generate the paybill.');
		clearAllData();
		document.getElementById("billNo").value='-1';
		}
	else if (count=='0'){
	alert('No employee found in the selected bill group.');
	clearAllData();
	document.getElementById("billNo").value='-1';
	}
	else{

		}
	
}

</script>


<script>

function checkHeadOfAccountCode()
{
	alert("hiii");
var billNo=document.getElementById("billNo").value;
alert("billNo"+billNo);
	
	
	var url="";
	 
	var uri = "ifms.htm?actionFlag=checkHeadCode&billNo="+billNo;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	checkUpdateHead(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	
	
	
	}



	function checkUpdateHead(myAjax){
		 alert("inside checkUpdateHead");
		var XMLDoc = myAjax.responseXML.documentElement;
		var item = XMLDoc.getElementsByTagName('value');
		var count = item[0].firstChild.nodeValue;
		 


		 
		if(count=="NA"){
			alert('Please update the head of account codes of employees to generate the paybill.');
			clearAllData();
			document.getElementById("billNo").value='-1';
			}
		else if (count=='0'){
		alert('No employee found in the selected bill group.');
		clearAllData();
		document.getElementById("billNo").value='-1';
		}
		else{

			}
		
	}



</script>
<style>/*  added by Pratik 07-08-23  */
.genPaybillTb {
	table-layout: fixed;
}

.genPaybillTb * {
	width: 200px !important;
}
</style>



<br> <br> <br>
	<table align="center" width="80%"  border="0" cellspacing="10" class="genPaybillTb" > <!-- add class by Pratik 07-08-23 -->
	<font color="red"><b><center>Note: Please Update the Head of Account Codes of all GPF Employees before generating Paybill</center></b></font>
	<br/>
	<font color="red"><b><center>NOTE : If you don't need NPS employer contribution in paybill then make this value as '0' in DCPS Delayed, DCPS DA Arrear and DCPS PAY Arrear.</center></b></font>
	<tr>	
	 <!-- added by ravysh for multiple month supplementary bill -->
 
  	<TD align="left" width="12%"  id="FromYear1" style="display: none;"><b>From Year</b></TD>
   <td align="left" width="60%" style="display: none" id="FromYear2">
	<c:choose>
	<c:when test="${demandCode eq null}">	
    <hdiits:select name="selFromYear" size="1" sort="false" id="cmbFromYear" onchange="GetFromMonths()" default="${curYear}"> 
	   	<hdiits:option value="-1"> --Select-- </hdiits:option>
	 	<c:forEach items="${yearList}" var="FromyearList">
 	     <hdiits:option value="${FromyearList.lookupShortName}"> ${FromyearList.lookupDesc} </hdiits:option>
	    </c:forEach>
	  
	   </hdiits:select>
	  </c:when>
	  <c:otherwise>
	      <hdiits:select name="selFromYear" size="1" sort="false" id="cmbFromYear" readonly="true" default="${curYear}"> 
	      <hdiits:option value="-1" selected="true"> ${year} </hdiits:option>
	      </hdiits:select>
	  </c:otherwise>
	 </c:choose>
	</td>
	<TD  align="left" width="12%"   style="display: none" id="FromMonth1"><b>From Month</b></TD>
	<td  align="left" width="60%" style="display: none" id="FromMonth2">
    <c:choose>
	<c:when test="${demandCode eq null}">	
	 <select  name="selFromMonth" id="selFromMonth" size="1" sort="false" id="cmbFromMonth" default="${curMonth}">
	 <option value="-1"> --Select-- </option>  	  
	</select>
    </c:when>
    <c:otherwise>
    	 <select name="selFromMonth" id="selFromMonth" size="1" sort="false" id="cmbFromMonth" readonly="true" onchange="fillBillDesc();updateBillNo();updateBillType();" default="${curMonth}">
    	 <option value="-1" selected="true"> ${month} </option>
    	 </select>
    </c:otherwise>
   </c:choose>
	</td>
	<!-- ended by ravysh -->
	
	<td  align="left" width="12%"  id="ToYear1"> 
		<b><fmt:message key="PR.YEAR" bundle="${commonLables}" /></b>				
     </TD>
      <TD  align="left" width="60%"   id="ToYear2" style="display: none;"><b>To Year</b></TD>
	<td>
	<c:choose>
	<c:when test="${demandCode eq null}">	
    <hdiits:select name="selYear" size="1" sort="false" id="cmbYear" onchange="GetMonths()" default="${curYear}" >
	   	<hdiits:option value="-1"> --Select-- </hdiits:option>
	 	<c:forEach items="${yearList}" var="yearList">
 	     <hdiits:option value="${yearList.lookupShortName}"> ${yearList.lookupDesc} </hdiits:option>
	    </c:forEach>
	  
	      <!--<hdiits:option value="${curYear}" >${curYear} </hdiits:option> -->
	   </hdiits:select>
	  </c:when>
	  <c:otherwise>
	      <hdiits:select name="selYear" size="1" sort="false" id="cmbYear" readonly="true" default="${curYear}"> 
	      <hdiits:option value="-1" selected="true"> ${year} </hdiits:option>
	      </hdiits:select>
	  </c:otherwise>
	 </c:choose>
	</td>
	<TD align="left" width="12%"   id="ToMonth1"><b><fmt:message key="PR.MONTH" bundle="${commonLables}"/></b></TD>
	 <TD  align="left" width="60%"   id="ToMonth2" style="display: none;"><b>To Month</b></TD>
	<td>
    <c:choose>
	<c:when test="${demandCode eq null}">	
	 <hdiits:select onchange="fillBillDesc();" name="selMonth" size="1" sort="false" id="cmbMonth" default="${curMonth}">
	 <hdiits:option value="-1"> --Select-- </hdiits:option>  	  
	</hdiits:select>
    </c:when>
    <c:otherwise>
    	 <hdiits:select name="selMonth" size="1" sort="false" id="cmbMonth" readonly="true" onchange="fillBillDesc();updateBillNo();" default="${curMonth}">
    	 <hdiits:option value="-1" selected="true"> ${month} </hdiits:option>
    	 </hdiits:select>
    </c:otherwise>
   </c:choose>
	
	</td>
  
	</tr>
	<tr  style="display: none"  >
	<TD  align="left" width="12%" ><b><fmt:message key="PR.DEPT" bundle="${commonLables}"/></b></TD>
	<td align="left" width="60%">
	<c:choose>
	<c:when test="${demandCode eq null}">	
	 <hdiits:select name="cmbDept" size="1" sort="false"  caption="Department" captionid="Dept"
		onchange="GetDemandNobyMonthYear()">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	     <c:forEach items="${deptList}" var="deptList">
	      <%--<hdiits:option value="${deptList.locationCode}"> ${deptList.locName} </hdiits:option>--%>
	      
								<c:choose>
									<c:when test="${deptList.locId==locationid}">
										<option value='<c:out value="${deptList.locationCode}"/>' selected="true">
										<c:out value="${deptList.locName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${deptList.locationCode}"/>'>
										<c:out value="${deptList.locName}"/></option>
									</c:otherwise>
								</c:choose>								
	      
	     </c:forEach>
	   </hdiits:select>
	  </c:when>
	  <c:otherwise>
	  <!-- modified by ravysh -->
	  <hdiits:select name="cmbDept" size="1" sort="false" caption="Department" captionid="Dept" readonly="true">	  
		<c:forEach items="${deptList}" var="deptList">
		<c:choose>
		<c:when test="${deptList.locId==locationid}">
										<option value="-1" selected="true">
										<c:out value="${deptList.locName}"/></option>
									</c:when>
  </c:choose>
  </c:forEach>
		<!--
		<c:choose>
		<c:when test="${locationid eq 300022 }">
		 <hdiits:option value="-1" selected="true"> General Administration Department </hdiits:option>
		</c:when>
		<c:when test="${locationid eq 300023 }">
		 <hdiits:option value="-1" selected="true"> Finance Department </hdiits:option>
		</c:when>
		</c:choose>-->

	 
	  </hdiits:select>
	  <!-- modification ends -->
	  </c:otherwise>
	 </c:choose>
	</td>
	
	</tr>
	
	<c:choose>
	<c:when test="${demandCode eq null}">
	<tr>
	<TD   align="left" width="16%"  ><b><fmt:message key="PR.BILLNO" bundle="${commonLables}"/></b></TD>
	<td  align="left" width="34%" >
	<select style="width:450px" name="billNo" size="1" id="billNo" sort="false"  caption="BillNo" captionid="billNo"
		validation="sel.isrequired" mandatory="true" onchange="checkNoOfEmp();clearAllData();getAllData();chckemployeeforbillgrp();updateBillNo();checkHeadOfAccountCode();" >
	   <option value="-1">-------------------------------Select--------------------------</option>
	    <c:forEach var="billList" items="${billList}">
         <option value="${billList.billHeadId}" title="${billList.billId}"><c:out value="${billList.billId}"> </c:out></option>
         </c:forEach>
	   </select>
	   	   </td>
	   	   <td></td>
	   	   </tr>
	   	   <tr>
	   <td  align="left" width="16%" >
		<b>Bill Type</b> 
		</td>
		<td  align="left" width="34%" >
			<hdiits:select name="billTypeCmb" style="width:140px" mandatory="true" default="paybill">
				<hdiits:option value="-1" >Select</hdiits:option>
				<hdiits:option value="paybill" selected="true">Paybill</hdiits:option> 
				<hdiits:option value="paybill">Supplimentary Paybill</hdiits:option>	
								
			</hdiits:select>
		</td></tr></c:when></c:choose>
	
	<tr>
	
	<tr>
	<!-- Added by ankit -->
	   	   
	   	   <TD   align="left" width="16%"  ><b>Scheme Name</b></TD>
	<td  align="left" width="34%" >
	<hdiits:select style="width:450px" name="schemeName" size="1" id="schemeName" sort="false" mandatory="" caption="SchemeName" captionid="SchemeName" readonly="true">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>	    
	   </hdiits:select>
	   	   </td>
	   	   
	   	   <!--  Ended -->
				
	<!-- Added by saurabh -->
	   	   
	   	   <TD   align="left" width="16%"  ><b>Sub Scheme Name</b></TD>
	<td  align="left" width="20%" >
	<hdiits:select style="width:350px" name="subSchemeName" size="1" id="subSchemeName" sort="false" mandatory="" caption="SubSchemeName" captionid="SubSchemeName" readonly="true">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>	    
	   </hdiits:select>
	   	   </td>
	   	   
	   	   <!--  Ended -->   
	
	
				
	
	</tr>
	<tr>
	<TD  align="left" width="16%"  ><b><fmt:message key="PR.DEMAND" bundle="${commonLables}"/></b></TD>
	<td  align="left" width="34%"  >
	<c:choose>
	<c:when test="${demandCode eq null}">	 
	 <hdiits:select style="width:140px" name="cmbDemand" id="cmbDemand" size="1" mandatory="true" caption="Demand No" captionid="DmdNo"
		validation="sel.isrequired" sort="false" readonly="true">
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
		size="1" sort="false" mandatory="true" readonly="true">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	</hdiits:select>
	</c:when>
	<c:otherwise>
	<hdiits:select style="width:140px" name="cmbMjrHead" caption="Major Head" captionid="MjrHeadNo" id="mjrHead"
		size="1" sort="false" readonly="true" >
	   <hdiits:option value="-1" selected="true"> ${majorHead} </hdiits:option>
	</hdiits:select>
	</c:otherwise>
    </c:choose>
	</td>
	</tr>
	<tr>
	<td align="left" width="16%"><b><fmt:message key="PR.SUBMJRHEAD" bundle="${commonLables}"/></b></TD>
	<td align="left" width="34%">
	<c:choose>
	 <c:when test="${subMajorHead eq null}">
	  <hdiits:select style="width:140px" name="cmbSubMjrHead" id="cmbSubMjrHead" size="1" caption="Sub Major Head"
		 captionid="subMjrHeadNo"
		validation="sel.isrequired" mandatory="true" sort="false" onchange="GetMnrHeadsbyMonthYear()" readonly="true">
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
		mandatory="true" validation="sel.isrequired" onchange="GetSubHeadsbyMonthYear()" readonly="true">
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
		 mandatory="true" validation="sel.isrequired" onchange="GetDtlHeadsbyMonthYear()" readonly="true">
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
	 <hdiits:select name="cmbDtlHead" id="cmbDtlHead" size="1" sort="false" style="width:140px" readonly="true">
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
    <td align="left" width="16%" style="display:none"><b><fmt:message key="eis.grade_lst" bundle="${commonLables}"/></b></td>
    <td align="left" width="34%"  style="display:none">
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

	    <td align="left" width="16%"  style="display:none"><b><fmt:message key="eis.dsgn_lst" bundle="${commonLables}"/></b></td>
	    <td align="left" width="34%"  style="display:none">
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
			<td align="left" width="34%" ><hdiits:text  style="width:450px"  id = "ctrlNo" name="ctrlNo" caption="Bill No."  maxlength="20" size="20" mandatory="true" validation="txt.isrequired" readonly="true"/></td>

			<td  align="left" width="16%"   style="display:none"><b>Arrear Type<b></td>
			<td align="left" width="34%"  style="display:none">
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
	<table>
		<tr>
		 <td>
     		<input type="hidden" name="txtGradeId" id="txtGradeId" maxlength="100"/>
     			<input type="hidden" id="flag" name="flag" value="${resultObj.resultValue.flag}">
     	
     	</td>
       </tr>
     </table>
     
     
     <hdiits:hidden  name="gradeId1" id="gradeId1" default="" />
	
  	 <!-- Added By Paurav for generating paybill designation wise -->
     <hdiits:hidden name="designations" id="designations" default="" />
	 <!-- Ended By Paurav -->
  	
	<script type="text/javascript">
	GetFromMonths();
	GetMonths();
	
		</script>
		
		
		
		<script><!--
function checkNoOfEmp(){
	//alert('hiii');
	var billNo=document.getElementById("billNo").value;


	
	
	var url="";
	
	
	var uri = "ifms.htm?actionFlag=checkEmpCount&billNo="+billNo;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters:url,
		        onSuccess: function (myAjax) {
		        	checkEmpCount(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function checkEmpCount(myAjax){
	 
	var XMLDoc = myAjax.responseXML.documentElement;
	var item = XMLDoc.getElementsByTagName('value');
	var count = item[0].firstChild.nodeValue;
	//alert("count="+count);
	 
	 
	if(count=="NA"){
		alert('Please update the bank branch details of the DDO to generate the paybill.');
		clearAllData();
		document.getElementById("billNo").value='-1';
		}
	else if (count=='0'){
	alert('No employee found in the selected bill group.');
	clearAllData();
	document.getElementById("billNo").value='-1';
	}
	else{

		}
	//checkDdoCodeForVocationl();
}
//function checkDdoCodeForVocationl()
//{
	//alert("checkNoOfEmp");
	//var flag=document.getElementById("flag").value;
	//if(flag==19)
	//{
	//alert("As per instruction given by department you Can not  generate Paybill for Vocational Admin office");
	//clearAllData();
	//document.getElementById("billNo").value='-1';

	//}
	
}









--></script>
		
		
<script type="text/javascript">
function chckemployeeforbillgrp()
{
	var BillNO = document.getElementById("billNo").value;
	var BillMonth=document.getElementById("cmbMonth").value;
	var BillYear=document.getElementById("cmbYear").value;
	if (BillNO!=''||BillNO!='-1')
	{
		//alert('BillNO: '+BillNO);
		//var uri = "ifms.htm?actionFlag=getalreadybill&billid="+BillNO;
		//var url = "";
		var uri = 'ifms.htm?actionFlag=getalreadybill';
		var url = '&billid='+BillNO+'&billmonth='+BillMonth+'&billyear='+BillYear;
		if(BillNO.length > 0){
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function (myAjax) {
								finalcheckforemp(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...');} 
				          } );
			}
	}
}


function finalcheckforemp(myAjax)
{
	var count;
	//XMLDoc = myAjax.responseXML.documentElement;
	//XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	//count= XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var BillNO = document.getElementById("billNo").value;
	var XMLDoc = myAjax.responseXML.documentElement;
	var item = XMLDoc.getElementsByTagName('value');
	count = item[0].firstChild.nodeValue;

	if (count>0)
	{
		alert("Some of the employee's PayBill have already been generated. Please detach and try again");
		//document.getElementById("billNo").value="-1";
		//document.getElementById("hiddenbillno").value="billNO";
		clearAllData();
		document.getElementById("billNo").value='-1';
	}
}	

</script>
		
		
		
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>





</html>
	