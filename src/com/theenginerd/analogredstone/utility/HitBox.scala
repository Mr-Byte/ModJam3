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

package analogredstone.utility

class HitBox(minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double)
{
    def isPointInside(x: Double, y: Double, z: Double): Boolean =
    {
        (x >= minX && x <= maxX) &&
        (y >= minY && y <= maxY) &&
        (z >= minZ && z <= maxZ)
    }
}

object HitBox
{
    def apply(minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double) = new HitBox(minX, minY, minZ, maxX, maxY, maxZ)
}
