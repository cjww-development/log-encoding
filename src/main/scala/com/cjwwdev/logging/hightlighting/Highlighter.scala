/*
 * Copyright 2019 CJWW Development
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

package com.cjwwdev.logging.hightlighting

import scala.Console._
import java.util.Locale.ENGLISH

object Highlighter {

  private lazy val ansiIsSupported: Boolean = {
    sys
      .props
      .get("sbt.log.noformat")
      .map(_ != "true")
      .orElse {
        sys
          .props
          .get("os.name")
          .map(_.toLowerCase(ENGLISH))
          .filter(_.contains("windows"))
          .map(_ => false)
      }
      .getOrElse(true)
  }

  val red: String => String = str => if(ansiIsSupported) s"$RED$str$RESET" else str
  val blue: String => String = str => if(ansiIsSupported) s"$BLUE$str$RESET" else str
  val cyan: String => String = str => if(ansiIsSupported) s"$CYAN$str$RESET" else str
  val green: String => String = str => if(ansiIsSupported) s"$GREEN$str$RESET" else str
  val magenta: String => String = str => if(ansiIsSupported) s"$MAGENTA$str$RESET" else str
  val white: String => String = str => if(ansiIsSupported) s"$WHITE$str$RESET" else str
  val black: String => String = str => if(ansiIsSupported) s"$BLACK$str$RESET" else str
  val yellow: String => String = str => if(ansiIsSupported) s"$YELLOW$str$RESET" else str
}
