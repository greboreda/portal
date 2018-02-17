package com.greboreda.portal.business.login.persistence.dao;

import com.greboreda.portal.business.login.persistence.dbo.LoginServiceDBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class LoginServiceDAOTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private LoginServiceDAO loginServiceDAO;

	@Test
	public void foo() {

		final Iterable<LoginServiceDBO> all = loginServiceDAO.findAll();

		assertThat(all.iterator().hasNext(), is(true));

	}

}