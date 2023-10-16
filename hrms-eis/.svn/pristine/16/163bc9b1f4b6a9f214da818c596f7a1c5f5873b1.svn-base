package com.tcs.sgv.eis.util;

import java.util.List;
import java.util.ResourceBundle;

import com.tcs.sgv.acl.valueobject.AclRoleMst;

public class PayrollCommonUtilities {

	public int getLoggedInUserInfo(List roleList)
	{
		int loggedInUserNormal=0;
		AclRoleMst aclRoleMst = new AclRoleMst();
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		
		long normalUserRoleId = Long.parseLong(resourceBundle.getString("normalUserRoleId"));
		
		
		long roleId=0;
		
		for(int roleCount=0;roleCount<roleList.size();roleCount++)
		{
			aclRoleMst= (AclRoleMst)roleList.get(roleCount);
			
			roleId = aclRoleMst.getRoleId();
			if(normalUserRoleId==roleId)
			{
				loggedInUserNormal= 1;
			}
		}
		
		
		return loggedInUserNormal;
	}
	
	
}
