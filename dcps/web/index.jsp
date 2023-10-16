<%@ include file="/WEB-INF/jsp/core/include.jsp" %>
<script type="text/javascript" src="<c:url value="/script/login/getLoginWindow.js"/>"></script>
<script type="text/javascript">
	window.onload=function()
	{
		openLoginWindow();		

		window.open('', '_parent', '');
		win = top;
		self.opener = this;
		self.close();      			
	}	
</script> 