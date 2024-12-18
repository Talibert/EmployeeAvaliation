package business;
import VO.PasswordInfoVO;
import java.util.Random;

/**
 * Classe responsável por gerar a senha
 */
public class PasswordGenerator {

    public PasswordGenerator (){
    }

    /**
     * Esse método vai gerar uma senha com base em indices selecionados de forma aleatória em cima da string base
     * @param passwordInfoVO
     * @return
     */
    public String geraSenha(PasswordInfoVO passwordInfoVO){
        String stringBase = getStringBase(passwordInfoVO);

        if(stringBase.isBlank())
            throw new RuntimeException("Pelo menos um parâmetro deve ser verdadeiro!");

        Random random = new Random();
        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < passwordInfoVO.getTamanho(); i++) {

            int indiceAleatorio = random.nextInt(stringBase.length());

            senha.append(stringBase.charAt(indiceAleatorio));
        }

        return senha.toString();
    }

    /**
     * Esse método vai gerar uma string base de acordo com a preferência do usuário
     * @param passwordInfoVO
     * @return
     */
    private String getStringBase(PasswordInfoVO passwordInfoVO){
        StringBuilder stringBase = new StringBuilder();

        if(passwordInfoVO.isPossuiLetrasMinusculas())
            stringBase.append("abcdefghijklmnopqrstuvwxyz");

        if(passwordInfoVO.isPossuiLetrasMaiusculas())
            stringBase.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        if(passwordInfoVO.isPossuiNumeros())
            stringBase.append("0123456789");

        if(passwordInfoVO.isPossuiCaracteresEspeciais())
            stringBase.append("!@#$%&*");

        return stringBase.toString();
    }

}
