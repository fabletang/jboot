#自定义spring-boot应用

本文演示了如何自定义一个spring-boot应用，包括Banner、Event、Exit等内容。

*  创建一个SpringApplication，并进行设置：
```
SpringApplication application = new SpringApplication(Application.class);
// 初始化器
application.addInitializers(initializers);
application.setInitializers(initializers);
// 事件监听器
application.addListeners(listeners);
application.setListeners(listeners);
// 在classpath下添加banner.txt，可以改变启动程序时的横幅，本例中输出了一个猫的图案:)
application.setShowBanner(showBanner);
// 设置source对象
application.setSources(sources);
// 是否启用命令行属性
application.setAddCommandLineProperties(addCommandLineProperties);
// 设置额外的profile属性
application.setAdditionalProfiles(profiles);
// 设置ApplicationContext的Class，必须是`ConfigurableApplicationContext`或其子类
application.setApplicationContextClass(applicationContextClass);
// 设置默认属性，支持Properties和Map
application.setDefaultProperties(defaultProperties);
// 设置环境变量
application.setEnvironment(environment);
application.setBeanNameGenerator(beanNameGenerator);
application.setHeadless(headless);
application.setLogStartupInfo(logStartupInfo);
application.setMainApplicationClass(mainApplicationClass);
application.setRegisterShutdownHook(registerShutdownHook);
application.setResourceLoader(resourceLoader);
application.setWebEnvironment(webEnvironment);
// 设置完成，启动程序
application.run(args);
```

*  退出spring-boot
```
SpringApplication.exit(applicationContext, exitCodeGenerator);
```

*  使用事件，通过`SpringApplication.addListeners(ApplicationListener)`添加监听器，事件执行顺序
  1.  ApplicationStartedEvent在程序启动时执行，在任何listener和initializer之前
  2.  ApplicationEnvironmentPreparedEvent在context创建之前
  3.  ApplicationPreparedEvent在`ConfigurableApplicationContext.refresh()`之前
  4.  ApplicationFailedEvent在启动过程中发生异常时触发
  *  官方不建议使用这些事件进行扩展，因为它们主要是spring-boot内部使用

*  最后修一下banner.txt
```
                      ,
                    _/((
           _.---. .'   `\
         .'      `     ^ T=
        /     \       .--'
       |      /       )'-.
       ; ,   <__..-(   '-.)
        \ \-.__)    ``--._)
         '.'-.__.-.
           '-...-'
 :: Spring Boot :: This is a cat application!
```