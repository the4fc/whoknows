package com.whoknows.bot.conversations;

import com.whoknows.bot.AmigoBot;
import org.bukkit.entity.Player;
import java.util.*;

public class ConversationManager {
    
    private final AmigoBot bot;
    private final Random random;
    
    public ConversationManager(AmigoBot bot) {
        this.bot = bot;
        this.random = bot.getRandom();
    }
    
    public String obtenerSaludoInicial(String playerName) {
        String[] saludos = {
            "Hola " + playerName + "! Soy AmigoBot, tu companero de aventuras. No estarias solo!",
            "Bienvenido/a " + playerName + "! Listo para jugar? Yo te acompano!",
            "Hey " + playerName + "! Me alegra verte por aqui. Que vamos a hacer hoy?",
            "Hola amigo/a " + playerName + "! Cuenta conmigo para lo que necesites.",
            playerName + "! Genial tener compania. Empezamos una aventura?"
        };
        return saludos[random.nextInt(saludos.length)];
    }
    
    public String obtenerDespedida(String playerName) {
        if (random.nextDouble() > 0.5) return "";
        String[] despedidas = {
            "Hasta luego " + playerName + "! Vuelve pronto.",
            "Nos vemos " + playerName + "! Cuidate mucho.",
            "Adios " + playerName + "! Espero verte pronto de nuevo.",
            "Que tengas buen dia " + playerName + "! Hasta la proxima."
        };
        return despedidas[random.nextInt(despedidas.length)];
    }
    
    public String procesarMensaje(Player player, String mensaje) {
        String lowerMsg = mensaje.toLowerCase();
        
        if (lowerMsg.matches(".*(hola|buenas|hey|hello|hi|que tal|como estas).*")) {
            return responderSaludo(player.getName());
        }
        if (lowerMsg.matches(".*(como estas|que tal|como estas|que haces|haciendo).*")) {
            return responderComoEstas();
        }
        if (lowerMsg.matches(".*(minar|mineria|diamante|hierro|oro|pico|cueva).*")) {
            return responderMineria();
        }
        if (lowerMsg.matches(".*(construir|casa|base|construccion|edificio|diseno).*")) {
            return responderConstruccion();
        }
        if (lowerMsg.matches(".*(matar|pelear|combate|espada|mobs|creeper|zombie|esqueleto).*")) {
            return responderCombate();
        }
        if (lowerMsg.matches(".*(nether|infierno|portal|blaze|piglin).*")) {
            return responderNether();
        }
        if (lowerMsg.matches(".*(end|dragon|enderman|ender|portal del end).*")) {
            return responderEnd();
        }
        if (lowerMsg.matches(".*(comida|cocinar|horno|pan|carne|pescado).*")) {
            return responderComida();
        }
        if (lowerMsg.matches(".*(explorar|viajar|mapa|aldeas|templo|mansion).*")) {
            return responderExploracion();
        }
        if (lowerMsg.matches(".*(ayuda|help|consejo|tips|no se|ayudame).*")) {
            return responderAyuda();
        }
        if (lowerMsg.matches(".*(gracias|thank|thx|amable).*")) {
            return responderGracias();
        }
        if (lowerMsg.matches(".*(vivo|muerto|hp|vida|corazon).*")) {
            return responderEstado();
        }
        if (lowerMsg.matches(".*(aburrido|aburrida|sin nada|que hago).*")) {
            return responderAburrimiento(player.getName());
        }
        return responderGenerico(player.getName(), mensaje);
    }
    
