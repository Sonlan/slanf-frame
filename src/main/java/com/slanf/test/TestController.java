package com.slanf.test;

import com.slanf.annotation.ConstValue.RequestMethod;
import com.slanf.annotation.Controller;
import com.slanf.annotation.Inject;
import com.slanf.annotation.RequestURI;
import com.slanf.beans.View;

/**
 * Created by Song on 2016/11/3.
 */
@Controller
public class TestController {
    @Inject
    private TestService service;
    @RequestURI(URI = "/test",METHOD = RequestMethod.GET)
    public View index(){
        View view = new View("/index.jsp");
        String test = service.test();
        return view;
    }
}
