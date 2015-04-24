package client.controller

import client.model.MyData
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@CompileStatic
class PostController {

    @RequestMapping(value = "/my/data", method = RequestMethod.POST)
    MyData post(@RequestBody MyData data){
        null
    }
}
