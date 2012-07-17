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

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 */
public class MSConfig {

    private int lowestLayer;
    private int highestLayer;
    private int percentChance;
    private int colonySize;
    private List<String> worlds;

    public MSConfig(ConfigurationSection rawConfig) {
        lowestLayer = rawConfig.getInt("lowestLayer");
        highestLayer = rawConfig.getInt("highestLayer");
        percentChance = rawConfig.getInt("percentChance");
        colonySize = rawConfig.getInt("percentChance");
        worlds = rawConfig.getStringList("worlds");

        if (lowestLayer < 0) lowestLayer = 0;
        if (lowestLayer > 254) lowestLayer = 254;
        if (highestLayer < 1) highestLayer = 1;
        if (highestLayer > 255) highestLayer = 255;
        if (highestLayer <= lowestLayer) highestLayer = lowestLayer + 1;
        if (percentChance < 0) percentChance = 0;
        if (percentChance > 100) percentChance = 100;
        if (colonySize < 0) colonySize = 0;
        if (colonySize > 20) colonySize = 20;
    }

    public int getColonySize() {
        return colonySize;
    }

    public int getHighestLayer() {
        return highestLayer;
    }

    public int getLowestLayer() {
        return lowestLayer;
    }

    public int getPercentChance() {
        return percentChance;
    }

    public List<String> getWorlds() {
        return worlds;
    }
}
