

<%
	try {
%> 


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true" %>


<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="adminCreatePostLabel" scope="request" />
<fmt:setBundle basename="resources.Payroll" var="payroll"
	scope="request" />
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="locId" value="${resValue.LocId}" />
</cset>
<c:set var="location" value="${resValue.Location}" />
</cset>
<c:set var="location_gu" value="${resValue.Location_gu}"></c:set>
<!-- added by vaibhav tyagi: start  -->
<c:set var="SubjectList" value="${resValue.SubjectList}"></c:set>
<!-- added by vaibhav tyagi: end  -->
<!-- added by divya  -->
<c:set var="branch" value="${resValue.Branch}"></c:set>
<c:set var="branch_gu" value="${resValue.Branch_gu}"></c:set>
<!-- added by divya  -->
<c:set var="designation" value="${resValue.Designation}"></c:set>
<c:set var="designation_gu" value="${resValue.Designation_gu}"></c:set>
<!-- added by divya  -->
<c:set var="postType" value="${resValue.postType}"></c:set>
<c:set var="postType_gu" value="${resValue.postType}"></c:set>
<!-- added by divya  -->
<c:set var="parentPost" value="${resValue.parentPost}"></c:set>
<c:set var="parentPost_gu" value="${resValue.parentPost_gu}"></c:set>
<!-- added by divya  -->
<c:set var="orgPostMst" value="${resValue.orgPostMst}"></c:set>


<!-- added by roshan kumar: start  -->
<c:set var="talukaId" value="${resValue.talukaId}"></c:set>
<c:set var="talukaList" value="${resValue.talukaList}"></c:set>
<c:set var="ddoSelected" value="${resValue.ddoSelected}"></c:set>
<c:set var="officeIdSelected" value="${resValue.officeIdSelected}"></c:set>
<c:set var="postTypeSelected" value="${resValue.postTypeSelected}"></c:set>
<c:set var="orderIdSeelcted" value="${resValue.orderIdSeelcted}"></c:set>

<c:set var="ddoCodeSelected" value="${resValue.ddoCodeSelected}"></c:set>
<!-- added by roshan kumar: end  -->


<c:set var="orderList" value="${resValue.orderList}"></c:set>

<c:set var="billList" value="${resValue.billList}"></c:set>
<script type="text/javascript" src="script/dcps/ddoScheme.js"></script>
<c:set var="officeList" value="${resValue.officeList}"></c:set>
<!-- added by ravysh -->
<c:set var="previousPost" value="${resValue.previousPost}"></c:set>
<c:set var="officePostMpg" value="${resValue.officePostMpg}"></c:set>

<c:set var="orgPostDtl_Gu" value="${resValue.orgPostDetailsRlt_gu}"></c:set>
<c:set var="orgPostDtl_En" value="${resValue.orgPostDetailsRlt_en}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="lvl2" value="${resValue.lvl2}"></c:set>
<!--
added by khushal
-->
<c:set var="PostType" value="${resValue.PostType}"></c:set>
<c:set var="oldOrderList" value="${resValue.orderList}"></c:set>
<c:set var="newOrderList" value="${resValue.orderList}"></c:set>
<c:set var="OrderDate" value="${resValue.date}"></c:set>
<c:set var="PostLookUpId" value="${resValue.PostLookUpId}"></c:set>
<c:set var="DDOlist" value="${resValue.DDOlist}"></c:set>
<c:set var="subOfficeList" value="${resValue.subOfficeList}"></c:set>
<!--

ended by khushal

-->

<!-- added by divya  -->
<%--  <c:set var="activeFlag" value="${resValue.activeFlag}"></c:set>added by divya --%>
<%--<c:set var="activeFlag_gu" value="${resValue.activeFlag_gu}"></c:set> added by divya --%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@page import="java.util.Map"%>
<%!long Dept_Id = 0;
	String location_Id = "";
	String branch_Id = "";%>
<!--  Added By Urvin -->

<c:set var="msg" value="${resValue.msg}"></c:set>
<c:set var="parentPostDetailsRlt"
	value="${resValue.parentPostDetailsRlt}"></c:set>
<c:set var="lstParentPost" value="${resValue.lstParentPost}"></c:set>
<!--  Ended -->

<%
	Map loginDetailsMap = (Map) session
				.getAttribute("loginDetailsMap");
		location_Id = loginDetailsMap.get("locationId").toString();

		//System.out.println("Location Id is :::::--->" + location_Id);
		pageContext.setAttribute("location_Id", location_Id);

		ResourceBundle constantsBundle = ResourceBundle
				.getBundle("resources.Payroll");

		Dept_Id = Integer.parseInt(constantsBundle.getString("GAD"));
		pageContext.setAttribute("Dept_Id", Dept_Id);

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		//Date date = new Date();
		Calendar cal = Calendar.getInstance();
		// cal.setTime(date);
		//  todays date
		// System.out.println("DATE IS >>>>>>>>> >>>>>>>> >>>>>>>>" + df.format(cal.getTime()));
		// first day of month    1/07/2009
		cal.set(Calendar.DAY_OF_MONTH, cal
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date toDayDate = cal.getTime();
		//System.out.println("DATE IS >>>>>>>>> >>>>>>>> >>>>>>>> :    " + df.format(cal.getTime()).toString());
		//String firstDate=first.toString();
		//Date firstDate=df.format(cal.getTime());
		//df.format(first);

		String defaultDate = df.format(cal.getTime()).toString();
		//System.out.println("DATE IS >>>>>>>>> >>>>>>>> >>>>>>>> :   "	+ defaultDate);
%>

<fmt:message key='admin.cash2Admin' bundle='${adminCreatePostLabel}'
	var="cash2LocationCode"></fmt:message>
<%@page import="java.util.ResourceBundle"%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%><html>

<head>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script>




	var chkflag=1;
function fn_getRemarks(previousPost)
{
if(previousPost.value!="")
 {
	document.getElementById("remarksMsg").style.display =""; 
}
else
 {
	document.getElementById("remarksMsg").style.display ="none"; 
 }
}
function previousPostSelected()
{
if(document.getElementById("previousPostCmb").value!="")
	return true;
else
	return false;
}



function chkbillHeadunique()
{
	var psr=document.frmAdminCrtPost.psr.value;
	var location_Id='${location_Id}';
	var psrPostId='${hrPayPsrPostMpg.psrPostId}';
	var mpgId =0;
	if(psr!=""&&location_Id!="")
	{
	try
   	{   
   		xmlHttp=new XMLHttpRequest();    
   	}
	catch (e)
	{    // Internet Explorer    
		try
     	{
     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
     	}
	    catch (e)
	    {
	    	try
       		{
            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
       		}
		    catch (e)
		    {
		        alert("Your browser does not support AJAX!");        
		        return false;        
		    }
		}
	}
		if(psrPostId != "" )
			{
			mpgId=psrPostId;
			}
		else
			{
			
			mpgId;
			}	
	
    var url = "hrms.htm?actionFlag=chkpsrLocationunique&psr="+psr+"&location_Id="+location_Id+"&ppMapId="+mpgId;  
    xmlHttp.onreadystatechange = function()
	{
		
		if (xmlHttp.readyState == 4) 
		{     
			
			if (xmlHttp.status == 200) 
			{
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var mpgflag = XMLDocForAjax.getElementsByTagName('pp-mapping');	
				
				if(mpgflag.length != 0)
				{
					if(mpgflag[0].childNodes[0].text!='null'&&mpgflag[0].childNodes[0].text!='-1')
					{			
						alert("Psr No is already Assigned");
						document.frmAdminCrtPost.psr.value = '';
						document.frmAdminCrtPost.psr.focus();
						chkflag=0;
					}
					else
					chkflag=1;
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	}
	
	<%--Ended By Varun --%>
	
	function getCheckedRadioValue(radioName)
	{
		var radioValue = "";
		
		objRadio = eval("document.getElementsByName(\""+ radioName +"\")");
		
		for (iter=0;iter<objRadio.length;iter++)
		{
			if (objRadio[iter].checked)
			{
				radioValue = objRadio[iter].value;
				break;			
			}
		}
			
		return 	radioValue;
	}
	
function submit_frmAdminCrtPost()
	{

	var loc_Id='${location_Id}';
	var Dept_Id ='${Dept_Id}';

	if(/*loc_Id==Dept_Id &&*/ document.getElementById("psrnumber").value== "" ) 
	{
	
	alert('Please Enter PSR Number');
	return;
	
	}
	
//Ended By Varun For GAD Specific Psr No.

		if('${langId}' == '1')
		{
			var fieldArrayEng1 = new Array('postNameTxt', 'postShrtNametxt', 'locationCmb', 'designationCmb'); // changed by divya
			var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
			
			if(statusValidationEng1)
			{
				if(document.getElementById("branchCmb").value == "")
				{
					alert("Please select Branch");
					return false;
				}
				var fieldArrayEng2 = new Array('postTypeCmb', 'parentPostCmb', 'postLevelNum', 'selActiveFlag', 'startDate'); // changed by divya
				var statusValidationEng2 = validateSpecificFormFields(fieldArrayEng2);
				if(statusValidationEng2)
				{
					var fieldArrayGuj1 = new Array('postNameTxt_gu', 'postShrtNametxt_gu', 'locationCmb_gu', 'designationCmb_gu'); // changed by divya
					var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
					if(statusValidationGuj1)
					{
						if(document.getElementById("branchCmb").value == "")
						{
							alert("Please select Branch");
							return false;
						}
						var fieldArrayGuj2 = new Array('postTypeCmb_gu', 'parentPostCmb_gu', 'postLevelNum_gu', 'selActiveFlag_gu', 'startDate_gu'); // changed by divya
						var statusValidationGuj2 = validateSpecificFormFields(fieldArrayGuj2);
						if(statusValidationGuj2)
						{
							return true;
						}
					}
				}
			}
		}
		else if('${langId}' == '2')
		{
			var fieldArrayGuj1 = new Array('postNameTxt_gu', 'postShrtNametxt_gu', 'locationCmb_gu', 'designationCmb_gu'); // changed by divya
			var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
			if(statusValidationGuj1)
			{
				if(document.getElementById("branchCmb").value == "")
				{
					alert("Please select Branch");
					return false;
				}
				var fieldArrayGuj2 = new Array('postTypeCmb_gu', 'parentPostCmb_gu', 'postLevelNum_gu', 'selActiveFlag_gu', 'startDate_gu'); // changed by divya
				var statusValidationGuj2 = validateSpecificFormFields(fieldArrayGuj2);
				if(statusValidationGuj2)
				{
					var fieldArrayEng1 = new Array('postNameTxt', 'postShrtNametxt', 'locationCmb', 'designationCmb'); // changed by divya
					var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
					alert(statusValidationEng1);
					if(statusValidationEng1)
					{
						if(document.getElementById("branchCmb").value == "")
						{
							alert("Please select Branch");
							return false;
						}
						var fieldArrayEng2 = new Array('postTypeCmb', 'parentPostCmb', 'postLevelNum', 'selActiveFlag', 'startDate'); // changed by divya
						var statusValidationEng2 = validateSpecificFormFields(fieldArrayEng2);
						if(statusValidationEng2)
						{
							return true;
						}
					}
				}
			}
		}
	}
	
	// Added By Urvin Shah.
	// This Function fetch the all post relevent to selected desigantion.
	
	function getParentPostByDesignation()
	{
		  var v=document.getElementById("parentPostCmb").length;
		  for(i=1;i<v;i++)
		  {
			  lgth = document.getElementById("parentPostCmb").options.length -1;
			  document.getElementById("parentPostCmb").options[lgth] = null;
		  }	
		  	
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  //alert("The Designation Id is:-"+document.frmAdminCrtPost.cmbDesig.value);
		  url= uri+'&designationId='+ document.frmAdminCrtPost.cmbDesig.value;
		  var actionf="getPostDtlsByDesigId";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		  xmlHttp.onreadystatechange=getPostsfromDesg;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);
	}

	function getPostsfromDesg() {	
		if (xmlHttp.readyState==complete_state)	{ 
			var parentPostCmb = document.getElementById("parentPostCmb");
			var XMLDoc=xmlHttp.responseXML.documentElement;
		    var entries = XMLDoc.getElementsByTagName('parentPostMapping');
			var val=0;
			var text='';
			//alert("The Length is :-"+entries.length);
			
			for ( var i = 0 ; i < entries.length ; i++ ) {
			    val=entries[i].childNodes[0].text; 
			    
			    text =entries[i].childNodes[1].text; 
				//break;
			    var optParentPost = document.createElement('option')   
		        optParentPost.value=val;
		        optParentPost.text=text;	
		        try {      				    					
	                parentPostCmb.add(optParentPost,null);
	    		}
		        catch(ex) {
			       parentPostCmb.add(optParentPost); 
	  	        }	
	        }
	  	}
	}	

	function submitButton_formSubmitButton()
	{
		if(document.getElementById("ddoCode").value == -1)
		{
			alert("Please select the Institute.");
			document.frmAdminCrtPost.postTypeCmbBox.focus();
			return false;
		}
		
		if(document.getElementById("cmbSubFieldDept").value == -1)
		{
			alert("Please select the Sub Field Department.");
			document.frmAdminCrtPost.postTypeCmbBox.focus();
			return false;
		}
		if(document.getElementById("postTypeCmbBox").value == -1)
		{
			alert("Please select the Post Type.");
			document.frmAdminCrtPost.postTypeCmbBox.focus();
			return false;
		}
		
		
		if(document.getElementById("postTypeCmbBox").value == 10001198129)
		{
			/*if(document.getElementById("postSubTypeCmbBoxPerm").value == -1)
			{
				alert("Please select the Sub Post Type");
				document.frmAdminCrtPost.postSubTypeCmbBoxPerm.focus();
				return false;
			}
			*/
			if(document.getElementById("designationCmb").value == "" || document.getElementById("designationCmb").value == -1)
			{
				alert("Please select the Designation.");
				document.frmAdminCrtPost.designationCmb.focus();
				return false;
			}
			if(document.getElementById("subjectCmb").value == "" || document.getElementById("subjectCmb").value == -1)
			{
				alert("Please select the Subject.");
				document.frmAdminCrtPost.subjectCmb.focus();
				return false;
			}
			if(document.getElementById("orderCmb").value == "" || document.getElementById("orderCmb").value == -1)
			{
				alert("Please select the Order/GR.");
				document.frmAdminCrtPost.orderCmb.focus();
				return false;
			}

			if(document.getElementById("officeCmb").value == "" || document.getElementById("officeCmb").value == -1)
			{
				alert("Please select the Office");
				document.frmAdminCrtPost.officeCmb.focus();
				return false;
			}

			/*if(document.getElementById("billCmb").value == "" || (document.getElementById("billCmb").value == -1))
			{
				alert("Please select the Bill Group");
				document.frmAdminCrtPost.billCmb.focus();
				return false;
			}*/
			if((document.getElementById("postNumber").value == "") || (document.getElementById("postNumber").value == "0") )
			{
				alert("Please provide number of posts to be created");
				document.frmAdminCrtPost.postNumber.focus();
				return false;
			}
		/*	if((document.getElementById("billCmb").value == "") || (document.getElementById("billCmb").value == -1) )
			{
				alert("Please provide number of posts to be created");
				document.frmAdminCrtPost.postNumber.focus();
				return false;
			}
			*/
			if(document.frmAdminCrtPost.startDate.value=="" )
			{
				alert("Please select start date");
				document.frmAdminCrtPost.startDate.focus();
				return false;
			}
	//		if(document.frmAdminCrtPost.Remarks.value=="")
	//		{
	//			alert("Please Enter Remarks");
	//			document.frmAdminCrtPost.Remarks.focus();
	//			return false;
				
	//		}
			var answer = confirm ("Do you want to submit?");

			var permenant = document.getElementById("postTypeCmbBox").value;
			  if (answer)
			  {
				  //alert("in if");
				  
				
				document.frmAdminCrtPost.action="ifms.htm?actionFlag=SubmitAdminPostData";
				document.frmAdminCrtPost.submit();
			  }
			
		}
		if(document.getElementById("postTypeCmbBox").value == 10001198155)
		{
			/*if(document.getElementById("postSubTypeCmbBoxPerm").value == -1)
			{
				alert("Please select the Sub Post Type");
				document.frmAdminCrtPost.postSubTypeCmbBoxPerm.focus();
				return false;
			}
			*/
			if(document.getElementById("subjectCmb").value == "" || document.getElementById("subjectCmb").value == -1)
			{
				alert("Please select the Subject.");
				document.frmAdminCrtPost.subjectCmb.focus();
				return false;
			}
			if(document.getElementById("designationCmb").value == "" || document.getElementById("designationCmb").value == -1)
			{
				alert("Please select the Designation");
				document.frmAdminCrtPost.designationCmb.focus();
				return false;
			}
			if(document.getElementById("orderCmb").value == "" || document.getElementById("orderCmb").value == -1)
			{
				alert("Please select the Order");
				document.frmAdminCrtPost.orderCmb.focus();
				return false;
			}

			if(document.getElementById("officeCmb").value == "" || document.getElementById("officeCmb").value == -1)
			{
				alert("Please select the Office");
				document.frmAdminCrtPost.officeCmb.focus();
				return false;
			}

			/*if(document.getElementById("billCmb").value == "" || (document.getElementById("billCmb").value == -1))
			{
				alert("Please select the Bill Group");
				document.frmAdminCrtPost.billCmb.focus();
				return false;
			}*/
			if((document.getElementById("postNumber").value == "") || (document.getElementById("postNumber").value == "0") )
			{
				alert("Please provide number of posts to be created");
				document.frmAdminCrtPost.postNumber.focus();
				return false;
			}
		/*	if((document.getElementById("billCmb").value == "") || (document.getElementById("billCmb").value == -1) )
			{
				alert("Please provide number of posts to be created");
				document.frmAdminCrtPost.postNumber.focus();
				return false;
			}
			*/
			if(document.frmAdminCrtPost.startDate.value=="" )
			{
				alert("Please select start date");
				document.frmAdminCrtPost.startDate.focus();
				return false;
			}
	//		if(document.frmAdminCrtPost.Remarks.value=="")
	//		{
	//			alert("Please Enter Remarks");
	//			document.frmAdminCrtPost.Remarks.focus();
	//			return false;
				
	//		}
			var answer = confirm ("Do you want to submit?");

			var permenant = document.getElementById("postTypeCmbBox").value;
			  if (answer)
			  {
				  //alert("in if");
				  
				
				document.frmAdminCrtPost.action="ifms.htm?actionFlag=SubmitAdminPostData";
				document.frmAdminCrtPost.submit();
			  }
			
		}
		if(document.getElementById("postTypeCmbBox").value == 10001198130)
		{
			//if(document.getElementById("purposeCmbBox").value == -1)
			//{
				//alert("Please select Natur of Post");
				//document.frmAdminCrtPost.purposeCmbBox.focus();
				//return false;
			//}
			//if(document.getElementById("purposeCmbBox").value == 3)
			//{
			
		/*	if(document.getElementById("postSubTypeCmbBoxTemp").value == -1)
		{
			alert("Please select the Sub Post Type");
			document.frmAdminCrtPost.postSubTypeCmbBoxTemp.focus();
			return false;
		}
			
			*/
			if(document.getElementById("subjectCmb").value == "" || document.getElementById("subjectCmb").value == -1)
			{
				alert("Please select the Subject.");
				document.frmAdminCrtPost.subjectCmb.focus();
				return false;
			}
				if(document.getElementById("designationCmb").value == "" || document.getElementById("designationCmb").value == -1)
				{
					alert("Please select the Designation");
					document.frmAdminCrtPost.designationCmb.focus();
					return false;
				}
				if(document.getElementById("orderCmb").value == "" || document.getElementById("orderCmb").value == -1)
				{
					alert("Please select the Order");
					document.frmAdminCrtPost.orderCmb.focus();
					return false;
				}
				if(document.getElementById("officeCmb").value == "" || document.getElementById("officeCmb").value == -1)
				{
					alert("Please select the Office");
					document.frmAdminCrtPost.officeCmb.focus();
					return false;
				}
				/*if(document.getElementById("billCmb").value == "" || (document.getElementById("billCmb").value == -1))
				{
					alert("Please select the Bill Group");
					document.frmAdminCrtPost.billCmb.focus();
					return false;
				}*/
				if((document.getElementById("postNumber").value == "") || (document.getElementById("postNumber").value == "0") )
				{
					alert("Please provide number of posts to be created");
					document.frmAdminCrtPost.postNumber.focus();
					return false;
				}

				if(document.frmAdminCrtPost.startDate.value=="" )
				{
					alert("Please select start date");
					document.frmAdminCrtPost.startDate.focus();
					return false;
				}
				if(document.frmAdminCrtPost.endDate.value=="" )
				{
					alert("Please select end date");
					document.frmAdminCrtPost.tempEndDate.focus();
					return false;
				}
		//		if(document.frmAdminCrtPost.Remarks.value=="")
		//		{
		//			alert("Please Enter Remarks");
		//			document.frmAdminCrtPost.Remarks.focus();
		//			return false;
					
		//		}
				var answer = confirm ("Do you want to submit?");
				  if (answer)
				  {
					  //alert("in if");
					document.frmAdminCrtPost.action='ifms.htm?actionFlag=SubmitAdminPostData';//by khushal
					document.frmAdminCrtPost.submit();
				  }
				
			//}
			if(document.getElementById("purposeCmbBox").value == 4)
			{
				if(document.getElementById("designationCmb").value == "")
				{
					alert("Please select the Designation");
					document.frmAdminCrtPost.designationCmb.focus();
					return false;
				}
				if(document.getElementById("OriginalorderCmb").value == "")
				{
					alert("Please select the Old Order Name");
					document.frmAdminCrtPost.orderCmb.focus();
					return false;
				}
				if(document.getElementById("RenewalorderCmb").value == "")
				{
					alert("Please select Renewal order Name");
					document.frmAdminCrtPost.RenewalorderCmb.focus();
					return false;
				}
				if(document.getElementById("RenewalOrderDate").value == "")
				{
					alert("Please select Renewal order Date");
					document.frmAdminCrtPost.RenewalOrderDate.focus();
					return false;
				}
			/*	if(document.getElementById("billCmb").value == "" || (document.getElementById("billCmb").value == -1))
				{
					alert("Please select the Bill Group");
					document.frmAdminCrtPost.billCmb.focus();
					return false;
				}*/
				if((document.getElementById("postNumber").value == "") || (document.getElementById("postNumber").value == "0") )
				{
					alert("Please provide number of posts to be created");
					document.frmAdminCrtPost.postNumber.focus();
					return false;
				}

				if(document.frmAdminCrtPost.startDate.value=="" )
				{
					alert("Please select start date");
					document.frmAdminCrtPost.startDate.focus();
					return false;
				}
				if(document.frmAdminCrtPost.endDate.value=="" )
				{
					alert("Please select end date");
					document.frmAdminCrtPost.endDate.focus();
					return false;
				}
				if(document.frmAdminCrtPost.tempEndDate.value!="")
				{

					
				}
			//	if(document.frmAdminCrtPost.Remarks.value=="")
			//	{
				//	alert("Please Enter Remarks");
				//	document.frmAdminCrtPost.Remarks.focus();
				//	return false;
					
			//	}
				var answer = confirm ("Do you want to submit?");
				  if (answer)
				  {
					showProgressbar("Saving Post Details");
					document.frmAdminCrtPost.action="ifms.htm?actionFlag=SubmitAdminPostData";
					document.frmAdminCrtPost.submit();
					  showProgressbar("Saving Post Details");
				  }
			}
			
		}
		else {
			return true;
		}
		
	}
	//added by khushal
	function resetForm()
	{
		if(confirm("All entered values will be cleared, please confirm!") == true)
		{
			document.forms[0].reset();
  	}			  				  	
	}

	function onBackfn()
	{
		document.frmAdminCrtPost.action='./hrms.htm?actionFlag=showAdminPostDtl&elementId=9000219';
		document.frmAdminCrtPost.submit();
	}
	function fieldDisplay()
	{
		//alert("khushal is in fuction fielddisplay()");
		 var commId = document.frmAdminCrtPost.postTypeCmbBox.value;
		 var commId1 = document.frmAdminCrtPost.purposeCmbBox.value;
		// alert("khushal is in fuction fielddisplay()"+commId1);
		//if(commId == -1){
			//document.getElementById("tempSubPost").style.display="none";
			 //document.getElementById("permSubPost").style.display="none";
			//}
		if(commId == 10001198129 || commId == 10001198155)
		 {
			 //alert("i am in permenet");
			 document.getElementById("tempCombo").style.display="none";
			 document.getElementById("original").style.display="none";
			 document.getElementById("renewal").style.display="none";
			 document.getElementById("selectpurpose").style.display="none";
			 document.getElementById("order").style.display="block";
			 document.getElementById("endDate1").style.display="none";
			 document.getElementById("endDate2").style.display="none";
			 document.getElementById("tempEndDate1").style.display="none";
			 document.getElementById("tempEndDate2").style.display="none";
			 //document.getElementById("tempSubPost").style.display="none";
			 //document.getElementById("permSubPost").style.display="block";
		 }
		 else if(commId == 10001198130)
		 {
			// alert("i am in temporary");
			 document.getElementById("selectpurpose").style.display="none";
			 document.getElementById("endDate1").style.display="none";
			 document.getElementById("tempEndDate1").style.display="block";
			 document.getElementById("tempEndDate2").style.display="none";
			 document.getElementById("endDate2").style.display="block";
			 document.getElementByName("tempEndDate").style.display="block";
			 document.getElementByName("endDate").style.display="block";

			 
			 //document.getElementById("tempSubPost").style.display="block";
			 //document.getElementById("permSubPost").style.display="none";
			 if(commId1 == 3)
			 {
			// alert("i am in Entry");
			// document.getElementById("tempCombo").style.display="none";
			 document.getElementById("original").style.display="none";
			 document.getElementById("renewal").style.display="none";
			 document.getElementById("order").style.display="block";
			
			 
			 }
			 else if(commId1 == 4)
			 {
				// alert("i am in Renewal")
				// document.getElementById("tempCombo").style.display="none";
				 document.getElementById("original").style.display="block";
				 document.getElementById("renewal").style.display="block";
				 document.getElementById("order").style.display="none";
				 
			 }
			 else if(commId == -1)
			 {
				 alert("Please select Post type");
				
			 }
	}
}
	function DisplayOriginalOrderDate()
	{
		
	var xmlHttp = null;
	var orderId=document.getElementById("OriginalorderCmb").value;
	var retValue=true;
	if(orderId=="")
	{
		alert("Please search the Old Order Name");
		retValue=false;
	}
	else
	{
		
		try 
		{   
			xmlHttp=new XMLHttpRequest();
	   	}
		catch(e)
		{    
			// Internet Explorer    
			try 
			{
		    	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
	    	} 
		    catch (e) 
		    {
			 	try 
			 	{
		        	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	    	   	}
				catch (e) 
				{
					alert("Your browser does not support AJAX!");        
				    retValue=false;     
				}
			}
		}
		var url = "hrms.htm?actionFlag=getOrderDate&orderId="+orderId+"";  
		
	    xmlHttp.onreadystatechange = function()
	    {		
		   
			if(xmlHttp.readyState == 4)
				{  
						
				if (xmlHttp.status == 200)
					{	
				
					var oderDate;
					
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					
					var orderDate = XMLDocForAjax.getElementsByTagName('getOldOrderDate');	
				
					if(orderDate.length != 0) 
						{
						
						orderDate = orderDate[0].childNodes[0].text;
						
						document.getElementById("OriginalOrderDate").value=	orderDate;
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));		
		return retValue;
	}
	}
	function DisplayOrderDate()
	{
	var xmlHttp = null;
	var orderId=document.getElementById("orderCmb").value;
	
	var retValue=true;
	if(orderId=="")
	{
		alert("Please search the Order Name");
		retValue=false;
	}
	else
	{
		
		try 
		{   
			xmlHttp=new XMLHttpRequest();
	   	}
		catch(e)
		{    
			// Internet Explorer    
			try 
			{
		    	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
	    	} 
		    catch (e) 
		    {
			 	try 
			 	{
		        	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	    	   	}
				catch (e) 
				{
					alert("Your browser does not support AJAX!");        
				    retValue=false;     
				}
			}
		}
		var url = "hrms.htm?actionFlag=getOrderDate&orderId="+orderId+"";  
		    xmlHttp.onreadystatechange = function()
	    {	
			   
			if(xmlHttp.readyState == 4)
				{     
						
				if (xmlHttp.status == 200)
					{	

					
					var oderDate;
					
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					
					var orderDate = XMLDocForAjax.getElementsByTagName('getOldOrderDate');	
				
					if(orderDate.length != 0) 
						{
						
						orderDate = orderDate[0].childNodes[0].text;
						
						document.getElementById("OrderDate").value=	orderDate;
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));		
		return retValue;
	}
	}
	function onchagevalue()
	{
            if(document.getElementById("postTypeCmbBox").value == "-1")
            {
            	document.getElementById("purposeCmbBox").value= -1;
            	document.getElementById("orderCmb").value=	-1;
            	document.getElementById("OrderDate").value=	"";
            	document.getElementById("OrderDate").value=	"";
            	document.getElementById("designationCmb").value= -1;
            	document.getElementById("officeCmb").value= -1;
            	document.getElementById("OriginalorderCmb").value= -1;
            	document.getElementById("RenewalorderCmb").value= -1;
            	document.getElementById("billCmb").value= -1;
            	document.getElementById("OriginalOrderDate").value="";
            	document.getElementById("startDate").value="";
            	document.getElementById("endDate").value="";
            	
            	
            }
            if(document.getElementById("purposeCmbBox").value == "-1")
            {
            	
            	document.getElementById("orderCmb").value=	-1;
            	document.getElementById("OrderDate").value=	"";
            	document.getElementById("OrderDate").value=	"";
            	document.getElementById("designationCmb").value= -1;
            	document.getElementById("officeCmb").value= -1;
            	document.getElementById("OriginalorderCmb").value= -1;
            	document.getElementById("RenewalorderCmb").value= -1;
            	document.getElementById("billCmb").value= -1;
            	document.getElementById("Remarks").value= "";
            	document.getElementById("OriginalOrderDate").value="";
            	document.getElementById("startDate").value="";
            	document.getElementById("endDate").value="";
            }
            if(document.getElementById("purposeCmbBox").value == "3")
            {
            	
            	document.getElementById("orderCmb").value=	-1;
            	document.getElementById("OrderDate").value=	"";
            	document.getElementById("OrderDate").value=	"";
            	document.getElementById("OriginalOrderDate").value= "";
            	document.getElementById("designationCmb").value= -1;
            	document.getElementById("officeCmb").value= -1;
            	document.getElementById("OriginalorderCmb").value= -1;
            	document.getElementById("RenewalorderCmb").value= -1;
            	document.getElementById("billCmb").value= -1;
            	document.getElementById("Remarks").value= "";
            	document.getElementById("startDate").value="";
            	document.getElementById("endDate").value="";
            }
            if(document.getElementById("purposeCmbBox").value == "4")
            {
            
            	document.getElementById("orderCmb").value=	-1;
            	document.getElementById("OrderDate").value=	"";
            	document.getElementById("OrderDate").value=	"";
            	document.getElementById("designationCmb").value= -1;
            	document.getElementById("officeCmb").value= -1;
            	document.getElementById("OriginalorderCmb").value= "";
            	document.getElementById("RenewalorderCmb").value= -1;
            	document.getElementById("billCmb").value= -1;
            	document.getElementById("Remarks").value= "";
            	document.getElementById("startDate").value="";
            	document.getElementById("endDate").value="";
            	
            	
            }
	}

            function chkDate()
            {
               
               
            	if(document.frmAdminCrtPost.endDate.value!=null && document.frmAdminCrtPost.endDate.value!="")
            	{	
            		var diff = compareDate(document.frmAdminCrtPost.startDate.value,document.frmAdminCrtPost.endDate.value);
            	    if(diff<0){
            	    	alert("End date should be greater than Start date");
            	    	document.forms[0].elements("endDate").value=''; 
            	    	document.forms[0].elements("endDate").focus();   	
            	    	return false;
            	    }
            	}
            }
            function chktempDate()
            {
               
               
            	if(document.frmAdminCrtPost.endDate.value!=null && document.frmAdminCrtPost.endDate.value!="")
            	{	
            		var diff = compareDate(document.frmAdminCrtPost.startDate.value,document.frmAdminCrtPost.endDate.value);
            	    if(diff<0){
            	    	alert("End date should be greater than Start date");
            	    	document.forms[0].elements("endDate").value=''; 
            	    	document.forms[0].elements("endDate").focus();   	
            	    	return false;
            	    }
            	}
            }
            function checkDate()
            {
              // regular expression to match required date format
            var  re = /^\d{1,2}\/\d{1,2}\/\d{4}$/;

              if(form.startdate.value != '' && !form.startdate.value.match(re)) {
                alert("Invalid date format: " + form.startdate.value);
                form.startdate.focus();
                return false;
              }
              if(form.enddate.value != '' && !form.enddate.value.match(re)) {
                alert("Invalid date format: " + form.enddate.value);
                form.enddate.focus();
                return false;
              }

              // regular expression to match required time format
              re = /^\d{1,2}:\d{2}([ap]m)?$/;

              if(form.starttime.value != '' && !form.starttime.value.match(re)) {
                alert("Invalid time format: " + form.starttime.value);
                form.starttime.focus();
                return false;
              }

              alert("All input fields have been validated!");
              return true;
            }
         
            function addPostDataToTable(){
            	
            	var amt=document.empLoan.principalAmt.value;
            	if(amt=="")
            	{
            		amt=document.empLoan.interestAmt.value;
            	}
            	//alert('anmount is '+amt);
            	var instNo=document.empLoan.principalInstNo.value;
            	if(instNo=="")
            	{
            		instNo=document.empLoan.interestInstNo.value;
            	}
            	//var emiAmount=amt/instNo;
            	//alert('instNo is '+instNo);
            	var emiAmount= document.empLoan.loanPrinEmiAmt.value;
            	if(emiAmount=="")
            	{
            		emiAmount= document.empLoan.loanIntEmiAmt.value;
            	}
            	//alert('emiAmount is '+emiAmount);
            	var reqAmt="";
            	if(amt % emiAmount != 0)
            	{
            		reqInstal = ((amt-(amt%emiAmount))/emiAmount)+1;
            	}
            	else
            	{
            		reqInstal=amt/emiAmount;
            	}

            	
            	if(instNo != reqInstal)
            	{
            		alert('Please Enter appropriate value of Installment No i.e.'+reqInstal);
            		return false;
            	}
            	
            	if((emiAmount*instNo)>amt)
            	{
            		var oddInsAmt=amt-(emiAmount*(instNo-1));
            		var oddInsNo=instNo;
            	
            	//alert('oddInsAmt=  '+oddInsAmt +' oddInsNo= '+oddInsNo );
            	var orginalOddInstNo=  document.getElementById("loanOddinstno").value;
            	//alert(''+orginalOddInstNo);
            	var orignialOddInstAmt=document.getElementById("loanOddinstAmt").value;
            	//alert(''+orignialOddInstAmt);
            	var box="";
            	if(orginalOddInstNo != oddInsNo)
            	{
            		alert('Please enter appropriate value of Odd Installment No. i.e.'+oddInsNo);
            		box=doucment.getElementById("loanOddinstno");
            		box.focus();
            		return false;
            	}
            	if(orignialOddInstAmt!= oddInsAmt)
            	{
            		alert('Please enter appropriate value of Odd Installment Amount i.e.'+oddInsAmt);
            		box=doucment.getElementById("loanOddinstAmt");
            		box.focus();
            		return false;
            	}
            	}
            	//alert(''+document.empLoan.loanVoucherNo.value);
            	if(document.empLoan.loanVoucherNo.value == "")
            	{
            		alert('Please Enter  Voucher No');
            		box=document.empLoan.loanVoucherNo.value;
            		box.focus();
            		return false;
            	}
            	//alert(''+document.empLoan.loanVoucherDate);
            	if(document.empLoan.loanVoucherDate.value == "")
            	{
            		alert('Please Enter  Voucher Date');
            		box=document.empLoan.loanVoucherDate;
            		box.focus();
            		return false;
            	}
            	if(document.empLoan.loanSancOrderDate.value == "")
            	{
            		alert('Please Enter loan sanction order Date');
            		box=document.empLoan.loanSancOrderDate;
            		box.focus();
            		return false;
            	}
            	
            	if(document.getElementById("Employee_ID_EmpSearch").value != ""){

            		var loanDataForVOGEN = new Array('Employee_ID_EmpSearch','loanName','principalAmt','principalInstNo','loanPrinEmiAmt','interestAmt','interestInstNo','loanIntEmiAmt','loanACNo','loanSancOrderNo','loanSancOrderDate','loanDate','loanPrinRecovAmt','loanPrinRecovInt','loanIntRecovAmt','loanIntRecovInt','loanOddinstno','loanOddinstAmt','loanVoucherNo','loanVoucherDate');
            		addOrUpdateRecord('addRecord', 'multipleLoanData', loanDataForVOGEN);

            	}
            	else{
            		alert("Please search employee first");
            	}
            	return true;
            	
            }//end function: addPostDataToTable()
          
/*---------Comparing two dates---------*/
function compareDatesLocally(fieldName1,fieldName2,alrtStr,flag)
{
	
	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;
    var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var day1=parseFloat(la_Date1[0]);
    var month1=parseFloat(la_Date1[1]);
    var year1=parseFloat(la_Date1[2]);

    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    var day2=parseFloat(la_Date2[0]);
    var month2=parseFloat(la_Date2[1]);
    var year2=parseFloat(la_Date2[2]);

    if (year2 == year1 && month2 == month1 && day2 == day1)
    {
    	if(flag == '=')
    	{
    		alert(alrtStr);
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else
    	{
	        return true;
	    }
    }
    else if( year2 > year1 )
    {
        return true;
    }
    else if( year2 < year1 && flag != '=')
    {
        alert(alrtStr);
        if(flag == '<')
        {
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else if(flag == '>')
    	{
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
            return true;
        }
        else if( month2 < month1 )
        {     
             alert(alrtStr);
             if(flag == '<')
	        {
    		    fieldName2.focus();
    		    fieldName2.value="";
	    	}
    		else if(flag == '>')
    		{
	    	    fieldName2.focus();
	    	    fieldName2.value="";
    		}
        }
        else
        {
            if( day2 > day1 )
            {
                return true;
            }
            else if( day2 < day1 )
            {
                 alert(alrtStr);
                 if(flag == '<')
			     {
    			    fieldName2.focus();
    			    fieldName2.value="";
			   	}
		    	else if(flag == '>')
    			{
    			    fieldName2.focus();
    			    fieldName2.value="";
		    	}
            }
        }
    }
    return true ;
}



</script>
</head>
<body>
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected" id="eng" ><a href="#" rel="tcontent1"><b>
		<hdiits:caption  captionid="admin.Eng" style="" bundle="${adminCreatePostLabel}" ></hdiits:caption>
		</b></a></li>
	</ul>
	</div>
<hdiits:form name="frmAdminCrtPost"
	action="hrms.htm?actionFlag=SubmitAdminPostData" validate="true"
	method="post" encType="multipart/form-data" >
	
	
	<%--Added by roshan --%>
	<fieldset class="tabstyle"><legend> <b>Filter Institute</b> </legend>
<table align="center">
<tr>
<td><c:out value="Taluka"></c:out></td>

<td><select name="cmbTaluka"
			id="cmbTaluka" style="width: 85%,display: inline;">
			<option title="Select" value="-1"><c:out value="Select"></c:out></option>

			<c:forEach var="talukaList" items="${talukaList}">
			<c:choose> 
			<c:when test="${talukaId==talukaList[0]}">
							<option value="${talukaList[0]}" title="${talukaList[1]}" selected="selected">
						<c:out value="${talukaList[1]}"/></option>
			</c:when>
			<c:otherwise>
						<option value="<c:out value="${talukaList[0]}"/>" title="${talukaList[1]}">
						<c:out value="${talukaList[1]}"/></option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					
					
		</select></td>


<td><c:out value=" DDO Code/Office Name"></c:out></td>
<td>
<c:choose>
<c:when test="${ddoSelected!=null}">

<input type="text" id = "ddoCodeforFilter" name="ddoCodeforFilter" maxlength="200" value="${ddoSelected}" size="30"/>
</c:when>
<c:otherwise>
<input type="text" id = "ddoCodeforFilter" name="ddoCodeforFilter" maxlength="200" size="30"/>
</c:otherwise>
</c:choose>			 
</td>


		</tr>
	<tr>	
<td colspan="4" align="center"><input id="btnFilter" class="buttontag" type="button" size="5" maxlength="5"
		value="Filter" onclick="filterInstituteForPostEntry();"
		name="btnFilter" style="width: 120px;" /></td>
</tr>
</table>
</fieldset>
<%--Added by roshan --%>
	
	
	<div id="tcontent1"  style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	
	<table class="tabtable" border="0"
		onkeypress="return submit_frmAdminCrtPostNew(event)">
		
		<tr bgcolor="#BD6C03">
			<td class="fieldLabel" colspan="4"><font color="#ffffff">
			<strong>Post Details</strong></font></td>
		</tr>

		
		
		<tr>
			<td style="width:200px;"> Institute Name&nbsp;</td>
			
			<td>
				<hdiits:select name="ddoCode" id="ddoCode" captionid="admin.ReortingDDOS" style="width:500px;"
				bundle="${adminCreatePostLabel}" mandatory="true" validation="sel.isrequired" onchange="crtPostForSubDDO()">
				<c:choose>
					<c:when
						test="${lvl2 == 'yes'}">
						<option value='<c:out value="${resValue.ddoCode}"/>'  selected="selected" readonly="readonly"><c:out value="${resValue.ddoCode}"  /></option>
					</c:when>
					<c:otherwise>
						<hdiits:option value="-1">------------------------------ Select ------------------------------ </hdiits:option>
						<c:forEach items="${DDOlist}" var="ddo">
							<option value=${ddo[0]}>(${ddo[0]})${ddo[1]}</option>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</hdiits:select>
			</td>
		</tr>
		
		<tr>
			<td>Sub Field Department</td>
				<td width="50%">
				
						<select name="cmbSubFieldDept" id="cmbSubFieldDept" style="width:100%" >
						<option value="-1">------------------------------ Select ------------------------------</option>
						<c:forEach items="${subOfficeList}" var="subOfficeList">
						<option value='${subOfficeList[0]}'>${subOfficeList[1]}</option>
						</c:forEach>
						</select><label class="mandatoryindicator">*</label>
						
				
				
		</tr>

		<tr >
			<td><hdiits:caption
				captionid="admin.SelectTypePost" bundle="${adminCreatePostLabel}" />
			&nbsp;</td>

			<td><hdiits:select name="postTypeCmbBox" mandatory="true"
				id="postTypeCmbBox" onchange="fieldDisplay();onchagevalue();" style="width:250px;">
			 	<hdiits:option value="-1">------------ Select ------------</hdiits:option>
				<hdiits:option value="10001198130"> Temporary </hdiits:option>	 
				<hdiits:option value="10001198129" > Permanent </hdiits:option>
				<hdiits:option value="10001198155" > Statutory </hdiits:option>
			</hdiits:select></td>
			
			
			
	</tr >
		<!-- added by shailesh -->
		<!--<tr id="tempSubPost" style="display:none">		
		<td > 
		<hdiits:caption captionid="admin.SelectSubTypePost" bundle="${adminCreatePostLabel}" />
			&nbsp;</td>
			<td>
			<select name="postSubTypeCmbBoxTemp" mandatory="true" id="postSubTypeCmbBoxTemp" >
			 	<option value="-1">-- Select -- </option>
				<option value="10001198155" > Statutory </option>
				<option value="10001198157"> Adhoc </option>			
				<option value="10001198153" > Contract </option>
				<option value="10001198175" > Clock Hour Basis </option>
				<option value="10001198154" > Honorary </option>
				
				
			</select>
			</td>
			<td></td>
		<td></td>
		</tr>
		<tr id="permSubPost" style="display:none">
		<td> 
		<hdiits:caption captionid="admin.SelectSubTypePost" bundle="${adminCreatePostLabel}" />
			&nbsp;</td>
			<td>
			<hdiits:select name="postSubTypeCmbBoxPerm" mandatory="true" id="postSubTypeCmbBoxPerm" >
			 	<hdiits:option value="-1">-- Select -- </hdiits:option>
				<hdiits:option value="10001198174"> Regular </hdiits:option>
				<hdiits:option value="10001198159"> Protected </hdiits:option>	 
				<hdiits:option value="10001198156" > Surplus </hdiits:option>			
			</hdiits:select>
		</td>
			
		<td></td>
		<td></td>
		</tr>
					--><!-- added by shailesh:end -->
					<tr>
			
		</tr>

		<tr id="selectpurpose" style="display: none">
			<td><hdiits:caption captionid="admin.Selectpurpose"
				bundle="${adminCreatePostLabel}" /> &nbsp;</td>
			<td><hdiits:select name="purposeCmbBox" mandatory="true"
				id="purposeCmbBox" onchange="fieldDisplay(); onchagevalue();">
			<!-- 	<hdiits:option value="-1"> -- Select -- </hdiits:option> -->
				<hdiits:option value="3" selected = "selected"> Entry Of Post </hdiits:option>
			<!-- 	<hdiits:option value="4"> Renewal Of Post</hdiits:option> -->
			</hdiits:select></td>
		</tr>


		<tr id="tempCombo" style="display: none;">
			<td><!-- Nature of temporary Post  --> <hdiits:caption
				captionid="admin.SelectTypeTempoPost"
				bundle="${adminCreatePostLabel}" /> &nbsp;</td>
			<td><hdiits:select name="tempPostTypeCmbBox" mandatory="true"
				id="tempPostTypeCmbBox" onchange="fieldDisplay();">
				<hdiits:option value="-1"> -- Select Nature Of Temporary Post -- </hdiits:option>
				<hdiits:option value="-1"> Temporary </hdiits:option>
				<hdiits:option value="-1"> Contract </hdiits:option>
				<hdiits:option value="-1"> Seasonable </hdiits:option>
				<hdiits:option value="-1"> C.R.T. </hdiits:option>
				<hdiits:option value="-1"> C.R.P </hdiits:option>
				<hdiits:option value="-1"> Honorary </hdiits:option>
				<hdiits:option value="-1"> Part Time </hdiits:option>
				<hdiits:option value="-1"> Supar Numerarie </hdiits:option>
			</hdiits:select></td>
		</tr>



		<tr>

			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.designation" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:select name="designationCmb"
				id="designationCmb" captionid="admin.designation"
				bundle="${adminCreatePostLabel}" mandatory="true"
				validation="sel.isrequired" style="width:250px;">
				<hdiits:option value="-1">
					<hdiits:caption captionid="admin.select"
						bundle="${adminCreatePostLabel}"></hdiits:caption>
				</hdiits:option>
				<c:forEach items="${designation}" var="desi">
					<option value="<c:out value="${desi.dsgnCode}"/>"><c:out
						value="${desi.dsgnName}" /></option>
				</c:forEach>
			</hdiits:select></td>
			<!-- added by vaibhav tyagi: start -->
			
		</tr>
		<tr>	
			<td class="fieldLabel">Subject</td>
			<td class="fieldLabel"><hdiits:select name="subjectCmb"
				id="subjectCmb" captionid="admin.subject"
				bundle="${adminCreatePostLabel}" mandatory="true"
				validation="sel.isrequired" style="width:250px;">
				<hdiits:option value="-1">
					<hdiits:caption captionid="admin.select"
						bundle="${adminCreatePostLabel}"></hdiits:caption>
				</hdiits:option>
				<c:forEach items="${SubjectList}" var="sub">
					<option value="<c:out value="${sub[1]}"/>"><c:out
						value="${sub[1]}" /></option>
				</c:forEach>
			</hdiits:select></td>
			
			
			
			<!-- added by vaibhav tyagi: end -->
		</tr>
		<tr id="order">
			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.GrNO" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:select name="orderCmb"
				id="orderCmb" captionid="admin.GrNO" style="width:250px"
				bundle="${adminCreatePostLabel}" mandatory="true"
				validation="sel.isrequired" onchange="DisplayOrderDate();">
				<hdiits:option value="-1">
					<hdiits:caption captionid="admin.select"
						bundle="${adminCreatePostLabel}"></hdiits:caption>
				</hdiits:option>
				<c:forEach items="${orderList}" var="orderList">
					<option value="<c:out value="${orderList.orderId}"/>"><c:out
						value="${orderList.orderName}" /></option>

				</c:forEach>
			</hdiits:select></td>
		</tr>
			<!--
			by khushal
				-->
		<tr>	
			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.GrDate" bundle="${adminCreatePostLabel}" /></b></td>
						
			
			<td class="fieldLabel"><input type="text" size="10" name = "OrderDate" id="OrderDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
															onBlur="validateDate(this);" value="" readonly="readonly"/> &nbsp;
															<img src='images/CalendarImages/ico-calendar.gif' 
															onClick='window_open("OrderDate${counter}",375,570)'
															style="cursor: pointer;"/>
				</td>
		</tr>
		<tr id="original">

			<td class="fieldLabel" style="display: none"><b><hdiits:caption
				captionid="admin.OriginalOrderName" bundle="${adminCreatePostLabel}" /></b></td>

			<td class="fieldLabel" style="display: none"><hdiits:select name="OriginalorderCmb"
				id="OriginalorderCmb" captionid="admin.OriginalOrderName"
				style="width:230px" bundle="${adminCreatePostLabel}"
				mandatory="true" onchange="DisplayOriginalOrderDate();">
				<hdiits:option value="-1">
					<hdiits:caption captionid="admin.select"
						bundle="${adminCreatePostLabel}"></hdiits:caption>
				</hdiits:option>
				<c:forEach items="${newOrderList}" var="OriginalorderList">
					<option value="<c:out value="${OriginalorderList.orderId}"/>"><c:out
						value="${OriginalorderList.orderName}" /></option>
				</c:forEach>
			</hdiits:select></td>

			<td class="fieldLabel" style="display: none"><b><hdiits:caption
				captionid="admin.OriginalOrderDate" bundle="${adminCreatePostLabel}" /></b></td>
			<td style="display: none"><input type="text" size="10" name="OriginalOrderDate" id="OriginalOrderDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
															onBlur="validateDate(this);" value="" /> &nbsp;
															<img src='images/CalendarImages/ico-calendar.gif' 
															onClick='window_open("OriginalOrderDate",375,570)'
															style="cursor: pointer;"/>



		</tr>
		<tr id="renewal">
			<td class="fieldLabel" style="display: none"><b><hdiits:caption
				captionid="admin.RenewalOrderName" bundle="${adminCreatePostLabel}" /></b></td>

			<td class="fieldLabel" style="display: none"><hdiits:select name="RenewalorderCmb"
				id="RenewalorderCmb" captionid="admin.OrderName" style="width:230px"
				bundle="${adminCreatePostLabel}" mandatory="true"
				validation="sel.isrequired">
				<hdiits:option value="-1">
					<hdiits:caption captionid="admin.select"
						bundle="${adminCreatePostLabel}"></hdiits:caption>
				</hdiits:option>
				<c:forEach items="${newOrderList}" var="orderList1">
					<option value="<c:out value="${orderList1.orderId}"/>"><c:out
						value="${orderList1.orderName}" /></option>
				</c:forEach>
			</hdiits:select></td>

			<td class="fieldLabel" style="display: none"><b><hdiits:caption
				captionid="
" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel" style="display: none">
			
			<hdiits:dateTime
				captionid="admin.RenewalOrderDate" name="RenewalOrderDate"
				mandatory="true" validation="txt.isdt" bundle="${commonLables}" minvalue=""/>
				</td>
				
				
		</tr>

		<tr>

			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.Office" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:select name="officeCmb"
				id="officeCmb" captionid="admin.Office"
				bundle="${adminCreatePostLabel}" mandatory="true"
				validation="sel.isrequired" style="width:250px;">
				<hdiits:option value="-1">
					<hdiits:caption captionid="admin.select"
						bundle="${adminCreatePostLabel}"></hdiits:caption>
				</hdiits:option>
				<c:forEach items="${officeList}" var="officeList">
					<option value="<c:out value="${officeList.dcpsDdoOfficeIdPk}"/>">
					<c:out value="${officeList.dcpsDdoOfficeName}" /></option>
				</c:forEach>
			</hdiits:select></td>
			<td class="fieldLabel">
			<%-- <b><hdiits:caption
				captionid="admin.bill" bundle="${adminCreatePostLabel}" /></b>--%></td>
			<td class="fieldLabel">
			<%-- <select name="billCmb" id="billCmb" captionid="admin.bill" style="width:400px" bundle="${adminCreatePostLabel}" >
				<option value="-1">------------------------Select----------------------------</option>
				<c:forEach items="${billList}" var="billList">
					<option value="${billList.dcpsDdoBillGroupId}" title="${billList.dcpsDdoBillDescription}">
					<c:out value="${billList.dcpsDdoBillDescription}"></c:out></option>
				</c:forEach>
			</select>
			--%>
			</td>
		</tr>

		<tr>

			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.SancFromDt" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel">
			 	  <input type="text" size="10" name="startDate" id="startDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
															onBlur="validateDate(this);compareDatesLocally(OrderDate,this,'Sanctioned From Date field should not be less than GR Date.','>');" value="" /> &nbsp;
															<img src='images/CalendarImages/ico-calendar.gif' 
															onClick='window_open("startDate",375,570)'
															style="cursor: pointer;"/>&nbsp;<label class="mandatoryindicator">*</label></td>

		</tr>
		
		<tr>	
			<td class="fieldLabel" id="endDate1"><b><hdiits:caption
				captionid="admin.SancToDt" bundle="${adminCreatePostLabel}" /></b></td>
			<!--<td class="fieldLabel" id="endDate2">
			
			<input type="text" size="10" name="endDate" id="endDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
															onBlur="validateDate(this);" value="" /> &nbsp;
															<img src='images/CalendarImages/ico-calendar.gif' 
															onClick='window_open("endDate",375,570)'
															style="cursor: pointer;"/></td>
				
			
			-->
			
			
			<td class="fieldLabel" id="tempEndDate1"><b><hdiits:caption
				captionid="admin.SancToDt" bundle="${adminCreatePostLabel}" /></b></td>
				<td class="fieldLabel" id="endDate2">
				<input type="text" size="10" name="endDate" id="endDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
															onBlur="validateDate(this);compareDatesLocally(startDate,this,'Sanctioned To Date field should not be less than Sanctioned From Date.','>');" value="" /> &nbsp;
															<img src='images/CalendarImages/ico-calendar.gif' 
															onClick='window_open("endDate",375,570)'
															style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label>
				</td>
				
			<td class="fieldLabel" id="tempEndDate2">
			<hdiits:dateTime
				captionid="admin.endDate"  name="tempEndDate" validation="txt.isdt"
				bundle="${commonLables}" onblur="chktempDate();dateFormat(this);" mandatory="true"/>
				<label class="mandatoryindicator">*</label>
				</td>

		</tr>
		
		
		
		<tr>

			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.postNumber" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:number caption="admin.postNumber"
				id="postNumber" captionid="admin.postNumber" name="postNumber"
				mandatory="true" bundle="${adminCreatePostLabel}" default="1"
				style="width:18%" /></td>
		</tr>

		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.Remarks" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:textarea rows="3" cols="30"
				name="Remarks" id="Remarks" captionid="eis.Remarks"
				bundle="${adminCreatePostLabel}" mandatory="false"
				onblur="OnlyAplha(this)">:</hdiits:textarea></td>
		</tr>
		

	</table>
	
	
	
	<hdiits:hidden name="flag" id="flag" default="${flag}" /> <hdiits:hidden
		name="postId" id="postId" default="" /> <%--<hdiits:hidden name="psrpostId" id="psrpostId" default="${hrPayPsrPostMpg.psrPostId}"/>	--%>

	</div>
	<hdiits:hidden default="showAdminPostDtl" name="givenurl" />
	<br/>
	<table class="tabNavigationBar" width="80" align="center" >
		<tr >
			<td width="37%" align="center"></td>
			<td  width="2%">
			<hdiits:button type="button" name="formSubmitButton"
				onclick="submitButton_formSubmitButton();" value="Save" ></hdiits:button></td>
			<td  width="2%">
			<hdiits:button name="backButton" type="button" value="Close" onclick="onBackfn()"/></td>
			<td  width="2%">
			<hdiits:button	name="resetButton" type="button" value="Reset" onclick="resetForm()"/></td>
			<td width="37%" align="center"></td>

		</tr>
	</table>
	
	<hdiits:jsField jsFunction="submit_frmAdminCrtPostNew()"
		name="submit_frmAdminCrtPostNew" />
	<hdiits:validate locale="${locale}" />

	<script type="text/javascript">
	initializetabcontent("maintab");	
	
</script>
</hdiits:form>
</body>
<script type="text/javascript">

hidetempDate();
function  hidetempDate()
{
  
	  document.getElementById("tempEndDate1").style.display="none";
	  document.getElementById("tempEndDate2").style.display="none";
}

 var loc_Id='${location_Id}';
   
	
	
	if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");			
			var url="hrms.htm?actionFlag=showAdminPostDtl";
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
		}
function crtPostForSubDDO(){
	var url="hrms.htm?actionFlag=addSubDDOPostDtl";
	document.frmAdminCrtPost.action=url;
	document.frmAdminCrtPost.submit();
	showProgressbar("Loading Sub DDO Dtls...");
	
}
	
</script>

</html>
<%
	} catch (Exception ex) {
		ex.printStackTrace();
	}
%>
