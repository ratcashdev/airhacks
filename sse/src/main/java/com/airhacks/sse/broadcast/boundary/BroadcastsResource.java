
package com.airhacks.sse.broadcast.boundary;

import java.time.LocalTime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author airhacks.com
 */
@Singleton
@Path("beats")
public class BroadcastsResource {

    private SseBroadcaster broadcaster;
    private Sse sse;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void register(@Context Sse sse, @Context SseEventSink eventSink) {
        this.sse = sse;
        if (broadcaster == null) {
            this.broadcaster = sse.newBroadcaster();
        }
        this.broadcaster.register(eventSink);
    }

    @Schedule(second = "*/2", minute = "*", hour = "*")
    public void beat() {
        System.out.println(".");
        if (this.sse == null) {
            return;
        }
        OutboundSseEvent outbound = this.sse.newEventBuilder().
                id("" + System.currentTimeMillis()).
                comment("featureTest").
                name("time event").
                data("time: " + LocalTime.now().toString()).
                build();

        this.broadcaster.broadcast(outbound);
    }

}
