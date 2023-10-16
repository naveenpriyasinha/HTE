<!--
 * @VERSION    : 1.0
 * Date        : 26 jun 2011
 * REV. HISTORY :
 *
 *-----------------------------------------------------------------------------
 * DATE          AUTHOR                  DESCRIPTION
 * 22-Oct-2007            Code Formation
 *-----------------------------------------------------------------------------
 **-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<fmt:setBundle basename="resources.pensionpay.PensionAlerts" var="pensionAlerts" scope="request" />

<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>
<c:set var="sOutPut" value="${resValue.sOutPut}"> </c:set>


<c:set var="sOutput4Print" value="${resValue.sOutput4Print}"> </c:set>
<c:set var="rptCode" value="${resValue.rptCode}"> </c:set>



<script language="javascript">

 currentPageNo = 1;
 jp = new Array();
 totalPage = parseFloat(0);
 condenceStart = "";
 condenceEnd = "";


function checkCondence()
{
 	if(jp[0].substring(0,10) == "<condence>")
 	{
 		condenceStart = "<condence>"; 
 		condenceEnd = "</condence>"
 		document.getElementById('id_chkCondence').disabled = true;
 	}
}

function printDotMatrix()
{

	var cw = window.open('','',"height=768,width=1024,top=0,left=0,status=no,scrollbars=yes,toolbar=no,menubar=no,location=no");
		cw.document.write('<style media="print">');
	    cw.document.write('@FONT-FACE { font-family: sans-serif;}');
	    cw.document.write('@media print {BODY { font-size: 10pt }}');
	    cw.document.write('@media screen {BODY { font-size: 10pt } }');
	    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
	    cw.document.write('@page cheque{ size 14in 12in; margin: 5cm }');
        cw.document.write('DIV {page: cheque; page-break-after: left;  }');
        cw.document.write('</style>');
        cw.document.write('<body><pre style="padding-left:15px;padding-top:15px;overflow:scroll;" >');  
	    var two = document.getElementById("forP").value.replace(/\f/g,'<div></div>');
	    cw.document.write(two);	
	    cw.document.write('</body><pre>');  
	    cw.document.write('<script language="javascript">');
		cw.document.write("window.print();");
		cw.document.write( '<' + "/script" + '>');
     	
        cw.location.reload(false); 
}
  function printIt()
    {
		var outv = document.getElementById("forP").value;
		var one = outv.replace(/<div>/g," ");
		var two = one.replace(/<\/div>/g,'\f');
		var three = two.replace(/<pre>/g," ");
		var finalout = three.replace(/<\/pre>/g," ");
		
		if(document.getElementById('id_chkCondence').checked == true)
			printDirectCondense(finalout);
		else
			printDirect(finalout);
		
	}
	
	function getNextRec()
	{
		if(currentPageNo < totalPage)
		{
			currentPageNo = currentPageNo + 1;
			document.getElementById('curpage').value = currentPageNo;
			document.getElementById("id_lop").value = jp[currentPageNo - 1];
		}  
	}
	function getPreRec()
	{
		if(currentPageNo > 1)
		{
			currentPageNo = currentPageNo - 1;
			document.getElementById('curpage').value = currentPageNo;
			document.getElementById("id_lop").value = jp[currentPageNo - 1];
		}  
	}
	function getFirstRec()
	{
		currentPageNo = 1;
			document.getElementById('curpage').value = currentPageNo;
			document.getElementById("id_lop").value = jp[currentPageNo - 1];
		 
	}
  	function getLastRec()
	{
			currentPageNo = totalPage;
			document.getElementById('curpage').value = currentPageNo;
			document.getElementById("id_lop").value = jp[currentPageNo - 1];
		  
	}
	function goToPage()
	{
		var pageNo= document.getElementById('curpage').value;
		
		if(pageNo == "")
			return;
		if(isNaN(pageNo))
		{
			alert('<fmt:message key="CMN.ENTER_NO" bundle="${pensionAlerts}"/>');
			document.getElementById('curpage').value = currentPageNo;
			document.getElementById('curpage').focus();
			return;
		}
		
		pageNo = parseFloat(pageNo);
		
		if(pageNo < 0)
		{
			alert('<fmt:message key="CMN.ENTER_POSTV_NO" bundle="${pensionAlerts}"/>');
			document.getElementById('curpage').value = currentPageNo;
			document.getElementById('curpage').focus();
			return;
		}
		else if(pageNo > totalPage || pageNo == 0)
		{
			alert('<fmt:message key="CMN.THERE_NO_PAGE" bundle="${pensionAlerts}"/>');
			document.getElementById('curpage').value = currentPageNo;
			document.getElementById('curpage').focus();
			return;
		}
		else
		{
			currentPageNo = (pageNo - 1);
			getNextRec();
		}
	}
	
	function printRangeDirect()
	{
		var fromPage = document.getElementById('id_fromPage').value;
		var toPage = document.getElementById('id_toPage').value;
		if(fromPage == "" || toPage == "")
		{
			alert('<fmt:message key="CMN.ENTER_RANGE" bundle="${pensionAlerts}"/>');
			return;
		}
		if(isNaN(fromPage))
		{
			alert('<fmt:message key="CMN.ENTER_NO" bundle="${pensionAlerts}"/>');
			document.getElementById('id_fromPage').focus();
			return;
		}
		if(isNaN(toPage))
		{
			alert('<fmt:message key="CMN.ENTER_NO" bundle="${pensionAlerts}"/>');
			document.getElementById('id_toPage').focus();
			return;
		}
		fromPage = parseInt(fromPage);
		toPage = parseInt(toPage);
		if(fromPage < 1 || toPage < 1)
		{
			alert('<fmt:message key="CMN.INVALID_RANGE" bundle="${pensionAlerts}"/>');
			document.getElementById('id_fromPage').focus();
			return;
		}
		if(fromPage > toPage)
		{
			alert('<fmt:message key="CMN.INVALID_RANGE" bundle="${pensionAlerts}"/>');
			document.getElementById('id_fromPage').focus();
			return;
		}
		if(toPage > totalPage)
		{
			alert('<fmt:message key="CMN.INVALID_RANGE" bundle="${pensionAlerts}"/>');
			document.getElementById('id_toPage').focus();
			return;
		} 
		
		var outv = "";
		
		if(jp[0] != null && jp[0].indexOf('<condence>') != -1)
			outv = condenceStart;
			
		for(var i = fromPage; i <= toPage; i++)
		{
			outv = outv + jp[i-1];
			outv = outv + '\f';	
		}
		outv = outv + condenceEnd;
		
		if(document.getElementById('id_chkCondence').checked == true)
			printDirectCondense(outv);
		else	
			printDirect(outv);
		
	}
	
	
	function printDirectCondense(sData)
    {
		finalout = "<condence>" + sData + "</condence>"
		try
		{
			var v  = new Array();
		 	v[0]=finalout;
		 	var vObj = window.showModalDialog('common/printservice.html',v , 
      	 	"dialogHeight:200px;dialogWidth:300px;center:1;resizable:0;scroll:0");	
		}catch(e)
		{
			alert("#Error. Try Again" );
		}
		
	} 
	function printDirect(sData)
    {
		try
		{
		 var v  = new Array();
		 v[0]=sData;
		 var vObj = window.showModalDialog('common/printservice.html',v , 
      	 "dialogHeight:200px;dialogWidth:300px;center:1;resizable:0;scroll:0");	
      	return vObj;
		}catch(e)
		{
			alert("#Error. Try Again" );
		}
	}
	function winCls()
	{
	 	try
	 	{
	 	 	if(window.opener.document) 
	 	 	{
	 	 		window.close();
	 	 	}	
	 	}
	 	catch(err)
	 	{
	 		enableAjaxSubmit(true);
	 		document.forms[0].action = 'ifms.htm?actionFlag=getHomePage';
	 		document.forms[0].submit();
			//setAjaxSubmit(document.forms[1]);
	 	}
	}
