# 建模工具

使用java和freemarker语法生成各类代码或文档。

## 如何使用

在Java项目中增加dependency依赖
``` xml
<dependency>
    <groupId>ltd.fdsa</groupId>
    <artifactId>code-egg</artifactId>
    <version>2.1.5-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```
并增加 build plugin
``` xml
<plugin>
    <groupId>ltd.fdsa</groupId>
    <artifactId>code-egg-maven-plugin</artifactId>
    <version>2.1.5-SNAPSHOT</version>
    <executions>
        <execution>
            <goals>
                <goal>package</goal>
            </goals>
            <configuration>
            </configuration>
        </execution>
    </executions>
</plugin>
```
目前只有implements IEntity的实体会被识别到。
## 模型定义

一个工程即一个模型module，使用java pojo定义多个实体entities和关系relations。

## 配置定义

在settings中可以自定义模型的名称，作者、版本、时间以及数据类型映射等。


#  贡献代码

## 如何成为Committer

- 认领并解决2个Issue后可以获得提交者权限
- 提供6个测试用例后可以获得提交者权限
- 帮助解决5个Issue后可以获得管理员权限

## 代码提交规范

1. 认领Issue，Fork 本项目的仓库。
2. 新建分支，如果是加新特性，分支名格式为`feature_${issue_id}`，如果是修改bug，则命名为`bug_${issue_id}`，如果是增强，则命名为`enhancement_${issue_id}`。

4. 代码注释，可以参考之前的注释，新增加代码需要相应的单元测试。

5. 提交代码的Commit Message格式为`#Issue的ID号\nISSUE的描述\n解决的问题描述`。

7. 创建PR，然后提PR到相应的分支。

8. 检验通过的代码会及时随新版发布。