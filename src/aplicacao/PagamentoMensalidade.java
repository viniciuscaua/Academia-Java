package aplicacao;

public class PagamentoMensalidade {
    private int id_pagamento;
    private int id_aluno;
    private String datapagamento;
    private int valor;

    public PagamentoMensalidade(String datapagamento, int valor){
        this.datapagamento = datapagamento;
        this.valor = valor;
    }
    
    public PagamentoMensalidade(int id_aluno, String datapagamento, int valor) {
        this.id_aluno = id_aluno;
        this.datapagamento = datapagamento;
        this.valor = valor;
    }
    
    public int getId_pagamento() {
        return id_pagamento;
    }
    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }
    public int getId_aluno() {
        return id_aluno;
    }
    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }
    public String getDatapagamento() {
        return datapagamento;
    }
    public void setDatapagamento(String datapagamento) {
        this.datapagamento = datapagamento;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
}