</script>

<center>

<input type="checkbox" name="chkCondence" id="id_chkCondence">Condence &nbsp&nbsp

<input type="image" src="themes/ifmsblue/images/reports/first.gif" onclick="getFirstRec();">&nbsp&nbsp
<input type="image" src="themes/ifmsblue/images/reports/previous.gif" onclick="getPreRec();">&nbsp&nbsp
<input type="text" value="1" id="curpage" style="text-align:right;" size="4" onblur="goToPage();">&nbsp&nbsp
<input type="image" src="themes/ifmsblue/images/reports/next.gif" onclick="getNextRec();">&nbsp&nbsp
<input type="image" src="themes/ifmsblue/images/reports/last.gif" onclick="getLastRec();">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

From Page
<input type="text" value="" id="id_fromPage" style="text-align:right;" size="4"/>&nbsp

To Page
<input type="text" value="" id="id_toPage" style="text-align:right;" size="4"/>&nbsp
<input type="button" class="buttontag" value="Print Range" onclick="printRangeDirect();">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

	<input type="button" class="buttontag" value="Print" onclick="printDotMatrix()">
	<input type="button" class="buttontag" name = "printDirect" value="Print Direct" onclick="printIt()">
	<input type="button" class="buttontag" name = "cancel" value="Cancel" onclick="winCls()">
	
	 Total Pages
	<input type="text" value="" id="totalpage" readonly="readonly" style="text-align:right" size="4" >
	
	<br>
	<font face="courier new" >
		<div  style="width: 100%;overflow: scroll;" >
			<textarea  class="tabstyle" style="font-size:13px;padding-left: 10px;" readonly="readonly" rows="40" cols="230" name="lop" id="id_lop" >
			</textarea>
		</div>
	</font>
	
