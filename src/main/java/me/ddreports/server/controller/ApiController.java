package me.ddreports.server.controller;

import json2.JSONArray;
import json2.JSONObject;
import me.ddreports.data.Store;
import me.ddreports.data.Zone;
import me.ddreports.storage.StorageManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class ApiController {
    @GetMapping("/api/graph-data")
    public List<Integer> graph_data() {
        // Example data, replace with your actual data source
        return Arrays.asList(10, 20, 15, 36, 27);
    }

    @GetMapping("/api/stores")
    public String stores(@RequestParam(value = "zone", required = false, defaultValue = "") String zoneId) {
        JSONObject data = new JSONObject();
        if (!zoneId.equals("")) {
            Zone zone = StorageManager.getZone(UUID.fromString(zoneId));
            if(zone == null) return data.put("error", "Zone not found").toString(2);
            data.put(zone.getName(), new JSONArray());
            for (Store store : StorageManager.getStores(zone)) {
                data.getJSONArray(zone.getName()).put(store.getName());
            }
        } else
            for (Zone zone : StorageManager.getZones()) {
                data.put(zone.getName(), new JSONArray());
                data.put(zone.getName() + "Id", zone.getUid().toString());
                for (Store store : StorageManager.getStores(zone)) {
                    data.getJSONArray(zone.getName()).put(store.getName());
                }

            }
        // Example data, replace with your actual data source
        return data.toString(2);
    }


}
