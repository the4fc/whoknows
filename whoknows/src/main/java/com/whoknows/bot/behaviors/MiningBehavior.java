package com.whoknows.bot.behaviors;

import com.whoknows.bot.AmigoBot;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import java.util.*;

public class MiningBehavior implements Behavior {
    
    private final AmigoBot bot;
    private final Random random;
    private long lastExecution = 0;
    private final Map<String, String[]> miningPhrases;
    
    public MiningBehavior(AmigoBot bot) {
        this.bot = bot;
        this.random = bot.getRandom();
        this.miningPhrases = new HashMap<>();
        initPhrases();
    }
    
    private void initPhrases() {
        miningPhrases.put("diamond", new String[]{
            "¡Estoy buscando diamantes! ¿Ya encontraste algunos?",
            "Nada como el sonido del pico rompiendo piedra... ¡espero encontrar diamantes!",
            "¿Sabías que los diamantes suelen estar cerca de la lava? ¡Cuidado al minar!"
        });
        miningPhrases.put("iron", new String[]{
            "El hierro es esencial, ¿verdad? Sin él no hay armaduras buenas.",
            "Minando hierro... ¡nunca se tiene suficiente!",
            "¿Has probado a hacer una gran mina en espiral? ¡Es muy eficiente!"
        });
        miningPhrases.put("gold", new String[]{
            "¡Oro! Aunque no sirva para mucho en supervivencia, siempre brilla bonito.",
            "Buscando oro... los cerditos lo adoran, jeje.",
            "El oro del Nether es más abundante, ¿has ido a por ello?"
        });
        miningPhrases.put("redstone", new String[]{
            "La redstone es fascinante... podríamos hacer automatizaciones increíbles.",
            "¿Te gusta la redstone? ¡Se pueden hacer cosas alucinantes!",
            "Minando redstone... algún día haré una granja automática."
        });
        miningPhrases.put("coal", new String[]{
            "Carbón para las antorchas... la luz es vida en las cuevas.",
            "Siempre llevo carbón extra. ¡Nunca sabes cuándo necesitarás luz!",
            "El carbón es el mineral más humble pero el más necesario."
        });
        miningPhrases.put("general", new String[]{
            "Me encanta minar... es tan relajante escuchar solo el pico.",
            "¿Vas a minar conmigo? ¡Entre dos es más divertido!",
            "La minería es mi terapia. Tú cavas, cavas y encuentras tesoros.",
            "A veces paso horas minando sin darme cuenta... ¡es hipnótico!",
            "¿Cuál es tu mineral favorito? A mí me gustan todos, cada uno tiene su utilidad."
        });
    }
    
    @Override
    public boolean debeEjecutarse() {
        // Ejecutar cada 2-5 minutos si hay jugadores online
        long now = System.currentTimeMillis();
        if (now - lastExecution < 120000) return false;
        
        // Solo ejecutar si hay jugadores
        return !Bukkit.getOnlinePlayers().isEmpty() && random.nextDouble() < 0.3;
    }
    
    @Override
    public void ejecutar() {
        lastExecution = System.currentTimeMillis();
        
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (players.isEmpty()) return;
        
        Player randomPlayer = (Player) players.toArray()[random.nextInt(players.size())];
        
        // Verificar si el jugador está minando (en una cueva o subterráneo)
        int y = randomPlayer.getLocation().getBlockY();
        boolean isUnderground = y < 40;
        
        String[] phrases;
        if (isUnderground) {
            // Elegir frase basada en el nivel Y
            if (y < 16) {
                phrases = miningPhrases.get("diamond");
            } else if (y < 32) {
                phrases = miningPhrases.get(random.nextBoolean() ? "iron" : "gold");
            } else if (y < 50) {
                phrases = miningPhrases.get("redstone");
            } else {
                phrases = miningPhrases.get("coal");
            }
        } else {
            phrases = miningPhrases.get("general");
        }
        
        if (phrases != null && phrases.length > 0) {
            String phrase = phrases[random.nextInt(phrases.length)];
            bot.enviarMensajePrivado(randomPlayer, "⛏️ " + phrase);
        }
    }
}
