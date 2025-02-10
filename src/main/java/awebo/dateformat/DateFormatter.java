package awebo.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * The {@code DateFormatter} class provides a utility method to format date strings
 * from the "d/M/yyyy HHmm" format to a more human-readable format with day suffixes.
 *
 * <p>Example:
 * <pre>{@code
 * String formattedDate = DateFormatter.formatDate("5/10/2023 1530");
 * System.out.println(formattedDate); // Output: "5th of October 2023, 3PM"
 * }</pre>
 *
 * <p>This class is abstract to prevent instantiation since it only contains static methods.
 */
public abstract class DateFormatter {

    /**
     * Formats an input date string from the "d/M/yyyy HHmm" format to
     * "d' of 'MMMM yyyy, ha" format with the appropriate day suffix (e.g., "st", "nd", "rd", "th").
     *
     * @param inputDate The date string in "d/M/yyyy HHmm" format.
     * @return A formatted date string in "d' of 'MMMM yyyy, ha" format with day suffix,
     *         or an error message if the input format is invalid.
     */
    public static String formatDate(String inputDate) {
        try {
            // Input format: day/month/year time
            SimpleDateFormat inputFormat = new SimpleDateFormat("d/M/yyyy HHmm");
            Date date = inputFormat.parse(inputDate);

            SimpleDateFormat dayFormat = new SimpleDateFormat("d");
            int day = Integer.parseInt(dayFormat.format(date)); // Extract day to determine suffix
            String daySuffix = getDaySuffix(day);

            SimpleDateFormat outputFormat = new SimpleDateFormat("d' of 'MMMM yyyy, ha");
            String formattedDate = outputFormat.format(date);

            return formattedDate.replaceFirst("\\d+", day + daySuffix); // Include suffix for the day
        } catch (ParseException e) {
            return "Error: Invalid date format. Please enter in d/M/yyyy HHmm format.";
        }
    }

    private static String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th"; // Special case for 11th, 12th, 13th
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
