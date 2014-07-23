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

package com.theenginerd.randomredstone.common.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.client.renderer.texture.IIconRegister
import com.theenginerd.randomredstone.MOD_ID

object TestBlock extends Block(Material.wood)
{
    setCreativeTab(CreativeTabs.tabMisc)

    override def registerBlockIcons(register: IIconRegister) =
    {
        blockIcon = register.registerIcon(s"$MOD_ID:uv_test")
    }

    override def isOpaqueCube = false
}