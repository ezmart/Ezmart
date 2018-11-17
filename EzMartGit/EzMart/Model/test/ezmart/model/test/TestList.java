package ezmart.model.test;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestList {

    static WebDriver driver;
    static WebDriverWait wait;

    public TestList() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testList() throws InterruptedException {

        //Configuração de acesso ao WebDriver
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Marcos Paulo\\Desktop\\DriverTest\\geckodriver.exe");

        //Chamada do sistema
        testHomePage();

        //Tentativa de login com o EMAIL e SENHA campos vazios
        testLoginEmailPasswordEmpty();
        
        //Tentativa de login com campo EMAIL vazio
        testLoginEmailEmpty();
        
        //Tentativa de login com campo SENHA vazio
        testLoginPasswordEmpty();
        
        //Tentativa de login com o cadastro não confirmado
        testLoginUnconfirmedRegister();
        
        //Tentativa de login com EMAIL inexistente
        testLoginEmailNotExist();
        
        //Tentativa de login sucesso
        testLoginSuccess();

        //Loga e cria a lista
        testCreateList();

        //Atualiza o nome da Lista
        testUpdateListName();

        //Tenta fazer a comparação com a lista vazia
        testComparePricesEmptyList();

        //Inclui produtos na lista
        testIncludeProducts();

        //Comparar preços
        testComparePrices();

        //Deleta a lista
        testDeleteList();

        //Desloga do sistema
        testLogout();
    }

    //Pagina inicial
    public void testHomePage() throws InterruptedException {

        // Passo 1: Abrir o browser
        driver = new FirefoxDriver();

        // Passo 2: Acessar URL
        driver.get("http://localhost:8084/ezmartWeb");
        wait = new WebDriverWait(driver, 80);

    }

    //Login com o EMAIL e SENHA campos vazios
    public void testLoginEmailPasswordEmpty() throws InterruptedException {

        Thread.sleep(4000);

        // Passo 1: Escolher a opção "Fazer Login"
        driver.findElement(By.id("login")).click();

        Thread.sleep(2000);

        // Passo 2: Clicar no botão entrar
        driver.findElement(By.id("btn-btn-ezmart-style")).click();

        //Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("recovery-password")));
        assertTrue(driver.findElement(By.id("recovery-password")).isDisplayed());
    }

    //Login com campo EMAIL vazio
    public void testLoginEmailEmpty() throws InterruptedException {
        Thread.sleep(2000);

        // Passo 1: Preencher os dados de login
        driver.findElement(By.id("senha")).click();
        driver.findElement(By.id("senha")).sendKeys("123456789");

        // Passo 2: Clicar no botão entrar
        driver.findElement(By.id("btn-btn-ezmart-style")).click();

        //Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("recovery-password")));
        assertTrue(driver.findElement(By.id("recovery-password")).isDisplayed());
    }

    //Login com campo SENHA vazio
    public void testLoginPasswordEmpty() throws InterruptedException {

        Thread.sleep(2000);

        // Passo 1: Preencher os dados de login
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("mpmoreno1990@gmail.com");

        // Passo 2: Clicar no botão entrar
        driver.findElement(By.id("btn-btn-ezmart-style")).click();

        //Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("recovery-password")));
        assertTrue(driver.findElement(By.id("recovery-password")).isDisplayed());
    }

    //Login com o cadastro não confirmado
    public void testLoginUnconfirmedRegister() throws InterruptedException {

        Thread.sleep(2000);

        // Passo 1: Preencher os dados de login
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("faitec@gmail.com");
        driver.findElement(By.id("senha")).click();
        driver.findElement(By.id("senha")).sendKeys("faitec123");

        // Passo 2: Clicar no botão entrar
        driver.findElement(By.id("btn-btn-ezmart-style")).click();

        //Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("recovery-password")));
        assertTrue(driver.findElement(By.id("recovery-password")).isDisplayed());
    }

    //Login com EMAIL inexistente
    public void testLoginEmailNotExist() throws InterruptedException {

        Thread.sleep(2000);

        // Passo 4: Preencher os dados de login
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("mp@gmail.com");
        driver.findElement(By.id("senha")).click();
        driver.findElement(By.id("senha")).sendKeys("123456789");

        // Passo 5: Clicar no botão entrar
        driver.findElement(By.id("btn-btn-ezmart-style")).click();

        //Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("recovery-password")));
        assertTrue(driver.findElement(By.id("recovery-password")).isDisplayed());
    }

    //Login sucesso
    public void testLoginSuccess() throws InterruptedException {

        Thread.sleep(2000);

//        // Passo 1: Escolher a opção "Fazer Login"
//        driver.findElement(By.id("login")).click();
//
//        Thread.sleep(2000);
        // Passo 4: Preencher os dados de login
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("mpmoreno1990@gmail.com");
        driver.findElement(By.id("senha")).click();
        driver.findElement(By.id("senha")).sendKeys("123456789");

        // Passo 5: Clicar no botão entrar
        driver.findElement(By.id("btn-btn-ezmart-style")).click();

        //Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("avatar")));
        assertTrue(driver.findElement(By.id("avatar")).isDisplayed());
    }

    //Cria a lista
    public void testCreateList() throws InterruptedException {

        Thread.sleep(2000);

        // Passo 5: Clicar em minhas opções
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-option")));
        driver.findElement(By.id("my-option")).click();

        // Passo 6: Clicar em minhas listas
        driver.findElement(By.id("my-list")).click();

        // Passo 7: Clicar em adicionar lista
        driver.findElement(By.id("add-list")).click();

        // Passo 8: Preenche o nome da lista 
        driver.findElement(By.name("value")).sendKeys("TESTE");

        // Passo 9: Salva a nova lista
        driver.findElement(By.name("save-new-list")).click();

        // Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("text-all-list")));
        assertTrue(driver.findElement(By.id("text-all-list")).isDisplayed());

    }

    //Atualiza o nome da lista
    public void testUpdateListName() throws InterruptedException {

        Thread.sleep(2000);
        // Passo 1: Clicar em excluir
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("update-new-list")));
        driver.findElement(By.id("update-new-list")).click();

        // Passo 2: Preenche com o novo nome da lista
        driver.findElement(By.id("valueName")).sendKeys("Lista Mercado");

        // Passo 3: Salva a nova lista
        driver.findElement(By.name("update-name-list")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("shopping-list-name")));
        assertEquals(driver.findElement(By.id("shopping-list-name")).getText(), "Lista Mercado");
    }

    //Compara preços com a lista vazia
    public void testComparePricesEmptyList() throws InterruptedException {

        Thread.sleep(2000);

        // Passo 1: Clicar em comparar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("compare-list")));
        driver.findElement(By.id("compare-list")).click();

        Thread.sleep(2000);

        // Passo 2: Clicar em minhas opções
        driver.findElement(By.id("my-option")).click();

        // Passo 3: Clicar em minhas listas
        driver.findElement(By.id("my-list")).click();

        //Verificar resultado esperado = obtido
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("empty-list")));
        //assertTrue(driver.findElement(By.id("empty-list")).isDisplayed());
    }

    //Inclui produtos na lista
    public void testIncludeProducts() throws InterruptedException {

        Thread.sleep(4000);

        // Passo 1: Clicar em produtos
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("products-list")));
        driver.findElement(By.id("products-list")).click();

        //Passo 2: Adicionar os produtos na lista
        driver.findElement(By.id("add-product")).click();
        driver.findElement(By.id("searchProduct")).sendKeys("ARROZ INTEGRAL");
        driver.findElement(By.id("search-product")).click();
        driver.findElement(By.id("add-new-product")).click();

        driver.findElement(By.id("add-product")).click();
        driver.findElement(By.id("searchProduct")).sendKeys("ERVILHA");
        driver.findElement(By.id("search-product")).click();
        driver.findElement(By.id("add-new-product")).click();

        driver.findElement(By.id("add-product")).click();
        driver.findElement(By.id("searchProduct")).sendKeys("FUBA");
        driver.findElement(By.id("search-product")).click();
        driver.findElement(By.id("add-new-product")).click();

        driver.findElement(By.id("add-product")).click();
        driver.findElement(By.id("searchProduct")).sendKeys("SELETO");
        driver.findElement(By.id("search-product")).click();
        driver.findElement(By.id("add-new-product")).click();

        //Verificar resultado esperado = obtido 
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("list-name-with-products")));
        assertTrue(driver.findElement(By.id("list-name-with-products")).isDisplayed());
    }

    //Compara os preços
    public void testComparePrices() {

        // Passo 1: Clicar em minhas opções
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("my-option")));
        driver.findElement(By.id("my-option")).click();

        // Passo 2: Clicar em minhas listas
        driver.findElement(By.id("my-list")).click();

        // Passo 3: Clicar em comparar
        driver.findElement(By.id("compare-list")).click();

        // Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("list-not-empty")));
        assertTrue(driver.findElement(By.id("list-not-empty")).isDisplayed());
    }

    //Deleta a lista
    public void testDeleteList() throws InterruptedException {

        Thread.sleep(3000);

        // Passo 1: Clicar em minhas opções
        driver.findElement(By.id("my-option")).click();

        // Passo 2: Clicar em minhas listas
        driver.findElement(By.id("my-list")).click();

        // Passo 3: Clicar em excluir
        driver.findElement(By.id("delete-list")).click();

        //Thread.sleep(1000);
        //Passo 4: Confirmar a exclusão
        driver.findElement(By.id("delete-confimation-list")).click();

        //Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("text-null-list")));
        assertTrue(driver.findElement(By.id("text-null-list")).isDisplayed());
    }

    //Sai do sistema
    public void testLogout() throws InterruptedException {

        Thread.sleep(2000);

        // Passo 1: Ir até perfil
        driver.findElement(By.id("avatar")).click();

        // Passo 2: Escolher a opção logout
        driver.findElement(By.id("logout")).click();

        //Verificar resultado esperado = obtido
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));
        assertTrue(driver.findElement(By.id("login")).isDisplayed());
    }
}
