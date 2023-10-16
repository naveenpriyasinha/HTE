package com.tcs.sgv.nps.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.CmnAttdocMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.nps.valueobject.FrmFormS1Dtls;
import com.tcs.sgv.nps.valueobject.FrmFormS1NpsDtls;

public interface FormS1UpdateDAO extends GenericDao{

	List getEmpListForFrmS1Edit(String strDDOCode, String flag, String txtSearch, String isDeputation);

	List getRelationList();

	void insertRecordToS1(FrmFormS1Dtls ffs, String doj, String nominee1DOB, String nominee2DOB, String nominee3DOB,String strDDOCode, Long lLngPkIdForFormS1);
	
	int insertRecordToNps(FrmFormS1NpsDtls ffs, String doj, String nominee1DOB, String nominee2DOB, String nominee3DOB,String strDDOCode, Long lLngPkIdForFormS1);

	Long checkFormS1(String strSevarthId);

	List getEmpDesigList(String strDDOCode);

	List getEmpForFrmS1Edit(String strDDOCode, String sevaarthId);
	//List getEmpForFrmS1Edit(String strDDOCode, String flag, String txtSearch, String isDeputation);

	//List getEmpNomineeList(Long DCPS_EMP_ID);

	List getEmpNomineeList(String sevaarthId);
	
	List getEmpListForFileGenerate(String strDDOCode,String txtSearch, String flag);
	List getTitleList();
	List getBankList();
	List getClassList();
	List getStateList();
	List getPayScaleList();
	List getDtoDetails(String strDDOCode,String EmpDDOCode);
	List getEmpForFileGenerate(String SevaarthIds, String flag);

	List getEmpForFileGenerateText(String SevaarthIds, String flag);
	List getAckNoBatchForValidateText(String SevaarthIds, String flag);
	List checkBachId(String Ymd);
	List getBachId(String Ymd);
	
	List getDataFromFrmS1(String SevaarthIds,String ColumnStr);
	
	void insertBachSeq(String Year,String Mon, String Day) ;
	void updateBachSeq(String Ymd) ;
	
	void updateFrmFormS1DTLS1(String sevaarthId,String ackNo,String BatchId);
	void updateNsdlStatusCode(String AckNo, String nsdlStatusCode);
	void updateNsdlFileVeryStatus(String AckNo, String fileVerifySatatus);
	
	List getDataForGetPran(String nsdlStatusCode,String ColumnStr) ;

	List getAkNoId(String dtoRegNo);	
	void insertAckNoSeq(String dtoRegNo) ;
	void updateAckNoSeq(String dtoRegNo) ;
	/*for File Sequence */
	List checkFileSeqId(String Ymd);
	List getFileSeqId(String Ymd);
	void updateFileSeq(String Ymd) ;
	void insertFileSeq(String Year,String Mon, String Day) ;
	
	List getEmpDcpsIdByAcknow(String dcspId);
	
	void updateFrmDtlsPranNo(String dcpsId,String PranNo,String AckNo) ;
	void updateMstDcpsEmpPranNo(String dcpsId,String PranNo,int pranActive) ;
	
	CmnAttdocMst findByAttdocId(Long srNo);

	List getDtoDetailsCSRF(String strDDOCode, String EmpDDOCode);

	void deleteUpdateCsrfEmpdata(String strSevarthId, String ddoCode);

	int deletedUpdateCsrfEmpdatamove(String strSevarthId, String ddoCode,int Status);
	
	void deletedCsrfEmpdatamove(String strSevarthId, String ddoCode,int Status);

	List getDDOOfcAddDesgName(String locId);
}
