package es.ull.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.time.format.DateTimeFormatter;

public class UllDate {

    public static String HOUR_FORMAT = "HH:mm:ss";
    public static String DAY_FORMAT = "dd/MM/yyyy";
    public static String DAY_HOUR_FORMAT = DAY_FORMAT + " " + HOUR_FORMAT;

    public static boolean isIso(DateTimeFormatter dateTimeFormatter, String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException exception) {
        }
        return false;
    }

    public static boolean isBasicIsoDate(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.BASIC_ISO_DATE, date);
    }

    public static boolean isIsoLocalDate(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_LOCAL_DATE, date);
    }

    public static boolean isIsoOffsetDate(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_OFFSET_DATE, date);
    }

    public static boolean isIsoDate(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_DATE, date);
    }

    public static boolean isIsoLocalTime(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_LOCAL_TIME, date);
    }

    public static boolean isIsoOffsetTime(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_OFFSET_TIME, date);
    }

    public static boolean isIsoTime(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_TIME, date);
    }

    public static boolean isIsoLocalDateTime(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_LOCAL_DATE_TIME, date);
    }

    public static boolean isIsoOffsetDateTime(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_OFFSET_DATE_TIME, date);
    }

    public static boolean isIsoZonedDateTime(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_ZONED_DATE_TIME, date);
    }

    public static boolean isIsoDateTime(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_DATE_TIME, date);
    }

    public static boolean isIsoOrdinalDate(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_ORDINAL_DATE, date);
    }

    public static boolean isIsoWeekDate(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_WEEK_DATE, date);
    }

    public static boolean isIsoInstant(String date) {
        if (date == null) {
            throw new NullPointerException("Date to check is not provided");
        }
        return UllDate.isIso(DateTimeFormatter.ISO_INSTANT, date);
    }

    public static LocalDateTime now() {
        return UllDate.now(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static LocalDateTime now(DateTimeFormatter formatter) {
        LocalDateTime date = LocalDateTime.now();
        String text = date.format(formatter);
        return LocalDateTime.parse(text, formatter);
    }

    public static LocalDate random(LocalDate startInclusive, LocalDate endExclusive) {
        final long startEpochDay = startInclusive.toEpochDay();
        final long endEpochDay = endExclusive.toEpochDay();
        final long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static LocalDate random(LocalDate startInclusive) {
        return UllDate.random(startInclusive, LocalDate.now());
    }

    public static LocalDate random() {
        final long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        final long maxDay = LocalDate.now().toEpochDay();
        final long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static Date sum(Date... dates) {
        if (dates == null) {
            throw new NullPointerException("Dates to sum are not provided");
        }
        if (dates.length == 0) {
            throw new ArrayIndexOutOfBoundsException("Array with dates is empty");
        }
        Date sum = dates[0];
        for (int i = 1; i < dates.length; i++) {
            sum = new Date(sum.getTime() + dates[i].getTime());
        }
        return sum;
    }

    public static Date toDate(String stringDate) throws ParseException {
        return new SimpleDateFormat(DAY_FORMAT).parse(stringDate);
    }

    public static Date toDateTime(String string) throws ParseException {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DAY_HOUR_FORMAT);
        Date date = simpleDateFormat.parse(string);
        return date;
    }
}
