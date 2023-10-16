<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
 <c:set var="empList" value="${resValue.empList}" > </c:set> 
<c:set var="financialYearList" value="${resValue.financialYearList}" ></c:set>
<c:set var="cmnLocVoList" value="${resValue.cmnLocVoList}" ></c:set>
<c:set var="loginLocId" value = "${resValue.baseLoginMap.locationId}" />  
<c:set var="orgGradeList" value = "${resValue.orgGradeList}" />  
<script><!--
function findParaData(YearSelect)
{
	var year=YearSelect.value;
	var deptId=document.getElementById("selDept").value;
	
	
	if(deptId!=-1)
	{
		xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&deptId='+ deptId+"&year="+year;
		  var actionf="chkForm16Data";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
  			//alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=form16_data;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
	}
	else
	{
		alert("Please Select The Department First.");
		YearSelect[0].selected=true;
		document.getElementById("selDept").focus();
	}
}
function form16_data()
{
if (xmlHttp.readyState==complete_state)
 { 						
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					//alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[2].text);
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
               			var namesEntries = XMLDoc.getElementsByTagName('form16-header');
	           			
	           			var isInserted='n';
     				    
	           			for ( var i = 0 ; i < namesEntries.length ; i++ )
	     				{
	     				    var TanNo=namesEntries[i].childNodes[0].text;
	     				    var ITO=namesEntries[i].childNodes[1].text;
	     				    var Q1=namesEntries[i].childNodes[2].text;
	     				    var Q2=namesEntries[i].childNodes[3].text;
	     				    var Q3=namesEntries[i].childNodes[4].text;
	     				    var Q4=namesEntries[i].childNodes[5].text;
	     				    var ddoId = namesEntries[i].childNodes[6].text;
	     				    isInserted=namesEntries[i].childNodes[8].text;
	     				    var headerId=namesEntries[i].childNodes[7].text;
	     				    
	     				    document.getElementById("txtTan").value=TanNo;
	     				    document.getElementById("txtITO").value=ITO;
   	     				    document.getElementById("txtquarter1").value=Q1;	     				    
	     				    document.getElementById("txtquarter2").value=Q2;
	     				    document.getElementById("txtquarter3").value=Q3;
	     				    document.getElementById("txtquarter4").value=Q4;
	     				    document.getElementById("isInserted").value=isInserted;
	     				    document.getElementById("headerId").value=headerId;
	     		         }
	     		         var length=document.getElementById("cmbDdoNames").length;
							var obj = document.getElementById("cmbDdoNames");
							var selectVal=0;
							for(var i =0; i<length;i++)
							{
								var value=obj[i].value;
								if(value==ddoId)
								{
									selectVal=i;
								}
								
							}
							obj[selectVal].selected= true;
							
	     		         if(isInserted=='y' || isInserted=='Y')
						 {
						 	var res = confirm("The Information is already entered. \nWant to Update it.");
						 	if(res)
						 	{	
						 		document.getElementById("isUpdate").value='y';
						 	}
						 	else
						 		document.getElementById("isUpdate").value='n';	
						 }
						 else
						 {
						 	document.getElementById("isUpdate").value='n';	
						 }
						 
						 
	   		         }
  }
}

function findEmployee(department)
{
	var deptId = department.value;
	xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&deptId='+ deptId;
		  var actionf="findEmpNamePayslip";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
  			//alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=EmpName;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
}

function EmpName()
{

if (xmlHttp.readyState==complete_state)
 { 						
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var village = document.getElementById("cmbEmpNames");
					var ddo = document.getElementById("cmbDdoNames");		
                 
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
               			clearEmployeeCombo();
               			
                        var namesEntries = XMLDoc.getElementsByTagName('emp-mapping');
                        //alert('Length ' + namesEntries.length);
	           			for ( var i = 0 ; i < namesEntries.length ; i++ )
	     				{
	     				    val=namesEntries[i].childNodes[0].text;    
	     				    text = namesEntries[i].childNodes[1].text + ' ' + namesEntries[i].childNodes[2].text + ' ' + namesEntries[i].childNodes[3].text;  
	//     				    alert('Village val is:' + val + 'and text is:-' + text);
	     				    var y = document.createElement('option') 
	     				    y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               village.add(y,null);
                               
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    village.add(y); 
	   			 	  		 }
	   		          }
	   		          
	   		          var namesEntries = XMLDoc.getElementsByTagName('ddo-mapping');
	   		         
	   		          for ( var i = 0 ; i < namesEntries.length ; i++ )
	     				{
	     				    val=namesEntries[i].childNodes[0].text;    
	     				    text = namesEntries[i].childNodes[1].text + ' ' + namesEntries[i].childNodes[2].text + ' ' + namesEntries[i].childNodes[3].text+ ' ' + namesEntries[i].childNodes[4].text ;  
	//     				    alert('Village val is:' + val + 'and text is:-' + text);
	     				    var y = document.createElement('option') 
	     				    y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               ddo.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		   ddo.add(y);
	   						 }
	   		          }
	   		          
	   		         }
  }
}


