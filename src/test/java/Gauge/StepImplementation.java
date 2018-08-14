package Gauge;

import com.thoughtworks.gauge.ContinueOnFailure;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation {
    @Step("Go to Gauge Get Started Page")
    public void gotoGetStartedPage() throws InterruptedException {
        WebElement getStartedButton = Driver.webDriver.findElement(By.xpath("//a[@class='link-get-started']"));
        getStartedButton.click();

        Gauge.writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Ensure installation instructions are available")
    public void ensureInstallationInstructionsAreAvailable() throws InterruptedException {
        WebElement instructions = Driver.webDriver.findElement(By.xpath("//p[@class='instruction']"));
        assertThat(instructions).isNotNull();
    }

    @Step("Open the Gauge homepage")
    public void implementation1() {
        String app_url = System.getenv("APP_URL");
        Driver.webDriver.get(app_url + "/");
        assertThat(Driver.webDriver.getTitle()).contains("Gauge");
    }

    @Step("Type UserName <UserName>")
    public void TypeUserName(String email) {


        Driver.webDriver.findElement(By.id("email")).sendKeys(email);

    }

    @ContinueOnFailure
    @Step("Access NewBee Website")
    public void OpenNewBee() {

        String app_url = System.getenv("APP_URL");
        Driver.webDriver.manage().deleteAllCookies();
        Driver.webDriver.manage().window().maximize();
        Driver.webDriver.get(app_url);
        assertThat(Driver.webDriver.findElement(By.className("welcome-page-title")).getText()).contains("NewBee Portal");
    }


    @Step("Enter UserID <UserId>")
    public void EnterUserID(String email) {

        Driver.webDriver.findElement(By.id("email")).sendKeys(email);

//        WebElement userId =  Driver.webDriver.findElement(By.id("email"));
//        userId.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        Driver.webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//        userId.sendKeys(email);

    }

    @Step("Enter Password <Password>")
    public void EnterPassword(String password) {

        Driver.webDriver.findElement(By.id("password")).sendKeys(password);

    }

    @Step("Click Login Button")
    public void ClickLoginButton() {

        Driver.webDriver.findElement(By.className("welcome-page-input-submit")).click();

    }

    @Step("Login as SuperUser")
    public void LoginSuperUser() {
        String userName = System.getenv("USER_NAME");
        String password = System.getenv("PASSWORD");
        EnterUserID(userName);
        EnterPassword(password);
        ClickLoginButton();

        assertThat(Driver.webDriver.findElement(By.className("menu-staff-right")).getText().contains("Hello, SuperUser SuperUser"));

    }

    @Step("Add HR Director as Reviewer <FirstName> <LastName> <Email> <AdminLevel> <Hive> <Job Position>")
    public void AddReviewer(String firstName, String lastName, String email, String adminLevel, String hive, String jobPosition) {

        // Click "Add Reviewer"

        Driver.webDriver.findElement(By.xpath("//*[@id=\"main-app-area\"]/div/div/div[2]/div/div[2]/div[1]/div[3]")).click();
        assertThat(Driver.webDriver.findElement(By.xpath("//*[@id=\"add-a-new-reviewer\"]/div/div[1]")).getText().contains("Add a new reviewer"));

        // Identity Reviewer Details Page & Enter details

        Driver.webDriver.findElement(By.xpath("//*[@id=\"add-a-new-reviewer\"]/div/div[1]")).click();
        assertThat(Driver.webDriver.findElement(By.xpath("//*[@id=\"main-app-area\"]/div/div/div[1]/div/div[2]/div/div[1]/div[2]")).getText().contains("REVIEWER DETAILS"));

        Driver.webDriver.findElement(By.id("reviewer-first-name")).sendKeys(firstName);
        Driver.webDriver.findElement(By.id("reviewer-surname")).sendKeys(lastName);
        Driver.webDriver.findElement(By.id("reviewer-email")).sendKeys(email);

        Select AdminLevel = new Select(Driver.webDriver.findElement(By.xpath("//*[@id=\"main-app-area\"]/div/div/div[1]/div/div[2]/div/div[2]/div[2]/div[3]/select")));
        AdminLevel.selectByVisibleText(adminLevel);

        Select Hive = new Select(Driver.webDriver.findElement(By.xpath("//*[@id=\"main-app-area\"]/div/div/div[1]/div/div[2]/div/div[2]/div[3]/div[1]/select")));
        Hive.selectByVisibleText(hive);

        Select JobPosition = new Select(Driver.webDriver.findElement(By.xpath("//*[@id=\"main-app-area\"]/div/div/div[1]/div/div[2]/div/div[2]/div[3]/div[3]/select")));
        JobPosition.selectByVisibleText(jobPosition);


    }


}