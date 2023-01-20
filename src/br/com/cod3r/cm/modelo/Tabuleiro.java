package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();
	
	public Tabuleiro(int linhas, int colunas, int minas) {
		this.colunas = colunas;
		this.linhas = linhas;
		this.minas = minas;
		
		gerarCampos();
		associarOsVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream()
			.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
			.findFirst()
			.ifPresent(c -> c.abrir());
		} catch (Exception e){
			// FIXME ajustar a implementação do método abrir
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst()
		.ifPresent(c -> c.alternarMarcacao());
	}

	private void gerarCampos() {
		for(int l = 0; l < linhas; l++) {
			for(int coluna = 0; coluna < colunas; coluna++) {
				campos.add(new Campo(l, coluna));
			}
		}
	}
	
	private void associarOsVizinhos() {
		for(Campo c1: campos){
			for(Campo c2: campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		long minasArmadas = 0;
		
		Predicate<Campo> minado = c -> c.isMinado();
		
		do {
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = campos.stream().filter(minado).count();
		} while(minasArmadas < minas);
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
}
