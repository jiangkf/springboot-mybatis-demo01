package cn.sierac.service;

import cn.sierac.entity.Person;
import cn.sierac.mapper.PersonMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jack on 2017/7/20.
 */
@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    /**
     * 查询所有
     * @param person
     * @return
     */
    public List<Person> getAll(Person person){
        if(person.getPage()!=null && person.getRows()!=null){
            PageHelper.startPage(person.getPage(),person.getRows());

        }
        return personMapper.selectAll();
    }

    /**
     * 根据id查询人员信息
     */
    public Person getById(Integer id) {
        return personMapper.selectByPrimaryKey(id);
    }

}
