package com.workshop.springbootdemo.model;

public class Course {
    private final String code;
    private final String title;
    private final int seatLimit;
    private int enrolledCount;

    public Course(String code, String title, int seatLimit) {
        this.code = code;
        this.title = title;
        this.seatLimit = seatLimit;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getSeatLimit() {
        return seatLimit;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public int getAvailableSeats() {
        return seatLimit - enrolledCount;
    }

    public boolean hasAvailableSeat() {
        return enrolledCount < seatLimit;
    }

    public void enrollOneStudent() {
        if (hasAvailableSeat()) {
            enrolledCount++;
        }
    }
}