# AutoUpdatePlugins

一个用于 Spigot/Paper 服务器的自动更新插件，可自动检查和下载插件更新。

## 功能特性

- ✅ 自动检查插件更新
- ✅ 下载并安装最新版本
- ✅ 支持 Folia API
- ✅ 资源占用极低
- ✅ 简单的命令管理

## 安装方法

1. 下载最新的 `autoupdateplugins-{版本号}.jar`
2. 放入服务器的 `plugins/` 文件夹
3. 重启服务器

## 插件命令

| 命令 | 说明 | 权限 |
|------|------|------|
| `/update` | 更新 list.yml 中的所有插件 | `autoupdateplugins.update` |
| `/aup` | 管理 AutoUpdatePlugins 设置 | `autoupdateplugins.manage` |

## 权限设置

- `autoupdateplugins.update` - 允许更新插件
- `autoupdateplugins.manage` - 允许管理插件列表

## 从源码构建

```bash
mvn clean package
```

编译后的 JAR 文件将位于 `target/autoupdateplugins-{版本号}.jar`

## 环境要求

- Java 17 或更高版本
- Spigot/Paper 1.18+
- 支持 Folia（可选）

## 许可证

本项目采用 MIT 许可证。

## 相关链接

- [GitHub 仓库](https://github.com/weikkadd/autoupdateplugins)
- [问题反馈](https://github.com/weikkadd/autoupdateplugins/issues)
