package com.mycompany.transactionphaseeventlistenertest.boundary;

import com.mycompany.transactionphaseeventlistenertest.control.EventHandler;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("sample")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SampleResource {

    @Inject
    private EventHandler eventHandler;

    @GET
    public Response test() {
        eventHandler.fireEvent();
        return Response.ok().build();
    }

    @GET
    @Path("exception")
    public Response testWithException() {
        try {
            eventHandler.fireEventWithException();
        } catch(Throwable ex) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

}
