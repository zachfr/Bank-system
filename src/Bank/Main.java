package Bank;

import Bank.commands.BankGUI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin implements Listener {

    private File dataFile;
    private YamlConfiguration dataConfig;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        BankGUI bankgui = new BankGUI(this);

        loadDataConfig();

    }

    private void loadDataConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        dataFile = new File(getDataFolder() + File.separator + "data.yml");

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    

}
