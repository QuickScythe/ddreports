package me.ddreports.storage;

import json2.JSONObject;
import me.ddreports.data.account.Account;
import me.ddreports.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {

    Map<String, Account> accounts = new HashMap<>();

    public Account getAccount(String user) {
        if(!accounts.containsKey(user)){
            File file = new File(StorageManager.getUsersFolder(), user + "/data.json");
            if(file.exists()) {
                Utils.getLogger().info("Loading account data file: {}", file.getAbsolutePath());
                JSONObject data = StorageManager.loadData(file);
                Account account = new Account();
                account.setUsername(data.getString("username"));
                account.setPassword(data.getString("password"));
                account.setEmail(data.getString("email"));
//                accounts.put(user, StorageManager.loadData(file, Account.class));
                accounts.put(user, account);
            } else return null;
        }
        return accounts.getOrDefault(user, null);
    }

    public void addAccount(Account account) {
        accounts.put(account.getUsername(), account);
        File file = new File(getAccountFolder(account), "data.json");
        if(!file.exists()) {
            Utils.getLogger().info("Creating new account data file: {}", file.getAbsolutePath());
            StorageManager.saveData(file, new JSONObject(account));
        }
    }

    public File getAccountFolder(Account account) {
        File file = new File(StorageManager.getUsersFolder(), account.getUsername());
        if (!file.exists()) {
            Utils.getLogger().info("User folder did not exist.. Creating: {}", file.mkdirs());
        }
        return file;

    }

    public File getDashesFolder(Account account) {
        File file = new File(getAccountFolder(account), "dashes");
        if (!file.exists()) {
            Utils.getLogger().info("Dashes folder did not exist.. Creating: {}", file.mkdirs());
        }
        return file;
    }

}
