/*
 * Copyright 2021 CJWW Development
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

package dev.cjww.logging.converters

import dev.cjww.logging.highlighting.Highlighter._

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.pattern.ClassicConverter
import ch.qos.logback.classic.spi.ILoggingEvent

class HighlightedLogLevelConverter extends ClassicConverter {
  override def convert(event: ILoggingEvent): String = {
    event.getLevel match {
      case level@Level.TRACE => s"[${blue(level.levelStr)}]"
      case level@Level.DEBUG => s"[${cyan(level.levelStr)}]"
      case level@Level.INFO  => s"[${white(level.levelStr)}]"
      case level@Level.WARN  => s"[${yellow(level.levelStr)}]"
      case level@Level.ERROR => s"[${red(level.levelStr)}]"
    }
  }
}
