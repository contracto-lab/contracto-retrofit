package client.controller

import client.model.MyData
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/my/data")
@CompileStatic
class SecondMyDataController {

    @RequestMapping
    public MyData myData() {
        null
    }

}
