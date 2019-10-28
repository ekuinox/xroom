import Webpack._
import play.sbt.PlayImport.PlayKeys.playRunHooks

name := """xroom"""
organization := "dev.ekuinox"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "dev.ekuinox.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "dev.ekuinox.binders._"


playRunHooks += baseDirectory.map(base => Webpack(base)).value
