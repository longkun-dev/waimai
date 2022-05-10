# 项目问题记录


## 1、改了 application.yml 中端口相关的配置没生效，一直报端口占用
> 因为没有在 pom.xml 文件中将 .yml 文件一起编译到 target 目录下，所以该玩的配置一直没生效。
> 尤其是配置中心中的配置，如果不编译到 target 配置下，服务会因为读取不到配置，启动失败，包括端口错误、连接到配置中心。


## 改了 pom.xml 文件，一直引入失败，有红线
> 1、maven 的缓存问题，直接清空 maven 本地仓库重新导入；
> 2、包引入了，但是 idea 的缓存问题导致红线，清空 idea 缓存重启。


## 引入了数据库相关的依赖之后启动报错，因为读取不到数据源相关的配置
> 在启动类上加 exclude = DataSourceAutoConfiguration.class


## 创建网关项目，报错需要排除 spring-boot-starter-web
错误信息：
Spring MVC found on classpath, Spring Cloud Gateway at this time. Please remove spring-boot-star
> 因为在父项目中引入了 web 依赖，所以子模块中也会引入该依赖，但是 spring-cloud-gateway 中需要排除该依赖，
> 利用 maven 中依赖覆盖原则
> 1.依赖路径短的优先
> 2.后声明的优先
> 3.路径长度相同，声明顺序优先
> 在网关项目中引入该依赖，然后加上 scope=test可以解决该问题。
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <scope>test</scope>
</dependency>
```
参考链接：[maven 如何不继承 parent 里面的部分依赖](https://www.oschina.net/question/1756518_221515)

