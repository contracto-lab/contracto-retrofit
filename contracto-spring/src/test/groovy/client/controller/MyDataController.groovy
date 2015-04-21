package client.controller

import client.model.MyData
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
class MyDataController {

    @RequestMapping("/my/data")
    public MyData myData() {
        return null
    }

}
