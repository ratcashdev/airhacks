
package com.airhacks.sse.events.boundary;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("events")
public class EventsSource {


    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void fetch(@Context Sse sse, @Context SseEventSink client) {
        for (int i = 0; i < 10; i++) {
            client.send(sse.newEvent("hey", "duke" + System.nanoTime()));
        }
    }

}
