package com.rsaapp.service;

import com.rsaapp.security.KeyUtility;
import com.rsaapp.security.RSA;
import com.rsaapp.security.RSAPaddingTypes;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.security.PrivateKey;

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
        String text = "jrjEO4AZhhQBRyr50p6vl3HSQHP4PjgCV8aM8LxQLruTZhhZNOC+fndb9Tc5HaP8o89ZenyVdpo4FQtRYuZ7LPgQAOyjPW0otcRBuexfBfxhFkxus2Shzp5D8c48J7Fa7fLKHzDJ/y/WaUhwsBMdNxhVeGgiiqtR2T4U8MrbMjw=";
        return getDecrypted(text, RSAPaddingTypes.OAEPSHA1);


    }

    @POST
    @Path("/decrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDecrypt(@FormParam("text") String text, @FormParam("type") RSAPaddingTypes type) {
        return getDecrypted(text, type);

    }

    private String getDecrypted(String text, RSAPaddingTypes paddingType) {
        try {
            PrivateKey pk = KeyUtility.getInstance().getPrivate();
            return RSA.decryptWithPH(text, paddingType, pk);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
