 
<%  System.out.println("----------IN JSP OF INTERMEDIATE FOR APPROVED---------------------"); 

	String lStrDivId = "";
	String lStrAdviceCode = "";
	String lStrMonthCode = "";
	String lStrBankCode = "";	
	String lStrDept="";
	String lStrApproved="";
	String lStrFromDt="";
	String lStrToDt="";
	
	if(request.getAttribute("lStrDivId")!=null)
	{	
		lStrDivId = request.getAttribute("lStrDivId").toString();   
		System.out.println("----------IN JSP OF INTERMEDIATE FOR APPROVED----lStrDivId-----------------"+lStrDivId);
	}
	if(request.getAttribute("lStrAdviceCode")!=null)
	{	
		lStrAdviceCode = request.getAttribute("lStrAdviceCode").toString();   
		System.out.println("----------IN JSP OF INTERMEDIATE FOR APPROVED----lStrAdviceCode-----------------"+lStrAdviceCode);
	}
	if(request.getAttribute("lStrMonthCode")!=null)
	{
		lStrMonthCode = request.getAttribute("lStrMonthCode").toString();
		System.out.println("----------IN JSP OF INTERMEDIATE FOR APPROVED----lStrMonthCode-----------------"+lStrMonthCode);
	}
	if(request.getAttribute("lStrBankCode")!=null)
	{
		lStrBankCode = request.getAttribute("lStrBankCode").toString();  
		System.out.println("----------IN JSP OF INTERMEDIATE FOR APPROVED----lStrBankCode-----------------"+lStrBankCode);
	}
	
	if(request.getAttribute("lStrDept")!=null)
	{
		lStrDept = request.getAttribute("lStrDept").toString();  
		System.out.println("----------IN JSP OF INTERMEDIATE FOR APPROVED----lStrDept-----------------"+lStrDept);
	}
	
	if(request.getAttribute("lStrApproved")!=null)
	{
		lStrApproved = request.getAttribute("lStrApproved").toString(); 
		System.out.println("----------IN JSP OF INTERMEDIATE FOR APPROVED----lStrApproved-----------------"+lStrApproved);
	}
	
	if(request.getAttribute("lStrFromDt")!=null)
	{
		lStrFromDt = request.getAttribute("lStrFromDt").toString(); 
		System.out.println("----------IN JSP OF INTERMEDIATE FOR APPROVED----lStrFromDt-----------------"+lStrFromDt);
	}
	if(request.getAttribute("lStrToDt")!=null)
	{
		lStrToDt = request.getAttribute("lStrToDt").toString(); 
		System.out.println("----------IN JSP OF INTERMEDIATE FOR APPROVED----lStrToDt-----------------"+lStrToDt);
	}
	
	
    
%>
<html>
<head>
<script>
function callUpdateLCDetails()
{ 
  var url = "ifms.htm?actionFlag=reportService&reportCode=150008&action=generateReport&divCode="+'<%=lStrDivId%>'+"&adviceNo="+'<%=lStrAdviceCode%>'+"&month="+'<%=lStrMonthCode%>'+"&bank="+'<%=lStrBankCode%>'+"&dept="+'<%=lStrDept%>'+"&approved="+'<%=lStrApproved%>'+"&datefrom="+'<%=lStrFromDt%>'+"&dateto="+'<%=lStrToDt%>';        
  //alert(url); 
  window.opener.location.href = url;
  window.close();
}
</script>
</head>

<body>
<form  method="post">
</form>
</body>

<script>
callUpdateLCDetails()
</script>

</html>
