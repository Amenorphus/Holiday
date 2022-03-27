package com.blue.holiday.dto;

import java.time.LocalDate;

public class Holiday {
	private LocalDate date;
	private String name1;
	private String name2;

	public LocalDate getDate() {
		return date;
	}

	public Holiday setDate(LocalDate date) {
		this.date = date;
		return this;
	}

	public String getName1() {
		return name1;
	}

	public Holiday setName1(String name1) {
		this.name1 = name1;
		return this;
	}

	public String getName2() {
		return name2;
	}

	public Holiday setName2(String name2) {
		this.name2 = name2;
		return this;
	}

}
