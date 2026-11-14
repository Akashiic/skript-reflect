package com.btk5h.skriptmirror;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.lang.parser.ParserInstance;
import com.btk5h.skriptmirror.util.SkriptReflection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Path;

public class SkriptMirror extends JavaPlugin {
  private static SkriptMirror instance;
  private static SkriptAddon addonInstance;

  public SkriptMirror() {
    if (instance == null) {
      instance = this;
    } else {
      throw new IllegalStateException();
    }
  }

  @Override
  public void onEnable() {
    if (!Bukkit.getPluginManager().isPluginEnabled("Skript")) {
      getLogger().severe("Disabling skript-reflect because Skript is disabled");
      Bukkit.getPluginManager().disablePlugin(this);
      return;
    }

    if (!Skript.classExists("ch.njol.skript.lang.parser.ParserInstance") || !Skript.methodExists(ParserInstance.class, "get")) {
      getLogger().severe("");
      getLogger().severe("Your version of Skript (" + Skript.getVersion() + ") is not supported, at least Skript 2.6 is required to run this version of skript-reflect.");
      getLogger().severe("If you want to use a version of Skript below 2.6, use skript-reflect 2.2.3: https://github.com/TPGamesNL/skript-reflect/releases/tag/v2.2.3");
      getLogger().severe("");
      Bukkit.getPluginManager().disablePlugin(this);
      return;
    }

    if (Bukkit.getPluginManager().getPlugin("skript-mirror") != null) {
      getLogger().warning("You shouldn't have both skript-mirror and skript-reflect enabled, it will probably cause issues");
    }

    saveDefaultConfig();

    try {
      getAddonInstance().loadClasses("com.btk5h.skriptmirror.skript");

      Path dataFolder = SkriptMirror.getInstance().getDataFolder().toPath();
      LibraryLoader.loadLibraries(dataFolder);
    } catch (IOException e) {
      e.printStackTrace();
    }

    ParseOrderWorkarounds.reorderSyntax();

    // Disable *all* and/or warnings
    SkriptReflection.disableAndOrWarnings();
  }

  public static SkriptAddon getAddonInstance() {
    if (addonInstance == null) {
      addonInstance = Skript.registerAddon(getInstance()).setLanguageFileDirectory("lang");
    }
    return addonInstance;
  }

  public static SkriptMirror getInstance() {
    if (instance == null) {
      throw new IllegalStateException();
    }
    return instance;
  }

}
