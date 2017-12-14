package com.airhacks.ping.boundary;

import javax.json.Json;
import javax.json.JsonObject;
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
    public JsonObject ping(@ValidJson(key = "language", expectedValue = "java") JsonObject input) {
        return Json.createObjectBuilder(input).
                add("got", "it").
                build();
    }

}
