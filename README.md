# LogUtil for android
android日志工具类

##特色
 - **基于系统Log日志改造**
 - **支持保存本地文件日志**
 - **支持分模块保存日志**
 - **支持快捷方式打日志**

##使用
1.首先得在使用前初始化一次，一般在Application里面初始化
```
LogConfig logConfig = new LogConfig.Builder(getApplicationContext())
         .setLogLevel(LogUtil.VERBOSE)  //设置日志级别，当为LogUtil.ASSERT不打印日志
         .setNeedSaveToDefaultFile(true)  
         .prefix("-prefix-")
         .suffix("-suffix-")
         .defaultTag("LOG")
         .dirPath("/sunshy")
         .addSaveRule("MainActivity", true)
         .addSaveRule("Feed", true)
         .addSaveRule("qqq", false)
         .build();
LogUtil.init(logConfig);
        
```

2.现在就可以像系统log一样使用
```
LogUtil.e(TAG, "e 1");
LogUtil.e(TAG, "e 2", new Exception("e2"));
LogUtil.e("error1");    //无tag默认当前类名作为tag
LogUtil.e("error2", new Exception("error2"));
```
##安装
###Gradle
```
compile 'com.qyl.log:library:1.1.0'
```
###Maven
```
<dependency>
  <groupId>com.qyl.log</groupId>
  <artifactId>library</artifactId>
  <version>1.1.0</version>
  <type>pom</type>
</dependency>
```
##注意
- **确保有读写文件权限**




License
-------
   
    Copyright 2016 qylshy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

   
   
   
