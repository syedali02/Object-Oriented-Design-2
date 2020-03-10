import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Problem
{
    public static void main(String[] args)
    {
        Reversing rev= new Reversing("Output");
        try {
           rev.readandwrite("test.txt","test.txt");
            //rev.readandwrite("alice29.txt","alice29.txt");
            Instant start = Instant.now();
            //rev.readandwrite("largefile.dat","largefile.dat");
            Instant stop= Instant.now();
            long timeElapsed= Duration.between(start,stop).toMillis();
            System.out.println("Time taken : "+timeElapsed +" Milli secs");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
