<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<!-- Added By Varun For GPF A/C Only : and / allow  Dt.02-08-2008-->
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<!-- Ended By Varun For GPF A/C Only : and / allow  Dt.02-08-2008-->

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="billNoList" value="${resValue.billNoList}" ></c:set>
<c:set var="lMonthList" value="${resValue.lMonthList}" ></c:set>
<c:set var="lArrYrLst" value="${resValue.lArrYrLst}" ></c:set>
<c:set var="lArrYrLst" value="${resValue.lArrYrLst}" ></c:set>
<c:set var="gisList" value="${resValue.gisList}" ></c:set>
<c:set var="actionName" value="${resValue.actionName}" ></c:set>

<c:set var="curMonth" value="${resValue.curMonth}" ></c:set>
<c:set var="curyear" value="${resValue.curyear}" ></c:set>


<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript">
function submitForm()
{
	//alert("==>  in fuction :: ");

	if(document.getElementById("billNo").value==-1) 
	{
		alert('Please select bill no');
		return;
	}

	
	//alert("=======> "+document.getElementById("EffectDate").value);
	if(document.getElementById("month").value== -1 ) 
	{
		alert('Please select month');
		return;
	}

	if(document.getElementById("year").value== -1 ) 
	{
		alert('Please select year');
		return;
	}
	var actionname = "${actionName}";
	//alert("action name :: "+actionname);
	
	if(actionname == 'getGisData')
	{
		if(document.getElementById("GIS").value== -1 ) 
		{
			alert('Please select GIS type');
			return;
		}
	}

	//alert("===> action name :: "+"${actionName}")
	 var answer = confirm ("Do you want to view report?");
	  if (answer)
	  {
		  //alert("in if");
		//document.ViewOuterFirst.action='./hrms.htm?actionFlag=getOuterData';
		document.ViewOuterFirst.action='./hrms.htm?actionFlag=${actionName}';
		document.ViewOuterFirst.submit();
		showProgressbar("Please wait<br>While Generating Outer ...");
	  }	
}

function GoToPage() 
{
	document.ViewOuterFirst.action = "./hrms.htm?actionFlag=getHomePage";
	document.ViewOuterFirst.submit();
}
</script>

<hdiits:form name="ViewOuterFirst" validate="true" method="POST" action="./hrms.htm?actionFlag=${actionName}" encType="multipart/form-data">


<c:if test="${actionName eq 'getGisData'}">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>GIS</b></a></li>
		</ul>
	</div>
</c:if>

<c:if test="${actionName eq 'getOuterData'}">
<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>Outer</b></a></li>
		</ul>
</div>
</c:if>

<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
		<br/><br/>
		<table class="tabtable" border="0" bordercolor="black" align="center">
			
			<tr>
	   		<td width="20%"></td>
	   		<TD  align="center" class="Label" width="5%">
		 		<b><fmt:message key="BillGrpPost.BillNo" bundle="${commonLables}"/></b></TD>
		 		<td width="1%"><b>:</b></td>
		    	<TD	width ="30%">
		    	<select style="width:630px" id="billNo"  name="billNo" size="1" sort="false" caption="Bill No" captionid="BillNo" validation="sel.isrequired" mandatory="true" default="-1">
			    		<option value="-1">----------------------------------------Select----------------------------------------</option>
			  			<c:forEach items="${billNoList}" var="billNoList">
			    		   <option value="${billNoList.dcpsDdoBillGroupId}" title="${billNoList.dcpsDdoBillDescription}">${billNoList.dcpsDdoBillDescription} </option>
			    		</c:forEach>
	   			</select>
	  			 </TD>
	  			 <td width="30%"></td>
			</tr>
			<tr/><tr/>
			<tr>
	   		<td width="20%"></td>
	   		<TD  align="center" class="Label" width="5%">
		 		<b><fmt:message key="PR.MONTH" bundle="${commonLables}"/></b></TD>
		 		<td width="1%"><b>:</b></td>
		    	<TD	width ="30%"><hdiits:select style="width:50%" id="month" name="month" size="1" sort="false" caption="Month" captionid="Month" validation="sel.isrequired" mandatory="true" default="${curMonth}">
	    		<hdiits:option value="-1">---------Select---------</hdiits:option>
	  		<c:forEach items="${lMonthList}" var="lMonthList">
	    		   <hdiits:option value="${lMonthList.lookupShortName}" >${lMonthList.lookupName} </hdiits:option>
	    		</c:forEach>
	   							</hdiits:select>
	  			 </TD>
	  			 <td width="30%"></td>
			</tr>
			<tr/><tr/><tr/><tr/>
			<tr>
	   		<td width="20%"></td>
	   		<TD  align="center" class="Label" width="5%">
		 		<b><fmt:message key="PR.YEAR" bundle="${commonLables}"/></b></TD>
		 		<td width="1%"><b>:</b></td>
		    	<TD	width ="30%"><hdiits:select style="width:50%" id="year" name="year" size="1" sort="false" caption="Year" captionid="Year" validation="sel.isrequired" mandatory="true" default="${curyear}">
	    		<hdiits:option value="-1">---------Select---------</hdiits:option>
	  			<c:forEach items="${lArrYrLst}" var="lArrYrLst">
	    		   <hdiits:option value="${lArrYrLst}" >${lArrYrLst} </hdiits:option>
	    		</c:forEach>
	   							</hdiits:select>
	  			 </TD>
	  			 <td width="30%"></td>
			</tr>
			
			<tr/><tr/><tr/><tr/>
			<c:if test="${actionName eq 'getGisData'}">
			
			<tr>
	   		<td width="20%"></td>
	   		<TD  align="center" class="Label" width="10%">
		 		<b><fmt:message key="PR.GISTYPE" bundle="${commonLables}"/></b></TD>
		 		<td width="1%"><b>:</b></td>
		    	<TD	width ="30%"><hdiits:select style="width:50%" id="GIS" name="GIS" size="1" sort="false" caption="GIS" captionid="GIS" validation="sel.isrequired" mandatory="true" default="-1">
	    		<hdiits:option value="-1">---------Select---------</hdiits:option>
	  			<c:forEach items="${gisList}" var="gisList">
	    		   <hdiits:option value="${gisList.lookupShortName}" >${gisList.lookupName} </hdiits:option>
	    		</c:forEach>
	   							</hdiits:select>
	  			 </TD>
	  			 <td width="30%"></td>
			</tr>
			</c:if>
			
			
			
		<!-- 	<tr><td></td><td></td><td></td>
			<td>
			<input type="submit" value="Show Report"/>
			</td></tr>
		 -->
		
		</table>	
		 <br/><br/>
				<table class="tabNavigationBar">
				<tr><td></td><td></td><td></td></tr>
 				<tr>
					<td align="center" width="80%">
						<!-- <input type="submit" value="Show Outer"/> -->
						<hdiits:button name="btnouter" value="ShowOuter" caption="View"	id="btnouter" captionid="View" onclick="submitForm()" type="button"/>
						
						<hdiits:button name="Back" value="Back" caption="Back"	id="Back" captionid="Back" onclick="GoToPage()" type="button"/>
					</td>
				</tr>
				
				</table>		
		</div>
  	

		
<script type="text/javascript"> initializetabcontent("maintab");</script>
		
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
