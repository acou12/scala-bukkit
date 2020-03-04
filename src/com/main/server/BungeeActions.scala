package com.main.server

import java.io.{ByteArrayOutputStream, DataOutputStream}

import com.main.MyMainClass
import org.bukkit.entity.Player

object BungeeActions {
  val connectByString: (String, String) => Unit = connectOther

  def connect(player: Player, server: String): Unit = {
    sendPluginMessage(player,
      "Connect", server
    )
  }

  def connectOther(player: String, server: String): Unit = {
    sendPluginMessage(anyPlayer(), "ConnectOther", player, server)
  }

  def ip(player: Player): Unit = {
    sendPluginMessage(player, "IP")
  }

  def playerCount(server: String): Unit = {
    sendPluginMessage(anyPlayer(), "PlayerCount", server)
  }

  def playerList(server: String): Unit = {
    sendPluginMessage(anyPlayer(), "PlayerList", server)
  }

  def sendPluginMessage(player: Player, strings: String*): Unit = {
    val byteStream = new ByteArrayOutputStream()
    val dataStream = new DataOutputStream(byteStream)
    strings foreach dataStream.writeUTF
    dataStream.flush()
    player.sendPluginMessage(MyMainClass.instance, "BungeeCord", byteStream.toByteArray)
  }

  def anyPlayer(): Player = MyMainClass.getOnlinePlayers.next()
}
