
package com.airhacks.ping.boundary;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class ExternalPing {

    @Resource
    ManagedExecutorService mes;

    private Client client;
    private WebTarget pingTarget;

    @PostConstruct
    public void initClient() {
        this.client = ClientBuilder.newBuilder().
                executorService(mes).
                readTimeout(1, TimeUnit.DAYS).
                connectTimeout(1, TimeUnit.DAYS).build();
        this.pingTarget = this.client.target("http://localhost:8080");
    }

    public CompletionStage<Response> fetchPing() {
        return this.pingTarget.request().rx().get();
    }



}
