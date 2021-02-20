package br.com.nilton.test;

import static br.com.nilton.core.DriverFactory.getDriver;
import static br.com.nilton.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.com.nilton.core.Dsl;
import br.com.nilton.page.CampoTreinamentoPage;

public class TesteCampoTreinamento {
    // Criando a instancia do Browse
    private Dsl dsl;
    private CampoTreinamentoPage page;

    @Before
    public void inicializa() {
        getDriver().get("http://localhost/curso-selenium/componentes.html");
        dsl = new Dsl();

        // Criando a classe com os métodos genéricos
        dsl = new Dsl();

        // Inicializando o Object page que manipula os elementos da pagina
        page = new CampoTreinamentoPage();
    }

    @After
    public void finaliza() {
        killDriver();
    }

    @Test
    public void testeTextField() {
        // Buscando o elemento pelo id e escrevendo texto no campo
        page.setNome("Teste de escrita");

        // Validando o conteúdo do campo
        assertEquals("Teste de escrita", page.obtemNome());

        // Buscando um elemento do tipo TextArea e preenchendo com texto
        page.setSugestoes("Tentando escrita em um \nElemento TextArea\nbla bla bla");

        // Validando se o TextArea não está vazio
        assertFalse(page.obtemSugestoes().isBlank());
    }

    @Test
    public void deveInteragirComCheckBox() {
        // Localizando o elemento pelo id e clicando no mesmo
        page.setSexoMasculino();

        // Verificando se o elemento realmente foi clicado
        assertTrue(page.isMasculinoSelecionado());
    }

    @Test
    public void deveInteragirComCombo() {
        // Selecionando um item do combo pelo valor exibido na tela
        page.setEscolaridade("2o grau completo");

        // Pegando o texto do primeiro combo selecionado e validando seu conteúdo
        assertEquals("2o grau completo", page.obtemEsporteSelecionado("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresComCombo() {
        // Validando se a lista possui 8 itens
        assertEquals(8, dsl.comboTamanho("elementosForm:escolaridade"));

        // Validando se encontrou o item 'Mestrado'
        assertTrue(dsl.comboBuscaItem("elementosForm:escolaridade", "Mestrado"));
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        // Selecionando dois itens na lista esporte para simular o erro
        page.setEsporte("Corrida", "O que eh esporte?", "Natacao");

        // Validando se foram selecionados 3 itens
        assertEquals(3, page.obtemQtdEsportesSelecionados());

        // Desmarcando um dos itens
        page.deSelecionaEsporte("Corrida");

        // Validando se agora ficaram 2 itens marcados
        assertEquals(2, page.obtemQtdEsportesSelecionados());
    }

    @Test
    public void deveInteragirComBotoes() {
        // Clica no botão buscando pelo ID
        dsl.clica("buttonSimple");

        // Validando se o texto do botão mudou
        assertEquals("Obrigado!", dsl.obtemValor("buttonSimple"));
    }

    @Test
    public void deveInteragirComLinks() {
        // Localizando o elemento pelo ID e armazenando sua instancia em uma variável
        WebElement element = getDriver().findElement(By.linkText("Voltar"));

        // Clicando no elemento
        element.click();

        // Validando texto buscando pelo elemento
        assertEquals("Voltou!", getDriver().findElement(By.id("resultado")).getText());

        // Força a falha do teste no jUnit
        //Assert.fail();
    }

    @Test
    public void deveBuscarTextoNaPagina() {        
        // Validando se um texto está contigo no conteúdo da tag body do html
        assertTrue(getDriver().findElement(By.tagName("body"))
            .getText().contains("Campo de Treinamento"));

        // Validando o texto buscando o elemento pelo nome da classe
        assertEquals("Cuidado onde clica, muitas armadilhas...", 
                getDriver().findElement(By.className("facilAchar")).getText());

    }

    @Test
    public void TestaTextFieldDuplo() {
        // Escrevendo o nome e validando
        dsl.escreve("elementosForm:nome", "Nilton");
        assertEquals("Nilton",dsl.obtemValor("elementosForm:nome"));
        
        // Escrevendo outro nome e validando
        dsl.escreve("elementosForm:nome", "Silvia");
        assertEquals("Silvia",dsl.obtemValor("elementosForm:nome"));

    }

    @Test
    public void testeJavaScript() { // Executanto JavaScript direto pelo Selenium
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        
        //Buscando elemento pelo ID
        WebElement elemento = getDriver().findElement(By.id("elementosForm:nome"));
        
        // executanto um comando javascript
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Eu estive aqui! hahaha'");
        
        // executanto um comando passando argumentos 
        js.executeScript("arguments[0].style.border = arguments[1]", elemento, "solid 4px red");

        // executanto um alert na página
        js.executeScript("alert('Estou manipulando a pagina via JavaScript!')");
    }

    @Test
    public void TestaBotaoDaTabela() {
        page.clicaBotaoTabela("Mestrado","Escolaridade", "Botao");
    }

}
