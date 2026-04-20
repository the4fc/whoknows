package com.whoknows;

import com.whoknows.bot.AmigoBot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class WhoKnowsPlugin extends JavaPlugin {
    
    private static WhoKnowsPlugin instance;
    private AmigoBot amigoBot;
    
    @Override
    public void onEnable() {
        instance = this;
        
        // Guardar configuración por defecto
        saveDefaultConfig();
        
        // Iniciar el bot amigo
        amigoBot = new AmigoBot(this);
        amigoBot.iniciar();
        
        getLogger().info("§a§lwhoknows! §r§aha sido activado!");
        getLogger().info("§7Tu compañero de aventuras está listo para jugar contigo.");
        getLogger().info("§7Usa §f/amigo [mensaje] §7o escribe §f@amigobot [mensaje] §7en el chat.");
    }
    
    @Override
    public void onDisable() {
        if (amigoBot != null) {
            amigoBot.detener();
        }
        getLogger().info("§cwhoknows! ha sido desactivado. ¡Hasta luego!");
    }
    
    public static WhoKnowsPlugin getInstance() {
        return instance;
    }
    
    public AmigoBot getAmigoBot() {
        return amigoBot;
    }
}
