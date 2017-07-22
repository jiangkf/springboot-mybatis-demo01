package cn.sierac.service;

import cn.sierac.entity.Fruit;
import cn.sierac.mapper.FruitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jack on 2017/7/21.
 */
@Service
public class FruitService {

    @Autowired
    private FruitMapper fruitMapper;

    public List<Fruit> getAll(){
        return  fruitMapper.getAll();
    }

    public List<Fruit> getByShop(Integer sid){return fruitMapper.getByShop(sid);}

    public void insertFruit(Fruit fruit){fruitMapper.insertFruit(fruit);}

    public void updateFruit(Fruit fruit){fruitMapper.updateFruit(fruit);}

    public void deleteFruit(int id){fruitMapper.delete(id);}



}
