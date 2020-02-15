package com.ayden.main

import com.ayden.main.chat.ChatHandler
import com.ayden.main.command.KitCommand
import org.bukkit.plugin.java.JavaPlugin

class MyMainClass extends JavaPlugin {
  override def onEnable(): Unit = {
    println("Beginning plugin setup...")
    MyMainClass.instance = this
    println("Registering commands...")
    getCommand("kit").setExecutor(KitCommand)
    getCommand("prefix").setExecutor(ChatHandler)
    getServer.getPluginManager.registerEvents(ChatHandler, this)
    println("Commands registered.")
    println("Setup complete.")
  }

  override def onDisable(): Unit = {
    println("Beginning disable...")
    println("Disable complete.")
  }
}

object MyMainClass {
  var instance: MyMainClass = _
}
