/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * @author 999951/502593533 : Sharath R
 */
package com.ge.dt.digitaltwin.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {
	public void test(String[] args) {
		String inputStart = "01/01/2014";
		String inputStop = "12/28/2014";
		DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate start = LocalDate.parse(inputStart, formatterInput);
		LocalDate stop = LocalDate.parse(inputStop, formatterInput);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH); // Or
																								// Locale.CANADA_FRENCH
																								// etc.
		List<String> outputs = new ArrayList<>();
		YearMonth yearMonth = YearMonth.from(start);
		while (!yearMonth.isAfter(YearMonth.from(stop))) {
			// String output = yearMonth.toString (); // ISO 8601 format.
			String output = yearMonth.format(formatter);
			outputs.add(output);
			// Prepare for next loop.
			yearMonth = yearMonth.plusMonths(1);
		}
		System.out.println("Months from start: " + start + " to stop: " + stop + " is: " + outputs);

		LocalDate localDate = LocalDate.parse("2016-08-16");

		System.out.println(localDate.getMonthValue());
	}

	public int getMonth(String date) {
		return LocalDate.parse(date).getMonthValue();
	}
	public int getYear(String date) {
		return LocalDate.parse(date).getYear();
	}
}
