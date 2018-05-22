package com.ge.dt.digitaltwin.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.dt.digitaltwin.domain.ModalityCust;
import com.ge.dt.digitaltwin.domain.ModalityService;
import com.ge.dt.digitaltwin.service.IModalityCust;
import com.ge.dt.digitaltwin.service.IModalityService;

@RestController
public class ModalityAPI {
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
	@GetMapping("/modality")
	public List<ModalityCust> getAllModality(@RequestParam("modality") String modality,
			@RequestParam("customerName") String customerName, @RequestParam("age") String age,
			@RequestParam("region") String region) {

		return modalityCust.findAll();
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
