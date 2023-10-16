package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.List;



import com.tcs.sgv.eis.valueobject.CMPRecord;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CMPRecordConverter implements Converter {

	

	public boolean canConvert(Class type) {

		return type==CMPRecord.class;
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) {
		// TODO Auto-generated method stub
		CMPRecord record = (CMPRecord) value;
        writer.startNode("bulkUploadFlag");
        writer.setValue(record.getBulkUploadFlag());
        writer.endNode();
        
        writer.startNode("billId");
        writer.setValue(record.getBillId());
        writer.endNode();
        
        writer.startNode("treasuryCode");
        writer.setValue(record.getTreasuryCode());
        writer.endNode();
        
        writer.startNode("ddoCode");
        writer.setValue(record.getDdoCode());
        writer.endNode();
        
        writer.startNode("benefName");
        writer.setValue(record.getBenefName());
        writer.endNode();
        
        writer.startNode("accNo");
        writer.setValue(record.getAccNo());
        writer.endNode();
        
        writer.startNode("ifscCode");
        writer.setValue(record.getIfscCode());
        writer.endNode();
        
        writer.startNode("micrCode");
        writer.setValue(record.getMicrCode());
        writer.endNode();
        
        writer.startNode("accType");
        writer.setValue(record.getAccType());
        writer.endNode();
        
        writer.startNode("amount");
        writer.setValue(record.getAmount());
        writer.endNode();
        
        writer.startNode("paymentRefNo");
        writer.setValue(record.getPaymentRefNo());
        writer.endNode();
        
        writer.startNode("payBydate");
        writer.setValue(record.getPayBydate());
        writer.endNode();
        
        writer.startNode("schemeCode");
        writer.setValue(record.getSchemeCode());
        writer.endNode();
        
        writer.startNode("ddoBillNo");
        writer.setValue(record.getDdoBillNo());
        writer.endNode();
        
        writer.startNode("authNo");
        writer.setValue(record.getAuthNo());
        writer.endNode();
        
        writer.startNode("toBilNo");
        writer.setValue(record.getToBilNo());
        writer.endNode();
        
        writer.startNode("billDate");
        writer.setValue(record.getBillDate());
        writer.endNode();
        
        writer.startNode("narration");
        writer.setValue(record.getNarration());
        writer.endNode();
        
        writer.startNode("noOfPayees");
        writer.setValue(record.getNoOfPayees());
        writer.endNode();
        
        writer.startNode("billNetAmt");
        writer.setValue(record.getBillNetAmt());
        writer.endNode();
        
        writer.startNode("emailId");
        writer.setValue(record.getEmailId());
        writer.endNode();
        
        writer.startNode("cellNo");
        writer.setValue(record.getCellNo());
        writer.endNode();
       
        
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {
		// TODO Auto-generated method stub
		

		List<CMPRecord> list=new ArrayList<CMPRecord>();
		for (; reader.hasMoreChildren(); reader.moveUp()) {
			reader.moveDown();
				
			
				
			}
			
				
			
				
			
		

		return list;
		
		

	
	}

}
