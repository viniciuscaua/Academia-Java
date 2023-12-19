package aplicacao;

public class AlunoInstrutor {
    private int id_fichadetreino;
    private int id_aluno;
    private int id_instrutor;
    
    public AlunoInstrutor(int id_aluno, int id_instrutor) {
        this.id_aluno = id_aluno;
        this.id_instrutor = id_instrutor;
    }

    public int getId_fichadetreino() {
        return id_fichadetreino;
    }
    public void setId_fichadetreino(int id_fichadetreino) {
        this.id_fichadetreino = id_fichadetreino;
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

    
}