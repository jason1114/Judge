import java.io.ByteArrayInputStream;
import java.util.Scanner;


public class FindLatestStableVersion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
                System.in
//                new ByteArrayInputStream(("7\n" +
//                        "10.3.5\n" +
//                        "9.2.16\n" +
//                        "11.4.20\n" +
//                        "11.3.14\n" +
//                        "2.1.12\n" +
//                        "12.4\n" +
//                        "13.5").getBytes())
        );
        String latest = null;
        int latestMajor = 0 ,latestMinor = 0 ,latestPatch = 0;
        int totalLines = scanner.nextInt();
        if (totalLines <= 0) {
            System.out.println("no stable available");
            return;
        }
        for (int i = 0; i < totalLines; i++) {
            String versionString = scanner.next();
            String[] raw = versionString.split("\\.");
            if (raw.length < 2) {
                continue;
            } else {
                int major = Integer.valueOf(raw[0]);
                int minor = Integer.valueOf(raw[1]);
                int patch = raw.length > 2?Integer.valueOf(raw[2]):0;
                if (Integer.valueOf(raw[1]) % 2 == 0 ) {
                    if (major > latestMajor) {
                        latestMajor = major;
                        latestMinor = minor;
                        latestPatch = patch;
                        latest = versionString;
                    } else if (major == latestMajor) {
                        if (minor > latestMinor) {
                            latestMajor = major;
                            latestMinor = minor;
                            latestPatch = patch;
                            latest = versionString;
                        } else if (minor == latestMinor) {
                            if (patch > latestPatch) {
                                latestMajor = major;
                                latestMinor = minor;
                                latestPatch = patch;
                                latest = versionString;
                            }
                        }
                    }
                }
            }
        }
        if (latestMajor > 0) {
            System.out.println(latest);
        } else {
            System.out.println("no stable available");
        }
    }
}
