<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory,java.io.*"%>
<%@ page import="java.util.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.servlet.*,java.net.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject,java.util.Map"%>
<%@page import="com.tcs.sgv.common.valueobject.MstCardexPath"%>
<%	
	int lIntUploadFlag = 0;
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	ResultObject rs = (ResultObject) request.getAttribute("result");
	//Map map = (Map)rs.getResultValue();
	//MstCardexPath serverPathStr1 = (MstCardexPath)map.get("CardexPath");
	//String serverPath =(String)map.get("CardexPath");
	//System.out.println(serverPath);
	
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);	
	List items = null;
	String cardexNo1 = "";
	String billtype1 ="";
	String mjrhd1="";
	String ddoNo1 ="";
	String ddoName1 = "";
	String ddoCode1 ="";
	String postId1 ="";
	String crdxEnDt1 = "";
	String crdxStDt1 ="";
	String cardNo1 ="";
	String hidMajorHead1 = "";
	String hidBillType1 ="";
//	String crdxPath1 ="";
	String serverPathStr =  application.getRealPath("/images/digiSigs");
	String tempFilePath = null;
	try {

		items = upload.parseRequest(request);
		Iterator iter = items.iterator();
		FileItem item1 = null;
		byte[] barray = null;
		byte[] barray1 = null;
		
		
		while (iter.hasNext()) {
			item1 = (FileItem) iter.next();
			String fieldName = item1.getFieldName();			
			if (fieldName.equals("cardexNo")) {
				cardexNo1 = item1.getString();
			}
			if (fieldName.equals("postId")) {
				postId1 = item1.getString();
			}
			
			if(fieldName.equals("ddoCode")){
				ddoCode1 = item1.getString();
			}
			if(fieldName.equals("billtype")){
				billtype1 = item1.getString();
			}
			if(fieldName.equals("mjrhd")){
				mjrhd1 = item1.getString();
			}
			if(fieldName.equals("ddoName")){
				ddoName1 = item1.getString();
			}
			if(fieldName.equals("ddoNo")){
				ddoNo1 = item1.getString();
			}
			if(fieldName.equals("crdxStDt")){
				crdxStDt1 = item1.getString();
			}
			if(fieldName.equals("crdxEnDt")){
				crdxEnDt1 = item1.getString();
			}
			if(fieldName.equals("cardNo")){
				cardNo1 = item1.getString();
			}
			if(fieldName.equals("hidBillType1")){
				hidBillType1 = item1.getString();
			}
			if(fieldName.equals("hidMajorHead1")){
				hidMajorHead1 = item1.getString();
			}
			
			if(fieldName.equals("ddoimage")){
				tempFilePath = "\\UPLOADED-FILES" + "\\" + cardexNo1 +"-"+cardNo1+ ".jpg";
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
			if(fieldName.equals("crdxPath")){
				String crdxPath =item1.getString();
				String tempFilePath1="\\\\"+crdxPath + "\\" + cardexNo1 +"-"+cardNo1+ ".jpg";
				
				System.out.println("Local Machine : " +tempFilePath);
				System.out.println("Server Machine : " +tempFilePath1);
				
				
				File f1 = null;
				FileOutputStream fo1 = null;
				File dir1 = new File(crdxPath);
				
				if (!dir1.exists()) {
					dir1.mkdir();
				}
				
				f1 = new File(tempFilePath1);
				fo1 = new FileOutputStream(f1);
				
				fo1.write(barray);
				lIntUploadFlag = 1;
				fo1.close();
			}
//				f1 = new File(tempFilePath);
//				fo1 = new FileOutputStream(f1);
				
//				is = item1.getInputStream();
//				int n1;
//				while ((n1 = is.available()) > 0) {
///					barray1 = new byte[n1];
//					int result1 = is.read(barray);
//					if (result1 == -1)
//						break;
				//}
				//is.close();
				//fo1.write(barray1);
				
//				fo1.close();
	//		}
			
		
	}
	}
	catch (Exception e1) {
		e1.printStackTrace();
	}
	
 %> 
<hdiits:form name="ImageUploadScreen" validate="true" method="post">
<input type="hidden" name="cardexNo" id="cardexNo" value="<%=cardexNo1%>" />
<input type="hidden" name="billtype" id="billtype" value="<%=billtype1%>" />
<input type="hidden" name="mjrhd" id="mjrhd" value="<%=mjrhd1%>" />
<input type="hidden" name="ddoName" id="ddoName" value="<%=ddoName1%>" />
<input type="hidden" name="ddoNo" id="ddoNo" value="<%=ddoNo1%>" />
<input type="hidden" name="crdxStDt" id="crdxStDt" value="<%=crdxStDt1%>" />
<input type="hidden" name="crdxEnDt" id="crdxEnDt" value="<%=crdxEnDt1%>" />
<input type="hidden" name="cardNo" id="cardNo" value="<%=cardNo1%>" />
<input type="hidden" name="postId" id="postId" value="<%=postId1%>" />
<input type="hidden" name="ddoCode" id="ddoCode" value="<%=ddoCode1%>" />
<input type="hidden" name="hidBillType" id="hidBillType" value="<%=hidBillType1%>"/>
<input type="hidden" name="hidMajorHead" id="hidMajorHead" value="<%=hidMajorHead1%>" />
</hdiits:form>
<script type="text/javascript">
	document.ImageUploadScreen.action = "ifms.htm?actionFlag=cardexDetails&cardexAction=insertCardex";	
	document.ImageUploadScreen.submit();
	
</script>
