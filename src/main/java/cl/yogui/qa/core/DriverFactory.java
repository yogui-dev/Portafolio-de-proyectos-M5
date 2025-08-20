package cl.yogui.qa.core;

import io.github.bonigarcia.wdm.WebDriverManager; // Importa WebDriverManager para manejar drivers
import org.openqa.selenium.WebDriver; // Importa la interfaz WebDriver
import org.openqa.selenium.chrome.ChromeDriver; // Importa el driver de Chrome
import org.openqa.selenium.chrome.ChromeOptions; // Importa opciones de Chrome

// Fábrica de drivers usando patrón ThreadLocal para soportar ejecución paralela segura
public class DriverFactory {
    // ThreadLocal para mantener un WebDriver por hilo de ejecución
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>(); // Almacena el driver por hilo

    // Inicializa el WebDriver según la configuración
    public static void initDriver() { // Método para crear el driver
        // Obtiene la configuración global
        Config cfg = Config.get(); // Lee baseUrl, browser y headless

        // Por ahora soportamos Chrome (valor por defecto)
        if (cfg.getBrowser().equalsIgnoreCase("chrome")) { // Verifica si el navegador es Chrome
            WebDriverManager.chromedriver().setup(); // Descarga y configura el binario de ChromeDriver
            ChromeOptions options = new ChromeOptions(); // Crea opciones para Chrome
            if (cfg.isHeadless()) { // Si está en modo headless
                options.addArguments("--headless=new"); // Activa modo headless moderno
            }
            options.addArguments("--remote-allow-origins=*"); // Evita restricciones de orígenes
            options.addArguments("--window-size=1920,1080"); // Fija tamaño de ventana
            WebDriver driver = new ChromeDriver(options); // Crea instancia del driver de Chrome
            driver.manage().window().maximize(); // Maximiza la ventana
            DRIVER.set(driver); // Guarda el driver en ThreadLocal
        } else {
            // Si se solicita un navegador no soportado, lanza excepción clara
            throw new IllegalArgumentException("Unsupported browser: " + cfg.getBrowser()); // Informa error de navegador
        }
    }

    // Devuelve el WebDriver actual del hilo
    public static WebDriver getDriver() { // Getter para el driver
        return DRIVER.get(); // Retorna el driver asociado al hilo
    }

    // Cierra y limpia el WebDriver
    public static void quitDriver() { // Método para cerrar el driver
        WebDriver driver = DRIVER.get(); // Obtiene el driver
        if (driver != null) { // Verifica que exista
            driver.quit(); // Cierra la sesión del navegador
            DRIVER.remove(); // Limpia el ThreadLocal
        }
    }
}
