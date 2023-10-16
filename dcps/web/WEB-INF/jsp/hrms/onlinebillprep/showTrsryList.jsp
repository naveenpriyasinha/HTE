<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="HierarchyId" value="${resValue.HierarchyId}"></c:set>

<fmt:setBundle basename="resources.onlinebillprep.OnlineBillConstants" var="OBPMConst" scope="application"/>
<fmt:message key="STATUS.BillSentToTO" bundle="${OBPMConst}" var="BillSentToTO"/>

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title> Show Treasury List </title>
	 
	<link rel="stylesheet" href="/web/themes/hdiits/hdiits.css" type="text/css" />
	<link rel="stylesheet" href="/web/themes/hdiits/WebGUIStandards.css" type="text/css" />
	 	 
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
	<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
	<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
	<script type="text/javascript" src="script/common/commonfunctions.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script src="script/common/prototype.js" language="JavaScript" type="text/javascript"></script>
	<script type="text/javascript" src="script/hrms/onlinebillprep/Common.js"> </script>
	
	<script type="text/javascript">

	function getTrsryUsers() 
	{ 
		req = createXMLHttpRequest();
		var billNo ="<%= request.getParameter("BillNo")%>";
		var hrchyId = "${HierarchyId}";
		var tryOfficeId = document.forms[0].cmbTrsryList.value;
		var url = "ifms.htm?actionFlag=getTrsryUsers&BillNo=" + billNo + "&TsryOfficeId=" + tryOfficeId + "&CurrentHierarchy=" + hrchyId + "&ReqFlg=1";
		req.open('post', url); 
		req.onreadystatechange = handleResponse; 
		req.send(null); 
	}
	
	function removeUserList()
	{
	  var elSel = document.getElementById('cmbName');
	  var i;
	  for (i = elSel.length - 1; i>=0; i--) 
	  {
	      elSel.remove(i);
	  }
  	}
  	
  	function appendOption(Item0, Item1, Item2)
	{
	  var elOptNew = document.createElement('option');
	  elOptNew.text = Item0;
	  elOptNew.value = Item1 + "~" + Item2;
	  var elSel = document.getElementById('cmbName');
	
      elSel.add(elOptNew); // IE only
	}
  	
		
	function handleResponse() 
	{ 
		if(req.readyState == 4)
		{ 
			if(req.status == 200)
			{
  				var XMLDoc = req.responseXML.documentElement;
  				
				var UserChilds = XMLDoc.getElementsByTagName('UsrList');
				
				var Item0, Item1, Item2;
				
				removeUserList();
				 	
				for ( var i = 0 ; i < UserChilds.length ; i++ )
   				{
   					Item0 = UserChilds[i].childNodes[0].text;
   					Item1 = UserChilds[i].childNodes[1].text;
   					Item2 = UserChilds[i].childNodes[2].text;
   					
   					appendOption(Item0, Item1, Item2);
   				}
   				
   				var HierarchyId = XMLDoc.getElementsByTagName('HierarchyRefId');
   				window.opener.document.forms[0].ToHeirarchyRefId.value = HierarchyId[0].childNodes[0].nodeValue;
   				window.opener.document.forms[0].CurrBillStatus.value = "${BillSentToTO}"
   				
   				var SelCntrl = document.getElementById('cmbName');
   				var seltdTrsry = document.getElementById('cmbTrsryList');
   				window.opener.document.forms[0].seltdTrsry.value= seltdTrsry.value;
			}
		}
	}   
	
	</script>	
</head>

<hdiits:form name="frmTrsryLst" method="post" validate="false">
	<hdiits:table align="center" width="100%">
	  	<tr class="TableHeaderBG"> 
			<td align="center" colspan="2" class="HLabel">
				Treasury Offices
			</td>
		</tr>
		<hdiits:tr>
			<hdiits:td>
				<br />
				<select name="cmbTrsryList" mandatory="true" style="width:250px" onchange="getTrsryUsers()">
					<option selected="selected" value="-1"> --Select-- </option>
					<c:forEach var="trsry" items="${resValue.TrsryList}" varStatus="No">
						<option value="${trsry.commonId}"> <c:out value="${trsry.commonDesc}"/> </option>
					</c:forEach>						
				</select>
			</hdiits:td>
		</hdiits:tr>
	</hdiits:table>
</hdiits:form>

<jsp:include page="/WEB-INF/jsp/hrms/billproc/cmnSelectForward.jsp" />
