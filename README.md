# play-json-typedid - TypedId Json Format for Play Framework Scala

## About

This Play plugin provided TypedId Format to map json object into TypedId objects. 


    {
        id: "someId",
        someOtherValue: "another Json Property"
    }

    // becomes
    case class TestId1(value: String) extends StringBaseId
    case class Test1(id: TestId1, someOtherValue:String)


Using typedid withing your case classes has a great adavantage when you provide those primary or foreign keys to other service classes. i.e.

    // instead of
    trait AService {
        def executeSomething(id:String): Future[Boolean]
    }

    // you could write
    trait AService {
        def executeSomething(id: TestId1): Future[Boolean]
    }

## Installation

Add the following to `build.sbt`:

    resolvers += "Tegonal releases" at "https://github.com/tegonal/tegonal-mvn/raw/master/releases/"

    libraryDependencies += "com.tegonal" %% "play-json-typedid" % "1.0.4"

## Usage

Your id classes must extends from a provided BaseId trait

    case class TestId1(value: String) extends StringBaseId
    case class TestId2(value: BigDecimal) extends NumberBaseId

For every id class provide an appropriate format

    //provide formats for id classes
    implicit val testId1Format: Format[TestId1] = Json.idformat[TestId1](TestId1.apply _)
    implicit val testId2Format: Format[TestId2] = Json.idformat[TestId2](TestId2.apply _)

After this, id classes can be used within automatically mapped case classes as described in `https://www.playframework.com/documentation/2.3.x/ScalaJsonCombinators`

    case class Test3(id: TestId1, foreigId: TestId2)
    implicit val test3Format: Format[Test3] = Json.format[Test3]

## License

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>

