package com.javanei.emulation.emuldb.factory;

import com.javanei.emulation.common.GameCatalog;
import com.javanei.emulation.emuldb.game.Game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vanei on 18/09/2015.
 */
public final class Database implements Serializable {

    private static final long serialVersionUID = 1L;
    private final List<GamePlatform> platforms = new ArrayList<>();
    private final Map<String, GamePlatform> PlatformsByName = new HashMap<>();

    protected Database() {
    }

    public GamePlatform getPlatform(String name) {
        return this.PlatformsByName.get(name);
    }

    public List<GamePlatform> getPlatforms() {
        return this.platforms;
    }

    public void addROMFile(GamePlatform platform, byte[] b) throws Exception {
        RepositoryManager.getInstance().saveROMFile(platform, b);
    }

    public void addDatFile(GamePlatform platform, GameCatalog catalog, String catalogVersion, String complement, byte[] b) throws Exception {
        RepositoryManager.getInstance().saveDatFile(platform, catalog, catalogVersion, complement, b);
    }

    public void addGames(GamePlatform platform, List<Game> newGames) throws Exception {
        RepositoryManager.getInstance().addGames(platform, newGames);
    }

    protected void addPlatform(GamePlatform platform) throws GamePlatformAlreadyExistsException {
        if (PlatformsByName.get(platform.getName()) != null) {
            throw new GamePlatformAlreadyExistsException();
        }
        platforms.add(platform);
        this.PlatformsByName.put(platform.getName(), platform);
        Collections.sort(this.platforms, new Comparator<GamePlatform>() {
            @Override
            public int compare(GamePlatform o1, GamePlatform o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    protected void removePlatform(String name) throws GamePlatformDoesNotExistsException {
        GamePlatform plat = PlatformsByName.get(name);
        if (plat == null) {
            throw new GamePlatformDoesNotExistsException();
        }
        platforms.remove(plat);
        this.PlatformsByName.remove(name);
    }
    
    public Set<Game> getGames(GamePlatform platform) throws Exception {
        return RepositoryManager.getInstance().readGamesFile(platform);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<database>\n");

        platforms.stream().forEach((gs) -> {
            sb.append("\t").append(gs.toString()).append("\n");
        });

        sb.append("</database>");
        return sb.toString();
    }
}
