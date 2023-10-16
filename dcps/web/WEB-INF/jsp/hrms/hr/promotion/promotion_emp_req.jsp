<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 

<fmt:setBundle basename="resources.hr.promotion.Promotion" var="promotionLabels" scope="request" />
<script type="text/javascript" src="script/hrms/hr/promotion/promotion.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
 
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fileId" value="${resValue.fileId}"></c:set>
<c:set var="desigList" value="${resValue.desigList}"></c:set>
<c:set var="desigList2" value="${resValue.desigList2}"></c:set>
<c:set var="deptList" value="${resValue.deptList}"></c:set>
<script>
var cacheKey = "";
var cachedData = new JSOC();
	function processResponse()
	{					    							
		if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200) 
			{          		        				
					var textStr;						
	            	var comboSubType=document.getElementById('location'); // getting  element by id name
			    	var xmlStr = xmlHttp.responseText;  // taking an XML file
			    	var XMLDoc=getDOMFromXML(xmlStr);					    	  					    
			    	var SubCoCurrStr = XMLDoc.getElementsByTagName('Location');      // getting an element from XML file				    	
			    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('LocationID'); 					    	     		     							
			    	for (var i=0;i<=comboSubType.length;i++)  // removing a previous value of combo
	    			{	     				     					
						comboSubType.remove(i);					
	    			}	
	    			var tempArray = new Array();	
					for ( var i = 0 ; i < SubCoCurrStr.length ; i++ ) // adding a new vlaue in combo
	    			{	     		     								
	    			    value=SubCoCurrStr[i].childNodes[0].text;	     				    
	    			    textStr=SubCoCurrStr_ID[i].childNodes[0].text;
	    			    if(value=='--Select--')
	   				    {
	   				   		value='--<fmt:message  bundle="${promotionLabels}" key="Promotion.Select"/>--'
	   				    }
	    				var y=document.createElement('option');
	 					y.text=value;
						y.value=textStr;
						tempArray[i] = y;			 					
						try
	  					{
	   						comboSubType.add(y,null); 	    						
	  					}
						catch(ex)
	  					{	   			 
	  			 			comboSubType.add(y);	   			 				 
	  					}     						     					
	          		} 
	          		if( (cacheKey != "") && (cachedData.get(cacheKey) == undefined) )
					{
						cachedData.add(cacheKey,tempArray);
						cacheKey = "";
					}         			
			}
			else  // if any error in adding a value in combo
			{  			
				alert("error adding in combo");					
			}
		}
	}
		function callLastJSFunction()
		{
			var validFlag=false;	
			validFlag = ValidateMe(new Array('dept','location','designation','forDesig','tentativeNoEmp'));
			if(validFlag==false){return false;}
			return true;
		}
		function ValidateMe(arr)
		{
			var validFlag=true;
			for(var j=0;j<arr.length;j++)
			{
				var id = arr[j];				
				str = document.getElementById(id).value;
				if(id=='tentativeNoEmp')
				{
					var annual_rule_num="0123456789";					
					for(var i=0;i<str.length;i++)
					{
						var cchar=str.charAt(i);
				        if(annual_rule_num.indexOf(cchar)==-1)
		        		{
		        			alert('<fmt:message  bundle="${promotionLabels}" key="Promotion.altNoOfEmp" />');
			        	  	validFlag=false;
							break;
		        		}
		        	}
				}
				if((str=='' || str==null  || str=='Select' || str=='select') && validFlag==true)	
				{
					document.getElementById(id).focus();
					if(id=='dept'){
						id='Department';
						alert('<fmt:message  bundle="${promotionLabels}" key="Promotion.altDeptReqSel" />');						
					}
					else if(id=='forDesig'){
						id='Requirement for Designation';
						alert('<fmt:message  bundle="${promotionLabels}" key="Promotion.altDesigReqSel" />');						
					}
					else if(id=='tentativeNoEmp'){
						id='Tentavie Number Of Employee';
						alert('<fmt:message  bundle="${promotionLabels}" key="Promotion.altTenReqSel" />');
					}
					//alert(id + ' is Required to Select');
					validFlag=false;	
					break;
				}
			}
			return validFlag;
		}
