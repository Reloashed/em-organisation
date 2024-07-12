package ch.axa.its.emorganisationbackend.repositories;

import ch.axa.its.emorganisationbackend.domain.Job;
import ch.axa.its.emorganisationbackend.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepositoryCustom {
  @PersistenceContext
  private EntityManager em;

  @Override
  public Iterable<Person> filter(String firstname, String lastname) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Person> cq = cb.createQuery(Person.class);

      Root<Person> person = cq.from(Person.class);

      List<Predicate> predicates = new ArrayList<>();

      if (firstname != null) {
        predicates.add(cb.equal(person.get("firstname"), firstname));
      }
      if (lastname != null) {
        predicates.add(cb.equal(person.get("lastname"), lastname));
      }

      cq.where(predicates.toArray(predicates.toArray(new Predicate[0])));

      return em.createQuery(cq).getResultList();
  }
}
