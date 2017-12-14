/*
 */
package com.airhacks.sse;

import java.io.IOException;
import static java.util.concurrent.TimeUnit.SECONDS;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class SSEClientIT {

    private Client client;
    private WebTarget tut;

    @Before
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:8080/sse/resources/beats");
    }

    @Test
    public void init() throws IOException {
        SseEventSource sse = SseEventSource.target(tut)
                .reconnectingEvery(1, SECONDS)
                .build();
        sse.register(this::onMessage);
        sse.open();
        System.in.read();
        System.out.println("...press any key to esc");
    }

    void onMessage(InboundSseEvent event) {
        String id = event.getId();
        String name = event.getName();
        String payload = event.readData();
        String comment = event.getComment();
        System.out.print(" id = " + id);
        System.out.print(" name = " + name);
        System.out.print("payload = " + payload);
        System.out.println(" comment = " + comment);
    }

}
