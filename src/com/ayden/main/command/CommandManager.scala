package com.ayden.main.command

import com.ayden.main.MyMainClass
import com.ayden.main.chat.SmiteCommand
import com.ayden.main.command.commands.HelloWorldCommand
import org.bukkit.{Bukkit, Server}
import org.bukkit.command.{CommandExecutor, CommandSender}
import org.bukkit.plugin.PluginManager

object CommandManager extends CommandExecutor {

  val commands: List[Command] = List(
    HelloWorldCommand,
    KitCommand,
    SmiteCommand
  )

  val commandMap: Map[String, Command] = Map from (commands map { c => (c.name, c) })

  def registerCommands(): Unit = {
    commandMap foreach { case (name, _) =>
      MyMainClass.instance.getCommand(name).setExecutor(this)
    }
  }

  override def onCommand(commandSender: CommandSender, command: org.bukkit.command.Command, s: String, strings: Array[String]): Boolean = {
    import org.bukkit.ChatColor._
    val name = command.getName
    if (!commandMap.contains(name)) {
      commandSender.sendMessage(s"$RED[ERROR]$GRAY Command not yet implemented.")
      throw new Exception(s"WARNING: $name is not defined in com.ayden.main.command.CommandManager.")
    }
    val theCommand = commandMap(name)
    try {
      theCommand.run(commandSender, strings)
    } catch {
      case e: Exception => commandSender.sendMessage(s"$RED[ERROR]$GRAY Command execution failed: ${e.getMessage}")
    }
    true
  }
}
