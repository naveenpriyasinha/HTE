
<%@ include file="../core/include.jsp"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="reportData" value="${resValue.reportData}"/>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<script Language = "JavaScript">

 function fn_print()
        {
            window.print();
            window.close();
        }
       
 function printFun(str)
 {
 		var arrayStr = str.innerHTML.split("^");
		var reportCode='<%=request.getParameter("reportCode")%>';

		var cw;
		 if(reportCode=='-3')
		 {
		 	cw = window.open("","","height=930,width=1000,top=00,left=0,status=no,toolbar=yes,menubar=no,location=no");
		 }
		 else
		 {
			cw = window.open("ifms.htm?actionFlag=printReportFlag&reportName="+document.getElementById("hidReportName").value+"&hidPpoNos="+document.getElementById("hidInwardNos").value,"","height=930,width=1000,top=00,left=0,status=no,toolbar=yes,menubar=no,location=no");
		 }
		cw.document.write("<link href=\"themes/ifmsblue/dppfReport.css\" type=\"text/css\" rel=\"stylesheet\">");
		cw.document.write('<style type="text/css" media="print">');
        cw.document.write('DIV {page-break-after: left;}');
        cw.document.write('</style>');
		for(i=0;i<arrayStr.length-1;i++)
		{
			if(i==(arrayStr.length-2))
				cw.document.write(arrayStr[i]);
			else
				cw.document.write("<div>"+arrayStr[i]+"</div>");
     	}
		cw.document.write('<script language="javascript">');
		cw.document.write("window.print();");
   		cw.document.write( '<' + "/script" + '>');
 }
</script>

		<table width="50%">
			<tr align='left'>
				<td colspan="3">
				
					<input type="hidden" name="hidReportName" id="hidReportName" value="<%=request.getParameter("reportName")%>"/>
					<input type="hidden" name="hidInwardNos" id="hidInwardNos" value="<%=request.getParameter("InwardNo")%>"/>
					Total SR : <INPUT id="totalCheque" name="totalCheque" size='5' maxlength='5'  type='text' value="" onkeyPress='validate(event);'>
				</td>
				<td></td>
			</tr>
 			<tr>
	 			<td width='5%'><input class='buttontag' type='button' onclick='printFun(forP);' value="Print All"></td>
				<td width='10%'>From Page Number : <input id="fromPage" name="fromPage" size='5' maxlength='5' type='text' value="1"></td>
				<td width='10%'>To Page Number : <input id="toPage" name="toPage" size='5' maxlength='5'  type='text' value=""></td>
				<td width='5%'><input class='buttontag'  type='button' onclick='printFun(forP);' value="Print"></td>
			</tr>			
		</table>
		
		<div id="forD" style="width:1000px;height:700px; overflow: scroll; overflow-y:scroll;border:1px solid;">
		     		${reportData}
		</div> 					
		
		<input class='buttontag'  type='button' onclick='ScrollDown();' value="Next">
		<input class='buttontag'  type='button' onclick='ScrollUp();' value="Previous">
		Current Page :
		<input  id="currantPage" name="currantPage" readonly='readonly' style='Border:none;' size='5' maxlength='5' type='text' value="1">
		<div id="forP" style="width:0px;height:0px; font-family:sans-serif; font-size:2pts; overflow: scroll; overflow-y:scroll;border:1px solid;visibility:hidden ; ">
			${reportData}
		</div>

	<script>
	
	function printIt()
	{
		var outv = document.getElementById("forP").innerHTML;
	 	outv = outv.replace(/\^/g,"");
	 	var vReturnValue = window.open('common/printservice.html',null,"height=200,width=300,top=200,left=400,status=no,toolbar=no,menubar=no,location=no");
	 	vReturnValue.document.getElementById("printData").value = outv;
	 	vReturnValue.close();	
   }
   
    function  rangePrint()
	{
		var fromPage =	 document.getElementById("fromPage").value;
		var toPage =	 document.getElementById("toPage").value;
	
		var outv = document.getElementById("forP").innerHTML;
		var s = outv.split('^');
		var page = '' ;	
		
		var sPage = fromPage -1 ;
		var ePage = toPage-1;
		
		for( i=sPage;i<=ePage;i++)
			page = page + s[i]  ;

		 document.getElementById("forP").innerHTML=page;
	}
	
	function validate(event)
	{
		if(event.keyCode >= 48 && event.keyCode <= 57 )  
		{}
		else
		event.keyCode =0;
	}
	
	 initText = document.getElementById("forP").innerHTML;
	 gTotalPages = initText.split('^');
	document.getElementById("toPage").value= gTotalPages.length;
	document.getElementById("totalCheque").value= gTotalPages.length;
	 currentPage = 1;
	
	function ScrollDown()
	{
		document.getElementById("forD").doScroll("pageDown");
		document.getElementById("forD").doScroll("down");
		if(currentPage <	 gTotalPages.length)
		 currentPage= currentPage+1;
		document.getElementById("currantPage").value=currentPage;	
		
		
		var outv = document.getElementById("forP").innerHTML;
		var s = outv.split('^');
		var page = '' ;	
		
		page = s[currentPage-1];	
		
		document.getElementById("forD").value=page;	
	}
	function ScrollUp()
	{
		document.getElementById("forD").doScroll("pageUP");
		document.getElementById("forD").doScroll("up");
		if(currentPage > 1)
		currentPage= currentPage-1;
		document.getElementById("currantPage").value=currentPage;
		
		var outv = document.getElementById("forP").innerHTML;
		var s = outv.split('^');
		var page = '' ;	
		
		page = s[currentPage-1];	
		
		document.getElementById("forD").value=page;		
		
	}
	</script>
