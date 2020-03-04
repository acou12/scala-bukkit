package com.main.command

trait CommandUtil {
  def require(req: Boolean, exception: CommandException = CommandException.InvalidArguments): Unit = {
    if (!req)
      throw exception
  }
}
