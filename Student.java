import java.util.HashMap;
import java.util.Map;

public class Student {

    private String name;
    private Map<String, Double> subjectMarks;

    public Student(String name) {
        this.name = name;
        this.subjectMarks = new HashMap<>();
    }

    public void addSubjectMark(String subject, double marks) {
        subjectMarks.put(subject, marks);
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getSubjectMarks() {
        return subjectMarks;
    }

    public double getAverage() {
        if (subjectMarks.isEmpty()) return 0;

        double sum = 0;
        for (double mark : subjectMarks.values()) {
            sum += mark;
        }
        return sum / subjectMarks.size();
    }

    public String getHighestSubject() {
        String highestSubject = "";
        double highestMarks = Double.MIN_VALUE;

        for (Map.Entry<String, Double> entry : subjectMarks.entrySet()) {
            if (entry.getValue() > highestMarks) {
                highestMarks = entry.getValue();
                highestSubject = entry.getKey();
            }
        }
        return highestSubject + " (" + highestMarks + ")";
    }

    public String getLowestSubject() {
        String lowestSubject = "";
        double lowestMarks = Double.MAX_VALUE;

        for (Map.Entry<String, Double> entry : subjectMarks.entrySet()) {
            if (entry.getValue() < lowestMarks) {
                lowestMarks = entry.getValue();
                lowestSubject = entry.getKey();
            }
        }
        return lowestSubject + " (" + lowestMarks + ")";
    }
}