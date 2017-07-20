## spring-boot的微调属性

`example`

```javascript
server:
  port: 9000
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/test?useSSL=false
    username: root
    password: root
    #这里如果不配数据源，spring-boot-start:jdbc自动装配使用默认数据源tomcat-jdbc
    type: com.zaxxer.hikari.HikariDataSource
    #driver可以省略，自动装配可以根据url自动识别
    driver-class-name: com.mysql.jdbc.Driver
    initial-size: 1
    maximum-pool-size: 10
    connection-timeout: 5000
  #开发环境设置false(这样改动前段不需重启应用了)，生产去掉该配置，使用默认的true
  thymeleaf:
        cache: false
  #request  before '/static/**' will control with static context
  mvc:
    static-path-pattern: /static/**
#使用spring-boot自带的logging输出,默认10M切分一次
logging:
  file: temp/logs
  level:
    root: debug
#add custom properties
id: 2
#文件上传的目录
stored: files

# tk.mapper
mapper:
    mappers:
        - cn.dceast.util.mapperUtil.MyMapper
    not-empty: false
    identity: MYSQL

# 分页插件
pagehelper:
    helperDialect: mysql
    reasonable: true
    supportMethodsArguments: true
    params: count=countSql
 ```

## 异常的统一处理

> @RestController

`通过通知注解@RestControllerAdvice(basePackages = "cn.dceast.api")绑定包名封装返回`

`example`

```javascript
@RestControllerAdvice(basePackages = "cn.dceast.api")
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Throwable.class)
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        ResponseEntity c = new ResponseEntity<>(new Result(status.value(), ex.getMessage(), status), status);
        return c;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}

```

> @Controller

`使用springboot默认的异常自动配置，最终会跳转到事先准备好的/**/error.html`

## AOP

> 可根据业务情况在统一@Aspect注解类上做具体拦截

`example`

```javascript
@Aspect
@Component
public class Aop {

    private static final Logger log = LoggerFactory.getLogger(Aop.class);
    @Autowired
    private UserMapper userMapper;

    /**
     * register without repeat username
     */
    @Before(value = "execution(* cn.dceast.service.UserService.getUserByUsername(..))")
    public void withoutRepeatUsernameForRegister(JoinPoint joinPoint) {
        log.debug("enter aop .....");
    }
}
```


## api的json返回

> 依赖Result类，并封装在org.springframework.http.ResponseEntity中统一返回

`example`

```javascript
return new ResponseEntity<>(new Result(status.value(), ex.getMessage(), status), status);
```


## interceptor
`example`

```javascript
@Configuration
public class SessionInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
        // 请求来之前，检查是否登录过
            @Override
            public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
                //可以在application.yml中配置server.session.timeOut时间
                if (null != httpServletRequest.getSession().getAttribute("user")) {
                    return true;
                }
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

            }
        });
    }
```

## war打包

* maven设置`<packaging>war</packaging>`
* extends SpringBootServletInitializer
    * `example`

        ```javascript
        @Override
            protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
                return builder.sources(DemoApplication.class);
            }
        ```

## 事务(异常回滚/只读等)

> 用自动配置的transactionManager,在需要的地方按需配置

`example`

```javascript
@Transactional(readOnly = true)
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }
```


## TaskTimer

`example`

```javascript
@Configuration
@EnableScheduling
public class TaskConfig {
    @Autowired
    ServiceManager serviceManger;

    @Scheduled(cron = "** 2 / **")
    public void scheduler() {
        serviceManger.doSyncServiceStatistics();
    }
}
```

## spring-boot-devtools
* Automatic Restart
* LiveReload
* 自动重启比手动重启更加快
* Remote Debug Tunneling  等等

`idea设置方法`

* settings->Compiler->make project automatically
* 然后 Shift+Ctrl+Alt+/，选择Registry
* 勾选compiler.automake.allow.when.app.running

## spring-boot-configuration-processor

[@ConfigurationProperties vs. @Value](http://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/reference/htmlsingle/#boot-features-external-config-vs-value)

## 文件上传组件

```javascript
// pom中暂时注释掉，将来稳定了，考虑封装通用组件jar，目前先写与源码中
<dependency>
    <groupId>cn.dceast</groupId>
    <artifactId>file-handler</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```


## 不适用自带的spring-boot-maven-plugin

```javascript
    // 这样打出的war会很精简
    <plugin>
        <artifactId>maven-war-plugin</artifactId>
        <configuration>
            <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
    </plugin>
```

## main方法直接启动

```javascript
    // main方法直接启动，需要将<scope>provided</scope>注释掉，否则无法再开发环境使用内嵌的tomcat提供的servlet环境
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <!--生产环境放开-->
        <!--<scope>provided</scope>-->
    </dependency>
```

## api接口调用

```javascript
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        //使用spring自带的相应包装类,业务逻辑只需要关注200 code,其他所有异常均会被拦截
        //对于接口调用方只需判断是否是200 code，而后做处理
        return new ResponseEntity<>(new Result(HttpStatus.OK.value(), userService.getAllUser(), HttpStatus.OK), HttpStatus.OK);
    }
```

## 集成cas

* 既然用cas来拦截登录，这里就不用spring security来做拦截登录了
* 如果后期需要拦截诸如实名认证才能访问的业务就加入aop，或过滤器
* cas集成操作如下：

```javascript
//1. 加入cas.propertiesp配置,见注释配置中注释
//2. 加入依赖

<dependency>
    <groupId>cn.dceast.platform</groupId>
    <artifactId>platform-cas-client</artifactId>
    <version>1.0.9</version>
</dependency>

//3. 需要部署uc-cash和user-center,这2个源码包已经push到git上了
```

## tk.mapper集成

```javascript
/**
 * 继承自己的MyMapper
 *
 * @author liuzh
 * @since 2015-09-06 21:53
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}

@MapperScan(basePackages = "cn.dceast.mapper", markerInterface = MyMapper.class)
public class DemoApplication extends SpringBootServletInitializer {}

// application.yml
mapper:
    mappers:
        - cn.dceast.util.mapperUtil.MyMapper
    not-empty: false
    identity: MYSQL

pagehelper:
    helperDialect: mysql
    reasonable: true
    supportMethodsArguments: true
    params: count=countSql
```

## 主要工具集
* google.guava
    * [Our users' guide, Guava Explained](https://github.com/google/guava/wiki)
    * [GitHub project](https://github.com/google/guava)
* java8 time [案例](http://blog.csdn.net/mn960mn/article/details/53148044)
* lombok [doc](https://projectlombok.org/features/)
    * idea需要装lombok插件`->设置->plugins->browse...`
* git [refer git doc url](http://git-scm.com/book/zh)


## 注意事项
* 调试过程浏览器务必深度刷新（ctrl+F5）