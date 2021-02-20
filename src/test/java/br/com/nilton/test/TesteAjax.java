package br.com.nilton.test;

import static br.com.nilton.core.DriverFactory.getDriver;
import static br.com.nilton.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.nilton.core.Dsl;

public class TesteAjax {
    private Dsl dsl;

    @Before
    public void inicializa() {
        // Preparando o driver
        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");

        // Criando a classe com os métodos genéricos
        dsl = new Dsl();

    }

    @After
    public void finaliza() {
        // Encerrando o driver do Browse
        killDriver();
    }

    @Test
    public void testAjax() {
        dsl.escreve("j_idt727:name", "Teste");
        dsl.clica("j_idt727:j_idt730");

        //Configura o DriveWait para 30 segundos
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);

        //Esperar até o texto esteja no valor do elemento.
        wait.until(ExpectedConditions.textToBe(By.id("j_idt727:display"), "Teste"));
        assertEquals("Teste", dsl.obtemTexto("j_idt727:display"));
    }
    
}
