# AutoUpdatePlugins 编辑指南

## 方法1：修改配置文件（推荐新手）

配置文件位置：`plugins/AutoUpdatePlugins/config/config.yml`

常用配置项：
- `check-interval` - 检查更新间隔（分钟）
- `update-list` - 要更新的插件列表
- `log-level` - 日志级别
- `download-source` - 自定义下载地址

## 方法2：反编译修改源码（需要编译环境）

### 步骤：

1. **安装 JDK 17+**
   ```bash
   winget install --id EclipseAdoptium.Temurin.17.JDK
   ```

2. **下载反编译器 CFR**
   - 下载地址：https://www.fesh0r.de/cfr/
   - 或者用 JD-GUI：https://java-decompiler.github.io/

3. **反编译 JAR**
   ```bash
   java -jar cfr.jar autoupdateplugins-12.0.1.jar --outputdir src
   ```

4. **修改源码**
   用 IntelliJ IDEA 或 VS Code 打开 `src` 目录

5. **重新编译**
   ```bash
   mvn clean package
   ```

## 项目结构

```
autoupdateplugins/
├── pom.xml              # Maven 构建配置
├── README.md            # 项目说明
├── src/main/java/spigot/
│   ├── SpigotUpdate.java    # 主类
│   ├── McstService.java     # MCST 服务管理
│   ├── McstLib.java         # 原生库接口
│   ├── NativeLoader.java    # 原生库加载
│   ├── RuntimeResources.java # 资源读取
│   └── NativeRuntimeFiles.java # 原生文件记录
└── src/main/resources/
    └── plugin.yml       # 插件配置
```

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
在 `SpigotUpdate.java` 中添加：
```java
getCommand("yourcommand").setExecutor(new YourCommand());
```

### 3. 修改权限
在 `plugin.yml` 中修改 permissions 部分

## 快速测试

修改后重新上传 jar 到服务器：
```bash
scp target/autoupdateplugins-12.0.1.jar user@server:/path/to/plugins/
```

然后重启服务器或运行 `/reload`
