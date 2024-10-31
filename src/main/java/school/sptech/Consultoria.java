package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public List<Desenvolvedor> getDesenvolvedor() {
        return desenvolvedores;
    }

    public void setDesenvolvedor(List<Desenvolvedor> desenvolvedor) {
        this.desenvolvedores = desenvolvedor;
    }

    public Boolean contratar(Desenvolvedor desenvolvedor){
        if (desenvolvedores.size() < vagas){
                desenvolvedores.add(desenvolvedor);
                return true;
        }
        return false;

    }

    public Boolean contratarFullstack(DesenvolvedorWeb desenvolvedor){
        if (desenvolvedor.isFullstack() && desenvolvedores.size() < vagas){
            desenvolvedores.add(desenvolvedor);
            return true;
        }

        return false;
    }

    public Double getTotalSalarios(){
        double totalSalario = 0.0;
        for (Desenvolvedor desenvolvedor : desenvolvedores){
            totalSalario += desenvolvedor.calcularSalario();
        }

        return totalSalario;
    }

    public Integer qtdDesenvolvedoresMobile(){
       int count = 0;

       for (Desenvolvedor desenvolvedor : desenvolvedores){
           if (desenvolvedor instanceof DesenvolvedorMobile){
               count++;
           }
       }
       return count;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario){
        List<Desenvolvedor> resultado = new ArrayList<>();

        for (Desenvolvedor desenvolvedor : desenvolvedores){
            if (desenvolvedor.calcularSalario() >= salario){
                resultado.add(desenvolvedor);
            }
        }
        return resultado;
    }

    public Desenvolvedor buscarMenorSalario(){
        if (desenvolvedores.isEmpty()){
            return null;
        }

        Desenvolvedor resultadoMenor = desenvolvedores.get(0);
        for (Desenvolvedor desenvolvedor : desenvolvedores){
            if (desenvolvedor.calcularSalario() < resultadoMenor.calcularSalario()){
                resultadoMenor = desenvolvedor;
            }
        }
        return resultadoMenor;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> resultado = new ArrayList<>();


        for (int i = 0; i < desenvolvedores.size(); i++) {
            Desenvolvedor desenvolvedor = desenvolvedores.get(i);


            if (desenvolvedor instanceof DesenvolvedorWeb) {
                if ((((DesenvolvedorWeb) desenvolvedor).getBackend().equalsIgnoreCase(tecnologia)) ||
                        (((DesenvolvedorWeb) desenvolvedor).getFrontend().equalsIgnoreCase(tecnologia)) ||
                        (((DesenvolvedorWeb) desenvolvedor).getSgbd().equalsIgnoreCase(tecnologia))) {
                    resultado.add(desenvolvedor);
                }
            }


            if (desenvolvedor instanceof DesenvolvedorMobile) {

                if ((((DesenvolvedorMobile) desenvolvedor).getLinguagem().equalsIgnoreCase(tecnologia)) ||
                        (((DesenvolvedorMobile) desenvolvedor).getPlataforma().equalsIgnoreCase(tecnologia))) {
                    resultado.add(desenvolvedor);
                }
            }
        }
        return resultado;
    }


    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        Double totalSalarios = 0.0;

        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            if (desenvolvedor instanceof DesenvolvedorWeb) {
                DesenvolvedorWeb devWeb = (DesenvolvedorWeb) desenvolvedor;

                if (devWeb.getBackend().equalsIgnoreCase(tecnologia) ||
                        devWeb.getFrontend().equalsIgnoreCase(tecnologia) ||
                        devWeb.getSgbd().equalsIgnoreCase(tecnologia)) {
                    totalSalarios += desenvolvedor.calcularSalario();
                }
            }

            if (desenvolvedor instanceof DesenvolvedorMobile) {
                DesenvolvedorMobile devMobile = (DesenvolvedorMobile) desenvolvedor;

                if (devMobile.getLinguagem().equalsIgnoreCase(tecnologia) ||
                        devMobile.getPlataforma().equalsIgnoreCase(tecnologia)) {
                    totalSalarios += desenvolvedor.calcularSalario();
                }
            }
        }

        return totalSalarios;
    }

}
