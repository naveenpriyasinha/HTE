
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src=<c:url value="/script/hrms/eis/Address.js"/>"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="desigList1" value="${resValue.desigList1}"></c:set>
<c:set var="userClassTypeList" value="${resValue.classTypeList}"></c:set>
<c:set var="typeList" value="${resValue.typeList}"></c:set>
<c:set var="lstCompDtls" value="${resValue.lstCompDtls}"></c:set>
<c:set var="type" value="${resValue.type}"></c:set>
<c:set var="component" value="${resValue.component}"></c:set>
<c:set var="startDate" value="${resValue.startDate}"></c:set>
<c:set var="endDate" value="${resValue.endDate}"></c:set>
<c:set var="amount" value="${resValue.amount}"></c:set>
<c:set var="className" value="${resValue.className}"></c:set>
<c:set var="desg" value="${resValue.desg}"></c:set>
<c:set var="compList" value="${resValue.compList}"></c:set>
<c:set var="desgmList1" value="${resValue.desgnList1}"></c:set>
<c:set var="hrEisGrpMangMst" value="${resValue.hrEisGrpMangMst}"></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="compId" value="${resValue.compId}" ></c:set>



<script>

if('${msg}'!=null && '${msg}'!='')
{
	alert('${msg}' );				
	var url="./hrms.htm?actionFlag=showGroupManagement";
	document.GroupManagement.action=url;
	document.GroupManagement.submit();
}

function GetDesignations()
{
	//alert ("Inside GetDesignations method");
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&gradeId='+ document.forms[0].Class.value;
		  
		  var actionf="getDesinationsFromClassUsingAjax";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		  xmlHttp.onreadystatechange=gradechangeddesigs;
		  xmlHttp.open("POST",encodeURI(url),false);
		  xmlHttp.send(null);		  	
}
function clearDesigantionsCombo()
{
	
	var v=document.getElementById("desigList").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("desigList").options.length -1;
			document.getElementById("desigList").options[lgth] = null;
	}		
}
function selectCmb(valu)
{	
	var v=document.getElementById("desigList").length;	
	for(i=1;i<v;i++)
	{
		if(document.getElementById("desigList").options[i].value == valu)
		{
			document.getElementById("desigList").options[i].selected=true;			
		}	
	}		
	populateSelectedDesignations();
}
function populateDesignations()
{
	var desigComboLength = document.getElementById('desigList').length;	
	//alert(document.getElementById('desigList').length);
	var txtDesig = document.getElementById('txtDesig');	 
	txtDesig.value = "";
	for(i=1;i<desigComboLength;i++)
	{				
		txtDesig.value += document.getElementById("desigList").options[i].value + ",";								
	}
}
function populateSelectedDesignations()
{
	
	//alert('inside set txtdesig');
	var desigComboLength = document.getElementById('desigList').length;	
	//alert('1');
//	alert('3' + document.forms[0].txtDesig.value);
	//alert(document.getElementById('desigList').length);
	var txtDesig1 = document.GroupManagement.txtDesig;
	txtDesig1.value = "";
	//alert('2');	 
	
	
	for(i=1;i<desigComboLength;i++)
	{				
		if(document.getElementById("desigList").options[i].selected==true)
		  txtDesig1.value += "," + document.getElementById("desigList").options[i].value;								
	}
	//alert('inside set txtdesig ' + txtDesig1.value);
}

function gradechangeddesigs()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			clearDesigantionsCombo();			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var namesEntries = XMLDoc.getElementsByTagName('gradeId');

				    var desigListObuect = document.getElementById('desigList');

				    for(var k = 0; k < namesEntries.length;k++)
					{
	                   var y1 = document.createElement('option');  
	                   val=namesEntries[k].childNodes[1].text;    
    				    text = namesEntries[k].childNodes[2].text; 
     			        y1.value=val;
     			        y1.text=text;	
     			        try
   				        {      				    					
     			        	desigListObuect.add(y1,null);
           			    }
 				        catch(ex)
   				        {
 				        	desigListObuect.add(y1); 
   			   	        }	
                    }
				    //populateDesignations();
					        
				                     
  		}
	}
function GetComponents()
{
	
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		//  alert ("Inside GetComponents method");
		  var url; 
		  //var uri='';
		  
		  //url= uri+'&lookupId='+ document.forms[0].selType.value;
		  
		  
		//  var actionf="getComponentlistByTypeUsingAjax";
		  url='./hrms.htm?actionFlag=getComponentlistByTypeUsingAjax&lookupId='+document.forms[0].Type.value;
		  xmlHttp.onreadystatechange=typechangedComponents;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}
