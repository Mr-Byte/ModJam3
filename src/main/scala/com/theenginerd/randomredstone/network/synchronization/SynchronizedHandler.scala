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

package com.theenginerd.randomredstone.network.synchronization

import cpw.mods.fml.common.FMLLog
import io.netty.channel.{ChannelHandlerContext, SimpleChannelInboundHandler}
import cpw.mods.fml.client.FMLClientHandler
import com.theenginerd.randomredstone.synchronization
import com.theenginerd.randomredstone.network.synchronization.data.{SynchronizedTileMessage, SynchronizedMessage}

object SynchronizedHandler extends SimpleChannelInboundHandler[SynchronizedMessage]()
{
    def channelRead0(context: ChannelHandlerContext, message: SynchronizedMessage) =
    {
        val world = FMLClientHandler.instance().getClient.theWorld

        message match
        {
            case synchronizedTileMessage: SynchronizedTileMessage =>
                for(tile <- synchronization.getSynchronizedTileEntity(world, synchronizedTileMessage.x, synchronizedTileMessage.y, synchronizedTileMessage.z))
                {
                    tile.handleSynchronizationMessage(message)
                }

            case _ =>
                FMLLog severe "Attempted to handle unknown SynchronizedMessage type."
        }
    }
}