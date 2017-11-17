import java.util.Timer;
import java.util.TimerTask;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

/**
 * Created by dmagda on 11/17/17.
 */
public class IgniteMetricsExample {
    public static void main(String args[]) {
        final Ignite ignite = Ignition.start("config/ignite-config.xml");

        final IgniteCache cache = ignite.cache("test-cache");

        new Timer().schedule(new TimerTask() {
            @Override public void run() {
                System.out.println("average put time = " + cache.metrics().getAveragePutTime());
                System.out.println("average get time = " + cache.metrics().getAverageGetTime());
            }
        }, 1000, 2000);

        //Generating dummy load.
        while (true) {
            for (int i = 0; i < 1000; i++) {
                cache.put(i, i);
            }

            for (int i = 0; i < 500; i++) {
                cache.get(i);
            }
        }
    }
}
