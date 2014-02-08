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

package com.theenginerd.analogredstone.client.model

import cpw.mods.fml.relauncher.SideOnly
import cpw.mods.fml.relauncher.Side
import net.minecraftforge.client.model.AdvancedModelLoader
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import cpw.mods.fml.client.FMLClientHandler
import com.theenginerd.analogredstone

@SideOnly(Side.CLIENT)
object VariableSwitchModel
{
    private lazy val resource = new ResourceLocation("analogredstone:models/VariableSwitch.obj")
    private lazy val model = AdvancedModelLoader.loadModel(resource)

    private final val SWITCH_TEXTURE = new ResourceLocation(analogredstone.MOD_ID, "textures/blocks/variableswitch.png")
    private final val WOOD_TEXTURE = new ResourceLocation("minecraft:textures/blocks/planks_oak.png")
    private final val COBBLE_TEXTURE = new ResourceLocation("minecraft:textures/blocks/cobblestone.png")

    def render(isActive: Boolean, powerOutput: Byte)
    {
        FMLClientHandler.instance.getClient.renderEngine.bindTexture(COBBLE_TEXTURE)
        model.renderPart("Base")

        FMLClientHandler.instance().getClient.renderEngine.bindTexture(SWITCH_TEXTURE)
        model.renderPart("BaseSlider")

        FMLClientHandler.instance.getClient.renderEngine.bindTexture(WOOD_TEXTURE)
        GL11.glPushMatrix()
        GL11.glTranslatef(0, 0, -0.625f * (powerOutput.toFloat / 15))
        model.renderPart("PowerAdjuster")
        GL11.glPopMatrix()

        GL11.glPushMatrix()

        if (isActive)
            GL11.glRotated(30, 1, 0, 0)
        else
            GL11.glRotated(-30, 1, 0, 0)

        model.renderPart("Switch")

        GL11.glPopMatrix()
    }
}
