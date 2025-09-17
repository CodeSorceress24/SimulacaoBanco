package view;

public class BancoView {
    
    public void mostrarCabecalho() {
        System.out.println("SISTEMA BANCARIO - TRANSACOES SIMULTANEAS");
        System.out.println("=========================================");
        System.out.println("Regras:");
        System.out.println("- 1 Saque OU 1 Deposito simultaneo");
        System.out.println("- NUNCA 2 Saques ou 2 Depositos juntos");
        System.out.println("- 20 transacoes aleatorias");
        System.out.println("=========================================");
        System.out.println();
    }
    
    public void mostrarTentativaSaque(String conta, double valor, int threadId) {
        System.out.println("Thread " + threadId + " - Conta " + conta + 
                         ": Tentando saque de R$" + valor);
    }
    
    public void mostrarSaqueSucesso(String conta, double valor, double saldo, int threadId) {
        System.out.println("Thread " + threadId + " - Conta " + conta + 
                         ": Saque de R$" + valor + " realizado. Saldo: R$" + saldo);
    }
    
    public void mostrarSaqueFalha(String conta, double valor, double saldo, int threadId) {
        System.out.println("Thread " + threadId + " - Conta " + conta + 
                         ": Saldo insuficiente para saque de R$" + valor + 
                         ". Saldo atual: R$" + saldo);
    }
    
    public void mostrarTentativaDeposito(String conta, double valor, int threadId) {
        System.out.println("Thread " + threadId + " - Conta " + conta + 
                         ": Tentando deposito de R$" + valor);
    }
    
    public void mostrarDepositoSucesso(String conta, double valor, double saldo, int threadId) {
        System.out.println("Thread " + threadId + " - Conta " + conta + 
                         ": Deposito de R$" + valor + " realizado. Saldo: R$" + saldo);
    }
    
    public void mostrarInterrupcao(String conta, String operacao, int threadId) {
        System.out.println("Thread " + threadId + " - Conta " + conta + 
                         ": Operacao de " + operacao + " interrompida");
    }
    
    public void mostrarEstatisticas(int saques, int depositos, double saldoFinal) {
        System.out.println();
        System.out.println("ESTATISTICAS DAS TRANSACOES");
        System.out.println("============================");
        System.out.println("Total de Saques: " + saques);
        System.out.println("Total de Depositos: " + depositos);
        System.out.println("Saldo Final: R$" + saldoFinal);
        System.out.println("Transacoes Concluidas: " + (saques + depositos) + "/20");
    }
    
    public void mostrarInicioTransacoes() {
        System.out.println("Iniciando 20 transacoes simultaneas...");
        System.out.println();
    }
}
