package cn.sierac.api;

import cn.sierac.entity.Fruit;
import cn.sierac.service.FruitService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017/7/21.
 */
@RestController
@RequestMapping(value = "/fruits")
public class FruitController {
    public static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private FruitService fruitService;

    @RequestMapping("/getall")
    public List<Fruit> getAll(){
       return  fruitService.getAll();
    }

    @PostMapping(value = "/getByShop",headers = {"Accept=application/json"})
    public List<Fruit> getByShop(@RequestBody String JsonParam) throws IOException {
        JavaType javaType = getCollectionType(ArrayList.class,Fruit.class);
        List<Fruit> fruits =  objectMapper.readValue(JsonParam,javaType);
        List<Fruit> fruitList  = new ArrayList<>();
        for(Fruit fruit : fruits){
            fruitList = fruitService.getByShop(fruit.getSid());
        }
        return fruitList;
    }

    @PostMapping(value = "/inorup",headers = {"Accept=application/json"})
    public Object insertOrUpdate(@RequestBody String JsonParam) throws IOException{
        //由于重复代码太多，更新和新增在此。

        JavaType javaType = getCollectionType(ArrayList.class,Fruit.class);
        List<Fruit> fruits =  objectMapper.readValue(JsonParam,javaType);
        Fruit fruit = fruits.get(0);

        List<Fruit> fruitList = fruitService.getAll();
        for(Fruit f:fruitList){
           if(f.getId().equals(fruit.getId()) ){
               //修改
               fruitService.updateFruit(fruit);
           }
           else if(fruit.getId()==null || fruit.getId() < 0){
               //新增
               fruitService.insertFruit(fruit);
               break;
           }
        }
        return fruitService.getAll();
    }

    @RequestMapping(value="/delete",headers = {"Accept=application/json"})
    public Object delete(@RequestBody Fruit fruit){
        fruitService.deleteFruit(fruit.getId());
        return fruitService.getAll();
    }




    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);}

}
