# whoknows! - Tu Compañero de Aventuras en Minecraft

![Versión](https://img.shields.io/badge/Minecraft-1.21+-green)
![Java](https://img.shields.io/badge/Java-21-orange)

## ¿Qué es whoknows!?

**whoknows!** es un plugin para servidores de Minecraft 1.21+ que añade **AmigoBot**, un compañero virtual diseñado especialmente para jugadores solitarios. ¡Nunca más estarás solo en tus aventuras!

## Características

### 🤖 AmigoBot - Tu Amigo Virtual

- **Conversaciones naturales**: Habla con @amigobot en el chat o usa `/amigo [mensaje]`
- **Comentarios contextuales**: Responde a lo que haces (minar, construir, combatir, etc.)
- **Iniciativa propia**: Ocasionalmente hace comentarios aleatorios sobre el juego
- **Saludos y despedidas**: Te saluda al entrar y se despide al salir

### 🎮 Comportamientos Inteligentes

El bot "sabe" sobre diferentes actividades del juego:

| Actividad | Emoji | Descripción |
|-----------|-------|-------------|
| ⛏️ Minería | Consejos sobre minerales, cuevas y técnicas |
| ⚔️ Combate | Tips para luchar contra mobs |
| 🍳 Cocina | Ideas sobre comida y granjas automáticas |
| 🔨 Construcción | Inspiración y consejos de diseño |
| 🗺️ Exploración | Lugares interesantes para visitar |
| 🎣 Pesca | Datos curiosos y técnicas |
| 🕳️ Cuevas | Consejos para explorar cavernas |
| 🌾 Granjas | Ideas de cultivos y animales |

## Instalación

### Requisitos

- Minecraft Server 1.21 o superior
- Java 21
- PaperMC o Spigot recomendado

### Pasos

1. Descarga el archivo `whoknows-1.0.0.jar`
2. Colócalo en la carpeta `plugins` de tu servidor
3. Reinicia el servidor
4. ¡Listo! El bot estará activo automáticamente

## Uso

### Comandos

```
/amigo <mensaje>     - Habla directamente con AmigoBot
@amigobot <mensaje>  - Menciona al bot en el chat
```

### Ejemplos de Conversación

```
Jugador: @amigobot hola, ¿qué tal?
AmigoBot: ¡Hola Jugador! 😊 ¿Qué planes tienes hoy?

Jugador: /amigo estoy aburrido, ¿qué hago?
AmigoBot: ¡Tengo ideas! 🌟 Podemos buscar diamantes, construir una base, o explorar el Nether.

Jugador: @amigobot ¿dónde encuentro diamantes?
AmigoBot: ¡La minería es lo mejor! ⛏️ Mi consejo: busca en Y=-58 para diamantes.
```

## Configuración

El plugin funciona perfectamente con la configuración por defecto. No requiere configuración adicional.

## Para Desarrolladores

### Compilar desde código fuente

```bash
cd whoknows
gradle build
```

El archivo JAR se generará en `build/libs/whoknows-1.0.0.jar`

### Estructura del Proyecto

```
whoknows/
├── src/main/java/com/whoknows/
│   ├── WhoKnowsPlugin.java      # Clase principal
│   └── bot/
│       ├── AmigoBot.java        # Lógica del bot
│       ├── behaviors/           # Comportamientos
│       │   ├── Behavior.java
│       │   ├── MiningBehavior.java
│       │   ├── CombatBehavior.java
│       │   └── ...
│       └── conversations/
│           └── ConversationManager.java  # Sistema de diálogo
├── src/main/resources/
│   └── plugin.yml
├── build.gradle
└── README.md
```

## Filosofía

Este plugin nace con la idea de que **nadie debería sentirse solo jugando Minecraft**. AmigoBot no reemplaza a otros jugadores, pero ofrece compañía y consejos a quienes juegan solos, haciendo la experiencia más amena y divertida.

## Contribuciones

¡Las contribuciones son bienvenidas! Si tienes ideas para nuevas frases, comportamientos o características, no dudes en contribuir.

## Licencia

Este proyecto es de código abierto. Úsalo libremente en tu servidor.

---

**¡Gracias por usar whoknows! - Porque jugar solo no significa estar solo** 🎮✨
