//Necessary declarations :emptypes (fixed,contract,new recruit)
//,commission id variables as well constant variables commissionSixId ,commissionFiveId)


function validateSIS1979(sis1979)
{
        if(sis1979!=0&&sis1979!=5)
		{
			alert('SIS 1979 amount can be 0 or 5 Rs. only');
		 	return false
		}
		else
	return true;
}


function validateVPF(VPF,basic,DA,DP,zeroVPFFlg,commissionId,CPFFlag)
{
	if(CPFFlag)
	{
	    if(commissionId==commissionSixId)
	    {	
		    if(zeroVPFFlg&&VPF>0)
		    {
				alert('VPF must be 0 for contractual,new or fixed pay employees');
			 	return false
		    } 
		    else if(VPF<(basic+DA)/10)
			{
				alert('VPF can not be less than CPF');
			 	return false
			}
			else if(VPF>(basic+DA))
			{
				alert('VPF can not be more than BASIC + DA');
			 	return false
			}
			else return true;
	    }
	    else if(commissionId==commissionFiveId)
	    {	
		    if(zeroVPFFlg&&VPF>0)
		    {
		    	alert('VPF must be 0 for contractual,new or fixed pay employees');
			 	return false
		    } 	
		    else if(VPF<(basic+DP+DA)/10)
			{
				alert('VPF can not be less than CPF');
			 	return false
			}
			else if(VPF>(basic+DA+DP))
			{
				alert('VPF can not be more than BASIC + DP + DA');
			 	return false
			}
			else return true;
		 }
	}
    else
	{
	    if(commissionId==commissionSixId)
	    {	
		    if(zeroVPFFlg&&VPF>0)
		    {
				alert('VPF must be 0 for contractual,new or fixed pay employees');
			 	return false
		    } 
		    else if(VPF%10!=0)
		    {
				alert('VPF must be multiple of 10');
			 	return false
		    } 
		    else if(VPF<basic/10)
			{
				alert('VPF can not be less than PF');
			 	return false
			}
			else if(VPF>(basic+DA))
			{
				alert('VPF can not be more than BASIC + DA');
			 	return false
			}
			else return true;
	    }
	    else if(commissionId==commissionFiveId)
	    {	
		    if(zeroVPFFlg&&VPF>0)
		    {
		    	alert('VPF must be 0 for contractual,new or fixed pay employees');
			 	return false
		    } 	
		    else if(VPF<(basic+DP)/10)
			{
				alert('VPF can not be less than PF');
			 	return false
			}
			else if(VPF>(basic+DA+DP))
			{
				alert('VPF can not be more than BASIC + DP + DA');
			 	return false
			}
			else return true;
		 }
	}
} 