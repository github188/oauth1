# oauth-center

## 部署

1. mvn clean package -Dmaven.test.skip=true 
2. docker-compose build 
3. docker-compose up

注：MySQL container 初始化耗时较长，需要1min左右。

## 开发建议

### 约定

#### 配置 profile

本地开发请使用 application-dev.properties, 生产环境请使用 application-prod.properties。

如果你在多人协作中发现使用 application-dev.properties 存在在环境冲突，那么请添加自定义的 properties 文件，譬如 application-zhaorong.properties，然后将 
application.properties 中 spring.profiles.active 指向 zhaorong，并将 application-zhaorong.properties 文件名添加到 .gitignore 中。

#### 日志

推荐的日志层级为：

开发环境：
  - logging.level.org=INFO
  - logging.level.com.yitutech=DEBUG
  
生产环境：
  - logging.level.org=WARN
  - logging.level.com.yitutech=INFO
  
日志类为 common module 中的 com.yitutech.common.log.MedicalLog，MedicalLog 对 slf4j api 进行了包装，请尽量避免直接使用 slf4j api。

MedicalLog 可以借助工厂类 MedicalLogFactory 来生成。

### api module

基于 biz module 完成 api 接口的开发。

#### 错误处理

错误码的定义在 error 包中，你可以在其中增加自定义的 error code 定义。但是请注意，你也需要在 resources 中增加以 -code.properties 结尾的 code message 说明。

错误的全局处理在 com.yitutech.controller.handler.ErrorControllerAdvice 中。你同样可以增加自定义的方法来对某些异常进行统一处理。

#### vo 的定义

请尽量在不要直接将 bo 和 model 的对象进行序列化，而建议在 vo 包中定义需要返回的数据格式，然后借助[modelmapper](http://modelmapper.org/)完成 bo => vo 的映射。

#### 测试

请使用 MockMvc 对 controller 进行测试。建议测试覆盖限于 controller 的逻辑，biz module 的接口通过合理的 mock 进行模拟。

#### 接口文档

文档工具为 [swagger.io](https://swagger.io/)，访问 uri 为 /swagger-ui.html，使用方法请参考示例代码。

### biz module

基于 dal module 完成业务逻辑的开发。

#### bo 的定义

请在 bo 中定义 biz 层需要的数据格式，而不要将 dal module 中 model 直接返回。我们同样推荐借助[modelmapper](http://modelmapper.org/)完成 model => bo 的映射。

#### 测试

请使用 MockMvc 对 controller 进行测试。建议测试覆盖限于 biz 的逻辑，dal module 的接口通过合理的 mock 进行模拟。

具体的测试的使用请参考实例代码。

### dal module

基于 MyBatis 完成数据访问层逻辑的开发。

#### 代码生成

请在 generatorConfig.xml 中配置数据访问，然后通过命令`mvn mybatis-generator:generate`完成代码生成，对于生产的 dao 代码，请不要忘记加上`@Mapper`注解。

#### 测试

测试通过 MybatisTest 来完成，使用请参考示例代码。

### sql-migration module

通过 [flyway](https://flywaydb.org/) 完成数据库管理。

#### 初始化 sql

请在 resources.db.migration 中增加自定义的 sql。
