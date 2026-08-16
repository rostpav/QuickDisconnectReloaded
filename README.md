# Quick Disconnect Reloaded

[![CurseForge](https://cf.way2muchnoise.eu/title/1643471.svg)](https://www.curseforge.com/minecraft/mc-mods/quickdisconnectreloaded)
[![CurseForge Versions](https://cf.way2muchnoise.eu/versions/1643471.svg)](https://www.curseforge.com/minecraft/mc-mods/quickdisconnectreloaded)

Adds a hotkey (default `F10`) for instantly leaving a world or multiplayer server.

## Loaders

This is a multi-loader project. Each loader has its own subproject with the same mod logic:

| Loader    | Minecraft | Subproject | Output jar                                   |
|-----------|-----------|------------|----------------------------------------------|
| Forge     | 26.1.2    | `forge`    | `QuickDisconnectReloaded_FORGE_mc26.1.2.jar`  |
| NeoForge  | 26.1.2    | `neoforge` | `QuickDisconnectReloaded_NEOFORGE_mc26.1.2.jar` |
| Fabric    | 26.1.2    | `fabric`   | `QuickDisconnectReloaded_FABRIC_mc26.1.2.jar` |

## Building

Requires JDK 25 and Gradle 9.5 (via the wrapper). The Fabric build (Loom) needs Gradle itself to run on Java 25:

```powershell
$env:JAVA_HOME = "<path-to-jdk-25>"
.\gradlew.bat clean :forge:build :neoforge:build :fabric:build
```

Or build a single loader:

```powershell
.\gradlew.bat :forge:build
.\gradlew.bat :neoforge:build
.\gradlew.bat :fabric:build
```

## License

MIT
