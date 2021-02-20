package br.com.nilton.test;

import static br.com.nilton.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.nilton.core.BaseTest;
import br.com.nilton.core.Dsl;
import br.com.nilton.page.CampoTreinamentoPage;

public class TesteRegraDeNegocio extends BaseTest {

    private CampoTreinamentoPage page;

    // DSL com funções genericas para manipular o driver
    private Dsl dsl;
    
    @Before
    public void inicializa() {
        // Inicializando o driver e o dsl
        getDriver().get("http://localhost/curso-selenium/componentes.html");
                    //Posicionando no topo da pagina

        dsl = new Dsl();
        this.page = new CampoTreinamentoPage();
    }

    @Test
    public void validaRegraNome() {      
        // Clicando no botão cadastrar para simular o erro
        page.cadastra();

        // Validando a mensagem do Alert
        assertEquals("Nome eh obrigatorio", dsl.alertaObtemTextoEAceita());
    }

    @Test
    public void validaRegraSobrenome() {
        // Preenchendo o nome para passar na validação e ir para o próximo campo
        page.setNome("Nome preenchido");

        // Clicando novamente no botão Cadastrar e repetindo o processo      
        page.cadastra();

        // Validando o sobrenome
        assertEquals("Sobrenome eh obrigatorio", dsl.alertaObtemTextoEAceita());
    }

    @Test
    public void validaRegraSexo() {
        // Preenchendo os campos anteriores para validar a próxima regra
        page.setNome("Nome preenchido");
        page.setSobrenome("Sobrenome preenchido");

        // Clicando no botao para validar o formulario      
        page.cadastra();
        
        // Validando se o sexo foi selecionado
        assertEquals("Sexo eh obrigatorio", dsl.alertaObtemTextoEAceita());
    }

    @Test
    public void validaRegraVegano() {
        // Preenchendo os campos anteriores para validar a próxima regra
        page.setNome("Nome preenchido");
        page.setSobrenome("Sobrenome preenchido");
        page.setSexoMasculino();

        // Clicando as opções carne e vegetariano para dar o erro
        page.setComidaCarne();
        page.setComidaVegetariado();

        // clicando no botão para submeter o formulário
        page.cadastra();

        //Validando a mensagem do alert
        assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObtemTextoEAceita());
    }

    @Test
    public void validaRegraEsportes() {
        // Preenchendo os campos anteriores para validar a próxima regra
        page.setNome("Nome preenchido");
        page.setSobrenome("Sobrenome preenchido");
        page.setSexoMasculino();

        // Clicando apenas na opcao carne para passar
        page.setComidaCarne();
        
        // Selecionando um esporte e a pergunta 
        page.setEsporte("Natacao","O que eh esporte?");

        //Submetendo o formulário
        page.cadastra();
        // dsl.clica("elementosForm:cadastrar");

        // Validando a mensagem do Alert
        assertEquals("Voce faz esporte ou nao?", dsl.alertaObtemTextoEAceita());
    }
}