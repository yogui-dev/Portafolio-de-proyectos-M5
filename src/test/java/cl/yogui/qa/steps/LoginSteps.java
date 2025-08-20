package cl.yogui.qa.steps;

import cl.yogui.qa.pages.LoginPage; // Página del POM para /login
import io.cucumber.java.en.Given; // Anotación Given
import io.cucumber.java.en.When; // Anotación When
import io.cucumber.java.en.Then; // Anotación Then
import io.cucumber.java.en.And; // Anotación And
import org.testng.Assert; // Aserciones de TestNG

// Steps de Login con comentarios en español explicando la lógica
public class LoginSteps {

    // Instancia de la página de login
    private final LoginPage loginPage = new LoginPage(); // Crea la página usando BasePage/DriverFactory

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() { // Navega a la página de login
        loginPage.open(); // Abre la ruta /login
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithCredentials(String username, String password) { // Realiza el login
        loginPage.login(username, password); // Envía credenciales y hace submit
    }

    @Then("login should be {string}")
    public void loginShouldBe(String result) { // Valida el resultado del login
        boolean success = loginPage.isLoginSuccessful(); // Determina si hubo éxito
        if ("success".equalsIgnoreCase(result)) { // Si se espera éxito
            Assert.assertTrue(success, "Expected login to succeed but it failed"); // Verifica éxito
        } else if ("error".equalsIgnoreCase(result)) { // Si se espera error
            Assert.assertFalse(success, "Expected login to fail but it succeeded"); // Verifica fallo
        } else { // Valor no reconocido
            Assert.fail("Unknown expected result: " + result); // Falla explicitamente
        }
    }

    @And("message should contain {string}")
    public void messageShouldContain(String text) { // Valida el contenido del mensaje
        String message = loginPage.getFlashMessage(); // Obtiene el mensaje flash
        Assert.assertTrue(message.toLowerCase().contains(text.toLowerCase()),
                "Expected message to contain '" + text + "' but was: " + message); // Verifica contenido parcial
    }
}
