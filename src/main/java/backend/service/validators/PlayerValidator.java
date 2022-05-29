package backend.service.validators;

import backend.model.Player;

public class PlayerValidator {

    public boolean validatePlayer(Player player) {
        return validateName(player.getName()) && validateAge(player.getAge()) && player.getGoalsScored() >= 0 &&
                player.getAssistsProvided() >=0 && validateKitNumber(player.getKitNumber());
    }

    /**
     *
     * @param name the user's name field
     * @return ture if the name begins with an upper letter and has a length of at least three letters, false otherwise
     */
    private boolean validateName(String name) {
        if (name == null || name.isEmpty()) return false;
        if (!Character.isUpperCase(name.toCharArray()[0]))
            return false;
        return name.length() > 2;
    }

    private boolean validateAge(Integer age) {
        return age > 10 && age < 50;
    }

    private boolean validateKitNumber(Integer kitNumber) {
        return kitNumber > 0 && kitNumber < 100;
    }

}
