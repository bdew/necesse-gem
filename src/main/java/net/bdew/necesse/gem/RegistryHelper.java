package net.bdew.necesse.gem;

import necesse.engine.registries.ItemRegistry;
import necesse.engine.registries.ObjectRegistry;
import necesse.inventory.item.Item;
import necesse.inventory.item.matItem.MatItem;
import necesse.level.gameObject.RockObject;
import necesse.level.gameObject.RockOreObject;

import java.awt.*;

public class RegistryHelper {
    public static void registerGem(String name, float value) {
        MatItem tmpItem = new MatItem(100, Item.Rarity.RARE);
        tmpItem.setItemCategory("materials", "minerals");
        ItemRegistry.registerItem(name + "gem", tmpItem, value, true);
    }

    public static void registerOre(String name, float value) {
        MatItem tmpItem = new MatItem(250);
        tmpItem.setItemCategory("materials", "ore");
        ItemRegistry.registerItem(name + "ore", tmpItem, value, true);
    }

    public static void registerBar(String name, float value) {
        MatItem tmpItem = new MatItem(100);
        tmpItem.setItemCategory("materials", "bars");
        ItemRegistry.registerItem(name + "bar", tmpItem, value, true);
    }

    public static void registerOreVariants(String name, Color color, int toolTier, String suffix, int minDrop, int maxDrop) {
        registerOreRock(name, color, toolTier, "rock", suffix, minDrop, maxDrop);
        registerOreRock(name, color, toolTier, "snowrock", suffix, minDrop, maxDrop);
        registerOreRock(name, color, toolTier, "sandstonerock", suffix, minDrop, maxDrop);
        registerOreRock(name, color, toolTier, "swamprock", suffix, minDrop, maxDrop);
        registerOreRock(name, color, toolTier, "deeprock", suffix, minDrop, maxDrop);
        registerOreRock(name, color, toolTier, "deepsnowrock", suffix, minDrop, maxDrop);
        registerOreRock(name, color, toolTier, "deepswamprock", suffix, minDrop, maxDrop);
        registerOreRock(name, color, toolTier, "deepsandstonerock", suffix, minDrop, maxDrop);
    }

    public static void registerOreRock(String name, Color color, int toolTier, String baseRock, String suffix, int minDrop, int maxDrop) {
        RockObject rock = (RockObject) ObjectRegistry.getObject(baseRock);
        RockOreObject tmpObj = new RockOreObject(rock, "oremask", name + "ore", color, name + suffix, minDrop, maxDrop);
        if (tmpObj.toolTier < toolTier)
            tmpObj.toolTier = toolTier;
        ObjectRegistry.registerObject(name + suffix + baseRock, tmpObj, 0.0f, false);
    }
}