function populateDropDownWithCachedData(key,dropDownId)
{
	var dropDown = document.getElementById(dropDownId);
	for(i=1;i<dropDown.length;i++)
	{
			lgth = dropDown.options.length -1;
			dropDown.options[lgth] = null;
	}
	for (var i=0;i<=dropDown.length;i++)  
	{	     				     					
		dropDown.remove(i);					
	}
	var cacheObj = cachedData.get(key);
	var cachedArray = cacheObj[key];	
	for ( var i = 0 ; i < cachedArray.length ; i++ )
	{
		var temp = cachedArray[i]
		var myOpt = new Option(temp.text,temp.value)
		dropDown.options[(i)] = myOpt;	
	}
}
function populateNextLevel(srcLevelId) 
{
	var nextLevelId = document.getElementById("location");
	var checkCachedData = isCachedData(srcLevelId,nextLevelId);	
	if(checkCachedData)
	{
		populateDropDownWithCachedData(cacheKey,"location")
	}
	else
	{
		com(srcLevelId);
	}
}
function isCachedData(srcLevelId,nextLevelId)
{
	cacheKey = "";
	cacheKey = "Location_" + srcLevelId.value;	
	if(cachedData.get(cacheKey) != undefined )
	{
		return true;
	}
	else
	{	
		return false;
	}
}
</script>
<hdiits:form name="promotionInit" validate="true" method="POST" action="hrms.htm?actionFlag=getPromoteVal" encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="Promotion.initiatePromotion" bundle="${promotionLabels}" /></b></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}"/>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.SearchVacancy" bundle="${promotionLabels}">		
		<table width=100%>
		<tr>
		<td width=25%><b><hdiits:caption captionid="Promotion.Department" bundle="${promotionLabels}" /></b></td>
			
			<td width=25%>	
			<hdiits:select id="dept" name="dept" onchange="populateNextLevel(this)" mandatory="true">
				<hdiits:option value="Select">
					<fmt:message key="Promotion.Select" bundle="${promotionLabels}" />
				</hdiits:option>
				<c:forEach var="deptList" items="${deptList}">
							<option value="${deptList.departmentId}">${deptList.depName}
							</option>
	   			   		</c:forEach>
			</hdiits:select>
			</td>
			<td width=25%><b><hdiits:caption captionid="Promotion.Location" bundle="${promotionLabels}" /></b></td>
			
			<td width=25%> 	
			<hdiits:select id="location" name="location" mandatory="true">
				<hdiits:option value="Select">
					<fmt:message key="Promotion.Select" bundle="${promotionLabels}" />
				</hdiits:option>
			</hdiits:select>
			</td>
		</tr>
		<tr>
			<td width=25%><b><hdiits:caption captionid="Promotion.Designation" bundle="${promotionLabels}" /></b></td>
			
			<td width=25%> 	
			<hdiits:select id="designation" name="designation" mandatory="true">
					<c:forEach var="desigList" items="${desigList}">
							<option value="${desigList.dsgnId}">${desigList.dsgnName}
							</option>
	   			   	</c:forEach>
			</hdiits:select>
			</td>
		</tr>
		</table>	
		<br><br>
		<table width=100%>
			<tr align="center">
				<td align="center">
					<hdiits:button name="Search" type="button" value="Search"  onclick="search();"/>
				</td>
			</tr>
		</table>
	</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.VacancyDetails" bundle="${promotionLabels}">		
		<table width="100%" id="vacancyTable" style="border-collapse: collapse;" borderColor="BLACK"  border=1>		
		<tr bgcolor="#C9DFFF">	
			<td class="fieldLabel"  align="center">
				<b><hdiits:caption captionid="Promotion.srNo" bundle="${promotionLabels}" /></b>
			</td>
			
			<td class="fieldLabel"  align="center">
				<b><hdiits:caption captionid="Promotion.officeName" bundle="${promotionLabels}" /></b>
			</td>
			<td class="fieldLabel"  align="center">
				<hdiits:caption captionid="Promotion.Designation" bundle="${promotionLabels}" />
			</td>
			<td class="fieldLabel"  align="center">
				<b><hdiits:caption captionid="Promotion.sanctionRules" bundle="${promotionLabels}" /></b>
			</td>
			
			<td class="fieldLabel"  align="center">
				<b><hdiits:caption captionid="Promotion.presenStrength" bundle="${promotionLabels}" /></b>
			</td>
			
			<td class="fieldLabel"  align="center">
				<b><hdiits:caption captionid="Promotion.vacancy" bundle="${promotionLabels}" /></b>
			</td>
			
			<td class="fieldLabel"  align="center" rowspan="2" colspan="2" >					
				<table  align="center" width="100%">
				<tr><td colspan="2" >
					<b><hdiits:caption captionid="Promotion.deputation" bundle="${promotionLabels}" /></b>
				</td></tr>
				<tr>
				<td class="fieldLabel" >
					<b><hdiits:caption captionid="Promotion.office" bundle="${promotionLabels}" /></b>
				</td>
				<td class="fieldLabel" >
					<b><hdiits:caption captionid="Promotion.otherOffice" bundle="${promotionLabels}" /></b>
				</td>
				</tr></table>
			</td>
		</tr>
		<tr><td></td></tr>		
		<tr bgcolor="#C9DFFF">
			<td class="fieldLabel" bgcolor="white" align="center"></td>
			<td class="fieldLabel" bgcolor="white" align="center"></td>
			<td class="fieldLabel"  align="center">
				<b><hdiits:caption captionid="Promotion.total" bundle="${promotionLabels}" /></b>
			</td>
			<td class="fieldLabel" align="center">
				<hdiits:text size="5" readonly="true" name="sanction_toatal" id="sanction_toatal" default="0"/>
			</td>
			<td class="fieldLabel" align="center">
				<hdiits:text size="5" readonly="true" name="present_toatal" id="present_toatal" default="0"/>
			</td>
			<td class="fieldLabel" align="center">
				<hdiits:text size="5" readonly="true" name="vacancy_toatal" id="vacancy_toatal" default="0"/>
			</td>
			<td class="fieldLabel" align="center">
				<hdiits:text size="5" readonly="true" name="inOffice_toatal" id="inOffice_toatal" default="0"/>
			</td>
			<td class="fieldLabel" align="center">
				<hdiits:text size="5" readonly="true" name="otherOffice_toatal" id="otherOffice_toatal" default="0"/>
			</td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.raiseReq" bundle="${promotionLabels}">		
		<table width="100%">
			<tr>
				<td><b><hdiits:caption captionid="Promotion.raiseReqForDesig" bundle="${promotionLabels}" /></b></td>				
				<td>
				<hdiits:select id="forDesig" name="forDesig" mandatory="true">
						<option value="Select"><fmt:message key="Promotion.Select" bundle="${promotionLabels}" /></option>
							<c:forEach var="desigList" items="${desigList2}">
								<option value="${desigList.dsgnId}">${desigList.dsgnName}
								</option>
		   			   		</c:forEach>
				</hdiits:select>
				</td>
			</tr>
			<tr>
				<td><b><hdiits:caption captionid="Promotion.tentEmployeeToBePromoted" bundle="${promotionLabels}" /></b></td>				
				<td>
				<hdiits:number name="tentativeNoEmp" id="tentativeNoEmp" mandatory="true"/>
				</td>
			</tr>
		</table>
	</hdiits:fieldGroup>
	<br>					
	<br>
	<center>
		<jsp:include page="../../../core/tabnavigation.jsp"/>
	</center>
	</div>
</div>
<hdiits:jsField jsFunction="callLastJSFunction()" name="callLastJSFunction" />
<hdiits:validate locale="${locale}" controlNames="" />		
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")		
</script>
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

