package com.main.command

case class CommandException(message: String, showUsage: Boolean = true) extends Exception

object CommandException {
  val TooManyArguments: CommandException = CommandException("Too many arguments were supplied.")
  val TooFewArguments: CommandException = CommandException("Too few arguments were supplied.")
  val MustBePlayer: CommandException = CommandException("You must be a player to run this command.", showUsage = false)
  val InvalidArguments: CommandException = CommandException("Invalid arguments.")

  def PlayerNotFound(player: String): CommandException = CommandException(s"Player '$player' not found.")
}