function getEmpFirstNames()
{
  var paybillGenChk="";
  if(document.getElementById("paybillGenChk").checked)
  {
 	paybillGenChk="YES";
  }
  var empname =document.getElementById("txtEmpFirstName").value;
  var deptId =document.getElementById("selDept").value;
  var grade = document.getElementById("cmbClassName").value;
  if(deptId=='')
  {
  	alert("select the department first");
  	document.frm16Para.selDept.focus();
  }
  else
  {
  //alert('Name is ' + empname);
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&emp_first_name='+ empname+'&deptId='+ deptId+'&gradeId='+grade;
		  var actionf="findEmpNamePayslip";
		  uri='./hrms.htm?actionFlag='+actionf+"&paybillGenChk="+paybillGenChk;
		  url=uri+url; 
          xmlHttp.onreadystatechange=find_EmpName;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);	
	}
}

function find_EmpName()
{

if (xmlHttp.readyState==complete_state)
 { 						
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var village = document.getElementById("cmbEmpNames");		
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
               			clearEmployeeCombo();
               			
                        var namesEntries = XMLDoc.getElementsByTagName('emp-mapping');
                        //alert('Length ' + namesEntries.length);
	           			for ( var i = 0 ; i < namesEntries.length ; i++ )
	     				{
	     				    val=namesEntries[i].childNodes[0].text;    
	     				    text = namesEntries[i].childNodes[1].text + ' ' + namesEntries[i].childNodes[2].text + ' ' + namesEntries[i].childNodes[3].text;  
	//     				    alert('Village val is:' + val + 'and text is:-' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               village.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    village.add(y); 
	   						 }
	   		          }
	   		          var ddo = document.getElementById("cmbDdoNames");	
	   		          var namesEntries = XMLDoc.getElementsByTagName('ddo-mapping');
	   		          for ( var i = 0 ; i < namesEntries.length ; i++ )
	     				{
	     				    val=namesEntries[i].childNodes[0].text;    
	     				    text = namesEntries[i].childNodes[1].text + ' ' + namesEntries[i].childNodes[2].text + ' ' + namesEntries[i].childNodes[3].text+ ' ' + namesEntries[i].childNodes[4].text ;  
	     				   // alert('ddo val is:' + val + 'and text is:-' + text);
	     				    var y = document.createElement('option') 
	     				    y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               ddo.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		   ddo.add(y);
	   						 }
	   		          }
	   		         }
  }
}

function clearEmployeeCombo()
{
	var v=document.getElementById("cmbEmpNames").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbEmpNames").options.length -1;
			document.getElementById("cmbEmpNames").options[lgth] = null;
	}
	var v=document.getElementById("cmbDdoNames").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbDdoNames").options.length -1;
			document.getElementById("cmbDdoNames").options[lgth] = null;
	}
		
}

function setEmpId()
{
 
 	document.frm16Para.txtEmpId.value = document.frm16Para.cmbEmpNames.value;
 	
}

