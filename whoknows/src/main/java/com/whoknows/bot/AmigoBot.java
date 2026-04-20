package com.whoknows.bot;

import com.whoknows.WhoKnowsPlugin;
import com.whoknows.bot.behaviors.*;
import com.whoknows.bot.conversations.ConversationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class AmigoBot implements Listener {
    
    private final WhoKnowsPlugin plugin;
    private final ConversationManager conversationManager;
    private final List<Behavior> behaviors;
    private final Random random;
    private BukkitTask task;
    private boolean running;
    
    // Bot "fantasma" - no es un jugador real, solo responde
    private final String BOT_NAME = "AmigoBot";
    
    public AmigoBot(WhoKnowsPlugin plugin) {
        this.plugin = plugin;
        this.random = new Random();
        this.behaviors = new ArrayList<>();
        this.conversationManager = new ConversationManager(this);
        
        // Registrar comportamientos
        registerBehaviors();
    }
    
    private void registerBehaviors() {
        behaviors.add(new MiningBehavior(this));
        behaviors.add(new CombatBehavior(this));
        behaviors.add(new CookingBehavior(this));
        behaviors.add(new BuildingBehavior(this));
        behaviors.add(new ExploringBehavior(this));
        behaviors.add(new FishingBehavior(this));
        behaviors.add(new CavingBehavior(this));
        behaviors.add(new FarmingBehavior(this));
    }
    
    public void iniciar() {
        running = true;
        
        // Registrar eventos
        Bukkit.getPluginManager().registerEvents(this, plugin);
        
        // Tarea periódica para comportamientos aleatorios
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (!running) return;
                
                // Ejecutar comportamiento aleatorio ocasionalmente
                if (random.nextDouble() < 0.05) { // 5% de probabilidad cada tick
                    ejecutarComportamientoAleatorio();
                }
                
                // Comentario aleatorio en chat ocasionalmente
                if (random.nextDouble() < 0.003) { // ~0.3% cada tick (~1 cada 3 segundos en promedio)
                    hacerComentarioAleatorio();
                }
            }
        }.runTaskTimer(plugin, 100L, 20L); // Cada segundo
        
        plugin.getLogger().info("§eAmigoBot §7está listo para acompañarte!");
    }
    
    public void detener() {
        running = false;
        if (task != null) {
            task.cancel();
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Saludar al jugador
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            String saludo = conversationManager.obtenerSaludoInicial(player.getName());
            enviarMensajeBot(saludo);
        }, 40L);
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        
        // Verificar si el mensaje es para el bot
        if (message.toLowerCase().startsWith("@amigobot") || 
            message.toLowerCase().startsWith("/amigo ")) {
            
            event.setCancelled(true); // Cancelar mensaje normal
            
            // Procesar respuesta
            String cleanMessage = message.replaceFirst("(?i)@amigobot|/amigo", "").trim();
            String respuesta = conversationManager.procesarMensaje(player, cleanMessage);
            
            // Enviar respuesta asíncronamente
            Bukkit.getScheduler().runTask(plugin, () -> {
                enviarMensajeBot("§f" + player.getName() + "§7: " + message);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    enviarMensajeBot(respuesta);
                }, 20L + random.nextInt(40)); // Retraso de 1-3 segundos
            });
        } else {
            // Escuchar chat normal para responder ocasionalmente
            if (random.nextDouble() < 0.02) { // 2% de probabilidad de responder a chat normal
                Bukkit.getScheduler().runTask(plugin, () -> {
                    String respuesta = conversationManager.responderAChat(message, player.getName());
                    if (respuesta != null && !respuesta.isEmpty()) {
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            enviarMensajeBot(respuesta);
                        }, 40L + random.nextInt(60));
                    }
                });
            }
        }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Despedirse
        String despedida = conversationManager.obtenerDespedida(event.getPlayer().getName());
        if (!despedida.isEmpty()) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                enviarMensajeBot(despedida);
            }, 20L);
        }
    }
    
    public void enviarMensajeBot(String mensaje) {
        Bukkit.broadcastMessage("§b[§e" + BOT_NAME + "§b] §r" + mensaje);
    }
    
    public void enviarMensajePrivado(Player player, String mensaje) {
        player.sendMessage("§b[§e" + BOT_NAME + "§b] §r" + mensaje);
    }
    
    private void ejecutarComportamientoAleatorio() {
        if (behaviors.isEmpty()) return;
        
        Behavior behavior = behaviors.get(random.nextInt(behaviors.size()));
        if (behavior.debeEjecutarse()) {
            behavior.ejecutar();
        }
    }
    
    private void hacerComentarioAleatorio() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty()) return;
        
        Player randomPlayer = (Player) players.toArray()[random.nextInt(players.size())];
        String comentario = conversationManager.obtenerComentarioAleatorio(randomPlayer);
        
        if (comentario != null && !comentario.isEmpty()) {
            enviarMensajeBot(comentario);
        }
    }
    
    public Random getRandom() {
        return random;
    }
    
    public ConversationManager getConversationManager() {
        return conversationManager;
    }
}
