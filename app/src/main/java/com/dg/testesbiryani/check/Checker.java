package com.dg.testesbiryani.check;

import com.dg.testesbiryani.ProLogger;
import com.dg.testesbiryani.model.User;
import com.dg.testesbiryani.retrofit.ResponseData;

public class Checker {

    User user = new com.dg.testesbiryani.model.User(1,"s", "aha");

    public void checkMe() {
        ProLogger logger = new ProLogger();
        logger.naam("ds");
        user.getName();

        ResponseData data = new ResponseData("d","D","W#");
        String id = data.id;

        ResponseData.companionInResponseData();
    }
}
