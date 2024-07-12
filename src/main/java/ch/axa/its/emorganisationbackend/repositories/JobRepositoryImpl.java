package ch.axa.its.emorganisationbackend.repositories;

import ch.axa.its.emorganisationbackend.domain.Job;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.List;

public class JobRepositoryImpl implements JobRepositoryCustom {
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Job> filter(String designation) {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Job> cq = cb.createQuery(Job.class);

      Root<Job> job = cq.from(Job.class);

      cq.where(cb.equal(job.get("designation"), designation));

      return em.createQuery(cq).getResultList();
  }
}
