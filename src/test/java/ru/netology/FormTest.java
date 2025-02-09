package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;
import static com.codeborne.selenide.Condition.text;

public class FormTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    public void dataThreeDaysAfterNowDay() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }


    @Test
    public void dataPlusTwoDay() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        $("[data-test-id='date'] [class='input__box'] [placeholder='Дата встречи']").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        LocalDate date = LocalDate.now().plusDays(2);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111112");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".calendar-input__custom-control").shouldBe(visible, ofSeconds(15));
        $(".calendar-input__custom-control").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void dataPlus30Day() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(30);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79111111112");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void dataPlus40Years() {
        $("[data-test-id=city] input").setValue("Москва");

        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        LocalDate date = LocalDate.now().plusYears(30);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79111111112");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();


        $(".calendar-input__custom-control").shouldBe(visible, ofSeconds(15));
        $(".calendar-input__custom-control").shouldHave(text("Запланируйте встречу на доступный день"));
    }

    @Test
    public void dataNowDay() {
        $("[data-test-id=city] input").setValue("Москва");

        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79111111112");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();


        $(".calendar-input__custom-control").shouldBe(visible, ofSeconds(15));
        $(".calendar-input__custom-control").shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void cityNameOrel() {
        $("[data-test-id=city] input").setValue("Орёл");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void cityNameNoRegistered() {
        $("[data-test-id=city] input").setValue("москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void cityNameDash() {
        $("[data-test-id=city] input").setValue("Горно-Алтайск");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void cityNameDoubleDash() {
        $("[data-test-id=city] input").setValue("Ростов-на-Дону");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void cityNewRegion() {
        $("[data-test-id=city] input").setValue("Донецк");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void cityAreaName() {
        $("[data-test-id=city] input").setValue("Гатчина");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void noCityName() {
        $("[data-test-id=city] input").setValue("");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id=city] .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id=city] .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void easyManName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Сергей");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void easyWomanName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванова Татьяна");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void onlyName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Татьяна");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Введите фамилию и имя точно как в паспорте"));
    }

    @Test
    public void oneSimbolName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Я");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".[data-test-id='name'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Введите фамилию и имя точно как в паспорте"));
    }

    @Test
    public void manySimbolName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("на улице было солнечно и тепло птицы пели в кронах деревьев ветерок нежно касался лица прохожие спешили по своим делам кто-то шел на работу кто-то гулял с собакой дети играли в парке рядом с фонтаном пожилые люди сидели на лавочках и читали газеты жизнь шла своим чередом все вокруг казалось таким привычным и знакомым но вдруг произошло нечто странное небо потемнело и затянулось тучами ветер усилился и стал холодным листья начали кружиться в воздухе и падать на землю люди стали озираться и прятаться под навесы началась гроза гром гремел так громко что невозможно было услышать друг друга молнии рассекали небо яркими зигзагами дождь хлестал по лицам и одежде прохожих все старались как можно быстрее добраться до дома никто не знал что это всего лишь начало чего-то большего что ждет их впереди");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Введите фамилию и имя точно как в паспорте"));
    }

    @Test
    public void doubleName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Петрозаводов Ай-Ай");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void doubleLastName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванова-Облепихова Татьяна");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void noName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void figureName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Огонь124");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void englishName() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Scarenko Leya");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void firstNameWithYo() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Добрынин Фёдор");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $(".notification__title").shouldBe(visible, ofSeconds(15));
        $(".notification__title").shouldHave(text("Успешно!"));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + formattedDate));
    }

    @Test
    public void nameOnlyDash() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("-");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Имя и Фамилия указаны неверно. Недопустимо успользование только символа."));
    }

    @Test
    public void nameDashATheBeginning() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("-Лея");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void nameDashInTheEnd() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Лея-");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void phoneNumberBeginEight() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(30);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("89111111112");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void phoneNumberCountTen() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(30);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+9111111112");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void phoneNumberCountTwelve() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(30);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+745874596214");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void phoneNumberZero() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(30);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+00000000000");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно."));
    }

    @Test
    public void phoneNumberLetters() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(30);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("овоправлыкц");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void NoPhoneNumber() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(30);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void phoneNumberAndSimbols() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(30);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));


        $("[data-test-id=date] input").sendKeys(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+7(983)5819757");

        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible, ofSeconds(15));
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }


    @Test
    public void CheckboxOff() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        LocalDate date = LocalDate.now().plusDays(3);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(formattedDate);

        $("[data-test-id=name] input").setValue("Иванов Иван");

        $("[data-test-id=phone] input").setValue("+79111111111");

        $("[class='button__text']").click();

       String text = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid .checkbox__text")).getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных" +
                " и разрешаю сделать запрос в бюро кредитных историй", text.trim());
    }
}
