public class Dependente
{
	private String nome;
	private char parentesco;

	public Dependente(String nome, char parentesco)
	{
		this.nome = nome;
		setParentesco(parentesco);
	}

	public void setParentesco(char parentesco)
	{
		if (parentesco != 'c' && parentesco != 'f' && parentesco != 'p' && parentesco != 'o')
			this.parentesco = 'o';
		else
			this.parentesco = parentesco;
	}

	public String traduzParentesco()
	{
		String parentescoPorExtenso = "";

		if (parentesco == 'c')
			parentescoPorExtenso = "Cônjuge";
		else if(parentesco == 'f')
			parentescoPorExtenso = "Filho(a)";
		else if(parentesco == 'p')
			parentescoPorExtenso = "Progenitor";
		else if(parentesco == 'o')
			parentescoPorExtenso = "outro";

		return parentescoPorExtenso;
	}

	public String toString()
	{
		return "Nome: " + nome + " Parentesco: " + traduzParentesco();
	}
}