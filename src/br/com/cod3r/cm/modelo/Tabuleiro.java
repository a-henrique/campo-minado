package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

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
		
	}
}
