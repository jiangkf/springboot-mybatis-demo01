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
public interface FruitMapper {

    @Select("select * from fruit")
    public List<Fruit> getAll();

    @Select("select * from fruit where id =#{id}")
    public Fruit get(Integer id);

    //查询某个商店的水果
    @Select("select f.id , f.type, f.price , f.sid from fruit f, shop p where f.sid = p.id and sid =#{sid}")
    public List<Fruit> getByShop(Integer sid);

    @Insert("insert into fruit(type,price,sid) values (#{type},#{price},#{sid})")
    public void insertFruit(Fruit fruit);

    @Update("update fruit set  type=#{type} , price=#{price} , sid = #{sid}  where id=#{id}")
    public void updateFruit(Fruit fruit);

    @Delete("delete from fruit where id =#{id}")
    public void delete(int id);


}
