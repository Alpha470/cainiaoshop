# cainiaoshop

#### 介绍
菜鸟商城

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

### 网关

1.创建springboot工程选中gateway

2.配置服务注册发现、配置中心信息

服务注册配置application.yml

```yml
server:
  port: 88
spring:
  application:
    name: cainiaosop-gateway
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
```

服务配置中心

bootstrap.properties

```properties
spring.application.name=cainiaoshop-gateway
##命名空间服务配置nacos
spring.cloud.nacos.config.namespace=3bd05659-7e1b-4510-82a2-764a3634025d
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
```

3.额外（如果maven添加了jdbc相关信息可以排除）![image-20200530135943705](/Users/guojun/Library/Application Support/typora-user-images/image-20200530135943705.png)

maven引入gateway,但是没有webstarter相关依赖，引入会报错（亲测）

```xml
 <dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-gateway</artifactId>
  </dependency>

```

### 校验

##### 前台校验

参考elementui官方文档form表单校验

##### 后台校验   JSR303

  1.给实体entiy对应字段添加校验规则。例如@NotBlank(message = "品牌名不能为空")

  2.给方法添加@valid 

3. BindingResult紧跟校验的bean后面，校验错误返回值

```java
@RequestMapping("/save" )
    public R save(@Valid @RequestBody BrandEntity brand, BindingResult result) {
        if (result.hasErrors()) {
            HashMap<String, String> hashMap = new HashMap<>();
            result.getFieldErrors().forEach((item) -> {
                        String defaultMessage = item.getDefaultMessage();
                        String field = item.getField();
                        hashMap.put(field, defaultMessage);
                    }
            );
          return   R.error(400,"数据不合法").put("data",hashMap);

        }
        brandService.save(brand);

        return R.ok();
    }
```

4.分组校验 groups

比如新增和修改校验规则不一样，比如场景：id新增的时候不需要，但是修改必填

1）.实体添加校验注解

```java
/**
 * 品牌id
 */
@Null(message = "品牌id必须为空",groups = {Addgroup.class})
@NotNull(message = "品牌id不能为空",groups = {Editgroup.class})
@TableId
private Long brandId;
```

2）.方法体指定注解类型

```java
/**
 * 新增
 */
@RequestMapping("/add" )
//@RequiresPermissions("product:brand:save")
public R add(@Validated(Addgroup.class) @RequestBody BrandEntity brand) {
    brandService.save(brand);
    return R.ok();
}
```

5.自定义校验

1）、编写一个自定义注解

2）、编写一个自定义校验器

3）、关联自定义校验器和自定义注解

```java
/**
 * 显示状态[0-不显示；1-显示]
 */
@ListVaLue(vals={1,0} ,groups = {Addgroup.class},message = "显示状态错误")
private Integer showStatus;
```

```java
package com.alpha.common.valid;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
//编写一个自定义注解
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ListValueConstraintValidator.class })
public @interface ListVaLue {

    String message() default "{com.alpha.common.valid.ListVaLue.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    int[] vals() default {};

}
```

```xml
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
</dependency>
```

```java
package com.alpha.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义校验器
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListVaLue,Integer> {
    private Set<Integer> set=new HashSet<>();
    //判断是否校验成功
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        return set.contains(value);
    }


    @Override
    public void initialize(ListVaLue constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int val :vals){
            set.add(val);
        }

    }
}
```

### 统一异常处理

springmvc 提供的controlleradvice

专门编写一个类处理异常，抛出异常

**此时save方法里面的参数（BindingResult result）就不需要了**

```java
//这俩注解合并等于
//@RestControllerAdvice(basePackages = "com.alpha.cainiaoshop.product")
@ResponseBody
@ControllerAdvice(basePackages = "com.alpha.cainiaoshop.product")
```

```java
@Slf4j
@RestControllerAdvice(basePackages = "com.alpha.cainiaoshop.product" )
public class cainiaoshopControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现异常", e.getMessage(), e.getCause());
        HashMap<String, String> errormap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((item) -> {
            errormap.put(item.getField(), item.getDefaultMessage());
        });
        return R.error(BizcodeEnume.VAILD_EXCEPTION.getCode(), BizcodeEnume.VAILD_EXCEPTION.getMsg()).put("data", errormap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {

        return R.error();
    }
}
```

### 



#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
