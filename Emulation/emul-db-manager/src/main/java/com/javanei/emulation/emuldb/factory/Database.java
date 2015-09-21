package com.javanei.emulation.emuldb.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vanei on 18/09/2015.
 */
public final class Database implements Serializable {

    private static final long serialVersionUID = 1L;
    private final List<GamePlatform> Platforms = new ArrayList<>();
    private final Map<String, GamePlatform> PlatformsByName = new HashMap<>();

    protected Database() {
    }

    public GamePlatform getPlatform(String name) {
        return this.PlatformsByName.get(name);
    }

    public List<GamePlatform> getPlatforms() {
        return this.Platforms;
    }

    protected void addPlatform(GamePlatform platform) throws GamePlatformAlreadyExistsException {
        if (PlatformsByName.get(platform.getName()) != null) {
            throw new GamePlatformAlreadyExistsException();
        }
        Platforms.add(platform);
        this.PlatformsByName.put(platform.getName(), platform);
    }

    protected void removePlatform(String name) throws GamePlatformDoesNotExistsException {
        GamePlatform plat = PlatformsByName.get(name);
        if (plat == null) {
            throw new GamePlatformDoesNotExistsException();
        }
        Platforms.remove(plat);
        this.PlatformsByName.remove(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<database>\n");

        for (GamePlatform gs : Platforms) {
            sb.append("\t").append(gs.toString()).append("\n");
        }

        sb.append("</database>");
        return sb.toString();
    }
}
