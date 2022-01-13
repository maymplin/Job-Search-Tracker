package org.launchcode.jobsearchtracker.data;

import org.launchcode.jobsearchtracker.models.JobListingDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobListingDetailsRepository extends CrudRepository<JobListingDetails, Integer> {
}
