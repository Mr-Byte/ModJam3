/*
 * Copyright 2013 Joshua R. Rodgers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================================
 */

package com.theenginerd.analogredstone

import cpw.mods.fml.common.registry.{LanguageRegistry, GameRegistry}

package object block
{
    private object blockIds
    {
        var variableSwitchId = 3400
    }

    final lazy val VARIABLE_SWITCH_ID = blockIds.variableSwitchId

    def configureBlockIds(configuration: AnalogRedstoneConfiguration)
    {
        def loadBlockId(propertyName: String, defaultValue: Int) =
        {
            val property = configuration.getBlock(propertyName, defaultValue)
            property.getInt
        }

        blockIds.variableSwitchId = loadBlockId("variableSwitch.id", blockIds.variableSwitchId)
    }

    def registerBlocks()
    {
        GameRegistry.registerBlock(VariableSwitchBlock, "variableSwitch")
        LanguageRegistry.addName(VariableSwitchBlock, "Variable Switch")
    }
}