# 建模工具

使用java和freemarker语法生成各类代码和文档。

## 如何使用

在Java项目中增加dependency依赖
``` xml
<dependency>
    <groupId>ltd.fdsa</groupId>
    <artifactId>code-egg</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```
并增加 build plugin
``` xml
<plugin>
    <groupId>ltd.fdsa</groupId>
    <artifactId>code-egg-maven-plugin</artifactId>
    <version>1.0.0</version>
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

## 模型定义

在Java工程中使用java pojo定义实体entities（@Table）和关系relations（@Relation）。

## 配置定义

在脚本的settings中可以自定义模型的名称，作者、版本、时间以及数据类型映射等。

## 模板定义

在脚本的templates中可以定义输入模板。

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

## 发布到中央库的方法

```shell
gpg --gen-key
gpg --list-keys
gpg --keyserver hkp://keyserver.ubuntu.com:11371 --send-keys %pub_key%

mvn versions:set -DnewVersion=1.0.0
mvn clean deploy -DskipTests -Dmaven.test.skip=true -Possrh  -Dgpg.skip
mvn versions:revert
rem mvn versions:commit
```