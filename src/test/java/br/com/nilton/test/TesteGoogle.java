package br.com.nilton.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
    @Test
    public void teste() {
        //WebDriver driver = new FirefoxDriver();
        //System.setProperty("webdrive.gecko.driver", "c:\\users\nilton\drivers");
        //System.setProperty("webdrive.chrome.driver", "c:\\users\nilton\drivers");    
                        
        // Criando a conexão com o Driver do navegador
        WebDriver driver = new FirefoxDriver();
 
        // Definindo a URL que será aberta
        driver.get("http://google.com.br");

        // Posicionando a tela com as coordenadas especificadas
        driver.manage().window().setPosition(new Point(100, 100));

        // Definindo o tamanho da tela
        driver.manage().window().setSize(new Dimension(1200, 765));
        
        // Deixando a tela maximizada
        driver.manage().window().maximize();

        // Validando o título da página
        assertEquals("Google", driver.getTitle());
        driver.quit();
    }
    
}
