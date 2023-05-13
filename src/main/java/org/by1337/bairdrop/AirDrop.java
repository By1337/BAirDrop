package org.by1337.bairdrop;

import com.sk89q.worldedit.EditSession;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.customListeners.observer.Observable;
import org.by1337.bairdrop.customListeners.observer.Observer;
import org.by1337.bairdrop.menu.EditAirMenu;
import org.by1337.bairdrop.ItemUtil.Items;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface AirDrop extends Observable {
    void startCommand();

    void save();

    File getAirDropFile();

    void unlock();

    void End();

    String replaceInternalPlaceholder(String str);

    void registerStaticObserver(Observer observer);

    void unregisterStaticObserver(Observer observer);

    boolean hasStaticObserver(Observer observer);

    void saveObserver(String observerKey);

    void removeSaveObserver(String observerKey);

    boolean hasSavedObserver(String observerKey);

    void callListener(NamespacedKey listener, @Nullable Player player, CustomEvent customEvent);

    Inventory getEditorItemsInventory(Inventory inv, String invName);

    EditAirMenu getEditAirMenu();

    void setEditAirMenu(EditAirMenu editAirMenu);

    void updateEditAirMenu();

    void updateEditAirMenu(String tag);

    void addEffect(String name, String id);

    void startEffect(String id);

    boolean isEffectStarted(String id);

    void StopEffect(String id);

    void StopAllEffects();

    void schematicsUndo();

    String getGeneratorSettings();

    void setEditSession(EditSession editSession);

    AirDrop clone(String id);

    void createFile();

    boolean delete();

    void unload();

    @Nullable
    Location getAnyLoc();

    boolean isUseOnlyStaticLoc();

    void setUseOnlyStaticLoc(boolean useOnlyStaticLoc);

    void registerAllSignedObservers();

    void setRegion();

    boolean isHoloTimeToStartEnabled();

    void setHoloTimeToStartEnabled(boolean holoTimeToStartEnabled);

    boolean isUsePlayerLocation();

    void setUsePlayerLocation(boolean usePlayerLocation);

    boolean isStopWhenEmpty();

    void setStopWhenEmpty(boolean stopWhenEmpty);

    boolean isSummoner();

    void setSummoner(boolean summoner);

    boolean isHideInCompleter();

    void setHideInCompleter(boolean hideInCompleter);

    HashMap<String, List<Items>> getListItems();

    void setHoloTimeToStartMinusOffsets(boolean holoTimeToStartMinusOffsets);

    boolean isCanceled();

    void setCanceled(boolean canceled);

    boolean isClone();

    void setClone(boolean clone);

    boolean isKill();

    void setKill(boolean kill);

    Location getStaticLocation();

    void setStaticLocation(Location staticLocation);

    boolean isUseStaticLoc();

    void setUseStaticLoc(boolean useStaticLoc);

    int getPickPreGenLocs();

    void setPickPreGenLocs(int pickPreGenLocs);

    int getSpawnChance();

    void setTimeCountingEnabled(boolean timeCountingEnabled);

    boolean isTimeCountingEnabled();

    @Nullable
    EditSession getEditSession();

    void schematicsPaste(String name);

    boolean isActivated();

    void setActivated(boolean activated);

    boolean isUsePreGeneratedLocations();

    void setUsePreGeneratedLocations(boolean usePreGeneratedLocations);

    boolean isFlatnessCheck();

    void setFlatnessCheck(boolean flatnessCheck);

    int getTimeToStartCons();

    int getTimeToStopCons();

    int getTimeToUnlockCons();

    int getSearchBeforeStartCons();

    void setTimeToStartCons(int timeToStartCons);

    void setTimeToStopCons(int timeToStopCons);

    void setTimeToUnlockCons(int timeToUnlockCons);

    void setSearchBeforeStartCons(int searchBeforeStartCons);

    String getInventoryTitle();

    void setInventoryTitle(String inventoryTitle);

    void updateInvName();

    String getDisplayName();

    void setDisplayName(String displayName);

    int getInventorySize();

    World getWorld();

    void setWorld(World world);

    int getSpawnRadiusMin();

    void setSpawnRadiusMin(int spawnRadiusMin);

    int getSpawnRadiusMax();

    void setSpawnRadiusMax(int spawnRadiusMax);

    int getRegionRadius();

    void setRegionRadius(int regionRadius);

    int getTimeToStart();

    void setTimeToStart(int timeToStart);

    int getSearchBeforeStart();

    void setSearchBeforeStart(int searchBeforeStart);

    int getTimeToOpen();

    void setTimeToOpen(int timeToOpen);

    boolean isStartCountdownAfterClick();

    void setStartCountdownAfterClick(boolean startCountdownAfterClick);

    boolean isTimeStopEventMustGo();

    void setTimeStopEventMustGo(boolean timeStopEventMustGo);

    int getTimeStop();

    void setTimeStop(int timeStop);

    Material getMaterialLocked();

    void setMaterialLocked(Material materialLocked);

    Material getMaterialUnlocked();

    void setMaterialUnlocked(Material materialUnlocked);

    boolean isAirDropLocked();

    void setAirDropLocked(boolean airDropLocked);

    Inventory getInventory();

    void setInventory(Inventory inventory);

    Location getAirDropLocation();

    void setAirDropLocation(Location airDropLocation);

    Location getFutureLocation();

    void setFutureLocation(Location futureLocation);

    FileConfiguration getFileConfiguration();

    int getMinPlayersToStart();

    void setMinPlayersToStart(int minPlayersToStart);

    String getId();

    boolean isWasOpened();

    void setWasOpened(boolean wasOpened);

    boolean isAirDropStarted();
}
