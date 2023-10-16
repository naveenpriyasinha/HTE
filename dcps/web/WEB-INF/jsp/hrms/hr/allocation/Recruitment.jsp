<%
try {
	
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 




<fmt:setBundle basename="resources.hr.allocation.Allocation" var="AllocLab" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="newlist" value="${resValue.newlist}"> </c:set>
<c:set var="gender" value="${resValue.gender}"> </c:set>
<c:set var="education" value="${resValue.education}"> </c:set>
<c:set var="postdetails" value="${resValue.postdetails}"> </c:set>

<c:set var="candidatespecificdtls" value="${resValue.recruit}"> </c:set>
<c:set var="salutation" value="${resValue.salutation}"> </c:set>



<html>

<head>


<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype.js"></script>
<script type="text/javascript" src="script/common/ajaxtags.js"></script>

<script type="text/javascript" src="script/common/address.js"></script>


<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>


<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript">

var vacpostid=0;
function checkDate()
{

if(document.Allocation.datetime_doj.value!='')
{

var bdate1='${newlist.cmnPersonMst.dob}';
var birthDate1=bdate1.split(" ");
var bdatetime=birthDate1[0].split("-");
var finalbdate=bdatetime[2]+"/"+bdatetime[1]+"/"+bdatetime[0];


var calcyear=getDateDiffInString(finalbdate,document.Allocation.datetime_doj.value);

var arraycalyear=calcyear.split("~");

if(arraycalyear[0]<18)
{
alert('<fmt:message bundle="${AllocLab}" key="Allocation.difference"/>');
document.Allocation.datetime_doj.value='';
}
}
}
function LeapYear(year)
{	
	if((year%4 == 0) && ((year % 100)!=0) || ((year % 400)==0) ){return true;}
	else 	{
		return false;		
	}
}

function getDateDiffInString(strDate1,strDate2)
{
		strDate1 = strDate1.split("/"); 		
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		starttime = new Date(starttime.valueOf()); 
		
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 												
						
		if(endtime >= starttime) 
		{	
			var setDay = 0;    	
			var lIntPenSerYear = strDate2[2] - strDate1[2];
     	 	var lIntPenSerMonth = strDate2[1]- strDate1[1];
     	 	var lIntPenSerDay = strDate2[0] - strDate1[0];     	 				
     	 	lIntPenSerDay=lIntPenSerDay+1;
     	 	var intMonth = parseInt(strDate1[1],10);

     	 	var intday = parseInt(strDate1[0],10);
     	 	intYear = parseInt(strDate1[2]);
     	 	while(parseInt(strDate2[2]) >= intYear)
     	 	{     	  		    	 		
				if (intMonth>=13) {			
					intMonth=1;
					intYear++;
				}
				if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) {
					setDay = 31;	
				}
				if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) {
					setDay = 30;
				}
				if (intMonth == 2) 
				{
					if (LeapYear(intYear) == true) {
						setDay = 29;
					}
					else {
						setDay = 28;
					}
				}				
	     	 	if(setDay!=0)
	     	 	{     		     	 			
				    while(lIntPenSerDay >= setDay)
				    {
				          lIntPenSerDay -= setDay;
				          lIntPenSerMonth++;
				          if(lIntPenSerMonth==12)
				          {
				              lIntPenSerMonth=0;
				              lIntPenSerYear++;
				          }
				    }
				    while(lIntPenSerDay < 0)
				    {
				          lIntPenSerDay = setDay + lIntPenSerDay;
				          lIntPenSerMonth--;
				          if(lIntPenSerMonth<=-1)
				          {
				              lIntPenSerMonth=12+lIntPenSerMonth;
				              lIntPenSerYear--;              
				          }
				    }				    
				    if(lIntPenSerMonth <=-1)
				    {
				          lIntPenSerMonth=12+lIntPenSerMonth;
				          lIntPenSerYear--;              
				    }
				    return (lIntPenSerYear+'~'+lIntPenSerMonth+'~'+lIntPenSerDay);
				}
				else 
				{
					return '0~0~0'; 
				}
				intMonth++;													
			}
		}
		else
		{
			return '0~0~0'; 
		}
}
function formsubmit(){
var addrName ='PersonAddress';
	var addrLookUpName= 'Present Address'; 
	var addressArray = addressParameters(addrName, addrLookUpName);
	var validationArr=new Array();
	
	if(isAddressClosed(addrName)== false)				
	{   
		validationArr = validationArr.concat(addressArray);	
	}
	var addrName1 ='BirthAddress';
	var addrLookUpName1= 'Birth Address'; 
	var addressArray1 = addressParameters(addrName1, addrLookUpName1);
	
	
	if(isAddressClosed(addrName1)== false)				
	{   
		validationArr = validationArr.concat(addressArray1);	
	} 
 
	var statFlag =validateSpecificFormFields(validationArr);

if(statFlag)
{
			var id=${newlist.serialno};
			document.getElementById('btnSave').disabled='true';
			document.Allocation.action="hrms.htm?actionFlag=createuserrecruitment&recruitid="+id;    
			document.Allocation.submit();
			window.close();
			}
}

	function validateEmail(field)
   {
		var field_value=field.value;
        var periodpos="";
		var atpos="";
        var rule_num="0123456789qwertyuiopasdfghjklzxcvbnmASDFGHJKLPOIUYTREWQzXCVBNM.@_-";
		if(field_value!="")
		{
			for(var i=0;i<field_value.length;i++)
		{
        var cchar=field_value.charAt(i);
        if(rule_num.indexOf(cchar)==-1)
        {
        
          alert('<fmt:message bundle="${AllocLab}" key="Allocation.ValidEmail_alert"/>');
          field.focus();
          return;
        }
    } 
	 atpos=field_value.indexOf("@",1)
  	if(atpos==-1)
	 {
       alert('<fmt:message bundle="${AllocLab}" key="Allocation.RightFormateEmail_alert"/>');
		field.focus();
		return;
	 }
	 periodpos=field_value.lastIndexOf(".")
	 if(periodpos==-1)
	 {
	      alert('<fmt:message bundle="${AllocLab}" key="Allocation.Position@_alert"/>');
			field.focus();
	        return;
      }

	if(!((periodpos + 3 == field_value.length) || (periodpos + 4 == field_value.length)))
	{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.lastEmail_alert"/>');
		return;
	}
	}
return;
 }
