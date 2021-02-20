package br.com.nilton.test;

import static br.com.nilton.core.DriverFactory.getDriver;
import static br.com.nilton.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.nilton.core.Dsl;
import br.com.nilton.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TestesGenericoRegras {

    // Criando o objeto de browser
    private Dsl dsl;
    private CampoTreinamentoPage page;

    // Parametros para os cenários de testes
    @Parameter
    public String nome;

    @Parameter(value = 1)
    public String sobrenome;

    @Parameter(value = 2)
    public String sexo;

    @Parameter(value = 3)
    public List<String> comidas;

    @Parameter(value = 4)
    public String[] esportes;

    @Parameter(value = 5)
    public String msg;

    @Before
    public void inicializa() {
        // Inicializando o driver e o dsl
        getDriver().get("http://localhost/curso-selenium/componentes.html");
        this.dsl = new Dsl();
        this.page = new CampoTreinamentoPage();
    }

    @After
    public void finaliza() {
        killDriver();
    }

    @Parameters
    public static Collection<Object[]> getCollection() {
        return Arrays.asList(new Object[][] {
            {"","","", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
            {"Nilton","","", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
            {"Nilton","Jurandir Dias","", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
            {"Nilton","Jurandir Dias","", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"}

        });     
    }

    @Test
    public void deveValidarRegras() {
        // Preenchendo os campos anteriores para validar a próxima regra
        page.setNome(nome);
        page.setSobrenome(sobrenome);
        if(sexo.equals("Masculino")) {
            page.setSexoMasculino();
        }
        if(sexo.equals("Feminino")) {
            page.setSexoFeminino();
        }

        // Clicando apenas na opcao carne para passar
        if(comidas.contains("Carne")) page.setComidaCarne();
        if(comidas.contains("Pizza")) page.setComidaPizza();
        if(comidas.contains("Vegetariano")) page.setComidaVegetariado();
        
        // Selecionando um esporte e a pergunta 
        page.setEsporte(esportes);

        //Submetendo o formulário
        page.cadastra();

        // Teste
        System.out.println(msg);

        // Validando a mensagem do Alert
        assertEquals(msg, dsl.alertaObtemTextoEAceita());
    }
    
}
