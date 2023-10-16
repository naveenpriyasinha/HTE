<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="adminCreatePostLabel" scope="request" />
<fmt:setBundle basename="resources.Payroll" var="payroll" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="locId" value = "${resValue.LocId}" /></cset>
<c:set var="location" value = "${resValue.Location}" /></cset>
<c:set var="location_gu" value="${resValue.Location_gu}"></c:set><!-- added by divya  -->
<c:set var="branch" value="${resValue.Branch}"></c:set>
<c:set var="branch_gu" value="${resValue.Branch_gu}"></c:set><!-- added by divya  -->
<c:set var="designation" value="${resValue.Designation}"></c:set>

<c:set var="postType" value="${resValue.postType}"></c:set>
<c:set var="postType_gu" value="${resValue.postType}"></c:set><!-- added by divya  --> 
<c:set var="parentPost" value="${resValue.parentPost}"></c:set>
<c:set var="parentPost_gu" value="${resValue.parentPost_gu}"></c:set><!-- added by divya  -->
<c:set var="orgPostMst" value="${resValue.orgPostMst}" ></c:set>

<c:set var="orderList" value="${resValue.orderList}"></c:set>
<c:set var="billList" value="${resValue.billList}"></c:set>

<c:set var="officeList" value="${resValue.officeList}"></c:set><!-- added by ravysh -->
<c:set var="previousPost" value="${resValue.previousPost}"></c:set>
<c:set var="officePostMpg" value="${resValue.officePostMpg}" ></c:set>

<c:set var="orgPostDtl_Gu" value="${resValue.orgPostDetailsRlt_gu}"></c:set>
<c:set var="orgPostDtl_En" value="${resValue.orgPostDetailsRlt_en}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>

<!-- added by divya  -->
<%--  <c:set var="activeFlag" value="${resValue.activeFlag}"></c:set>added by divya --%>
<%--<c:set var="activeFlag_gu" value="${resValue.activeFlag_gu}"></c:set> added by divya --%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@page import="java.util.Map"%>
<%!  long Dept_Id=0; 
	String location_Id = "";
	String branch_Id = "";
%>
<!--  Added By Urvin -->