</center>

<textarea id='forP' style="visibility:hidden" >${sOutput4Print} </textarea>

<form name="reportForm" action="ifms.htm?actionFlag=getHomePage" method="get" ></form>
<script>

try
{
  var j = document.getElementById("forP").value;
  jp = j.split('\f');
 	
  if(jp[jp.length] != "")
  {
  	document.getElementById("totalpage").value = jp.length;
  	totalPage = parseFloat(jp.length);
  }
  else
  {
  	document.getElementById("totalpage").value = jp.length - 1;
  	totalPage = parseFloat(jp.length - 1);
  }	
  document.getElementById("id_lop").value = jp[currentPageNo - 1];
  checkCondence();
 }
 catch(e){
 }

function getOk()
{
	window.location ='ifms.htm?actionFlag=getHomePage';
}
function printDirectCondense(sData)
    {
		finalout = "<condence>" + sData + "</condence>"
		try
		{
			var v  = new Array();
		 	v[0]=finalout;
		 	var vObj = window.showModalDialog('common/printservice.html',v , 
      	 	"dialogHeight:200px;dialogWidth:300px;center:1;resizable:0;scroll:0");	
		}catch(e)
		{
			alert("#Error. Try Again" );
		}
		
	} 
	function printDirect(sData)
    {
		try
		{
		 var v  = new Array();
		 v[0]=sData;
		 var vObj = window.showModalDialog('common/printservice.html',v , 
      	 "dialogHeight:200px;dialogWidth:300px;center:1;resizable:0;scroll:0");	
      	return vObj;
		}catch(e)
		{
			alert("#Error. Try Again" );
		}
	}
	function printDirectList(sData)
    {
		try
		{
		 var v  = new Array();
		 v[0]=sData;
		 var vObj = window.showModalDialog('common/printservice.html',v , 
      	 "dialogHeight:300px;dialogWidth:350px;center:1;resizable:0;scroll:0");	
      	return vObj;
		}catch(e)
		{
			alert("#Error. Try Again" );
		}
	}
</script>