package com.example.cinema.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

//    HANDLE DATE TIME

    public static LocalDate parseAnyStringToLocalDate(String dateString) {
        dateString=dateString.trim();
        List<String> patterns = Arrays.asList(
                "yyyy-MM-dd",
                "dd/MM/yyyy",
                "MM/dd/yyyy",
                "yyyy/MM/dd",
                "dd-MM-yyyy",
                "MM-dd-yyyy",
                "yyyy-MM-dd HH:mm:ss",
                "dd/MM/yyyy HH:mm:ss",
                "MM/dd/yyyy HH:mm:ss",
                "yyyy/MM/dd HH:mm:ss",
                "dd-MM-yyyy HH:mm:ss",
                "MM-dd-yyyy HH:mm:ss"
        );

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Ignore and try the next pattern
            }
        }

        throw new IllegalArgumentException("Date |"+dateString+"|string could not be parsed with any of the provided patterns.");
    }

    public static LocalDateTime parseAnyStringToLocalDateTime(String dateTimeString) {
        dateTimeString=dateTimeString.trim();
        List<String> patterns = Arrays.asList(
                "yyyy-MM-dd",
                "dd/MM/yyyy",
                "MM/dd/yyyy",
                "yyyy/MM/dd",
                "dd-MM-yyyy",
                "MM-dd-yyyy",
                "yyyy-MM-dd HH:mm:ss",
                "dd/MM/yyyy HH:mm:ss",
                "MM/dd/yyyy HH:mm:ss",
                "yyyy/MM/dd HH:mm:ss",
                "dd-MM-yyyy HH:mm:ss",
                "MM-dd-yyyy HH:mm:ss",
                "yyyy-MM-dd'T'HH:mm:ss", // ISO 8601 format
                "yyyy-MM-dd'T'HH:mm", // ISO 8601 format
                "yyyy-MM-dd'T'HH", // ISO 8601 format
                "yyyy-MM-dd'T'HH:mm:ss.SSS", // ISO 8601 format with milliseconds
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ" // ISO 8601 format with milliseconds and timezone
        );

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                if(pattern.length()==new String("yyyy-MM-dd").length()){
                    pattern+=" HH:mm:ss";
                    return LocalDateTime.parse(dateTimeString + " 00:00:00", DateTimeFormatter.ofPattern(pattern));
                }
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
            }
        }

        throw new IllegalArgumentException("DateTime string could not be parsed with any of the provided patterns.");
    }
    public static LocalTime convertToTime(String timeString) {
        String pattern = "HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalTime DL;
        try{
           DL= LocalTime.parse(timeString, formatter);
        }catch (Exception e){
            DL= LocalTime.parse(timeString+":00", formatter);
        }
        return DL;
    }
    public static LocalTime addMinuteHour(LocalTime time, int minutes ){
        long totalSeconds = (long) (minutes * 60L);
        LocalTime finalTime = time.plus(totalSeconds, ChronoUnit.SECONDS);
        return finalTime;
    }



    public static int differenceInYears(LocalDate startDate, LocalDate endDate) {
        // Calcul de la différence en années
        Period period = Period.between(startDate, endDate);

        // Récupération de la différence en années
        int years = period.getYears();

        return years;
    }


//    HANDLE VALUES
    public static int stringToInt(String number){
        number=number.trim();
        return Integer.valueOf(number);
    }
    public static double stringToDouble(String number){
        number=number.trim();
        return Double.valueOf(number);
    }
    public static float stringToFloat(String number){
        number=number.trim();
        return Float.valueOf(number);
    }
    public static Long stringToLong(String number){
        number=number.trim();
        return Long.valueOf(number);
    }
    public static boolean checkIfNegative(Object number){
        return true;
    }


// CAST SOMETHING
    public static <T> T castToObject(Object obj, Class<T> clazz) {
        if (clazz.isInstance(obj)) {
            return clazz.cast(obj);
        } else {
            throw new IllegalArgumentException("Object is not an instance of the specified class");
        }
    }
    public static LocalDate addDaysToLocalDate(LocalDate date,double nbjours){
        long daytoadd=(long) nbjours;
        return date.plusDays(daytoadd);
    }
    public static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
    private static final Pattern DURATION_PATTERN = Pattern.compile(
            "(\\d+) years? (\\d+) mons? (\\d+) days? (\\d+) hours? (\\d+) mins? ([\\d\\.]+) secs?");
    public static Duration parseDuration(String durationStr) {
        Matcher matcher = DURATION_PATTERN.matcher(durationStr);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid duration format: " + durationStr);
        }

        long hours = Long.parseLong(matcher.group(4));
        long minutes = Long.parseLong(matcher.group(5));
        double seconds = Double.parseDouble(matcher.group(6));

        long totalSeconds = hours * 3600 + minutes * 60 + (long) seconds;
        long nanos = (long) ((seconds - (long) seconds) * 1_000_000_000);

        return Duration.ofSeconds(totalSeconds, nanos);
    }

    public static void main(String[] args) throws ParseException {
        String test = "00-02-2002";
        int p=2;
        System.out.println(Utils.parseAnyStringToLocalDate(test));
        checkIfNegative(p);
    }

}
