package cn.sierac.service;

import cn.sierac.entity.Fruit;
import cn.sierac.mapper.FruitMapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jack on 2017/7/21.
 */
@Service
public class FruitService {

    @Autowired
    private FruitMapperDao fruitMapperDao;

    @Transactional(readOnly = true)
    public List<Fruit> getAll(){
        return  fruitMapperDao.getAll();
    }

    @Transactional(readOnly = true)
    public Fruit get(Integer id){
        return  fruitMapperDao.get(id);
    }

    @Transactional(readOnly = false)
    public List<Fruit> getByShop(Integer sid){return fruitMapperDao.getByShop(sid);}

    @Transactional(readOnly = false)
    public void insertFruit(Fruit fruit){fruitMapperDao.insert(fruit);}

    @Transactional(readOnly = false)
    public void updateFruit(Fruit fruit){fruitMapperDao.update(fruit);}

    @Transactional(readOnly = false)
    public void deleteFruit(Integer id){fruitMapperDao.delete(id);}



}
