package jrapl;

import java.lang.reflect.Field;

public class EnergyCheckUtils {
	public native static int scale(int freq);
	public native static int[] freqAvailable();
	
	public native static double[] GetPackagePowerSpec();
	public native static double[] GetDramPowerSpec();
	public native static void SetPackagePowerLimit(int socketId, int level, double costomPower);
	public native static void SetPackageTimeWindowLimit(int socketId, int level, double costomTimeWin);
	public native static void SetDramTimeWindowLimit(int socketId, int level, double costomTimeWin);
	public native static void SetDramPowerLimit(int socketId, int level, double costomPower);
	public native static int ProfileInit();
	public native static int GetSocketNum();
	public native static String EnergyStatCheck();
	public native static void ProfileDealloc();
	public native static void SetPowerLimit(int ENABLE);
	public static int wraparoundValue;	
	
	public static int socketNum;
	static {
		// tababako1, again, fix path
		try {
			Field fieldSysPath = ClassLoader.class
					.getDeclaredField("sys_paths");
			fieldSysPath.setAccessible(true);
			fieldSysPath.set(null, null);
		} catch (Exception e) {

		}
		//System.loadLibrary("CPUScaler");
		try {
		NativeUtils.loadLibraryFromJar("/jrapl/libCPUScaler.so");
		} catch (Exception e) {
			e.printStackTrace();
		}
		wraparoundValue = ProfileInit();
		socketNum = GetSocketNum();
	}
	
	/**
	 * @return an array of current energy information.
	 * The first entry is: Dram/uncore gpu energy(depends on the cpu architecture.
	 * The second entry is: CPU energy
	 * The third entry is: Package energy
	 */
	
	public static double[] getEnergyStats() {
		socketNum = GetSocketNum();
		String EnergyInfo = EnergyStatCheck();
		//System.out.println("energy is: " + EnergyInfo);
		/*One Socket*/
		if(socketNum == 1) {
			double[] stats = new double[3];
			String[] energy = EnergyInfo.split("#");
			
			stats[0] = Double.parseDouble(energy[0]);
			stats[1] = Double.parseDouble(energy[1]);
			stats[2] = Double.parseDouble(energy[2]);
			
			return stats;
		
		} else {
		/*Multiple sockets*/
			String[] perSockEner = EnergyInfo.split("@");
			double[] stats = new double[3*socketNum];
			int count = 0;

			
			for(int i = 0; i < perSockEner.length; i++) {
				String[] energy = perSockEner[i].split("#");
				for(int j = 0; j < energy.length; j++) {
					count = i * 3 + j;	//accumulative count
					stats[count] = Double.parseDouble(energy[j]);
				}
			}
			return stats;
		}

	}

  public static void DeallocProfile() {
		ProfileDealloc();
  }
	
	public static void main(String[] args) {
		//Info info = (Info)energyInfo.EnergyStatCheck();
		/*For jni header generation*/
		//SetPowerLimit(0);
	//	double[] info1 = GetPackagePowerSpec();
//		double[] info2 = GetDramPowerSpec();



		//for(int i = 0; i < 4; i++)
			//System.out.println("package: " + info1[i]);
		
		//SetPackagePowerLimit(0, 0, 150.0);
//		SetPackagePowerLimit(1, 0, 150.0);
//		SetDramPowerLimit(0, 0, 130.0);
//		SetDramPowerLimit(1, 0, 130.0);

		//SetPackageTimeWindowLimit(0, 1, 1.0);
//		SetPackageTimeWindowLimit(1, 1, 1.0);
		
		/*
		EnergyStatCheck();
		ProfileDealloc();

		int[] a = freqAvailable();
		scale(10000);
		*/
		


		double[] before = getEnergyStats();
		//System.out.println(before);
		try {
			Thread.sleep(5000);
		} catch(Exception e) {
		}
		double[] after = getEnergyStats();
		//System.out.println(after);
		//System.out.println(after_info[0][0]);
		//System.out.println(before_info[0][0]);
		for(int i = 0; i < socketNum; i++) {
			//System.out.println("dram: " + (after[0] - before[0]) / 60.0 + " cpu: " + (after[1] - before[1]) / 60.0 + " package: " + (after[2] - before[2]) / 300.0);
			System.out.println("dram: " + (after[0] - before[0])  + " cpu: " + (after[1] - before[1])  + " package: " + (after[2] - before[2])  );
		}
		ProfileDealloc();
	}
}
