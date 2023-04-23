package org.by1337.bairdrop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import org.bukkit.Bukkit;
import org.by1337.bairdrop.BAirDrop;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import static org.by1337.bairdrop.BAirDrop.instance;
import static org.by1337.bairdrop.BAirDrop.reload;

public class Manager {
    String string;//licenseKey
    private static final Integer[] num = new Integer[8];
    private static final String[] strings = new String[18];
    Object[][] array = new Object[1415][2];

    public String manager(String s) {//loadAndRegister


        this.string = s;
        Message.logger(strings[0]);
        Message.logger(strings[1]);
        int vt = isIs();
        //  Message.error(vt + "");
        By1337̷̷̶̸̡̢̨̹̠̹̖̪̙̦͇̃ͣ̉̄͑̇͐ͯ̉ͣͫ̓ͯ̌͛͑́͒ͩ̅̂̚̚͡͡();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length + array[0].length - 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j < array.length && i - j < array[0].length) {
                    Object obj = array[j][(i - j)];
                    i++;
                    Object key = array[j][i - j];
                    key = sObf((String) key, -1500);
                    sb.append(decrypt((String) obj, (String) key));
                }
            }
        }
        HashMap<String, Object> property = new HashMap<>();
        property.put("vt", vt);
        String res = (String) new org.by1337.bairdrop.scripts.Manager().runJsScriptForString(sb.toString(), property);
        Message.logger(res);
        return res;

//        int varvarvar = Integer.parseInt("111000010", 2);
//        int Vvar890 = 453364388 ^ 111;//453364427
//        switch (Vvar890) {
//            case 453364427://453364427
//                varvarvar = -Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1568841397:
//                throw null;
//            case 1683868623:
//                varvarvar = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 458424783:
//                varvarvar = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 871809019:
//                throw null;
//            case 571414282:
//                varvarvar = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1451364614:
//                throw null;
//        }
//
//        int var0 = Integer.parseInt("1110100", 2);
//        int Vvar532062141 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));//182
//        switch (Vvar532062141) {
//            case 1296610439:
//                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 182://182
//                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 765484397:
//                throw null;
//            case 910259207:
//                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 697991621:
//                throw null;
//            case 165202375:
//                throw null;
//            case 264823946:
//                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 2073713503:
//                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
//                break;
//        }
//        int var1 = Integer.parseInt("1101100", 2);
//        int Vvar237830551 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));//1345
//        switch (Vvar237830551) {
//            case 91977231:
//                throw null;
//            case 1485745095:
//                throw null;
//            case 581582461:
//                throw null;
//            case 1392704805:
//                throw null;
//            case 1177536376:
//                var1 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1095448965:
//                var1 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1345://1345
//                var1 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1407213046:
//                throw null;
//        }
//        int var2 = Integer.parseInt("11000001", 2);
//        int Vvar143625821 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));//22722
//        switch (Vvar143625821) {
//            case 2143486334:
//                throw null;
//            case 168446079:
//                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 179834477:
//                var2 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 22722://22722
//                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1538110257:
//                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 707108995:
//                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1583449380:
//                var2 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 280370884:
//                throw null;
//            case 345765087:
//                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1163970761:
//                throw null;
//            case 2000289197:
//                throw null;
//            case 1735931479:
//                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//        }
//        int var3 = Integer.parseInt("1011000001", 2);
//        int Vvar1943351023 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));//367
//        switch (Vvar1943351023) {
//            case 367://367
//                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1054348052:
//                throw null;
//            case 1063993252:
//                var3 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 549836206:
//                throw null;
//            case 20282617:
//                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1565685196:
//                var3 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 836095074:
//                throw null;
//            case 1746629737:
//                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
//                break;
//        }
//        int var4 = Integer.parseInt("1010010001", 2);
//        int Vvar1539857684 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));//398
//        switch (Vvar1539857684) {
//            case 592313341:
//                var4 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1086607794:
//                throw null;
//            case 398://398
//                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 191409784:
//                throw null;
//            case 1968696441:
//                throw null;
//            case 1154436179:
//                var4 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 929861627:
//                var4 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1279707290:
//                throw null;
//            case 1570782122:
//                throw null;
//            case 1119352303:
//                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 982095360:
//                throw null;
//        }
//        int var5 = Integer.parseInt("1010000101", 2);
//        int Vvar1509663844 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));//247
//        switch (Vvar1509663844) {
//            case 256053001:
//                var5 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1410894208:
//                var5 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1276273126:
//                var5 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1641976377:
//                var5 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 558820117:
//                throw null;
//            case 111886048:
//                var5 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 640756608:
//                throw null;
//            case 247://247
//                var5 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 422023573:
//                throw null;
//        }
//        int var6 = Integer.parseInt("1010110000", 2);
//        int Vvar1616995789 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));//1249
//        switch (Vvar1616995789) {
//            case 2062399452:
//                throw null;
//            case 618705825:
//                throw null;
//            case 1235725888:
//                var6 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1249://1249
//                var6 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 2071003877:
//                throw null;
//            case 832687158:
//                throw null;
//            case 542459294:
//                throw null;
//            case 708758350:
//                throw null;
//            case 1753240810:
//                throw null;
//            case 547731916:
//                throw null;
//        }
//        int var7 = Integer.parseInt("1011011000", 2);
//        int Vvar1323072151 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));//130
//        switch (Vvar1323072151) {
//            case 1148200972:
//                var7 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 130://130
//                var7 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1950293131:
//                var7 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 204808432:
//                var7 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 245401556:
//                var7 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1663966825:
//                throw null;
//            case 314445315:
//                var7 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1320117682:
//                throw null;
//        }
//        int var8 = Integer.parseInt("11011000", 2);
//        int Vvar869522528 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));//11376
//        switch (Vvar869522528) {
//            case 11376://11376
//                var8 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 93576058:
//                throw null;
//            case 1140455362:
//                var8 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 294896261:
//                var8 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1422697983:
//                throw null;
//            case 464599260:
//                var8 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
//                break;
//        }
//        int var9 = Integer.parseInt("1110111000", 2);
//        int Vvar705461757 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));//113
//        switch (Vvar705461757) {
//            case 1306977605:
//                var9 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 113://113
//                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1372159936:
//                var9 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 844202021:
//                var9 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 369922019:
//                var9 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1937317831:
//                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1787630172:
//                var9 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 973340250:
//                throw null;
//            case 1346474282:
//                throw null;
//            case 364734733:
//                throw null;
//            case 986116739:
//                throw null;
//        }
//        int var10 = Integer.parseInt("110010010", 2);
//        int Vvar1535426735 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)));//23560
//        switch (Vvar1535426735) {
//            case 23560://23560
//                var10 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 164513683:
//                var10 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1761112626:
//                throw null;
//            case 344666790:
//                var10 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 991433204:
//                throw null;
//            case 1528573476:
//                var10 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1359331263:
//                throw null;
//            case 1674555928:
//                var10 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
//                break;
//        }
//        int var11 = Integer.parseInt("1100101110", 2);
//        int Vvar882312766 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));//1614
//        switch (Vvar882312766) {
//            case 1614://1614
//                var11 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1589371957:
//                throw null;
//            case 1608976084:
//                throw null;
//            case 1675757619:
//                var11 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 685278011:
//                throw null;
//            case 2117617786:
//                throw null;
//            case 1262096176:
//                throw null;
//        }
//        int var12 = Integer.parseInt("1110101100", 2);
//        int Vvar1010940190 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));//17980
//        switch (Vvar1010940190) {
//            case 17980://17980
//                var12 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1413775130:
//                throw null;
//            case 1344021760:
//                var12 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1181590221:
//                throw null;
//            case 102730105:
//                var12 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 951017335:
//                var12 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 713869936:
//                var12 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 2098266157:
//                throw null;
//            case 1912407711:
//                throw null;
//        }
//        int var13 = Integer.parseInt("10001011", 2);
//        int Vvar396004466 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));//2415
//        switch (Vvar396004466) {
//            case 1099207518:
//                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1859912881:
//                throw null;
//            case 756469975:
//                throw null;
//            case 1479390450:
//                var13 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 412834829:
//                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 401239198:
//                throw null;
//            case 2415://2415
//                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1281729178:
//                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
//                break;
//        }
//        int var14 = Integer.parseInt("10100001", 2);
//        int Vvar687510032 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));//8234
//        switch (Vvar687510032) {
//            case 1795455108:
//                throw null;
//            case 8234://8234
//                var14 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1544947694:
//                var14 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 135609680:
//                throw null;
//            case 1432772440:
//                var14 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1739766306:
//                throw null;
//            case 325454599:
//                var14 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1016847760:
//                throw null;
//            case 1715196814:
//                throw null;
//            case 214857460:
//                throw null;
//            case 2088550544:
//                var14 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1513713394:
//                var14 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
//                break;
//        }
//        int var15 = Integer.parseInt("1000101011", 2);
//        int Vvar197898643 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));//5272
//        switch (Vvar197898643) {
//            case 5272://5272
//                var15 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1181558613:
//                throw null;
//            case 1877543554:
//                var15 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 568701578:
//                throw null;
//            case 430468801:
//                var15 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));
//                break;
//        }
//        int var16 = Integer.parseInt("100000010", 2);
//        int Vvar2015025472 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));//186
//        switch (Vvar2015025472) {
//            case 186://186
//                var16 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1949923863:
//                var16 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 297267780:
//                var16 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1819348183:
//                var16 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 936028087:
//                throw null;
//            case 1414206294:
//                throw null;
//            case 1836840126:
//                var16 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
//                break;
//        }
//        int var17 = Integer.parseInt("1010000111", 2);
//        int Vvar256781301 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));//48
//        switch (Vvar256781301) {
//            case 1804754588:
//                throw null;
//            case 764547374:
//                var17 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1447042099:
//                throw null;
//            case 48://48
//                var17 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 673732982:
//                throw null;
//        }
//        int var18 = Integer.parseInt("10110010", 2);
//        int Vvar1984512756 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));//23
//        switch (Vvar1984512756) {
//            case 473471778:
//                var18 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1994811419:
//                var18 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 17903731:
//                var18 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 995105128:
//                throw null;
//            case 465664783:
//                throw null;
//            case 23://23
//                var18 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 45068371:
//                throw null;
//            case 2043668668:
//                throw null;
//            case 488112232:
//                throw null;
//        }
//        int var19 = Integer.parseInt("1000111110", 2);
//        int Vvar855965487 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));//23587
//        switch (Vvar855965487) {
//            case 2146377222:
//                var19 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1991955314:
//                var19 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1938505300:
//                throw null;
//            case 1682764625:
//                throw null;
//            case 1863259486:
//                var19 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 23587://23587
//                var19 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1653893972:
//                throw null;
//            case 729152630:
//                var19 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
//                break;
//        }
//        int var20 = Integer.parseInt("1010100000", 2);
//        int Vvar232305849 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));//911
//        switch (Vvar232305849) {
//            case 323980198:
//                throw null;
//            case 911://911
//                var20 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 675676601:
//                var20 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1835637531:
//                throw null;
//            case 585365162:
//                throw null;
//            case 1668130497:
//                throw null;
//            case 1652796888:
//                var20 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
//                break;
//        }
//        int var21 = Integer.parseInt("1110001101", 2);
//        int Vvar1872012444 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)));//59
//        switch (Vvar1872012444) {
//            case 344670586:
//                var21 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 1652098148:
//                throw null;
//            case 1227267239:
//                var21 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
//                break;
//            case 743876956:
//                var21 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 603258169:
//                var21 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
//                break;
//            case 1542937662:
//                throw null;
//            case 59://59
//                var21 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
//                break;
//        }
//
//        try {
//            Message.logger(strings[var0]);
//            if (vt == num[vt >> var1 ^ var2] && getGet().hashCode() == varvarvar) {
//                Message.logger(infoCode(vt));
//                BAirDrop.len = getInt(vt >> var3 ^ var4);
//                if (vt == num[var5]) {
//                    Message.logger(strings[var6]);
//                    Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
//                    Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
//                }
//                return strings[vt >> var7 ^ var8];
//            } else {
//                Message.logger(strings[var9]);
//                Message.logger(infoCode(vt));
//                BAirDrop.len = getInt(vt >> var10 ^ var11);
//                if (vt != var12 >> var13) {
//                    Message.logger(strings[var14]);
//                    Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
//                    Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
//                }
//                return strings[var15];
//            }
//        } catch (NumberFormatException e) {
//            Message.logger(strings[var16]);
//            Message.logger(infoCode(vt));
//            Message.logger(strings[var17]);
//            Message.logger(strings[var18]);
//            Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
//            Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
//            BAirDrop.len = getInt(vt >> var19 ^ var20);
//            return strings[var21];
//        }


//        try {
//            if (vt == num[2] && getGet().hashCode() == varvarvar) {
//                Message.logger(infoCode(vt));// valid
//                Message.logger(strings[0]);
//                BAirDrop.len = getInt(vt >> 23 ^ 3); //10
//                return strings[2];
//            } else {
//                Message.logger(strings[3]); //ne valid
//                Message.logger(infoCode(vt));
//                Message.logger(strings[4]);
//                Message.logger(strings[0]);
//                Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
//                Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
//                BAirDrop.len = getInt(vt >> 23 ^ 3);
//                return strings[5];
//            }
//        } catch (NumberFormatException e) {
//            Message.logger(strings[3]); //ne valid
//            Message.logger(infoCode(vt));
//            Message.logger(strings[4]);
//            Message.logger(strings[0]);
//            Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
//            Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
//            BAirDrop.len = getInt(vt >> 23 ^ 3);
//            return strings[5];
//        }
    }

    private void By1337̷̷̶̸̡̢̨̹̠̹̖̪̙̦͇̃ͣ̉̄͑̇͐ͯ̉ͣͫ̓ͯ̌͛͑́͒ͩ̅̂̚̚͡͡() {
        array[0][0] = "v0u0fBKJp6G3up7XLQBkjQ==";
        array[0][1] = "ؕققؓؓـ؍ؕ؉ؐ؎ق؎؉ؐؓ،ؕ؉ؽؕ؍،؉،ؒقؓ؏ؿؾؑؾؑفؑ";
        array[1][0] = "wk0n93lNNqrM1KIg9/LTYQ==";
        array[1][1] = "ـ؏ـ؎ؐقؿـ؉ؑؽؑ؏؉ؐؓ؏؏؉ؾؕؾؑ؉،ؐؑ؎،ؕفؐؽقؑؿ";
        array[2][0] = "Q7qBwBkbvX422pFBI+8N+A==";
        array[2][1] = "،قؿق؏؍؍ـ؉ؕ،ؑؓ؉ؐفؐؒ؉ؕؒؾ؍؉؍ؐ،قؒؿؔ؏ؕؒؓف";
        array[3][0] = "Mnx0IDs4H7JHD1LLUiT8QQ==";
        array[3][1] = "ؓ؎ؕف،ؿـؕ؉؍؍ؑ،؉ؐ،ؽؽ؉ؽؑ،؎؉ؑفؔؔؓ،؎ؑؔ؍ـ،";
        array[4][0] = "sbotwc7Voc8SBCiYIDpjPA==";
        array[4][1] = "ؑ،ؽؓقؔ؏ؿ؉ؔؓؐ،؉ؐقؾ؎؉ؕؽ،ؾ؉ؕقفؿؓ،ؾؐؒ،ؕـ";
        array[5][0] = "7IkfNGTA94ksvkf5aHgJiw==";
        array[5][1] = "ؐؕؐ؍،ؓؾؓ؉ؑـ؎؎؉ؐ،؎؎؉ؽ،فؒ؉ؿقؒـ؏ـ؏ؾ؍ؾقؽ";
        array[6][0] = "HX5fQWkcxPY7a+iy0aMCUw==";
        array[6][1] = "؍ؑؿؒؽ،؎ؕ؉ؽؕؿ،؉ؐؓؕ؎؉ؽؐؾف؉؍ؿؔؕؓ،،؏قؔـؓ";
        array[7][0] = "KEfDbuNaWaxQJJDLBAqVsQ==";
        array[7][1] = "ـقفقؔؑ؍ؔ؉قؽؑق؉ؐ؍ؾف؉ؕقؐ؎؉ؑؐؔؿؿؿ؎ؾؕ؍ؿؾ";
        array[8][0] = "rf5uCrEuwXAh3j65ArlT/A==";
        array[8][1] = "ؕؕؔؽؿؿـ؍؉ؐؒؾف؉ؐؿ؎ؽ؉ؾؒــ؉قؐؒ؏قؒؐققؿؿف";
        array[9][0] = "EJTTaIbnQhBIl8Fgq73ofw==";
        array[9][1] = "ق؎ؑؔف؏ؑف؉ؐفقؑ؉ؐقؑؿ؉ؔؾؓؓ؉ؒؾؓ؏ؒؓؓؽـؓؾ؎";
        array[10][0] = "QKoPm+u+Dg2JJh57j5WxDA==";
        array[10][1] = "ؿؿفؽؑ،ؽ؍؉ؑــؿ؉ؐؐؔؾ؉ؕ،ؿـ؉قؕؓ،ؕ؏؍ؑ،ؔؒ؍";
        array[11][0] = "ux8NEQz7qVzxbz6hlISHJw==";
        array[11][1] = "ؓؓ،،ؕؐؒـ؉ؐؑ؏ـ؉ؐؕؕؒ؉ؕؐف،؉ؕؐ؍ؽؾؔؐؕـؒف،";
        By1337̷̯̬̹̦̪͔̥̪̙̭͓̼̠͍̤ͫͧͭ̀̽̓̅̊ͤ͋̎͐̂͗̇̒̍̀̇̋͘͢͠͡();
    }

    private void By1337̷̯̬̹̦̪͔̥̪̙̭͓̼̠͍̤ͫͧͭ̀̽̓̅̊ͤ͋̎͐̂͗̇̒̍̀̇̋͘͢͠͡() {
        array[12][0] = "C5mvFIQbN80YwAyukn+LJQ==";
        array[12][1] = "ؓؓ؏ؽؑؐ؏ؓ؉ؓؿؿؾ؉ؐف؍ؕ؉ؔؾق،؉ؐؐؑؑ؎؍ؿؔؐ؏ؑ؎";
        array[13][0] = "CWxJJssi0Ow/WujjbwndBw==";
        array[13][1] = "فؑـق؏ـؓؿ؉ؐؔف،؉ؐؾؾؓ؉ؽؔ،ؓ؉ؓؽؐؾؐؑؑـؓـؕؿ";
        array[14][0] = "DjQ/uInSXTp9kfD9u5AhLA==";
        array[14][1] = "؏ف؍فؓؐ؍ؒ؉،؏ؑ،؉ؐق؎ؽ؉ؔؾؑؓ؉ؕ؏ؾؒقؔؐقؽؾ؍ؔ";
        array[15][0] = "1NhUF9G5BVCeA9vYI+lJGQ==";
        array[15][1] = "؎؍ؑؓ؍،؍؍؉قؓؕـ؉ؐف؍ؽ؉ؕؐؐق؉؏ؽ؏ؾقؓؐؕؑ؍ق،";
        array[16][0] = "shrL4GRCWOscDYi1yCG5ZA==";
        array[16][1] = "ؐففؒـؕؽ؏؉ؑف؎ؑ؉ؐؒؓ؍؉ؔؽؐف؉ؒـفؾؾؔؓؐ؏ـؒ؎";
        array[17][0] = "RN+7fJ/66c9GiQPS6Yquqw==";
        array[17][1] = "ؕؑؐؓ؍؎فؕ؉ؑؓؔؒ؉ؐقفؽ؉ؾؓف،؉؍ؒفؿ؎؏ـؒؐؕؾؽ";
        array[18][0] = "NTgHATbOqmIL0k/Dp04x7A==";
        array[18][1] = "ف؏قؽـ؎ق؍؉؏؎؏؍؉ؐف،ؒ؉ؾؽؾؾ؉؍ؕؒؒؾؔؽفؒؿؽـ";
        array[19][0] = "SH5W61wRoFk8YuZJ/pAbKw==";
        array[19][1] = "؍ؔؐؿؐؔؾـ؉ؾـؔؔ؉ؐ؏ؽؔ؉ؽـؑ؍؉ؓؐ؎ؓؓؾؕؾؐؾؐف";
        array[20][0] = "ka7oeB2N5bSGafFpCLEiKQ==";
        array[20][1] = "ؾ؎ؒف؍ؽ؏ف؉،ؐ؎ق؉ؐ؎،ق؉ؕؓؒؐ؉؎ؕؿ؎؎ؒؕؒؾؿؓـ";
        array[21][0] = "ktBAvtwuLJp7uNyFuewTeA==";
        array[21][1] = "ؔـ؎ـؓؓـؔ؉ؾؒـ؎؉ؐ؍ؐؔ؉ؽ؎ؓ؍؉ؽقؽؑ؍ؽؿـ؍فؑؿ";
        array[22][0] = "TIoB+Yp+OkEomJ+4/GMRfw==";
        array[22][1] = "،ــؿقفـ؍؉قؒـؔ؉ؐقؔؽ؉ؾق؍ؽ؉قفؿؕ،؏؏قـؿؔؒ";
        By1337̴̷̢̨̛̩̩̦̮̣̼͙̤̰̣̦͈́̾̀̀ͮͦ̚͡͏̞̯͚͉̤͉̗͂̿̋́̊͢͝();
    }

    private void By1337̴̷̢̨̛̩̩̦̮̣̼͙̤̰̣̦͈́̾̀̀ͮͦ̚͡͏̞̯͚͉̤͉̗͂̿̋́̊͢͝() {
        array[23][0] = "a0U/W0H/mTyqZ6Y80Pn87A==";
        array[23][1] = "ؿؑؔ؏ؓ؏؍ؽ؉فؿؔ؏؉ؐق؎ـ؉ؕ؎ؾؕ؉ؾفقفؒق؏؏؎ؿؔؽ";
        array[24][0] = "UdMzL2AnG141ZBXAEsEdkw==";
        array[24][1] = "فؑ؏قؾؓـؔ؉ؕؕـ؍؉ؐؕؒـ؉ؾؑـ؍؉ؐؿ؎ؔؽؽ؍ؐ؍ؾقؿ";
        array[25][0] = "Ua1EJHdhEGoTejVl+8HeKw==";
        array[25][1] = "ؓ؏ؿ؍ـؕؑ،؉ؕؒؑ؍؉ؐؑ؏ؕ؉ؔ،ؓؓ؉ؿ،ؓؾ،؎ـؾؔؔ؏؏";
        array[26][0] = "1We+oRghBUjFguuUSl1F9A==";
        array[26][1] = "؎ؿفففؾؿ؏؉قؐ؎ؓ؉ؐؕؓؕ؉ؾؑؐ؏؉فـقؽ؍؍قؒؿؓؐؿ";
        array[27][0] = "J3rZiWzjWgk1S03IydlUqg==";
        array[27][1] = "ؐؐ؎ق؍؎ؾـ؉ؾ،؍ؒ؉ؐؒؓؒ؉ؾ؏؎ؽ؉؎ؐؔؿؾ،ؕؐ،ؑؿف";
        array[28][0] = "HvRhjmkz3RvrWlwcxi+uhA==";
        array[28][1] = "ــ؏ؽؓؾ؎ؑ؉ؕؕ؎،؉ؐ،ـ؏؉ؕفؔق؉؎قؓقؑؔؓؽ؍ؒؕـ";
        array[29][0] = "ZdthMI+a1UsrAKvISh2VzQ==";
        array[29][1] = "ق،ؿ؍ؔفؽ؏؉ؔؕ؏؏؉ؐؑؔؔ؉ؔؿ؏ؔ؉ؒؑ؎ؑؽف؍فـؐ؏ؓ";
        array[30][0] = "ljVtcP7ALrYgIJj2gjY1GA==";
        array[30][1] = "؏ؑـؐقؓؿق؉ؑ؎ؽؿ؉ؐؾ؍ؐ؉ؕ،قف؉ؓؿ؏ؾؾؑؔ،ؒقؿؑ";
        array[31][0] = "99qpcOxTe4vdZiC0NqGOmg==";
        array[31][1] = "فؕففؔفؽؾ؉؏ؾـؓ؉ؐفـؕ؉ؾؿؕؽ؉ؿ؍ؑؑؐ؏قؿؓفؾؽ";
        array[32][0] = "5JRCSm41ixGjSY9XvJwthQ==";
        array[32][1] = "ؿؓؕؐق؍ؿؾ؉قؓقؿ؉ؐؑ،ف؉ؔ؍ؓ؍؉ؓؾ؏ؿ؎،ؒ؎قؑــ";
        array[33][0] = "2mDOjRsrhqGzdHGlK49rZQ==";
        array[33][1] = "؎؎؍؏ؔقؿؿ؉ـ؎؎ؒ؉ؐؔ،ؽ؉ؾؒقؿ؉ؽؓؓ،ؓـ؎ؑ؏،؍ؾ";
        By1337̧̢̢̛̛͔̹̝͕͇̠̻͔̯͇͉͙̠̝̜͔̓ͫ̓̀̀̑̿͌̌̑ͯͭ͂͋͟͠͝͠͠();
    }

    private void By1337̧̢̢̛̛͔̹̝͕͇̠̻͔̯͇͉͙̠̝̜͔̓ͫ̓̀̀̑̿͌̌̑ͯͭ͂͋͟͠͝͠͠() {
        array[34][0] = "Y0pV5uAKif823x1cJNf8sw==";
        array[34][1] = "ؽؒ؍ؒؕ،؏ؐ؉ؑؑ؍ف؉ؐـؿؐ؉ؔؕ؏ؐ؉ؽؒـؿ،ف؎ؾ،ـ؍ؒ";
        array[35][0] = "kDxugN+vDcCeRLLTQ+hrOQ==";
        array[35][1] = "ق؍ؓ؏قؐؐ،؉ؔؔؾ؎؉ؐؽ،ـ؉ؕؒـؿ؉ـؔ،؏ؓ؏قـقؔؾؐ";
        array[36][0] = "UP5aRpGXLrhrgAL32GL/+g==";
        array[36][1] = "ؒـ؎،ؕ،؏؎؉فؾ؎؏؉ؐ؏ؒؐ؉ؽؽؽؔ؉؍ـؽ؏؎ـؓ،؏ؽؕؔ";
        array[37][0] = "E8BGuqhJpV2of6uDKc54Iw==";
        array[37][1] = "ؐقؔ؏؏ـؾق؉ق؍ؑ؎؉ؐؓؒؐ؉ؾؕـؐ؉ؐؽؽ؏ؑؾؿؔؔ؏؏ؔ";
        array[38][0] = "aL3yMLRE566XKpK+BjWkJQ==";
        array[38][1] = "ؓؕؑؾؓؓؔؒ؉،،ؒؾ؉ؐؓؓؿ؉ؾ؍؎ف؉ؑؕق،؍ؐؔفؔؓ؍ؑ";
        array[39][0] = "MHRANwJUzDfIroRmMeJ2Ow==";
        array[39][1] = "ؿؓؕؓؕؿؒـ؉ؓقؕؕ؉ؐؓؒؑ؉ؕ،ؔؾ؉ؽ؏ؐؾؾؿؕف؏ف؏ف";
        array[40][0] = "h58Oc5HjS9y59I5rSly2yA==";
        array[40][1] = "؍ف؏ؕؔـؐؿ؉ؑـؐؓ؉ؐ؏ؽ؏؉ؾؿ؍ـ؉ؿؒؾـ؍ؔؕؐؕقؾ؎";
        array[41][0] = "LsRV/F2IQuuM/v2ue8mbnw==";
        array[41][1] = "ؑؓق،؏ـ؏ؒ؉ؑفؕف؉ؐ؏ـق؉ؔؑؐؓ؉،ؓؑففـؑؓـؓؔؐ";
        array[42][0] = "Brxi/LWDaWwg4AJyPx6qHw==";
        array[42][1] = "ؽ؍؍ؾؽ؍ؒؐ؉؍؏ؓ؎؉ؐ؍ف؍؉ؾقفـ؉؏ؽؿقؔ؎؏؎ؔ؎ؾف";
        array[43][0] = "9yBajES5y0Rxo3XzSOTe0w==";
        array[43][1] = "ؔؐؾـؿ؎ؔق؉ؕؿ؏ؾ؉ؐؾؾؕ؉ؽ،ؕؒ؉؍ؽ؏ؑؿؔ؍ؿؑ؏ؓ؍";
        array[44][0] = "NQhdcxgOzBv+oLnk0vK4GQ==";
        array[44][1] = "ــؐؕؑـقؑ؉ؾؐؓ؎؉ؐؒ؏؍؉ؕؔ؏ؿ؉؏ؒق؏ؕؿقؒؽؒؿؿ";
        By1337̷̡͖̹̬̥̜͎̞͚͈̣̋ͦ̿ͪ̆ͯ̑ͥͨ̇͛̆̾̽ͧ͋̔ͦͫ̂̐̔ͪ͌͟͢͞ͅ();
    }

    private void By1337̷̡͖̹̬̥̜͎̞͚͈̣̋ͦ̿ͪ̆ͯ̑ͥͨ̇͛̆̾̽ͧ͋̔ͦͫ̂̐̔ͪ͌͟͢͞ͅ() {
        array[45][0] = "ZbynY5Szqej080YF8at8AA==";
        array[45][1] = "؍فؾؒؿؑؾ؏؉ؕؔف؎؉ؐؕقؒ؉ؽؓؒ؍؉قؑؽؽفـؽؓق،ؐ؎";
        array[46][0] = "/Cm63++99K4k4piohKiDtg==";
        array[46][1] = "ؑؓ؎ؕـؾ؍ؿ؉ف؎ؽؔ؉ؐؕ؍ؑ؉ؕـؽؐ؉ؔؒفـؐ،ؒؿفؓؔؕ";
        array[47][0] = "5s0K2hGA4KB1ptXOQnZ0Ew==";
        array[47][1] = "ـؔقـؔؽؿ؎؉؍ؾؑ؍؉ؐ؎ؔ؍؉ؽــؽ؉ؑؾؕقف؍ـقؿ،؍ؿ";
        array[48][0] = "U+C657EqY+lGKZsTkxsEoA==";
        array[48][1] = "قؓ؏ؿ؍ؐؾف؉فؔؾؾ؉ؐؿ،ؓ؉ؾؔ؍ؕ؉؎ؐ؍ق؎قؔؐ،ؿ؏ؑ";
        array[49][0] = "VDI1I09GAFRPqjzFtsSCAQ==";
        array[49][1] = "ققؑؑؕؿ،ؽ؉ؑـؿؿ؉ؐؔؐؕ؉ؾ؎؏ؒ؉ؽؒؒـ؎ؾؾ؎؍ؑؑـ";
        array[50][0] = "CyLhQvekMlu82HRy0l34zQ==";
        array[50][1] = "؎ؿ؍ؐؑؾ؍ؔ؉ؔؔ،ؾ؉ؐ؎فؾ؉ؔؓؑؾ؉ؿ،ؕؔؕؑؿؐـؿؔؾ";
        array[51][0] = "vcGLeSd6NDkdqTpzGF7uEA==";
        array[51][1] = "ؾؿؿ،ق؎ؒـ؉،ؒ،ؿ؉ؐ؎؍؏؉ؕؿ؏ؽ؉ؓؔؾـ؏قفف؍ؿؕؐ";
        array[52][0] = "u+s9kBQZM620B91ONP7ylA==";
        array[52][1] = "،ؕؽ؏ؿؿؐؿ؉فؾ؎ؾ؉ؐؒؔؓ؉ؾقؓ؏؉ؑؾ؏فؾؒؕؑؽؑؑؔ";
        array[53][0] = "0i3CUf8MLHMNBCVIbAwTRg==";
        array[53][1] = "ؐؓ؏،ؒؐ،ـ؉قؐـؕ؉ؐققؓ؉ؕؓؾ؏؉ؐؽؾق،؎ؓؐؿؒقـ";
        array[54][0] = "a1EsDyEWV1+y7t5gmUuUiw==";
        array[54][1] = "؍ؑقؿـؒ،ق؉فقؐ؍؉ؐؐفؽ؉ؔؐؿؒ؉،ؿؓؾفؓ؎ـؓ؍ؔؽ";
        array[55][0] = "4677H4ltM7rvRb4BdQdAzA==";
        array[55][1] = "ؑؔؽؕؿفؿؒ؉ؔؑؿ؏؉ؐؕؔؓ؉ؽؑ؍ؔ؉ؑؐؑؽؔؿ؎ؔق؏ؔـ";
        By1337̸̵̨̨̫͚̯̥̺̦͎̙̼̜̠͓ͣ͋̌̈ͩ̅̈́͊ͣ͐̑͒̓̈̿̾ͬ̐́ͯ̓ͮ̕͠();
    }

    private void By1337̸̵̨̨̫͚̯̥̺̦͎̙̼̜̠͓ͣ͋̌̈ͩ̅̈́͊ͣ͐̑͒̓̈̿̾ͬ̐́ͯ̓ͮ̕͠() {
        array[56][0] = "lrsdwONdGNxGHz6Q+8AyMQ==";
        array[56][1] = "ؿؒ؍ؿ؏فـؾ؉ؑـؑـ؉ؐقـؐ؉ؕق؏ق؉ـ؎،ؽ؍،ؒؐؓ؎ؾف";
        array[57][0] = "jF+eBPy7PN0Af/e1F7HOPg==";
        array[57][1] = "ؑ؏،ـ؎؎ؽؕ؉،ؓؓ؍؉ؐؾؿؽ؉ؾؿؑـ؉ؔؔـؽؓؑ؏ؐـف؍ؾ";
        array[58][0] = "wnECXJbR+azXP4tW6xGSWQ==";
        array[58][1] = "ؾقؔـؾؾؕؕ؉ؽقق؏؉ؐؕؿؕ؉ؾ؎،ؾ؉ـؔؾؽققؑؒؽ؏؏ؑ";
        array[59][0] = "/8zY8xecRTaSn2ovyEzzBw==";
        array[59][1] = "ؿؐ؎قؽؒؿ؎؉ؐ؏ؔـ؉ؐؕ؎ف؉ؔؿفؾ؉ؓققفؔؽ،ؒؐؕؓؑ";
        array[60][0] = "NbtRLwuvol4/UTvx/XgxsQ==";
        array[60][1] = "ؑؔ؏ق؏ؓؐؓ؉ؿ؍؏؏؉ؐ؏ؐ؏؉ؽ؎ؓق؉،؏ؓقؕؿف،ؓؾفؑ";
        array[61][0] = "MN0/ERf8Pri34PrUJCrB/Q==";
        array[61][1] = "ؕفقؓؾقؕؾ؉ؐؓؐؾ؉ؐؓ؏ؽ؉ؔؾؕؿ؉ؽؑؿؿؒؒؑؑؐؾؐؓ";
        array[62][0] = "YbVnTiFZK9KQTVzWfQtgOg==";
        array[62][1] = "ؔؾؾ؎ؓفقؽ؉ؐـؓؐ؉ؐ؏ؓـ؉ؔؓؾف؉؏قفؿقف،قؑفؽؑ";
        array[63][0] = "pfzcrDYe9gzFNixX0dl5Kw==";
        array[63][1] = "ف؍،؏ؑ،ؒؿ؉؍؍فؔ؉ؐفـؕ؉ؔؓؑؑ؉ؓ؍ـؐؑؕـؿؿ؏؏؏";
        array[64][0] = "1ruCUCfjhfJB9rIm3d1Krw==";
        array[64][1] = "فـؑؿـؕ؏ؕ؉ؑؿؾف؉ؐقؒؿ؉ؾؿؔف؉ؓؒؐؐؿؒفؿؔف؏ؾ";
        array[65][0] = "7ps0wbTyt3JAul9wa/ouYw==";
        array[65][1] = "ـؿؽ؎ؕؿؕف؉فؾؽؓ؉ؐؓ؏ؑ؉ؕ؏قؾ؉ؓ،ؐؑؾ؏ؑق،؎،ؐ";
        array[66][0] = "sdL/NBLbrr14JjTQPvs32A==";
        array[66][1] = "ؽ؎؍ؔفففؽ؉ؒؓ؎،؉ؐق،ف؉ؾؔـؽ؉ف؏ؒؿؒؐؾؑ،،ؕـ";
        By1337̷̴̥̞̲̤̙̣̯̥͍̥̩̠̖̫̞͂͋͒̿ͮ̆̊͑̂͋̎ͣ̾̐̊̓͊̂͘̚͢͞͝();
    }

    private void By1337̷̴̥̞̲̤̙̣̯̥͍̥̩̠̖̫̞͂͋͒̿ͮ̆̊͑̂͋̎ͣ̾̐̊̓͊̂͘̚͢͞͝() {
        array[67][0] = "HxjF5gfqyJN4IXJ9xaZvwQ==";
        array[67][1] = "ؔؓ،ق؍ؾؔ؎؉؏؍ؐؔ؉ؐؽؽف؉ؾؽؕؑ؉،ؐـؕؕؿؑ؏ؓؐ؏ؒ";
        array[68][0] = "uT8wbcbALM/tyTOS2g0lQQ==";
        array[68][1] = "قؒؿ؎ؽـؾق؉؏ؑؓؿ؉ؐؾ؏ؔ؉ؕؒقؓ؉،ؐؐ،،قؓ؏ؓؓؿف";
        array[69][0] = "ARCDISkrYTa5i2jAKLFbcw==";
        array[69][1] = "ؒؒ،؏؏ؐ؏؏؉؍ؐ،؏؉ؐـؔ؎؉ؔؑؿؾ؉ؕـقـؓ؍ؑؓؾؕؐـ";
        array[70][0] = "3GU6BOMp5+mVtUVm+IBcLQ==";
        array[70][1] = "ؾؕؐ،ؑؿؒ؍؉ؓ؍قؔ؉ؐ؍ؕؓ؉ؔؓؾ،؉؏ؿقؐؿؒؿؒؕؐؒؾ";
        array[71][0] = "chSNxcTgS0DM4/xoeP8rew==";
        array[71][1] = "؍ؓؾقؾـف؍؉ؑ،ؔؒ؉ؐؕؑؑ؉ؽؽؔؔ؉ؿؕؾ؏ؔؾؐؒؽؐؓـ";
        array[72][0] = "XtHIEav7o9rAO6Y8fSrkLg==";
        array[72][1] = "ؑؔؾؕؓ؏ؑؓ؉؎ؑؾؒ؉ؐ؎؏ؑ؉ؽؒقؑ؉ؿق؍ـفؽؑؽؐؿؿؐ";
        array[73][0] = "+ob8SzmRY3l4hoUX4fT4lA==";
        array[73][1] = "ؽؾؔـؕؓؽ؎؉قؑؕؐ؉ؐ؏ؐؓ؉ؾؽف؍؉ؿؔ؍ؔؐؒؔؔؾ؎ؓؿ";
        array[74][0] = "5/wY1+WFcgWg+5gFirGdfA==";
        array[74][1] = "،ف؏ؐؿؔؐـ؉ؾ؏؍ؓ؉ؐؐفف؉ؽؑؐؾ؉؍ؾؾؾققـ،ؿؾ،ؿ";
        array[75][0] = "Q72Nv8RNe2o3A4gQkr7BQA==";
        array[75][1] = "ؔؕ؎ـؑؑؾف؉؎قؔف؉ؐؔؾف؉ؔـؒؽ؉ـؐ؏ؑقؒؿؾؿؿؿؕ";
        array[76][0] = "hEF5rZO6MgD1MU+eJ45F0w==";
        array[76][1] = "ؽؾففؔ؎فف؉ؿؒف؏؉ؐؕؾؽ؉ؾقؓؔ؉ـؔ؍؎ؓؑقؔ؎؏،ؕ";
        array[77][0] = "+oxBoRjuLiFN7t0LKE8tPQ==";
        array[77][1] = "ؾؾ،ؽ؍ؑقـ؉،ؾـق؉ؐؑؓؕ؉ؾ؍فؐ؉ؕؑؒؒ،ؒ؎،؏؍؍ؽ";
        By1337̘̱̣̲̠̉ͧ̓͌͏̷̨̧̟̳̲̜̣̩̯͓ͧ̈͋ͬ̇̅ͫ̌͂̑͐ͤ̉̈́̂̔̚͜͝();
    }

    private void By1337̘̱̣̲̠̉ͧ̓͌͏̷̨̧̟̳̲̜̣̩̯͓ͧ̈͋ͬ̇̅ͫ̌͂̑͐ͤ̉̈́̂̔̚͜͝() {
        array[78][0] = "nJShurduzOBI+awKObwKLQ==";
        array[78][1] = "قؑؽؿؐقؐؔ؉ؕؓؽ،؉ؐفؕ؎؉ؔؿ؏ؓ؉ؿؾ؍ؓؿؽؓؔ،؏،ق";
        array[79][0] = "piH6weuB/0BbFzdxvlNW+w==";
        array[79][1] = "قـؕ؎ؑ؍ؕؒ؉ؿؾق؍؉ؐؔؑق؉ؔؾق؎؉؎ؽؓفؐؽقؐؓـؓؒ";
        array[80][0] = "FBZKBMdKhsaMWVzSj1sfjw==";
        array[80][1] = "ؓ،ؓ؎؍؎ؾ،؉ؒؑؕؕ؉ؐـؐ؎؉ؔؿؕؿ؉فؽؑؒـ؍قؐ؍ؓؐؑ";
        array[81][0] = "Iyn2jpdnBFjVzXvZIA918Q==";
        array[81][1] = "ف؎ؕ،ؔ؏ؿؐ؉ؔؐؒ؏؉ؐ؏ؓؑ؉ؔ؎،؍؉ؓؾؓؕ؏ؔؿؿؕ؏ؔؓ";
        array[82][0] = "bExjO64TBxA+ef051Qj4+A==";
        array[82][1] = "ؽقؾؔ،؍؍؎؉؍ؑؓؿ؉ؐ؎ؾؒ؉ؽؿؑؿ؉ؑؓؿفؒ،ؾؒؽـؽؿ";
        array[83][0] = "8R0ttCMRyV4Mp9Vz+QB9SA==";
        array[83][1] = "ؕققؐ؍ؕؔؿ؉ؾـؽ،؉ؐ؍ؿؓ؉ؕؐؒؕ؉؎ؑؿؒؕؑفؕ؍ؿؑؓ";
        array[84][0] = "PDqWqdxloLS6M+HqmZRfGA==";
        array[84][1] = "؍ؕفقؐؕــ؉ؔ؍ؓ،؉ؐؽؑؑ؉ؔ،،ؽ؉ؽ،؎ؕ؎ؓؾؾفـ؏ؽ";
        array[85][0] = "z7bma6PnlrQJRb5AdxCYuQ==";
        array[85][1] = "قؔ؎ؒؕقؐف؉ؓ؍؏ؾ؉ؐؓقق؉ؾفؽؐ؉ؒقؔقؒ،ؽ؎ف،فق";
        array[86][0] = "GcLuABK89nGhe8wqYcv+PA==";
        array[86][1] = "ؿؓـقؿؕ؎ؐ؉ؒف؏ؓ؉ؐ؍؍ؾ؉ؽ؏فؿ؉فؑؽؑؓقف؏ف؏ؑؔ";
        array[87][0] = "4AegpWtcAVDQSOncYf2wuQ==";
        array[87][1] = "ؐفـ؍،ق؎ؒ؉ؕقؑؿ؉ؐؐؕؐ؉ؽؒؽؾ؉ؾـؿ،فؐؽؒؔؐؓف";
        array[88][0] = "BsP9jcny6ic3Xrio+IShUw==";
        array[88][1] = "؏ؒؐ؎ـؐؽؔ؉؎ؽ؍ؑ؉ؐؓؕؾ؉ؕ،ؽ،؉ؑـؾؓؔ؏ؒ؏ؒؽؽؐ";
        By1337̸̸̛̞̳̲̖̝̯̥̽ͯ͆͐̓̑̀̄͂͂̾̎ͦ͑̄͐̃̋͌ͩ̐͐͛ͯ͊͘̚͘͜͠();
    }

    private void By1337̸̸̛̞̳̲̖̝̯̥̽ͯ͆͐̓̑̀̄͂͂̾̎ͦ͑̄͐̃̋͌ͩ̐͐͛ͯ͊͘̚͘͜͠() {
        array[89][0] = "XL7NmX1ZH/YGpHMm7MWFVg==";
        array[89][1] = "ؑؑفؔؒؔؿـ؉ؾؾـؐ؉ؐ؎،ؿ؉ؕ؏قؓ؉؎ؕؾؓؓؐؾـؿؓؾؒ";
        array[90][0] = "/D18X9of9n2MwkWF78Jo5A==";
        array[90][1] = "ؑؔؿقؐؾ؏ؔ؉ؑ؎ؿؐ؉ؐؕؔؾ؉ؕؽؾؽ؉؎ؑفؽؕؐؕؒ؎؏ؒ؏";
        array[91][0] = "4xSTpWqA1Z9K2kj4zllnwg==";
        array[91][1] = "ؐ؏ؑ،ؽؿؿؐ؉؎؍ف؎؉ؐ؍ؕ؏؉ؾؿ،ق؉ؾ؍ؿؑ؍ؐؾؿ؍فؾ،";
        array[92][0] = "UO3R4FsFU0/MUKr74Ta1yQ==";
        array[92][1] = "؏ؔؓؑ؎ؽ،ؐ؉ؒؿ؍ؕ؉ؐف؎ـ؉ؾؽ،ـ؉ؔ؏ؾؓؔـؿؕؒ؏ؾف";
        array[93][0] = "aZxAAy/ywas3jL83Qz+SVQ==";
        array[93][1] = "؏؍؎ؓؐ،؎ؐ؉قؐ؎ؾ؉ؐ؏ؑ،؉ؕؕؒؽ؉قؓؽ؏ففؽؔؽؕؕف";
        array[94][0] = "tygbOLm7nNEW7+dQQugwqg==";
        array[94][1] = "ف،ؔقؓ؎،ق؉ـؿ؍ؓ؉ؐؒؽـ؉ؔؿق؏؉؎؍ؿؽؾؕؓؔ،،؍ؑ";
        array[95][0] = "D2PeCIWcC0AkiHf7vKfdUw==";
        array[95][1] = "ؒ،؍ف؏؏ؕ؍؉؍ؕؕؐ؉ؐ؎ؽف؉ؕؓؽؒ؉ؑؒ؍ؽؒ،ق؎ـؒ؎ؐ";
        array[96][0] = "ZO5cy2qIfaNPuCmtZ7Q9Ow==";
        array[96][1] = "ؽؑؽؑ،ـؒؿ؉ؒقؽؑ؉ؐ؍قؔ؉ؕؾق؍؉ؽ؎ؓ؍؎ؕؔؔـ؏ـؕ";
        array[97][0] = "5Rp13uTfsV6HQ7ATCijxAA==";
        array[97][1] = "ؕ،ؓؾق؍ـؑ؉؏ؐؐؽ؉ؐ؍فؕ؉ؽق؍؎؉؏؏ؾـؒؽقؕؕؔؑف";
        array[98][0] = "tvUrC9FFtTovMEDByLOTcw==";
        array[98][1] = "ققف؎،،؎ؒ؉؏،ؐـ؉ؐ،ؽؿ؉ؕؑفؕ؉،فؿق؍فؿق؏ـؓف";
        array[99][0] = "O63V5Yf4uYDG12/E9/BiGw==";
        array[99][1] = "ؒؕ؎ـ؏ؿ،ؐ؉؏فؒؿ؉ؐؒـؽ؉ؽ؏؏ؒ؉ؾ؎ؽ؏؎،،ؿق،ؓؽ";
        By1337̶̨̧̱̯̠͚̗̠̜̹͉̙̟̼̝̞̱̃́̆͊̉̊ͤ̒͂͂̾ͭ̾͗̃͌͘͘̚͟͠ͅ();
    }

    private void By1337̶̨̧̱̯̠͚̗̠̜̹͉̙̟̼̝̞̱̃́̆͊̉̊ͤ̒͂͂̾ͭ̾͗̃͌͘͘̚͟͠ͅ() {
        array[100][0] = "cmlAbKKa7IP453PD/gXAzQ==";
        array[100][1] = "؎ؾ،ؐؽ؏؏ق؉ؕؓفؾ؉ؐؑؕ؎؉ؔؑؔؑ؉ؽؕؽ؏ؽؾ،ؽ؍ؽؐؔ";
        array[101][0] = "DZb65yzBHggoY5vWbkds9A==";
        array[101][1] = "ؒؐقف،ؾؽف؉فؽؑؔ؉ؐفف؎؉ؽ؎ؿق؉ؔؕؕ؏ؓؒؽؕؑؐ؏؍";
        array[102][0] = "4xSvaIkTF1eOlalz/S7RPw==";
        array[102][1] = "؍ؾقؾؑؾؒف؉ؔــ،؉ؐؿؽؽ؉ؾ؍فؐ؉ـؒؒؑ؎ؒؐقؔ؏،،";
        array[103][0] = "drGEZoD553HWmjeAmGZPFg==";
        array[103][1] = "ؓ،؏ؒؒؿؾؓ؉ؒفؐؑ؉ؐؐؑ،؉ؕؓؒؕ؉؎،ففؿق؍فؒؓؑف";
        array[104][0] = "h4rGfhznbAJp2m/IpX92kg==";
        array[104][1] = "ؕؽقؾ؍ـ؍ؕ؉ؒ،ؽ،؉ؐ؏فؒ؉ؽ؏؍ؽ؉؍ؑؽؾؾؓؔ؎؍ـؽؓ";
        array[105][0] = "r4LgFL/ruUmsWVm/Qm/hPQ==";
        array[105][1] = "؎ؑؔــ؏،ؕ؉؏ؿـؐ؉ؐؒ؏ؒ؉ؔؔؑ؏؉قـ،ؿؓؾؓؿؔقفـ";
        array[106][0] = "CZhVlnZbFIrz5kulOITg+Q==";
        array[106][1] = "ؐؿ؍ؿؽ؏ؾ؏؉ــ؎؎؉ؐ،ـؿ؉ؕقؔؕ؉ؽـ،؍ؑ؏ؒؒؾـفق";
        array[107][0] = "Z475qoOjerTr8bLCPfYDwQ==";
        array[107][1] = "ـ،ؿـؓؔـؾ؉ؕؒ؍ؒ؉ؐؐؑ؎؉ؕ؏؎؎؉؎ؽؔـؓؕؒ؎ؽؐؿؓ";
        array[108][0] = "C5U0C8k6MW94/ZQ8cCRMXw==";
        array[108][1] = "ؓؕ؎قؾؽؓؐ؉فؾؾق؉ؐ؍ف؏؉ؽؐؽ؏؉؍ـ،؎؍قؽؓ؍؏؍؎";
        array[109][0] = "b6NCnyPsTXZXxBp068Nj+g==";
        array[109][1] = "ؕق؎ؾؽ،ؿؾ؉ؔؕ؏ؽ؉ؐ؏ق؎؉ؽفؾ؏؉؍قؒؑـف؎ؑؒؽؓق";
        array[110][0] = "aa+U0HDejzy5ubqCPt2Ulg==";
        array[110][1] = "ؓؓؓؕ،؏ؒؕ؉ؿؔؽؕ؉ؐؑؕؐ؉ؽ؍ـؾ؉،ؔؿؔؽؽؽ؍ؾؕفؑ";
        By1337̴̲̞̯͓̣̦̱̭̖͆̅́̾͆ͦ̔̃̎ͭ̀̃̀̎̑ͮ̓̂̚͜͝͞͏̨͙̰͖ͭ̿͢();
    }

    private void By1337̴̲̞̯͓̣̦̱̭̖͆̅́̾͆ͦ̔̃̎ͭ̀̃̀̎̑ͮ̓̂̚͜͝͞͏̨͙̰͖ͭ̿͢() {
        array[111][0] = "UKayQUNohppkUaiiv1uurg==";
        array[111][1] = "قؔؒ،فق؏؏؉ؕؑؿؾ؉ؐؾؾؕ؉ؾ؎ؾ،؉فؑؒ،؏ـؾقفؑ؎ف";
        array[112][0] = "qWa5YxOlp5Fm4Xt85ewXRA==";
        array[112][1] = "،ؕؓ؍ؒؑؔؽ؉ؑـؑ؎؉ؐ؎؍؏؉ؾؒؒق؉؎ؕفقؕؓؔ؎،ؿؔؕ";
        array[113][0] = "D7vuWs/lgtVrDYx8+n4S/A==";
        array[113][1] = "؎ؽ؎ؒق؎ؾ؏؉فقؒـ؉ؐؾؑؑ؉ؽ؏قف؉ؓ،ف،ؓ؏ؐؓؓؿقؑ";
        array[114][0] = "4Au1/mNTHgLH5aL4b3TUPQ==";
        array[114][1] = "ؾ،فؾؾؓؽ؎؉ق؏ؾؑ؉ؐؑؔق؉ؽقفف؉؎قؓؔفقؽؑؕؿؽؽ";
        array[115][0] = "yeJOb//3mUn27rL6zZkMRg==";
        array[115][1] = "؏ؽ،ؒؔـفؽ؉ؿؕؒؾ؉ؐ؎ـق؉ؕؒؔ؎؉،ـ؍ؕفؕ،ؾؕؓؕؽ";
        array[116][0] = "4XSOYrPIL4yRF9n5Hx6YYA==";
        array[116][1] = "ؓؐؾؒف؎ؓؽ؉ؿ؎؏ؔ؉ؐ،ؔق؉ؾؔؓؐ؉ؔ،قؔقؑـؒؐ؍ؐؔ";
        array[117][0] = "HWzfzIpZFJaf6QbxdAcjwQ==";
        array[117][1] = "ؽ؍ؔ؏،ؐق؍؉ؒقؕؾ؉ؐؓؐؕ؉ؾؽؾ،؉ؾفؕؓؓؕؕ؍؍فؽ؏";
        array[118][0] = "nsKnbJbKd///kKG5FnPPBw==";
        array[118][1] = "قؓـ؎ف؍؏ؐ؉قؑؒؐ؉ؐفؓ؏؉ؾؾـؾ؉؍ؐؐفؑ؏ؒؑ؏ؿؒ؏";
        array[119][0] = "YFo9pSp76oRyREYblsVdUA==";
        array[119][1] = "؍ؐفؐؾـف؏؉ؾقق؎؉ؐفؿ؎؉ؔؓؿؔ؉ؽفؐ؏ف؎ؾؔ؎ف،؏";
        array[120][0] = "6TBy6rIiT51YEqPtsXC5QQ==";
        array[120][1] = "ؿؕق،ؾؐؑؐ؉ؒقؒؾ؉ؐ؏،ؾ؉ؕ؏ؕف؉فؒؾؾ؎ؑؔؿ،ؿؾؾ";
        array[121][0] = "1tWsu8Cnm9cyf9O2RNQRuw==";
        array[121][1] = "ؑ؎ـؾؒ؏؏؎؉؏،ؑ؏؉ؐـؒ؏؉ؕفؒؽ؉؏ؒ،؏ؑؐؔؾ؎فـف";
        By1337̪̟̰̜̩̝̹̠̗̫̞̻̼̤͎͂̓̓ͣ̅ͯ͛̽͐͛͊ͦ͋̏̾̀ͬ̋̊̔͒̎̚͝͝();
    }

    private void By1337̪̟̰̜̩̝̹̠̗̫̞̻̼̤͎͂̓̓ͣ̅ͯ͛̽͐͛͊ͦ͋̏̾̀ͬ̋̊̔͒̎̚͝͝() {
        array[122][0] = "Vdv1L9ybDqziy49ftG66RQ==";
        array[122][1] = "ؐؔ؍ـؕـ؎،؉ؓؑؑف؉ؐؽ؎ؒ؉ؽؾؔ؍؉ؾؒؽؔؿ؎ؐؓ؏ؓؿؔ";
        array[123][0] = "gtM4BQbmIUbfZTEaOkN49w==";
        array[123][1] = "؎ؿؐؕؿفؽ؍؉ؕؑـؔ؉ؐفؑؐ؉ؾؒؿؐ؉ؿؐ؎ؓ،ؐ؍ؒؒؑـ؍";
        array[124][0] = "KvtjCttctnOa8UkB22nMoA==";
        array[124][1] = "فؾؔقؕ،ؕ؎؉؍ؑفؒ؉ؐؿ؏ـ؉ؾؽ؏ؕ؉ؐؒؑؽؑف؍ؿ؎فؑؕ";
        array[125][0] = "TMNRpqVTZsz3RqIT7PN9ow==";
        array[125][1] = "ؒـقؒق؏؏ؾ؉،ؔ؏ؔ؉ؐ؍،ؽ؉ؾؾؿؐ؉قؽؓ؎ؾق؎ؾ؎ؒؽؽ";
        array[126][0] = "JI+hubjCEA35H6HSuTzZdA==";
        array[126][1] = "ؿؾقفؾؿف؏؉ؒـؔؕ؉ؐؾؽؑ؉ؕ؍ؒؓ؉؍ؾفؾؾ؏ؿؓ؍ؓ؍ؾ";
        array[127][0] = "4B9Q6nkLJxODgPPHogwYkQ==";
        array[127][1] = "فق،ؕؾـقؽ؉ؔؿ؍ف؉ؐفـؾ؉ؕؿؐ؍؉،ؒ،؍ؿؒفقؐؾقؔ";
        array[128][0] = "0MIG2CHPIbq0pqYlL1hxxg==";
        array[128][1] = "ؓ؎ؿؓؔ؏ؑؐ؉ؕؑؔؾ؉ؐقـؑ؉ؽقؔؐ؉،،ؒؔؽؔ،فؒـ؎ؔ";
        array[129][0] = "L50VVqIf6q8cXTXgSBqqrA==";
        array[129][1] = "،ؓ؎ؕ؎ـؓ،؉؏فؑؿ؉ؐ،ؒؓ؉ؕؽؒؿ؉قؾفؐفؑؕؕؿؓؒؐ";
        array[130][0] = "LZYq95WEnCrA8QT6zTZQcA==";
        array[130][1] = "ؾـ؎،ؔؐؓف؉ق؍،ؓ؉ؐقف؏؉ؕ؍،؏؉ؓ؏ؓقؕـ؏ؕؑـف؍";
        array[131][0] = "xubH7Q7ibaedbt4FUV4hUQ==";
        array[131][1] = "،ؑفؓ،ؑؽـ؉ق؏،ؒ؉ؐق،ؾ؉ؔؾ،ؑ؉ؽؐق،فف،ؐؕؿ؎ؿ";
        array[132][0] = "GwvmrmG9Adrdyw7Jdnwj6g==";
        array[132][1] = "ؿؕؓؐؕ؎،ؔ؉ـ؏،ؾ؉ؐ؎ق؏؉ؾؐفق؉؍ؐففؿف،ــؒقف";
        By1337̵̧̡͍̠̙̞̮̬̺̜̩̫͕͚̮͉̦̲͎̔͑̊̅ͬͦͦ͑̿̽͑̈́̄̋ͣ͢͢͞͠ͅ();
    }

    private void By1337̵̧̡͍̠̙̞̮̬̺̜̩̫͕͚̮͉̦̲͎̔͑̊̅ͬͦͦ͑̿̽͑̈́̄̋ͣ͢͢͞͠ͅ() {
        array[133][0] = "r8yxvCeeev2vgY0/lg/b3A==";
        array[133][1] = "فؔؑؒؾؓؕف؉ؔؿ،ؑ؉ؐ؎ؔ،؉ؕؕ،ؕ؉ؽؓؕ؏ؔقؑ؍ؕؿ؍ؽ";
        array[134][0] = "wQK/XM58P7PAi6rqkO4OcA==";
        array[134][1] = "ؽؒؒؔـفؾؒ؉ؓؽؾؿ؉ؐ؍،ؒ؉ؽؑؓؔ؉ؔ؏ؾؿ؏ـؑ؏ؓ؍ؐ؍";
        array[135][0] = "eVUaHjM7IR3MYH/ElHbAVA==";
        array[135][1] = "؍ؿ؍ؕؑؿؒؕ؉ؔ؏ؽؐ؉ؐ؏ؑ،؉ؔ؍ؕ؏؉ؐ؏؍؏ؓ؏ف؍؎ؒقق";
        array[136][0] = "0+sWlI/6C/d4Lzst51JxVA==";
        array[136][1] = "فؕ؍؏ؕق؎ؾ؉ؒفؕؕ؉ؐؒفؕ؉ؽف؍؎؉ؽؓؿ؍قؐ؎؍ق؎؏؎";
        array[137][0] = "2TxuQNQixkqshtgcTKb6dw==";
        array[137][1] = "؎ـ،،ؽ،قؿ؉ؕفؔؑ؉ؐؑؔؔ؉ؕؒ؎ؓ؉ـؑـقؽؓؒ؍فؽؓ؎";
        array[138][0] = "CUM2FCbcrwo4+IIOXLKF1Q==";
        array[138][1] = "ؐؐؑؽ،قؾؽ؉ؑ؏؍ؽ؉ؐؿؿق؉ؽؿؾؿ؉قؔ،ؔ،ؾؽ؏ؒؑ؎؎";
        array[139][0] = "wijYa0+mkAEt+wczxgqqrw==";
        array[139][1] = "،ؑؔؽقؑـؒ؉قف؏؍؉ؐؕؒـ؉ؕ،قف؉ؑقؕ؎ؾ؏ؑؾؾـؕ؍";
        array[140][0] = "uNBxqoEVBEQAyLfwHQRq8A==";
        array[140][1] = "؎ؓـ،،ؒؐؓ؉؏ؿؐ؎؉ؐ؎ؾ؏؉ؔؿؐ،؉ــ؎فؿــؕؿ،؏ؾ";
        array[141][0] = "t1MPYYWTI1Lst/lX0C+Vvg==";
        array[141][1] = "ـ؎ؕؒ؎ؐؒق؉ؿؒؑ؍؉ؐ،ؿؽ؉ؽؾؐ،؉ؑؽؓؕفؔ؍ق؏ؐ؏ؐ";
        array[142][0] = "zhROziTSK3VpFtAGMz5AJA==";
        array[142][1] = "ـؑؽ؏ؒ،ؽ،؉ؽؾ؏ؐ؉ؐف؏؎؉ؾ؍ؒؔ؉،ؿؔ؏ؕؿؔ؎،؎؎،";
        array[143][0] = "TOkX+Kc34YsYrDNOiK31WQ==";
        array[143][1] = "فؿ؏ؔؑ؏قؑ؉ؓ؍؍ؓ؉ؐؐـؑ؉ؔؽؐؽ؉ؑفؾق؎ؒؑؒؒؓ؏ؔ";
        By1337̵̴̡̢̧̩͎̩̫̳̘̟̭̱͕̲̞̦͓ͤ̔̑ͥ͂̌ͤͮͤ́ͪͦͤ̑͛͜͡͞ͅͅͅ();
    }

    private void By1337̵̴̡̢̧̩͎̩̫̳̘̟̭̱͕̲̞̦͓ͤ̔̑ͥ͂̌ͤͮͤ́ͪͦͤ̑͛͜͡͞ͅͅͅ() {
        array[144][0] = "t36q82kzKkwNbRJIzwG54g==";
        array[144][1] = "ؿؓؾ؍؏ؿؔـ؉ؐؕؓف؉ؐؑقق؉ؔـؿؾ؉؏ؑ؎ؿ؏ؒق؏؎ؐؐـ";
        array[145][0] = "vPJ3YH20mmdPMIVjTrdQMg==";
        array[145][1] = "ؓؽ؍ؾؒـؾؕ؉فؿ؏ق؉ؐفق؍؉ؔؾـؕ؉ؐؽؐؒ،،،قؒ،ؑؕ";
        array[146][0] = "mo91jIRmP+ADkT2FribLgw==";
        array[146][1] = "؎،ؒؒؑـؾؐ؉؍ؔفؕ؉ؐؒؽ،؉ؔؔقـ؉؏؍ف؎ؾؕ،ؽؔ؎ؾؽ";
        array[147][0] = "Euh4NjI3GXaxh+DXFDRLqw==";
        array[147][1] = "ؒؕ،فؒؔقـ؉ؔؽؕ؎؉ؐفؑ؎؉ؕؽؓ؎؉؎،قـؓؾق؍ؾ؍فؿ";
        array[148][0] = "i9nW8pEEot4fecwf4L9I2g==";
        array[148][1] = "،؍؏؏فؕقؒ؉ؔؔؓؔ؉ؐؐؾؒ؉ؕؾؕؑ؉ؑؓؓ؏؍ؒؾؐؒؑؒؒ";
        array[149][0] = "0JiLmA0+hzQZHW4YqMrARQ==";
        array[149][1] = "؎ؽؿؿؐؑ؎ؕ؉؏فؔف؉ؐؕؕؽ؉ؔؑؑـ؉ؐؐ؍،ؕؿف؍ؕؿؒ،";
        array[150][0] = "26ShFtnsEWD8mY2pa6343w==";
        array[150][1] = "فؔؒفؑ؎ؿؕ؉ؕؽـؕ؉ؐؒ؍ؐ؉ؕ،ؔ؎؉ؒؑ؍ؑ؍فؐفؿؿؔؐ";
        array[151][0] = "u4vSPDxKZBdy2izAzw+uBQ==";
        array[151][1] = "ؕ،ؽـ؏؍ؓؒ؉ؔؾـؾ؉ؐؾؽق؉ؽؑؒؿ؉،ؾؓؔ؍ـؾؔؒؓؿؕ";
        array[152][0] = "yvPbe4bdLUydCWYWy+0aeQ==";
        array[152][1] = "؎ؽ؍ؐفقـؔ؉،ؓؕؓ؉ؐؑؕؿ؉ؕؓقـ؉ـؐؒؐقؔ؎،ؽؒؓؾ";
        array[153][0] = "1oiYvTIXfi4BCVXS2w5ZUg==";
        array[153][1] = "قؑؔؽ؎ـؒ؎؉ؿؒ؍ؾ؉ؐؐـؑ؉ؔؽؑؾ؉فؕؽؒقؓـ؏ؔؔؑ؎";
        array[154][0] = "N5vw/I0TwFly12G02IvgxQ==";
        array[154][1] = "ؾـففـؑؐف؉ؑـؒؓ؉ؐؒؓؑ؉ؽؽق،؉ؕؕؔ؎ؕؾ؍؍ؕؕ؏ؾ";
        By1337̶̢̛̘̪͔̮̝̭̮̮̙͈̔ͫ͆̈ͩ͒ͫͣͦ͌̇̕͜͝ͅ͏̯̟͎͖͗́͒̄̾ͮ̚();
    }

    private void By1337̶̢̛̘̪͔̮̝̭̮̮̙͈̔ͫ͆̈ͩ͒ͫͣͦ͌̇̕͜͝ͅ͏̯̟͎͖͗́͒̄̾ͮ̚() {
        array[155][0] = "6Nr1U/xRuZP2oOTwtdLOOA==";
        array[155][1] = "؎ف؏؏ؾؽ،ف؉ؑؑؐؽ؉ؐـ؎؏؉ؽؕـ؎؉ؑؒـؒؓ؏؍ؓفؿـؐ";
        array[156][0] = "2lUORYf6VdJ0yQtyj4Sxlw==";
        array[156][1] = "؏ؕفؾقـؕق؉ؐؿؐؐ؉ؐؔـؔ؉ؽ؍ـؕ؉؏ؔؕ؏ؿؒ،؍؏قـق";
        array[157][0] = "1yy+UGttg3OuWCbuOmbxgg==";
        array[157][1] = "ؾؐؿؒؐؓؕؿ؉ؒ؍ؽق؉ؐ؍؍؏؉ؾ؍ؽؓ؉ؾ؍ؾؓؾؽؔ؏ؽؕ؏ؒ";
        array[158][0] = "4Fnfb1RUaKfGNwi6L1U8Pw==";
        array[158][1] = "ـؔؽؾؔؔقؿ؉ؒؒ؏؎؉ؐ،ؒؒ؉ؽؿ،ؿ؉،ؾؾؓؓؿؕؒؒ؍،ؾ";
        array[159][0] = "tnJn5QqKljNj5Xkm58LCLQ==";
        array[159][1] = "،ؿؔفـؾ؎ق؉،؎قؽ؉ؐؽؽؐ؉ؽؓقق؉ؕؽؽؔـؿؾ،ؽؑق؎";
        array[160][0] = "tqEStHVnvWkhiXTUEdfMCA==";
        array[160][1] = "؏ؓ؍فؓؾؑؒ؉قـؽؕ؉ؐؓؒ؎؉ؽـقؓ؉ؽؓؓؾ؍ؔؒ؏ؿ؎؎ؕ";
        array[161][0] = "NUqYPgYXXxl3/ur0LH/CPg==";
        array[161][1] = "؏قؑفؽؿؑؓ؉ؾؒؔؾ؉ؐؑؿؐ؉ؾؒ،ف؉؍؍ؽ؏ؓفقؕ،؎فـ";
        array[162][0] = "c3DEcLh5w7Kq6DfXeBMP7A==";
        array[162][1] = "ففؽفؑقؒؓ؉ؽ،ؕؽ؉ؐؐؽؾ؉ؕؔؿؾ؉ـف؍ؕؐؒؕؾ؎؏ؿ؎";
        array[163][0] = "+mrQaV1qv+sAjpFD+GyABw==";
        array[163][1] = "ف؍ؕـؕـ؍ؒ؉ؕؿؑؒ؉ؐ،ؒؔ؉ؕـ،ؒ؉ؾؽؾ،قؽؽؿفؒ؍ؒ";
        array[164][0] = "Majd3WVVpnpxlOgzxQI1pg==";
        array[164][1] = "ؔف؏؏ؑ؎ـؾ؉ؒؾفؑ؉ؐؕؾ،؉ؔؾ؍ؿ؉ؿ؏،ؽ؎ؾؐــ،ؓؔ";
        array[165][0] = "44HdYLnwtO7kS7u0Dahy0g==";
        array[165][1] = "؎قؕ؍ؐـ،،؉ؔؑؔؒ؉ؐؐؓق؉ؕؿ؍ؿ؉ؒـؕؓؾؾ؏؍ؐؓق؍";
        By1337̴̛̘̣͖̫̠͉̹̫̺̖̗͈ͭ͑̆̑̀͗ͪ̎̓͐ͬ̄̌̂͌ͩ͒̄̏͛̾͗͘͟͢͝();
    }

    private void By1337̴̛̘̣͖̫̠͉̹̫̺̖̗͈ͭ͑̆̑̀͗ͪ̎̓͐ͬ̄̌̂͌ͩ͒̄̏͛̾͗͘͟͢͝() {
        array[166][0] = "Gmxdh4/9gf7CJYJZGV3jkA==";
        array[166][1] = "؎ؾؐفقؐ؏ؿ؉؏؍ؿ،؉ؐؿ؍ف؉ؾؑؐ؎؉ؔؓؓفؑؓـؑؐ؎ؑف";
        array[167][0] = "4i+V/fL6ey0EFE1LtIb8Rw==";
        array[167][1] = "ؕفـفؐـؓق؉؎قؾؒ؉ؐ؎ؽؐ؉ؽؽـ،؉ؽؾؿؿؽؐ؏ف؎ؽـ؏";
        array[168][0] = "4O/wBo0JGxrmve36JrWmwg==";
        array[168][1] = "؏؏ؓؕ؍،ؒؐ؉ؾؑـ،؉ؐؑؾ،؉ؔؾ؎ؐ؉ؐؔؕؔؽ؏ف؍؏ؕؒؾ";
        array[169][0] = "cClFa/wbbybu6UbtBw4QLQ==";
        array[169][1] = "ؑؿؽؽفق؍ؽ؉ؕؿؕؔ؉ؐؕفؔ؉ؽؾقؒ؉ؽؔؾ،ؐؿؒ؍؏،ؑؒ";
        array[170][0] = "JBFQe2L0fOkjwqpR1mjTHA==";
        array[170][1] = "،؎؍ؕؓؑ؏ؔ؉ؑؒؐـ؉ؐؓؒف؉ؾ؏فؒ؉؎،؏ؿؾؔؒ؎ؒؓفؾ";
        array[171][0] = "Iz1ZUxivySFhMdI8R3cwxg==";
        array[171][1] = "؍ـؕؒؽؕؿؿ؉ؿؑ؍ؾ؉ؐــؽ؉ؕؐ؍ؑ؉ـؐفؓؑؐؑ؍،؏ـؔ";
        array[172][0] = "waEfFMLPMYt6teAwnr4wKg==";
        array[172][1] = "ؕفؔؐف؏قؔ؉ؿف؏؎؉ؐؔؓـ؉ؕ؏ؒؕ؉؏فؽقؓؓؔف؏،فؔ";
        array[173][0] = "YfXLu0z0Q/inXkoSWj8Evg==";
        array[173][1] = "؏ؒ؎قؓؿ؍ؾ؉ؿؕؐؑ؉ؐ؏ؕؽ؉ؾؓ،ؐ؉ف؍؏؍ؓؓفؕ،؏؏؎";
        array[174][0] = "h6hrJAGS29U+6SMwPii2ng==";
        array[174][1] = "؏ؔـ،ؾؿؕ؍؉ؔ؎ؕؽ؉ؐؒقق؉ؾ؎ؿؐ؉ؾؾؑؒؔؕ،؎ؒقؔؽ";
        array[175][0] = "eXyRHtTNoQjGokB9Nmoi7g==";
        array[175][1] = "ؑؕؐؒؔؽؐ،؉؍فؕـ؉ؐؓؔف؉ؽقفؓ؉ؐؒقؑؑؐ؎،؍ؔ؍ـ";
        array[176][0] = "h1uEmaHJG5Q9AEqQtTRnng==";
        array[176][1] = "ــؔقؽقؐؔ؉ؕ،ؿف؉ؐؑؒؽ؉ؕؑؽؐ؉فؕؿ،ؑفؒ؎؎؎ؐؒ";
        By1337̢̛̻̫͖̜͖͕̪͖̥̯̥̘͇̣̩̘̫̫̆͌ͭ̓͂͌̔ͧ͐̐̂̓͒̍̋̈̓̃͟ͅ();
    }

    private void By1337̢̛̻̫͖̜͖͕̪͖̥̯̥̘͇̣̩̘̫̫̆͌ͭ̓͂͌̔ͧ͐̐̂̓͒̍̋̈̓̃͟ͅ() {
        array[177][0] = "Snx/FQje1ZCA+4ilk47w+A==";
        array[177][1] = "ؑؕؒؓق؏،؍؉،ؑؕؽ؉ؐؕؐؔ؉ؽ؍ؿؔ؉ـ؎ؿ؏؏قؐ؍ففـؑ";
        array[178][0] = "RykgHk+32G4MxP+Qr0tOiA==";
        array[178][1] = "؏ق،ؒؕؑؔؐ؉،؎،ؿ؉ؐؒؐـ؉ؽؓـؾ؉ؿؾـ؍ؽ؎ؒف؏ؑ،؏";
        array[179][0] = "FNpP4Y1lQ9lMjCk3Zq0U8Q==";
        array[179][1] = "فؕقف؏ؾقـ؉ؽـ؏ؓ؉ؐؒؑف؉ؾؐؒ،؉ؑؿ؍ق،؎ؓؽؒؾؔ؍";
        array[180][0] = "MhLiMHye7+Y960bvRK9cKw==";
        array[180][1] = "ـ؍ؒؐ،ؕؾؿ؉فؒ؏ق؉ؐؽ؎؍؉ؽؕؓؔ؉؏،ؾؿ؍فؓؓؓقـؔ";
        array[181][0] = "GtPuoUCNfvE5P5iicvKS+g==";
        array[181][1] = "ؒ،؏ؾؓؓؿؕ؉ؾـؓؾ؉ؐقؔؐ؉ؕؽؿؽ؉ـؐؔ؍؍ؾؒؑؓؾؓؒ";
        array[182][0] = "nNu/T/j0CFROAigSovBDSw==";
        array[182][1] = "؍ؽ؎ف،ؿؐؒ؉؍ؔ؍؏؉ؐؾؾؿ؉ؕق،ؒ؉؎ؓؕفؕؑؓؾ؏،قـ";
        array[183][0] = "n6HlhiWTyiatcbZ9rbMZMA==";
        array[183][1] = "ـؑؓؿ؎؎ق؎؉؍ؓؕ؎؉ؐؽ؍ق؉ؽؾ،ؕ؉ؒؐ؍ؐؔـقؒ،ؿ؍ـ";
        array[184][0] = "5byIb34c1VBdVBCmzO/aFA==";
        array[184][1] = "ؓؔ؏؎ؑؕؽق؉فؓؽق؉ؐؔ،ؓ؉ؔؾؔ؍؉ق؏ـ؍ؓؔؓؓ،ؐؐق";
        array[185][0] = "Z7o88kc0upb89TjgEzxpJA==";
        array[185][1] = "فؒقؕؽؔ،؎؉ؕق؏ؾ؉ؐؑ،ؒ؉ؔفؑؐ؉ؔف،؏ؒقؽؕف،؏ف";
        array[186][0] = "r834qRg8U1T9uq7Ig3QSOw==";
        array[186][1] = "ؒ؏ؒقؐؕقؾ؉ؓؿؾؐ؉ؐؐؽؾ؉ؾ،ؕؾ؉ؾؔؾـؽؔقؕقؾقؔ";
        array[187][0] = "ocJZFpeXiGJEUPS6KXSzYQ==";
        array[187][1] = "ؓؓؕؾؒؿؓؽ؉ـؒ؍ؑ؉ؐؓقؒ؉ؽ؏ؾؐ؉ؒؔ،،ؕؔؔؕ؏؍ــ";
        By1337̷̧̻̗͚̫̝̥̩̗̥̘̣̤̭̻̼͎̲̰̀̐̾̈́̌̐͑̑ͣ̀͐͛̑̒̇͗͟͟͠͠();
    }

    private void By1337̷̧̻̗͚̫̝̥̩̗̥̘̣̤̭̻̼͎̲̰̀̐̾̈́̌̐͑̑ͣ̀͐͛̑̒̇͗͟͟͠͠() {
        array[188][0] = "lCBBms1JHDn4Doe5gXn9lg==";
        array[188][1] = "،؍،ؔ؏ؿ،ـ؉ـ؎؎ؔ؉ؐؐ؎؏؉ؕؒؐ؏؉ؽؒـؿؑـؒفؔؓ،ف";
        array[189][0] = "5jT7B7h/53GBEf2raHartA==";
        array[189][1] = "،ؕ؎ؓ،فؐؑ؉ؓ؍ـق؉ؐ،،ؐ؉ؾؑؽؕ؉ؓؒؕؔؽؿؔؔؓ؍ؿؑ";
        array[190][0] = "A+V9cz+b3yHPL9INX03C9Q==";
        array[190][1] = "،ؾؽؒؐ؍ؑؐ؉ؐ؏ؔ؎؉ؐؾ؍ؐ؉ؕ؏ق؍؉،؏ؓؿؿؕ،ؽؒؑ؍؍";
        array[191][0] = "YR3WrM7lz0zrrB6jxGO5vg==";
        array[191][1] = "ؾؾؾؕؕؔ؏ؔ؉ؓؑؓ،؉ؐؐقؾ؉ؔ؏فؾ؉ؒؕ؏ؑؔؒقؓؕ؏ؕؔ";
        array[192][0] = "QRw8Ro8GtAuce8IfdHzYgg==";
        array[192][1] = "ؐ؏ؐؽؑؾؽؽ؉ؿ؏ؐؾ؉ؐقؑؿ؉ؽ؎؏ؒ؉؏ؽفؓ؎ؔؓـؓؓؔ؏";
        array[193][0] = "bN1+QL++AVHDOFY0PdCStg==";
        array[193][1] = "ؓؐؑ؏ؑؿ؍ؕ؉قؾؑ؍؉ؐؽـؔ؉ؾ؏،ـ؉ؿ؍؏قؿؿقؕؽـؽؽ";
        array[194][0] = "SzJuIKtRENjhtpRxMUMFPA==";
        array[194][1] = "ؕؾؓؐ،فؾؾ؉ف؍ؓؽ؉ؐؕــ؉ؽؑـؾ؉ؕـؔؔفؐ؍ققفف؎";
        array[195][0] = "G5o3k40YTHqps0cAMAWDfQ==";
        array[195][1] = "ؐفقؒؽ؍ؽـ؉ؾؕ؍؍؉ؐؐ،،؉ؾ؍؏؍؉ؕــؾفؓؒؐ،قؐ؍";
        array[196][0] = "0STruxZMgwkNUDbDtWE49Q==";
        array[196][1] = "ؔؐؾقؿؒؐؿ؉ؕ؏ؓؾ؉ؐؔـؿ؉ؾؓؓف؉؎ؔقؽ؏ؿؐؑؑؓؓؾ";
        array[197][0] = "BBpnaO1FBOXn7xyqQfSEFQ==";
        array[197][1] = "ق؏ؐ؍فؐؽؓ؉ؓقؔؒ؉ؐؓؿؿ؉ؔ،ق،؉؏ف،ؐؿ،فؔؓ؍ؿؕ";
        array[198][0] = "Hp4F3yYYvklNKjI9vEG2Pw==";
        array[198][1] = "ق،؎ؔ؎ؽؒؐ؉ؑ؍ؐق؉ؐؔؑؔ؉ؕؿؑؾ؉ؕؒؕؔؾفؾق؎ـؐ،";
        By1337̣͍͓ͬ̅̏ͤ̔̊ͥͮ͘͏̶̷̸̨̛͖͖̮̩̰͕͕̥ͮ̊̽̆̊ͨ̄ͣ̉̚͘͟͢͞();
    }

    private void By1337̣͍͓ͬ̅̏ͤ̔̊ͥͮ͘͏̶̷̸̨̛͖͖̮̩̰͕͕̥ͮ̊̽̆̊ͨ̄ͣ̉̚͘͟͢͞() {
        array[199][0] = "kp4ilVkGjDpKTdD3bPAmNQ==";
        array[199][1] = "؎؍ؑقؐؓ،ؓ؉؎ؽؿؾ؉ؐؐؐق؉ؽ؎ؿؾ؉،ؾقؑؓؽؕؐؿ؎ؓؑ";
        array[200][0] = "52ceCOtUdlWS3BqP3oX/6Q==";
        array[200][1] = "ق؎قفؒؾق؎؉ؐ؎فف؉ؐؽ،ؑ؉ؕؕؑؓ؉ؔؔفؑؑؽ؎ؿ،فؑؾ";
        array[201][0] = "Hm69bi8lFNHEm0DCkvztWg==";
        array[201][1] = "؎ؒف؎ؽؕؾؑ؉ؔؔؾ؏؉ؐـؓؐ؉ؽ،ــ؉ؽؑؿ؎ؿؓؽــ؎ؿق";
        array[202][0] = "Fwz0rhABwRjgn9LZMSy7ZQ==";
        array[202][1] = "ـق؏ف؎ــ،؉ؓؽؕق؉ؐ؍ؾق؉ؔ؎فؽ؉قؕؒفؑـؓ؏ؽـفف";
        array[203][0] = "7rbAmFP15WfWTW+9sCYZIA==";
        array[203][1] = "ؕؔؒ؏ؿؕؐ،؉ؑ،؎ؐ؉ؐؑؽؓ؉ؽ؎،ؽ؉ؾؐ؎ؔؿق؏؏ؐقؑف";
        array[204][0] = "nHiTXP1rsA04eF+8YjaWLw==";
        array[204][1] = "ؾؕ،ؾؾ؏فؐ؉ؑؾؑف؉ؐؿؾ،؉ؕف؎؎؉ـ؎ؽـقؔ،ؽؕفقؓ";
        array[205][0] = "rUeMPcLfuFkuper8Qtey0w==";
        array[205][1] = "ؐ؎ؑـؓـؒ،؉ؐـ؍ؐ؉ؐؕؓؔ؉ؾؐفؕ؉ؓؐ؍؍قؐقؑؒ،،؍";
        array[206][0] = "10009rOF8wMN0XkSBtuU/w==";
        array[206][1] = "،ـقؕـؿؾؑ؉ــؓؽ؉ؐـؔق؉ؔفؾؓ؉ؽؽؕؾ؍ؕقؔؒـؔؒ";
        array[207][0] = "Clu3DYkWhVMYZHQ7kyXS+Q==";
        array[207][1] = "ؑؕؓ؍ؽ؏ؾؒ؉ؔؿقؔ؉ؐؽ؍ؐ؉ؔؽؿؾ؉؎،؍ؐؑفؒ؍،،ـف";
        array[208][0] = "cU1OiwoWH2X4LTPlGuwtQQ==";
        array[208][1] = "ؿؐ؍؍ؓ؍ـف؉فؕ؍ف؉ؐؐؐؓ؉ؕ؏ؓف؉،؍ؔق؏ؾؑ؏ؔؓؽؑ";
        array[209][0] = "5sjRnRPhsQ+IaEcSHz5JGA==";
        array[209][1] = "؏؎فؐؓقؑق؉ؾؕؽؿ؉ؐؑ،؎؉ؕؒ؎ؔ؉ؕ،؎ؔق؍،ؿ؎فق،";
        By1337̶̴̢̡̛̩͚͙̣͙͚̬̜̠ͬ͐ͣ̅̓̉̀́ͯ̎̑ͭ̾̀̓̎ͨ͆ͪ̓ͬͥͤ͢͡͞();
    }

    private void By1337̶̴̢̡̛̩͚͙̣͙͚̬̜̠ͬ͐ͣ̅̓̉̀́ͯ̎̑ͭ̾̀̓̎ͨ͆ͪ̓ͬͥͤ͢͡͞() {
        array[210][0] = "b0vaem7zzOynXtTktLvYrg==";
        array[210][1] = "ؿؿؽـ؎ـؿؿ؉ق؍ؿق؉ؐق؍،؉ؔؐؕ؏؉؏ؔففقـؕؔؓـف،";
        array[211][0] = "Eff9IOWSQ2Yoge9cWomIdA==";
        array[211][1] = "ؔؐؒففؑ،ق؉،ؓؐؓ؉ؐؑفؽ؉ؔـ؎ـ؉ؾؕؒقؑ؍ؔؾؓ،ؾؽ";
        array[212][0] = "HvL4EsMKBxUlv6vJsx5NBg==";
        array[212][1] = "ؾؕؑؾؔفؕؕ؉ؿؕؔؾ؉ؐؔؽؕ؉ؔ؏ؕف؉ؕ،ؔؓـؽؒ؍ؐ؏ؓـ";
        array[213][0] = "y/82gJnQSFHKzPqVdWACsA==";
        array[213][1] = "؍فؐؒؐفؔؕ؉ؑؒؐ،؉ؐؿؔؾ؉ؽ،؏ؾ؉ؓقؑؑؽؾؕف؏ؿ؏ؕ";
        array[214][0] = "X0l5mdMvXG9eaynXDMJ4Ew==";
        array[214][1] = "ؐؒؾ؎؍؏؎ؽ؉ؾقؔؐ؉ؐ،؍ف؉ؽؒ؍ؓ؉ؿـؓؿقفـ؍ؔ؏ؽؑ";
        array[215][0] = "eHng+KYzDipk/KWSiFrcFA==";
        array[215][1] = "؍ـؒؓفقؿؔ؉قؽؒؕ؉ؐؐ؍ف؉ؕؽؑؑ؉ؿؿؓقففؓف،ؽ؍ؕ";
        array[216][0] = "xEeudH2BBZxv87lE4bYvgQ==";
        array[216][1] = "؎ؒ؍قؿؿ؎ؕ؉،ؓؕ؎؉ؐفؐ؏؉ؕقـ؍؉ؓقؽؑؿؽؕؑؔؓؑؔ";
        array[217][0] = "Tow2BNX87uQBKQqGRRcScA==";
        array[217][1] = "ـؕؕؔ؏؎ـق؉ؽقؾؿ؉ؐؾ؍ؕ؉ؽؒفؾ؉فقؿ؎ؿ؎ؓؕؐؕـؔ";
        array[218][0] = "JxFtxp0BHoaDLy8Ij2OSrw==";
        array[218][1] = "؎ؑ؏ؑؾؓؐؕ؉؏ؓفؕ؉ؐقؑـ؉ؔؾقؔ؉؎فؿ،ؕ؍فؿؿ؏؍ؽ";
        array[219][0] = "OhbGEN6lM0Ph+9XhB14KTQ==";
        array[219][1] = "ؕؐؒ،،ؾؽ؏؉فؾـؐ؉ؐؔ؏؏؉ؕؽؾق؉؍ؿؐؿؽؔؽ؍ؑؿ؍؏";
        array[220][0] = "6OtlFL+VnRvLSKh4lsceNg==";
        array[220][1] = "ؿـؽ؏ؐـ؍ؿ؉قؽ؏ؔ؉ؐؐؑؿ؉ؕؿؒ؍؉ؾؽ؏ؑؾؑؔؾؾ؏ؽؽ";
        By1337̶̴̵̡̨̡̲̩̩̫̯̩̻ͥ̈́̅̓̅ͥ̀ͣ͆̍͑̾ͬ̌́̉͗͛̽̄̑̿͘͢͜͞͝();
    }

    private void By1337̶̴̵̡̨̡̲̩̩̫̯̩̻ͥ̈́̅̓̅ͥ̀ͣ͆̍͑̾ͬ̌́̉͗͛̽̄̑̿͘͢͜͞͝() {
        array[221][0] = "yDtIX3wopVN8GWEzD3IW8Q==";
        array[221][1] = "ؽ؏ؑ؎،ؾ؏ؕ؉ؓؔؿق؉ؐؾ؎ؕ؉ؾؑؿ؏؉؏ؿؕؾفؑ؎ؾـ،،ؐ";
        array[222][0] = "kK9VLunmlbfvE/9wevmapw==";
        array[222][1] = "فؕؕؾؕفؒق؉؎،؍ؔ؉ؐف؍ؕ؉ؾؿؒ؍؉ؕ؍؎فؾؔؐؕ،ؐؽؐ";
        array[223][0] = "vOlBhSNIItRkp2ohGowO2Q==";
        array[223][1] = "ؕ؍ؑ؎فؾ؍؏؉ؾ؎،ق؉ؐؒؕؐ؉ؕ؎؍؎؉ؽؾؐؿؑؔؐؽؒؐـؕ";
        array[224][0] = "U6xOrSDLUZvP3LGmRhYHJw==";
        array[224][1] = "،فؽققفؿق؉؎ؾ،؏؉ؐؒؑؔ؉ؔؿـؓ؉؏ؽفؒقؽ؏؎،ؿ،ق";
        array[225][0] = "yQrWHOVxuSUOEpq3ENa83w==";
        array[225][1] = "قؾؒقؕ،ؓق؉ؓقؔؓ؉ؐ؎ـ،؉ؕقؽؕ؉ؑقؑؑؓؾؔ؏ؓ،ؕؽ";
        array[226][0] = "zlaOA7dgZZiFoS7grt+d0A==";
        array[226][1] = "؏ؓؔ؍ففق؏؉،ؒؔ؍؉ؐؔـؿ؉ؾقؕؕ؉ؿ؏،ؐ؍ؐ؍ؔؾؕؾ؏";
        array[227][0] = "F7Hdz7cmrnmzrb0O4Neoow==";
        array[227][1] = "ـؓ،؏ؽؕؔؕ؉ؓقؑؾ؉ؐؾ؏ؐ؉ؔ،ؑؔ؉ؐؑؿؔؽؒـ؍ق؏؎ؒ";
        array[228][0] = "fQsgWNAVl3NqdcKj91m/1Q==";
        array[228][1] = "ؒؓؽـؑؽ؎ؕ؉ؽؓ؎ؓ؉ؐفف؍؉ؔؐ؎ف؉؎؍ؽـؾؐق؍ؔؕ؎؎";
        array[229][0] = "HyTi/yz17OPnHDTe9vPktA==";
        array[229][1] = "ؿؓؿقؽؾـؑ؉ؿؕؑؽ؉ؐ،قؒ؉ؾؾـق؉،قؽؒ؏؍ؐؕؒقؽؓ";
        array[230][0] = "lR/WnVBedqQeVeZ2jZ3vBQ==";
        array[230][1] = "ؑف؎ؔؑؑؑ؏؉؎؏ؒؐ؉ؐؽؑ،؉ؔؐ،ؓ؉ؕؿ؍قؓؾؒف؍؎فؿ";
        array[231][0] = "B9zdRfFFpIzEkSBCnZihJw==";
        array[231][1] = "؍ؕؓق؏ؕـؾ؉؎ؐؔف؉ؐؿؔؒ؉ؔ؎ـق؉ؕؿؿؐؿؐق؏؍ؾؿؕ";
        By1337̧̧̛͕͖̮̦̖̞̝͈̞̙͈̼͔̙̩̄̑̾̆ͩ̓̇ͧ͋͆̋͑̾͊̒́͆ͨ̎͘͜͞();
    }

    private void By1337̧̧̛͕͖̮̦̖̞̝͈̞̙͈̼͔̙̩̄̑̾̆ͩ̓̇ͧ͋͆̋͑̾͊̒́͆ͨ̎͘͜͞() {
        array[232][0] = "ghMGQNueO+tlqLqxU2qfag==";
        array[232][1] = "ؓؓ،ؔؔ،ف؎؉ـؐؾق؉ؐقؒؓ؉ؕؒؔؕ؉؍قـ؏؍ؔقؕ؏ؐؔؾ";
        array[233][0] = "0cjguYbBsuef7ePiuHOywA==";
        array[233][1] = "ؽؾؑؒفؔق؏؉ؿق؏،؉ؐ؍ؽـ؉ؾؑؕ؎؉ؔفؾؽـؔفقؽؑـف";
        array[234][0] = "i5yh26n1vccnqGcz2NsDSQ==";
        array[234][1] = "ؑؑؽؽؕؒؽؔ؉ؕؽ؍؎؉ؐقؿـ؉ؽؓ؏ؐ؉؎فققؓفؿؓؒق؎؏";
        array[235][0] = "Vgxhw3MtCznGYrYIJz+ZiA==";
        array[235][1] = "ؽ؍؍ؽ؏ؽفؽ؉ؐؔؕؽ؉ؐؕؿؑ؉ؾؽق؏؉قـ؏؍،؎ؒؿ؎ؐق؏";
        array[236][0] = "d5r/LFYFABA5+/9owSXqwA==";
        array[236][1] = "؎ؕفؔؓؑؓؐ؉ـؑؑف؉ؐؒؿؿ؉ؕـ؍؍؉،ؽؕؑؿقؕ،ق؏ؔـ";
        array[237][0] = "5CNs81scYLhn+mlj7qHGPg==";
        array[237][1] = "؎؏ؐ؍ؑؿ؍ؕ؉ؑـؿ؏؉ؐؒؽؔ؉ؕ؏ؕؾ؉ـؒؑ؎ؿؒؾؕـؿ؎ـ";
        array[238][0] = "Jc379cXJ74hSluQDZX8OSw==";
        array[238][1] = "ؐ؎ؽ؍ؓؑ؎ؒ؉ؾفؒؒ؉ؐ؍ـؐ؉ؔ،ـؔ؉ـ؏فـ،ؿق؎ؒقؐؔ";
        array[239][0] = "RVMXxNK53jJ4m2tplj+J+A==";
        array[239][1] = "؏ؽ؏ؓؾؑؑؐ؉ؿؿ؎ؐ؉ؐؾقؒ؉ؔفؽ؏؉ؿؿؽ؍ؑ؏ؿـؕق؎،";
        array[240][0] = "GhtW4IdQ+3nhOlWmNOTBiQ==";
        array[240][1] = "ـ؍ؑؑؔ،ؑ؏؉ؓؓ؎ؾ؉ؐؽؕؿ؉ؾقؾـ؉ـؾؾؒـؒفؐ؎،؍ؐ";
        array[241][0] = "HFD6nLLnzii84v644R+fQg==";
        array[241][1] = "ؿؐ،فؓؿ؎ؽ؉فؾؔؑ؉ؐ؎؏ؑ؉ؕ؎ؔؒ؉ؔؐ؍؎ؔؔ؎ففؓؿ؍";
        array[242][0] = "6VPVPO7vafswf0YEUc6Eyg==";
        array[242][1] = "ؓؑؔقؔؽؐق؉؏ؓـ؎؉ؐؓ،ق؉ؔ؍ـ،؉ؑ؎ؔؽف؏ؾؾؔ؎فؾ";
        By1337̶̶̶̨̛̛͚͈͓̲̳̝̞͓̠̲͙̩̲̳̞̿́́̓̇̅͂ͧ̌̏ͤ̿͌́ͫ̍͝͝͡();
    }

    private void By1337̶̶̶̨̛̛͚͈͓̲̳̝̞͓̠̲͙̩̲̳̞̿́́̓̇̅͂ͧ̌̏ͤ̿͌́ͫ̍͝͝͡() {
        array[243][0] = "K1FvU8R7PGg6asLIKaMbpw==";
        array[243][1] = "ؑ؎؏،فؔؑ؍؉ؐؒؽ؍؉ؐؓ،ؕ؉ؔ؎ؾؒ؉؎ؿ؏ؔؾ؏ؽؒؾ،ـؓ";
        array[244][0] = "AL8rorHjzOmt+kZ9lmM+pQ==";
        array[244][1] = "ؐؿؽ؎؍ؑـؑ؉فؕ؏ؕ؉ؐؿ؎ـ؉ؾؽفؕ؉؎فؿؔؽ؎ؿؿ؏ؿؽؿ";
        array[245][0] = "ugc5GgOQkEaeTGeh5mD2oA==";
        array[245][1] = "؏فقؓؕؓ؍ؒ؉ؿؒق،؉ؐؓؕف؉ؾـ،ؔ؉،فؐؑؿؿؽؓؓ،؏؏";
        array[246][0] = "n6R2bi50Oi/Gs6vUu48uRw==";
        array[246][1] = "ؕؓ؏ؿؒـفؽ؉فؐ،ف؉ؐؾف؏؉ؾ؏ؾؐ؉ؕ؏؎،،ؕؑؕ؎؎قؐ";
        array[247][0] = "ihmnnGD2sQWujCUWnkRNEQ==";
        array[247][1] = "ؾؑ؎ؽؔؐؾف؉؎ؽؔف؉ؐؓؑ؍؉ؽؾ؎؍؉؎ؒؾقؽؔفؽؔؾؾـ";
        array[248][0] = "BoWSriHZSsprSRehPCi3BA==";
        array[248][1] = "ؑؒؔؑفؓؔ،؉ؔؿؕؒ؉ؐؐـؐ؉ؔؕ؎؍؉ؐؽؕؿؑ،ؽؔ؏ؕفؒ";
        array[249][0] = "nXTnon9uVsHIgWdl3GofRw==";
        array[249][1] = "ـؔؓؒ،ؿؾق؉ف؍؎ؾ؉ؐؽؾ؍؉ؕؐـ؏؉ؐؐؒقؔؐؿؓؔؽؐ؎";
        array[250][0] = "tamCB3xKbAb0SPzcqgWkag==";
        array[250][1] = "ؓ،،فؒؒؐ،؉؎قؿ؍؉ؐؔ،ؐ؉ؔ؎فق؉ؑؔ؎ؽؓؐ؎ؑؐ؏ؾؐ";
        array[251][0] = "1RvvDcCGpnzGVs/ttHEYHw==";
        array[251][1] = "؏ــؒؓؓؐف؉فؕ؏ق؉ؐقؽـ؉ؔـؿؓ؉؍ؽؽؓؔؿؒؾؿؐؿؾ";
        array[252][0] = "j0v1qQCVqXxPvwSmr/fZbQ==";
        array[252][1] = "ؑؐ؍ؔـؕؑؓ؉؎فؒؐ؉ؐؓ؎؎؉ؕـؽؔ؉؎ققؒؔؔؕ،،ؔؒؔ";
        array[253][0] = "3GLvNURHFQ3WH6dTSBZIPQ==";
        array[253][1] = "ؾـؕقؑؕ؍ؔ؉ؔؾؾؒ؉ؐ؍ؽؔ؉ؽؔؓؑ؉ؿؒؓؑـ؏ؾ؍ؽـؔؓ";
        By1337̡̱̗̠̭́̈́ͥ͘͏̵̶̨̧̣̘͎̤̲̙̬͓̣̖̯̱͈̖͎ͤ̑̆̏̊́̎̃̏ͭ͠();
    }

    private void By1337̡̱̗̠̭́̈́ͥ͘͏̵̶̨̧̣̘͎̤̲̙̬͓̣̖̯̱͈̖͎ͤ̑̆̏̊́̎̃̏ͭ͠() {
        array[254][0] = "FWMS9o7R0mpwxkbdYPDyRw==";
        array[254][1] = "ـؑ؍؎ؕؒؔؓ؉،ؔؑ؏؉ؐ؍؏ؓ؉ؾقؾؿ؉ؾؒؽ؎ؿؽؑؽ؍ؒؽؐ";
        array[255][0] = "APjmlyrPrdjSP4VvCUFY9Q==";
        array[255][1] = "ؐؔؾؿؒ؍ؔ؍؉ففؒ،؉ؐؾ،ق؉ؽ؍ؔ؍؉؏ؿقؔؑؿؾقؐؔؾؓ";
        array[256][0] = "5UxTNuFgAFSASJnm5g1jYw==";
        array[256][1] = "فؕ؎؎؏ؽؾ؏؉ـؿقق؉ؐؓؒؽ؉ؔؽ؍ؔ؉ؽؔؓؔؑؐؽـفـ،،";
        array[257][0] = "7HwnWK92ztcAXbYPR9WNnA==";
        array[257][1] = "ؾ؍ؕؿفؾ؏ـ؉ؑقف؍؉ؐؿ؍؍؉ؾققؑ؉فؓ؍ؾؓفؔؾؾؐقؒ";
        array[258][0] = "8zAmi6jzVjBEvIG8fWNGTA==";
        array[258][1] = "ـؿ؎،؍ؔف؍؉؎ؒؕؕ؉ؐ؍فـ؉ؔ،،ف؉ؑؽقفؕ؏ؐف،ؽؑؑ";
        array[259][0] = "aD7feTi6C11IMmZrTsQfNg==";
        array[259][1] = "ـؕؑؐؑ،قؿ؉ؽ؎ف؍؉ؐ،ؐ؎؉ؾؓفؓ؉ؒؕؾؐ؍ؔ؎ؾ؍ؐ،؏";
        array[260][0] = "TQplkz3IIWUZVLepepd9PQ==";
        array[260][1] = "؎ؐؑفؽؓؐ؏؉؍؎فؒ؉ؐؾ؍ؽ؉ؔؕؐؐ؉؏ف،ؒؒفؿؾ،ؿ؎ؒ";
        array[261][0] = "f+LQCcaF9BK0aPkaveGc9A==";
        array[261][1] = "ؓؓفؒـق؍،؉فؓؔ؏؉ؐؒؑ؎؉ؕ،ؐ،؉ـؕؽـ؍ـ؎قؐؐقؓ";
        array[262][0] = "W9GUS8rs59O2UaXAu0Gpag==";
        array[262][1] = "ؿؾـؓفـ،ؒ؉ؿؕؕ،؉ؐؾؒؾ؉ؔق،ف؉ـؐؕ،،ؾؒ؍،ؔؑؑ";
        array[263][0] = "k8mFw/CywgB/DNVDqCc7xg==";
        array[263][1] = "ؐؒ؏ؑؔؽؓف؉ؿؒـؐ؉ؐ،ـؔ؉ؔفؕف؉،ؓـؿف؏فؒفؽ؎ؐ";
        array[264][0] = "68r7uxf5KC9tIbnb8kl+Cw==";
        array[264][1] = "؏ؾؐؐؒ؎ؑ؎؉ؾؿ،ف؉ؐؑؕؒ؉ؾ،ؓؕ؉ؓـؿؽؑؓؓؔؒ؎؎،";
        By1337͖̭̦̼̠ͯ̓̃͏̢̥͇̬͇̯͈̬̭̩͍̉ͫ̋̎ͧ̓ͤ͊͋ͧ̽̿͋͋̌̑ͮͨ͟͞();
    }

    private void By1337͖̭̦̼̠ͯ̓̃͏̢̥͇̬͇̯͈̬̭̩͍̉ͫ̋̎ͧ̓ͤ͊͋ͧ̽̿͋͋̌̑ͮͨ͟͞() {
        array[265][0] = "WwzbRDzVdflDucvRlvRWMw==";
        array[265][1] = "،ؒ؎ؑؐؔؿؕ؉ؽقؿ؍؉ؐؒؽؒ؉ؕؑ،ؿ؉ؾؓ،ؒ،؎؍ـ؍ؕؾ،";
        array[266][0] = "uzYhRXB6MBESfEEttRFLyQ==";
        array[266][1] = "ؕ،؍ف،ؑؕؾ؉ؕؾؐؒ؉ؐؔؑؒ؉ؔؓؽق؉ؔؿؒؓؓؔ؍ؾـؑؿ؍";
        array[267][0] = "UVH3FWRcabJzTRHyhPRVig==";
        array[267][1] = "؏،ؔ؍قؽؾق؉؏ف؍ؓ؉ؐؽقؑ؉ؾؕفـ؉ؐؑقؐؿؐؑؿؕـ؎؍";
        array[268][0] = "16YEZqDkQ8u1wFjVgKhkZA==";
        array[268][1] = "فؓؐ؏ؿؒـ؍؉ؿؔؿق؉ؐؿ؍ؑ؉ؽؐؾؑ؉ؽ،ؓؿؽ،ؾؕؑـ؎؏";
        array[269][0] = "ikRAs4bYW3FMJdhprpcXkA==";
        array[269][1] = "ؽؾؽؒ،ؑؓ؏؉ؾ؎ؒ؍؉ؐ؎فؾ؉ؔ،ؔؽ؉ؓؿ،؏فـقؾؕؓؐؿ";
        array[270][0] = "PU9qLr0DlTOJNAkO1pu8ng==";
        array[270][1] = "؏؎ؐؔقـفـ؉ؽ؍؍ؐ؉ؐفؒؾ؉ؕؔؿؿ؉،؎؍؏ؑؑ،ؒق؎فف";
        array[271][0] = "a8BRnanAwZpc7iS914+faA==";
        array[271][1] = "؏،ق؎ؓؾ؎؏؉؏،ؒؕ؉ؐفؑؽ؉ؔ؏ؔ؍؉ؽؽ؍ؽؐقؽؓ؎ؒق؏";
        array[272][0] = "nbmV2Vi0TWUI9OBcWNP58g==";
        array[272][1] = "ؑؑؿؿؕؓ؍ؐ؉ف؎ؔؽ؉ؐؓـؿ؉ؽؕ،ؔ؉ؐ،ؐؕ؏فؿ؎قؓفؽ";
        array[273][0] = "p8IrQuLArHXgR9F41968Vw==";
        array[273][1] = "ؔؕؾ؍ؐ؏؎ؓ؉ؽـؽؐ؉ؐ،؏ؐ؉ؕؓؕق؉ـف؍ؒؔـؽـؑ؍ؿؓ";
        array[274][0] = "TBtm42b3mBD7xo36zrnFIQ==";
        array[274][1] = "ؔـؓؑـ؍ؕؔ؉ؿؓ؎ؾ؉ؐؓؑ،؉ؾؒؿق؉قؐ؎ف،ؐفؽفؐ؎ـ";
        array[275][0] = "AxrYG0l8LOLadKJZb88+xg==";
        array[275][1] = "؎ف،ؒ؏ؿـ،؉ؕؑؑؑ؉ؐ؏ؓ؍؉ؾ؎ف؏؉فؕ؎؎؎فؿؾ؏فؿ،";
        By1337̸̵͈̮̝̯̺͉͙̮̺̤̜̤̦͇̣͙̙̯̾̌ͯ̓̾ͮͣͯͫ̈̐̆̑͌͘͢͢͟͢͝();
    }

    private void By1337̸̵͈̮̝̯̺͉͙̮̺̤̜̤̦͇̣͙̙̯̾̌ͯ̓̾ͮͣͯͫ̈̐̆̑͌͘͢͢͟͢͝() {
        array[276][0] = "Zc4PbTFOm3cL6KZiewYEEA==";
        array[276][1] = "فؕؿ؏ؕففف؉؍؍؎ؒ؉ؐ،ؽؾ؉ؾقـؑ؉ؔؔؾؒقؽؓؽؔـ،،";
        array[277][0] = "ek6qGD+a+V9uxF+fTTgszg==";
        array[277][1] = "ؒ؏فـؾؕقـ؉ؔـؾ،؉ؐق؏؏؉ؕ؏ؐؑ؉فؑؔقؾ؍ؾؐقفؒ؍";
        array[278][0] = "bHmfpU1Awsl7V7uII9R7qQ==";
        array[278][1] = "؍ؕؑؽـؽ،؍؉ؿ؍ؐؕ؉ؐقؾؕ؉ؕف؎،؉ؑؑق؏ؒؕؽ،،ؒؾؽ";
        array[279][0] = "82VwdrG6ax/2s+kAGeJOkg==";
        array[279][1] = "؎ؓؓ،ؿؾؿؑ؉ؾؓؓؿ؉ؐقؕؿ؉ؕؾؓق؉ؽؕؓؿؔؾ؍ؿؑ؏ؕؒ";
        array[280][0] = "NNemY/ErHix4yb7D/UprMg==";
        array[280][1] = "ؐ؎،ؕؕؓ؎ؐ؉ق؍ؔؔ؉ؐؔؿؿ؉ؽ؏ؒؓ؉،ؿ،؎فؿ،ـؑ،ؽق";
        array[281][0] = "ztwXR2H2dhDFO0PCfFgrmQ==";
        array[281][1] = "ؒؐ؏ؓؿ،ؐف؉ؽؓؕ؏؉ؐ؎؍ؑ؉ؾ،؎ؒ؉ؽ،ؽقـؐ؏ـفؔـ،";
        array[282][0] = "8rRL2+nv0QXAVUprN6Yi4g==";
        array[282][1] = "ؓؐؓ؍،ؐؕؔ؉؏ـؔؕ؉ؐـؐ؏؉ؽ،ؔؿ؉ؾؒؓؾؐـ؏ؒؑؑؽؽ";
        array[283][0] = "ohiXW5mtdrS+zapuDLl1yA==";
        array[283][1] = "ـؑـؑؽ؏؎ؓ؉قؿؒ؎؉ؐؒقف؉ؾـؿؒ؉ؓ؎ؕؾؐؽؔفق؎ؿؐ";
        array[284][0] = "1aMs+KXTitj8N5lk4XTjnw==";
        array[284][1] = "،ؽؿق،ؑؿ؎؉ـؑ؏ؒ؉ؐف،ؐ؉ؔؓؒ؏؉قؕ؎؎؎ؕؿؒـ؎؏ؔ";
        array[285][0] = "WQxQBj86DUvICbi+MK0VRQ==";
        array[285][1] = "ؿؐ؍فؓؐؿؔ؉ؽؾؒؾ؉ؐؿؽؿ؉ؔؐ،ؽ؉،ؒؿؾ؍ؽؒؿؿ؍،ؑ";
        array[286][0] = "m2sfjBFPbuz5oau0+prKjQ==";
        array[286][1] = "ؔ،ـ؏فف؏ف؉؍فؕ؎؉ؐ؏ؒ؏؉ؽؽق؏؉قؕ،،،ؕؐؑ؎؏فق";
        By1337̶̨͔̱̞̯͔͎͉̱̱̯͎͍̬̜̞͚̈́͆̍̿̎̌ͩ̏̀ͧ̄̔̈́ͥͭͯͧ̾̀̊͟͡();
    }

    private void By1337̶̨͔̱̞̯͔͎͉̱̱̯͎͍̬̜̞͚̈́͆̍̿̎̌ͩ̏̀ͧ̄̔̈́ͥͭͯͧ̾̀̊͟͡() {
        array[287][0] = "GN6XRXjWbbco8ulP38dJ1w==";
        array[287][1] = "ؕؾؿـ،ؒ،ؾ؉ؒؒؿ؏؉ؐؒؽؓ؉ؕقؔف؉؏ف؍ؐؓؑ؍؍،؏؏ق";
        array[288][0] = "mIoa6W80D/Z3X68HTKWlnw==";
        array[288][1] = "ؑؓ؍؎ؐ،ؔؒ؉ؿؑؑؾ؉ؐؒؒـ؉ؕف،؍؉؏؏ـؐ؏ؾؒف؍ؔؿـ";
        array[289][0] = "Cdgp3GvseGlzlXJrXVSCpA==";
        array[289][1] = "؏ؑؑؽ؏؍؎ؐ؉؏ؒ،ؑ؉ؐ؏فؽ؉ؽؽؕؔ؉،؎ؔؾؐؿـؽؿؓؔؑ";
        array[290][0] = "rIMtEhCyfWroIGDg3Ooo0Q==";
        array[290][1] = "ؽ،ؔففؕ؍ؔ؉ؐ،ـ؎؉ؐؕؿؕ؉ؔ؍ق؍؉،ؽؑؾؒؐؐؾؿ،،ؐ";
        array[291][0] = "UJqwJUJk+KMLWs435LEXGQ==";
        array[291][1] = "ؐـقؑؾ؍ؓؿ؉فؐؑؐ؉ؐـ؍ؽ؉ؾ؏ؾؒ؉ؿؐؐ؏ؕؕؕفؾ؎ؐق";
        array[292][0] = "F7ypiFx/Qzpcv0rG53j8oA==";
        array[292][1] = "،ؒؿ؏ؔؕـ؎؉ؔؿ؎ؓ؉ؐؕؕ؏؉ؽؾؔؒ؉؏فقؔ؏ؐـؑ؎ؽؽؓ";
        array[293][0] = "sRQoI6eeD70c2mGp6Mq4uQ==";
        array[293][1] = "فؕؔ،ؿؿؕؐ؉؎ؔؾق؉ؐؕفؿ؉ؕؓ؎ؾ؉ؔؒؽؾؑؾ،فؽ،ـ؏";
        array[294][0] = "Uqnt+EcgwX84ZZbiQtaWoA==";
        array[294][1] = "ؽؕؿؑؑؕؕ،؉؍ؿؾ،؉ؐـ،ؕ؉ؕؔ؏ؐ؉ؑؒؔ،ؒفؕ؎،؍،؍";
        array[295][0] = "jkblErME91J0JM1oBrGoAg==";
        array[295][1] = "ؑؐؽؑف؍؎ف؉ؐؒؔـ؉ؐؒ؏ق؉ؔ؍؍ؽ؉ؕ،ـقؕقؾؐؾ؎ق؍";
        array[296][0] = "94OKljtWJdX2eNCtmrQz6Q==";
        array[296][1] = "ؔؾؓؒـؓؽف؉ـ،ؕؒ؉ؐ،ؕق؉ؔؒقؕ؉ؑؔؐؐؔؾؒؑؓـ؍ؑ";
        array[297][0] = "7nnOLppS6A2qsbuJoC8iKA==";
        array[297][1] = "ؓف،ؑفؾ؎؏؉ؕفؓق؉ؐقؑـ؉ؾـؒؽ؉قؐؔـؑؓ؎ؑؕؿؑؔ";
        By1337̴̡̥͇͉̯͉̭͈̫̹̺̙̖̣͙͈̯͕̻͓̎̎͊͐ͩ̍ͮ̊̈́̽͆ͮͪ̕͢͠͏̲̪();
    }

    private void By1337̴̡̥͇͉̯͉̭͈̫̹̺̙̖̣͙͈̯͕̻͓̎̎͊͐ͩ̍ͮ̊̈́̽͆ͮͪ̕͢͠͏̲̪() {
        array[298][0] = "z3Xb82rkBJadhJVNMtjZOQ==";
        array[298][1] = "؎ؑفؒؐؔؐؾ؉،؏ؿؿ؉ؐؔؽـ؉ؔؿؑؽ؉ؽؿ،ؕؕ،ـؽف؏ؔؿ";
        array[299][0] = "Fly+gh1JD4KQtYBjQogSfg==";
        array[299][1] = "،؏ؔؿؐؒف؎؉ؒؓ،ؓ؉ؐؾؓؾ؉ؕ،ؾ؍؉ؐفؐؔؽ؎ؿ؍،ؕؕ؏";
        array[300][0] = "av48MvB4gPOCd5ml10cekQ==";
        array[300][1] = "ف؏؍؏ؐؔؿؑ؉فؒؓق؉ؐ،،،؉ؾؐ؍ؑ؉ؑ؏ؔؿـؾ؍ؿؽ،ؑؽ";
        array[301][0] = "/vZKr60MfEZhXiQtb9zlug==";
        array[301][1] = "ؑ؏ؕؾؕؐؕف؉،؎فؾ؉ؐؔؔؕ؉ؔؐؒؽ؉ق،ففؔق،ؐؕؒؓؒ";
        array[302][0] = "FZMEhoPzKoFaUmed6ejiCw==";
        array[302][1] = "ؓ؎؏ـ؏ؓ؎ؔ؉؎؎ؑؿ؉ؐ؏ف؍؉ؽؾؔ؏؉؍؏ـؕفؐ؎فؓؾؽؽ";
        array[303][0] = "/3Tu7x7nq+32UvfMQx2edg==";
        array[303][1] = "؏ؑقؐؕـؐق؉،؎ـؓ؉ؐؿفؒ؉ؔ،؏ؿ؉؎ؕقؒؐ؎ؽ؏؎ؒؐق";
        array[304][0] = "gCSH+U4Yw2SnbWbUuWRbBQ==";
        array[304][1] = "ؑؕؑ؍ؑؽؓؕ؉ؽؐؐؿ؉ؐ؏؏؏؉ؔؐفؒ؉ـؕؒؕ؏ؽـؒف؏ؓؑ";
        array[305][0] = "mv/rCYRTYsPDSw+NcxvdFg==";
        array[305][1] = "ؽ؎ؽفؑ؎ؾؒ؉ؿ،ؒق؉ؐؔؽف؉ؾؓؓ؏؉ؔ؏ؿـؑف؏ؾ؍؎ؕؔ";
        array[306][0] = "G+cr9xIC0Ox496OtqiMpqA==";
        array[306][1] = "ؿ؍قـ،ؾؔؐ؉،ؾ؏؍؉ؐـ؎ؾ؉ؾ؍ـف؉فقفؽؕفؑفؔؽ؎ؒ";
        array[307][0] = "8Teg9HIozGHxofCuzpNWPw==";
        array[307][1] = "قؓـؽ؍؏قـ؉ؽؽؾؽ؉ؐؽقؽ؉ؽؑقؾ؉قؾ،ؐؐؒؕ؍ؔؔؑؽ";
        array[308][0] = "uHkXKJ6HkrgRQfIBbKInqg==";
        array[308][1] = "؏قؾؔـفؑؽ؉فؾ؎؍؉ؐـ،ؐ؉ؕؐؒف؉ؒق؍ؕؑ،ؾؓ؎قــ";
        By1337̸̡̟͙̫͎̖̰̤͎͎̌ͬ̒̈̽ͤ̓̚͏̹͚̘͈̙͎͕̟͗̏̃̇̄ͣ̓̅́̈ͭ͞();
    }

    private void By1337̸̡̟͙̫͎̖̰̤͎͎̌ͬ̒̈̽ͤ̓̚͏̹͚̘͈̙͎͕̟͗̏̃̇̄ͣ̓̅́̈ͭ͞() {
        array[309][0] = "Xb4/hRPyrNW1u78ahpZ6AQ==";
        array[309][1] = "فؕ؍ؑؔؽ؏؎؉ؒؒ،؎؉ؐؔؾؒ؉ؔؓؒؿ؉ؓؿؔؿؑؽ؏ؑ؏ـ،ؒ";
        array[310][0] = "gL4c92ye0IbfdjjUaDKbGQ==";
        array[310][1] = "ؾؐـ؏ؾؔؐؑ؉ؽ؎ؾف؉ؐقؔف؉ؽفؽؿ؉؎ؾؓؕؔ؏ـ؍قؿفؕ";
        array[311][0] = "I71zpltKrMNQXz3wAbT3gA==";
        array[311][1] = "،ــؿ؏ؽؽؓ؉ؒفـؾ؉ؐقؑؾ؉ؾؕؔؓ؉ؒقؒؿؐؒقـؕ،قؔ";
        array[312][0] = "WxAyJRqHjKPAS6PWT0z2Iw==";
        array[312][1] = "ؐؾـؾؐؒـؔ؉قؽؕؕ؉ؐؕؒؔ؉ؽؓؐـ؉ؑؽؒ؏ؑؕؔ؏؏؍؍ف";
        array[313][0] = "ImElIXm0U5V2M5JRm+wkdg==";
        array[313][1] = "ؑؑ؎ؿق،ؕ؏؉،فؐؑ؉ؐفؽ؏؉ؽؾ؎ؐ؉ؕؑؿؑف؍ؕؽؿؕـؽ";
        array[314][0] = "QyA0zUMy0xxyr625lI+MMw==";
        array[314][1] = "ؐؑ،ؓفؔ،ؾ؉فؕؐـ؉ؐق؏ؑ؉ؾؒ؍ؿ؉ؔؑؿؓؾ؏ؑؿؑؓؕؿ";
        array[315][0] = "94aRscnsW0b2j7tCCTWRMA==";
        array[315][1] = "؎ـ؏؏ؕؿؔؿ؉،ق،ف؉ؐؑؕ؍؉ؽؐف،؉ؔؽف؏ـ؍ؓؓؐؑؽ؍";
        array[316][0] = "AZJeCTJPu7DwEBPszfgIUw==";
        array[316][1] = "؎ؿفؾـؿؐـ؉ؿؓـؿ؉ؐؾفؓ؉ؽؔؾؒ؉ؐؐ؍ؒ؎ـؓ؏ؔؒؕؓ";
        array[317][0] = "4wCQdLkGFje90a1Hpo0Izw==";
        array[317][1] = "فؒؑؔؽ؍؍ف؉ؿؓفؿ؉ؐـؿـ؉ؕؓؽ؏؉ؔؒؽف؍؏؎؎؎ؾ؍ؿ";
        array[318][0] = "ytOgIwx6iAppnt3hjQxhAg==";
        array[318][1] = "؍،؎ؑؿؔق؎؉؏؏ؾؿ؉ؐؐ،ـ؉ؾؽ؏ؔ؉ؔؐ؏ؒؾؽؕؐ؏قؑق";
        array[319][0] = "7/3/vxa3grgtGAg008SGYQ==";
        array[319][1] = "ؽ؏ؓؿ،؍؏ؒ؉ؑؐؑؑ؉ؐؿؾؽ؉ؾؿ،؏؉ؒؽؾؓؽ؏ف؏ؽ،ؔف";
        By1337̩͇͉̪͕̈́ͦ̍ͭ̓̽ͦ̆ͨ͢͏̶̴̛̛̜̲̦̻͙̩͖̥̌̐͒͡͝͏̵̪̼̅̎͜();
    }

    private void By1337̩͇͉̪͕̈́ͦ̍ͭ̓̽ͦ̆ͨ͢͏̶̴̛̛̜̲̦̻͙̩͖̥̌̐͒͡͝͏̵̪̼̅̎͜() {
        array[320][0] = "wYvpwJ+o8YxrKD2cFtlAWQ==";
        array[320][1] = "ـؒـؿؿؾق؍؉ؕؒق؎؉ؐؿ؏ف؉ؽؕؓؒ؉ق؎ـؕقؿفـؐ؎؏؏";
        array[321][0] = "34A5hZARoMY9XpkiBA1AMg==";
        array[321][1] = "فؓ،ؐؔؕؿف؉ؔؒؾؓ؉ؐؔ؍ؔ؉ؾؓ،؍؉ؽ؎ؕؒؒ؎ؐؒ؏ؓؿؽ";
        array[322][0] = "vGaUAKTrlHvJSqEDevQOzA==";
        array[322][1] = "؎؍ؔؿؓ؍ؓؿ؉قؔؿ؎؉ؐـؾؔ؉ؔؓؾ؍؉ؿؕؐؿؔؾف؎،ؾؾق";
        array[323][0] = "28MLXDA/CpfLuYelt1GJSA==";
        array[323][1] = "ؕؿؽفف،ـ؏؉ؿؐـ؏؉ؐ،قف؉ؕؾـؔ؉ؐ؎ؾ؎ـ؍؏ؑ،ؐؕؐ";
        array[324][0] = "QCe4rhw6C7QSdN01UACKhg==";
        array[324][1] = "ؓؕ،فـ؏ف،؉ؑ،؎؏؉ؐؓ،ؕ؉ؔؽؾؓ؉ـ؍؍ؿؔ؍قؐقؽؒؐ";
        array[325][0] = "lTeamE8xpEkpO0cUrfn8XA==";
        array[325][1] = "،ؒؒؔؾؐؿؒ؉ؿؾـؾ؉ؐ؏ؐؓ؉ؕ؎؎ؔ؉فؐـؔؓؽـؔ؍ؾؔق";
        array[326][0] = "MAhpbzcyDi3HASy13g2ROA==";
        array[326][1] = "قفؽ؏ـؐؽؾ؉ؑؑؽؒ؉ؐؔ؏ؑ؉ؾؔقؔ؉ؽؒؽقؓؾ،ؐؽـؒ؎";
        array[327][0] = "7ATdYY8DUxv6L5yI74LMKA==";
        array[327][1] = "ؒؒقؐؒؿـ،؉؎ؾ؎ؾ؉ؐـؕؑ؉ؔؔؕف؉ؑؔؑؔؐؾ؍ؿؒؿقف";
        array[328][0] = "SswnGTln5CH8UEQIfEG6aA==";
        array[328][1] = "ؓفـؓفؑ؍ؒ؉ؕ،ؽؔ؉ؐؾؽ؎؉ؕ؍؎ؓ؉ققؑؽ؍؎ـق؏ؑ،؎";
        array[329][0] = "xG1vGcIhO/NNMHzkSOSbwg==";
        array[329][1] = "،ؾؾؿؑفقؒ؉ؑؽ؎ؿ؉ؐؕؽؓ؉ؽ،؎ؐ؉؎ف؍ؔؕ،قؽ؏ؽؽؽ";
        array[330][0] = "D6CbvSsCAWbQQpiqEcQ5Cw==";
        array[330][1] = "ؓ،ؾـؐ؎ؾؑ؉ـؽقؽ؉ؐؿؕؒ؉ؔؓ؍ـ؉ؔؔـ؍؎؍ق،؍؏ؾ؎";
        By1337̸̡͉͈̤̮̜̝̜͔͚͈̦̦̗̻̝̒̽́̌ͬ̌̎̓͒͋́͗͋́͒͗ͥ͢͜͢͟͢͞();
    }

    private void By1337̸̡͉͈̤̮̜̝̜͔͚͈̦̦̗̻̝̒̽́̌ͬ̌̎̓͒͋́͗͋́͒͗ͥ͢͜͢͟͢͞() {
        array[331][0] = "6ecemFROAz9GG0M0zqrA8w==";
        array[331][1] = "ؑؓ؎ـؽؾقؿ؉ـؿ،ؒ؉ؐؿؐ؎؉ؕؑؕؐ؉،؍ؒؕؓؽ؏ؽ؎ؿقؿ";
        array[332][0] = "PvQCiAe9WtU4rMqclUMNVA==";
        array[332][1] = "،قؕؒؐفؽؔ؉ؑـؿؕ؉ؐؽؿؽ؉ؽ؏؍ق؉ؾ؍؍ؿؑؽ،ققؿـؾ";
        array[333][0] = "3CcgvHyjk/K4gMN9qsUnow==";
        array[333][1] = "ؽؔ؏ق؍؍ؽؾ؉؏ؿؐؐ؉ؐؽ؏ف؉ؾؓؒ؍؉ؒؾؾؔؓؒ،ؓفـؽؽ";
        array[334][0] = "I75/lJ9+bEAkaxPYw0Zhvg==";
        array[334][1] = "؏ففؔؒؕؓؽ؉،ـ؏ؿ؉ؐؔ؍ؐ؉ؕؐ،؍؉؎ق؏ؒ،قؓؒؒف،ق";
        array[335][0] = "UEk5/SFZgsXthlsRLxEdcw==";
        array[335][1] = "قـؑقؐ،،ؒ؉ؒ؏ؐؑ؉ؐ؍قف؉ؕ؏ؒـ؉،؎ؿفؽ؎فؽــ؏؏";
        array[336][0] = "O8gWGUa/pqJQZdUxbVOFpw==";
        array[336][1] = "؍قؑ،ـؔؔؿ؉ؾؓ؎،؉ؐؔـؓ؉ؔؔؔ؎؉ق؏ؾ؏ؓؾ؎ؿقؒؒؑ";
        array[337][0] = "zFShVhLY9SAUwIBNu3qb9Q==";
        array[337][1] = "فؓـ؍،ـؾؔ؉ؽؾؕف؉ؐؔق؏؉ؔفقؿ؉ؓـؒ؏ؑ؎قؐق،ؾ؏";
        array[338][0] = "aEniLVgMdK3YwP80t8bjVg==";
        array[338][1] = "ؿؕؑ،ؑقؿؔ؉ؿفؐؾ؉ؐؒ،ؐ؉ؾؐؑؓ؉فؕؽ؎ؔ؏ؑ؏ؔفؐؽ";
        array[339][0] = "oNY1n0LTTQ5C6H2OP4lj3Q==";
        array[339][1] = "ؾؿف؎ؑقؿؕ؉ؽؕ،ق؉ؐؔؾؒ؉ؔؕ،ؓ؉ؿؽؒ؎فؽؑ؏؎،ؕؾ";
        array[340][0] = "+TaDwqvPQtCzU3NFrEDmAw==";
        array[340][1] = "؍ؿؿؔؓؒ،؍؉ؓ؍فؕ؉ؐ؍ؑ،؉ؕ؍؎؍؉ؒؔؑؒؐـؒؿؓ؎ؕ،";
        array[341][0] = "GlAWNSOZcr8SPX5LRXqY3w==";
        array[341][1] = "؎،؏ؽؿؓ؏ؾ؉ؾ؎ؾ؎؉ؐ؎ؑؑ؉ؽؒؿ؏؉ؓ؍ؑؿؿؓ؍ؕ؎ؑؑ،";
        By1337̷̵̧̨̢͕͓̻̫̥̯͎̜̰̰͚̗͉͎̳̃ͯͭ͗́̏̌̆́ͫ̾̽ͥͭ̊͘͢͢͞͠();
    }

    private void By1337̷̵̧̨̢͕͓̻̫̥̯͎̜̰̰͚̗͉͎̳̃ͯͭ͗́̏̌̆́ͫ̾̽ͥͭ̊͘͢͢͞͠() {
        array[342][0] = "IAv6orYlhfQpj+nt+baLcw==";
        array[342][1] = "،ؑ؏ؾؽؑ؍ؓ؉ـ؍ؔف؉ؐؐؕؾ؉ؾ؎،ؓ؉ف؏ؑ،ؓقفؕؑ؎،ؽ";
        array[343][0] = "hnr2KxnZDBRAUQ9zzaQxjQ==";
        array[343][1] = "ؑؓؐ؍ؽققق؉؏ؾؾف؉ؐ،ؒؒ؉ؔ؏،ؒ؉ؿ،فؔـؿ؏ؐؿؾفؑ";
        array[344][0] = "efwIbOD+FcApvK30siSyuw==";
        array[344][1] = "؏ؐؾؑؾ؏ؕؓ؉ؔؔ؎ؕ؉ؐقؽؕ؉ؔقؓ،؉،ؽؕف؎قؐـؒؿؑ؍";
        array[345][0] = "NqSKzDWLwMndlSYX39qsbw==";
        array[345][1] = "؎ؔ،ـؕ؏ؾؒ؉ق؎ؒ؎؉ؐؿؑق؉ؽـ؎؎؉قؐؕؔؿؐ؎ؽفف،؏";
        array[346][0] = "O/QUalYyzfMT74XDNzDIdQ==";
        array[346][1] = "،ؿقفؑؑؑؓ؉؎ؽؾ؍؉ؐؓؔؽ؉ؕؒؐؽ؉ؕؕ،ؒؕؔؑؑؓ؏ؕؒ";
        array[347][0] = "YsKT0fbUHLYrDSNLDYM2CQ==";
        array[347][1] = "ؽـؒؿ؎ؐؐؔ؉ؒؽؑ؍؉ؐؔؐؾ؉ؕؽؾ؍؉؏ؓـق؍ؔ؍ـؾؕ؎ؐ";
        array[348][0] = "9YrzTLs++1xse5WeoQPj3Q==";
        array[348][1] = "؎ـفؕـؕؽ؎؉ؑ؎ؔـ؉ؐؔؽ؏؉ؽؽؽؒ؉ؒ؎،ؔ؏ؔ؍؍ؑؑ؍ؿ";
        array[349][0] = "N3muYlEtCcP66f6HI8gt4g==";
        array[349][1] = "ؑؿقؕؑ؏فؐ؉ؒؔفؒ؉ؐ؍؍ؑ؉ؾؒق؍؉ؓؐ؎فقـ؏ؓؐؒؔؑ";
        array[350][0] = "lVX7ZXgqP2Dw+tnO2/Pp7A==";
        array[350][1] = "ؐؒؑؒؔؑ؎ف؉،؍ؒؿ؉ؐؒؔ،؉ؕؐؓؕ؉ؾؒؐؒؕؽؽ،ؒ،ـؔ";
        array[351][0] = "VhoqpRvnbz4l60AaoPNInA==";
        array[351][1] = "قؐؑ،؍ؒؓؑ؉ؓفـؐ؉ؐ؎ؔؽ؉ؾؕ،ؿ؉ؓ؏؍ؒؔ؏ؒؐؽؑقؽ";
        array[352][0] = "HYy9uju1bFOqHUp/tLC75Q==";
        array[352][1] = "ـؑؐؔؓؓؿؑ؉ؿ؎ؾ؎؉ؐ،؏ؿ؉ؔفؾ؎؉ؾؐؾؕـقؓق؏ؑ؏ؾ";
        By1337̴̡̣͎̪̰͓́́ͨͦ̇́͋͐̎ͪ̀͢͏̵̢͍̥̩͙̜̩̻̪̅̈́͑ͫ̎̆̌͘͝͝();
    }

    private void By1337̴̡̣͎̪̰͓́́ͨͦ̇́͋͐̎ͪ̀͢͏̵̢͍̥̩͙̜̩̻̪̅̈́͑ͫ̎̆̌͘͝͝() {
        array[353][0] = "KYT4FYOIZLRdKppzsanrGw==";
        array[353][1] = "قؐ؏ؒؿؑؽؔ؉ف؎؏ؑ؉ؐ؏؍ؾ؉ؽفؽ؎؉ؒ؎ؓؕؓؔؾقف؎قؿ";
        array[354][0] = "3QXjUyA2wSt1jTN5c/0dUg==";
        array[354][1] = "ؔ؎ـف؎ؔؑؾ؉ؓؕؐؓ؉ؐؐـ،؉ؽؔؿف؉ـؑ؎فؿؐؓ،؏ؓفؽ";
        array[355][0] = "ognlWT2IX4jsXBide/PIuA==";
        array[355][1] = "؎؍ؐؽؽؒؑؔ؉ؒ،فؐ؉ؐؓـؔ؉ؔؔؐف؉فـؕ،ؔؔ؍ؓؒؔؒؒ";
        array[356][0] = "6Tf0REtE68PEN6rPnf9CXw==";
        array[356][1] = "فؽؓؽ؏ؽؿؕ؉ؑ،؏ؽ؉ؐؑؾ؏؉ؽؿؓ؎؉ؑؑؽ،؏ؕؑ؍ف،؍؍";
        array[357][0] = "GPfaPhofjUUBzCNnB5MkPA==";
        array[357][1] = "ؔؕؔفقؓؓؔ؉ؽقق؏؉ؐ؏قـ؉ؽؔؽؓ؉ؿ؎فق؍ؿؔؒؑؐفؿ";
        array[358][0] = "BHVY57tZvKB+pTWX4hhtDg==";
        array[358][1] = "ؕؽؔؿؿؑؓؒ؉ؽؐـؓ؉ؐ؍،ؓ؉ؔؐفؒ؉ففؐؾؽ؎ؐؽقؑؾ،";
        array[359][0] = "hkA9F1kPlvlHK6aBGsAzBg==";
        array[359][1] = "ؿؒؕؾؓؔؐؕ؉؏ؽ؎؎؉ؐفؒق؉ؕ؍ـؽ؉،؎؏؎ؕفؒؔؔ؍ـؾ";
        array[360][0] = "Ichz1m580biIzKO+h6/L9A==";
        array[360][1] = "ؾؒؓ؍ؽ؍ؒـ؉ؕــؓ؉ؐؓؽ،؉ؾ؏ؾ؏؉؏ؓقـ؏؏فؑ؏؎ؐؐ";
        array[361][0] = "iFa8dp3r62wbLgeKlfNzxQ==";
        array[361][1] = "ؕ؍فؔق؎ق؍؉؍؍ؒؕ؉ؐفؓـ؉ؔؕؑؿ؉ؒؑؽ؏ؿفؾؕؿ؏؎ـ";
        array[362][0] = "43/6NxOMEQkpVCxvR5pxhw==";
        array[362][1] = "ؓؔ؍ؔ؍؏ؕق؉؍ؔ؏ؐ؉ؐؐؔف؉ؾؒفف؉ؿـققؐ؍ؽـق؍،ؒ";
        array[363][0] = "CsqJ6VbCRg73J2BH5qBwJw==";
        array[363][1] = "ف؍؍،؍ؒـؽ؉ـؾؓؾ؉ؐ؎ؑؾ؉ؽؒقف؉فؽؿفؓ؏،؍،ؽقـ";
        By1337̶̞̹̥̺͇̱̭̘̹̯̤͖̼̫͖͚̣͓̲̙ͫ̅͒̄̀͐̃ͧͧ̽̓͊͐̕͜͟͢͝͞();
    }

    private void By1337̶̞̹̥̺͇̱̭̘̹̯̤͖̼̫͖͚̣͓̲̙ͫ̅͒̄̀͐̃ͧͧ̽̓͊͐̕͜͟͢͝͞() {
        array[364][0] = "Xx4sgITMsGjeSHC68foUyg==";
        array[364][1] = "ؕ؏؏؍ؔؑ؍ؑ؉ق،ؓؒ؉ؐ؎ؿؑ؉ؔؽ؎ؑ؉قؒؐؾ؎ؾؑؔـؾقؑ";
        array[365][0] = "g9IUvndjlML4+cT6GLxEBA==";
        array[365][1] = "ؿـؽـ،؎؍؍؉ؐ،ؽؽ؉ؐفــ؉ؕؕؿ،؉ؐـ؏ؿقؕ،؍؎،ؾؒ";
        array[366][0] = "iAn4Z8ZSRm9cotDyHyRbBg==";
        array[366][1] = "ؽ،ؔؽـؐؾؕ؉؍ؿف؎؉ؐؓؽؽ؉ؕق؏ؒ؉ؓؾـؔؓ؏؍ؒؒؐ؏؍";
        array[367][0] = "KGfhbmciKQ0kAnD4T1P4ow==";
        array[367][1] = "ؑؑ؏ؾ؎؍ؒؑ؉ؑؔؾؑ؉ؐـفؕ؉ؕـ؏ؕ؉ق؍ؽ،ـ،ؕؑ؍؏ؽؓ";
        array[368][0] = "QfS9Q5/gMjiEGHO1WSVUig==";
        array[368][1] = "ؒ؍؎ؓؓ؎ؕ،؉ؑؒقؾ؉ؐؽؿق؉ؽؕؐؒ؉ؽؕؒؔقؐفؿؿقف؎";
        array[369][0] = "R5hRTGZ4ehhikg9WURQ9Hg==";
        array[369][1] = "ؾؒ؍،،ؔ؍ؿ؉ؓقؽـ؉ؐؑ؎ؿ؉ؽ؎،ؑ؉ؔؑقؿفؑؔ،،؎ف؎";
        array[370][0] = "OiDaSh+Q8ON9twSl8aAaGg==";
        array[370][1] = "ؐقؕقؾؐؽؓ؉ؑقؽ؍؉ؐـؽ،؉ؽؿقؓ؉ؑؿ؏ؓف؍،ؑؿق؏ؐ";
        array[371][0] = "wNs/DhAATOdhPw49HGPhmw==";
        array[371][1] = "فق؍؏؎قؔ؎؉ؿ؍ــ؉ؐؕؽ،؉ؾقؔؿ؉ؿ؍،ؐــؒؑؒ؎ؾق";
        array[372][0] = "v0iUHlkb+R2qbCSTBVAALw==";
        array[372][1] = "ؑف؍؎؎ؽـ؏؉ؒؓؽ؎؉ؐؑؐ؎؉ؔ؎ؒ؏؉قؿ؏ؐؓؑ؍؎؎ؒؒ؎";
        array[373][0] = "p+35jgy53n4I4N7/6rhW6A==";
        array[373][1] = "قؑ؏ؿؔؿؽؔ؉ـ؏ؾـ؉ؐؕ،ؑ؉ؽــؾ؉قؔؑؒؐـق؎ؔؔقؿ";
        array[374][0] = "65P9p7Q5Rpnu2WEq8aIn5w==";
        array[374][1] = "؎ؐ؏ؕؕؾـؔ؉ؿقؕؾ؉ؐفؿؿ؉ؽؑؑؽ؉؏ؕـؓؓؾـف؍؍ـ،";
        By1337͔̻̠̰̱ͦ̀ͫ͒͆͋ͩ̄ͩ͌͘ͅ͏̸̡͎͇̯͇̼̠͖̍̌̍͛ͫ͊ͩ̂͆̇̈́͂̓();
    }

    private void By1337͔̻̠̰̱ͦ̀ͫ͒͆͋ͩ̄ͩ͌͘ͅ͏̸̡͎͇̯͇̼̠͖̍̌̍͛ͫ͊ͩ̂͆̇̈́͂̓() {
        array[375][0] = "gooManewofsy90Pu1/JcOw==";
        array[375][1] = "ؿؓقق؏ؔؐ؎؉قـقؔ؉ؐؒ؍ؓ؉ؔؓؒـ؉فؑـؿـ،ؽؒفؒ؍ؾ";
        array[376][0] = "QcS3CLGQGTtqjj6HWMPdyw==";
        array[376][1] = "ؔ،ؑ؏فؒؒؓ؉ـؾؕؾ؉ؐ؎ؽـ؉ؽؒق؍؉ؔؕؐؽؓق،ؽؑؒ،ؽ";
        array[377][0] = "M27xzfwabX1c9w6HNl0LcA==";
        array[377][1] = "،ـؔؽؕؓفـ؉ؾؒؔف؉ؐؒ؏ق؉ؾـؒؓ؉ؑـؿؽؑ؍ـ؍ـؿ؏ؽ";
        array[378][0] = "PjK5hMrCHwWQXFvVfPO2DA==";
        array[378][1] = "ــؽؿقق؍؎؉ؔؔ؎ؒ؉ؐؔـؐ؉ؔـف؏؉ؕـ؏ؿؒؒ،ف؎ؐؒؑ";
        array[379][0] = "vkzcqK19zWi/PFmnX3mbYQ==";
        array[379][1] = "ق؏؍،ؽؒؔؔ؉ؕؒ؏ؓ؉ؐ؏ؽؐ؉ؽؐقف؉،ـؕؑؾؒ؏ؑؽ؎؎ؿ";
        array[380][0] = "3L1AKs59R4+mgCIZPvtlrw==";
        array[380][1] = "،ـؽـؔؾؒؒ؉ؑؿقؑ؉ؐؕقؔ؉ؽؽؓؓ؉قؒ؎ؓؾؔؾؿ؍،ؿق";
        array[381][0] = "OWuN2BoyiyI36Vh5e/rJBQ==";
        array[381][1] = "ـؽؔؐؔؾؔؕ؉ق،؏ؓ؉ؐؽؔؔ؉ؽؕف؏؉ؾؐؒؓؽؾقؽؾؿفـ";
        array[382][0] = "03LYMymQo09DFV3v017QUg==";
        array[382][1] = "ؽؒفؑق؍؎ق؉،ؐفؑ؉ؐؿ؎؍؉ؽؔؒ؍؉؏ؕؓؒؽقؒؕؔؓؔـ";
        array[383][0] = "p9lt8dje2bdXQuDP9vfwSw==";
        array[383][1] = "ؒؐؓ؎ـؿؿؕ؉ؽؽؾـ؉ؐؓؐـ؉ؔ؎ؔؿ؉ؾقؔؑؿؐؕ؍ؔؓؑؿ";
        array[384][0] = "PDNJAmn/psgHdb6Pnifo+g==";
        array[384][1] = "؎،ؓ؎؎قؓؿ؉؍ؓؔ؏؉ؐؓؔؿ؉ؽؕؓ؎؉ققؾؑـؑفؒؽ،ؐؿ";
        array[385][0] = "bQJSBG+MzjnR9RXCpRAzKg==";
        array[385][1] = "ؓف؎ؿ؎ؓؿؿ؉ؓؒؾؕ؉ؐف؍ؑ؉ؕؔ؍؍؉؏؏ؓؓؐؕؾؓ؎؏ؐؔ";
        By1337̷̴̸̶̧̢̹̩̗̻̤͖͓̤̜̺̯̤̒͆̌̓̌ͥ͒̂̎ͦ͗̈̃̃͊̃̈́̏͘͠ͅͅ();
    }

    private void By1337̷̴̸̶̧̢̹̩̗̻̤͖͓̤̜̺̯̤̒͆̌̓̌ͥ͒̂̎ͦ͗̈̃̃͊̃̈́̏͘͠ͅͅ() {
        array[386][0] = "3uffePVkCWYDb7NucbaQlg==";
        array[386][1] = "؎؎،؍؎ؔؔـ؉ق،؍؎؉ؐفؒؕ؉ؽؽ؎؏؉ؔ؎قؽؽ،ؑؾؐ؎؏ؿ";
        array[387][0] = "wYDBpEktmhmfsUC/0J9GlA==";
        array[387][1] = "ؐؾؾؽؔؒ؍ف؉ؽفؒؓ؉ؐؽؾؑ؉ؾ؍ؕؿ؉ؐؑؔؐفؕؓ؎ؕ؍،؏";
        array[388][0] = "kR6vY8H5t9LeV1kgL0ydAw==";
        array[388][1] = "ؿؑؑؽ،ـقف؉ؾـؽؔ؉ؐ؍ؓ؏؉ؕؕؑؕ؉ؽؕؓؐؽؿ؍ـؔؕؑؑ";
        array[389][0] = "gj4DgqzevGqqAaYreU//nQ==";
        array[389][1] = "؍ؔ؎ـؓـؕـ؉قق؎؎؉ؐـ،ؔ؉ؕؿؔؕ؉؍ؾؾؔؾ؍ؾؿؔؑؓؕ";
        array[390][0] = "dUjoMbHSlP/2/F+K2gOfIg==";
        array[390][1] = "ؓؿؓؔ؏ـؒق؉ؕؑ؎؏؉ؐؒؔ؎؉ؔفؓؑ؉ؕؐؕؿؓق؏ـؑؐؔؾ";
        array[391][0] = "drwUEFNF4yAtA/u30EKuEg==";
        array[391][1] = "؏ؾقؽ؍ؐقؔ؉ؓؽؓؓ؉ؐؒؓ،؉ؕؓ؍؏؉ؽ،ؔؾؾقـؒؽؕ؎ؓ";
        array[392][0] = "YAUYjOD4btUzQYsrfmUnag==";
        array[392][1] = "ؕفؐؾؽؓؔؕ؉؍؎ؽف؉ؐؿؔ؎؉ؽؕق،؉ؑؓؐؾ؍ـؿ،ففؔ؎";
        array[393][0] = "lXFJijp9REEGeOsLi+ccTQ==";
        array[393][1] = "؏ؓؾؿ؍ؒؽ؏؉ؕ؏؏ؓ؉ؐؿـ؏؉ؔؒ؎؏؉؍ؽفؕؑقؒ؍فؾؿؾ";
        array[394][0] = "E/FfYzgfb/QtROadszEXGw==";
        array[394][1] = "ؐق؏ؽ،ؽؑؿ؉ؔ؎؏ؐ؉ؐؕق؏؉ؕؾقق؉ـؓؾ؎ؐؾ،ؽؾؐــ";
        array[395][0] = "Uw1ITYHYiCo4OELnOTvong==";
        array[395][1] = "ؿـ،ؓؿ؍ؒق؉ؽؕ؍؏؉ؐ،فؔ؉ؕؔ؍ؒ؉ؔقفؕؐف؍ؽؕؒؾؽ";
        array[396][0] = "dIICzo56H7EpMyK9NxjHAQ==";
        array[396][1] = "؎ؕ؏ؕؽ؏ؑؕ؉؍ؕ؎ؐ؉ؐؿؒؽ؉ؾ؎،ؐ؉ـق؎،فؽؾؕؿؒ،ؓ";
        By1337̷̶̴̵̢̧̡͇̫͍͇̭̙̮̮̱̪͕̯̅ͥ̀̓́̆̽ͪ͂ͪͬ̾̔͒̕͘̕͜͠͝͝();
    }

    private void By1337̷̶̴̵̢̧̡͇̫͍͇̭̙̮̮̱̪͕̯̅ͥ̀̓́̆̽ͪ͂ͪͬ̾̔͒̕͘̕͜͠͝͝() {
        array[397][0] = "OngP4UdHO04hGTGUg089Cw==";
        array[397][1] = "ؽؾ؎؍قؒ؏ؓ؉؏ؿ؎ؿ؉ؐؾؔؾ؉ؾقؿؕ؉فـؾ؎قؿـؿ؎ق؍،";
        array[398][0] = "DPIMKxpHJ80TDXKC9rpipg==";
        array[398][1] = "ؕؔؓ؏؏؏ؑؐ؉ف،ؕؽ؉ؐؿ؏؏؉ؽؒؾؐ؉ؾ؎؏ؑ،ؕؓ؎ؾ،ؓف";
        array[399][0] = "TKNAHbfAdaPrQS7Lo6QKXQ==";
        array[399][1] = "ؓ،ؒفؔؔؔ،؉؍ؑؔؿ؉ؐ،ؾـ؉ؾؒ؎ف؉ؕ،ؿؔؕؒـؐؐؽؾؑ";
        array[400][0] = "vN1OKMspPGLfvPuCuKh7Ew==";
        array[400][1] = "؍؍ؒؔ؏ـؿؾ؉ؿؕؒق؉ؐؽفؔ؉ؾففؾ؉؎؍فؕ؎،ؾؽفؿـؓ";
        array[401][0] = "ONiF/i+8xOQ9Y5BEgOJkvg==";
        array[401][1] = "ؽؐـؓؾـؽؐ؉ؿؽ؏؏؉ؐؕقؓ؉ؕقؓؕ؉ؒ؍ؓؒ؏؎؏ؿؽؾؑـ";
        array[402][0] = "jo3wDBITiMeR+7bOpwLPvg==";
        array[402][1] = "ـؐؓؓؿؒ؏ؿ؉؍ؕؕـ؉ؐ؏ؾؾ؉ؾ؏ؑؓ؉ؑؽفؑ؏ؑؽؽؾؑؽؕ";
        array[403][0] = "6o6xZRCbSbHfRaGKSXR/hA==";
        array[403][1] = "؏فققؕؾ؎؍؉ؔـؒق؉ؐؒؓؕ؉ؕؒؐ؏؉ؔؾ؍ؔف،قؒقف؎ؕ";
        array[404][0] = "1VOzAlQs4kWgR1+WhXjVzg==";
        array[404][1] = "ؾ،ف،ؽؔؓؕ؉ؾ؎،ؽ؉ؐ؎ؕؐ؉ؽ؍ؿق؉؏ؑـؓؽؓؽف،ؾؑق";
        array[405][0] = "VPH7fOk3lowhPbGLUFHWZA==";
        array[405][1] = "،ؕؔؔ؍فؑؓ؉ؑـؔ؏؉ؐ،ؐ؍؉ؔـؓؕ؉ؕؾ؏ؐفؒؐؑ؏؏ؿؓ";
        array[406][0] = "4FLyx8xN8ptw2K8MbYrNVg==";
        array[406][1] = "ؿ؎ؽف؏؏؏ؔ؉،ؓؕؐ؉ؐؓفؽ؉ؽؿؿؔ؉ؐؓ؍ؕؑقؽ؎ق؍؏ؒ";
        array[407][0] = "YjrXMHcpVB3zQM7mfNDAkg==";
        array[407][1] = "ؐقـؕؑفؽؿ؉ؽ؎؏؏؉ؐؑؔؐ؉ؔ،فؓ؉ؑ؏ؔؐؾفؓؑؿؾــ";
        By1337̢͙͉̫̠̘̺̼̥̦͎̓ͭ́̔͐̋ͯ̌̔̾ͬ͂ͯͨ̎́̽̊̍̐̀ͯͨ̚̚̚͡͝͞();
    }

    private void By1337̢͙͉̫̠̘̺̼̥̦͎̓ͭ́̔͐̋ͯ̌̔̾ͬ͂ͯͨ̎́̽̊̍̐̀ͯͨ̚̚̚͡͝͞() {
        array[408][0] = "jD7JaiiP4U03DkWZ13nnzQ==";
        array[408][1] = "،؏ؑؓـؓ؎ؐ؉ؿؐؒؽ؉ؐؽ؏ؔ؉ؔؔ؍؎؉ؒ؍ؾؾـ؍ؑق؎؍ؐؿ";
        array[409][0] = "5Jcmb/oepktr/Dz3P6IFLQ==";
        array[409][1] = "فؔؕ،ؽؿؕ؎؉ؽؓؾ؍؉ؐؑؾؑ؉ؾ؍ؓـ؉؎فؕؐؾفـ؎ؽؕقق";
        array[410][0] = "v7Z2oNre1HOXSc+dc40lBA==";
        array[410][1] = "ؿ؏ؿؿ؏ؓ؏ؾ؉؏ؒؔؑ؉ؐؾؽؕ؉ؕؕ،،؉قؽق،ؔؐؑ؍ؕق؎؍";
        array[411][0] = "gtbyawD/jgy6OgwmA1Z7UA==";
        array[411][1] = "،ؒؕؔؽ؏ؑؓ؉؏ـ؎ؒ؉ؐؾؔق؉ؽؾؔؽ؉فؑ؍قؐؽؐؿؐؿؾف";
        array[412][0] = "O149r/M/1Mgyv51wa9CZmQ==";
        array[412][1] = "ؐؓفؓ؍فؒؐ؉ؓؾؽؓ؉ؐؒؑ،؉ؕ؏ؾق؉ؑؽؑ،ؒؾ؎قؒؕؓؕ";
        array[413][0] = "A9jJ0WVIf5TCI4yoXq8MRg==";
        array[413][1] = "ؾ،؍ؓؽؾؕـ؉؏ؕ؍ؽ؉ؐ؎ؓف؉ؽؔؓؿ؉ؕؓؿفؾ؎ؒؿ؍ؕ؍ؔ";
        array[414][0] = "z8Mjtu8F92LqZh0za24bOA==";
        array[414][1] = "ؿؓؕ؎ؕـ؏ؕ؉ؕؾ؍ؔ؉ؐ؍ـق؉ؾؽ،ق؉ؓؿ؏ؑؾ؏؍ؽؽقؓؔ";
        array[415][0] = "iA/PtMjNypprPBJ72n0T3g==";
        array[415][1] = "ؽؕؒؐـؕؓ؏؉ؑفـؽ؉ؐؓفؒ؉ؽؾؾؒ؉ؓؿ؎ففؒؔؓؿؓؾؐ";
        array[416][0] = "TbtIHDJJFslQpfJA9EZvIA==";
        array[416][1] = "ـؽ؎ؐ؎ؿؿؑ؉؏فؔ؏؉ؐ،قؕ؉ؾ؎فـ؉؎،ؔؽؒؑؾؿ؏،فؾ";
        array[417][0] = "X0/W8L0j1Va0BCL3HlxLUA==";
        array[417][1] = "،ؔؒؿؔؓـؕ؉ؾؕؿق؉ؐف،ؓ؉ؕؑؑؔ؉؎ـ،،؎،قـفؽقؾ";
        array[418][0] = "5ZisHRdhB08TLW6sbg32oA==";
        array[418][1] = "ؓؔؿ،قؕق؎؉؍ؓؒق؉ؐؔـؿ؉ؕ؍؍ؿ؉ؐؔؽ؍ؒفؓؿ؍ؽفؐ";
        By1337̷̨̧̛̻͕̻̞͙͓̱͇̥̖̪̱͇̊ͬ̀̇̔̆́͐ͦͦ͌͛̔ͩͦͩͮ̄̾͘̕͢ͅ();
    }

    private void By1337̷̨̧̛̻͕̻̞͙͓̱͇̥̖̪̱͇̊ͬ̀̇̔̆́͐ͦͦ͌͛̔ͩͦͩͮ̄̾͘̕͢ͅ() {
        array[419][0] = "wMHAFRdZuhQVc/za0sWydg==";
        array[419][1] = "ؿـؐ؏قفـ،؉،ؑ،ق؉ؐؕ؏ؿ؉ؽؔؕؔ؉ؔؔ؎ؔؓق،ؑؾقؑف";
        array[420][0] = "RSxJPvAyEI2uRRHbIBs5Og==";
        array[420][1] = "؏ؓ،؎ؾؒؐؓ؉ؒ،ؒ؏؉ؐفؑؐ؉ؔؿـ،؉ؔفؽؓ؍ؕؿؔؓفـؾ";
        array[421][0] = "DEx5kxigntkEg4j6JzMHtw==";
        array[421][1] = "؏ؽــؓؒؿ،؉ؐ،ؽؑ؉ؐ؎؏ؑ؉ؔ،،؍؉ؐ؎،ؾـؐقؿقؑؿ،";
        array[422][0] = "cwmttJi+XYxkJvGwqy6VNA==";
        array[422][1] = "ؓؾفؿ؎؏ـ؎؉؎؏قؔ؉ؐؒفف؉ؽ؎ؔؽ؉ؓؓ،فؿؒ،ؑؑؒؔؓ";
        array[423][0] = "9mdqSxbcdQSPRWAhIuAP9g==";
        array[423][1] = "؏ؕ؍؍؏ؓؒؽ؉ؓؽؔ،؉ؐ،ق؎؉ؔؓؿؾ؉؎ؒ؍ؓـؔؑ،؏ؔؽؔ";
        array[424][0] = "nJNW3m71Q4R4zytuk97/vg==";
        array[424][1] = "ؿ؍؏ؾفـؕ؏؉؍قؒ،؉ؐؒؽؔ؉ؔ؍قؒ؉؏ؽؿفؕـ،قـفؐـ";
        array[425][0] = "Ndqybc/iP8pbS5PojuUuUQ==";
        array[425][1] = "ؽؿؐؕفــؾ؉ؓؽ؍ؽ؉ؐ؍ؕ؏؉ؾ؍ؕؐ؉ؿؑ؏ؑ؏ق؍؎ؽق؍ف";
        array[426][0] = "EAMfgEYvtAbhqBB5APNV9Q==";
        array[426][1] = "ؽؑق،قؓؽؓ؉ؾ؏ؔ؏؉ؐؓ؎ؑ؉ؔؽقؔ؉ؓؑ،ف؍ؕقؒـؔؽـ";
        array[427][0] = "gm659bAFciAX8uISnQyUeQ==";
        array[427][1] = "فؒؔؽؓؒؐؑ؉؏ؕقق؉ؐـؐؓ؉ؾؿؕؔ؉ؐق؎ؑؿؐؔؓؽؾ،ؽ";
        array[428][0] = "3tETBMkxJYQSbI2MxSIYDg==";
        array[428][1] = "ؽؕقؽـؽؽؽ؉ؐ؎؎؏؉ؐفقؐ؉ؾؑؔق؉ؕؑؑ؏ؑ،ؕؔؿؒؔق";
        array[429][0] = "y5Elp3lKbgBLD1d1EP0x4w==";
        array[429][1] = "ؑؽـؐؿققف؉؍ـفؒ؉ؐؓ؎ؐ؉ؾؓ؍،؉،؏ؒؔـؐ؍فؓؿ؍ف";
        By1337̴̷̵̷̴̨̨̛̛̗̼̱̦̗̹͙͇͖͍̭̺̤̍ͤ̋͌̈́ͥͩ͌̇̒͊̓̌̑̒̕͢͠();
    }

    private void By1337̴̷̵̷̴̨̨̛̛̗̼̱̦̗̹͙͇͖͍̭̺̤̍ͤ̋͌̈́ͥͩ͌̇̒͊̓̌̑̒̕͢͠() {
        array[430][0] = "ptlEo/GZFuVlnkyiHEMlZw==";
        array[430][1] = "ؑؽؕ؍ؕ؏؍؎؉ؿؔ،،؉ؐؔ؍ؾ؉ؾ؍ؕف؉،؎ؒؕؽؒؒؽ؍ؾؿف";
        array[431][0] = "gfFO02juE30Geqm52vf36w==";
        array[431][1] = "ؾؐفؾؐ،،ف؉ــؿ؎؉ؐ،؍؎؉ؔـؔؑ؉؎،ؔؿؒ؎ؿؑ؏ـقؔ";
        array[432][0] = "WewqKnnEgctIQTQR+qHMdQ==";
        array[432][1] = "ؽـ،ـف،ؕؾ؉ؒؐؑؒ؉ؐ؏ـؑ؉ؾؽؿؓ؉ؒؐ؏ؑؒؽؾـؑؑـف";
        array[433][0] = "K875K655nbKTowTevOB43A==";
        array[433][1] = "ؒؓؾؾؔ؍ؾؾ؉ؔؓؑؓ؉ؐ،ؕؑ؉ؔؔؾ؏؉،ؽؑـفـ؎فقـ،،";
        array[434][0] = "9keOCyM6hrBPaygPtITPgw==";
        array[434][1] = "ؑؒ؍،ؐفؽؑ؉ؓؽؓ؍؉ؐ،ؿؓ؉ؔؔؔ؍؉؍ؓؑؑؑ،؍ؾ؎ؑفؽ";
        array[435][0] = "jPDotzw2Ad1eVyO8Y6xYbg==";
        array[435][1] = "،،ؔ؍قفؒف؉ؔ؎ؑق؉ؐؑـ؏؉ؽؒؔؒ؉قؓؔؕ؍ؔــ،ؒ؍ؕ";
        array[436][0] = "K/coZ46Ps90ihk9YreE7hg==";
        array[436][1] = "ق؏،؍ـؓفـ؉ؐ؍ؿ؎؉ؐؓ،ـ؉ؕؓؑ؎؉ؕؽؑـؑؽؒؑؐؒؽؒ";
        array[437][0] = "/7NMvvi+7Efp/sZzrFqA8A==";
        array[437][1] = "؎ؕؿ؍ؑؔ؍ؓ؉؎ؽ؏؏؉ؐ؏ف،؉ؾ؍ؔ؏؉ؔؿؐؔ؏ؿ؎ؾؑؐؑ؎";
        array[438][0] = "ElRhQH1Fo8HBR3FS0S8ZGA==";
        array[438][1] = "ؾؽؓ؏ؓ،؏ؾ؉،قؐؑ؉ؐؒ؎ؕ؉ؔـف؍؉ؕؐؿؒؽؕف،ؑؔ؎ؽ";
        array[439][0] = "Vgu2B2giOrMAtMo3Z3nkjA==";
        array[439][1] = "ؽؿ،ؐؕ؍فؾ؉ؑؑقف؉ؐ؏ؓ؏؉ؾق،ؑ؉فؿ؍قؒ،؏؏ؾؽ،ؿ";
        array[440][0] = "HAlIiYO4fCSeyRKsEVIcjw==";
        array[440][1] = "قؑؐؑؕفقؒ؉ؒؽؑق؉ؐ؏ؓ؍؉ؾؒؾؒ؉ؓؕفؿف،ق؎؎ؑــ";
        By1337̨̨̛͖̺͈̪͚̰͇̣̩̬̝́ͭͧ̑ͧͥ̑̎̇͐̑ͬ͑͑̆͛̒̌̀ͭ̽̚̕͜͠ͅ();
    }

    private void By1337̨̨̛͖̺͈̪͚̰͇̣̩̬̝́ͭͧ̑ͧͥ̑̎̇͐̑ͬ͑͑̆͛̒̌̀ͭ̽̚̕͜͠ͅ() {
        array[441][0] = "zoTA2LgiGKRLsC2U8THoHQ==";
        array[441][1] = "ؓ؎ؕؓؾؐؐ؏؉؎ؽؐؔ؉ؐؕف،؉ؕؐ؏؏؉قؔفؕ؍ؽؽ؎ؐؒ؏ـ";
        array[442][0] = "lcpiLZuULc2qrOEqDIvBLw==";
        array[442][1] = "،قؽؒ؏؎ؒؐ؉ؿقؐؓ؉ؐـؿ،؉ؔ،ؑؓ؉ؑؐؾ؏ؓ؎ؿف،ــ؏";
        array[443][0] = "ZhMQPbIHNFEUeJeHGeUCww==";
        array[443][1] = "فؒ،؎ؓؽؑؑ؉ؕؑؿؓ؉ؐؽ؏ؿ؉ؾؓ؎ؽ؉ؾ؍فؽ؏ؑؽؽؔؽؐـ";
        array[444][0] = "mymhEDBD9GXmoXFZMI0u2g==";
        array[444][1] = "ؽؒ؍ؔ،،ؐؐ؉ؓفؾؔ؉ؐؽفؔ؉ؔ؏ؽؒ؉ؑؓ؍ؔؾؒؽؓ؏ؐؓؔ";
        array[445][0] = "GisQA4xF1ZXwAcM8ZWVZRA==";
        array[445][1] = "؎ؾفق؍؍ؕـ؉ؑؕؽؕ؉ؐ؎ؐـ؉ؽؿؔ؏؉،ـ؍؍ؿق،قؐؑقـ";
        array[446][0] = "/Tqe61g0xKgS83cnrwIe0Q==";
        array[446][1] = "؍؍؍؏ؽؽؓؿ؉ؾؔؔؽ؉ؐ؏قؽ؉ؽـ،ؔ؉؏قؓؾ؏ؔؽؽؓ؏؎ؑ";
        array[447][0] = "4x8tuZK0TC9gd09urrGS+A==";
        array[447][1] = "ـؐق؏ؾؓـؽ؉ؿؑؑؔ؉ؐؿـ،؉ؔؒ؏ؿ؉فؓؾقؔ؍ؑف؍ؔؑـ";
        array[448][0] = "AF0XUVs9zlatwxxAnSnBoA==";
        array[448][1] = "؎ؔ؏ؒؽؓؒؕ؉؍ؾ؍ؐ؉ؐؕؒؕ؉ؕفؔؾ؉ق،ؒؾؾؓؔؔ؏؍ؐ؍";
        array[449][0] = "IP8aqZ4MdfKF5wqhFnUapw==";
        array[449][1] = "ؔ،ؓ؍ـؕؿؒ؉فؑفؓ؉ؐؐؕف؉ؔؿؽؕ؉ؾفؑؐ؏ـ؏ؽؓؓؒف";
        array[450][0] = "CQpaCYIdEiX6hi+dSTcANw==";
        array[450][1] = "؎ؐؿ؏،؎ؾ؎؉ؒؿ؍ؔ؉ؐؒؒؓ؉ؾؕؒؐ؉ؽؒؑ؏؎ؿ؏ؐـؕؑؔ";
        array[451][0] = "z9isOfG3BAJ6vVE1Qm6CfQ==";
        array[451][1] = "ؾفؕؔؒ؎ؾؾ؉ؾؒؽؐ؉ؐفقؔ؉ؾؓؽؐ؉،ؑؓؾ؍ؓفقــ؎ؿ";
        By1337̴̶̯̮̥̫̹ͩ̀̓̌ͮ͢͢͠͏̻͔͙̰̦̦̱̄ͪ͐̓́͛͛̀͋ͩ̏̔ͨ̒ͩ̊ͧ();
    }

    private void By1337̴̶̯̮̥̫̹ͩ̀̓̌ͮ͢͢͠͏̻͔͙̰̦̦̱̄ͪ͐̓́͛͛̀͋ͩ̏̔ͨ̒ͩ̊ͧ() {
        array[452][0] = "1bT7KFRBLNDBYT86ukqTMg==";
        array[452][1] = "ؽؓؔ؎ؕؒ؍ؒ؉ؔؑؔؐ؉ؐؓؐف؉ؾـ؎؍؉ؕفؒؾؿفؔ؍ؽؔ؎؏";
        array[453][0] = "o9Rz70ngjugqwCiOrJ7Asg==";
        array[453][1] = "ؾؐ؏ؽـؿؐف؉ؐؾؔؓ؉ؐؒـف؉ؕؿ؎ؒ؉ؿؒؑؿؒ؏ـ؎؍ؽؔف";
        array[454][0] = "wWr30wA5fRxIyOltn6Aw3g==";
        array[454][1] = "ؒؔ،؍ؾؑؽ،؉ؐـؓف؉ؐؾؾ؍؉ؕ؎؍ـ؉ؑؕؾؐفؓؐؑ؍؏ؑؾ";
        array[455][0] = "tJvwQgGe91eHivpFDSLPZA==";
        array[455][1] = "،ؾؾؐ؎،ؑق؉؎ؾف؏؉ؐؔفؿ؉ؔ،ؐ؏؉ؒقؐؕؓ؏ؒؓؓؐ؏ؔ";
        array[456][0] = "FsWy9siJBSY3rpuhKH19hg==";
        array[456][1] = "ؽؐؾؕـؔ،ف؉ؕؓ؏؍؉ؐ؍ؒؒ؉ؾؕؒ؍؉ؾ؍ؿ؍ؽقؽ؍ففـ؎";
        array[457][0] = "e+a2M/RdWw+LTlis/Z9FOg==";
        array[457][1] = "ـ؍ؕؒ؏ؒؒؒ؉ف؏ؔؿ؉ؐؽف؏؉ؾؑؔؒ؉ؒؾؐؽؔؒؒؔؑ،ؽق";
        array[458][0] = "ju6UkaiALeXUzXETsSOO0w==";
        array[458][1] = "ؾؐؒـ؍ـ؎ؽ؉؏ؽؓؐ؉ؐؒؐق؉ؔؿؾؔ؉ؔ؍ؿ؎ؔ؏ؽؽقؑؐ؎";
        array[459][0] = "lVHg9DOT53t42DE2kEKKDg==";
        array[459][1] = "؏ؐؔؔؿقؒؒ؉ؐؓؕؕ؉ؐؓؒؒ؉ؽ،؎ؐ؉ؾؒ؏ؕؐؒ؏،؍ؔؕ؏";
        array[460][0] = "y0NIhQFeCXGVyjuF3wTqsg==";
        array[460][1] = "ؒؽق،ق؍ؾؾ؉ؿـؑق؉ؐؾؾؽ؉ؾف؍ؔ؉ؐؾ؍ؽـؒؒؓؑؕؐؐ";
        array[461][0] = "piMlfX2o6uBJcA2ddc4U9A==";
        array[461][1] = "ؔف،،؍ؿؔؑ؉،ق؍ؕ؉ؐؕؒؔ؉ؕ؎؍ؽ؉ؿـؒؓؔؐ،قؐ؍ؕؐ";
        array[462][0] = "AxgGTqjqZIMuo+T7wCy1/g==";
        array[462][1] = "ؔؑؕؑق؎ؕؑ؉؎ؔـؽ؉ؐ؎ق؏؉ؕؓ؎ؕ؉ـؐفؾؑؐؑ؎ـؒـؕ";
        By1337̸̷̛̰̘̳̳̥̘͇̹͓͕̙̩̻̂ͬ́̅͒̿̍ͣͣ̌̃́ͩͪ̓ͬ͑ͮ͑̆͌́͛ͣ();
    }

    private void By1337̸̷̛̰̘̳̳̥̘͇̹͓͕̙̩̻̂ͬ́̅͒̿̍ͣͣ̌̃́ͩͪ̓ͬ͑ͮ͑̆͌́͛ͣ() {
        array[463][0] = "Gx79WqCTUslDC8ULB1oOlA==";
        array[463][1] = "ؿ؏فؽؒؔؑؐ؉ؑؽق؏؉ؐؿفؽ؉ؔؒ؎؍؉؍؏ؿؓؿؓـفؿؑؒ؎";
        array[464][0] = "7bzr5yt9e8EguLrafPZSrA==";
        array[464][1] = "؍ـ،ؾ؍ؓ،؍؉؍؎ؑ؍؉ؐؓ،ؓ؉ؕؿؕـ؉ؕ؏ؒؕؾؿؓؒ،؍ؒؒ";
        array[465][0] = "lvBxEy+v7zsdGWZrdyb04Q==";
        array[465][1] = "ـؿ،قؐؿؓـ؉؍،ؐؔ؉ؐؾؒ؎؉ؕ؏ـؿ؉،ؔؑؒؔ؏ؔقؿؕؿؿ";
        array[466][0] = "iYUEPteeXJLZLOumc+zMZw==";
        array[466][1] = "قؑف؎ؒؿؒؐ؉؎قفف؉ؐفؐؾ؉ؔؿـؾ؉؏ـ؍ـؔؒـؐؐؑؕ؍";
        array[467][0] = "wobKsu8w/fjThb7aTtygog==";
        array[467][1] = "ؿؕفؾؒ؍ؑؓ؉ؑؐقؐ؉ؐؑفؽ؉ؕ؍ؒؑ؉قؒؐ؏ـؓ؍ؒ؏ؿفؿ";
        array[468][0] = "NK/btclOnPZypxf9CTzheg==";
        array[468][1] = "ؑ؎،ؑؾ؎،ؾ؉؍،؎ؑ؉ؐؿؿ؎؉ؔ،فؾ؉ؒؔؔؾقؑؑؓـ؎ؑف";
        array[469][0] = "R42Q7LuzkRVFiI8NprcHRg==";
        array[469][1] = "؏ؔؒ؏،ؐ،؎؉ـؿؐؽ؉ؐ،قؾ؉ؕ؍ؐؐ؉ؾؑ،ؓ؍ؿق،فقؾؾ";
        array[470][0] = "l+NFXJiZwPYFJp9HX0w+Nw==";
        array[470][1] = "ؐؓؔ؎ؿؿؔـ؉فؑؿ؏؉ؐؐؽ،؉ؔؒؓق؉ؔ؎ؐ؍؏ؐؕؐؾـ؏ؾ";
        array[471][0] = "dVO9EeAAx+HkYBLoptgjQg==";
        array[471][1] = "؎؏ؑ؏ؿ،ؒؕ؉،ؐـؽ؉ؐؐـ،؉ؾـؓؑ؉فؓ؍؏ق؍،ؒف؍فؐ";
        array[472][0] = "RswN0P0rGw9IOYdXnlqmsQ==";
        array[472][1] = "؏؍ؐؓؽؽـ؏؉ؔؒؔف؉ؐؿؽؕ؉ؾؐقؽ؉؎ؔؒ؍؎ؿفؕؑؽؓؽ";
        array[473][0] = "7dwasY00dtW+qtQUnHfC0g==";
        array[473][1] = "؎؎ؓؾؔؓـؾ؉ؾؾؓؒ؉ؐؔؽؓ؉ؽ؏فق؉ـؽؾؾــ؏ؾ،ؓؓؽ";
        By1337̵̡̨̧̣̰̖̳͖̘̪̗̭̬̯̰̠̣͖̗͍ͮ́ͤ͗̏ͮ̅̍ͧ̇͋͐ͣͥͬ̕͜͝ͅ();
    }

    private void By1337̵̡̨̧̣̰̖̳͖̘̪̗̭̬̯̰̠̣͖̗͍ͮ́ͤ͗̏ͮ̅̍ͧ̇͋͐ͣͥͬ̕͜͝ͅ() {
        array[474][0] = "nT8C704PEtk3G8Fs1ZaLUA==";
        array[474][1] = "ؑؑؔؽؓفؑؑ؉ؾقؾق؉ؐؽ؍ؓ؉ؾق؍؏؉ؽـ؍ؿؓ؍،ؑؕق؏ف";
        array[475][0] = "pKQoIXUJOyCZN+2GWA13+w==";
        array[475][1] = "فـؾؕؔؑؒؐ؉ف؎فؽ؉ؐ؎،؍؉ؔـؿ؍؉ؒؾؒ؏؍ؾؕـؽؓقؕ";
        array[476][0] = "dLi50vvHytSDYyWCy3N3gA==";
        array[476][1] = "؎،قؽق؏ؿف؉ؒؑ،ؑ؉ؐؿؑؾ؉ؾؾؒؒ؉ؾؓؽؔفؾف؎ؿ؍ؾ،";
        array[477][0] = "msHyL2HvPRdLTl0syHPWQA==";
        array[477][1] = "ؐـف؏ؿـؾؔ؉ؓؽ؎ؕ؉ؐؿؕؾ؉ؽؿؒؔ؉ؑـ،؍ؑؕ؍ؑ؍ؕؑؿ";
        array[478][0] = "xAlMHOdat0kJ6hj220o1iQ==";
        array[478][1] = "قؕؽؿؒ؍ؿؒ؉ؽؽقق؉ؐـؽؾ؉ؽفؓؕ؉،فؐـؿؑؔؒؔؕؕؒ";
        array[479][0] = "j3Mc+SdA/uqf0hoS59YLtQ==";
        array[479][1] = "؎ؒؿؓؕؒؕ؎؉؎ؐ،ؿ؉ؐ؏ؔؐ؉ؕؑف؍؉ؔفؽؐؓؑ؏ؔؾؐـؿ";
        array[480][0] = "I+DYWpGu4r+ONhh3yZPxwQ==";
        array[480][1] = "ؿؾ؍؏ؓؑفق؉ؔ؍،ؐ؉ؐؽ؍ق؉ؕؾ،؏؉قؒ،؎ؐؓؔؒ،؏فؿ";
        array[481][0] = "a4Q6BhVVopWovsWYJywgCA==";
        array[481][1] = "ؒفؿؐؑ؍ؒؐ؉ؐـ؎ؓ؉ؐؒؓ؏؉ؾؾؑ،؉ؾ؍ؓقؿؐ؍؏ف؍؍؍";
        array[482][0] = "LMXb6Jh6qOhsh53iV7KJfA==";
        array[482][1] = "ؿؔؕؕؿفـؐ؉ؒؕ؍ؔ؉ؐـؿق؉ؾؓؽؕ؉ؕؿؒؿقؒ؍؎ـ،؏ؓ";
        array[483][0] = "g/1xkVZPXy+pJe6USPNEiA==";
        array[483][1] = "ـ؏ؔؿ؍ؿؒؽ؉ؿ،ؕف؉ؐؓؒ؎؉ؾؔؒؕ؉ؓؐ؎ؔ؎ؾفؕؕ،ؾ؏";
        array[484][0] = "p6HYQ3rcvspZDGtQcsgw0A==";
        array[484][1] = "؍ؒؐ؏ف؎ؿ؎؉ؽؾؑؒ؉ؐؐؐؿ؉ؔؐؒ،؉؏فؐؿؒ؎؏،ؒ؏ــ";
        By1337̛̛̦̞̘͓̳͓̩̖̝̝̗͙ͩ̄͒̾̾̏ͬ̃̉ͭ̑͒̌̿́̿ͧ́ͤ̑̎́̽͟͜͠();
    }

    private void By1337̛̛̦̞̘͓̳͓̩̖̝̝̗͙ͩ̄͒̾̾̏ͬ̃̉ͭ̑͒̌̿́̿ͧ́ͤ̑̎́̽͟͜͠() {
        array[485][0] = "r5DXH37AsXtwBxipDdlf+w==";
        array[485][1] = "ؿقؐؐقؓؿؿ؉ؐؾؽؐ؉ؐؑؿـ؉ؾؐؒف؉ؽفؐؑ،فؽؓؿؽ؏؍";
        array[486][0] = "TgDXPwi3KRjRugWuooQvww==";
        array[486][1] = "،قؽ؎ؕ؎ؒؐ؉ؒؾؐ؎؉ؐؑؒؒ؉ؕؐ؏؍؉قـفؾؿقؿ،ؔؓ؎ؿ";
        array[487][0] = "6r6Bry16TG3o9vQoS1sAsw==";
        array[487][1] = "؎ؐؾؽؽـؔ؏؉ؔؔ؎؍؉ؐؑؕ؍؉ؔؑؕؒ؉؎ؓ؍ؽؓؿ،ؽفؓ؏؎";
        array[488][0] = "nkOvYvsVy9nl1rrnyWxMfA==";
        array[488][1] = "ؿؒقؑؐف؍ف؉؎ـؽؑ؉ؐؑفف؉ؽفؔف؉،؎ؿ؏ؾؕؑ؍ـؒؾؔ";
        array[489][0] = "/ZxhAQX2B+EQQ5GCp24wuQ==";
        array[489][1] = "ؑؾفؑؽ،ؾ؎؉؏؏ؽـ؉ؐؓؔؕ؉ؕؿؾؓ؉ؿؒ؎ؽف؎ؑفؒ؎فؐ";
        array[490][0] = "bn7PaG7KiuMHMFgyRGXB5Q==";
        array[490][1] = "؍،ؒؐؽ؏قؔ؉؍؏ؓؓ؉ؐؽؔؿ؉ؕ،ـق؉ؒؾؕؿؓؔ؍ؓؾؓـ؍";
        array[491][0] = "4LpjDic33Z1HqJEZIRHpgA==";
        array[491][1] = "ؔؒـؒؽ،ؾؐ؉ؕؑؐـ؉ؐق،ؔ؉ؽؓؔ؍؉؏ؒؽؐؒؽ،ؽؐفؑـ";
        array[492][0] = "HXLA7Rx5wDZSFCFTsmRK2w==";
        array[492][1] = "ؓق،؎ق؏ـؔ؉؎ؐؿ،؉ؐؐؐؾ؉ؔؓؒؑ؉ؿقؕ،؎ؽؒ؏ؒ؏ؕؐ";
        array[493][0] = "AwmmIJrDfP2A/yRsPUaZmg==";
        array[493][1] = "ؽؔـؒؓؐف،؉ؾؕؔؔ؉ؐؔ؍؍؉ؾؕ؏ؿ؉ق؍،ؽ،،ؐؾؿـؔف";
        array[494][0] = "dq4rKZCLgmlRppf1JuOV5w==";
        array[494][1] = "؏ؓؒؒؒـؔؑ؉ؓ؍ؕؒ؉ؐـ؍ؑ؉ؽؽـ،؉ؐ،ؓقؔؓؑؾفؓ؍ؑ";
        array[495][0] = "mKBxO9T644u91tXoHtkuow==";
        array[495][1] = "ـؔقؔؑؿؿؐ؉؎؍؏ـ؉ؐقؒف؉ؔ؍ؿ؍؉ؐقفؓؒ؍ؕ؍ؽ؏؎ؓ";
        By1337̷̸̧̨̨͇̬̫͚̯̯̙͚̬̗̥̼̳̱̭̠͍̃͛̊ͣ̓͊͂̉͗͆̌̐͌̽ͫ͝͝͝();
    }

    private void By1337̷̸̧̨̨͇̬̫͚̯̯̙͚̬̗̥̼̳̱̭̠͍̃͛̊ͣ̓͊͂̉͗͆̌̐͌̽ͫ͝͝͝() {
        array[496][0] = "KX0M09k915rg5ODLZHOREQ==";
        array[496][1] = "؍؍ؿ؎،ؒقؐ؉؍؏ؕق؉ؐؽ؎ؿ؉ؾؐـق؉ؕؑؕفؽؔ؏؏ؕؽ؍ؑ";
        array[497][0] = "cNoE95fUh9I9b9mw2DAKTw==";
        array[497][1] = "؏ـقؕقؽؐؽ؉؎قؔ،؉ؐـ،؏؉ؔؔؔؽ؉ؾؽؿ،؎ؾؕؕف؍ؑؔ";
        array[498][0] = "jMKO57YWgJ3kwHM/UooORA==";
        array[498][1] = "؍ؔؕ،ؿف؍؎؉ؒ؎ؾ؍؉ؐؾؔؒ؉ؕؽؽؐ؉ؐؔؔفؑـفـ،؏ؽ،";
        array[499][0] = "dalUW0lYii/OdgEtzge/Iw==";
        array[499][1] = "؏ؐؿ؎ؓؓؾ؏؉ؾؿ؏ؐ؉ؐ؏؏؎؉ؽؐـ،؉ؒ؍ؐؽؕ؏؎ؓؿ؏ؒؕ";
        array[500][0] = "6FDJMOt9wbOdvYGqfIpDIQ==";
        array[500][1] = "؏؍ؑؾـ،؍ؑ؉فؔؔـ؉ؐؽ؍ؿ؉ؾؔؒ،؉؍؍ؒـؽ؍ؔؾؑؾ؏ؐ";
        array[501][0] = "OPjnuazcbRZAw97XMuJgZQ==";
        array[501][1] = "ؒقؔؾؽـ؏؎؉ف؍؏ـ؉ؐؑؿؕ؉ؾق؍ف؉ؒ؍فؕؔؕقفـف؎ؓ";
        array[502][0] = "SvmoIFOEBFxa2hs93s9UYQ==";
        array[502][1] = "قؒففؾ؎ؐؾ؉ؐؐؾف؉ؐ،ؿف؉ؔؑؑؑ؉ؐؔؕؾؕؽؐؑؔؑ؎ؽ";
        array[503][0] = "GxjDr+/tSgJzSw0Xh62qJA==";
        array[503][1] = "ـؒ؎؏فؽؾـ؉ؒ؍ؑؿ؉ؐؒؕؐ؉ؔؔؕؽ؉ؓؽؿؕؓؓؔؕؐؓؔ،";
        array[504][0] = "nv82JqNywW7VvRY5jtDijg==";
        array[504][1] = "ؾقؐ؎ؐؐؾف؉ؑ،ؔؓ؉ؐفؕؾ؉ؕؽ؎ف؉ؕؾقؕؿ؎؎قؿؿ؍؎";
        array[505][0] = "3cwh+yotmkKQSJmFsP+OdQ==";
        array[505][1] = "قــؽؒؔفـ؉؎ؓؕ؏؉ؐـؿؒ؉ؾؐ؍ؽ؉ؑؽف؎فؔؽؔق؎ؐؕ";
        array[506][0] = "zTcIMtkeAsKcMIpuCdu1aQ==";
        array[506][1] = "؎ؕؐقؕ؎ؽؒ؉ؕؓ؏ؑ؉ؐؔؒف؉ؔف؏ؽ؉فؿؔ؎ؾقؽـؒ؎فؿ";
        By1337̴̸̢̢̛͕̞̼̭͓̝͖͎̺̦̯̞ͤ̂͂̏̑̄ͫ͆͋̔͋̿̍͑̈ͤ̄̌̕͜͝͠͞();
    }

    private void By1337̴̸̢̢̛͕̞̼̭͓̝͖͎̺̦̯̞ͤ̂͂̏̑̄ͫ͆͋̔͋̿̍͑̈ͤ̄̌̕͜͝͠͞() {
        array[507][0] = "5ifXgUH3pkV/6IY57DiF6A==";
        array[507][1] = "؍ؓ،قـقؑؑ؉ؒؐفؿ؉ؐ،؍ف؉ؽؽ؍ق؉،ؓؑ؎ؑؑؾ؏ؒ؎ؔؓ";
        array[508][0] = "heHcbS/YO/xXAyaDAUDYCA==";
        array[508][1] = "؎ؐفؒ،فؿؾ؉ؒفؾؒ؉ؐ؏ق،؉ؾ،ؿف؉ؾؔقؕ؏ؕؔؕؑؓؿؕ";
        array[509][0] = "500wAUEzLuILUYWqM8blEQ==";
        array[509][1] = "ؿ،ؓؽؓؔقؑ؉ف؍؍ؓ؉ؐفؐؔ؉ؾؽؾؕ؉ؕؕ؎ؽؾؽؓـؔؿ؍ؓ";
        array[510][0] = "FQgEeA4dprpMf52bBGLxTw==";
        array[510][1] = "ؕ؏ؕؾؕ؏فؑ؉ؓ؏؏؍؉ؐؽؑف؉ؽ؏،ؑ؉؏ؐؑفؐؽقؐؕ؍؍ؒ";
        array[511][0] = "w5V+8LB8oaqMrVS/oAaVPA==";
        array[511][1] = "ؽؑؿـؾؕ؎ؾ؉؎ؽؿف؉ؐؑؔق؉ؽ؏؍ؽ؉ق،؏ؕؑـؓ؍ؽؒؕ؏";
        array[512][0] = "8Gp7Ws078GHY7OEJM45cpA==";
        array[512][1] = "ؓؐ؎؍؍ق،ؓ؉؎ؿقؐ؉ؐؓؔ؍؉ؾؾـؑ؉ؕؐ؎ؒؓؐقفـفؾؒ";
        array[513][0] = "xJPtDJHerm2c6PBYs0nTCg==";
        array[513][1] = "ؓ؏؎ؒؾؐ،ـ؉؎ؓؿؔ؉ؐؓؑؾ؉ؕؐؐؓ؉قؽف؎ؽؑؐف؎؎ؕف";
        array[514][0] = "bCfAxp6mZyhNSZCFnMaIOQ==";
        array[514][1] = "ؒف؍ؽؾؔؿف؉ؒ،ؓؓ؉ؐؐؓ؏؉ؕؓ؏ؓ؉ؕ؏ؒؽؔ؍؎ؾـفؾؑ";
        array[515][0] = "8UXRbw2UkF0z79dwz/4vTA==";
        array[515][1] = "ؿ،ؕؐ؏ـؾف؉ؓؐؐ؍؉ؐقؾ؏؉ؔ؎،ؓ؉ؽـؽؔ،ؑؓؓ؍ؒ،؏";
        array[516][0] = "lp9ytqBQsJQtYgzZF1v41g==";
        array[516][1] = "ؾق،؍ؓؕق،؉ؾـ؍ؓ؉ؐفؕؕ؉ؔ؍،ؾ؉ؔؔ؎؏ؾ؍ؓؑ؍ؐف؏";
        array[517][0] = "/d6CIZWowE5Dhk6S0snUog==";
        array[517][1] = "قؔـف؏؏،ؒ؉قؾ؍ؑ؉ؐؑؔـ؉ؔـؔ،؉ؽ؍ـؒ؎ؕفؓؿ؍ؑؓ";
        By1337̵̸̸̧̡͙̺̠͙͙͉̘̯̱͇͚̭̲̅̀̐ͣͩ̆̏ͧͤ͒̀̎̽͒̾̑͛̊ͩͧ͟͠();
    }

    private void By1337̵̸̸̧̡͙̺̠͙͙͉̘̯̱͇͚̭̲̅̀̐ͣͩ̆̏ͧͤ͒̀̎̽͒̾̑͛̊ͩͧ͟͠() {
        array[518][0] = "+BXjBC+tf2/3wvrhDTQ/Sg==";
        array[518][1] = "ؔ؍فـ،؍ؕ،؉ؕقؐؑ؉ؐؒؾؾ؉ؔؑؑ،؉،ف؎ؔؽ؏ــ؍ؾؾؔ";
        array[519][0] = "iiVIFkfCpFCMenpLw2y/gA==";
        array[519][1] = "ؔقؕؔؑؓؑؾ؉ؓ؏فؑ؉ؐ؎ـؽ؉ؾؿؾق؉ؽـ؎ؐقؑؒؓقؑق؏";
        array[520][0] = "NhCrNlrY6CgJjiLcF6km1Q==";
        array[520][1] = "ق؎،ؓؿ؎؏ؾ؉،ؐؽؓ؉ؐ،؏ؾ؉ؽ؍ؑق؉ؔ؎فؐـؐفؽؕؾفؒ";
        array[521][0] = "ezxRnVrfmlozphWljO7WDA==";
        array[521][1] = "ؽؐفؓ،قف؍؉ؾؑؾف؉ؐؑؓؑ؉ؾ؏ؕف؉ؔ؏ؑؕؐؿقـؓ؎ؿؿ";
        array[522][0] = "hkSxJzA652frRrH7c6rilA==";
        array[522][1] = "ـؔؽ؏ؔ؎فؕ؉؍ؾقؕ؉ؐـ؏ؐ؉ؽ؍ؽق؉ؐ؍ؑؒؒؕؑؒؾؐؾؾ";
        array[523][0] = "PO+ajNdfg3airTbhnqd/cw==";
        array[523][1] = "؏ؔؓـؕ،ؒؔ؉،ـؽؑ؉ؐؽـؽ؉ؔؐؽؐ؉ؽؕؕ؏؏ق؏ؕؐق؏ؐ";
        array[524][0] = "rSmLIUGtTn/gyZM33WSl0Q==";
        array[524][1] = "؍ؓؒؓؕـ،ؽ؉ؔ،ـؔ؉ؐؿؑـ؉ؔؑؕـ؉فقؒق؏ـ؏ـؐ؏؏ؔ";
        array[525][0] = "P5NgoqGSc7c5Vxk0nXolEA==";
        array[525][1] = "؎ؕؾؾؐؔؓ،؉؏قؕؽ؉ؐؓقـ؉ؕقؔؑ؉ؔؓؽؑ،ؔؐ؏،ـؿف";
        array[526][0] = "RoXNeuU2mSVoytre/cIegQ==";
        array[526][1] = "؍،،ؐؔؿ،؍؉؎ؔؽؕ؉ؐ؏فؐ؉ؾ؎ـؑ؉؎ؓؾؐؽؔ؎؎؎ؔؔ؍";
        array[527][0] = "1w5FReGRiDXiAkEfy4SY1g==";
        array[527][1] = "فؑؾـؽ،؍ؓ؉ؑؽؔؕ؉ؐ؏ـف؉ؽؒ؍؎؉ؓؕؔ؏ؓؾ؎ؒؽ؏قؐ";
        array[528][0] = "nNNZLb2N0GgUocWY1OU5tw==";
        array[528][1] = "؏ؾ؎ؕقق؎ؓ؉ؐؾ،ؓ؉ؐقؑف؉ؾؽ،ؿ؉ق؏ؽؓ؏ـ؍ؑ؎ؐؕ؎";
        By1337̨̬͓̦͙̫̳̙̪͎̝͕̹̊̉ͮ͗͑͗̽ͣ̊ͫ͒̃͒ͭͦͪ͑̎̊̆̕͘̕͘͢͜͡();
    }

    private void By1337̨̬͓̦͙̫̳̙̪͎̝͕̹̊̉ͮ͗͑͗̽ͣ̊ͫ͒̃͒ͭͦͪ͑̎̊̆̕͘̕͘͢͜͡() {
        array[529][0] = "M9qBLrOpAM4dFyLnNO4t5Q==";
        array[529][1] = "ف؎فؒؓ،،ؑ؉؎؍ؑ؎؉ؐؕؕؔ؉ؽؿ،ؐ؉ؑققؒؾؒؑؓؔ؍ؑ؏";
        array[530][0] = "UHtOHexKbDTG9pZPVvg39A==";
        array[530][1] = "ؐفؑ؎ؾؔؽؐ؉ـؓ،؏؉ؐ؏؍؍؉ؾؕ؏،؉ؑقفقؑؿففؿق،ؿ";
        array[531][0] = "eqot8UtLVtVd3VMst8ePMw==";
        array[531][1] = "؏؏ؾؑؐ؍ؿق؉ؽؐؓق؉ؐؒـؿ؉ؔؔؑف؉ـؐؽؐؾؿؽفؔؓؾؑ";
        array[532][0] = "3AYF1kYmenJ8/lLH47/0ZQ==";
        array[532][1] = "ؒؽ؏ؓؽؓ؎ؿ؉فؾؾؒ؉ؐؑؾؾ؉ؽفؐؐ؉ؒؓ؏ؿؕؒؒ؍؏ؿؐ؎";
        array[533][0] = "trLt81hNntTM6dROelHqbw==";
        array[533][1] = "ؐؿ؏؍فؓؓؐ؉قؓـؾ؉ؐؒف؏؉ؾفؒ،؉فؑؐؒؾؒفؿقؒؐؽ";
        array[534][0] = "NL6meInHMbRzm/I6j2KEpg==";
        array[534][1] = "ـفؐ؎ؑـؽؾ؉ؐقؕ؍؉ؐ؏ؾف؉ؾؿؿؒ؉؍ؔ؍ؐ؏ؓؓؑـؓؔؽ";
        array[535][0] = "HmlyAKjoU1tzo8Mn6tRLGQ==";
        array[535][1] = "ؿؿؾفف؎فؑ؉ؒ؍؎ؿ؉ؐفؑ؏؉ؾؾؽؓ؉؏قؒ؍ـؾؕؔ؍ؑؔ؍";
        array[536][0] = "8w9zGIPcobi5ygSt2XG/xg==";
        array[536][1] = "؎؎ؓؔؿؿؒـ؉ؒؕؑ؍؉ؐ،ؓؑ؉ؕ،؏؍؉ف؎فؐؑؑ؏؎؎ؾؾؑ";
        array[537][0] = "3/NwjSqsfibQEed0R7UJcQ==";
        array[537][1] = "ف،؎؏ؑؾ؍ف؉؍ؽؐؑ؉ؐ؍؎ؐ؉ؕؽؐؓ؉؍ؔؕؽ،؍ؐؕؒ،؏ؑ";
        array[538][0] = "IqBxEQliqC48ohGoeyMMDw==";
        array[538][1] = "ؔؕؔ؎ؓ؎ؒؿ؉؍ؿقـ؉ؐ؍ـؽ؉ؽؕـؾ؉،؍ؾؔؐؽ؎؏،ؿـؓ";
        array[539][0] = "yHWfI8k2ap4ugD400QACGA==";
        array[539][1] = "ـؔؔ؍ؕؒق؎؉ؿؓ؏ؑ؉ؐؽؓؾ؉ؾؽ؎؎؉ـ؍؏فؿ،ؓؽ؏ق؍ؽ";
        By1337̶̵̧̺̦͔̞̞̣̖̳̖͚̫̓͆ͤ̑ͮ̄́̔̃͊̿̒̽̓̒͐ͫ̀ͦ̓̋ͬ̕̚͡ͅ();
    }

    private void By1337̶̵̧̺̦͔̞̞̣̖̳̖͚̫̓͆ͤ̑ͮ̄́̔̃͊̿̒̽̓̒͐ͫ̀ͦ̓̋ͬ̕̚͡ͅ() {
        array[540][0] = "20V8s5dF4akx0DsgU0AudQ==";
        array[540][1] = "فؓؕؐ؏ؾؿؽ؉ؕؽـؿ؉ؐف؍ؽ؉ؔ؎ف،؉ـؓؑؒؑ؍ؽفؿؔ؎ؕ";
        array[541][0] = "uMHZ4Hk1ib6OxmLc+c9G/A==";
        array[541][1] = "ؽ؍ؓؽؾؒ،ؐ؉،،ؕؓ؉ؐـ؏ؓ؉ؔ؍ؽؽ؉ؕفـؾقؕ؍قؐؑؐ،";
        array[542][0] = "WFnPlbjy4GsRgT9NlOJpZg==";
        array[542][1] = "ؓ،؎؍ؾقؓؕ؉ؕؕ؎ق؉ؐ؏ؾ؍؉ؾؾؿؽ؉ـ؏ـؓ؎؏،ؽقـ،ؕ";
        array[543][0] = "L4GgbqMRaoQ+hZy0O9h+Yw==";
        array[543][1] = "ؔؕؑؒؔؾقف؉ؽؑؑؕ؉ؐ؏ؓؿ؉ؕؾؓ،؉ؕ؍ؓؽؒؾ،ـؓفؓؿ";
        array[544][0] = "onwbyX6NB6j+wnM0bjwj/Q==";
        array[544][1] = "ق،فؐـ؏ؐ،؉ؓق؏ؑ؉ؐ؏ؐ؎؉ؽـؽؑ؉ـؑؿؕؽ؍ؓؑؿؿؿؑ";
        array[545][0] = "0xoBAPnbN+K+mp70XULPzQ==";
        array[545][1] = "؏ؕ،ؔفؐـق؉ؑؾ،ؿ؉ؐؿؿق؉ؕقؕؿ؉قؒؾؒؔؑ؍؎ققؿـ";
        array[546][0] = "E3iP4kGvZtX+6l2h15W6HQ==";
        array[546][1] = "ؑؑؔؕؓف،ؔ؉ؽـؾؐ؉ؐ؍ؿؑ؉ؔؔ؎؏؉ؐؐؽ؎ؕ؍قؔؾففؓ";
        array[547][0] = "KQKrhEmfxJZan5/sGhm0nA==";
        array[547][1] = "؏ؿ؏ؾؑ؍،ؔ؉ؿ؍ؓؿ؉ؐ؏ؒؽ؉ؾ،ؽؿ؉؎؎ؓؿ؏ؒ،ؔ؎ؽؾؕ";
        array[548][0] = "IRseGZF1D7NMGCIGzdWDPw==";
        array[548][1] = "ؓـ؍؍ؽؔ؍ق؉ؾ؏ؽؑ؉ؐؽ،ؐ؉ؕؕ،،؉ؿؐؽؒـ؎ؾؕ؎؍ؓ؎";
        array[549][0] = "XJEc4MPpMMrfJvjn0lXBug==";
        array[549][1] = "قؽؽؿؒؐؑ؍؉ؿؿؒ؏؉ؐؿفؓ؉ؕؓقؐ؉ؐفؔؐ،ــؿ؍ؿؑؔ";
        array[550][0] = "MB9o6DLr1zNF/gj1nT5wzQ==";
        array[550][1] = "ؾؓ؏؎ؔ؍ؔق؉ؓقؽؾ؉ؐـ؎ؒ؉ؽ؍قؒ؉؏ؐـقؑؔق؏؍،ؾؕ";
        By1337̨̧̢̥͕̝̬̱̬͖̲̝͎̣͔̫̪̼̻̻̘̹͉̯̠̌̅̿ͭͮ̆͌̊ͧͮ̕͘͜͜͜();
    }

    private void By1337̨̧̢̥͕̝̬̱̬͖̲̝͎̣͔̫̪̼̻̻̘̹͉̯̠̌̅̿ͭͮ̆͌̊ͧͮ̕͘͜͜͜() {
        array[551][0] = "GN9JhTwnjnBNS4u7Qgbi/Q==";
        array[551][1] = "؍ؔؽ؏ؿ؎ؑ،؉؏ؿؔؒ؉ؐ؎ؿؕ؉ؕؿؿؔ؉؍فقؑؐؿؒؐ؎فؑؔ";
        array[552][0] = "vIysMNkjHwFv7tqW9jDo0A==";
        array[552][1] = "ؒفؽقفؔؔؿ؉ؿؿؕؔ؉ؐؕفف؉ؾق،ق؉ؽ؍،ؽؽؒؐ،ؔؐ؍ف";
        array[553][0] = "bQNN8Ibm6lrq4HQZG+dRmQ==";
        array[553][1] = "ؑ؏؏ؾفففؽ؉ؔؓؓؿ؉ؐؒؽؾ؉ؾفؔؽ؉ؑؾؒؿ؏؎؍ف؍؍ؐؐ";
        array[554][0] = "iN12VFw32b5uVlBeAPGwtA==";
        array[554][1] = "؍ؓؾؾؿؾؒـ؉؏ؕؑ،؉ؐؾ؍ؓ؉ؾقؽ؍؉ؑؿؐ؏،ؐؽؔؔؕؽ؍";
        array[555][0] = "FC4+1MP7p1f2sDKR1e7TBg==";
        array[555][1] = "ؽؔ؎ؕفؕؕؽ؉؍ؿؐـ؉ؐؒـ،؉ؕفؕ؍؉،ؾؿق،ؒؿ؍؍ؽفف";
        array[556][0] = "74f913QBWIABP4tNUH4hqg==";
        array[556][1] = "ؽ،ؕـؒؑؾ؏؉ـؓفؔ؉ؐ؎؍ف؉ؽؽفؾ؉؏؎ؔؕؕؒؔؔقؑؽ؍";
        array[557][0] = "n5xgg6xb5Zga82YPLuTDlA==";
        array[557][1] = "ؓؑؕؔؿؑؐؽ؉ؑ؍،ف؉ؐؑؿؐ؉ؾؓ؎ؐ؉ـؕ؍؏ؔؒؐؿؐ،ؿؓ";
        array[558][0] = "4YeFacPI6POEbWm7WMoWQw==";
        array[558][1] = "ؾقؾ،؍ؐفؔ؉ؽ؍؍ؒ؉ؐؕؾ،؉ؔؽؕؐ؉؍ؿق؎ؕــؽؿ،ؽؓ";
        array[559][0] = "cTBvF73iuMTJWEdCNh/Q0w==";
        array[559][1] = "ؾقؿؓـؿ؍ؿ؉ؽؐؒؿ؉ؐؐؽؿ؉ؾ،ؓؕ؉ؓؐؐؾ؎،ؔؽؿ،ؐؾ";
        array[560][0] = "+0L1/iTdTyGVo3nOwTBERA==";
        array[560][1] = "ـ؏ؽؽؑ،فؿ؉قــ؎؉ؐؐؿ؎؉ؕؽؿـ؉؏ؐؔف،ـؓؽؒؒؕف";
        array[561][0] = "Gn/Vcxet8Q26qZhyGeJnPA==";
        array[561][1] = "ؿؓؿؽؾ؏ق؍؉فقـؿ؉ؐ؏ؔ؍؉ؽ؍ؓـ؉ؿؾؔقؿؽؐؕ؏ؾ؏ؾ";
        By1337̵̵̴̤̹͉̻͚̤̺͎͖̤͖͕͚̳͍̣͓͖ͪͥͫ̂̈́̓̆ͧ́̅̈́ͮ̑̾͒̚͟͡ͅ();
    }

    private void By1337̵̵̴̤̹͉̻͚̤̺͎͖̤͖͕͚̳͍̣͓͖ͪͥͫ̂̈́̓̆ͧ́̅̈́ͮ̑̾͒̚͟͡ͅ() {
        array[562][0] = "KWZIl9B76sTgN13lwGPeNA==";
        array[562][1] = "؍فؒؔ؍،ؿ،؉ؕفقـ؉ؐؽؿؒ؉ؽؕ؏ؑ؉؎ؾـؿ؎ؿ،ؽـؽـ،";
        array[563][0] = "2NTdAWaoQL32h8iwloxzSQ==";
        array[563][1] = "ؿؕ؎ؕؒؕؾق؉ففؾق؉ؐـ،،؉ؾؾؐؒ؉فف؎؍ق؍ؽؽ؎،ؒ؍";
        array[564][0] = "k2BHKQEReLGOww8DRX9yEw==";
        array[564][1] = "؍ؿق،ـؕؕؐ؉ؐقؓؾ؉ؐؿؐ؎؉ؕفؿؿ؉ؒـ؎ؒـققؿؽؐـؿ";
        array[565][0] = "fUO0RWiDJXI1gJjqUNq7vg==";
        array[565][1] = "ؿفف؍ؔؒـق؉ؐؐفؔ؉ؐفـؿ؉ؽؓؑؿ؉؏؎ؿؾؐ؎؏ؔ؍ؑ؏ؕ";
        array[566][0] = "c/qMS5pHHQZQ1EDbG+Yyag==";
        array[566][1] = "ؐ؏ؓ؍ؐ؏ـف؉ق؍،ؐ؉ؐفؒـ؉ؕؕؕؔ؉ؾؓ،ؑؔؔؕ؍ف؎ؐ،";
        array[567][0] = "07AHytwqg8l6f9lFTKi0zw==";
        array[567][1] = "؍،؍قف؏ؐـ؉قؕ؏ؽ؉ؐؒؑؑ؉ؔقؒؑ؉ؕؾؒؾؒقـؔؔفؑؐ";
        array[568][0] = "yL67B24zOnOU7dazsBvn7Q==";
        array[568][1] = "؍ق؎؎ـؒؾؓ؉ف؏،ؒ؉ؐؿؔ،؉ؾ؏،ف؉ـؾـؐ؍ـؓ؏؎ؿؿؔ";
        array[569][0] = "KGg8MaU7f7TIspIaSAgxWA==";
        array[569][1] = "؍ؿؐقؔؾف؏؉ؓؑقـ؉ؐؽؐق؉ؽؽؒ،؉ؐؔفؽؒ؏فؾ،ؔـ؎";
        array[570][0] = "4ukk2zfJA8j4ICwQjXJeHw==";
        array[570][1] = "؍ؽـؕؒؔؔؑ؉ؑؑ؍؍؉ؐؒؾؕ؉ؾــؐ؉ؐؿؔ؍ؑ،ؔ،ؓؒؐق";
        array[571][0] = "PKfKQwopjd+QSfXhqEcF7g==";
        array[571][1] = "ؐؿ؎؍؎؏ؾ؎؉ؐ؏ؑق؉ؐؿؽؾ؉ؾ؏ؒؓ؉؍ؒقـؑ،ؑؕ؎ـؓؔ";
        array[572][0] = "+DMWTqShA8hewXh+eaARPA==";
        array[572][1] = "ؓؒؓؒ،ق؎ؓ؉ؐـقف؉ؐفؔؐ؉ؕؾؓؔ؉ؓـؑؿ،ؾ؏ؓفؿؿؐ";
        By1337̴̧̡̠͚̳̹͈͔͉͎͖͚̩̭̠̯̋̽̑ͭ͂̍̃ͭ̍̇͋ͥ͛ͧ̎̎̂̾̓ͭ͞͡͡();
    }

    private void By1337̴̧̡̠͚̳̹͈͔͉͎͖͚̩̭̠̯̋̽̑ͭ͂̍̃ͭ̍̇͋ͥ͛ͧ̎̎̂̾̓ͭ͞͡͡() {
        array[573][0] = "5AyuI14YnYFyVJnJ91KtBw==";
        array[573][1] = "ؓـؒؕ؏ؿ؏ؔ؉ؿؔقؒ؉ؐقفق؉ؔؑؽؕ؉ؕــ؏؏؎قؔ؏؍ؒؐ";
        array[574][0] = "qRzMvx8WGI8WI0vCgASOnw==";
        array[574][1] = "؏ؒ؏ؑ،ؿؕؿ؉؍ؾ؍ؔ؉ؐفـؒ؉ؾـؕـ؉ؕؐؽ؎ؿؓؕقؒؒ؍ؔ";
        array[575][0] = "jZK3Z+SpA3ReXGoNdbMsDA==";
        array[575][1] = "؍،؏،،ف،،؉ـؿؐ؍؉ؐؓؒؿ؉ؾؔؓق؉ـ؍ؐـ،ؕ؎ؔ؎ؾؽؓ";
        array[576][0] = "hdvtCyc4Uge5iJ8LZRxqeA==";
        array[576][1] = "ف؎ؒ؎ؾ؍ؕؒ؉ؾقؓ؎؉ؐؿؽف؉ؕ،ؾ؎؉قؒؿؔؑؒ؍ؔؽ؏ؑؿ";
        array[577][0] = "j1aOcpaTs9fgMlF+L1jryw==";
        array[577][1] = "ؑؿ؍ؒـ،ف؍؉ؿ؍ؿؐ؉ؐؕؓق؉ؕؓؕؐ؉ؕؐـؕفؒؑ؎ـؐؑؒ";
        array[578][0] = "PaertvN6lxC6HBJiS5Bd8g==";
        array[578][1] = "قؑؔـؽـؔـ؉ؔ؎ق،؉ؐؿؾؽ؉ؔؒ؍؍؉ؽؓؐؒؾؿقؔف؍ؽؓ";
        array[579][0] = "Y1crD6hOtE8qKYBj1T1XMw==";
        array[579][1] = "ـ؎ؿقؔؑـؐ؉ؕؕـؔ؉ؐ؏ؾ؍؉ؕقؐـ؉ؾ؍ؔ؏ؑؽؓ؍ؔؾؾؓ";
        array[580][0] = "CV7s5YqyBAQIEassFTkpvA==";
        array[580][1] = "ؐ؏ؑؐفقؓؐ؉ؐ؏؍؎؉ؐـؿؒ؉ؕقـؐ؉ؽؽؑ؍؏قؒـ،ؾفؑ";
        array[581][0] = "QT8GE2XMxuSEAp318WxzrA==";
        array[581][1] = "ؐؾؽؒؽؔؓف؉ف؍،ؽ؉ؐؓ؍؎؉ؽفـ؍؉ؾ،ؓؒؓفؾؕؒؔؾؓ";
        array[582][0] = "n6BPs52YrZ/2xWRZeJt7Zg==";
        array[582][1] = "،؍،فؽؽ،؍؉ؑ؏ق؏؉ؐ؍ؿؐ؉ؕؐقؽ؉؍ؒؽؒ؍؍ؐؾ؏ؑؑؑ";
        array[583][0] = "+b05uh5g+hYn30Fzd04AZQ==";
        array[583][1] = "ؕؕف؍ؑق؍،؉ؒـ؏ف؉ؐ؍ؑؿ؉ؾـؑؽ؉ؔؐؽؽؕ،ـؿؑقؐؾ";
        By1337̷̶̨̨̘̮̥̹̯͓̞̀̉̍̈̎ͭ͑͌ͪ͐̆ͯ̄̑̅͟͜͠͏̱͔͈̳̙̆̄́̉͠();
    }

    private void By1337̷̶̨̨̘̮̥̹̯͓̞̀̉̍̈̎ͭ͑͌ͪ͐̆ͯ̄̑̅͟͜͠͏̱͔͈̳̙̆̄́̉͠() {
        array[584][0] = "9VYFjkEOY5iqzBaWVRRPqw==";
        array[584][1] = "قؓ؍ؓؕقؓ،؉ؿفؽؿ؉ؐؕـؒ؉ؔـؾق؉؍،ؕؓؐ،ؾ؎ــؾ؍";
        array[585][0] = "G6xThsyefwlo9/8+R5VPQw==";
        array[585][1] = "ؿؓؐؿؕ؎؍ؔ؉ؒ؏ؽق؉ؐ؍ؐؽ؉ؔؐ؏؏؉ؑـقؿؿؿفؕ؎ؾؕف";
        array[586][0] = "oi8ekwO5qC2B5m+sxDf2zw==";
        array[586][1] = "ؑؔؑ،؍ؓفؑ؉فؾ؏ؔ؉ؐؽؾ؏؉ؕ؎قؽ؉؎؎ؒؕ؎؏فؐؽؽفؒ";
        array[587][0] = "klVM9KEMAPsOnhPr9Uy1Yw==";
        array[587][1] = "ؾف؏ؐف؍ؽ؏؉فـ؎ؾ؉ؐ؏؏ق؉ؕففؔ؉؎،؍ؕؑؕفؐقؽـ؎";
        array[588][0] = "fa1JoRJlTYtE/aTk9G9lxg==";
        array[588][1] = "ؐؐفؿ؏؍ؒؿ؉ؐؿؔ؏؉ؐ؏ؿؑ؉ؾؕؒـ؉؏ؽفؔؐؐؾؾققؒف";
        array[589][0] = "lK+Tw9an9zw4AS07FoFyJA==";
        array[589][1] = "ؓؒؕؔ؏ؽفؐ؉ؔ؎ؓف؉ؐ؏،ؒ؉ؔؾ؏،؉ؔؕؒ،؍ـــؒفؑ؍";
        array[590][0] = "zp1mzCtpkkFshTZIX20acQ==";
        array[590][1] = "ؿقؕؽفـؾؕ؉ؕؕ،ؿ؉ؐفؔ،؉ؾؐؐق؉ؓؕؓـؑـؕفؽؾؾؒ";
        array[591][0] = "0hLAYDBFVWfrkKOvZWgqoQ==";
        array[591][1] = "؏ؐؔف؍؍ؔ؏؉ؓؓف،؉ؐفـق؉ؕؒؓـ؉ؒــ؎ؐ؎ؕ؍ؑ؏ؕؒ";
        array[592][0] = "CEuLnlBENDTIX1fENd/KZg==";
        array[592][1] = "؏ـؕفؓؾؔؽ؉ؕ،ـ،؉ؐ؎ؕق؉ؾؿؓؐ؉ف،ؓ،ؿؑقؒؒؓؐؐ";
        array[593][0] = "cU5x6Q9QyKRqlZdB/wis4g==";
        array[593][1] = "ؔؓ؏،فؿـؓ؉ؒـؕ؏؉ؐؓفؽ؉ؾ؍ؽؑ؉فؓؓ؏ؒ؏؎ؓؓقؓف";
        array[594][0] = "GKNumOj4r91cG/4DPG8Y0A==";
        array[594][1] = "ؒؓ؎فؒؔؑ؍؉ـؔ؍ف؉ؐؔؿق؉ؽؑؕف؉؎ؽؓ؎؎ؕؐؐؕؔ،ؿ";
        By1337̶̸̢̘͇̬̜͚̰̼͚͉̙̘̳̲̱̫̣̲̿̉̔ͬ̃̄̄̒̀̂̏ͭ͑̈́̔̄̚͢͟͞();
    }

    private void By1337̶̸̢̘͇̬̜͚̰̼͚͉̙̘̳̲̱̫̣̲̿̉̔ͬ̃̄̄̒̀̂̏ͭ͑̈́̔̄̚͢͟͞() {
        array[595][0] = "vOL5Pst7ZIbeIcoMpEOumA==";
        array[595][1] = "ؑؒؾ؏ؓؑؿ،؉ؾؽؾؽ؉ؐ؏ؓؓ؉ؾؒؑؒ؉؎ؐ؏ؓـقؐق،ؾؾ؏";
        array[596][0] = "wwx+qGrqW746P68JT3Hu/w==";
        array[596][1] = "ؽ؍ؕ،،ؐ؍ؿ؉ؔـ،؏؉ؐؿقؒ؉ؽفؿؔ؉ؕؔؔفؔؒ؏ؑؐفؑ؏";
        array[597][0] = "+MHnXT/awfvML/Y7V526lQ==";
        array[597][1] = "ؒؑؽ؍؏،؍ـ؉ؾ؍ؑؿ؉ؐؾؑؿ؉ؕ؏ؑؽ؉فؑ؎ؔفؑؽ،ــؓؔ";
        array[598][0] = "Q/wOSUuvA9r3K7h3AEL90g==";
        array[598][1] = "ؽؒؽق؎ؕفف؉ؐؓ؍؎؉ؐؔؕـ؉ؔ؎؏ـ؉ؿؕقؔـفؿقف؍ـ،";
        array[599][0] = "0O/Dkjt2vC6KLv1CbjW+Vw==";
        array[599][1] = "ؐـؑففؿؓؽ؉ؓؾؾؿ؉ؐقؽؾ؉ؕؾ؎ؑ؉؎ؔؐ،ؔؓقؕقؐؔؑ";
        array[600][0] = "R+M5HfO8PacveGxFIKZAlw==";
        array[600][1] = "؎ؓؿفؒ؍ؔق؉ؐ؍ــ؉ؐـؽؕ؉ؕ؎ؽؔ؉ؽف؏ـؔؐؒـؾؕفؒ";
        array[601][0] = "HnnC4TT88hctjrNFbAiPdg==";
        array[601][1] = "،ف؏ؔ؍،؏ف؉ؓؓقق؉ؐؽ؍،؉ؔ؍فؑ؉قؐؑ؍؍ؔ؏ؑ؏ؔفؐ";
        array[602][0] = "Dt8ahkI7I84yqtYqpGtiqQ==";
        array[602][1] = "ؽؔؽؿقؿؐـ؉؏ؓؓؾ؉ؐؑؾ؏؉ؕقؒؾ؉ؔؐـؕ؏ؑ؏؍ؽؒ،؍";
        array[603][0] = "J3FucIDIrxXiBteNArI8cQ==";
        array[603][1] = "ؑ؏؏،ؿؑ؏ؿ؉،؍؍ـ؉ؐ؍ؐؽ؉ؽؕؿؕ؉ؓـ،ؐ؏؍ؐ،ؕقؒؕ";
        array[604][0] = "wKbMhzK/cy4FhqZW/ID6FQ==";
        array[604][1] = "ؔؐؑؕف،فؕ؉؏؍قق؉ؐؾؾؽ؉ؾ؎ـ؍؉ؾ؎،ؽؽ؏ؐؑؽؾؑؒ";
        array[605][0] = "SNUFLhOfDr5kke24codASQ==";
        array[605][1] = "ؽؕؕؿ،ؔؔؑ؉،ـؑف؉ؐقؑؾ؉ؔؕ؏ف؉ؒؔ؎ؕـؐقق؏ؾؽؿ";
        By1337̶̢͍͉̱ͤͬ̇̕ͅ͏̷̛͔̜̞̤̯͍̠̲̼̼̰̳̻̹̪̹͚̐̂ͦͩ̑̃̂̐̚͜();
    }

    private void By1337̶̢͍͉̱ͤͬ̇̕ͅ͏̷̛͔̜̞̤̯͍̠̲̼̼̰̳̻̹̪̹͚̐̂ͦͩ̑̃̂̐̚͜() {
        array[606][0] = "fIOZQ+z8pEZNkH5YvkFQDA==";
        array[606][1] = "قؒـ؍ؓقؕ؏؉؍ؒؓ؏؉ؐق؎ق؉ؾؕ؏ؑ؉،،،ؾـف؏قـف؏ؓ";
        array[607][0] = "7uL3Akz838+cWf+yU7QMuQ==";
        array[607][1] = "فؿؐؑ؎؏؏،؉ؐ،ق،؉ؐ؏ؽف؉ؕؔؔؕ؉ؾـؽؾف؏ؐـ؏ؽؐؑ";
        array[608][0] = "hRkw8eE08nsAGiaA9dWCIQ==";
        array[608][1] = "ؔؔ،ؓ،ؾؽؑ؉ؾـؓؾ؉ؐؐقؒ؉ؾؕ،ؑ؉ؔ،ؿ؎ؿ؍،ؔؑفـؕ";
        array[609][0] = "28D9j4Ql4Fftt6RYJrcEKw==";
        array[609][1] = "ؔؓؿؽ؍ؒقؿ؉ؒـؔ؍؉ؐ؎؍ؐ؉ؕف،ؽ؉ؕققؿؕفؽؕؐؔؓ؍";
        array[610][0] = "GI6XUM3ySheroBX2dGJZ7w==";
        array[610][1] = "ؒقؽؒؕ،ؽؐ؉ؒ؏ؑ،؉ؐؔؒؽ؉ؾؿ؍ؿ؉قؾقؐؕـ؏ؐؒؾؒ،";
        array[611][0] = "x+9+sCPdIsKxH1bg7Vg0nQ==";
        array[611][1] = "ؾؓؾؒ؍،ـؐ؉ؔؒؑؐ؉ؐف؍؍؉ؾؑؾؐ؉؍ؓ؍ؒؕ،ؿؿؿؔؕف";
        array[612][0] = "ZpTnNXnGuFQccpSpifuAWg==";
        array[612][1] = "ق،ؽؾؿؿؐ؎؉ؐق؍ؐ؉ؐؐ؏ؾ؉ؾ؍؏ؿ؉ؾؕ،؍ؽ؏ـقؿ؍ؕؽ";
        array[613][0] = "eBzqc83w1nR9wqpYv3VTUQ==";
        array[613][1] = "ؾؽؕ؎ــقـ؉،ؽ؎؍؉ؐ؎ؓ؍؉ؾؑ؎ؑ؉؍ؓفقؒؿؓؾؑؿ؍ؐ";
        array[614][0] = "QB0bfEhQSYPCo8DNT5BdWA==";
        array[614][1] = "ؐ؏؎ؓؽـــ؉ؿـؔؿ؉ؐقؐؑ؉ؔقؓـ؉ؿ؏ؕؿؓؿؾ؏ق؏ـؐ";
        array[615][0] = "PmO9xisQSqbXyeMoPyawGA==";
        array[615][1] = "ؐ؏ؓؕـ،،ؐ؉فؑؑ؍؉ؐؓؔ؎؉ؽؔؑ؎؉ؕؽ؍ؐ؏فؓؒ؍ؔؑف";
        array[616][0] = "99YVOtVbph4weoMESuOimQ==";
        array[616][1] = "ؑـؓقؔفؐؾ؉ؾفؾؑ؉ؐــ؏؉ؾؓ،ـ؉ؿؑؐ؏ؒؑؒقؑ،فؿ";
        By1337̛̯͍͔̤̺̪̰͚̯̹̦ͤ̇͒̈̈̉̐͠͡ͅͅ͏̢̠̼̣͙̏ͯ̂͑̐͊͆̓͘͠͝();
    }

    private void By1337̛̯͍͔̤̺̪̰͚̯̹̦ͤ̇͒̈̈̉̐͠͡ͅͅ͏̢̠̼̣͙̏ͯ̂͑̐͊͆̓͘͠͝() {
        array[617][0] = "7CoWv0k87PXMHYTD5EJeng==";
        array[617][1] = "ؑؑؿفؔؑ،ؔ؉قفؓؽ؉ؐ؎ـ،؉ؔؽؓ؍؉ـؐؑؔؾ؏ق،،ؾ،ـ";
        array[618][0] = "lqTOIyIyC5g++1JpUISDaA==";
        array[618][1] = "ؓؑ؍؍؎؎ؓؓ؉؍ؾ؏ف؉ؐ؍فؕ؉ؕؓ؎ؕ؉؏ؔؾ؍ؒؿـؐ؎ؔـؔ";
        array[619][0] = "C3yMmk7N1/vkwJMogwj4dA==";
        array[619][1] = "ؾؕؿق؎ؑؾ،؉ـ،ؔؑ؉ؐؔؕ،؉ؽؔؾؓ؉ؔؑؔؐفؕؕ؍ؾـ؍ق";
        array[620][0] = "N9p54XDgCkhb4d64SvJI3A==";
        array[620][1] = "ؽؑؐف؎ؓ؏ف؉ـؓ؏ف؉ؐ؏ؓف؉ؔ؎؍ف؉ؿؐقؽـؓؾ؏قؓؕؐ";
        array[621][0] = "1P1mPLuXx8vTbYYXf8/VbQ==";
        array[621][1] = "فؒؔؑؔ؏ؑ؎؉ـؒؽ؏؉ؐ،ؿؐ؉ؔؾؿ؏؉فؔؔ؎؏ـ؏؍؎؎ؒف";
        array[622][0] = "PNc0UJ3l9/5BuZ71Syvd4A==";
        array[622][1] = "ؒؓؓؔؿؐؔؿ؉فؽ؎ؑ؉ؐقفؿ؉ؕ؏ؒف؉فؾ؏ؾ،ؒفؒؕؕ؏ؕ";
        array[623][0] = "pcMatkUnUQUVcHiYf3N16Q==";
        array[623][1] = "ؑؒقؓ،ؕؽؔ؉ـف،؍؉ؐـ؍ؒ؉ؔؒؿـ؉قؾؐؐـؿؾؓقؒؾؽ";
        array[624][0] = "2bYBY3hVe9z4UWRHbNTzSA==";
        array[624][1] = "ؑؾـ؍؏فؐؒ؉ؽ؏ؿـ؉ؐؾؓؑ؉ؽؐ؎ؿ؉ؽؑؽؓؾؒـ؎ؽـؕؓ";
        array[625][0] = "QDs+UEAHuHUDuK2exSWXRA==";
        array[625][1] = "ؑ،ـؐؕ؎ف؎؉ؔ؎ف؍؉ؐؿؐ،؉ؕققـ؉ؒؒؕؑؑؕؓ،ـؑؽؔ";
        array[626][0] = "pdi36wO45Cqa3SOJxmHuIg==";
        array[626][1] = "ؐ؏ؕؾ؎ؓؔ؏؉فؾ،ؐ؉ؐـقؓ؉ؾؑ؏ق؉؎ؽ؏ؿؿؐؔؽؓؒؒ،";
        array[627][0] = "FIlhs+T59YEFcsqNw8laxw==";
        array[627][1] = "ؕ؍ؐؐ؎ؑؽؓ؉؍ؕ؏ؑ؉ؐؾ؏ؓ؉ؽؾؓؑ؉ف؍ؔؽـؓؕؿ؏ـؐؿ";
        By1337̵̷͎͇̅̓͋̏̃ͤ̅̚͘͏̶̴̢̡̟̺̝͇͔̪͌ͮͫ̐̂̈́̀̆ͧ́ͦͪͧ̑͢ͅ();
    }

    private void By1337̵̷͎͇̅̓͋̏̃ͤ̅̚͘͏̶̴̢̡̟̺̝͇͔̪͌ͮͫ̐̂̈́̀̆ͧ́ͦͪͧ̑͢ͅ() {
        array[628][0] = "hIOzlG/meZwGjy4na6EcHQ==";
        array[628][1] = "ؓؕفؽ؏ؔ،ؾ؉فؿ،ؔ؉ؐـ؎ؾ؉ؾـ،ؒ؉ؿ؎فقؑؕـؾؾ؎ؒؕ";
        array[629][0] = "3oo+wCP5r8S1GE3VTGGmUg==";
        array[629][1] = "،ؐؓق،؍،ـ؉ؓؓ؏ـ؉ؐـ؎؎؉ؕـؕـ؉ؑؿؐؒفؒؐؿـؐؕؾ";
        array[630][0] = "3PPRYDzolL2Bs+pTkAV/9Q==";
        array[630][1] = "؎ؑ؎؍ؓؑق؏؉ؔؓؽ،؉ؐ؏ؿؑ؉ؾقؒؓ؉،ؐؾؿ؏؍ؾؕـ؍ؓؓ";
        array[631][0] = "b2veLVjehibdIQ6sobOAAQ==";
        array[631][1] = "ؓؕ،؍ؑؒ؏؎؉ؑ؏؎؍؉ؐؾ؎ؑ؉ؾ،قؔ؉ـؐؓ،ؒ؎ؿـفؾ؍،";
        array[632][0] = "x4Qwoxc4mdp+vJg3n6UHMA==";
        array[632][1] = "ؔؽ،؎ف،فف؉ؒؐؔ،؉ؐف؏ؔ؉ؾ؏ؽؐ؉؍ؽفـؒؿؾـؕؒؿؓ";
        array[633][0] = "vbv0+UpPNcErh8j+zixEYw==";
        array[633][1] = "فؕؾ؎؍ؕقؐ؉ؽف؏؍؉ؐؕؒ؍؉ؔؑؔؒ؉ؾؾؿققؾؾ؏ؕؽؐؕ";
        array[634][0] = "b/5zW/yGE2nYYD9KXARthQ==";
        array[634][1] = "ؒؿ؍ف؍،ؽؕ؉ؾؒـ،؉ؐ،؍ؑ؉ؕف؏،؉ؿفـؔفؔؓ؎؏ؑفؽ";
        array[635][0] = "1kPtc/jqSd9c3JEk861VMQ==";
        array[635][1] = "؎؎؏؍قـؒ،؉فـؒؿ؉ؐفؽ؏؉ؕـقؔ؉ـؽؕؿؿ؍ؽ،فؔؔق";
        array[636][0] = "UVZ4hhLe1WnrfVvlKK/BjQ==";
        array[636][1] = "فؑق؎ؽفؐف؉ؔؓـؓ؉ؐؾ؎؍؉ؕ؎ؑؐ؉ق؍،ؒ؍؎؏ؿؐ؏ؕؒ";
        array[637][0] = "wd8CFx5lyFDYKZGIkxr84g==";
        array[637][1] = "ؒؾ؏ؔؕؐ؏ؓ؉ؐ؍ؽ،؉ؐؒؑ،؉ؽؒؑؿ؉ؔؿ؏ؿفؿ؎قؔـ؏ؕ";
        array[638][0] = "M0wnYiPTQcGWckVFwLMvsQ==";
        array[638][1] = "؎ـ؍،؏ـقؑ؉ؿف؍ق؉ؐقؒـ؉ؽؒؔ،؉؎ؿؑؔؑقؐؒؑقؑـ";
        By1337̶̧̮̩͇̟̳̘͙͚͍̦̘͈̗̜͛̓̀̎̔̂̈́ͩ̑͆ͥ̅̐͒ͩͩ̈̅̀̕͘͘͢͜();
    }

    private void By1337̶̧̮̩͇̟̳̘͙͚͍̦̘͈̗̜͛̓̀̎̔̂̈́ͩ̑͆ͥ̅̐͒ͩͩ̈̅̀̕͘͘͢͜() {
        array[639][0] = "1bQMKmDm56VDEfUrZkyk/g==";
        array[639][1] = "؍،ؐ؍؎ؿؓؕ؉؍ؾؕف؉ؐ؏،ؽ؉ؽؾؿؓ؉ؽ،ـؐف،ف؍ؒـؽ؏";
        array[640][0] = "BQhktZHI3MDaiB29JIPV3Q==";
        array[640][1] = "قؒـؑفؓؕ؏؉ؐؕؓق؉ؐؿ،،؉ؔقؓ،؉ؽؾـؐ؏؎ؓ؏ؐؑؐؐ";
        array[641][0] = "wgw2MjE5ThYnkD9z4oPb2g==";
        array[641][1] = "ؕ؎،ؕـؾؒؒ؉؎ؕؕؐ؉ؐؒؒـ؉ؔ؎ؒؿ؉ؑ،؏؍؏،قق؍ؿؾؔ";
        array[642][0] = "Eb8/Nws675m+NTBam48Rig==";
        array[642][1] = "فؒففقؔـف؉ـؿـؾ؉ؐؐ؎ؓ؉ؕؔفؕ؉؍؍ؕ؍،ؐ،؏؍؏ؽؑ";
        array[643][0] = "Sg9OsFMjuEd4utNgP3h2bw==";
        array[643][1] = "ؕ،ؐؓؔؿ؏؍؉ؐؾؐق؉ؐـ؏ؓ؉ؔؑـق؉ق،ؕؒؒقؽ؍ؑؔؕـ";
        array[644][0] = "+pk8I2SEUN7pIGcJkKtcPA==";
        array[644][1] = "ؑ؍ؕؐ؍ؽ؍ؾ؉فؑ؏ؿ؉ؐؕؕؔ؉ؽؿ؍؏؉ؽؿ،ؽ؏ق؎ؿؽ؏؎ـ";
        array[645][0] = "cfl1OjlIcyfz9Or9dOyUWw==";
        array[645][1] = "فؾ؏ـ؎ؑ؏؎؉؎قؔق؉ؐ؍ـؿ؉ؽؐـؾ؉؍ؕؐؾؽؑؓؑؿـؽ؍";
        array[646][0] = "2ia42d0ge7dtLIQ0hpBl0g==";
        array[646][1] = "ؾؿ؏ف؍؏؍ؔ؉ؿؑؒ؍؉ؐؑـ،؉ؾؑؔؑ؉؍،ـؕؾ؏ؔفقؾؿـ";
        array[647][0] = "Le6cEFL1c1dSLWyKhGHP/g==";
        array[647][1] = "ؓؽـؿؒؽؑؕ؉؏ؿؑ؎؉ؐؔ؎ؿ؉ؔؐـؓ؉ؒؐؐؿؐؾؑؑ،؍فـ";
        array[648][0] = "GFyir70gsnVAdYuYhvBpPw==";
        array[648][1] = "قؽ،ؾ،؍ؑؑ؉ؾؔؽؑ؉ؐ؏ؓؾ؉ؽؕؾؕ؉؏قؑؑ؍ؽ؎ؓؾؔؾ؎";
        array[649][0] = "t1Qtka633vJQz8CUk9vC+A==";
        array[649][1] = "ؽؓفؑؿؽؿؒ؉ؽ؎ؽؕ؉ؐؐ،؎؉ؔقفؾ؉قؔ،ـقؕؾ؎ؾ؍ؓؕ";
        By1337̷̴͂ͨͣͮ̆̽ͫ͏̸̨̡̨̛̪̗̮͍͙̜̤̜͓̰͗̀͆̓̑ͤͣ́ͫ͘͝͠ͅ͏͑();
    }

    private void By1337̷̴͂ͨͣͮ̆̽ͫ͏̸̨̡̨̛̪̗̮͍͙̜̤̜͓̰͗̀͆̓̑ͤͣ́ͫ͘͝͠ͅ͏͑() {
        array[650][0] = "8V1y/dJsRfVdPBGU0YSphQ==";
        array[650][1] = "ؕــؒؐفؐؔ؉ؑؒؒؓ؉ؐ؏قؑ؉ؽؐؕق؉ؓ؍ؐ؏ؒؒ؎ؓؑؕؓؽ";
        array[651][0] = "6+gYXq8/mEwFtGDk/Kfr/A==";
        array[651][1] = "ؒــؐؐؓفؔ؉ؿؿ؎ـ؉ؐ؎؏ف؉ؕؔؓؾ؉؏ؿؑؽؓ؎ؐقؒـفؽ";
        array[652][0] = "J1AXZG0N534FM3P4ytFy6A==";
        array[652][1] = "قؾؓؓ،؎ؔؑ؉؍ؿؽ؎؉ؐـفؓ؉ؔفؑؓ؉ؑؾؐؔـؑؽؕقؒؿؐ";
        array[653][0] = "HUaXglLfTXiWGi/wnmTRKw==";
        array[653][1] = "؍؎؍ؓؐ؍ؑ؎؉ؐؽ؍ؔ؉ؐؿؓـ؉ؔ؏؏ؑ؉ؽ؍ـؐ؍ؿفؑقؑؐؒ";
        array[654][0] = "8O4L1iiLWayv5PIeNyaH3Q==";
        array[654][1] = "فؑ،ـ؎،،ؓ؉،؏،ؐ؉ؐؾؔ؍؉ؕؑؕؽ؉ؐفؕ؍ؽؔ،؎ـؿ؏ؾ";
        array[655][0] = "rTtm7G/F3uj0aJ6LXTKm+w==";
        array[655][1] = "؎ؕؔؔفـؒؾ؉فؾؒـ؉ؐ؎ف؏؉ؕف،ـ؉ؾؕ؎ؿ،قــؐؿق؍";
        array[656][0] = "0HMK7iqdXyUDegmhrmhM8w==";
        array[656][1] = "ؿ؏؍ؔقؐؑ؍؉قؑؾ،؉ؐ؍ؒق؉ؕ؏ؒؕ؉ؔؽ؏ؐؾؔـؿؽؓؓـ";
        array[657][0] = "Gy1ogKPAVfApOTgZ/pJInw==";
        array[657][1] = "ؐ؍؎قؑقؒق؉؎ؿ؎ؑ؉ؐـ؍ف؉ؾؾف؏؉،ؽؑؓؽؑؔؾقؑقـ";
        array[658][0] = "9S4xefRNsXGOI0rm9OnSHw==";
        array[658][1] = "ؒؑؽ؏ؾؐ؏ؓ؉قؿؕؒ؉ؐؽق؎؉ؕؿ؍ؐ؉ؿف؏؎،قق؍،ؿفق";
        array[659][0] = "asxtltiQpYkyZUQM8+JiGw==";
        array[659][1] = "ؿؓؑؔؿؽ؍؎؉؍؍،؏؉ؐؾؿؕ؉ؕقؑ،؉؏؏ؒؒفؾ؏؍ؐؒفق";
        array[660][0] = "oEgQ+lH/xSV+elNH8rFETg==";
        array[660][1] = "ؽؓ؎ؽفؐؔؐ؉ؒـ؏ؓ؉ؐؑؓؓ؉ؔؾ؎؏؉ـ؏ؾؓؑقؑ؎ؾؒؕـ";
        By1337̴͈̲̮̰̼͈̺̺̬̣͚̭̩͎͍͔̘ͣ̈̓͊̅͌̀ͣ̉̽ͯ̊̌̋ͤ͋ͫ̚͘͜͞͡();
    }

    private void By1337̴͈̲̮̰̼͈̺̺̬̣͚̭̩͎͍͔̘ͣ̈̓͊̅͌̀ͣ̉̽ͯ̊̌̋ͤ͋ͫ̚͘͜͞͡() {
        array[661][0] = "zkCt3DdhdfBswTGPkNwbCA==";
        array[661][1] = "؍ؔؔفـؓقؾ؉؍ؕؐـ؉ؐؒؾؓ؉ؔفؽف؉ـ؍ؑؔؐ؎ؿؓ؏ـفؾ";
        array[662][0] = "8oQx/1YGF4AelEGzsnYRVQ==";
        array[662][1] = "؎ؕ؍ؽف،ؒؿ؉ؕــؾ؉ؐففؐ؉ؕ؎ؕ؏؉ؕ؍ؒ؏ؽ؎ؐ،ؾؒـ؍";
        array[663][0] = "rPO03SEvz5YBk0BFolOZ6Q==";
        array[663][1] = "فـؕف،ـؐف؉ؾؕ،ؿ؉ؐ؎،؎؉ؾقؑؓ؉ؾؕؔفقؑقؿؐ؎؎،";
        array[664][0] = "c1EbnoosJ1vSJOJ+/030ZA==";
        array[664][1] = "ق؎ؑؿؕـؾؾ؉قـ؎ؽ؉ؐ؎ؑؒ؉ؽ؎ؾ؎؉ؒؿؽ؏ؕؔـؾؽقؿؐ";
        array[665][0] = "d/H+5T0xq3pD3AvOFsZM7Q==";
        array[665][1] = "ؓؒؐ؎ـؒؿؓ؉ق؏فؒ؉ؐفؽؓ؉ؔؔؿق؉ؕؾ؍قف؍ؾؓؽ،؏ؾ";
        array[666][0] = "A8atRV6A9zn4CbgTGJ9njg==";
        array[666][1] = "ـفؕ؍ؐؓؾـ؉قـ؎ؿ؉ؐؔؾؽ؉ؾؔؐؾ؉ؒ؏ـ،؍ؑ؎ؒـؑؾ؍";
        array[667][0] = "csHMS2X6W64jWbpqrNhPQQ==";
        array[667][1] = "؎ؿؐؐؓ؏؍،؉؍؎ؽؕ؉ؐؓؾؑ؉ؕؑؑؽ؉؎ؕ؍ؽؽؓـقؿؕقؾ";
        array[668][0] = "kk/9SKqBOE+ktOwxE8zP0Q==";
        array[668][1] = "ؒ؏ؒؽ،فؽ؍؉ؐؽؒؓ؉ؐـ؍ؒ؉ؽ،ؓؕ؉،ـؓؒ؍ؑ،ؕؽؔؓؕ";
        array[669][0] = "bMgG3ne9nfN11JBIO4Nd8A==";
        array[669][1] = "فؓؕ؍ؕ؎ؐؾ؉ؾؒؐق؉ؐؽـ؏؉ؔؔ؎ؕ؉ـؾؑفؽقـ؏فؒؕـ";
        array[670][0] = "iDwCAZt5JihRh/XxTnFrNA==";
        array[670][1] = "ؾؓ؏ؿؓـفؓ؉ؾؿ،؎؉ؐ؎ؐؒ؉ؾؑ؏؍؉ؓ،ؕؓؽؿق،؎ؕقؑ";
        array[671][0] = "2z/S93qsWHHAYcAAp3aOlQ==";
        array[671][1] = "؍؏قؓ؎ؐؑؕ؉ـفؕ؎؉ؐؕؿق؉ؕؓؐؾ؉ؒؔؕؒؔـؽ،قؑؽؽ";
        By1337̗̳̰̱͙̜̳͋͑͆ͯ͂̍͏̵̱̭̪̙̲͇̲̮͕͎͇̬̰ͤ̅͋̐͋̾͘͜͢͢͠ͅ();
    }

    private void By1337̗̳̰̱͙̜̳͋͑͆ͯ͂̍͏̵̱̭̪̙̲͇̲̮͕͎͇̬̰ͤ̅͋̐͋̾͘͜͢͢͠ͅ() {
        array[672][0] = "Vw2GTCH8DOFi9/38zIb6KA==";
        array[672][1] = "ف؎ؕقؽ؎،ؽ؉ؕؑؕؒ؉ؐـؕؾ؉ؔؾؑؕ؉ؿؐ؏؏؍؏؎ففؕؑؿ";
        array[673][0] = "zEy2dM7XL5M+44WQ60DWfQ==";
        array[673][1] = "ؿ؍ؓفؑؿؒؔ؉ؒؑؽؿ؉ؐؕؕؾ؉ؕ؍ؿ؍؉ؒؽؕؿؔ؏ؿؕؒؓؒق";
        array[674][0] = "N0zsAOplaTd3XjQYG3iQFg==";
        array[674][1] = "ؑؓ؎ـ؎ؐؕـ؉؍ؒؓ؍؉ؐـؔؓ؉ؕ؏ؐف؉ؔؑؑ؎ؒؿ،ؓفؕؔ،";
        array[675][0] = "/m6yvmNMGqX/MXuwq8+RjQ==";
        array[675][1] = "ؑقؿؒؾؑ،؎؉ف،ؒؕ؉ؐقـف؉ؾ؎ؽق؉ؐؑ؍؍ـؓؐؾ؏ؽ؏؏";
        array[676][0] = "VVUPyTaxmW9JRk38rX8KZg==";
        array[676][1] = "،ؽؐؿققؿؒ؉؏ـفق؉ؐـ؍ؐ؉ؽؓ؎؎؉ـف؍،ؔؽؑؐؑؽ؏ؓ";
        array[677][0] = "efdTqqwYn3YntldbT1w2UQ==";
        array[677][1] = "ؓ،ؿؓؕ،،؎؉ؒ؎؍ؑ؉ؐؿ؍ؔ؉ؽؐؓ؎؉؎ؒؐؓؾف؎؎ؿؔؒ،";
        array[678][0] = "icLd0iha7mPFjlCHDEQFRg==";
        array[678][1] = "؏ؕفقؐـؿؿ؉؏ؕ؏ؽ؉ؐقؒؐ؉ؾؿؽ؎؉ؑق؏ــ،؎ؽؒؿ؎ؒ";
        array[679][0] = "QKwxifTvaEydkDq9TxtTdw==";
        array[679][1] = "ؔؐؾ؎ؓ؍ؽؾ؉ؐ؏ؾؒ؉ؐقؒؒ؉ؽقؿف؉ؑؕؐؽؾؓؾؿؕؿقؑ";
        array[680][0] = "V5l+ArS+kewYKmOIkZv8+A==";
        array[680][1] = "ؑؑـؑؔ؍ؾ؍؉؍ؾؾ؍؉ؐؕؒق؉ؕؕفؽ؉؎ـؒؒ؎؎ؔ؎ؑؾفؽ";
        array[681][0] = "XsyVChbyaHP4io7czRsXHA==";
        array[681][1] = "؎ؽففؑ؏؏؍؉؍ف؏؎؉ؐ؎ؔؿ؉ؔق؏ؑ؉ؓ؍ؕ؏قؽؕؕق؎ؑؾ";
        array[682][0] = "oGGnNO51okYm/Fk+V1keFQ==";
        array[682][1] = "ـ؎ف،ؔ؍ؕ؍؉فقؐ،؉ؐؾؔ؏؉ؾؓؐؔ؉؏ؽؿؓ؎ؔ؏ؾق،ؓؔ";
        By1337̴̸̵̷̢̡̱̣͈̗̣̻̞̺̜͎͌̾̄́͂͆̎̽̏́̒̀̎̍̿̋͒̈̑̕̕͘͡͏();
    }

    private void By1337̴̸̵̷̢̡̱̣͈̗̣̻̞̺̜͎͌̾̄́͂͆̎̽̏́̒̀̎̍̿̋͒̈̑̕̕͘͡͏() {
        array[683][0] = "lJx8mOj6Mfn4rwWsv0cOQw==";
        array[683][1] = "ؑؕؕ؏ـفؑ؍؉ؿقؓ؎؉ؐ،ؿق؉ؕؐؓؒ؉فـ،ؔ؎قؐ؎ؔؓق،";
        array[684][0] = "8vd+HTCzzKmi2zTsJAAAkQ==";
        array[684][1] = "ؒؒـقؕ؎فؾ؉ؿؿؒف؉ؐ،ؒؽ؉ؽؓؔؔ؉ؽ؍ؒؓفؿؓـ؍؏ؐؾ";
        array[685][0] = "StAfhi/yEBoFDijp7wM3Pw==";
        array[685][1] = "ؒ؍،ؔؾـق؏؉ؾ؏ؔؒ؉ؐفؕؾ؉ؾؽؿ،؉،؎ؒؒـؐقؕ؍؍ؽؒ";
        array[686][0] = "XPwuynKoHKbYwBQ/E9HFBA==";
        array[686][1] = "فؾففـ،فف؉ؿ؏ـق؉ؐؓ؏؏؉ؽؾؑـ؉ؑ؎ـفؿؔؕؒؿؑؓؽ";
        array[687][0] = "kHbUsz1sN2wNmRocIV+QLg==";
        array[687][1] = "ؕؔؑقؐ؏؍ؕ؉؏ؽؑؓ؉ؐؑـؿ؉ؾقؐق؉ؒؾؐؔؾ؍ؓ؍فؓؒ؎";
        array[688][0] = "oVq7W1ooEOQoDTTOeLeNCQ==";
        array[688][1] = "؍فؔ؏،قؽؿ؉ـ،ؾؿ؉ؐؾؿؓ؉ؾـؕؔ؉ؒ،؏ق،ؾـؑؑؾ؎ؐ";
        array[689][0] = "YsDsbHObPpH8qaEfdzuXfQ==";
        array[689][1] = "؍ؽ؍ؾؒؕـ؎؉ؒــ؎؉ؐؒؔؽ؉ؽ؏؏؎؉ؽؔ؏؏ؐؐفؒ؎؍ؾ؎";
        array[690][0] = "W32FVIDkFcFROjrIq0MFEg==";
        array[690][1] = "؏ؐؾفؐؓقؽ؉؎،ؾؕ؉ؐـؒؿ؉ؽ،؏ؾ؉ؒـ؏ؾؑؐف؏ؔؐؐؔ";
        array[691][0] = "pt8hd1Oe4tS81ys58FpxCg==";
        array[691][1] = "فؕؒؾؓؐؾـ؉ؽؿؔؿ؉ؐؐؽق؉ؔ؎ؑ؏؉؎ؾـؔفؔ؎ؔؑؾؽ،";
        array[692][0] = "RCZDBqq71PzrowyB5aGqlw==";
        array[692][1] = "ؒؒؾق؍ؑقؑ؉ؽ؍ؓ؏؉ؐؕؾؽ؉ؕ؎؏ف؉قؕ؍ؓؕ؍ؔؓؒ،ؿؕ";
        array[693][0] = "Ng5zoJ4Ce64OOWuAiRhDsg==";
        array[693][1] = "قؒؒؔـؑقق؉ؾـ؏ؓ؉ؐؔؑؒ؉ؽؿـؕ؉قؿؔؕؾؕؽؒ؎ؓ؏ؿ";
        By1337̵̨̢̟̤̦̮͇̠͈̳̞̣̞̖̖͙̖̆ͪ̌̒́́̀ͤ̈́̋́̎̿̆͜͠͝͠͝ͅ͏̛();
    }

    private void By1337̵̨̢̟̤̦̮͇̠͈̳̞̣̞̖̖͙̖̆ͪ̌̒́́̀ͤ̈́̋́̎̿̆͜͠͝͠͝ͅ͏̛() {
        array[694][0] = "MIunaTVHoxt11qS2SxhGIg==";
        array[694][1] = "قؐؓؾؾفؿؿ؉ؔ؏ؐؽ؉ؐ؎ؔ؍؉ؕؒؐؐ؉؏قؓ؎ؿـؾؽؿ؍ؐ؏";
        array[695][0] = "30EU+D1tikBFBAQITx299A==";
        array[695][1] = "ـؽؕــؾؕ؍؉؏ؓ؏ؿ؉ؐؑ،ؽ؉ؔؐ،ؕ؉ؒق؎ؐؔف؏ؽ،؍ؾـ";
        array[696][0] = "s5ne0+8coz3NtXdNu4g7Ww==";
        array[696][1] = "؏ؿ؎فؕ؏فؽ؉؎ؿ؏،؉ؐؽؾـ؉ؽؽؕؔ؉ؾـؓؕ؍فؑؾؽ؏ؔؒ";
        array[697][0] = "JXnKSsNYK5nA3qRn4vOeIQ==";
        array[697][1] = "؎ؕؑؽـ؎؏ؕ؉؍ؾؾف؉ؐف؎ف؉ؾؾ؍ؕ؉،ؐ؎ؕؾؑ،ؔؿ؎ؐ؎";
        array[698][0] = "vDdrv1TKf8dohABSWaRhTg==";
        array[698][1] = "؏ـؕؽ،؍ؐؓ؉،فؓؿ؉ؐؑؔ،؉ؾففؾ؉ؽؽق؏ؓؿ،فؿ،فؔ";
        array[699][0] = "rrQI8ntZm8UxmEGo0UK/MA==";
        array[699][1] = "،؏ؐ؎ؓؾؐؕ؉،ؓؾـ؉ؐؿؽ؎؉ؽ؍ؿؾ؉؏قؓ،ؾقؕؑـؐؕؿ";
        array[700][0] = "sxVUc9V2mPLpxvDwJxCOoQ==";
        array[700][1] = "فؑؑؔؓ،ؐؐ؉ـق؎ؔ؉ؐؾؐؓ؉ؕؾ؍ؔ؉ؓفؾؐ،؍ؒؽـؾف؎";
        array[701][0] = "RlSerefKQng2OMjnXSrnyQ==";
        array[701][1] = "ؾ؎،فـؿؒؓ؉ؑؒؿ؎؉ؐقؒؿ؉ؔ،ؽف؉؎ؕؽؒؿؾ؏ؓؔ؍،ؐ";
        array[702][0] = "+lk9LciLFiNMcrRMzZyXoQ==";
        array[702][1] = "،قؑؓ،ؔؕؓ؉ؾؓ؎؍؉ؐؽؐ،؉ؕ؎ؔف؉ؔؔف؏ؽف؎ـؾؽ؍ؔ";
        array[703][0] = "Wp2O+Q5gP+gyMo1MFyixZQ==";
        array[703][1] = "،ـفؐؕؐقؽ؉؏قؿؾ؉ؐ؏ؽؕ؉ؽؕؾؒ؉ؿفقؐفقؾـقؓؿؑ";
        array[704][0] = "sygMArZrSJxIowwjr6Vtmg==";
        array[704][1] = "ـؽ؍ؒؕف،ـ؉ؕ؏ؿؒ؉ؐ،قؐ؉ؽؾؕؕ؉ؔؔ؏ؑق؎،ؔؽؕؿؿ";
        By1337̵̸̴̧̬̰̺̖̳̖̙̝̲̩̽̄ͥ̑ͮ̾ͩ̍ͭ̏̒̎ͪ̃͗ͫ͆̒͗̍ͤͮͯͬ̚ͅ();
    }

    private void By1337̵̸̴̧̬̰̺̖̳̖̙̝̲̩̽̄ͥ̑ͮ̾ͩ̍ͭ̏̒̎ͪ̃͗ͫ͆̒͗̍ͤͮͯͬ̚ͅ() {
        array[705][0] = "MVpFdorbFVqgEniMD1I30A==";
        array[705][1] = "ؐؓؔؔـقؿؕ؉ؔؾؓؿ؉ؐؐؐؿ؉ؔؕؐ؍؉ؓؕـؕ؎؍قؒؐؿؓؔ";
        array[706][0] = "vcMAQWGTKZHGp6Kq/Dt71w==";
        array[706][1] = "ؿ،ؐؕؔؒ؏ق؉ؓؒؾؔ؉ؐؓؽؐ؉ؾففؔ؉ـؒ؍ؔؓؾؔؕؔؔؔؾ";
        array[707][0] = "rDj9hT9+ZUCV9DHMNYjF5w==";
        array[707][1] = "ؽؐـفؓ؏؎ؿ؉ؽؽؕؓ؉ؐؓ،؏؉ؔؽؑؒ؉ؔ؍ؽؾؔؾؔؔؑؿ،ق";
        array[708][0] = "OR2idhd013QobWeqH0alig==";
        array[708][1] = "ؾؐؕ،ؾقؔ؍؉ؑؾؿؑ؉ؐؔ،ؿ؉ؾ؏ؑق؉،؏ؕؾؕؾق؏ؾ؍ؕؿ";
        array[709][0] = "/xjpKYG4LN1nDuaHc31GEA==";
        array[709][1] = "ؿؓؒؒؐ؍ؓ؍؉؍ؕؓف؉ؐـؾؕ؉ؾففؾ؉ؓ؎ؓؽ؎ؔؑؑ؏ؓـق";
        array[710][0] = "rIlZo6YfX1Z42gDBWCzlWg==";
        array[710][1] = "؎ؾؔؐؽ؏ؔ؎؉،فؾـ؉ؐؽؐؐ؉ؕ،ؒف؉ؿفؽـؐؾؒ؎،ؑ؎؎";
        array[711][0] = "BSiRxPCB601sq8E1FesXDQ==";
        array[711][1] = "ؔؓؿؕؓ؏،ف؉ؓؿ؎ؒ؉ؐؒ؎ؐ؉ؔ؎ؽـ؉،ؕؒ؍ؓؿؕؽفؓؑؐ";
        array[712][0] = "9qT4HD038LWEDD4ny9W+Ig==";
        array[712][1] = "ؕؐقؐـؒؿؔ؉ؓقؒؒ؉ؐ؏ؾؽ؉ؽؔ،ق؉ـؽؽـ؍ؾؿؽ؍؎ؒؓ";
        array[713][0] = "D8NYCjmRvE3P/QFXjxz57A==";
        array[713][1] = "ؽ؏؎ؾف؏،؏؉ؾؒ؍ـ؉ؐؒؑق؉ؾ؍ؐؽ؉ؐ،؍ؕ؍ؾؑـ،ؔ؎ف";
        array[714][0] = "2IMMIkKHymdVYryGRiS4/w==";
        array[714][1] = "ـؐـؿؕؒ؏ف؉ؾؕ؍ؔ؉ؐؾق؎؉ؾؔ؎ؑ؉قـؽ؎ؾؾؕؑؿؒؕف";
        array[715][0] = "twl4DkHtlbTBnJ1u5E/taQ==";
        array[715][1] = "ؑ؍؏،ؕؿؽف؉ؾؽؾؽ؉ؐقؿق؉ؕؒ؍ؒ؉ؒـ؏ؽؒ؎ؑق،ؔ؏ؽ";
        By1337̼͇͈͈̣͎͇̗̼̱̹͍͐̎̓́̊ͩͯ̈̒͛̀͂̀̿ͣͫͥ͐̍̓ͬ̈̚̕͟͟͡͠();
    }

    private void By1337̼͇͈͈̣͎͇̗̼̱̹͍͐̎̓́̊ͩͯ̈̒͛̀͂̀̿ͣͫͥ͐̍̓ͬ̈̚̕͟͟͡͠() {
        array[716][0] = "Lxyd0m0Mz2uH+6XNoXNwfQ==";
        array[716][1] = "ؕؔؽؕؾؒفؾ؉ؽؿ؍ق؉ؐؽؿؿ؉ؽ؍ؐـ؉،ؓؾؾ،ؓ؍ؿ،فـؕ";
        array[717][0] = "l2vkouf1wB8U3hP4niQYCw==";
        array[717][1] = "ؿ؎،ؕ؏،ؐ؎؉قؔؔؕ؉ؐ،؎ؽ؉ؽ؍ؿ؍؉فؕؾـؑؽؒفؒؿؐف";
        array[718][0] = "yQbnSPFthPBaqNu+U67xmg==";
        array[718][1] = "ؔؓؐ؏ؒ؏ؿؿ؉ؾف؎ؑ؉ؐف؍ـ؉ؔؾؽؽ؉ؽقؔؐ؎؎ؐفؽؓؓ،";
        array[719][0] = "a+yTTEwAi4wiawubVFpyYw==";
        array[719][1] = "قؐ،ؾ؏،،؎؉ؓؽؿؽ؉ؐؒؿؿ؉ؔ،ق؍؉ؽؐ؎ؿفؓؽؽ،ؑ؏؍";
        array[720][0] = "8R6yjXLmyQ1nF8NCI+QWxw==";
        array[720][1] = "؏ـؽق؎ؕفؒ؉ـؒقف؉ؐؓؽـ؉ؽف؎ؾ؉ؿؓـؒؽ؎فقؿقـق";
        array[721][0] = "cQhgJi3RMnivuUTR4DEQnQ==";
        array[721][1] = "ؿؽؕؑقــؒ؉؎ؒؔؽ؉ؐؕ؎ؒ؉ؽؐؽؒ؉ؿؔ؏ؔؑق؍ؾ؏ؐ؏ؑ";
        array[722][0] = "rjJZTzvHidH8FrZN0o/qEw==";
        array[722][1] = "ـؔؽؿؕؽؾ؎؉؎ؐ؏ؒ؉ؐ،قؑ؉ؾ،ؓؿ؉؏ؽؑؾؓؒؓؿؑؔ؎ؑ";
        array[723][0] = "Op22nzO0cUjy2ROssCok/Q==";
        array[723][1] = "ؑؽ؏؎ؑ،،ؾ؉ؔـؓؔ؉ؐؿؽؿ؉ؕق؍؏؉ؒ؍ؑ؎ؔ؎ؾ؎قؓؔـ";
        array[724][0] = "2W+KMql9m5oAT+ugVaVXYA==";
        array[724][1] = "ؓؾقؒ،ؽ؏ؒ؉ؑ؍ـ؎؉ؐفؒؒ؉ؔ؍ـؾ؉ؐؑ؍ؓؾؐ؎قؐؿؕؐ";
        array[725][0] = "wcvML+hnc7jPPN5KwvWJJA==";
        array[725][1] = "ـؾؓؑ،؏؍؎؉ؽؒ؍ف؉ؐؒؾق؉ؕؓفؔ؉ؾؽ،ؾ،ؓؒ،ؑ؏ؽؐ";
        array[726][0] = "BzX0s6qyM+GyJBgXO7kJvw==";
        array[726][1] = "؎ـؾ،ؓفؾف؉ؑـقؔ؉ؐؽؕؕ؉ؽؾ؍ؔ؉ؓؓؕقؒؒـ؍ؕؕؕ؏";
        By1337̟͑͛̽ͯ͞͏̶̶͙͇͉̝̺̞̮͚̼̳̝̋̓̎͂͒̊͆̇ͬ̀̀ͭ̀̽ͫ͋ͣ͒ͥͯ();
    }

    private void By1337̟͑͛̽ͯ͞͏̶̶͙͇͉̝̺̞̮͚̼̳̝̋̓̎͂͒̊͆̇ͬ̀̀ͭ̀̽ͫ͋ͣ͒ͥͯ() {
        array[727][0] = "In8LmofNBmS80I9Ka8qfsg==";
        array[727][1] = "ؐ،ؑ،؎؏ؓؒ؉ؔؕ؍ؒ؉ؐؐؽؒ؉ؽؒؿ،؉ؕففـؑؕؽ؎؍ؒ؍ـ";
        array[728][0] = "r+6IzgH+ia8r47UZ4amRXg==";
        array[728][1] = "ؐؕؐـ؍،ؿؾ؉؏فؽ؎؉ؐؓ؍ق؉ؽ؏ف؏؉ؓف،ؔ،فـؐفؾؿؾ";
        array[729][0] = "JH3kygib3KRl+qJgREZhlA==";
        array[729][1] = "؎؍ؐفؕـؿ؎؉ؕقؓؓ؉ؐ؏ـؾ؉ؔؽؐؽ؉ق،ؓ،فؿؔفؓـ؍ؕ";
        array[730][0] = "ZLPXCidnMcfLcI3OJp1xBQ==";
        array[730][1] = "ؐؾف؏،ؓـ؎؉ف؏ؾؔ؉ؐؑقؿ؉ؕفؑؐ؉ؿؔؽققــؐؕفـؑ";
        array[731][0] = "rXRNSSkCQXHpMVwIncRImQ==";
        array[731][1] = "،ؔؔ؏ؓؿؐق؉،ؕ،ؔ؉ؐ؏ؓـ؉ؽؐ،ؕ؉ؔؿؑـ؏؏ؐقؐؽ؎ؾ";
        array[732][0] = "x99hqIx+w++qVJLNOqXd/A==";
        array[732][1] = "؎ؓؒؿفؽؓؾ؉قؕؒ؎؉ؐ؍ؔؽ؉ؾؔؕؒ؉ؒ؎ؿ؍قؽف؎،؎قؾ";
        array[733][0] = "btLkkgIJoevZFWg8/IA4DQ==";
        array[733][1] = "ؔؿؽ؎؏ؐؾـ؉؏ؒ،ؑ؉ؐؒؾؾ؉ؔ؏قف؉ؿ؏ـؑؓؾؾ؎؍ؑؕؐ";
        array[734][0] = "T8KGAMepA2MiZ7j9W2nNMQ==";
        array[734][1] = "ؐفؽؕؑؓؽؽ؉ق؎ؕ؍؉ؐؐـف؉ؔؑفؕ؉؍ؿؾؔـ؏،؏؏ق،ؔ";
        array[735][0] = "ihynzhRuaiO4B12aoPJDcg==";
        array[735][1] = "ؔقؽ؍ق،ؒؿ؉ق،ـؔ؉ؐقفـ؉ؽؔـؐ؉،،ؑ؎ؐؓؽ؎؍ؐ؍؍";
        array[736][0] = "LXDNDnfABKIXjCDHkulpvw==";
        array[736][1] = "ق؏ؒ؎ـ؍ؑؓ؉؎،قؕ؉ؐ؍؍ؒ؉ؾ؍ؐ؏؉؏ـؓؐؓ؎ؔؐقؿؒؔ";
        array[737][0] = "m9c4nC1xmjHpuDKdHdsW0w==";
        array[737][1] = "فؿؿ؏قؐؕـ؉؎ؒؑؾ؉ؐؿ؏ؓ؉ؾؔؔؕ؉؍ؾؿؑؾؒؿؒققؾؾ";
        By1337̸̵̧̛͖͙͎̼̻̩͙̳̞̬͇̫̥̈́̉́̂ͤͨ̔́ͥ̂ͮ̈́̾̏̂̃̆̉̍̂̋̎͜();
    }

    private void By1337̸̵̧̛͖͙͎̼̻̩͙̳̞̬͇̫̥̈́̉́̂ͤͨ̔́ͥ̂ͮ̈́̾̏̂̃̆̉̍̂̋̎͜() {
        array[738][0] = "pydhCMeLSbSVkbjiwVRuTQ==";
        array[738][1] = "ؔ؏ؿؔؽؐق؏؉ـؐؓؕ؉ؐؽؔؓ؉ؾؐف؍؉؍ؒـقؔؿـؕؒ؎ؕؿ";
        array[739][0] = "7r4nUiAK5P39W93GHJpagw==";
        array[739][1] = "؏فؽ،ؽ؍؎ق؉ؾؓ؏ؕ؉ؐق؎ؐ؉ؔ؍قق؉،ؽ؏ؐ؏ؓؿؐؿ؏ؒ،";
        array[740][0] = "wSkVaBDRcnjav2CoHRPJPg==";
        array[740][1] = "فؿـقـفؽؔ؉ؓؐـؑ؉ؐؾ،ؒ؉ؾ؍ـؑ؉فؑفؿ؎ؑقؑؐـؐؐ";
        array[741][0] = "Ly0wAVCo3vrCxzf+kYhBmg==";
        array[741][1] = "ؑؽؐؑؑؔـ،؉ؿؽؽؑ؉ؐ،فـ؉ؕقؒ؏؉ؓـؿؒففؔؔـ؍قؒ";
        array[742][0] = "CEg1fPphrfvWTxX9qm0dVA==";
        array[742][1] = "ؓؾؽؐؔؓؿ،؉؍،ؽ؏؉ؐؒ؍؎؉ؾؿؾ؍؉ـ؍ؿؽؾفؽ،؏ؿؓؾ";
        array[743][0] = "AwlnjOr6hx/fmZTY15zIcw==";
        array[743][1] = "،ؑق؍ؕ؍ؾؐ؉ؒؐؽ؏؉ؐ؎؎ؐ؉ؽؔؕؓ؉؍قؔؒفققؓؽـؾؒ";
        array[744][0] = "IKPszh7XWWM+Ht0Wm4fsuA==";
        array[744][1] = "؏؎ؕؓــؕؑ؉ــؑؿ؉ؐف؍ؿ؉ؾؽؾ؎؉فؓقؑؾؿ؎ق؏؍ؐؕ";
        array[745][0] = "bMtxGE2K2nkMYTmmsPq6gA==";
        array[745][1] = "ؒؿ،ـفؾؽؕ؉ؓؽ،ف؉ؐؐؓق؉ؔؓ؏ؿ؉ؑؐؐ؍ؑؾؿؓ،ؔ،ؔ";
        array[746][0] = "cjcAT56alAOg4jqi8E9ryQ==";
        array[746][1] = "ؑؑؽؑؔؓفؓ؉ؓؑؾؑ؉ؐؓؿؑ؉ؔ؍،ق؉ؾ؍ؕففـ؎ؓ؏ؒقؕ";
        array[747][0] = "oXpiUhcyrNxe2Y32UZ7Fog==";
        array[747][1] = "ؿ؍ؿؕـ؎ؔؐ؉ـؿؽف؉ؐ؏ؓؑ؉ؾؑؒـ؉ؓق؍ؾف؍،؎ؐ،ؔؓ";
        array[748][0] = "47KUi9DYiA68PsqabYjPKA==";
        array[748][1] = "ؽقؾــفؐؓ؉ؑ،ـؑ؉ؐؕؐؿ؉ؕ؏قؔ؉ؽؓؿف،ؕؔ؍فؿ؏ؽ";
        By1337̧̪͚͚̥̱̳͔͔͓̜ͥͭ̀̃̈́̀̆̕͢͏͔̮̮̤̞͈̓̿ͪ̆͂̉͊̐͑̍͗͆ͣ();
    }

    private void By1337̧̪͚͚̥̱̳͔͔͓̜ͥͭ̀̃̈́̀̆̕͢͏͔̮̮̤̞͈̓̿ͪ̆͂̉͊̐͑̍͗͆ͣ() {
        array[749][0] = "Jp7komLPi4GnxqvqZZQ+Qw==";
        array[749][1] = "فؾؔؑ؏ؽؔ؏؉ؿ؍ؿ،؉ؐؕؔؑ؉ؕؓؐؒ؉؍ـؓفؓؔ،ؐؕ،قؕ";
        array[750][0] = "6K9BlGJnlkeswpBNX8sGPQ==";
        array[750][1] = "قؽؓ؍ؑ؍ؾؕ؉؍ؽؿـ؉ؐؑؽؾ؉ؾ،ؒ،؉ؔ؏؍ؑ؏ـؓؿؓ،ؽؑ";
        array[751][0] = "6xPJrS1oHueteyHvfnU6AQ==";
        array[751][1] = "فؐؓؿؿ؍ؿؐ؉ؿ؏،،؉ؐ،ؒف؉ؕؑفؾ؉ؾـؒؑ؍ـ،ؑؑفـؿ";
        array[752][0] = "0GzptYrbbEt5l5P5irYuZg==";
        array[752][1] = "ؽؐؾؑؕـؽؿ؉ؔؐؔ؎؉ؐؔ،ؑ؉ؾؔؿؐ؉ؐ؎فؿؿؿ،ؾـف؍ؽ";
        array[753][0] = "nKcFnXB3SLDUAz0oSlQ0hA==";
        array[753][1] = "ؒفؑق؎ؐقؕ؉؏ؓف؎؉ؐ؏ؔؓ؉ؽؐ؏ؒ؉،؏،ؑ؏؎ـقؑؕؽؐ";
        array[754][0] = "MV5kfc4OoZUfnWrTevD8eg==";
        array[754][1] = "ؒـؑؒـؿؑؒ؉ؓؾؽؐ؉ؐفؿـ؉ؽ؎؏ؿ؉فؓؕ؍؏،ؓف؎ؐؕ؎";
        array[755][0] = "yS/HSWl9g1KabQQ1o2oWoQ==";
        array[755][1] = "ؾؑؒ؏؎ؑؒؾ؉؏ؑ؎ـ؉ؐؓؾؾ؉ؾقؿؕ؉ؑؿؑؔ،ـؔفقؿ،ف";
        array[756][0] = "F0rkxj8XLKBUXLv+008vdQ==";
        array[756][1] = "ق؏ؔـ؎ؓ،ق؉ؾؿ؎ق؉ؐؾ؎ؓ؉ؽ؎؎ف؉ف؍ففؿـ؏ففؕ؏؎";
        array[757][0] = "Nd9H6dcZSc0Foo3uoEtSUA==";
        array[757][1] = "ؓ؏ؐ؏؏ــؾ؉ـ؏ؐف؉ؐؕؓؾ؉ؾؐؐ؏؉ؒ؍قؾؔؽـؽؕؽ،ؒ";
        array[758][0] = "nKqLFgvhCYva9wJ58ucN6g==";
        array[758][1] = "،ؾؑؑـ؎ـؒ؉،ؑ،ؒ؉ؐؓؑف؉ؔف؏ؑ؉؏ؾـ؏ؒؾؑفؿؾـ؍";
        array[759][0] = "tDtj8eqcE60aoJD1Ny9Prw==";
        array[759][1] = "ؾف؍ؾـؒ؏؏؉ـؕ؏ؓ؉ؐؑؿف؉ؽؕـؒ؉،؍ؑؿؓ،ؾؽقؑؕؐ";
        By1337͏̷̵̴̛̯͚̟̣͚̭̰̻̭̖̯͕͕̻͆̑̏̐͒̀͑̃͐̊̀ͯͦ͋̇̒ͮͯ͟͜͝();
    }

    private void By1337͏̷̵̴̛̯͚̟̣͚̭̰̻̭̖̯͕͕̻͆̑̏̐͒̀͑̃͐̊̀ͯͦ͋̇̒ͮͯ͟͜͝() {
        array[760][0] = "A8A8xYYK/1WJFVoX4oeHiQ==";
        array[760][1] = "؏ؓؑؑؿؓؽؓ؉،ؓؒ؎؉ؐؐـق؉ؽؒ؎ؕ؉ؐ؏ؑ؎ف؎؎ؔؒؐؿ؏";
        array[761][0] = "6YWTm1dR4x6OToml5BLHQA==";
        array[761][1] = "ف؍ؐـؕؒ؏ؓ؉ؓؕؐـ؉ؐقؐؐ؉ؾق؍ؐ؉ؓؑؓؾفؿف؏ـ؍ؽؔ";
        array[762][0] = "A82sm3/djCWlX3iOLscGXw==";
        array[762][1] = "ؾؑؽؽ؎ؑ؍؍؉ؐـ؍ؒ؉ؐؑؐؔ؉ؽؕؾؾ؉ؿؕ؎ـؒف؏ؿؿ؍،ؽ";
        array[763][0] = "MeyB2BhSs5xnyyMtbaR0Mw==";
        array[763][1] = "قؿ؎؏ؒق؏ؐ؉ؔققؽ؉ؐ؍ؐ؍؉ؔؔؾ؎؉؏ؑؔؐـؒففؕؔ؏ؑ";
        array[764][0] = "hFaywG0Rfyi9mLW7g5I7Xw==";
        array[764][1] = "ؐؾؐقؿ؏ؔؿ؉قفؔؕ؉ؐؑ؎ـ؉ؾف؏ؓ؉ؕؒ؍ؐؿقؕفـؿ؏؏";
        array[765][0] = "HoJd8deZujYV294jZdQpGw==";
        array[765][1] = "قؒؓقـ؍فؿ؉ؒؐؑؑ؉ؐ؎ؾ؎؉ؾؾؓؾ؉ؾؓؽؑؓؑ؍ؒ؍ؕؔؽ";
        array[766][0] = "w+tezcyoON4x1BZXLFLuzw==";
        array[766][1] = "ؒؔؐف؍ؽؾؽ؉؏ؐؓ؍؉ؐ؏؏ؓ؉ؾؐؓـ؉ؒق؎ؓف،ـؐفؔؐؓ";
        array[767][0] = "BlfVMFSQBAC3sdWbKJV62Q==";
        array[767][1] = "ؓؒؓـؒؐ،ؑ؉ؑقؕؑ؉ؐؾؓ؏؉ؔ؏ؾؐ؉ؕ،ـؑؔقؐـؐؔؐـ";
        array[768][0] = "vQEc8fZdBae6b14YlqEpTw==";
        array[768][1] = "؍قؑؒؑؑؒؕ؉ؒ؎ؒؔ؉ؐ؏ؕؽ؉ؔ،ؾؒ؉؎ؾ،ؽؿ،قؒؔفـؕ";
        array[769][0] = "xnaDCiVtgIxBKfcaQ0TNpg==";
        array[769][1] = "؏ـؾؿ؍قـؾ؉؍ف؍ؾ؉ؐؑؽ؏؉ؕؐؽؒ؉قؔؔؒؓؑ؍ؽ؍ؔؿؾ";
        array[770][0] = "DgAOf4j8Ws7l5+b+q0RJqQ==";
        array[770][1] = "ؓؔققؕؒؾف؉ؑؐؾؒ؉ؐ؏؍ـ؉ؔؽف،؉ؕ،ؕفؿؐؐؽـؿ؍ؿ";
        By1337̶̧̛̮̗̠̘̹͚̞̝̠̹̣̠̹̭͉̖̉ͭ̒̿̈ͧ̽̍͗͂ͮ̌ͪͨ͌̐͗̕͜͟ͅ();
    }

    private void By1337̶̧̛̮̗̠̘̹͚̞̝̠̹̣̠̹̭͉̖̉ͭ̒̿̈ͧ̽̍͗͂ͮ̌ͪͨ͌̐͗̕͜͟ͅ() {
        array[771][0] = "6iUusBxMDd7IV5pvVkdHXQ==";
        array[771][1] = "ؿفؽؑ؍؍،ؽ؉ؓؓؓف؉ؐ؎ؒؒ؉ؔ؍؏،؉،ؑف؎ؓفؽؽؾؐقؾ";
        array[772][0] = "QYx1V1wp2XKqXEO/lRuSTw==";
        array[772][1] = "؏ؑؔ؏؏ؽ؏؍؉ؑؔؒ؎؉ؐؓؓؕ؉ؽؕ؏ؽ؉قؑقؾف،ؽؔؽ؏ؿف";
        array[773][0] = "YDxEYq8IEBhIdZ5xAJtEOg==";
        array[773][1] = "؍ؿ؎؍ـؓ؏؎؉ؕؾؕؐ؉ؐفؿؓ؉ؽؐف،؉؎فؾ؎قؽـؿؓؒؔؽ";
        array[774][0] = "ZQnOyTRg8zo8YmjRHIkQKg==";
        array[774][1] = "ؔؽ؎ؽقؽ؏ؔ؉،ف؍ف؉ؐفؓؽ؉ؽؔفق؉؍؎ؿؽؐؓؾؿؿؕؾ،";
        array[775][0] = "9iXjcOSZhjjAJZixmcE5Vw==";
        array[775][1] = "؍ؐ؍ؑؾؾ،ـ؉،ؒؑؐ؉ؐـؒـ؉ؾؾؾؔ؉ؾ؏قؐ؏؍؍؏ؓؒ؎ف";
        array[776][0] = "uySi2dejt4WXQVFMqMzqSA==";
        array[776][1] = "ؽؑؑقؒؑؔـ؉ؽؔ؍؎؉ؐؕؕق؉ؕؑؽؑ؉؏؎ـفف؍،قؿؐؒؕ";
        array[777][0] = "3Mm1nu9T5M1W9QbOKUzbWw==";
        array[777][1] = "ؑ؏؎؍؎ؾ؎ؒ؉؏ف؎ـ؉ؐؕقـ؉ؔؿؾؽ؉ؾؒؕــف؎ؔؿؔؐؓ";
        array[778][0] = "BB70FpOg7U/tQuTSG+fPEg==";
        array[778][1] = "ؾؐفؿ؎؍،ق؉قؑؕؔ؉ؐؔؑؕ؉ؔؓؔف؉ـ؎؎ؽؕؕؽؑؓــؾ";
        array[779][0] = "7XCK/eiiqBfItx5kOhowpg==";
        array[779][1] = "ؓؽؽقـؽؒؔ؉ؑؕؿ؏؉ؐـ؏،؉ؔؓؾ،؉ؾؐـؔؒـ؏ؿؓؿؔف";
        array[780][0] = "E+N0x+B8M6KZItUwbIFHBg==";
        array[780][1] = "ؓفؕؿؕؑؽؾ؉ؽؕ؎ؒ؉ؐؽؐؐ؉ؕؔؾ؏؉ؐفؽ؍فؔ،ؾؒؕؿؒ";
        array[781][0] = "5p+JAteQ1a+vdQwwPsACSQ==";
        array[781][1] = "؎ؓؓؽ؏ؾؑـ؉،ؔؒؾ؉ؐؽ؎ؕ؉ؔؿقف؉ؐؿ،ؿؓ،ؐؾ،ؐؔؓ";
        By1337̵̛̪̭̝̫̝̯͓̰̞͇̓̏ͥ̓̔͐ͮ̊̆̈́̕͏̸̡̧͙̩̻͕̺̀ͮ̾̅́̉͐̔();
    }

    private void By1337̵̛̪̭̝̫̝̯͓̰̞͇̓̏ͥ̓̔͐ͮ̊̆̈́̕͏̸̡̧͙̩̻͕̺̀ͮ̾̅́̉͐̔() {
        array[782][0] = "ShWKV4y4Zjc1AQLaRIvseg==";
        array[782][1] = "ؕؐـؔ؎ؑؓؕ؉؏؍ؕؿ؉ؐ؍ـ؍؉ؾ،،ـ؉ؓؑ،ــقف؎فف؍؏";
        array[783][0] = "YT/huE0Yt2VqJLjbrfolWA==";
        array[783][1] = "ؿ؍ؿؔـ؍؏ـ؉،؍ؽؓ؉ؐؿؽف؉ؔف؎ؓ؉،ؔؓ؍ؿ؍ؽ،ؔؑؕؽ";
        array[784][0] = "UiBt/x461kGy4YmABbnrZQ==";
        array[784][1] = "ؕؓؑؐفقؿؓ؉ؒ؍ؕـ؉ؐ؏ؾ؍؉ؾق،ؾ؉ؐفؑ؏ؾفؽؽؑ؎ؓـ";
        array[785][0] = "mefRbddZ0fHflPmNYgXgrg==";
        array[785][1] = "ؾؽ،؍ؐؽ،ؒ؉؍ؒؐق؉ؐ،؏ؔ؉ؕؓؾق؉ؾفقؓفؕ؏ؑؽ؎؍ؾ";
        array[786][0] = "KZMKyw6t32QqsK6mmSfnug==";
        array[786][1] = "ؐؓـؔ؍؍؎ؒ؉،ـؽ،؉ؐ؏فؾ؉ؾؐق؍؉ؾؽؐؕؾؔ؏؏ـؓؕؽ";
        array[787][0] = "bHLGaiRZTg9JTlk+T43C8A==";
        array[787][1] = "؍ؒ،فؐؐؕؕ؉ؔ،ؑؽ؉ؐ،قؔ؉ؔؾؿؓ؉؎؎؎ؿؓ؍ؿ؏ـؽقؽ";
        array[788][0] = "q0xGMhixinlA24RgOX38bA==";
        array[788][1] = "فـؿ؏ؽؽؕ؍؉ؕف؍ـ؉ؐ؍ـؕ؉ؾؾقق؉ؽف؏ؽؽؾفؾؽؑـؾ";
        array[789][0] = "W2yXmiT5ufy3xRH/9gCr9w==";
        array[789][1] = "ؑؑـق؎ؿؿؕ؉ؕؽـؾ؉ؐؾؿؿ؉ؾـقـ؉؍ؿ؎ققؽؐؑؾفؑف";
        array[790][0] = "FKkwMBFRVwc8xz1pmlov+g==";
        array[790][1] = "؎ؔـ؎؏قؐؔ؉ؒؿؑ؎؉ؐؾ؏ؽ؉ؾقـؽ؉ـ؏ؕـ؏ـؿ؏فؔ؏ؿ";
        array[791][0] = "EVqWyW5FpmnsyrKxsQ0KRA==";
        array[791][1] = "ؐؔ؎ؓ؎ؒقؔ؉،ؾؓ؏؉ؐــؾ؉ؕ،ؽؕ؉فؾ؎؍؎فؔؕؕؒــ";
        array[792][0] = "f3YIg7qz2YDvUZWU3qhWEA==";
        array[792][1] = "ؐؐؕؐف؍فؿ؉ؑؽ؎ف؉ؐ؍؏ؕ؉ؾ؍ؾق؉؎ؑ،؎ق؍؎ؿؔ؏؍ؔ";
        By1337̨̫͍͖͚̻̜̥̮͕͔̠̾ͪͣ̍͆̚̚͠͏̛̪̲̣̜̭̘̗̫̣̲͌̒̿͜͞͝͝͏();
    }

    private void By1337̨̫͍͖͚̻̜̥̮͕͔̠̾ͪͣ̍͆̚̚͠͏̛̪̲̣̜̭̘̗̫̣̲͌̒̿͜͞͝͝͏() {
        array[793][0] = "APL/C7aqzbYf29b+PKGHgA==";
        array[793][1] = "ؐ؍،ؽقؐـ؏؉؏؍؎ؽ؉ؐ؍ؽ؏؉ؕــق؉ـؒـؽفؽ،ـؐؔؓق";
        array[794][0] = "pnh6CWVA0QclTRwA1EpDbQ==";
        array[794][1] = "ف؍ؾؒ؏ؔؑؔ؉ؐفؿؽ؉ؐؔ،؎؉ؕؔقؽ؉؍ؓؔقؿؕؽؒـقؔؾ";
        array[795][0] = "m3LS/Tb8JTRJKbrtLfw8jg==";
        array[795][1] = "ؒؐؔؿؒؾؾق؉ؾــف؉ؐؔؽؿ؉ؾ؏ؑق؉ؽؒـؑؕق؍ؾؿؐفؾ";
        array[796][0] = "mPWOf2kKt4H7woLGdw7GFg==";
        array[796][1] = "؏ؿؾؔ؍،ؑف؉فؿـؽ؉ؐقق؏؉ؾ؍،ؑ؉ؿـؒؐ؏،ؿؕف؏؏ؽ";
        array[797][0] = "qRza4pgPtnYWRuUle3nnLQ==";
        array[797][1] = "ؑؑؑ؏ؐؒؐ؏؉ؕؕؿؒ؉ؐؽؑ،؉ؔ؏ؒؾ؉ؐؕقؓ؍ؔؑؕؽؔـق";
        array[798][0] = "A+ERmRiJUDJQG0dMa2SxEg==";
        array[798][1] = "ؾؓؑـؽؒؓؑ؉قفقق؉ؐـؾف؉ؔف؎ؒ؉ق؎،ؿؕؕؽؓؽؐؔؔ";
        array[799][0] = "9hKwWwNGxzwJwwAGZeIAbw==";
        array[799][1] = "،ؑؑؒؑـ،ؓ؉؏فقؓ؉ؐ؍؎ـ؉ؕـؿؓ؉؏ؓؿ؏؏ؒ؍ؕ؎ؕؿ؍";
        array[800][0] = "qT4KgiI/c0FjME61js/8DQ==";
        array[800][1] = "ؽققؐقؔؾ،؉ؐؒف؏؉ؐ؍ؿف؉ؔـؐ؏؉ـفؔ؎ؔؓؾفؒ؍ؿف";
        array[801][0] = "eL7R+TMxWuvzjJnuJrp+xQ==";
        array[801][1] = "؎ؒؓؽؽؒق؍؉؎ؕؽ؎؉ؐؓ؍ق؉ؽؕؐؓ؉ؕؑقؔؾق؏فؽؐؕؿ";
        array[802][0] = "zQhwGW/kLR6+pq1aJmf1IQ==";
        array[802][1] = "ق؍ـ،ؑؿـ؍؉ؓؓؒؿ؉ؐ؍ؑ،؉ؔ،ؑؿ؉ؽ،ؑؽفؽؓ؏ؔ؍ق؏";
        array[803][0] = "ATxfO1VpeB6qgJcC7i5S6g==";
        array[803][1] = "،؍ؒؐؕـؽؾ؉ؕؿ،ؾ؉ؐقؿؑ؉ؕ،ؒق؉قفـ،ؾؑؐـؐؾفق";
        By1337̴̭̹͎̅̎͛ͣ̍͂̒͠͏̴̶̸̢̼̟̹̬̰̰̯̥̬̘̲̤̲̇̐̂͗ͭ̊͐ͮͫ͋();
    }

    private void By1337̴̭̹͎̅̎͛ͣ̍͂̒͠͏̴̶̸̢̼̟̹̬̰̰̯̥̬̘̲̤̲̇̐̂͗ͭ̊͐ͮͫ͋() {
        array[804][0] = "KrZjNa13FvPxiVgDC1W8zA==";
        array[804][1] = "ؿفؒؿـؾؕ؏؉ـؔؐق؉ؐ؏ؐؓ؉ؽؓؒؑ؉قؑؐؕؿؾ؎؏ـؑ؎ؿ";
        array[805][0] = "WEdhTXvkNKG5X1me/7JsBw==";
        array[805][1] = "ؕ؏ؓؕ؍؏ؔ؎؉؏ف؏ق؉ؐؔؒ؎؉ؕـ؏ؾ؉،ـؕ،ؽـؑؒفؔؐؾ";
        array[806][0] = "J8W4/jcFxcDaFLaMxvBtmw==";
        array[806][1] = "ؒؓؽؑؒؓقـ؉ؒ؎؏ؑ؉ؐ؏؍ف؉ؕقؾؓ؉،؎ؾؿ؏ؐؽ؍ق؏ؓؽ";
        array[807][0] = "GcAXxyAvogSokmoNI664SA==";
        array[807][1] = "ـؾفؓ؏فؒ؎؉،قؽق؉ؐؑ؎؏؉ؾؑؐق؉؎؏ؓؾ،ؾؕؽؑق؏ؕ";
        array[808][0] = "Xia+8stdjM8tmqHPh2MfBQ==";
        array[808][1] = "ؽ،،ؿفؿفؑ؉ؒ؎قق؉ؐؾ؎ؔ؉ؔق؎ف؉؍ؑفؔ؏ؾؾ؍ؿ،ؕ،";
        array[809][0] = "YSww45nwDNaNbx47Ia+1jQ==";
        array[809][1] = "ؐؓؾؾ،؍؎ؔ؉فق؍،؉ؐفؾؕ؉ؽؓؕؽ؉ؔؓؕ؍ؽؒؐؐؒ،فـ";
        array[810][0] = "dJcrOZ9+nHdlHkvR4Q2AlQ==";
        array[810][1] = "ؐؒؕؒ؏ؓ؏ؐ؉،ؾؒؔ؉ؐؐؕؑ؉ؾـ؍ؽ؉ؑؕؐؾف؍ؔؒؐ؍فؾ";
        array[811][0] = "78v+jla2KWp6Oql4i1bQpA==";
        array[811][1] = "ؒؕؽؽؑؾ؍ؕ؉؏ؑ؎؍؉ؐؿؾؐ؉ؔ؍ؿ،؉ؒؓؽؓ؍ؑؕؒ؎؎ؕؾ";
        array[812][0] = "wQk2EQvHrUNrZve7BP96nQ==";
        array[812][1] = "ـفؾؑؔؐق؍؉؍ؕ؍ـ؉ؐق؎؍؉ؕؾفؾ؉؏؎ؿ؏ؓفؑؔؑقؾف";
        array[813][0] = "MlzM8qMi6Ile6ZF26wrF/g==";
        array[813][1] = "؏ؐ؍ؐؾؕؾف؉ؓقؒؿ؉ؐؾؿ،؉ؔؕ؏ف؉فؐؾؿؿؒؿ؎،ؔؓؕ";
        array[814][0] = "Gvf1Djr7PLz4yjeTQaEiZA==";
        array[814][1] = "؎ؕؒؒفؒؽ،؉ؑؿ؏ؐ؉ؐ؍فـ؉ؽفؽـ؉ـفؽؽقؓقؒقؐؐؕ";
        By1337̶̵̨̧̖̲̭̱̼̙̱̱͉̼͎͍͕̬̺͗͒ͭͩͨ̓̏͆̏͐̑ͭ̇͆ͧͬ̅̽̚̚͟();
    }

    private void By1337̶̵̨̧̖̲̭̱̼̙̱̱͉̼͎͍͕̬̺͗͒ͭͩͨ̓̏͆̏͐̑ͭ̇͆ͧͬ̅̽̚̚͟() {
        array[815][0] = "zWS06OYyP1KcNgCQhEYAkw==";
        array[815][1] = "ؕؿقؒؽ؎،ؔ؉ف؍،؏؉ؐـؓ؎؉ؔؕؓؔ؉ـؿؓؒؐؓؑؔقؒؓؾ";
        array[816][0] = "foGaFQNI8idcsytkS8xhCA==";
        array[816][1] = "ق،ؓقؕؑؕؑ؉،ؽ؏؎؉ؐؾ؏ؾ؉ؔؕؑؾ؉ؕ،ؐقؔؿ،؏ؒؕؽؐ";
        array[817][0] = "PFvq2ioGYvIw8FsrQhsXKA==";
        array[817][1] = "ؓؾؕؐ؎ف؏ؓ؉ؒؽق؏؉ؐؔ؍ؾ؉ؔفؓؒ؉ؐـؑؐـ؍ؾقؐؿفؿ";
        array[818][0] = "WaeJwkxG9bPBN44bOElGBA==";
        array[818][1] = "قؓـؒ،ـؑؔ؉ؾؒؕـ؉ؐقؔـ؉ؔؓؓ؏؉ؕؔؔؽـؿؾ؎ـؿقـ";
        array[819][0] = "ALePWAxl0iSHjv46uEDSrw==";
        array[819][1] = "ؓؾؑـؔؾؕؔ؉ؾ؍ؑؿ؉ؐ؍؎،؉ؽ؏؍ؽ؉ؐؐؒؐؑؒ،ؒ؎ؕ؏؎";
        array[820][0] = "hKO+WINtXEnpeifZTXih3A==";
        array[820][1] = "؎ـ؏فف؍ؐؽ؉ؓ؏ؔ؎؉ؐؽ؏؍؉ؔ؍ؿؕ؉؍ؽـؕؾفؒق،ؔقؓ";
        array[821][0] = "WVJmStRrh2bWoHtTHutJCg==";
        array[821][1] = "ؔفؾـق،ؿق؉،ؒؕ،؉ؐ،ؓف؉ؔ،ــ؉قفؕقؕ،قؽ،؎ؒؽ";
        array[822][0] = "OVT6x4Y9n82TMykHb5vvkg==";
        array[822][1] = "ؿؓؿؾؔؕـؐ؉ؒ،،ف؉ؐفؽؾ؉ؾ؎؏ؑ؉ـؾفؔـق؍ؐ؎،ؐؓ";
        array[823][0] = "Sy1/WenKb3p15SlQz29kxQ==";
        array[823][1] = "ؒ؍ؾـؑ؏ؾؕ؉ؽفقؑ؉ؐقفؕ؉ؽؐؓؿ؉ؾ؍؍ف؎ؿ؍ؕؑؾفؐ";
        array[824][0] = "Gt2723h2m3RKWhHTHBPI8A==";
        array[824][1] = "؏؍ـؽؑ؏ـ؍؉ف؏ؑؕ؉ؐؑؔؓ؉ؔققؔ؉ؕ؎ؒؾقؕــؔؽؐؿ";
        array[825][0] = "3M+4icEhrXjtNo1yu+yllA==";
        array[825][1] = "قؒ،ؒؽف؍ق؉؏ؐؿؾ؉ؐؿـؽ؉ؾ،ؽؽ؉ؒف؍ؒؿؿؐ؏ؽؑ،ؑ";
        By1337̢̰̬̭̬͎̤̮̯͖̪̱͙͉͈̈́̐̅̏̐̏̆̊͑̽̌͗̆ͭ̈́̀͑̀͘͟͟͜͠ͅͅ();
    }

    private void By1337̢̰̬̭̬͎̤̮̯͖̪̱͙͉͈̈́̐̅̏̐̏̆̊͑̽̌͗̆ͭ̈́̀͑̀͘͟͟͜͠ͅͅ() {
        array[826][0] = "IvB4jEiH0xFcoOLQqGJSDg==";
        array[826][1] = "ؕؔؔـقؑفق؉ؔف،ؔ؉ؐ؏ؓؑ؉ؕ،ق،؉ؿ؍ـؔ؍،ـ؏ـؕ؍ؽ";
        array[827][0] = "CvZRs9/ZH04AenFAcLLFrg==";
        array[827][1] = "ؒؑ؎؍؎ؐؽؓ؉ؑؾؑؓ؉ؐؒؓق؉ؾؓ،،؉ؔؔ؍ؐؑؒف؏ؿ،،؍";
        array[828][0] = "1sLlw5C1TI2lsbHRc6EVsA==";
        array[828][1] = "ؒ؍ؾق،ؔؔؕ؉ؔؾؔق؉ؐ؍ؒؽ؉ؔـف؍؉ق؏ـؽؒؔؾؒؑفؑ،";
        array[829][0] = "708DefCy2GuBG/CGDBftSQ==";
        array[829][1] = "ؔؕ،ؽؾقؾؑ؉ؒؑؕؑ؉ؐ؍ؓق؉ؽؕـؕ؉؍ؑف؏ؕؑؕ؍ؿ،ؽؾ";
        array[830][0] = "B3A6t/Qy0tySF8pwrAftFA==";
        array[830][1] = "ؿؕف،ؑقؾؐ؉ؒفؽف؉ؐ؎ؔؓ؉ؔؿؐؽ؉ـؓ؎؎ق؍؏؏ؒؔ؎ؽ";
        array[831][0] = "oQZIJCzQNcuSlUgRr2s9fw==";
        array[831][1] = "ؐ؍ـؔؓؔ،؍؉؍ؽؓؽ؉ؐؐفؐ؉ؾؐ،ؑ؉؍ؽ؏ؾ،ؽؽــؽؓؔ";
        array[832][0] = "AkjUHaq2yoRY7ifceBa/XQ==";
        array[832][1] = "،ؾق؎ؓؽؐؕ؉ؽؒقؓ؉ؐ؎فق؉ؔ؏فؓ؉؏فؑ،ؽؾ؍؍ؔق؎ؕ";
        array[833][0] = "4Kpv8GwUJoqTvEiLy/OHQQ==";
        array[833][1] = "ؕؾ؏ؿؕؑف؍؉ؐـ؎ق؉ؐؔ؍ؑ؉ؾـ؍ف؉ؒ؏ؔف،ؿ؎ؔ؏ؒ؍؎";
        array[834][0] = "bAa/SIV0Mf2luuLjHHkFfw==";
        array[834][1] = "ؒ،ؾؔفؒـؿ؉ؾ؏ؾ؍؉ؐؽؔؓ؉ؽؿ،ؐ؉ـؿ،ؕـؾؔؒؾف؍ؑ";
        array[835][0] = "9sRwXbwZlTmCsPQYLmYcrQ==";
        array[835][1] = "ؑ؍ؾؒؑ؎؎ؽ؉ؾؽفؿ؉ؐق،ق؉ؕؓؓؑ؉ؾؑ،ؔؿؾؑؒؑ،ؽؑ";
        array[836][0] = "rGzEO/IrqJgN6aGRtEzUwg==";
        array[836][1] = "ؓ؍،ؔؾ؍ؓؾ؉،،؎ؕ؉ؐؒ؍؍؉ؾؐؕؑ؉،ـف؍قؕؽ؎ؓؾ؏؎";
        By1337̢̢̗̯̙̘̭͈͚̪̳̖̲ͤͧ͋̍̈͋͊͌́̋͒̿̾ͬ͌ͣͦ̄̈͑̓͘̕͘̕͞ͅ();
    }

    private void By1337̢̢̗̯̙̘̭͈͚̪̳̖̲ͤͧ͋̍̈͋͊͌́̋͒̿̾ͬ͌ͣͦ̄̈͑̓͘̕͘̕͞ͅ() {
        array[837][0] = "XlKksgP1zeuY7pv3eAPKVA==";
        array[837][1] = "ؽؽـؐؕفؑ،؉ؾ؏ؔؒ؉ؐففؿ؉ؔـؕؑ؉؍،ؑؕؔ،،ؑؐ؏ـؿ";
        array[838][0] = "G4UyU0pRyjyrF2gUOPW7jQ==";
        array[838][1] = "فؐؕؽؔـؒؿ؉ؓؾؓؒ؉ؐؒفـ؉ؾؾؾـ؉ؒقؾؐ؎ؒ،؎،ؓؕؕ";
        array[839][0] = "GlD/aeEpRJhoHNWwlw8pjQ==";
        array[839][1] = "ؒؾؿؐـقؽؿ؉؍ؔ؏ؐ؉ؐفـؽ؉ؔؔ،ؔ؉ــ،؍ؔفؒ،ؽؓ؎ؒ";
        array[840][0] = "giq5hzB8rYVc+bzDKLUjHQ==";
        array[840][1] = "ؑؒؕؒؒؐؒؐ؉ؒ؎ؑؑ؉ؐقؑؽ؉ؕ؍ؐ،؉ؓ؎ؔؿ،؍ؿ،ـؒؓؑ";
        array[841][0] = "PHhslN+tU44vhpfMFGfDMA==";
        array[841][1] = "،ؾؓؕ،قؕف؉ؔؔؽؔ؉ؐؒؾ؎؉ؽؿ؏ؑ؉ؽؑؽؽ؏ق؎ؐؐقؔؔ";
        array[842][0] = "Ruit4uSa9gCRp+u7CNpf5A==";
        array[842][1] = "ـؿ؍ؾ؎ؽؑؓ؉؎ؓؾؐ؉ؐ؍ؕق؉ؾؐؑق؉؎ؑق؍ؽفؾ؎ؿؐؕ؍";
        array[843][0] = "Co2ULqLBbOk69AgMcEpZcQ==";
        array[843][1] = "؏فؐؿؒؐ؏،؉ؿقف؏؉ؐؑفـ؉ؕ؏ف؎؉ؓ؏ؐؕ؍ؿؔفؓؿؾؕ";
        array[844][0] = "m8uJLbg8d2gnz2z7TpFInQ==";
        array[844][1] = "ف؏؍؏فؕ؎ؐ؉؍ؕ؎ؽ؉ؐؾؑؕ؉ؽؑؑق؉ؾؽؕؒ؍ؑ؎ؔؕؓ؍؍";
        array[845][0] = "u0tCD0mFlbHmU/5uoz1y8Q==";
        array[845][1] = "ـؐؐفؕفؑؕ؉ف؏،ؒ؉ؐ؍ؿؑ؉ؕؒ؍ؔ؉؎ؿؓؐؿؽؾ؍ففؔ،";
        array[846][0] = "nRYx4NwZ8u8pc86v3MUUIQ==";
        array[846][1] = "قـ؎ؔؔؽؕؔ؉فؓؑ؍؉ؐ؏ؕؒ؉ؔفؿ،؉؏ؾ؏ؕ؍ؔؐؑ؍،ؕؿ";
        array[847][0] = "CsrLKedq15USgEcEKL8Paw==";
        array[847][1] = "ؑ؏؎ؿؑؒ؎ؽ؉ؽؑفؓ؉ؐؓ،ف؉ؽؒؔ؎؉،؎ؽقؾ؎قؔ؍ـفؔ";
        By1337̵̴̸̼̠͓͎͚̝͕̭̻̙̰͇̘̭̗̫ͯͥͦͭͣ̾̃̃́͆͛̈ͪͯ̒̀̌͋̃̇̚();
    }

    private void By1337̵̴̸̼̠͓͎͚̝͕̭̻̙̰͇̘̭̗̫ͯͥͦͭͣ̾̃̃́͆͛̈ͪͯ̒̀̌͋̃̇̚() {
        array[848][0] = "xmrxI/VZy7qvgQO3sySlTw==";
        array[848][1] = "؎ؒؑ،؏ؾفؓ؉ؑؿؐ؎؉ؐـ؎ؿ؉ؾؽ،ؿ؉ؑـ؍ؾؐ،ؔؾ؏ؽؔ؎";
        array[849][0] = "/l5bwPhWN10THSA2f8HHXw==";
        array[849][1] = "؏ؿفؐ؏ؾؔ؍؉ؒؽـؒ؉ؐؓؔؓ؉ؾؾفؽ؉؎ؾؽؕؐؐؐ؍ؓؐؕؑ";
        array[850][0] = "7eP9wzAhwaZXwtdiAhE3dQ==";
        array[850][1] = "ؔ؎ـ؍ؔؑ؎ق؉؎،فؿ؉ؐؑ؎،؉ؾؐؓؓ؉؍؍ؔقؿؐؒفؿؿـق";
        array[851][0] = "hMd1NTS2GcMi36JimBRUZg==";
        array[851][1] = "ؐؿؽؔ؏ؽــ؉ؔ،قؕ؉ؐؿ،ؾ؉ؽؒفق؉ؕفؓؐؓؿؒفؾفؐؐ";
        array[852][0] = "LrzjD0U0uDOYlcAxbA6vHw==";
        array[852][1] = "ؑـؓؒ؎؏،ؽ؉ؾؕؔؒ؉ؐ؍؏؏؉ؔؕؐق؉ؑقؒ،ؿقفؐؔؐـؐ";
        array[853][0] = "BURcJ4XGRolERK/xi0SPpQ==";
        array[853][1] = "؍قؿ؍ؒ؎ؑؒ؉؍ؕ؍ؽ؉ؐؾؔؽ؉ؕـفؓ؉؍؎فؕؐؿؿ؏ـؓؿؕ";
        array[854][0] = "BpJ3GkfacsGpAN8jBJHqmA==";
        array[854][1] = "،،ؒؽؿـقؒ؉ف،ؓؔ؉ؐؾفؾ؉ؽ؏،؏؉،ؿؽ؍ؔؓؾ،؍ؽؑؿ";
        array[855][0] = "NGJ90sLiXwYzHc7zmYnqqQ==";
        array[855][1] = "ؒ؎ؑؽؾؽ؏ق؉ؑؑؿـ؉ؐق؎ق؉ؔؐـؕ؉ؐؑؓؓ،؎ؒفؔؽؿـ";
        array[856][0] = "h+mzroN+Xi66UfI9yOc8Hw==";
        array[856][1] = "فؿؕؐ؏ؽ؏ؕ؉؎ؔؐ؎؉ؐ؏؏ف؉ؔ،ؓؑ؉؏ؔؽ؎ؾقـ؍فؽق؏";
        array[857][0] = "B7JesHpReYX5hOVSrF+w+w==";
        array[857][1] = "ؒؕؑفؑؓؽ؍؉ؒـؐف؉ؐؐـؐ؉ؽ؎ؒ،؉ؔ؏قؽق،،ؒؑقؑؓ";
        array[858][0] = "Mc4ZWFyOwjJ2jHYa57cjZw==";
        array[858][1] = "ؾققؒف،،ؒ؉ؽؓؑؕ؉ؐؑؕؾ؉ؔ؏؍ؔ؉ف؎؏ـقؕقفف؍؏؍";
        By1337̷̷̵̡̨̱̖̼̼͓̯̟̼̺͇̯̳͕̜̦͌̿̊͊͑͐ͫ̅ͥ̓͊͆̇̒́͆͒̽̾͟();
    }

    private void By1337̷̷̵̡̨̱̖̼̼͓̯̟̼̺͇̯̳͕̜̦͌̿̊͊͑͐ͫ̅ͥ̓͊͆̇̒́͆͒̽̾͟() {
        array[859][0] = "WjBsERZdVB9gfxGaoX46YQ==";
        array[859][1] = "؍ؐؐؔ؍ؽفؕ؉ؽؕ؍ؿ؉ؐ،؍ق؉ؾقؕؾ؉ؓؔقؔـؾ؍ؽفؿؔؒ";
        array[860][0] = "HdB7xEV2a6NYulbCmbjeNg==";
        array[860][1] = "ؔؒ،ؿؒ،ف؍؉ؔؿؿف؉ؐفؿؕ؉ؾؐؔؿ؉ؓففؽؓفؿؓؒؔفف";
        array[861][0] = "U77ER76w4NRBevQzRlv09A==";
        array[861][1] = "،؍ـــ؍ؐؔ؉ـؽؾؾ؉ؐؾؿ؏؉ؕؔؐق؉فؿؒ،؍ؾؑ؍؎ؒؿـ";
        array[862][0] = "rCj+5d36Ck3N8C8F5OITKA==";
        array[862][1] = "ؒ؎ؾؽؐف؎،؉،ؽؓ؏؉ؐؽؾؾ؉ؕفؾؓ؉؍ؿؒفؕ،فؾ؏؎ـؽ";
        array[863][0] = "T1UOrTCJUOZnglVpPnjeLg==";
        array[863][1] = "ؒ؏ؿف؍،فـ؉ؑق؍ؔ؉ؐؓـ؍؉ؔؒ؏؍؉؏ـؾؒؐؔ؏ؓ،،ؿف";
        array[864][0] = "BTJwAsnDALVTrnp3JPuR8A==";
        array[864][1] = "ؓـؓؕؾؿؿؓ؉ـ؎ؽق؉ؐؐؿف؉ؔؓ؏ؐ؉ؽؾ،ققؕـــ؏ؽ؍";
        array[865][0] = "aPchQyv0VXeIap1nIEr8Pg==";
        array[865][1] = "ؾؕ؍؏ؾؐؽ،؉ـؾؾ،؉ؐؓؽق؉ؾؐؔؿ؉؎ؾؔ؏ؽ؎ؽ،؍ؐؐف";
        array[866][0] = "9Frb0XS9cx3W5xiY28p1yA==";
        array[866][1] = "ؐقؕ؏ؿفؿؒ؉؎ؓؾؕ؉ؐفؽؒ؉ؾفؕق؉ؾ؎ؿؔؑقف؏ؐ؍؎ف";
        array[867][0] = "6o6QeF2ZlW6enqn+KH3H3Q==";
        array[867][1] = "؎ؓ؎ؿ؎،،ؒ؉ؾؕقق؉ؐـ؏ؾ؉ؾؔقؓ؉ؿفؽؽؔف؎ؐـ؍ؿؐ";
        array[868][0] = "JIXZGOGjFxqc7SwzZo+uKA==";
        array[868][1] = "ؾ؍ؿؑؽ؎؎ق؉ؑ؎ؒؾ؉ؐفؕؓ؉ؽ؎،ـ؉ـؑؒؾق،ـ؎ؽؿؕف";
        array[869][0] = "BkswbGSa9ZbwuSxmsyZ/Kg==";
        array[869][1] = "ؓؐ،ؾؓؽ،ؿ؉؎ؒؓ؎؉ؐؾؒؒ؉ؕؓؒ؍؉؍؏ؑؑؑؓؐ؍؏قؽؕ";
        By1337̴̫̜͎̭ͪ̋ͧ́̓̿͏̴̸̡̧͇̠͉͇̹̪̫͖͎͓̰̬̉̌̎̏ͫͩ̔̉̿̎̚͜();
    }

    private void By1337̴̫̜͎̭ͪ̋ͧ́̓̿͏̴̸̡̧͇̠͉͇̹̪̫͖͎͓̰̬̉̌̎̏ͫͩ̔̉̿̎̚͜() {
        array[870][0] = "Rb3f+mLXc9kfx/cS1BV5XQ==";
        array[870][1] = "ؕؽ؏،،ؔؔ؎؉ف؏ؐ؏؉ؐ،ؑؑ؉ؕف؍ؽ؉ؔ؍ؓؕؓؾ؎،ؑفؔؔ";
        array[871][0] = "hUOY0ZRbjB+JxW0U6KwJDQ==";
        array[871][1] = "قق؎ؑقـؒؿ؉ؒ؏فؓ؉ؐؽ؍؍؉ؽق،ق؉ؐف؏؎ؽـؐؑـ؎ؾؔ";
        array[872][0] = "JdFqMYz9A7SvZHSrDFAZzA==";
        array[872][1] = "فـؐؐ،ؒؾؑ؉ؐؓؿؒ؉ؐؿؒق؉ؔؽؐؐ؉؏؏ؕ؍ؒؿ؏ؑ؍فؑ،";
        array[873][0] = "NA/6QmM7qhihduvD3Oh+2Q==";
        array[873][1] = "ـؔؓؑ؏ؾقـ؉ؒؓؐؾ؉ؐؔؓق؉ؾؾؒؓ؉ؐؿؽ؍ق؍ـؾـؕ؍ؑ";
        array[874][0] = "hKB69kua4c9U36ZhZOO52w==";
        array[874][1] = "ؒ،ؐ؏ؓؕفؾ؉ؾفــ؉ؐـؾ،؉ؕـؾؒ؉؎؏ؿ؏؍فـ،قؾ؎ؿ";
        array[875][0] = "ZBjv63hsXItwDJpybLdaSQ==";
        array[875][1] = "ؐؒـف؍ؕؓؔ؉ؕؐؽؾ؉ؐؾ،ؽ؉ؕؑ؎ؿ؉ـؔـؽؔؔؿ؍؏قؕؔ";
        array[876][0] = "4XHbQi765xnXJUatbIK0qA==";
        array[876][1] = "ق؎ؿ؎ؑؿؐؔ؉،ؿؑؒ؉ؐقفؓ؉ؾؕ؏؎؉ـؑؒقفؾ؍ؽؒؿؽؕ";
        array[877][0] = "TAMEmEe3HJ+xWXHhrLyMsA==";
        array[877][1] = "،ؒؕؽ؏ؕؐ،؉ؾؕؑؿ؉ؐ؎؏ؒ؉ؕؕ؏؎؉ؑ،ؑؾؑؕؽؓؔؒؽؿ";
        array[878][0] = "bay5+qRg9bUHCdVskJYsfQ==";
        array[878][1] = "ؒؑؐؓؒؓؿؿ؉فؾـؕ؉ؐ؍فؑ؉ؾؔفؓ؉؎ؐؔفؿـؽؐ؎ؒق؎";
        array[879][0] = "uJvOzV8fpZ9iIhPEaPqNww==";
        array[879][1] = "؎ق؏ؾؔؽؿ؎؉ؑ،ؿؿ؉ؐؾ،؍؉ؔ؍ؔ،؉ؿؕؕ،ؾؐؕؾ؍ؽفؿ";
        array[880][0] = "zYYMqeF6GRi7lmvydx6JUg==";
        array[880][1] = "ؽ،ؒؽؿؐؓؽ؉ؑؔـؑ؉ؐق؎؎؉ؾ؎ؔؒ؉ؒؑؔـؐؽقؾؔؕـق";
        By1337̨̼͈̗̼͌̆̎̄ͩͫ̔̕͟͏̴̛̯̣͈̼͉̰̹͍ͭ̔̑ͫͮͩͨ͛̌̓ͩ͛͡͡ͅ();
    }

    private void By1337̨̼͈̗̼͌̆̎̄ͩͫ̔̕͟͏̴̛̯̣͈̼͉̰̹͍ͭ̔̑ͫͮͩͨ͛̌̓ͩ͛͡͡ͅ() {
        array[881][0] = "vIAvuSodQUCgO6vPB/yeOQ==";
        array[881][1] = "؏؎ؒ؎ؑؓؽؾ؉ؽؔ؎؍؉ؐؓؑؓ؉ؽؒ؍،؉؎،؎ؒؓ؍ؕؓففؐؔ";
        array[882][0] = "AUWiqaTeOinbmJhHBl+OGQ==";
        array[882][1] = "ؽ؏ؐؾ؏ؔ؍ؑ؉؍ـف،؉ؐؑؐؑ؉ؽؿؿؽ؉ؑ؎ؒ؏ـؕ؍ؕؐ؎؏ؑ";
        array[883][0] = "lS6RKJ8JDNm3oKrWeRKtsA==";
        array[883][1] = "ف؍ؒفؔؐفق؉ؒؿقؒ؉ؐق؎؎؉ؔؔـؐ؉ؐؽؑ؎ؿ؎ؒ؏ـؓؾؔ";
        array[884][0] = "/BANJ1Wiy5KYjq1a1J7tmw==";
        array[884][1] = "ؑؕؽؔ،ؕ؍ؒ؉ؒؒؒؽ؉ؐ؏ؔؿ؉ؕؽؔؿ؉ؕؐ؏ؕفؐ؎ؽؽؿؑ؎";
        array[885][0] = "EZCHzR7XJSVJqp+cGH64mQ==";
        array[885][1] = "ؑؔفؽؿ،ـؕ؉ـؒـ؍؉ؐؔؕؾ؉ؽؐؾق؉ـؓؔ؏ف،؍ؕؽؓؽ؍";
        array[886][0] = "VGXyIoPuA+MfdRjeghkOcQ==";
        array[886][1] = "؏؍؏؎؍ؒـ،؉ؿؿؓـ؉ؐؑؒؽ؉ؽؓ؍ؒ؉ف؎،ؕؒق؍؏؎ؒـؔ";
        array[887][0] = "sE6xg0yx5TcPa2WG7vcMWw==";
        array[887][1] = "ؒ؍ـؔؽ؎ؽ؎؉ؔ؏ؾؓ؉ؐ؍؏ؒ؉ؕؐؽؓ؉ؽـفؐـؾ؎ؔؕ؎؏ؾ";
        array[888][0] = "DRyl0DxN2gOIAuKfY1PGfQ==";
        array[888][1] = "ؕفـ؍ؔؿؓؑ؉ؾؑ؎ؓ؉ؐـؒؑ؉ؕـؿؕ؉ؑؒؒ؍ؔقؿؔــؾ؏";
        array[889][0] = "pAftByxM8vMKts7GTFfPAg==";
        array[889][1] = "؎ؔ؎ؔؑ؏ؑؓ؉فـفؑ؉ؐـؑؽ؉ؕؾؐق؉قؐ؍ؑ،فؓؔؒؔؿ؏";
        array[890][0] = "CDm3Wb7+NAGf3HQijc1FRA==";
        array[890][1] = "ـؑؔؽؑؽ؍ؾ؉؏ؒؾ؍؉ؐف؎ؕ؉ؔؑفؽ؉ؑؿؕؓ؏ؽؕؐؐؔق،";
        array[891][0] = "dy/RwSgP7UBoNC/nmvT/KA==";
        array[891][1] = "ؽ؍ؓ؎؏ففؔ؉؍ؒؽؓ؉ؐؿؑؐ؉ؾـؿؐ؉ؔؿ؎؎ؽؕؽؐ،ـؿ،";
        By1337̷̴̧̢̻̖̬̲͖̬̖͙̙̖̦͕͈͔̔͑͐ͨ̄̾̀̅ͯ͒͆̍̓̈́ͪͥ̍̚͢͞͞ͅ();
    }

    private void By1337̷̴̧̢̻̖̬̲͖̬̖͙̙̖̦͕͈͔̔͑͐ͨ̄̾̀̅ͯ͒͆̍̓̈́ͪͥ̍̚͢͞͞ͅ() {
        array[892][0] = "GWg0YGfVmzki6OfNE5Ii7A==";
        array[892][1] = "ؕ،ؾؒ،؎ؿق؉ؕؿؓؽ؉ؐقؕؔ؉ؾؑؔف؉ؓؽ؍قؓؽؑقؑؾـ؎";
        array[893][0] = "NQCD7bTFb8I5UwR5jYYGKQ==";
        array[893][1] = "ؐؿ؍ؿ؎ؿؓؑ؉ؑؓؒؕ؉ؐ؏ؿؕ؉ؔؿ؎ؐ؉ـؒؽؒ،؎ؓ؎؍قؓؒ";
        array[894][0] = "JxGUnTFOkT8JCHf6Y5u/zA==";
        array[894][1] = "ؕؓقؾؐؕـ؍؉ؐ؎ؕق؉ؐؑـؔ؉ؔ؎ـؕ؉ؐ؎ف،ــ؍ؓؔؿفـ";
        array[895][0] = "lnqk6QVRU2ZkXQ+ewFUwuA==";
        array[895][1] = "؎ؑـؔفؕؕؐ؉ؾ؍؏،؉ؐؾؕؿ؉ؾؓ؏ؿ؉ؿفؒؔ؍ـؽؑؾقؐ؍";
        array[896][0] = "mtr74kTI/S57Md3wbIIBMg==";
        array[896][1] = "؍قؕؽ؍؎ؓؑ؉ـقؐؒ؉ؐؿفؓ؉ؔ؍ؔ؏؉ؕؕققؽؐؽ،؎؍ف؎";
        array[897][0] = "wL3we39eP3gIL2YqPAjQ/A==";
        array[897][1] = "ؕ،ؿـؽؾؿـ؉ؕؐ،ؓ؉ؐؔؐؒ؉ؕـؕؓ؉ؿؓ،،ؑ؏ؒؾؕؕؿؾ";
        array[898][0] = "EKsC0eoStba60gMhI5ltuQ==";
        array[898][1] = "ـؕ،؏قؓ؏ق؉؎ؐؐؓ؉ؐؿؕؔ؉ؕ؏فؕ؉؍ؽؕؾؿؔفؓؿؓؕق";
        array[899][0] = "QbQ9taSK+4CAfF5todHBEA==";
        array[899][1] = "ففؾؐفؑؿـ؉ؐ؎ؾـ؉ؐؔ؏؎؉ؾؑ؍؏؉ـ؏ؾ،ؽ؎؏ؒف؏ؽؿ";
        array[900][0] = "wNomOdFH818aZS8QGLW5Pg==";
        array[900][1] = "ؕ؏ؕؐؕؒؐؒ؉ؾؔؿؓ؉ؐقؽؓ؉ؾ؍؏ؓ؉ؑؐـ؎ؽـؿ؍ؑؐؒؾ";
        array[901][0] = "i182zDtyH0JK7VWIpplwjw==";
        array[901][1] = "ؐؒؒ،ؒـؒؓ؉؏ؒؔق؉ؐؽؽ،؉ؾؽؓ،؉ؒؽ؍ؔف،؏ؕؽ؍قق";
        array[902][0] = "v5EdbUCG2/qbavLAG2tczA==";
        array[902][1] = "ؔ؏ؔقفؕ؍ؓ؉ؐ؍ؿؒ؉ؐ؎ؓؾ؉ؕؑ؏؍؉ؐقؑؐ؏؏ؽؾفؐقق";
        By1337̶̢̨͔̬̫̟̬̦̲͎̯̤̺͖͉̣̲͕̠̗̠̰͓͆͒̎̀̊̿ͣͦ̂̃̅́ͦ̚͝͞();
    }

    private void By1337̶̢̨͔̬̫̟̬̦̲͎̯̤̺͖͉̣̲͕̠̗̠̰͓͆͒̎̀̊̿ͣͦ̂̃̅́ͦ̚͝͞() {
        array[903][0] = "Okk7MwALF6NelRdGQZUCVQ==";
        array[903][1] = "؍ـق؍ؑؔـ؎؉فؕفؐ؉ؐؕ؍ؿ؉ؽؑؒؕ؉ف؍ـؿؿقفؓؒفؑ؍";
        array[904][0] = "yYTflPOOgnt9Fr7I78+apw==";
        array[904][1] = "ؒقؕؿؕ؏ـؓ؉،ؐقؐ؉ؐ؎ؾؔ؉ؔقؿ؏؉؎ؐ؏ؓؾؐؐؑؒ؏ؿؑ";
        array[905][0] = "P5RouavIV5s2UzalsDvTPA==";
        array[905][1] = "فؔؽؕؿؿؐ؏؉فؒؽف؉ؐ،ؕؾ؉ؾؕؿؽ؉؎فقؕـ؎ؔ،فؕؐؒ";
        array[906][0] = "EjNQS+s5paQvozF1xk5T2g==";
        array[906][1] = "ؒؑؽؓؿق،؎؉ق،،ؒ؉ؐ،ؐ؎؉ؕ،ؽق؉قفؕ؎؏ؓؓؿؾؒ؏،";
        array[907][0] = "JqmJ6nVO+R1GVjrdc7hOkA==";
        array[907][1] = "؍ؒؑ؍؏،ؔؾ؉ؾؾؒؑ؉ؐؿ،ؓ؉ؽ،ؐؕ؉؍ؾـ،،؏قفؾ؎ؔؕ";
        array[908][0] = "K708e1/LUxBfdQS7SF5GxQ==";
        array[908][1] = "ؐ؏ؒ؎،ؿؒؐ؉ؑ؍؏،؉ؐؽؕ؏؉ؽؿ؎ق؉ؓؒؓ؎؏ؿؑؔؾ؎ؒؽ";
        array[909][0] = "Xe+wiQPo0R6IOSzhdMm/oQ==";
        array[909][1] = "؏ؔؐقؓؽـ؎؉ؓفؑؐ؉ؐف؍؍؉ؾـؔؐ؉ؿ،ؽـؾؾؐؕ؎ؽؑ؍";
        array[910][0] = "14PQXZy81J3rpJpBPj+fqQ==";
        array[910][1] = "ؕـؿؔؑؒؕؽ؉؍ـ؎ؑ؉ؐؽؓ؍؉ؽؑ؍ؑ؉ق؏؍ـؕــؿؑ؍ؑؾ";
        array[911][0] = "G/W1F9U/A2wmyAmI6LMRfw==";
        array[911][1] = "ـؑقفقؓ،ؑ؉ؓؓؑؒ؉ؐؒـؐ؉ؽؑقـ؉،ؿؾ؍قؑؒؔؒؑ؍ق";
        array[912][0] = "Aj/Q5z4ytGR5tci8ujiqJA==";
        array[912][1] = "ؒؕف،؎ؓؐؔ؉،؎ؒ؎؉ؐقؐؽ؉ؔؐؔؐ؉ؿ؎ؕؿؐؿؽؒؕؑفـ";
        array[913][0] = "Gv8tl3H7N7mOJS6TQdjnvw==";
        array[913][1] = "ؽؕؐؿ،ف؎ؑ؉ؐؔؿـ؉ؐؓؓؽ؉ؕؓؓؐ؉؏؎فـؑ؎؎ؑؐ؍ف؏";
        By1337̬̳̖̤́ͭ̕̚͏̧͖͈͉͓͙͙͚̺̘͉͇̤̬͓̞͚̔̿ͪ̄ͭͧ͑̈́̄ͩ͌̊̚͠();
    }

    private void By1337̬̳̖̤́ͭ̕̚͏̧͖͈͉͓͙͙͚̺̘͉͇̤̬͓̞͚̔̿ͪ̄ͭͧ͑̈́̄ͩ͌̊̚͠() {
        array[914][0] = "k1bNT42HKbKiWhbU8njdig==";
        array[914][1] = "ؾؑ؏ؓؑؕؐؿ؉ـقؿؾ؉ؐؾـؽ؉ؔؒـ؏؉ؔؽؒؾؿقؾؐ،فؓف";
        array[915][0] = "YkNbsz/CHBEO1npgSvy0CA==";
        array[915][1] = "ؾـؓفؐؾ؎ؔ؉ؿق؏ؕ؉ؐ؍ؓف؉ؕؕ؎ق؉فـؿ؏ؒؓ؍ؽؔؓــ";
        array[916][0] = "1epvJbj82aQRi0EVViQmSA==";
        array[916][1] = "ؒق؏؍ؕؓفـ؉ؾ،ؒؑ؉ؐفؕؑ؉ؽفؿق؉؏فؓـؑ؍ؔؓؑؐفق";
        array[917][0] = "c8Uxzvd9mTMPT4iUP2AUmw==";
        array[917][1] = "ؾ،ؽؐؑؒؔ،؉؏قؽ؍؉ؐؽؕ؍؉ؾؐؾؿ؉ؒؔؽؾقؓؒؾ؏،؏؎";
        array[918][0] = "NwaOWGH2F8I3rgRbYqD2rw==";
        array[918][1] = "ـؒق؎ـ،؍ؿ؉ؕفؐ؎؉ؐ؏فؐ؉ؔ؏؏؏؉ؒـؿؓؾـ؍ؕؽـؾ؏";
        array[919][0] = "3fXQZwh7jsrZ/N1Ohv4vNA==";
        array[919][1] = "ؔـ،؍قـ،ؿ؉ـ؍ؔؾ؉ؐؑؔؓ؉ؕفؔؿ؉ـفؾ؎ؕفؒ،ؔؽفق";
        array[920][0] = "kMzQ9xsmCHpi3479Spumcg==";
        array[920][1] = "ؾقؒ؍ـفؑق؉ؕف؍؏؉ؐ؍ؑؓ؉ؕؒ؏ؕ؉ؒقـؾؑؽؑؑؕؓؔؿ";
        array[921][0] = "1X7lDejDRCLKfSGGCCNukw==";
        array[921][1] = "ؽؾؑق؎ؾؑؾ؉؏؎،ؾ؉ؐ؎ؓؿ؉ؾؿؐف؉ؑق؎فؽؽ؎ؔ،ؿــ";
        array[922][0] = "mvKtMbVgD9w5gplbCC8h9g==";
        array[922][1] = "ففقؿؓؑقؒ؉؍ؿفؐ؉ؐ؍ؿؽ؉ؔؒؒؓ؉ؾ؍؏قؽؿؾؕ؎فؾؑ";
        array[923][0] = "67kWju85Hrn4vXX4bq+RCg==";
        array[923][1] = "ؽ؏ؐ،؏قؾف؉ـ؏قؓ؉ؐؿؽؕ؉ؽؾق؎؉ؔؑقؒقــ؏،ففؒ";
        array[924][0] = "uRv09RWUaiVEd1DUunyYGg==";
        array[924][1] = "ؐؓ،ؑؔ،ؑؽ؉فـؔؾ؉ؐؕؒ؎؉ؕؕؒ؍؉ؿقف؏ـؓ؍ق؍ق،ـ";
        By1337̵̵̶̢̡̛̛̰̟̟̟̘̮̣̩̗̽̑͑ͥ̑͆͑̀ͤ͌̈́̽͛̾͊̒̒͛͘͘͟͡͡͡();
    }

    private void By1337̵̵̶̢̡̛̛̰̟̟̟̘̮̣̩̗̽̑͑ͥ̑͆͑̀ͤ͌̈́̽͛̾͊̒̒͛͘͘͟͡͡͡() {
        array[925][0] = "DejsVk4CLCNsOVijerBclQ==";
        array[925][1] = "،ؓؽؐ؍ـؒف؉ؒؔ؍ؾ؉ؐؐؾ؏؉ؾؐؐف؉ؿؐؐؓؑفؕؕقؽؓ،";
        array[926][0] = "gD6Vuj/TdF/7M+rW0uwNHA==";
        array[926][1] = "ؓقؾ؏،؎،ف؉ف؍ؓؑ؉ؐؿـق؉ؔؕـؑ؉ؐؒـق،ؕفؽؕؕؒؽ";
        array[927][0] = "NmGwtcFW1SDKvgCl74JP+g==";
        array[927][1] = "ؽؐقؐ؎؎ؿف؉ؓ؎ؽؔ؉ؐ؎ؕ،؉ؾؓ؏ؕ؉ؒؔؾؾ؎ؾ،فق،ؓؑ";
        array[928][0] = "dPM0+9m3Jl5Uzm+uTt9DuA==";
        array[928][1] = "؍؏ؾؐفؽؑق؉،؏ؽ؍؉ؐقؑق؉ؾؾفـ؉ؿؒؾؿ؏؎،ؽـؽؓؾ";
        array[929][0] = "1GCLg0NkWw/x5pwE7vmFOQ==";
        array[929][1] = "ؒ؎ؿـؿؐؿف؉ؔ؍ؾف؉ؐؕؾؔ؉ؔ؏،ؽ؉ؕؕ؍ؓؕقؿؕؓ؍ؒؿ";
        array[930][0] = "HGLhKAuvts6obCZwuFD7yA==";
        array[930][1] = "ؐ؍ؽؓؒ؍؎؍؉؍ـؑؕ؉ؐ؍ـؕ؉ؔ؎؏ـ؉؏؍؏ؽؑؿؐؽ؏ؓؐؾ";
        array[931][0] = "L1xFp0leLZ2f2Bm/TEz2Lg==";
        array[931][1] = "ؑؽفؾؕ؏ؾؔ؉ق؎فؓ؉ؐؕف؍؉ؕؿؑؔ؉ؒؕؓؑؽ،؏قـ،،ؾ";
        array[932][0] = "FFmibDdh+b7LkeIhpW4ddg==";
        array[932][1] = "؏ـؔؽق؏ؑق؉ف؍؏ؐ؉ؐؔ؏ـ؉ؽؽؐؑ؉ؾؔؓ؍؏ؐؐؕ؏؏ؔ،";
        array[933][0] = "X5nuNduVfxcSoiziW5e+rw==";
        array[933][1] = "ـفؾق،،ؒؑ؉ؽؿفق؉ؐؕؽ؏؉ؾؔ؍ف؉ؽؐؓ؎ؿؾؓؒؐؒؑؓ";
        array[934][0] = "xw+ypa25Y2dcVcc/fjhixA==";
        array[934][1] = "ؐؕؐؔ،؎ؑؿ؉؏ؕ؍ف؉ؐقؒؽ؉ؔ،ؿ؍؉ؿ؎ؑؓفقؑؔؽؿ،؍";
        array[935][0] = "kIX5s3SCTnxkgbpOwjq5DQ==";
        array[935][1] = "ؐؓؕـؔؐ؍ؾ؉ق؎؏ؑ؉ؐؕؿـ؉ؽـق،؉ـؑؿؽؽؿؓ؏ؽق،؍";
        By1337̴̴̨̛̳͙͕̫͈̩̖̖̰͍͉̤͚̖̜͒̈́́ͩ̀͋̆̅ͭ͆̄ͮ̌ͮ̐͂ͭ͐̚͝ͅ();
    }

    private void By1337̴̴̨̛̳͙͕̫͈̩̖̖̰͍͉̤͚̖̜͒̈́́ͩ̀͋̆̅ͭ͆̄ͮ̌ͮ̐͂ͭ͐̚͝ͅ() {
        array[936][0] = "eJtpQqbKkf8cDRODn9ptNQ==";
        array[936][1] = "ؔؑؾ،؍فؕؑ؉ؾؐؓؑ؉ؐؓفؔ؉ؔـ،ؔ؉فؿؽق؍ؓؒ؎فؕ؏ف";
        array[937][0] = "l6dP1xsEWPRd23PIMw2LBA==";
        array[937][1] = "؏؎ـؐ؎ـؓ؍؉ؿ،ؔـ؉ؐؐؿؐ؉ؾؔ؏ؒ؉ؿ،ؔ؍؎،ؾ؍ؑؿؿف";
        array[938][0] = "VZDHp138ATNyLc4mpJTIyQ==";
        array[938][1] = "؏فؐؔـؾ؏ؑ؉ؿفـف؉ؐـؽؐ؉ؔؿ؎ق؉ؿف؎ؽ؎؏ؑؐؐــؕ";
        array[939][0] = "SY5BtCy0H/As47REaRUV+g==";
        array[939][1] = "ـؒؔؿؓؽؔق؉،ؽؕؑ؉ؐ؎ؾ؎؉ؾؐؓ؍؉ؕ،ؔفؓفؑؕ؏ف؍؍";
        array[940][0] = "vPG1FCAmAYqr0tn6WduVmg==";
        array[940][1] = "قف؎ؿفؑقؑ؉ؓؾؽف؉ؐؓؔؽ؉ؾؔقؓ؉؎ؾؿؐ؎؏ؒ؍فؓؕ؎";
        array[941][0] = "VgMZNhLf55sMalF/IUSFJg==";
        array[941][1] = "ؔؓؓؐ،،،؎؉ؾ؏فؑ؉ؐؑ؎ؑ؉ؔؐ؎ؓ؉ؓـــ؏؍ؿؾف؎؎ؕ";
        array[942][0] = "nUN7A/6V/BIl8zsdMFi3pw==";
        array[942][1] = "؏ؑقؓ؏ؽؾف؉ؿؔؐـ؉ؐ؍ؕؓ؉ؕؿؐؿ؉فؕ؏؏ؒف؍؏ؾؐؾؽ";
        array[943][0] = "5MRjUNs5rgBiSP+yVWE/jQ==";
        array[943][1] = "ؾؓفؿفؾفؿ؉ؕؐؔؕ؉ؐؑفؓ؉ؾ؏،ؔ؉،؏ؾ؏ؐ؎ؿؒؑؿؔـ";
        array[944][0] = "XbevvxYGmqFII76EGNrkcg==";
        array[944][1] = "ؿ؎ـؕ؍،قؽ؉قؑؑؾ؉ؐـؔؕ؉ؾ؎؍ف؉قؿؑفؑ؏؎ق؏؎قؐ";
        array[945][0] = "lu/ZTpS8ioMoRmrSiwMEYA==";
        array[945][1] = "قؓؓؔؽؔؑؾ؉؏ـؒق؉ؐ؏ؾ؏؉ؔؑ؍،؉،ؾؿـؕؾ؏؍؍ؔ؏ؐ";
        array[946][0] = "ojMJJ1XU4g9A2EMJ8BGkTA==";
        array[946][1] = "؍؏ؽؽؾؒ،ؒ؉ؾؾؔؐ؉ؐ؎؍ف؉ؾؕ؍ؑ؉ؽقفؐفؕؾؐؔؕفؕ";
        By1337̷̲͕̆̌́͂͘͏̶̢̡̨̢̖̟̼͙̗̼̰̊̊͛̎̈̏́ͪ̉̇̓̽͗̄̍ͣͬ́̕();
    }

    private void By1337̷̲͕̆̌́͂͘͏̶̢̡̨̢̖̟̼͙̗̼̰̊̊͛̎̈̏́ͪ̉̇̓̽͗̄̍ͣͬ́̕() {
        array[947][0] = "rmc+gOjY0nZOWEpy/O+8+g==";
        array[947][1] = "ـؾ؎؍ـق؏،؉ؐ؎؍ؓ؉ؐؓ،،؉ؕؕقؾ؉ؿؿؑؽؿؿ،ـؕؐؽؕ";
        array[948][0] = "CDcieEqcEIEXQY1wEsLQqg==";
        array[948][1] = "قؒؑــؾ؍ؽ؉ؾؽ،؍؉ؐؾفؔ؉ؕؕفؔ؉قؑفؑ؏ؓ؏ؾؕقق؎";
        array[949][0] = "/CMwpLqL9wduhYHcrFLZlw==";
        array[949][1] = "فؽق؎ؿؐؕؓ؉ؽ؎؍ف؉ؐؒ؍؍؉ؽؓ؏ؓ؉ؒؾـؔ؎؎قؑؔ؎ؒؓ";
        array[950][0] = "2gyck+5uFJxcy0/IbX/cpA==";
        array[950][1] = "؎ؔؐف؍؍؎؏؉ف؎ـف؉ؐ،ؾؿ؉ؽ،ؔؿ؉ففقؐ؎قؕؕؾقؔ؏";
        array[951][0] = "XtmKfEplqUWKE+JlQA2sTg==";
        array[951][1] = "ؐؒ؏ؓؑؕؒؾ؉ؾؓ؏؍؉ؐ،ؓق؉ؕؾقؽ؉ـؐقؿؐؐؾقف؏ؔؾ";
        array[952][0] = "e2W2gQ6wpdVoCQrxXaeErw==";
        array[952][1] = "ؔـؓ،ؕؾـؔ؉؍ؔؒؔ؉ؐؔؓؐ؉ؕؽؾؿ؉ؐ؍ؓقؽؿ،ف؍ـؔف";
        array[953][0] = "qSZoWm5quXqyTgrCyIEP2g==";
        array[953][1] = "ؾفؿؑؒؿؐق؉ؐؔـؒ؉ؐؑؾؿ؉ؾؑ؍ؿ؉قؑؔ؏قؑؿقؐؒؐؐ";
        array[954][0] = "LlinLNP6tNeEqv9yHv3GRg==";
        array[954][1] = "؍ـ؏فؒـؾـ؉ؽ؍ؾؓ؉ؐؕ؍ـ؉ؾؒق؍؉ؾؑ؎ؿؾ؎ؑؔ؎ؽؒف";
        array[955][0] = "AjgIF/+t07byGL+lO5iZSg==";
        array[955][1] = "؍قؑؿـؕؿؕ؉ؐؿؒـ؉ؐ،،ؔ؉ؾؕؓؒ؉،ؑقؿ،ؕـؐؑؐؓؐ";
        array[956][0] = "a63/OdG6B2DH1Hx/iGyMgQ==";
        array[956][1] = "ؿؐؔ؍ؐؽ؏ـ؉؍ؐقؒ؉ؐؿقـ؉ؕؑؓؿ؉ؑؿؓ؍؍ؕ؏ـ؍؏ؽؕ";
        array[957][0] = "73HTBj/gA9PDtqN5qC8gpA==";
        array[957][1] = "ؕـؔؑ،ؾقؔ؉ؒؒؐؐ؉ؐؓ؎ف؉ؕؑقـ؉ؒ؎ؾؾؔؕقؒؑ؎ؒؔ";
        By1337̵̷̴̡̡̹͉̙̬͈̙͔̖̗̪̜̜̫̩̪̱͕͚ͨ̽ͩ̏̄̾̌̈́̏̂̈̇͑͑͢͝͡();
    }

    private void By1337̵̷̴̡̡̹͉̙̬͈̙͔̖̗̪̜̜̫̩̪̱͕͚ͨ̽ͩ̏̄̾̌̈́̏̂̈̇͑͑͢͝͡() {
        array[958][0] = "qQTjaXmFEKV3ibDtyxMBHw==";
        array[958][1] = "ـؿؐؕقفؓؾ؉ؾـ،؏؉ؐ؍ؓؒ؉ؔؾؔؾ؉ف؏ؿؕ؏فؓؔؽؐـؑ";
        array[959][0] = "2hsnYtbK0rsRT8O/oniOJA==";
        array[959][1] = "؍ؑف؍ؽؑف،؉ؐ؎ؕ؏؉ؐقؐؽ؉ؕؕؑ،؉،ؔـؕؾؾؔؕؕـ،؎";
        array[960][0] = "0W014R8rOL57kJpztzpQBQ==";
        array[960][1] = "ؽ،ؔؑؑؓؑؽ؉قـ،ؐ؉ؐ؎؏؍؉ؕؿفؓ؉؍ؐؐفؿؔؕ؏ف؍؎ؐ";
        array[961][0] = "TKRprDbNdHgXE5agH5piQg==";
        array[961][1] = "ؿؕؒؕ،؍ؓـ؉؎ؽ؏ؑ؉ؐ؏ؑ؍؉ؔؽؓؔ؉ـ؏قؑؾؓؐ،ؽققؓ";
        array[962][0] = "uc3HDEZ7CEGLBKtibAvl3A==";
        array[962][1] = "قؓؐؾقفؿؕ؉قؕؾ،؉ؐف؏؏؉ؕفؐؓ؉ؔـ،فؔفؑ؍؍ؒؓؐ";
        array[963][0] = "XNkDvwNAa3uqR3jUDgyeHA==";
        array[963][1] = "ؾؓ؎ؽؑؒق؍؉ؓفؓؑ؉ؐؒؕؓ؉ؽؐ؍ف؉؏ؒ؎ؾؓؔؒؓـقؽؐ";
        array[964][0] = "v4wbqYUAFgQGqz7z7T9AFQ==";
        array[964][1] = "ؽ؎ؽؿؔؑؽؒ؉ؿؕؽـ؉ؐف؏،؉ؔفقؔ؉ؽؔ؎ـفؽؕؔ؎فؓؕ";
        array[965][0] = "jNf3ShVQMu7UfzYIyw+GOA==";
        array[965][1] = "ؒ؏ؐـؑ؎فؾ؉ؽ،ؽؓ؉ؐؓؑؑ؉ؾـ؎؍؉ؕؽ؍ـؓـؾؑؓقؐؾ";
        array[966][0] = "R1pJ3n50tmYXIMTukjPdpA==";
        array[966][1] = "قؿؿ؏ؕؿؽ؎؉ـؔ؏ؒ؉ؐق؎ؓ؉ؾؕؐؒ؉؍ؽ،،ؿؒقؑؓـؿؑ";
        array[967][0] = "kgToq/SCj1+qDmco0O4Rkw==";
        array[967][1] = "ؒفؒـ؎ؑ؏ؾ؉ـؑ؏ؽ؉ؐؒؑؕ؉ؾؽ؏ؒ؉ؾقؾـؾـؐؒـؽ،ؑ";
        array[968][0] = "6oLDEGImtwFvGKEh/FNsDQ==";
        array[968][1] = "ققؕؑؒؐؾق؉ؕؒؿؔ؉ؐؕـؔ؉ؕ؏ؕـ؉؍ؽ؎ؑ،قـ؍؎ؾؕ؎";
        By1337̴̳̤͚̯̮̟̖͉͔̣̲̱ͯͥͮͪ́͆̆̓̏̐̆̌̏̐ͨͭ̈́̄ͪ̈ͮ̅̏̄̓͒́();
    }

    private void By1337̴̳̤͚̯̮̟̖͉͔̣̲̱ͯͥͮͪ́͆̆̓̏̐̆̌̏̐ͨͭ̈́̄ͪ̈ͮ̅̏̄̓͒́() {
        array[969][0] = "lr72Wd9d33qcJgkTiAYUlQ==";
        array[969][1] = "فؕؔؿـؓ؍ؓ؉ؑؑؾف؉ؐؿؒ،؉ؔؓق،؉ق؏ؑ؏،ؔؔؐؑؽ،ؾ";
        array[970][0] = "PQuJg19lYAwGmW5BLhkYnw==";
        array[970][1] = "ؓ،ؓؔ،ؕؔؔ؉ؓؑؑؑ؉ؐؒؿؿ؉ؔ؍ؓؓ؉ؒؐ،،ؐؒؕ؍ؐ؍ؒ؎";
        array[971][0] = "ZWEsGrksAYCktkLofHWXqA==";
        array[971][1] = "قؐ؎ؐؒؑؔؿ؉ــف؏؉ؐ؏ؓؕ؉ؾؐ؍ؑ؉ؐؔ؏؎؏ـفؒؽ؍ق؎";
        array[972][0] = "DZ6htVIH6zIH0LfOgZXYGQ==";
        array[972][1] = "؏،ـققـفؑ؉،ؿ؏،؉ؐ؍ؾ؍؉ؔ؎ؒؽ؉؍،فؔؿقؾؓؐؾ؏ؔ";
        array[973][0] = "YuRHMFDq+U/KWzOg8/Raqg==";
        array[973][1] = "ؕؔؾؾ؎فؔؑ؉؎ؕؽق؉ؐؒ؏؏؉ؔؽؕؐ؉؏ؔؐـ،،ؽؔ،ـفق";
        array[974][0] = "9ghyTpaM423O3itNYPHDLA==";
        array[974][1] = "ؑـؽؾؓفـق؉ؽؽؾ؏؉ؐؐؔؕ؉ؽؾؽ؎؉؎ؒؓـؾ؍؎ؿؓـؿ؎";
        array[975][0] = "LgaWG8xW7U1UdjA19bk2eg==";
        array[975][1] = "قؒؓؒؓف؏ؒ؉ؓؐؿف؉ؐففؑ؉ؾق،ف؉،ؐؒؾؐـؑ؏ؑؔ؍ؕ";
        array[976][0] = "ZYgPIyI8Vn6XOus7c3SJsg==";
        array[976][1] = "؏ؕقـ؍ؕفق؉ؾؕؿؓ؉ؐقـؔ؉ؕؽ؏ؔ؉،ؽؑؿؿ،ؕؐقؐؒ؍";
        array[977][0] = "LznVVS1m39AMrUvpkcQhzA==";
        array[977][1] = "ؓ؏ؾؓؑ،ـؕ؉فففؓ؉ؐ؎؎ؒ؉ؔـ؏ؾ؉ؕؐؽؕؐ؎ؐـ؍فؐ،";
        array[978][0] = "UgfUtDkJFYZhFB1RM3v57g==";
        array[978][1] = "؍ؓؓ؏ـفؽ؏؉قؾؿؕ؉ؐؓ؏ؽ؉ؽؑؾؒ؉ؕؿ؏ؿقؽ؍؏؎ؐؕق";
        array[979][0] = "VqHt0UGPxHMpyrBPnl/ATA==";
        array[979][1] = "ؽ؎ؓـؒؒؑؐ؉،،ـف؉ؐفؓؿ؉ؾؔق؏؉ؑؾؔ؎؏فؑفـ؎ؑؔ";
        By1337̢͙̗͈̮̪̮̙͈̻̫̞͔̰̫̣͉̰̣͔̱̅ͮͦ̂ͤ̔ͯ̅̿͗̑͌̋ͪ͛͟͞͝ͅ();
    }

    private void By1337̢͙̗͈̮̪̮̙͈̻̫̞͔̰̫̣͉̰̣͔̱̅ͮͦ̂ͤ̔ͯ̅̿͗̑͌̋ͪ͛͟͞͝ͅ() {
        array[980][0] = "xG4ykGFhGAntI6DCyw+r1A==";
        array[980][1] = "؎؏ف؎ـ؏؍ف؉ؕؓؓؐ؉ؐؓفؿ؉ؕ؎ؾؿ؉ؐؾؿق؏؍ؐؑؐؽؑؔ";
        array[981][0] = "m/wuef8oKQJUSowoVZVeiw==";
        array[981][1] = "ؑؽؕؓ؏،ؓؑ؉ؕؔؾ؍؉ؐقؒؐ؉ؾ؍ؽ،؉ؒؔؓ؏؎؎؍ؐؓ،ـف";
        array[982][0] = "A+GMjV9RvUYT/WrNOg/YNQ==";
        array[982][1] = "،ؐؔق،ؕؒؕ؉؎ؽؕؓ؉ؐـ؎ؒ؉ؾ؎ؓف؉؎ؐ؎ؾؑؔؔؽ؎ؿـؾ";
        array[983][0] = "i03e/a6PP6qzcXLpXxoirg==";
        array[983][1] = "؏ؿقؿؽ؎ف؍؉قؑؑؿ؉ؐؔؓؽ؉ؾؿؒؾ؉ؐ؏؏؏؏؎ـؐؽؾفؿ";
        array[984][0] = "j/8mpGkoLwpprk2k07UzUQ==";
        array[984][1] = "ؑققؓؒ؏ـق؉ؓ؏،ؑ؉ؐف؎؍؉ؾ؎ف؎؉فؓؾؓؐؐؽؑ؎؍قؓ";
        array[985][0] = "gDiL5cNZ9Uw5XBtYGzICLA==";
        array[985][1] = "،ؕؒؓ؍؍؍ؒ؉فؽق،؉ؐؿؓف؉ؔؒفؾ؉؏قؑقؾفـؿؿؔ؎ـ";
        array[986][0] = "9nYzIqeZSg2NNP/mov/sfg==";
        array[986][1] = "،ؕؔؾؾؔؒؿ؉ؓفؿ؎؉ؐؑ؎ؽ؉ؔـفؕ؉ؑؕ؏ؓ،ؐـؐؔؔؑؕ";
        array[987][0] = "tKpp8y7ceRmXprnoAK2KEA==";
        array[987][1] = "ؽقؓؕ،ؕؒؒ؉ؒؿ؍ف؉ؐققؕ؉ؔفؿؔ؉ؑؿ؎قؽؑ؏،ؾؿؐق";
        array[988][0] = "4kDZTtYrDix0eKRYd9GRJA==";
        array[988][1] = "ؾف؏ؕؔ؏ؽ؏؉ؔؔؑؿ؉ؐؿ،ف؉ؔؑؽؒ؉قفففؕفؓؕؒؕ؎؏";
        array[989][0] = "aVacYy0YY20z1cbu+eT9Cw==";
        array[989][1] = "ؐؓ؎ؐـ،ؒؒ؉ؾؓؾؐ؉ؐؿـق؉ؾؾفؿ؉؎ؕـقؿؒؒـؿ؎ؒؾ";
        array[990][0] = "i/rLg7VZ6MktP3s2T05ZGQ==";
        array[990][1] = "ؽ؎ؽفؽؽؽؽ؉؍ؒؓف؉ؐؾؒؽ؉ؕقؑ،؉ؕـؾ؍ؾ،ؐؽؽـفؓ";
        By1337̵̷̨̧̛̱̱̦̹̻̱̯̟̥͔̻͍̘̬͎̱̤̓̈́ͦ̇̎̆̏͗̽̈́͒̅̐͟͡͞͝ͅ();
    }

    private void By1337̵̷̨̧̛̱̱̦̹̻̱̯̟̥͔̻͍̘̬͎̱̤̓̈́ͦ̇̎̆̏͗̽̈́͒̅̐͟͡͞͝ͅ() {
        array[991][0] = "+f5qEl4ckkMLmtrF2Bn08A==";
        array[991][1] = "ؔؒؿ؎؎ؿؓؔ؉ـؓ،ؾ؉ؐ؎ؒف؉ؽ؏،ق؉؏ؿؾؽ؏؍ؽ؍ؒؾ؏ؔ";
        array[992][0] = "TxRpLWvwV2Bqp4eSaH/NAQ==";
        array[992][1] = "؎ؾؽؽ،ؕقق؉ـؾؾؓ؉ؐـؽؕ؉ؕؒ،؎؉؏ؿ؎،ف؎ؒؽؐ،ؔق";
        array[993][0] = "xB6JDHRvPKhzAvmU49VcKQ==";
        array[993][1] = "؎قؓؐؽ؏ـ؍؉ؒفؾؕ؉ؐؒؾؕ؉ؕفؕ؏؉؏ؓؕؕ،ؓؾ،ــؽ؎";
        array[994][0] = "48LqTQO4gSB1U3x25rpDLw==";
        array[994][1] = "ؕؿؓؿقؾؾف؉ؑؑؽـ؉ؐؾؓق؉ؽؒ؏؎؉ؑؒـفؑ؍ؽ؍؍ؒؒؽ";
        array[995][0] = "gv4Ze+3v4qUf99UvMrMfXQ==";
        array[995][1] = "،ؑؑؽؾؿ،؏؉؎ؐقؔ؉ؐ؎؎ؒ؉ؔ؎ق،؉ؑؓؔؾؑؔؐ؎ـقؒ؎";
        array[996][0] = "JLKGMgNlJ88k03Iwg3N1Xg==";
        array[996][1] = "؏ؾ،؎؍ؑ؎ؿ؉ؑؒ؏ؿ؉ؐؑ؏ؐ؉ؾؕ،ؾ؉ؒؕ؏ؿؐ؍؎ق،؍ؑـ";
        array[997][0] = "YJ64yMLIbl11iaNmY10VNQ==";
        array[997][1] = "ؿفؔ؍؍ؐ؎ؒ؉ـؐـ؍؉ؐ؏ؾؾ؉ؾؑؔق؉فؕفـؽؽؐـ؍ؒفؑ";
        array[998][0] = "5iFAiWG6SFxpKPzGOmxmfw==";
        array[998][1] = "ؑؑ؍فـ؍ؕ؍؉ؾؒ؍ؑ؉ؐؿؿؽ؉ؔؿؓ؏؉؎؎ؾؑؾؽؐؿؕؽؐ؏";
        array[999][0] = "jUT7ktFr1KbCOetgWJ2w4w==";
        array[999][1] = "ؑ؎ؐقق؏ؔؑ؉ق؍ؽؕ؉ؐؓؿـ؉ؽؔ،ؑ؉ؕؾ؍ؕؑؑ؎ؒؒؒؾ؎";
        array[1000][0] = "Zzn1FMIfKfi7FoOoWOlLkw==";
        array[1000][1] = "؏ــؑقؽف،؉ؒؑ،ؑ؉ؐؕؔؕ؉ؾؒؑؓ؉فؿ،ؕؔؓف؎؍ؐـؿ";
        array[1001][0] = "Z2K5uY3UAEiqmUG06EC0Ow==";
        array[1001][1] = "قؔ،ؾؓ؎ؐ،؉ؒ،؎ف؉ؐ؏ؕ؏؉ؔؾؕؽ؉؏؍ؐ؍ؔفؔ؏ؽ،؎؎";
        By1337̷̨̡͇̻̩͔̜̣̘͎̬̫̭̙͐͌́͊̋͌ͫ͑̀̉̅́ͦ̒̓͑̋͆̕̕͘͘͟͡͝();
    }

    private void By1337̷̨̡͇̻̩͔̜̣̘͎̬̫̭̙͐͌́͊̋͌ͫ͑̀̉̅́ͦ̒̓͑̋͆̕̕͘͘͟͡͝() {
        array[1002][0] = "MG1Bp0JxQmzycqXvAdLxNA==";
        array[1002][1] = "،ؾف،ؽ؏ق؎؉؎ؽ؎،؉ؐؔؐؔ؉ؽؽ؎؎؉ؿفؿف؎ؿؾؔ،ؓؐـ";
        array[1003][0] = "DBksK79e2oEIpGSkzHnvCA==";
        array[1003][1] = "؍ؽؒؔ؏،؎ـ؉؏ؐفؿ؉ؐـ؏ؽ؉ؾؒؔ؏؉ق؏،ؒؓ؏؎ـؿ؍ؒؒ";
        array[1004][0] = "jd9QCkhijJ2Kpq+nWxsRbQ==";
        array[1004][1] = "ؑؾؐؾـفؐؑ؉ؓ؏ؐ،؉ؐؾؒ،؉ؾؐؔؽ؉ؑؐؔؒ؏ؓفؐؑقؒؾ";
        array[1005][0] = "WIEpDzuJlyGMK1suIQ975w==";
        array[1005][1] = "ؒ،ؽؽؕ؎ؒ؏؉ؑؒ؏ف؉ؐؐؑؓ؉ؕؓ؍ؒ؉ؕؐؔؑ؏فؐؽؒ،ؓـ";
        array[1006][0] = "k74p+rLmGd2EDt9sgtMqEw==";
        array[1006][1] = "،ؽف؍ؾقؓؓ؉فقؽؾ؉ؐ؏ـؓ؉ؽؽؽؓ؉ؑؐ؏ؾؒ؏ؽؽ؏ؒ؍ؑ";
        array[1007][0] = "fFpeANd8FLx24YJi2sArkw==";
        array[1007][1] = "ـؒؒ؎ـؑ؍ؒ؉،ـ؎،؉ؐف؍ؿ؉ؾ؎؎؎؉ؓؓ؏ؔ؎ؽ؏،ـؓؽؾ";
        array[1008][0] = "WNxl/+lMlLtMgFvAmeo4aw==";
        array[1008][1] = "ؑ،ؐ؍،ؐفؔ؉ؑق؍ؑ؉ؐ؍ف؏؉ؔ،ؑؾ؉ؕؑق،ؓ؍،ؽفؽؐ؍";
        array[1009][0] = "+q6HbQ+VITdAQy4yejBpcQ==";
        array[1009][1] = "ؒ؏ؽف؎فـؑ؉،ـؐؕ؉ؐقـ؎؉ؕ،ؒ،؉ؑؽؔفـف؏ؕؕـفق";
        array[1010][0] = "DgpyKM5NXhWOl1KtWyZZsQ==";
        array[1010][1] = "؍ؐؾ؍ؑؔؽؑ؉؏؎ؕؒ؉ؐق؏ؐ؉ؾؑؔؓ؉ؔؒؑؽق؍؏ؕؿؾـؒ";
        array[1011][0] = "MNSU3/u57d4qO9wlGgqCUA==";
        array[1011][1] = "ؿؿؾؓؾؐؿؓ؉ؕؓؿـ؉ؐفؽق؉ؔ؎ـق؉؍ـؾؿقـفؐ؏ؾؓؔ";
        array[1012][0] = "bi0wgk8bo9VA/OKpwLHBUw==";
        array[1012][1] = "ؐ؎ؓؾ؏ـ،ؑ؉ؿ؏ؑؕ؉ؐ؍ؾؒ؉ؕؔؽق؉ؐقـؽ؍ؕؽـ؏ؔفؕ";
        By1337̶̴͈̬̮̤͉̤͕͔̲̫͎͍̹̣̝̠̹̆̀́̄͛̃͛ͯ͛̑ͦ̊̈́̀̓̂̔ͤ̇͘͠();
    }

    private void By1337̶̴͈̬̮̤͉̤͕͔̲̫͎͍̹̣̝̠̹̆̀́̄͛̃͛ͯ͛̑ͦ̊̈́̀̓̂̔ͤ̇͘͠() {
        array[1013][0] = "n7ZdSTn2i654Zk12J8PlzQ==";
        array[1013][1] = "ق؍؍،ؔؔؿؕ؉ؒـؓ؎؉ؐؑؐ؏؉ؕ؎،ؒ؉ؾ؎ؔؽؕ؏ف،ؔؒؕق";
        array[1014][0] = "K9+IqNCjSnyT+e/QV/S6wg==";
        array[1014][1] = "فؔؕؒؓ؎؎ؾ؉؍؎ؽؓ؉ؐـــ؉ؕ؏ؒؐ؉قـ؏ؾققؽؽؽؓـؒ";
        array[1015][0] = "Vq/OmZT8kE2vEaKj+OllRw==";
        array[1015][1] = "فؐؿؿ؏؎ؾؑ؉ؕفؒؑ؉ؐ؍ؒؕ؉ؔؒؔؒ؉؏ؾ؍ؒؑؓقؾ؎؍ؕـ";
        array[1016][0] = "qnCukUGRDMFwIDwn0VQbiQ==";
        array[1016][1] = "ؿ؏ؓؾ؎؎؎؍؉فؐ؍،؉ؐؔؐ؎؉ؾؓؿ؎؉قفؑؕؕؽؑؔؐؽفؔ";
        array[1017][0] = "rAm7uJE3cLLw7Jf8+JCDlA==";
        array[1017][1] = "ؾ،ؐؽ،،ؾؿ؉ؾؔ؍ؽ؉ؐ؍؏ق؉ؾ؏ق؍؉؎ؕ؍ؐـؕؽؿق،ؕؑ";
        array[1018][0] = "H3s7W5dhfNoo50VXuFnOCg==";
        array[1018][1] = "ؐؕؑؔ؎ؐؽؕ؉فؽؕ،؉ؐؔـؐ؉ؕ؏ؾؾ؉ؒـق؍ف؍؎؏ؔ؎؎ؾ";
        array[1019][0] = "+XV9XFkqozguDljKy0813g==";
        array[1019][1] = "فؔ،؍فؑؕ؏؉ؿ؏؍؍؉ؐف؍ؽ؉ؽ؍؏ؽ؉،ف؏ؐف؍،،،ؒؽؔ";
        array[1020][0] = "msAaA6OnImeZ5FZlWDtz5w==";
        array[1020][1] = "ؑؑؒفـؔؿ؎؉ؿؒـؽ؉ؐؒؿق؉ؕؑؐؿ؉ؒؿؾ،ؾؓؒؾ،ؔؿ،";
        array[1021][0] = "Ac13R904KHot5D4dvX8KXQ==";
        array[1021][1] = "ؽؑقـ؎ـؿ،؉ؓقؔؾ؉ؐقؑف؉ؕؽؑـ؉ؿؓ؎ؑؕؔؽؐؐؒ؎؍";
        array[1022][0] = "LMJH09GZdGXjq32gjr4j5A==";
        array[1022][1] = "؏قـ،؍ؾ؎ـ؉ؑؕ؎ؾ؉ؐؕؕـ؉ؽؒؽؒ؉ؕفؐؒؔؓ؏ؽ؍،؏ؒ";
        array[1023][0] = "racoqHkQt+CNQKtzu6nuSw==";
        array[1023][1] = "ؓؿؕ؏ؓؕؽؐ؉ق؎ؐف؉ؐؒؐؽ؉ؽؒـؓ؉؎ؕؾ؎؏ؾؑ؏ؿؓؑق";
        By1337̴̵̢̲̖͎͉̟̠̩̦̲̈͆̿̊̓̄ͭ͐̉ͭ̓͐̄̍ͫ̊ͥ͊͂̕̚̚̕͘͜͡͝ͅ();
    }

    private void By1337̴̵̢̲̖͎͉̟̠̩̦̲̈͆̿̊̓̄ͭ͐̉ͭ̓͐̄̍ͫ̊ͥ͊͂̕̚̚̕͘͜͡͝ͅ() {
        array[1024][0] = "SacSeadekUyML4RzRyttaQ==";
        array[1024][1] = "ؿفقـؓــ؍؉ؿؓ؎ؑ؉ؐؕؑ؎؉ؽؑؒؿ؉؎ؓؕؽؔؿؐؐؾؔؑ؍";
        array[1025][0] = "qCngFfTL0Z5D5RP+XGakIg==";
        array[1025][1] = "ؒؐؔ،؍ؑؓؕ؉ؔؾؕؐ؉ؐ،فف؉ؾـ؎ؔ؉؏ؕؑ؍؏ققؒؔؔ؏؍";
        array[1026][0] = "G4InGMC5cjBAK5K/aDK8YQ==";
        array[1026][1] = "،ؐؾؔؿؓ؍ؾ؉فؽ؎ق؉ؐؿؕ،؉ؾؑقؾ؉ؐؑؿ؎؎ؐؕؓ،ؽ؏ؕ";
        array[1027][0] = "GN/HfdFUdcLcHNYhNUvnvA==";
        array[1027][1] = "ؒؓؕـ؎؍قق؉قؒ؏،؉ؐؾفؒ؉ؾ،ؐؕ؉ؿؿ؏؍ؿؿ؍ؕؔ؎ؓؓ";
        array[1028][0] = "Mixrv6OwxtU4oVEThhyDfQ==";
        array[1028][1] = "ؔقؑ،ؽؒؽ؎؉؎فؐؕ؉ؐـف؎؉ؾؿؿؿ؉ؐؿؽؑؾؑــؒؒؒؾ";
        array[1029][0] = "1oFZqVqffHgglbsnoLjzxA==";
        array[1029][1] = "ـ؍؏ؿـؿفؕ؉ؐؔؽؾ؉ؐ؎فف؉ؽؾـ،؉ؿؔ،ؒؽ؏ؐ؎ؿفؾؐ";
        array[1030][0] = "5uyn1r3qu5DUMGK7/HiZZA==";
        array[1030][1] = "؍ؒ؏ؑ؎ؔـؽ؉ؿـؒ؏؉ؐؐقؽ؉ؾ؎ؕؿ؉فؽؽف؎فـؒؽؽؕق";
        array[1031][0] = "+UYx51HY+Bhvxc9SDKIZ5A==";
        array[1031][1] = "ــ؍؍ؿؔؽ،؉؏ؐؕـ؉ؐؔ،ؕ؉ؔؐؽـ؉ؐقف؍ؕق؍قؓ،ؾؐ";
        array[1032][0] = "uy/RW1jBqpX9CZ+Ie9U1ig==";
        array[1032][1] = "ؿؾؽقـؒؾؾ؉قؐفؿ؉ؐ؎ؓؑ؉ؽؽؾؓ؉ؑؔؾؒفق؍؎،ـؑؽ";
        array[1033][0] = "ChY8Jjz0ocDbZdhXVIHqmQ==";
        array[1033][1] = "؏،ؐقؐؽــ؉ؑ؏قـ؉ؐؕق،؉ؕقؔ،؉فؾ؍ؕؾ؍ؽؓؽ؏ؑؕ";
        array[1034][0] = "cawl6XgbXnZrH077v1Zlzw==";
        array[1034][1] = "ؔ؎ـؑقؔ؎،؉ـؿق؎؉ؐفقؿ؉ؕ؍ؒؑ؉؎؍ؕؿـفؓؔؔ؎؎؎";
        By1337̷̶̧̨̨̲̬̖̞̬̩͚̙̝̺͔̲̤̩̹̩̂̉̉ͮ̒ͦͬ̉ͩͩ̓̇̓̀̀̌ͫ̊͡();
    }

    private void By1337̷̶̧̨̨̲̬̖̞̬̩͚̙̝̺͔̲̤̩̹̩̂̉̉ͮ̒ͦͬ̉ͩͩ̓̇̓̀̀̌ͫ̊͡() {
        array[1035][0] = "ieppvAtvfzpXl/FmuCSHUw==";
        array[1035][1] = "؎؍؏ـ؏ؓؔؕ؉قؔؔؓ؉ؐؓؓؽ؉ؕؽ؏ؽ؉ؽؑ،ؽؔ،ؑفؾفؕ؏";
        array[1036][0] = "BrjkTX1Sc9E3gbXuD7IHQQ==";
        array[1036][1] = "ـفقؒؑفـ؏؉ؑؕؓؐ؉ؐؓ؎ؓ؉ؔؾؿؾ؉؍ـؐؐ؎ؔؿؾــؿؐ";
        array[1037][0] = "CId9XKoc8yw8jsNpwg4Kbw==";
        array[1037][1] = "ؾف؎ؽؕ؍ؑق؉ؕ؎؏ؾ؉ؐؿؑؔ؉ؾ؍؏ؒ؉ؒؿقؽؐـؓؔؿؕؽؾ";
        array[1038][0] = "sUP5hVax9LzRJlaLqOJOFA==";
        array[1038][1] = "؍فؔؑف،،ـ؉ؾ؍ؽؒ؉ؐ؍ؓؔ؉ؽؕؽؕ؉ؽفؒؕ؍ؔؑ؍ؔ؎ؿ؎";
        array[1039][0] = "SKhW/oC59Sm023iQDDdoRw==";
        array[1039][1] = "؎؏ؓؿ؏ؔ،؎؉ؽ؍ـؑ؉ؐؕفؒ؉ؽؐؔق؉ؒؐقؑـ؎ـ؎؎ؑؾؾ";
        array[1040][0] = "7N4UGG8UhiabFejoOpcMtg==";
        array[1040][1] = "ؒ؎ؔق؍ؓفؾ؉،فؑؽ؉ؐ؍ؕؑ؉ؾفؐؔ؉ؓؔؓ،فؑـ؎ؑؔؐف";
        array[1041][0] = "zYSA4Wmf1usXtezOUsjCPQ==";
        array[1041][1] = "فؐ؍؍ف؏قق؉ؓـؒق؉ؐؔؓؑ؉ؔؑؒف؉؏،ؕـ؎ؔ؍ؑ؎ؿؕؐ";
        array[1042][0] = "Rm4zmi0OwIHMBPpMVyeucg==";
        array[1042][1] = "ـؽؕؒ؏،ؑؔ؉قفقؓ؉ؐؓقؾ؉ؔؔقف؉ؑؾفـؽ،،ؕقف؏ؾ";
        array[1043][0] = "ZdfImYC+Y06BG6nFsJCNcw==";
        array[1043][1] = "،قؑ؍ؔـؽـ؉؏؍ؿؐ؉ؐ؎ؿؑ؉ؕق،؏؉؏ق؏؍ؒؐ؍ؑؾؽ؏ق";
        array[1044][0] = "I/45SuttZqmqJNWlUrPdXQ==";
        array[1044][1] = "ؓؒقؐؾـؿؕ؉ؔـؑؓ؉ؐ؍؍؎؉ؾؒـف؉ؒؿقؓؿف،ؾؾؓقؐ";
        array[1045][0] = "SgtlCbceRM2CdIJUHLOcvg==";
        array[1045][1] = "؏ـؒ؎ؔؔؕـ؉ؓف؎ؕ؉ؐؐؾؔ؉ؔؕؔؽ؉ؔؓؿفؐـؽؽ،؎ؾؾ";
        By1337̛̈ͯ̈͏̨͎̬̰̪͎̠͔̠̼̫̙̲̂ͨ̂̑ͣ̈̽̍ͥ̂ͧͬͥͭ̎̋̋̊̆͛̀̕();
    }

    private void By1337̛̈ͯ̈͏̨͎̬̰̪͎̠͔̠̼̫̙̲̂ͨ̂̑ͣ̈̽̍ͥ̂ͧͬͥͭ̎̋̋̊̆͛̀̕() {
        array[1046][0] = "cPrfVCOpU9XhIBRcaiC0mA==";
        array[1046][1] = "ؕؑ؎ؐقؒ؎ؿ؉فؐ؍ف؉ؐفـؓ؉ؾؐ؎ؑ؉ـ؍،ـف،ؓ،ؽؿؑف";
        array[1047][0] = "30/x85fJwsScariGX7Q9sA==";
        array[1047][1] = "قؐف؏ؾ؏فـ؉؎؍ؾؿ؉ؐ؍؍؍؉ؔؒؔؒ؉ؽ؏ؑؕـ،ؒ؎ؑؐؑ،";
        array[1048][0] = "Mql8sPm8MOWwd4qBALTgfA==";
        array[1048][1] = "ق؍فؿـقؒـ؉ؿ؍ؕ؏؉ؐق؎ؒ؉ؾقؓؔ؉قؑؐ؏ؒؽف،ؔؽ؎ؽ";
        array[1049][0] = "Rq8karFAZC8zkQYK+mR6WQ==";
        array[1049][1] = "ؕففـؒؓؔؒ؉ؽؓ؎ؽ؉ؐـؓؔ؉ؾ؍ؽؒ؉،؍،؏ؾؓؑؒؑؐ؏،";
        array[1050][0] = "6bjCqedutWb0NqnZd/Cc+A==";
        array[1050][1] = "ؑؓؿؓؕ،ؽؒ؉ؓف؎ؿ؉ؐؕؑؾ؉ؽؾـ؎؉،ق،؍؏؏ؑ؏؍ؽ،ؒ";
        array[1051][0] = "22BLfTnOrd11nNPLlDPL6A==";
        array[1051][1] = "ؒفؔؓ؏؍ؿؐ؉؏،ؓؔ؉ؐؕؔؾ؉ؽؿؒؐ؉ؑؒـفؒؕؽؐ،قؿـ";
        array[1052][0] = "jV6cdWybSREp/wzupejAaA==";
        array[1052][1] = "؎ؐؑؾؿؒ؍ؿ؉ؕ؏ؕ؍؉ؐؓؓؔ؉ؕؾؑف؉ؿق؏ؕؔؿؿـؾؑؐؑ";
        array[1053][0] = "gp45I1rWUl+HAVtPG5R8Ew==";
        array[1053][1] = "ؒ؍فؓؐؾ،ؐ؉ـؐ؏،؉ؐؔق؍؉ؕ؍ؕؒ؉ؽ،ف،،قؑؔ؍ؿؐ؍";
        array[1054][0] = "QKD3rJFCLuVDypV7HHxSfA==";
        array[1054][1] = "قق؎،ـف؎ؑ؉قؐــ؉ؐؔ،؏؉ؔؐفؿ؉ؾقـؾففؔؕؿؔؒـ";
        array[1055][0] = "xDgkDVZIg/ouzjFJTgCzAQ==";
        array[1055][1] = "ؒـؐؾ؍ؑفؽ؉؍ؐؓ؎؉ؐؿؑـ؉ؾؔؔف؉ؐؔ؎فؐؽؔقؿؔؾ؍";
        array[1056][0] = "jCFbRTrlQhqqW6hAbUJpKw==";
        array[1056][1] = "ؒ؎،ؕقفؾف؉؍ؑفـ؉ؐؑ؍ؒ؉ؽؐؓؾ؉ؔ،،ـؐؐــفؒ؍ف";
        By1337̧̨̛̝͇̖̝̳̲̘̼̳͍̟͈͕̊ͫͫ͑͒̀̊͊͗̑͑͐̀̊͆̎̑ͣ̂ͣ͢͞͞ͅ();
    }

    private void By1337̧̨̛̝͇̖̝̳̲̘̼̳͍̟͈͕̊ͫͫ͑͒̀̊͊͗̑͑͐̀̊͆̎̑ͣ̂ͣ͢͞͞ͅ() {
        array[1057][0] = "NkrX+zafcGhmkAQxx6iVtA==";
        array[1057][1] = "ؔـؽؾقؒؕؐ؉ؾ؏؎؍؉ؐؾؑـ؉ؕؿؐؒ؉ؿف؎،ؕفؓ؏،ؓؿؔ";
        array[1058][0] = "2tKtZvOOZE7IU3j2sBC8bQ==";
        array[1058][1] = "ؾؿق؍ؑؕؽؓ؉؎قـ،؉ؐؒ؍ؔ؉ؕؓقؾ؉قـؐقؕؑؾؓؔؔؾؿ";
        array[1059][0] = "eUjcDEJNKxFEffbBmW1H8A==";
        array[1059][1] = "؍؍ؐفقؒؓ؎؉ؽ؏ـق؉ؐؿؔؓ؉ؔفؓـ؉ؓفؽؽؓـؔؕؒ؏؏؍";
        array[1060][0] = "XPJg7f9P0tSiWD+E4DJrPg==";
        array[1060][1] = "ؕ؏،قؔـقؕ؉ـ؏ؑ؍؉ؐ؎ؿؒ؉ؕ؏ؑؔ؉ؿ،ؕؑؓ،ؽففقؕ؏";
        array[1061][0] = "ZhdqiYyYKeAjmj/38qH3/A==";
        array[1061][1] = "قفؽؒ؏ؕؐؕ؉؍؍؍ؕ؉ؐؕؐؾ؉ؔؑؓؐ؉فؽؔؔؕؾ،ؓؐـؽ؍";
        array[1062][0] = "j1dkx3S9ZY4bHlS8hM6czw==";
        array[1062][1] = "؎؍ؾؐؓ؍ؒؐ؉ؒؾ؎ؕ؉ؐ؎ؔـ؉ؾؾؾؑ؉ققؓؐ؎ـؾؐ؍ؿؿؽ";
        array[1063][0] = "l8FsWPbyqHvnoihrrbZrZg==";
        array[1063][1] = "ؑففــؑ؍ؽ؉؏ؒ؍؏؉ؐ،؏؎؉ؽؓ؏ؐ؉ؿ؍ققؔفؔؐؿؑؒؑ";
        array[1064][0] = "IzbFXdunXsQ440GeoDcONw==";
        array[1064][1] = "ف،ؐؾقؕؓؔ؉فؓؾؔ؉ؐفؽؿ؉ؽؾ؏ؔ؉ـقؓؒؽؓؒؓؑقؿؔ";
        array[1065][0] = "ZyNV5A8Y4XnudFXikBcVJQ==";
        array[1065][1] = "؎ؒ؍،؏؏،ـ؉؎،ؐ،؉ؐؕ؏ؿ؉ؾؔؐؒ؉فقؓؑ؎ـؔؐف؏؏ـ";
        array[1066][0] = "57DJz8xczLIrcZPN7m9xRA==";
        array[1066][1] = "ؿؾؾؾؔ؏ؔؾ؉ؑؾؕؿ؉ؐؑؽ؏؉ؕقؿ،؉؏ؿ؎ق؏ؿف؍ققؿـ";
        array[1067][0] = "gCZjQIAYbKIfeG8wQvEeCw==";
        array[1067][1] = "ؿؓؐ؍قؾ،ؿ؉ـقؕ؍؉ؐق؍؍؉ؽ؏ؔؐ؉ؔـؒؒؾؐقؾؒ؏ؽؕ";
        By1337̷̵̡̨͈͈̹͇̝͓̳̪̮̺͖̯̝̗̣ͥ́ͪͬ̑̓̃̏̏ͣͨ͂͗ͨ͗̑̒ͩ͛̚͟();
    }

    private void By1337̷̵̡̨͈͈̹͇̝͓̳̪̮̺͖̯̝̗̣ͥ́ͪͬ̑̓̃̏̏ͣͨ͂͗ͨ͗̑̒ͩ͛̚͟() {
        array[1068][0] = "5Dg1DwOl3XBUp2UUJnmCXw==";
        array[1068][1] = "ؕق،ؑؔ؍قـ؉ؕ،؎ف؉ؐؽؑ؎؉ؕـؐؒ؉؏،فؿؔؑ؍،؏؍ـ؏";
        array[1069][0] = "8FYnD7uqimPgrZyOzOm+Ug==";
        array[1069][1] = "قفؽفؐ،؏ؔ؉ؾؒؐؐ؉ؐ،؍،؉ؽ؍ؕف؉ـؔؾق؍ؽؑفؿؿؔ،";
        array[1070][0] = "c86FHi/IBwqFlKS2wFdtow==";
        array[1070][1] = "ؾؓؔؽؓؓؽؕ؉ؓقؓف؉ؐـؓؿ؉ؽ؎ؽؕ؉ؾقفقؽ؎؎؎ؔؾؑؐ";
        array[1071][0] = "OAUjhjFdfQL8FlOiqWyx8Q==";
        array[1071][1] = "،ؕؓفؐؕؿؒ؉ؔؽقق؉ؐؽقـ؉ؾ؎ق؍؉ؔؕؕؕ،؏ؐ؍ؒؕؐؐ";
        array[1072][0] = "lXJTBILBVRpAfS0hH2xJ7A==";
        array[1072][1] = "فؿ؏قؕقفؓ؉؎ؿؒؾ؉ؐ؍؍ف؉ؽ،ؑؓ؉ؔؕـ؎ؓؒق؎ؕ،ؒؔ";
        array[1073][0] = "/GeYv9YtbW3y1O9Lr/IdaA==";
        array[1073][1] = "ـؾ،ؔ؏فؔؑ؉ؓؾؔؓ؉ؐؕـؿ؉ؽقؒؔ؉ؔؔ؎ؑـؒـؿؑؐـؾ";
        array[1074][0] = "IKv5ljCAS6pDzIyqZezoDw==";
        array[1074][1] = "ؑ؎ؔ؏؎ـؔؑ؉ؔؕ،؎؉ؐفؑ؎؉ؾ،ؒ؍؉ؿؔؑؒؾ،ؓـ،ؾـق";
        array[1075][0] = "gJIUAhdZv58MACvKCsqDGQ==";
        array[1075][1] = "ؒ؎ؾؾـؑ،؎؉؎ؿؾق؉ؐقـ؍؉ؔؕ،ـ؉؍ؿؐ،ؕ؎ؒ؏ـ؎قؕ";
        array[1076][0] = "YJWJRQ2ddVl981eafxf5dw==";
        array[1076][1] = "؏ؒؒف؎،ؓ،؉ؾؽ؏ؐ؉ؐ؍ؑؓ؉ؾؔؔؑ؉ؾؐ؏ؿؿ؎ؔؽ؏،؎ف";
        array[1077][0] = "4TQIjpGV6Y1Y8RsorKo47A==";
        array[1077][1] = "ؒؿؔفـفؑؿ؉ق؎؎ؐ؉ؐؔق؏؉ؕؒؒؐ؉؍ؓؒ،ؿؿ؎،؍؏ؔؓ";
        array[1078][0] = "oK1if/qco8uE2ebeSWzQ3g==";
        array[1078][1] = "ؾـ؍ؐؿ؏ؑق؉،،ؓ،؉ؐؿؿف؉ؕ؎ؐف؉ؽؐؕؿف؍ؓؒؒؐؒؽ";
        By1337̢̛̲̖̼̞͔̫̰͚̹̞͓̦̬͈̭̩͎̥̻̘̔͒̂̎ͧ̎ͮ͌ͤ͊̀́̓̑̕͘͝͞();
    }

    private void By1337̢̛̲̖̼̞͔̫̰͚̹̞͓̦̬͈̭̩͎̥̻̘̔͒̂̎ͧ̎ͮ͌ͤ͊̀́̓̑̕͘͝͞() {
        array[1079][0] = "6tKK0bfDkJNcZSjcHs2xSA==";
        array[1079][1] = "ـؒف؏ؐ؍ؓ،؉ؔؑؔؽ؉ؐقؕؓ؉ؾؾؕ،؉ؔ؏ؽؿؽؽـؿ؍،ؑف";
        array[1080][0] = "XPTOan9UiDx41f5jH8737Q==";
        array[1080][1] = "ــف؏ؕؽؐؑ؉؍،ؑؿ؉ؐ؏ؔؑ؉ؔؓفـ؉ؐؔ؏ق؍قؔؔ؏،ؓق";
        array[1081][0] = "qZpW/l1FrEY0a/x2boXiZg==";
        array[1081][1] = "ؿ،ؽؒؒق،ؾ؉؍؎؎ؽ؉ؐ؎ؾؽ؉ؽؿفؐ؉ؒـؕؿ؏؎ؽؽ؎فؐؔ";
        array[1082][0] = "19xPs5L5/ABF7ueiYGPtZg==";
        array[1082][1] = "ققـؒ؍ؽ؎؍؉ؔؿؓؾ؉ؐؕـؐ؉ؔؔـؑ؉قف،ؾؕ؎ؒؓؓؾؒؑ";
        array[1083][0] = "GNdgqwjHFjCVlOzHTDfrIg==";
        array[1083][1] = "ؽ،ؐ؍،ؾؔؿ؉؍ؕ؍ؒ؉ؐ؍قؽ؉ؾؔؿؔ؉؎ؔؔؒؽقفق؏؏،ؾ";
        array[1084][0] = "cBV26lmtsBGOTFcBUv+3Fg==";
        array[1084][1] = "؍ؕـؽ،ؐ؎ف؉فـؓ،؉ؐؿؾ؎؉ؔؾؑؑ؉ؾفؔؔؑؔؽؔفـفؒ";
        array[1085][0] = "D30t8lJZ4aIYg4OW/3YSfQ==";
        array[1085][1] = "ؑقؿ؍ؔقؾ؎؉؎ؾؒؾ؉ؐف؍،؉ؕقؾف؉ؕؑؾؔ،؏؏ؿفؿؐؒ";
        array[1086][0] = "udP88ML8CT5BSMMmuhjAXQ==";
        array[1086][1] = "ق؏ؔؽق،ق؎؉ـ؏قؿ؉ؐؽ،؏؉ؾؒؿؒ؉؏؍ؔؒؿقؒؑؿؒؓؓ";
        array[1087][0] = "pY3+LK9eFleVZDkenDuLDA==";
        array[1087][1] = "ؒؔققؑؑؾؾ؉،ؓ،ق؉ؐؾ؏؍؉ؾ؏ؿؐ؉ؾق؍فؽؒؽ؍ؿؔ؍،";
        array[1088][0] = "SmqkfDy8e5AdMcd5XlCELQ==";
        array[1088][1] = "ؿـؕفـ،ؽق؉ؔؓقـ؉ؐ،ؓؓ؉ؾـ؍ؐ؉ـؑؒؾؔؕؿؾؒــ؍";
        array[1089][0] = "i85E+PpN/gIdFn+QjF/qqQ==";
        array[1089][1] = "ــؓفؽؓؾق؉ـؿؕؐ؉ؐؕـف؉ؔفؐف؉قؑؔ؍ؿ؎ـ،ؕؒؑ،";
        By1337̵̷̸̴̨̨̺̗͔͔̝̥̪̻̥̮̭̤̮̐͛̋͛͂ͪ̂͒ͥͨ̀́͗̇ͪ̇̕̚͢͜ͅ();
    }

    private void By1337̵̷̸̴̨̨̺̗͔͔̝̥̪̻̥̮̭̤̮̐͛̋͛͂ͪ̂͒ͥͨ̀́͗̇ͪ̇̕̚͢͜ͅ() {
        array[1090][0] = "Hw8BLPjy5LHB4iKCGZ3DTg==";
        array[1090][1] = "ؒؿؐـؓ؏ؕ؍؉ؓؾ؍ؐ؉ؐـؑؒ؉ؾـؑؐ؉؍،ؐؿقؾؓ؎ؿؓؑؔ";
        array[1091][0] = "JXozr+IOdoKFZeuyd+aZlw==";
        array[1091][1] = "ـف،فؐ،ؿ،؉ؕؓؓؾ؉ؐؒقؓ؉ؾقؔق؉ؾؽؿؑؓؕؾؿؑؾ؎ؐ";
        array[1092][0] = "xh/ADkcCyrgoiQnZEB8P6Q==";
        array[1092][1] = "ؿقؔؐؑؿؽ؎؉ؿؽ،ؒ؉ؐـ؏،؉ؾ؎؎ؿ؉ؓـؑ؍ؒؓؾ؏ؔؓؐؔ";
        array[1093][0] = "TU9P5fggjc3j89a5Yf6Dag==";
        array[1093][1] = "ؔؽؿ؏؎ؾؿـ؉ـؐف؎؉ؐؽؕ،؉ؕؒؔؔ؉ؽ،ؓؒؔ؍ؿؔؾؐؽؒ";
        array[1094][0] = "gFu5SCUrY5ni7BJEHZSWBQ==";
        array[1094][1] = "ؿؐؓؒؽؿؾؒ؉ؽؐ؏ؐ؉ؐؿؒؔ؉ؾ،ؾؾ؉قفـ؏ق؏،؍ؾ،ؿؾ";
        array[1095][0] = "EzMihbzen7nSn93U9l5PMw==";
        array[1095][1] = "؍ؕؿ،ؓ،ؕؾ؉؎،ؐ،؉ؐ؍،ؑ؉ؾـ؏،؉ؓ؎ؒؿؕؽؑ؍؎ؾفـ";
        array[1096][0] = "A4+Z/K6QHWvxolcfW4s85Q==";
        array[1096][1] = "قؿؿؕؽ؍قؒ؉فؑف؍؉ؐؿؒ؍؉ؔؑؔف؉؍قؿؓ؏ــؐؽؽؕؒ";
        array[1097][0] = "rNSE+P0E2A0kW+nILBUDrw==";
        array[1097][1] = "قفـؑؓ؎ؐؕ؉قؾقؑ؉ؐقؿؽ؉ؾ،،،؉ؒؑـؔؔ،ؾؓ،؍ؾؿ";
        array[1098][0] = "Dg6u2ie5+94yuyoLyXzKeQ==";
        array[1098][1] = "ؕؑؾؓؓ؍؏ؑ؉ؓؿؒـ؉ؐؕؿق؉ؔفؔؐ؉ف؏ؾؾؓؐق؎؎ؓفؑ";
        array[1099][0] = "lI2F0qZRSCwaWESvgUBHKQ==";
        array[1099][1] = "فقؔؽ،ؿؒؕ؉،ؕؓ،؉ؐ؍؍؍؉ؾؕق،؉ؿ؍ؐ،ؽـؔ؍ؕؾف،";
        array[1100][0] = "2mfSyoIZOwYbha4iegUNMg==";
        array[1100][1] = "ؒ؏؏ؔؑـ؎؎؉فؐؐؐ؉ؐؑ؏؏؉ؔـ؏ق؉فؽؒؒؾقؓفـؓؓؽ";
        By1337̸̸̶̡̧̺̦̝̟͉͔̭̳̬̹̙̝̟͖̜̭͇͐͒ͫͬ̀ͦ̉ͣ̋ͯͣ̀̈͂̆́ͥ͑();
    }

    private void By1337̸̸̶̡̧̺̦̝̟͉͔̭̳̬̹̙̝̟͖̜̭͇͐͒ͫͬ̀ͦ̉ͣ̋ͯͣ̀̈͂̆́ͥ͑() {
        array[1101][0] = "sFSxHv5KiT6y5FjAHOXvkg==";
        array[1101][1] = "؏ؽؒ؏ؔ؍ؓ؏؉؍ؑؓـ؉ؐ؏ؿف؉ؽؿؒؾ؉؍ؑ،؎ـؑقؐقـؐؿ";
        array[1102][0] = "7zntxp1ctD1SToXATpLA9w==";
        array[1102][1] = "،ق؍ؽؐؾؕ؍؉ؔ؍ؕؑ؉ؐؕؕؾ؉ؕؒؐ؏؉،ؕؽقؐؓؿ،ؾؿقف";
        array[1103][0] = "sCwqkh438oT9rY/kkUEAkw==";
        array[1103][1] = "ؿؽؒقؽؽؾف؉ـؽ؎ؽ؉ؐؕؕ؏؉ؕف؏ؒ؉؍،ؿؽ؍ؾ؎؍ؔـ؎ؾ";
        array[1104][0] = "0XLTF/mRx8QlvpBjlagzRw==";
        array[1104][1] = "ؐؿقؒ؎ؾفؕ؉ـ؏،؏؉ؐؓؽـ؉ؕ؏؍ؿ؉ؒؑؑؕ،ؕ،؏ؐؐ؎ؿ";
        array[1105][0] = "dc988zy2GYen4hvOfj4flA==";
        array[1105][1] = "ؕقؕ،ؔؓقؒ؉ؕؽفق؉ؐ؍ق،؉ؾفؾؑ؉ـؽؔؽفؓ؏ؕقؾؾؿ";
        array[1106][0] = "kcQK5Sbp6syQ4X5NgwG6kQ==";
        array[1106][1] = "ؿفؒفـؕؒؽ؉ؒـؓؑ؉ؐؒقؿ؉ؕ،ـؔ؉ؽؑؔؽؕؕ؎ؔؑ؏ؒق";
        array[1107][0] = "C7lY77xNJQUvrVdZ0Wv0Dw==";
        array[1107][1] = "ؾؾ؏ق؍؍ؕ،؉ؽؒؿ؍؉ؐؕؽؐ؉ؽ؍ؒق؉قؒؽؿؿ؏،ؐؔؒقؑ";
        array[1108][0] = "aXlIIBRchvN38AV+sx5k5w==";
        array[1108][1] = "؎؍؎ؐؿقؐؕ؉؏ؐـق؉ؐؒؕ؍؉ؔؾفؒ؉؎ؾؽ؍ؒؽـ؎ؐؐ؎؍";
        array[1109][0] = "OpFgP0j8MDpx34LyTczUvQ==";
        array[1109][1] = "ؔؾ؎،؏؍؍ـ؉ؾ؏قؑ؉ؐؔؒؐ؉ؕ؍ؒؓ؉ؐق؎ؐؾؐؕؔؾ؏فؐ";
        array[1110][0] = "weYoEvn/5nV3yIy4El0dgA==";
        array[1110][1] = "ؕ؎؍ؐـ؍ق؎؉فؓ؍ف؉ؐ،ؒ؏؉ؾ؎ؿ؎؉ؒفؐ؏ـؓ؏؎ؕفـ؏";
        array[1111][0] = "nDCOfgPd88x7cTSdx21zYQ==";
        array[1111][1] = "ؿؿ؍قؕؑ؍ؑ؉،ؑؾؒ؉ؐؓقؒ؉ؕـ؍ؓ؉ف،ؔؕ؎ؕؔ؏؏،ـؽ";
        By1337̸̵͔͉͍̦̝̫̖͈̪̻̦̣͗ͭ͗̃̌̀ͧ̽̌ͫ̇̄ͥͯ͏̶̮͍̤̲̐̑͒̀͜͡();
    }

    private void By1337̸̵͔͉͍̦̝̫̖͈̪̻̦̣͗ͭ͗̃̌̀ͧ̽̌ͫ̇̄ͥͯ͏̶̮͍̤̲̐̑͒̀͜͡() {
        array[1112][0] = "XCUNTHOUOj9XkijemkCbpA==";
        array[1112][1] = "ؕؕؾؕؐ؍ؒ؏؉ؑؑؿؐ؉ؐ؍،ؑ؉ؾؕؓ؍؉ؽـ،ؕؒ،ؓؕؔ؏،؍";
        array[1113][0] = "efIhB6/XwrnIIrvJLQ5bGw==";
        array[1113][1] = "ـ؍ـؓؐـؓق؉ؾقؐـ؉ؐـؿؿ؉ؕؕ؍ؑ؉فؒ؏ؒ،ؑؓفؽؕؾـ";
        array[1114][0] = "6DTNq+v9VwOkgHMSzaz8XA==";
        array[1114][1] = "ؽفؕ؎ؿؽؑؐ؉ؕؽـؽ؉ؐؾ،ؓ؉ؾؒؕؿ؉ؽ؍ؓؒؿؽؾؿ؍ؑؒ؏";
        array[1115][0] = "XNcnbxWPPKRdBpe94cKKUQ==";
        array[1115][1] = "ؓفـؽ؍ؕؓؔ؉؍؍ؐ؍؉ؐؾفؒ؉ؕق؏ؓ؉ؑ،ؽ،؍ؑ؎ؽ؍؎ؓؔ";
        array[1116][0] = "f4zsrJrd4FHv0wB54NCc1A==";
        array[1116][1] = "ـؓؕ؍؍ؕؔف؉؏؎ف،؉ؐفؐؒ؉ؔؿـ؏؉ؒ؍ؕؔؒؑ؎ؕ،ؔؒؓ";
        array[1117][0] = "ZNcaA7FwIqOG3SzD0lA/8w==";
        array[1117][1] = "فقؐ؎ؽ؎ؾـ؉؏؍ؓ؍؉ؐ،ؓؒ؉ؕؒؑؓ؉ؽفف؎،ؐؒـؑؔؐ؏";
        array[1118][0] = "rto7oZpF6+CP+nhLI9LBow==";
        array[1118][1] = "ؑ؎ؒ؏ؾؿؽ؏؉ؿ،ف؏؉ؐؿؿؕ؉ؔ؎،ق؉ؑؕ،ؕ،ؾؓؔـ؎ؽؽ";
        array[1119][0] = "0bBgxSbU30phEjp+Rk3p1w==";
        array[1119][1] = "قؾؓؿؒؔ؎ق؉ؕؒ؍ؾ؉ؐؐؾؕ؉ؕؐ؎،؉ؑقؓؓؽؿؐؕؔ،فؾ";
        array[1120][0] = "T7/2aYCa9Tbol9Fu6L2hqA==";
        array[1120][1] = "ؿ؎فؑؿـق،؉ـؽ؍ؔ؉ؐ،،ؒ؉ؽققؓ؉ؓؑقؑؕؾؾؽؑؒؾؔ";
        array[1121][0] = "SHU4skZut8JDnEOvAoCHlQ==";
        array[1121][1] = "ؔؕ؏ؒ؏ؽ،ؔ؉ؾؾقؒ؉ؐؽؿؕ؉ؽ،ؔ؎؉ؽؕ؍ؕ؏ؓـؿ؍ؑق؎";
        array[1122][0] = "ef/5v2XiJznlQXVrXJQHkw==";
        array[1122][1] = "،؎ؿ،قق؎ق؉ؕ؎ؾؑ؉ؐــؕ؉ؽـقف؉؍ؒؿؒ؎ؕفؿؾ،؏ـ";
        By1337̶̵̵̱̙͇̹̥̣͎̻͎͉͈̭͖̯̩͇̥͂͒̓̒ͭͫ̇ͫ̾̏̿ͭ͒͒ͦ̆͘͜͞ͅ();
    }

    private void By1337̶̵̵̱̙͇̹̥̣͎̻͎͉͈̭͖̯̩͇̥͂͒̓̒ͭͫ̇ͫ̾̏̿ͭ͒͒ͦ̆͘͜͞ͅ() {
        array[1123][0] = "CeWBoSreFLWRq92pfBqneQ==";
        array[1123][1] = "؏؍ؓ،ؔ؏ؔؔ؉؏فؿق؉ؐفـؽ؉ؽؕؑؽ؉ؒقؽؐؓؒؐؒـ؎؏ؿ";
        array[1124][0] = "FKNPM13d3Nux4ZmySDKp4g==";
        array[1124][1] = "ؽ؎؍فؕؔؿ،؉ؐفؐؓ؉ؐؑقف؉ؔؿـ؍؉؍؍ؑؽؽؓـؾؓؓؽؾ";
        array[1125][0] = "9CyrMhwEbzxMDbbkECzQ1A==";
        array[1125][1] = "ؾؐ؏؎؍ؔؔؐ؉ؿؓ؏ؐ؉ؐؽؿ؎؉ؽؽ،ق؉ؿؔؓ،ؑق؏ؿق؏ؿؓ";
        array[1126][0] = "zyFc/ZumCRw5gRYKDWOYMg==";
        array[1126][1] = "؍ؕقؿ؏ؾ،ؑ؉ؾؿـق؉ؐؔ؍ؽ؉ؕ؎ؽ،؉ؾؾؽؽ؏ؒؐؽ؍ـؿؐ";
        array[1127][0] = "w/VN1sSwBtBEc16tzPiMTQ==";
        array[1127][1] = "فؐؾ؏قؿؓف؉؎ؾ؏ؔ؉ؐؑفؓ؉ؾؐؓق؉؍ؾؽ؎ؒؑؓؐؒؓفؔ";
        array[1128][0] = "nciZMak7hAL6SShz9GOaJA==";
        array[1128][1] = "ؾؐؕـؐؕؐؿ؉ؽ؎ؐف؉ؐؒؐؾ؉ؽفــ؉ؾؽ؏ؒؔقؾـؕؐ،ؾ";
        array[1129][0] = "+oZ0R7Ikeo7kITKzJTgHcg==";
        array[1129][1] = "،ؒؓ؏فؕؾؽ؉ؐؐؒ؎؉ؐؿ؏ؑ؉ؾ؏ف؎؉ؕ؏؎ؒفقؾؐ؎ـؿ؍";
        array[1130][0] = "W+o7LGHsAm9gltnybZ+Y2Q==";
        array[1130][1] = "،ؑؽ؎ـ،؎ؑ؉،ؿ؎ؿ؉ؐؑؿؾ؉ؾ؎،ؒ؉ؕؐ؏ؒؒ؍ؐؑؔؕؒق";
        array[1131][0] = "i5EsOSlSL5Gv/kNM+hYRPg==";
        array[1131][1] = "؏؎؍ؑؓؐؕؓ؉ؿؒ؎؏؉ؐق؍ؾ؉ؽؐؓؔ؉ؐ؏ف؍ؾؐ،ؒؽؒ،ـ";
        array[1132][0] = "pAtr7zGsN1dhI2SI2y/FEQ==";
        array[1132][1] = "ؒؒؒ؍ــ؏ؕ؉؏فؐ؍؉ؐؓ؍ؔ؉ؽف؍؎؉ف؎ؑؾؓؑ؍ـؒ؎فؒ";
        array[1133][0] = "h581rq3J5SCKGt9FgnePvA==";
        array[1133][1] = "ؑؐؐ؎ؔ؍ؒؾ؉؎ؿؑؽ؉ؐقؑ؏؉ؾؕؕق؉ؽؐؔـ؎ؾؐؕ؏ـقـ";
        By1337̴̵̸̨̹̮̯̝̭̝̲̗̂͂͌ͩ̂͐ͨͥ̉̅̐ͮ͒̋̓ͧͥ̂̋̊ͧ̽ͪͮ̉͏̢͡();
    }

    private void By1337̴̵̸̨̹̮̯̝̭̝̲̗̂͂͌ͩ̂͐ͨͥ̉̅̐ͮ͒̋̓ͧͥ̂̋̊ͧ̽ͪͮ̉͏̢͡() {
        array[1134][0] = "bSCW4f03DQPYadlo7Kcc7w==";
        array[1134][1] = "فؽؿؕ؏؏؍ؽ؉؍ؿؕق؉ؐفؑؐ؉ؽ؎،ـ؉،ففؿؓ؍ؽ؏ـ؏ـ،";
        array[1135][0] = "jMxvpj15v5z5q50KrIkNpg==";
        array[1135][1] = "ؓؑؑف؍،ؿؕ؉؏ؒ؎ق؉ؐـؑ،؉ؔؔؿؑ؉ؔقؿؒؑؑ؍؎ؔ؎؏،";
        array[1136][0] = "cujRZd45JCmTQBL65lTGaQ==";
        array[1136][1] = "فؿفؕ؍،؏ؑ؉ـؕؾؕ؉ؐؐؑؽ؉ؾؑؾؾ؉ؾ؍ؾؾ؏ف،ؑؿؾفـ";
        array[1137][0] = "E+hKnFrx/C7EGMzcWGb+OQ==";
        array[1137][1] = "؎فؿق؎ؽؑف؉ؒؔؑ؏؉ؐؾؑؾ؉ؕؾؾؿ؉ؒقـؽؔؔ؎ؒؓؑؾ؍";
        array[1138][0] = "MMdqalr+7+anKcHDVwbHgQ==";
        array[1138][1] = "ؕؐؐؕؒـفؐ؉ؔ،فؓ؉ؐقؽف؉ؔ،قؕ؉،ؿ؏؏ؔقؔ،ؽؕفؒ";
        array[1139][0] = "dG0kgHdIgdrS+B7J1feglw==";
        array[1139][1] = "قفؑـؓـؒؿ؉؏،فؿ؉ؐؓؓـ؉ؕؑؑف؉ؕؿؑؓؿقؐ،ؑؿ؍ؐ";
        array[1140][0] = "oWO64zSseC2dJKEonyalyw==";
        array[1140][1] = "ؐفؓ؏ؑ،ؐ؍؉ؿف؎ؾ؉ؐفؾـ؉ؾؒقؿ؉؏فؕؔ؎ؑؿؔؓؕؔؒ";
        array[1141][0] = "BjV8AkLjJqQ2kcvreCdYlQ==";
        array[1141][1] = "ؾؿؔ؍ؔـؽ،؉ؽ؏قف؉ؐ؎ؕ؍؉ؕؕقـ؉ؐقؑؿؿؑؐ،ؕؑ؎ؽ";
        array[1142][0] = "erEkV+gSTkUmaOD4LL9xmw==";
        array[1142][1] = "؍؎ؒؓؕ،قف؉فؓ؍ـ؉ؐ؏ؕؓ؉ؕ؏ؾؓ؉ؕؿؒؽ؏ـ؏ؐؽـؽ؎";
        array[1143][0] = "alEL76aqbVlSkIeM8bKzeQ==";
        array[1143][1] = "؍ؐؐؔـؾؕق؉ؑؿؿ،؉ؐقفؑ؉ؕؑؔؔ؉ؒ؎ؾؒؽ،ققؕ؏ؔؕ";
        array[1144][0] = "7Vz8tkRqSois820m8AgtCA==";
        array[1144][1] = "ـؑؑ؍؎ؽ؏ؐ؉ـؔـ؎؉ؐؽؔؽ؉ؽؒؑ؎؉ؔفؔؔؕ؏؎ؽؑ،؎؏";
        By1337̶̷̨̨̧̧̢̩̲̗̯̮͉̹͓͇̪̜̳̈́͑ͫ͛̐ͣͪ͐́̀͑̎ͤ̓͟͟͟͡͠͞͞();
    }

    private void By1337̶̷̨̨̧̧̢̩̲̗̯̮͉̹͓͇̪̜̳̈́͑ͫ͛̐ͣͪ͐́̀͑̎ͤ̓͟͟͟͡͠͞͞() {
        array[1145][0] = "sE0QJd10s8K87RsAs7s7hQ==";
        array[1145][1] = "ـؽؿ؍؎قؑؐ؉؎ؕ،ؔ؉ؐؐ؎ؿ؉ؽقف،؉ـؕ،ؔؽ؍ؽؔؕؑؕ؏";
        array[1146][0] = "CSpJfgTc7KSVt+ryS6Q8qw==";
        array[1146][1] = "؍ؾؽؐؿ؏؎ـ؉ؽؓ؎ؐ؉ؐؕؾؑ؉ؔ؎ف؏؉ؐ؎ؾـؽؔقؒؿؿؾق";
        array[1147][0] = "Pv8mlr8IOJv5IazueJWhjA==";
        array[1147][1] = "ؓــق؏،قؒ؉فؿ؎؏؉ؐـؐؓ؉ؔؓؐؾ؉ؐؽ؏ؑؐف؍فؿؿؓـ";
        array[1148][0] = "l9nuzxZRsz0NstDQJaIB4g==";
        array[1148][1] = "ؿؿ؍فؕؑ،ؔ؉ؽف؏ؕ؉ؐـ؍ؕ؉ؽؑؿف؉؎ؒؔؒؓ،ؿ؍ؿؓؒؽ";
        array[1149][0] = "//eXMU1xsCl/3a1M8B/c0Q==";
        array[1149][1] = "ؑفؽ،؎؏ؓق؉ؓؐؔؑ؉ؐؒؕؑ؉ؾؐؾق؉،ؐـ؎؎؏ؐؑقؓـؕ";
        array[1150][0] = "x9BN08kHIG9mYWeQJ61lIA==";
        array[1150][1] = "ـؕؑفؔؾ؍ؽ؉ؽؕؿؑ؉ؐ؍فؔ؉ؾؐؓؒ؉ؐ؎ؒ؏ؽ؏قؓـ،ؔؒ";
        array[1151][0] = "J3KpBM3fMtebgoxyml3N3Q==";
        array[1151][1] = "ؒؓ؎؏؍ؔ؍ؒ؉ف،ؑؓ؉ؐققؓ؉ؽؕؒؾ؉ؽؾؑؕؕؓ؎ـؾ؎ؾ؏";
        array[1152][0] = "bd4o0nEC+X6B6wTkP4IK0A==";
        array[1152][1] = "؎قؽق؏ؽؐؽ؉ؑؑؓؔ؉ؐؾفؿ؉ؽؑقف؉؎ؽؐؓؐؽؽ؏؍ؑؓؑ";
        array[1153][0] = "Vqw/ugYMU4P0t0ab/OZy2g==";
        array[1153][1] = "؍؎ؾ،،؎ؑـ؉ف،ؒؿ؉ؐ؍فؿ؉ؔ؍فؒ؉ؽؓؿؾ،؏؍ؓؕؕؽؿ";
        array[1154][0] = "Uvz85TDY42TSDgNxpbcFrQ==";
        array[1154][1] = "ؾ؍فؕؾقؓف؉؎ؽؑق؉ؐؿؔ،؉ؕ؍ؾـ؉ـؕفؒؒؒقؑؓ؍ؓؑ";
        array[1155][0] = "IexmhFTOSRfJJFEr+2CXAw==";
        array[1155][1] = "؍ؕ؎ؽؔؐقؐ؉؏؏ؾ؍؉ؐؓؓؿ؉ؔقؕ؎؉ؕؓفقؒؽؽ؎ـؔؑ؍";
        By1337̴̧̛̳̮̮͉̺̗̹̙͈̩̺͔̰͓̉̈́̔ͤ̏̄͌̌̍̓͐ͯ͋͌ͪ̾ͩͬ̚͘͟͞ͅ();
    }

    private void By1337̴̧̛̳̮̮͉̺̗̹̙͈̩̺͔̰͓̉̈́̔ͤ̏̄͌̌̍̓͐ͯ͋͌ͪ̾ͩͬ̚͘͟͞ͅ() {
        array[1156][0] = "oa3b7zsfebmvZPesheotWw==";
        array[1156][1] = "ؽؽؑؽؓ،فؓ؉ؽقؿؕ؉ؐؕـؒ؉ؕؽؔؒ؉،ؽ،ؾؑق؎ؕ،ؒؐق";
        array[1157][0] = "NzF4N7UioRWc2L9aMAqnSA==";
        array[1157][1] = "قـ؍ؒؐ؏ؑؾ؉ؒؔؓف؉ؐؕقق؉ؔ،ؾؕ؉ؔؓ،ؔؔؒ؏ؕؓؔؔف";
        array[1158][0] = "m4DMCb4zfwi0+N1dvY/0rg==";
        array[1158][1] = "ؔؓ؎؍ؑؿؒ؎؉فؒ؎ف؉ؐق؍؎؉ؽؽ؏ؽ؉؍فؔؽؔؔقؐؔ؍؍ؑ";
        array[1159][0] = "K5J6OZglnclZYaDoytwtvg==";
        array[1159][1] = "ؽؿؿؽ؏؍ؕؑ؉؏ؔـ،؉ؐ؏ؓ؍؉ؽـؔـ؉؏ؑؑفؒؓؾؓ؏ؑؽق";
        array[1160][0] = "7zqvRTaBkgZ6JnPb8Kb1oQ==";
        array[1160][1] = "ؿق؍،ؕ؎ـؔ؉ق؎فؽ؉ؐ؎ؑ؏؉ؔ؎ـؿ؉ؽ؎ؿقـ؏ؕ؎ؔق؏؎";
        array[1161][0] = "gt/eYrA2b/Zyx0plkxGAog==";
        array[1161][1] = "؎؍؏ؑؽؿؾ؎؉ؔ؍ؓؽ؉ؐقـؔ؉ؾق؏ؾ؉فؑ؍فؓؓؿ؍،ـ؍ؽ";
        array[1162][0] = "sPGi5C2WK/YENe8d+xCiCQ==";
        array[1162][1] = "ـؕؔؽ،؍ق؏؉،ؔؐؽ؉ؐ؏ؕؑ؉ؔ،ؒؑ؉ؿ؎ـؽقؑؔـؑؓ؏،";
        array[1163][0] = "Vj3WlTis71u8ABTMnEKQCA==";
        array[1163][1] = "ؔ؏ـؔؿؕـؒ؉ؓقؑؔ؉ؐ؎ؽؐ؉ؔؒ؍ؔ؉ؿؑـف،ؓ؏فؽؒؕ؍";
        array[1164][0] = "sLHrAb5SywffbTXuXwYvSw==";
        array[1164][1] = "؍ؐؽقؕؕؿؓ؉؎ـؕؾ؉ؐ،؏ؓ؉ؔؐؿف؉؎ؓؽؿؿقؾ؏؏ؒ؍،";
        array[1165][0] = "vvkhDX9WGB4qVwwFNQzBIg==";
        array[1165][1] = "؏ؿؔ؍ؒؕؐ؎؉ؔؕـؐ؉ؐؾ؍؏؉ؾقـؿ؉ؒقؕؾ؎ـؾؽ،؏ؿق";
        array[1166][0] = "48C3HI0RwwdObJkoIEGMJQ==";
        array[1166][1] = "،ؕ،ؽؐ؏ؒ،؉ؒ؎ؐؑ؉ؐؽف؏؉ؔؑ؍ؾ؉؎ؿـقؕؾؕؾؓؿؒ؎";
        By1337̴̸̨̨̢̹̙̙̩͙͈̜͕̭̲͎̟̗̭͉̞̘̅͗͑̂̋͊͌̌͛̿̏͊͆̓͋̚͠ͅ();
    }

    private void By1337̴̸̨̨̢̹̙̙̩͙͈̜͕̭̲͎̟̗̭͉̞̘̅͗͑̂̋͊͌̌͛̿̏͊͆̓͋̚͠ͅ() {
        array[1167][0] = "Vg0zMwxnw1B6F9VM2BTlSQ==";
        array[1167][1] = "ؿؽؽؕؑ؏ؽؕ؉؍ؓؽؓ؉ؐفؐؾ؉ؾؑـؔ؉ؾـؓ؎ؑؒ؍؍ـؿؓؓ";
        array[1168][0] = "amNH+EOuo6e/ZNPddOpOBw==";
        array[1168][1] = "فؑ؍ق؎ـؿؓ؉ؔؑؕ؎؉ؐؒؕ؍؉ؔؓ؍ؓ؉ؾقؾؐؓ؎؎ؐ؍ق؏ؽ";
        array[1169][0] = "PEJR7Bk0jgceLF9GViDH0Q==";
        array[1169][1] = "ؕؾق؏؏ؽؽؾ؉؍ؿفـ؉ؐـقـ؉ؾؔؽـ؉ـؕؑؕ؍؏ؕؐؔؾقؿ";
        array[1170][0] = "35Jof7IoH1GhVKROm8swCw==";
        array[1170][1] = "ؕؓق،ؿؑ،ؿ؉ـؕؒ؏؉ؐ؏ـ؏؉ؔفؿف؉ؑؒـؽ؍ؔؿؑؑ،؍ؾ";
        array[1171][0] = "NAGNN/lLdgsx4BMlAdyQGQ==";
        array[1171][1] = "ؓ؍ؿ؎ؑ؎ؔـ؉ؐؾ؏ؿ؉ؐ؍؍ق؉ؾ؎؎ؓ؉؎ـؒؾؑ؏؎ؿؽؐؕؑ";
        array[1172][0] = "qHYJNLsvFJSdy/XDAkWyyA==";
        array[1172][1] = "ؒؕؾ؍ؑـ؏ؔ؉ؾؕؓ،؉ؐـؕؒ؉ؔؿؕ؍؉؏ـف؏ؿؓؐ،فقؾؔ";
        array[1173][0] = "JkUNQJ0Bjx4PvNgfKKJbUQ==";
        array[1173][1] = "ؑ؏ؑؑؾـؾؾ؉ؐؓؕق؉ؐؿ؏ؕ؉ؔؓؑؐ؉ؒؾؒؓق؏قؑف؍ؒؕ";
        array[1174][0] = "ZfZG87pncPQb9N7G+7Z4Dg==";
        array[1174][1] = "ؐقؽؑؔؑ،؍؉ؓؕؾف؉ؐؓـؒ؉ؽؾق،؉ؔؑؑـ؎؏؎ؐؔؐؔؿ";
        array[1175][0] = "3IFqbEbttWm7adzZt4rbvg==";
        array[1175][1] = "ؑفؔفـ،ؔؔ؉،ؓ؎ؽ؉ؐ؍ؐ؎؉ؽ؍ف؎؉ـؓؒففؿؐؕؽؾؾؾ";
        array[1176][0] = "jIPlVLWN2nFQ6Hw1m4WKLg==";
        array[1176][1] = "ؿؓقفؾ؍ؒؽ؉ؐـؔؑ؉ؐؔ؏ؿ؉ؔ؍ؿـ؉؏؍،ؒفؽقؑؒؑؿؑ";
        array[1177][0] = "r8NMViHF/IZAqEFnXTOp+Q==";
        array[1177][1] = "ؐ؎؍؎ـؽؽق؉ؓؐؓؑ؉ؐ،ؔـ؉ؽؒ،ف؉ؐـ؍؎ؓؾؐؐؓؔفؐ";
        By1337̢̨̺̟̲͙̘̩̤̱̖̙̠̗̖̟͔̺̭͕̪̻̟̎ͣ̊ͫ́̿̂̋̏̂̃̔̌ͦ̇͟͡();
    }

    private void By1337̢̨̺̟̲͙̘̩̤̱̖̙̠̗̖̟͔̺̭͕̪̻̟̎ͣ̊ͫ́̿̂̋̏̂̃̔̌ͦ̇͟͡() {
        array[1178][0] = "BnXC4k96GKjpGLYwx6wm+g==";
        array[1178][1] = "،؏،،ؔؔ؏ؔ؉؏؎،؏؉ؐق،ؒ؉ؾؒـف؉،ؔؿؽ؏،ؿؔقفؓؕ";
        array[1179][0] = "5Viomn4mhh7yJ5mZQSGxBg==";
        array[1179][1] = "ـفؕؿؾ؏ؕؾ؉فؾؕؿ؉ؐؒقؔ؉ؽ،؏؍؉ـؕؕ،فؒقؽفف؎؏";
        array[1180][0] = "jAzPvnr8U6pR+qAg6h/XlA==";
        array[1180][1] = "ؔؾفؾؓؔؓ؏؉ؔؔ،ؑ؉ؐؐقؽ؉ؔؾؽف؉،ؓؿؒق؍ؐ؎ؓققؾ";
        array[1181][0] = "YFjj9tJ8KLeNU5q2MS9GgA==";
        array[1181][1] = "فقؽؽقـ؍؍؉ؐ،ؐؔ؉ؐؔـؓ؉ؽ،ؕؿ؉؏ؕؕ،ؐؕ؍ؽؒفؓؒ";
        array[1182][0] = "wsyRZC900GKjWdhb2VS0ug==";
        array[1182][1] = "فؒؓـؽؐ،ف؉ؽ؎؏ؓ؉ؐؾؕؽ؉ؽؿؽـ؉ؐؔؑؔؒ؏؏ؿؐؓ؏ؔ";
        array[1183][0] = "dso9Lh2LC/6mat0owwFpAw==";
        array[1183][1] = "قؕؒقؾؾؽ،؉؍ؿؓؾ؉ؐ؎،ؕ؉ؔؑف؏؉؎ؿؾؕؿؿ؍ؽؑؿؽ؍";
        array[1184][0] = "l1fgNAf9tS91fEU9XeqIHg==";
        array[1184][1] = "ؑؓؐ؏ـؑؒؒ؉ؓ؍ؓف؉ؐؾ،،؉ؔؕ؍ؕ؉ؽؾ؎قؓؑؾ؍ؐؑ؎ف";
        array[1185][0] = "fl/T8O260fxAnWfU5CeAqA==";
        array[1185][1] = "؏؍ؽ،ؕؔقؾ؉فؔــ؉ؐؐ،ـ؉ؽؿؔ؍؉؏؎ؓؕؽؐ؏فؔؽـؿ";
        array[1186][0] = "Jxac5ft8xz5XBdfOaKSgFQ==";
        array[1186][1] = "؏؏ؾؽـؾفـ؉ؓ؍ـ،؉ؐؐ؍ؔ؉ؾؑؿؾ؉ؽ؏ق؎ؓؿؿؾؽؓؔـ";
        array[1187][0] = "wx+tS9/anzJvck2YYtICNw==";
        array[1187][1] = "ؔ؎ؿقـقؑؽ؉ؑؕؑؕ؉ؐ؏ؑ؏؉ؾؒؓـ؉ؒؕ،ؔ؎ؔف؏ـؔؿؕ";
        array[1188][0] = "lGGj4hC5jHSMThK5WGqOww==";
        array[1188][1] = "ؐ؎ؐ؍ـ،ؐؾ؉ؿ؏ؕ؎؉ؐـ؍ؑ؉ؔؓ؏،؉قؓققـؒؕـؓؽؑ؏";
        By1337̧̧̲̭̥͖̮̗̘̹̞͈̝̘̠͕͚̬̰͆͛͂̇̾ͣͥ͒ͫ̓̅͒̂̂ͦ̇̕̕͠͡͠();
    }

    private void By1337̧̧̲̭̥͖̮̗̘̹̞͈̝̘̠͕͚̬̰͆͛͂̇̾ͣͥ͒ͫ̓̅͒̂̂ͦ̇̕̕͠͡͠() {
        array[1189][0] = "tZI/lv7XYqmZ+rwaxNe71Q==";
        array[1189][1] = "ق؍ؕ؍؎ؿؔؽ؉؎،ؑف؉ؐؕ،ؐ؉ؕـؿؾ؉ؔقؔؐؓؽ؎ؑ؎ؾفـ";
        array[1190][0] = "ULXknnfLMBfIcBLpWkW5Zg==";
        array[1190][1] = "ؔق؏ؔ؏،ؑ؍؉؍ؐؿؔ؉ؐؿق؏؉ؕـؿؾ؉؏ؽؾقفؿق؍،،قؐ";
        array[1191][0] = "EA3XzYBNSAgzudEW2+FHsQ==";
        array[1191][1] = "ؑؓؿؾؕؕؾؑ؉فؾؔؑ؉ؐؐؑؕ؉ؽ،ـ؎؉ؽ؍فؔؽؔ؏؎ؐففؒ";
        array[1192][0] = "QGdzIkC+fdqfSYMe4y5u8g==";
        array[1192][1] = "ـفؒفؿؓؒ؍؉ؑفـف؉ؐؕ؎ؾ؉ؾؽفؿ؉ؕ،؏ؽؔ،ؿؾؑف؎ؕ";
        array[1193][0] = "nbsDJxD47kM6V3atvG978g==";
        array[1193][1] = "فؔؐؐ؎ؕف؍؉ؿؓ،ؿ؉ؐؽؓؐ؉ؾ،فـ؉ؾ،ؽ؎؎؏،ؾؔؑؾـ";
        array[1194][0] = "IoX0tJL1ESt6WxJ9zSkRrA==";
        array[1194][1] = "ؓ،ؿفؿؒؔف؉ؾؐؿؿ؉ؐ؎؏ؽ؉ؾؿؒؐ؉ؽؿؐ،ــ؍ؿؒؾؽؐ";
        array[1195][0] = "oCMeX0ir+Nwo4KKLoGcqfQ==";
        array[1195][1] = "ف؍ؒفؑؑؐؒ؉ق؎،ؑ؉ؐ،ؾؾ؉ؔؐؓؿ؉؏؎ؐؑؕؓؒؿؓؑ؏؍";
        array[1196][0] = "nLYLtFBeDyRNKtMh/RT3Mw==";
        array[1196][1] = "ؒؑؑـؽ؍ؕ؏؉؏؎ؕؔ؉ؐؿؽؾ؉ؕؔ؍،؉ؒؿؾؐؽؽـؐؑؑؑ؎";
        array[1197][0] = "fO3sH51Zur0AXARrrONNqQ==";
        array[1197][1] = "قؕـ،ف،ؕق؉ؔؐ،ؿ؉ؐفؽف؉ؾؕ؎ؓ؉؏؏ؾؒ،ؐؑ؏ؿـؒ؍";
        array[1198][0] = "uXGLbDJUZHcBX3romeHBEQ==";
        array[1198][1] = "ؐؓؑ،ؽؓف؍؉ؒ؏ؒؒ؉ؐـ،ؔ؉ؕؔقؒ؉ف؏؎؏ؽؕؕ،قؒؓؒ";
        array[1199][0] = "j9vJLWNrkRdTXaBbkERfDw==";
        array[1199][1] = "ؓؔؑؽفؿفؐ؉ق؍ؕؽ؉ؐ؎ؿؒ؉ؾؽؕف؉ؑ؎؏ؓؽؓؓ؍ؔؽ؏ؾ";
        By1337̵̴̸̷̢̢̹̺͚̖͈̹͕̖̝̬̮͉̈́̌̅̓ͣͪ̒̉ͪ̄̔̀̕͞͝ͅ͏̵̧̦͊ͭ();
    }

    private void By1337̵̴̸̷̢̢̹̺͚̖͈̹͕̖̝̬̮͉̈́̌̅̓ͣͪ̒̉ͪ̄̔̀̕͞͝ͅ͏̵̧̦͊ͭ() {
        array[1200][0] = "YZWfEIP82UZv9aT1ATbmEw==";
        array[1200][1] = "ؒف؍؍؎ؿؾ؍؉ؐـؐ،؉ؐـؒؿ؉ؽؑؑؿ؉؏ؾؔؐؐؾـ؏؎ؐ،ؾ";
        array[1201][0] = "Ohz7L5t6Wrx8EHtBQvBSOA==";
        array[1201][1] = "ؔ؍ؕـؽؓؔؽ؉ؔؐـق؉ؐؔؔ؍؉ؕ؎ـ؍؉ؔؕؔؐؔؿؒؾؒؒؓؑ";
        array[1202][0] = "x6VSZrExl/ZuVSt9AIuClg==";
        array[1202][1] = "؏ؓؒ؍ؾؿؿؒ؉ؑ؏قؽ؉ؐؕـ؎؉ؔ؏ؐ؏؉ؐ؎قـؽ؏؍؎ؑؑـف";
        array[1203][0] = "3PgcR65JzgyP/ymUWAuImA==";
        array[1203][1] = "،؎ـؒؾؓ،ؕ؉ؑؐــ؉ؐؑ،ؔ؉ؾؑؕؿ؉ؓ،؍؎ؒقؽؽؐؿؑؓ";
        array[1204][0] = "Ow7frVbokv7BsQ1Khn6ISg==";
        array[1204][1] = "ؒ،ؒـؐؒؾ،؉ؔؓؒـ؉ؐـ؏؎؉ؕؾ؏،؉ؓـؔ؏؎ؔ،ـؐؐ؍ف";
        array[1205][0] = "5+DNzVvxaNTsFMMhLY96sw==";
        array[1205][1] = "ؾؾؽؓؿ؍؎ؐ؉،؎ؐ؏؉ؐ؍قؓ؉ؽـف؏؉فؐؑؕ؍ف؏ؑؾؔؓؓ";
        array[1206][0] = "M46xFmiYSIxp0M3iVWE5PA==";
        array[1206][1] = "؏؎ؑؑ؏؏؍ؒ؉ؕؒؽؾ؉ؐؒؾؓ؉ؕ،ؓؔ؉قؔؽؑؿقؽ؏ؑؓ،ف";
        array[1207][0] = "WnyB6pMDw98axghWN1pAfQ==";
        array[1207][1] = "ؔؕـؐؽ،ؓؒ؉؍ؑفق؉ؐؔ؏ؓ؉ؽ؎ؕ؍؉؍ؑقفؔف؍ؒؾ؍،،";
        array[1208][0] = "VezQtR5MMRS5vAHjxW6wKA==";
        array[1208][1] = "ؑؒؑؾ،ؽ؎ؔ؉ـ؏فؓ؉ؐؿفـ؉ؽؔفؽ؉ـؓؔفقؾق؏فؿ؍ـ";
        array[1209][0] = "ek1iQp4CMk0Wh8APgfCEfg==";
        array[1209][1] = "ؾ؏؍ؾؿ،ؾ؎؉ؒؽؾ؍؉ؐؽ؍ؿ؉ؕؿ،ؕ؉ؒؓؽؕفؽـؐؒفؔ،";
        array[1210][0] = "mgWia97VhzBn8Cxe7yXa2g==";
        array[1210][1] = "ق؏؎ؾؕؓ؏،؉قؔؐؐ؉ؐؓ،ؓ؉ؽؓ؎ـ؉ؒؾـؔؒؓ؍ؕ؏،؍ؕ";
        By1337̵̴̵̢̧̛̤͇̳̘̯̮̤̘̣̲͔̝̬̭ͨͪ͐̂̈́͌́̀ͮ̎ͩ͗̇̕͝͝͝͝͡ͅ();
    }

    private void By1337̵̴̵̢̧̛̤͇̳̘̯̮̤̘̣̲͔̝̬̭ͨͪ͐̂̈́͌́̀ͮ̎ͩ͗̇̕͝͝͝͝͡ͅ() {
        array[1211][0] = "65d8hH/oJLGQ1Mc/BiBn5A==";
        array[1211][1] = "ؾؽ؏ق؎ققؑ؉ؔ؍ؐؒ؉ؐؽقؕ؉ؔـؔؕ؉؍،ؒؐؾ؏ؽ،ؑ؏ؓؐ";
        array[1212][0] = "hug8EksypIrr9XR7nNB/Jg==";
        array[1212][1] = "ؾ؍ؐؒ؍؏ؐؾ؉؏ؓؐؾ؉ؐ،،؏؉ؔؒ؎ؽ؉،ؔ؍ؕؿؽ؎؏ـؕقؽ";
        array[1213][0] = "QaMqmF5u2NtC1ZJ9KcsQ7w==";
        array[1213][1] = "ق؍ؓؽؿ؏؎ؒ؉،ؽق؍؉ؐ؍ؾؽ؉ؾؾ؏ؐ؉فؕ؏ؿؑؕـؔؕؓؿؓ";
        array[1214][0] = "UoTCNC7MkJBzgNDMmDbx1Q==";
        array[1214][1] = "ؔؿـق؏فؐ؏؉ؑؔـؽ؉ؐـؽؕ؉ؔؾفؓ؉ؓؿؒؐؒ؍ؓق،ؐؿؐ";
        array[1215][0] = "BMAXsXB2hWdlmhdYLzQRXQ==";
        array[1215][1] = "ؾؑؕـؿؐ؏؎؉ؐ؍،ؕ؉ؐؒـؔ؉ؾفؐق؉ؿؕؑؽـ؎ؑؑ؏ؾؐؔ";
        array[1216][0] = "VAWs9I2ldoWfuOKHYnj6GQ==";
        array[1216][1] = "ؾففقؕقؽؒ؉ؾؐؓؕ؉ؐ،ؿ؏؉ؾؓقؐ؉؏ؿؽؿ،ؿفقؐفؾؒ";
        array[1217][0] = "TL0jzdMov+mMKPQriW7AOA==";
        array[1217][1] = "ؾؿؒؽؐفؾؔ؉،ؓ؏؍؉ؐؿ؎ؾ؉ؔ،ؕؒ؉ؾققؕؓؒؾؕؿ؏ـ؏";
        array[1218][0] = "60Kw2vkJA9/ywHMVMa9B6Q==";
        array[1218][1] = "؏ؿؿؒ؍؎؍؍؉ق،ؒ؏؉ؐـ؍ؐ؉ؔؕفؿ؉ؑؾؔ؍ؿ؎ؑ؎؏ؕؽؐ";
        array[1219][0] = "YZpaBBBrQYwEY0/JJOVx6w==";
        array[1219][1] = "؍،ق؏؏ؐؒؔ؉ؐ؍ؕ؏؉ؐؓؒؾ؉ؽؐؽؔ؉ؐقؿقفؾؔف؏ؐـ؍";
        array[1220][0] = "3JCEz8cTBvLBDjzKz2tsrg==";
        array[1220][1] = "ؽؓؿ؍،ؑؑؽ؉ؾفؽؾ؉ؐؔؿؔ؉ؾؓؑ،؉ؑ؎ؕؾؽ؎؎ؽؽؒؓؓ";
        array[1221][0] = "8qDNVEUQ2wjBuJ+q0/dAFg==";
        array[1221][1] = "ؔ؍ؑفؿـؽ؍؉؏ؿف؎؉ؐؽقؿ؉ؾؒقؔ؉قؽ؏ؕؒؔؐق؍ؐؐق";
        By1337͉̦͈̦̫ͣͥͯ̓̈́̃͌͜ͅ͏̨͓̱̥̮͕̣͍̙̗͕̌̌ͭͫ̍ͧͦͣͬ̑͐͢͜ͅ();
    }

    private void By1337͉̦͈̦̫ͣͥͯ̓̈́̃͌͜ͅ͏̨͓̱̥̮͕̣͍̙̗͕̌̌ͭͫ̍ͧͦͣͬ̑͐͢͜ͅ() {
        array[1222][0] = "xbcgSerwuRQoyzi/nZNo/A==";
        array[1222][1] = "؎ؿؾ؏ؓؽؑ،؉ؿؽؽ؎؉ؐ،ؽؾ؉ؾ،ؾؓ؉ؑؒ؎؍ؔ،؏؏ؐ؎ؐؾ";
        array[1223][0] = "bloz7XCnnGJQpXtI3z9znw==";
        array[1223][1] = "؎؍ؑ؎؎ف،ؽ؉؎ف؎ؑ؉ؐ؏،ؔ؉ؽق؍ؒ؉ؾ،ؾؾؒـؕؐؑؽقق";
        array[1224][0] = "DNEcDZcZBVnHIqoHQ40AJg==";
        array[1224][1] = "؏ؔقؑقؓفؔ؉ؔؑـؿ؉ؐؾ؏ؔ؉ؽقؒ؏؉ؿـ،؏ؑؕؓؑؒفق؍";
        array[1225][0] = "Fq79+KTCvq9OYoXBdMvK+Q==";
        array[1225][1] = "؏ؑؔؽ؍،ؽؑ؉ف؍فؐ؉ؐؕفؾ؉ؾ؎ؑؓ؉؎ؓ،ؐفؐؿؓـؕفـ";
        array[1226][0] = "ELrOFZfLeGYjxr0zHpQ6zQ==";
        array[1226][1] = "ؓؒ؏ؽؓؒـ؍؉فؓؑؓ؉ؐؐؒـ؉ؽؽؐؑ؉؎ؒـؽؕؐ،؏ؾقـؑ";
        array[1227][0] = "oz1N1DTK5AUYPmEdwbpNjA==";
        array[1227][1] = "ؓ،؎،فقؔق؉ؑؾؔؒ؉ؐؾ،ؓ؉ؔؔؽؐ؉فؔ؎؎،ـؕ؏ؓ؍ؽ؍";
        array[1228][0] = "CHz7w/ywN5a9dXz1hp0uQg==";
        array[1228][1] = "ؽؕ؍؎ؓـؑ؏؉ؑؽؐؾ؉ؐـؾؿ؉ؾؑـؑ؉ؒؐــؾؐؓؔؔـ،ؓ";
        array[1229][0] = "oF4BNwoPOuo2V2HbEMn16g==";
        array[1229][1] = "فؾؐؑ،ؕـؑ؉ؽؿؔ؏؉ؐؑؑؕ؉ؾؾفؓ؉ؿـفؒؑؓؿؕقؔقؓ";
        array[1230][0] = "wRLaRhcdG8QAbninzZkkvw==";
        array[1230][1] = "ؽؕؒؽؒؽفؽ؉ؑؽؐؾ؉ؐؕ؏ـ؉ؾؓق؏؉ؑؐ؍؍ؐؿقؿ؎قؾؿ";
        array[1231][0] = "E0e9yFcdoI+OMEV55c/yMw==";
        array[1231][1] = "ؾؽ؎؎ؔ،ؑؑ؉؏قؐ؏؉ؐـؿف؉ؾ؎؍ؿ؉فؔـؐفؔؑؾ،ف؎،";
        array[1232][0] = "KF7HbvGBN16X7Ylk8/yasw==";
        array[1232][1] = "ف؍،ؓ؏؏ؕؾ؉فؔؾؒ؉ؐ؎ؽؒ؉ؔؓؓؐ؉ؿ؎قففقؾق،ف؏ؿ";
        By1337̶̵̷̧͓̱̗̦̬̦̳̹̺͕̫̠̝̫̽͊͊̀̈ͫ̑̉̉͗ͨ͌͗͂̍͜͞͞͠͞ͅͅ();
    }

    private void By1337̶̵̷̧͓̱̗̦̬̦̳̹̺͕̫̠̝̫̽͊͊̀̈ͫ̑̉̉͗ͨ͌͗͂̍͜͞͞͠͞ͅͅ() {
        array[1233][0] = "sA/FYyqXFSsD/VISrdzS4g==";
        array[1233][1] = "ففؓؓؽؾؾؕ؉فؐؽؒ؉ؐ؏ؑؐ؉ؾ؏ؑؿ؉؍ؾؓؿؓؕق،ؒف؎ؿ";
        array[1234][0] = "t+v7z4IRTnNe4k+grLOHIQ==";
        array[1234][1] = "ؓ؎؏؎ؔؑ؎؎؉ؽؽقق؉ؐ؎قف؉ؕقفؐ؉؎؍؎ؕ؎ق؍ؾؐؑؔؕ";
        array[1235][0] = "r3wWa9yV2cxDA4FKeo8hhg==";
        array[1235][1] = "ؿ،ؽقـؔـؾ؉ؓؿؔؿ؉ؐؓ،ؒ؉ؾؓ؏ؒ؉ؿ،ؿؔف،ؐؐ؍؍قؓ";
        array[1236][0] = "mqv4rM3r/7uWBRIzHvFxjA==";
        array[1236][1] = "،ؐؾؽ؎،؎ؒ؉ؾ؏ؕـ؉ؐ؏ؑق؉ؕؑؔؿ؉ؓؕؑؒؒؐؿؑؿؓفؿ";
        array[1237][0] = "Meu2Ryfb15367Gfz9jINpg==";
        array[1237][1] = "،ؾـؾؕق؎؎؉ؔففؾ؉ؐ؍ؽؑ؉ؕؓؑـ؉ؽـ؍ؽؓ؍؍ؾؒؔ؏ؕ";
        array[1238][0] = "v1Bb3ACSf6wevDTJHjSHKA==";
        array[1238][1] = "ؔؕؒففؽؽؿ؉ـ؎ؓؐ؉ؐؔؿؾ؉ؕؑ،ؐ؉؍ؽف؍ؔؽـفققفؿ";
        array[1239][0] = "ZARq836stEwtAmEkyGSH4w==";
        array[1239][1] = "ؑـق؎ؕ؎ؿ؏؉؏ؿ،ؿ؉ؐققؒ؉ؕق؍ق؉؎ؔؿـ؏،؍؏ق؍؎؏";
        array[1240][0] = "NQTyY+1G8WEs1abIhCDrIg==";
        array[1240][1] = "؎ؿـؑ؍ؐ؏ق؉ؿؓ؍ؑ؉ؐؑؕؔ؉ؽؓ،ؔ؉ؿ؍؎قؓؽ،،ؔ؍ؑ؏";
        array[1241][0] = "Ya+LjUkzPSLjlAejS019Xg==";
        array[1241][1] = "ؿ؎ـق؏ؓؑـ؉ؾفـ؍؉ؐ،؎ؑ؉ؕ؎فؕ؉ؾ؎ؒقفؾـ؏ؑؿؒؓ";
        array[1242][0] = "vUKAcO8PqQYzKPXEmkeTTw==";
        array[1242][1] = "ؔ؍؏ؾ؏ؿ؎؏؉ؽ؍ؒ؎؉ؐؽفؒ؉ؾ؏ؿ؎؉قؔـ،ؓ؏ؒ؏ؿؔ؎ق";
        array[1243][0] = "3rk9KU8qSloEjZsGrJNjdg==";
        array[1243][1] = "؏ؐؿ؏فؔف؍؉ــؓؾ؉ؐـؾؕ؉ؾقؐؓ؉ؔؾؿؒؾؿ؏؎،ؓؓق";
        By1337̶̡̢̧̢̛̮̟͓͈͇̙̹̜̰̰̺̭̬̪̪̺̳̭ͤ̓̇ͯ̌̊ͪ͛̍̉ͨ̽͐ͯ͗̕();
    }

    private void By1337̶̡̢̧̢̛̮̟͓͈͇̙̹̜̰̰̺̭̬̪̪̺̳̭ͤ̓̇ͯ̌̊ͪ͛̍̉ͨ̽͐ͯ͗̕() {
        array[1244][0] = "COuKDjtUAQU11zwAR3bPkg==";
        array[1244][1] = "ؕؽـؑ،ؐـؐ؉ؽ؎ؔؔ؉ؐؽؑ،؉ؽ،ؾؽ؉ؽؒ؏ؾؕؔؑ؍فقؔ؍";
        array[1245][0] = "CfKsRgxbt66lToO4LCLi4Q==";
        array[1245][1] = "قؕؿؾؑ؍ؓؿ؉ؐؕ،ؒ؉ؐؔ؍ؔ؉ؕؽ؍ؿ؉ؾؽــؑؑؒؑؒؒؽؿ";
        array[1246][0] = "EngCLvoBMVwArfOb3l3suw==";
        array[1246][1] = "،ؾق،ؕؐؽـ؉ؾؽؽـ؉ؐؑؾؽ؉ؾؐ؏ؽ؉،ؑـؐؑؔفؐ،ؔفؐ";
        array[1247][0] = "8FV54BHZ8k/MYsph4V1FyQ==";
        array[1247][1] = "قؒؒفـؑفق؉فؿ؎ق؉ؐؒؐؓ؉ؾ؏،ؕ؉ؿ،ؒ،ؓفؾؒف،،ؐ";
        array[1248][0] = "2WlWeLrPGlSUsVX4aOQ96A==";
        array[1248][1] = "فؕؕؑفؐؑـ؉ؿؑؐؿ؉ؐ؍ؑؔ؉ؽق،ؒ؉ؔؽؿؔففؽققؒؿف";
        array[1249][0] = "A8iOXjeOUeDMUS0ldMffeQ==";
        array[1249][1] = "ؐؓقؿؕؿؿؓ؉ؒؾ؍؎؉ؐقؽؕ؉ؕؾ؍ؐ؉ؑؑ؍ؾ؍فؒؔ؏؍ؽؾ";
        array[1250][0] = "A9uBYV9u0Fm7bgOd45+Tvw==";
        array[1250][1] = "ؽؕؓؓؑؿؐؑ؉ؑـؿؿ؉ؐؓؓؒ؉ؾؑؐؿ؉ؽؐؽؐ،ؓؿؕؿؐ،ؕ";
        array[1251][0] = "J95tqnvLqXoiyQGKDX85cA==";
        array[1251][1] = "،ؓؓؐفؔؕؐ؉؍؎ؽ؍؉ؐؑفؕ؉ؾ،ؐؒ؉ـ؍،؎،ؽؓؒؕؽؒق";
        array[1252][0] = "uESD38bxIEQXsSMXNgVGYg==";
        array[1252][1] = "ـؽ؎ؒ؏ؒؑؑ؉ق؏ؿـ؉ؐــؿ؉ؕؓؔ،؉ؐ؎فؕؕؑؐ؏؍ؔ؎؏";
        array[1253][0] = "H/u0AwH7+XY6qx3OQM/Qhg==";
        array[1253][1] = "ؓؒؒ؍؍ؿؐؾ؉ـؿ؎ؔ؉ؐفـؾ؉ؾؒؐؿ؉،ؕ؎ف؍ؾؔؔؒؐؔؽ";
        array[1254][0] = "Mbxe6g2Oc9/pg/+6hkoJpA==";
        array[1254][1] = "ـؐـؔ؏ؑ؎ؾ؉ؕ؍؏ؽ؉ؐؾؒؽ؉ؾؔ؎ؔ؉ؿؒ،ف؏ؒؿؿ،ؕؽق";
        By1337̸̵̷̷̡̢͍̗͈͚̟̣̰̣̭̮̹̼̪̊̌̈͗ͭͧͨ͒̂ͣͪ̏́ͦͤ̈ͧ̆̕͟͡();
    }

    private void By1337̸̵̷̷̡̢͍̗͈͚̟̣̰̣̭̮̹̼̪̊̌̈͗ͭͧͨ͒̂ͣͪ̏́ͦͤ̈ͧ̆̕͟͡() {
        array[1255][0] = "eOtHT54fHe8LECPeKW5gxA==";
        array[1255][1] = "ق،ؒ؎؎ؔؽؽ؉ؓؓؕؕ؉ؐ؎ف؍؉ؕقؐ؍؉ؕؔ؏؍؍ــقؕؐ؎ؽ";
        array[1256][0] = "0o+I/azinWTjEE/dMldGMw==";
        array[1256][1] = "قؑ؎ؕ؎ؾ،ق؉ؔفق،؉ؐ؍،؏؉ؕ؏فؒ؉ـقؾ؍ق،،،،فؐ؎";
        array[1257][0] = "rAH8IzYWfVoXgW2JjIw7QQ==";
        array[1257][1] = "ؔؑفؒ،ؾفؑ؉؏ؔؿؐ؉ؐؾؐف؉ؕف؍ؐ؉ؑؒق؎ؓ؏ؾ،ـؔق،";
        array[1258][0] = "3U/G24N5f9spF878mYdjig==";
        array[1258][1] = "قؒؾ،؏ؿؑف؉ق،؏ؒ؉ؐفؒ؎؉ؕ،قؾ؉قؿؕ؏؏؎؎ؕ؎ؓؿؕ";
        array[1259][0] = "M1ZHd66Qs5nG8Drdaqe1+A==";
        array[1259][1] = "ف؏ؽ،ؓؓؒؔ؉؍ققؔ؉ؐؐؕ،؉ؾ؏ؿـ؉ـ؎ؔؔ؏ؑؾـؕؾؑؔ";
        array[1260][0] = "koZYWLPAzLxKWNoJlbk04Bwr9amEsjbH";
        array[1260][1] = "ؕؔؕؒقؒ،ؽ؉ؽؓؽ،؉ؐؾ؎؍؉ؽفؓف؉ق؏ـؔؓؐؐؓ،ؾ؍،";
        array[1261][0] = "shUd7VBYoD4jAP/zNRQMjw==";
        array[1261][1] = "ؐفؑؽؽؒؔـ؉ؒؿؒؿ؉ؐفؓ؍؉ؔـ؎؍؉ؕفؓؓؕ،؎ؑ؎ؕ؍ؔ";
        array[1262][0] = "lBILNBPRcZwDAXmB8inhvg==";
        array[1262][1] = "فؓقؕ؍ؕؕ؍؉؏ـؾؿ؉ؐؑق؎؉ؽؔؐؔ؉،ق،ؑؓؔؕؐ؍ؑؓؕ";
        array[1263][0] = "/7V0NIz4VAwtnWvZNsgGkvnuLKYj2h1e";
        array[1263][1] = "ؔؐؔؑؿؿؾؿ؉ؾ؎؎ف؉ؐ؍؍ؐ؉ؔفؿؒ؉؎ؿقفقؕ؍ؕؑؾؓؕ";
        array[1264][0] = "pfjAQ/cUAJ0wgJrEdLoZD9E3kRfYNJ6x";
        array[1264][1] = "ؐؓـؾق،؍ق؉ـ؎ؑؾ؉ؐـؕؑ؉ؾقؒؾ؉ؔؿـقؓ؎؍قق؍ؕ،";
        array[1265][0] = "pLR8xYgaIIIobIHMn2ipSQ==";
        array[1265][1] = "قؽؾؾ؏ؐؐؔ؉،ؾـؓ؉ؐ،،ؕ؉ؽؿؿؾ؉ؐؕقؐؽؽؿؿؿؒؓـ";
        By1337̨̹͕ͬ̌ͯ͜͏̸̡͉̰͖̜̝̬̭̔̈ͯ̌͑̆̍͒ͧ̓́́́ͯ͂̕͜͝͝͡͡ͅͅ();
    }

    private void By1337̨̹͕ͬ̌ͯ͜͏̸̡͉̰͖̜̝̬̭̔̈ͯ̌͑̆̍͒ͧ̓́́́ͯ͂̕͜͝͝͡͡ͅͅ() {
        array[1266][0] = "MRQdshTKk/jwggNhLYyveA==";
        array[1266][1] = "ؕ؍ؑ؏ؿؕؓؐ؉ـؾؾؕ؉ؐؾ؏ؿ؉ؾؽؔ،؉ؽف؏ؿؕؾــف؍ؕؿ";
        array[1267][0] = "WsrUvyFoyP1Pvm2GFk0qmA==";
        array[1267][1] = "؏ؕؑ،ؕؒؐؓ؉؏ؔؾ؏؉ؐؓؾ؍؉ؽؓ؏ؐ؉ؒـ؏ؑؓف؍ؕفف؏ؔ";
        array[1268][0] = "1kt2pK2s4xiUsB7G6qJXJA==";
        array[1268][1] = "ؐؒــؓ؎ؔؕ؉؍؍،ؕ؉ؐ؍فؔ؉ؽ؎ؽ؎؉؎ـ،ؽؐؔ؎ؾ؍ؔفؑ";
        array[1269][0] = "8gXGEGnZiG/C6Hmp2qvKKQ==";
        array[1269][1] = "ؾؕؾق،ؑؿؑ؉ـؒؔؿ؉ؐ؏؍؎؉ؽؽؒؿ؉ـ؎قؾ؎ؑ،ؕؿ؏فؔ";
        array[1270][0] = "AbXTiBAaJADaX4RiQs9x/g==";
        array[1270][1] = "،ؾ،،ؔؒفف؉،ؽ؎ـ؉ؐؔؑ؏؉ؾف؎ؑ؉ؑؓ؍ـ؏ــؑؑقؕق";
        array[1271][0] = "fcwPrtcQCop9jUBG5vRPrA==";
        array[1271][1] = "ؓؑ؎ؑ؍ؾـؿ؉قـؑؾ؉ؐؑفـ؉ؕـؑؓ؉ؔؿؔؐؔؓ؏ؑ؏ؽؑؒ";
        array[1272][0] = "kB8+BrbUkhiZSkbHNWQA3Q==";
        array[1272][1] = "ؒؐؽ،فؿ؏ؾ؉ؐقف؎؉ؐ،؎ؿ؉ؕؐؽ؎؉ؔ،؏ؑ؍؍ؔؒؐؔقؿ";
        array[1273][0] = "8xNHVNMeYxGj5Oq2DQaIaQ==";
        array[1273][1] = "ؔـؐؑؾ؏قؒ؉ؐ،ؾ؎؉ؐؔـؒ؉ؽؒ؍؍؉ؓ،ؽؔؒ؏ؑؐؾؐؑف";
        array[1274][0] = "9Do+oK3el3liVeLZ+b4u5A==";
        array[1274][1] = "ؽ،ؑؒ؏؎ؾ؏؉ؿؒ،ق؉ؐؔؕؒ؉ؔفؔـ؉؏قؕققؓفـؿقـق";
        array[1275][0] = "b3007dqp+ZXdVoUgVunjCuFC3T+GxkQ5";
        array[1275][1] = "قؔؑؐؒؾؕؓ؉ؽؒؔف؉ؐـ؏ـ؉ؕؕؐؒ؉ؕؔؒؾؽ؍قؑؽـ،ؕ";
        array[1276][0] = "CCiK5o1gPA+ekqZjNx48oQ==";
        array[1276][1] = "فقؑؾ؎ؿؕ؏؉،؎قؽ؉ؐ؍؏ؓ؉ؾف؏،؉،ق؍؎ؑ؍ؐ،ؒ؍؏ؐ";
        By1337̵̸̛͎͔̲̪͙̭̞̮̬̘͇̠̮̟̩̗̞͇͖͐̔͛̔̿ͣ̏ͧ̉ͭ̃ͣ́̎͋͘͡͠();
    }

    private void By1337̵̸̛͎͔̲̪͙̭̞̮̬̘͇̠̮̟̩̗̞͇͖͐̔͛̔̿ͣ̏ͧ̉ͭ̃ͣ́̎͋͘͡͠() {
        array[1277][0] = "UnKkQi94NjSv3iMoHBnTXg==";
        array[1277][1] = "ؾقؓـ؍ؽؿ،؉؍ؑؕق؉ؐفؓؓ؉ؔؽؽؑ؉ؔؔؿ،ـؕؽ،ؐؓؔؑ";
        array[1278][0] = "3LI0Ql2YMe8MKLT2almOCw==";
        array[1278][1] = "ؒـؒ؍ـؕ؎ؔ؉،فؓؕ؉ؐؕؾ؍؉ؔ؎ؑف؉ـؾؾؑؓؒؽ؏؏قف،";
        array[1279][0] = "5Jr1PvpadzbnOL7Gsm3T8Q==";
        array[1279][1] = "ؕؒ؎ؔقؽ؏ؑ؉ؽ،ؾ؎؉ؐ؍ؐ؎؉ؽؔؿ،؉؍؍ؿفؓ؎؍ؐؓؒـ،";
        array[1280][0] = "qd/2ox35lg4rdKg1FeENNg==";
        array[1280][1] = "؏ؕؔفؐقـؐ؉ؿؾؾؿ؉ؐ؎ؿف؉ؾؐؔؓ؉ـؒؽفؐ؎؎،؍،ؕؽ";
        array[1281][0] = "+K3+45Y7G2ln3Obrj0ipkw==";
        array[1281][1] = "ؽف؏ؐ؍ؽ،ؒ؉ــؿـ؉ؐؐؔؐ؉ؾؒؓؐ؉ؓؒـؐ؏ؓ،؏ؓؒؑؑ";
        array[1282][0] = "uQgHtmhxcs0fQ9/iM56zug==";
        array[1282][1] = "ؽؒؿ؍ـقؿؕ؉ؓققؔ؉ؐؽ،،؉ؔؒ؎،؉؏ؿؿؓـؾؾؔؐ؍قؕ";
        array[1283][0] = "J0KipL9C4ezfMPtINWhusQ==";
        array[1283][1] = "،ؔؽ،ؿؔؑؕ؉ؐ؍؍ؕ؉ؐ،ؔؽ؉ؽؑ؏؍؉؎ؔ،ؾؕ؏ؓؑؾ؏ؽؕ";
        array[1284][0] = "wbYAENqZyy5ifY1T66cWle+5JIqWagII";
        array[1284][1] = "ؔفؾؿؑؾ؍ؒ؉ؐؒ،ؑ؉ؐؿؕؔ؉ؽؓ؎؍؉ؐفؕـؿـ؏فـؕقؑ";
        array[1285][0] = "SfmiPuslgWCQb0LNeik2Yw==";
        array[1285][1] = "،ؓـؿؓؒؐ،؉ققـ؎؉ؐؽ؏ؿ؉ؕؽؔق؉ق،ؓؽ؍ــقؾقفؾ";
        array[1286][0] = "YWSi8INGC6ptqLUIwaCp3Q==";
        array[1286][1] = "ق؎؎؎ؽ؎؏ؿ؉ؕؓؒ،؉ؐفؒ؏؉ؾـؓؕ؉ؽؽ،ؕفؐؐــؿؓؓ";
        array[1287][0] = "GY/t2LjqfW2JxZhxQiVpHg==";
        array[1287][1] = "ؓؓق؎ق؍ؾؑ؉ؔ،،؎؉ؐؓؾ؎؉ؕؓ؎؎؉ؑف؏قفق؏ؔق؏ؕؽ";
        By1337̸̸̧̨̢̯̮̳͖̠̦̰͙̬͉̳͔̔ͪͫ͐̊͆̓̄̓ͪͮͤ̒ͣ̏̐̎ͣ̀̽͢͞͝();
    }

    private void By1337̸̸̧̨̢̯̮̳͖̠̦̰͙̬͉̳͔̔ͪͫ͐̊͆̓̄̓ͪͮͤ̒ͣ̏̐̎ͣ̀̽͢͞͝() {
        array[1288][0] = "dXYwn3PfRAefe6jRi59LMg==";
        array[1288][1] = "ؓؓقؒؽؐؕف؉ؽؾؑ،؉ؐ؎؍؏؉ؔؾ؍ف؉ؒ؎ؽفقؐؓؔ؎؏؍ؑ";
        array[1289][0] = "76YHiUzv4xq131KyvwrgWQ==";
        array[1289][1] = "ؕـؓ،ففؽ؍؉ؒقـؐ؉ؐؐؔـ؉ؾؕؑؔ؉فؓؔ؎،ق؎ؕؒفؔؓ";
        array[1290][0] = "IDahErUS9Wh8ILLG8zwoUQ==";
        array[1290][1] = "ؓ،ؽؔؾؒؔؾ؉ؓـ؏،؉ؐؐؔؾ؉ؕؐؽ؍؉ؔؾ؎قؿ؏ؓؐـؑؐؔ";
        array[1291][0] = "QhB7ITfD5tvboL74bIZrHA==";
        array[1291][1] = "ؕؾ،ؿؽفق؍؉ؒ؍ؾؾ؉ؐؒؽؕ؉ؽؑؒؽ؉ؿق،؍ؽؒ،؎ـؕق؍";
        array[1292][0] = "KVl1K6XYSAWRfps/7IVj6w==";
        array[1292][1] = "ؽؽؕؿقؕؿؓ؉؍ـؔ؏؉ؐؓق؏؉ؕ؍ؒؽ؉قؿ؎ؕ،ؿؒؒ؎ؑف؍";
        array[1293][0] = "SV4wntv1pzvI09A1z740Zw==";
        array[1293][1] = "ؔؽ؎ؕؓ؎،ـ؉ؾؾـ؏؉ؐؿ؎ؓ؉ؔفـؓ؉؎ؐفؔ؏،ؾؕؿؾؕف";
        array[1294][0] = "21+xgS2V9z7Y4dkEMDHC8w==";
        array[1294][1] = "ـ؍قـؽؑ؎؎؉ؒ؏ؿؔ؉ؐ؏ؔ،؉ؽ؍؏ؐ؉؏؎ؐؐؔؒؾؿؽؕؕؓ";
        array[1295][0] = "BtbdWI5fThOa0SBjqrpiHA==";
        array[1295][1] = "؏ؿ،ؔؔؐق؎؉ؓؓؾؿ؉ؐ؍،ؑ؉ؾ،؍ؐ؉ؑؔ،،ؐ؍ؽؔؑؔؔ،";
        array[1296][0] = "ZS7IcrafkkXVR77gyXAagw==";
        array[1296][1] = "ؿ،ؓؽؾ،ؽق؉؍ؕؐق؉ؐؓؾـ؉ؽؔؓؔ؉ؒؒؿؔؒؿفؒ؏ؐؕؕ";
        array[1297][0] = "pits9MtEtFf4CrFaKITpng==";
        array[1297][1] = "ؕؓفؔؔؾ؍؏؉قفؑؑ؉ؐفؾؑ؉ؔؽؓ،؉ؿؒؒؽـؔففؓؾـؿ";
        array[1298][0] = "Ky1LJ9B85GkUj4WrjeCR/w==";
        array[1298][1] = "؍ؿؒؐ؏ؿؓؓ؉؍ؑقؿ؉ؐفؕ؏؉ؔؓؓق؉ؒ؍؍ؽ؍فؾؒفقؕ؏";
        By1337̸̴̴̴̢̛̬̹̫̝͕ͬ̓ͦͫ̋̄ͩ̓͗̓̈́̇̌͘͝͏̶̴̙͕̺͙ͦ́̏ͦ̚̕͜();
    }

    private void By1337̸̴̴̴̢̛̬̹̫̝͕ͬ̓ͦͫ̋̄ͩ̓͗̓̈́̇̌͘͝͏̶̴̙͕̺͙ͦ́̏ͦ̚̕͜() {
        array[1299][0] = "KjDPIp6ElpnyFTPgFeKtWQ==";
        array[1299][1] = "؏،ؓؑؾؕؿـ؉ؑـ؎؎؉ؐؓؒـ؉ؕفؾ،؉ـؔـ؍ـــقؕفقؿ";
        array[1300][0] = "65foGLvINw2PmH4SqlSKQYRq0EyTcdgX";
        array[1300][1] = "؎ؔؓؑؔفؐؔ؉ؾؒؑ؍؉ؐؽقؿ؉ؾؐؔؐ؉؎ؔ؏ؾقؽؓقؑؾؔؒ";
        array[1301][0] = "Ia3CIeECSSJliXGiudv2cw==";
        array[1301][1] = "؎ؑؕؓ؍ؕؿ؎؉ققؿؕ؉ؐؕ؎،؉ؾ؏ؑؐ؉،ؾفؔؕ؎؍ؾؓؓؔ؎";
        array[1302][0] = "klXHauICupkemrgrBwRBhg==";
        array[1302][1] = "ؽ،،ؿ؍؏ؔؾ؉ؑؒـؿ؉ؐؐؕـ؉ؽؔؓؽ؉؍،ف؏؏ؿؽ؍ؕؕ؏،";
        array[1303][0] = "5qqZRBFACdRGT0fEqUZObQ==";
        array[1303][1] = "ؐفؓؕفؑؐؽ؉ؓ؏؏،؉ؐؔ؍ـ؉ؕف؍ـ؉ؾؓ؎ؑؑؒؑؒؓ؍،ؕ";
        array[1304][0] = "FLcH3IEcWyZmZIg5fE23tg==";
        array[1304][1] = "؏ـفـ؍؎ؽ؍؉ؒؓؾؾ؉ؐؽؓ؎؉ؔ؏؎،؉،ـ؏؍ؐق،،قف؎ـ";
        array[1305][0] = "AfPHn5mjTWzXd/3BAmxnPz2Z4POUKv2P";
        array[1305][1] = "،ؑفؾؔؕؐف؉ؽـؒ؏؉ؐؐؑ؍؉ؕؑؾؑ؉ؐؾفؒؐؔؔؽؔؔؐ؍";
        array[1306][0] = "TL6CsmyBDHLiWIN7TCYvQg==";
        array[1306][1] = "ؿ؍ؑؑؐؑؓ؍؉؎ؑقف؉ؐؓ؍ـ؉ؕؐ؍ؕ؉؍ؾؓؓؐؒ؎ؑؓؿؒؽ";
        array[1307][0] = "olo9r/R32JbU1ng8QM5MuQ==";
        array[1307][1] = "ق؎ـ؎ؾؕؔ،؉ؔؒ؎؏؉ؐؕـق؉ؕ؏ؽ،؉فؓ،ؕؿـؒفؐـؽؽ";
        array[1308][0] = "0mKXUmmvfM35RX/rKRYuMw==";
        array[1308][1] = "ؔؒؑؓؒؒ،ؿ؉ـؾ؎ف؉ؐ؏ؐؿ؉ؾؐ،ؐ؉ؓؒفؾؐؔقؔؾؓ؎ؽ";
        array[1309][0] = "WwTY4waR39Id8/wk6men/g==";
        array[1309][1] = "ؿ؏ؔؒؓؔقق؉؍ؐ؍ؒ؉ؐـؾؒ؉ؽقؕؿ؉،؏ؽ؏ـفف،قؿؕـ";
        By1337̶̴̛̫͈̠̭̟͍̘͔͍͓̦̩͚͙ͫ̇͗̎̑͆ͭ́ͮͦͦ͋̋ͩ̈̌̌͛͆ͫ͜͜͞();
    }

    private void By1337̶̴̛̫͈̠̭̟͍̘͔͍͓̦̩͚͙ͫ̇͗̎̑͆ͭ́ͮͦͦ͋̋ͩ̈̌̌͛͆ͫ͜͜͞() {
        array[1310][0] = "KbtuqQPp1Ro/7z3TaWLBQg==";
        array[1310][1] = "ؒ؎،؏؎ؒؒ؍؉ؾؽؽ؍؉ؐؾؔـ؉ؾؔ؏ق؉ؕقؒؕؿؽؑـ؍ؾـ؎";
        array[1311][0] = "kgfvsdMmxnnNp3ELGU/wkg==";
        array[1311][1] = "ؾفف؏،قؕف؉ؓ؍ؑؽ؉ؐ؎،ؑ؉ؾؑقؕ؉؏ؕؐؾفؑؕؐـؔؿ؎";
        array[1312][0] = "9QVa2ju8C7UuMKI1xVFjpg==";
        array[1312][1] = "ؕ؏ؐؔؒؑؐؑ؉ؑـؓؕ؉ؐقؽؔ؉ؾقـؾ؉؎ؾؑؾؔ،ؐؿؒؔ،؍";
        array[1313][0] = "Mo1DD9G5TtUgZd4tvxP6ZQ==";
        array[1313][1] = "؎ؐؾؽقؑؒؑ؉ــؑؕ؉ؐقؔؓ؉ؽؾقف؉،؎ؓؔ؎فففؑؑؒف";
        array[1314][0] = "lIZGUVch/0Pg8bjJJx06jQ==";
        array[1314][1] = "ـؿؾ؍ؒؔؕؾ؉،،ف،؉ؐ؎ؑؔ؉ؔؓـ؎؉ؽ،؎؍ؔؽ؎؎ؕؔفؑ";
        array[1315][0] = "esudNKyoBZ++tSRwTQtkUg==";
        array[1315][1] = "ؔؾؽ؍ؽقؔؾ؉ؔؓؾؔ؉ؐ؍ؕؿ؉ؔؾؒؓ؉؍،ؓؒ؍ؔؿ،ؒف؏ؕ";
        array[1316][0] = "tbIHERjjGMobiLYF4I3KAg==";
        array[1316][1] = "ؓؒ؍ف؏؎ؑؑ؉ؾؕؾؒ؉ؐـؽؒ؉ؔؑؿ؍؉فؿ؍،ؽؾؿؾــؽؕ";
        array[1317][0] = "uhqKHPwSfrxppIxSmfck1Q==";
        array[1317][1] = "ؾؿقـؐؕ؏ؿ؉ؑقؾق؉ؐ؎ؽ،؉ؾف،ؿ؉قؒؒؔ؏ؿؐفففؕ؎";
        array[1318][0] = "OMAMiSmvVuvOuhl0drgHTQ==";
        array[1318][1] = "ؕؒؔؽ؏ؾـؓ؉ؿؐ؎؏؉ؐؿؽ؍؉ؔؾؿؕ؉ؒؽقؑؾؓ؏ؾفـ؏ؿ";
        array[1319][0] = "rMH1MHY6DESu4jDQ5CePsA==";
        array[1319][1] = "ؒؽقؿؽؕؓف؉فف،ؽ؉ؐؑ؍،؉ؔؿؐؕ؉،ؒؔؿؒـ،ؓؕؿفؔ";
        array[1320][0] = "A6jw0d+n+jQSphPurAx0Gw==";
        array[1320][1] = "ؒؿؾ؎ؒقؽؔ؉ؽفؐؒ؉ؐؓؽؽ؉ؔؿؾؐ؉؎ؓؿؑفؕ؎ؽ؎؍ؒ؎";
        By1337̢̢̦̤̘̗̯̠͍̲̠̣͖̼͔͋̌̀̋ͦ̏ͭ̒̇̓̌ͯ̒̓ͧ́͌͐͂ͨ̕͡͠͞ͅ();
    }

    private void By1337̢̢̦̤̘̗̯̠͍̲̠̣͖̼͔͋̌̀̋ͦ̏ͭ̒̇̓̌ͯ̒̓ͧ́͌͐͂ͨ̕͡͠͞ͅ() {
        array[1321][0] = "hI8vkMpAQXya57Uc5FR3ag==";
        array[1321][1] = "،ؒ؎ؾؒـؔؔ؉؍ؐؿـ؉ؐؔ؍ؒ؉ؕؕـ،؉ؽـؽؐؒ؏ؒؿ؎ؒقؒ";
        array[1322][0] = "PW4yWUpX+Rn/btDjVRJSuQ==";
        array[1322][1] = "؏؍ؿق؍ؐ،ق؉ؽؒؔؔ؉ؐؿؑؽ؉ؕ؍ؽـ؉قؕ،ؿؾـؐـؾؿ؏ؐ";
        array[1323][0] = "MkcnmtIeBKWgf+Zxi4dFmA==";
        array[1323][1] = "؍ف؏ؐؽ،ق،؉ؿ؍فؓ؉ؐؒ؏؍؉ؾـ؍؏؉ؽؐقؓ؎ؑؕقف؍ؑؐ";
        array[1324][0] = "BoOFcEYP3+GWOZO2IRf4hQ==";
        array[1324][1] = "قؑ،؏ؑؕؿ؍؉ؓؕـ؏؉ؐـؐؔ؉ؾؕؑؾ؉قؕقف،ؒ؎،،ؕؑ،";
        array[1325][0] = "iS71Ey0/LtLYIFAbs/0cOA==";
        array[1325][1] = "ؑؿؔؓؽ،،ؓ؉؎؎قؓ؉ؐؔفؐ؉ؾ؍ؿ،؉ؐؓفؔؓؾؒؔق؏ؔق";
        array[1326][0] = "Txz6vjumoFpzMVzWAw9GeA==";
        array[1326][1] = "ؐ؍ؐؑؔؒؒـ؉ؒق؎ؾ؉ؐؐؾؓ؉ؾؓؑؒ؉ؒ،ؽؽ؎؍ؿؾق؍ؕؾ";
        array[1327][0] = "ePRFboo2s2ZgaR7rFNUU4w==";
        array[1327][1] = "ـؾ؎؍قؑؐؽ؉ؔؐؔ،؉ؐؿ؎ف؉ؕ؎ؕ،؉؏فف؎ؿؐؽؿؾؓؾؒ";
        array[1328][0] = "KGEzBNoxdOHaAUmtWn1hNw==";
        array[1328][1] = "ؾؐؕــــؑ؉ؔققؽ؉ؐؒؑ؏؉ؽ؏ؿ،؉فففؐؾق؍ؓؑؕؒؾ";
        array[1329][0] = "WF7XW/kpLZoEq3jsZMt8NQ==";
        array[1329][1] = "ؕؒؒؑق؏،ؾ؉؎؎ؔؽ؉ؐ؎ؓق؉ؽؓ؏ؒ؉ؐؑقؒ،ـ؏ؽؾؓـ؍";
        array[1330][0] = "Vp6HuUbQRJFzoLLcQsVPng==";
        array[1330][1] = "،ؕؾؿؕقؒ،؉؎ؾـؿ؉ؐ؏؍؏؉ؕؒؕؿ؉،ؕق،قؒؿ؏ؓؒ؏؎";
        array[1331][0] = "T6qaVzsC0mVBOruNMRwQyw==";
        array[1331][1] = "ؕ،ؾـؓ،ؽق؉قؐ؍ؓ؉ؐـ؏ؿ؉ؾؽ؏؏؉ؽفقؕ؏؏قؾ،ؓ؎ؑ";
        By1337̶̸̛̺͖͙̦͖̫̣̞̫͇̞̭ͯ̎̍̏ͪͦ̏ͩ̓̽̀̾ͧ͌́ͣ̉͑͛͘͢͠͞͞ͅ();
    }

    private void By1337̶̸̛̺͖͙̦͖̫̣̞̫͇̞̭ͯ̎̍̏ͪͦ̏ͩ̓̽̀̾ͧ͌́ͣ̉͑͛͘͢͠͞͞ͅ() {
        array[1332][0] = "LAJW9EdASlBFNKuLDhr8DUEx8kKFqSHI";
        array[1332][1] = "ؔ؍؏ؒؽ؏ؿؒ؉ؔؕؾ؎؉ؐؕؔؽ؉ؕ؏ؾؑ؉ؽفؔؒؽؒؕؕـؽؒؽ";
        array[1333][0] = "nZqaXCZzNkbpXqLqAJ2CEA==";
        array[1333][1] = "؎،ؿ؎ؒقؽؑ؉قؿؿؾ؉ؐؕ؏،؉ؽق؍ؔ؉؎ؾؑؿ؎؏ؔ؏ؑ؎ؓ؎";
        array[1334][0] = "rpWhCIz3L5zKvFrpwuhNjQ==";
        array[1334][1] = "فف؍؍ؔؓقؒ؉ؕؐ،،؉ؐ؍ؽق؉ؾقؐؔ؉قؓفق؍ؿؽؐؕفؽؐ";
        array[1335][0] = "pzBL3i6hO+/CRZ3dR6dlDw==";
        array[1335][1] = "ؐ؏؏ؾؔؓ؏ؔ؉ف؏ؽؒ؉ؐؽؕؑ؉ؕؽ؎ؾ؉؎ؒؾؒفؽـؔ،ؔؕؒ";
        array[1336][0] = "Zqv0wXoVUycorzcOs+nw2g==";
        array[1336][1] = "ؾؽ؎؎ـؒ؍ؾ؉؍؍ؐؔ؉ؐف؎ؐ؉ؾ؏ؿ؍؉ؽؔؐقف؍قؕ؏؏قؽ";
        array[1337][0] = "ItGSd/gMH0eTkDNyKy/f9w==";
        array[1337][1] = "ـؐقؓؑؕؑق؉ؓؿ؍؏؉ؐؕؿؓ؉ؔؓؿؓ؉ف؎ق؍؏؎ؕؽؐؒؒؓ";
        array[1338][0] = "WsJl1QzbMRLpreHrn1GjiA==";
        array[1338][1] = "قؕؾـ،ؒ؍ؿ؉،ؿؔ؏؉ؐؔؾؓ؉ؽؿـؔ؉ؑ،ؕؒ،ؾ،؍،ؕؑ؍";
        array[1339][0] = "+wCSvMHYSqJlleslB1G+9A==";
        array[1339][1] = "ق؎،ؒؕؾ،ؽ؉قؓ؍ؔ؉ؐؑفؕ؉ؕؐ،ؽ؉؎قفؾ،ؒـؾؓـؑف";
        array[1340][0] = "t7RDuyJnqe5mFh0ib3MfWA==";
        array[1340][1] = "ؑؕ؏؍ؾؔؕؐ؉ؒ؏فـ؉ؐؒ؏ؽ؉ؔقؾؑ؉ؾؔـقؒؔق،ـ؍ـؑ";
        array[1341][0] = "B2G0N0qjEa6Hqoqdkgs5TA==";
        array[1341][1] = "ؒـ،ؒؿؕؐؑ؉ؒـؽق؉ؐؔؑ؍؉ؔ؍ؑؿ؉؎؏ؑؑ،ؔؓؕ؍ؕفؾ";
        array[1342][0] = "0vioo+J0gzc00r6JsFzrUw==";
        array[1342][1] = "؏؎ؿؒقؐ،ق؉ؐ،ؽؾ؉ؐ؍؎ؿ؉ؔ؎ؒؔ؉ؒـؐؽؽؑؑ؏ؾ؍؍ؿ";
        By1337̶̶̨̝̭̥͉̮͉͙̬͖̬̦̼̱ͥ̃ͣͩ͋̔͌ͧ̐͌̑̆ͩ́̂̈́̿̋͑̇́͢͜͠();
    }

    private void By1337̶̶̨̝̭̥͉̮͉͙̬͖̬̦̼̱ͥ̃ͣͩ͋̔͌ͧ̐͌̑̆ͩ́̂̈́̿̋͑̇́͢͜͠() {
        array[1343][0] = "Djlt2jV/vIekqbI2F9nwEg==";
        array[1343][1] = "ؿؾؾؐ،ؐؽؽ؉ؿ؍فؑ؉ؐؓفق؉ؽؽؿ،؉ؾؒق؍ؔ،،ؽؑ،ؾؑ";
        array[1344][0] = "G9nv20uESolOk7nWJwjwQQ==";
        array[1344][1] = "ؑؑ؎ؕ؍ؾؿؕ؉ؕؑؽؿ؉ؐؔؔـ؉ؾؾؔؑ؉ففؒف؍،،ـفؿؐ؎";
        array[1345][0] = "cGWmMqiCxwRM7hxC4BDNiw==";
        array[1345][1] = "ؿ،؏ؑـؔؐ؍؉؏؎،،؉ؐ؍،ؓ؉ؽؐؓؓ؉ؿؕ؍،ؒؐـؑؽؑؿؐ";
        array[1346][0] = "nFX6UPh1rYq5D+JCbW5zIA==";
        array[1346][1] = "؎ؑؕؽ،ؽؓؔ؉ـؑؒ؍؉ؐؐؕؒ؉ؾؽقف؉فؑؒؔؔؿؑؾؒؒـف";
        array[1347][0] = "53W+cSGtB4PR0dLXPpFrEQ==";
        array[1347][1] = "ؐؐؓفؕؓـؿ؉ـ؎؍ق؉ؐؒـؕ؉ؾ؎ؕؒ؉ؓفؿؿؒؐـؕؒؓؔؑ";
        array[1348][0] = "P6u4nRNzp2X88s+hv6dfvg==";
        array[1348][1] = "،ـؐ؎؏ؕـف؉ؿؑؕ؏؉ؐؑؿؔ؉ؕؕؔؔ؉؏،ؐؐ،ؓؕؓق؍؍،";
        array[1349][0] = "/fzXsscY7RNmvgzfRr14UA==";
        array[1349][1] = "ؽـ؏ؐؔؽؓـ؉ؒف؎ؓ؉ؐؿؿؓ؉ؔؾفؾ؉،ؿؓ؍ؑؾؿ،ؾـ؍ف";
        array[1350][0] = "kTUf7G477dket2SLLPgzzg==";
        array[1350][1] = "ؽ؎ف،؎ؒ؍ؔ؉ؽ؏ؒـ؉ؐؕؔؿ؉ؕف،،؉ؑؕفؽؿؿؿ؎ؾف،؏";
        array[1351][0] = "HPaQARPpxn2jHU0yAvN2UA==";
        array[1351][1] = "ؿؒ؍ؔفؽـؓ؉ؓؾؔؿ؉ؐـؿف؉ؾؽ؏؏؉ؿ؏؏ؿؔؑف؍؍فؑـ";
        array[1352][0] = "rvrGYrUuOLZRFu1LHzPRFQ==";
        array[1352][1] = "فؾؽؑ،ؓقف؉؍،ؾ؍؉ؐؽ؏ـ؉ؾؽف؏؉،؍؍قؔؒؿ؎ؿؾ؎ؓ";
        array[1353][0] = "PZQKscn4GQCaXNmX0A6x5g==";
        array[1353][1] = "؎؎ؔؽ؎ؽؒؐ؉ـؒـؔ؉ؐؾؕ؏؉ؕؿ،ؒ؉؏ـؒؿق؍؏قؾق،ؕ";
        By1337̧̧͙̤͍͚͖̤͚̻̪̺̬͖̜̙̦̣̥̽̀ͬ̽͐̏ͪ͒ͤ̅ͣͯ̌ͭ̎̾̀͜͞͞ͅ();
    }

    private void By1337̧̧͙̤͍͚͖̤͚̻̪̺̬͖̜̙̦̣̥̽̀ͬ̽͐̏ͪ͒ͤ̅ͣͯ̌ͭ̎̾̀͜͞͞ͅ() {
        array[1354][0] = "vkpBW0Nn2uSk8At2tBsU+g==";
        array[1354][1] = "فؐؕ؎قفؐؐ؉؎ف؎؏؉ؐ؎،ؓ؉ؾقؔؑ؉ؔق؏ـؐق؍؏ؕ،ـ؏";
        array[1355][0] = "tCFj4cix2his4gTWCTTDgA==";
        array[1355][1] = "ؾؓؐ؎،ؿ،ؒ؉،ؽؐ،؉ؐ؏ؓ؍؉ؔؽـؕ؉ؾــؔؽؽـؕؿؽؓؕ";
        array[1356][0] = "vutV1/vfN1w9m/IcqrU/9Q==";
        array[1356][1] = "قؽ؏ؓؽؕؾف؉ؓؿؔؾ؉ؐ؍ـؒ؉ؾؓؓـ؉ؔؽؔؓؐؾؾؑ؍،ؓؾ";
        array[1357][0] = "ySC1zGR93xRbPlhi3FoAqg==";
        array[1357][1] = "ؑؑ،ؾؒؕؽؑ؉ؿؓؿق؉ؐؒق؎؉ؾقؕؿ؉؎ؔ؏؏قؐ؏؏؎؎فؾ";
        array[1358][0] = "a+7yjcoOPKhjy1vmZ8TMbQ==";
        array[1358][1] = "؍؍ؔؑؓؕ؍ؕ؉،ؔـ؍؉ؐـؑـ؉ؽؔ،ؒ؉ؿؑف،فؓؐؔق؎ؽؕ";
        array[1359][0] = "TJXluyjs7SPTC8UmImjKgg==";
        array[1359][1] = "؎ـ؍،؍ـؒؔ؉ؒ؏،،؉ؐؑؒؒ؉ؽؿؔؽ؉ؕ؍ـؿؑ؍فقؾفؑؑ";
        array[1360][0] = "kIz6W5Qfb1sNsqKpH/Eqvg==";
        array[1360][1] = "ؿ؍ؓ؎؏ؔؐؔ؉ؓ،ؿ؎؉ؐ؍ؽؽ؉ؔفؔـ؉ؿف،؎ؑ،ـؿؓؕ؎ف";
        array[1361][0] = "umecj+WSdwCNO1YQYb4P+g==";
        array[1361][1] = "،؍ؓؿقؓ؍ؓ؉،ؿ؍ؒ؉ؐؾؾ؏؉ؔؿق؏؉ؾف؍؎ؔف؍ؔؿؓؔ،";
        array[1362][0] = "XFyHlnjY0849c+dFl+EHKA==";
        array[1362][1] = "؍ؑؔؑقفؐؽ؉ؾؕؿؽ؉ؐقف؏؉ؕؾؒؿ؉ف؍ؽ؍؍ؓـؿؔ؏؏ف";
        array[1363][0] = "3suHqRkdsglTfMP3r8uxWA==";
        array[1363][1] = "ؾؒـؔؑؑؔق؉ؓؓؑق؉ؐؽ،ؒ؉ؾؿ؏ق؉ؒ،ؒ،ؒ؏،ؔقؒ،ؒ";
        array[1364][0] = "EUfqs6LWSprFs5YyPhdKNQ==";
        array[1364][1] = "؏ؕؔؒ؎ـؒؓ؉ؾؽؿؒ؉ؐـفـ؉ؔفق؍؉ؐؕ؏؎،ــؐـقؑؕ";
        By1337̨̳̹̰̳̅̓̄̇ͨͬ̀͗͏̧̺͚̫̮̱͖̙̭̹̩̎͑ͪ̆̅̈ͣͥ̌̿͂̒͘̚ͅ();
    }

    private void By1337̨̳̹̰̳̅̓̄̇ͨͬ̀͗͏̧̺͚̫̮̱͖̙̭̹̩̎͑ͪ̆̅̈ͣͥ̌̿͂̒͘̚ͅ() {
        array[1365][0] = "r1psSdz0bbEfTko3WPeITw==";
        array[1365][1] = "ؾق؎،ؽؐ؎ؾ؉؍ؓفؒ؉ؐـؓ؎؉ؾ؏ؾؑ؉؏ؿؕؿ؏؍؏؎ؓؕؕؐ";
        array[1366][0] = "uBLZR7OQV+dtFbIjxqX0tQ==";
        array[1366][1] = "ؾ؎؍فقؾؿؑ؉ؽ؍فؕ؉ؐؓ؎ؕ؉ؔؐـؐ؉ؿؐ؍ف؏؎ؒفؑـفؓ";
        array[1367][0] = "xEfVYEUSHRrynRbx1wRfUQ==";
        array[1367][1] = "ؓق،،فؓؾؔ؉؏ؓؑف؉ؐفؽؿ؉ؽ،؍،؉؍،قؓؿؕؔؿ؍ؐؔؒ";
        array[1368][0] = "dKDp0daLwcsp0SnX9Cl7al0Fd0Ac5J9Q";
        array[1368][1] = "ـؓؔؓؒقؒؑ؉؏ؒؑ،؉ؐ؎ؐ،؉ؽقؔـ؉ؕ؎ؒـفؔؑؐؔـؒ؎";
        array[1369][0] = "lyp12fvgA4E34bOj7x+SYA==";
        array[1369][1] = "،ؐ؍؎ؾ؏قف؉ؽ؍ؕ؍؉ؐؓفؑ؉ؕؓؽؐ؉ؓ؏ؽؑؓؽؓ،،قؐؿ";
        array[1370][0] = "/akSSOuIx5Gpb1tworC1fg==";
        array[1370][1] = "؍ؾ،؍ؒؾؓؾ؉ؾ؏فؕ؉ؐ،ؑـ؉ؔفؑؿ؉ؕؑـؒؽ؍ـ،ؾ؏فؒ";
        array[1371][0] = "AuKWNLV1bxoZiJb0rpm+Qg==";
        array[1371][1] = "؍؎ؔؒؿؿؽـ؉ؑؽؒؾ؉ؐ؏؏ؾ؉ؽؐ؎ق؉ؒــؔ؎فؿؓؑـؕف";
        array[1372][0] = "TQOVVTRwJuxH4DyMv7q3Ag==";
        array[1372][1] = "فؿؽؓف؏ؐؑ؉؎ـؓؑ؉ؐؾف؍؉ؽفؾ؏؉ؓؑؒؾؕؿؿقؾؒؾؕ";
        array[1373][0] = "rs8ePyfsz8h8eFO6iixhig==";
        array[1373][1] = "قؒؽؒ؍ؑؾ؎؉،ؑؔق؉ؐؕؒؑ؉ؔؑقؕ؉ؐؑ؍ؾؒؒقؓؒؽقؐ";
        array[1374][0] = "GBKJK0MClFBOG4tO/TF0Xg==";
        array[1374][1] = "ؔ،ؿؽؑؑ؏ؐ؉ؽؒؾ؎؉ؐؽؕؿ؉ؔ؏ؽ،؉ؿفؑؕؐقؔ؎ؿؓ؎ؾ";
        array[1375][0] = "nu+X95OtFJLf7dGnWnbPCA==";
        array[1375][1] = "ؿفؕؒؾفقؑ؉ـؓؓؑ؉ؐؽؓـ؉ؔؾؽ،؉؎ؓ؎؏؎ؒؿـؿ؎قؕ";
        By1337̞͉͚͙̈ͬ̑́̋̒ͧ͡͏̵̦̰͔͔̦̼͖͔͖͓͇̀̄͆ͤ̓̈̈́́̄̂͘͟͡͡͝();
    }

    private void By1337̞͉͚͙̈ͬ̑́̋̒ͧ͡͏̵̦̰͔͔̦̼͖͔͖͓͇̀̄͆ͤ̓̈̈́́̄̂͘͟͡͡͝() {
        array[1376][0] = "6+KUAvZcOES9/teJTkfwyA==";
        array[1376][1] = "،ؒؿ؏ؽ؏ؔ؏؉،ـقؓ؉ؐقؔؾ؉ؔؑؔؿ؉ـفؕؐؽ؏ؓقؔ؎ؾ،";
        array[1377][0] = "bdvuzgDj6zvE/1kAGCBpvw==";
        array[1377][1] = "ؿؾؒفؐؔ؍ؒ؉ؽؽؽؔ؉ؐففؔ؉ؾؐـؑ؉ؐؽؓـؓؕؕ،ؑ،،ؓ";
        array[1378][0] = "DyXMUiuvAj//GcHL/VWTvA==";
        array[1378][1] = "ؑؾؿؽؑ؏؏،؉ؓؾ،ؾ؉ؐ؎ؐؐ؉ؕؑؔف؉ـ؍؎؎ف؏؍ؾ؎؍؍؏";
        array[1379][0] = "wwkvwYYp8hySCC1jYWBHWFkCiUnTWkuO";
        array[1379][1] = "ؿ،ؔؒؕ؍ؒ،؉فؒؒ،؉ؐؑـؿ؉ؕؿؑؽ؉؍ؑؽؾؽ؏ؓق؎فؾؿ";
        array[1380][0] = "6UYXXh1bOtL8UWvvmmxIPw==";
        array[1380][1] = "؏فـفؐؑؑؕ؉ؽ؎؏،؉ؐؽؕ؏؉ؽؿؽؑ؉،ؐؑ؎ؿ،ؕؓؿؑـ؍";
        array[1381][0] = "IUKZhAB9NsLj4N3Lfd6eDg==";
        array[1381][1] = "ؑ؍ؕؿـؿؓؒ؉ؐؐ؍؎؉ؐففؔ؉ؔؕؓ؎؉ؽ،ف،ؽق؍؎ؾـ؍؍";
        array[1382][0] = "NyQejXO+2MzYThxTos5fbw==";
        array[1382][1] = "؏ؐ؏ـفؾقؕ؉ؐ؏ؑق؉ؐ؎؍ؔ؉ؕؓ؍ؒ؉ؐؾ؏ؑؽؽقؽ؏ـ،ؐ";
        array[1383][0] = "aiu4kww+4pIR4FjUyeIV+Q==";
        array[1383][1] = "ؔ،ؐؐفؑؕؕ؉؎ؔؕؐ؉ؐ؏؎ف؉ؕؽؾؒ؉ؑقؿ،ؐؕ؍ؿؔ؍ؑف";
        array[1384][0] = "1509zpHLEcMyFRVrV3Gw4A==";
        array[1384][1] = "ؓؐؐــؑ؎ؑ؉ؒؔ،ؿ؉ؐؓ؎ؿ؉ؾؑ؍ف؉ؾـؓؑ،ؽؑ؍فؓؒ؏";
        array[1385][0] = "p4MpOzm64nLV8GRUfhmlSQ==";
        array[1385][1] = "ؽ؏؎ؕؔ؍ؒؕ؉ؓ؎ؿؿ؉ؐؐؐؑ؉ؾؿؒؕ؉ؽؑؕ؍ؾؿ؍ؐؕ؏ؐؒ";
        array[1386][0] = "oGtAWjSzHmM7DPzajsZbiA==";
        array[1386][1] = "ؓؕؐؿ؎ؓ؎ؑ؉ؿ؏ؓ؏؉ؐ؏ؑؿ؉ؽؿؾؕ؉؍ؾ؎ؕ،ؾـؑؓق،ؐ";
        By1337̷̶̢̨͉̙̫͔͈̹̘̖͇̝͚̬͍̟̩̤ͨ̓ͥ̅̈͋̈̽̉́ͭ͐ͧ̈́ͥͭ̓́́ͅ();
    }

    private void By1337̷̶̢̨͉̙̫͔͈̹̘̖͇̝͚̬͍̟̩̤ͨ̓ͥ̅̈͋̈̽̉́ͭ͐ͧ̈́ͥͭ̓́́ͅ() {
        array[1387][0] = "OdJqAsQLudorxrnSSqWEFQ==";
        array[1387][1] = "؏؏؎ؿؾ؏؎ؒ؉ؽق،ؐ؉ؐ؏؍ؓ؉ؽ،؎ؑ؉ؿ؏ق؎ؐؽ؍؍ؒؑؾـ";
        array[1388][0] = "8r86xVwij1tq/QdOeZfRxw==";
        array[1388][1] = "ـفق؏ؒؒؐؐ؉ؑؕـ؏؉ؐ؎ؽف؉ؕؓ؍ؿ؉؎ؾ؎ؔؓفؕؽؔ؍ف؏";
        array[1389][0] = "Ma+ung2Pi1H+bkJWrbfNDg==";
        array[1389][1] = "ؒؐؓؾؐؓقؽ؉ؐ؏قـ؉ؐ؎ؔ؍؉ؾؔؑؽ؉ؾؐف؏ؒؒؕ؍؎،ؕؓ";
        array[1390][0] = "XbOVMkceBUwGtlLhradmrg==";
        array[1390][1] = "؍ف؏ؿـؾؽ؏؉فؕـؕ؉ؐ؍ؐف؉ؕؿ؍ؕ؉ف؎ؓـؒ،ؔؓفففؔ";
        array[1391][0] = "NUHFFH7b/rWmheNdBPbjWw==";
        array[1391][1] = "ؑؒؔ؎فؑ؍ـ؉ـ؎ؿ؎؉ؐ،؎ق؉ؕ،ؿؕ؉؏ؾؑ؏ؑ؎،،ؕؔقؽ";
        array[1392][0] = "5zVYBI07S7kGUjccRLb89Q==";
        array[1392][1] = "ؐ؏ـؑفؾؿ،؉؍؎ؑؾ؉ؐـؑ؏؉ؔؾ؎؏؉ؔ؏ـ،ؑ؍ؽؒؓقؒؓ";
        array[1393][0] = "+fN1POjgKG8lIe8W1kwVBA==";
        array[1393][1] = "ف،ؓؓؕؔؔ،؉ؿؽؽق؉ؐؕؿؾ؉ؔ؎ؾؿ؉ؓفؽق؏ؕؓؕ؏ـؿ؍";
        array[1394][0] = "/bx0qXylpAv5mRSxoYU0Fw==";
        array[1394][1] = "ؿؐ؏ؑقفؿؕ؉ؑقؐ؏؉ؐفؒؿ؉ؾؕؿق؉؍ؿؑؽ؎ـ؎ؾؒ؏؍ؿ";
        array[1395][0] = "neQVlPXtyk8QtMcVM/PC8w==";
        array[1395][1] = "ؓفؑؓفؾؐؽ؉ؕؔؒ؎؉ؐ؍ؒؕ؉ؕؿؾؓ؉؎ؽ،ؿ؍ؿؒؔـ؏ف؍";
        array[1396][0] = "T+0DkxumCvtNiuLVNaYlaA==";
        array[1396][1] = "ؒؐؕؕقؽؕق؉ؿؕؿؔ؉ؐـ،؏؉ؕقؾؾ؉ؾؑقؕؓؒــؒ،ـ،";
        array[1397][0] = "xHPMivaRBiJqSBxihf2R19docNKz3BU9";
        array[1397][1] = "ؿ؏؍؏ؓ،؏ق؉ف؏؍ؓ؉ؐ،ؒؔ؉ؽؒؿ؍؉ؾؾؐؑ؏ؐ؍ؒؔقؑؐ";
        By1337̵̶̡̼͙̰̥͙̙̱̯̳̭̹̃̾ͮ̍̅̏ͩͤ̍͂̅́̔̓ͥ̇͂͘̕͢͟͟͡͡͡ͅ();
    }

    private void By1337̵̶̡̼͙̰̥͙̙̱̯̳̭̹̃̾ͮ̍̅̏ͩͤ̍͂̅́̔̓ͥ̇͂͘̕͢͟͟͡͡͡ͅ() {
        array[1398][0] = "koBnFGerZV6XOgTyxxK1Sw==";
        array[1398][1] = "ؓؾؿقؒـ؍ف؉قؕؓؾ؉ؐؾؿؕ؉ؕؔؕؔ؉ؔفققؑؐؔؓ،ؓ؏ؒ";
        array[1399][0] = "e9mFAunZegsyylHvG9Wt7g==";
        array[1399][1] = "،ؕؒ؍؎ؾؐؔ؉ق،ـق؉ؐؾؔق؉ؽ؏قؕ؉فقؽؾ؎ــؐؿفؿؕ";
        array[1400][0] = "PDFuEcI5xWXpVZyz7puR4g==";
        array[1400][1] = "؏ؿؾ؎قؒ؍،؉ؑ،ؿؒ؉ؐؕؕؕ؉ؔف،ؾ؉ؾ؎ؒؕؒؓؾؑؾـؑؒ";
        array[1401][0] = "d1Gen4hCp5/3XkrZBJgTCA==";
        array[1401][1] = "؎قؓ؎؎ؒؿف؉ؿ،فؒ؉ؐؒؔؽ؉ؾفؽ،؉؍؏فؑـؿؕؿؓ،ؓ؎";
        array[1402][0] = "S/k05Fj51NXx1lqylwmD4g==";
        array[1402][1] = "ؿؒؔ؏ؿ،؍ؑ؉،ؑقؒ؉ؐقفف؉ؔؔ؎ف؉ؔؐقؽؕؑ؎ــؾؕف";
        array[1403][0] = "ld0h3R8AqL2ogZVjc1dZVg==";
        array[1403][1] = "ؕؕؐؕؕ،ؐ،؉؍فـ؏؉ؐؽ؏؍؉ؾ؎فـ؉ؑؑقؽفؒ؎ؒؕفؔـ";
        array[1404][0] = "xGhkG8TkLU1XX0t9UpD8hw==";
        array[1404][1] = "؎فؽؕ؏؎؏ؿ؉،؎ؔؓ؉ؐ،ف؎؉ؕ؏فق؉ف؏ؑ؎ــقؔؕؿؔؔ";
        array[1405][0] = "n3LEXQvA+lRBLGaRpS+elw==";
        array[1405][1] = "قؕؓؾ؎ؐـؒ؉ؾؐ؎ؒ؉ؐ؏ؽف؉ؽقؽؔ؉ؽؒؓ،ؿؒ؎ؔؿ؎؍ؔ";
        array[1406][0] = "EQuQAXTx66d+lhM5Cc2gXfBsROzfizOY";
        array[1406][1] = "؏ـؕؽ؏ـؐؓ؉ؽؿقؓ؉ؐؽؓؿ؉ؔـؐ،؉ـؽفؑؓؓ؏ؐ،ـؿؿ";
        array[1407][0] = "+mf1jZFhBtrRNtREYx7eKA==";
        array[1407][1] = "؍ؽ؍ؕفؒؾؕ؉ؔق؎ؾ؉ؐقؾؔ؉ؕؐ؎ـ؉؎؏ؒ؎ؿقؽـؒؽ؏ؽ";
        array[1408][0] = "klu2hIETXIHVfczFzm1FxQ==";
        array[1408][1] = "ؒؑ؎ؕـؑؔؕ؉؍ؿـؐ؉ؐؒق،؉ؽؑؓؒ؉؎ؾؒؑ؏ؒؿ؎ؒؐؔؿ";
        By1337̷̴̥͕̥̠̄̇͋͏̵̛̫̩̍̿͒̈́̇͑͞͏̧̻͈͍̮̗͍̳͔̀̾̈́͌ͯ̋̉̽͛();
    }

    private void By1337̷̴̥͕̥̠̄̇͋͏̵̛̫̩̍̿͒̈́̇͑͞͏̧̻͈͍̮̗͍̳͔̀̾̈́͌ͯ̋̉̽͛() {
        array[1409][0] = "+GV3+RbShkPY3qj8AZYO6Q==";
        array[1409][1] = "،ؒؿؑؔـ،ؾ؉؎فؕؔ؉ؐ؍ؽؾ؉ؾؿؕؔ؉ؿ؏ؒ؎قق؎ؑ؏؎؎ؕ";
        array[1410][0] = "W55EUbxvMSyuC3+UadAw3g==";
        array[1410][1] = "ؽفق؏؎ؽؽؿ؉؏؍ؐؕ؉ؐؔؓف؉ؕؒؕؑ؉ـؐؕؿؒؑؐؓقؿؕق";
        array[1411][0] = "Z/Ff7kKbwaJW4zKZji/3Eg==";
        array[1411][1] = "ؑؒؑؐفؓ؏ـ؉ؐؕؔـ؉ؐؔ؎ق؉ؽ؍ؕ؎؉؎ؿؒؿ؏،ؿ؎،؏؎ؔ";
        array[1412][0] = "2k/jaZm7avJzWmLzBHuR/w==";
        array[1412][1] = "؍ؿؕقققؽؿ؉ؑ،ق؍؉ؐؒ؎؏؉ؕؔؒف؉ـؔؕؒؓ؎؏ؒؽؐؐؾ";
        array[1413][0] = "UkTxtCsG0zybStT7GRkBbw==";
        array[1413][1] = "ؾ؎ؐف،،قؾ؉ؓ،ؒؔ؉ؐؽؔؒ؉ؾ؏ؐؿ؉ؔ؏ؑؽؾؕؿؔ؎؏ؑؑ";
        array[1414][0] = "yh1Vd32Kj+8=";
        array[1414][1] = "ؑ؎،ؔؓؒؒق؉ؓـفؓ؉ؐؾؔ؍؉ؔؑؓؿ؉؎ؓؿ؍ف؎؎؍ؿـ؎ؓ";
    }

    public static String decrypt(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("SHA-384").digest(key.getBytes(StandardCharsets.UTF_8)), 8), "Blowfish");

            Cipher des = Cipher.getInstance("Blowfish");
            des.init(Cipher.DECRYPT_MODE, keySpec);

            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);

        } catch (IllegalArgumentException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException |
                 NoSuchAlgorithmException | InvalidKeyException e) {
            return obj;
        }
        // return null;
    }

    public static String sObf(String s, int offset) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int codePoint = Character.codePointAt(Character.toString(c), 0);
            sb.append(Character.toChars(codePoint + offset));
        }
        return sb.toString();
    }

    private String infoCode(int code) {//getForCode
        if (code == num[0]) return strings[6];//&cОшибка сервера лицензий
        if (code == num[1]) return strings[7];//&cОтсутствует лицензионный ключ!
        if (code == num[2]) return strings[8];//&aЛицензия валидна
        if (code == num[3]) return strings[9];//&cНеправильный ответ от сервера!
        if (code == num[4]) return strings[10];//&cНеправильный ключ!
        if (code == num[5]) return strings[11];//&cIP адрес не валидный!
        if (code == num[6]) return strings[12];//&cНеправильный плагин!
        if (code == num[7]) return strings[13];//&cКлюч устарел!
        return strings[14];
    }

    static {
//        num[0] = -1276891080;
//        num[1] = 1694123819;
//        num[2] = 81434588;
//        num[3] = -973558093;
//        num[4] = -1694123818;
//        num[5] = 1377916022;
//        num[6] = 190116507;
//        num[7] = -2017473048;

        int var0 = Integer.parseInt("100100101", 2);
        int Vvar940 = 49 ^ 162;//147
        switch (Vvar940) {
            case 766428835:
                throw null;
            case 998009813:
                throw null;
            case 147://147
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 1248458386:
                var0 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1730642134:
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1343100808:
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1770150975:
                var0 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1532825948:
                var0 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1069234375:
                var0 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
                break;
        }
        int var1 = Integer.parseInt("11111100", 2);
        int Vvar688 = 116650613 ^ 99;//116650518
        switch (Vvar688) {
            case 1662949525:
                var1 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 116650518://116650518
                var1 = -Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 119147128:
                throw null;
            case 1621999360:
                throw null;
            case 898109765:
                throw null;
            case 914957924:
                throw null;
        }
        int var2 = Integer.parseInt("1010110000", 2);
        int Vvar150 = 45 ^ 68;//105
        switch (Vvar150) {
            case 105://105
                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
                break;
            case 671015800:
                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 2046571350:
                throw null;
            case 557837929:
                throw null;
            case 917187622:
                var2 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
                break;
            case 31275876:
                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 834102631:
                var2 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));
                break;
            case 830348061:
                throw null;
            case 2027769150:
                throw null;
            case 143219900:
                throw null;
            case 907937506:
                throw null;
        }
        int var3 = Integer.parseInt("101110110", 2);
        int Vvar33 = 95999123 ^ 253;//95999086
        switch (Vvar33) {
            case 95999086://95999086
                var3 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 469858420:
                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
                break;
            case 760407665:
                throw null;
            case 289454666:
                var3 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));
                break;
            case 375836068:
                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 207130804:
                throw null;
            case 348121098:
                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1826441302:
                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 2051233287:
                throw null;
            case 1154831326:
                throw null;
            case 1661407898:
                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
                break;
            case 102041555:
                var3 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1884056961:
                throw null;
            case 1922759557:
                throw null;
        }
        int var4 = Integer.parseInt("10101110", 2);
        int Vvar67 = 1112 ^ 99;//1083
        switch (Vvar67) {
            case 620512594:
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
                break;
            case 1608705851:
                throw null;
            case 1848618312:
                var4 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1930790539:
                throw null;
            case 1083://1083
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 1731804571:
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
                break;
            case 833445736:
                throw null;
            case 346879217:
                throw null;
            case 105443913:
                throw null;
            case 265761306:
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1007625907:
                throw null;
            case 1379676717:
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 264724439:
                throw null;
            case 2020031904:
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8));
                break;
            case 1707043497:
                var4 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
                break;
            case 606802998:
                var4 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));
                break;
        }
        int var5 = Integer.parseInt("101101", 2);
        int Vvar89 = 1255163895 ^ 78;//1255163833
        switch (Vvar89) {
            case 1084638281:
                throw null;
            case 1255163833://1255163833
                var5 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1496542986:
                var5 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 130006932:
                throw null;
            case 815046783:
                throw null;
        }
        int var6 = Integer.parseInt("100000110", 2);
        int Vvar251 = 19 ^ 215;//196
        switch (Vvar251) {
            case 1161675823:
                var6 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 2136994803:
                throw null;
            case 1100194972:
                throw null;
            case 196://196
                var6 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 67735298:
                throw null;
            case 722060668:
                var6 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 792257639:
                throw null;
            case 397755098:
                throw null;
        }
        int var7 = Integer.parseInt("1101110001", 2);
        int Vvar454 = 1602939770 ^ 109;//1602939671
        switch (Vvar454) {
            case 1945719876:
                var7 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1516656611:
                var7 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
                break;
            case 1602939671://1602939671
                var7 = -Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
                break;
            case 597907085:
                throw null;
            case 1997846097:
                var7 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
                break;
            case 108912455:
                var7 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1908192198:
                var7 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1668036019:
                var7 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
                break;
        }
        int var8 = Integer.parseInt("1101100010", 2);
        int Vvar942 = 8208 ^ 148;//8324
        switch (Vvar942) {
            case 1679059966:
                var8 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
                break;
            case 1313376385:
                var8 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1175705299:
                throw null;
            case 679520832:
                var8 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
                break;
            case 820693647:
                throw null;
            case 110016590:
                var8 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
                break;
            case 2062985967:
                var8 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
                break;
            case 8324://8324
                var8 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
                break;
        }
        int var9 = Integer.parseInt("1011010101", 2);
        int Vvar690 = 1622612631 ^ 141;//1622612506
        switch (Vvar690) {
            case 1622612506://1622612506
                var9 = -Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1511204894:
                var9 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1168976089:
                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 351277476:
                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
                break;
            case 1636736713:
                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1295639889:
                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 579344239:
                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
                break;
            case 1622967741:
                var9 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
                break;
        }
        int var10 = Integer.parseInt("101000000", 2);
        int Vvar272 = 70 ^ 13;//75
        switch (Vvar272) {
            case 30236654:
                var10 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 647971233:
                var10 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
                break;
            case 75://75
                var10 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 2077955599:
                var10 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
                break;
            case 124628592:
                var10 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 775367718:
                throw null;
            case 700980980:
                var10 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8));
                break;
            case 1316395031:
                throw null;
            case 554221387:
                throw null;
            case 2126049430:
                throw null;
            case 2087758289:
                throw null;
            case 865522465:
                throw null;
            case 2066929373:
                throw null;
            case 458821142:
                throw null;
        }
        int var11 = Integer.parseInt("110011111", 2);
        int Vvar918 = 178499686 ^ 154;//178499836
        switch (Vvar918) {
            case 178499836://178499836
                var11 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 842199349:
                throw null;
            case 988082930:
                throw null;
            case 1524887297:
                throw null;
            case 115758535:
                var11 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 390894977:
                var11 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
                break;
            case 2016070426:
                throw null;
            case 881929919:
                throw null;
        }
        int var12 = Integer.parseInt("101000001", 2);
        int Vvar192 = 126 ^ 163;//221
        switch (Vvar192) {
            case 1112122296:
                var12 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
                break;
            case 930784047:
                throw null;
            case 548701796:
                throw null;
            case 221://221
                var12 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
                break;
            case 2003855787:
                throw null;
            case 2083802613:
                var12 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 977697024:
                throw null;
        }
        int var13 = Integer.parseInt("1111100100", 2);
        int Vvar490 = 1405774933 ^ 126;//1405774891
        switch (Vvar490) {
            case 2109686060:
                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8));
                break;
            case 1405774891://1405774891
                var13 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
                break;
            case 1931435442:
                throw null;
            case 2058127059:
                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
                break;
            case 814568224:
                var13 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)));
                break;
            case 594684596:
                var13 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1223178632:
                throw null;
            case 1197660962:
                throw null;
            case 1896625968:
                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 2077350351:
                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
        }
        int var14 = Integer.parseInt("111000", 2);
        int Vvar926 = 3649 ^ 137;//3784
        switch (Vvar926) {
            case 850548194:
                var14 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 1802501695:
                var14 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 1321076163:
                throw null;
            case 904359235:
                throw null;
            case 2086707388:
                var14 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 299430528:
                var14 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1859491433:
                throw null;
            case 3784://3784
                var14 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 1106313155:
                throw null;
            case 1240122697:
                var14 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
                break;
            case 840724726:
                var14 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8));
                break;
        }
        int var15 = Integer.parseInt("110111000", 2);
        int Vvar966 = 134628173 ^ 18;//134628191
        switch (Vvar966) {
            case 1401359443:
                throw null;
            case 112568710:
                throw null;
            case 1065355983:
                var15 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 134628191://134628191
                var15 = -Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1638109607:
                var15 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
                break;
            case 2001985168:
                throw null;
            case 290947305:
                throw null;
        }
        // Integer[] num = new Integer[8];

        num[var0] = var1;
        num[var2] = var3;
        num[var4] = var5;
        num[var6] = var7;
        num[var8] = var9;
        num[var10] = var11;
        num[var12] = var13;
        num[var14] = var15;


        strings[0] = By1337(new String(new byte[]{45, 43, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 45, 43}, StandardCharsets.UTF_8)); //[]==============================[]
        strings[1] = By1337(new String(new byte[]{-47, -105, -47, -120, -47, -125, -47, -126, -47, -114, -47, -117, -47, -125, -47, -117, -47, -114, -47, -125, 86, -48, -73, 86, -48, -73, -47, -125, -48, -74, -47, -124, -47, -125, -48, -74, -47, -120, -47, -118}, StandardCharsets.UTF_8)); //Соединение с сервером
        strings[2] = By1337(new String(new byte[]{2, 4, 3, 19}, StandardCharsets.UTF_8)); //true
        strings[3] = By1337(new String(new byte[]{-61, -111, 21, -47, -83, -47, -114, -48, -80, -47, -125, -47, -117, -47, -127, -47, -114, -48, -71, 86, -47, -117, -47, -125, 86, -47, -124, -47, -122, -47, -115, -47, -114, -47, -126, -47, -117, -47, -122, 87}, StandardCharsets.UTF_8)); //§cЛицензия не валидна!
        strings[4] = By1337(new String(new byte[]{-61, -111, 21, -47, -87, -47, -115, -47, -122, -47, -123, -47, -114, -47, -117, 86, -47, -124, -48, -67, -47, -116, -47, -115, -48, -72, -48, -79, -47, -125, -47, -117, 87}, StandardCharsets.UTF_8)); //§cПлагин выключен!
        strings[5] = By1337(new String(new byte[]{16, 23, 26, 5, 19}, StandardCharsets.UTF_8)); //false
        strings[6] = By1337(new String(new byte[]{80, 21, -47, -88, -48, -66, -47, -114, -47, -121, -47, -116, -47, -122, 86, -48, -73, -47, -125, -48, -74, -47, -124, -47, -125, -48, -74, -47, -122, 86, -47, -115, -47, -114, -48, -80, -47, -125, -47, -117, -47, -127, -47, -114, -47, -113}, StandardCharsets.UTF_8)); //&cОшибка сервера лицензий
        strings[7] = By1337(new String(new byte[]{80, 21, -47, -88, -48, -76, -48, -73, -48, -75, -48, -76, -48, -73, -48, -76, -47, -124, -48, -75, -47, -125, -48, -76, 86, -47, -115, -47, -114, -48, -80, -47, -125, -47, -117, -47, -127, -47, -114, -47, -120, -47, -117, -47, -117, -48, -67, -47, -113, 86, -47, -116, -47, -115, -48, -72, -48, -79, 87}, StandardCharsets.UTF_8)); //&cОтсутствует лицензионный ключ!
        strings[8] = By1337(new String(new byte[]{80, 23, -47, -83, -47, -114, -48, -80, -47, -125, -47, -117, -47, -127, -47, -114, -48, -71, 86, -47, -124, -47, -122, -47, -115, -47, -114, -47, -126, -47, -117, -47, -122}, StandardCharsets.UTF_8)); //&aЛицензия валидна
        strings[9] = By1337(new String(new byte[]{80, 21, -47, -85, -47, -125, -47, -119, -48, -74, -47, -122, -47, -124, -47, -114, -47, -115, -48, -70, -47, -117, -48, -67, -47, -113, 86, -47, -120, -48, -76, -47, -124, -47, -125, -48, -76, 86, -47, -120, -48, -76, 86, -48, -73, -47, -125, -48, -74, -47, -124, -47, -125, -48, -74, -47, -122, 87}, StandardCharsets.UTF_8)); //&cНеправильный ответ от сервера!
        strings[10] = By1337(new String(new byte[]{80, 21, -47, -85, -47, -125, -47, -119, -48, -74, -47, -122, -47, -124, -47, -114, -47, -115, -48, -70, -47, -117, -48, -67, -47, -113, 86, -47, -116, -47, -115, -48, -72, -48, -79, 87}, StandardCharsets.UTF_8)); //&cНеправильный ключ!
        strings[11] = By1337(new String(new byte[]{80, 21, 63, 38, 86, -47, -122, -47, -126, -48, -74, -47, -125, -48, -73, 86, -47, -117, -47, -125, 86, -47, -124, -47, -122, -47, -115, -47, -114, -47, -126, -47, -117, -48, -67, -47, -113, 87}, StandardCharsets.UTF_8)); //&cIP адрес не валидный!
        strings[12] = By1337(new String(new byte[]{80, 21, -47, -85, -47, -125, -47, -119, -48, -74, -47, -122, -47, -124, -47, -114, -47, -115, -48, -70, -47, -117, -48, -67, -47, -113, 86, -47, -119, -47, -115, -47, -122, -47, -123, -47, -114, -47, -117, 87}, StandardCharsets.UTF_8)); //&cНеправильный плагин!
        strings[13] = By1337(new String(new byte[]{80, 21, -47, -84, -47, -115, -48, -72, -48, -79, 86, -48, -75, -48, -73, -48, -76, -47, -122, -48, -74, -47, -125, -47, -115, 87}, StandardCharsets.UTF_8)); //&cКлюч устарел!
        strings[14] = By1337(new String(new byte[]{80, 21, -47, -85, -47, -125, -47, -114, -47, -127, -47, -124, -47, -125, -48, -73, -48, -76, -47, -117, -47, -122, -48, -71, 86, -47, -120, -48, -66, -47, -114, -47, -121, -47, -116, -47, -122}, StandardCharsets.UTF_8)); //&cНеизвестная ошибка
        strings[15] = By1337(new String(new byte[]{73, 32, 71, 75}, StandardCharsets.UTF_8)); //?v1=
        strings[16] = By1337(new String(new byte[]{80, 32, 68, 75}, StandardCharsets.UTF_8)); //&v2=
        strings[17] = By1337(new String(new byte[]{80, 6, 26, 75}, StandardCharsets.UTF_8)); //&pl=
//
//        strings[0] = By1337("-+KKKKKKKKKKKKKKKKKKKKKKKKKKKKKK-+"); //[]==============================[]
//        strings[1] = By1337("їшутюыуыюуVзVзужфужшъ"); //Соединение с сервером
//        strings[2] = By1337(""); //true
//        strings[3] = By1337("ÑѭюауысюйVыуVфцэютыцW"); //§cЛицензия не валидна!
//        strings[4] = By1337("ÑѩэцхюыVфньэибуыW"); //§cПлагин выключен!
//        strings[5] = By1337(""); //false
//        strings[6] = By1337("PѨоючьцVзужфужцVэюауысюя"); //&cОшибка сервера лицензий
//        strings[7] = By1337("PѨдзедздфеудVэюауысюшыыняVьэибW"); //&cОтсутствует лицензионный ключ!
//        strings[8] = By1337("PѭюауысюйVфцэютыц"); //&aЛицензия валидна
//        strings[9] = By1337("PѫущжцфюэкыняVшдфудVшдVзужфужцW"); //&cНеправильный ответ от сервера!
//        strings[10] = By1337("PѫущжцфюэкыняVьэибW"); //&cНеправильный ключ!
//        strings[11] = By1337("P?&VцтжузVыуVфцэютыняW"); //&cIP адрес не валидный!
//        strings[12] = By1337("PѫущжцфюэкыняVщэцхюыW"); //&cНеправильный плагин!
//        strings[13] = By1337("PѬэибVездцжуэW"); //&cКлюч устарел!
//        strings[14] = By1337("PѫуюсфуздыцйVшоючьц"); //&cНеизвестная ошибка
//        strings[15] = By1337("I GK"); //?v1=
//        strings[16] = By1337("P DK"); //&v2=
//        strings[17] = By1337("PK"); //&pl=
//
    }

    private int getInt(int length) {//generateRandomBinaryNumber
        if (length > 0b10100)
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

    private String builder(String v1, String v2) {
        StringBuilder urlBuilder = new StringBuilder(getGet());
        urlBuilder.append(strings[15].toLowerCase()).append(v1);
        urlBuilder.append(strings[16].toLowerCase()).append(v2);
        urlBuilder.append(strings[17].toLowerCase()).append(instance.getName());
        return builderToString(urlBuilder);
    }

    private String builderToString(StringBuilder sb) {
        return sb.toString();
    }

    private String Universal(String v1, String v2) throws IOException {//requestServer
        URL url = new URL(builder(v1, v2));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setReadTimeout(5000);
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

    private int isIs() {//isValidationType
        String rand = toS(UUID.randomUUID().toString());
        String sKey = toS("2APZ5JCR2nuIapCO7eT04knQ");
        String key = toS(string);

        int var0 = Integer.parseInt("1110111", 2);
        int Vvar1881923903 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));//20
        switch (Vvar1881923903) {
            case 154196396:
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 565621422:
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
                break;
            case 1262763303:
                var0 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 792927725:
                throw null;
            case 1396643934:
                throw null;
            case 1159613300:
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 1517199256:
                throw null;
            case 48561653:
                throw null;
            case 20://20
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
                break;
            case 2116730101:
                var0 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 2131079342:
                var0 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));
                break;
            case 2069435292:
                throw null;
            case 1734421989:
                throw null;
        }
        int var1 = Integer.parseInt("1101001100", 2);
        int Vvar852856064 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));//153
        switch (Vvar852856064) {
            case 554371099:
                throw null;
            case 2065664928:
                throw null;
            case 153://153
                var1 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8));
                break;
            case 1602176253:
                var1 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 1739217318:
                throw null;
            case 1907839104:
                throw null;
            case 1856635595:
                throw null;
            case 2006692192:
                throw null;
        }
        int var2 = Integer.parseInt("11110011", 2);
        int Vvar1761786163 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));//209
        switch (Vvar1761786163) {
            case 85934121:
                var2 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
                break;
            case 48971305:
                throw null;
            case 195724836:
                var2 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));
                break;
            case 2076457108:
                var2 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
                break;
            case 1763188684:
                throw null;
            case 209://209
        }
        int var3 = Integer.parseInt("100000101", 2);
        int Vvar1256271787 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)));//44
        switch (Vvar1256271787) {
            case 44://44
                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8));
                break;
            case 1578429045:
                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8));
                break;
            case 1616592875:
                throw null;
            case 644634844:
                throw null;
            case 507091914:
                var3 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
                break;
            case 2065735579:
                var3 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 103375120:
                throw null;
        }
        int var4 = Integer.parseInt("110101001", 2);
        int Vvar1237348026 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));//64
        switch (Vvar1237348026) {
            case 400442372:
                throw null;
            case 1645252660:
                var4 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1914879022:
                throw null;
            case 64://64
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 737358041:
                var4 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1770918727:
                var4 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
                break;
            case 837566803:
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
                break;
            case 332106532:
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8));
                break;
            case 1395228879:
                var4 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
        }
        int var5 = Integer.parseInt("1001011", 2);
        int Vvar13966291 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));//1294
        switch (Vvar13966291) {
            case 1570096165:
                var5 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
                break;
            case 779059755:
                throw null;
            case 1294://1294
                var5 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1965218951:
                throw null;
            case 584013660:
                var5 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1779387530:
                var5 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 490334989:
                var5 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1011048079:
                var5 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1294754301:
                throw null;
            case 993847002:
                throw null;
            case 1257035872:
                throw null;
            case 896909169:
                var5 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8));
                break;
        }
        int var6 = Integer.parseInt("1001111100", 2);
        int Vvar501023966 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)));//1341
        switch (Vvar501023966) {
            case 1341://1341
                var6 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1948880221:
                throw null;
            case 1919881024:
                var6 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
                break;
            case 912509416:
                var6 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));
                break;
            case 484474496:
                var6 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));
                break;
            case 97947843:
                throw null;
            case 1801412037:
                var6 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
        }
        int var7 = Integer.parseInt("101010010", 2);
        int Vvar1086682150 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)));//19
        switch (Vvar1086682150) {
            case 666881371:
                throw null;
            case 609460560:
                var7 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1712846481:
                throw null;
            case 19://19
                var7 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 2120336860:
                throw null;
            case 1066698736:
                throw null;
            case 2020030433:
                var7 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 1783533677:
                throw null;
            case 285912001:
                throw null;
            case 1152059764:
                var7 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));
                break;
            case 146106505:
                var7 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8)));
                break;
        }
        int var8 = Integer.parseInt("1011000110", 2);
        int Vvar799141601 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));//179
        switch (Vvar799141601) {
            case 1579532466:
                throw null;
            case 1769402515:
                var8 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 179://179
                var8 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 1732006034:
                throw null;
            case 1169708310:
                throw null;
        }
        int var9 = Integer.parseInt("1001010010", 2);
        int Vvar991924248 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));//92
        switch (Vvar991924248) {
            case 92://92
                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 165807949:
                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 105736881:
                throw null;
            case 1775030954:
                var9 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1769993092:
                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 2105794356:
                var9 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 1067054396:
                var9 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)));
                break;
        }
        int var10 = Integer.parseInt("110011001", 2);
        int Vvar1891772187 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));//177
        switch (Vvar1891772187) {
            case 936575569:
                var10 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 922934348:
                throw null;
            case 437071816:
                var10 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
            case 237896988:
                throw null;
            case 1682568430:
                throw null;
            case 177://177
                var10 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110110", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8));
                break;
        }
        int var11 = Integer.parseInt("1111001001", 2);
        int Vvar125809181 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8)));//1
        switch (Vvar125809181) {
            case 1://1
                var11 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 1867259564:
                throw null;
            case 332150033:
                throw null;
            case 1034092769:
                var11 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 1168270727:
                var11 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)));
                break;
            case 777379166:
                throw null;
            case 678603743:
                throw null;
            case 8893813:
                var11 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8));
                break;
            case 1676595844:
                throw null;
            case 1065780772:
                var11 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1828267884:
                throw null;
        }
        int var12 = Integer.parseInt("100011010", 2);
        int Vvar890851513 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8)));//1768
        switch (Vvar890851513) {
            case 1425533054:
                throw null;
            case 587934439:
                var12 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110100", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));
                break;
            case 632904564:
                throw null;
            case 1768://1768
                var12 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111001", 2)}, StandardCharsets.UTF_8));
                break;
            case 2052547685:
                throw null;
            case 1682762243:
                var12 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110011", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8));
                break;
        }
        int var13 = Integer.parseInt("100100001", 2);
        int Vvar1507808793 = Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("111000", 2)}, StandardCharsets.UTF_8)));//229
        switch (Vvar1507808793) {
            case 229://229
                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110101", 2)}, StandardCharsets.UTF_8));
                break;
            case 1969951573:
                var13 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110111", 2)}, StandardCharsets.UTF_8)));
                break;
            case 11054883:
                var13 = -((Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110101", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110011", 2)}, StandardCharsets.UTF_8)));
                break;
            case 1328960540:
                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110110", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110010", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110100", 2)}, StandardCharsets.UTF_8));
                break;
            case 1248577114:
                throw null;
            case 1733716109:
                var13 = (Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8)) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110010", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8))) >> Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8));
                break;
        }

        try {
            String response = Universal(up(rand, sKey), up(rand, key));
            int hash = response.hashCode();
            if (response.startsWith("<")) {
                return num[var0];
            }
            if (hash == num[var1]) {
                if (key.length() == var2) return num[var3];
                return num[var4];
            }
            if (hash == num[var5]) return num[var6];
            if (hash == num[var7]) return num[var8];
            if (hash == num[var9]) return num[var10];
            String respRand = up(up(response, key), sKey);
            if (rand.startsWith(respRand)) return num[var11];
            else return num[var12];
        } catch (IOException e) {
            return num[var13];
        }


//        try {
//            String response = Universal(up(rand, sKey), up(rand, key));
//            int hash = response.hashCode();
//
//            if (response.startsWith("<")) {
//                return num[0]; //PAGE_ERROR
//            }
//            if (hash == num[4]) {//KEY_NOT_FOUND
//                if (key.length() == 0)
//                    return num[1];
//                return num[4]; //ключ не валидный
//            }
//            if (hash == num[5]) //NOT_VALID_IP
//                return num[5];
//            if (hash == num[6]) //INVALID_PLUGIN
//                return num[6];
//            if (hash == num[7]) //KEY_OUTDATED
//                return num[7];
//
//            String respRand = up(up(response, key), sKey);
//            if (rand.startsWith(respRand))
//                return num[2]; //VALID
//            else
//                return num[3]; //WRONG_RESPONSE
//
//        } catch (IOException e) {
//            return num[0]; //PAGE_ERROR
//        }
    }

    private String toS(String s) {//toBinary
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

    private static String up(String s1, String s2) {//xor
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < (Math.min(s1.length(), s2.length())); i++)
                result.append(Byte.parseByte("" + s1.charAt(i)) ^ Byte.parseByte(s2.charAt(i) + ""));
            return result.toString();
        } catch (NumberFormatException e) {
            return "<";
        }
    }

    private static String By1337(String string) {//encryption
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        while (n < string.length()) {
            stringBuilder.append((char) (string.charAt(n) ^ 0x76));
            ++n;
        }
        return stringBuilder.toString();
    }

    private String getGet() {//getVerifyUrl
        return "http://by1337.space/verify.php";
//        return (new Object() {
//            int t;
//
//            public String toString() {
//                byte[] buf = new byte[30];
//                t = 646293480;
//                buf[0] = (byte) (t >>> 20);
//                t = -1882527427;
//                buf[1] = (byte) (t >>> 9);
//                t = 1010017847;
//                buf[2] = (byte) (t >>> 11);
//                t = -831856194;
//                buf[3] = (byte) (t >>> 9);
//                t = 1278899551;
//                buf[4] = (byte) (t >>> 9);
//                t = 1902665687;
//                buf[5] = (byte) (t >>> 9);
//                t = 778041700;
//                buf[6] = (byte) (t >>> 17);
//                t = 1095601118;
//                buf[7] = (byte) (t >>> 10);
//                t = 891116275;
//                buf[8] = (byte) (t >>> 1);
//                t = -1186584604;
//                buf[9] = (byte) (t >>> 13);
//                t = 2040455124;
//                buf[10] = (byte) (t >>> 19);
//                t = 1032009294;
//                buf[11] = (byte) (t >>> 12);
//                t = -897991253;
//                buf[12] = (byte) (t >>> 11);
//                t = 1515980195;
//                buf[13] = (byte) (t >>> 17);
//                t = 851028959;
//                buf[14] = (byte) (t >>> 15);
//                t = -1148855386;
//                buf[15] = (byte) (t >>> 19);
//                t = 1058413521;
//                buf[16] = (byte) (t >>> 12);
//                t = 965619623;
//                buf[17] = (byte) (t >>> 18);
//                t = -1167280557;
//                buf[18] = (byte) (t >>> 13);
//                t = 1655451413;
//                buf[19] = (byte) (t >>> 8);
//                t = -1875162278;
//                buf[20] = (byte) (t >>> 15);
//                t = 848626008;
//                buf[21] = (byte) (t >>> 23);
//                t = 980592881;
//                buf[22] = (byte) (t >>> 16);
//                t = -891497507;
//                buf[23] = (byte) (t >>> 9);
//                t = 303934671;
//                buf[24] = (byte) (t >>> 5);
//                t = 1808310129;
//                buf[25] = (byte) (t >>> 19);
//                t = 280668396;
//                buf[26] = (byte) (t >>> 18);
//                t = 915569539;
//                buf[27] = (byte) (t >>> 3);
//                t = -1390497296;
//                buf[28] = (byte) (t >>> 21);
//                t = -1602064253;
//                buf[29] = (byte) (t >>> 8);
//                return new String(buf);
//            }
//        }.toString());
    }//http://by1337.space/verify.php
}