package client.controller

import client.model.MyData
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@RequestMapping
@CompileStatic
class SecWrongMethodController {

    @RequestMapping(value = "/my/data", method = RequestMethod.POST)
    public MyData myData() {
        null
    }

}
