package dk.sdu.cookie.castle.map;

import dk.sdu.cookie.castle.common.assets.Asset;
import dk.sdu.cookie.castle.common.assets.AssetLoader;
import dk.sdu.cookie.castle.common.assets.AssetType;
import dk.sdu.cookie.castle.common.assets.FileType;
import dk.sdu.cookie.castle.common.data.GameData;
import dk.sdu.cookie.castle.common.data.World;
import dk.sdu.cookie.castle.common.services.IGamePluginService;
import dk.sdu.cookie.castle.map.entities.door.DoorPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class MapPlugin implements IGamePluginService {

    private static java.util.Map<String, String> assets = new ConcurrentHashMap<>();

    @Override
    public void start(GameData gameData, World world) {

        initializeAssets(gameData);

        // Set door positions with display dimensions from GameData
        for (DoorPosition doorPosition : DoorPosition.values()) {
            doorPosition.setPosition(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        }

        // skal initiate singleton og kalde "generateNewMap" func
        Map.getInstance().generateMap(3, world);
        Map.getInstance().setCurrentRoom(Map.getInstance().getListOfRooms().get(0));
        for (String s : Map.getInstance().getCurrentRoom().getEntityList()) {
            world.getEntity(s).setIsActive(true);
        }
    }

    public static String getAssetId(String name) {
        return assets.get(name);
    }

    private void initializeAssets(GameData gameData) {
        Collection<Asset> loadingAssets = new ArrayList<>();
        loadingAssets.add(new Asset("rock", AssetType.TEXTURE, FileType.PNG));
        loadingAssets.add(new Asset("door", AssetType.TEXTURE, FileType.PNG));
        assets = AssetLoader.loadAssets(this.getClass(), loadingAssets);
        gameData.addAssets(loadingAssets);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
