package com.ayden.main.chat

import com.ayden.main.command.Command
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

object SmiteCommand extends Command {
  override def name: String = "smite"

  override def run(sender: CommandSender, arguments: Array[String]): Unit = {
    import com.ayden.main.command.CommandImplicits._
    arguments.requireLength(1)
    val target = Bukkit.getPlayer(arguments(0))
    if (target == null) {
      throw new Exception("Player is not online.")
    }
    import org.bukkit.ChatColor._
    sender.sendMessage(s"${RED}Sent lightning to player ${target.getDisplayName}.")
    target.getWorld.strikeLightning(target.getLocation)
  }
}
