package com.qa.trcrm.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.trcrm.utils.Log;

public class LogListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		Log.info("starting test case: "+result.getMethod().getMethodName() + " starting");
		Log.info(result.getMethod().getDescription());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Log.info("test case: "+result.getMethod().getMethodName() + " passed!");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		Log.error("test case: "+result.getMethod().getMethodName() + " failed!");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Log.warn("test case: "+result.getMethod().getMethodName() + " skipped!");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		Log.info(context.getName()+" Test started!");

	}

	@Override
	public void onFinish(ITestContext context) {

		Log.info(context.getName()+" Test ended!");

	}

}
