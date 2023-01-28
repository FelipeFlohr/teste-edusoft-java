package br.com.edusoft.testejava.utils.logger.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import com.google.inject.ScopeAnnotation;

@ScopeAnnotation
@Retention(RUNTIME)
public @interface Request {

}
