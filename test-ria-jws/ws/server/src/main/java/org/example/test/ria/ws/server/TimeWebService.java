package org.example.test.ria.ws.server;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class TimeWebService {

	@WebMethod
    public String time() {
        return new Date().toString();
    }
}
