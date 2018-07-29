package org.example.test.core.domain;

import org.example.test.core.domain.base.ApplicationDao;
import org.example.test.core.domain.base.ApplicationDaoBase;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends ApplicationDaoBase<Person, Long> implements ApplicationDao<Person, Long> {



}
