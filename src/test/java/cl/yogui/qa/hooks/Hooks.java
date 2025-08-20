package cl.yogui.qa.hooks;

import cl.yogui.qa.core.Config; // Importa la configuración global
import cl.yogui.qa.core.DriverFactory; // Importa la fábrica de drivers
import io.cucumber.java.After; // Importa la anotación @After
import io.cucumber.java.Before; // Importa la anotación @Before
import io.cucumber.java.Scenario; // Importa el tipo Scenario para adjuntar evidencia
import org.openqa.selenium.OutputType; // Tipo de salida para screenshots
import org.openqa.selenium.TakesScreenshot; // Interfaz para tomar screenshots
import org.openqa.selenium.WebDriver; // Interfaz del driver

import java.io.IOException; // Manejo de excepciones de IO
import java.nio.file.Files; // Utilidad para archivos
import java.nio.file.Path; // Representa rutas de archivo
import java.nio.file.Paths; // Utilidad para construir rutas
import java.time.LocalDateTime; // Fecha y hora para nombrar screenshots
import java.time.format.DateTimeFormatter; // Formato de fecha

// Hooks de Cucumber para inicializar y cerrar el navegador
public class Hooks {

    // Se ejecuta antes de cada escenario
    @Before
    public void setUp() { // Inicializa el navegador y navega al baseUrl
        DriverFactory.initDriver(); // Crea el WebDriver según la configuración
        WebDriver driver = DriverFactory.getDriver(); // Obtiene el WebDriver actual
        driver.get(Config.get().getBaseUrl()); // Abre la URL base
    }

    // Se ejecuta después de cada escenario
    @After
    public void tearDown(Scenario scenario) { // Cierra el navegador y adjunta screenshot en caso de fallo
        WebDriver driver = DriverFactory.getDriver(); // Obtiene el WebDriver actual
        try { // Intenta tomar y guardar el screenshot si falló
            if (scenario.isFailed() && driver != null) { // Verifica si el escenario falló
                // Toma el screenshot como bytes
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES); // Captura imagen
                // Adjunta el screenshot al reporte de Cucumber
                scenario.attach(screenshot, "image/png", "Failure Screenshot"); // Adjunta evidencia

                // Crea carpeta de destino si no existe
                Path screenshotsDir = Paths.get("target", "screenshots"); // Define ruta de screenshots
                Files.createDirectories(screenshotsDir); // Asegura la existencia de la carpeta

                // Genera nombre de archivo con timestamp y nombre de escenario
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")); // Crea timestamp
                String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9-_]", "_"); // Sanitiza nombre
                Path screenshotPath = screenshotsDir.resolve("FAILED_" + safeName + "_" + timestamp + ".png"); // Construye nombre final

                // Guarda el screenshot en el sistema de archivos
                Files.write(screenshotPath, screenshot); // Escribe archivo en disco
            }
        } catch (IOException e) { // Maneja errores de IO al guardar
            // No interrumpe el cierre del navegador; solo registra en consola
            System.err.println("Error saving screenshot: " + e.getMessage()); // Imprime error
        } finally { // Siempre cierra el navegador
            DriverFactory.quitDriver(); // Cierra y limpia el WebDriver
        }
    }
}
