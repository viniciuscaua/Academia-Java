package aplicacao;

public class FichaTreino {
    private int id_ficha;
    private int id_aluno;
    private int id_instrutor;
    private String dataInicio;
    private String dataFim;
    private String descricao;

    public FichaTreino(int id_aluno, int id_instrutor, String dataInicio, String dataFim, String descricao) {
        this.id_aluno = id_aluno;
        this.id_instrutor = id_instrutor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
    }

    public FichaTreino(int id_ficha, int id_aluno, int id_instrutor, String dataInicio, String dataFim, String descricao) {
        this.id_ficha = id_ficha;
        this.id_aluno = id_aluno;
        this.id_instrutor = id_instrutor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
    }

    public int getId_ficha() {
        return id_ficha;
    }
    public void setId_ficha(int id_ficha) {
        this.id_ficha = id_ficha;
    }
    public int getId_aluno() {
        return id_aluno;
    }
    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }
    public int getId_instrutor() {
        return id_instrutor;
    }
    public void setId_instrutor(int id_instrutor) {
        this.id_instrutor = id_instrutor;
    }
    public String getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
    public String getDataFim() {
        return dataFim;
    }
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



}