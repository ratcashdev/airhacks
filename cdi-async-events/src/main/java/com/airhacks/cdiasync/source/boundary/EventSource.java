
package com.airhacks.cdiasync.source.boundary;

import java.time.LocalTime;
import java.util.concurrent.CompletionStage;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.inject.Inject;

@Startup
@Singleton
public class EventSource {

    @Inject
    Event<String> fireAlarm;
    
    @Resource
    ManagedExecutorService threadPool;

    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void send() {
        String event = "fire " + System.currentTimeMillis();
        CompletionStage<String> completion = this.fireAlarm.
                fireAsync(event, NotificationOptions.ofExecutor(threadPool));

        System.out.println("Immediate return: " + LocalTime.now());
        completion.thenAccept(this::eventDelivered);
    }

    void eventDelivered(String event) {
        System.out.println(event + " receipt at: " + LocalTime.now());
    }
}
