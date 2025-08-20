package cl.yogui.qa.pages;

import cl.yogui.qa.core.BasePage; // Importa la clase base del POM
import org.openqa.selenium.By; // Importa By para localizar elementos

// Página de Login que modela /login del sitio
public class LoginPage extends BasePage { // Extiende BasePage para reutilizar utilidades

    // Localizadores de la página
    private final By usernameInput = By.id("username"); // Campo de usuario
    private final By passwordInput = By.id("password"); // Campo de contraseña
    private final By loginButton = By.cssSelector("button[type='submit']"); // Botón de login
    private final By flashMessage = By.id("flash"); // Mensaje de feedback (éxito/error)

    // Navega a la página de login
    public void open() { // Abre la ruta /login
        navigateToPath("/login"); // Usa la baseUrl + "/login"
    }

    // Realiza el login con credenciales dadas
    public void login(String username, String password) { // Método de login
        type(usernameInput, username); // Escribe el usuario
        type(passwordInput, password); // Escribe la contraseña
        click(loginButton); // Presiona el botón de login
    }

    // Devuelve el texto del mensaje flash mostrado tras intentar login
    public String getFlashMessage() { // Obtiene mensaje de feedback
        return getText(flashMessage); // Retorna el contenido del banner
    }

    // Verifica si el mensaje de éxito está presente
    public boolean isLoginSuccessful() { // Verifica éxito
        return getFlashMessage().contains("You logged into a secure area!"); // Busca texto de éxito
    }

    // Verifica si hay mensaje de error conteniendo una cadena
    public boolean isErrorMessageContaining(String expected) { // Verifica error específico
        return getFlashMessage().toLowerCase().contains(expected.toLowerCase()); // Compara ignorando mayúsculas
    }
}
