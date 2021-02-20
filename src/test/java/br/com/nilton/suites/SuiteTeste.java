package br.com.nilton.suites;

import static br.com.nilton.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.nilton.test.TesteCadastro;
import br.com.nilton.test.TesteRegraDeNegocio;

@RunWith(Suite.class)
@SuiteClasses({ TesteRegraDeNegocio.class, TesteCadastro.class })
public class SuiteTeste {
    @AfterClass
    public static void finalizaTudo() {
        killDriver();
    }
    
}
