# Quick Disconnect Reloaded

Adds a hotkey (default `F10`) for instantly leaving a world or multiplayer server.

## Loaders

This is a multi-loader project. Each loader has its own subproject with the same mod logic:

| Loader    | Minecraft | Subproject | Output jar                                   |
|-----------|-----------|------------|----------------------------------------------|
| Forge     | 1.21.1    | `forge`    | `QuickDisconnectReloaded_FORGE_mc1.21.1.jar` |
| NeoForge  | 1.21.1    | `neoforge` | `QuickDisconnectReloaded_NEOFORGE_mc1.21.1.jar` |
| Fabric    | 1.21.1    | `fabric`   | `QuickDisconnectReloaded_FABRIC_mc1.21.1.jar` |

## Building

Requires JDK 21. The Fabric build (Loom) needs Gradle itself to run on Java 21:

```powershell
$env:JAVA_HOME = "<path-to-jdk-21>"
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
