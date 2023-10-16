<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ViewCreatedPostDtls" value="${resValue.ViewCreatedPostDtls}"></c:set>

<script type="text/javascript" src="script/common/common.js"></script>
<script>
function generatePostCreatedExcel()
{
	//alert("generateExcel Calling");
	var url = "ifms.htm?actionFlag=generatePostCreatedExcel&empStat=Y";
	document.frmViewCreatedPostDtls.action = url ;
	document.frmViewCreatedPostDtls.submit();
}

/*   function loadEmpPostCreatedDtls()
{	
	var Ddocode = document.getElementById("txtDdocode").value;
	if(Ddocode.trim() == "")
	{
		alert("Ddocode Cannot be Empty.");
		document.getElementById("txtDdocode").focus();		
		return false;
	}		
	else
	{
		var url = "ifms.htm?actionFlag=loadEmpDtlsDdoWise&Ddocode="+Ddocode+"&empStat=N&elementId=700215";
		document.frmEmpStatistics.action = url ;
		document.frmEmpStatistics.submit();
	}
		
} 
  
   */
  
function saveReport() 
{
	 var oPrntWin = window.open("","_blank","width=1,height=1,left=1,top=1,menubar=yes,toolbar=yes,resizable=yes,location=no,scrollbars=yes");
	   oPrntWin.document.open();
	   var printcontent = document.getElementById("frmViewCreatedPostDtls").innerHTML;
	   oPrntWin.document.write(printcontent);
	   //oPrntWin .document.execCommand("SaveAs",true,"C:\\abc.html");
	   oPrntWin .document.execCommand("SaveAs");
	  oPrntWin.document.close();
}
function printReport() 
{

	//document.getElementById('btnExporttoExcel').style.visibility = 'hidden'; // hide
	//document.getElementById('Back').style.visibility = 'hidden'; // hide   
	//document.getElementById('Save').style.visibility = 'hidden'; // hide   
	 window.print();
	 document.getElementById('Print').style.visibility = 'visible'; // show 
	         
	//document.getElementById('Back').style.visibility = 'visible'; // show 
	//document.getElementById('Save').style.visibility = 'visible'; // show 
	

}
</script>
<style>

 @media print {
     .scrollablediv {
         width: 100%;
         height: 100% !important;
    }
     div#content * {
         font-size: 8px;
         padding: 0.5px;
    }
     div#pageContent {
         overflow-x: auto;
    }
     div#footer, input#btnprintReport, input#btnExporttoExcel, div#header, div#nav, div#currentApplication,span.pagelinks,span.pagebanner,input#btnsaveReport,.hidetb {
         display: none !important;
    }
     div#content * {
         word-break: break-all;
    }
   .scrollablediv {
    overflow: visible !important;
}
div#content {
    overflow: hidden;
    width: 98% !IMPORTANT;
}
}

</style>
<hdiits:form name="frmViewCreatedPostDtls" action="" id="frmViewCreatedPostDtls" encType="multipart/form-data" validate="true" method="post">
<c:if test="${ViewCreatedPostDtls != null && ViewCreatedPostDtls[0] != null}">
<fieldset class="tabstyle" ><legend>View Created Post</legend>
	<div class="scrollablediv" >	
    <display:table list="${ViewCreatedPostDtls}"  id="vo" style="width:100%"  pagesize="500" requestURIcontext="false" requestURI="" >	

		<display:setProperty name="paging.banner.placement" value="top" />	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Employee Name" >		
				<c:out value="${vo[0]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Designation" >					
				<c:out value="${vo[1]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Status" >					
				<c:out value="${vo[3]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Post Name" >					
				<c:out value="${vo[4]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="DDO Code" >					
				<c:out value="${vo[5]}"></c:out>
		</display:column>
		<%-- <display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Post Type"  >
			<c:if test="${vo[2]==10001198130}">
				<c:out value="Temporary"></c:out>
			</c:if> 
			<c:if test="${vo[2]==10001198129}">
				<c:out value="Permanent"></c:out>
			</c:if> 
			<c:if test="${vo[2]==10001198155}">
				<c:out value="Statutory"></c:out>
			</c:if>
		</display:column> --%>
		</display:table>	
	</div>
</fieldset>
</c:if>
<%-- <hdiits:button id="btnExporttoExcel" name="btnExporttoExcel" value="Export to Excel" classcss="bigbutton" type="button" onclick="generatePostCreatedExcel()"/> --%>
<hdiits:button id="btnprintReport" name="btnprintReport" value="Print Report" classcss="bigbutton" type="button" onclick="printReport()"/>
<hdiits:button id="btnsaveReport" name="btnsaveReport" value="Save Report" classcss="bigbutton" type="button" onclick="saveReport()"/>
</hdiits:form>
<ajax:autocomplete source="txtDdocode" target="txtDdocode" baseUrl="ifms.htm?actionFlag=getDdoCodeForAutoComplete"
	parameters="searchKey={txtDdocode}" className="autocomplete" minimumCharacters="4" indicator="roleIndicatorRegion"/>	
