package org.example.test.core.domain;

import org.example.test.core.domain.base.HibernateDao;
import org.example.test.core.domain.base.HibernateDaoBase;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonDao extends HibernateDaoBase<Person, Long> implements HibernateDao<Person, Long> {

	

}
