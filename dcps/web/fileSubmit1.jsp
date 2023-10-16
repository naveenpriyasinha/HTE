
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@page
	import="org.apache.commons.fileupload.disk.DiskFileItemFactory,java.io.*"%>
<%@ page
	import="java.util.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.servlet.*"%>

<%
	int lIntUploadFlag = 0;
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	List items = null;
	String cardexNo1 = "";
	String IddoCode1 ="";
	String InchargeDdoCode1 = "";
	String inchargeStDt1 ="";
	String inchargeEnDt1 ="";
	String cardNo1 = "";
	String serverPathStr = application.getRealPath("/images/digiSigs");
	String tempFilePath ="";
	//String serverPathStr = "D://UPLOADED-FILES";
	
	try {

		items = upload.parseRequest(request);
		Iterator iter = items.iterator();
		FileItem item1 = null;
		byte[] barray = null;

		while (iter.hasNext()) {
			item1 = (FileItem) iter.next();
			String fieldName = item1.getFieldName();
			if (fieldName.equals("IddoCode")) {
				IddoCode1 = item1.getString();
			}
			if (fieldName.equals("InchargeDdoCode")) {
				InchargeDdoCode1 = item1.getString();
			}
			if (fieldName.equals("inChargeStDt")) {
				inchargeStDt1 = item1.getString();
			}
			
			if (fieldName.equals("inChargeEnDt")) {
				inchargeEnDt1 = item1.getString();
			}
			if (fieldName.equals("id_crdxNo")) {
				cardexNo1 = item1.getString();
			}
			if (fieldName.equals("id_cardNo")) {
				cardNo1 = item1.getString();
			}
			if(fieldName.equals("Iddoimage")){
				tempFilePath = serverPathStr + "\\" + cardexNo1 +"-"+cardNo1+ ".jpg";
				System.out.println("Temp Path is:"+tempFilePath);
				File f = null;
				FileOutputStream fo = null;
				File dir = new File(serverPathStr);
				
				if (!dir.exists()) {
					dir.mkdir();
				}//if ends
				
				f = new File(tempFilePath);
				fo = new FileOutputStream(f);
				InputStream is = null;
				is = item1.getInputStream();
				int n;
				while ((n = is.available()) > 0) {
					barray = new byte[n];
					int result = is.read(barray);
					if (result == -1)
						break;
				}
				is.close();
				fo.write(barray);
				fo.close();

			}
			if(fieldName.equals("IcrdxPath")){
				String IcrdxPath1 =item1.getString();
				String tempFilePath1=IcrdxPath1 + "\\" + cardexNo1 +"-"+cardNo1+ ".jpg";
				
				System.out.println("Local Machine : " +tempFilePath);
				System.out.println("Server Machine : " +tempFilePath1);
				
				
				File f1 = null;
				FileOutputStream fo1 = null;
				File dir1 = new File(IcrdxPath1);
				
				if (!dir1.exists()) {
					dir1.mkdir();
				}
				
				f1 = new File(tempFilePath1);
				fo1 = new FileOutputStream(f1);
				
				fo1.write(barray);
				lIntUploadFlag = 1;
				fo1.close();
			}
		}
	} catch (Exception e1) {
		e1.printStackTrace();
	}
%>
<hdiits:form name="InchargeImageUploadScreen" validate="true" method="post">
	<input type="hidden" name="cardNo" id="cardNo" value="<%=cardNo1%>" />
	<input type="hidden" name="cardexNo" id="cardexNo" value="<%=cardexNo1%>" />
	<input type="hidden" name="IddoCode" id="cardNo" value="<%=IddoCode1%>" />
	<input type="hidden" name="InchargeDdoCode" id="cardNo" value="<%=InchargeDdoCode1%>" />
	<input type="hidden" name="inChargeStDt" id="cardNo" value="<%=inchargeStDt1%>" />
	<input type="hidden" name="inChargeEnDt" id="cardNo" value="<%=inchargeEnDt1%>" />
</hdiits:form>
<script type="text/javascript">
	document.InchargeImageUploadScreen.action =  "ifms.htm?actionFlag=cardexDetails&cardexAction=insertCrdxDetails&flag=incharge";
	document.InchargeImageUploadScreen.submit();
//	document.InchargeImageUploadScreen.action = "ifms.htm?viewName=cardexInchargeInfo&flag=incharge";
//	document.InchargeImageUploadScreen.submit();
</script>
