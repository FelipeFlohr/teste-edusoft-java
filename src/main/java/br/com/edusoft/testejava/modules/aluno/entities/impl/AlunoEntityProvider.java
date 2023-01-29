package br.com.edusoft.testejava.modules.aluno.entities.impl;

import javax.inject.Provider;

import br.com.edusoft.testejava.modules.aluno.entities.IAlunoEntity;

public class AlunoEntityProvider implements Provider<IAlunoEntity> {
	@Override
	public IAlunoEntity get() {
		return new AlunoEntityImpl();
	}
}
