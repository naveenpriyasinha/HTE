package com.tcs.sgv.pension.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * Life Certificate Service Interface.
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */
public interface LifeCertificateService 
{
	ResultObject showAuditorPage(Map inputMap);
	ResultObject generateLifeCertificate(Map inputMap);
	ResultObject printCertificate(Map inputMap);
}
