import VO.PasswordInfoVO;
import business.PasswordGenerator;
import business.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();

        PasswordGenerator passwordGenerator = new PasswordGenerator();

        userInterface.openSC();

        PasswordInfoVO passwordInfoVO = userInterface.montaInfoDaSenha();

        String senha = passwordGenerator.geraSenha(passwordInfoVO);

        System.out.println("Senha gerada: " + senha);

        userInterface.closeSC();
    }
}