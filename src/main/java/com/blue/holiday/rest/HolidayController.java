package com.blue.holiday.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blue.holiday.dto.Holiday;
import com.blue.holiday.service.HolidayService;

@RestController
public class HolidayController {

	@Autowired
	private HolidayService holidayService;

	@GetMapping("/holiday")
	public Holiday nextHoliday(@RequestParam("code1") String code1, @RequestParam("code2") String code2,
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

		return holidayService.getNextHoliday(code1, code2, date);
	}
}
