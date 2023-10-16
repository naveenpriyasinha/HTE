<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="http://code.jquery.com/jquery-3.7.0.min.js" type="text/javascript"></script>

<script language="JavaScript">
/* var url = "ifms.htm?viewName=checkForAccountModal";
width=false;
height=false;

var modalDiv,
dialogPrefix = window.showModalDialog ? 'dialog' : '',
unit = 'px',
maximized = width === true || height === true,
w = width || 325,
h = height || 150,
border = 5,
taskbar = 40, // windows taskbar
header = 20,
x,
y;

if (maximized) {
x = 0;
y = 0;
w = screen.width;
h = screen.height;
} else {
x = window.screenX + (screen.width/2)-(w/2) - (border * 2);
y = window.screenY + (screen.height/2)-(h/2) - taskbar - border;
}

//alert("location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,center=yes,height="+h+unit+",width="+w+unit+",top="+y+unit+",left="+x+unit+"");
// IE

 if (window.showModalDialog) {
	 var returnvalue = window.showModalDialog(url, "","dialogHeight:150px;dialogWidth:325px;scroll:No;resizable:no;status:no;resizable:no");
	 if(returnvalue=="Yes")
	 {
	 	self.location.href="ifms.htm?viewName=DCPSEmpSearch&elementId=700162";
	 }
	 else if(returnvalue== "No")
	 {
	 	self.location.href="ifms.htm?actionFlag=loadDCPSForm&User=ZPDDOAsst&elementId=700022";
	 } 
 // Other browsers
} else {
	 var returnvalue = window.open(url, '', "location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,center=yes,height="+h + unit+",width="+w + unit+",top="+y+ unit+",left="+x+unit+"")
	 if(returnvalue)
	 {
	 	self.location.href="ifms.htm?viewName=DCPSEmpSearch&elementId=700162";
	 }
	 else if(returnvalue)
	 {
	 	self.location.href="ifms.htm?actionFlag=loadDCPSForm&User=ZPDDOAsst&elementId=700022";
	 }  	  
}   
 
//if(confirm("Does the Employee have a 21-digit DCPS ID/HTESevaarth ID ?\n Click No in case you want to enter data for new employee."))
 */
 

/*  
 var url = "ifms.htm?viewName=checkForAccountModal";

 var returnvalue = window.showModalDialog(url, "","dialogHeight:150px;dialogWidth:325px;scroll:No;resizable:no;status:no;resizable:no");


 if(returnvalue == "Yes")
 {
 	self.location.href="ifms.htm?viewName=DCPSEmpSearch&elementId=700162";
 }
 else if(returnvalue == "No")
 {
 	self.location.href="ifms.htm?actionFlag=loadDCPSForm&User=ZPDDOAsst&elementId=700022";
 }   */
 
//Open the modal dialog
/*  $('#confirmationModal').modal();

 // Handle the button clicks
 $('#yesBtn').on('click', function() {
   self.location.href = "ifms.htm?viewName=DCPSEmpSearch&elementId=700162";
 });

 $('#noBtn').on('click', function() {
   self.location.href = "ifms.htm?actionFlag=loadDCPSForm&User=ZPDDOAsst&elementId=700022";
 }); */
 
 var confirmationMessage = "Does the Employee have a 21-digit DCPS ID/HTESevaarth ID ? \n Click Cancel in case you want to enter data for new employee.";

 if (window.confirm(confirmationMessage)) {
   self.location.href = "ifms.htm?viewName=DCPSEmpSearch&elementId=700162";
 } else {
   self.location.href = "ifms.htm?actionFlag=loadDCPSForm&User=ZPDDOAsst&elementId=700022";
 }

</script> 


<!-- 
<div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog" aria-labelledby="confirmationModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confirmationModalLabel">Confirmation</h5>
      </div>
      <div class="modal-body">
        Are you sure?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="yesBtn">Yes</button>
        <button type="button" class="btn btn-secondary" id="noBtn" data-dismiss="modal">No</button>
      </div>
    </div>
  </div>
</div> -->

