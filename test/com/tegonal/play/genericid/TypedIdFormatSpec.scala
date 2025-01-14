/* __ __ *\
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
package com.tegonal.play.genericid

import com.tegonal.play.json.TypedId._
import org.scalatestplus.play.PlaySpec
import play.api.libs.json._

class TypedIdFormatSpec extends PlaySpec {
  // Provide some helper classes
  case class TestId1(value: String)     extends StringBaseId
  case class TestId2(value: BigDecimal) extends NumberBaseId

  // provide formats for id classes
  object TestId1 {
    implicit val testId1Format: Format[TestId1] =
      Json.idformat[TestId1](TestId1.apply _)
  }
  object TestId2 {
    implicit val testId2Format: Format[TestId2] =
      Json.idformat[TestId2](TestId2.apply _)
  }

  case class Test1(id: TestId1)
  case class Test2(id: TestId2)
  case class Test3(id: TestId1, foreignId: TestId2)

  // provide formats for container classes in companion objects
  object Test1 {
    implicit val test1Format: Format[Test1] = Json.format[Test1]
  }

  object Test2 {
    implicit val test2Format: Format[Test2] = Json.format[Test2]
  }
  object Test3 {
    implicit val test3Format: Format[Test3] = Json.format[Test3]
  }

  // test data
  val test1: JsValue = Json.parse("""
    {
      "id":"111"
    }
   	""")
  val test2: JsValue = Json.parse("""
    {
      "id":222
    }
   	""")
  val test3: JsValue = Json.parse("""
    {
      "id":"333",
	  "foreignId":123
    }
   	""")

  "Parsing generic id object" should {

    "Test1 will be parsed correctly" in {
      val test = test1.as[Test1]
      test.id === TestId1("111")
    }

    "Test2 will be parsed correctly" in {
      val test = test2.as[Test2]
      test.id === TestId2(222)
    }

    "Test3 with foreign key will be parsed correctly" in {
      val test = test3.as[Test3]
      test.id === TestId1("333")
      test.foreignId == TestId2(123)
    }
  }

  "Writing correct json" should {

    "Test1 will be stored correctly" in {
      val res = Json.toJson(Test1(TestId1("111")))
      res \ "id" === JsString("111")
    }

    "Test2 will be stored correctly" in {
      val res = Json.toJson(Test2(TestId2(222)))
      res \ "id" === JsNumber(222)
    }

    "Test3 with foreign key will be stored correctly" in {
      val res = Json.toJson(Test3(TestId1("333"), TestId2(123)))
      res \ "id" === JsString("333")
      res \ "foreignId" === JsNumber(123)
    }
  }
}
