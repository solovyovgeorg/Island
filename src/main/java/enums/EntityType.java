package enums;

public enum EntityType {
    WOLF("Волк"),
    SNAKE("Удав"),
    FOX("Лиса"),
    BEAR("Медведь"),
    EAGLE("Орел"),
    HORSE("Лошадь"),
    DEER("Олень"),
    RABBIT("Кролик"),
    MOUSE("Мышь"),
    GOAT("Коза"),
    SHEEP("Овца"),
    PIG("Кабан"),
    BUFFALO("Буйвол"),
    DUCK("Утка"),
    CATERPILLAR("Гусеница"),
    PLANT("Растение");

    private String nameRus;

    private EntityType(String nameRus) {
        this.nameRus = nameRus;
    }

    public String getNameRus() {
        return this.nameRus;
    }

}
