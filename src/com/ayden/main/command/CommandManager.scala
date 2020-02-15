package com.ayden.main.command

import com.ayden.main.command.commands.HelloWorldCommand
import org.bukkit.command.{CommandExecutor, CommandSender}

object CommandManager extends CommandExecutor {

  val commands: List[Command] = List(
    HelloWorldCommand
  )

  val commandMap: Map[String, Command] = Map from (commands map { c => (c.name, c) })

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
