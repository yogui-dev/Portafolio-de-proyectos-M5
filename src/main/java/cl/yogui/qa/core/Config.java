package cl.yogui.qa.core;

// Clase de configuración que lee propiedades del sistema con valores por defecto
public class Config {
    // URL base del sitio a testear
    private final String baseUrl; // Se guarda la baseUrl
    // Navegador a utilizar (chrome por defecto)
    private final String browser; // Se guarda el nombre del navegador
    // Modo headless (false por defecto)
    private final boolean headless; // Se guarda si el modo headless está activo

    // Instancia singleton para reutilizar la misma configuración
    private static final Config INSTANCE = new Config(); // Se crea una instancia única

    // Constructor privado para aplicar el patrón Singleton
    private Config() {
        // Lee la propiedad 'baseUrl' o usa el valor por defecto
        this.baseUrl = System.getProperty("baseUrl", "https://the-internet.herokuapp.com");
        // Lee la propiedad 'browser' o usa 'chrome' como valor por defecto
        this.browser = System.getProperty("browser", "chrome");
        // Lee la propiedad 'headless' y la convierte a booleano, por defecto 'false'
        this.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    // Permite obtener la instancia única de Config
    public static Config get() { // Devuelve la instancia singleton
        return INSTANCE; // Retorna la configuración ya creada
    }

    // Devuelve la URL base configurada
    public String getBaseUrl() { // Getter para baseUrl
        return baseUrl; // Retorna el valor leído
    }

    // Devuelve el navegador configurado
    public String getBrowser() { // Getter para browser
        return browser; // Retorna el navegador seleccionado
    }

    // Indica si el modo headless está activo
    public boolean isHeadless() { // Getter para headless
        return headless; // Retorna true/false según configuración
    }
}
