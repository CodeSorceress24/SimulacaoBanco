package controller;

import java.util.Random;

public class TransacaoController implements Runnable {
    private final ContaController conta;
    private final Random random;
    private final int idTransacao;

    public TransacaoController(ContaController conta, int idTransacao) {
        this.conta = conta;
        this.random = new Random();
        this.idTransacao = idTransacao;
    }

    @Override
    public void run() {
        boolean isSaque = random.nextBoolean();
        double valor = 50 + random.nextInt(251);

        if (isSaque) {
            conta.sacar(valor, idTransacao);
        } else {
            conta.depositar(valor, idTransacao);
        }
    }
}