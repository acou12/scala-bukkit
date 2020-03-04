package com.main.command.commands

import com.ayden.main.command.Command
import com.main.command.{Command, CommandException}
import org.bukkit.command.CommandSender


object HelloWorldCommand extends Command {
  override def name: String = "hello"

  override def run(sender: CommandSender, args: Array[String]): Unit = {
    require(args(0) == "test", CommandException("dude, you can't do that"))
  }
}
