package br.com.edusoft.testejava.modules.aluno.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.edusoft.testejava.container.Container;
import br.com.edusoft.testejava.modules.aluno.enums.AlunoStatus;
import br.com.edusoft.testejava.modules.aluno.models.Aluno;
import br.com.edusoft.testejava.modules.aluno.models.Nota;

@TestInstance(value = Lifecycle.PER_CLASS)
public class AlunoEntityTest {
	Injector injector;
	IAlunoEntity entity;
	Nota notaBoaFaltaUm;
	Nota notaBoaSemFalta;
	Nota notaRuimFaltaUm;
	Nota notaRuimSemFalta;

	@BeforeAll
	void beforeAll() {
		injector = Guice.createInjector(new Container());

		this.notaBoaFaltaUm = new Nota(1, 10);
		this.notaBoaSemFalta = new Nota(0, 10);
		this.notaRuimFaltaUm = new Nota(1, 5);
		this.notaRuimSemFalta = new Nota(0, 5);
	}

	@BeforeEach
	void beforeEach() {
		entity = injector.getInstance(IAlunoEntity.class);
	}

	@AfterEach
	void afterEach() {
		entity = null;
	}

	@Test
	void testAlunoStatus() {
		// Reprovado Frequencia
		List<Nota> notasReprovacaoFrequencia = new ArrayList<>();
		notasReprovacaoFrequencia.add(notaBoaFaltaUm);
		notasReprovacaoFrequencia.add(notaBoaFaltaUm);
		notasReprovacaoFrequencia.add(notaBoaFaltaUm);

		Aluno alunoReprovacaoFrequencia = new Aluno(1, "teste", 4, notasReprovacaoFrequencia);
		assertEquals(entity.getAlunoStatus(alunoReprovacaoFrequencia), AlunoStatus.REPROVADO_FREQUENCIA);

		// Aprovacao
		List<Nota> notasAprovacao = new ArrayList<>();
		notasAprovacao.add(notaBoaSemFalta);
		notasAprovacao.add(notaBoaSemFalta);
		notasAprovacao.add(notaBoaFaltaUm);

		Aluno alunoAprovacao = new Aluno(1, "teste", 4, notasAprovacao);
		assertEquals(entity.getAlunoStatus(alunoAprovacao), AlunoStatus.APROVADO);

		// Reprovacao Média
		List<Nota> notasReprovacaoMedia = new ArrayList<>();
		notasReprovacaoMedia.add(notaRuimSemFalta);
		notasReprovacaoMedia.add(notaRuimSemFalta);
		notasReprovacaoMedia.add(notaRuimFaltaUm);

		Aluno alunoReprovacaoMedia = new Aluno(1, "teste", 4, notasReprovacaoMedia);
		assertEquals(entity.getAlunoStatus(alunoReprovacaoMedia), AlunoStatus.REPROVADO_MEDIA);
	}

	@Test
	void testMedia() {
		List<Nota> notas = new ArrayList<>();
		notas.add(notaBoaSemFalta);
		notas.add(notaBoaSemFalta);
		notas.add(notaBoaSemFalta);
		notas.add(notaRuimSemFalta);

		Aluno aluno = new Aluno(1, "teste", 4, notas);
		assertEquals(entity.getMedia(aluno), 8.75);
	}

	@Test
	void testFrequencia() {
		List<Nota> notas = new ArrayList<>();
		notas.add(notaBoaSemFalta);
		notas.add(notaBoaSemFalta);
		notas.add(notaBoaSemFalta);
		notas.add(notaBoaFaltaUm);

		Aluno aluno = new Aluno(1, "teste", 4, notas);
		assertEquals(entity.getFrequencia(aluno), 75.0);
	}
}
