package com.ayden.main.command

import org.bukkit.command.CommandSender

trait Command {
  def name: String

  def requirements()

  def run(sender: CommandSender, arguments: Array[String])
}