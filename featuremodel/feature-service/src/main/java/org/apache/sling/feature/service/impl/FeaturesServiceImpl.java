/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.feature.service.impl;

import org.apache.sling.feature.service.Features;
import org.osgi.framework.Version;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

class FeaturesServiceImpl implements Features {
    private final Map<Map.Entry<String, Version>, Set<String>> bundleFeatureMap;

    FeaturesServiceImpl(Map<Map.Entry<String, Version>, Set<String>> bundleIDFeatures) {
        bundleFeatureMap = Collections.unmodifiableMap(bundleIDFeatures);
    }

    @Override
    public Set<String> getFeaturesForBundle(String bsn, Version version) {
        return bundleFeatureMap.get(new AbstractMap.SimpleEntry<String, Version>(bsn, version));
    }
}
