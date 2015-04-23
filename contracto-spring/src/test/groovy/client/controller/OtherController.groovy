package client.controller

import client.model.OtherData
import org.springframework.web.bind.annotation.RequestMapping

class OtherController {

    @RequestMapping("/other/data")
    OtherData getOtherData() {
        null
    }
}
