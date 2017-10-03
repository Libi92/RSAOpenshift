package com.rsaapp.service;

import com.rsaapp.security.RSA;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Created by 10945 on 29-Sep-17.
 */

@Path("/netpay")
@RequestScoped
public class NetpayService {

    @GET
    @Produces("text/plain")
    public String getName(@Context UriInfo info) {
//        String text = info.getQueryParameters().getFirst("text");
        String text = "IXyDW4tDsRi5Zi+Vu8QNnr48dQFV6JvyCISbQ6/T/vdaiDrYAKY9kTh2Pq2WcVdBOMwYktYiadKIPdsGx3/yNDSPq5Mzn9LqWOLk4qlk13onL3Ev1QuoAKB+Gq0Y45koIz7GYUw6oClkj70PlPNE4xGt4YFQM71PX93ZBqS3pvA=";
        try {
            String dec = RSA.decryptWithPH(text);
            return dec;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error";
    }

    @POST
    @Path("/decrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDecrypt(String text) {
        try {
            String dec = RSA.decryptWithPH(text);
            return dec;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error";
    }
}
