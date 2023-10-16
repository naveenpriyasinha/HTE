
<%
try {	
%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<%@page	import="java.util.List,com.tcs.sgv.ess.valueobject.EmployeeSearchVO;"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.ess.Lables" var="lables"	scope="request" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<c:set var="resultObject" value="${result}" />
<c:set var="empMap" value="${resultObject.resultValue}" />
<c:set var="name" value="${empMap.empSrchName}"></c:set>
<c:set var="finalEmpList" value="${empMap.finalEmpList}" />
<c:set var="firstName" value="${empMap.defEmpfnameTxtVal}" />
<c:set var="lastname" value="${empMap.tempempLname}" />
<c:set var="loggedInDistrictCode" value="${empMap.loggedInDistrictCode}" />
<c:set var="loggedInCityCode" value="${empMap.loggedInCityCode}" />
<c:set var="searchInMyLocation" value="${empMap.ownLocationKey}"/>
<c:set var="designationCode" value="${empMap.designationCode}"/>
<c:set var="numberResultsPerPage" value="${empMap.numberResultsPerPage}" />
<c:set var="districtlist" value="${empMap.districtlist}" />
<c:set var="dsgnList" value="${empMap.dsgnList}" />
<c:set var="loggedInLocationcode" value="${empMap.loggedInLocationcode}" />
<c:set var="listReadOnlyParam" value="${empMap.listReadOnlyParam}" />
<c:set var="functionName" value="${empMap.functionName}" />
<c:set var="levelSortParam" value="${empMap.levelSortParam}" />
<c:set var="multiEmplSelect" value="${empMap.multiEmplSelect}" />
<c:set var="prevSeldEmpls" value="${empMap.seldEmployeeArray}" />
<c:set var="prevSeldEmplsName" value="${empMap.seldEmplsNameArray}" />
<c:set var="prevSeldEmplsDesig" value="${empMap.seldEmplsDesigArray}" />
<c:set var="prevSeldEmplsPost" value="${empMap.seldEmplsPostArray}" />
<c:set var="post" value="${empMap.post}" />
<c:set var="OfficeName" value="${empMap.OfficeName}" />
<c:set var="officeList" value="${empMap.officeList}" />
<c:set var="empId" value="${empMap.empId}" />
<c:set var="gpfAcctNo" value="${empMap.gpfAcctNo}" />
<c:set var="forReports" value="${empMap.forReports}" />
<c:set var="multiSelectStatus" value="${empMap.multiSelectStatus}" />
<c:set var="msg" value="${empMap.msg}" />
<c:set var="sevarthEmpCode" value="${empMap.sevarthEmpCode}" />
<c:set var="sizeoffinalEmpList" value="${empMap.sizeoffinalEmpList}" />

<script>

if('${msg}'!=null && '${msg}'!='')
alert('${msg}');
</script>
	<fmt:message key="DISP_FIRST" var="dispFirst" 	bundle="${lables}"/>
	<fmt:message key="DISP_PREV" var="dispPrev" 	bundle="${lables}"/>
	<fmt:message key="DISP_NEXT" var="dispNext" 	bundle="${lables}"/>
	<fmt:message key="DISP_LAST" var="dispLast" 	bundle="${lables}"/>


<script type="text/javascript">	

	var selectedEmplArray = new Array();
	var selEmpsNameArray  = new Array();
	var selEmpsDesigArray = new Array();
	var selEmpsPostArray = new Array();
	var firstTime = true;
	
	/* This function is called on submit button. It will call function(if any) specified by developer.
	   It returns array of employee ids if employee search is used in multiple employee select mode.
	   In other case, selected emplyee details are returned	 */
	function windowclose()
	{
			//alert("windowclose");
		var multiSelectStatus = '${multiEmplSelect}';
	//	 alert("multiSelectStatus" +multiSelectStatus);
		 
		/* Return array of employee IDs,selected employee Names and their designation
		 if multiple employee selection is enabled.*/
		
		if(multiSelectStatus == 'Yes')
		{
			if(window.opener.parent.document.forms[0].Employee_ID_${name} != null)
			{
				// alert("if")
				window.opener.parent.document.forms[0].Employee_ID_Array_${name}.value = document.getElementById('hidden_employeeSelArray').value;
				window.opener.parent.document.forms[0].Sel_Employee_NAME_Array_${name}.value = document.getElementById('hidden_selEmpsNameArray').value;
				window.opener.parent.document.forms[0].Sel_Employee_DESIG_Array_${name}.value = document.getElementById('hidden_selEmpsDesigArray').value;
				window.opener.parent.document.forms[0].Sel_Employee_POST_Array_${name}.value = document.getElementById('hidden_selEmpsPostArray').value;		
			}
			else
			{
				//	alert("else");
				parent.window.opener.document.getElementById("Employee_ID_Array_${name}").value	= document.getElementById('hidden_employeeSelArray').value;
				parent.window.opener.document.getElementById("Sel_Employee_NAME_Array_${name}").value	= document.getElementById('hidden_selEmpsNameArray').value;
				parent.window.opener.document.getElementById("Sel_Employee_DESIG_Array_${name}").value	= document.getElementById('hidden_selEmpsDesigArray').value;
				parent.window.opener.document.getElementById("Sel_Employee_POST_Array_${name}").value	= document.getElementById('hidden_selEmpsPostArray').value;
			}
		}
		// Set single employee in parent JSP.
		else
		{
				//	alert("window second else");
			setSelectedUser();
		}
		// Call developer's function (if any) before closing window.
		var forReports = document.getElementById("forReports").value;
		var returnValue =null;


		if(!forReports == 'No' || forReports == '')
		{
			//alert('ayyooooo');
			
			 returnValue = window.opener.${functionName}();
			
		}
		else if(forReports=='No')
		{
		
			 returnValue = window.opener.${functionName}();
			
		}
		
		else
		{
		
			
			 returnValue = true;
		}
		
		if(returnValue)
		{
			
			window.close(); 
		}
		else
		{
			window.focus();
		}
	}
	
	function checkSpecialCharEnterForEmployee()
	{
	     var key;
	     var keychar;
	     if (event)
	     {
	         key = event.keyCode;
	     }
	     else if (event)
	     {
	         key = event.which;
	     }
	     else
	     {
	         return true;
	     }
	     keychar = String.fromCharCode(key);
	     var specialChars = "`-=$#:;,@!&*?~(){}<>^\\\'|\[]/+";
	     if ( specialChars.indexOf(keychar) != -1 )
	     {
	         return false;
	     }
     return true;       
	}
	
	/* This function is used to re-configure links in display table. 
	   This is done to retain values selected in previous request.
	   It is used when employee search service is used to select multiple employees.
	*/
	function showPageForSectionDispTable(pageNo,mode)
	{
		document.getElementById("hidden_PageNoForDisp").value	= pageNo	; 
		/*
		Here "mode" indicate from which link function called.
		If	mode = -1 function called from Prev link
			mode = 1  function called from Next link
			mode = 0  function called from First OR Last OR paging link (like 1,2,...,8)	
		*/
		if(mode == -1)
		{
			pageNo = pageNo - 1;
		}
		else if(mode == 1)
		{
			pageNo = pageNo + 1;
		}
		//SET FOR CURRENT SELECTED SECTION CODES 
		//	Id of section display table is 1927817
		document.form1.action='hdiits.htm?actionFlag=payrollSearchEmployeeByAll&d-16544-p='+pageNo;
		document.form1.submit();
	}
	
	/* This function is called on check/uncheck event of checkbox. 
	   It appends value of selected employee ID in an array on check event of checkbox.
	   It will remove employee ID on uncheck event of checkbox. */
	   
	function editValueCheckBox(chkBox,empId,sevarthEmpCode,empFirstName,empLastName,empDesigName,empPostId)
	{
		var curSelChkBxValue = chkBox.value ;

		/* This code snippet is executed when we need to track record of employees 
		   selected in previous request. It will assign the previously selected employee Ids,
		   their names and desigantion in respective arrays.
		*/
		   
		if(firstTime)
		{
			var fromReqOrFirst = '${prevSeldEmpls}';
			var fromReqOrFirstSelEmpNames = '${prevSeldEmplsName}';
			var fromReqOrFirstSelEmpDesig = '${prevSeldEmplsDesig}';
			var fromReqOrFirstSelEmpPost = '${prevSeldEmplsPost}';
			
			if(fromReqOrFirst.length>0)
			{
				selectedEmplArray = fromReqOrFirst.split(',');
				selEmpsNameArray  = fromReqOrFirstSelEmpNames.split(',');
				selEmpsDesigArray = fromReqOrFirstSelEmpDesig.split(',');
				selEmpsPostArray = fromReqOrFirstSelEmpPost.split(',');
			}
			firstTime = false;
		}
		
		/* Following code is executed if checkbox is checked.
		   Employee ID,Name and Designation is appended to respective arrays.
		*/
		if(chkBox.checked)
		{

			
			var curSelSectCode = chkBox.value ;
			var tempNameVal = empFirstName + " " + empLastName;
			var tempArrayName = new Array(tempNameVal);
			var tempDesigName = new Array(empDesigName);
			var tempArraySel = new Array(curSelChkBxValue);
			var tempPostId = new Array(empPostId);
			selectedEmplArray = selectedEmplArray.concat(tempArraySel);
			selEmpsNameArray = selEmpsNameArray.concat(tempArrayName);
			selEmpsDesigArray = selEmpsDesigArray.concat(tempDesigName);
			selEmpsPostArray = selEmpsPostArray.concat(tempPostId);
			document.getElementById('hidden_employeeSelArray').value = selectedEmplArray;
			document.getElementById('hidden_selEmpsNameArray').value = selEmpsNameArray;
			document.getElementById('hidden_selEmpsDesigArray').value = selEmpsDesigArray;
			document.getElementById('hidden_selEmpsPostArray').value = selEmpsPostArray;
		}
		
		/* Following code is executed if checkbox is unChecked.
		   Employee ID, Name and Designation is removed from respective arrays.
		*/
		else
		{

			var tempEmplArray = new Array();
			var tempNameVal = empFirstName + " " + empLastName;
			var tempArrayName = new Array();
			var tempArrayDesig = new Array();
			var tempPostDesigArray = new Array();
			for(var arrCtr = 0 ; arrCtr < selectedEmplArray.length ; arrCtr++)
			{
				if(selectedEmplArray[arrCtr]==curSelChkBxValue);
				else
				{
					var tempArrayDel = new Array(selectedEmplArray[arrCtr]);
					tempEmplArray = tempEmplArray.concat(tempArrayDel);
				}
			}
			selectedEmplArray = tempEmplArray;
			for(var arrNameCtr = 0 ; arrNameCtr < selectedEmplArray.length ; arrNameCtr++)
			{
				if(selEmpsNameArray[arrNameCtr] == tempNameVal);
				else
				{
					var tempNameArrayDel = new Array(selEmpsNameArray[arrNameCtr]);
					tempArrayName = tempArrayName.concat(tempNameArrayDel);
				}
			}
			selEmpsNameArray = tempArrayName;
			for(var arrDesigCtr = 0 ; arrDesigCtr < selectedEmplArray.length ; arrDesigCtr++)
			{
				if(selEmpsDesigArray[arrDesigCtr] == tempNameVal);
				else
				{
					var tempDesigArrayDel = new Array(selEmpsDesigArray[arrDesigCtr]);
					tempArrayDesig = tempArrayDesig.concat(tempDesigArrayDel);
				}
			}
			selEmpsDesigArray = tempArrayDesig;
			for(var arrPostCtr = 0 ; arrPostCtr < selectedEmplArray.length ; arrPostCtr++)
			{
				if(selEmpsPostArray[arrPostCtr] == tempNameVal);
				else
				{
					var tempPostArrayDel = new Array(selEmpsPostArray[arrPostCtr]);
					tempPostDesigArray = tempPostDesigArray.concat(tempPostArrayDel);
				}
			}
			selEmpsPostArray = tempPostDesigArray;
			document.getElementById('hidden_employeeSelArray').value = selectedEmplArray;
			document.getElementById('hidden_selEmpsNameArray').value = selEmpsNameArray;
			document.getElementById('hidden_selEmpsDesigArray').value = selEmpsDesigArray;
			document.getElementById('hidden_selEmpsPostArray').value = selEmpsPostArray;
		}
    }	
	
	
	function editValue(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10)
	   {
		  
	       document.form1.hidden_empPrefix.value=obj1;						    					
	       document.form1.hidden_empFname.value=obj2;				    
	  	   document.form1.hidden_empLname.value=obj3;			    
	       document.form1.hidden_empId.value=obj4;		
	       document.form1.hidden_userId.value=obj5;				   
		   document.form1.hidden_DsgnName.value=obj6;				   				    
	       document.form1.hidden_PlaceName.value=obj7;			     
	       document.form1.hidden_gpfAccountNo.value=obj8;
	       document.form1.hidden_PostId.value=obj9;				    	   
	       document.form1.hidden_SevarthId.value=obj10;
   }	
	               
	function setSelectedUser()
	{

		
		var usernameValue=document.form1.hidden_empLname.value;
		
		var empidvalue=document.form1.hidden_empId.value
		
		var useridvalue=document.form1.hidden_userId.value
	
		var placeName=document.form1.hidden_PlaceName.value
	
		var dsgnValue=document.form1.hidden_DsgnName.value
	
		var gpfAccountNo=document.form1.hidden_gpfAccountNo.value
	
		var empPostId=document.form1.hidden_PostId.value

		var sevarthIdvalue=document.form1.hidden_SevarthId.value
		
		var empSrchName = '${name}';
		
		var forReports = document.getElementById("forReports").value;
		
		if(forReports == 'No' || forReports == '')
		{
		
			
			if(window.opener.parent.document.forms[0].Employee_ID_${name} != null)
			{
				
		
				//window.opener.parent.document.forms[0].Employee_ID_${name}.value=usernameValue;
				//window.opener.parent.document.forms[0].Employee_Post_ID_${name}.value=usernameValue;
				window.opener.parent.document.forms[0].USER_ID_${name}.value=empidvalue;
				//window.opener.parent.document.forms[0].DISTRICT_NM_${name}.value=usernameValue;
				window.opener.parent.document.forms[0].Employee_Name_${name}.value=usernameValue;
				window.opener.parent.document.forms[0].Police_ST_NM_${name}.value=dsgnValue;
				window.opener.parent.document.forms[0].Dsgn_NM_${name}.value=useridvalue;
				window.opener.parent.document.forms[0].GPF_NM_${name}.value=empPostId;
				window.opener.parent.document.forms[0].Employee_ID_${name}.value=empidvalue;
				window.opener.parent.document.forms[0].Emp_Sevarth_Id_${name}.value=sevarthIdvalue;
				//alert('empid'+window.opener.parent.document.forms[0].Employee_Name_${name}.value);
				//window.opener.parent.document..Employee_ID_.${name}.value =
				 //var url = requestURL+ "?actionFlag=payrollSearchEmployeeByAll&SearchEmployee=<%=request.getParameter("SearchEmployee")%>&hidden_OwnLocationParam=<%=request.getParameter("ownlocation")%>&hidden_functionName="+functionName+"&hidden_levelSortParam="+levelSortParam+"&hidden_childLocSrchParam="+childLocSearch+"&hidden_multiSelStatus="+multiEmplSelect+"&hidden_hidnSpecLocId="+hidnSpecLoc+"&hidden_srchNameText="+srchText+"&hidden_SearchFlag=true"+"&ajaxCallFlag=true"+"&hidden_empExpDateSrchFlag"+empExpDateSrchFlag+"&hiddenSpecDsgnCodes="+hidnSpecDsgnCodes+"&hiddenSpecDepttCodes="+hidnSpecDepttCodes;
				 //vifms.htm?actionFlag=getOtherDataMerged&otherId=301147&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES
			}
			else
			{
				
				parent.window.opener.document.getElementById("Employee_ID_${name}").value	= empidvalue; 
				parent.window.opener.document.getElementById("USER_ID_${name}").value	= useridvalue; 
				parent.window.opener.document.getElementById("Police_ST_NM_${name}").value	= placeName; 
			//	parent.window.opener.document.getElementById("DISTRICT_NM_${name}").value	= districtName; 
				parent.window.opener.document.getElementById("Dsgn_NM_${name}").value = dsgnValue; 
				parent.window.opener.document.getElementById("GPF_NM_${name}").value = GPFname;
				parent.window.opener.document.getElementById("Employee_Post_ID_${name}").value = empPostId;
				parent.window.opener.document.getElementById("Emp_Sevarth_Id_${name}").value=sevarthIdvalue;
			}		
		}
		else
		{
			
			var searchTagName = document.getElementById("srchTagName").value;
			setSelectedItem(searchTagName, usernameValue, empidvalue,sevarthIdvalue);
		}
	}


	 
    function clearListEmpSearch(listId)
    {
  	var listLength = listId.length;				    		      
	 for(var i=listId.length;i>=1;i--)
			{				    		       
			listId.remove(i);				    		        
	       }   	       
    }  
    var nxtCombo;
	
	var addrName;
	
	
	
	
	
	
	function resetAllField()
	{
		var ownLocationKey = '${searchInMyLocation}';
		if(ownLocationKey!='Yes')
		{

			
			document.form1("empFname").value='';	
			document.getElementById('dsgn').value='';
			document.form1("city").value='';
			document.form1("locPlacename").value='';
			document.form1("empId").value='';

			document.getElementById("dsgn").options[0].selected="selected";
			document.form1("city").options[0].selected="selected";
			
		}
			
		document.getElementById("dsgn").options[0].selected="selected";
		document.form1("city").options[0].selected="selected";
		document.form1("empFname").focus();
	}
	
	if (window.dialogArguments) 
	{
    	window.opener = window.dialogArguments;
  	}
	

	function getDataList()
	{
	
		var DesgnCode=document.getElementById('dsgn').value;
		var city=document.form1("city").value;
		var empIdd=document.form1("empId").value;
		
		if(empIdd>0 || empIdd!=0)
		{
			//alert('if condition');
			var empId=empIdd;
		}
		else
		{
			//alert('else condition');
		 empId=-1;
		}
		var GPFACCtNo=document.form1("locPlacename").value;
		//alert('GPFACCtNo is'+GPFACCtNo);
		//alert('city is'+city);
		
		if(GPFACCtNo>0 || GPFACCtNo!=0)
		{
			//alert('taking gpfactno');
			var GPFAcctNo=GPFACCtNo;
		}
		else
		{
			GPFAcctNo=-1;
		}
		
		var empFName=document.form1("empFname").value;
	//	alert("empFName" +empIdd);
		if(empFName>0 || empFName!=0)
		{
			var empFname=empFName;
		}
		else
		{
			empFname=-1;
		}

		var value =GPFACCtNo;
		if (isFinite(value)) 
			{ 
			//alert('numeric');
			var Flag =true;
			}
		else
		{
			//alert('non numeric');
			 Flag  = false;
		}
		//alert("empFname" +empFname);
			if(DesgnCode>0 || city>0 || empId!=null || GPFAcctNo!=null || empFname!=null)
			{
				//alert('going to service layer');
			document.form1.action="hrms.htm?actionFlag=showEmployeeDtlsFromDept&desgnCode="+DesgnCode+"&city="+city+"&empId="+empId+"&GPFAcctNo="+GPFAcctNo+"&empFname="+empFname+"&Flag="+Flag;
			//alert("action" +document.form1.action);
			document.form1.submit();
			}

			else if(city>0)
			{
			document.form1.action="hrms.htm?actionFlag=showEmployeeDtlsFromOffice&dcpsDdoOfficeIdPk="+city;
			//alert("action" +document.form1.action);
			document.form1.submit();
			}
			else if(empId>0)
			{
			document.form1.action="hrms.htm?actionFlag=showEmployeeDtlsFromEmployeeId&empId="+empId;
			//alert("action" +document.form1.action);
			document.form1.submit();
			}
			else if(GPFAcctNo>0)
			{
			document.form1.action="hrms.htm?actionFlag=showEmployeeDtlsFromGPFAcctNo&GPFAcctNo="+GPFAcctNo;
			//alert("action" +document.form1.action);
			document.form1.submit();
			}
			else 
			{
			document.form1.action="hrms.htm?actionFlag=showEmployeeDtlsFromEmployeeName&empFname="+empFname;
			//alert("action" +document.form1.action);
			document.form1.submit();
			}

			showProgressbar("Please wait<br>Your Request is in progress...");
		
	}

	



	function EnterkeyPressed(e,form)
	{	
		
		
			if ((e.keyCode==13))
			{
				document.form1("city").focus();
				getDataList();

			}
	  					
	}   
	
	
</script>	

<hdiits:form  method="POST" name="form1" validate="true" action="./hdiits.htm?actionFlag=payrollSearchEmployeeByAll" >
	<hdiits:hidden name="SearchEmployee" default='<%=request.getParameter("SearchEmployee")%>' />
	<hdiits:hidden name="checkForId" default="${empMap.checkForId}"/>
	<hdiits:hidden name="hidden_LocationPoliceStationName"></hdiits:hidden>	
		<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="Search_Employee" bundle="${lables}"/></a></li>
			</ul>
		</div>
		<div class="tabcontentForPopupstyleForPopupForPopup" style="height: 100%">
		<div id="tcontent1" class="tabcontentForPopup" tabno="0" >
		<table class="datatable" headerClass="datatableheader" width="100%" border="0" height="30%" style="tabtable">
		<tr>
			<td class="datatableheader" colspan="4" align="center"><b><hdiits:caption captionid="Please_Enter_Your_Criteria" bundle="${lables}" /></b></td>
		</tr>	
		<tr>
			<td class="fieldLabel" width="25%">Name</td>
			<td class="fieldLabel" width="25%">
			<input type="text" name="empFname" captionid="EMP_FNAME"  bundle="${lables}" onKeyDown= "return EnterkeyPressed(event,document.form1)"></td>
			
			<td class="fieldLabel" width="25%">Sevarth Id</td>
			<td class="fieldLabel" width="25%">
			<input type="text" name="empId"  id="empId" captionid="EMP_LASTNAME"  bundle="${lables}" validation=""  onKeyDown= "return EnterkeyPressed(event,document.form1)"></td>
			<%--<hdiits:number name="bankCode" caption="bankCode"   default="${actionList.bankCode}" validation="txt.isrequired,txt.isnumber"   maxlength="10"   size="25" />	</td>--%>
												
			
		</tr>
		
		
	
		<tr>
		
			<td class="fieldLabel" width="25%">G.P.F Number</td>
			<td class="fieldLabel" width="25%">
			
			
			<input type="text" name="locPlacename" captionid="LOCATION"  bundle="${commonLables}" onKeyDown= "return EnterkeyPressed(event,document.form1)"></td>
			</td>
			
			
			
			<td class="fieldLabel" width="25%"><hdiits:caption	captionid="CITY" bundle="${commonLables}" /></td>
			<td class="fieldLabel" width="25%"><hdiits:select   sort="false" name="city" captionid="CITY"	bundle="${lables}"  onchange= "return EnterkeyPressed(event,document.form1);">
				<option value="-1"  ><fmt:message key="Select"  bundle="${lables}"/></option>
				<c:forEach var="officeList" items="${officeList}">
				<hdiits:option value="${officeList.dcpsDdoOfficeIdPk}" >${officeList.dcpsDdoOfficeName}</hdiits:option>
				</c:forEach>
			</hdiits:select>				
			</td>
		
		
		</tr>
		<tr>
			<td class="fieldLabel" width="25%">Designation</td>
					<td class="fieldLabel" width="25%"><hdiits:select name="dsgn" sort="false" captionid="Dsgn_NM" 	bundle="${lables}"  onchange= "return EnterkeyPressed(event,document.form1)">				
				<option value="-1"><fmt:message key="Select"  bundle="${lables}"/></option>
				<c:forEach var="dsgnListVal" items="${dsgnList}">
				<hdiits:option value="${dsgnListVal.dsgnCode}" >${dsgnListVal.dsgnName}</hdiits:option>
				</c:forEach>	
				</hdiits:select>	
			</td>
		
			</tr>			
		<tr>
			<td  colspan="4" align="center">
			<hdiits:submitbutton type="button" captionid="Search" bundle="${lables}"  name="searchButton" onclick="getDataList()" />				
			<hdiits:button type="button" captionid="RESETBTN"  bundle="${lables}" name="btnReset" onclick="resetAllField()" /></td>
		</tr>
	</table>
	
	<hdiits:hidden name="hidden_empFname"></hdiits:hidden> 
	<hdiits:hidden name="hidden_empId"></hdiits:hidden> 
	<hdiits:hidden name="hidden_empLname" ></hdiits:hidden> 
	<hdiits:hidden name="hidden_empPrefix"></hdiits:hidden> 
	<hdiits:hidden name="hidden_userId"></hdiits:hidden> 
	<hdiits:hidden name="hidden_PlaceName"></hdiits:hidden>
	<hdiits:hidden name="hidden_DsgnName"></hdiits:hidden> 
	<hdiits:hidden name="hidden_gpfAccountNo"></hdiits:hidden>
	<hdiits:hidden name="hidden_PostId"></hdiits:hidden>	
	<hdiits:hidden name="hidden_OwnLocationParam" default="${searchInMyLocation}"></hdiits:hidden>
	<hdiits:hidden name="hidden_SearchFlag"></hdiits:hidden>
	<hdiits:hidden name="hidden_functionName" default="${empMap.functionName}"></hdiits:hidden>
	<hdiits:hidden name="hidden_levelSortParam" default="${empMap.levelSortParam}"></hdiits:hidden>
	<hdiits:hidden name="hidden_hidnSpecLocId" default="${empMap.hidnSpecLocId}"></hdiits:hidden>
	<hdiits:hidden name="hidden_childLocSrchParam" default="${empMap.childLocSrchParam}"></hdiits:hidden>
	<hdiits:hidden name="hidden_multiSelStatus" default="${empMap.multiEmplSelect}"></hdiits:hidden>
	<hdiits:hidden name="hidden_employeeSelArray" default="${empMap.prevSeldEmpls}"></hdiits:hidden>
	<hdiits:hidden name="hidden_selEmpsNameArray" default="${empMap.prevSeldEmplsName}"></hdiits:hidden>
	<hdiits:hidden name="hidden_selEmpsDesigArray" default="${empMap.prevSeldEmplsDesig}"></hdiits:hidden>
	<hdiits:hidden name="hidden_selEmpsPostArray" default="${empMap.prevSeldEmplsPost}"></hdiits:hidden>
	<hdiits:hidden name="hidden_PageNoForDisp"></hdiits:hidden>
	<hdiits:hidden name="hidden_srchTextFlag" default="${empMap.srchNameTextFlag}"></hdiits:hidden>
	<hdiits:hidden name="hidden_srchNameText" default="${empMap.srchNameText}"></hdiits:hidden>
	<hdiits:hidden name="forReports" default="${empMap.forReports}"></hdiits:hidden>	
	<hdiits:hidden name="srchTagName" default="${empMap.srchTagName}"></hdiits:hidden>
	<hdiits:hidden name="hidden_empExpDateSrchFlag" default="${empMap.empExpSrchFlag}"></hdiits:hidden>
	<hdiits:hidden name="hiddenSpecDsgnCodes" default="${empMap.hiddenDsgnCodes}"></hdiits:hidden>
	<hdiits:hidden name="hiddenSpecDepttCodes" default="${empMap.hiddenSpecDepttCodes}"></hdiits:hidden>
	<hdiits:hidden name="hidden_SevarthId"></hdiits:hidden>		
	
	<c:set var="filteredEmpIdArr" value='<%=request.getParameter("checkForId")%>' />
	
	<%	List temp = (List) pageContext.getAttribute("finalEmpList");
				if (temp != null) 
				{
					if (temp.size() > 0) 
					{	%>				
		
		
				<display:table pagesize="${numberResultsPerPage}" name="${finalEmpList}" id="row" requestURI="" export="false" style="width:100%">		
					
				
				
				<display:column  class="tablecelltext" title="Select" headerClass="datatableheader" style="background: ${colorVal}">
						<hdiits:radio readonly="${disabledFlag}" name="Select" value="${row}" onclick="editValue('${row.empPrf}','${row.empFname}','${row.empId}','${row.userId}','${row.dsgnName}','${row.officeName}',' ${row.userName}','${row.postId}','${row.gpfAccountNo}','${row.sevarthEmpCode}' );" />
				</display:column>	
											
				<display:column class="tablecelltext" sortable="true"property="empFullname" title="Employee Name"headerClass="datatableheader" style="background: ${colorVal}"></display:column>
					
				
				<display:column class="tablecelltext" sortable="true"property="sevarthEmpCode" title="Sevarth Id" headerClass="datatableheader" style="background: ${colorVal}">	</display:column>
				<display:column class="tablecelltext" sortable="true"property="gpfAccountNo" title="Gpf AcctNumber" headerClass="datatableheader" style="background: ${colorVal}">	</display:column>
				<display:column class="tablecelltext" sortable="true"property="dsgnName" title="Designation Name" headerClass="datatableheader" style="background: ${colorVal}"></display:column>
				<display:column class="tablecelltext" sortable="true"property="officeName" title="Office Name"headerClass="datatableheader" style="background: ${colorVal}"></display:column>
				<display:column class="tablecelltext" sortable="true"property="empId" title=""headerClass="datatableheader" style="background: ${colorVal};display:none;">	</display:column>		
				<%-- <display:setProperty name="paging.banner.page.link" 
			value='<a href="javascript:showPageForSectionDispTable({0},0)">{0}</a>'>
		</display:setProperty>
		
		<display:setProperty name="paging.banner.first" >
			<span class="pagelinks">
			[${dispFirst}/${dispPrev}] 
			{0}
			[<a href="javascript:showPageForSectionDispTable({5},1)">${dispNext}</a>
			/<a href="javascript:showPageForSectionDispTable({6},0)">${dispLast}</a>]
			</span>
		</display:setProperty>
		
		<display:setProperty name="paging.banner.full">
			<span class="pagelinks">		
			[<a href="javascript:showPageForSectionDispTable(1,0)">${dispFirst}</a>
			/
			<a href="javascript:showPageForSectionDispTable({5},-1)">${dispPrev}</a>]
			{0}
			[<a href="javascript:showPageForSectionDispTable({5},1)">${dispNext}</a>	
			/
			<a href="javascript:showPageForSectionDispTable({6},0)">${dispLast}</a>]
			</span>
		</display:setProperty>
		
		<display:setProperty name="paging.banner.last">
		<span class="pagelinks">
		[<a href="javascript:showPageForSectionDispTable(1,0)">${dispFirst}</a>
		/
		<a href="javascript:showPageForSectionDispTable({5},-1)">${dispPrev}</a>]
		{0} 
		[${dispNext}/${dispLast}]
		</span>
		</display:setProperty>--%>
		</display:table>	
		<!-- END : SET PROPERTY OF DISPLAY TAG -->
				
					
				
			<table align="center"  border="0">
					<tr>
						<td>
							<hdiits:button type="button" captionid="RETWITHSEL" bundle="${lables}" name="Button" onclick="windowclose()" />			
						</td>
					</tr>
				</table>
			<%} else { %>
				<fmt:message bundle="${lables}" key="LIST_EMPTY"/>
			<% 	} } %>

	
	</div>
	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	
	
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
	}
	catch (Exception e) {
		e.printStackTrace();
	}
%>

