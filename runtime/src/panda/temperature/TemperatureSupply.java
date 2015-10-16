package panda.runtime.temperature;

import panda.runtime.util.OsUtil;

public class TemperatureSupply {
	private BareMetalTemperature bareMetalTemperature;
	
	public TemperatureSupply() {
		// Load specific OS version for BareMetalBattery
		switch(OsUtil.getOsType()) {
			case WINDOWS:
				System.err.println("Windows not supported. Exiting.");
				System.exit(1);
        break;
			case MACOS:
				bareMetalBattery = new OSXBareMetalBattery();
        break;
			case LINUX:
				bareMetalTemperature = new UnixBareMetalTemperature();
				break;
			case ANDROID:
				System.err.println("Windows not supported. Exiting.");
				System.exit(1);
        break;
			case NONE:
				System.err.println("Encountered unsupported operating system. Exiting.");
				System.exit(1);
        break;
		}

	}
	
	public int getCurrentTemperature() {
		return bareMetalTemperature.getCurrentTemperature();
	}
	
}
