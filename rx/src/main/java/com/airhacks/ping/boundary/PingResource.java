package com.airhacks.ping.boundary;

import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("ping")
public class PingResource {

    @Inject
    ExternalPing ping;

    @GET
    public void ping(@Suspended AsyncResponse response) {
        response.setTimeout(1, TimeUnit.SECONDS);
        ping.fetchPing().thenAccept(response::resume);

    }

}
