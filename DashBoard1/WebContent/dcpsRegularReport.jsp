
<%@page import="java.math.BigDecimal" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.util.* " %>

<%
     String driverName = "com.ibm.db2.jcc.DB2Driver";
     /* String connectionUrl = "jdbc:db2://124.153.117.34:55000/"; */
     /* connectionUrl = "jdbc:db2://10.10.10.3:55000/";
     dbName = "DMER"; */
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
<title>MahaSevaarth DCPS Regular Data Status</title>
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
    
    
    
    
$('#example').append('<caption style="caption-side: top">Customised Sevaarth DCPS Regular Status Report</caption>');
	
    $('#example').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf'
        ],
		columnDefs: [
    { className: 'text-right', targets: [0,1,2] },
    { className: 'text-center', targets: [3] },
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
td {
    text-align: left !important;
}
th {
    text-align: center !important;
}
    </style>




</head>
  </div>
  <a href=""><img src="images/mahasevaarth.png" id="image" name="image" crossorigin="anonymous" style="display:none;" ></a>
	<!-- Added Brijoy  -->
	    <div class="container body-contents">
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



<h2 align="center"><font><strong>HTESevaarth DCPS Regular Data Status Report</strong></font></h2>
	<img src="/images/report.png" id="image" name="image" crossorigin="anonymous" style="display:none;" >
<div class="table-main">

<!-- <button id="excelbtn" class="chrome dt-button buttons-pdf buttons-html5">EXCEL </button> -->
        <!-- Table goes here -->
        <table class="table table-bordered" id="example">
            <thead>
              <tr>
              <th>Sr No</th>
                <th>JD Office name(DDO CODE)</th>
                <th>File status</th>  
                <th>Total Amount</th>
              </tr>
            </thead>
            <tbody >

 
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();

String sql ="SELECT ddo.OFF_NAME||'('||ddo.DDO_CODE||')' as DDO_OFF,  sum(decimal(bh.BH_EMP_AMOUNT,20,2))+sum(decimal(bh.BH_EMPLR_AMOUNT,20,2)) as TOTAL_AMT,  case when bh.file_status = 0 then cast('File not Validated' as varchar(20)) when bh.file_status = 1 then cast('File is validated' as varchar(20))   when bh.file_status = 2 then cast('File is rejected' as varchar(20)) when bh.file_status = 5 then cast('Contribution file send' as varchar(25)) when bh.file_status =11 then cast('File send and get Challan' as varchar(25)) when bh.file_status =12 then cast('Bill Locked' as varchar(25)) end as File_status  FROM ifms.NSDL_BH_DTLS bh inner join ifms.MST_DCPS_DDO_OFFICE as ddo on ddo.DDO_CODE=bh.DDO_CODE where bh.STATUS <> -1 and   bh.file_status in  (5,11,12) and ddo.DDO_OFFICE='Yes' and bh.IS_LEGACY_DATA='N' group by  ddo.OFF_NAME||'('||ddo.DDO_CODE||')',FILE_STATUS";

BigDecimal totAmt=new BigDecimal("0");
BigDecimal tempAmt=null;
resultSet = statement.executeQuery(sql);
int srno=1;
while(resultSet.next()){
%>
<tr>
<td align="left"><%=srno++%></td>
<td align="left"><%=resultSet.getString("DDO_OFF") %></td>
<td align="left"><%=resultSet.getString("File_status") %></td>
<% tempAmt =new BigDecimal(resultSet.getString("TOTAL_AMT")); %>
<td align="left"> <%=tempAmt %></td>
</tr>
<% 

totAmt=totAmt.add(tempAmt);
}
%>
<tr><td colspan="3"><b>Total Amount</b></td>
<td><b><%=totAmt %></b></td> </tr> 
<% 
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
    <!--  <button onclick="generatePDF()">Download as PDF</button> -->
    <div id="invoice">
      <h1></h1>
    </div>
   <a href="index.html">
 <button class="btn btn-md dt-button"> Back</button></a></center>



