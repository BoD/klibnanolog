/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2025-present Benoit 'BoD' Lubek (BoD@JRAF.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jraf.klibnanolog

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

enum class LogLevel {
  DEBUG,
  INFO,
  WARNING,
  ERROR,
  NONE,
}


var logLevel = LogLevel.DEBUG

private val DATE_TIME_FORMAT by lazy {
  LocalDateTime.Format {
    @OptIn(FormatStringsInDatetimeFormats::class)
    byUnicodePattern("yyyy-MM-dd HH:mm:ss")
  }
}

private fun log(
  level: LogLevel,
  message: String,
  throwable: Throwable? = null,
) {
  if (level < logLevel) return
  print(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).format(DATE_TIME_FORMAT))
  print(
    when (level) {
      LogLevel.DEBUG -> " D "
      LogLevel.INFO -> " I "
      LogLevel.WARNING -> " W "
      LogLevel.ERROR -> " E "
      LogLevel.NONE -> {
        // Should never happen
      }
    },
  )
  println(message)
  if (throwable != null) {
    println(throwable.stackTraceToString())
  }
}

fun logd(s: String) {
  log(LogLevel.DEBUG, s)
}

fun logi(s: String) {
  log(LogLevel.INFO, s)
}

fun logw(t: Throwable, s: String) {
  log(LogLevel.WARNING, s, t)
}

fun logw(s: String) {
  log(LogLevel.WARNING, s)
}

fun loge(s: String) {
  log(LogLevel.ERROR, s)
}

fun loge(t: Throwable, s: String) {
  log(LogLevel.ERROR, s, t)
}