function openForm16()
{

 if(document.frm16Para.selDept.value=='-1')
 {
 	alert("Select The Department First");
 	document.frm16Para.selDept.focus();
 	return false;
 }
 //if(document.frm16Para.cmbEmpNames.value=='-1' || document.frm16Para.cmbEmpNames.value=="")
 //{
 //	alert("Select The Employee First");
 //	document.frm16Para.cmbEmpNames.focus();
 	//return false;
 //}
 else if(document.frm16Para.selYear.value=='-1')
 {
 	alert("Select The Year First");
 	document.frm16Para.selYear.focus();
 	return false;
 }
 else if(document.getElementById("cmbDdoNames").value=="-1")
 {
 	alert("Please Enter the DDO Name First.");
 	document.getElementById("cmbDdoNames").focus();
 	return false;
 }
 else if(document.getElementById("txtTan").value=="")
 {
 	alert("Please enter the TAN number first.");
 	document.getElementById("txtTan").focus();
 	return false;
 }
 if(document.getElementById("txtITO").value=="")
 {
 	alert("Please Enter The Income Tax Office First");
 	document.getElementById("txtITO").focus();
 	return false;
 }
 else if(document.getElementById("txtquarter1").value=="")
 {
 	alert("Please Enter the Value for quarter-1.");
 	document.getElementById("txtquarter1").focus();
 	return false;
 }
 else if(document.getElementById("txtquarter2").value=="")
 {
 	alert("Please Enter the Value for quarter-2.");
 	document.getElementById("txtquarter2").focus();
 	return false;
 }
 else if(document.getElementById("txtquarter3").value=="")
 {
 	alert("Please Enter the Value for quarter-3.");
 	document.getElementById("txtquarter3").focus();
 	return false;
 }
 else
 {
 	var selDept=document.getElementById("selDept").value;
 	var selYear=document.getElementById("selYear").value;
 	var txtEmpId= document.getElementById("cmbEmpNames").value;
 	var isUpdate=document.getElementById("isUpdate").value;
 	var isInserted = document.getElementById("isInserted").value;
 	var headerId=document.getElementById("headerId").value;
 		
 	var office_address=chkForAmpersand(document.getElementById("cmbDdoNames"));	
 	var txtITO=chkForAmpersand(document.getElementById("txtITO"));	
 	var txtTan=chkForAmpersand(document.getElementById("txtTan"));
 	var txtquarter1=chkForAmpersand(document.getElementById("txtquarter1"));
 	var txtquarter2=chkForAmpersand(document.getElementById("txtquarter2"));
 	var txtquarter3=chkForAmpersand(document.getElementById("txtquarter3"));
 	var txtquarter4=chkForAmpersand(document.getElementById("txtquarter4"));
 	var txtEmpIdNew='';
 	var list = document.getElementById('cmbEmpNames');
 	for(var i = 0; i < list.options.length; i++)
 	{     
 	 	if(list.options[i].selected) 
 	 	{   
 	 	 	if(list.options[i].value!=-1)
 	 	 	{
 	 	 		if(txtEmpIdNew!='')
 	 	 	 		txtEmpIdNew=txtEmpIdNew+',';
	 	 	 		
 	 		txtEmpIdNew= txtEmpIdNew+list.options[i].value;
			
 	 	 	}
 	 	}
 	}
 	
 	var form16Window="";
 	var wndWidth = typeof(screen.availWidth) != "undefined" ? screen.availWidth : screen.width - WND_W_MARGIN; 
	var wndHeight = typeof(screen.availHeight) != "undefined" ?	screen.availHeight : screen.height - WND_H_MARGIN; 
 	
	url="./hrms.htm?actionFlag=getForm16Data&selDept="+selDept+"&selYear="+selYear+"&txtEmpId="+txtEmpIdNew+"&office_address="+office_address;
	url+="&txtTan="+txtTan+"&txtITO="+txtITO+"&txtquarter1="+txtquarter1+"&txtquarter2="+txtquarter2+"&txtquarter3="+txtquarter3+"&txtquarter4="+txtquarter4;
	url+="&isUpdate="+isUpdate+"&isInserted="+isInserted+"&headerId="+headerId;
	form16Window=window.open(url,"Form16","status=yes, toolbar=yes,resizable=1,height="+wndHeight+", width="+wndWidth+",scrollbars=1");
	form16Window.moveTo(0, 0); 
	form16Window.focus();	
 }	
}
function chkForAmpersand(element)
{
	var value=element.value;
	var chk =value.indexOf('&');
	while(chk!=-1)
	{
		value = value.replace("&","and");
		chk=value.indexOf('&');
	}
	element.value=value;
	
	
	return value;
}
--></script>


