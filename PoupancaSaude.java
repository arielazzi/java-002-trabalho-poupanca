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

	// public creditaRendimento

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

	// public buscaDependente

	// public retiraDependente

	// public retiraSaude

	// public amortizaFinanciamento

	// private ordenaDependentes

	// public toString

}