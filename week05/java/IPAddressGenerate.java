import java.util.Random;

public class IPAddressGenerate {
    public String[] getIPAddress(int num) {
        String[] res = new String[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            res[i] = random.nextInt(256) + "." + random.nextInt(256) + "."
                    + random.nextInt(256) + "." + random.nextInt(256) + ":"
                    + random.nextInt(9999);
        }
        return res;
    }
}