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

package com.theenginerd.analogredstone.network

import cpw.mods.fml.common.network.{Player, IPacketHandler}
import net.minecraft.network.INetworkManager
import net.minecraft.network.packet.Packet250CustomPayload
import net.minecraft.entity.player.EntityPlayer
import com.theenginerd.analogredstone.network.synchronization.{SynchronizationAction, TileSynchronizationAction, getSynchronizedTile}

class PacketHandler extends IPacketHandler
{
    def onPacketData(manager: INetworkManager, packet: Packet250CustomPayload, player: Player): Unit =
    {
        SynchronizationAction(packet.data) match
        {
            case Some(update) =>
                update match
                {
                    case tileUpdate: TileSynchronizationAction =>
                        val playerEntity = player.asInstanceOf[EntityPlayer]
                        for (synchronizedTile <- getSynchronizedTile(playerEntity.worldObj, tileUpdate.position))
                        {
                            synchronizedTile.processSynchronizationAction(tileUpdate, playerEntity)
                        }

                    case _ =>
                }
            case None => ()
        }
    }
}