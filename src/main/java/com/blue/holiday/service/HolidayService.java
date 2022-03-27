package com.blue.holiday.service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.blue.holiday.dto.ApiResponseDay;
import com.blue.holiday.dto.Holiday;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HolidayService {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public Holiday getNextHoliday(String code1, String code2, LocalDate date) {

		List<ApiResponseDay> dayList1 = getDayList(code1, date.getYear());
		List<ApiResponseDay> dayList2 = getDayList(code2, date.getYear());
		
		Holiday next = null;
		next = checkNext(dayList1, dayList2, date);
		
		if (next == null) {
			dayList1 = getDayList(code1, date.getYear()+1);
			dayList2 = getDayList(code2, date.getYear()+1);
			next = checkNext(dayList1, dayList2, date);
		}
		
		return next;
	}
	
	private Holiday checkNext(List<ApiResponseDay> dayList1, List<ApiResponseDay> dayList2, LocalDate date) {
		Holiday next = null;
		boolean found = false;
		for (ApiResponseDay day1 : dayList1) {
			for (ApiResponseDay day2 : dayList2) {
				if (day1.getDate().isEqual(day2.getDate()) && day1.getDate().isAfter(date)) {
					next = new Holiday()
							.setDate(day1.getDate())
							.setName1(day1.getLocalName())
							.setName2(day2.getLocalName());
					found = true;
					break;
				}
			}
			if (found) {
				break;
			}
		}
		
		return next;
	}

	private List<ApiResponseDay> getDayList(String countryCode, int year) {
		List<ApiResponseDay> dayList = new ArrayList<>();
		try {
			String url = "https://date.nager.at/api/v3/publicholidays/" + String.valueOf(year) + "/" + countryCode;
			URL jsonUrl = new URL(url);
			dayList = objectMapper.readValue(jsonUrl, new TypeReference<List<ApiResponseDay>>() {
			});
		} catch (JsonMappingException e) {
			System.err.println(e.getMessage());
		} catch (JsonProcessingException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return dayList;
	}
}
