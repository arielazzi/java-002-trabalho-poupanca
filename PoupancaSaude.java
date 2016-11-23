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

	public String retiraSaude(double valorDespesaSaude)
	{
		if (valorDespesaSaude <= saldoVinculado)
		{
			saldoVinculado -= valorDespesaSaude;	
		}
		else
		{
			Teclado t = new Teclado();
			int op;
			do{
				double valorRestanteDespesa = valorDespesaSaude - saldoVinculado;

	            System.out.println("------------------------------");
	            System.out.println("SALDO VINCULODO INSUFICIENTE!");
	            System.out.println("------------------------------");
	            System.out.println("Valor Restante da despesa : R$ " + valorRestanteDespesa);
	            System.out.println("Saldo Livre Disponivel : " + super.getSaldoLivre());
	            System.out.println("------------------------------");
	            System.out.println("1 – Utilizar Saldo Livre");
	            System.out.println("2 – Financiamento");
	            System.out.println("3 – Ambas");
	            System.out.println("4 – Sair");
	            System.out.println("------------------------------");
	            
	            do{
	                op = t.leInt("Selecione a opção do menu: ");
	                if(op<1 || op>7)
	                    System.out.println("Opção invalida, Digite novamente!");
	            }while(op<1 || op>7);

	            if(op == 1)
	            {
	                t.leint("Informe a quantidade de saldo livre você deseja utilizar!");
	             	do{
		                int valorRequerido = t.leint("Informe a quantidade de saldo livre você deseja utilizar!");
		                if(valorRequerido > super.getSaldoLivre())
		                    System.out.println("Valor muito grande, redigíte!");
		            }while(valorRequerido > super.getSaldoLivre());   
	            }
	            // else if(op == 2)
	            // {
	            //     seq.geraExibePares();
	            // }
	            // else if(op == 3)
	            // {
	            //     seq.geraExibeMultiplos();
	            // }
	            
	            if(op != 4)
	                t.leInt("Tecle Enter para exibir o menu novamente");

	        }while(op != 4);
		}
		return "teste";
	}

	// public amortizaFinanciamento

	// private ordenaDependentes

	// public toString

}