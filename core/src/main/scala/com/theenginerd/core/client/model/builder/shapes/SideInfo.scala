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

package com.theenginerd.core.client.model.builder.shapes

abstract sealed class TextureCoordinates
{
    def getOrElse(default: => (Int, Int, Int, Int)): (Int, Int, Int, Int) =
    {
        this match
        {
            case TextureRectangle(left, bottom, width, height) => (left, bottom, width, height)
            case TextureDefault => default
        }
    }
}

case class TextureRectangle(left: Int, bottom: Int, width: Int, height: Int) extends TextureCoordinates
case object TextureDefault extends TextureCoordinates

case class SideInfo(textureCoordinates: TextureCoordinates, faceGroupName: Option[String])
