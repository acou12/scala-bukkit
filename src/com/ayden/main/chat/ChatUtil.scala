package com.ayden.main.chat

import org.bukkit.ChatColor

object ChatUtil {

  implicit class StringExtension(s: String) {
    def colorCode: String = ChatColor.translateAlternateColorCodes('&', s)
  }

}
