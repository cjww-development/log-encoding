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

import com.typesafe.config.ConfigFactory
import scala.util.{Try, Success, Failure}

val libraryName = "log-encoding"

val btVersion: String = Try(ConfigFactory.load.getString("version")) match {
  case Success(ver) => ver
  case Failure(_)   => "0.1.0"
}

val dependencies: Seq[ModuleID] = Seq(
  "ch.qos.logback"             % "logback-core"     % "1.2.3",
  "ch.qos.logback"             % "logback-classic"  % "1.2.3",
  "com.typesafe"               % "config"           % "1.3.4",
  "commons-io"                 % "commons-io"       % "2.6",
  "org.apache.commons"         % "commons-lang3"    % "3.9",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.9.3",
  "com.fasterxml.jackson.core" % "jackson-core"     % "2.9.9"
)

lazy val library = Project(libraryName, file("."))
  .settings(
    version                              :=  btVersion,
    scalaVersion                         :=  "2.13.0",
    organization                         :=  "com.cjww-dev.libs",
    resolvers                            +=  "cjww-dev" at "http://dl.bintray.com/cjww-development/releases",
    libraryDependencies                  ++= dependencies,
    bintrayOrganization                  :=  Some("cjww-development"),
    bintrayRepository                    :=  "releases",
    bintrayOmitLicense                   :=  true,
    bintrayReleaseOnPublish in ThisBuild :=  true,
    fork                    in Test      :=  true
  )