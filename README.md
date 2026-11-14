# skript-reflect para Minecraft 1.7.10 (Crucible) no Java 21

Este branch é o skript-reflect **2.3**, a última versão compatível com o Skript 2.6, adaptado para o
[Skript 1.7.10](https://github.com/Akashiic/Skript) (Crucible, Thermos, KCauldron) no **Java 21**.

Mudanças:
* Compila contra a API Bukkit 1.7.10 e o jar do Skript 1.7.10.
* bStats/Metrics removido (não funciona na 1.7.10).
* Build com Gradle 8.14 (roda no Java 21), bytecode Java 8.

## Compilar

Compile primeiro o fork do Skript ao lado deste repositório (`../Skript`), depois:

```
./gradlew build
```

Para usar outro jar do Skript: `./gradlew build -PskriptJar=/caminho/para/Skript.jar`.
O jar fica em `build/libs/skript-reflect.jar`.

## Reflexão em servidores Forge (Crucible)

* Classes do Bukkit e do CraftBukkit funcionam normalmente (`org.bukkit.craftbukkit.v1_7_R4...`).
* As classes do Minecraft/Forge em tempo de execução usam os nomes do Forge
  (por exemplo `net.minecraft.entity.player.EntityPlayerMP`, não `net.minecraft.server.v1_7_R4.EntityPlayer`),
  e métodos/campos usam os nomes SRG (`func_xxxxx_x`, `field_xxxxx_x`).
  Strings em scripts não passam pelo remapeador de plugins do Crucible, então use esses nomes.

---

# skript-reflect

This fork of [skript-mirror](https://github.com/btk5h/skript-mirror) aims to fix
multiple issues that I believe have been present for too long, and implement some long-wanted features.

Documentation: https://tpgamesnl.gitbook.io/skript-reflect

Source code: https://github.com/TPGamesNL/skript-reflect

Downloads: [releases](https://github.com/TPGamesNL/skript-reflect/releases) or
[actions](https://github.com/TPGamesNL/skript-reflect/actions?query=event%3Apush+is%3Asuccess+actor%3ATPGamesNL) \(not stable\)

Discord: https://discord.gg/jDW8UbD

## What's been changed in this fork:
* Added custom events
* Support for more Java versions \(Java 13+, OpenJ9\)
* Support for listening to asynchronous events
* Class proxy fixes + documentation
* Fixed multiple issues with local variables
* \+ much more
