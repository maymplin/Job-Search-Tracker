package org.launchcode.jobsearchtracker.Data;

import org.launchcode.jobsearchtracker.Models.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Integer> {
}
