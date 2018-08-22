/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.api;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.dt.digitaltwin.domain.Questions;
import com.ge.dt.digitaltwin.domain.ResponseObj;
import com.ge.dt.digitaltwin.service.ICustomerIB;
import com.ge.dt.digitaltwin.service.IModalityCust;
import com.ge.dt.digitaltwin.service.IModalityService;
import com.ge.dt.digitaltwin.service.IRegionContract;
import com.ge.dt.digitaltwin.service.IRegionExpenditure;
import com.ge.dt.digitaltwin.service.ISystemComponents;
import com.ge.dt.digitaltwin.service.IUserProfile;

@RestController
@CrossOrigin("*")
@SuppressWarnings("unused")
public class ModalityAPI<T> {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IModalityCust modalityCust;

	@Autowired
	private IModalityService modalityService;

	@Autowired
	private ISystemComponents systemComponents;

	@Autowired
	private IRegionExpenditure regionExpenditure;

	@Autowired
	private IRegionContract regionContract;

	@Autowired
	private ICustomerIB customerIB;

	@Autowired
	private IUserProfile userProfile;

//	@Autowired
//	@Qualifier("jdbcpostgres")
//	private JdbcTemplate jdbcTemplate;
//
//	@Autowired
//	@Qualifier("jdbchive")
//	private JdbcTemplate jdbcTemplate2;

