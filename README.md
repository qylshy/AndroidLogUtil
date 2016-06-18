# LogUtil for android
android日志工具类

##特色
 - **基于系统Log日志改造,简单易用**
 - **支持保存本地文件日志**
 - **支持分模块保存日志,在大工程项目中分模块调试有优势**
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
3.LogConfig 配置
```
setLogLevel(int logLevel)  //设置日记打印级别, LogUtil.VERBOSE 打印VERBOSE级别以上所有日志
```
```
prefix(String prefix)  //指定保存日志的文件前缀
suffix(String suffix)  //指定保存日志的文件后缀
```
```
defaultTag(String tag) //指定保存到默认日志文件model
```
```
dirPath(String dirPath); //指定保存文件的目录，格式:"/xxx", 不设置 默认使用当前应用名作为目录
```
```
setNeedSaveToDefaultFile(boolean flag) //设置是否需要保存所有的日志到默认文件
.addSaveRule(String tag, boolean needSave)  //配置保存模块规则，例如有个“PUBLISH”模块，则所有使用这个tag都会保存在同一个文件 //指定tag是否需要保存，如果需要保存，则会保存到指定模块文件，反之，则不保存，也不保存到默认日志文件
```
##示例
![生成日志文件](https://github.com/qylshy/AndroidLogUtil/blob/master/app/image/logfile.png)
![生成的日志内容](https://github.com/qylshy/AndroidLogUtil/blob/master/app/image/logContent.png)

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
- **保证手机有sdcard**
- **打release包记得关闭日志 设置LogLevel最高ASSERT不打印日志**




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

   
   
   
