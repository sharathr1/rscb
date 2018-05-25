package com.ge.dt.digitaltwin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ge.dt.digitaltwin.domain.ModalityCust;

@Service
public class ModalityCustImpl extends GenericDaoImpl<ModalityCust, ModalityCust, Long> implements IModalityCust {

	@Override
	public List<ModalityCust> getModality(String modality, String customerName) {
		System.out.println(modality + "  :: " + customerName);
		List<Predicate<ModalityCust>> allpredicates = getAllRequiredPredicate(modality, customerName);
		System.out.println(allpredicates.size());
		Predicate<ModalityCust> requiredFilter = allpredicates.get(0);
		for (int i = 1; i < allpredicates.size(); i++) {
			requiredFilter = requiredFilter.and(allpredicates.get(i));
		}
		return findAll().stream().filter(requiredFilter).collect(Collectors.toList());	
	}

	private List<Predicate<ModalityCust>> getAllRequiredPredicate(String modality, String customerName) {

		List<Predicate<ModalityCust>> allPredicates = new ArrayList<>();
		if (StringUtils.hasText(customerName)) {
			Predicate<ModalityCust> customerNameP = (Predicate<ModalityCust>) u -> u.getCustomerName()
					.equalsIgnoreCase(customerName);
			allPredicates.add(customerNameP);
		}
		if (StringUtils.hasText(modality)) {
			Predicate<ModalityCust> modalityP = (Predicate<ModalityCust>) u -> u.getModality()
					.equalsIgnoreCase(modality);
			allPredicates.add(modalityP);
		}
		return allPredicates;
	}

}
