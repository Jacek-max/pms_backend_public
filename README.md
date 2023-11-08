# 项目介绍
本系统基于SpringBoot+Vue开发的前后端分离系统，适用于个人学习使用、毕业设计参考、日常大作业等情况，仅开放于个人使用，如需商用请自行在原基础上进行二次定制化开发。<br />**前端代码仓库：**[https://github.com/Jacek-max/pms_front_public](https://github.com/Jacek-max/pms_front_public)<br />**后端代码仓库：**[https://github.com/Jacek-max/pms_backend_public](https://github.com/Jacek-max/pms_backend_public)
# 功能介绍
## 已完成功能列表

- **系统管理**
   - 员工管理
   - 角色管理
   - 权限管理
- **房屋管理**
   - 楼栋管理
   - 单元管理
   - 房屋管理
- **车位管理**
- **业主管理**
   - 收费项管理
   - 水电费管理、停车费用管理
- **投诉管理**
- **报修管理**
   - 报修工单派工、用户确认
- **公告管理**
## 待完善功能（后续补充）

- [ ] 系统管理：登录页轮播管理模块
- [ ] 字典数据优化：现字典为写死数据，灵活率不高，后续可抽取为配置项
- [ ] 多小区管理：目前为单租户情况
- [ ] 场地管理：可以设置小区内开放的运动健身场所给住户进行预约
- [ ] ……
# 技术栈
## 前端
主要技术：

- vue 2.0
- js
- element-ui组件库
- vue-admin-template开源框架
## 后端
主要技术：

- Spring Boot 2.4.x
- MyBatis Plus 3.4.x
- MySQL 8.x
- Spring Security
# 快速启动
## 前端
安装依赖：
```
npm install
```
运行：
```
npm run dev
```
打包：
```
npm run build
```
## 后端

1. 运行 sql 目录下的 sys_wygl.sql 建表
2. 修改 application-test.yml 中的数据库地址为自己的
3. 安装完 Maven 依赖后，直接运行即可
4. 如需部署，请自行编写dockerfile文件
# 系统设计
主要分享系统的整体架构和核心设计，而传统 web 开发部分不做过多介绍。
# 运行效果
## 登录界面
![image.png](https://cdn.nlark.com/yuque/0/2023/png/25709972/1699456608410-6cbbd177-e122-4ec4-a9e0-3bdade3bf38f.png#averageHue=%2383c5e0&clientId=ub3c32ace-b447-4&from=paste&height=783&id=u71dbecbd&originHeight=783&originWidth=1183&originalType=binary&ratio=1&rotation=0&showTitle=false&size=590850&status=done&style=none&taskId=u606e6984-eee9-420b-9ef9-51f57945c7a&title=&width=1183)
## 管理端
控制台<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/25709972/1699456656859-c40dba0c-afc0-408d-a5e8-bb3585d108de.png#averageHue=%239ec540&clientId=ub3c32ace-b447-4&from=paste&height=859&id=ub90c4fa8&originHeight=859&originWidth=2553&originalType=binary&ratio=1&rotation=0&showTitle=false&size=119867&status=done&style=none&taskId=u672f6a14-981f-40ab-ba2f-a68087a5e70&title=&width=2553)<br />部分功能界面<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/25709972/1699456703053-15578a8a-7e14-4f09-952a-eb485ea68470.png#averageHue=%23999999&clientId=ub3c32ace-b447-4&from=paste&height=780&id=u30a65719&originHeight=780&originWidth=2556&originalType=binary&ratio=1&rotation=0&showTitle=false&size=116164&status=done&style=none&taskId=ub2fa5679-0b47-4c74-9fa3-8e6be65b82b&title=&width=2556)<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/25709972/1699456730295-0243cf78-8522-47de-94a4-555dd8dca8f1.png#averageHue=%23fcfafa&clientId=ub3c32ace-b447-4&from=paste&height=1300&id=u2a542ebf&originHeight=1300&originWidth=2556&originalType=binary&ratio=1&rotation=0&showTitle=false&size=224447&status=done&style=none&taskId=uff03670d-b0a4-42d4-bccc-cc288e86215&title=&width=2556)<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/25709972/1699456783148-9a799e82-7b16-44a1-90f0-f2a1078ae764.png#averageHue=%23fefdfd&clientId=ub3c32ace-b447-4&from=paste&height=738&id=ub33f8fa2&originHeight=738&originWidth=2559&originalType=binary&ratio=1&rotation=0&showTitle=false&size=98082&status=done&style=none&taskId=ubd4cfd2e-3a33-4d2f-84f0-4711369c8a0&title=&width=2559)
## 维修端
![image.png](https://cdn.nlark.com/yuque/0/2023/png/25709972/1699456872752-38f31649-96b1-4ead-b87b-09e4a135b673.png#averageHue=%23fefefe&clientId=ub3c32ace-b447-4&from=paste&height=536&id=u53b6bc92&originHeight=536&originWidth=2558&originalType=binary&ratio=1&rotation=0&showTitle=false&size=65956&status=done&style=none&taskId=u2d275d47-131a-499e-8987-14806c6c3a8&title=&width=2558)
## 业主端
控制台<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/25709972/1699456939251-e42c1885-0d77-40d2-9fc9-129817c229b2.png#averageHue=%23e6a440&clientId=ub3c32ace-b447-4&from=paste&height=809&id=u9f295098&originHeight=809&originWidth=2557&originalType=binary&ratio=1&rotation=0&showTitle=false&size=91218&status=done&style=none&taskId=u445a7fab-cf73-4865-9ef1-355528a7775&title=&width=2557)<br />部分功能页面<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/25709972/1699456976305-c94888ab-9788-42f2-abb9-0c2a538c1e6d.png#averageHue=%23fefdfd&clientId=ub3c32ace-b447-4&from=paste&height=509&id=u23f405c3&originHeight=509&originWidth=2559&originalType=binary&ratio=1&rotation=0&showTitle=false&size=74534&status=done&style=none&taskId=u32ff44df-882b-4f65-8a4f-045442726a8&title=&width=2559)
# 更多介绍
# 致谢
