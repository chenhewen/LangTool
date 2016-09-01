package com.robust.cmd;

public abstract class BaseCmd {

	protected String[] mArgs;
	private int mNextArg;
	private String mCurArgData;

	public void run(String[] args) {
		mArgs = args;

		onRun();
	}

	public abstract void onRun();

	public void printInfo() {
	}

	public String nextOption() {
		if (mCurArgData != null) {
			String prev = mArgs[mNextArg - 1];
			throw new IllegalArgumentException("No argument expected after \"" + prev + "\"");
		}
		if (mNextArg >= mArgs.length) {
			return null;
		}
		String arg = mArgs[mNextArg];
		if (!arg.startsWith("-")) {
			return null;
		}
		mNextArg++;
		if (arg.equals("--")) {
			return null;
		}
		if (arg.length() > 1 && arg.charAt(1) != '-') {
			if (arg.length() > 2) {
				mCurArgData = arg.substring(2);
				return arg.substring(0, 2);
			} else {
				mCurArgData = null;
				return arg;
			}
		}
		mCurArgData = null;
		return arg;
	}

	/**
	 * Return the next argument on the command line, whatever it is; if there
	 * are no arguments left, return null.
	 */
	public String nextArg() {
		if (mCurArgData != null) {
			String arg = mCurArgData;
			mCurArgData = null;
			return arg;
		} else if (mNextArg < mArgs.length) {
			return mArgs[mNextArg++];
		} else {
			return null;
		}
	}

	/**
	 * Return the next argument on the command line, whatever it is; if there
	 * are no arguments left, throws an IllegalArgumentException to report this
	 * to the user.
	 */
	public String nextArgRequired() {
		String arg = nextArg();
		if (arg == null) {
			String prev = mArgs[mNextArg - 1];
			throw new IllegalArgumentException("Argument expected after \"" + prev + "\"");
		}
		return arg;
	}
}
