/*
 * Copyright 2014 Joshua R. Rodgers
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

package com.theenginerd.analogredstone.tileentity

import net.minecraft.nbt.NBTTagCompound
import com.theenginerd.analogredstone.network.synchronization._
import com.theenginerd.analogredstone.network.PacketHandler

class VariableSwitchTileEntity extends SynchronizedTile
{
    final val IS_ACTIVE_FIELD: String = "isActive"
    final val POWER_OUTPUT_FIELD: String = "powerOutput"

    val powerOutput: BytePropertyCell = BytePropertyCell(value = 0)
    var isActive: BooleanPropertyCell = BooleanPropertyCell(value = false)

    override def getDescriptionPacket =
        PacketHandler.convertMessageToPacket(buildSynchronizedMessage(Array(powerOutput, isActive)))

    def toggleActive() =
        synchronized(isActive)
        {
            isActive := !(~isActive)
        }

    def raisePower() =
        synchronized(powerOutput)
        {
            powerOutput := ((~powerOutput + 1) % 16).toByte
        }

    override def writeToNBT(tag: NBTTagCompound)
    {
        super.writeToNBT(tag)

        tag.setByte(POWER_OUTPUT_FIELD, ~powerOutput)
        tag.setBoolean(IS_ACTIVE_FIELD, ~isActive)
    }
    
    override def readFromNBT(tag: NBTTagCompound)
    {
        super.readFromNBT(tag)

        powerOutput := tag.getByte(POWER_OUTPUT_FIELD)
        isActive := tag.getBoolean(IS_ACTIVE_FIELD)
    }
}
