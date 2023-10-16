<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<style type="text/css">    
            .pg-normal {
                color: black;
                font-weight: normal;
                text-decoration: none;    
                cursor: pointer;    
            }
            .pg-selected {
                color: black;
                font-weight: bold;        
                text-decoration: underline;
                cursor: pointer;
            }
 </style>
       
<script>

function Pager(tableName, itemsPerPage) {
    this.tableName = tableName;
    this.itemsPerPage = itemsPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.inited = false;
    
    this.showRecords = function(from, to) {        
        var rows = document.getElementById(tableName).rows;
        // i starts from 1 to skip table header row
        for (var i = 1; i < rows.length; i++) {
            if (i < from || i > to)  
                rows[i].style.display = 'none';
            else
                rows[i].style.display = '';
        }
    }
    
    this.showPage = function(pageNumber) {
    	if (! this.inited) {    		
    		return;
    	}

        var oldPageAnchor = document.getElementById('pg'+this.currentPage);
        if(oldPageAnchor!=null)
        {
	        oldPageAnchor.className = 'pg-normal';
        
	        this.currentPage = pageNumber;
     	    var newPageAnchor = document.getElementById('pg'+this.currentPage);
     
	        newPageAnchor.className = 'pg-selected';
        
        	var from = (pageNumber - 1) * itemsPerPage + 1;
        	var to = from + itemsPerPage - 1;
        	this.showRecords(from, to);
        }
    }   
    
    this.prev = function() {
        if (this.currentPage > 1)
            this.showPage(this.currentPage - 1);
    }
    
    this.next = function() {
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        }
    }                        
    
    this.init = function() {
        var rows = document.getElementById(tableName).rows;
        var records = (rows.length - 1); 
        this.pages = Math.ceil(records / itemsPerPage);
        this.inited = true;
    }

    this.showPageNav = function(pagerName, positionId) {
    	if (! this.inited) {    		
    		return;
    	}
    	var element = document.getElementById(positionId);
    	
    	var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> &#171 Prev </span> | ';
        for (var page = 1; page <= this.pages; page++) 
            pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> | ';
        pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"> Next &#187;</span>';            
        
        element.innerHTML = pagerHtml;
    }
}



					
function submitCheckedContent()
{
	if(document.getElementById('codeForSubmit').value == 1)
	{	
		var checkboxes=document.forms[0].elements['checkPoint'];
		if( !(isNaN(checkboxes.length)) )
		{
			for(var i=0;i<checkboxes.length;i++)
			{
				if(checkboxes[i].checked)
				{
					parent.document.getElementById('hiddenCheckBox').value=checkboxes[i].value+"~"+parent.document.getElementById('hiddenCheckBox').value;
				}
			}
		}
		else
		{
			if(document.forms[0].elements['checkPoint'].checked)
			{
				parent.document.getElementById('hiddenCheckBox').value=document.forms[0].elements['checkPoint'].value+"~"+parent.document.getElementById('hiddenCheckBox').value;
			}
		}
		
	}
	else
	{
		return true;
	}
	
}
function submitSearchData(desgSel,authSel)
{
	
	
	document.getElementById('hiddenDesgList').value=desgSel;
	document.getElementById('hiddenAuthList').value=authSel;
	document.searchResult.submit();

}

function populatePoints(cellPoint,langId)
{
	var parent=window.parent;

	if(cellPoint.languageId==1)
	{
		var engId ="pointCell_"+cellPoint.pointcode+"_"+1;
		parent.parent.document.getElementById("GoalEn").innerHTML=document.getElementById(engId).innerHTML;
		var gujId ="pointCell_"+cellPoint.pointcode+"_"+2;
		parent.parent.document.getElementById("GoalGu").innerHTML=document.getElementById(gujId).innerHTML;
	}
	else if(cellPoint.languageId==2)
	{
		var engId ="pointCell_"+cellPoint.pointcode+"_"+1;
		parent.parent.document.getElementById("GoalEn").innerHTML=document.getElementById(engId).innerHTML;
		var gujId ="pointCell_"+cellPoint.pointcode+"_"+2;
		parent.parent.document.getElementById("GoalGu").innerHTML=document.getElementById(gujId).innerHTML;
	}


}


</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="searchResultsList" value="${resValue.searchResultsList}"></c:set>
<c:set var="searchLength" value="${resValue.searchLength}"></c:set>

