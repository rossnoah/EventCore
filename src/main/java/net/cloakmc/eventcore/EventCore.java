package net.cloakmc.eventcore;

import dev.s7a.base64.Base64ItemStack;
import net.cloakmc.eventcore.commands.*;
import net.cloakmc.eventcore.gui.GUIListener;
import net.cloakmc.eventcore.hooks.DeathMessagesKillListener;
import net.cloakmc.eventcore.hooks.PlaceholderAPIExpansion;
import net.cloakmc.eventcore.listener.*;
import net.cloakmc.eventcore.util.GamemodeUtil;
import net.cloakmc.eventcore.util.SchedulerUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public final class EventCore extends JavaPlugin {

    public static Plugin plugin;
    public static EventCore instance;
    public static Configuration cfg;
    public static boolean inGame = false;
    public static boolean kitOnJoin = true;
    public static boolean blockExplosions = false;
    public static ItemStack[] kit = new ItemStack[41];
    public static HashMap<UUID,Integer> kills = new HashMap<>();
    public static HashMap<UUID,String> names = new HashMap<>();
    public static int buildLimit = 250;
    public static int simulatePlayers = 0;

    public static long timeStarted = 0;
    public static MiniMessage miniMessage = MiniMessage.miniMessage();
    public static String red;
    public static String green;
    @Override
    public void onEnable() {
        plugin = this;
        instance = this;
        cfg = this.getConfig();
        SchedulerUtils.setPlugin(this);




        this.saveDefaultConfig();
        if(cfg.contains("kit")) {
            this.loadData();
        }

        startUp();

        registerCommands();
        registerEvents();
//        this.getCommand("dashboard").setExecutor(new DashboardCommand());
//        this.getCommand("help").setExecutor(new HelpCommand());

        loadData();

        red = cfg.getString("red");
        green = cfg.getString("green");


        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIExpansion().register();
        }




    }

    @Override
    public void onDisable() {


    }



    private void registerCommands(){

        this.getCommand("dashboard").setExecutor(new DashboardCommand());
        this.getCommand("loadkit").setExecutor(new Loadkit());
        this.getCommand("savekit").setExecutor(new Savekit());
        this.getCommand("setborder").setExecutor(new SetBorderCommand());
        this.getCommand("shrinkborder").setExecutor(new ShrinkBorderCommand());
        this.getCommand("expandborder").setExecutor(new ExpandBorderCommand());
        this.getCommand("kickspectators").setExecutor(new KickSpectators());
        this.getCommand("alive").setExecutor(new AliveCountCommand());
        this.getCommand("discord").setExecutor(new DiscordCommand());
        this.getCommand("help").setExecutor(new HelpCommand());
        this.getCommand("spread").setExecutor(new SpreadPlayersCommand());
        this.getCommand("setbuildlimit").setExecutor(new SetBuildLimitCommand());
        this.getCommand("top").setExecutor(new TopKillsCommand());
        this.getCommand("clearspawn").setExecutor(new ClearSpawn());
    }

    private void registerEvents(){

        getServer().getPluginManager().registerEvents(new DeathListener(),this);
        getServer().getPluginManager().registerEvents(new JoinListener(),this);
        getServer().getPluginManager().registerEvents(new GUIListener(),this);
        getServer().getPluginManager().registerEvents(new PreventExplosionsListener(),this);
        getServer().getPluginManager().registerEvents(new SpectatorListener(),this);
        //Disabled to use border listener instead
        //getServer().getPluginManager().registerEvents(new PearlListener(),this);
        getServer().getPluginManager().registerEvents(new GamemodeListener(),this);
        getServer().getPluginManager().registerEvents(new InventoryHelperListener(),this);
        getServer().getPluginManager().registerEvents(new LeaveListener(),this);
        getServer().getPluginManager().registerEvents(new PreGameListener(),this);
        getServer().getPluginManager().registerEvents(new BorderListener(),this);
        getServer().getPluginManager().registerEvents(new AntiMobSpawn(),this);
        getServer().getPluginManager().registerEvents(new BuildLimitListener(),this);
        getServer().getPluginManager().registerEvents(new DurabilityListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerDeathDrops(),this);
        getServer().getPluginManager().registerEvents(new PreventBlockItemDrops(),this);
        getServer().getPluginManager().registerEvents(new AntiVoidDamage(),this);
        getServer().getPluginManager().registerEvents(new DeathListener(),this);
        getServer().getPluginManager().registerEvents(new DeathMessagesKillListener(),this);
        getServer().getPluginManager().registerEvents(new HungerListener(),this);
    }
    public static void saveData(){

        String[] data = new String[41];
        for(int i = 0; i<41;i++){
            data[i] = Base64ItemStack.encode(kit[i]);
        }

//        save to a file kit.txt with one line per itemstack

//        create a new file with the name kit.txt

        File file = new File(plugin.getDataFolder(), "kit.data");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        write the kit to the file
        try {
            FileWriter fw = new FileWriter(file);
            for (String s : data) {
                fw.write(s + "\n");
            }
            fw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }




    }


    public void loadData(){

//        check if the file kit.data exists

        File file = new File(getDataFolder(), "kit.data");
        if(!file.exists()){
            return;
        }

//        read the file line by line and add the itemstacks to the kit array
        try {
            java.util.Scanner scanner = new java.util.Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                this.kit[i] = Base64ItemStack.decode(line);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Plugin getPlugin() {
        return plugin;
    }

    public static EventCore getInstance() {
        return instance;
    }

    public static Configuration getConfiguration() {
        return cfg;
    }


    public static void startUp(){
        if(Bukkit.getWorld("world")!=null) {
            World w = Bukkit.getWorld("world");
            w.getWorldBorder().setCenter(0,0);
            w.getWorldBorder().setSize(5000);
            w.setPVP(false);
            w.setTime(12000);
            w.setDifficulty(Difficulty.HARD);
            w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            w.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
            w.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
            w.setGameRule(GameRule.DISABLE_RAIDS, true);
            w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            w.setGameRule(GameRule.DO_ENTITY_DROPS, true);
            w.setGameRule(GameRule.DO_FIRE_TICK, true);
            w.setGameRule(GameRule.DO_INSOMNIA, false);
            w.setGameRule(GameRule.DO_LIMITED_CRAFTING, false);
            w.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
            w.setGameRule(GameRule.SPAWN_RADIUS, 0);
            w.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
            w.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            w.setGameRule(GameRule.NATURAL_REGENERATION, true);
            w.setGameRule(GameRule.SPAWN_RADIUS, 0);
        }

        inGame = false;
        kitOnJoin = true;
    }

    public static void startGame(){


        timeStarted = System.currentTimeMillis();
        final Component mainTitle = MiniMessage.miniMessage().deserialize(cfg.getString("green")+"Event Started");
        final Component subtitle = MiniMessage.miniMessage().deserialize("<white>PvP Enabled");

        // Creates a simple title with the default values for fade-in, stay on screen and fade-out durations
        final Title title = Title.title(mainTitle, subtitle);

        // Send the title to your audience

        for (Player p:Bukkit.getOnlinePlayers()) {
            p.setHealth(20);
            p.setSaturation(20);
            p.setFoodLevel(20);
            p.updateInventory();
            p.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Event Starting Now!"));
            p.sendTitle(String.valueOf(MiniMessage.miniMessage().deserialize(EventCore.green+"EVENT STARTED")),"",1,20,1);
            p.showTitle(title);
            GamemodeUtil.gamemodeAll(GameMode.SURVIVAL);
        }
        if(Bukkit.getWorld("world")!=null) {
            World w = Bukkit.getWorld("world");
            w.setPVP(true);
        }
        inGame = true;
        kitOnJoin = false;




    }



}
