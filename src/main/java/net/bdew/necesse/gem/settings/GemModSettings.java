package net.bdew.necesse.gem.settings;

import necesse.engine.GameLog;
import necesse.engine.modLoader.ModSettings;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GemModSettings extends ModSettings {
    public List<GemSpawnEntry> oreGen;
    public final int oreGenVersion = 1;

    public GemModSettings() {
        oreGen = new ArrayList<>();
        oreGen.add(new GemSpawnEntry("forest", "rock", "emeraldgemrock", 0.8f, 1, 2));
        oreGen.add(new GemSpawnEntry("forest", "rock", "sapphiregemrock", 0.6f, 1, 2));
        oreGen.add(new GemSpawnEntry("forest", "rock", "rubygemrock", 0.5f, 1, 2));
        oreGen.add(new GemSpawnEntry("forest", "rock", "diamondgemrock", 0.4f, 1, 2));
        oreGen.add(new GemSpawnEntry("forest", "rock", "tinorerock", 0.3f, 3, 6));

        oreGen.add(new GemSpawnEntry("forest", "deeprock", "emeraldgemrock", 0.8f, 1, 3));
        oreGen.add(new GemSpawnEntry("forest", "deeprock", "sapphiregemrock", 0.6f, 1, 3));
        oreGen.add(new GemSpawnEntry("forest", "deeprock", "rubygemrock", 0.5f, 1, 3));
        oreGen.add(new GemSpawnEntry("forest", "deeprock", "diamondgemrock", 0.4f, 1, 3));
        oreGen.add(new GemSpawnEntry("forest", "deeprock", "tinorerock", 0.07f, 3, 6));
    }

    @Override
    public void addSaveData(SaveData saveData) {
        saveData.addInt("oreGenVersion", oreGenVersion);
        oreGen.forEach(entry -> {
            SaveData tmp = new SaveData("oreGen");
            entry.addSaveData(tmp);
            saveData.addSaveData(tmp);
        });
    }

    @Override
    public void applyLoadData(LoadData loadData) {
        int ogv = loadData.getInt("oreGenVersion");
        if (ogv == oreGenVersion) {
            oreGen = loadData.getLoadDataByName("oreGen")
                    .stream()
                    .map(GemSpawnEntry::new)
                    .collect(Collectors.toList());
            GameLog.debug.println("Loaded ore generation settings");
            oreGen.forEach(x -> GameLog.debug.println("- " + x));
        } else {
            GameLog.debug.println(String.format("Ore generation settings are too old (%d vs %d), ignoring", ogv, oreGenVersion));
        }
    }
}
