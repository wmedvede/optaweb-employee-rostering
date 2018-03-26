/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.openshift.employeerostering.server.common.jaxrs;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Provider
public class OptaShiftObjectMapperResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public OptaShiftObjectMapperResolver() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                // This converts the server side offset to GMT, is that what we want?
                .setDateFormat(new ISO8601DateFormat());
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }

}
