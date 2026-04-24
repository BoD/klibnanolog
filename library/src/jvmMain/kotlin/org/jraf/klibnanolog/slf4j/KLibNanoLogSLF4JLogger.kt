/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2026-present Benoit 'BoD' Lubek (BoD@JRAF.org)
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

package org.jraf.klibnanolog.slf4j

import org.jraf.klibnanolog.logd
import org.jraf.klibnanolog.logi
import org.jraf.klibnanolog.logw
import org.slf4j.Marker
import org.slf4j.event.Level
import org.slf4j.helpers.AbstractLogger
import org.slf4j.helpers.MessageFormatter

object KLibNanoLogSLF4JLogger : AbstractLogger() {
  override fun readResolve(): Any = KLibNanoLogSLF4JLogger

  override fun getName(): String = "root"

  override fun isTraceEnabled(): Boolean = false

  override fun isTraceEnabled(marker: Marker): Boolean = false

  override fun isDebugEnabled(): Boolean = true

  override fun isDebugEnabled(marker: Marker): Boolean = true

  override fun isInfoEnabled(): Boolean = true

  override fun isInfoEnabled(marker: Marker): Boolean = true

  override fun isWarnEnabled(): Boolean = true

  override fun isWarnEnabled(marker: Marker): Boolean = true

  override fun isErrorEnabled(): Boolean = true

  override fun isErrorEnabled(marker: Marker): Boolean = true

  override fun getFullyQualifiedCallerName(): String? = null

  override fun handleNormalizedLoggingCall(
    level: Level,
    marker: Marker?,
    messagePattern: String?,
    arguments: Array<out Any?>?,
    throwable: Throwable?,
  ) {
    val formattedMessage = MessageFormatter.basicArrayFormat(messagePattern, arguments)
    when (level) {
      Level.TRACE -> {}
      Level.DEBUG -> if (throwable != null) logd(throwable, formattedMessage) else logd(formattedMessage)
      Level.INFO -> if (throwable != null) logi(throwable, formattedMessage) else logi(formattedMessage)
      Level.WARN -> if (throwable != null) logw(throwable, formattedMessage) else logw(formattedMessage)
      Level.ERROR -> if (throwable != null) logw(throwable, formattedMessage) else logw(formattedMessage)
    }
  }
}
