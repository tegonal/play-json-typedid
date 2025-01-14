/* ____ *\
 * / /____ ___ ____ ___ ___ _/ / generic-id *
 * / __/ -_) _ `/ _ \/ _ \/ _ `/ / contributed by tegonal *
 * \__/\__/\_, /\___/_//_/\_,_/_/ http://tegonal.com/ *
 * /___/ *
 * *
 * This program is free software: you can redistribute it and/or modify it *
 * under the terms of the GNU Lesser General Public License as published by *
 * the Free Software Foundation, either version 3 of the License, *
 * or (at your option) any later version. *
 * *
 * This program is distributed in the hope that it will be useful, but *
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY *
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for *
 * more details. *
 * *
 * You should have received a copy of the GNU General Public License along *
 * with this program. If not, see http://www.gnu.org/licenses/ *
 * *
\* */
package com.tegonal.play.json

import play.api.libs.json._

import scala.annotation.unused
import scala.language.implicitConversions

object TypedId {

  // implicit convertion to extended json object
  implicit def fromJson(@unused json: Json.type): TypedId.type = TypedId

  // extended format function
  def idformat[I <: StringBaseId](implicit fact: Factory[String, I]) =
    new StringTypedIdFormat[I]
  def idformat[I <: NumberBaseId](implicit fact: Factory[BigDecimal, I]) =
    new NumberTypedIdFormat[I]

  trait BaseId[V] {
    val value: V
  }
  // Try to read any write generic id objects from json
  private type Factory[V, I <: BaseId[V]] = V => I

  trait StringBaseId extends BaseId[String]
  trait NumberBaseId extends BaseId[BigDecimal]

  @unused
  case class StringId[T](value: String) extends BaseId[String]
  @unused
  case class NumberId[T](value: BigDecimal) extends BaseId[BigDecimal]

  class StringTypedIdFormat[I <: BaseId[String]](implicit
      fact: Factory[String, I])
      extends Format[I] {
    def reads(json: JsValue): JsResult[I] = json match {

      case JsString(value) => JsSuccess(fact(value))
      case _               => JsError(s"Unexpected JSON value $json")
    }

    def writes(id: I): JsValue = JsString(id.value)
  }
  class NumberTypedIdFormat[I <: BaseId[BigDecimal]](implicit
      fact: Factory[BigDecimal, I])
      extends Format[I] {
    def reads(json: JsValue): JsResult[I] = json match {
      case JsNumber(value) => JsSuccess(fact(value))
      case _               => JsError(s"Unexpected JSON value $json")
    }

    def writes(id: I): JsValue = JsNumber(id.value)
  }
}
