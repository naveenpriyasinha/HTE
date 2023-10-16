<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VacantPostDtls" value="${resValue.VacantPostDtls}"></c:set>

<script type="text/javascript" src="script/common/common.js"></script>
<script>
function generatePostCreatedExcel()
{
	//alert("generateExcel Calling");
	var url = "ifms.htm?actionFlag=generateVacantPostExcel&empStat=Y"; 
	document.frmVacantPostDtls.action = url ;
	document.frmVacantPostDtls.submit();
}

  
function saveReport() 
{
	 var oPrntWin = window.open("","_blank","width=1,height=1,left=1,top=1,menubar=yes,toolbar=yes,resizable=yes,location=no,scrollbars=yes");
	   oPrntWin.document.open();
	   var printcontent = document.getElementById("frmVacantPostDtls").innerHTML;
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
<hdiits:form name="frmVacantPostDtls" action="" id="frmVacantPostDtls" encType="multipart/form-data" validate="true" method="post">
<c:if test="${VacantPostDtls != null && VacantPostDtls[0] != null}">
<fieldset class="tabstyle" ><legend>Vacant Post Details</legend>
	<div class="scrollablediv" >	
    <display:table list="${VacantPostDtls}"  id="vo" style="width:100%"  pagesize="500" requestURIcontext="false" requestURI="" >	

		<display:setProperty name="paging.banner.placement" value="top" />	
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Employee Name" >		
				<c:out value="${vo[0]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Post Name" >					
				<c:out value="${vo[1]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="Post Type"  >
		        <c:out value="${vo[2]}"></c:out>
		</display:column> 
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Status" >					
				<c:out value="${vo[3]}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Designation" >					
				<c:out value="${vo[4]}"></c:out>
		</display:column>
		<%-- <display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="DDO Code" >					
				<c:out value="${vo[5]}"></c:out>
		</display:column> --%>
		<%-- <display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="DDO Office" >					
				<c:out value="${vo[6]}"></c:out>
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
