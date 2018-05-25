package com.ge.dt.digitaltwin.service;

import java.util.List;

import com.ge.dt.digitaltwin.domain.ModalityService;

public interface IModalityService extends GenericDao<ModalityService, ModalityService, Long> {

	List<ModalityService> getModality(String modality, String age, String region, String sysCovLevWarrantyc);

}

