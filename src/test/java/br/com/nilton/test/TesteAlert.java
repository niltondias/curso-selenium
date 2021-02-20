package br.com.nilton.test;

import static br.com.nilton.core.DriverFactory.getDriver;
import static br.com.nilton.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class TesteAlert {

    @Test
    public void deveInteragirComAlertSimples() {
        getDriver().get("http://localhost/curso-selenium/componentes.html");

        // Clicando no botão que emitirá o alert
        getDriver().findElement(By.id("alert")).click();

        // Para acessar o elemento alert é preciso fazer
        // o Selenium mudar o foco para o alert e armazenando
        // a instancia em uma variável
        Alert alert = getDriver().switchTo().alert();

        // Salvando o texto do alert em uma variavel
        String texto = alert.getText();

        // Fechando o alert
        alert.accept();

        // Preenchendo o Textfield com o texto do alert
        getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto);

        // Validando o texto do alert
        assertEquals("Alert Simples", texto);
    }

    @Test
    public void deveInteragirComAlertConfirm() {
        getDriver().get("http://localhost/curso-selenium/componentes.html");

        // Clicando no botão que irá acionar o Alert Confirm
        getDriver().findElement(By.id("confirm")).click();

        // Para acessar o elemento alert é preciso fazer
        // o Selenium mudar o foco para o alert e armazenando
        // a instancia em uma variável
        Alert alerta = getDriver().switchTo().alert();

        // Validando o texto do Alert antes de clicar em confirmar
        assertEquals("Confirm Simples", alerta.getText());

        // Dando o cancelar usando o método dimiss
        alerta.dismiss();

        // Validando novamente o texto depois de aceitar o alert
        assertEquals("Negado", alerta.getText());

        // Dando ok no novo dimiss novamente.
        alerta.dismiss();

    }

    @Test
    public void deveInteragirComAlertPrompt() {
        getDriver().get("http://localhost/curso-selenium/componentes.html");

        // Clicando no botão que irá chamar o Alert
        getDriver().findElement(By.id("prompt")).click();

        // Mudando o foco do browser para o Alert e capturando
        // a instância para uma variável
        Alert alerta = getDriver().switchTo().alert();

        // Preenchendo o prompt do alert
        alerta.sendKeys("12");

        // Dando o acept para validar o texto digitado
        alerta.accept();

        // Validando o texto depois do acept
        assertEquals("Era 12?", alerta.getText());

        // Dando outro acept para validar a segunda mensagem
        alerta.accept();

        // Validando a segunda mensagem
        assertEquals(":D", alerta.getText());

        // Dando outro acept para fechar o alert
        alerta.accept();

        // Fechando a instancia do Driver do Browser
        killDriver();
    }
    
}
