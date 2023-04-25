package org.by1337.bairdrop;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.Hologram.CMIHolo;
import org.by1337.bairdrop.Hologram.DecentHologram;
import org.by1337.bairdrop.Hologram.EmptyHologram;
import org.by1337.bairdrop.Hologram.IHologram;
import org.by1337.bairdrop.Listeners.Compass;
import org.by1337.bairdrop.Listeners.CraftItem;
import org.by1337.bairdrop.Listeners.InteractListener;
import org.by1337.bairdrop.Listeners.PlayerJoin;
import org.by1337.bairdrop.command.Commands;
import org.by1337.bairdrop.command.Completer;
import org.by1337.bairdrop.effect.EffectFactory;
import org.by1337.bairdrop.util.*;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;
import org.mozilla.javascript.NativeJavaObject;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.security.MessageDigest;

import static org.by1337.bairdrop.AirDrop.getHash;

public final class BAirDrop extends JavaPlugin {

    public static HashMap<String, AirDrop> airDrops = new HashMap<>();
    public static HashMap<String, InternalListener> internalListeners = new HashMap<>();

    public static Summoner summoner = new Summoner();
    public static String version;
    public static String currentVersion = "1.0.6";
    public static GlobalTimer globalTimer;
    public static HashMap<String, CustomCraft> crafts = new HashMap<>();
    public static Compass compass;
    public static int len;
    public static int[] info = new int[32];
    //[0] = 12
    //[1] = 4
    //[2] = 6
    //[3] = 8
    //[4] = 15
    //[5] = 10
    //[6] = 20

    public static LogLevel logLevel;
    public static IHologram hologram;
    public static BAirDrop instance;

