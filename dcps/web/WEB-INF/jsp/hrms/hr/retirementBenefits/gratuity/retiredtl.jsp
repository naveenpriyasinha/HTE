<%
try{
%>

<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.hr.gratuity.gratuity" var="commonLables" scope="request"/>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="amt" value="${resValue.sys_Cal_Amt}"/>
<c:set var="userId" value="${resValue.userId}"/>
<c:set var="elbal" value="${resValue.elbal}"/>
<c:set var="eolDays" value="${resValue.eolDays}"/>
<c:set var="lwpDays" value="${resValue.lwpDays}"/>
<c:set var="suspDays" value="${resValue.suspDays}"/>

<c:set var="eolDayMsg" value="${resValue.eolDayMsg}"/>
<c:set var="lwpDayMsg" value="${resValue.lwpDayMsg}"/>
<c:set var="suspDayMsg" value="${resValue.suspDayMsg}"/>

<c:set var="retirementTypeDesc" value="${resValue.retirementType}"/>
<c:set var="retirementTypeDesc" value="${resValue.retirementTypeDesc}"/>
<c:set var="totalService" value="${resValue.totalService}"/>
<c:set var="applicationDate" value="${resValue.applicationDate}"/>
<c:set var="dateOfSrvcExp" value="${resValue.dateOfSrvcExp}"/>
<c:set var="dateOfJoin" value="${resValue.dateOfJoin}"/>
<c:set var="DeptInqrDtls" value="${resValue.DeptInqrDtls}"/>

<script>
function getDateDiffInString(strDate1,strDate2)
	{
		var currDate=new Date();	
		

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
			var intMonth = parseInt(eval(strDate1[1]));

			var intday = parseInt(eval(strDate1[0]));
			intYear = parseInt(eval(strDate1[2]));
			while(parseInt(eval(strDate2[2])) >= intYear)
			{ 
				if (intMonth>=13) 
				{ 
					intMonth=1;
					intYear++;
				}
				if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) 
				{
					setDay = 31; 	
				}
				if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) 
				{
					setDay = 30;
				}
				if (intMonth == 2) 
				{
					if (LeapYear(intYear) == true) 
					{
						setDay = 29;
					}
					else 
					{
						setDay = 28;
					}
				}
				if(setDay!=0)
				{
					while(lIntPenSerDay > setDay)
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
					if(strDate1.toString() == strDate2.toString())
						{lIntPenSerDay=1;}
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

	function LeapYear(year)
	{	
		if(year%4 == 0 )
		{
			return true;
		}
		else 	
		{
			return false;		
		}
	}
 
  function dispDeptEnq()
		{
			document.getElementById('penInqrTab').style.display='';
			getFieldGroupObj(document.getElementById('dummy'));
			document.getElementById('dummy').focus();
		}


</script>


<hdiits:fieldGroup bundle="${commonLables}" titleCaptionId="Gra.RetDetail"  id="retirementType"  expandable="true" mandatory="true">
<table width="100%" >
		
	<tr>
		<td align="left" >
			<strong>
			<hdiits:caption captionid="Gra.RetType" bundle="${commonLables}" /> 
			</strong>
		</td>
		<td align="left" >
			${resValue.retirementTypeDesc}
			<hdiits:hidden  name="retirementType" caption="retirementType" default="${resValue.retirementType}" />
		</td>
		<td align="left" >
			<strong>
			<hdiits:caption captionid="Gra.ApplicationDate" bundle="${commonLables}" /> 
			</strong>
		</td>
		<td align="left" >
			<fmt:formatDate value="${list.createdDate}" pattern="dd/MM/yyyy" var="createdDate"/>
			${createdDate}
			<fmt:formatDate value="${resValue.applicationDate}" pattern="dd/MM/yyyy" var="applicationDate"/>
			${applicationDate}
		</td>
	</tr>
	
	<tr>
		
		<td align="left" >
			<strong>
			<hdiits:caption captionid="Gra.YrService" bundle="${commonLables}" /> 
			</strong>
		</td>
		<td align="left" id="yearsOfServ">
		<c:if test="${totalService ne 'NIL'}">
		
			<fmt:formatDate value="${dateOfJoin}" pattern="dd/MM/yyyy" var="dateOfJoin"/>
			<script>

				var yearsOfService=getDateDiffInString('${dateOfJoin}','${totalService}');
				var yosArr=yearsOfService.split("~");
				if( yosArr[1] <= 1 )
				{
					var yosMsg=yosArr[0].toString()+" <fmt:message   key="Gra.year"/> "+yosArr[1].toString()+" <fmt:message   key="Gra.month"/> "+yosArr[2].toString()+" <fmt:message   key="Gra.days"/> ";
				}
				else
				{
					var yosMsg=yosArr[0].toString()+" <fmt:message   key="Gra.year"/> "+yosArr[1].toString()+" <fmt:message   key="Gra.months"/> "+yosArr[2].toString()+" <fmt:message   key="Gra.days"/> ";
				}
				document.getElementById('yearsOfServ').innerHTML=yosMsg;
			</script>
			
		
		</c:if>
		<c:if test="${totalService eq 'NIL'}">
			${totalService}
		</c:if>		
		</td>
		<td align="left" >
			<strong>
			<hdiits:caption captionid="Gra.DptInq" bundle="${commonLables}" /> 
			</strong>
		</td>
		<td align="left" >
		<c:if test="${not empty DeptInqrDtls}">
			<a href="#" tabindex="5" onclick="dispDeptEnq();" ><fmt:message   key="Gra.yes"/> </a>
		</c:if>
		<c:if test="${empty DeptInqrDtls}">
			<fmt:message   key="Gra.no"/> 
		</c:if>
		</td>
		
	</tr>	
	
	<tr>
	
		<td align="left" >
			<strong>
			<hdiits:caption captionid="Gra.SuspDuration" bundle="${commonLables}" /> 
			</strong>
			
		</td>
		<td align="left" >
			${resValue.suspDayMsg} &nbsp;&nbsp;<hdiits:caption captionid="Gra.days" bundle="${commonLables}" /> 
			<hdiits:hidden name="suspensionDuration" default="${resValue.suspDays}" captionid="Gra.SuspensionDuration"/>
			<!--<hdiits:text name="suspensionDuration" default="${resValue.suspDays}" captionid="Gra.SuspensionDuration" mandatory="true" />-->
		</td>
		
		
		
		<td align="left" >
			<strong>
			<hdiits:caption captionid="Gra.EoLeave" bundle="${commonLables}" /> 
			</strong>
		</td>
		<td align="left" >
			${resValue.eolDayMsg}&nbsp; &nbsp; <fmt:message   key="Gra.days"/>
			<hdiits:hidden name="extraOrdinaryLeave" default="${resValue.eolDays}" captionid="Gra.ExOLeave" />
			<!--<hdiits:text name="extraOrdinaryLeave" default="${resValue.eolDays}" captionid="Gra.ExOLeave" mandatory="true" />-->
		</td>
		
	</tr>
	
	<tr>
		<td align="left" >
			<strong>
				<hdiits:caption captionid="Gra.LwPay" bundle="${commonLables}" /> 
			</strong>
		</td>
		<td align="left" >
			${resValue.lwpDayMsg}&nbsp; &nbsp;<fmt:message   key="Gra.days"/>
			<hdiits:hidden name="leaveWithoutPay" default="${resValue.lwpDays}" captionid="Gra.LeaveWithoutPay"/>
			<!--<hdiits:text name="leaveWithoutPay" default="${resValue.lwpDays}" captionid="Gra.LeaveWithoutPay" mandatory="true" />-->
		</td>
	 
	 	<td>
	 	
	 	</td>
	</tr>		
	
	
</table>
</hdiits:fieldGroup>

<div id="penInqrTab" style="display:none">
<hdiits:fieldGroup bundle="${commonLables}" titleCaptionId="HRMS.DeptInq" id="DeptDtlGrp"  expandable="true">
<label id="dummy" name="dummy"></label>
<table class="TableBorderLTRBN" border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" align="center" width="85%">
	<tr>
<%@ include file="/WEB-INF/jsp/hrms/hr/retirementBenefits/commonSearch/departmentalEnq.jsp"%>

	</tr>	
</table>
</hdiits:fieldGroup>	
</div>	
<%}
catch(Exception e){
	e.printStackTrace();
}
%>