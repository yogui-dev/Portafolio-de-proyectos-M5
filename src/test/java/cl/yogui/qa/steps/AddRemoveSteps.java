package cl.yogui.qa.steps;

import cl.yogui.qa.pages.AddRemovePage; // Página del POM para /add_remove_elements/
import io.cucumber.java.en.Given; // Anotación Given
import io.cucumber.java.en.When; // Anotación When
import io.cucumber.java.en.Then; // Anotación Then
import org.testng.Assert; // Aserciones de TestNG

// Steps para agregar y eliminar elementos dinámicos
public class AddRemoveSteps {

    // Instancia de la página de Add/Remove Elements
    private final AddRemovePage addRemovePage = new AddRemovePage(); // Crea la página con utilidades base

    @Given("I am on the add-remove elements page")
    public void iAmOnAddRemovePage() { // Abre la página objetivo
        addRemovePage.open(); // Navega a /add_remove_elements/
    }

    @When("I add {int} elements")
    public void iAddElements(int count) { // Agrega N elementos
        addRemovePage.addElements(count); // Click en "Add Element" N veces y espera
    }

    @When("I remove {int} elements")
    public void iRemoveElements(int count) { // Elimina N elementos
        addRemovePage.removeElements(count); // Click en "Delete" N veces con espera
    }

    @Then("I should see {int} elements")
    public void iShouldSeeElements(int expected) { // Verifica el conteo
        int actual = addRemovePage.getElementsCount(); // Lee el conteo actual de botones Delete
        Assert.assertEquals(actual, expected, "Unexpected elements count"); // Compara con lo esperado
    }
}
