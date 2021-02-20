package br.com.nilton.test;

import static br.com.nilton.core.DriverFactory.getDriver;
import static br.com.nilton.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.nilton.core.Dsl;

public class TesteSincronismo {
    private Dsl dsl;

    @Before
    public void inicializa() {
        // Preparando o driver
        getDriver().get("http://localhost/curso-selenium/componentes.html");

        // Criando a classe com os métodos genéricos
        dsl = new Dsl();

    }

    @After
    public void finaliza() {
        // Encerrando o driver do Browse
        // getDriver().quit();
        killDriver();
    }

    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException {
        dsl.clica("buttonDelay");
        Thread.sleep(5000);
        dsl.escreve("novoCampo", "Deu certo?");
    }
    @Test
    
    public void deveUtilizarEsperaImplicita() throws InterruptedException {
        dsl.clica("buttonDelay");
        
        //Configura o driver para resperar um tempo para que o elemento esteja presente na pagina
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        dsl.escreve("novoCampo", "Deu certo?");

        // Desligando a configuração 
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void deveUtilizarEsperaExplicita() throws InterruptedException {
        dsl.clica("buttonDelay");
        
        //Configura o driver para resperar um tempo para que o elemento esteja presente na pagina
        // Esperando a presença de um elemento expecífico
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);  // Classe waint do Webdrive
        
        // Configurando o wait para um elemento específico
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("novoCampo")));

        dsl.escreve("novoCampo", "Deu certo?");

        // Desligando a configuração 
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
    
}
