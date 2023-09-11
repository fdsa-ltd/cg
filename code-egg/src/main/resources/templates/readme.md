# 模型

## 总体

一个工程（project）视为模型（model），每个模型中包含若干个实体（entity）和实体关系（relation）

## 模型定义module

```java
String name;
String description;
Entity[] entities;
RelationDefine[] relations;
// 输入输出设置
String jarFolder;
String outputFolder;
String templateFolder;
```

## 实体定义entity

```java
String name;     // 默认为java命名
String code;     // 可以设置新命名
String remark;   // 可以设置备注
Field[] fields;
```

## 字段定义

```
String name;     // 默认为java命名
String code;     // 可以设置新命名
String remark;   // 可以设置备注
boolean isNull;
boolean autoIncrement;
String type;
int length;
int scale;
```

## 关系relation

```java
Type name;
Class fromEntity;
String fromField;
Class toEntity;
String toField;

public enum Type {
    One2One,
    One2Many,
    Many2One,
    Many2Many,
}
```

# 模板

## 总体

模板目录可以包括多层级目录和文件，目录和文件名和文件内容一样支持freemarker模板语言。

如果文件路径名中包含relation或entity，文件模板中将引入单个relation和entity信息。文件输出将基于项目中的relations和entities生成对应的多个文件。


## 模板上下文

模板中将包含以下内容：

1. 模型信息module

    - String name
    - String description
    - Entity[]  entities（详细参考entity）
    - Relation[] relations（详细参考relation）
    - String input
    - String output
    - String template
    - Setting setting 

2. 实体信息entity

    -  String name
    -  String code
    -  String remark
    -  Field[] fields
        - String name
        - String code
        - String remark
        - boolean isNull
        - boolean autoIncrement
        - String type
        - int length
        - int scale

3. 关系信息relation

    - String name;
    - String code;
    - String remark;
    - Type type;
       - One2One
       - One2Many
       - Many2One
       - Many2Many
    - Entity source（详细参考entity）
    - String primaryKey;
    - Entity target（详细参考entity）
    - String foreignKey;

4. 配置信息setting

   在setting目录中的yaml或property文件内容将通过
   setting('key') 或  setting('key',default) 访问

5. 模板函数

``` js
snake_case('input')  //蛇式
camel_case('input')  //小驼峰
spinal_case('input') //脊椎式  kebab 烤肉串 
pascal_case('input')  //大驼峰
```

## freemarker

[FreeMarker 中文官方参考手册](http://freemarker.foofun.cn/)