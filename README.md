# Quartz Minimal Demo Websphere + Tomcat

Minimal project for demonstrate Quartz scheduler in Websphere and Tomcat environment.

* Quartz by default uses SimpleThreadPool which runs scheduled jobs outside J2EE context.
* Project uses thread pool wrapper and runs threads with server context via work managers, JSR 237, with access to resources via JNDI.
* Runs on both Tomcat and Websphere containers with same source base, same build (war).
* Includes Tomcat alternative resource configuration (DataStore, Url, WorkManager) to websphere's resourcers.

## Setup
Compile: `gradle clean build`  
Eclipse: `gradle cleanEclipse eclipse`  
Prereq:
- Oracle DB 11g 
- Gradle 3.4

Setup DB:
1. Create Scheduler tables `DBscripts\tables_oracle.sql` 

Setup Websphere:
1. Create resources (Datastore, Url, WorkManger)  
References to resources are bind by `WebContent\WEB-INF\ibm-web-bnd.xml` (only Websphere needs bindings definition, Tomcat ignores this)
Scheduler starts on `http://localhost:10039/quartz-minimal/`

Setup Tomcat:
1. Copy jars from tomcat/libs to <TOMCAT_HOME>/libs  
Resources configuration are defined in `WebContent\META-INF\context.xml` (only Tomcat use this configuration, Websphere ignores this)
Scheduler starts on `http://localhost:8080/quartz-minimal/` 

Libs used:
- Quartz 2.2.3
- foo-commonj-1.1 (JSR 237 Timer and WorkManager implementation, used in Tomcat)
- resource-factory-0.1.jar (URL provider implementation, used in Tomcat)

Tested environments:
- Websphere 8.0.0.5
- Tomcat 7

## Quartz on Websphere
Websphere requires threadPool and threadExecutor to runs thread via WorkManager, see `sk.fillo.quartz.ManagedThreadPool` and `sk.fillo.quartz.ManagedThreadExecutor`.

## Quartz on Tomcat
Tomcat requires to Quartz jobs must be wrapped by commonj Work with setup deamon to true, see `sk.fillo.quartz.WorkAdapter`.

## Quartz properties
```
org.quartz.threadPool.class=sk.fillo.quartz.ManagedThreadPool
org.quartz.threadPool.workManagerName=java:comp/env/wm/quartz

org.quartz.threadExecutor.class=sk.fillo.quartz.ManagedThreadExecutor
org.quartz.threadExecutor.workManagerName=java:comp/env/wm/quartz
```

