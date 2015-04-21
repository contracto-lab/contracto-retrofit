package client.controller

import client.model.MyData
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/my/data")
class SecondMyDataController {

    public MyData myData() {
        return null
    }

}
