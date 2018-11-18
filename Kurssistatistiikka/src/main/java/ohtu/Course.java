package ohtu;

import java.util.List;

public class Course {

    private String name;
    private String fullName;
    private String term;
    private int year;

    private List<Integer> exercises;

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTerm() {
        return term;
    }

    public int getYear() {
        return year;
    }

    public List<Integer> getExercises() {
        return exercises;
    }
}