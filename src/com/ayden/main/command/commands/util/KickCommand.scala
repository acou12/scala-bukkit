package com.ayden.main.command.commands.util

import com.ayden.main.command
import com.ayden.main.command.Command
import org.bukkit.command.CommandSender

object KickCommand extends Command {
  override def name: String = "kick"

  override def run(sender: CommandSender, args: Array[String]): Unit = {
  }
}