function clearComponentCombo()
{
	var v=document.getElementById("Component").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("Component").options.length -1;
			document.getElementById("Component").options[lgth] = null;
	}		
}
function typechangedComponents()
{	
	if (xmlHttp.readyState==complete_state)
	{ 
		clearComponentCombo();
		
				var XMLDoc=xmlHttp.responseXML.documentElement;
			    var componentEntries = XMLDoc.getElementsByTagName('typeId');

			    var compListObuect = document.getElementById('Component');

			    for(var k = 0; k < componentEntries.length;k++)
				{
                   var y1 = document.createElement('option');  
                   val=componentEntries[k].childNodes[0].text;    
				    text = componentEntries[k].childNodes[1].text;
				 //   alert('val is '+ val);
				   // alert('text is ' + text); 
 			        y1.value=val;
 			        y1.text=text;	
 			        try
				        {      				    					
 			        	compListObuect.add(y1,null);
       			   		 }
				        catch(ex)
				        {
				        	compListObuect.add(y1); 
			   	        }	
                }          

			                     
	}
}

function validateAmount()
{
	var amount = document.forms[0].elements['Amount'].value
	 if(amount == "")
	 {
		  alert('Please Enter Amount');
	 }
}

function checkDate()
{
	
	var stdate=document.GroupManagement.startDt.value;
	
	var endDate=document.GroupManagement.eDate.value;
	
	
	if(stdate!='' && endDate!='' && compareDate(stdate,endDate) < 0)
	{
		alert('Start Date Should not be Greater than End Date');
		//document.frmNewEmpInfo.DOB.value='';
		//document.frmNewEmpInfo.DOJ.value='';
		//document.frmNewEmpInfo.DOR.value='';
		document.GroupManagement.eDate.value = '';
		document.GroupManagement.eDate.focus();
		return false;
	}

	return true;
}

/*function cmpDate()
{
	alert('in function');
	 var diff = compareDate(document.GroupManagement.startDt.value,document.GroupManagement.endDt.value);   
	// alert('diff is ' + diff);
	 if(document.GroupManagement.endDt.value!=null&&document.GroupManagement.endDt.value!='')
	 {
		// alert('endDt is ' + document.GroupManagement.endDt.value);
	 	if(diff < 0)
  	 	{
   			alert("End Date must be greater than Start Date");
   			document.GroupManagement.endDt.value='';
   			return false;
  	 	}
  	 	else
  	 	{
 			datediff(document.GroupManagement.startDt.value,document.GroupManagement.endDt.value);
  		 }
  	 }
 }
*/
function onclosefn()
{
		window.location="./hrms.htm?actionFlag=GroupManagementView&elementId=3000049";
}

function validateForm1()
{
	
	var TypeId=document.GroupManagement.Type.value; 
	var Type=document.GroupManagement.Type.options[document.GroupManagement.Type.selectedIndex].text;
	var ComponentId=document.GroupManagement.Component.value;
	var ComponentType=document.GroupManagement.Component.options[document.GroupManagement.Component.selectedIndex].text;
    //alert("Value is"+document.GroupManagement.startDt.value);

	var StrtDate=document.GroupManagement.startDt.value;
	
	var EndDate=document.GroupManagement.eDate.value;
	var Amount=document.GroupManagement.Amount.value;
	var ClassId=document.GroupManagement.Class.value;
	var ClassType=document.GroupManagement.Class.options[document.GroupManagement.Class.selectedIndex].text;
	var DesignId=document.GroupManagement.desigList.value;
	var Designation=document.GroupManagement.desigList.options[document.GroupManagement.desigList.selectedIndex].text;

 		document.GroupManagement.action = "./hdiits.htm?actionFlag=saveGroupManagement&TypeId=" + TypeId + "&Type=" + Type + "&ComponentId=" + ComponentId +"&ComponentType="+ComponentType
	 									  "&StrtDate=" +StrtDate+"&EndDate=" +EndDate+"&Amount="+Amount+"&ClassId="+ClassId+"&ClassType="+ClassType+"&DesignId="+DesignId+"&Designation="+Designation;
		document.GroupManagement.submit();
}

