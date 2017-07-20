package cn.sierac.api;

import cn.sierac.common.result.Result;
import cn.sierac.entity.Person;
import cn.sierac.service.PersonService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017/7/20.
 */
@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    private Logger logger = Logger.getLogger(PersonController.class);

    public static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PersonService personService;


    //简单的测试
    //    @PostMapping(value="/person",headers = { "Accept=application/json" })
//    public Object demo(@RequestBody String JsonParam) throws Exception {
//        JavaType javaType = getCollectionType(ArrayList.class,Person.class);
//        List<Person> list =  objectMapper.readValue(JsonParam,javaType);
//        Person person = new Person();
//        for (Person p : list){
//            person.setId(p.getId());
//            person.setName(p.getName());
//        }
//        return new ResponseEntity<>(new Result(HttpStatus.OK.value(), personService.personDemo(person), HttpStatus.OK), HttpStatus.OK);
//    }


    //获取person所有信息
    @GetMapping
    public Object get(Person person){
//        JavaType javaType = getCollectionType(ArrayList.class,Person.class);
        List<Person> list = new ArrayList<>();
        try
        {
             list = personService.getAll(person);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Result(HttpStatus.OK.value(), list, HttpStatus.OK), HttpStatus.OK);
    }

    //根据id获取相关信息
    @RequestMapping(value = "/view/{id}")
    public Object view(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Person person =  personService.getById(id);
        return new ResponseEntity<>(new Result(HttpStatus.OK.value(), person, HttpStatus.OK), HttpStatus.OK);
    }


//    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
//        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
//    }
}
