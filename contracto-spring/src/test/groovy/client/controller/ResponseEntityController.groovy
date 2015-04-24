package client.controller

import client.model.MyData
import groovy.transform.CompileStatic
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
class ResponseEntityController {

    @RequestMapping("/my/data")
    public ResponseEntity<MyData> myData() {
        null
    }

}
