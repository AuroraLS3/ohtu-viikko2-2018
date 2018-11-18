package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static final Gson MAPPER = new Gson();
    ;

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        Submission[] subs = getSubmissions(studentNr);
        Map<String, Course> courses = Arrays.stream(getCourses()).collect(Collectors.toMap(Course::getName, Function.identity()));

        Map<String, List<Submission>> submissions = new HashMap<>();
        for (Submission submission : subs) {
            String course = submission.getCourse();
            List<Submission> submissionsForCourse = submissions.getOrDefault(course, new ArrayList<>());
            submissionsForCourse.add(submission);
            submissions.put(course, submissionsForCourse);
        }

        System.out.println("opiskelijanumero " + studentNr + "\n");

        for (Map.Entry<String, List<Submission>> entry : submissions.entrySet()) {
            String courseName = entry.getKey();
            Course course = courses.get(courseName);
            System.out.println(course.getFullName() + " " + course.getTerm() + " " + course.getYear() + "\n");

            for (Submission submission : entry.getValue()) {
                int week = submission.getWeek();
                String exList = submission.getExercises().toString();
                System.out.println("viikko " + week + ":\n  " +
                        "tehtyjä tehtäviä " + submission.getExercises().size() + "/" + course.getExercises().get(week)
                        + " aikaa kului " + submission.getHours() + " tehdyt tehtävät: " + exList.substring(1, exList.length() - 1));
            }

            long exercises = entry.getValue().stream().map(Submission::getExercises).mapToLong(Collection::size).sum();
            long exercisesMax = course.getExercises().stream().mapToLong(i -> i).sum();
            long hours = entry.getValue().stream().mapToLong(Submission::getHours).sum();
            System.out.println("\nyhteensä: " + exercises + "/" + exercisesMax + " tehtävää " + hours + " tuntia\n");
        }
    }

    private static Course[] getCourses() throws IOException {
        String url = "https://studies.cs.helsinki.fi/courses/courseinfo/";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        return MAPPER.fromJson(bodyText, Course[].class);
    }

    private static Submission[] getSubmissions(String studentNr) throws IOException {
        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        return MAPPER.fromJson(bodyText, Submission[].class);
    }
}
