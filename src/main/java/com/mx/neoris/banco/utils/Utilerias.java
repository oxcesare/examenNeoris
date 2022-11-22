package com.mx.neoris.banco.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utilerias {

	public static String formatLocaDateTimeSinT(Date dateTime) {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = formatter.format(dateTime);
		return formattedDate;
	}

	public static Date formatLocaDateTime(String dateTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
		Date date = formatter.parse(dateTime);
		return date;
	}
	
	

}
