
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.util.* " %>

      
      
      
<%

     String driverName = "com.ibm.db2.jcc.DB2Driver";

/*    String connectionUrl = "jdbc:db2://3.6.98.156:55000/";
String dbName = "HTE";    */
String connectionUrl = "jdbc:db2://10.187.201.236:50001/";
String dbName = "SALAARTH"; 
  
String userId = "ifms";
String password = "CMahait@4561";

	try {
	Class.forName(driverName);
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	}
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>
   
   
<head>
<title>MahaSevaarth Paybill Status</title>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <script type="text/javascript" src="js/tableHTMLExport.js"/></script>
    <script type="text/javascript" src="js/findbrowser.js"/></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
     
    <style>
	.text-center{
	text-align:center;
	}
	</style>
    
  <link href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
	
	
	<link href="https://cdn.datatables.net/buttons/1.6.1/css/buttons.dataTables.min.css">
	
	
	

	
<!--   <script src="https://code.jquery.com/jquery-3.3.1.js"></script> -->
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.rawgit.com/rainabba/jquery-table2excel/1.1.0/dist/jquery.table2excel.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>

    <script>
 /* $(document).ready(function() {
	 $("#excelbtn").click(function(){
	 $("#example").table2excel({
         filename: "Employees.xls"
     });
	 });
} ); */
  </script>
     
     
    <!--  <script>
     $('#excelbtn').click(function () {
    	    alert("hello");
    	   
    	});
     </script>
      -->
     
     <script>
 $(document).ready(function() {
    	var MyDate = new Date();
	var MyDateString;	

	
	var firstDay = new Date(MyDate.getFullYear(), MyDate.getMonth(), 1);
	
	
	//console.log(firstDay);
	
	fromDate = MyDate.getFullYear() + '-'
    + ('0' + (MyDate.getMonth()+1)).slice(-2) + '-'
    + ('0' + firstDay.getDate()).slice(-2);
	
	MyDateString = MyDate.getFullYear() + '-'
	             + ('0' + (MyDate.getMonth()+1)).slice(-2) + '-'
	             + ('0' + MyDate.getDate()).slice(-2);
	
	
	var curData=('0' + MyDate.getDate()).slice(-2)+'-'+ ('0' + (MyDate.getMonth()+1)).slice(-2) + '-'+MyDate.getFullYear();
    
	
	

    var c = document.createElement('canvas');
    var img = document.getElementById('image');
    c.height = img.naturalHeight;
    c.width = img.naturalWidth;
    var ctx = c.getContext('2d');

    ctx.drawImage(img, 0, 0, c.width, c.height);
    var logo = c.toDataURL();
    
    
    
    
$('#example').append('<caption style="caption-side: top">HTESevaarth Post Status Report</caption>');
	
    $('#example').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf'
        ],
		columnDefs: [
    { className: 'text-right', targets: [0,1,2] },
    { className: 'text-center', targets: [3,4,5,6] },
  ]
    } );
    
    
    
  
} ); 
  </script>
    
    <style type="text/css">
    .pageTitle {
    text-align: center;
    font-weight: 600;
    border: none !important;
}
/* table#example td:nth-child(5),table#example td:nth-child(4),table#example td:nth-child(6),table#example td:nth-child(7) {
    text-align: center;
    align-items:center;
} */
 .body-contents {
 margin-top: 90px;
    box-shadow: 0px 0px 7px #ccc;
    min-height: 550px;
}   .logo-mainn {
    border-bottom: 2px solid orange;
    padding-bottom: 5px;
}
    .navbar-inverse {
        background-color: #37bec3;
        border-color: #37bec3;
    }

    .navbar-nav li a {
        color: #fff !IMPORTANT;
    }

    .navbar-inverse .navbar-nav>.active>a {
        background-color: #157478 !important;
    }

    .navbar-inverse .navbar-nav>li:hover {
        background-color: #157478 !important;
    }

    .m-title {
        font-size: 25px;
        font-family: 'Alfa Slab One', arial;
        letter-spacing: 0px;
        color: #ef7c01;
        font-weight: 900;
        margin-bottom: 0;
    }

    .logo-sub-text {
        position: relative;
        top: 5px;
        color: #004b8f;
    }

    .logo-Title {
        margin: auto 0;
        padding: 0 15px;
    }

    .flex {
        display: flex;
        align-items: center;
    }

    img.logo-img {
        width: 100%;
        max-width: 90px;
    }

    img.logo-secondary {
        max-width: 75px;
    }

    img.logo-img2 {
        max-width: 90px;
    }

    .navbar {
        min-height: 30px !important;
    }
    @media screen and (max-width: 750px){
            .logobar1 {
        display: none;
    }
    .body-contents {
        min-height: auto !important;
    }  
        }
        
     .next,.previous,.dt-button {
    border-radius: 4px;
    border: 1px solid #428bca;
    padding: 3px 12px;
    margin-left: 5px;
    background: #428bca;
    color: #fff;
    
}
.dt-buttons{
margin-bottom:20px;
}
        div#example_info {
    text-align: right;
}
div#example_paginate {
    text-align: right;
    margin-top: 10px;
}
    </style>




