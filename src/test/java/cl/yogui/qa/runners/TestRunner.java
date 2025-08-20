package cl.yogui.qa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests; // Clase base para ejecutar Cucumber con TestNG
import io.cucumber.testng.CucumberOptions; // Anotación para configurar Cucumber
import org.testng.annotations.DataProvider; // Proveedor de datos para paralelismo

// Runner de Cucumber con TestNG
@CucumberOptions(
        features = "src/test/resources/features", // Ruta de los archivos .feature
        glue = {"cl.yogui.qa"}, // Paquetes que contienen hooks y steps
        plugin = {
                "pretty", // Salida legible en consola
                "json:target/reports/cucumber.json", // Reporte JSON para integraciones
                "html:target/reports/cucumber.html" // Reporte HTML simple
        }
        // Nota: El filtrado por tags se maneja vía propiedad del sistema: -Dcucumber.filter.tags="@smoke"
)
public class TestRunner extends AbstractTestNGCucumberTests { // Clase que ejecuta escenarios como pruebas TestNG

    // Habilita ejecución paralela de escenarios si TestNG lo permite
    @Override
    @DataProvider(parallel = true) // Provee escenarios para ejecución (true = paralelo)
    public Object[][] scenarios() { // Método que devuelve escenarios de Cucumber
        return super.scenarios(); // Delega en la implementación base
    }
}
