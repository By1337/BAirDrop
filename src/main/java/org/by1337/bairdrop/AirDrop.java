package org.by1337.bairdrop;

import com.sk89q.worldedit.EditSession;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.by1337.bairdrop.locationGenerator.Generator;
import org.by1337.bairdrop.worldGuardHook.SchematicsManager;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.customListeners.observer.Observable;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.menu.AddingItems;
import org.by1337.bairdrop.menu.EditAirMenu;
import org.by1337.bairdrop.ItemUtil.Items;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface AirDrop extends Observable {
    /**
     * Launches the airdrop and generates locations for it in a separate thread.
     *
     * @param player Sends the location generation process log.
     */

    void startCommand(@Nullable Player player);

    /**
     * Saves the airdrop to a file.
     */

    void save();

    /**
     * @return The file of the airdrop.
     */

    File getAirDropFile();

    /**
     * Opens the airdrop.
     *
     * @throws IllegalArgumentException if the airdrop was not started.
     */
    void unlock();

    /**
     * Stops the airdrop.
     *
     */
    void End();

    /**
     * Applies all placeholders to the string.
     *
     * @param str the raw message
     * @return the string with applied placeholders
     */
    String replaceInternalPlaceholder(String str);

    /**
     * Saves the observer key to a file.
     *
     * @param observerKey the name to be saved
     */
    void saveObserver(String observerKey);

    /**
     * Removes the observer key from the saved data.
     *
     * @param observerKey the name to be removed
     */
    void removeSaveObserver(String observerKey);

    /**
     * Checks if the observer key is saved in the file.
     *
     * @param observerKey the name of the observer
     * @return true if this observer key is saved in the file
     */
    boolean hasSavedObserver(String observerKey);

    /**
     * Invokes the listener.
     *
     * @param listener    the listener to invoke
     * @param player      the player
     * @param customEvent the custom event
     */
    @Deprecated
    void InvokeListener(NamespacedKey listener, @Nullable Player player, CustomEvent customEvent);
    /**
     * Invokes the listener.
     *
     * @param listener    the listener to invoke
     * @param player      the player
     * @param customEvent the custom event
     */
    void invokeListener(NamespacedKey listener, @Nullable Player player, CustomEvent customEvent);

    /**
     * Returns the editing items inventory.
     *
     * @param inv     the inventory
     * @param invName the name of the editing inventory
     * @return the editing inventory
     * @see AddingItems
     */
    Inventory getEditorItemsInventory(Inventory inv, String invName);

    /**
     * Returns the object of the airdrop editing menu.
     *
     * @return the airdrop editing menu object
     */
    EditAirMenu getEditAirMenu();

    /**
     * Sets the current object to the specified editAirMenu.
     *
     * @param editAirMenu the new object to set
     */
    void setEditAirMenu(EditAirMenu editAirMenu);

    /**
     * Updates all elements in the menu.
     *
     * @see EditAirMenu
     */
    void updateEditAirMenu();

    /**
     * Updates the menu object based on the tag.
     *
     * @param tag The tag of the item to update.
     * @see EditAirMenu
     */
    void updateEditAirMenu(String tag);

    /**
     * Loads an effect object.
     *
     * @param name The name of the effect to load.
     * @param id   The ID under which this effect will be stored.
     * @throws IllegalArgumentException If the effect was not found.
     */
    void loadEffect(String name, String id);

    /**
     * Starts an effect.
     *
     * @param id The ID of the effect to start.
     * @throws IllegalArgumentException If the effect was not found.
     * @throws IllegalArgumentException If the effect has already been started.
     */
    void startEffect(String id);

    /**
     * Checks if an effect has been started.
     *
     * @param id The ID of the effect.
     * @return true if the effect has been started.
     */
    boolean isEffectStarted(String id);

    /**
     * Stops an effect.
     *
     * @param id The ID of the effect to stop.
     * @throws IllegalArgumentException if the effect was not found.
     */
    void StopEffect(String id);

    /**
     * Stops all effects, even if they were not started.
     */
    void StopAllEffects();

    /**
     * @return Returns all loaded effects.
     */
    HashMap<String, IEffect> getLoadedEffect();

    /**
     * Sets the map of loaded effects.
     *
     * @param loadedEffect the map of loaded effects
     */
    void setLoadedEffect(HashMap<String, IEffect> loadedEffect);

    /**
     * Removes the placed schematic.
     */
    void schematicsUndo();

    /**
     * Retrieves the generator-settings from the airdrop configuration file.
     *
     * @return The generator-settings value from the airdrop configuration file.
     */
    String getGeneratorSettings();

    /**
     * Sets the editing session.
     *
     * @param editSession The editing session to set.
     */
    void setEditSession(EditSession editSession);

    /**
     * Creates a clone of the airdrop.
     *
     * @param id The ID of the clone.
     * @return The cloned airdrop with the new ID.
     * The clone is not marked as a clone. Any changes made to this airdrop will reflect on the original.
     * To avoid this, you need to call the createFile() method on the clone.
     */
    AirDrop clone(String id);

    /**
     * Creates a configuration file for the airdrop.
     */
    void createFile();

    /**
     * Deletes the configuration file.
     *
     * @return true if the file is deleted.
     */
    boolean delete();

    /**
     * Unloads the airdrop.
     */
    void unload();

    /**
     * Returns the location or preliminary location of the airdrop, or null.
     *
     * @return the location or preliminary location of the airdrop, or null.
     */
    @Nullable
    Location getAnyLoc();

    /**
     * Returns true if the airdrop uses only the static location.
     *
     * @return true if the airdrop uses only the static location.
     */
    boolean isUseOnlyStaticLoc();

    /**
     * Sets whether the airdrop should use only the static location.
     *
     * @param useOnlyStaticLoc true to use only the static location.
     */
    void setUseOnlyStaticLoc(boolean useOnlyStaticLoc);

    /**
     * Registers all subscribed observers.
     *
     * @see org.by1337.bairdrop.customListeners.observer.Observer
     */
    void registerAllSignedObservers();

    /**
     * Sets the region at the airdrop spawn location.
     */
    void setRegion();

    /**
     * Returns true if there is a hologram with countdown at the airdrop spawn location.
     *
     * @return true if there is a hologram with countdown at the airdrop spawn location.
     */
    boolean isHoloTimeToStartEnabled();

    /**
     * Sets whether there is a hologram with countdown at the airdrop spawn location.
     *
     * @param holoTimeToStartEnabled true if there should be a hologram with countdown.
     */
    void setHoloTimeToStartEnabled(boolean holoTimeToStartEnabled);

    /**
     * Returns true if the airdrop uses the player's location as the spawn location.
     *
     * @return true if the airdrop uses the player's location.
     * @see org.by1337.bairdrop.summoner.Summoner
     * @see org.by1337.bairdrop.summoner.SummonerItem
     */
    boolean isUsePlayerLocation();

    /**
     * Sets whether the airdrop uses the player's location as the spawn location.
     * The location is set using the setAirDropLocation() method.
     *
     * @param usePlayerLocation true if the airdrop should use the player's location.
     */
    void setUsePlayerLocation(boolean usePlayerLocation);

    /**
     * Returns true if the airdrop was stopped due to inventory depletion.
     *
     * @return true if the airdrop was stopped due to inventory depletion.
     */
    boolean isStopWhenEmpty();

    /**
     * Sets whether the airdrop should be stopped when the inventory is empty.
     *
     * @param stopWhenEmpty true if the airdrop should be stopped when the inventory is empty.
     */
    void setStopWhenEmpty(boolean stopWhenEmpty);

    /**
     * Returns true if the airdrop was summoned by a summoning item.
     *
     * @return true if the airdrop was summoned by a summoning item.
     * @see org.by1337.bairdrop.summoner.Summoner
     */
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

    void schematicsPaste(SchematicsManager manager, String name);

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

    Generator getGenerator();

    void setGenerator(Generator generator);

    String getSuperName();
    void setSuperName(String superName);

    void addDec(String... s);
    List<String> getDec();
}
