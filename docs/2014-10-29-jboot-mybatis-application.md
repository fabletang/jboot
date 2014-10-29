#在spring-boot中使用mybatis访问数据库

本文改造上一篇[<在spring-boot使用jdbc访问数据库>](2014-10-29-jboot-jdbc-application.md)中的留言板程序，使用Mybatis访问数据库

*  首先在`Application`类中定义dataSource、sqlSessionFactory和transactionManager

```
@Bean(name = "dataSource")
public DataSource dataSource() {
	EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	builder.setType(EmbeddedDatabaseType.H2);
	builder.continueOnError(false);
	builder.setName("jboot");
	builder.setScriptEncoding("UTF-8");
	builder.setSeparator(";");
	builder.addScript("classpath:/sql/schema.sql");
	builder.addScript("classpath:/sql/data.sql");
	return builder.build();
}
@Bean(name = "transactionManager")
public DataSourceTransactionManager transactionManager() {
	return new DataSourceTransactionManager(dataSource());
}
@Bean(name = "sqlSessionFactory")
public SqlSessionFactory sqlSessionFactory() throws Exception {
	SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	sessionFactory.setDataSource(dataSource());
	sessionFactory.setTypeAliasesPackage("org.lenic.jboot.mybatis.domain");
	return sessionFactory.getObject();
}
```

*  删除`application.properties`中spring.datasource.*配置

*  引入依赖

```
<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis-spring</artifactId>
	<version>1.2.2</version>
</dependency>
<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis</artifactId>
	<version>3.2.8</version>
</dependency>
```

*  添加`MapperScan`注解，让mybatis扫描指定package中的mapper

```
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
@MapperScan("org.lenic.jboot.mybatis.mapper")
public class Application {
  // ...
}
```

*  添加`MessageMapper.java`和`MessageMapper.xml`

```
public interface MessageMapper {
	public List<Message> findMessage();
	public String findMaxId();
	public void deleteMessage(String id);
	public void createMessage(Message message);
}
```

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.lenic.jboot.mybatis.mapper.MessageMapper">
	<select id="findMessage" resultType="Message">
		SELECT * FROM MESSAGE ORDER BY CREATED DESC
	</select>
	<select id="deleteMessage" parameterType="java.lang.String">
		DELETE FROM MESSAGE WHERE ID = #{id}
	</select>
	<select id="createMessage" parameterType="Message">
		INSERT INTO MESSAGE(ID,AUTHOR,EMAIL,CONTENT,CREATED)VALUES(#{id},#{author},#{email},#{content},#{created})
	</select>
	<select id="findMaxId" resultType="java.lang.String">
		SELECT MAX(ID) FROM MESSAGE 
	</select>
</mapper>
```

*  添加`MessageService`类并启用事务

```
@Service
@Transactional
public class MessageService {
	@Autowired
	private MessageMapper	messageMapper;
	// ...
}
```

*  将增删改查的请求单独放在`MessageControler`类中

```
@Controller
public class MessageController {
	@Autowired
	private MessageService	messageService;
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Message message) {
		messageService.deleteMessage(message);
		return "redirect:index";
	}
	@RequestMapping("/index")
	public ModelAndView index() {
		List<Message> messages = messageService.findMessage();
		return new ModelAndView("index", "messages", messages);
	}
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(Message message) {
		messageService.createMessage(message);
		return "redirect:index";
	}

}
```

*  其他东西就不用变了，比如freemarker的配置，sql脚本等等

*  启动之后就会看到和上篇文章中一样的效果了
