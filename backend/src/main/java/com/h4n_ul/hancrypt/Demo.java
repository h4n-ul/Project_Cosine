package com.h4n_ul.hancrypt;

import java.util.Scanner;
import java.util.UUID;

public class Demo {
    private Scanner input;

    public void hc() {
        HanCrypt hancrypt = new HanCrypt();

        input = new Scanner(System.in);

        System.out.println("======================================User======================================");
        System.out.print("Type your password: ");String string = input.nextLine();
        System.out.println("=====================================Server=====================================");
        System.out.print("Type your password strength: ");int passwordStrength = input.nextInt();
        System.out.print("Type your salt resolution: ");int saltRes = input.nextInt();
        String uuid = UUID.randomUUID().toString();
        CryPackage password = hancrypt.hash(uuid, string, passwordStrength, saltRes);

        String hashed = password.getHash();
        String salt = password.getSalt();
        int passes = password.getPasses();

        System.out.println("======================================Input=====================================");
        System.out.println("{'string': '" + string + "', 'password_strength': " + passwordStrength + ", 'uuid': '" + uuid + "'}");

        System.out.println("=================================Hashed Password================================");
        System.out.println(hashed);

        if (salt != "") {
            System.out.println("======================================Salt======================================");
            System.out.println(salt);
        }
        System.out.println("===================================Pass count===================================");
        System.out.println(passes + " / " + HanCrypt.PASS_LIMIT);
        System.out.println("=====================================Object=====================================");
        System.out.println(password);
        System.out.println("================================================================================");
        System.out.println(hancrypt.confirm(uuid, string, passes, hashed, salt) ? "Pass" : "Fail");
    }

    public void hca() {
        HanCrypt hancrypt = new HanCrypt();

        input = new Scanner(System.in);

        System.out.println("======================================User======================================");
        System.out.print("Type your password: ");String string = input.nextLine();
        System.out.println("=====================================Server=====================================");
        System.out.print("Type your password strength: ");int passwordStrength = input.nextInt();
        System.out.print("Type your salt resolution: ");int saltRes = input.nextInt();
        String uuid = UUID.randomUUID().toString();
        String password = hancrypt.hash_allinone(uuid, string, passwordStrength, saltRes);

        System.out.println("======================================Input=====================================");
        System.out.println("{'string': '" + string + "', 'password_strength': " + passwordStrength + ", 'uuid': '" + uuid + "'}");

        System.out.println("=================================Hashed Password================================");
        System.out.println(password);
        System.out.println("================================================================================");
        System.out.println(hancrypt.confirm_allinone(uuid, string, password) ? "Pass" : "Fail");
    }

    public void hcl() {
        HanCrypt hancrypt = new HanCrypt();

        input = new Scanner(System.in);

        System.out.println("======================================User======================================");
        System.out.print("Type your password: ");String string = input.nextLine();
        String uuid = UUID.randomUUID().toString();
        CryPackage password = hancrypt.hash(uuid, string);
        // CryPackage password = hashvault.pwHashTest();

        String hashed = password.getHash();
        String salt = password.getSalt();
        int passes = password.getPasses();

        System.out.println("======================================Input=====================================");
        System.out.println("{'string': '" + string + "', 'uuid': '" + uuid + "'}");

        System.out.println("=================================Hashed Password================================");
        System.out.println(hashed);
        System.out.println("======================================Salt======================================");
        System.out.println(salt);
        System.out.println("===================================Pass count===================================");
        System.out.println(passes + " / " + HanCrypt.PASS_LIMIT);
        System.out.println("=====================================Object=====================================");
        System.out.println(password);
        System.out.println("================================================================================");
        System.out.println(hancrypt.confirm(uuid, string, passes, hashed, salt) ? "Pass" : "Fail");
    }

    public void hcc() {
        HanCrypt hancrypt = new HanCrypt();

        input = new Scanner(System.in);

        System.out.println("======================================User======================================");
        System.out.print("Type your password: ");String string = input.nextLine();
        System.out.println("====================================Database====================================");
        System.out.print("Type your hashed password: ");String hash = input.nextLine();
        System.out.print("Type your uid: ");String uid = input.nextLine();
        System.out.print("Type your salt: ");String salt = input.nextLine();
        System.out.print("Type your password passes: ");int passes = input.nextInt();

        System.out.println("======================================Pass?=====================================");
        System.out.println(hancrypt.confirm(uid, string, passes, hash, salt) ? "Pass" : "Fail");
    }

    public void hcca() {
        HanCrypt hancrypt = new HanCrypt();

        input = new Scanner(System.in);

        System.out.println("======================================User======================================");
        System.out.print("Type your hashed password: ");String str = input.nextLine();
        System.out.println("====================================Database====================================");
        System.out.print("Type your uid: ");String uid = input.nextLine();
        System.out.print("Type your password: ");String hash = input.nextLine();

        System.out.println("======================================Pass?=====================================");
        System.out.println(hancrypt.confirm_allinone(uid, str, hash) ? "Pass" : "Fail");
    }

    public void hct() {
        HanCrypt hancrypt = new HanCrypt();
        CryPackage password = hancrypt.hashTest();
        String hashed = password.getHash();
        String salt = password.getSalt();
        int passes = password.getPasses();
        System.out.println("=================================Hashed Password================================");
        System.out.println(hashed);
        System.out.println("======================================Salt======================================");
        System.out.println(salt);
        System.out.println("===================================Pass count===================================");
        System.out.println(passes + " / " + HanCrypt.PASS_LIMIT);
        System.out.println("=====================================Object=====================================");
        System.out.println(password);
        System.out.println("================================================================================");
    }
}