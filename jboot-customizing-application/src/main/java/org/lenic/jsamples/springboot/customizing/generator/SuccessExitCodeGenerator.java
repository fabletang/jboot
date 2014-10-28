package org.lenic.jsamples.springboot.customizing.generator;

import org.springframework.boot.ExitCodeGenerator;

public class SuccessExitCodeGenerator implements ExitCodeGenerator {

	@Override
	public int getExitCode() {
		return 0;
	}
}