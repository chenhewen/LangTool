package com.robust;

import java.io.IOException;
import java.util.Arrays;

import org.jdom2.JDOMException;

import com.robust.cmd.DiffCmd;
import com.robust.cmd.FormatCmd;
import com.robust.cmd.RetranCmd;
import com.robust.cmd.RmCmd;

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
		} else if (cmd.equals("retran")) {
			new RetranCmd().run(cmdArgs);
		} else if (cmd.equals("rm")) {
			new RmCmd().run(cmdArgs);
		} else if (cmd.equals("format")) {
			new FormatCmd().run(cmdArgs);
		}

	}

}
