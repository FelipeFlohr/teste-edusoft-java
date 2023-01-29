package br.com.edusoft.testejava.container;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.inject.Provider;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@TestInstance(value = Lifecycle.PER_CLASS)
class ContainerTest {
	Injector injector;

	@BeforeAll
	void beforeAll() {
		injector = Guice.createInjector(new TestModule());
	}

	@Test
	void testSingleton() throws InterruptedException {
		final ISingletonTimestamp timestamp1 = injector.getInstance(ISingletonTimestamp.class);
		Thread.sleep(100);
		final ISingletonTimestamp timestamp2 = injector.getInstance(ISingletonTimestamp.class);
		Thread.sleep(100);
		final ISingletonTimestamp timestamp3 = injector.getInstance(ISingletonTimestamp.class);

		assertEquals(timestamp1.getDate(), timestamp2.getDate());
		assertEquals(timestamp1.getDate(), timestamp3.getDate());
		assertEquals(timestamp2.getDate(), timestamp3.getDate());
	}

	@Test
	void testProvider() throws InterruptedException {
		final IProviderTimestamp timestamp1 = injector.getInstance(IProviderTimestamp.class);
		System.out.println(timestamp1.getDate());
		Thread.sleep(100);
		final IProviderTimestamp timestamp2 = injector.getInstance(IProviderTimestamp.class);
		System.out.println(timestamp2.getDate());
		Thread.sleep(100);
		final IProviderTimestamp timestamp3 = injector.getInstance(IProviderTimestamp.class);

		assertNotEquals(timestamp1.getDate(), timestamp2.getDate());
		assertNotEquals(timestamp1.getDate(), timestamp3.getDate());
		assertNotEquals(timestamp2.getDate(), timestamp3.getDate());
	}
}

class TestModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ISingletonTimestamp.class).to(SingletonTimestamp.class);
		bind(IProviderTimestamp.class).toProvider(ProviderTimestampProvider.class);
	}
}

interface ISingletonTimestamp {
	long getDate();
}

interface IProviderTimestamp {
	long getDate();
}

@Singleton
class SingletonTimestamp implements ISingletonTimestamp {
	private final Date date;

	SingletonTimestamp() {
		date = new Date();
	}

	@Override
	public long getDate() {
		return date.getTime();
	}
}

class ProviderTimestamp implements IProviderTimestamp {
	private final Date date;

	public ProviderTimestamp() {
		date = new Date();
	}

	@Override
	public long getDate() {
		return date.getTime();
	}
}

class ProviderTimestampProvider implements Provider<IProviderTimestamp> {
	@Override
	public ProviderTimestamp get() {
		return new ProviderTimestamp();
	}
}