    @Override
    public void onEnable() {
        instance = this;
        new BukkitRunnable() {
            @Override
            public void run() {
                long x = System.currentTimeMillis();
                File config = new File(instance.getDataFolder() + File.separator + "config.yml");
                if (!config.exists()) {
                    instance.getLogger().info("Creating new config file, please wait");
                    instance.getConfig().options().copyDefaults(true);
                    instance.saveDefaultConfig();
                }
                instance.saveConfig();
                try {
                    String lvl = instance.getConfig().getString("log-level", "LOW");
                    logLevel = LogLevel.valueOf(lvl);
                } catch (IllegalArgumentException e) {
                    Message.error(e.getLocalizedMessage());
                    logLevel = LogLevel.LOW;
                }
                info();
                if (Integer.parseInt(new String(new byte[]{49, 49, 49, 49, 49, 49, 49}, StandardCharsets.UTF_8), 2) != ((Integer.toBinaryString(len).length() << 3) ^ Integer.parseInt(new String(new byte[]{48, 49, 48, 49, 49, 49, 49}, StandardCharsets.UTF_8), 2))) { //127 != 127 при валиджной личензии
                    return;
                }
                if (Bukkit.getPluginManager().getPlugin("DecentHolograms") != null) {
                    hologram = new DecentHologram();
                } else if (Bukkit.getPluginManager().getPlugin("CMI") != null) {
                    hologram = new CMIHolo();
                } else {
                    hologram = new EmptyHologram();
                    Message.error(Config.getMessage("depend-not-found"));
                }
                //  new Metrics(this, 17870);
                // Objects.requireNonNull(this.getCommand("bairdrop")).setExecutor(new Commands());
                // Objects.requireNonNull(this.getCommand("bairdrop")).setTabCompleter(new Completer());
                //  Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), instance);
                //  getServer().getPluginManager().registerEvents(summoner, instance);
                //  getServer().getPluginManager().registerEvents(new PlayerJoin(), BAirDrop.instance);
                // getServer().getPluginManager().registerEvents(new CraftItem(), BAirDrop.instance);
                // getServer().getPluginManager().registerEvents(compass, BAirDrop.instance);
                //   register();
                for (File file : Config.getAirDrops().keySet()) {
                    airDrops.put(Config.getAirDrops().get(file).getString("air-id"), new AirDrop(Config.getAirDrops().get(file), file));
                }
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
                    new PlaceholderExpansion().register();

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        updateCheck();
                        cancel();
                        if (!BAirDrop.version.equals(BAirDrop.currentVersion)) {
                            Message.logger("{PP} &fВышла новая версия плагина!");
                            Message.logger("{PP} &fТекущая версия " + BAirDrop.currentVersion + " новая версия " + version);
                        }
                    }
                }.runTaskAsynchronously(instance);

                if (BAirDrop.instance.getConfig().getBoolean("global-time.enable")) {
                    globalTimer = new GlobalTimer((BAirDrop.instance.getConfig().getInt("global-time.time") * 60));
                }
                if (logLevel == LogLevel.HARD) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Message.debug("потоков плагина = " + instance.getServer().getScheduler().getPendingTasks().stream().filter(t -> t.getOwner().getName().equalsIgnoreCase("BairDrop"))
                                    .count(), LogLevel.HARD);
                        }
                    }.runTaskTimerAsynchronously(instance, 10, 10);
                }
                Message.logger("&aПлагин успешно включён за " + (System.currentTimeMillis() - x) + "ms");
            }
        }.runTask(instance);

    }

    @Override
    public void onDisable() {
        if (!Config.isLoaded)
            return;
        long x = System.currentTimeMillis();
        for (AirDrop airDrop : airDrops.values()) {
            if (airDrop.isAirDropStarted())
                airDrop.End();
            if (airDrop.isClone())
                airDrop.End();
            airDrop.event(Events.UNLOAD, null);
            BAirDrop.hologram.remove(airDrop.getAirId());
            airDrop.save();
            airDrop.schematicsRemoveAll();
            RegionManager.RemoveRegion(airDrop);
        }
        GeneratorLoc.save();
        CustomCraft.unloadCrafts();
        Message.logger("&aПлагин успешно выключен за " + (System.currentTimeMillis() - x) + "ms");

    }

    private void register() {
        Objects.requireNonNull(instance.getCommand("bairdrop")).setExecutor(new Commands());
        Objects.requireNonNull(instance.getCommand("bairdrop")).setTabCompleter(new Completer());
        getServer().getPluginManager().registerEvents(new InteractListener(), instance);
        getServer().getPluginManager().registerEvents(summoner, instance);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), BAirDrop.instance);
        getServer().getPluginManager().registerEvents(new CraftItem(), BAirDrop.instance);
        getServer().getPluginManager().registerEvents(compass, BAirDrop.instance);
    }

    public static void reload() {
        for (AirDrop airDrop : airDrops.values()) {
            if (airDrop.isAirDropStarted())
                airDrop.End();
            if (airDrop.isClone())
                airDrop.End();
            airDrop.event(Events.UNLOAD, null);
            BAirDrop.hologram.remove(airDrop.getAirId());
            airDrop.cancel();
            airDrop.schematicsRemoveAll();
            RegionManager.RemoveRegion(airDrop);
        }
        CustomCraft.unloadCrafts();
        Config.Schematics.clear();
        airDrops.clear();
        internalListeners.clear();
        EffectFactory.EffectList.clear();
        Config.getAirDrops().clear();

        instance.reloadConfig();
        compass.loadItem();
        GeneratorLoc.locs.clear();
        summoner.Load();
        if (globalTimer != null) {
            if (!BAirDrop.instance.getConfig().getBoolean("global-time.enable")) {
                globalTimer.setStop(true);
                globalTimer = null;
            } else {
                globalTimer.setTimeToStartCons(BAirDrop.instance.getConfig().getInt("global-time.time") * 60);
                globalTimer.setTimeToStart(globalTimer.getTimeToStartCons());
                if (globalTimer.getAir() != null)
                    globalTimer.setAir(null);
            }
        } else if (BAirDrop.instance.getConfig().getBoolean("global-time.enable")) {
            globalTimer = new GlobalTimer((BAirDrop.instance.getConfig().getInt("global-time.time") * 60));
        }
        Config.LoadConfiguration();
        for (File file : Config.getAirDrops().keySet()) {
            airDrops.put(Config.getAirDrops().get(file).getString("air-id"), new AirDrop(Config.getAirDrops().get(file), file));
        }
    }

    private static boolean info() { //checkHash
        long i = System.currentTimeMillis();
        try {
            String jarFilePatch = instance.getFile().getAbsolutePath();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (FileInputStream fis = new FileInputStream(jarFilePatch)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    digest.update(buffer, 0, bytesRead);
                }
            }
            byte[] hashBytes = digest.digest();
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashBuilder.append(String.format("%02x", b));
            }
            String hash = hashBuilder.toString();

            Message.debug(hash, LogLevel.HARD);
            Message.debug(master(hash), LogLevel.HARD);
            if (true) {// //sha256(hash).equals(getHash()) //master(hash).equals(getHash()) //master
                boolean result = Boolean.parseBoolean(new Manager().manager(instance.getConfig().getString("License")));
                Message.logger(System.currentTimeMillis() - i + " = ms");
                if (!result) {
                    check(String.valueOf(result)); //false
                    return result;//false
                } else {
                    check(String.valueOf(result));//true
                    return result;//true
                }
            } else {
                Message.debug(master(hash), LogLevel.HARD);
                Message.error("Лицензия не валидна!");
                Message.error("Файлы повреждены!");
                //Message.logger(master(hash).hashCode() + "");
                instance.getServer().getPluginManager().disablePlugin(instance);
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //  Message.error(e.getLocalizedMessage());
            Message.error("Ошибка при проверке лицензионного ключа!");
            instance.getServer().getPluginManager().disablePlugin(instance);
            Message.error("err-key=\"" + loads(e.getLocalizedMessage(), "_customKey=qwsax123") + "\"");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            Message.error("Ошибка при проверке лицензионного ключа!");
            instance.getServer().getPluginManager().disablePlugin(instance);
            Message.error("err-key=\"" + loads(e.getLocalizedMessage(), "_customKey=qwsax123") + "\"");
            return false;
        }
    }

    public static int hashCode(byte a[]) {
        if (a == null)
            return 0;

        int result = 0;
        for (byte element : a)
            result = 45 * result + element;

        return result;
    }

    public static String decrypt(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("SHA-384").digest(key.getBytes(StandardCharsets.UTF_8)), 8), "Blowfish");

            Cipher des = Cipher.getInstance("Blowfish");
            des.init(Cipher.DECRYPT_MODE, keySpec);

            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);

        } catch (IllegalArgumentException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException |
                 NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return obj;
        }
    }

    public static void check(String str) {
        int vvar = Integer.parseInt("110", 2); //6
        int vvar2 = Integer.parseInt("101", 2); //5
        int vvar3 = Integer.parseInt("000000000000000000000000001", 2); //1
        int vvar4 = Integer.parseInt("101110010110001100100100011", 2); // 97196323
        int vvar6 = Integer.parseInt("1101100111010110001110", 2); //3569038
        int var = vvar ^ vvar2;
        int var1 = Integer.toBinaryString(len).length() / vvar2;
        int var5 = str.hashCode() ^ var1 ^ var;
        int var3 = var5 ^ vvar3;
        if (var3 == vvar4) {
            if (var3 >>> vvar2 == vvar3) {
                return;//NOP
            } else {
                instance.getServer().getPluginManager().disablePlugin(instance);
                return;
            }
        }
        int var4 = var5 ^ vvar3;
        if (var4 == vvar6) {
            if (var4 >>> vvar2 == vvar3) {
                instance.getServer().getPluginManager().disablePlugin(instance);//NOP
                return;
            } else {
               // Config.LoadConfiguration();
                return;
            }
        }
        instance.getServer().getPluginManager().disablePlugin(instance);
    }

    public static String master(String str) throws NoSuchAlgorithmException {//sha256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String loads(String obj, String key) {//encrypt
        if (obj == null)
            obj = "obg = null";
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("SHA-384").digest(key.getBytes(StandardCharsets.UTF_8)), 8), "Blowfish");
            Cipher des = Cipher.getInstance("Blowfish");
            des.init(1, keySpec);
            String str = new String(Base64.getEncoder().encode(des.doFinal(obj.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateCheck() {
        try {
            URL url = new URL(isInfo());
            URLConnection conn = url.openConnection();
            conn.setReadTimeout(5000);
            conn.addRequestProperty("User-Agent", "BAirDrop Update Checker");
            conn.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();
            if (!response.equals(""))
                version = response.replace(" ", "");
            else version = currentVersion;
        } catch (Exception e) {
            return;
        }
        return;
    }

    public static String isInfo() {//getVersionUrl
        return (new Object() {
            int t;

            public String toString() {
                byte[] buf = new byte[36];
                t = 1177825898;
                buf[0] = (byte) (t >>> 15);
                t = 415474556;
                buf[1] = (byte) (t >>> 11);
                t = -1487810547;
                buf[2] = (byte) (t >>> 10);
                t = -1757360640;
                buf[3] = (byte) (t >>> 5);
                t = 1195563769;
                buf[4] = (byte) (t >>> 21);
                t = -931450124;
                buf[5] = (byte) (t >>> 4);
                t = 778067936;
                buf[6] = (byte) (t >>> 9);
                t = -819577940;
                buf[7] = (byte) (t >>> 7);
                t = 2002886577;
                buf[8] = (byte) (t >>> 24);
                t = 936312790;
                buf[9] = (byte) (t >>> 13);
                t = 777340770;
                buf[10] = (byte) (t >>> 24);
                t = -866489473;
                buf[11] = (byte) (t >>> 21);
                t = 1283364752;
                buf[12] = (byte) (t >>> 4);
                t = -456716396;
                buf[13] = (byte) (t >>> 18);
                t = -1993087325;
                buf[14] = (byte) (t >>> 16);
                t = -932026421;
                buf[15] = (byte) (t >>> 9);
                t = -766640746;
                buf[16] = (byte) (t >>> 14);
                t = -2040697917;
                buf[17] = (byte) (t >>> 17);
                t = 2110746742;
                buf[18] = (byte) (t >>> 18);
                t = -600054934;
                buf[19] = (byte) (t >>> 22);
                t = 1377335689;
                buf[20] = (byte) (t >>> 14);
                t = 361500062;
                buf[21] = (byte) (t >>> 18);
                t = 321889010;
                buf[22] = (byte) (t >>> 19);
                t = 174057750;
                buf[23] = (byte) (t >>> 17);
                t = 370065458;
                buf[24] = (byte) (t >>> 13);
                t = -1800237181;
                buf[25] = (byte) (t >>> 15);
                t = 484834290;
                buf[26] = (byte) (t >>> 17);
                t = -1485618446;
                buf[27] = (byte) (t >>> 16);
                t = 698510655;
                buf[28] = (byte) (t >>> 5);
                t = 1400434035;
                buf[29] = (byte) (t >>> 19);
                t = -152801899;
                buf[30] = (byte) (t >>> 20);
                t = -149196049;
                buf[31] = (byte) (t >>> 4);
                t = -854879265;
                buf[32] = (byte) (t >>> 21);
                t = -11627114;
                buf[33] = (byte) (t >>> 13);
                t = -2073222466;
                buf[34] = (byte) (t >>> 16);
                t = 1533868968;
                buf[35] = (byte) (t >>> 16);
                return new String(buf);
            }
        }.toString());
    }//http://www.by1337.space/version.html

    public static String plus() {//getCheckUrl
        return (new Object() {
            int t;

            public String toString() {
                byte[] buf = new byte[41];
                t = 282125698;
                buf[0] = (byte) (t >>> 17);
                t = 1794017061;
                buf[1] = (byte) (t >>> 13);
                t = 975487326;
                buf[2] = (byte) (t >>> 23);
                t = -924789758;
                buf[3] = (byte) (t >>> 17);
                t = -774687346;
                buf[4] = (byte) (t >>> 19);
                t = -1752636406;
                buf[5] = (byte) (t >>> 23);
                t = 1331264994;
                buf[6] = (byte) (t >>> 11);
                t = 299847322;
                buf[7] = (byte) (t >>> 18);
                t = 552038776;
                buf[8] = (byte) (t >>> 12);
                t = -909673305;
                buf[9] = (byte) (t >>> 12);
                t = 391782751;
                buf[10] = (byte) (t >>> 23);
                t = 1949393414;
                buf[11] = (byte) (t >>> 15);
                t = -1728893572;
                buf[12] = (byte) (t >>> 17);
                t = 1905413223;
                buf[13] = (byte) (t >>> 6);
                t = 457604017;
                buf[14] = (byte) (t >>> 13);
                t = -1714362974;
                buf[15] = (byte) (t >>> 23);
                t = -1683743429;
                buf[16] = (byte) (t >>> 23);
                t = 109075638;
                buf[17] = (byte) (t >>> 9);
                t = 2054409819;
                buf[18] = (byte) (t >>> 16);
                t = -1563175155;
                buf[19] = (byte) (t >>> 4);
                t = 1402059970;
                buf[20] = (byte) (t >>> 1);
                t = 1862886276;
                buf[21] = (byte) (t >>> 8);
                t = -1157310141;
                buf[22] = (byte) (t >>> 6);
                t = 226882731;
                buf[23] = (byte) (t >>> 13);
                t = 616994396;
                buf[24] = (byte) (t >>> 17);
                t = 1923517896;
                buf[25] = (byte) (t >>> 12);
                t = 1702972915;
                buf[26] = (byte) (t >>> 24);
                t = 1349666190;
                buf[27] = (byte) (t >>> 2);
                t = 713590450;
                buf[28] = (byte) (t >>> 4);
                t = 1152030587;
                buf[29] = (byte) (t >>> 7);
                t = 825767962;
                buf[30] = (byte) (t >>> 7);
                t = 2019175645;
                buf[31] = (byte) (t >>> 14);
                t = -172273440;
                buf[32] = (byte) (t >>> 1);
                t = -48264711;
                buf[33] = (byte) (t >>> 3);
                t = -1831317495;
                buf[34] = (byte) (t >>> 14);
                t = -2125784436;
                buf[35] = (byte) (t >>> 11);
                t = 1820998172;
                buf[36] = (byte) (t >>> 7);
                t = 1472761124;
                buf[37] = (byte) (t >>> 5);
                t = 697865975;
                buf[38] = (byte) (t >>> 4);
                t = 1688409826;
                buf[39] = (byte) (t >>> 4);
                t = -2032319524;
                buf[40] = (byte) (t >>> 4);
                return new String(buf);
            }
        }.toString());
    }//http://www.by1337.space/check.php?action=
}
