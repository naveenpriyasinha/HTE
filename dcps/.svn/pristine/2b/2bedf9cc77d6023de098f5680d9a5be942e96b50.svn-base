function chkKey(e){	
	if(e.keyCode=='13')
		return false;
	else	
		return true;
}
function checkAvailability()
{
alert('hi');
if(document.getElementById('zerovpfMonths').checked!=true)
{
	var newVpf=document.frmBF.vpfAmount.value;
	if(newVpf=='')
	return ;
	else
	{
	var initialBasic = "${intialBasic}";
	//var da = "${daAmount}";	
	//var daAmount = parseFloat(da);
	//var currentBasic = parseFloat(initialBasic);
	//var dpAmount = currentBasic / 2;
	//var maxAmount = currentBasic + dpAmount +daAmount;	
	var maxPfAmount = "${maxPfAmount}";
	var maxAmount = parseFloat(maxPfAmount);
	var pfValue = "${pfAmount}";
	var pfVal = parseFloat(pfValue);	
	if(newVpf < pfVal){
		alert("VPF Amount must be greater then the PF "+pfVal);
		document.frmBF.vpfAmount.value='';
		document.frmBF.vpfAmount.focus();
		return false;
	}
	else if(newVpf > maxAmount){
			 alert("VPF Amount must be Less then "+maxAmount);
			 document.frmBF.vpfAmount.value='';
		     document.frmBF.vpfAmount.focus();
		     return false;
		}
		else{		    
			return true;
		}
		}
		}
		else
		return false;
}

function dateDifferent1()
{
		var CURDATE = new Date();
		var t1=new Date();
		var CURYear = CURDATE.getYear();
		var CURMonth = CURDATE.getMonth();
		var CURDate = CURDATE.getDate();
		var CURTime = CURDATE.getTime();
		

t1="${orgEmpMst.empSrvcExp}";

//var t2=document.frmBF.endDate.value;
var _Diff=0;
   //Total time for one day
        var one_day=1000*60*60*24; 
//Here we need to split the inputed dates to convert them into standard format

        var x=t1.split("-");
        var y=x[2].split(" ")


      //  var date1=new Date(y[0],(x[1]-1),x[0]);
    /*    var date1=new Date(x[0],(x[1]-1),y[0]);
  		alert(date1);
        var date2=new Date(CURYear,CURMonth,CURDate);
        alert(date2); */
        
        var date1=new Date(x[0],(x[1]-1),y[0]);
 
        var date2=new Date(CURYear,CURMonth,CURDate);

        var month1=x[1]-1;
        
        var month2=CURMonth;
        
        //Calculate difference between the two dates, and convert to days
               
        _Diff=Math.ceil((date1.getTime()-date2.getTime())/(one_day)); 
        if(_Diff<=180 && _Diff>90 && _Diff!=0)
        {
        if(document.getElementById('zerovpfMonths').checked!=true)
        document.getElementById('vpfcheck').style.display='';
		else
		{
		alert('User Stops His VPF so you Cannto Change VPF Value'); 
		document.getElementById('vpfAmount').readOnly=true;
	
		if("${empAllRec}"=='false')
		{
        	 	var url="./hrms.htm?actionFlag=getVPFView";
         		document.frmBF.action=url;
				document.frmBF.submit(); 
		}
		}
        return false;
        }
        else if(_Diff<90 && _Diff!=0)
        {
            document.frmBF.zerovpfMonths.checked=true;
            document.getElementById('vpfAmount').readOnly=true;
          
        alert("Your Retirement Date is within 3 Months so You can not enter VPF");
		if("${empAllRec}"=='false')
		{
           		var url="./hrms.htm?actionFlag=getVPFView";
       

			document.frmBF.action=url;
			document.frmBF.submit(); 
			}
			return false;
        }

return true;
}

function submit()
{
     
     var uri = "./hrms.htm?actionFlag=";
  if("${empAllRec}"=='true')
    var url = uri + "insertUpdateVPFDtls&edit=Y&vpfId=${actionList.payVpfId}&empAllRec=true";
  else
   var url = uri + "insertUpdateVPFDtls&edit=Y&vpfId=${actionList.payVpfId}&empAllRec=false";


 document.frmBF.action = url;
 document.frmBF.submit();
 
 

}