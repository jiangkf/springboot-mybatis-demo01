package cn.sierac;

import cn.sierac.entity.Fruit;
import cn.sierac.mapper.FruitMapperDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SieracApiApplicationTests {

	@Autowired
	private FruitMapper fruitMapper;

	@Autowired
	private FruitMapperDao fruitMapperDao;

	@Test
	public void contextLoads() {
//	    List<Fruit> fruitList =  fruitMapperDao.getAll();
        Fruit fruit = fruitMapperDao.get(1);
	}


	@Test
	public void getAll(){
		List<Fruit> fruitList = fruitMapperDao.getAll();
	}

	@Test
	public void getByShop(){
        List<Fruit> fruitList =  fruitMapperDao.getByShop(1);
	}

	@Test
	public void insert(){
		Fruit fruit = new Fruit();
		fruit.setPrice("1000");
		fruit.setType("奇异果");
		fruit.setSid(2);
		fruitMapperDao.insert(fruit);
	}

	@Test
	public void update(){
		Fruit fruit = new Fruit();
		fruit.setId(4);
	    fruit.setPrice("10");
	    fruitMapperDao.update(fruit);
	}

	@Test
	public void delete(){
		fruitMapperDao.delete(4);
	}



}
