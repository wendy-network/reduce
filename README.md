# reduce短网址平台

测试站地址：http://dev.icoody.cn/

#### 技术说明

    本项目为Coody Framework+Minicat首秀，短网址服务平台。部署在服务器，使用短域名解析即可提供服务。

    全项目打包后(带前端页面)大小约：2.5M，非常精简。

    前端采用Amaze UI，后端采用Coody Framework MVC，数据库采用H2DB


Coody Framework地址：[https://gitee.com/coodyer/Coody-Framework](https://gitee.com/coodyer/Coody-Framework)

#### 基本使用

导入Maven项目，运行访问即可

也可以Maven Install 构建Jar包，丢服务器java -jar 运行。记得配置数据库。

![输入图片说明](https://images.gitee.com/uploads/images/2020/0320/213406_6f5b702f_1200611.png "T.png")

#### 更换数据库

有好几位朋友来问我换mysql，介绍下换成mysql的方案
1、引入mysql驱动（根据你mysql版本来）

```
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.18</version>
</dependency>
```
2、修改配置

```

coody.bean.dataSource.class=org.coody.framework.esource.ESource
coody.bean.dataSource.field.driver=com.mysql.jdbc.Driver
coody.bean.dataSource.field.url=jdbc\:mysql\://localhost:3306/reduce?useUnicode\=true&characterEncoding\=UTF-8&serverTimezone=GMT%2B8
coody.bean.dataSource.field.user=reduce
coody.bean.dataSource.field.password=Coody888!
coody.bean.dataSource.field.maxPoolSize=64
coody.bean.dataSource.field.minPoolSize=2
coody.bean.dataSource.field.initialPoolSize=5
```
3、导入数据表

```
CREATE TABLE `short_info` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `frequency` bigint(32) NOT NULL DEFAULT 0,
  `status` tinyint(4) DEFAULT '1',
  `appId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) 
);
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(32) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) ,
  UNIQUE KEY `email_uk` (`email`) USING BTREE
) ;
CREATE TABLE `app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32)  DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `unionId` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int(2) DEFAULT '0',
  PRIMARY KEY (`id`) ,
  UNIQUE KEY `app_uk` (`unionId`)  USING BTREE
) ;
CREATE TABLE `email_queue` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `unionId` varchar(32) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `context` varchar(256) DEFAULT NULL,
  `targeEmail` varchar(32) DEFAULT NULL,
  `status` int(2) DEFAULT '0',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `millisecond` bigint(32) DEFAULT NULL,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)

```

然后就可与愉快的使用MYSQL了

#### 随便丢几张图

###### 登录页面  http://127.0.0.1:81/html/login.html

![输入图片说明](https://images.gitee.com/uploads/images/2020/0110/171636_d9d9093f_1200611.png "1.png")

###### 注册页面  http://127.0.0.1:81/html/register.html

![输入图片说明](https://images.gitee.com/uploads/images/2020/0110/171653_0950dff5_1200611.png "2.png")

###### 找回密码页面  http://127.0.0.1:81/html/reset_password.html

![输入图片说明](https://images.gitee.com/uploads/images/2020/0110/171709_80999218_1200611.png "3.png")

###### 用户首页

![输入图片说明](https://images.gitee.com/uploads/images/2020/0110/171729_a20c28b9_1200611.png "4.png")

###### 应用页面

![输入图片说明](https://images.gitee.com/uploads/images/2020/0110/171743_a4f5b2c0_1200611.png "5.png")

###### 短网址页面

![输入图片说明](https://images.gitee.com/uploads/images/2020/0110/171756_649adeb7_1200611.png "6.png")


### 版权说明：

    作者：Coody
    
    版权：©2014-2020 Test404 All right reserved. 版权所有

    反馈邮箱：644556636@qq.com  交流群号:218481849

