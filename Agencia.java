public class Agencia
{
    private Poupanca[] poupancas;
    private int qtd;

    public Agencia(int qtdMaxima)
    {
        poupancas = new Poupanca[qtdMaxima];
        qtd = 0;
    }

    public int abreConta()
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
}
