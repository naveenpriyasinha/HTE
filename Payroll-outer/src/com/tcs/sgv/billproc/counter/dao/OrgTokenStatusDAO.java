/**
 * 
 */
package com.tcs.sgv.billproc.counter.dao;

/**
 * OrgTokenStatusDAO
 * This interface contains methods for setting different status of token, i.e., U(used),A(available),L(lost)
 * It also validates the usage of particular token. 
 * This interface and its methods are implemented in OrgTokenStatusDAOImpl.
 * 
 * Date of Creation : 11th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 * 
 */
public interface OrgTokenStatusDAO 
{
	public boolean isValidateToken(Long lLngTokenNo, String lStrLocCode);
	public void updateTokenStatus(Long lLngTokenNo, String lStrLocCode, Long lLngBillNo,Long lLngUserId, Long lLngPostId);
	public void updateTokenLost(Long lLngTokenNo, String lStrLocCode,Long lLngUserId, Long lLngPostId);
	public void updateTokenRedeem(Long lLngTokenNo, String lStrLocCode,Long lLngUserId, Long lLngPostId);
	public Long getTokenId(Long lLngTokenNo, String lStrLocCode);
}
