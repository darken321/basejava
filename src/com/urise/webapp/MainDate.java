package com.urise.webapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class MainDate {
    public static void main(String[] args) {
        Date date = new Date();
        long now = System.currentTimeMillis();
        System.out.println(date);
        System.out.println(System.currentTimeMillis() - now);
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        System.out.println(ld);
        System.out.println(lt);
        System.out.println(LocalDateTime.of(ld,lt));
        System.out.println(LocalDateTime.now().getDayOfMonth());
    }
}
