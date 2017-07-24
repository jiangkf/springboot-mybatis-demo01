package cn.sierac.service;

import cn.sierac.entity.Fruit;
import cn.sierac.mapper.FruitMapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jack on 2017/7/21.
 */
@Service
public class FruitService {

    @Autowired
    private FruitMapperDao fruitMapperDao;

    public List<Fruit> getAll(){
        return  fruitMapperDao.getAll();
    }
    public Fruit get(Integer id){
        return  fruitMapperDao.get(id);
    }
    public List<Fruit> getByShop(Integer sid){return fruitMapperDao.getByShop(sid);}
    public void insertFruit(Fruit fruit){fruitMapperDao.insert(fruit);}
    public void updateFruit(Fruit fruit){fruitMapperDao.update(fruit);}
    public void deleteFruit(int id){fruitMapperDao.delete(id);}



}
