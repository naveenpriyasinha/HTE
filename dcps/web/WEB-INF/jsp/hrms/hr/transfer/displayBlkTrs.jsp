<%
try {

%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<fmt:setBundle basename="resources.hr.transfer.transferLabels" var="transferLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="listTrsVo" value="${resultValue.listTrsVo}"></c:set>
<c:set var="sNOlist" value="${resultValue.sNOlist}"></c:set>
<script type="text/javascript">
function Closebt()
{	
	method="POST";
	document.form1.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.form1.submit();
}
function clearf(form)
{


		
			var href='./hrms.htm?actionFlag=generateBlkTrsOrder&sNolist='+${sNOlist};
		
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
			
				
}

</script>
<style type="text/css">
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
<hdiits:form name="form1" validate="true" method="post">
 <br>
  <br>
  <br>
  
  <table align="center">
  
  
  
  <tr>
   <td align="center"> <font color="red" size="h4"> <b><hdiits:caption captionid="trn.admintrslist"
				bundle="${transferLables}" captionLang="single"></hdiits:caption></b></font>
  </td>
  </tr>
  
  
</table>
<br>
  <br>
<display:table id="ResultPage" export="false" style="width:100%" requestURI=""  name="${listTrsVo}" pagesize="5">
		
	
		<display:column class="tablecell" 
			headerClass="tablerow" value="${ResultPage.empfName}"
			style="text-align: center" titleKey="trn.empNm">
		</display:column>
		<display:column class="tablecell" 
			headerClass="tablerow" value="${ResultPage.currentLocation}"
			style="text-align: center" titleKey="trn.loc">
		</display:column>
		<display:column class="tablecell" 
			headerClass="tablerow" value="${ResultPage.locName}"
			style="text-align: center" titleKey="trn.locto">
		</display:column>
		<display:column class="tablecell" 
			headerClass="tablerow" value="${ResultPage.fromDate}"
			style="text-align: center" titleKey="trn.transferDate">
		</display:column>
		
		

	</display:table>
<br>
  <br>
  <table align="center">
<tr align="center">
<td align="center"><a href="#"  name="genOrder" id="genOrder" onclick="clearf(this)"><fmt:message key="Trn.generateOrder" bundle="${transferLables}"/> </a> </td>
</tr>
</table>
<br>
  <br>
<table align="center">
<tr align="center">

<td  align="center"><hdiits:button name="Closebutton"
				id="Closebutton" type="button" captionid="trn.Close"
				bundle="${transferLables}" onclick="Closebt()" /></td>	
</tr>
</table>


</hdiits:form>


<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
	