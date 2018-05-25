package com.ge.dt.digitaltwin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ge.dt.digitaltwin.domain.ModalityService;

@Service
public class ModalityServiceImpl extends GenericDaoImpl<ModalityService, ModalityService, Long>
		implements IModalityService {

	@Override
	public List<ModalityService> getModality(String modality, String age, String region, String sysCovLevWarrantyc) {
		List<Predicate<ModalityService>> allpredicates = getAllRequiredPredicate(modality, age, region, sysCovLevWarrantyc);
		System.out.println(allpredicates.size());
		Predicate<ModalityService> requiredFilter = allpredicates.get(0);
		for (int i = 1; i < allpredicates.size(); i++) {
			requiredFilter = requiredFilter.and(allpredicates.get(i));
		}
		return findAll().stream().filter(requiredFilter).collect(Collectors.toList());
	}

	public List<Predicate<ModalityService>> getAllRequiredPredicate(String modality, String age, String region,
			String sysCovLevWarrantyc) {
		List<Predicate<ModalityService>> allPredicates = new ArrayList<>();
		if (StringUtils.hasText(age)) {
			Predicate<ModalityService> ageP = (Predicate<ModalityService>) u -> u.getAge().equalsIgnoreCase(age);
			allPredicates.add(ageP);
		}
		if (StringUtils.hasText(modality)) {
			Predicate<ModalityService> modalityP = (Predicate<ModalityService>) u -> u.getModality()
					.equalsIgnoreCase(modality);
			allPredicates.add(modalityP);
		}
		if (StringUtils.hasText(region)) {
			Predicate<ModalityService> regionP = (Predicate<ModalityService>) u -> u.getRegion()
					.equalsIgnoreCase(region);
			allPredicates.add(regionP);
		}
		if (StringUtils.hasText(sysCovLevWarrantyc)) {
			Predicate<ModalityService> sysCovLevWarrantycP = (Predicate<ModalityService>) u -> u
					.getSystemCoverageLevelWarrantyc().equalsIgnoreCase(sysCovLevWarrantyc);
			allPredicates.add(sysCovLevWarrantycP);
		}
		return allPredicates;
	}
}
