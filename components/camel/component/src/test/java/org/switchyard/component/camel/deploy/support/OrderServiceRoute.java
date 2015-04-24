/*
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.switchyard.component.camel.deploy.support;

import org.apache.camel.builder.RouteBuilder;
import org.switchyard.component.camel.Route;

/**
 *  Java DSL route equivalent to:
 *  <route xmlns="http://camel.apache.org/schema/spring" id="Camel Test Route">
 *   <log message="ItemId [${body}]"/>
 *  <to uri="switchyard://WarehouseService?operationName=hasItem"/>
 *  <log message="Title Name [${body}]"/>
 *  </route>
 */
@Route(OrderService.class)
public class OrderServiceRoute extends RouteBuilder {

    public void configure() {
        from("switchyard://OrderService")
            .log("ItemId [${body}]")
            .to("switchyard://WarehouseService?operationName=hasItem")
            .log("Title Name [${body}]");
    }

}