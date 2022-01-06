public class EncryptionProtocols {

	//---  Constants   ----------------------------------------------------------------------------
	
	private static final int [] CEREBUS = {18,31};
	private static final int CEREBUS_INIT_VALUE = 32;
	private static final int CEREBUS_ALPH_SIZE = 128;
	
	private static final int[][] OUROBOROS = {{3, 1, 0, 2},{2, 5, 4, 1, 3, 0}};

	public static String encrypt(String Protocol,int N,String in) {
		switch(Protocol) {
			case "Cerebus":
				return encryptCerebus(CEREBUS[N], in);
			case "Ouroboros":
				return encryptOuroboros(OUROBOROS[N], in);
			default:
				return null;
		}
	}

	public static String decrypt(String Protocol,int N,String in) {
		switch(Protocol) {
			case "Cerebus":
				return decryptCerebus(CEREBUS[N], in);
			case "Ouroboros":
				return decryptOuroboros(OUROBOROS[N], in);
			default:
				return null;
		}
	}
	
	//---  Cerebus   ------------------------------------------------------------------------------
		
	private static String encryptCerebus(int am, String in) {
		StringBuilder out = new StringBuilder();
		String use = in.toLowerCase();
		for(char a : use.toCharArray()) {
			out.append((char)(((a + am - CEREBUS_INIT_VALUE) % CEREBUS_ALPH_SIZE) + CEREBUS_INIT_VALUE)+"");
		}
		return out.toString();
	}
	
	private static String decryptCerebus(int am, String in) {
		StringBuilder out = new StringBuilder();
		String use = in.toLowerCase();
		for(char a : use.toCharArray()) {
			out.append((char)(((a - am  - CEREBUS_INIT_VALUE + CEREBUS_ALPH_SIZE) % CEREBUS_ALPH_SIZE) + CEREBUS_INIT_VALUE)+"");
		}
		return out.toString();
	}
		
	//---  Ouroboros   ----------------------------------------------------------------------------
		
	private static String encryptOuroboros(int[] am, String in) {
		StringBuilder out = new StringBuilder();
		String use = in + new String(new char[am.length - (in.length() % am.length)]).replace("\0", " ");
		for(int i = 0; i < use.length(); i += am.length) {
			char[] next = new char[am.length];
			for(int j = 0; j < am.length; j++) {
				next[j] = use.charAt(i + am[j]);
			}
			out.append(new String(next));
		}
		return out.toString();
	}
	
	private static String decryptOuroboros(int[] am, String in) {
		StringBuilder out = new StringBuilder();
		String use = in+"";
		for(int i = 0; i < use.length(); i += am.length) {
			char[] next = new char[am.length];
			for(int j = 0; j < am.length; j++) {
				next[am[j]] = use.charAt(i + j);
			}
			out.append(new String(next));
		}
		return out.toString();
	}
}
	
