package com.robust;

import java.io.IOException;
import java.util.Arrays;

import org.jdom2.JDOMException;

import com.robust.cmd.DiffCmd;
import com.robust.cmd.FormatCmd;
import com.robust.cmd.HelpCmd;
import com.robust.cmd.RetranCmd;
import com.robust.cmd.RmCmd;
import com.robust.cmd.UpdateCmd;

public class Main {

	public static void main(String[] args) throws IOException, JDOMException {

		/*String cmdline = "update G:/output/values.xml G:/output/values2.xml";
		args = cmdline.split(" ");*/
		
		int cmdAndArgsLength = args.length;

		if (cmdAndArgsLength == 0) {
			new HelpCmd().run(args);
			return;
		}

//		int argsLength = cmdAndArgsLength - 1;
		String cmd = args[0];
		String[] cmdArgs = Arrays.copyOfRange(args, 1, cmdAndArgsLength);
		
		if (cmd.equals("help")) {
			new HelpCmd().run(cmdArgs);
		} else if (cmd.equals("diff")) {
			new DiffCmd().run(cmdArgs);
		} else if (cmd.equals("retran")) {
			new RetranCmd().run(cmdArgs);
		} else if (cmd.equals("rm")) {
			new RmCmd().run(cmdArgs);
		} else if (cmd.equals("format")) {
			new FormatCmd().run(cmdArgs);
		} else if (cmd.equals("update")) {
			new UpdateCmd().run(cmdArgs);
		}
	}

}
