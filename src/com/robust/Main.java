package com.robust;

import java.io.IOException;
import java.util.Arrays;

import org.jdom2.JDOMException;

import com.robust.cmd.DiffCmd;

public class Main {

	public static void main(String[] args) throws IOException, JDOMException {

		int cmdAndArgsLength = args.length;

		if (cmdAndArgsLength == 0) {
			return;
		}

		int argsLength = cmdAndArgsLength - 1;
		String cmd = args[0];
		String[] cmdArgs = Arrays.copyOf(args, argsLength);
		
		if (cmd.equals("diff")) {
			new DiffCmd().run(cmdArgs);
		} 

	}

}
