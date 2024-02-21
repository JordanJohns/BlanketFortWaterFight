/**
 * Menu class controls MenuOptions and can display to the user.
 */
public class Menu {
    /**
     * Multiple MenuOptions are used for menus. Immutable Class. 
     */
    public static class MenuOption {
        private String title;
        private Runnable runner;

        public MenuOption(String title, Runnable runner) {
            this.title = title;
            this.runner = runner;
        }

        public String getTitle() {
            return title;
        }

        public Runnable getRunner() {
            return runner;
        }
    }

    private MenuOption[] menuOptions;

    public void displayMenu() {
        int i = 1;
        for (MenuOption menuOption : menuOptions) {
            System.out.println(i + ") " + menuOption.title);
            i += 1;
        }
    }

    public void selectMenuOption(int selection) {
        menuOptions[selection].getRunner().run();
    }

    public Menu(Menu.MenuOption[] menuOptions) {
        this.menuOptions = menuOptions;
    }
}
