package com.ge.dt.digitaltwin.service;

import java.util.List;

import com.ge.dt.digitaltwin.domain.ModalityCust;

public interface IModalityCust extends GenericDao<ModalityCust, ModalityCust, Long> {

	List<ModalityCust> getModality(String modality, String customerName);

}
