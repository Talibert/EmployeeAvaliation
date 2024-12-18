package business;

import VO.PasswordInfoVO;

import java.util.Scanner;

/**
 * Classe responsável por estabelecer a comunicação com o usuário via terminal, coletar e validar os dados
 */
public class UserInterface {

    private Scanner sc;

    public UserInterface(){
    }

    /**
     * Esse método vai construir o VO com as preferências do usuário sobre a senha
     * @return
     */
    public PasswordInfoVO montaInfoDaSenha(){
        PasswordInfoVO passwordInfoVO = new PasswordInfoVO();

        passwordInfoVO.setTamanho(getQuantidadeDeLetras());
        passwordInfoVO.setPossuiLetrasMinusculas(isPossuiLetrasMinusculas());
        passwordInfoVO.setPossuiLetrasMaiusculas(isPossuiLetrasMaiusculas());
        passwordInfoVO.setPossuiNumeros(isPossuiNumeros());
        passwordInfoVO.setPossuiCaracteresEspeciais(isPossuiCaracteresEspeciais());

        return passwordInfoVO;
    }

    /**
     * Obtém a informação do usuário sobre o tamanho da senha
     * @return
     */
    public int getQuantidadeDeLetras(){
        System.out.println("Digite um número de caracteres entre 6-256 para gerar a senha: ");
        int tamanho = sc.nextInt();
        // Consumindo quebra de linha
        sc.nextLine();
        validaRespostaInt(tamanho);
        return tamanho;
    }

    /**
     * Obtém a informação do usuário sobre a existência de letras minúsculas
     * @return
     */
    public boolean isPossuiLetrasMinusculas(){
        System.out.println("Deseja que a senha possua letras minúsculas? (y/n)");
        String response = sc.nextLine();
        return getRespostaString(response);
    }

    /**
     * Obtém a informação do usuário sobre a existência de letras maiúsculas
     * @return
     */
    public boolean isPossuiLetrasMaiusculas(){
        System.out.println("Deseja que a senha possua letras maiúsculas? (y/n)");
        String response = sc.nextLine();
        return getRespostaString(response);
    }

    /**
     * Obtém a informação do usuário sobre a existência de números
     * @return
     */
    public boolean isPossuiNumeros(){
        System.out.println("Deseja que a senha possua números? (y/n)");
        String response = sc.nextLine();
        return getRespostaString(response);
    }

    /**
     * Obtém a informação do usuário sobre a existência de caracteres especiais
     * @return
     */
    public boolean isPossuiCaracteresEspeciais(){
        System.out.println("Deseja que a senha possua caracteres especiais? (y/n)");
        String response = sc.nextLine();
        return getRespostaString(response);
    }

    /**
     * Verifica a resposta do usuário para definir se é positiva ou negativa. Trata respostas inesperadas
     * @param response
     * @return
     */
    public boolean getRespostaString(String response){
        if(response.equalsIgnoreCase("y"))
            return true;

        if(response.equalsIgnoreCase("n"))
            return false;

        throw new RuntimeException("valor digitado inválido");
    }

    /**
     * Valida a resposta do usuário sobre o tamanho desejado de senha
     * @param tamanho
     */
    public void validaRespostaInt(int tamanho){
        if(tamanho < 6 || tamanho > 256)
            throw new RuntimeException("O tamanho da senha é inválida!");
    }

    /**
     * Abre scanner
     */
    public void openSC(){
        this.sc = new Scanner(System.in);
    }

    /**
     * Fecha scanner
     */
    public void closeSC(){
        this.sc.close();
    }
}
