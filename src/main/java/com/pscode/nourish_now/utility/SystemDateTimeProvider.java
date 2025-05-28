package com.pscode.nourish_now.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SystemDateTimeProvider {

	public static String returnDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String time = localDateTime.format(myFormatObj);
		return time;
	}

}
