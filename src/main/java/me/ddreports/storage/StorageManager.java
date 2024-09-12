package me.ddreports.storage;

import json2.JSONArray;
import json2.JSONObject;
import me.ddreports.data.Dash;
import me.ddreports.data.State;
import me.ddreports.data.Store;
import me.ddreports.data.Zone;
import me.ddreports.data.account.Account;
import me.ddreports.utils.Utils;

import java.io.*;
import java.util.*;

public class StorageManager {

    private static final File dataFolder = new File("data");
    private static final File usersFolder = new File(dataFolder, "users");
    private static final File storeFile = new File(dataFolder, "stores.json");
    private static final Map<Zone, List<Store>> stores = new HashMap<>();
    private static AccountManager accountManager;

    public static void init() {
        if (!dataFolder.exists()) {
            Utils.getLogger().info("Data folder did not exist.. Creating: {}", dataFolder.mkdirs());
        }
        if (!usersFolder.exists()) {
            Utils.getLogger().info("Users folder did not exist.. Creating: {}", usersFolder.mkdirs());
        }
        if (!storeFile.exists()) {
            Utils.getLogger().info("Stores file did not exist.. Creating: {}", saveStores());
        }
        loadStores();
        accountManager = new AccountManager();

    }

    private static void loadStores() {
        JSONObject data = loadData(storeFile);


            for (int i = 0; i != data.getJSONArray("stores").length(); i++) {
                JSONObject storeData = data.getJSONArray("stores").getJSONObject(i);
                String name = storeData.getString("name");
                JSONObject zoneData = storeData.getJSONObject("zone");
                Zone zone = new Zone();
                zone.setCity(zoneData.getString("city"));
                zone.setState(State.valueOf(zoneData.getString("state")));
                if (!zoneExists(zone)) {
                    stores.put(zone, new ArrayList<>());
                } else zone = getZone(zone);
                Store store = new Store();
                store.setZone(zone);
                store.setName(name);
                stores.get(zone).add(store);


            }

    }

    public static JSONObject loadData(String path) {
        return loadData(new File(path + ".json"));
    }

    public static JSONObject loadData(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }

            return new JSONObject(stringBuilder.toString());
        } catch (IOException e) {
            Utils.getLogger().error("Error loading store data", e);
        }
        return new JSONObject();
    }

    public static void saveData(String path, JSONObject data) {
        saveData(new File(path + ".json"), data);
    }

    public static void saveData(File file, JSONObject data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data.toString(2));
            Utils.getLogger().info("{} written successfully.", file.getPath());
        } catch (IOException e) {
            Utils.getLogger().error("Error saving data to file", e);
        }
    }

    private static Zone getZone(Zone zone) {
        for (Zone z : stores.keySet()) {
            if (z.getCity().equalsIgnoreCase(zone.getCity()) && z.getState().equals(zone.getState())) {
                return z;
            }
        }
        return null;
    }

    private static boolean zoneExists(Zone zone) {
        boolean exists = false;
        for (Zone z : stores.keySet()) {
            if (z.getCity().equalsIgnoreCase(zone.getCity()) && z.getState().equals(zone.getState())) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private static boolean saveStores() {

        Utils.getLogger().info("Saving store file: {}", storeFile.getAbsolutePath());
        JSONObject data = new JSONObject();
        JSONArray storesArray = new JSONArray();
        for (Zone zone : stores.keySet()) {
            for (Store store : stores.get(zone)) {
                JSONObject storeData = new JSONObject();
                storeData.put("name", store.getName());
                JSONObject zoneData = new JSONObject();
                zoneData.put("city", zone.getCity());
                zoneData.put("state", zone.getState().name());
                storeData.put("zone", zoneData);
                storesArray.put(storeData);
            }
        }
        data.put("stores", storesArray);
        saveData(storeFile, data);

        return true;
    }







    public static Set<Zone> getZones() {
        return stores.keySet();
    }

    public static Collection<Store> getStores(Zone zone) {
        return stores.get(zone);
    }

    public static List<Store> getStores() {
        List<Store> allStores = new ArrayList<>();
        for (List<Store> storeList : stores.values()) {
            allStores.addAll(storeList);
        }
        return allStores;
    }

    public static void saveStore(Store store) {
        Zone zone = store.getZone();
        if (!stores.containsKey(zone)) {
            stores.put(zone, new ArrayList<>());
        }
        stores.get(zone).add(store);
        saveStores();
    }

    public static Zone getZone(UUID uuid) {
        for (Zone zone : stores.keySet()) {
            if (zone.getUid().equals(uuid)) {
                return zone;
            }
        }
        return null;
    }

    public static AccountManager getAccountManager() {
        return accountManager;
    }

    public static File getDataFolder() {
        return dataFolder;
    }

    public static File getUsersFolder() {
        return usersFolder;
    }

//    public static Account loadData(File file, Class<Account> accountClass) {
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//            JSONObject data = new JSONObject(stringBuilder.toString());
//            return new Account();
//        } catch (IOException e) {
//            Utils.getLogger().error("Error loading account data", e);
//        }
//        return null;
//    }
}
