/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Licensed to the Apache Software Foundation (ASF) under one
 ~ or more contributor license agreements.  See the NOTICE file
 ~ distributed with this work for additional information
 ~ regarding copyright ownership.  The ASF licenses this file
 ~ to you under the Apache License, Version 2.0 (the
 ~ "License"); you may not use this file except in compliance
 ~ with the License.  You may obtain a copy of the License at
 ~
 ~   http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing,
 ~ software distributed under the License is distributed on an
 ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 ~ KIND, either express or implied.  See the License for the
 ~ specific language governing permissions and limitations
 ~ under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package org.apache.sling.scripting.resolver.internal;

import java.io.StringReader;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.scripting.SlingScriptConstants;

public class PrecompiledScript implements ScriptEngineExecutable {

    private static final StringReader EMPTY_READER = new StringReader(StringUtils.EMPTY);

    private final ScriptEngine scriptEngine;
    private final Object precompiledScript;

    PrecompiledScript(ScriptEngine scriptEngine, Object precompiledScript) {
        this.scriptEngine = scriptEngine;
        this.precompiledScript = precompiledScript;
    }

    @Override
    public String getName() {
        return precompiledScript.getClass().getName();
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    @Override
    public void eval(ScriptContext context) throws ScriptException {
        context.setAttribute("precompiled.unit", precompiledScript, SlingScriptConstants.SLING_SCOPE);
        scriptEngine.eval(EMPTY_READER, context);
    }
}
