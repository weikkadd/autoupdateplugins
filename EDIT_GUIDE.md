# AutoUpdatePlugins 编辑指南

本文档介绍如何修改和自定义 AutoUpdatePlugins 插件。

---

## 方法一：修改配置文件（推荐新手）

配置文件位于服务器目录：
```
plugins/AutoUpdatePlugins/config/config.yml
```

常用配置项：
- `check-interval` - 检查更新的间隔时间（分钟）
- `update-list` - 需要更新的插件列表
- `log-level` - 日志级别（INFO, WARNING, SEVERE）
- `download-source` - 自定义下载地址

**优点**：无需重新编译，修改后重启服务器即可生效。

---

## 方法二：修改 Java 源码（高级用户）

### 第一步：安装 JDK 17+

```bash
winget install --id EclipseAdoptium.Temurin.17.JDK
```

### 第二步：克隆项目

```bash
git clone https://github.com/weikkadd/autoupdateplugins.git
cd autoupdateplugins
```

### 第三步：反编译（如需查看原始逻辑）

如果原始 JAR 中有未公开的逻辑，可以使用反编译器：
```bash
java -jar cfr.jar autoupdateplugins-12.0.1.jar --outputdir decompiled
```

### 第四步：修改源码

使用 IntelliJ IDEA 或 VS Code 打开项目，修改 `src/main/java/spigot/` 下的代码。

### 第五步：重新编译

```bash
mvn clean package
```

### 第六步：上传到新 JAR 文件

```bash
scp target/autoupdateplugins-12.0.1.jar user@服务器地址:/path/to/plugins/
```

### 第七步：重启服务器

```
/reload
```
或完全重启服务器。

---

## 项目目录结构

```
autoupdateplugins/
├── pom.xml                      # Maven 构建配置文件
├── README.md                    # 项目说明文档
├── EDIT_GUIDE.md               # 编辑指南（本文件）
├── config-example.yml          # 配置文件示例
├── .gitignore                  # Git 忽略规则
└── src/main/java/spigot/
    ├── SpigotUpdate.java       # 主类（插件启动/停止逻辑）
    ├── McstService.java        # MCST 服务管理器
    ├── McstLib.java            # 原生库接口定义
    ├── NativeLoader.java       # 原生库加载器
    ├── RuntimeResources.java   # 资源读取工具类
    └── NativeRuntimeFiles.java # 原生文件记录（Java 16+ Record）
└── src/main/resources/
    └── plugin.yml              # 插件配置文件
```

---

## 常见修改示例

### 1. 修改版本号

编辑 `pom.xml`：
```xml
<version>12.0.2</version>
```

编辑 `src/main/resources/plugin.yml`：
```yaml
version: '12.0.2'
```

### 2. 添加新命令

在 `SpigotUpdate.java` 的 `onEnable()` 方法中添加：
```java
getCommand("你的命令").setExecutor(new 你的命令处理器());
```

### 3. 修改权限名称

在 `plugin.yml` 中修改 permissions 部分：
```yaml
permissions:
  autoupdateplugins.yourpermission:
    description: "描述信息"
    default: op
```

### 4. 修改检查间隔

如果需要直接修改默认值，可以在代码中修改常量。

---

## 快速测试

1. 修改源码后编译
2. 将 JAR 文件复制到服务器的 `plugins/` 目录
3. 重启服务器或执行 `/reload`
4. 运行 `/update` 测试功能

---

## 注意事项

⚠️ **重要提示**：本项目是从原始 JAR 文件反编译重构的，部分功能可能是简化版本。
如需完整源码，请联系原作者 NewAmazingPVP。

## 技术支持

遇到问题？请在 GitHub 上提交 Issue：
https://github.com/weikkadd/autoupdateplugins/issues
