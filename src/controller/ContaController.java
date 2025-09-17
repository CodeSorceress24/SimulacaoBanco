package controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import view.BancoView;

public class ContaController {
    private static final Lock lockSaque = new ReentrantLock();
    private static final Lock lockDeposito = new ReentrantLock();
    private static final BancoView view = new BancoView();
    
    private final String codigoConta;
    private double saldo;

    public ContaController(String codigoConta, double saldoInicial) {
        this.codigoConta = codigoConta;
        this.saldo = saldoInicial;
    }

    public void sacar(double valor, int threadId) {
        lockSaque.lock();
        try {
            view.mostrarTentativaSaque(codigoConta, valor, threadId);
            
            if (saldo >= valor) {
                Thread.sleep(100 + (int)(Math.random() * 200));
                saldo -= valor;
                view.mostrarSaqueSucesso(codigoConta, valor, saldo, threadId);
            } else {
                view.mostrarSaqueFalha(codigoConta, valor, saldo, threadId);
            }
        } catch (InterruptedException e) {
            view.mostrarInterrupcao(codigoConta, "saque", threadId);
            Thread.currentThread().interrupt();
        } finally {
            lockSaque.unlock();
        }
    }

    public void depositar(double valor, int threadId) {
        lockDeposito.lock();
        try {
            view.mostrarTentativaDeposito(codigoConta, valor, threadId);
            
            Thread.sleep(100 + (int)(Math.random() * 200));
            saldo += valor;
            view.mostrarDepositoSucesso(codigoConta, valor, saldo, threadId);
            
        } catch (InterruptedException e) {
            view.mostrarInterrupcao(codigoConta, "deposito", threadId);
            Thread.currentThread().interrupt();
        } finally {
            lockDeposito.unlock();
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public String getCodigoConta() {
        return codigoConta;
    }
}