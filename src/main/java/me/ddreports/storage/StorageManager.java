package me.ddreports.storage;

import json2.JSONArray;
import json2.JSONObject;
import me.ddreports.data.*;
import me.ddreports.utils.Utils;

import java.io.*;
import java.util.*;

public class StorageManager {

    private static Map<Zone, List<Store>> stores = new HashMap();
    private static final File dataFolder = new File("data");
    private static final File dashesFolder = new File(dataFolder, "dashes");
    private static final File storeFile = new File(dataFolder, "stores.json");

    public static void init(){
        if(!dataFolder.exists()){
            Utils.getLogger().info("Data folder did not exist.. Creating: {}", dataFolder.mkdirs());
        }
        if(!dashesFolder.exists()){
            Utils.getLogger().info("Dashes folder did not exist.. Creating: {}", dashesFolder.mkdirs());
        }
        if(!storeFile.exists()){
            Utils.getLogger().info("Stores file did not exist.. Creating: {}", saveStores());
        }
        loadStores();

    }

    private static void loadStores() {
        try (BufferedReader br = new BufferedReader(new FileReader(storeFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONObject data = new JSONObject(stringBuilder.toString());
            for(int i=0;i!=data.getJSONArray("stores").length();i++){
                JSONObject storeData = data.getJSONArray("stores").getJSONObject(i);
                String name = storeData.getString("name");
                JSONObject zoneData = storeData.getJSONObject("zone");
                Zone zone = new Zone();
                zone.setCity(zoneData.getString("city"));
                zone.setState(State.valueOf(zoneData.getString("state")));
                Utils.getLogger().info("Testing something: {}", stores.keySet().contains(zone));
                if(!zoneExists(zone)){
                    stores.put(zone, new ArrayList<>());
                } else zone = getZone(zone);
                Store store = new Store();
                store.setZone(zone);
                store.setName(name);
                stores.get(zone).add(store);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Zone getZone(Zone zone) {
        for(Zone z : stores.keySet()){
            if(z.getCity().equalsIgnoreCase(zone.getCity()) && z.getState().equals(zone.getState())){
                return z;
            }
        }
        return null;
    }

    private static boolean zoneExists(Zone zone) {
        boolean exists = false;
        for(Zone z : stores.keySet()){
            if(z.getCity().equalsIgnoreCase(zone.getCity()) && z.getState().equals(zone.getState())){
                exists = true;
                break;
            }
        }
        return exists;
    }

    private static boolean saveStores() {

        Utils.getLogger().info("Saving store file: {}", storeFile.getAbsolutePath());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storeFile))) {
            JSONObject data = new JSONObject();
            JSONArray storesArray = new JSONArray();
            for(Zone zone : stores.keySet()){
                for(Store store : stores.get(zone)){
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

            writer.write(data.toString(2));
            Utils.getLogger().info("Store file written successfully.");
        } catch (IOException e) {
            Utils.getLogger().error("Error saving dash to file", e);
        }
        return true;
    }

    public static void saveDash(Dash dash, String user) {
        //Save to file
        File file = new File(dashesFolder, user + "/" + dash.getStartTime().replaceAll(":",".") + ".json");
        if(!file.getParentFile().exists()){
            Utils.getLogger().info("{} did not have a dashes folder.. Creating: {}", user, file.getParentFile().mkdirs());
        }
        Utils.getLogger().info("Saving dash to file: {}", file.getAbsolutePath());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(new JSONObject(dash).toString(2));
            Utils.getLogger().info("File written successfully.");
        } catch (IOException e) {
            Utils.getLogger().error("Error saving dash to file", e);
        }

        //Check zones and stores

    }


    public static Set<Zone> getZones() {
        return stores.keySet();
    }

    public static Collection<Store> getStores(Zone zone){
        return stores.get(zone);
    }

    public static List<Store> getStores(){
        List<Store> allStores = new ArrayList<>();
        for(List<Store> storeList : stores.values()){
            allStores.addAll(storeList);
        }
        return allStores;
    }

    public static void saveStore(Store store) {
        Zone zone = store.getZone();
        if(!stores.containsKey(zone)){
            stores.put(zone, new ArrayList<>());
        }
        stores.get(zone).add(store);
        saveStores();
    }
}
