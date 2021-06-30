# SpringCloud微服务学习笔记(图片待完善) 从零开始的微服务学习(持续更新), 版本2020.0.3
这里是我自己的微服务学习过程中的一些杂碎笔记, 针对开发过程中我自己踩的种种坑, 如依赖不全, 服务注册中心集群配置错误等等. 希望能给大家提供点帮助, 在微服务学习上更加顺畅

## 引言
这是我学习 bilibili上的SpringCloud课程 [链接](https://www.bilibili.com/video/BV18E411x7eT) 后所写

## Maven仓库配置(依赖版本无法下载等问题必看)

改 Maven 的配置文件, **settings.xml**

![IDEA中查看Maven配置文件路径](https://images.gitee.com/uploads/images/2021/0629/234117_56f7ef62_5350519.png "屏幕截图.png")

只使用阿里云的镜像是不够的, 学习SpringCloud之前, 加上 Spring官方的镜像, 方便依赖的引入

```xml
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <!-- 依据路径不同进行配置repository  -->
  <localRepository>C:\Users\悠一木碧\.m2\repository</localRepository>
  
  <interactiveMode>true</interactiveMode>
  <offline>false</offline>

  <pluginGroups>
  </pluginGroups>

  <proxies>
  </proxies>

  <servers>
  </servers>

  <mirrors>
	<mirror>
	  <id>aliyunmaven</id>
	  <mirrorOf>*</mirrorOf>
	  <name>阿里云公共仓库</name>
	  <url>https://maven.aliyun.com/repository/public</url>
	</mirror>
	
  </mirrors>

    <profiles>
        <profile>
            <id>jdk-11</id>
            <activation>
                <activeByDefault>true</activeByDefault>
                <jdk>11</jdk>
            </activation>
            <properties>
                <maven.compiler.source>11</maven.compiler.source>
                <maven.compiler.target>11</maven.compiler.target>
                <maven.compiler.compilerVersion>11</maven.compiler.compilerVersion>
            </properties>
        </profile>
    <!-- spring 依赖中所使用到的配置, 加上之后, 基本能解决所有的spring相关依赖  -->    
    <profile>
      <id>spring</id>
      <repositories>
        <repository>
          <releases>
            <enabled>false</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
          <id>spring-snapshots</id>
          <name>Spring Snapshots</name>
          <url>https://repo.spring.io/libs-snapshot-local</url>
        </repository>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>spring-milestones</id>
          <name>Spring Milestones</name>
          <url>https://repo.spring.io/libs-milestone-local</url>
        </repository>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>spring-releases</id>
          <name>Spring Releases</name>
          <url>https://repo.spring.io/release</url>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <releases>
            <enabled>false</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
          <id>spring-snapshots</id>
          <name>Spring Snapshots</name>
          <url>https://repo.spring.io/libs-snapshot-local</url>
        </pluginRepository>
        <pluginRepository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>spring-milestones</id>
          <name>Spring Milestones</name>
          <url>https://repo.spring.io/libs-milestone-local</url>
        </pluginRepository>
      </pluginRepositories>
    </profile>
    </profiles>
  
</settings>

```



## IDEA热部署 dev-tool启用

1. 首先在 pom.xml 中引入对应的依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
```

2. 在IDEA-Settings-Build 全部勾上下列选项![](https://images.gitee.com/uploads/images/2021/0629/234339_87a91b70_5350519.png "屏幕截图.png")

3. Ctrl + Alt + Shift + /  打开IDEAMaintenance 窗口

![](https://images.gitee.com/uploads/images/2021/0629/234423_7b1b0b00_5350519.png "屏幕截图.png")
   将下列两个选项勾上

   ![](https://images.gitee.com/uploads/images/2021/0629/234437_48dab5f6_5350519.png "屏幕截图.png")

   至此, 完成了热部署的配置

## IDEA打开微服务 Services(原 RunDashboard)
这里我使用的IDEA是最新版 2021, 如果IDEA不是此版本, 可以忽略

IDEA中, 一般有运行多个 Application时, 会自动开启 **services** 窗口

![](https://images.gitee.com/uploads/images/2021/0629/234616_208e7e21_5350519.png "屏幕截图.png")

如果没有, 则打开项目所在文件目录, 进入到 .idea目录并找到名称为 **workspace.xml** 的文件

![](https://images.gitee.com/uploads/images/2021/0629/234636_ac696d32_5350519.png "屏幕截图.png")

检查是否有如下配置

![](https://images.gitee.com/uploads/images/2021/0629/234645_edafdc85_5350519.png "屏幕截图.png")

```xml
  <component name="RunDashboard">
    <option name="configurationTypes">
      <set>
        <option value="SpringBootApplicationConfigurationType" />
      </set>
    </option>
  </component>
```

如果没有, 加上即可


## Eureka集群部署

踩坑: Eureka集群时, Eureka可以相互注册, 但是对应的地址不能正确访问![](https://images.gitee.com/uploads/images/2021/0629/234721_4722098c_5350519.png "屏幕截图.png")

## 负载均衡(load balancing)

负载均衡分两种

1. 集中式负载均衡(如 nginx), 客户端的所有请求都会交给该服务端, 由服务端进行负载均衡
2. 进程内负载均衡(如 Ribbon), 本地负载均衡, 在调用微服务接口时, 会在服务注册中心获取服务信息列表后缓存到本地, 然后RPC远程调用

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
```

### SpringCloud-Netflix 的负载均衡源码解析

新版本的 SpringCloud(**2020.0.3**)版本, 引入的默认负载均衡是 loadbalancer 而不是 ribbon

![](https://images.gitee.com/uploads/images/2021/0629/234748_24a4707e_5350519.png "屏幕截图.png")

进入到 **org.springframework.cloud.loadbalancer.config.LoadBalancerAutoConfiguration** 中, 可以看到其向IOC容器中, 注入了一个**LoadBalancerClientFactory**

```java
/**
 * @author Spencer Gibb
 * @author Olga Maciaszek-Sharma
 */
@Configuration(proxyBeanMethods = false)
@LoadBalancerClients
@EnableConfigurationProperties(LoadBalancerProperties.class)
@AutoConfigureBefore({ ReactorLoadBalancerClientAutoConfiguration.class,
		LoadBalancerBeanPostProcessorAutoConfiguration.class })
@ConditionalOnProperty(value = "spring.cloud.loadbalancer.enabled", havingValue = "true", matchIfMissing = true)
public class LoadBalancerAutoConfiguration {

	...
	...
	...

	@ConditionalOnMissingBean
	@Bean
	public LoadBalancerClientFactory loadBalancerClientFactory() {
		LoadBalancerClientFactory clientFactory = new LoadBalancerClientFactory();
		clientFactory.setConfigurations(this.configurations.getIfAvailable(Collections::emptyList));
		return clientFactory;
	}

}
```

如果将 debug 模式开启, 并给它打上断点, 可以发现, 应用启动的时候是注入了这个负载均衡工厂的

![](https://images.gitee.com/uploads/images/2021/0629/234815_2e45c8d5_5350519.png "屏幕截图.png")

进入到 LoadBalancerClientFactory 类中

```java
public class LoadBalancerClientFactory extends NamedContextFactory<LoadBalancerClientSpecification>
		implements ReactiveLoadBalancer.Factory<ServiceInstance> {

	/**
	 * Property source name for load balancer.
	 */
	public static final String NAMESPACE = "loadbalancer";

	/**
	 * Property for client name within the load balancer namespace.
	 */
	public static final String PROPERTY_NAME = NAMESPACE + ".client.name";

	public LoadBalancerClientFactory() {
		super(LoadBalancerClientConfiguration.class, NAMESPACE, PROPERTY_NAME);
	}

	public String getName(Environment environment) {
		return environment.getProperty(PROPERTY_NAME);
	}

	@Override
	public ReactiveLoadBalancer<ServiceInstance> getInstance(String serviceId) {
		return getInstance(serviceId, ReactorServiceInstanceLoadBalancer.class);
	}

}
```

这个工厂有一个方法, getInstance(String serviceId), 通过服务名称, 获取一个反应式负载均衡器

本地负载均衡肯定是在执行 HTTP 请求时进行的; 所以, 我们给下面这行语句打上断点并进入到debug模式



1. 

![输入图片说明](https://images.gitee.com/uploads/images/2021/0629/234842_5efe17b1_5350519.png "屏幕截图.png")![输入图片说明](https://images.gitee.com/uploads/images/2021/0629/234842_7690ecc9_5350519.png "屏幕截图.png")

2. 通过 debug 模式, 进入到 **LoadBalancerInterceptor.intercept(...)**

![输入图片说明](https://images.gitee.com/uploads/images/2021/0629/234926_e8f3d174_5350519.png "屏幕截图.png")

3. 可以看到这个方法里最后调用了 loadBalancer 的 execute() 查看这个 loadBalancer 是哪个类![输入图片说明](https://images.gitee.com/uploads/images/2021/0629/234956_fd32cc48_5350519.png "屏幕截图.png")

   进入到阻塞式负载均衡客户端类内部, 其是 **org.springframework.cloud.client.loadbalancer.LoadBalancerClient** 的默认实现

   ```java
   /**
    * The default {@link LoadBalancerClient} implementation.
    *
    * @author Olga Maciaszek-Sharma
    * @since 2.2.0
    */
   @SuppressWarnings({ "unchecked", "rawtypes" })
   public class BlockingLoadBalancerClient implements LoadBalancerClient {
       ...
       ...
       ...
   }了解了 loadBalancer 是什么, 继续往方法下面走
   ```

   ![输入图片说明](https://images.gitee.com/uploads/images/2021/0629/235038_3e266266_5350519.png "屏幕截图.png")

   可以看到, 这个负载均衡工厂, 就是 **org.springframework.cloud.loadbalancer.config.LoadBalancerAutoConfiguration** 自动注入的Bean

  ![输入图片说明](https://images.gitee.com/uploads/images/2021/0629/235334_a35090a5_5350519.png "屏幕截图.png")

   自然而然, 其调用的是就是上面看到的 **getInstance()** 方法

   ```java
   	@Override
   	public ReactiveLoadBalancer<ServiceInstance> getInstance(String serviceId) {
   		return getInstance(serviceId, ReactorServiceInstanceLoadBalancer.class);
   	}
   ```

   继续进入到 getInstance(...)方法内部, 其是从容器中获取对应类型的Bean

![输入图片说明](https://images.gitee.com/uploads/images/2021/0629/235442_d8ca631e_5350519.png "屏幕截图.png")

4. 这个负载均衡处理器是什么呢

![输入图片说明](https://images.gitee.com/uploads/images/2021/0629/235518_af5f607a_5350519.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000050_36305ea5_5350519.png "屏幕截图.png")

   这样, 我们就知道了默认 RestTemplate 使用的是使用轮询算法的负载均衡

5. 可以看到, 获取了负载均衡处理器后, 通过这个方法获取到了实例id对应实例的详细信息, 包括实例的ip地址等![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000104_e498e8c0_5350519.png "屏幕截图.png")
6. 具体实现在 **org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer** 类内部

![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000134_2cce4f11_5350519.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000159_0fa4d87d_5350519.png "屏幕截图.png")

7. 现在知道了 RestTemplate 使用的默认是 RoundRobinLoadBalancer, 那么它是怎么创建的呢?

   在 org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration 类中, 可以看到其向容器中注入了这个Bean

   而且, 这个类**只有当服务发现被开启时, 才会生效**

   ```java
   @Configuration(proxyBeanMethods = false)
   @ConditionalOnDiscoveryEnabled
   public class LoadBalancerClientConfiguration {
   
   	@Bean
   	@ConditionalOnMissingBean
   	public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
   			LoadBalancerClientFactory loadBalancerClientFactory) {
   		...
            ...
   		return new RoundRobinLoadBalancer(
   				loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
   	}
   }
   ```

   再来看看这个 RoundRobinLoadBalancer 默认是怎样初始化的

![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000243_06937ce7_5350519.png "屏幕截图.png")

### 自定义负载均衡

```java
/**
 * @author LeXin Huang
 * @date 2021年06月28日 15:29
 */
public interface MyRoundLoadBalancer {
    ServiceInstance choose();
}

/**
 * 核心: 通过 AtomicInteger 自增, 通过服务发现获取对应实例集合, 然后获取余数作为下标, 返回实例即可
 * @author LeXin Huang
 * @date 2021年06月28日 15:30
 */
public class MyRoundLoadBalancerImpl implements MyRoundLoadBalancer{

    private final DiscoveryClient discoveryClient;

    final AtomicInteger position;

    final String serviceId;

    public MyRoundLoadBalancerImpl(DiscoveryClient discoveryClient, int position, String serviceId) {
        this.discoveryClient = discoveryClient;
        this.position = new AtomicInteger(position);
        this.serviceId = serviceId;
    }

    @Override
    public ServiceInstance choose() {
        int pos = Math.abs(this.position.incrementAndGet());
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        System.out.println("instances = " + instances);
        return instances.get(pos % instances.size());
    }
}
```

controller 

```java
/**
 * @author LeXin Huang
 * 不能启用带有@LoadBalanced注解标记的 RestTemplate
 */
@RestController
public class UserController {

    @Resource
    RestTemplate restTemplate;

    @Resource
    MyRoundLoadBalancer myRoundLoadBalancer;


    @GetMapping("/loadBalancing")
    public ApiResult<?> testLoadBalance() {
        ServiceInstance instance = myRoundLoadBalancer.choose();
        System.out.println(instance.getUri());
        return restTemplate.getForObject(instance.getUri() + "/loadBalancing", ApiResult.class);
    }


}
```

## OpenFeign

客户端服务调用的一层封装

### 超时配置

官方文档: 

[SpringCloud-OpenFeign参考文档](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/)

![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000257_0223b63d_5350519.png "屏幕截图.png")

回到配置文件中, 发现是如下类负责该配置

**org.springframework.cloud.openfeign.FeignClientProperties**

![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000334_e7a505d0_5350519.png "屏幕截图.png")

其中 config 属性是个 map, 默认的配置 key 为 default

所以, 我的配置如下, 可根据自己的应用具体更改, 更多属性见 Map中的 **FeignClientConfiguration** 类属性

```yaml
server:
  port: 80

spring:
  application:
    name: service-consumer-payment
eureka:
  client:
    fetch-registry: true
    register-with-eureka: false
    service-url:
      defaultZone: http://localhost:7001/eureka, http://localhost:7002/eureka
feign:
  client:
    config:
      default:
        readTimeout: 4000 
        
```

服务端接口默认睡眠3秒

![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000357_f415e6ac_5350519.png "屏幕截图.png")

测试结果如下

![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000405_76777fe7_5350519.png "屏幕截图.png")

现在修改超时时间为 3000 ms


```yaml
server:
  port: 80

spring:
  application:
    name: service-consumer-payment
eureka:
  client:
    fetch-registry: true
    register-with-eureka: false
    service-url:
      defaultZone: http://localhost:7001/eureka, http://localhost:7002/eureka
feign:
  client:
    config:
      default:
        readTimeout: 3000
        
```
![输入图片说明](https://images.gitee.com/uploads/images/2021/0630/000412_2a1496aa_5350519.png "屏幕截图.png")





