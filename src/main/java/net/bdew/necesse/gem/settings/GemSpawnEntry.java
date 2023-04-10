package net.bdew.necesse.gem.settings;

import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;

public class GemSpawnEntry {
    public String biome;
    public String baseRock;
    public String ore;
    public float veinsPerChunk;
    public int minVeinSize;
    public int maxVeinSize;

    public GemSpawnEntry(String biome, String baseRock, String ore, float veinsPerChunk, int minVeinSize, int maxVeinSize) {
        this.biome = biome;
        this.baseRock = baseRock;
        this.ore = ore;
        this.veinsPerChunk = veinsPerChunk;
        this.minVeinSize = minVeinSize;
        this.maxVeinSize = maxVeinSize;
    }

    public GemSpawnEntry(LoadData data) {
        applyLoadData(data);
    }

    public void addSaveData(SaveData saveData) {
        saveData.addSafeString("biome", biome);
        saveData.addSafeString("baseRock", baseRock);
        saveData.addSafeString("ore", ore);
        saveData.addInt("minVeinSize", minVeinSize);
        saveData.addInt("maxVeinSize", maxVeinSize);
        saveData.addFloat("veinsPerChunk", veinsPerChunk);
    }

    public void applyLoadData(LoadData loadData) {
        biome = loadData.getSafeString("biome");
        baseRock = loadData.getSafeString("baseRock");
        ore = loadData.getSafeString("ore");
        minVeinSize = loadData.getInt("minVeinSize");
        maxVeinSize = loadData.getInt("maxVeinSize");
        veinsPerChunk = loadData.getFloat("veinsPerChunk");
    }

    @Override
    public String toString() {
        return "GemSpawnEntry{" +
                "biome='" + biome + '\'' +
                ", baseRock='" + baseRock + '\'' +
                ", ore='" + ore + '\'' +
                ", veinsPerChunk=" + veinsPerChunk +
                ", minVeinSize=" + minVeinSize +
                ", maxVeinSize=" + maxVeinSize +
                '}';
    }
}