</script>
</script>

	<hdiits:form name="GroupManagement" validate="true" method="POST" action="javascript:validateForm1()">
	<hdiits:hidden  default ="${resValue.edit}" name="edit" caption="edit"  />
		<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
		<c:if test="${resValue.edit != 'Y'}">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="gm.showGroupManagement" bundle="${commonLables}"/></b></a></li>
		</c:if>
		<c:if test="${resValue.edit == 'Y'}">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="gm.GroupManagementUpdate" bundle="${commonLables}"/></b></a></li>
		</c:if>
		</ul>
	</div>
	<hdiits:hidden  default ="${resValue.groupManagementId}" name="groupManagementId" caption="groupManagement Id"  />
	<br><br><br>
	<table width="75%" bgcolor="white" align="center" id="searchTable">
		<tr>
		   <td  align = "center" width="20%"><b><fmt:message key="gm.typeName" bundle="${commonLables}"/></b></td>
		   <td align = "left" width="25%">
				<hdiits:select name="Type" size="1" sort="false" caption="Type" id="Type"  validation="sel.isrequired" mandatory = "true" onchange="GetComponents()" >
						<hdiits:option value="" >-------Selected-------</hdiits:option>
						<c:forEach var="typeList" items="${typeList}">
							<c:choose>
								<c:when test="${typeList.lookupShortName == type}">
			 				 	<% //System.out.println("In If Loop type Name");			  %>
 			  				 	<hdiits:option  value="${typeList.lookupId}" selected="true">${typeList.lookupShortName}</hdiits:option>
			  					</c:when>
			  						<c:otherwise>
			  							<% //System.out.println("In Bank Name else Loop"); %>
										<hdiits:option value="${typeList.lookupId}"><c:out value="${typeList.lookupShortName}" /></hdiits:option>	
									</c:otherwise>
							</c:choose>						
						</c:forEach>
				</hdiits:select>
	      </td>
	      <td  align = "center" width="20%"><b><fmt:message key="gm.componentType" bundle="${commonLables}"/></b></td>
	       <td align = "left" width="25%"> 
				<hdiits:select name="Component" size="1" sort="false" caption="Component" id="Component" validation="sel.isrequired" mandatory = "true" onchange="validatecombo()" >
						<hdiits:option value="" >---------------Selected---------------</hdiits:option>	
											
						<c:forEach var="compList1" items="${compList}">
						<script>//alert("${resValue.type == 'Deduction'}"); </script>
						<c:if test="${resValue.type == 'Deduction'}">	
						<script>//alert("deducCode = "+"${compList1.deducCode}");</script>
							<c:choose>
								<c:when test='${compList1.deducCode eq compId}'> 
								<hdiits:option value="${compList1.deducCode}" selected="true">${compList1.deducName}</hdiits:option>
								</c:when>
							<c:otherwise>
								<hdiits:option value="${compList1.deducCode}" ><c:out value="${compList1.deducName}"/></hdiits:option>
								<script>//alert("Deduction Code:::::::"+"${compList1.deducCode}");</script>
							</c:otherwise>
							</c:choose>							
						</c:if>
							
							
							
							<c:if test="${resValue.type == 'Allowance'}">
						
							<script>//alert("allowCode = "+"${compList1.allowCode}");</script>
							<script>//alert("compId = "+"${compId}");</script>
							<c:choose>
								<c:when test='${compList1.allowCode eq  compId}'>
								<script>//alert("inside if");</script> 
								<script> //alert('name ' + "${compList1.allowName}"); </script>
								<hdiits:option value="${compList1.allowCode}" selected="true">${compList1.allowName}</hdiits:option>
								</c:when>
							<c:otherwise>
								<hdiits:option value='${compList1.allowCode}'><c:out value="${compList1.allowName}"/></hdiits:option>
							</c:otherwise>
							</c:choose>														
						</c:if>
						</c:forEach>
						
				</hdiits:select>
	      </td>
	    </tr>
	<tr>
	  
	     
        
        
        <td  align = "center" width="20%"><b><fmt:message key="gm.startDate" bundle="${commonLables}"/></b></td>
			<td width="25%" align="left">
			 <fmt:formatDate var="stDate" value="${startDate}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium" />
					<hdiits:dateTime name="startDt" default="${stDate}" captionid="gm.startDate" bundle="${commonLables}"  mandatory=""  validation="txt.isdt,txt.isrequired" />
			</td>
        
        
        <td  align = "center" width="20%"><b><fmt:message key="gm.endDate" bundle="${commonLables}"/></b></td>
			<td width="25%" align="left">
			 <fmt:formatDate var="endDate" value="${endDate}" pattern="yyyy-MM-dd HH:mm:ss.s"  dateStyle="medium" />
					<hdiits:dateTime name="eDate" default="${endDate}" captionid="gm.endDate" bundle="${commonLables}"  mandatory=""  validation="txt.isdt" onblur="checkDate()" />
			</td>
        
        
        
        
        
        
        
        
        
        
        
	     
	    </tr>
	    <tr>
	        <td  align = "center" width="20%"><b><fmt:message key="gm.amount" bundle="${commonLables}"/></b></td>
	        <td  align = "left" width="25%">
	              <hdiits:number name="Amount" id="Amount" default = "${resValue.amount}" mandatory="true" size = "16"  validation="txt.isnumber" caption="Amount" onblur="validateAmount()"/></td>
	    </tr>  
	   
	    <tr>
	        <td  align = "center" width="20%"><b><fmt:message key="gm.class" bundle="${commonLables}"/></b></td>
	        <td rowspan="3" align = "left" width="25%"> 
	            <hdiits:select multiple="false" name="Class" captionid="gm.class" bundle="${commonLables}" id="Class" validation="sel.isrequired" mandatory="true" size="5" onchange="GetDesignations()" sort="false"> 
				              <hdiits:option value="-1" selected="true">-------Select--------</hdiits:option>
			                        <c:forEach var="classes" items="${userClassTypeList}">
			                        	<c:choose>
											<c:when test="${classes.gradeName == className}">
												<hdiits:option  value="${classes.gradeId}" selected="true">${classes.gradeName}</hdiits:option>
			                        		</c:when>
			                        		<c:otherwise>
								            	 <hdiits:option value="${classes.gradeId}"><c:out value="${classes.gradeName}" /></hdiits:option>							
											</c:otherwise>
										</c:choose>						
									</c:forEach>
				</hdiits:select>		      
				</td>
			
			
	     <td  align = "center" width="20%"><b><fmt:message key="gm.designation" bundle="${commonLables}"/></b></td>
	        <td rowspan="3" align = "left" width="25%" > 
	        <hdiits:select multiple="true" name="desigList" captionid="desigList" id="desigList" validation="" size="5" sort="false" onchange="populateSelectedDesignations()">
				<hdiits:option value="-1" selected="">-------Select--------</hdiits:option>
				<script>//alert(1);</script>
			   	 <c:forEach var="desigList1" items="${desigList1}">
			   	 <script>//alert(2);</script>
			   	  <hdiits:option value="${desigList1.orgDesignationMst.dsgnId}" ><c:out value="${desigList1.orgDesignationMst.dsgnName}" /></hdiits:option>
			   	 
				</c:forEach> 
				
			</hdiits:select>
			
			</td>
	    </tr>
	
	
