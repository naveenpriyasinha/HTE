<%
try
{
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp" %>
<%@ page import="java.util.*" %>
<%@ page language="java" import="com.tcs.sgv.core.valueobject.ResultObject" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<fmt:setBundle basename="resources.ess.Lables" var="lables" scope="request"/>
<%
	String reqUniqueKey=request.getParameter("SearchEmployee");
	ResultObject result=(ResultObject)request.getAttribute("result");
	Map empSearchMap =(Map)result.getSessionValues(); 
	Object uniqueKey=null;

	//System.out.println("empSearchMap  ==>"+empSearchMap);
	
	if(empSearchMap!= null) 
	{
		
		//System.out.println("with search   ==>");
 		uniqueKey=empSearchMap.get("empSearchVO"+reqUniqueKey);
 		//System.out.println("uniqueKey   ==>" +uniqueKey);
 		if(uniqueKey == null)
 		{
 			Map empSearchMapTemp = (Map)result.getResultValue();
 			uniqueKey = empSearchMapTemp.get("empSearchVO"+reqUniqueKey);
 		}
 	}
	else
	{
		//System.out.println("with out search==>");
		
		Map empSearchMapTemp = (Map)result.getResultValue();
		if(empSearchMapTemp!= null)
		{
			//System.out.println("uniqueKey   ==>" +empSearchMapTemp.get("empSearchVO"+reqUniqueKey));
 			uniqueKey = empSearchMapTemp.get("empSearchVO"+reqUniqueKey);
		}
	}
	
%>

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="name" value='<%=request.getParameter("SearchEmployee")%>' />
<c:set var="mandatory" value='<%=request.getParameter("mandatory")%>' />
<c:if test="${mandatory eq 'Yes'}">
<c:set var="mandatoryInd" value="true" />
</c:if>
<c:set var="condition" value='<%=request.getParameter("condition")%>' />
<c:set var="empSearchVO" value="<%=uniqueKey %>" />


<c:set var="title" value='<%=request.getParameter("searchEmployeeTitle")%>' />
<c:set var="resultObj" value="${result}"></c:set> 
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>

<c:set var="OfficeName" value="${resValue.OfficeName}"></c:set>

<c:set var="viewModeSearchEmployee" value="viewMode_${name}" />
<c:set var="readOnly" value="${resultMap[viewModeSearchEmployee]}"></c:set> 
<c:set var="ownLocation" value='<%=request.getParameter("ownlocation")%>'></c:set>
<c:set var="funcName" value='<%=request.getParameter("functionName")%>'></c:set>
<c:set var="postSortParam" value='<%=request.getParameter("postLevelSortParam")%>'></c:set>
<c:set var="childLocnSearch" value='<%=request.getParameter("childLocSearch")%>'></c:set>
<c:set var="multipleEmployeeSel" value='<%=request.getParameter("multiEmployeeSel")%>'></c:set>
<c:set var="childLocnSearch" value='<%=request.getParameter("childLocSearch")%>'></c:set>
<fmt:message var="noResMsg" key="NORESULTS" bundle="${lables}"></fmt:message>
<script language="javascript">
  
	function resetSearchEmployee(SearchEmployee)
  { 			 
		    var searchEmployeeParams = new Array();		   	    
		    searchEmployeeParams[0]="Employee_Name_"+SearchEmployee;
			searchEmployeeParams[1]="Employee_ID_"+SearchEmployee;
			searchEmployeeParams[2]="USER_ID_"+SearchEmployee;
			searchEmployeeParams[3]="GPF_NM_"+SearchEmployee;
			searchEmployeeParams[4]="Dsgn_NM_"+SearchEmployee;
			searchEmployeeParams[5]="Police_ST_NM_"+SearchEmployee;

	    	
		   //  var empName   = document.getElementsByName(searchEmployeeParams[0]);
		     var empId     = document.getElementsByName(searchEmployeeParams[1]);
			 var userId    = document.getElementsByName(searchEmployeeParams[2]);
			 var gpfName   = document.getElementsByName(searchEmployeeParams[3]);
			 var dsgnName  = document.getElementsByName(searchEmployeeParams[4]);
			 var psName    = document.getElementsByName(searchEmployeeParams[5]);
		
		 
		  if(empId!= null)
		 {
		   if(empId[0]!=null)
		   {
	   	     empId[0].value="";
		    }
		 }
		 
		  if(userId!= null)
		 {
		   if(userId[0]!= null)
		   {
		     userId[0].value="";
		    }
		 }
		    		   		
		 if(gpfName!= null)
		 {
		    if(gpfName[0]!= null)
		    {
		      gpfName[0].value="";
		    }
		 }		   	  
		   
		 if(dsgnName!= null)
		 {
		   if(dsgnName[0]!= null)
		   {
		      dsgnName[0].value="";
		    }
		 }
		 
		  if(psName!= null)
		 {		   
		   if(psName[0]!= null)
		   {
		      psName[0].value="";
		    }
		 }
		   
	 }

	function getXPathValueFromDOMMY(xmlDom,xPath)
	{
		if(getXPathValueFromDOM(xmlDom,xPath)==null)
			return '';
		else
			return getXPathValueFromDOM(xmlDom,xPath);
	}
   function editSearchEmployee(SearchEmployee,xmlDOM,searchEmpXPath)
  {    

  	if(searchEmpXPath == undefined)
  	{
  		editSearchEmployeeOld(SearchEmployee,xmlDOM);
  		return;
  	}
//	alert ("searchEmpXPath :"+searchEmpXPath.length );




	
	var searchEmployeeParams = new Array();     
   // var empName		= null;
    var empId  		= null;
    var userId  	= null;
    var gpfName  	= null;
    var dsgnName  	= null;
    var	psName  	= null; 
    var dtName  	= null;
	//	condition added by 
	//	202414	- CHIRAGKUMAR SHAH.
	// 	IN CASE XML FILE GENERATED DIRECTLY SEARCH EMPLOYEE VO 
	//	PROGRAMMER PASS ZERO LENGTH STRING ('') AS A THIRD ARGUMENT OF 
	//  editSearchEmployee (...,...,...) FUNCTION.	
	//	
	
	if(searchEmpXPath.length == 0)    
    {
    //	empName  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'empFname');	
    	empId  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'empId');
    	
    	userId  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'userId');
    	gpfName  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'userName');
    	dsgnName  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'dsgnName');
    	psName  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'locPlacename');
    } 		
	else
	{
    	//empName  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'/empFname');
    		
    	empId  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'/empId');
    	userId  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'/userId');
    	gpfName  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'/userName');
    	dsgnName  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'/dsgnName');
    	psName  = getXPathValueFromDOMMY(xmlDOM,searchEmpXPath +'/locPlacename');
	}
					//alert("coming"); 
		searchEmployeeParams[1]="Employee_Name_"+SearchEmployee;
		searchEmployeeParams[2]="Employee_ID_"+SearchEmployee;
		searchEmployeeParams[3]="USER_ID_"+SearchEmployee;
		searchEmployeeParams[4]="GPF_NM_"+SearchEmployee;
		searchEmployeeParams[5]="Dsgn_NM_"+SearchEmployee;
		searchEmployeeParams[6]="Police_ST_NM_"+SearchEmployee;
			
		// var empNameArr   = document.getElementsByName(searchEmployeeParams[1]);
		 var empIdArr     = document.getElementsByName(searchEmployeeParams[2]);
	     var userIdArr    = document.getElementsByName(searchEmployeeParams[3]);
		 var gpfNameArr   = document.getElementsByName(searchEmployeeParams[4]);
		 var dsgnNameArr  = document.getElementsByName(searchEmployeeParams[5]);
		 var psNameArr    = document.getElementsByName(searchEmployeeParams[6]);
		
	
				
			    
			    if(empIdArr!= null)
				 {
				   if(empIdArr[0]!=null)
				    {
				     empIdArr[0].value=empId;
				     }
		 	    }
			 
			    if(userIdArr!= null)
				 {
				   if(userIdArr[0]!= null)
				   {
				    userIdArr[0].value=userId;
				    }
				 }
			   if(gpfNameArr!= null)
				 {
				    if(gpfNameArr[0]!= null)
				    {
				    gpfNameArr[0].value=gpfName;
				    }
				 }
			    				 
			   if(dsgnNameArr!= null)
				 {
				    if(dsgnNameArr[0]!= null)
				    {
				    dsgnNameArr[0].value=dsgnName;
				    }
				 } 
				 
			   if(psNameArr!= null)
		 		{
				    if(psNameArr[0]!= null)
				    {				      
				    psNameArr[0].value=psName;
				    }
		 		}
		 		 
				
		 		
	 }
	
	function editSearchEmployeeOld(SearchEmployee,xmlDOM)
	{    
    var searchEmployeeParams = new Array(); 

    var empName=getValueFromDOM(xmlDOM, 'empFname');
    var empId=getValueFromDOM(xmlDOM, 'empId');
    var userId=getValueFromDOM(xmlDOM, 'userId');
    var gpfName=getValueFromDOM(xmlDOM, 'userName');
    var dsgnName=getValueFromDOM(xmlDOM, 'dsgnName');
    var psName=getValueFromDOM(xmlDOM, 'locPlacename');

    		searchEmployeeParams[1]="Employee_Name_"+SearchEmployee;
			searchEmployeeParams[2]="Employee_ID_"+SearchEmployee;
			searchEmployeeParams[3]="USER_ID_"+SearchEmployee;
			searchEmployeeParams[4]="GPF_NM_"+SearchEmployee;
			searchEmployeeParams[5]="Dsgn_NM_"+SearchEmployee;
			searchEmployeeParams[6]="Police_ST_NM_"+SearchEmployee;
			
		 var empIdArr     = document.getElementsByName(searchEmployeeParams[2]);
	     var userIdArr    = document.getElementsByName(searchEmployeeParams[3]);
		 var gpfNameArr   = document.getElementsByName(searchEmployeeParams[4]);
		 var dsgnNameArr  = document.getElementsByName(searchEmployeeParams[5]);
		 var psNameArr    = document.getElementsByName(searchEmployeeParams[6]);
		
	
				 
			    
			    if(empIdArr!= null)
				 {
				   if(empIdArr[0]!=null)
				    {
				     empIdArr[0].value=empId;
				     }
		 	    }
			 
			    if(userIdArr!= null)
				 {
				   if(userIdArr[0]!= null)
				   {
				    userIdArr[0].value=userId;
				    }
				 }
			   if(gpfNameArr!= null)
				 {
				    if(gpfNameArr[0]!= null)
				    {
				    gpfNameArr[0].value=gpfName;
				    }
				 }
			    				 
			   if(dsgnNameArr!= null)
				 {
				    if(dsgnNameArr[0]!= null)
				    {
				    dsgnNameArr[0].value=dsgnName;
				    }
				 } 
				 
			   if(psNameArr!= null)
		 		{
				    if(psNameArr[0]!= null)
				    {				      
				    psNameArr[0].value=psName;
				    }
		 		}
		 		 
				
	 }
	 
	function SearchEmployeeParameters(SearchEmployee)
	{
		var searchEmployeeParams = new Array();
			searchEmployeeParams[0]="Employee_Name_"+SearchEmployee;
			searchEmployeeParams[1]="Employee_ID_"+SearchEmployee;
			searchEmployeeParams[2]="USER_ID_"+SearchEmployee;
			searchEmployeeParams[3]="GPF_NM_"+SearchEmployee;
			searchEmployeeParams[4]="Dsgn_NM_"+SearchEmployee;
			searchEmployeeParams[5]="Police_ST_NM_"+SearchEmployee;
	
		return searchEmployeeParams;
	}
	
	
	/* clearEmployee function is called when "clear" link is clicked. It clears employee details from
	   searchEmployee.jsp. A developer can call his/her function on click on "clear" link.
	   For this, he/she must pass following param and value : 
	   param name="functionNameForClearButton" value="remSelectedEmp()witness"
	   in the above code , "remSelectedEmp()" is your function and "witness" is the EmployeeSearchName.
	*/
	
	function clearEmployee(EmployeeSearchName)
	{

		//alert("clear");
		
		var funcNameForClearLink = '<%=request.getParameter("functionNameForClearButton")%>';
		var charIndex = funcNameForClearLink.indexOf(')');
		var funcName = funcNameForClearLink.substring(0,charIndex-1) +"()";
		var appendName = funcNameForClearLink.substring(charIndex+1); 
		if(appendName==EmployeeSearchName)
		{
			eval(funcName);
		}

		//alert("clear1");
		document.getElementById("Employee_Name_"+EmployeeSearchName).value='';
		document.getElementById("Employee_ID_"+EmployeeSearchName).value='';
		document.getElementById("USER_ID_"+EmployeeSearchName).value='';
		document.getElementById("GPF_NM_"+EmployeeSearchName).value='';
		document.getElementById("Dsgn_NM_"+EmployeeSearchName).value='';
		document.getElementById("Police_ST_NM_"+EmployeeSearchName).value='';
		document.getElementById("Emp_Sevarth_Id_"+EmployeeSearchName).value='';
	}
	
	
		
	
	function testCall()
	{
		//alert("testCall");
		return true;
	}
	
	


	var emplWindowObj=null; 			 
	function GoToNewPage<%=request.getParameter("SearchEmployee")%>() 
	{




		if(document.getElementById("cmpType").value=="")
		{
			alert("Please select type of component first");
			document.getElementById("cmpType").focus();
			return false;
		}
		else if(document.getElementById("payItemCombo").value=="")
		{
			alert("Please select pay item first");
			document.getElementById("payItemCombo").focus();
			return false;
		}
			
    	var ownlocation='<%=request.getParameter("ownlocation")%>';
    
    	var childLocSearch = '<%=request.getParameter("childLocSearch")%>';
    	var functionName='<%=request.getParameter("functionName")%>';
    	var levelSortParam='<%=request.getParameter("postLevelSortParam")%>';
    	var searchEmpName = '<%=request.getParameter("SearchEmployee")%>';

    	//alert("searchEmpName");

    		
    	var multiEmplSelect ='<%=request.getParameter("multiEmployeeSel")%>';
    	/* default value for empExpDateSrchFlag is "Yes".It will display presently active employees
    	   only. 
    	   If empExpDateSrchFlag is "YesRetd" , then it will display employees who have retired from service.*/
    	var empExpDateSrchFlag ='<%=request.getParameter("empExpDateSrchFlag")%>';
    	var hidnSpecLoc ;
    	var hidnSpecDsgnCodes ;
    	var hidnSpecDepttCodes ;
    	var forReports='No' ;
    	var srchText = document.getElementById('Employee_srchNameText_<%=request.getParameter("SearchEmployee")%>').value;
    	if(document.getElementById('hidden_locSpec_<%=request.getParameter("SearchEmployee")%>'))
    	{
	    	//alert("hidnSpecLoc"+hidnSpecLoc);
    		hidnSpecLoc = document.getElementById('hidden_locSpec_<%=request.getParameter("SearchEmployee")%>').value;
    	}
    	else
    	{
    		hidnSpecLoc = '';
    	}
    	if(document.getElementById('hidden_dsgnCodesSpec_<%=request.getParameter("SearchEmployee")%>'))
    	{
	    	//alert("hidnSpecLoc"+hidnSpecLoc);
    		hidnSpecDsgnCodes = document.getElementById('hidden_dsgnCodesSpec_<%=request.getParameter("SearchEmployee")%>').value;
    	}
    	else
    	{
    		hidnSpecDsgnCodes = '';
    	}
    	if(document.getElementById('hidden_depttCodesSpec_<%=request.getParameter("SearchEmployee")%>'))
    	{
	    	//alert("hidnSpecLoc"+hidnSpecLoc);
    		hidnSpecDepttCodes = document.getElementById('hidden_depttCodesSpec_<%=request.getParameter("SearchEmployee")%>').value;
    	}
    	else
    	{
    		hidnSpecDepttCodes = '';
    	}

    	if(empExpDateSrchFlag=='null')
    	{
    		empExpDateSrchFlag="Yes";
    	}  
    	
//    	alert("hidnSpecLoc 234234"+hidnSpecLoc);
    	if(multiEmplSelect=='null')
    	{
    		multiEmplSelect="none";
    	}   
    	if(levelSortParam == 'null')
    	{
    	levelSortParam="none";
    	}    	
    	var urlstyle = 'height=550,width=700,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=50,left=200';
		var requestURL= '${rootUrl}';

		
		if(functionName == 'null')
		{
			functionName = "testCall";
		}
		//alert("srchTextValue is "+srchText);
		if(srchText == '')
		{
			
			var url = requestURL+ "?actionFlag=payrollFindemployee&SearchEmployee=<%=request.getParameter("SearchEmployee")%>&ownlocation=<%=request.getParameter("ownlocation")%>&functionName="+functionName+"&levelSortParam="+levelSortParam+"&childLocSearch="+childLocSearch+"&multiEmplSelect="+multiEmplSelect+"&hidSpecLocId="+hidnSpecLoc+"&name_srchNameText="+srchText+"&empExpDateSrchFlag="+empExpDateSrchFlag+"&hiddenSpecDsgnCodes="+hidnSpecDsgnCodes+"&hiddenSpecDepttCodes="+hidnSpecDepttCodes;
			
			//alert(url);
			if(emplWindowObj==null)
	   		{
	   			//alert("srchText");
	   			emplWindowObj = displayModalDialog(url,"Show",urlstyle); 
	   		}
	   		else
	   		{
	   			emplWindowObj.close();
	   			emplWindowObj = displayModalDialog(url,"Show",urlstyle); 
	   		}   		
		}
		else
		{
			
			try
		    {   
		    	xmlHttpFirstLevel=new XMLHttpRequest();    
		    }
			catch (e)
			{   
				try
	      		{
	      		    xmlHttpFirstLevel=new ActiveXObject("Msxml2.XMLHTTP");       		       
	      		}
			    catch (e)
			    {
			          try
	        		  {
	        		      xmlHttpFirstLevel=new ActiveXObject("Microsoft.XMLHTTP");     
	           		  }
				      catch (e)
				      {
				              alert("Your browser does not support AJAX!");     
				              return false;        
				      }
				 }
	        }
	        var url = requestURL+ "?actionFlag=payrollSearchEmployeeByAll&SearchEmployee=<%=request.getParameter("SearchEmployee")%>&hidden_OwnLocationParam=<%=request.getParameter("ownlocation")%>&hidden_functionName="+functionName+"&hidden_levelSortParam="+levelSortParam+"&hidden_childLocSrchParam="+childLocSearch+"&hidden_multiSelStatus="+multiEmplSelect+"&hidden_hidnSpecLocId="+hidnSpecLoc+"&hidden_srchNameText="+srchText+"&hidden_SearchFlag=true"+"&ajaxCallFlag=true"+"&hidden_empExpDateSrchFlag"+empExpDateSrchFlag+"&hiddenSpecDsgnCodes="+hidnSpecDsgnCodes+"&hiddenSpecDepttCodes="+hidnSpecDepttCodes;
	       
		        //alert(url);
			xmlHttpFirstLevel.open("POST", encodeURI(url) , false);		
			xmlHttpFirstLevel.onreadystatechange =  processResponseFromSearch;
			xmlHttpFirstLevel.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttpFirstLevel.send(encodeURIComponent(null));
		}
		
        
        //url = requeswtURL+"?viewName=findemployee&SearchEmployee=<%=request.getParameter("SearchEmployee")%>"
   		//alert(url);
   		var srchCntVal = document.getElementById("srchCountVal_<%=request.getParameter("SearchEmployee")%>").value;
   		
   		if(srchCntVal == 'MoreThanOne')
   		{
   		//	alert('1');
   			 url = requestURL+ "?actionFlag=payrollSearchEmployeeByAll&SearchEmployee=<%=request.getParameter("SearchEmployee")%>&hidden_OwnLocationParam=<%=request.getParameter("ownlocation")%>&hidden_functionName="+functionName+"&hidden_levelSortParam="+levelSortParam+"&hidden_childLocSrchParam="+childLocSearch+"&hidden_multiSelStatus="+multiEmplSelect+"&hidden_hidnSpecLocId="+hidnSpecLoc+"&hidden_srchNameText="+srchText+"&hidden_SearchFlag=true"+"&lstResultsPerPage=-1"+"&hidden_empExpDateSrchFlag"+empExpDateSrchFlag+"&hiddenSpecDsgnCodes="+hidnSpecDsgnCodes+"&hiddenSpecDepttCodes="+hidnSpecDepttCodes;
   		
    			//alert(url);
			if(emplWindowObj==null)
	   		{
				
	   			emplWindowObj = displayModalDialog(url,"Show",urlstyle); 
	   		}
	   		else
	   		{
	   			emplWindowObj.close();
	   			emplWindowObj = displayModalDialog(url,"Show",urlstyle); 
	   		}   		
   		}
   		//window.open(url);    	 	  	
	}
	
	function processResponseFromSearch()
	{

		
		{	
			if (xmlHttpFirstLevel.readyState == 4) 
			{    
				if (xmlHttpFirstLevel.status == 200) 
				{    
				    	var XMLDoc=xmlHttpFirstLevel.responseXML.documentElement;
				    	//alert(XMLDoc);
				    	var entries = XMLDoc.getElementsByTagName('srchResults');
//				    	alert(entries);
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					var empSrchName = entries[i].childNodes[0].text;   
	     					   
	     				    var srchCount = entries[i].childNodes[1].text;
							var srchCntHidnCtrl = "srchCountVal_" + empSrchName;
							document.getElementById(srchCntHidnCtrl).value = srchCount;
	     				    if(srchCount == 'One')
	     				    {
	     				    	var txtEmpnameCtrl = "Employee_Name_" + empSrchName;
	     				    	var txtEmpLocCtrl = "Police_ST_NM_" + empSrchName;
	     				    	var txtEmpDesigCtrl = "Dsgn_NM_" + empSrchName;
	     				    	var txtEmpUserNameCtrl = "GPF_NM_" + empSrchName;
	     				    	var txtEmpIdCtrl = "Employee_ID_" + empSrchName;
	     				    	
	     				    	var txtEmpUserIdCtrl = "USER_ID_" + empSrchName;
	     				    	var txtEmpPostIdCtrl = "Employee_Post_ID_" + empSrchName;
	     				    	var txtEmpSevIdCtrl = "Emp_Sevarth_Id_" + empSrchName;
	     				    	document.getElementById(txtEmpIdCtrl).value	= entries[i].childNodes[2].text;
	     				    	document.getElementById(txtEmpnameCtrl).value	= entries[i].childNodes[2].text;
	     				    	document.getElementById(txtEmpLocCtrl).value	= entries[i].childNodes[4].text;
	     				    	document.getElementById(txtEmpDesigCtrl).value	= entries[i].childNodes[6].text;
	     				    	document.getElementById(txtEmpUserNameCtrl).value	= entries[i].childNodes[7].text;
	     				    	document.getElementById(txtEmpUserIdCtrl).value	= entries[i].childNodes[8].text;
	     				    	//alert(entries[i].childNodes[10].text);
	     				    	document.getElementById(txtEmpSevIdCtrl).value	= entries[i].childNodes[10].text;
	     				    	var funcCallName = entries[i].childNodes[9].text;
	     				    	//alert("funcCallName"+funcCallName);
	     				    	if(funcCallName != '')
	     				    	{
	     				    		eval(funcCallName+"()");
	     				    	}
	     				    }
	     				    if(srchCount == 'Zero')
	     				    {
	     				    	var resMsgDisp = '${noResMsg}';
	     				    	alert(resMsgDisp);
	     				    }
	     				}
	     		}
	     	}		
		}
	}
	
	
