public class PoupancaSaude extends Poupanca
{
    private double saldoVinculado;
    private double saldoFinanciado;
    private Dependente[] dependentes;

    public PoupancaSaude(int numConta, String nome)
    {
        super(numConta, nome);
        dependentes  = new Dependente[5];
    }

    public int contaDependentes()
    {
        int quantidade = 0;
        for (int i = 0; i < dependentes.length; i++)
            if (dependentes[i] != null)
                quantidade++;
        return quantidade;
    }

    public void deposita(double valor)
    {
        double valorVinculado = 0;
        if (contaDependentes() == 0)
            valorVinculado += valor * 0.15;
        else if (contaDependentes() >= 1 && contaDependentes() <= 2)
            valorVinculado += valor * 0.20;
        else if (contaDependentes() >= 3 && contaDependentes() <= 4)
            valorVinculado += valor * 0.30;
        else if (contaDependentes() >= 5)
            valorVinculado += valor * 0.50;

        super.deposita(valor - valorVinculado);

        saldoVinculado += valorVinculado;

    }

    public double creditaRendimento(double taxa)
    {
        double rendimentoSaldoLivre = super.creditaRendimento(taxa);

        double rendimentoSaldoVinculado = taxa * this.saldoVinculado;
        this.saldoVinculado += rendimentoSaldoVinculado;

        return rendimentoSaldoLivre + rendimentoSaldoVinculado;
    }

    public boolean insereDependente(Dependente dep)
    {
        boolean resp = false;
        for (int i = 0; i < dependentes.length; i++)
            if(dependentes[i] == null)
            {
                dependentes[i] = dep;
                i = dependentes.length + 1;
                resp = true;
            }
        return resp;
    }

    public int buscaDependente(String nomeDependente)
    {
        int indice = 99;
        for (int i = 0; i < dependentes.length; i++)
            if (dependentes[i] != null)
                if(dependentes[i].getNome() == nomeDependente)
                    indice = i;
        return indice;
    }

    public Dependente retiraDependente(String nomeDependente)
    {
        int indice = buscaDependente(nomeDependente);
        Dependente dep = null;

        if (indice != 99){
            dep = dependentes[indice];
            dependentes[indice] = null;
        }

        return dep; 

    }

    public double retiraSaude(double valorDespesaSaude)
    {
        if (valorDespesaSaude <= saldoVinculado)
        {
            saldoVinculado -= valorDespesaSaude;    
        }
        else
        {
            Teclado t = new Teclado();

            double valorRestanteDespesa = valorDespesaSaude - saldoVinculado;

            saldoVinculado = 0;

            System.out.println("------------------------------");
            System.out.println("SALDO VINCULODO INSUFICIENTE!");
            System.out.println("------------------------------");
            System.out.println("Valor Restante da despesa : R$ " + valorRestanteDespesa);
            System.out.println("Saldo Livre Disponivel : " + super.getSaldoLivre());
            System.out.println("------------------------------");

            double valorSolicitado = 0;
            do{
                valorSolicitado = t.leDouble("Informe a quantidade de saldo livre você deseja utilizar:");

                if(valorSolicitado > super.getSaldoLivre() || valorSolicitado > valorRestanteDespesa){
                    System.out.println("Valor muito grande, redigíte!");
                    System.out.println("Saldo Disponivel : " + super.getSaldoLivre());
                }

            }while(valorSolicitado > super.getSaldoLivre()  || valorSolicitado > valorRestanteDespesa);

            valorRestanteDespesa -= valorSolicitado;

            super.retira(valorSolicitado);
            double financiado = 0;
            if (valorRestanteDespesa > 0)
            {
                if (saldoFinanciado == 0) 
                    financiado = valorRestanteDespesa + (valorRestanteDespesa * 0.05);
                else if(saldoFinanciado <= 500)
                    financiado = valorRestanteDespesa + (valorRestanteDespesa * 0.10);
                else if(saldoFinanciado > 500)
                    financiado = valorRestanteDespesa + (valorRestanteDespesa * 0.15);
                
                saldoFinanciado += financiado;
            }
            return financiado;
        }
            return 0.00;
    }

    public double amortizaFinanciamento(double valorArmonizador)
    {
    	if (valorArmonizador > saldoFinanciado) return 0;
    	else{
    		saldoFinanciado -= valorArmonizador;
    		if (saldoFinanciado == 0) {
    			super.deposita(valorArmonizador * 0.05);
    			return valorArmonizador * 0.05;
    		}
    	}
    	return 0;

    }

    private void ordenaDependentes()
    {
    	Dependente aux = null;
        for(int i=0; i < dependentes.length; i++)
            for(int j=i+1; j < dependentes.length; j++)
            	if (dependentes[i]  != null && dependentes[j] != null) {
	                if(dependentes[j].getNome().compareToIgnoreCase(dependentes[i].getNome()) < 0)
	                {
	                    aux = dependentes[j];
	                    dependentes[j] = dependentes[i];
	                    dependentes[i] = aux;
	                }
            	}
    }

    public String toString()
    {
    	String infoConta = super.toString() + "\nSaldo Vinculado: R$ " + this.saldoVinculado +"\nSaldo Financiado: R$ " + this.saldoFinanciado;
    	ordenaDependentes();
    	String infoDep   =  "";
    	for (int i=0; i < dependentes.length; i++) {
    		if (dependentes[i] != null) {
    			infoDep += "\n" + dependentes[i].toString();
    		}
    	}
    	return infoConta + " " + infoDep;
    }

}