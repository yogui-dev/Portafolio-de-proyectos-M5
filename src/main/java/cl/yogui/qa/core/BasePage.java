package cl.yogui.qa.core;

import org.openqa.selenium.*; // Importa las clases básicas de Selenium
import org.openqa.selenium.support.ui.ExpectedConditions; // Importa condiciones de espera
import org.openqa.selenium.support.ui.WebDriverWait; // Importa WebDriverWait

import java.time.Duration; // Importa Duration para tiempos de espera

// Clase base para todas las páginas del POM
public class BasePage {
    // Driver compartido por las páginas, obtenido de la fábrica
    protected WebDriver driver; // Referencia al WebDriver
    // Espera explícita para sincronización confiable
    protected WebDriverWait wait; // Referencia al WebDriverWait

    // Constructor que inicializa driver y wait
    public BasePage() { // Constructor por defecto
        this.driver = DriverFactory.getDriver(); // Obtiene el driver actual
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Configura espera explícita de 10s
    }

    // Navega a una ruta relativa a la baseUrl
    protected void navigateToPath(String path) { // Método de navegación
        String url = Config.get().getBaseUrl() + path; // Construye la URL completa
        driver.navigate().to(url); // Navega a la URL
    }

    // Espera hasta que un elemento sea visible
    protected WebElement waitForVisible(By locator) { // Espera visibilidad
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // Devuelve el elemento visible
    }

    // Hace click en un elemento cuando está clickable
    protected void click(By locator) { // Click seguro
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click(); // Espera y hace click
    }

    // Escribe texto en un campo de entrada
    protected void type(By locator, String text) { // Escribe en input
        WebElement el = waitForVisible(locator); // Espera a que sea visible
        el.clear(); // Limpia el contenido previo
        el.sendKeys(text); // Envía el texto
    }

    // Obtiene el texto de un elemento visible
    protected String getText(By locator) { // Obtiene texto
        return waitForVisible(locator).getText().trim(); // Retorna el texto sin espacios extremos
    }

    // Verifica si un elemento está visible en pantalla
    protected boolean isDisplayed(By locator) { // Verifica visibilidad
        try { // Maneja excepciones si no existe
            return waitForVisible(locator).isDisplayed(); // Retorna true si es visible
        } catch (TimeoutException e) { // Si no se encuentra a tiempo
            return false; // Retorna false
        }
    }
}