<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>

<hdiits:form name="searchResult"  method="POST" validate="true" action="./hrms.htm?actionFlag=getExistingAcrPoints" encType="multipart/form-data">

<hdiits:hidden id="hiddenDesgList"  name="hiddenDesgList" />
<hdiits:hidden id="hiddenAuthList"  name="hiddenAuthList" />
<hdiits:hidden id="codeForSubmit"  name="codeForSubmit" default="0" />
<table border="2" width="100%" id="pointTbl">

	
	
	<c:if test="${ searchLength == 0 }">
	<tr width="100%" colspan="3">
		<td align="center" width="100%" colspan="1" bgcolor="lightblue">
			<b><fmt:message key="HR.ACR.noRecordsFound" bundle="${commonLables}" /></b>
		</td>
	</tr>
		<script>document.getElementById("codeForSubmit").value=0;</script>
	</c:if>

	<c:if test="${not empty searchResultsList}">
	<tr width="100%" colspan="3" >
		<td colspan="1" bgcolor="lightblue" align="center">
			<b>
				<fmt:message key="HR.ACR.Select" bundle="${commonLables}" />
			</b>
		</td>
		<td colspan="1" bgcolor="lightblue"> 
			<b>
				<fmt:message key="HR.ACR.Designation" bundle="${commonLables}" />
			</b>
		</td>
		<td colspan="1" bgcolor="lightblue"> 
			<b>
				<fmt:message key="HR.ACR.Authority" bundle="${commonLables}" />
			</b>
		</td>
		<td colspan="1" bgcolor="lightblue">
			<b>
				<fmt:message key="HR.ACR.PointEnglish" bundle="${commonLables}" />
			</b>
		</td>
		<td colspan="1" bgcolor="lightblue"> 
			<b>
				<fmt:message key="HR.ACR.PointGujarati" bundle="${commonLables}" />
			</b>
		</td>
	</tr>
		<script>document.getElementById("codeForSubmit").value=1;</script>
	</c:if>

				<c:set var="counter" value="0"></c:set>
	<tr width="100%" colspan="3">
		<c:if test="${not empty searchResultsList}">
			
				<c:set var="pointCode1" value="-1"></c:set>
				<c:set var="nextId" value="0"></c:set>		

			<c:forEach var="searchResults" items="${searchResultsList}" >			
				<c:if test="${pointCode1 != searchResults.pointCode}">
					</tr>
					<tr width="100%" colspan="3">
						<td colspan="1" align="center">
							<hdiits:checkbox name="checkPoint" id='checkPoint"+nextId+"' style="display:none" value="${searchResults.pointCode}" onchange=""/>
							<hdiits:a href="#" captionid="HR.ACR.Add" bundle="${commonLables}" onclick="populatePoints(document.getElementById('pointCell_${searchResults.pointCode}_${searchResults.cmnLanguageMst.langId}'))"/>
						</td>
						<td colspan="1" pointcode="${searchResults.pointCode}" languageId="${searchResults.cmnLanguageMst.langId}"  id = "dsgnCell_${searchResults.pointCode}_${searchResults.cmnLanguageMst.langId}" ondblclick="populatePoints(this);">
							${searchResults.dsgnCode}
						</td>
						<td colspan="1" pointcode="${searchResults.pointCode}" languageId="${searchResults.cmnLanguageMst.langId}"  id = "roleCell_${searchResults.pointCode}_${searchResults.cmnLanguageMst.langId}" ondblclick="populatePoints(this);">
							${searchResults.cmnLookupMst.lookupDesc}
						</td>	
				</c:if>						
						<td colspan="1" id = "pointCell_${searchResults.pointCode}_${searchResults.cmnLanguageMst.langId}" pointcode="${searchResults.pointCode}" languageId="${searchResults.cmnLanguageMst.langId}" ondblclick="populatePoints(this);" >
							
							${searchResults.point}

						</td>					
				<c:set var="pointCode1" value="${searchResults.pointCode}"></c:set>	
					<c:set var="counter" value="${counter+1}"></c:set>	
			</c:forEach>
			
		</c:if>
	</tr>
</table>
<div id="pageNavPosition"></div>
<script>

 var pager = new Pager('pointTbl', 5); 
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);					
	
</script>
</hdiits:form>



<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>