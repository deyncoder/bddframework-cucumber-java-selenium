package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
	(
			features=".//Features/", 													// for running all feature files
//			features={".//Features/Login.feature",".//Features/Customers.feature"}, 	// for running multiple feature files
//			features=".//Features/Customers.feature", 									// feature path
//			features=".//Features/Login.feature", 										// feature path
			glue="stepDefinitions",														// pkg that contains the steps
			dryRun=false, 																// cross check whether every step is having corresponding methods/step definition implemented or not
			monochrome=true,  															// remove unnecessary characters in console window for clearer output
			plugin= {"pretty", 
					 "html:test-output"} ,												// generate report
			tags= {"@sanity"}
//			tags= {"@sanity","@regression"}												// sanity AND regression
//			tags= {"@sanity,@regression"}												// sanity OR regression
			)
public class TestRun {
	
}
