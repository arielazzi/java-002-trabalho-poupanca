public class Agencia
{
    private Poupanca[] poupancas;
    private int qtd;

    public Agencia(int qtdMaxima)
    {
        poupancas = new Poupanca[qtdMaxima];
        qtd = 0;
    }

    private int abreConta()
    {
        if (poupancas.length == qtd)
            return -1;
        else
        {
            Teclado t = new Teclado();
            int tipoConta = t.leInt("Informe o tipo da de conta que você deseja abrir: \n 1 - Poupança Simples \n 2 - Poupança Saúde");

            if (tipoConta != 1)
                tipoConta = 2;

            if (tipoConta == 1)
                poupancas[qtd] = new Poupanca(t.leInt("Informe o numero da conta:"), t.leString("Informe o seu Nome:"));
            else
                poupancas[qtd] = new PoupancaSaude(t.leInt("Informe o numero da conta:"), t.leString("Informe o seu Nome:"));

            if (tipoConta == 2)
            {
                char insereDependente = t.leChar("Deseja inserir um dependente(s/n)?");
                while(insereDependente == 's')
                {
                    Dependente dep = new Dependente(t.leString("Informe o nome do depente:"), t.leChar("Informe o parentesco:"));
                    
                    ((PoupancaSaude)poupancas[qtd]).insereDependente(dep);

                    if (((PoupancaSaude)poupancas[qtd]).contaDependentes() == 5)
                        insereDependente = 'n';
                    else
                        insereDependente = t.leChar("Deseja inserir mais um dependente(s/n)?");
                }
                
            }
            int indice = qtd; 
            qtd++;
            return poupancas[indice].getNumero();
        }
    }

    private int buscarConta(int numConta)
    {
        int aux = -1; 
        for (int i=0;i < poupancas.length; i++)
            if(poupancas[i] != null)
                if (poupancas[i].getNumero() == numConta)
                    aux = i;
        return aux;
    }

    public void menuDeTransacoes()
    {
        Teclado t = new Teclado();
        int op;
        do
        {
            System.out.println("\f1 - Abre Conta");
            System.out.println("2 - Deposita");
            System.out.println("3 - Retira");
            System.out.println("4 - Retira Para Saúde");
            System.out.println("5 - Amortiza financiamento");
            System.out.println("6 - Emite extrato da conta");
            System.out.println("7 - Credita rendimento");
            System.out.println("8 - Insere um dependente");
            System.out.println("9 - Retira um dependente");
            System.out.println("10 - Encerra");
            
            do{
                op = t.leInt("Selecione a opção do menu: ");
                if(op<1 || op>10)
                    System.out.println("Opção invalida, Digite novamente!");
            }while(op<1 || op>10);

            if(op == 1)
            {
                int result = abreConta();
                if (result == -1)
                    System.out.println("Não pode abrir novas contas nesta agência");
                else
                    System.out.println("Conta aberta de numero: " + result);
            }
            else if(op == 2)
            {
                int indice = buscarConta(t.leInt("Informe o numero da conta:"));
                if (indice == -1)
                    System.out.println("Conta Inexistente");
                else
                    poupancas[indice].deposita(t.leDouble("Informe o valor do deposito"));
            }
            else if(op == 3)
            {
                int indice = buscarConta(t.leInt("Informe o numero da conta:"));
                if (indice == -1)
                    System.out.println("Conta Inexistente");
                else{
                    boolean ret = poupancas[indice].retira(t.leDouble("Informe o valor da retirada"));
                    if (!ret)
                        System.out.println("Saldo Insuficiente");
                }

            // }
            // else if(op == 4)
            //     seq.geraExibeMultiplosCalcMed(t.leInt("Digite um numero: "));
            // else if(op == 5)
            //     seq.geraExibeDivisores();
            // else if(op == 6)
            //     seq.geraExibePrimos();
            // else if(op == 7)
            //     seq.geraExibePrimos();
            // else if(op == 8)
            //     seq.geraExibePrimos();
            // else if(op == 9)
            //     seq.geraExibePrimos();

            // op = t.leInt("Informe uma opção:");
            if(op != 10)
                t.leInt("Tecle Enter para exibir o menu novamente");


        }while(op != 10);
    }
}













 // do{
 //            System.out.println("\f------------------------------");
 //            System.out.println("1 – Gera e exibe inteiros");
 //            System.out.println("2 – Gera e exibe Pares");
 //            System.out.println("3 – Gera e exibe  múltiplos comuns de 3 e 5");
 //            System.out.println("4 – Exibe a média aritmética dos múltiplos");
 //            System.out.println("5 – Gera e exibe divisores");
 //            System.out.println("6 – Gera e exibe Primos");
 //            System.out.println("7 – Sair");
 //            System.out.println("------------------------------");
            
 //            do{
 //                op = t.leInt("Selecione a opção do menu: ");
 //                if(op<1 || op>7)
 //                    System.out.println("Opção invalida, Digite novamente!");
 //            }while(op<1 || op>7);

 //            if(op == 1)
 //                seq.geraExibeInt();
 //            else if(op == 2)
 //                seq.geraExibePares();
 //            else if(op == 3)
 //                seq.geraExibeMultiplos();
 //            else if(op == 4)
 //                seq.geraExibeMultiplosCalcMed(t.leInt("Digite um numero: "));
 //            else if(op == 5)
 //                seq.geraExibeDivisores();
 //            else if(op == 6)
 //                seq.geraExibePrimos();
            
 //            if(op != 7)
 //                t.leInt("Tecle Enter para exibir o menu novamente");
 //        }while(op != 7);