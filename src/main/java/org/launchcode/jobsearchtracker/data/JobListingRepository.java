package org.launchcode.jobsearchtracker.data;

import org.launchcode.jobsearchtracker.models.JobListing;
import org.springframework.data.repository.CrudRepository;

public interface JobListingRepository extends CrudRepository<JobListing, Integer> {
}
