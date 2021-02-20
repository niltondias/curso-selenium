package br.com.nilton.util;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class DriverUtil {
    public static void preparaDriver(WebDriver driver, String url) {
        // Posicionando a tela com as coordenadas especificadas
        driver.manage().window().setPosition(new Point(100, 100));
             
        //
        // Definindo a URL que será aberta
        //driver.get("file:///" + System.getProperty("user.dir") + 
        //        "/src/main/resources/componentes.html");
        //
        // Maximizando a tela do browser
        // driver.manage().window().maximize();

        // Utilizando o XAMMP pois o protocolo file: não funcionou no Selenium IDE
        driver.get(url);
        
    }    
    
    public static void preparaDriver(WebDriver driver) {
        // Chama a versão com o parametrl da URL
        preparaDriver(driver, "http://localhost/curso-selenium/componentes.html");

    }

}