<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="parentPostDetailsRlt" value="${resValue.parentPostDetailsRlt}"></c:set>
<c:set var="lstParentPost" value="${resValue.lstParentPost}"></c:set>
<!--  Ended -->
<%
Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	location_Id=loginDetailsMap.get("locationId").toString();
	

	System.out.println("Location Id is :::::--->" + location_Id);
 	pageContext.setAttribute("location_Id",location_Id);
 	
 	  ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");

 	 Dept_Id=Integer.parseInt(constantsBundle.getString("GAD")); 
  	pageContext.setAttribute("Dept_Id",Dept_Id);
	
	
	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    //Date date = new Date();
    Calendar cal = Calendar.getInstance();
   // cal.setTime(date);
    //  todays date
   // System.out.println("DATE IS >>>>>>>>> >>>>>>>> >>>>>>>>" + df.format(cal.getTime()));
    // first day of month    1/07/2009
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    Date toDayDate = cal.getTime();
    System.out.println("DATE IS >>>>>>>>> >>>>>>>> >>>>>>>> :    " + df.format(cal.getTime()).toString());
	//String firstDate=first.toString();
   //Date firstDate=df.format(cal.getTime());
	//df.format(first);

	String defaultDate = df.format(cal.getTime()).toString();
	System.out.println("DATE IS >>>>>>>>> >>>>>>>> >>>>>>>> :   " + defaultDate);
	%>
	
	<fmt:message key='admin.cash2Admin' bundle='${adminCreatePostLabel}' var="cash2LocationCode"></fmt:message>
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

	/*function copyEngToGuj()
	{
		if('${flag}'=='add')
		{
			document.getElementById('postNameTxt_gu').value = document.getElementById('postNameTxt').value;
			document.getElementById('postShrtNametxt_gu').value = document.getElementById('postShrtNametxt').value;
		}	
		document.getElementById('locationCmb_gu').value = document.getElementById('locationCmb').value;
		document.frmAdminCrtPost.locationCmb_gu.disabled = true;
		
		document.getElementById('designationCmb_gu').value = document.getElementById('designationCmb').value;
		document.frmAdminCrtPost.designationCmb_gu.disabled = true;
		
		document.getElementById('branchCmb_gu').value = document.getElementById('branchCmb').value;
		document.frmAdminCrtPost.branchCmb_gu.disabled = true;
		
		// document.getElementById('postTypeCmb_gu').value = document.getElementById('postTypeCmb').value;
		
		document.getElementById('parentPostCmb_gu').value = document.getElementById('parentPostCmb').value;
		document.frmAdminCrtPost.parentPostCmb_gu.disabled = true;
		
		document.getElementById('postLevelNum_gu').value = document.getElementById('postLevelNum').value;
		document.frmAdminCrtPost.postLevelNum_gu.disabled = true;
		
		// document.getElementById('selActiveFlag_gu').value = document.getElementById('selActiveFlag').value;
		// document.frmAdminCrtPost.selActiveFlag_gu.disabled = true;
				
		document.getElementById('startDate_gu').value = document.getElementById('startDate').value;
		document.frmAdminCrtPost.startDate_gu.disabled = true;
		
		document.getElementById('endDate_gu').value = document.getElementById('endDate').value;			
		document.frmAdminCrtPost.endDate_gu.disabled = true;
		
	}
	
	function copyGujToEng()
	{	
		if('${flag}'=='add')
		{	
			document.getElementById('postNameTxt').value = document.getElementById('postNameTxt_gu').value;
			document.getElementById('postShrtNametxt').value = document.getElementById('postShrtNametxt_gu').value;
		}
		document.getElementById('locationCmb').value = document.getElementById('locationCmb_gu').value;
		document.frmAdminCrtPost.Cmb.disabled = true;
			
		document.getElementById('designationCmb').value = document.getElementById('designationCmb_gu').value;
		document.frmAdminCrtPost.designationCmb.disabled = true;
			
		document.getElementById('branchCmb').value = document.getElementById('branchCmb_gu').value;
		document.frmAdminCrtPost.branchCmb.disabled = true;
			
		document.getElementById('postTypeCmb').value = document.getElementById('postTypeCmb_gu').value;
		
		document.getElementById('parentPostCmb').value = document.getElementById('parentPostCmb_gu').value;
		document.frmAdminCrtPost.parentPostCmb.disabled = true;
			
		document.getElementById('postLevelNum').value = document.getElementById('postLevelNum_gu').value;
		document.frmAdminCrtPost.postLevelNum.disabled = true;
		
		// document.getElementById('selActiveFlag').value = document.getElementById('selActiveFlag_gu').value;
		// document.frmAdminCrtPost.selActiveFlag.disabled = true;
			
		document.frmAdminCrtPost.radActiveBtn[0].disabled = true;
		document.frmAdminCrtPost.radActiveBtn[1].disabled = true;	
					
		document.getElementById('startDate').value = document.getElementById('startDate_gu').value;
		document.frmAdminCrtPost.startDate_gu.disabled = true;
			
		document.getElementById('endDate').value = document.getElementById('endDate_gu').value;
		document.frmAdminCrtPost.endDate_gu.disabled = true;
		
	}*/
	
	
	<%-- Added By Varun For Unique Psr NO in Jsp--%>


	
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

	function submit_frmAdminCrtPostNew()
	{
		if(document.getElementById("designationCmb").value == "")
		{
			alert("Please select the Designation");
			document.frmAdminCrtPost.designationCmb.focus();
			return false;
		}
		if(document.getElementById("orderCmb").value == "")
		{
			alert("Please select the Order");
			document.frmAdminCrtPost.orderCmb.focus();
			return false;
		}
		if(document.getElementById("officeCmb").value == "")
		{
			alert("Please select the Office");
			document.frmAdminCrtPost.officeCmb.focus();
			return false;
		}
		if(document.getElementById("billCmb").value == "")
		{
			alert("Please select the Bill Group");
			document.frmAdminCrtPost.billCmb.focus();
			return false;
		}
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
		else {
			return true;
		}
		
	}

