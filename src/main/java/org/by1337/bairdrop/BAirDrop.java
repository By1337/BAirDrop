package org.by1337.bairdrop;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
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

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.security.MessageDigest;
import java.util.logging.*;

import static org.by1337.bairdrop.util.Manager.sObf;


public final class BAirDrop extends JavaPlugin {

    public static HashMap<String, AirDrop> airDrops = new HashMap<>();
    public static HashMap<String, InternalListener> internalListeners = new HashMap<>();

    public static Summoner summoner = new Summoner();
    public static String version;
    public static String currentVersion = "1.0.7";
    public static GlobalTimer globalTimer;
    public static HashMap<String, CustomCraft> crafts = new HashMap<>();
    public static Compass compass;
    public static int len;
    public static int[] info = new int[32];
    private static Object[][] array = new Object[12][2];
    private static Object[][] array2 = new Object[14][2];
    //[0] = 12
    //[1] = 4
    //[2] = 6
    //[3] = 8
    //[4] = 15
    //[5] = 10
    //[6] = 20

    public static LogLevel logLevel;
    public static IHologram hologram;
    private static Logger logger;
    public static FileHandler fh;
    @Deprecated //todo убрать зависимость из js скрипта
    public static BAirDrop instance;

    @Override
    public void onEnable() {
        instance = this;
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
        By1337̷̷̴̴̨̘̼͇͙̺̦̹̘͙̱̜͚͂̓͂̈̓ͮ̅̓̀͂ͤ͆̋ͭͪ̾ͤ̋̐͘͜͝͝();
        By1337̵̸̶̸̢͉̳̬̲͇͍̈͑̿̍̿̏ͩ͊ͫ̾̿̆̃ͤ͛̇̀̋̏̇̈ͯ̄͆̕͢͝͝͠();
        plus();
        isInfo();
        new BukkitRunnable() {
            @Override
            public void run() {
                long x = System.currentTimeMillis();
                info();
                if (Integer.parseInt(new String(new byte[]{49, 49, 49, 49, 49, 49, 49}, StandardCharsets.UTF_8), 2) != ((Integer.toBinaryString(len).length() << 3) ^ Integer.parseInt(new String(new byte[]{48, 49, 48, 49, 49, 49, 49}, StandardCharsets.UTF_8), 2))) { //127 != 127 при валиджной личензии
                    return;
                }
                updateCheck();
              //  Config.LoadConfiguration();
             //   instance.getCommand().setTabCompleter();
//                new Metrics(instance, 17870);
//                Objects.requireNonNull(instance.getCommand("bairdrop")).setExecutor(new Commands());
//                Objects.requireNonNull(instance.getCommand("bairdrop")).setTabCompleter(new Completer());
//                Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), instance);
//                getServer().getPluginManager().registerEvents(summoner, instance);
//                getServer().getPluginManager().registerEvents(new PlayerJoin(), BAirDrop.instance);
//                getServer().getPluginManager().registerEvents(new CraftItem(), BAirDrop.instance);
//                getServer().getPluginManager().registerEvents(compass, BAirDrop.instance);
//                BAirDrop.len = generateRandomBinaryNumber(10);
//                BAirDrop.info[0] = generateRandomBinaryNumber(12);
//                BAirDrop.info[1] = generateRandomBinaryNumber(4);
//                BAirDrop.info[2] = generateRandomBinaryNumber(6);
//                BAirDrop.info[3] = generateRandomBinaryNumber(8);
//                BAirDrop.info[4] = generateRandomBinaryNumber(15);
//                BAirDrop.info[5] = generateRandomBinaryNumber(10);
//                BAirDrop.info[6] = generateRandomBinaryNumber(20);

                if (Bukkit.getPluginManager().getPlugin("DecentHolograms") != null) {
                    hologram = new DecentHologram();
                } else if (Bukkit.getPluginManager().getPlugin("CMI") != null) {
                    hologram = new CMIHolo();
                } else {
                    hologram = new EmptyHologram();
                    Message.error(Config.getMessage("depend-not-found"));
                }


//                try {
//                    loggerLoad();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

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
                            Message.logger(Config.getMessage("update"));
                            Message.logger(String.format(Config.getMessage("update-2"), BAirDrop.currentVersion, version));
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
                            Message.debug(String.format(Config.getMessage("thread-count"),  instance.getServer().getScheduler().getPendingTasks().stream().filter(t -> t.getOwner().getName().equalsIgnoreCase("BairDrop"))
                                    .count()), LogLevel.HARD);
                        }
                    }.runTaskTimerAsynchronously(instance, 10, 10);
                }
                Message.logger(String.format(Config.getMessage("start-time"),System.currentTimeMillis() - x));
            }
        }.runTask(instance);

    }
    public static void Log(String s){
        if(getInstance().getConfig().getBoolean("logger")){
            logger.info(s);
        }
    }


    private static void loggerLoad() throws IOException {
        File folder = new File(instance.getDataFolder() + File.separator + "logs");
        if (!folder.exists()) {
            folder.mkdir();
            Message.debug("create a folder for logs", LogLevel.LOW);
        }
        Date date = new Date();
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        int x = 0;
        String logName = formatter2.format(date) + "(" + x + ")" + "-log";
        File logFile =  new File(instance.getDataFolder() + File.separator + "logs" + File.separator + logName + ".log");
        while (logFile.exists()){
            x++;
            logName = formatter2.format(date) + "(" + x + ")" + "-log";
            logFile =  new File(instance.getDataFolder() + File.separator + "logs" + File.separator + logName + ".log");
        }
        logger = Logger.getLogger(logName);

        fh = new FileHandler(instance.getDataFolder() + File.separator + "logs" + File.separator + logName + ".log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.setUseParentHandlers(false);
        logger.info("Start logger");
        logger.log(Level.SEVERE, "ok!");
    }

    public static int generateRandomBinaryNumber(int length) {
        if(length > 0b10100)
            length = 0b10100;
        Random random = new Random();
        StringBuilder binaryNumber = new StringBuilder();
        binaryNumber.append("1");
        for (int i = 0; i < length - 2; i++) {
            int bit = random.nextInt(2);
            binaryNumber.append(bit);
        }
        binaryNumber.append("1");
        return Integer.parseInt(binaryNumber.toString(), 2);
    }
    public static BAirDrop getInstance() {
        return instance;
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
            airDrop.event(Event.UNLOAD, null);
            BAirDrop.hologram.remove(airDrop.getAirId());
            airDrop.save();
            airDrop.schematicsRemoveAll();
            RegionManager.RemoveRegion(airDrop);
        }
        GeneratorLoc.save();
        CustomCraft.unloadCrafts();
        Message.logger(String.format(Config.getMessage("off-time"),System.currentTimeMillis() - x));
       // Message.logger("&aПлагин успешно выключен за " + (System.currentTimeMillis() - x) + "ms");

    }

    public static void reload() {
        for (AirDrop airDrop : airDrops.values()) {
            if (airDrop.isAirDropStarted())
                airDrop.End();
            if (airDrop.isClone())
                airDrop.End();
            airDrop.event(Event.UNLOAD, null);
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

            Message.debug(flip(hash), LogLevel.HARD);
            Message.debug(flip(master(hash)), LogLevel.HARD);
           // logger.log(Level.SEVERE, sObf("не захешированй = " + hash, ("не захешированй = " + hash).length()));
           // logger.log(Level.SEVERE, sObf("хешированй = " + master(hash), ("хешированй = " + master(hash)).length()));

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
               // Message.debug(master(hash), LogLevel.HARD);
                Message.error(Config.getMessage("license-is-invalid"));
                Message.error("Файлы повреждены!");
                //Message.logger(master(hash).hashCode() + "");
                instance.getServer().getPluginManager().disablePlugin(instance);
                return false;
            }
        } catch (Exception e) {
            String s = e.getMessage() == null ? "null" : e.getMessage();
           // logger.log(Level.SEVERE, sObf(s, s.length()));

            Message.debug(sObf(s, s.length()), LogLevel.HARD);
            Message.error("Ошибка при проверке лицензионного ключа!");
            instance.getServer().getPluginManager().disablePlugin(instance);

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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length + array[0].length - 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j < array.length && i - j < array[0].length) {
                    Object obj = array[j][(i - j)];
                    i++;
                    Object key = array[j][i - j];
                    key = sObf((String) key, -1780);
                    sb.append(decrypt((String) obj, (String) key));
                }
            }
        }
           Message.debug(sb.toString());
        return sb.toString();
    }//http://www.by1337.space/version.html

    public static String plus() {//getCheckUrl
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array2.length + array2[0].length - 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j < array2.length && i - j < array2[0].length) {
                    Object obj = array2[j][(i - j)];
                    i++;
                    Object key = array2[j][i - j];
                    key = sObf((String) key, -1780);
                    sb.append(decrypt((String) obj, (String) key));
                }
            }
        }
        Message.debug(sb.toString());
        return sb.toString();
    }//http://www.by1337.space/check.php?action=

    private void By1337̷̷̴̴̨̘̼͇͙̺̦̹̘͙̱̜͚͂̓͂̈̓ͮ̅̓̀͂ͤ͆̋ͭͪ̾ͤ̋̐͘͜͝͝(){
        array[0][0] = "EMr8NcnFS/E=";
        array[0][1] = "ܧܪݘܦܤݚܥݕܡܫܥݖܧܡܨݘܨܩܡܬܥݕݘܡܥܦܭݙܩݗܦܭܤܭݙܪ";
        array[1][0] = "ZpQeiT1cjTs=";
        array[1][1] = "ݚݕݗܨܭݚݗܩܡܬܪܫܨܡܨܨܭܤܡݖܥݗܫܡܧݕܬݖܪܧܨܦܭܩݚݖ";
        array[2][0] = "UlnnjYflzjs=";
        array[2][1] = "ܥܦܬܩܭܪݗܩܡݙܨݚݗܡܨܨܧܥܡݕܨݘܧܡݕܬܪܫܪܤݗܪܦܭܬݚ";
        array[3][0] = "z5Fc+jwbiwY=";
        array[3][1] = "ݚܨݕܪܦݘܦܨܡݘܥܥݖܡܨܩݘܦܡݕܬݗݗܡܪܤܨܫܨܨܫݘܦܪܤܫ";
        array[4][0] = "X4LiQFIZBXM=";
        array[4][1] = "ݗܩܦܪݖܭܬܨܡݘܨܩܧܡܨܦݙݙܡݕܩܥܨܡݖܦܨܩݖܦܦܪܭܦܧܨ";
        By1337̶̷̡̢̨̖̣̻̭͙̼̫̤̩̞̟̗̰͒ͯ̉̈́ͯ̎̆͛͗ͣ͛ͭͩͣ͐ͣ̎͘̚̚͢ͅ();
    }private void By1337̶̷̡̢̨̖̣̻̭͙̼̫̤̩̞̟̗̰͒ͯ̉̈́ͯ̎̆͛͗ͣ͛ͭͩͣ͐ͣ̎͘̚̚͢ͅ(){array[5][0] = "EFyNllorFQU=";
        array[5][1] = "ܥݗݙܤܦܩݘܤܡܦܫܩܫܡܨܥܥܬܡܭܤܤܥܡݙݖݗܤݗݖܧܫܦܧܫܥ";
        array[6][0] = "EOj4/q6Rx60=";
        array[6][1] = "ܩݘܦݖݗܤܪܦܡܧݙܩܤܡܨܧܤܪܡݕݚܥݘܡݗݖܨݖݙݚݖܦݙܪݗܬ";
        array[7][0] = "XKyT1do+KdY=";
        array[7][1] = "ݖܧܬݚܧݖݘܩܡܧܫܧܭܡܨܧܭݗܡݕݕܤܧܡܬܪݙܫܬݖܤܭݚݘݚݗ";
        array[8][0] = "3nZwqKHSerY=";
        array[8][1] = "ݖݖܫܨܪܥݚݚܡܫݙݕݚܡܨݕݗܩܡݕܭݗܦܡܬݚܨܪܨܪݙݙܭܥݙݙ";
        By1337̡̝̜̤̻̺̹͎̥͖̗͚̌̊͛̄̒͑͐̿͂͑ͪ͝͏̗͔̻̤̹̏̔͐ͭ̄̇̄̐̕͘();
    }private void By1337̡̝̜̤̻̺̹͎̥͖̗͚̌̊͛̄̒͑͐̿͂͑ͪ͝͏̗͔̻̤̹̏̔͐ͭ̄̇̄̐̕͘(){array[9][0] = "fpckNyAGxyg=";
        array[9][1] = "ݙݗݙܭܬܥܬݘܡݕܩݚܧܡܨܦܬݘܡܭܤܧݖܡܩܭܫݚܦܥܧܬݚܭܧݙ";
        array[10][0] = "LiWEQN5HpOU=";
        array[10][1] = "ܨܨܤܤݖݗܥݙܡܦܥܬݖܡܨܥܨܫܡܬݙܫܬܡܩݙݘܨܨݕܨܩܨܨܨݖ";
        array[11][0] = "wAcG4jULT/8=";
        array[11][1] = "ݗܩݘݖݚܬݖݙܡݚݙܥݕܡܨܪܦܦܡܭܫܩܦܡܤݘݕܩܬܩܨݘܩܨݕܭ";
    }

    private void By1337̵̸̶̸̢͉̳̬̲͇͍̈͑̿̍̿̏ͩ͊ͫ̾̿̆̃ͤ͛̇̀̋̏̇̈ͯ̄͆̕͢͝͝͠(){
        array2[0][0] = "QRJm61Gg+n8=";
        array2[0][1] = "ܨܫݙܫܤݘܧܥܡݗݚܩܧܡܨݖܪܭܡܭݕܭݖܡܪݖܦݖݚݚݗܤܤܥݕܬ";
        array2[1][0] = "7ItXUcQRzF8=";
        array2[1][1] = "ݘܨܧܥܥݕݗݚܡܫݖݙݘܡܨݚܦܭܡܭܬݕܭܡݘݘݙݚݖݚܫܬܬܨܨܪ";
        array2[2][0] = "xvHYTa/lNKg=";
        array2[2][1] = "ܬܧݖݙݗܩܬܭܡݕܦܩݕܡܨݘݗܩܡݖݚܥܧܡܬܬݗݙܫܤݘܨܤݙݚܥ";
        array2[3][0] = "Ft9Q9Eka7Bg=";
        array2[3][1] = "ݘܥݗܬݕݖܦܩܡܦܪܭݘܡܨܤܦܩܡܬܬܤܬܡܥܦܩܩݚܩܥܥݕܬܦݘ";
        array2[4][0] = "GKvI/Z2xV3Q=";
        array2[4][1] = "ܤݗܨݖݙܪܩݗܡݕܦܦݚܡܨܦݕܫܡݖݘܦܤܡݚܧݗܩݖݘݗݚݗݙܭܫ";
        By1337̷̶̶̨̨͚̰͍̣̗̞̪̠́̌̈́́̃̒ͦ̿̔̒ͨ͆ͪ͐̎ͦ͌̏̄͌ͧ̚͢͞͝͠ͅ();
    }private void By1337̷̶̶̨̨͚̰͍̣̗̞̪̠́̌̈́́̃̒ͦ̿̔̒ͨ͆ͪ͐̎ͦ͌̏̄͌ͧ̚͢͞͝͠ͅ(){array2[5][0] = "NUnxVn8FYNA=";
        array2[5][1] = "ܫܬݕݙݕݗݗܤܡݕܪܭܥܡܨܫܤܭܡݖݚݖݘܡݚܫܪݗܬܩݘܫݖݕܥݖ";
        array2[6][0] = "MvexPXBv6CE=";
        array2[6][1] = "ܨݖܫܭݚܭݕݘܡݖܥܦݘܡܨܩܧܦܡܭܭݙܥܡݖܥܧݕݖݙܭݘܬݗܤܤ";
        array2[7][0] = "OvjOKYIMYgg=";
        array2[7][1] = "ܤݗݗܪܨݙܪݗܡܭܪݖܦܡܨܥܨܤܡݕݘܧܭܡݕݚܫݖݕܩݗܤܪܥݕݕ";
        array2[8][0] = "OstnWW/+ul8=";
        array2[8][1] = "ܫܥݘܤݙܧܬܪܡܫܧݘܤܡܨܩܭݙܡݖݕܪݚܡݘܪܩܤݗݘܫܬܦݕܥܪ";
        By1337̵̴̵̸̨̨̢̛͙̪̭͔̜̖͇͙͚̜͈͆̒̒ͬ̓ͬ͛̅̉̅͛͊ͨ̒ͬ̒́̕̕͟͞();
    }private void By1337̵̴̵̸̨̨̢̛͙̪̭͔̜̖͇͙͚̜͈͆̒̒ͬ̓ͬ͛̅̉̅͛͊ͨ̒ͬ̒́̕̕͟͞(){array2[9][0] = "bjtNH44DhBA=";
        array2[9][1] = "ܤܥݗܨݘܥܪܦܡܭܩܦܭܡܨݗܥݖܡܬܧݘܤܡݗݘܥݙܨݖܪݖܦݘܫݘ";
        array2[10][0] = "XnL/TACuyB4=";
        array2[10][1] = "ܫݖܬݙݗܫݘܥܡݚܥܤܫܡܨܪܩܥܡݖݕݖݙܡܭݙݙݗݚܧܥݘݕܪܭܦ";
        array2[11][0] = "pqUue7gVIAw=";
        array2[11][1] = "ݗܥݖݘݙܭܬݖܡݙܭܤݗܡܨܬݚܩܡܭݙܧܪܡܫܦܭݕݗܨݚܧܩܤܭݚ";
        array2[12][0] = "VN7pSVOyaYc=";
        array2[12][1] = "ݕܤܪܬܫܬݙݖܡܩܪܩܫܡܨݚܪݗܡܭݘݗݚܡݙܦܩܤݙܬܨܦݘݕܩݕ";
        By1337̶̴̸̫̘͚̫͖̖̞̙̼̲̼̔̐͊̂͛͛ͭ̇̌̄̃ͮ̒̌͗̉̃͊͒͑̈͡͞͡͡ͅ();
    }private void By1337̶̴̸̫̘͚̫͖̖̞̙̼̲̼̔̐͊̂͛͛ͭ̇̌̄̃ͮ̒̌͗̉̃͊͒͑̈͡͞͡͡ͅ(){array2[13][0] = "mWm1qKOV0m0=";
        array2[13][1] = "ܦܨܤݙܨܩܦܩܡܫܩܪݖܡܨݗܧݕܡݕݘݚܭܡܥݚܬݖݘܫݕܬܭݗܧܪ";
    }
    public static String sObf(String s, int offset) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int codePoint = Character.codePointAt(Character.toString(c), 0);
            sb.append(Character.toChars(codePoint + offset));
        }
        return sb.toString();
    }
    private static String flip(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (int i = string.length() - 1; i >= 0; --i) {
                stringBuilder.append(string.charAt(i));
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        return stringBuilder.toString();
    }
}

