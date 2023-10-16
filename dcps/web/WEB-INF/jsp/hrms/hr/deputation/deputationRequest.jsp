
<%
try {
%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>


<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<fmt:setBundle basename="resources.hr.deputation.Deputation" var="comLable"
	scope="request" />

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="deputreqLst" value="${resultValue.deputreqLst}">
</c:set>
<style type="text/css">

/* All form elements are within the definition list for this example */
.tablecell{
   border-bottom: solid 1px #DEDEDE;
	border:1;
	border-color:black;
	font-size: 11px;
	font-style: normal;
	font-weight: normal;
	background: white;
	text-align: left;
	padding: 1px;
	
}
.tablerow{
  border-top: solid 1px #333333;
	border-left: solid 1px #666666;
	border-right: solid 0px #888888;
	border-bottom: solid 1px #666666; 
	font-size: 11px;
	font-style: normal;
	font-weight: bold;
	text-align: center;
	background: #C9DFFF;
	color: black;
	padding: 1px;
	
	
}
</style>


<script type="text/javascript">

var emparray = new Array();
var empcount = 0 ;

function checkclick(form){


	if(form.checked == true)
	{

		
		emparray[empcount]=form.id;
		empcount++;
		
		
	}
	else{
	for(var i=0; i<emparray.length; i++)
  	{  
		
  
 		 if(emparray[i]== form.id){
  			emparray.splice(i,1);
		    empcount--;
  
  }
	}
}

}

function submitRecord()
{
		if(emparray.length>0){
		
		document.deputRequest.action='./hrms.htm?actionFlag=depSelectPost&emparray='+emparray;
		document.deputRequest.submit()	
		}else
		{
		 alert('<fmt:message bundle="${comLable}" key="Dep.selCheckBoxValid"/>');

		}
	
}	

function addRow()
{

	
	deleteAllCheckedUserId();

				
	

			
	
}
function deleteAllCheckedUserId() {
	var a = document.getElementsByName('check');
	for(i=a.length ;  i>=1 ; i--) {
			if(a[i-1].checked==true) {
			document.getElementById('row').deleteRow(i);
			}
	}
}
function clearf(form)
{
	
			var idf=form.id; 
			var href='./hrms.htm?actionFlag=deputReqViewDtl&fileId='+idf;
			window.open(href,'chield', 'width=750,height=400,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
			
				
}
	function Closebt()
{	
	method="POST";
	document.deputRequest.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.deputRequest.submit();
}
</script>


<hdiits:form name="deputRequest" validate="true" method="POST">

	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<c:if test="${not empty resultValue.deputreqLst }">	
<hdiits:fieldGroup titleCaptionId="Dep.EmpDeputReq" bundle="${comLable}"  collapseOnLoad="true">
	<table width="100%">
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="Dep.EmpDeputReq" bundle="${comLable}"></fmt:message>
				 </u></strong> </font></td>
		</tr> -->
		<tr>
			<td>
			
			<c:set var="j" value="0" /> <%
 int b=0;
 %>
  <display:table list="${resultValue.deputreqLst}" id="row" requestURI="" export="false" style="width:100%" offset="1">
  <display:setProperty name="paging.banner.placement" value="bottom" />

				<display:column class="tablecell" titleKey="SrNo" 
					headerClass="tablerow" value="<%=b=b+1 %>"
					style="text-align: center" >
				</display:column>

				<display:column class="tablecell" titleKey="Dep.select"
					headerClass="tablerow" style="text-align: center">
					<hdiits:checkbox id="${row.distributionId}" name="check"
						value="${row.distributionId}" onclick="checkclick(this);" />

				</display:column>


				<display:column class="tablecell" titleKey="reqforclass" headerClass="tablerow" style="text-align: center">
			
			${row.reqForClass}
		</display:column>
				<display:column class="tablecell" titleKey="reqforpost" headerClass="tablerow" style="text-align: center">
			
			${row.reqForPost}
		</display:column>


				<display:column class="tablecell" titleKey="deputFromDate" 
					headerClass="tablerow" style="text-align: center">
			<fmt:formatDate value="${row.reqFromDate}" pattern="dd/MM/yyyy"/>
			
		</display:column>

				<display:column class="tablecell" titleKey="deputToDate"
					headerClass="tablerow" style="text-align: center">
			<fmt:formatDate value="${row.reqToDate}" pattern="dd/MM/yyyy"/>
			
			
		</display:column>

				<display:column class="tablecell" titleKey="addressForDeputn"
					headerClass="tablerow" style="text-align: center">
			
			${row.address}
		</display:column>
				<display:column class="tablecell" titleKey="viewDetails" 
					headerClass="tablerow" style="text-align: center">
					<a href="#" name="${row.deputationreqmtId}"
						id="${row.deputationreqmtId}" onclick="clearf(this)">Details </a>

				</display:column>

				<c:set var="j" value="${j+1}" />

			</display:table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" id="buttonTbl">
				<tr>

					<td align="right" width="50%"><hdiits:button name="submitB"
						captionid="submitB" bundle="${comLable}" type="button"
						onclick="submitRecord();" /></td>
						<td width="50%">
		<hdiits:button name="close" type="button" captionid="Dep.Close" bundle="${comLable}" onclick="Closebt()"/>
		</td>
				</tr>
	    	</table>
					</td>
		</tr>
		
	</table>
	</hdiits:fieldGroup>
	</c:if>
	
	<c:if test="${empty resultValue.deputreqLst }">	
	<br/>
<br/>
<br/>
	<br/>
<br/>
<br/>

<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	<tr>
		<td align="center">
		<hdiits:caption captionid="Dep.norecfnd" bundle="${comLable}"/>
		</td>
	</tr>
		<tr>
		<td align="center">
		<hdiits:button name="close" type="button" captionid="Dep.Close" bundle="${comLable}" onclick="Closebt()"/>
		</td>
		
		</tr>

</table>
	
	</c:if>
</hdiits:form>





<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
