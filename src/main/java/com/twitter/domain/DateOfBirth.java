package com.twitter.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class DateOfBirth {

    @Column(name = "the_day")
    private Integer day;

    @Column(name = "the_month")
    private Integer month;

    @Column(name = "the_year")
    private Integer year;

    public DateOfBirth(){

    }

    public DateOfBirth(Integer day, Integer month, Integer year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateOfBirth that = (DateOfBirth) o;
        return day.equals(that.day) && month.equals(that.month) && year.equals(that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
