package com.ayden.main.chat

import java.util.UUID

import org.bukkit.command.{Command, CommandExecutor, CommandSender}
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.{Bukkit, ChatColor}

import scala.collection.mutable

object ChatHandler extends CommandExecutor with Listener {

  val prefixMap: mutable.Map[UUID, String] = mutable.Map()

  @EventHandler
  def onPlayerChat(event: AsyncPlayerChatEvent): Unit = {
    import ChatColor._
    import ChatUtil._
    val player = event.getPlayer
    val prefix = {
      if (prefixMap.contains(player.getUniqueId))
        prefixMap(player.getUniqueId)
      else
        GRAY.toString
      }.colorCode

    event.setCancelled(true)
    import scala.jdk.CollectionConverters._
    val onlinePlayers: java.util.Collection[_ <: Player] = Bukkit.getServer.getOnlinePlayers
    val onlinePlayersIterable = onlinePlayers.asScala
    onlinePlayersIterable foreach { p =>
      p.sendMessage(s"$prefix ${p.getDisplayName}$GRAY: $WHITE${event.getMessage}")
    }
  }

  override def onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array[String]): Boolean = {
    val player = commandSender match {
      case p: Player => p
      case _ => throw new Exception()
    }
    if (strings.length < 1)
      throw new Exception()
    val prefix = strings(0)
    prefixMap(player.getUniqueId) = prefix
    true
  }
}
