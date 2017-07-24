package cn.sierac.mapper;

import cn.sierac.entity.Fruit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Jack on 2017/7/21.
 */
public interface FruitMapperDao {

    public List<Fruit> getAll(); //获取所有水果

    public Fruit get(Integer id);//根据id获取水果

    public List<Fruit> getByShop(Integer sid);//获取莫水果店的水果

    public void insert(Fruit fruit);//新增

    public void update(Fruit fruit);//修改

    public void delete(int id);//删除




}
