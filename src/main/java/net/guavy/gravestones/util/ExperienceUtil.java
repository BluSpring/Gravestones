package net.guavy.gravestones.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

// Thank you this thing
// https://www.spigotmc.org/threads/how-to-get-players-exp-points.239171/
public class ExperienceUtil {
    // Calculate amount of EXP needed to level up
    public static int getExpToLevelUp(int level) {
        return level <= 15 ? 2 * level + 7 : level <= 30 ? 5 * level - 38 : 9 * level - 158;
    }

    // Calculate total experience up to a level
    public static int getExpAtLevel(int level) {
        return (int) (level <= 16 ?
                MathHelper.square(level) + 6 * level :
                level <= 31 ?
                        (2.5 * MathHelper.square(level) - 40.5 * level + 360) :
                        (4.5 * MathHelper.square(level) - 162.5 * level + 2220));
    }

    // Calculate player's current EXP amount
    public static int getPlayerExp(PlayerEntity player) {
        var exp = 0;
        var level = player.experienceLevel;

        // Get the amount of XP in past levels
        exp += getExpAtLevel(level);

        // Get amount of XP towards next level
        exp += Math.round(getExpToLevelUp(level) * player.experienceProgress);
        return exp;
    }
}