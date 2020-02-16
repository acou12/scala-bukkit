package com.ayden.main

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, DataInputStream, InputStream}
import java.util

import com.ayden.main.chat.ChatHandler
import com.ayden.main.command.CommandManager
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener

class MyMainClass extends JavaPlugin with PluginMessageListener {
  override def onEnable(): Unit = {
    println("Beginning plugin setup...")
    MyMainClass.instance = this
    println("Registering commands...")
    CommandManager.registerCommands()
    getServer.getPluginManager.registerEvents(ChatHandler, this)
    getServer.getMessenger.registerOutgoingPluginChannel(this, "BungeeCord")
    println("Commands registered.")
    println("Setup complete.")
  }

  override def onDisable(): Unit = {
    println("Beginning disable...")
    println("Disable complete.")
  }

  override def onPluginMessageReceived(s: String, player: Player, bytes: Array[Byte]): Unit = {
  }
}

object MyMainClass {
  var instance: MyMainClass = _
  def getOnlinePlayers: Iterator[Player] = {
    import scala.jdk.CollectionConverters._
    (instance.getServer.getOnlinePlayers: util.Collection[_ <: Player]).asScala.iterator
  }
}
