package client.controller

import client.model.OtherData
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping

@CompileStatic
class OtherController {

    @RequestMapping("/other/data")
    OtherData getOtherData() {
        null
    }
}
