package awebo.dateformat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {

    @Test
    void testFormatDate_validDate() {
        String formattedDate = DateFormatter.formatDate("10/2/2025 1530");
        assertEquals("10th of February 2025, 3PM", formattedDate);
    }

    @Test
    void testFormatDate_validDateWithSingleDigitDay() {
        String formattedDate = DateFormatter.formatDate("1/1/2025 0900");
        assertEquals("1st of January 2025, 9AM", formattedDate);
    }

    @Test
    void testFormatDate_validDateWithDifferentSuffix() {
        String formattedDate = DateFormatter.formatDate("3/5/2024 1800");
        assertEquals("3rd of May 2024, 6PM", formattedDate);
    }

    @Test
    void testFormatDate_edgeCaseForSuffixes() {
        assertEquals("2nd of June 2023, 10AM", DateFormatter.formatDate("2/6/2023 1000"));
        assertEquals("11th of July 2022, 11PM", DateFormatter.formatDate("11/7/2022 2300"));
        assertEquals("21st of August 2021, 4AM", DateFormatter.formatDate("21/8/2021 0400"));
    }

    @Test
    void testFormatDate_invalidDateFormat() {
        assertEquals("Error: Invalid date format. Please enter in d/M/yyyy HHmm format.",
                DateFormatter.formatDate("2025-02-10 1530"));
    }

    @Test
    void testFormatDate_nonNumericDate() {
        assertEquals("Error: Invalid date format. Please enter in d/M/yyyy HHmm format.",
                DateFormatter.formatDate("ten/feb/2025 1530"));
    }

    @Test
    void testFormatDate_emptyInput() {
        assertEquals("Error: Invalid date format. Please enter in d/M/yyyy HHmm format.",
                DateFormatter.formatDate(""));
    }

    @Test
    void testFormatDate_nullInput() {
        assertThrows(NullPointerException.class, () -> DateFormatter.formatDate(null));
    }
}

