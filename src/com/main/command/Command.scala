package com.main.command

import org.bukkit.command.CommandSender

trait Command extends CommandUtil {
  def name: String

  def run(sender: CommandSender, arguments: Array[String])
}