</script>
</head>
<body>
<hdiits:form name="frmAdminCrtPost" action="hrms.htm?actionFlag=SubmitAdminPostData"  validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" >
	<li class="selected" id="eng"><a href="#" rel="tcontent1" ><b>
				<hdiits:caption captionid="admin.Eng" bundle="${adminCreatePostLabel}"></hdiits:caption>
			</b></a></li>

	</ul>
	</div>			
	<div id="createPost" name="createPost">
	<div id="tcontent1" class="tabcontent" tabno="0" >	
		<table class="tabtable">	
			<tr> </tr><tr></tr>

		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4">
			 <font color="#ffffff">
			 <strong>Post Details</strong></font>
			</td>
		</tr>	
		
	<%--	<tr>
			<td class="fieldLabel"><b><hdiits:caption captionid="admin.PostName" bundle="${adminCreatePostLabel}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="postNameTxt" id="postNameTxt" captionid="admin.PostName" style="width:222px"  bundle="${adminCreatePostLabel}"   validation="txt.isrequired"></hdiits:text></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.PostShortName" bundle="${adminCreatePostLabel}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="postShrtNametxt" id="postShrtNametxt" captionid="admin.PostShortName" style="width:180px" bundle="${adminCreatePostLabel}" mandatory="true" validation="txt.isrequired"></hdiits:text></td>
			</tr>  --%>
			
				
				<tr>
								
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.designation" bundle="${adminCreatePostLabel}"/></b></td>
				
				
				<td class="fieldLabel"><hdiits:select name="designationCmb" id="designationCmb" captionid="admin.designation"  bundle="${adminCreatePostLabel}" mandatory="true" validation="sel.isrequired">
				<hdiits:option value=""><hdiits:caption
						captionid="admin.select" bundle="${adminCreatePostLabel}"></hdiits:caption></hdiits:option>
				<c:forEach items="${Designation}" var="desi">
				<option value="${desi[1]}"/>${desi[1]}</option>
					<!--<option value="<c:out value="${desi.dsgnCode}"/>">
							<c:out value="${desi.dsgnName}" /></option>
					--></c:forEach>
					</hdiits:select>
					
				</td>
				
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.OrderName" bundle="${adminCreatePostLabel}"/></b></td>
				<td class="fieldLabel"><hdiits:select name="orderCmb" id="orderCmb" captionid="admin.OrderName" style="width:230px" bundle="${adminCreatePostLabel}" mandatory="true" validation="sel.isrequired">
				<hdiits:option value=""><hdiits:caption
						captionid="admin.select" bundle="${adminCreatePostLabel}"></hdiits:caption></hdiits:option>
				<c:forEach items="${orderList}" var="orderList">
					<option value="<c:out value="${orderList.orderId}"/>">
							<c:out value="${orderList.orderName}" /></option>
					</c:forEach>
					</hdiits:select>
					
				</td>
				</tr>
	
		<tr>
								
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.Office" bundle="${adminCreatePostLabel}"/></b></td>
				<td class="fieldLabel" > <hdiits:select name="officeCmb" id="officeCmb" captionid="admin.Office" bundle="${adminCreatePostLabel}" mandatory="true" validation="sel.isrequired">					
					<hdiits:option value=""><hdiits:caption
						captionid="admin.select" bundle="${adminCreatePostLabel}"></hdiits:caption></hdiits:option>
					<c:forEach items="${officeList}" var="officeList">
						<option value="<c:out value="${officeList.dcpsDdoOfficeIdPk}"/>">
						<c:out value="${officeList.dcpsDdoOfficeName}" /></option>
					</c:forEach>					
					</hdiits:select>
				</td>			
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.bill" bundle="${adminCreatePostLabel}"/></b></td>
				<td class="fieldLabel"><hdiits:select name="billCmb" id="billCmb" captionid="admin.bill" style="width:230px" bundle="${adminCreatePostLabel}" mandatory="true" validation="sel.isrequired">
				<hdiits:option value=""><hdiits:caption
						captionid="admin.select" bundle="${adminCreatePostLabel}"></hdiits:caption></hdiits:option>
				<c:forEach items="${billList}" var="billList">
						<option value="<c:out value="${billList.dcpsDdoBillGroupId}"/>">
						<c:out value="${billList.dcpsDdoBillDescription}" /></option>
					</c:forEach>
					</hdiits:select>
					
				</td>
				</tr>
			
			<tr>
			
			<td class="fieldLabel"><b><hdiits:caption captionid="admin.startDate" bundle="${adminCreatePostLabel}"/></b></td>
		<td class="fieldLabel"><hdiits:dateTime caption="StartDate"  name="startDate" mandatory="true"  validation="txt.isdt" /> </td>
		
		<td class="fieldLabel"><b><hdiits:caption captionid="admin.endDate" bundle="${adminCreatePostLabel}"/></b></td>
		
		<td class="fieldLabel"><hdiits:dateTime caption="EndDate"  name="endDate"  mandatory="true" validation="txt.isdt"/> </td>
		
			</tr>
			<tr>
			
			<td class="fieldLabel"><b><hdiits:caption captionid="admin.postNumber" bundle="${adminCreatePostLabel}"/></b></td>
		<td class="fieldLabel"><hdiits:number caption="admin.postNumber" id="postNumber" captionid="admin.postNumber"  name="postNumber" mandatory="true"  bundle="${adminCreatePostLabel}" default="1"/> </td>
		
			</tr>
			
			
			
		<%--<tr>
				<td class="fieldLabel">
				<b><hdiits:caption captionid="admin.ActiveFlag" bundle="${adminCreatePostLabel}"/></b></td>
				<td class="fieldLabel" >

					<hdiits:radio name="rdoActiveFlag" id="rdoDelete" value="0" captionid="admin.Delete" bundle="${adminCreatePostLabel}"/><br>
					<hdiits:radio name="rdoActiveFlag"  id="rdoDeactive" value="2" captionid="admin.Deactive"  bundle="${adminCreatePostLabel}" /><br>
					<hdiits:radio name="rdoActiveFlag"  id="rdoActive" value="1" captionid="admin.Active" bundle="${adminCreatePostLabel}" />					
				</td>
				<td class="fieldLabel"></td>				
				<td class="fieldLabel"></td>
			</tr>
			--%>
			
		</table>		
	</div>
	<hdiits:hidden name="flag" id="flag" default="${flag}"/>
	<hdiits:hidden name="postId" id="postId" default=""/>
	<%--<hdiits:hidden name="psrpostId" id="psrpostId" default="${hrPayPsrPostMpg.psrPostId}"/>	--%>	
	
	</div>
	<hdiits:hidden default="showAdminPostDtl" name="givenurl"/>
