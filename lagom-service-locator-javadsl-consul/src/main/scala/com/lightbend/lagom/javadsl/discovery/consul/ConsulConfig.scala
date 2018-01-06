package com.lightbend.lagom.javadsl.discovery.consul

import javax.inject.{Inject, Singleton}

import com.typesafe.config.ConfigException.BadValue
import play.api.Configuration

trait ConsulConfig {
  def agentHostname: String
  def agentPort: Int
  def scheme: String
  def routingPolicy: RoutingPolicy
  def serviceName: String
  def serviceId: String
  def serviceAddress: String
  def servicePort: Int
}

object ConsulConfig {

  @Singl
  class ConsulConfigImpl @Inject()(config: Configuration) extends ConsulConfig {
    override val agentHostname = config.get[String]("lagom.discovery.consul.agent-hostname")
    override val agentPort = config.get[Int]("lagom.discovery.consul.agent-port")
    override val scheme = config.get[String]("lagom.discovery.consul.uri-scheme")
    override val routingPolicy = RoutingPolicy(config.get[String]("lagom.discovery.consul.routing-policy"))
    override val serviceName = config.get[String]("lagom.register.serviceName")
    override val serviceId = config.get[String]("lagom.register.serviceId")
    override val serviceAddress = config.get[String]("lagom.register.serviceAddress")
    override val servicePort = config.get[Int]("lagom.register.servicePort")
  }

}

object RoutingPolicy {
  def apply(policy: String): RoutingPolicy = policy match {
    case "first" => First
    case "random" => Random
    case "round-robin" => RoundRobin
    case unknown => throw new BadValue("lagom.discovery.consul.routing-policy", s"[$unknown] is not a valid routing algorithm")
  }
}

sealed trait RoutingPolicy

case object First extends RoutingPolicy

case object Random extends RoutingPolicy

case object RoundRobin extends RoutingPolicy
