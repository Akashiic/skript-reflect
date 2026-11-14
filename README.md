# skript-reflect para Minecraft 1.7.10 (Crucible) no Java 21

Este branch é o skript-reflect **2.3**, a última versão compatível com o Skript 2.6, adaptado para o
[Skript 1.7.10](https://github.com/Akashiic/Skript) (Crucible, Thermos, KCauldron) no **Java 21**.

Mudanças:
* Compila contra a API Bukkit 1.7.10 e o jar do Skript 1.7.10.
* bStats/Metrics removido (não funciona na 1.7.10).
* Erro de carga explica quando um membro do JDK existe mas o Java 9+ não deixa acessá-lo (falta `--add-opens`).
* Build com Gradle 8.14 (roda no Java 21), bytecode Java 8.

## Compilar

Compile primeiro o fork do Skript ao lado deste repositório (`../Skript`), depois:

```
./gradlew build
```

Para usar outro jar do Skript: `./gradlew build -PskriptJar=/caminho/para/Skript.jar`.
O jar fica em `build/libs/skript-reflect.jar`.

## Reflexão em servidores Forge (Crucible)

Testado num Crucible 1.7.10 real (Java 21) com Et Futurum Requiem, Netherlicious, NecroTempus e Kunftig: os scripts de
teste estão em `tools/crucible-test/scripts/reflect*.sk` do fork do Skript.

* Classes do Bukkit, do CraftBukkit (`org.bukkit.craftbukkit.v1_7_R4...`), do Forge/FML (`cpw.mods.fml...`,
  `net.minecraftforge...`), de mods (`ganymedes01.etfuturum...`) e a API do Kunftig podem ser importadas e chamadas.
* Classes do Minecraft: os nomes do Forge (`net.minecraft.entity.player.EntityPlayerMP`) funcionam em `import`; com
  `the class "..."` o Crucible também traduz os nomes estilo Bukkit (`net.minecraft.server.v1_7_R4.EntityPlayer`).
* Métodos e campos do Minecraft usam os nomes SRG em tempo de execução: `MinecraftServer.func_71276_C()`,
  `player.getHandle().func_70005_c_()`, `Item.field_150901_e`. Nomes MCP (`getServer()`) dão erro ao carregar o script.
* Campos e métodos privados do JDK funcionam nos pacotes que o Crucible abre (`java9args.txt`: `java.lang`,
  `java.util`, `java.io`...). Em outros pacotes (ex.: `java.util.concurrent`) o erro de carga diz qual `--add-opens`
  adicionar aos argumentos do Java.
* `run section ... async ... and wait` volta para a thread principal no tick seguinte (cerca de 50 ms por chamada).
* Classes precisam de `import` (ou `the class "..."`): `new java.util.ArrayList()` com o nome completo não funciona.

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
