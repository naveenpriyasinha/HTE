package com.tcs.sgv.exprcpt.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.common.valueobject.TrnReceiptDetails;

public interface ReceiptDAO {
	
	public List getReceipts(long userId, List receiptList);
	public void accReceiptsFromChqBranch(Map objectArg);
	public List getChallansForDist(long userId,List receiptList);
	public List<TrnReceiptDetails> getUndistributedChallan(long userId,List receiptList);
	public List getReceiptsForDetPost(long userId,List receiptList);
	public void accReceiptsForDetPost(Map objectArg);
	public List getChallanListForDet(long userId,List receiptList,Integer posted);
}
