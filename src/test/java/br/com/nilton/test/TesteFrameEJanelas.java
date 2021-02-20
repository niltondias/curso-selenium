package br.com.nilton.test;

import static br.com.nilton.core.DriverFactory.getDriver;
import static br.com.nilton.core.DriverFactory.killDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class TesteFrameEJanelas {
    @Test
    public void deveInteragirComFrames() {

        // Preparando o driver
        getDriver().get("http://localhost/curso-selenium/componentes.html");

        // Mudando o foco para o iframe pois o botão está dentro dele.
        getDriver().switchTo().frame("frame1");

        // Clicando no botão localizado dentro do iFrame
        getDriver().findElement(By.id("frameButton")).click();

        // Mudando o foco para o Alert para pegar a mensagem
        Alert alerta = getDriver().switchTo().alert();

        // Pegando a mensagem que emitida pelo alert
        String msg = alerta.getText();

        // Dando o aceite no alert
        alerta.accept();

        // Validando a mensagem do Alert
        assertEquals("Frame OK!", msg);

        // Mudando o foco de volta para o formulário princial
        // para preencher o input com a mensagem extraída do alert
        getDriver().switchTo().defaultContent();

        // Buscando o elemento de texto e digitando o conteúdo
        getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg);

        // Fechando o driver do Browse
        getDriver().quit();
    }

    @Test
    public void deveInteragirComJanelas() {
        // Preparando o driver
        getDriver().get("http://localhost/curso-selenium/componentes.html");

        // Localizando o botao e clicando
        getDriver().findElement(By.id("buttonPopUpEasy")).click();

        // Mudando o foco para a janela PopUp
        getDriver().switchTo().window("Popup");

        // Digitando na caixa de texto do PopUp
        getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");

        // Fechando a janela de PopUp
        getDriver().close();

        // Mudando o foco para a pagina princial
        getDriver().switchTo().window("");

        // Digitando um texto no elemento TextArea da pagina princial
        getDriver().findElement(By.tagName("textarea")).sendKeys("E agora?");

        // Fechando o driver do browser
        killDriver();
    }

    @Test
    public void deveInteragirComJanelasSemTitulo() {
        // Preparando o driver
        getDriver().get("http://localhost/curso-selenium/componentes.html");

        // Localizando e clicando no botão
        getDriver().findElement(By.id("buttonPopUpHard")).click();

        // Localizando todas as janelas abertas e mudando o foco para o PopUp
        // Converte a lista de Handles para array e faz o casting para String pois o
        // window espera uma String
        getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);

        // Digitando na textarea da PopUp
        getDriver().findElement(By.tagName("textarea")).sendKeys("Digitando na PopUp");

        // Mudando o foco para a janela princial
        getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);

        // Digitando na textarea da página principal
        getDriver().findElement(By.tagName("textarea")).sendKeys("Digitando o texto");

        // Fechando o browse. o quit() fecha todas as janelas aberta
        killDriver();

    }
}
