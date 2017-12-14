package com.airhacks.ping.boundary;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
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

    @Resource
    ManagedExecutorService mes;

    @GET
    public void ping(@Suspended AsyncResponse response) {
        response.setTimeout(1, TimeUnit.SECONDS);
        //not reasonable -> just showcase
        ping.fetchPing().thenAcceptAsync(response::resume, mes);

    }

}
