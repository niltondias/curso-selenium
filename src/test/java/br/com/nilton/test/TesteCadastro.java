package br.com.nilton.test;

import static br.com.nilton.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import br.com.nilton.core.BaseTest;
import br.com.nilton.core.Dsl;
import br.com.nilton.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest {
    // Criando a instancia do Browse
    private Dsl dsl;
    private CampoTreinamentoPage page;

    @Before 
    public void inicializa() {
        // Preparando o driver
        getDriver().get("http://localhost/curso-selenium/componentes.html");

        // Criando o object Page para manipular os elementos da página
        page = new CampoTreinamentoPage();
        
        // Criando a classe com os métodos genéricos
        dsl = new Dsl();    
        
    }

    @Test
    public void ValidaCadastroComSucesso() {
        // Nome
        page.setNome("Nilton");

        // Sobrenome
        page.setSobrenome("Jurandir Dias");

        // Sexo masculino
        page.setSexoMasculino();
        
        // Comida - Pizza
        page.setComidaPizza();
        
        // Escolaridade - Especialização
        page.setEscolaridade("Especializacao");
          
        // Esportes
        page.setEsporte("Futebol","Corrida");

        // Sugestões
        page.setSugestoes("Testar mais");

        // Clicar no botão cadastrar
        page.cadastra();

        // Pegando o elemento resultado que contem uma div para cada campo e um span com o conteúdo
        // do campo
        List<WebElement> listaResultados = dsl.listaElementos("resultado", "span");
        
        // Teste
        listaResultados.forEach(elemento -> System.out.println(elemento.getText()));
        System.out.println("Fim");
                
        // Validando a div resultado com os dados do formulário
        assertTrue(page.obtemResultadoCadastro().startsWith("Cadastrado!"));
        assertTrue(page.obtemResultadoNome().endsWith("Nilton"));

        assertEquals("Sobrenome: Jurandir Dias", page.obtemResultadoSobrenome());
		assertEquals("Sexo: Masculino", page.obtemResultadoSexo());
		assertEquals("Comida: Pizza", page.obtemResultadoComida());
		assertEquals("Escolaridade: especializacao", page.obtemResultadoEscolaridade());
		assertEquals("Esportes: Futebol Corrida", page.obtemResultadoEsportes());

        // assertEquals("Cadastrado!",listaResultados.get(0).getText());
        // assertEquals("Nilton", listaResultados.get(1).getText());
        // assertEquals("Jurandir Dias", listaResultados.get(2).getText());
        // assertEquals("Masculino", listaResultados.get(3).getText());
        // assertEquals("Pizza", listaResultados.get(4).getText());
        // assertEquals("especializacao", listaResultados.get(5).getText());
        // assertEquals("Futebol Corrida", listaResultados.get(6).getText());
        // assertEquals("Testar mais", listaResultados.get(7).getText());
    }    
}
