package com.workshop.springdemo.model;

public class Course {
    private final String code;
    private final String title;
    private final int seatLimit;
    private int enrolledStudents;

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

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public int getAvailableSeats() {
        return seatLimit - enrolledStudents;
    }

    public boolean hasAvailableSeat() {
        return enrolledStudents < seatLimit;
    }

    public void enrollOneStudent() {
        if (!hasAvailableSeat()) {
            throw new IllegalStateException("No seats are available in " + code);
        }

        enrolledStudents++;
    }
}