package VO;

/**
 * VO criado para armazenar as informações de customização da senha
 */
public class PasswordInfoVO {

    private boolean isPossuiLetrasMinusculas;
    private boolean isPossuiLetrasMaiusculas;
    private boolean isPossuiNumeros;
    private boolean isPossuiCaracteresEspeciais;
    private int tamanho;

    public PasswordInfoVO() {
    }

    public boolean isPossuiLetrasMinusculas() {
        return isPossuiLetrasMinusculas;
    }

    public void setPossuiLetrasMinusculas(boolean possuiLetrasMinusculas) {
        isPossuiLetrasMinusculas = possuiLetrasMinusculas;
    }

    public boolean isPossuiLetrasMaiusculas() {
        return isPossuiLetrasMaiusculas;
    }

    public void setPossuiLetrasMaiusculas(boolean possuiLetrasMaiusculas) {
        isPossuiLetrasMaiusculas = possuiLetrasMaiusculas;
    }

    public boolean isPossuiNumeros() {
        return isPossuiNumeros;
    }

    public void setPossuiNumeros(boolean possuiNumeros) {
        isPossuiNumeros = possuiNumeros;
    }

    public boolean isPossuiCaracteresEspeciais() {
        return isPossuiCaracteresEspeciais;
    }

    public void setPossuiCaracteresEspeciais(boolean possuiCaracteresEspeciais) {
        isPossuiCaracteresEspeciais = possuiCaracteresEspeciais;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