</head>
  </div>
  <a href="">
<img src="images/mahasevaarth.png" id="image" name="image" crossorigin="anonymous" style="display:none;" ></a>
 
	    <div class="container-fluid body-contents">
        <div class="hearder-top">
            <div class="user-name-main row">
                <div id="txt" class="Heading-timer col-md-4"></div>
                <div class="col-md-4 deptNameheaderTop">
                    <input type="hidden" name="departmentCode" id="departmentCode" th:value="${departmentCode}" /> <span th:text="${departmentName}"></span>
                </div>
                <div class="col-md-4 user-name-main-top">
                    <span class="User-name2"><span id="userName" th:text="${loggedInUserType} +' |' "></span></span> <span class="User-name"> <span id="userName" th:text="${loggedInUserName}"> </span>
                    </span>
                </div>
            </div>
      
        <div class=" logo-mainn">
            <div class="row logo-row">
                <div class="col-md-9 flex">
                    <div class="logobar ">
                        <a class="logo" href="index.html"><img src="images/logo-v16.png" class="logo-img"></a>
                    </div>
                    <h5 class="logo-Title">
                        <p class="m-title">HTESevaarth</p>
                        <p class="logo-sub-text">Official Website of Government of Maharashtra</p>
                    </h5>
                </div>
                <div class="col-md-3 text-right">
                    <div class="logobar1">
                        <a class="logo-secondary-m" href="#"><img src="images/mahait_logo.png" class="logo-secondary"></a> <a class="logo" href="#"><img src="images/GOM.png" class="logo-img2"></a>
                    </div>
                </div>
            </div>
        </div>
      
	<!-- Ennd by Brijoy09042020 -->



<h2 align="center"><font><strong>HTESevaarth Post Status Report</strong></font></h2>
	<img src="/images/report.png" id="image" name="image" crossorigin="anonymous" style="display:none;" >
<div class="table-main">

<!-- <button id="excelbtn" class="chrome dt-button buttons-pdf buttons-html5">EXCEL </button> -->
        <!-- Table goes here -->
        <table class="table table-bordered" id="example">
            <thead>
              <tr>
              <tr>
                <th rospan="2">Sr No</th>
                <th colspan="3" >SANCTIONED</th>
                 <th colspan="4">FILLED</th>
				 <th colspan="2">TEMPORARY</th>
				  <th rospan="2">TOTAL FILLED</th>
				 <th colspan="3">VACANT</th>
              </tr>
              <tr>
              <th > </th>
                <th>PERMANENT SANCTIONED POST</th>
				 <th>TEMPORARY SANCTIONED POST</th>
				 <th>TOTAL SANCTIONED POST</th>
				 <th>PERMANENT FILLED</th>
				 <th>GPF(OPS) PERMANENT</th>
				 <th>NPS(DCPS) PERMANENT</th>
				 <th>TEMPORARY FILLED</th>
				 <th>GPF(OPS) TEMPORARY</th>
				 <th>NPS(DCPS) TEMPORARY</th>
				 <th> </th>
				 <th>PERMANENT VACANT</th>
				 <th>TEMPORARY VACANT</th>
				 <th>TOTAL VACANT </th>
              </tr>
            </thead>
            <tbody >

            
            
            
            
  






<tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql =" SELECT NVL(COUNT(CASE WHEN POST_TYPE_LOOKUP_ID=10001198129  THEN post.POST_id END),0) AS PERMANENT_SANCTIONED_POST, NVL(COUNT(CASE WHEN POST_TYPE_LOOKUP_ID=10001198130  THEN post.POST_id END),0) AS TEMPORARY_SANCTIONED_POST, NVL(COUNT(CASE WHEN POST_TYPE_LOOKUP_ID in(10001198130,10001198129)  THEN post.POST_id END),0) AS TOTAL_SANCTIONED_POST, NVL(COUNT(CASE WHEN POST_TYPE_LOOKUP_ID=10001198129 AND post.ACTIVATE_FLAG=1 THEN EMP.DCPS_EMP_ID END),0) AS PERMANENT_FILLED, NVL(COUNT( (CASE WHEN POST_TYPE_LOOKUP_ID=10001198129 AND EMP.DCPS_OR_GPF = 'N' and  post.ACTIVATE_FLAG=1 THEN EMP.DCPS_EMP_ID END)),0) AS GPF_PERMANENT, NVL(COUNT(  (CASE WHEN POST_TYPE_LOOKUP_ID=10001198129 AND EMP.DCPS_OR_GPF = 'Y' and  post.ACTIVATE_FLAG =1 THEN EMP.DCPS_EMP_ID END)),0) AS  NPS_PERMANENT, NVL(COUNT(CASE WHEN POST_TYPE_LOOKUP_ID=10001198130 AND post.ACTIVATE_FLAG <>0 THEN post.POST_ID END),0) AS TEMPORARY_FILLED, NVL(COUNT(  (CASE WHEN POST_TYPE_LOOKUP_ID=10001198130 AND EMP.DCPS_OR_GPF = 'N' and post.ACTIVATE_FLAG=1 THEN EMP.DCPS_EMP_ID END)),0) AS GPF_TEMPORARY, NVL(COUNT(  (CASE WHEN POST_TYPE_LOOKUP_ID=10001198130 AND EMP.DCPS_OR_GPF = 'Y' and post.ACTIVATE_FLAG=1 THEN EMP.DCPS_EMP_ID END)),0) AS NPS_TEMPORARY, NVL(COUNT(CASE WHEN post.ACTIVATE_FLAG <>0 THEN post.POST_ID END),0) AS TOTAL_FILLED, NVL(COUNT(CASE WHEN POST_TYPE_LOOKUP_ID=10001198129 AND post.ACTIVATE_FLAG=0 THEN postrlt.POST_ID END),0) AS PERMANENT_VACANT, NVL(COUNT(CASE WHEN POST_TYPE_LOOKUP_ID=10001198130 AND post.ACTIVATE_FLAG=0 THEN postrlt.POST_ID END),0) AS TEMPORARY_VACANT, NVL(COUNT(CASE WHEN  post.ACTIVATE_FLAG=0 THEN post.POST_ID END),0) AS TOTAL_VACANT FROM ORG_POST_MST post inner join HR_PAY_POST_PSR_MPG mpg on post.POST_ID=mpg.POST_ID inner join ifms.ORG_USERPOST_RLT as postrlt on post.POST_ID =postrlt.POST_ID inner join ifms.ORG_DESIGNATION_MST desig on post.DSGN_CODE=desig.DSGN_ID inner join ifms.ORG_DDO_MST ddo on post.LOCATION_CODE=ddo.LOCATION_CODE inner join ifms.MST_DCPS_DDO_OFFICE  ofc on ofc.ddo_code=ddo.ddo_code inner join ifms.RLT_ZP_DDO_MAP map on ddo.DDO_CODE=map.ZP_DDO_CODE inner join ifms.ZP_ADMIN_NAME_MST name on  ddo.ddo_type=name.id inner join ifms.ORG_EMP_MST org on postrlt.USER_ID=org.USER_ID inner join ifms.MST_DCPS_EMP emp on org.emp_id=emp.ORG_EMP_MST_ID inner join ifms.cmn_district_mst dist on cast(dist.district_id as varchar(20))= ofc.district where  map.STATUS=1  and emp.SUPER_ANN_DATE>sysdate and emp.zp_status=10 and post.DSGN_CODE<>1";

resultSet = statement.executeQuery(sql);
int srno=1;
while(resultSet.next()){
%>
<td><%=srno++%></td>
<td><%=resultSet.getString("PERMANENT_SANCTIONED_POST") %></td>
<td><%=resultSet.getString("TEMPORARY_SANCTIONED_POST") %></td>
<td><%=resultSet.getString("TOTAL_SANCTIONED_POST") %></td>
<td><%=resultSet.getString("PERMANENT_FILLED") %></td>
<td><%=resultSet.getString("GPF_PERMANENT") %></td>
<td><%=resultSet.getString("NPS_PERMANENT") %></td>
<td><%=resultSet.getString("TEMPORARY_FILLED") %></td>
<td><%=resultSet.getString("GPF_TEMPORARY") %></td>
<td><%=resultSet.getString("NPS_TEMPORARY") %></td>
<td><%=resultSet.getString("TOTAL_FILLED") %></td>
<td><%=resultSet.getString("PERMANENT_VACANT") %></td>
<td><%=resultSet.getString("TEMPORARY_VACANT") %></td>
<td><%=resultSet.getString("TOTAL_VACANT") %></td>
</tr>
</tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
  finally{
	  
	  if (resultSet != null) {
	        try {
	        	resultSet.close();
	        } catch (Exception e) { /* ignored */}
	    }
	    if (statement != null) {
	        try {
	        	statement.close();
	        } catch (Exception e) { /* ignored */}
	    }
	    if (connection != null) {
	        try {
	        	connection.close();
	        } catch (Exception e) { /* ignored */}
	    }
	  
  }      
        
%>  

 </tbody>
           
  </table>
 
 
  </div>  
   <center>
     <button onclick="generatePDF()">Download as PDF</button>
    <div id="invoice">
      <h1></h1>
    </div>
   <a href="index.html">
 <button class="btn btn-md dt-button"> Back</button></a></center>