</table> 

				<c:forEach var="desgmList2" items="${desgmList1}">
							 <script>selectCmb('${desgmList2}');</script>
				</c:forEach> 
	<br><br><br>
	
	<c:if test="${resValue.edit == 'Y'}">
	
	<fmt:setBundle basename="resources.payroll.payrollLables" var="Lables" scope="request"/>
	<div align="center">
    <hdiits:formSubmitButton name="Update" id="Update" type="button" caption="Update" onclick="submitForm()"/>
     <hdiits:button name="btnClose1" type="button" captionid="eis.close" bundle="${Lables}" onclick="onclosefn()" />   
	
	</c:if>
	<c:if test="${resValue.edit != 'Y'}">
	<center>
		<jsp:include page="../../core/PayTabnavigation.jsp" />
				 <hdiits:hidden default="getHomePage" name="givenurl"/>
	</center>
	</c:if>
	
	<c:if test="${resValue.edit != 'Y'}">
	<hdiits:hidden name="txtDesig" default="" id="txtDesig"/>	
	</c:if>
	<c:if test="${resValue.edit == 'Y'}">
	<hdiits:hidden name="txtDesig" default="${desg}" id="txtDesig"/>	
	</c:if>
<hdiits:validate locale="${locale}" controlNames="" />

<script type="text/javascript">
	initializetabcontent("maintab")
</script>

	</hdiits:form>
	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>




	



