package com.nodeflux.course;

/**
 * Created by cmmuk_000 on 12/10/2016.
 */

public class Course {

    private String Name;
    private String University;
    private String Country;

    public Course() {
    }

    public Course(String country, String name, String university) {
        Country = country;
        Name = name;
        University = university;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }
}
