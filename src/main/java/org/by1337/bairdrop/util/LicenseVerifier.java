package org.by1337.bairdrop.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.by1337.bairdrop.BAirDrop;

import static org.by1337.bairdrop.BAirDrop.instance;

public class LicenseVerifier {
    String licenseKey;

    private String loadAndRegister(String licenseKey) {
        this.licenseKey = licenseKey;
        Message.logger("[]==============================[]");
        Message.logger("Соединение с сервером");
        int vt = isValidationType();
        try {
            if (vt == 81434588 && getVerifyUrl().hashCode() == -98892341) {
                Message.logger(getForCode(vt));
                Message.logger("[]==============================[]");
                return "true";
            } else {
                Message.logger("§cЛицензия не валидна!");
                Message.logger(getForCode(vt));
                Message.logger("§cПлагин выключен!");
                Message.logger("[]==============================[]");
                Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
                Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
                return "false";
            }
        } catch (NumberFormatException e) {
            Message.logger("§cЛицензия не валидна!");
            Message.logger(getForCode(vt));
            Message.logger("§cПлагин выключен!");
            Message.logger("[]==============================[]");
            Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
            Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
            return "false";
        }
    }
    private String getForCode(int code){
        if(code == -1276891080) return "&cОшибка сервера лицензий";
        if(code == -1694123818) return "&cОтсутствует лицензионный ключ!";
        if(code == 81434588) return "&aЛицензия валидна";
        if(code == -973558093) return "&cНеправильный ответ от сервера!";
        if(code == -1694123819) return "&cНеправильный ключ!";
        if(code == 1377916022) return "&cIP адрес не валидный!";
        if(code == 190116507) return "&cНеправильный плагин!";
        if(code == -2017473048) return "&cКлюч устарел!";
        return "&cНеизвестная ошибка";
    }

    private String builder(String v1, String v2){
        StringBuilder urlBuilder = new StringBuilder(getVerifyUrl());
        urlBuilder.append("?v1=").append(v1);
        urlBuilder.append("&v2=").append(v2);
        urlBuilder.append("&pl=").append(instance.getName());
        return builderToString(urlBuilder);
    }
    private String builderToString(StringBuilder sb){
        return sb.toString();
    }
    private String requestServer(String v1, String v2) throws IOException {
        URL url = new URL(builder(v1, v2));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return builderToString(response);
        }
    }
    public int isValidationType() {
        String rand = toBinary(UUID.randomUUID().toString());
        String sKey = toBinary("2APZ5JCR2nuIapCO7eT04knQ");
        String key = toBinary(licenseKey);

        try {
            String response = requestServer(xor(rand, sKey), xor(rand, key));

            if (response.startsWith("<")) {
                return -1276891080; //PAGE_ERROR
            }
            if(response.hashCode() == -1694123818) {//KEY_NOT_FOUND
                if(key.length() == 0)
                    return -1694123818;
                return -1694123819; //ключ не валидный
            }
            if(response.hashCode() == 1377916022) //NOT_VALID_IP
                return 1377916022;
            if(response.hashCode() == 190116507) //INVALID_PLUGIN
                return 190116507;
            if(response.hashCode() == -2017473048) //KEY_OUTDATED
                return -2017473048;

            String respRand = xor(xor(response, key), sKey);
            if (rand.startsWith(respRand))
                return 81434588; //VALID
            else
                return -973558093; //WRONG_RESPONSE

        } catch (IOException e) {
            return -1276891080; //PAGE_ERROR
        }
    }
    private String toBinary(String s) {
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }
    public static String xor(String s1, String s2) {
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < (Math.min(s1.length(), s2.length())); i++)
                result.append(Byte.parseByte("" + s1.charAt(i)) ^ Byte.parseByte(s2.charAt(i) + ""));
            return result.toString();
        } catch (NumberFormatException e) {
            return "<";
        }
    }
    private String getVerifyUrl(){
        return (new Object() {
            int t;

            public String toString() {
                byte[] buf = new byte[30];
                t = 646293480;
                buf[0] = (byte) (t >>> 20);
                t = -1882527427;
                buf[1] = (byte) (t >>> 9);
                t = 1010017847;
                buf[2] = (byte) (t >>> 11);
                t = -831856194;
                buf[3] = (byte) (t >>> 9);
                t = 1278899551;
                buf[4] = (byte) (t >>> 9);
                t = 1902665687;
                buf[5] = (byte) (t >>> 9);
                t = 778041700;
                buf[6] = (byte) (t >>> 17);
                t = 1095601118;
                buf[7] = (byte) (t >>> 10);
                t = 891116275;
                buf[8] = (byte) (t >>> 1);
                t = -1186584604;
                buf[9] = (byte) (t >>> 13);
                t = 2040455124;
                buf[10] = (byte) (t >>> 19);
                t = 1032009294;
                buf[11] = (byte) (t >>> 12);
                t = -897991253;
                buf[12] = (byte) (t >>> 11);
                t = 1515980195;
                buf[13] = (byte) (t >>> 17);
                t = 851028959;
                buf[14] = (byte) (t >>> 15);
                t = -1148855386;
                buf[15] = (byte) (t >>> 19);
                t = 1058413521;
                buf[16] = (byte) (t >>> 12);
                t = 965619623;
                buf[17] = (byte) (t >>> 18);
                t = -1167280557;
                buf[18] = (byte) (t >>> 13);
                t = 1655451413;
                buf[19] = (byte) (t >>> 8);
                t = -1875162278;
                buf[20] = (byte) (t >>> 15);
                t = 848626008;
                buf[21] = (byte) (t >>> 23);
                t = 980592881;
                buf[22] = (byte) (t >>> 16);
                t = -891497507;
                buf[23] = (byte) (t >>> 9);
                t = 303934671;
                buf[24] = (byte) (t >>> 5);
                t = 1808310129;
                buf[25] = (byte) (t >>> 19);
                t = 280668396;
                buf[26] = (byte) (t >>> 18);
                t = 915569539;
                buf[27] = (byte) (t >>> 3);
                t = -1390497296;
                buf[28] = (byte) (t >>> 21);
                t = -1602064253;
                buf[29] = (byte) (t >>> 8);
                return new String(buf);
            }
        }.toString());
    }//http://by1337.space/verify.php
}