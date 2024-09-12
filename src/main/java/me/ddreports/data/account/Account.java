package me.ddreports.data.account;

import json2.JSONObject;
import me.ddreports.data.Dash;
import me.ddreports.storage.StorageManager;
import me.ddreports.utils.Utils;

import java.io.File;

public class Account {

    String username;
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void saveDash(Dash dash) {
        //Save to file
        File file = new File(StorageManager.getAccountManager().getDashesFolder(this), dash.getStartTime().replaceAll(":", ".") + ".json");
        if (!file.getParentFile().exists()) {
            Utils.getLogger().info("{} did not have a dashes folder.. Creating: {}", getUsername(), file.getParentFile().mkdirs());
        }
        Utils.getLogger().info("Saving dash to file: {}", file.getAbsolutePath());
        StorageManager.saveData(file, new JSONObject(dash));

        //Check zones and stores

    }
}