<jsp:include page="../core/PayTabnavigation.jsp" />
<hdiits:jsField jsFunction="submit_frmAdminCrtPostNew()" name="submit_frmAdminCrtPostNew"/>
<hdiits:validate locale="${locale}" /> 

<script type="text/javascript">
	initializetabcontent("maintab")	
	//fn_getRemarks('${officePostMpg.orgPostMstParentPostId.postId}'); 
//Added By Varun For GAD Psr Number specific	
/*var loc_Id='${location_Id}';
if(loc_Id=='${Dept_Id}'){


document.getElementById("psrId").style.display="";

}
else
{
document.getElementById("psrId").style.display="none";
}*/
//Ended By Varun For GAD Psr Number specific
</script>
</hdiits:form>
</body>
<script type="text/javascript">

 var loc_Id='${location_Id}';
   
	//document.frmAdminCrtPost.startDate.readOnly = true;
	//document.frmAdminCrtPost.endDate.readOnly = false;
	//document.frmAdminCrtPost.startDate_gu.readOnly = true;
	//document.frmAdminCrtPost.endDate_gu.readOnly = true;
	//document.getElementById("flag").value='${flag}';
	//document.getElementById('rdoActive').checked= true;
	//if('${flag}'=='edit')
	//{	
	//	document.getElementById('postId').value='${orgPostMst.postId}';
	///	document.getElementById('postNameTxt').value='${orgPostDtl_En.postName}';
	//	document.getElementById('postShrtNametxt').value='${orgPostDtl_En.postShortName}';			
	//	document.getElementById('locationCmb').value='${orgPostDtl_En.cmnLocationMst.locationCode}';		
	//	document.getElementById('designationCmb').value='${orgPostDtl_En.orgDesignationMst.dsgnCode}';	
	//	if('${orgPostDtl_En.cmnBranchMst}'!=null)
	//		document.getElementById('branchCmb').value='${orgPostDtl_En.cmnBranchMst.branchCode}';
		//alert('${orgPostMst.postTypeLookupId.lookupName}');	
	//	if('${orgPostMst.postTypeLookupId.lookupName}'!=null || '${orgPostMst.postTypeLookupId.lookupName}'!='')			
	//		document.getElementById('postTypeCmb').value='${orgPostMst.postTypeLookupId.lookupName}'; 
	//	document.getElementById('postLevelNum').value='${orgPostMst.postLevelId}';

				/* added by Varun -- starts 
						
						var loc_Id='${location_Id}';
						if(loc_Id =='${Dept_Id}')*/
						
		//				document.getElementById('psrnumber').value='${hrPayPsrPostMpg.psrId}';
				/* added by Varun -- Ended */		
	
	
		/* added by divya -- starts */
		
		//document.frmAdminCrtPost.startDate.value='${startDate}';
		//document.frmAdminCrtPost.endDate.value='${endDate}';
		
		/* added by divya -- ends */	

		//document.getElementById('rdoActiveFlag').value='${orgPostMst.activateFlag}'; 
		//setCheckedValue(document.getElementById('rdoActiveFlag'),'${orgPostMst.activateFlag}');
	//	var activateFlag = '${orgPostMst.activateFlag}';
		
	//	if(activateFlag == '0')
	//		document.getElementById('rdoDelete').checked= true;
	//	else if(activateFlag == '1')
		//		document.getElementById('rdoActive').checked= true;
	//		 else
		//	 	document.getElementById('rdoDeactive').checked= true;
			 	
	//	document.getElementById('cmbDesig').value='${parentPostDetailsRlt.orgDesignationMst.dsgnId}'; 
		//document.getElementById('parentPostCmb').value='${parentPostDetailsRlt.orgPostMst.postId}';

		//added by ravysh
	//	if(loc_Id=='${cash2LocationCode}')
	//	{
		//document.getElementById('officeCmb').value='${officePostMpg.hrPayOfficeMst.officeId}';
	//	document.getElementById('previousPostCmb').value='${officePostMpg.orgPostMstParentPostId.postId}';
		//document.getElementById('remarksTxt').value='${officePostMpg.remarks}';
	//	}
		//ended by ravysh
	//}
	
	//else if(loc_Id !='${Dept_Id}') {

	//document.getElementById("psrnumber").value = '${maxPsrNo}';

	//}
	if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");			
			var url="hrms.htm?actionFlag=showAdminPostDtl";
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
		}
	
</script>	
</html>
<%
}catch(Exception ex){ex.printStackTrace();}
%>			