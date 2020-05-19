### 说明
#### 环境
- jdk 8
- mysql 5.7
- idea

### 运行步骤
- 新建一个名为sdk-demo的数据库连接
- 运行建表语句 位置：data/createTable.sql
- 导入idea sdk-demo-server项目
- 更新maven依赖
- 运行SdkDemoServerApplication

### 完成功能
- 实现添加、查询、下载、修改文件的restful接口
- 实现多人同时修改同一文件时websocket通讯
- 多人对同一文件修改时的加锁策略

### 未完成功能