</script>

<hdiits:fieldGroup titleCaption="${title}" mandatory="${mandatoryInd}" > 

<hdiits:table align="right">
<tr>
<c:if test="${readOnly ne true }">
<td><hdiits:a href="javascript:clearEmployee('${name}')" captionid="CLEARDETAILS" bundle="${lables}"></hdiits:a></td>
</c:if>
</tr>
</hdiits:table>

<hdiits:table align="left" >
<tr>
<td>
Search With Name/SevaarthId</td>
<td><hdiits:text name="Employee_srchNameText_${name}" captionid="PRESRCHNAME" id="txtSearchName" bundle="${lables}"  readonly="${readOnly}"/>
</td>
<td width="50%" align="left" colspan="3">
   	<hdiits:button     type="button" captionid="Search_Employee"  bundle="${commonLables}" value="Search Employee" name="search_${name}"  onclick="GoToNewPage${name}()" readonly="${readOnly}" />
<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span>
</td>
</tr>
<tr>  
    <c:choose>    
		<c:when test="${mandatory eq 'Yes'}">         
		   	<td class="fieldLabel" width="25%" style="display:none">Employee Id</td>	   	
	    	  	<c:choose>
		    	    <c:when test="${empty condition}">  		        	     
		        		<td style="display:none"><hdiits:text  name="Employee_Name_${name}"  readonly="true"  mandatory="false" validation="txt.isrequired"  captionid="EMP"  bundle="${lables}" ></hdiits:text></td>		
			        </c:when>		        	        	        						
					<c:otherwise>								
						<td style="display:none"><hdiits:text  condition="${condition}" default="${empSearchVO.empLname} " name="Employee_Name_${name}"  readonly="true"  mandatory="true" captionid="EMP" validation="txt.isrequired"   bundle="${lables}" ></hdiits:text></td>	 						
					</c:otherwise> 								    
				</c:choose>
    	 </c:when>  
   
	    <c:otherwise>  
    	   	<td class="fieldLabel" width="25%" style="display:none">Employee Id</td>
   			 <c:choose>
				 <c:when test="${empty empSearchVO}">
	   				 <td style="display:none"><hdiits:text name="Employee_Name_${name}"  readonly="true"  captionid="EMP"  bundle="${lables}" ></hdiits:text></td>		
   				 </c:when>
   		 	    <c:otherwise> 
 				    <td style="display:none"><hdiits:text default="${empSearchVO.empId}" name="Employee_Name_${name}"  readonly="true"  captionid="EMP"  bundle="${lables}" ></hdiits:text></td>		
	 		    </c:otherwise>
			</c:choose>
	   </c:otherwise>
   </c:choose>         
   
    <c:choose>    
		<c:when test="${mandatory eq 'Yes'}">         
		   	 <td class="fieldLabel" width="25%">Sevaarth Id</td>	   	
	    	  	<c:choose>
		    	    <c:when test="${empty condition}">  		        	     
		        		 <td><hdiits:text  name="Emp_Sevarth_Id_${name}"  readonly="true"  mandatory="false" validation="txt.isrequired"  captionid="EMP"  bundle="${lables}" ></hdiits:text></td>		
			        </c:when>		        	        	        						
					<c:otherwise>								
						<td><hdiits:text  condition="${condition}" default="${empSearchVO.empLname} " name="Emp_Sevarth_Id_${name}"  readonly="true"  mandatory="true" captionid="EMP" validation="txt.isrequired"   bundle="${lables}" ></hdiits:text></td>	 						
					</c:otherwise> 								    
				</c:choose>
    	 </c:when>  
   
	    <c:otherwise>  
    	   	<td class="fieldLabel" width="25%">Sevaarth Id</td>	 
   			 <c:choose>
				 <c:when test="${empty empSearchVO}">
	   				 <td><hdiits:text  name="Emp_Sevarth_Id_${name}"  readonly="true"  mandatory="false" validation="txt.isrequired"  captionid="EMP"  bundle="${lables}" ></hdiits:text></td>		
   				 </c:when>
   		 	    <c:otherwise> 
 				    <td><hdiits:text default="${empSearchVO.empId}" name="Emp_Sevarth_Id_${name}"  readonly="true"  captionid="EMP"  bundle="${lables}" ></hdiits:text></td>		
	 		    </c:otherwise>
			</c:choose>
	   </c:otherwise>
   </c:choose>
   
 <%--   <td class="fieldLabel" width="25%"><hdiits:caption captionid="LOCATION" bundle="${lables}"/></td>
   <c:choose>
		<c:when test="${empty empSearchVO}">   
		    <td><hdiits:text name="Police_ST_NM_${name}"  readonly="true"  captionid="LOCATION"  bundle="${lables}" ></hdiits:text></td>		
	    </c:when>
    	<c:otherwise>  
		     <td><hdiits:text default="${empSearchVO.locPlacename}" name="Police_ST_NM_${name}"  readonly="true"  captionid="LOCATION"   bundle="${lables}" ></hdiits:text></td>		
	     </c:otherwise>
    </c:choose>        	       
 --%>
 <%-- 
  <td class="fieldLabel" width="25%">Office Name</td>
   <c:choose>
		<c:when test="${empty empSearchVO}">   
		    <td><hdiits:text name="Police_ST_NM_${name}"  readonly="true"  captionid="LOCATION"  bundle="${lables}" ></hdiits:text></td>		
	    </c:when>
    	<c:otherwise>  
		     <td><hdiits:text default="${empSearchVO.locPlacename}" name="Police_ST_NM_${name}"  readonly="true"  captionid="LOCATION"   bundle="${lables}" ></hdiits:text></td>		
	     </c:otherwise>
    </c:choose>
    --%>
   <td class="fieldLabel" width="25%"><hdiits:caption captionid="Dsgn_NM" bundle="${lables}"/></td>
	<c:choose>
		<c:when test="${empty empSearchVO}">
		    <td><hdiits:text name="Dsgn_NM_${name}"  readonly="true"  captionid="Dsgn_NM"  bundle="${lables}" ></hdiits:text></td>		
	    </c:when>
    	<c:otherwise>  
		    <td><hdiits:text default="${empSearchVO.dsgnName}" name="Dsgn_NM_${name}"  readonly="true"  captionid="Dsgn_NM"   bundle="${lables}" ></hdiits:text></td>		
	    </c:otherwise>
    </c:choose>
  <td></td>
  </tr>
  <tr>
   	 	
 	<td class="fieldLabel" width="25%" style="display:none"><hdiits:caption captionid="DISTRICT_NM" bundle="${lables}"/></td>
 	<c:choose>
		<c:when test="${empty empSearchVO}">   
		    <td  style="display:none"><hdiits:text name="DISTRICT_NM_${name}"  readonly="true" captionid="DISTRICT_NM"  bundle="${lables}" ></hdiits:text></td>		
	    </c:when>
    	<c:otherwise>  
		     <td  style="display:none"><hdiits:text  default="${empSearchVO.locDistrictName}"name="DISTRICT_NM_${name}"  readonly="true"  captionid="DISTRICT_NM"  bundle="${lables}" ></hdiits:text></td>		
	     </c:otherwise>
    </c:choose>
    
     <td class="fieldLabel" width="25%"><hdiits:caption captionid="GPF_NM" bundle="${lables}"/></td>
    <c:choose>
		<c:when test="${empty empSearchVO}">
		       <td><hdiits:text name="GPF_NM_${name}"  readonly="true"  captionid="GPF_NM"  bundle="${lables}" ></hdiits:text></td>		
	    </c:when>
    	<c:otherwise>  
			    <td><hdiits:text default="${empSearchVO.userName}" name="GPF_NM_${name}"  readonly="true"  captionid="GPF_NM"  bundle="${lables}" ></hdiits:text></td>		
	    </c:otherwise>
    </c:choose>
    
    <td class="fieldLabel" width="25%">Office Name</td>
   <c:choose>
		<c:when test="${empty empSearchVO}">   
		    <td><hdiits:text name="Police_ST_NM_${name}"  readonly="true"  captionid="LOCATION"  bundle="${lables}" style="width:400px"></hdiits:text></td>		
	    </c:when>
    	<c:otherwise>  
		     <td><hdiits:text default="${empSearchVO.locPlacename}" name="Police_ST_NM_${name}"  readonly="true"  captionid="LOCATION"   bundle="${lables}" style="width:400px"></hdiits:text></td>		
	     </c:otherwise>
    </c:choose>
    
   
 </tr> 
 
 
