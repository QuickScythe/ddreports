package me.ddreports.storage;

import me.ddreports.data.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageManager {

    private static final Map<Zone, List<Store>> stores = new HashMap();
    private static final File dataFolder = new File("data");
    private static final File dashesFolder = new File(dataFolder, "dashes");
    private static final File storeFile = new File(dataFolder, "stores.json");

    public static void init(){
        if(!dataFolder.exists()){
            dataFolder.mkdirs();
        }
        if(!dashesFolder.exists()){
            dashesFolder.mkdirs();
        }
        if(!storeFile.exists()){
            saveStores();
        }
        loadStores();

    }


}
