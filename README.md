## 什么是事务

*概念*

事务一般特指数据库事务(Database Transaction) ,是指作为一个程序执行单元执行的一系列操作,要么完全执行,要么完全不执行。

*特性*

* 原子性(atomicity) – 一个事务是一个不可分割的工作单位。 

* 一致性(consistency) – 事务必须是使数据库从一个一致性状态变到另一个一致性状态。 

* 隔离性(isolation) – 一个事务的执行不能被其他事务干扰。 

* 持久性(durability) – 一个事务一旦提交,它对数据库中数据的改变就应该是永久性的。

## Mysql事务处理

* 基本规则 

    – MySQL 中只有使用了 Innodb 数据库引擎的数据库或表才支持事务。 
    
    - show engines;  --查看服务器支持的引擎
    
    - default-storage-engine = Innodb  --my.ini修改默认引擎 
    
    – MySQL默认以自动提交(autocommit)模式运行。 

* 语句 

    – BEGIN(START TRANSACTION) 
        
    • 显式地开启一个事务  
    
    – COMMIT 
    
    • 提交事务,并使已对数据库进行的所有修改变为永久性的
    
    – ROLLBACK 
    
    • 回滚事务,并撤销正在进行的所有未提交的修改
 
## 事务并发问题

* 脏读

* 不可重复读

* 幻读

## Mysql事务隔离级别

事务隔离级别|脏读|不可重复读|幻读
:---:|:---:|:---:|:---:|
读未提交(read-uncommitted)| 是| 是| 是
读已提交(read-committed)| 否| 是| 是
可重复读(repeatable-read)| 否| 否| 是
串行化(serializable)| 否| 否| 否

## 语句 
– select @@tx_isolation 

* 查询默认隔离级别 

– set session transaction isolation level XXX  

* 设置当前会话隔离级别

## JDBC事务处理 
* Connection接口 

– JDBC的事务处理是基于Connection的,JDBC通过Connection对象进行事务管理。 

– JDBC默认事务处理行为是自动提交。 

• 事务相关方法 

– setAutoCommit 

* 设置自动提交 

– commit 

* 提交事务 

– rollback 

* 回滚事务
## TransationDefinition接口

## 隔离级别
 
- ISOLATION_DEFAULT
- ISOLATION_READ_UNCOMMITTED
- ISOLATION_READ_COMMITTED
- ISOLATION_REPEATABLE_READ
- ISOLATION_SERIALIZABLE

## 默认超时

— TIMEOUT_DEFAULT 默认30s

## 事务传播行为

* PROPAGATION_REQUIRED 

– 支持当前事务,如果当前没有事务,就新建一个事务。
 
* PROPAGATION_SUPPORTS 

– 支持当前事务,如果当前没有事务,就以非事务方式执行。 

* PROPAGATION_MANDATORY 

– 支持当前事务,如果当前没有事务,就抛出异常。 

* PROPAGATION_REQUIRES_NEW 
– 新建事务,如果当前存在事务,把当前事务挂起。 

* PROPAGATION_NOT_SUPPORTED 

– 以非事务方式执行操作,如果当前存在事务,就把当前事务挂起。
 
* PROPAGATION_NEVER 

– 以非事务方式执行,如果当前存在事务,则抛出异常。 

* PROPAGATION_NESTED 

– 如果当前存在事务,则在嵌套事务内执行。如果当前没有事务,就新建一个事务。

## Spring编程式事务处理 

* 基于底层 API 的编程式事务管理 

– PlatformTransactionManager 

– TransactionDefinition  

– TransactionStatus   

* 基于 TransactionTemplate 的编程式事务管理 

– TransactionTemplate

## Spring声明式事务处理 
* 概述 

– Spring 的声明式事务处理是建立在 AOP 的基础之上的。其本质是对方法前后进行拦截,然后在目标方法开始之前创建或者加入一个事务,在执行完目标方法之后根据执行情况提交或者回滚事务。 

– 建议在开发中使用声明式事务,是因为这样可以使得业务代码纯粹干净,方便后期的代码维护。

## Spring声明式事务处理 

* 基于TransactionInterceptor的声明式事务处理 

* 基于TransactionProxyFactoryBean的声明式事务处理  

* 基于 <tx> 命名空间的声明式事务管理  

* 基于 @Transactional 的声明式事务管理