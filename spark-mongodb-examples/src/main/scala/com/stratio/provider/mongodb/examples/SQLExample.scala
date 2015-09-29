/*
 *  Licensed to STRATIO (C) under one or more contributor license agreements.
 *  See the NOTICE file distributed with this work for additional information
 *  regarding copyright ownership. The STRATIO (C) licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package com.stratio.provider.mongodb.examples

import com.stratio.provider.mongodb.examples.MongoExampleFunctions._

object SQLExample extends App with MongoDefaultConstants {

  val mongoClient = prepareEnvironment()

  withSQLContext { sqlContext =>

    sqlContext.sql(
      s"""|CREATE TEMPORARY TABLE $Collection
          |(id STRING, age INT, description STRING, enrolled BOOLEAN, name STRING, optionalField BOOLEAN)
          |USING $MongoProvider
          |OPTIONS (
          |host '$MongoHost:$MongoPort',
          |database '$Database',
          |collection '$Collection'
          |)
       """.stripMargin.replaceAll("\n", " "))

    sqlContext.sql(s"SELECT id, name FROM $Collection WHERE age > 16").show(5)

  }

  cleanEnvironment(mongoClient)
}