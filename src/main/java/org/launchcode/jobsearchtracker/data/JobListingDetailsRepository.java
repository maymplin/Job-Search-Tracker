package org.launchcode.jobsearchtracker.data;

import org.launchcode.jobsearchtracker.models.JobListingDetails;
import org.springframework.data.repository.CrudRepository;

public interface JobListingDetailsRepository extends CrudRepository<JobListingDetails, Integer> {
}
