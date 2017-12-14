package com.airhacks.ping.boundary;

import javax.validation.constraints.Size;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
public class PingResource {

    /*
     * curl -i -XPOST -d'd' http://localhost:8080/beanvalidation/resources/ping
     * ...bad request
     */
    @POST
    public String ping(@Size(min = 2, max = 5) String input) {
        return input;
    }

}
