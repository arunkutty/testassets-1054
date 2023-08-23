package customcode;

import com.ibm.rational.test.lt.kernel.IDataArea;
import com.ibm.rational.test.lt.kernel.services.IEngineInfo;
import com.ibm.rational.test.lt.kernel.services.ITestExecutionServices;

/**
 * @author unknown
 */
public class ProvideDelayV2 implements com.ibm.rational.test.lt.kernel.custom.ICustomCode2 {

	/**
	 * Instances of this will be created using the no-arg constructor.
	 */
	public ProvideDelayV2() {
	}
	
	int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}

	/**
	 * For javadoc of ICustomCode2 and ITestExecutionServices interfaces, select 'Help Contents' in the
	 * Help menu and select 'Extending Rational Performance Tester functionality' -> 'Extending test execution with custom code'
	 */
	public String exec(ITestExecutionServices tes, String[] args) {
		tes.getTestLogManager().reportMessage("Find out number of VUs");
		int vuLoad = ((IEngineInfo)tes.findDataArea(IDataArea.ENGINE).get(IEngineInfo.KEY)).getActiveUsers();
		Integer ret = (int) Math.min(vuLoad/randomWithRange(7,13), randomWithRange(4, 6)); //increase delay slightly to max out between 3 and 5
		if (ret < 1){ //delay of at least 1
			ret = 1;
		}
		String retStr = ret.toString();
		return retStr;
	}

}
