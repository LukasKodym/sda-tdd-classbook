package pl.sda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Student {

    private int studentId;
    private String name;
    private String lastName;
    private Sex sex;
    private Map<SchoolSubject, List<Double>> grades;

    public Student(int studentId, String name, String lastName, Sex sex) {
        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.grades = new HashMap<>();
    }

    public void addGrade(SchoolSubject subject, Double grade) {
        grades.putIfAbsent(subject, new ArrayList<>());
        grades.get(subject).add(grade);

    }

    public List<Double> getGrades(SchoolSubject subject) {
        return grades.get(subject);
    }

    public Double getFinalGrade(SchoolSubject subject) {
        Double sum = 0.0;
        List<Double> subjectGrade = grades.get(subject);
        for (Double grade : subjectGrade) {
            sum += grade;
        }
        return Double.valueOf(Math.round(sum/subjectGrade.size()));
    }
    public Double getAverage() {
        Double sumOfAll = 0.0;
        for (Map.Entry<SchoolSubject, List<Double>> entry : grades.entrySet()) {
            sumOfAll += getFinalGrade(entry.getKey());
        }
        return sumOfAll / grades.size();
    }
    public Double getFinalGradeUsingLamda(SchoolSubject subject) {
        return grades.get(subject).stream().collect(Collectors.averagingDouble(x -> x));
    }
}
