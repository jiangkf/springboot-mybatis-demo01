package cn.sierac.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by root on 16-11-22.
 * //refer -->    @AfterReturning("execution(* cn..*Service.*(..))")
 */
@Aspect
@Component
public class Aop {

    private static final Logger log = LoggerFactory.getLogger(Aop.class);
//    @Autowired
//    private UserMapper userMapper;

    /**
     * register without repeat username
     */
//    @Before(value = "execution(* cn.dceast.service.UserService.getUserByUsername(..))")
//    public void withoutRepeatUsernameForRegister(JoinPoint joinPoint) {
//        log.debug("enter aop .....");
////        userMapper.getUserByUsername(joinPoint.getArgs()[0].toString());
//        throw new RuntimeException("**********");
//        //TODO
//        /*if(null != user) {
//            throw new exception();
//        }*/
//    }
}