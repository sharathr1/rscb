package com.ge.dt.digitaltwin.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.dt.digitaltwin.domain.ModalityService;
import com.ge.dt.digitaltwin.service.IModalityCust;
import com.ge.dt.digitaltwin.service.IModalityService;

@RestController
@CrossOrigin("*")
public class ModalityAPI<T> {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IModalityCust modalityCust;
	@Autowired
	private IModalityService modalityService;

	/**
	 * @Cacheable will skip running the method, whereas @CachePut will actually
	 *            run the method and then put its results in the cache.
	 * @CacheEvict annotation is used to indicate the removal of one or more/all
	 *             values
	 * 
	 *             Group multiple caching annotations with @Caching
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/modality")
	public List<T> getAllModality(@RequestParam(value = "modality", required = false,defaultValue = "") String modality,
			@RequestParam(value = "customerName", required = false ,defaultValue = "") String customerName,
			@RequestParam(value = "age", required = false,defaultValue = "") String age,
			@RequestParam(value = "region", required = false,defaultValue = "") String region,
			@RequestParam(value = "sysCovLevWarrantyc", required = false,defaultValue = "") String sysCovLevWarrantyc,
			@RequestParam(value = "fetchAll", required = false,defaultValue = "") String fetchAll) {
		if (StringUtils.hasText(fetchAll)) {
			if (fetchAll.equalsIgnoreCase("customer")) {
				return (List<T>) modalityCust.findAll();
			} else {
				return (List<T>) modalityService.findAll();
			}
		} else {

			if (StringUtils.hasText(customerName)) {
				return (List<T>) modalityCust.getModality(modality, customerName);
			} else if (StringUtils.hasText(customerName) || StringUtils.hasText(modality) ||StringUtils.hasText(age)
					|| StringUtils.hasText(sysCovLevWarrantyc) || StringUtils.hasText(region)) {
				return (List<T>) modalityService.getModality(modality, age, region, sysCovLevWarrantyc);
			}
			return null;
		}
	}

	@GetMapping("/modality2")
	public List<ModalityService> getAllModality2() {
		LOGGER.debug("This is a debug message");
		LOGGER.info("This is an info message");
		LOGGER.warn("This is a warn message");
		LOGGER.error("This is an error message");
		return modalityService.findAll();
	}
}
