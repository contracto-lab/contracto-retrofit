package client.controller

import client.model.MyData
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@RequestMapping(value = "/my", method = RequestMethod.GET)
@CompileStatic
class CombinedController {

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public MyData myData() {
        null
    }

}
