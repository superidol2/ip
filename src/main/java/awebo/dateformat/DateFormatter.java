package awebo.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateFormatter {
    public static String formatDate(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("d/M/yyyy HHmm"); //input format: day/month/year time
            Date date = inputFormat.parse(inputDate);

            SimpleDateFormat dayFormat = new SimpleDateFormat("d");
            int day = Integer.parseInt(dayFormat.format(date)); //extract day to determine suffix
            String daySuffix = getDaySuffix(day);

            SimpleDateFormat outputFormat = new SimpleDateFormat("d' of 'MMMM yyyy, ha");
            String formattedDate = outputFormat.format(date);

            return formattedDate.replaceFirst("\\d+", day + daySuffix); //include suffix for the day
        } catch (ParseException e) {
            return "Error: Invalid date format. Please enter in d/M/yyyy HHmm format.";
        }
    }

    private static String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th"; //special case for 11th, 12th, 13th
        } else {
            switch (day % 10) {
                case 1:
                    return "st";
                case 2:
                    return "nd";
                case 3:
                    return "rd";
                default:
                    return "th";
            }
        }
    }
}
