function addAdminData()
{
	//alert('addAdminData');
	var totalArray=new Array('purchasesale','assettype','property','other_movable','registration','asset','other_fixed');
	addOrUpdateRecord('addAdminRecords', 'addAdminAssetRecords', totalArray)
}	

function addAdminRecords()
{
			if (xmlHttp.readyState == 4)
		  	{		  		
		  		if(xmlHttp.status == 200)
		  		{	
		  		
		  			
		  			document.getElementById('adminAddTable').style.display="";
		  			
		  		
		  			
		  			if(document.getElementById('purchasesale').value  == "Purchase_asset")
		  			{
		  				var displayFieldArray = new Array('purchasesale','assettype');	
		  					
		  				if(document.getElementById('property').value != '0' && document.getElementById('property').value!= '')
		  				{	//alert(document.getElementById('property').value);
		  					document.getElementById('other_fixed').value = '';
		  					document.getElementById('asset').value = '';
		  					displayFieldArray[2] = 'property' ;
		  				}
		  				if(document.getElementById('other_movable').value != '0' && document.getElementById('other_movable').value!= '')
		  				{	//alert(document.getElementById('other_movable').value);
		  					document.getElementById('other_fixed').value = '';
		  					displayFieldArray[2] = 'other_movable' ;
		  				}
		  				if(document.getElementById('asset').value != '0' && document.getElementById('asset').value!= '')
		  				{	//alert(document.getElementById('asset').value);
		  					document.getElementById('other_movable').value = '';
		  					document.getElementById('property').value = '';
		  					displayFieldArray[2] = 'asset' ;

		  				}
		  				if(document.getElementById('other_fixed').value != '0' && document.getElementById('other_fixed').value!= '')
		  				{	//alert(document.getElementById('other_fixed').value);
		  					document.getElementById('other_movable').value = '';
		  					displayFieldArray[2] = 'other_fixed' ;
		  				}
		  				if(document.getElementById('registration').value != '')
		  				{
		  					displayFieldArray[3] = 'registration' ;
		  				}
		  			}
		  			
		  			addDataInTable('adminAddTable', 'encXML', displayFieldArray, 'editAdminRecord', 'deleteAdminRecord', '');				
   	  				//resetData();   
		  				
					
					
				}
			}
}			
function submitAssetData()
{
 	//alert('submitAssetData');
}
