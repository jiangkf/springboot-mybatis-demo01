package cn.sierac.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhming on 2017/7/19.
 */
@Service
public class DemoService {


    @Transactional(readOnly = true)
    public Object demo() {
        return null;
    }



}
