 
<%  System.out.println("----------IN JSP OF INTERMEDIATE---------------------"); 

	String lStrYear = "";
	String lStrMonthCode = "";
	String lStrBankCode = "";	
	String lStrAdviceCode = "";
	String lStrLcFromDt="";
	String lStrLcToDt="";
	String lStrEntryFromDt="";
	String lStrEntryToDt="";
	String lStrStatusCode="";
	
	if(request.getAttribute("lStrYear")!=null)
	{	
		lStrYear = request.getAttribute("lStrYear").toString();   
		System.out.println("----------IN JSP OF INTERMEDIATE----lStrYear-----------------"+lStrYear);
	}
	if(request.getAttribute("lStrMonthCode")!=null)
	{
		lStrMonthCode = request.getAttribute("lStrMonthCode").toString();
		System.out.println("----------IN JSP OF INTERMEDIATE----lStrMonthCode-----------------"+lStrMonthCode);
	}
	if(request.getAttribute("lStrBankCode")!=null)
	{
		lStrBankCode = request.getAttribute("lStrBankCode").toString();  
		System.out.println("----------IN JSP OF INTERMEDIATE----lStrBankCode-----------------"+lStrBankCode);
	}
	
	if(request.getAttribute("lStrAdviceCode")!=null)
	{
		lStrAdviceCode = request.getAttribute("lStrAdviceCode").toString();  
		System.out.println("----------IN JSP OF INTERMEDIATE----lStrAdviceCode-----------------"+lStrAdviceCode);
	}
	
	if(request.getAttribute("lStrLcFromDt")!=null)
	{
		lStrLcFromDt = request.getAttribute("lStrLcFromDt").toString(); 
		System.out.println("----------IN JSP OF INTERMEDIATE----lStrLcFromDt-----------------"+lStrLcFromDt);
	}
	
	if(request.getAttribute("lStrLcToDt")!=null)
	{
		lStrLcToDt = request.getAttribute("lStrLcToDt").toString(); 
		System.out.println("----------IN JSP OF INTERMEDIATE----lStrLcToDt-----------------"+lStrLcToDt);
	}
	
	if(request.getAttribute("lStrEntryFromDt")!=null)
	{
		lStrEntryFromDt = request.getAttribute("lStrEntryFromDt").toString(); 
		System.out.println("----------IN JSP OF INTERMEDIATE----lStrEntryFromDt-----------------"+lStrEntryFromDt);
	}
	  
	if(request.getAttribute("lStrEntryToDt")!=null)
	{
		lStrEntryToDt = request.getAttribute("lStrEntryToDt").toString();   
		System.out.println("----------IN JSP OF INTERMEDIATE----lStrEntryToDt-----------------"+lStrEntryToDt);
	}
	if(request.getAttribute("lStrStatusCode")!=null)
	{
		lStrStatusCode = request.getAttribute("lStrStatusCode").toString();   
		System.out.println("----------IN JSP OF INTERMEDIATE----lStrStatusCode-----------------"+lStrStatusCode);
	}
    
%>
<html>
<head>
<script>
function callUpdateLCDetails()
{ 
  var url = "ifms.htm?actionFlag=reportService&reportCode=150005&action=generateReport&finYear="+'<%=lStrYear%>'+"&adviceNo="+'<%=lStrAdviceCode%>'+"&month="+'<%=lStrMonthCode%>'+"&bank="+'<%=lStrBankCode%>'+"&lcValidfrom="+'<%=lStrLcFromDt%>'+"&lcValidto="+'<%=lStrLcToDt%>'+"&entryDatefrom="+'<%=lStrEntryFromDt%>'+"&entryDateto="+'<%=lStrEntryToDt%>'+"&status="+'<%=lStrStatusCode%>';        
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
