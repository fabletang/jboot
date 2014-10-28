#在spring-boot应用中使用logger记录日志

本文演示了在spring-boot应用中使用logger记录日志。

*  spring-boot默认在控制台打印INFO,WARN,ERROR日志，并写入temp目录的spring.log文件，下面我们通过application.properties来改变默认设置

*  添加`logging.path`改变日志写入的目录，文件名仍是spring.log

```
logging.path=path/to/log
```

*  添加`logging.file`改变日志写入的文件，设置该项会使`logging.path`无效

```
logging.file=jboot.log
```

*  添加`logging.level.*=LEVEL`改变日志输出级别，可用的LEVEL包括`TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF`

```
# 自定义日志
logging.level.org.lenic.jboot.logging.Application=DEBUG
# 改变spring-web的日志级别
logging.level.org.springframework.web=ERROR
```

*  获取logger(slf4j)

```
private org.slf4j.Logger		logger	= org.slf4j.LoggerFactory.getLogger(getClass());
```

*  获取logger(log4j)

```
private org.apache.log4j.Logger	logger	= org.apache.log4j.Logger.getLogger(getClass());
```

*  使用logger打印日志

```
logger.debug("this is debug");
logger.info("this is info");
logger.warn("this is warn");
logger.error("this is error");
```

*  打印日志结果

```
2014-10-28 14:03:33.904 DEBUG 6152 [http-nio-8080-exec-1] --- ication$$EnhancerBySpringCGLIB$$bab0d30a : this is slf4j debug
2014-10-28 14:03:33.904  INFO 6152 [http-nio-8080-exec-1] --- ication$$EnhancerBySpringCGLIB$$bab0d30a : this is slf4j info
2014-10-28 14:03:33.905  WARN 6152 [http-nio-8080-exec-1] --- ication$$EnhancerBySpringCGLIB$$bab0d30a : this is slf4j warn
2014-10-28 14:03:33.905 ERROR 6152 [http-nio-8080-exec-1] --- ication$$EnhancerBySpringCGLIB$$bab0d30a : this is slf4j error
2014-10-28 14:03:33.905 DEBUG 6152 [http-nio-8080-exec-1] --- ication$$EnhancerBySpringCGLIB$$bab0d30a : this is log4j debug
2014-10-28 14:03:33.906  INFO 6152 [http-nio-8080-exec-1] --- ication$$EnhancerBySpringCGLIB$$bab0d30a : this is log4j info
2014-10-28 14:03:33.906  WARN 6152 [http-nio-8080-exec-1] --- ication$$EnhancerBySpringCGLIB$$bab0d30a : this is log4j warn
2014-10-28 14:03:33.906 ERROR 6152 [http-nio-8080-exec-1] --- ication$$EnhancerBySpringCGLIB$$bab0d30a : this is log4j error
```