package org.launchcode.jobsearchtracker.Data;

import org.launchcode.jobsearchtracker.Models.JobDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDetailsRepository extends CrudRepository<JobDetails, Integer> {
}