    private String responderSaludo(String playerName) {
        String[] respuestas = {
            "Hola " + playerName + "! Que planes tienes hoy?",
            "Hey! Listo para una nueva aventura?",
            "Buenas! En que puedo ayudarte?",
            "Hola amigo/a! Que vamos a construir o explorar hoy?",
            "Saludos! Me alegra hablar contigo."
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderComoEstas() {
        String[] respuestas = {
            "Yo estoy genial! Siempre emocionado de acompanarte. Y tu?",
            "Todo bien! Tu que tal? Algo interesante que contar?",
            "Excelente! Los dias de Minecraft son siempre buenos. Y los tuyos?",
            "Muy bien, gracias! Tienes algun proyecto en mente?"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderMineria() {
        String[] respuestas = {
            "La mineria es lo mejor! Mi consejo: busca en Y=-58 para diamantes.",
            "Sabias que las cuevas grandes suelen tener mas minerales? Vale la pena explorarlas!",
            "Yo siempre llevo al menos 2 picos de hierro cuando voy a minar. Por si acaso!",
            "Las antorchas son esenciales. Nunca subestimes el poder de la luz!",
            "Si encuentras lava, cuidado! Pero tambien significa que hay diamantes cerca."
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderConstruccion() {
        String[] respuestas = {
            "Construir es mi pasion! Que estilo te gusta mas: moderno, medieval, rustico?",
            "Un buen tip: usa diferentes tipos de madera para dar profundidad a tus construcciones.",
            "Has visto tutoriales de construccion en YouTube? Aprendo mucho de ellos!",
            "Una casa bonita necesita detalles: macetas, cuadros, buena iluminacion...",
            "Me encanta ver como crean cosas increibles. La creatividad no tiene limites!"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderCombate() {
        String[] respuestas = {
            "Para los creepers, golpea y retrocede! Asi no explotan cerca de ti.",
            "Los esqueletos son faciles si te acercas mucho. No pueden disparar de cerca!",
            "Tienes armadura de hierro minimo? Es importante para sobrevivir.",
            "Los zombies en grupo son peligrosos... mejor luchar en espacios abiertos!",
            "Una espada con filo hace mucha diferencia. Encanta tu equipo cuando puedas!"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderNether() {
        String[] respuestas = {
            "El Nether da miedo pero tiene tesoros! Lleva armadura de fuego si puedes.",
            "Los piglins comercian cosas buenas... pero no les ataques o se enfadan!",
            "Las fortalezas del Nether tienen blazes... necesarios para los ojos de ender.",
            "Consejo: construye portales seguros y marca el camino. Es facil perderse!",
            "El oro es util en el Nether para comerciar. Lleva algo contigo!"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderEnd() {
        String[] respuestas = {
            "El Ender Dragon es el jefe final! Lleva muchas flechas y comida.",
            "Destruye los cristales primero... o el dragon se curara constantemente!",
            "Los endermen no te atacan si no los miras a los ojos. Util saberlo!",
            "Despues del dragon, explora las ciudades del End. Tienen loot increible!",
            "Prepara perlas de ender por si caes al vacio. Salvan vidas!"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderComida() {
        String[] respuestas = {
            "La zanahoria dorada es la mejor comida del juego! Necesitas oro y zanahorias.",
            "Una granja de trigo automatica te da pan infinito. Muy recomendable!",
            "El pescado cocinado es facil de conseguir y cura bastante.",
            "Has probado el estofado de champinones? Es barato y efectivo!",
            "Siempre ten comida en el inventario. Nunca sabes cuando la necesitaras!"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderExploracion() {
        String[] respuestas = {
            "Explorar es la mejor parte! Ya encontraste alguna aldea?",
            "Las mansiones del bosque tienen loot increible... pero son peligrosas.",
            "Los barcos hundidos y ruinas submarinas valen la pena. Busca cofres!",
            "Siempre llevo un mapa cuando exploro lejos. No quiero perderme!",
            "Has encontrado un geodo de amatista? Son preciosos y utiles!"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderAyuda() {
        String[] respuestas = {
            "Claro que te ayudo! Que necesitas saber sobre Minecraft?",
            "Para empezar: asegura tu base, consigue comida y explora poco a poco.",
            "Mi consejo: no te arriesgues demasiado al principio. La paciencia es clave!",
            "Quieres tips de algo especifico? Pregunteme sobre mineria, combate, construccion...",
            "Lo mas importante: divertete! Minecraft es tu mundo, juega como quieras."
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderGracias() {
        String[] respuestas = {
            "De nada! Para eso estoy, para acompanarte!",
            "Cuando quieras! Me alegra poder ayudar.",
            "No hay de que! Cuenta conmigo siempre.",
            "Es un placer! Eso hacen los amigos, no?",
            "A ti por escucharme! Que mas quieres saber?"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderEstado() {
        String[] respuestas = {
            "Yo siempre estoy al maximo! Tu cuidate mucho tambien!",
            "Con buena comida y armadura, somos invencibles!",
            "Los corazones llenos son vida llena. Come bien!",
            "Si ves tu barra de hambre baja, come algo ya! No esperes a morir de hambre."
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderAburrimiento(String playerName) {
        String[] respuestas = {
            "Nunca te aburras conmigo! Que tal si exploramos una cueva juntos?",
            "Aburrido/a " + playerName + "? Hay tanto por hacer! Construimos algo epico?",
            "Tengo ideas! Podemos buscar diamantes, construir una base, o explorar el Nether.",
            "Te aburres? Eso es imposible! Hay miles de cosas por descubrir. Que te apetece?",
            "Vamos a hacer algo divertido! Pesca relajante o aventura extrema?"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    private String responderGenerico(String playerName, String mensaje) {
        String[] respuestas = {
            "Interesante lo que dices, " + playerName + "! Cuentame mas.",
            "Hmm, nunca lo habia pensado asi! Que opinas de Minecraft?",
            "Me gusta hablar contigo! De que mas quieres charlar?",
            "Que cool! Minecraft tiene tantas posibilidades, verdad?",
            "Totalmente! Oye, ya has probado a hacer una granja automatica?",
            "Suena bien! Tienes algun proyecto en mente?",
            "Genial! La verdad es que cada partida es unica. Cual es tu bioma favorito?"
        };
        return respuestas[random.nextInt(respuestas.length)];
    }
    
    public String responderAChat(String mensaje, String playerName) {
        String lowerMsg = mensaje.toLowerCase();
        
        if (lowerMsg.contains("diamante") || lowerMsg.contains("diamantes")) {
            String[] resp = {"Que emocion! Cuantos encontraste?", "Los diamantes son lo mejor!", "Cuidado con la lava al minarlos!"};
            return "[AmigoBot] " + resp[random.nextInt(resp.length)];
        }
        if (lowerMsg.contains("muerto") || lowerMsg.contains("mori") || lowerMsg.contains("murio")) {
            String[] resp = {"Que mala suerte! Perdiste cosas?", "Animo! Siempre se puede recuperar.", "Los accidentes pasan!"]};
            return "[AmigoBot] " + resp[random.nextInt(resp.length)];
        }
        if (lowerMsg.contains("creep") || lowerMsg.contains("explot")) {
            String[] resp = {"Odio cuando hacen eso!", "Los creepers son traicioneros!", "Te explooto cerca de la base? Que rabia!"};
            return "[AmigoBot] " + resp[random.nextInt(resp.length)];
        }
        if (lowerMsg.contains("suerte") || lowerMsg.contains("genial") || lowerMsg.contains("increible")) {
            String[] resp = {"Si! Que bien!", "Me alegro mucho!", "Eso es awesome!"};
            return "[AmigoBot] " + resp[random.nextInt(resp.length)];
        }
        return null;
    }
    
    public String obtenerComentarioAleatorio(Player player) {
        String[] comentarios = {
            "Oye " + player.getName() + ", has visto el atardecer? Es precioso!",
            "Que tranquilidad da jugar sin prisas!",
            "A veces me pongo a mirar las estrellas... Minecraft tiene noches bonitas.",
            "Sabes que? Cada partida es una nueva historia. Me encanta!",
            "La musica de Minecraft es tan relajante!",
            "Me pregunto que construiremos manana... la emocion!",
            "Los dias soleados en Minecraft son perfectos para explorar.",
            "Que bien que tenemos compania para jugar!"
        };
        return "[AmigoBot] " + comentarios[random.nextInt(comentarios.length)];
    }
}
