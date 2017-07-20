package cn.sierac.api;

import cn.sierac.common.result.Result;
import cn.sierac.entity.Person;
import cn.sierac.service.DemoService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhming on 2017/7/19.
 */
@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    DemoService demoService;

    public static ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public Object demo() throws Exception {
        return new ResponseEntity<>(new Result(HttpStatus.OK.value(), demoService.demo(), HttpStatus.OK), HttpStatus.OK);
    }


}
