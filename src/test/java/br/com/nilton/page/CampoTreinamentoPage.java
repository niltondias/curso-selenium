package br.com.nilton.page;

import java.util.Arrays;

import br.com.nilton.core.BasePage;

public class CampoTreinamentoPage extends BasePage {
    public void setNome(String nome) {
        dsl.escreve("elementosForm:nome", nome);
    }

    public void setSobrenome(String sobrenome) {
        dsl.escreve("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMasculino() {
        dsl.clica("elementosForm:sexo:0");
    }
    
    public void setSexoFeminino() {
        dsl.clica("elementosForm:sexo:1");
    }

    public void setComidaPizza() {
        dsl.clica("elementosForm:comidaFavorita:2");
    }

    public void setComidaCarne() {
        dsl.clica("elementosForm:comidaFavorita:0");
    }

    public void setComidaVegetariado() {
        dsl.clica("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String valor) {
        dsl.selecionaPorVisivel("elementosForm:escolaridade", valor);
    }

    public void setEsporte(String... valor) {
        dsl.selecionaPorVisivel("elementosForm:esportes", Arrays.asList(valor));
    }

    public void setSugestoes(String texto) {
        dsl.escreve("elementosForm:sugestoes", texto);
    }

    public void cadastra() {
        dsl.clica("elementosForm:cadastrar");
    }

    public String obtemResultadoCadastro() {
        return dsl.obtemTexto("resultado");
    }

    public String obtemResultadoNome() {
        return dsl.obtemTexto("descNome");
    }

    public String obtemResultadoSobrenome() {
        return dsl.obtemTexto("descSobrenome");
    }

    public String obtemResultadoSexo() {
        return dsl.obtemTexto("descSexo");
    }

    public String obtemResultadoComida() {
        return dsl.obtemTexto("descComida");
    }
    
    public String obtemResultadoEscolaridade() {
        return dsl.obtemTexto("descEscolaridade");
    }

    public String obtemResultadoEsportes() {
        return dsl.obtemTexto("descEsportes");
    }

    public String obtemNome() {
        return dsl.obtemValor("elementosForm:nome");
    }

    public String obtemSugestoes() {
        return dsl.obtemValor("elementosForm:sugestoes");
    }

    public boolean isMasculinoSelecionado() {
        return dsl.estaSelecionado("elementosForm:sexo:0");
    }

    public String obtemEsporteSelecionado(String id) {
        return dsl.comboItemSelecionado(id);
    }

    public int obtemQtdEsportesSelecionados() {
        return dsl.comboQtdSelecionados("elementosForm:esportes");
    }

    public void deSelecionaEsporte(String valor) {
        dsl.deSelecionaPorVisivel("elementosForm:esportes", valor);

    }

	public void clicaBotaoTabela(String nome, String nomeCampo, String botao ) {
        dsl.clicaBotaoDaTabela(nomeCampo, nome, botao, "elementosForm:tableUsuarios");
        
        //return dsl.alertaObtemTextoEAceita();

	}
}