<hdiits:form name="frm16Para" validate="true" method="POST"
	action="#" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="form16" bundle="${commonLables}"></hdiits:caption></b></a></li>
	</ul>
	</div>
	
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">  
	
	<table align ="center" width="80%">
   
   	 <hdiits:hidden name="txtEmpId" default="-1"/>
   	 
   	 <tr>
   <td>
  <b> <hdiits:caption captionid="OT.empDept" bundle="${commonLables}"></hdiits:caption></b></td>
   <td colspan="3">
	<hdiits:select name="selDept" size="1" captionid="OT.empDept" bundle="${commonLables}" sort="false" validation="sel.isrequired"  mandatory="true" onchange="findEmployee(this)">
	<!--<hdiits:option value="-1"> --Select-- </hdiits:option>-->
	<c:forEach items="${cmnLocVoList}" var="cmnLocVoList">
	<c:choose>
	<c:when test="${cmnLocVoList.locId==loginLocId}">
	<hdiits:option value="${cmnLocVoList.locId}" selected="true"> ${cmnLocVoList.locName}</hdiits:option>
	</c:when>
	</c:choose>
	</c:forEach>
	</hdiits:select>
	</td>
    </tr>
   	 
   	 <tr>
     <TD> <b><hdiits:caption captionid="PR.YEAR" bundle="${commonLables}"/></b></TD>
	<td>
	<hdiits:select name="selYear" size="1" captionid="PR.YEAR" bundle="${commonLables}" sort="false" validation="sel.isrequired"  mandatory="true" onchange="findParaData(this)">
	<hdiits:option value="-1"> --Select-- </hdiits:option>
	<c:forEach items="${financialYearList}" var="financialYearList">
	<hdiits:option value="${financialYearList.lookupShortName}"> ${financialYearList.lookupDesc} </hdiits:option>
	</c:forEach>
	</hdiits:select>
	</td>
	<td> <b>
   <hdiits:caption captionid="searchName" bundle="${commonLables}"/> </b></td>
   <td>
    <input type="text" name="txtEmpFirstName" onkeyup="getEmpFirstNames()"/>
    </td>
    </tr>
    <tr>
    	<td>
    	<b>
    		<!-- <hdiits:caption captionid="eis.grade_lst" bundle="${commonLables}"/> -->
    	</b>
    	</td>
    	<td>
    		<!--<hdiits:select captionid="eis.grade_lst" bundle="${commonLables}"   name="cmbClassName"  id="cmbClassName" size="1"   onchange="getEmpFirstNames()" >
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${orgGradeList}" var="list">
								<hdiits:option value="${list.gradeId}" > ${list.gradeDesc} </hdiits:option>
							</c:forEach>  
			</hdiits:select>	-->
		</td>
		<td>
				<hdiits:caption captionid="eis.paybillGenerated" bundle="${commonLables}"/>
       	</td>
    	<td>
    			<hdiits:checkbox name='paybillGenChk' id='paybillGenChk' value="" /> 
		</td>
		
    </tr>
 <tr>  								
			<td  width="25%"><b><hdiits:caption captionid="off_add" bundle="${commonLables}"/></b></td>
	   	<td  width="25%"><!--<hdiits:textarea  captionid="off_add" bundle="${commonLables}" name="office_address" rows="5" cols="25"></hdiits:textarea></td>
	-->
	<hdiits:select name="cmbDdoNames" captionid="OT.empName" bundle="${commonLables}" validation="sel.isrequired"  mandatory="true"  id="cmbDdoNames" sort="false">
			<hdiits:option value="-1" selected="true">-------Select--------</hdiits:option>
		    </hdiits:select><hdiits:hidden name="headerId" default="0"/>
		</td>	
			<td rowspan="3"  width="15%"> <b><hdiits:caption captionid="OT.empName" bundle="${commonLables}"/> </b></td>
			<td rowspan="3"> <hdiits:select multiple="true" name="cmbEmpNames" captionid="OT.empName" bundle="${commonLables}"   id="empID" size="10" 
			sort="false" onchange="setEmpId()">
			<hdiits:option value="-1" selected="true">-------Select--------</hdiits:option>
		     
			</hdiits:select>		       
			</td>
	</tr>
  	<tr><td><b><hdiits:caption captionid="tanNo" bundle="${commonLables}"/></b></td><td><hdiits:text name="txtTan" mandatory="true"  validation="txt.isrequired" captionid="tanNo" bundle="${commonLables}"/></td></tr>

   	<tr><td><b><hdiits:caption captionid="ito" bundle="${commonLables}"/></b></td><td><hdiits:text name="txtITO" mandatory="true"  validation="txt.isrequired"  captionid="ito" bundle="${commonLables}"/></td></tr>

	<tr><td colspan="4"><b><hdiits:caption captionid="quater" bundle="${commonLables}"/></b></td></tr>

	<tr><td><b><hdiits:caption captionid="q1" bundle="${commonLables}"/></b></td><td><hdiits:text name="txtquarter1" mandatory="true"  validation="txt.isrequired"  captionid="q1" bundle="${commonLables}"/></td>

	<td><b><hdiits:caption captionid="q2" bundle="${commonLables}"/></b> </td><td><hdiits:text name="txtquarter2" mandatory="true"  validation="txt.isrequired"  captionid="q2" bundle="${commonLables}"/></td></tr>
	<tr>

	<td><b><hdiits:caption captionid="q3" bundle="${commonLables}"/></b> </td><td><hdiits:text name="txtquarter3" mandatory="true"  validation="txt.isrequired"  captionid="q3" bundle="${commonLables}"/></td>

	<td><b><hdiits:caption captionid="q4" bundle="${commonLables}"/></b></td><td><hdiits:text name="txtquarter4"  captionid="q4" bundle="${commonLables}"/></td>
	</tr>
	<tr>
	<%//System.out.println("test 9"); %>
	<td align="center" colspan="4"><hdiits:hidden name="isUpdate"/><hdiits:hidden name="isInserted"/>
	<hdiits:button name="btn1" id="btnSubmit1" bundle="${commonLables}" captionid="genForm16" onclick="openForm16()" type="button" />
	  </td>
	  </tr>	
	</table> 
    <br>
 </div>

	
	</div>
	<script type="text/javascript">
	
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		document.getElementById("paybillGenChk").checked = true;
		var deptId=document.frm16Para.selDept;
		findEmployee(deptId);
	</script>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>