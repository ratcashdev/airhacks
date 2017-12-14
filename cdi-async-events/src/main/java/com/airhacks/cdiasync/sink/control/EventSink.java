
package com.airhacks.cdiasync.sink.control;

import java.time.LocalTime;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.enterprise.event.TransactionPhase;

/**
 *
 * @author airhacks.com
 */
public class EventSink {

    public void onFireNotification(@ObservesAsync String event) {
        System.out.println("Received: " + event + " Blocking for 2 secs " + LocalTime.now());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
    }

    public void onOrder(@Observes(during = TransactionPhase.AFTER_SUCCESS) String order) {

    }


}
