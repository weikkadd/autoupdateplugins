# AutoUpdatePlugins

A Spigot/Paper plugin that automatically updates Minecraft server plugins.

## Features

- Automatically checks for plugin updates
- Downloads and installs updated versions
- Supports Folia API
- Minimal resource footprint

## Installation

1. Download the latest `autoupdateplugins-{version}.jar`
2. Place it in your server's `plugins/` folder
3. Restart your server

## Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/update` | Update all plugins in list.yml | `autoupdateplugins.update` |
| `/aup` | Manage AutoUpdatePlugins | `autoupdateplugins.manage` |

## Permissions

- `autoupdateplugins.update` - Allows updating plugins
- `autoupdateplugins.manage` - Allows managing plugin list

## Building from Source

```bash
mvn clean package
```

The compiled JAR will be in `target/autoupdateplugins-{version}.jar`

## Requirements

- Java 17+
- Spigot/Paper 1.18+
- Folia support enabled

## License

This project is licensed under the MIT License.

## Links

- [GitHub Repository](https://github.com/weikkadd/autoupdateplugins)
- [Issues](https://github.com/weikkadd/autoupdateplugins/issues)
