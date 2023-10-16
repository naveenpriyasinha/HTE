
<%@ include file="../core/include.jsp"%>
<%@page import="com.tcs.sgv.acl.valueobject.AclElementMst"%>
<%@page import="java.util.List"%>

<script type="text/javascript" src="script/common/mainNavJs.js"></script>
<c:set var="isFromFile" value="${param.fromFile}"></c:set>
<c:set var="fileId" value="${param.fileIdFromWorkFlow}"></c:set>

<script type="text/javascript" language="javascript" src="<c:url value="/script/workflow/LinkSpecificMenu.js"/>">
</script>	
<!--[if gte IE 6]>
	<link rel="stylesheet" href="<c:url value="/themes/${themename}/ie7fix.css"/>">
<![endif]-->
<%
	try
	{
		if(session.getAttribute("aclElementMstList") != null)
		{
			//List<AclElementMst> lAclElementMstList = (List<AclElementMst>) session.getAttribute("aclElementMstList");
			String lAclElementMstList = session.getAttribute("aclElementMstList").toString();
			session.removeAttribute("aclElementMstList");
			//System.out.println(" in jsp Maitrak  " + lAclElementMstList.size());
			Integer noOfLevelsInMenuStr = (Integer)session.getAttribute("noOfLevelsInMenu");
			int hideMenuLookupId = 0;
			if(request.getParameter("hideMenuLookupID") != null)
				hideMenuLookupId = Integer.parseInt(request.getParameter("hideMenuLookupID").toString());
			//int totalSize = lAclElementMstList.size();
%>	

	<table  width="100%">
		<tr>
		<td style="background-color: black">
		<div class="mlmenu horizontal themeclrHorizontal arrow" id="div1"><%out.print(lAclElementMstList.toString());%></div>
			</td>
			</tr>
			</table>
	<%
		}
		else
		{
			System.out.println(" in else of LinkSpecificMenu.jsp" );
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();	
	}
%>
