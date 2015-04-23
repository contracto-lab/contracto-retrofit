package client.controller

import client.model.MyData
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

class WrongMethodController {

    @RequestMapping(value = "/my/data", method = RequestMethod.POST)
    public MyData myData() {
        return null
    }

}