	/**
	 * API will fetch Modality data from 5 different Tables
	 * 
	 * @param id
	 * @param name
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/modality")
	public ResponseObj<T> getAllModality(
			@RequestParam(value = "fetchAll", required = false, defaultValue = "true") boolean fetchAll,
			@RequestParam(value = "modality", required = false, defaultValue = "") String modality,
			@RequestParam(value = "customerName", required = false, defaultValue = "") String customerName,
			@RequestParam(value = "age", required = false, defaultValue = "") Double age,
			@RequestParam(value = "region", required = false, defaultValue = "") String region,
			@RequestParam(value = "sysCovLevWarrantyc", required = false, defaultValue = "") String sysCovLevWarrantyc,
			@RequestParam(value = "product", required = false, defaultValue = "") String product,
			@RequestParam(value = "productDesc", required = false, defaultValue = "") String prodDesc,
			@RequestParam(value = "contractName", required = false, defaultValue = "") String contractName,
			@RequestParam(value = "srProcessYear", required = false, defaultValue = "0") Integer srProcessYear,
			@RequestParam(value = "srProcessMom", required = false, defaultValue = "0") Integer srProcessMom,
			@RequestParam(value = "periodFrom", required = false, defaultValue = "") String periodFrom,
			@RequestParam(value = "periodTo", required = false, defaultValue = "") String periodTo,
			@RequestParam(value = "componentsDesc", required = false, defaultValue = "") String componentsDesc,
			@RequestParam(value = "systemId", required = false, defaultValue = "") String systemId,
			@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", required = false, defaultValue = "16000") Integer limit,
			@RequestParam(value = "question", required = false, defaultValue = "0") int que) {

//		try {
//			System.out.println("Conn ::"+jdbcTemplate.getDataSource().getConnection());
//			System.out.println("Conn 2::"+jdbcTemplate2.getDataSource().getConnection());
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		ResponseObj<T> respone = new ResponseObj<T>();

		if (que == 1) {
			respone.setData((List<T>) modalityCust.getModality(modality, customerName, offset, limit, fetchAll));
			respone.setCount(modalityCust.getFilteredCount(modality, customerName, offset, limit, fetchAll));
		} else if (que == 2 || que == 3 || que == 4 || que == 5) {
			respone.setData((List<T>) modalityService.getModality(modality, age, region, sysCovLevWarrantyc, offset,
					limit, fetchAll));
			respone.setCount(modalityService.getFilteredCount(modality, age, region, sysCovLevWarrantyc, offset, limit,
					fetchAll));
		} else if (que == 6) {
			respone.setData(
					(List<T>) regionContract.getRegionContract(region, product, contractName, offset, limit, fetchAll));
			respone.setCount(regionContract.getFilteredCount(region, product, contractName, offset, limit, fetchAll));
		} else if (que == 7) {
			respone.setData((List<T>) customerIB.getCustomerIB(customerName, 0, offset, limit, fetchAll));
			respone.setCount(customerIB.getFilteredCount(customerName, 0, offset, limit, fetchAll));
		} else if (que == 8) {
			respone.setData(
					(List<T>) systemComponents.getSystemComponents(systemId, componentsDesc, offset, limit, fetchAll));
			respone.setCount(systemComponents.getFilteredCount(systemId, componentsDesc, offset, limit, fetchAll));
		} else if (que == 9 || que == 10 || que == 11 || que == 12) {
			respone.setData((List<T>) regionExpenditure.getRegionExpenditure(region, prodDesc, srProcessYear,
					srProcessMom, periodTo, periodFrom, offset, limit, fetchAll));
			respone.setCount(regionExpenditure.getFilteredCount(region, prodDesc, srProcessYear, srProcessMom, periodTo,
					periodFrom, offset, limit, fetchAll));
			return respone;
		}
		return respone;
	}

	/*
	 * 
	 * Map<String, Long> counts = list.stream().collect(Collectors.groupingBy(e
	 * -> e, Collectors.counting()));
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/modality2")
	public ResponseObj<T> getAllModality2(@RequestParam Map<String, String> reqParam) {

		reqParam.forEach((k, v) -> {
			System.out.println((k + ":" + v));
		});
		if (reqParam.get("offset") == null) {
			reqParam.put("offset", "0");
		}
		if (reqParam.get("limit") == null) {
			reqParam.put("limit", "5");
		}
		reqParam.entrySet().removeIf(i -> i.getValue().equals(""));
		Integer que = Integer.parseInt(reqParam.get("question"));
		reqParam.remove("question");
		if (que == 1) {
			return (ResponseObj<T>) modalityCust.getModality(reqParam);
		} else if (que == 2 || que == 3 || que == 4 || que == 5) {
			return (ResponseObj<T>) modalityService.getModality(reqParam);
		} else if (que == 6) {
			return (ResponseObj<T>) regionContract.getRegionContract(reqParam);
		} else if (que == 7) {
			return (ResponseObj<T>) customerIB.getCustomerIB(reqParam);
		} else if (que == 8) {
			return (ResponseObj<T>) systemComponents.getSystemComponents(reqParam);
		} else if (que == 9 || que == 10 || que == 11 || que == 12) {
			return (ResponseObj<T>) regionContract.getRegionContractFS(reqParam);
		}
		return null;
	}

	@GetMapping("/questions")
	public ResponseObj<T> getAllQuestion(
			@RequestParam(value = "ssoId", required = false, defaultValue = "") Long ssoId) {
		return (ResponseObj<T>) userProfile.getAllQuestions(ssoId);
	}

	@GetMapping("/question")
	public ResponseObj<T> getRecentQuestion(
			@RequestParam(value = "ssoId", required = false, defaultValue = "") Long ssoId) {
		return (ResponseObj<T>) userProfile.getRecentQuestion(ssoId);
	}

	@PostMapping("/questions")
	public ResponseObj<T> addQuestion(@RequestParam(value = "ssoId", required = false, defaultValue = "") Long ssoId,
			@RequestBody Questions question) {
		return (ResponseObj<T>) userProfile.addQuestion(question);
	}

	/**
	 * if (StringUtils.hasText(fetchAll)) { if
	 * (fetchAll.equalsIgnoreCase("customer")) { return (List<T>)
	 * modalityCust.findAll(); } else { return (List<T>)
	 * modalityService.findAll(); } } else {
	 * 
	 * if (StringUtils.hasText(customerName)) { return (List<T>)
	 * modalityCust.getModality(modality, customerName); } else if
	 * (StringUtils.hasText(customerName) || StringUtils.hasText(modality) ||
	 * (age != null) || StringUtils.hasText(sysCovLevWarrantyc) ||
	 * StringUtils.hasText(region)) { return (List<T>)
	 * modalityService.getModality(modality, age, region, sysCovLevWarrantyc); }
	 * return null; }
	 */

}
