
public class Test_JobList {

	public static void main(String[] args) {

		// Jobs for our jobList.
		Job testJob1 = new Job("testJob1", 10, 100);
		Job testJob2 = new Job("testJob2", 10, 100);
		Job testJob3 = new Job("testJob3", 10, 100);

		// Create the first Listnode with a Job object.
		Listnode<Job> listNode = new Listnode<Job>(testJob1);

		// Create a new JobList of length 3.
		JobList<Job> jobList = new JobList<Job>(listNode);
		jobList.add(testJob2);
		jobList.add(testJob3);

		// Number of tests passed
		int passedTests = 0;

		System.out.println("Testing JobList.java class.");
		System.out.println("===========================");

		System.out.println("---------------------------");
		System.out.println("Testing JobList.add(item)");

		// Try adding a null item
		try {
			jobList.add(null);
		} catch (IllegalArgumentException e) {
			System.out.println("Exception successfully thrown for null argument.");
			passedTests++;
		}

		System.out.println("---------------------------");
		System.out.println("Testing JobList.add(pos, item)");

		// Try adding a null item
		try {
			jobList.add(1, null);
		} catch (IllegalArgumentException e) {
			System.out.println("Exception successfully thrown for null argument.");
			passedTests++;
		}

		// Try a bad position
		try {
			jobList.add(200, new Job("t", 10, 10));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception successfully thrown for bad position.");
			passedTests++;
		}
		//Try a close to bad position
		try {
			jobList.add(4, new Job("t", 10, 10));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception successfully thrown for close to bad position.");
			passedTests++;
		}

		System.out.println("---------------------------");
		System.out.println("Testing JobList.contains(E item)");

		// Check if jobList contains a value it should:
		if (jobList.contains(testJob1)) {
			System.out.println("Correctly found contained job");
			passedTests++;
		} else {
			System.out.println("FAIL 1");
		}
		if (!jobList.contains(new Job("fakeJob", 1, 1))) {
			System.out.println("Correctly returned false when job didn't exist.");
			passedTests++;
		} else {
			System.out.println("FAIL 2");
		}
		try {
			jobList.contains(null);
		} catch (IllegalArgumentException e) {
			System.out.println("Exception successfully thrown for null argument");
			passedTests++;
		}

		System.out.println("---------------------------");
		System.out.println("Testing JobList.get(int)");

		// Check if correctly returns value
		if (jobList.get(0) == testJob1) {
			System.out.println("Correctly got first position job");
			passedTests++;
		} else {
			System.out.println("FAIL 3");
		}
		if (jobList.get(1) == testJob2) {
			System.out.println("Correctly got second position job");
			passedTests++;
		} else {
			System.out.println("FAIL 4");
		}

		// Try a bad position
		try {
			jobList.get(100);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception successfully thrown for bad position.");
			passedTests++;
		}

		System.out.println("---------------------------");
		System.out.println("Testing JobList.size()");

		// Check if it's correctly sized.
		if (jobList.size() == 3) {
			System.out.println("Correctly returned size");
			passedTests++;
		} else {
			System.out.println("FAIL 5 jobList.size() was: " + jobList.size() + ", expected: 3");
		}

		System.out.println("---------------------------");
		System.out.println("Testing Joblist.iterator()");
		
		//Make sure Joblist returns an iterator.
		JobListIterator iterator = jobList.iterator();
		if (JobListIterator.class.isInstance(iterator)) {
			System.out.println("Successfully returned an iterator.");
			passedTests++;
		}
		else {
			System.out.println("FAIL 12");
		}
		
		//Make sure iterator works
		int counter = 0;
		while (iterator.hasNext()) {
			iterator.next();
			counter++;
		}
		
		if (counter == 2) {
			System.out.println("Iterator correctly shifts through list");
			passedTests++;
		}
		else {
			System.out.println("FAIL 13");
		}
		
		
		System.out.println("---------------------------");
		System.out.println("Testing JobList.remove() and JobList.isEmpty()");

		// try a border case
		try {
			jobList.remove(3);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Exception successfully thrown for bad position.");
			passedTests++;
		}

		try {
			// Check if remove works correctly
			if (jobList.remove(2) == testJob3) {
				System.out.println("Correctly removed last index");
				passedTests++;
			} else {
				System.out.println("FAIL 6");
			}

			if (jobList.remove(0) == testJob1) {
				System.out.println("Correctly removed first index");
				passedTests++;
			} else {
				System.out.println("FAIL 7");
			}

			// Check if empty.
			if (jobList.isEmpty()) {
				System.out.println("FAIL 8");
			} else {
				System.out.println("Correctly returned false for is empty");
				passedTests++;
			}

			// Make sure the list is shifted
			if (jobList.get(0) == testJob2) {
				System.out.println("Correctly shifts down after jobs are deleted");
				passedTests++;
			} else {
				System.out.println("FAIL 9");
			}

			// Remove last index now that it has shifted
			// Check if remove works correctly
			if (jobList.remove(0) == testJob2) {
				System.out.println("Correctly removed first index after shift");
				passedTests++;
			} else {
				System.out.println("FAIL 10");
			}

			// Makes sure list is now empty.
			if (jobList.isEmpty()) {
				System.out.println("Correctly returned true for is empty");
				passedTests++;
			} else {
				System.out.println("FAIL 11");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Caught IndexOutOfBoundsExeption");
		}

		System.out.println("===========================");
		System.out.println("TESTS PASSED: " + passedTests + "/20");
	}

}
