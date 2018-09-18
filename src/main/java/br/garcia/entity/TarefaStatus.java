package br.garcia.entity;

public enum TarefaStatus{
    TODO(1),
    DOING(2),
    CODE_REVIEW(3),
    TEST(4),
    DONE(5);

    public int valor;
    TarefaStatus(int _valor){
        valor = valor;
    }
}
