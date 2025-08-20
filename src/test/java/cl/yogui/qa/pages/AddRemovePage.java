package cl.yogui.qa.pages;

import cl.yogui.qa.core.BasePage; // Importa la clase base para utilidades comunes
import org.openqa.selenium.By; // Importa By para localizadores
import org.openqa.selenium.support.ui.ExpectedConditions; // Importa condiciones de espera

// Página para /add_remove_elements/
public class AddRemovePage extends BasePage { // Extiende BasePage

    // Localizador del botón "Add Element"
    private final By addElementButton = By.xpath("//button[text()='Add Element']"); // Botón para agregar
    // Localizador de los botones "Delete" agregados dinámicamente
    private final By deleteButtons = By.cssSelector("button.added-manually"); // Colección de elementos

    // Abre la página de Add/Remove Elements
    public void open() { // Navega a la ruta de la funcionalidad
        navigateToPath("/add_remove_elements/"); // Concatena baseUrl + ruta
    }

    // Agrega N elementos haciendo click en "Add Element"
    public void addElements(int count) { // Agrega 'count' elementos
        int initial = getElementsCount(); // Obtiene el conteo inicial
        for (int i = 0; i < count; i++) { // Itera la cantidad deseada
            click(addElementButton); // Hace click en el botón para agregar
        }
        int expected = initial + count; // Calcula el conteo esperado
        // Espera hasta que el número de elementos sea el esperado
        wait.until(ExpectedConditions.numberOfElementsToBe(deleteButtons, expected)); // Sincroniza la vista
    }

    // Elimina N elementos si existen
    public void removeElements(int count) { // Elimina 'count' elementos
        for (int i = 0; i < count; i++) { // Itera la cantidad a eliminar
            int current = getElementsCount(); // Obtiene el conteo actual
            if (current == 0) break; // Si no hay elementos, termina
            click(deleteButtons); // Click en el primer botón "Delete" disponible
            // Espera a que el conteo disminuya
            int expected = Math.max(0, current - 1); // Calcula el esperado
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(deleteButtons, current)); // Espera decremento
        }
    }

    // Devuelve el número actual de elementos "Delete"
    public int getElementsCount() { // Obtiene el conteo de elementos
        return driver.findElements(deleteButtons).size(); // Cuenta elementos presentes
    }
}
