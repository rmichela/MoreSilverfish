//    Copyright (C) 2012  Ryan Michela
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package com.ryanmichela.moresilverfish;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

/**
 */
public class SilverfishPopulator extends BlockPopulator {

    private MSConfig config;

    public SilverfishPopulator(MSConfig config) {
        this.config = config;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        // 1. Determine if this chunk will get a Silverfish colony
        int existsRoll = random.nextInt(101); // [0..100]
        if (config.getPercentChance() > existsRoll) {

            // 2. Determine the size of the Silverfish colony
            int colonySize = 0;
            for (int i = 0; i < config.getColonySize(); i++) {
                colonySize += (random.nextInt(6) + 1); // [1..6]
            }

            if (config.isDebug()) {
                Bukkit.getLogger().info("Creating Silverfish colony of size " + colonySize);
            }

            // 3. Establish the colony depth
            int colonyDepth = random.nextInt(config.getHighestLayer() - config.getLowestLayer() + 1);
            colonyDepth += config.getLowestLayer();

            // 4. Lay out the Silverfish colony
            int x = 7;
            int y = colonyDepth;
            int z = 7;

            while (colonySize > 0) {
                Block current = chunk.getBlock(x, y, z);

                // Attempt to lay a Silverfish egg
                if (current.getType() == Material.STONE) {
                    current.setType(Material.MONSTER_EGGS); // Block 97
                    if (config.isDebug()) {
                        chunk.getBlock(x, 128, z).setType(Material.GLASS);
                    }
                    colonySize--;
                }

                // Translate randomly, respecting chunk boundaries
                int direction = random.nextInt(6); // [0..5]
                switch (direction) {
                    case 0:
                        if (x + 1 < 16) x++;
                        break;
                    case 1:
                        if (x - 1 >= 0) x--;
                        break;
                    case 2:
                        if (z + 1 < 16) z++;
                        break;
                    case 3:
                        if (z - 1 >= 0) z--;
                        break;
                    case 4:
                        if (y + 1 < 256) y++;
                        break;
                    case 5:
                        if (y - 1 >= 0) y--;
                        break;
                }
            }
        }
    }
}
