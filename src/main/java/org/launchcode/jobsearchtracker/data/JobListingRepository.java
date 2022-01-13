package org.launchcode.jobsearchtracker.data;

import org.launchcode.jobsearchtracker.models.JobListing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobListingRepository extends CrudRepository<JobListing, Integer> {
}
