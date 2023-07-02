package org.by1337.bairdrop.locationGenerator;

import org.bukkit.Location;
import org.by1337.bairdrop.AirDrop;
import org.jetbrains.annotations.NotNull;

public interface Generator {
    /**
     * Returns a pre-generated location
     * @param airDrop The AirDrop for which to obtain the location
     * @return Pre-generated location
     * @see GeneratorLoc
     * @see CGenLoc
     */

    Location getPreLocation(@NotNull AirDrop airDrop);

    /**
     * Main method for generating locations
     * @param airDrop The AirDrop for which to generate the location
     * @param isGenerator If this is a generator generating locations, there will be no logging
     * @return Generated location
     */

    Location getLocation(@NotNull AirDrop airDrop, boolean isGenerator);

    /**
     * Checks the flatness of a location
     * @param location The location to check for flatness
     * @param airDrop The AirDrop whose settings will be used
     * @return Returns true if the location is flat, otherwise false
     */
    boolean checkForEvenness(@NotNull Location location, AirDrop airDrop);

}
