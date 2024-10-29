package ex_241023_ch7.homework2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Jih0316_DateUtil {
	public static String getCurrentDateTime() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return now.format(datetimeFormatter);
	}
}
