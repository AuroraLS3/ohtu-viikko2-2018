package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("opiskelijanumero " + studentNr + "\n");

        for (Submission submission : subs) {
            System.out.println(submission);
        }

        long exercises = Arrays.stream(subs).map(Submission::getExercises).mapToLong(Collection::size).sum();
        long hours = Arrays.stream(subs).mapToLong(Submission::getHours).sum();

        System.out.println("\nyhteensä: " + exercises + " tehtävää " + hours + " tuntia");

    }
}
