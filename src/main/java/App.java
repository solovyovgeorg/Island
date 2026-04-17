import config.ConfigGenerator;
import view.ConsoleView;
import view.View;

public class App {

    public static void main(String[] args) {
        View consoleView = new ConsoleView();
        ConfigGenerator configGenerator = new ConfigGenerator();
        configGenerator.loadAndCheckConfig();
        if (!configGenerator.getErrors().isEmpty()) {
            for (String val : configGenerator.getErrors()) {
                System.out.println(val);
            }
            return;
        }
        Engine engine = new Engine(configGenerator.getConfig(), consoleView);
        engine.startSimulation();
    }
}
