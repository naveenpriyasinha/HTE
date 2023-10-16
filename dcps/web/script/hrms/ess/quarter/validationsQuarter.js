function ValidateLines()
{
if(document.getElementById('policeline2').value==null || document.getElementById('policeline2').value="")
{
alert("Name of New Police Line is Required");
}
if(document.form1.rdoAddressAddress2[0].checked == true && document.form1.rdoAddressAddress2[1].checked == true)
			{

			}
			else if(document.form1.rdoAddressAddress2[0].checked == false && document.form1.rdoAddressAddress2[1].checked == true)
			{
				//alert('Address Of Asset is Required To Select');

			}
			else if(document.form1.rdoAddressAddress2[0].checked == true && document.form1.rdoAddressAddress2[1].checked == false)
			{

			}
			else if(document.form1.rdoAddressAddress2[2].checked == true)
			{

			}
			else 
			{
				//document.form1.approxprice.value = '';
				alert('Address Of Asset is Required To Select');
			//	document.form1.approxprice.value = '';


			//	document.form1.approxprice.value = "";
				return;
			}
		}
}