//Function For Email Validation

function Closebt()
{	
	window.close();
	
}
</script>
</head>


<hdiits:form name="Allocation" validate="true" method="POST"  action="hdiits.htm" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Recruitment.details" bundle="${AllocLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
	
	<div id="tcontent1" class="tabcontent" tabno="0">	
	
<hdiits:fieldGroup titleCaptionId="Allocation.personal" bundle="${AllocLab}" expandable="true" mandatory="true" > 
   
	
	<table width="100%" >
	<tr>
	
	<td width="25%"><b><hdiits:caption captionid="Allocation.empprefix" bundle="${AllocLab}"/></b></td>
	<td width="25%">${salutation}</td>
	<td width="25%"></td>
	<td width="25%"></td>
	</tr>
	<tr>
	<td width="25%"> </td><td width="25%"><b><hdiits:caption captionid="Allocation.fstname" bundle="${AllocLab}"/></b></td><td width="25%"><b><hdiits:caption captionid="Allocation.mdlname" bundle="${AllocLab}"/></b></td><td width="25%"><b><hdiits:caption captionid="Allocation.lstname" bundle="${AllocLab}"/></b></td>
	</tr>
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.namecandidate" bundle="${AllocLab}"/><hdiits:caption captionid="Allocation.ineng" bundle="${AllocLab}"/></b></td><td width="25%">${newlist.cmnPersonMst.firstName} </td><td width="25%">${newlist.cmnPersonMst.middleName} </td><td width="25%">${newlist.cmnPersonMst.lastName} </td>
	</tr>
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.namecandidate" bundle="${AllocLab}"/><hdiits:caption captionid="Allocation.ingu" bundle="${AllocLab}" /></b></td><td width="25%"><hdiits:text name="txt_fstnameguj" maxlength="25" mandatory="true" default="${candidatespecificdtls.gujFirstName}" id="txt_fstnameguj" /> </td><td width="25%"><hdiits:text name="txt_mdlnameguj" maxlength="25"  default="${candidatespecificdtls.gujMiddleName}" id="txt_mdlnameguj"/> </td><td width="25%"><hdiits:text name="txt_lstnameguj" maxlength="25" mandatory="true" default="${candidatespecificdtls.gujLastName}" id="txt_lstnameguj"/> </td>
	</tr>
	

	
	
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></b></td>
	<td width="25%"><fmt:formatDate  value="${newlist.cmnPersonMst.dob}" pattern="dd/MM/yyyy" /></td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.age" bundle="${AllocLab}"/></b></td>
	<td width="25%"><label id="age_value" ></label></td>
	</tr>
	
	
	<tr>
	
	<td width="25%"><b><hdiits:caption captionid="Allocation.category" bundle="${AllocLab}"/></b></td>
	<td width="25%">${newlist.cmnPersonMst.category}</td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.gender" bundle="${AllocLab}"/></b></td>
	<td width="25%">${gender} </td>
	</tr>
	
	
	<tr>
	
	<td width="25%"><b><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></b></td>
	<td width="25%">${education}</td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.dateofjoin" bundle="${AllocLab}"/></b></td>
	<td width="25%"><hdiits:dateTime name="datetime_doj" captionid="Allocation.dateofjoin" bundle="${AllocLab}" default="${candidatespecificdtls.doj}"  mandatory="true" onblur="checkDate()" maxvalue="31/12/2099" /></td>
	</tr>
	</table>
	</hdiits:fieldGroup>
	<table width="100%">
	<tr>
	
				<td class="fieldLabel" colspan="3">
	<jsp:include page="../../../common/address.jsp">
						<jsp:param name="addrName" value="PersonAddress" />
						<jsp:param name="addressTitle" value="Contact Address" />
						<jsp:param name="addrLookupName" value="Present Address" />
						<jsp:param name="mandatory" value="Y" />						
					</jsp:include>
					</td>
				
</tr>
		</table>
		
	
<table width="100%">
	<tr>
	
				<td class="fieldLabel" colspan="3">
	<jsp:include page="../../../common/address.jsp">
						<jsp:param name="addrName" value="BirthAddress" />
						<jsp:param name="addressTitle" value="Birth Address" />
						<jsp:param name="addrLookupName" value="Permanent Address" />
						<jsp:param name="mandatory" value="Y" />
						 <jsp:param name="sameAsAddressTitle" value="Contact Address"/>
		                  <jsp:param name="sameAsAddrName" value="PersonAddress"/>						
					</jsp:include>
					</td>
				
</tr>
	</table>
<hdiits:fieldGroup titleCaptionId="Allocation.contact" bundle="${AllocLab}" expandable="true" mandatory="true" > 	
	
	<table width="100%">
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.phoneno" bundle="${AllocLab}"/></b></td>
	<td width="25%"><hdiits:number name="phoneno" default="${newlist.phoneNo}"  maxlength="11" id="phoneno" /></td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.email" bundle="${AllocLab}"/></b></td>
	<td width="25%"><hdiits:text name="recuit_email"  default="${newlist.email}"   onblur="validateEmail(this);" id="recuit_email"/></td>
	</tr>
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.postname" bundle="${AllocLab}"/></b></td>
	<td width="25%" ><hdiits:select name="sel_post" id="sel_post" mandatory="true" ><hdiits:option value="0" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	
	<c:forEach var="postdetails" items="${postdetails}">
	 <option value=<c:out value="${postdetails.orgPostMst.postId}"/>>
	<c:out value="${postdetails.postName}"/></option>
	</c:forEach>
	</hdiits:select></td>
	<td width="25%"></td>
	<td width="25%"></td>
	</tr>
	</table>
	</hdiits:fieldGroup>
	
	<table border="1" id="attachtb">
		<tr>
			<td><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="Recruitment123" />
				<jsp:param name="formName" value="Allocation" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Recruitment" />
				<jsp:param name="multiple" value="N" />
				<jsp:param name="removeAttachmentFromDB" value="Y" />
			</jsp:include></td>
		</tr>
	</table>
		
	</div>	
	<table align="center" >
	<tr align="center">
	<td align="center">
	<hdiits:button  name="btnSave"  captionid="Allocation.Save" bundle="${AllocLab}" type="button" onclick="formsubmit()" id="btnSave"/>
	</td>
	<td align="center">
	<hdiits:button name="clsbtn" type="button"  captionid="Allocation.Close" bundle="${AllocLab}" onclick="Closebt()"/>
	</td>
	</tr>
	</table>
	<script >


	

		 document.Allocation.datetime_doj.readOnly=true;
			var bdate='${newlist.cmnPersonMst.dob}';
			var birthDate=bdate.split(" ");
		
			var splitDate=birthDate[0].split("-");							
			var bday=parseInt(splitDate[2],10);
			var bmo=(parseInt(splitDate[1])-1,10);
			var byr=parseInt(splitDate[0]);
			var age;
			var now = new Date();		
			
			tday=now.getUTCDate();
			tmo=(now.getUTCMonth());
			tyr=(now.getUTCFullYear());
			if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr}				
			else  {age=byr+1}
			
			
			
			 document.getElementById('age_value').innerHTML=tyr-age;
			 
		
			 </script>
			
			<c:if test="${not empty newlist.orgPostMstByAllocatedPost.postId}">	
				<script>
			
			vacpostid='${newlist.orgPostMstByAllocatedPost.postId}';
		 
		
		 </script>
		</c:if>
			
	
 	</div>
 	
	
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>	

	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
</html>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script type="text/javascript">

document.getElementById('sel_post').value=vacpostid;

</script>