</hdiits:table>

<hdiits:hidden name="Employee_ID_${name}"></hdiits:hidden> 
<hdiits:hidden name="USER_ID_${name}"></hdiits:hidden> 
<hdiits:hidden name="filteredEmpIDPostFix" default="${name}" ></hdiits:hidden>
<hdiits:hidden name="Employee_ID_Array_${name}" default="${name}" ></hdiits:hidden>
<hdiits:hidden name="Sel_Employee_NAME_Array_${name}" default="${name}" ></hdiits:hidden>
<hdiits:hidden name="Sel_Employee_DESIG_Array_${name}" default="${name}" ></hdiits:hidden>
<hdiits:hidden name="Sel_Employee_POST_Array_${name}" default="${name}" ></hdiits:hidden>
<hdiits:hidden name="srchCountVal_${name}"></hdiits:hidden>
<hdiits:hidden name="Employee_Post_ID_${name}"></hdiits:hidden>

</hdiits:fieldGroup>
<c:if test="${empSearchVO!= null}">
	<script language="javascript">
	var idName = "Employee_ID_" + "${name}";
	var uName = "USER_ID_" + "${name}";
	document.getElementById(idName).value='${empSearchVO.empId}';
	document.getElementById(uName).value='${empSearchVO.userId}';
	
	
	</script>
</c:if>

<%
}
catch(Exception e)
  {
  e.printStackTrace();
  }
	
	  String strSearchEmployee=request.getParameter("SearchEmployee");
	StringBuffer seControlNames=new StringBuffer();
	
	seControlNames.append("Employee_Name_"+strSearchEmployee);
	seControlNames.append(',');
	seControlNames.append("Employee_ID_"+strSearchEmployee);
	seControlNames.append(',');
	seControlNames.append("USER_ID_"+strSearchEmployee);
	seControlNames.append(',');
	seControlNames.append("GPF_NM_"+strSearchEmployee);
	seControlNames.append(',');
	seControlNames.append("Dsgn_NM_"+strSearchEmployee);
	seControlNames.append(',');
	seControlNames.append("Police_ST_NM_"+strSearchEmployee);
	seControlNames.append(',');
	request.setAttribute(strSearchEmployee+"_Search_Employee_Parameters",seControlNames.toString());  

  %>
    
  <input type="hidden" name="hidSearchFromSRKA" id="hidSearchFromSRKA" value="searchBySRKA" />

<ajax:autocomplete source="txtSearchName" target="txtSearchName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompletePayrollSearch"
	parameters="searchKey={txtSearchName},searchBy={hidSearchFromSRKA}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />