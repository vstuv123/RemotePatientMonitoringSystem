package UserManagement;

import Helper.Helper;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class User {
    private static int noOfPatients = 0;
    private static int patientCount = -1;
    private static int noOfDoctors = 0;
    private static int doctorCount = -1;
    private static int noOfAdmins = 0;
    private static int adminCount = -1;
    private static final int MAX_USERS = 50;
    private long unique_id;
    private String name;
    private String email;
    private String password;
    private String contactNumber;
    private LocalDate dob;
    private String role;
    private String address;
    private long assignedTo; // contains doctor Id of Patient
    private boolean isLoggedIn;
    private static User[] registeredPatients = new Patient[MAX_USERS];
    private static User[] registeredDoctors = new Doctor[MAX_USERS];
    private static User[] registeredAdmins = new Administrator[MAX_USERS];
    Helper helper = new Helper();


    public User() {
        this.unique_id = randomNumberGenerator();
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static User[] getRegisteredDoctors() {
        return registeredDoctors;
    }

    public static User[] getRegisteredPatients() {
        return registeredPatients;
    }

    public static User[] getRegisteredAdmins() {
        return registeredAdmins;
    }

    public static int getNoOfPatients() {
        return noOfPatients;
    }

    public static void setNoOfPatients(int noOfPatients) {
        User.noOfPatients = noOfPatients;
    }

    public static int getPatientCount() {
        return patientCount;
    }

    public static void setPatientCount(int patientCount) {
        User.patientCount = patientCount;
    }

    public static int getNoOfDoctors() {
        return noOfDoctors;
    }

    public static void setNoOfDoctors(int noOfDoctors) {
        User.noOfDoctors = noOfDoctors;
    }

    public static int getDoctorCount() {
        return doctorCount;
    }

    public static void setDoctorCount(int doctorCount) {
        User.doctorCount = doctorCount;
    }

    public static int getNoOfAdmins() {
        return noOfAdmins;
    }

    public static int getAdminCount() {
        return adminCount;
    }

    public static int getNoOfUsers() { return noOfPatients + noOfDoctors + noOfAdmins; }

    public long getUnique_id() { return unique_id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(long assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setRole(long assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void registerUser(String adder) {
        Scanner scanner = new Scanner(System.in);

        if (!(adder.equals("Patient") || adder.equals("Doctor"))) {
            noOfAdmins++;
            adminCount++;
            if (noOfAdmins == MAX_USERS + 1) {
                registeredAdmins = new Administrator[MAX_USERS + 50];
                noOfAdmins = 0;
            }
            registeredAdmins[adminCount] = new Administrator();
            System.out.print("Enter " + adder + " Name: ");
            registeredAdmins[adminCount].name = scanner.nextLine();

            System.out.print("Enter " + adder + " Email: ");
            String email = scanner.nextLine();
            validation(email, registeredPatients, false, adder);
            registeredPatients[patientCount].email = email;

            System.out.print("Enter " + adder + " Password: ");
            registeredAdmins[adminCount].password = scanner.nextLine();
            System.out.print("Enter " + adder + " Contact Number: ");
            registeredAdmins[adminCount].contactNumber = scanner.nextLine();

            System.out.print("Enter " + adder + " Date Of Birth (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            registeredAdmins[adminCount].dob = LocalDate.parse(date);

            System.out.print("Enter " + adder + " Address: ");
            registeredAdmins[adminCount].address = scanner.nextLine();
            if (adder.equals("your")) {
                System.out.print("Enter " + adder + " Role (Admin / Doctor / Patient): ");
                registeredAdmins[adminCount].role = scanner.nextLine();
            }else {
                registeredAdmins[adminCount].role = adder;
            }

            System.out.println("\nAdmin Registered Successfully");
            Administrator.getLogs().add("New Admin registered in System with ID " +
                    registeredAdmins[adminCount].getUnique_id() + " at " +
                    Helper.currentTimestamp());

            ObjectStorage.userObject = registeredAdmins[adminCount];
        }else if (adder.equals("Patient")) {
            noOfPatients++;
            patientCount++;

            if (noOfPatients == MAX_USERS + 1) {
                registeredPatients = new Patient[MAX_USERS + 50];
                noOfPatients = 0;
            }
            registeredPatients[patientCount] = new Patient();
            System.out.print("Enter " + adder + " Name: ");
            registeredPatients[patientCount].name = scanner.nextLine();

            System.out.print("Enter " + adder + " Email: ");
            String email = scanner.nextLine();
            validation(email, registeredPatients, false, adder);
            registeredPatients[patientCount].email = email;

            System.out.print("Enter " + adder + " Password: ");
            registeredPatients[patientCount].password = scanner.nextLine();
            System.out.print("Enter " + adder + " Contact Number: ");
            registeredPatients[patientCount].contactNumber = scanner.nextLine();

            System.out.print("Enter " + adder + " Date Of Birth (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            registeredPatients[patientCount].dob = LocalDate.parse(date);

            System.out.print("Enter " + adder + " Address: ");
            registeredPatients[patientCount].address = scanner.nextLine();

            System.out.print("Enter id of Doctor to which " + adder + " is AssignedTo: ");
            long assignedTo = scanner.nextLong();

            if (helper.isValidDoctorID(assignedTo)) {
                registeredPatients[patientCount].assignedTo = assignedTo;
            }else {
                System.out.println("You have entered Incorrect Doctor Id. Plz enter correct one");
            }
            scanner.nextLine();
            registeredPatients[patientCount].role = adder;

            System.out.println("\nPatient Registered Successfully");
            Administrator.getLogs().add("New Patient registered in System with ID " +
                    registeredPatients[patientCount].getUnique_id() + " at " +
                    Helper.currentTimestamp());

            ObjectStorage.userObject = registeredPatients[patientCount];
        }else {
            noOfDoctors++;
            doctorCount++;
            if (noOfDoctors == MAX_USERS + 1) {
                registeredDoctors = new Doctor[MAX_USERS + 50];
                noOfDoctors = 0;
            }
            registeredDoctors[doctorCount] = new Doctor();
            System.out.print("Enter " + adder + " Name: ");
            registeredDoctors[doctorCount].name = scanner.nextLine();

            System.out.print("Enter " + adder + " Email: ");
            String email = scanner.nextLine();
            validation(email, registeredDoctors, false, adder);
            registeredDoctors[doctorCount].email = email;

            System.out.print("Enter " + adder + " Password: ");
            registeredDoctors[doctorCount].password = scanner.nextLine();
            System.out.print("Enter " + adder + " Contact Number: ");
            registeredDoctors[doctorCount].contactNumber = scanner.nextLine();

            System.out.print("Enter " + adder + " Date Of Birth (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            registeredDoctors[doctorCount].dob = LocalDate.parse(date);

            System.out.print("Enter " + adder + " Address: ");
            registeredDoctors[doctorCount].address = scanner.nextLine();
            registeredDoctors[doctorCount].role = adder;

            System.out.println("\nDoctor Registered Successfully");
            Administrator.getLogs().add("New Doctor registered in System with ID " +
                    registeredDoctors[doctorCount].getUnique_id() + " at " +
                    Helper.currentTimestamp());

            ObjectStorage.userObject = registeredDoctors[doctorCount];
        }
    }

    public void loginUser(String loginPerson) {
        String email;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your email: ");
        email = scanner.nextLine();

        if (!(loginPerson.equals("Patient") || loginPerson.equals("Doctor"))) {
            validation(email, registeredAdmins, true, loginPerson);
        }else if (loginPerson.equals("Patient")) {
            validation(email, registeredPatients, true, loginPerson);
        }else {
            validation(email, registeredDoctors, true, loginPerson);
        }
    }

    public void logout() {
        isLoggedIn = false;
        SessionStorage.loggedInUser = null;
        Administrator.getLogs().add("User Logged out from System with ID " +
                this.getUnique_id() + " at " +
                Helper.currentTimestamp());
    }

    public void userProfile() {
        System.out.println("\n\tUser ID: " + this.unique_id);
        System.out.println("\tUser Name: " + this.name);
        System.out.println("\tUser Email: " + this.email);
        System.out.println("\tUser Contact Number: " + this.contactNumber);
        System.out.println("\tUser Date Of Birth: " + this.dob);
        System.out.println("\tUser Address: " + this.address);
        System.out.println("\tUser Role: " + this.role + "\n");
    }

    public long randomNumberGenerator() {
        Random random = new Random();
        return 100000 + random.nextLong(900000);
    }

    public void validation(String email, User[] registeredUsers, boolean isLogin, String adder) {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;

        if (!(adder.equals("Patient") || adder.equals("Doctor"))) {
            Administrator[] admins = (Administrator[]) registeredUsers;
            int adminCount = User.getNoOfAdmins();
            for (int i = 0; i < adminCount; i++) {
                if (admins[i].getEmail() != null && admins[i].getEmail().equals(email)) {
                    if (!isLogin) {
                        System.out.print("Email Already exists. Please enter your email again: ");
                        String em = scanner.nextLine();
                        validation(admins[i].getEmail(), admins, false, adder);
                        admins[i].setEmail(em);
                    }else {
                        found = true;
                        System.out.print("Please enter your password: ");
                        String password = scanner.nextLine();
                        if (admins[i].getPassword().equals(password)) {
                            System.out.println("Welcome " + admins[i].getName() + ", Login Successful");
                            isLoggedIn = true;
                            SessionStorage.loggedInUser = admins[i];
                            Administrator.getLogs().add("User Logged In System with ID " +
                                    admins[i].getUnique_id() + " at " +
                                    Helper.currentTimestamp());
                            break;
                        }else {
                            System.out.println("Email or Password Incorrect");
                            break;
                        }
                    }
                }
            }
        }else if (adder.equals("Patient")) {
            Patient[] patients = (Patient[]) registeredUsers;
            int patientCount = User.getNoOfPatients();
            for (int i = 0; i < patientCount; i++) {
                if (patients[i].getEmail() != null && patients[i].getEmail().equals(email)) {
                    if (!isLogin) {
                        System.out.print("Email Already exists. Please enter your email again: ");
                        String em = scanner.nextLine();
                        validation(patients[i].getEmail(), patients, false, adder);
                        patients[i].setEmail(em);
                    }else {
                        found = true;
                        System.out.print("Please enter your password: ");
                        String password = scanner.nextLine();
                        if (patients[i].getPassword().equals(password)) {
                            System.out.println("Welcome " + patients[i].getName() + ", Login Successful");
                            isLoggedIn = true;
                            SessionStorage.loggedInUser = patients[i];
                            Administrator.getLogs().add("User Logged In System with ID " +
                                    patients[i].getUnique_id() + " at " +
                                    Helper.currentTimestamp());
                            break;
                        }else {
                            System.out.println("Email or Password Incorrect");
                            break;
                        }
                    }
                }
            }
        }else {
            Doctor[] doctors = (Doctor[]) registeredUsers;
            int doctorCount = User.getNoOfDoctors();
            for (int i = 0; i < doctorCount; i++) {
                if (doctors[i].getEmail() != null && doctors[i].getEmail().equals(email)) {
                    if (!isLogin) {
                        System.out.print("Email Already exists. Please enter your email again: ");
                        String em = scanner.nextLine();
                        validation(doctors[i].getEmail(), doctors, false, adder);
                        doctors[i].setEmail(em);
                    }else {
                        found = true;
                        System.out.print("Please enter your password: ");
                        String password = scanner.nextLine();
                        if (doctors[i].getPassword().equals(password)) {
                            System.out.println("Welcome " + doctors[i].getName() + ", Login Successful");
                            isLoggedIn = true;
                            SessionStorage.loggedInUser = doctors[i];
                            Administrator.getLogs().add("User Logged In System with ID " +
                                    doctors[i].getUnique_id() + " at " +
                                    Helper.currentTimestamp());
                            break;
                        }else {
                            System.out.println("Email or Password Incorrect");
                            break;
                        }
                    }
                }
            }
        }

        if (isLogin) {
            if (!found) {
                System.out.println("Email not exists. Please enter email again: ");
                email = scanner.nextLine();
                validation(email, registeredUsers, true, adder);
            }
        }
    }
}
