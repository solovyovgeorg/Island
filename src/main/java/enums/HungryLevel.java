package enums;

public enum HungryLevel {
    NONE("сыт"),
    MEDIUM("средний"),
    HIGH("высокий"),
    LETHAL("критический");
    private final String DESCRIPTION;
    private HungryLevel(String description) {
        this.DESCRIPTION = description;
    }
    public String getRusDescription(){
        return this.DESCRIPTION;
    }
}
